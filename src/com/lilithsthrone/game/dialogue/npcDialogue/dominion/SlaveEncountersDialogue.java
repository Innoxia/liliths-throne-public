package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.occupantManagement.OccupancyUtil;
import com.lilithsthrone.game.occupantManagement.slave.SlaveJob;
import com.lilithsthrone.game.occupantManagement.slave.SlavePermissionSetting;
import com.lilithsthrone.game.occupantManagement.slaveEvent.SlaveEvent;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.managers.OrgasmBehaviour;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3.9.2
 * @version 0.3.9.2
 * @author Innoxia
 */
public class SlaveEncountersDialogue {

	private static NPC slave;
	private static NPC characterForSex;

	private static NPC getSlave() {
		return slave;
	}
	
	public static DialogueNode getSlaveUsesYou(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU;
	}

	public static DialogueNode getSlaveUsesYouDungeon(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU_DUNGEON;
	}
	
	public static DialogueNode getSlaveUsesYouStreet(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU_STREETS;
	}
	
	public static DialogueNode getSlaveUsesYouAlleyway(NPC targetedSlave) {
		slave = targetedSlave;
		Main.game.setActiveNPC(targetedSlave);
		targetedSlave.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_YOU_ALLEYWAY;
	}
	
	public static DialogueNode getSlaveUsingOtherSlaveAlleyway(Value<NPC, NPC> slaves) {
		slave = slaves.getKey();
		characterForSex = slaves.getValue();
		Main.game.setActiveNPC(slave);
		slave.setLocation(Main.game.getPlayer(), false);
		characterForSex.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_OTHER_SLAVE_ALLEYWAY;
	}
	
	public static DialogueNode getSlaveUsingOtherSlaveLilayaCorridor(Value<NPC, NPC> slaves) {
		slave = slaves.getKey();
		characterForSex = slaves.getValue();
		Main.game.setActiveNPC(slave);
		slave.setLocation(Main.game.getPlayer(), false);
		characterForSex.setLocation(Main.game.getPlayer(), false);
		return SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR;
	}
	
