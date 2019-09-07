package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.3
 * @version 0.3.3
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

	private static void applyReactionReset() {
		if(getCharacterViewed().isVisiblyPregnant()){
			getCharacterViewed().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getCharacterViewed(), true);
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
				if(Objects.equals(getCompanionCharacter(), targetedCharacterForSex)) {
					companionCharacter = (NPC) Main.game.getPlayer().getCompanions().stream().filter((npc) -> !npc.equals(getCharacterViewed())).findFirst().get();
				}
			} else {
				companionCharacter = null;
			}
		}
		return companionCharacter;
	}

	private static String getTextFilePath() {
		if(getCharacterViewed().isRelatedTo(Main.game.getPlayer())) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}

	private static String getThreesomeTextFilePath() {
		if(getCharacterViewed().isRelatedTo(Main.game.getPlayer()) || (getCompanionCharacter()!=null && getCompanionCharacter().isRelatedTo(Main.game.getPlayer()))) {
			return "characters/offspring/slave";
		} else {
			return "misc/slaveDialogue";
		}
	}
	
	private static boolean isCompanionSexPublic() {
		return Main.game.getPlayer().getLocationPlace().isPopulated()
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)
				&& !Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS);
	}

//	private static boolean isSittingSex() {
//		return Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_SEATING_AREA)
//				|| Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_VIP_AREA);
//	}
	
	
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
				List<GameCharacter> companions = Main.game.getPlayer().getCompanions();
				if (index == 1) { //TODO improve descriptions and affection hit from rape
					
					if(Main.game.isNonConEnabled() && !getCharacterViewed().isAttractedTo(Main.game.getPlayer())) {
						if(!getTargetedCharacterForSex().isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
							return new Response("Rape", getTargetedCharacterForSex().getCompanionSexRejectionReason(true), null);
						}
						return new ResponseSex("Rape", UtilText.parse(getTargetedCharacterForSex(), "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter..."),
								false, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getTargetedCharacterForSex()),
								null,
								null,
								(getTargetedCharacterForSex().isSlave() && getTargetedCharacterForSex().hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
									?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
									:new ArrayList<>())),
								AFTER_SEX,
								UtilText.parseFromXMLFile(getTextFilePath(), "RAPE_START", getTargetedCharacterForSex())) {
							@Override
							public void effects() {
								applyReactionReset();
								int affection = 0;
								if(isCompanionSexPublic()) {
									if(getTargetedCharacterForSex().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
										affection+=5;
									} else if(getTargetedCharacterForSex().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
										affection-=25;
									}
								}
								if(getTargetedCharacterForSex().getFetishDesire(Fetish.FETISH_NON_CON_SUB).isPositive()) {
									affection+=5;
								} else {
									affection-=25;
								}
								Main.game.getTextEndStringBuilder().append(getTargetedCharacterForSex().incrementAffection(Main.game.getPlayer(), affection));
							}
						};
					
					} else {
						if(!getTargetedCharacterForSex().isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
							return new Response("Sex", getTargetedCharacterForSex().getCompanionSexRejectionReason(true), null);
						}
						return new ResponseSex("Sex", UtilText.parse(getTargetedCharacterForSex(), "Have sex with [npc.name]."),
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getTargetedCharacterForSex()),
								null,
								null,
								(getTargetedCharacterForSex().isSlave() && getTargetedCharacterForSex().hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
										?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
										:new ArrayList<>())) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								AFTER_SEX,
								UtilText.parseFromXMLFile(getTextFilePath(), "SEX_START", getTargetedCharacterForSex())) {
							@Override
							public void effects() {
								applyReactionReset();
								int affection = 5;
								if(isCompanionSexPublic()) {
									if(getTargetedCharacterForSex().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
										affection+=5;
									} else if(getTargetedCharacterForSex().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
										affection-=25;
									}
								}
								Main.game.getTextEndStringBuilder().append(getTargetedCharacterForSex().incrementAffection(Main.game.getPlayer(), affection));
							}
						};
					}
					
				} else if (index == 2) {
					if(getCompanionCharacter()==null || companions.size()<2) {
						return new Response("Spitroast (front)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(getTargetedCharacterForSex().isPlayer()) {
						return new Response("Spitroast (front)", "You cannot target yourself for this action!", null);
						
					} else if(!getTargetedCharacterForSex().isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroast (front)", getTargetedCharacterForSex().getCompanionSexRejectionReason(true), null);
						
					} else if(!getCompanionCharacter().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroast (front)", getCompanionCharacter().getCompanionSexRejectionReason(false), null);
						
					} else if(!getCompanionCharacter().isAttractedTo(getTargetedCharacterForSex())) {
						return new Response("Spitroast (front)",
								UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(),
										"[npc.Name] is not attracted to [npc2.name], and so it would not be possible to make [npc.herHim] take a dominant position in order to fuck [npc2.herHim]..."),
								null);
							
					} else {
						 if((!Main.game.isNonConEnabled() || !getTargetedCharacterForSex().isSlave()) && !getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroast (front)",
									UtilText.parse(getTargetedCharacterForSex(),
											"[npc2.Name] is not attracted to you, and so would not be willing to be in a threesome position in which [npc2.she] interacts with you..."),
									null);
							
						} else if((!Main.game.isNonConEnabled() || !getTargetedCharacterForSex().isSlave()) && !getTargetedCharacterForSex().isAttractedTo(getCompanionCharacter())) {
							return new Response("Spitroast (front)",
									UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(),
											"[npc2.Name] is not attracted to [npc.name], and so would not be willing to be in a threesome position in which [npc2.she] interacts with [npc.herHim]..."),
									null);
							
						} else {
							boolean isRape = !getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer()) || !getTargetedCharacterForSex().isAttractedTo(getCompanionCharacter());
							return new ResponseSex(
									isRape
										?"Spitroast rape (front)"
										:"Spitroast (front)",
									UtilText.parse(getTargetedCharacterForSex(), getCompanionCharacter(), "Move around in front of [npc.name] so that you can use [npc.her] mouth while [npc2.name] takes [npc.her] rear."),
									null, null, null, null, null, null,
									!isRape, false,
									new SMGeneric(
											Util.newArrayListOfValues(getCompanionCharacter(), Main.game.getPlayer()),
											Util.newArrayListOfValues(getTargetedCharacterForSex()),
											null,
											null,
											ResponseTag.PREFER_DOGGY)  {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									AFTER_SEX,
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_FRONT_START", getTargetedCharacterForSex(), getCompanionCharacter())) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					}
				
				} else if (index == 3) {
					if(getCompanionCharacter()==null || companions.size()<2) {
						return new Response("Spitroast (behind)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(getTargetedCharacterForSex().isPlayer()) {
						return new Response("Spitroast (behind)", "You cannot target yourself for this action!", null);
						
					} else if(!getTargetedCharacterForSex().isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroast (behind)", getTargetedCharacterForSex().getCompanionSexRejectionReason(true), null);
						
					} else if(!getCompanionCharacter().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroast (behind)", getCompanionCharacter().getCompanionSexRejectionReason(false), null);
						
					} else if(!getCompanionCharacter().isAttractedTo(getTargetedCharacterForSex())) {
						return new Response("Spitroast (behind)",
								UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(),
										"[npc.Name] is not attracted to [npc2.name], and so it would not be possible to make [npc.herHim] take a dominant position in order to fuck [npc2.herHim]..."),
								null);
							
					} else {
						 if((!Main.game.isNonConEnabled() || !getTargetedCharacterForSex().isSlave()) && !getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroast (behind)",
									UtilText.parse(getTargetedCharacterForSex(),
											"[npc2.Name] is not attracted to you, and so would not be willing to be in a threesome position in which [npc2.she] interacts with you..."),
									null);
							
						} else if((!Main.game.isNonConEnabled() || !getTargetedCharacterForSex().isSlave()) && !getTargetedCharacterForSex().isAttractedTo(getCompanionCharacter())) {
							return new Response("Spitroast (behind)",
									UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(),
											"[npc2.Name] is not attracted to [npc.name], and so would not be willing to be in a threesome position in which [npc2.she] interacts with [npc.herHim]..."),
									null);
							
						} else {
							boolean isRape = !getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer()) || !getTargetedCharacterForSex().isAttractedTo(getCompanionCharacter());
							return new ResponseSex(
									isRape
										?"Spitroast rape (behind)"
										:"Spitroast (behind)",
									UtilText.parse(getTargetedCharacterForSex(), getCompanionCharacter(), "Move around behind [npc.name] so that you can use [npc.her] rear while [npc2.name] takes [npc.her] mouth."),
									null, null, null, null, null, null,
									!isRape, false,
									new SMGeneric(
											Util.newArrayListOfValues(Main.game.getPlayer(), getCompanionCharacter()),
											Util.newArrayListOfValues(getTargetedCharacterForSex()),
											null,
											null,
											ResponseTag.PREFER_DOGGY)  {
										@Override
										public boolean isPublicSex() {
											return isCompanionSexPublic();
										}
									},
									AFTER_SEX,
									UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROAST_BEHIND_START", getTargetedCharacterForSex(), getCompanionCharacter())) {
								@Override
								public void effects() {
									applyReactionReset();
								}
							};
						}
					}
				
				} else if (index == 4) {
					if(getCompanionCharacter()==null || companions.size()<2) {
						return new Response("Side-by-side (as dom)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(getTargetedCharacterForSex().isPlayer()) {
						return new Response("Side-by-side (as dom)", "You cannot target yourself for this action!", null);
						
					} else if(!getTargetedCharacterForSex().isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Side-by-side (as dom)", getTargetedCharacterForSex().getCompanionSexRejectionReason(true), null);
						
					} else if(!getCompanionCharacter().isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Side-by-side (as dom)", getCompanionCharacter().getCompanionSexRejectionReason(true), null);
						
					} else if((!Main.game.isNonConEnabled() || !getCompanionCharacter().isSlave()) && !getCompanionCharacter().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Side-by-side (as dom)",
								UtilText.parse(getCompanionCharacter(),
										"[npc.Name] is not attracted to you, and so would not be willing to be in a threesome position in which [npc.she] interacts with you..."),
								null);
							
					} else if((!Main.game.isNonConEnabled() || !getTargetedCharacterForSex().isSlave()) && !getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Side-by-side (as dom)",
								UtilText.parse(getTargetedCharacterForSex(),
										"[npc.Name] is not attracted to you, and so would not be willing to be in a threesome position in which [npc.she] interacts with you..."),
								null);
						
					} else {
						boolean isRape = !getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer()) || !getCompanionCharacter().isAttractedTo(Main.game.getPlayer());
						return new ResponseSex(
								isRape
									?"Side-by-side rape (as dom)"
									:"Side-by-side (as dom)",
								UtilText.parse(getTargetedCharacterForSex(), getCompanionCharacter(), "Push [npc.name] and [npc2.name] down onto all fours, before kneeling behind [npc.name], ready to fuck them both side-by-side."),
								null, null, null, null, null, null,
								!isRape, false,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(getTargetedCharacterForSex(), getCompanionCharacter()),
										null,
										null,
										ResponseTag.PREFER_DOGGY)  {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								AFTER_SEX,
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_START", getTargetedCharacterForSex(), getCompanionCharacter())) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
					
				} else if (index == 6) {
					if(!getTargetedCharacterForSex().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Submissive sex", getTargetedCharacterForSex().getCompanionSexRejectionReason(false), null);
					}
					
					if(!getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Submissive sex",
								UtilText.parse(getTargetedCharacterForSex(), 
									"[npc.Name] is not attracted to you,"
									+ (Main.game.isNonConEnabled() && getTargetedCharacterForSex().isSlave()
											?" so if you wanted to have sex with [npc.herHim], you'd need to rape [npc.herHim] as the dominant partner."
											:" so you can't have submissive sex with [npc.herHim].")),
								null);
						
					} else {
						return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getTargetedCharacterForSex()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								(getTargetedCharacterForSex().isSlave() && getTargetedCharacterForSex().hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)
										?Util.newArrayListOfValues(ResponseTag.PREFER_DOGGY)
										:new ArrayList<>())) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								AFTER_SEX,
								UtilText.parseFromXMLFile(getTextFilePath(), "SEX_AS_SUB_START", getTargetedCharacterForSex())) {
							@Override
							public void effects() {
								applyReactionReset();
								int affection = 0;
								if(isCompanionSexPublic()) {
									if(getTargetedCharacterForSex().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isPositive()) {
										affection+=5;
									} else if(getTargetedCharacterForSex().getFetishDesire(Fetish.FETISH_EXHIBITIONIST).isNegative()) {
										affection-=25;
									}
								}
								Main.game.getTextEndStringBuilder().append(getTargetedCharacterForSex().incrementAffection(Main.game.getPlayer(), affection));
							}
						};
					}
					
				} else if (index == 7) {
					if(getCompanionCharacter()==null || companions.size()<2) {
						return new Response("Spitroasted (front)", "You'd a third person to be present in order to get spitroasted...", null);
						
					} else if(getTargetedCharacterForSex().isPlayer()) {
						return new Response("Spitroasted (front)", "You cannot target yourself for this action!", null);
						
					} else if(!getTargetedCharacterForSex().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroasted (front)", getTargetedCharacterForSex().getCompanionSexRejectionReason(false), null);
						
					} else if(!getCompanionCharacter().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroasted (front)", getCompanionCharacter().getCompanionSexRejectionReason(false), null);
						
					} else if(!getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer())) {
						if(!getCompanionCharacter().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroasted (front)",
									UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(),
											"Neither [npc.name] nor [npc2.name] are attracted to you,"
											+ (Main.game.isNonConEnabled() && getCompanionCharacter().isSlave()
													?" so if you wanted to have sex with them, you'd need to rape them as the dominant partner."
													:" so you can't have submissive sex with them.")),
									null);
						} else {
							return new Response("Spitroasted (front)",
									UtilText.parse(getTargetedCharacterForSex(),
											"[npc.Name] is not attracted to you,"
											+ (Main.game.isNonConEnabled()
												?" so if you wanted to have sex with [npc.herHim], you'd need to rape [npc.herHim] as the dominant partner."
												:" so you can't have submissive sex with [npc.herHim].")),
									null);
						}
						
					} else if(!getCompanionCharacter().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Spitroasted (front)",
								UtilText.parse(getCompanionCharacter(),
									"[npc.Name] is not attracted to you,"
									+ (Main.game.isNonConEnabled() && getCompanionCharacter().isSlave()
										?" so if you wanted to have sex with [npc.herHim], you'd need to rape [npc.herHim] as the dominant partner."
										:" so you can't have submissive sex with [npc.herHim].")),
								null);
						
					} else {
						return new ResponseSex(
								"Spitroasted (front)",
								UtilText.parse(getTargetedCharacterForSex(), getCompanionCharacter(), "Get down on all fours facing [npc.name], so that [npc.she] can use your mouth while [npc2.name] takes your rear."),
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getCompanionCharacter(), getTargetedCharacterForSex()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.PREFER_DOGGY)  {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								AFTER_SEX,
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", getTargetedCharacterForSex(), getCompanionCharacter())) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
						
					
				} else if (index == 8) {
					if(getCompanionCharacter()==null || companions.size()<2) {
						return new Response("Spitroast (behind)", "You'd need a third person to be present in order to get a spitroast going...", null);
						
					} else if(getTargetedCharacterForSex().isPlayer()) {
						return new Response("Spitroasted (behind)", "You cannot target yourself for this action!", null);
						
					} else if(!getTargetedCharacterForSex().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroasted (behind)", getTargetedCharacterForSex().getCompanionSexRejectionReason(false), null);
						
					} else if(!getCompanionCharacter().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Spitroasted (behind)", getCompanionCharacter().getCompanionSexRejectionReason(false), null);
						
					} else if(!getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer())) {
						if(!getCompanionCharacter().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroasted (behind)",
									UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(),
											"Neither [npc.name] nor [npc2.name] are attracted to you,"
											+ (Main.game.isNonConEnabled() && getCompanionCharacter().isSlave()
													?" so if you wanted to have sex with them, you'd need to rape them as the dominant partner."
													:" so you can't have submissive sex with them.")),
									null);
						} else {
							return new Response("Spitroasted (behind)",
									UtilText.parse(getTargetedCharacterForSex(),
											"[npc.Name] is not attracted to you,"
											+ (Main.game.isNonConEnabled()
												?" so if you wanted to have sex with [npc.herHim], you'd need to rape [npc.herHim] as the dominant partner."
												:" so you can't have submissive sex with [npc.herHim].")),
									null);
						}
						
					} else if(!getCompanionCharacter().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Spitroasted (behind)",
								UtilText.parse(getCompanionCharacter(),
									"[npc.Name] is not attracted to you,"
									+ (Main.game.isNonConEnabled() && getCompanionCharacter().isSlave()
										?" so if you wanted to have sex with [npc.herHim], you'd need to rape [npc.herHim] as the dominant partner."
										:" so you can't have submissive sex with [npc.herHim].")),
								null);
						
					} else {
						return new ResponseSex(
								"Spitroasted (behind)",
								UtilText.parse(getTargetedCharacterForSex(), getCompanionCharacter(), "Get down on all fours and present your rear to [npc.name], so that [npc.she] can fuck you while [npc2.name] uses your mouth."),
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(getTargetedCharacterForSex(), getCompanionCharacter()),
										Util.newArrayListOfValues(Main.game.getPlayer()),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								AFTER_SEX,
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SPITROASTED_START", getCompanionCharacter(), getTargetedCharacterForSex())) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if (index == 9) {
					if(getCompanionCharacter()==null || companions.size()<2) {
						return new Response("Side-by-side (as sub)", UtilText.parse(getTargetedCharacterForSex(), "You'd need a third person to be present in order to get fucked alongside either them or [npc.name]..."), null);
						
					} else if(getTargetedCharacterForSex().isPlayer()) {
						return new Response("Side-by-side (as sub)", "You cannot target yourself for this action!", null);
						
					} else if(!getTargetedCharacterForSex().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Side-by-side (as sub)", getTargetedCharacterForSex().getCompanionSexRejectionReason(false), null);
						
					} else if(!getCompanionCharacter().isCompanionAvailableForSex(false)) { // Takes into account whether in a neutral dialogue or not.
						return new Response("Side-by-side (as sub)", getCompanionCharacter().getCompanionSexRejectionReason(false), null);
						
					} else if(!getTargetedCharacterForSex().isAttractedTo(Main.game.getPlayer())) {
						if(!getCompanionCharacter().isAttractedTo(Main.game.getPlayer())) {
							return new Response("Side-by-side (as sub)", UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(), "Neither [npc.name] nor [npc2.name] are attracted to you..."), null);
						} else {
							return new Response("Side-by-side (as sub)", UtilText.parse(getTargetedCharacterForSex(), "[npc.Name] is not attracted to you, and so would be unwilling to participate in a threesome..."), null);
						}
						
					} else if(!getCompanionCharacter().isAttractedTo(Main.game.getPlayer())) {
						return new Response("Side-by-side (as sub)", UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(), "[npc.Name] is not attracted to you, and so neither [npc.she] nor [npc2.name] would be willing to have a threesome..."), null);
						
					} else if(!getCompanionCharacter().isAttractedTo(getTargetedCharacterForSex())) {
						return new Response("Side-by-side (as sub)",
								UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(), "[npc.Name] is not attracted to [npc2.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);

					} else if(!getTargetedCharacterForSex().isAttractedTo(getCompanionCharacter())) {
						return new Response("Side-by-side (as sub)",
								UtilText.parse(getCompanionCharacter(), getTargetedCharacterForSex(), "[npc2.Name] is not attracted to [npc.name], and so neither of them would be willing to be in a threesome position in which they are expected to interact with one other..."),
								null);
						
					} else {
						return new ResponseSex("Side-by-side (as sub)",
								UtilText.parse(getTargetedCharacterForSex(), getCompanionCharacter(), "Get down on all fours beside [npc2.name], so that [npc.name] can kneel down behind the two of you, ready to fuck you both side-by-side."),
								null, null, null, null, null, null,
								true, false,
								new SMGeneric(
										Util.newArrayListOfValues(getTargetedCharacterForSex()),
										Util.newArrayListOfValues(Main.game.getPlayer(), getCompanionCharacter()),
										null,
										null,
										ResponseTag.PREFER_DOGGY) {
									@Override
									public boolean isPublicSex() {
										return isCompanionSexPublic();
									}
								},
								AFTER_SEX,
								UtilText.parseFromXMLFile(getThreesomeTextFilePath(), "SEX_SIDE_BY_SIDE_AS_SUB_START", getTargetedCharacterForSex(), getCompanionCharacter())) {
							@Override
							public void effects() {
								applyReactionReset();
							}
						};
					}
				
				} else if(index==11) {
					if(getCompanionCharacter()!=null) {
						return new ResponseEffectsOnly(
								UtilText.parse(getTargetedCharacterForSex(), "Target: <b style='color:"+getTargetedCharacterForSex().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
								"Cycle the targeted character for group sex.") {
							@Override
							public void effects() {
								if(companions.size()>1) {
									for(int i=0; i<companions.size();i++) {
										if(companions.get(i).equals(getTargetedCharacterForSex())) {
											if(i==companions.size()-1) {
												targetedCharacterForSex = (NPC) companions.get(0);
												if(getCompanionCharacter().equals(getTargetedCharacterForSex())) {
													companionCharacter = (NPC) companions.get(1);
												}
											} else {
												targetedCharacterForSex = (NPC) companions.get(i+1);
												if(getCompanionCharacter().equals(getTargetedCharacterForSex())) {
													companionCharacter = (NPC) companions.get((i+2)<companions.size()?(i+2):0);
												}
												break;
											}
										}
									}
								}
								Main.game.updateResponses();
							}
						};
						
					} else {
						return new Response(
								UtilText.parse(getCharacterViewed(), "Target: <b>[npc.Name]</b>"),
								"Cycle the targeted character for group sex.<br/>[style.italicsBad(You'd need to have a companion with you for this action to be unlocked!)]",
								null); 
					}
					
				} else if(index==12) {
					if(getCompanionCharacter()!=null) {
						return new ResponseEffectsOnly(
								UtilText.parse(getCompanionCharacter(), "Secondary: <b style='color:"+getCompanionCharacter().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
								"Cycle the secondary targeted character for group sex.") {
							@Override
							public void effects() {
								if(companions.size()>1) {
									for(int i=0; i<companions.size();i++) {
										if(companions.get(i).equals(getCompanionCharacter())) {
											if(i==companions.size()-1) {
												companionCharacter = (NPC) companions.get(0);
												if(getCompanionCharacter().equals(getTargetedCharacterForSex())) {
													targetedCharacterForSex = (NPC) companions.get(1);
												}
											} else {
												companionCharacter = (NPC) companions.get(i+1);
												if(getCompanionCharacter().equals(getTargetedCharacterForSex())) {
													targetedCharacterForSex = (NPC) companions.get((i+2)<companions.size()?(i+2):0);
												}
											}
											break;
										}
									}
								}
								Main.game.updateResponses();
							}
						};
						
					} else {
						return new Response(
								UtilText.parse(getCharacterViewed(), "Secondary: <b>[npc.Name]</b>"),
								"Cycle the secondary targeted character for group sex.<br/>[style.italicsBad(You'd need to have a companion with you for this action to be unlocked!)]",
								null); 
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public DialogueNode getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							applyReactionReset();
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
					};
					
				} else  {
					return null;
				}
			
//				if (index == 0) {
//					return new ResponseEffectsOnly("Back", "Stop viewing the characters present and return to the main game."){
//						@Override
//						public void effects() {
//							Main.mainController.openCharactersPresent();
//						}
//					};
//					
//				} else if (index == 1) { //TODO improve descriptions and affection hit from rape
//					if(Main.game.isNonConEnabled() && !((NPC) characterViewed).isAttractedTo(Main.game.getPlayer())) {
//						if(!characterViewed.isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
//							return new Response("Rape", characterViewed.getCompanionSexRejectionReason(true), null);
//							
//						} else {
//							if(isSittingSex()) {
//								return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
//										false, false,
//										new SMChair(
//													Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.CHAIR_BOTTOM)),
//													Util.newHashMapOfValues(new Value<>(characterViewed, SexSlotBipeds.CHAIR_TOP))) {
//												@Override
//												public boolean isPublicSex() {
//													return isCompanionSexPublic();
//												}
//											},
//										null,
//										null,
//										AFTER_SEX,
//										"<p>"
//											+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
//											+ " [npc.She] desperately tries to push you away, [npc.moaning],"
//											+ " [npc.speech(No! Stop!)]"
//										+ "</p>") {
//									@Override
//									public void effects() {
//										Main.game.setActiveNPC((NPC) characterViewed);
//										if(Main.game.getActiveNPC().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
//											Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
//										} else {
//											Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -50));
//										}
//									}
//								};
//								
//							} else {
//								return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
//										false, false,
//										new SMGeneric(
//													Util.newArrayListOfValues(Main.game.getPlayer()),
//													Util.newArrayListOfValues(characterViewed),
//										null,
//										null),
//										AFTER_SEX, "<p>"
//											+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
//											+ " [npc.She] desperately tries to push you away, [npc.moaning],"
//											+ " [npc.speech(No! Stop!)]"
//										+ "</p>") {
//									@Override
//									public void effects() {
//										Main.game.setActiveNPC((NPC) characterViewed);
//										if(Main.game.getActiveNPC().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
//											Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
//										} else {
//											Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -50));
//										}
//									}
//								};
//								
//							}
//							
//						}
//						
//					} else {
//						if(!characterViewed.isCompanionAvailableForSex(true)) { // Takes into account whether in a neutral dialogue or not.
//							return new Response("Sex", characterViewed.getCompanionSexRejectionReason(true), null);
//							
//						} else {
//							if(isSittingSex()) {
//								return new ResponseSex("Sex", "Have sex with [npc.name].", 
//										true, false,
//										new SMChair(
//													Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.CHAIR_BOTTOM)),
//													Util.newHashMapOfValues(new Value<>(characterViewed, SexSlotBipeds.CHAIR_TOP))) {
//												@Override
//												public boolean isPublicSex() {
//													return isCompanionSexPublic();
//												}
//											},
//										null,
//										null,
//										AFTER_SEX,
//										"<p>"
//											+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
//											+ " [npc.She] desperately leans into you, [npc.moaning],"
//											+ " [npc.speech(~Mmm!~ Yes!)]"
//										+ "</p>") {
//									@Override
//									public void effects() {
//										Main.game.setActiveNPC((NPC) characterViewed);
//										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
//									}
//								};
//								
//							} else {
//								return new ResponseSex("Sex", "Have sex with [npc.name].", 
//										true, false,
//										new SMGeneric(
//													Util.newArrayListOfValues(Main.game.getPlayer()),
//													Util.newArrayListOfValues(characterViewed),
//													null,
//													null),
//										AFTER_SEX,
//										"<p>"
//											+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
//											+ " [npc.She] desperately leans into you, [npc.moaning],"
//											+ " [npc.speech(~Mmm!~ Yes!)]"
//										+ "</p>") {
//									@Override
//									public void effects() {
//										Main.game.setActiveNPC((NPC) characterViewed);
//										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
//									}
//								};
//							}
//							
//						}
//					}
//					
//				} else if (index == 2) {
//					if(!characterViewed.isCompanionAvailableForSex(false)) {
//						return new Response("Submissive Sex", characterViewed.getCompanionSexRejectionReason(false), null);
//						
//					} else {
//						if(((NPC) characterViewed).isAttractedTo(Main.game.getPlayer())) {
//							if(isSittingSex()) {
//								return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
//										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
//										true, true,
//										new SMChair(
//													Util.newHashMapOfValues(new Value<>(characterViewed, SexSlotBipeds.CHAIR_BOTTOM)),
//													Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBipeds.CHAIR_TOP))) {
//												@Override
//												public boolean isPublicSex() {
//													return isCompanionSexPublic();
//												}
//											},
//										null,
//										null, AFTER_SEX, "<p>"
//											+ "Taking hold of [npc.namePos] [npc.arms], you take a step forwards, guiding [npc.her] [npc.hands] around your body as you press forwards into a passionate kiss."
//											+ " [npc.She] eagerly pulls you into [npc.herHim], [npc.moaning],"
//											+ " [npc.speech(Looking for some fun, hmm?)]"
//										+ "</p>") {
//									@Override
//									public void effects() {
//										Main.game.setActiveNPC((NPC) characterViewed);
//										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
//									}
//								};
//								
//							} else {
//								return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
//										Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
//										true, true,
//										new SMGeneric(
//													Util.newArrayListOfValues(characterViewed),
//													Util.newArrayListOfValues(Main.game.getPlayer()),
//										null,
//										null), AFTER_SEX, "<p>"
//											+ "Taking hold of [npc.namePos] [npc.arms], you take a step forwards, guiding [npc.her] [npc.hands] around your body as you press forwards into a passionate kiss."
//											+ " [npc.She] eagerly pulls you into [npc.herHim], [npc.moaning],"
//											+ " [npc.speech(Looking for some fun, hmm?)]"
//										+ "</p>") {
//									@Override
//									public void effects() {
//										Main.game.setActiveNPC((NPC) characterViewed);
//										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
//									}
//								};
//							}
//							
//						} else {
//							return new Response("Submissive sex", "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom...", null);
//						}
//					}
//					
//				}
//				
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
						return new Response("Transformations", characterViewed.getUnableToTransformDescription(), null);
						
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
						return new Response(characterViewed instanceof Elemental?"Dispel":"Go Home", "You're in the middle of something right now! (Can only be used when in a tile's default dialogue.)", null);
						
					} else {
						if(charactersPresent.size()==1 || (charactersPresent.size()==2 && characterViewed.isElementalSummoned())) {
							return new ResponseEffectsOnly(characterViewed instanceof Elemental?"Dispel":"Go Home",
									characterViewed instanceof Elemental?"Dispel [npc.namePos] physical form, and return [npc.herHim] to your arcane aura.":"Tell [npc.name] to go home."){
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
							return new Response(characterViewed instanceof Elemental?"Dispel":"Go Home",
									characterViewed instanceof Elemental?"Dispel [npc.namePos] physical form, and return [npc.herHim] to your arcane aura.":"Tell [npc.name] to go home.",
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
									menuTitle = "Characters Present ("+Util.capitaliseSentence(charactersPresent.get(0).getName(true))+")";
									menuContent = ((NPC) charactersPresent.get(0)).getCharacterInformationScreen(true);
								}
							};
						}
					}
					
				} else if (index == 6) {
					return new Response("Perk Tree", "Assign [npc.namePos] perk points.", PERKS);
					
				} else if(index==10) {
					if(!characterViewed.isElementalSummoned()) {
						return new Response("Dispel Elemental", "[npc.Name] doesn't have an elemental summoned...", null);
						
					} else {
						if(!Main.game.isSavedDialogueNeutral()) {
							return new Response("Dispel Elemental", "You're in the middle of something right now! (Can only be used when in a tile's default dialogue.)", null);
							
						} else {
							return new Response("Dispel Elemental", "Tell [npc.name] to dispel [npc.her] elemental.", MENU){
								@Override
								public void effects() {
									characterViewed.removeCompanion(characterViewed.getElemental());
									characterViewed.getElemental().returnToHome();
								}
							};
						}
					}
					
				} else if(index==11) {
					if(Main.game.isSavedDialogueNeutral()) {
						return new Response("Combat Moves", "Adjust the moves [npc.name] can perform in combat.", CombatMovesSetup.COMBAT_MOVES_CORE) {
							@Override
							public void effects() {
								CombatMovesSetup.setTarget(characterViewed, MENU);
							}
						};
					} else {
						return new Response("Combat Moves", "You are too busy to change [npc.namePos] combat moves.", null);
					}
				}
				
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
			if(Sex.getAllParticipants().size()>2) {
				return UtilText.parseFromXMLFile(getTextFilePath(), "AFTER_SEX_THREESOME", getTargetedCharacterForSex(), getCompanionCharacter());
				
			} else if(Sex.getNumberOfOrgasms(getCharacterViewed()) >= getCharacterViewed().getOrgasmsBeforeSatisfied()) {
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
			
			if(!(characterViewed instanceof Elemental)) {
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
