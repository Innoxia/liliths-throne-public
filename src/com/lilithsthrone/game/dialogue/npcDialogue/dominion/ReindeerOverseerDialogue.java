package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.96
 * @version 0.3.9
 * @author Innoxia
 */
public class ReindeerOverseerDialogue {
	
	private static NPC getReindeer() {
		return Main.game.getActiveNPC();
	}
	
	private static Response getDefaultResponses(int index) {
		if(index == 1) {
			return new ResponseTrade("Trade",
					UtilText.parse(getReindeer(), "Ask [npc.name] what Yuletide presents [npc.sheIs] selling."),
					getReindeer()) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
				}
			};
			
		} else if(index == 2) {
			if(Main.game.getDialogueFlags().hasWorkedForReindeer(getReindeer().getId())) {
				return new Response("Work",
						UtilText.parse(getReindeer(), "You've already helped [npc.name] to finish all of the work [npc.she] had today, so you'll need to come back tomorrow if you wanted to work for [npc.herHim] again."),
						null);
				
			} else {
				return new Response("Work", "Offer to work with the reindeer-morphs.", ENCOUNTER_WORK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
						Main.game.getDialogueFlags().addReindeerDailyWorkedFor(getReindeer().getId());
					}
				};
			}
			
		} else if(index == 3) {
			if(!Main.game.getDialogueFlags().hasWorkedForReindeer(getReindeer().getId())) {
				return new Response("Relieve stress",
						UtilText.parse(getReindeer(), "[npc.Name] is far too busy to take any time off work right now. Perhaps if you helped out first, [npc.she]'d have time to have sex with you..."),
						null);
				
			} else if(!getReindeer().isAttractedTo(Main.game.getPlayer())) {
				return new Response("Relieve stress",
						UtilText.parse(getReindeer(), "[npc.Name] is not attracted to you, and as such, [npc.sheIs] not willing to have sex with you..."),
						null);
				
			} else {
				return new ResponseSex("Relieve stress",
							UtilText.parse(getReindeer(), "Ask [npc.name] if [npc.she]'d like to blow off some steam with you."),
							true, true,
							new SMGeneric(
									Util.newArrayListOfValues(Main.game.getPlayer()),
									Util.newArrayListOfValues(getReindeer()),
							null,
							null) {
								@Override
								public boolean isPublicSex() {
									return false;
								}
							},
							AFTER_SEX,
							UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "SEX_START")) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
						}
					};
			}
			
		} else if(index==0) {
			return new Response("Leave",
					UtilText.parse(getReindeer(), "Tell [npc.name] that you might come back another time, before taking your leave."),
					ENCOUNTER_START){
				@Override
				public void effects() {
					Main.game.getDialogueFlags().addReindeerEncountered(getReindeer().getId());
				}
				@Override
				public DialogueNode getNextDialogue(){
					return Main.game.getDefaultDialogue(false);
				}
			};
		}
		return null;
	}
	
	public static final DialogueNode ENCOUNTER_START = new DialogueNode("Reindeer Overseer", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_START", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNode ENCOUNTER_WORK = new DialogueNode("Reindeer Overseer", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Shovel snow",
						UtilText.parse(getReindeer(), "Tell [npc.name] that you'll shovel snow with the rest of the workers."
								+ "<br/>Your performance and payment will be based on your [style.italicsStrength("+Attribute.MAJOR_PHYSIQUE.getName()+")]."),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_PHYSIQUE)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_SHOVEL_SNOW", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
				
			} else if(index==2) {
				return new Response("Use heat-stave",
						UtilText.parse(getReindeer(), "Tell [npc.name] that you'd like to use one of the heat-staves to score out lines in the snow."
								+ "<br/>Your performance and payment will be based on your [style.italicsIntelligence("+Attribute.MAJOR_ARCANE.getName()+")]."),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_ARCANE)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_HEAT_STAVE", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
				
			} else if(index==3) {
				return new Response("'Encouragement'",
						UtilText.parse(getReindeer(), "Tell [npc.name] that you'd be best suited for delivering drinks and 'encouraging' the workers."
								+ "<br/>Your performance and payment will be based on your [style.italicsCorruption("+Attribute.MAJOR_CORRUPTION.getName()+")]."),
						ENCOUNTER_WORK_FINISHED) {
					@Override
					public void effects() {
						int money = 100 + (int)(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)*1.5f);
						UtilText.addSpecialParsingString(Util.intToString(money), true);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_ENCOURAGEMENT", getReindeer()));
						Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(money));
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode ENCOUNTER_WORK_FINISHED = new DialogueNode("Reindeer Overseer", "", true) {
		@Override
		public int getSecondsPassed() {
			return 60 * 4*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "ENCOUNTER_WORK_FINISHED", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getReindeer(), "[npc.Name] has had enough and brings an end to the sex...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/reindeerOverseer", "AFTER_SEX", getReindeer());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getDefaultResponses(index);
		}
	};
}
