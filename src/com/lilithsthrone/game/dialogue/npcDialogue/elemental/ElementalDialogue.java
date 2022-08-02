package com.lilithsthrone.game.dialogue.npcDialogue.elemental;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.npc.misc.Elemental;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CombatMovesSetup;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.9
 * @version 0.3.9.3
 * @author Innoxia
 */
public class ElementalDialogue {
	
	private static Elemental getElemental() {
		return Main.game.getPlayer().getElemental();
	}

	public static final DialogueNode UTIL_NO_CONTENT = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELEMENTAL_START = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START");
		}
		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Interactions";
			} else if(index==1) {
				return UtilText.parse("[style.colourCompanion(Manage)]");
			}
			return null;
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) { // Interactions:
				if(index==1) {
					if(!getElemental().isActive()) {
						return new Response("Inspect", "You need to have [el.name] take on [el.her] active form in order to inspect [el.herHim].", null);
					}
					return new Response("Inspect", "Take a good look at [el.name].", ELEMENTAL_INSPECT);
					
				} else if(index==2) {
					return new Response("Talk", "Talk to [el.name] for a while.", ELEMENTAL_TALK);
					
				} else if(index==3) {
					if(getElemental().isActive()) {
						return new Response("Pet", "[el.Name] needs to be in [el.her] passive form in order to pet [el.herHim]...", null);
					}
					return new Response("Pet", "Give [el.name] a good petting.", ELEMENTAL_PETTING);
					
				}
				// index==5 is transform toggle defined below
				if(index==6) {
					if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("Dominant sex", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("Dominant sex",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with the idea of having sex with [el.name] in a place where people could see you!",
								null);
						
					} else if(!getElemental().isActive()) {
						return new Response("Dominant sex", "You need to have [el.name] take on [el.her] active form in order to have sex with [el.herHim].", null);
						
					} else if(getElemental().isSummonerServant() && !getElemental().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
						return new Response("Dominant sex",
								"As you have sworn subservience to the school of [el.school], and [el.name] does not have the '"+Fetish.FETISH_SUBMISSIVE.getName(getElemental())+"' fetish, [el.name] is not going to let you dominantly fuck [el.herHim]...",
								null);
					}
					return new ResponseSex("Dominant sex",
							"Have dominant sex with [el.name].",
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getElemental(), SexSlotStanding.STANDING_SUBMISSIVE))) {
								public boolean isCharacterAbleToStopSex(GameCharacter character) {
									return character.isPlayer();
								}
							},
							null,
							null,
							ELEMENTAL_AFTER_SEX,
							UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START_SEX_DOMINANT"));
					
				} else if(index==7) {
					if(!Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()) {
						return new Response("Submissive sex", Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getValue(), null);
						
					} else if(Main.game.getPlayerCell().getPlace().isPopulated() && !Main.game.getPlayer().hasFetish(Fetish.FETISH_EXHIBITIONIST)) {
						return new Response("Submissive sex",
								"As you do not have the [style.colourFetish("+Fetish.FETISH_EXHIBITIONIST.getName(Main.game.getPlayer())+" fetish)], you are not comfortable with the idea of having sex with [el.name] in a place where people could see you!",
								null);
						
					} else if(!getElemental().isActive()) {
						return new Response("Submissive sex", "You need to have [el.name] take on [el.her] active form in order to have sex with [el.herHim].", null);
					}
					return new ResponseSex("Submissive sex",
							"Let [el.name] take charge and have submissive sex with [el.herHim].",
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getElemental(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_SUBMISSIVE))),
							null,
							null,
							ELEMENTAL_AFTER_SEX,
							UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_START_SEX_SUBMISSIVE"));
					
				}
			
			} else if(responseTab==1) { // Management:
				if(index==1) {
					if(getElemental().isActive()) {
						return new Response("Passive transform", "You need to have [el.name] take on [el.her] passive form in order for [el.herHim] to change it to something else...", null);
					}
					return new Response("Passive transform", "Ask [el.name] about [el.her] passive form and whether [el.she] can change it to something else...", ELEMENTAL_PASSIVE_FORM);
					
				} else if(index==2) {
					if(!getElemental().isActive()) {
						return new Response("Active transform", "You need to have [el.name] take on [el.her] active form in order for [el.herHim] to change it to something else...", null);
					}
					return new Response("Active transform",
							"Get [el.name] to transform [el.herself] into a different form...",
							BodyChanging.BODY_CHANGING_CORE){
						@Override
						public void effects() {
							Main.game.saveDialogueNode();
							BodyChanging.setTarget(getElemental());
						}
					};
					
				} else if(index==3) {
					return new Response("Perks", "Assign [el.namePos] perk points.", ELEMENTAL_PERKS);
					
				} else if(index==4) {
					return new Response("Fetishes", "Use your intimate psychic connection with [el.name] to alter [el.her] fetishes based on which fetishes you have.", ELEMENTAL_FETISHES);
					
				}
				// index==5 is transform toggle defined below
				if(index==6) {
					return new Response("Combat moves", "Adjust the moves [el.name] can perform in combat.", CombatMovesSetup.COMBAT_MOVES_CORE) {
						@Override
						public void effects() {
							CombatMovesSetup.setTarget(getElemental(), ELEMENTAL_START);
						}
					};
					
				} else if(index==7) {
					if(!getElemental().isActive()) {
						return new Response("Inventory", "You need to have [el.name] take on [el.her] active form in order to manage [el.her] inventory...", null);
					}
					return new ResponseEffectsOnly("Inventory", "Manage [el.namePos] inventory.") {
						@Override
						public void effects() {
							Main.mainController.openInventory((NPC) getElemental(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if(index==8) {
					return new ResponseEffectsOnly("Self-clean: "+(!getElemental().hasFlag(NPCFlagValue.elementalStayDirty)?"[style.colourGood(On)]":"[style.colourBad(Off)]"),
							!getElemental().hasFlag(NPCFlagValue.elementalStayDirty)
								?"[el.Name] is currently using [el.her] self-transformative powers to clean any fluids from [el.her] body or clothing.<br/>[style.colourMinorBad(Click to toggle self-clean off.)]"
								:"[el.Name] is currently using [el.her] self-transformative powers to retain any fluids which dirty [el.her] body or clothing.<br/>[style.colourMinorGood(Click to toggle self-clean on.)]") {
						@Override
						public void effects() {
							if(getElemental().hasFlag(NPCFlagValue.elementalStayDirty)) {
								getElemental().removeFlag(NPCFlagValue.elementalStayDirty);
							} else {
								getElemental().addFlag(NPCFlagValue.elementalStayDirty);
							}
							Main.game.updateResponses();
						}
					};
					
				} else if(index==9) {
					return new Response("Set names", "Change [el.namePos] name or tell [el.herHim] to call you by a different name.", ELEMENTAL_CHOOSE_NAME);
					
				}
			}
			
			if(index==5) {
				if(getElemental().isActive()) {
					return new Response("Form: <b style='color:"+getElemental().getCurrentSchool().getColour().toWebHexString()+";'>Active</b>",
							"Tell [el.name] to switch back into [el.her] passive form.",
							UTIL_NO_CONTENT) {
						@Override
						public void effects() {
							getElemental().returnToHome();
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TAKE_FORM_PASSIVE"));
						}
					};
					
				} else {
					return new Response("Form: <i style='color:"+getElemental().getCurrentSchool().getColour().toWebHexString()+";'>Passive</i>",
							"Tell [el.name] to switch into [el.her] active form.",
							UTIL_NO_CONTENT) {
						@Override
						public void effects() {
							getElemental().setLocation(Main.game.getPlayer(), false);
							UtilText.addSpecialParsingString(String.valueOf(Main.game.getPlayer().getSexAvailabilityBasedOnLocation().getKey()), true);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TAKE_FORM_ACTIVE"));
						}
					};
				}
				
			} else if(index==10) {
				return new Response("Dispel", "Dispel [el.name]...",  Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getElemental().returnToHome();
						Main.game.getPlayer().setElementalSummoned(false);
					}
				};
			}
			
			if(index==0) {
				return new Response("Finished", "Tell [el.name] that you've finished talking with [el.herHim] and let [el.herHim] return to [el.her] passive form.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
						getElemental().returnToHome();
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode ELEMENTAL_AFTER_SEX = new DialogueNode("Finished", "Having had [el.her] fun with you, [el.name] puts an end to the sex...", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_AFTER_SEX");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_INSPECT = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_INSPECT")
					+ getElemental().getCharacterInformationScreen(false);
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_INSPECT) {
				return new Response("Inspect", "You are already taking a good look at [el.name].", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_TALK = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_TALK");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_TALK) {
				return new Response("Talk", "You are already talking to [el.name].", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_PETTING = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PETTING");
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_PETTING) {
				return new Response("Petting", "You are already petting [el.name]!", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode ELEMENTAL_PASSIVE_FORM = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Back", "Decide against having [el.name] take on a different passive form.", UTIL_NO_CONTENT) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_BACK"));
					}
				};
			}
			
			List<Response> responses = new ArrayList<>();
			List<AbstractSubspecies> subspecies = new ArrayList<>();
			subspecies.addAll(Subspecies.getAllSubspecies());
			subspecies.removeIf(s -> !s.getRace().isFeralPartsAvailable());
			subspecies.removeIf(s -> s.getRace()==Race.DEMON || s.getRace()==Race.ELEMENTAL || s.getRace()==Race.SLIME);
			subspecies.removeIf(s -> s.isNonBiped()); // Otherwise centaurs get added as well as horse-morphs
			
			if(getElemental().getPassiveForm()==null) {
				responses.add(new Response("Wisp", "[el.Name] is already assuming the passive form of an elemental wisp!", null));
			} else {
				responses.add(new Response("Wisp", "Tell [el.name] to assume the passive form of an elemental wisp.", UTIL_NO_CONTENT) {
					@Override
					public void effects() {
						UtilText.addSpecialParsingString("wisp", true);
						getElemental().setPassiveForm(null);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_CHANGE"));
					}
				});
			}
			
			for(AbstractSubspecies sub : subspecies) {
				String feralName = sub.getFeralName(getElemental().getBody());
				if(getElemental().getPassiveForm()==sub) {
					responses.add(new Response(Util.capitaliseSentence(feralName), "[el.Name] is already assuming the passive form of a small, feral "+feralName+"!", null));
					
				} else {
					responses.add(new Response(Util.capitaliseSentence(feralName), "Tell [el.name] to assume the passive form of a small, feral "+feralName+".", UTIL_NO_CONTENT) {
						@Override
						public void effects() {
							UtilText.addSpecialParsingString(UtilText.generateSingularDeterminer(feralName)+" "+feralName, true);
							UtilText.addSpecialParsingString(feralName, false);
							getElemental().setPassiveForm(sub);
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/elemental", "ELEMENTAL_PASSIVE_FORM_CHANGE"));
						}
					});
				}
			}
			
			for(int i=0; i<responses.size(); i++) {
				if(index==i+1) {
					return responses.get(i);
				}
			}
			
			return null;
		}
	};

	public static final DialogueNode ELEMENTAL_PERKS = new DialogueNode("[el.NamePos] Perk Tree", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(),
					"<details>"
							+ "<summary>[style.boldPerk(Perk & Trait Information)]</summary>"
							+ "[style.colourPerk(Perks)] (circular icons) apply permanent boosts to [npc.namePos] attributes.<br/>"
							+ "[style.colourPerk(Traits)] (square icons) provide unique effects for [npc.name]."
								+ " Unlike perks, <b>traits will have no effect on [npc.name] until they're slotted into [npc.her] 'Active Traits' bar</b>.<br/>"
							+ "Perks require perk points to unlock. [npc.Name] earns one perk point each time [npc.she] levels up, and earns an extra two perk points every five levels.<br/><br/>"
							+ "In addition to the perks that can be purchased via perk points, there are also several special, hidden perks that are unlocked via special events."
					+ "</details>"));
			
			UtilText.nodeContentSB.append(PerkManager.MANAGER.getPerkTreeDisplay(getElemental(), true));
			
			UtilText.nodeContentSB.append("</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_PERKS) {
				return new Response("Perks", "You are already assigning [el.namePos] perk points!", null);
			}
			if(responseTab==1 && index==11) {
				return new Response("Reset perks", "Reset all perks and traits, refunding all points spent. (This is a temporary action while the perk tree is still under development.)", ELEMENTAL_PERKS) {
					@Override
					public void effects() {
						getElemental().resetPerksMap(false, false);
					}
				};
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_FETISHES = new DialogueNode("[el.NamePos] Fetishes", "", true) {
		@Override
		public String getContent() {
			StringBuilder sb = new StringBuilder();
			
			sb = new StringBuilder(
					"<details>"
						+ "<summary>[style.boldFetish(Fetish Information)]</summary>"
							+ "You can select [el.namePos] [style.colourLust(desire)] for each fetish [style.colourArcane(for free)],"
							+ " or choose to take the associated [style.colourFetish(fetish)] for a cost of [style.colourArcane(arcane essences)].<br/><br/>"
							+ "Choosing a desire will affect bonus lust gains in sex, while taking a fetish will permanently lock [el.namePos] desire to 'love', and also give [el.herHim] special bonuses."
							+ " Fetishes can only be removed through enchanted potions.<br/><br/>"
							+ "[el.NamePos] currently selected desire has a "+PresetColour.FETISH.getName()+" border, but [el.her] true desire (indicated by the coloured desire icon) may be modified by enchanted clothes or other items.<br/><br/>"
							+ "[el.name] will earn experience for each fetish through performing related actions in sex."
							+ " Experience is earned regardless of whether or not [el.she] has the associated fetish."
							+ " Higher level fetishes will cause both [el.name] and [el.her] partner to gain more arousal from related sex actions, as well as increase the fetish's bonuses.<br/><br/>"
							+ "Finally, derived fetishes cannot be directly unlocked, but are instead automatically applied when [el.name] meets their requirements."
					+ "</details>");
			
			// Normal fetishes:

			sb.append("<div class='container-full-width' style='text-align:center; font-weight:bold;'><h6>Fetishes</h6></div>");
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DOMINANT, Fetish.FETISH_SUBMISSIVE));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_VAGINAL_GIVING, Fetish.FETISH_VAGINAL_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_PENIS_GIVING, Fetish.FETISH_PENIS_RECEIVING));
			if(Main.game.isAnalContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ANAL_GIVING, Fetish.FETISH_ANAL_RECEIVING));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BREASTS_OTHERS, Fetish.FETISH_BREASTS_SELF));
			if(Main.game.isLactationContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_LACTATION_OTHERS, Fetish.FETISH_LACTATION_SELF));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ORAL_RECEIVING, Fetish.FETISH_ORAL_GIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_LEG_LOVER, Fetish.FETISH_STRUTTER));
			if(Main.game.isFootContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_FOOT_GIVING, Fetish.FETISH_FOOT_RECEIVING));
			}
			if(Main.game.isArmpitContentEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_ARMPIT_GIVING, Fetish.FETISH_ARMPIT_RECEIVING));
			}
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_CUM_STUD, Fetish.FETISH_CUM_ADDICT));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DEFLOWERING, Fetish.FETISH_PURE_VIRGIN));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_IMPREGNATION, Fetish.FETISH_PREGNANCY));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_TRANSFORMATION_GIVING, Fetish.FETISH_TRANSFORMATION_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_KINK_GIVING, Fetish.FETISH_KINK_RECEIVING));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_SADIST, Fetish.FETISH_MASOCHIST));
			if(Main.game.isNonConEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_NON_CON_DOM, Fetish.FETISH_NON_CON_SUB));
			}

			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BONDAGE_APPLIER, Fetish.FETISH_BONDAGE_VICTIM));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_DENIAL, Fetish.FETISH_DENIAL_SELF));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_VOYEURIST, Fetish.FETISH_EXHIBITIONIST));
			sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_BIMBO, Fetish.FETISH_CROSS_DRESSER));
			if(Main.game.isIncestEnabled()) {
				sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_INCEST));
				if(Main.game.isPenetrationLimitationsEnabled()) {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_SIZE_QUEEN, null));
				}
			} else {
				if(Main.game.isPenetrationLimitationsEnabled()) {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, Fetish.FETISH_SIZE_QUEEN));
				} else {
					sb.append(PhoneDialogue.getFetishEntry(getElemental(), Fetish.FETISH_MASTURBATION, null));
				}
			}
			
			// Derived fetishes:

			sb.append("<div class='container-full-width' style='text-align:center; font-weight:bold; margin-top:16px;'><h6>Derived Fetishes</h6></div>");
			sb.append("<div class='fetish-container'>");
			
			for(AbstractFetish fetish : Fetish.getAllFetishes()) {
				if(!fetish.getFetishesForAutomaticUnlock().isEmpty()) {
					sb.append(
							"<div id='fetishUnlock" + Fetish.getIdFromFetish(fetish) + "' class='fetish-icon" + (Main.game.getPlayer().hasFetish(fetish)
							? " owned' style='border:2px solid " + PresetColour.FETISH.getShades()[1] + ";'>"
							: (fetish.isAvailable(Main.game.getPlayer())
									? " unlocked' style='border:2px solid " +  PresetColour.TEXT_GREY.toWebHexString() + ";" + "'>"
									: " locked' style='border:2px solid " + PresetColour.TEXT_GREY.toWebHexString() + ";'>"))
							+ "<div class='fetish-icon-content'>"+fetish.getSVGString(Main.game.getPlayer())+"</div>"
							+ (Main.game.getPlayer().hasFetish(fetish) // Overlay to create disabled effect:
									? ""
									: (fetish.isAvailable(Main.game.getPlayer())
											? "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.5; border-radius:5px;'></div>"
											: "<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.7; border-radius:5px;'></div>"))
							+ "</div>");
				}
			}
			
			sb.append("</div>");
			
			
			return sb.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_FETISHES) {
				return new Response("Fetishes", "You are already assigning [el.namePos] fetishes!", null);
			}
			//TODO
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode ELEMENTAL_CHOOSE_NAME = new DialogueNode("[el.Name]", "", true) {
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(), 
				"<p>"
					+ "At the moment, [npc.nameIsFull] calling you '[npc.pcName]', and you wonder if you should get [npc.herHim] to call you by a different name or title."
					+ " As [npc.sheIs] your slave, you could also change [npc.her] name to whatever you'd like it to be..."
				+ "</p>"));
			
			UtilText.nodeContentSB.append(
				"<div class='container-full-width' style='padding:8px 16px;'>"
					+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
						+ "Name"
					+ "</div>"
					+ "<div style='width:18%; float:left; font-weight:bold; margin:0 13% 0 0; padding:0; text-align:center;'>"
						+ "Surname"
					+ "</div>"
					+ "<div style='width:20%; float:left; font-weight:bold; margin:0 18% 0 0; padding:0; text-align:center;'>"
						+ UtilText.parse(getElemental(), "What [npc.she] calls you")
					+ "</div>"
					
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveNameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(getElemental().getName(false))+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
						
					+ "<form style='float:left; width:18%; margin:0; padding:0;'><input type='text' id='slaveSurnameInput'"
						+ " value='"+ UtilText.parseForHTMLDisplay(getElemental().getSurname())+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME_SURNAME' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_RENAME_SURNAME_RANDOM' style='float:left; width:5%; height:28px; line-height:28px; margin:0 2% 0 0.5%; padding:0; text-align:center;'>"
						+ "&#127922;"
					+ "</div>"
					
					+ "<form style='float:left; width:20%; margin:0; padding:0;'><input type='text' id='slaveToPlayerNameInput' value='"+ UtilText.parseForHTMLDisplay(getElemental().getPetName(Main.game.getPlayer()))
						+ "' style='width:100%; margin:0; padding:0;'></form>"
					+ "<div class='normal-button' id='"+getElemental().getId()+"_CALLS_PLAYER' style='float:left; width:5%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "&#10003;"
					+ "</div>"
					+ " <div class='normal-button' id='GLOBAL_CALLS_PLAYER' style='float:left; width:12%; height:28px; line-height:28px; margin:0 0 0 0.5%; padding:0; text-align:center;'>"
						+ "All Slaves"
					+ "</div>");
			
			UtilText.nodeContentSB.append(UtilText.parse(getElemental(), 
						"<p style='text-align:center; margin-top:4px;'>"
							+ "<i>If [npc.name] is told to call you 'Mom' or 'Dad', 'Mommy' or 'Daddy', 'Mistress' or 'Master', or 'Ma'am' or 'Sir',"
							+ " then [npc.she] will automatically switch to the appropriate paired name depending on the femininity of your character.</i>"
						+ "</p>"
					+ "</div>"));
			
			UtilText.nodeContentSB.append("<p id='hiddenFieldName' style='display:none;'></p>");
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getResponseTabTitle(int index) {
			return ELEMENTAL_START.getResponseTabTitle(index);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(ELEMENTAL_START.getResponse(responseTab, index)!=null
					&& ELEMENTAL_START.getResponse(responseTab, index).getNextDialogue()==ELEMENTAL_CHOOSE_NAME) {
				return new Response("Set names", "You are already telling [el.name] what names [el.she] should use!", null);
			}
			return ELEMENTAL_START.getResponse(responseTab, index);
		}
	};
}
