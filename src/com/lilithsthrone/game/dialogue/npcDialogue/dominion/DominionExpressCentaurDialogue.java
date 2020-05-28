package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.warehouseDistrict.DominionExpress;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.SMDominionExpressEncounter;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.3.7.7
 * @version 0.3.7.7
 * @author Innoxia
 */
public class DominionExpressCentaurDialogue {

	private static SexAreaInterface sexAreaWanted = null;
	private static int slavePointsReward = 1;
	
	private static NPC centaur() {
		return Main.game.getActiveNPC();
	}
	
	private static void banishCentaur(boolean delete) {
		if(delete) {
			Main.game.banishNPC((NPC) centaur());
			
		} else {
			centaur().setHomeLocation(WorldType.DOMINION_EXPRESS, PlaceType.DOMINION_EXPRESS_STABLES);
			centaur().returnToHome();
		}
		Main.game.setActiveNPC(null);
	}
	
	public static DialogueNode initEncounter() {
		if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) { // Need mouth access to kiss asshole.
			return null;
		}
		if(Main.game.getPlayer().getWorldLocation()==WorldType.DOMINION && !Main.game.isExtendedWorkTime()) { // Centaurs are not out at work during the night.
			return null;
		}
		
		slavePointsReward = 5+Util.random.nextInt(6); // Slaves give the player 5-10 points to service them.
		
		List<SexAreaInterface> list = Util.newArrayListOfValues(SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
		if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
			list.remove(SexAreaOrifice.ANUS);
		}
		
		if(list.isEmpty() || Math.random()<0.25f) {
			sexAreaWanted = null;
		} else {
			sexAreaWanted = Util.randomItemFrom(list);
		}
		
		if(sexAreaWanted!=null) {
			NPC slave = (NPC) DominionExpress.spawnSlave(Math.random()<0.5f);
			
			Main.game.setActiveNPC(slave);
			slave.setPlayerKnowsName(false);
			slave.setLocation(Main.game.getPlayer(), false);
			
			return INITIAL_ENCOUNTER;
		}
		
		return null;
	}
	
	private static final DialogueNode INITIAL_ENCOUNTER = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(slavePointsReward), true);

			if(sexAreaWanted==SexAreaOrifice.MOUTH) {
				UtilText.addSpecialParsingString("suck my cock", false);
			} else if(sexAreaWanted==SexAreaOrifice.ANUS) {
				UtilText.addSpecialParsingString("let me rut your ass", false);
			} else {
				UtilText.addSpecialParsingString("give me a rimjob", false);
			}
			return UtilText.parseFromXMLFile("characters/dominion/dominionExpressCentaur", "INITIAL_ENCOUNTER", centaur());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Refuse",
						UtilText.parse(centaur(), "Tell [npc.name] that you don't want to service [npc.herHim] right now and continue on your way..."),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/dominionExpressCentaur", "INITIAL_ENCOUNTER_REFUSED", centaur()));
						banishCentaur(true);
					}
				};
				
			} else if(index==2) {
				if(sexAreaWanted==SexAreaOrifice.MOUTH) {
					return new ResponseSex(
							"Suck cock",
							UtilText.parse(centaur(), "Drop down onto your knees and kiss [npc.namePos] asshole, before shuffling around to suck [npc.her] cock."),
							true,
							false,
							new SMDominionExpressEncounter(SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(centaur(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL)),
									Util.newHashMapOfValues(new Value<>(centaur(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))),
									Util.newHashMapOfValues(
											new Value<>(centaur(), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))) {
								@Override
								public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
									Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
									map.put(centaur(), new HashMap<>());
									map.get(centaur()).put(SexAreaOrifice.ANUS, new HashMap<>());
									map.get(centaur()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
									return map;
								}
							},
							null,
							null,
							AFTER_SEX,
							UtilText.parseFromXMLFile("characters/dominion/dominionExpressCentaur", "INITIAL_ENCOUNTER_BLOWJOB_START", centaur())) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getPlayer(), centaur(), PenisMouth.GIVING_BLOWJOB_START, false, true));
						}
					};
					
				} else if(sexAreaWanted==SexAreaPenetration.TONGUE) {
					return new ResponseSex(
							"Rimjob",
							UtilText.parse(centaur(), "Drop down onto your knees behind [npc.name] and give [npc.herHim] a rimjob."),
							true,
							false,
							new SMDominionExpressEncounter(SexPosition.STANDING,
									Util.newHashMapOfValues(new Value<>(centaur(), SexSlotStanding.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.PERFORMING_ORAL_BEHIND)),
									Util.newHashMapOfValues(new Value<>(centaur(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))),
									Util.newHashMapOfValues(
											new Value<>(centaur(), Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH)))),
							null,
							null,
							AFTER_SEX,
							UtilText.parseFromXMLFile("characters/dominion/dominionExpressCentaur", "INITIAL_ENCOUNTER_RIMJOB_START", centaur())) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), centaur(), TongueAnus.ANILINGUS_START, false, true));
						}
					};
					
				} else if(sexAreaWanted==SexAreaOrifice.ANUS) {
					return new ResponseSex(
							"Mounted",
							UtilText.parse(centaur(), "Drop down onto your knees and kiss [npc.namePos] asshole, before presenting your own [pc.asshole+] to [npc.herHim] so that [npc.she] can mount and rut you."),
							true,
							false,
							new SMDominionExpressEncounter(SexPosition.ALL_FOURS,
									Util.newHashMapOfValues(new Value<>(centaur(), SexSlotAllFours.BEHIND)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS)),
									Util.newHashMapOfValues(new Value<>(centaur(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))),
									Util.newHashMapOfValues(
											new Value<>(centaur(), Util.newArrayListOfValues(CoverableArea.PENIS)),
											new Value<>(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS)))) {
								@Override
								public Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> getStartingWetAreas() {
									Map<GameCharacter, Map<SexAreaInterface, Map<GameCharacter, Set<LubricationType>>>> map = new HashMap<>();
									map.put(centaur(), new HashMap<>());
									map.get(centaur()).put(SexAreaOrifice.ANUS, new HashMap<>());
									map.get(centaur()).get(SexAreaOrifice.ANUS).put(Main.game.getPlayer(), Util.newHashSetOfValues(LubricationType.SALIVA));
									return map;
								}
							},
							null,
							null,
							AFTER_SEX,
							UtilText.parseFromXMLFile("characters/dominion/dominionExpressCentaur", "INITIAL_ENCOUNTER_MOUNTED_START", centaur())) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(new InitialSexActionInformation(centaur(), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
						}
					};
					
				}
			}
			return null;
		}
	};
	
	private static final DialogueNode AFTER_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(centaur(), "[npc.Name] has finished with you...");
		}
		@Override
		public void applyPreParsingEffects() {
			Main.game.getTextEndStringBuilder().append(Main.game.getDialogueFlags().incrementNatalyaPoints(slavePointsReward));
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("characters/dominion/dominionExpressCentaur", "AFTER_SEX", centaur());
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue",
						UtilText.parse(centaur(), "Leave [npc.name] to get on with [npc.her] work and continue on your way..."),
						Main.game.getDefaultDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/dominionExpressCentaur", "AFTER_SEX_LEAVE", centaur()));
						banishCentaur(true);
					}
				};
			}
			return null;
		}
	};
}
