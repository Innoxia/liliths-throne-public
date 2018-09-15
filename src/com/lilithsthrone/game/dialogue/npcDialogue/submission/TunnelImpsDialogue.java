package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.submission.ImpAttacker;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerInterface;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.11
 * @version 0.2.11
 * @author Innoxia
 */
public class TunnelImpsDialogue {

	private static List<GameCharacter> impGroup = new ArrayList<>();
	
	public static void setImpGroup(List<GameCharacter> impGroup) {
		TunnelImpsDialogue.impGroup = impGroup;
	}
	
	public static List<GameCharacter> getImpGroup() {
		return impGroup;
	}
	
	public static ImpAttacker getImpLeader() {
		return (ImpAttacker) impGroup.get(0);
	}

	public static void banishImpGroup() {
		for(GameCharacter imp : impGroup) {
			if(!imp.isSlave()) {
				Main.game.banishNPC(imp.getId());
			}
		}
		impGroup.clear();
	}
	
	private static SexManagerInterface getImpSexManager(SexPace playerStartPace) { //TODO
		return new SMStanding(
				Util.newHashMapOfValues(new Value<>(getImpGroup().get(0), SexPositionSlot.STANDING_DOMINANT)),
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))){
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					return playerStartPace;
				}
				return null;
			}
		};
	}
	
	private static SexManagerInterface getDominantImpSexManager(SexPace playerStartPace) { //TODO
		return new SMStanding(
				Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
				Util.newHashMapOfValues(new Value<>(getImpGroup().get(0), SexPositionSlot.STANDING_SUBMISSIVE))){
			@Override
			public SexPace getStartingSexPaceModifier(GameCharacter character) {
				if(character.isPlayer()) {
					return playerStartPace;
				}
				return null;
			}
		};
	}
	
	private static String getImpEncounterId() {
		if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_ALPHA) {
			// Alpha imp group encounter:
			return "Alpha";
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_DEMON) {
			// Demon group encounter:
			return "Demon";
			
		} else if(Main.game.getPlayer().getLocationPlace().getPlaceType()==PlaceType.SUBMISSION_IMP_TUNNELS_FEMALES) {
			// Female imps encounter:
			return "Females";
			
		} else {
			// Male imps encounter:
			return "Males";
		}
	}
	
	public static final DialogueNodeOld IMP_ATTACK = new DialogueNodeOld("Assaulted!", "A group of imps attack!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK", getImpGroup());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getImpGroup(), null);
				
			} else if (index == 2) {
				return new Response("Offer money", "These imps are not interested in your money! You'll have to either fight or surrender yourself to them...", null);
				
			} else if (index == 3) {
				return new ResponseSex("Offer body", "Offer your body to the imps so that you can avoid a violent confrontation.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
						null, null, null,
						true, true,
						getImpSexManager(SexPace.SUB_NORMAL),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_OFFER_BODY", getImpGroup()));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated the imps!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Standard";
				
			} else if(index==1) {
				return "Inventories"; // TODO enslaving checks
				
			} else if(index==2) {
				return "Transformations";
				
			}
 			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if (index == 1) {
					return new Response("Continue", "Leave the imps and continue on your way...", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							banishImpGroup();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Sex",
							"Well, they <i>are</i> asking for it!",
							true, false,
							getDominantImpSexManager(SexPace.DOM_NORMAL),
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX", getImpGroup()));
					
				} else if (index == 3) {
					return new ResponseSex("Gentle Sex",
							"Well, they <i>are</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							getDominantImpSexManager(SexPace.DOM_GENTLE),
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_GENTLE", getImpGroup()));
					
				} else if (index == 4) {
					return new ResponseSex("Rough Sex",
							"Well, they <i>are</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							getDominantImpSexManager(SexPace.DOM_ROUGH),
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_ROUGH", getImpGroup()));
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now... Perhaps it would be best to let the imps choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							false, false,
							getImpSexManager(SexPace.SUB_NORMAL),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "IMP_ATTACK_COMBAT_VICTORY_SEX_SUBMIT", getImpGroup()));
				}
				
			} else if(responseTab == 1) {
				for(int i=1; i<=getImpGroup().size(); i++) {
					if(index==i) {
						NPC imp = (NPC) getImpGroup().get(i-1);
						return new ResponseEffectsOnly(UtilText.parse(imp, "[npc.Name]"),
								UtilText.parse(imp, "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items...")) {
							@Override
							public void effects() {
								Main.mainController.openInventory(imp, InventoryInteraction.FULL_MANAGEMENT);
							}
						};
					}
				}
				
			} else if(responseTab == 2) {
				for(int i=1; i<=getImpGroup().size(); i++) {
					if(index==i) {
						NPC imp = (NPC) getImpGroup().get(i-1);
						return new Response(UtilText.parse(imp, "[npc.Name]"),
								UtilText.parse(imp, "Take a very detailed look at what [npc.name] can transform [npc.herself] into..."),
								BodyChanging.BODY_CHANGING_CORE){
							@Override
							public void effects() {
								Main.game.saveDialogueNode();
								BodyChanging.setTarget(imp);
							}
						};
					}
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		Value<String, AbstractItem> potion = null;
		
		@Override
		public String getDescription() {
			return "You have been defeated by the imps!";
		}

		@Override
		public String getContent() {
			potion = getImpLeader().getTransfomativePotion(Main.game.getPlayer(), true);
			
			if(potion == null) {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_NO_TF", getImpGroup());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF", getImpGroup());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(potion != null) {
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION_REFUSED);
					}
					
				} else if (index == 2) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();

					Util.Value<String, AbstractItem> potion = getImpLeader().getTransfomativePotion(Main.game.getPlayer(), false);
					
					return new Response("Swallow", "Do as you're told and swallow the strange potion.", AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorrutionLevel,
							null,
							null,
							null) {
						@Override
						public void effects(){
							Main.game.getTextStartStringBuilder().append(
									UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_ACCEPTED", getImpGroup())
									+ "<p>"
										+ getImpLeader().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
									+"</p>");
						}
					};
				}
				
			} else {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							getImpSexManager(SexPace.SUB_NORMAL),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getImpGroup()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							getImpSexManager(SexPace.SUB_EAGER),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getImpGroup()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							getImpSexManager(SexPace.SUB_RESISTING),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getImpGroup()));
					
				}
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION_REFUSED = new DialogueNodeOld("Avoided Transformation", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_REFUSED", getImpGroup());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						getImpSexManager(SexPace.SUB_NORMAL),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getImpGroup()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						getImpSexManager(SexPace.SUB_EAGER),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getImpGroup()));
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						getImpSexManager(SexPace.SUB_RESISTING),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getImpGroup()));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION = new DialogueNodeOld("Transformed", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "COMBAT_DEFEAT_TF_APPLIED", getImpGroup());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						getImpSexManager(SexPace.SUB_NORMAL),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX", getImpGroup()));
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						getImpSexManager(SexPace.SUB_EAGER),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_EAGER", getImpGroup()));
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						getImpSexManager(SexPace.SUB_RESISTING),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_COMBAT_DEFEAT_SEX_RESIST", getImpGroup()));
				
			} else {
				return null;
			}}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave the imps to recover and disperse.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_VICTORY_SEX", getImpGroup());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0 || index == 1) {
				return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
			}
			return null;
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							banishImpGroup();
						}
					};
				}
				
			} else if(responseTab==1) {
				return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/submission/impAttack"+getImpEncounterId(), "AFTER_DEFEAT_SEX", getImpGroup());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()) {
					@Override
					public void effects() {
						banishImpGroup();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld CONTINUE_ENSLAVEMENT = new DialogueNodeOld("Imps", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return AFTER_COMBAT_VICTORY.getResponseTabTitle(index);
		}
		
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return AFTER_COMBAT_VICTORY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld IMP_ENSLAVEMENT_DIALOGUE = new DialogueNodeOld("New Slave", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return ".";
		}

		@Override
		public String getContent() {
			GameCharacter target = SlaveDialogue.getEnslavementTarget();
			AbstractClothing enslavementClothing = target.getEnslavementClothing();
			
			if(!target.isSlave()) {
				return UtilText.parse(target,
						"<p>"
							+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
							+ " [npc.She] lets out a distressed cry as [npc.she] sees what you're about to do, but [npc.sheIs] so exhausted that [npc.she] can't manage to put up any significant amount of resistance."
						+ "</p>"
						+ "<p>"
							+ "Forcing the item of clothing onto [npc.herHim], you step back, looking down at a face filled with fear."
							+ " The "+enslavementClothing.getName()+"'s arcane enchantment recognises [npc.name] as being a criminal, and, with a purple flash,"
								+ " <b>[npc.sheIs] teleported to the 'Slave Administration' building in Slaver Alley, where [npc.she]'ll be waiting for you to pick them up</b>."
						+ "</p>"
						+ "<p>"
							+ "Just before they disappear, glowing purple lettering is projected into the air, which reads:<br/>"
							+ "<i>Slave identification: [style.boldArcane("+target.getNameIgnoresPlayerKnowledge()+")]</i>"
						+ "</p>");
				
			} else {
				if(target.isSlave()) {
					return UtilText.parse(target,
							"<p>"
								+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
								+ " [npc.She] lets out a sigh as [npc.she] sees what you're about to do, and says,"
								+ " [npc.speech(If you're trying to enslave me, it won't work... I'm already a slave...)]"
							+ "</p>"
							+ "<p>"
								+ "Despite [npc.her] words, you force the item of clothing onto [npc.name], before stepping back and waiting to see if anything happens."
								+ " True to [npc.her] words, however, the "+enslavementClothing.getName()+"'s arcane enchantment recognises [npc.name] as already being a slave,"
										+ " evidenced by glowing green lettering that's projected into the air, which reads:<br/>"
								+ "<i>[style.boldGreen(Target already enslaved!)]</i>"
							+ "</p>");
					
				} else if(target.getSubspecies()==Subspecies.DEMON) {
					return UtilText.parse(target,
							"<p>"
								+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
								+ " [npc.She] lets out a mocking laugh as [npc.she] sees what you're about to do, and sneers,"
								+ " [npc.speech(If you're trying to enslave me, it's no use! Demons aren't allowed to be enslaved without their written consent! Everyone knows that!)]"
							+ "</p>"
							+ "<p>"
								+ "Despite [npc.her] words, you force the item of clothing onto [npc.name], before stepping back and waiting to see if anything happens."
								+ " True to [npc.her] words, however, the "+enslavementClothing.getName()+"'s arcane enchantment doesn't recognise [npc.name] as being a criminal,"
										+ " evidenced by glowing pink lettering that's projected into the air, which reads:<br/>"
								+ "<i>[style.boldPink(Demonic target! Cannot enslave!)]</i>"
							+ "</p>");
					
				} else {
					return UtilText.parse(target,
							"<p>"
								+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
								+ " [npc.She] lets out a mocking laugh as [npc.she] sees what you're about to do, and sneers, [npc.speech(If you're trying to enslave me, it's no use! I haven't been targeted by the Enforcers for anything!)]"
							+ "</p>"
							+ "<p>"
								+ "Despite [npc.her] words, you force the item of clothing onto [npc.name], before stepping back and waiting to see if anything happens."
								+ " True to [npc.her] words, however, the "+enslavementClothing.getName()+"'s arcane enchantment doesn't recognise [npc.name] as being a criminal,"
										+ " evidenced by glowing red lettering that's projected into the air, which reads:<br/>"
								+ "<i>[style.boldRed(Invalid target! Cannot enslave!)]</i>"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(getImpGroup().size()>1) {
					return new Response("Continue", "Continue dealing with the other imps.", CONTINUE_ENSLAVEMENT){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(IMP_ENSLAVEMENT_DIALOGUE.getContent());
							GameCharacter target = SlaveDialogue.getEnslavementTarget();
							
							target.applyEnslavementEffects(Main.game.getPlayer());
							Main.game.getPlayer().addSlave((NPC) target);
							target.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
							getImpGroup().remove(target);
						}
					};
					
				} else {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							GameCharacter target = SlaveDialogue.getEnslavementTarget();
							
							target.applyEnslavementEffects(Main.game.getPlayer());
							Main.game.getPlayer().addSlave((NPC) target);
							target.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
							getImpGroup().remove(target);
						}
					};
				}
				
			} else {
				return null;
			}
		}
	};
}