	private static SMGeneric getSlaveOnSlaveSexManager(List<GameCharacter> doms, List<GameCharacter> subs, List<GameCharacter> spectators) {
		return new SMGeneric(
				doms,
				subs,
				null,
				spectators) {
			@Override
			public boolean isHidden(GameCharacter character) {
				return character.isPlayer() && Main.sex.isSpectator(character);
			}
			@Override
			public OrgasmBehaviour getCharacterOrgasmBehaviour(GameCharacter character) {
				GameCharacter fuckingTarget = 
						Main.sex.getOngoingCharactersUsingAreas(character, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).isEmpty()
							?null
							:Main.sex.getOngoingCharactersUsingAreas(character, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA).iterator().next();
				if(character.isSlave() && fuckingTarget!=null) {
					if(!character.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE) || (fuckingTarget.isSlave() && !fuckingTarget.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED))) {
						return OrgasmBehaviour.PULL_OUT;
					}
				}
				return super.getCharacterOrgasmBehaviour(character);
			}
			@Override
			public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
				return Util.newHashMapOfValues(
						new Value<>(slave, Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.PENIS)),
						new Value<>(characterForSex, Util.newArrayListOfValues(CoverableArea.VAGINA, CoverableArea.PENIS)));
			}
		};
	}
	
	private static final DialogueNode SLAVE_USES_YOU = new DialogueNode("Ambushed", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_SEX_EAGER", slave));
				
			} else if(index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_SEX_RESIST", slave));
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_POST_SEX = new DialogueNode("Used", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Now that [npc.sheHas] had [npc.her] fun, [npc.name] starts to step back...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_POST_SEX", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode SLAVE_USES_YOU_DUNGEON = new DialogueNode("Ambushed", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_DUNGEON_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_DUNGEON_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_SEX_EAGER", slave));
				
			} else if(index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_DUNGEON_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_SEX_RESIST", slave));
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_YOU_DUNGEON_POST_SEX = new DialogueNode("Used", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Now that [npc.sheHas] had [npc.her] fun, [npc.name] starts to step back...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_DUNGEON_POST_SEX", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode SLAVE_USES_YOU_STREETS = new DialogueNode("Ambushed", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_POST_SEX_STREETS,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_POST_SEX_STREETS,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS_SEX_EAGER", slave));
				
			} else if(index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_POST_SEX_STREETS,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_STREETS_SEX_RESIST", slave));
			}
			return null;
		}
	};

	public static final DialogueNode SLAVE_USES_YOU_POST_SEX_STREETS = new DialogueNode("Used", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Now that [npc.sheHas] had [npc.her] fun, [npc.name] starts to step back...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_POST_SEX_STREETS", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	private static final DialogueNode SLAVE_USES_YOU_ALLEYWAY = new DialogueNode("Ambushed", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null),
						SLAVE_USES_YOU_POST_SEX_ALLEYWAY,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY_SEX", slave));
				
			} else if(index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_EAGER),
						SLAVE_USES_YOU_POST_SEX_ALLEYWAY,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY_SEX_EAGER", slave));
				
			} else if(index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMGeneric(
								Util.newArrayListOfValues(getSlave()),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null,
								ResponseTag.START_PACE_PLAYER_SUB_RESISTING),
						SLAVE_USES_YOU_POST_SEX_ALLEYWAY,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_ALLEYWAY_SEX_RESISTING", slave));
			}
			return null;
		}
	};

	public static final DialogueNode SLAVE_USES_YOU_POST_SEX_ALLEYWAY = new DialogueNode("Used", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Now that [npc.sheHas] had [npc.her] fun, [npc.name] starts to step back...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_YOU_POST_SEX_ALLEYWAY", slave);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue on your way.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						getSlave().returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	
	// Slaves having sex in alleyway:
	
	private static final DialogueNode SLAVE_USES_OTHER_SLAVE_ALLEYWAY = new DialogueNode("", "", true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Ignore", "You don't care to find out who's responsible for making those lewd noises.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_IGNORE", slave, characterForSex));
						slave.returnToHome();
						characterForSex.returnToHome();
					}
				};
				
			} else if(index == 2) {
				return new Response("Check", "Continue forwards around the next corner and find out who's responsible for making those lewd noises.", SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK);
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK = new DialogueNode("", "", true, true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Join (dom)",
						UtilText.parse(slave, characterForSex, "Announce your presence and take charge of fucking [npc.name] and [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(Main.game.getPlayer()), Util.newArrayListOfValues(slave, characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_START_SEX_DOM", slave, characterForSex));
				
			} else if(index == 2) {
				if(!slave.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Join (sub)", UtilText.parse(slave, "As [npc.name] isn't attracted to you, you can't force [npc.herHim] to dominate you..."), null);
				}
				return new ResponseSex("Join (sub)",
						UtilText.parse(slave, characterForSex, "Announce your presence and submit to [npc.name], allowing [npc.herHim] to dominate both you and [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(Main.game.getPlayer(), characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_START_SEX_SUB", slave, characterForSex));
					
			} else if(index == 3) {
				return new ResponseSex("Watch",
						UtilText.parse(slave, characterForSex, "Stay hidden around the corner and watch as [npc.name] fucks [npc2.name]..."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(characterForSex), Util.newArrayListOfValues(Main.game.getPlayer())),
						SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_START_SEX_WATCHING", slave, characterForSex));
				
			} else if(index == 4) {
				return new Response("Leave", "Leave your slaves to have their fun together and continue on your way...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_CHECK_LEAVE", slave, characterForSex));
						slave.returnToHome();
						characterForSex.returnToHome();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Now that [npc.sheHas] had [npc.her], [npc.name] [npc.steps] back...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_ALLEYWAY_POST_SEX", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = new SlaveryEventLogEntry(Main.game.getHourOfDay(),
									slave,
									Util.newArrayListOfValues(characterForSex.getId()),
									SlaveEvent.SLAVE_SEX,
									null,
									Util.newArrayListOfValues(UtilText.parse(slave,  characterForSex, "You caught [npc.name] having sex with [npc2.name] in one of Dominion's alleyways.")),
									true);
						Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						slave.returnToHome();
						characterForSex.returnToHome();
					}
				};
			}
			return null;
			
		}
	};
	
	
	// Slaves having sex in Lilaya's house corridor:
	
	private static void returnSlavesToHomeLilayasCorridor() {
		if(slave.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.CLEANING) {
			SlaveJob.CLEANING.sendToWorkLocation(slave);
		} else {
			slave.returnToHome();
		}
		if(characterForSex.getSlaveJob(Main.game.getHourOfDay())==SlaveJob.CLEANING) {
			SlaveJob.CLEANING.sendToWorkLocation(characterForSex);
		} else {
			characterForSex.returnToHome();
		}
	}
	
	private static final DialogueNode SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR = new DialogueNode("", "", true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Ignore", "You don't care to find out who's responsible for making those lewd noises.", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_IGNORE", slave, characterForSex));
						returnSlavesToHomeLilayasCorridor();
					}
				};
				
			} else if(index == 2) {
				return new Response("Check", "Move closer and find out who's responsible for making those lewd noises.", SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK);
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK = new DialogueNode("", "", true, true) {
		@Override
		public String getAuthor() {
			return "PoyntFury";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Join (dom)",
						UtilText.parse(slave, characterForSex, "Announce your presence and take charge of fucking [npc.name] and [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(Main.game.getPlayer()), Util.newArrayListOfValues(slave, characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_START_SEX_DOM", slave, characterForSex));
				
			} else if(index == 2) {
				if(!slave.isAttractedTo(Main.game.getPlayer())) {
					return new Response("Join (sub)", UtilText.parse(slave, "As [npc.name] isn't attracted to you, you can't force [npc.herHim] to dominate you..."), null);
				}
				return new ResponseSex("Join (sub)",
						UtilText.parse(slave, characterForSex, "Announce your presence and submit to [npc.name], allowing [npc.herHim] to dominate both you and [npc2.name]."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(Main.game.getPlayer(), characterForSex), null),
						SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_START_SEX_SUB", slave, characterForSex));
					
			} else if(index == 3) {
				return new ResponseSex("Watch",
						UtilText.parse(slave, characterForSex, "Stay hidden and watch as [npc.name] fucks [npc2.name]..."),
						true, false,
						getSlaveOnSlaveSexManager(Util.newArrayListOfValues(slave), Util.newArrayListOfValues(characterForSex), Util.newArrayListOfValues(Main.game.getPlayer())),
						SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX,
						UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_START_SEX_WATCHING", slave, characterForSex));
				
			} else if(index == 4) {
				return new Response("Leave", "Leave your slaves to have their fun together and continue on your way...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = OccupancyUtil.applySlaveSexWithOtherSlave(Main.game.getDayNumber(), Main.game.getHourOfDay(), slave, characterForSex);
						if(event!=null) {
							Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						}
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_CHECK_LEAVE", slave, characterForSex));
						returnSlavesToHomeLilayasCorridor();
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription(){
			return UtilText.parse(slave, "Now that [npc.sheHas] had [npc.her], [npc.name] [npc.steps] back...");
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/slaveEncounters", "SLAVE_USES_OTHER_SLAVE_LILAYA_CORRIDOR_POST_SEX", slave, characterForSex);
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new Response("Continue", "Continue on your way...", Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						SlaveryEventLogEntry event = new SlaveryEventLogEntry(Main.game.getHourOfDay(),
									slave,
									Util.newArrayListOfValues(characterForSex.getId()),
									SlaveEvent.SLAVE_SEX,
									null,
									Util.newArrayListOfValues(UtilText.parse(slave,  characterForSex, "You caught [npc.name] having sex with [npc2.name] in one of the corridors in Lilaya's home.")),
									true);
						Main.game.addSlaveryEvent(Main.game.getDayNumber(), event);
						returnSlavesToHomeLilayasCorridor();
					}
				};
			}
			return null;
			
		}
	};
	
}
