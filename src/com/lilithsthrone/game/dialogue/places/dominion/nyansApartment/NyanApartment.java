package com.lilithsthrone.game.dialogue.places.dominion.nyansApartment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.character.npc.dominion.NyanMum;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexControl;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.nyan.SMNyanSex;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.game.sex.sexActions.baseActions.ClitClit;
import com.lilithsthrone.game.sex.sexActions.baseActions.FingerVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisBreasts;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.TongueVagina;
import com.lilithsthrone.game.sex.sexActions.baseActionsSelfPartner.PartnerSelfFingerVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.AbstractWorldType;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.AbstractPlaceType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public class NyanApartment {

	private static GameCharacter activeSexPartner;
	
	public static GameCharacter getActiveSexPartner() {
		if(activeSexPartner==null) {
			return getNyan();
		}
		return activeSexPartner;
	}

	public static void setActiveSexPartner(GameCharacter activeSexPartner) { //TODO remember to call this...
		NyanApartment.activeSexPartner = activeSexPartner;
	}

	private static Nyan getNyan() {
		return ((Nyan)Main.game.getNpc(Nyan.class));
	}

	private static NyanMum getNyanMum() {
		return ((NyanMum)Main.game.getNpc(NyanMum.class));
	}
	
	private static void travelTo(AbstractWorldType worldType, AbstractPlaceType placeType) {
		Main.game.getPlayer().setLocation(worldType, placeType);
		getNyan().setLocation(Main.game.getPlayer(), false);
	}

	public static final DialogueNode VISIT_START = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			travelTo(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_ENTRANCE);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "VISIT_START");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
			}
			return null;
		}
	};
	
	public static final DialogueNode HALLWAY = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "HALLWAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENTRANCE_HALL = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "ENTRANCE_HALL");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode NYAN_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "NYAN_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode ENSUITE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "ENSUITE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode SPARE_BEDROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SPARE_BEDROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode BATHROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "BATHROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode KITCHEN = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "KITCHEN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode DINING_ROOM = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DINING_ROOM");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode LOUNGE = new DialogueNode("", "", false) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "LOUNGE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode SOLO_SEX_FOREPLAY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			responses.add(
				new ResponseSex("Cunnilingus", "Give the horny cat-girl what she wants by eating her out.",
					true, true,
					new SMNyanSex(
							SexPosition.SITTING,
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.PERFORMING_ORAL)),
							Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotSitting.SITTING))) {
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.isPlayer()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
							}
						}
						@Override
						public boolean isCharacterStartNaked(GameCharacter character) {
							return character.isPlayer();
						}
					},
					null,
					null,
					SOLO_SEX_MAIN,
					UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					return Util.newArrayListOfValues(
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
				}
			});
			
			responses.add(
				new ResponseSex("Fingering", UtilText.parse(getActiveSexPartner(), "Push [npc.name] down on the bed and finger her while kissing her."),
					true, true,
					new SMNyanSex(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN_TWO)),
							Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
						@Override
						public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
							if(character.isPlayer()) {
								return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
							} else {
								return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
							}
						}
						@Override
						public boolean isCharacterStartNaked(GameCharacter character) {
							return character.isPlayer();
						}
					},
					null,
					null,
					SOLO_SEX_MAIN,
					UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_FINGERING" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
				@Override
				public List<InitialSexActionInformation> getInitialSexActions() {
					return Util.newArrayListOfValues(
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueMouth.KISS_START, false, true),
							new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), FingerVagina.FINGERING_START, false, true));
				}
			});
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (blowjob)":""),
							UtilText.parse(getActiveSexPartner(), "As you are unable to access your penis, you cannot sixty-nine with [npc.name]!"),
							null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (blowjob)":""), "Due to your [pc.legRace]'s lower body, you cannot use this position!", null));
							
				} else {
					responses.add(
							new ResponseSex("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (blowjob)":""),
									UtilText.parse(getActiveSexPartner(), "Get [npc.name] to straddle your face and bend down to suck your cock while you're eating her out."),
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_SIXTY_NINE_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasPenis()?" (cunnilingus)":""),
							UtilText.parse(getActiveSexPartner(), "As you are unable to access your vagina, you cannot sixty-nine with [npc.name]!"),
							null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Sixty-nine"+(Main.game.getPlayer().hasVagina()?" (cunnilingus)":""), "Due to your [pc.legRace]'s lower body, you cannot use this position!", null));
							
				} else {
					responses.add(
							new ResponseSex("Sixty-nine"+(Main.game.getPlayer().hasPenis()?" (cunnilingus)":""),
									UtilText.parse(getActiveSexPartner(), "Get [npc.name] to straddle your face and bend down to eat you out while you're returning the favour."),
									true, true,
									new SMNyanSex(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.SIXTY_NINE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_SIXTY_NINE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Receive blowjob",
							UtilText.parse(getActiveSexPartner(), "As you are unable to access your penis, you cannot get [npc.name] to suck your cock!"),
							null)); 
				} else {
					responses.add(
							new ResponseSex("Receive blowjob",
									UtilText.parse(getActiveSexPartner(), "Get [npc.name] to suck your cock."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisMouth.BLOWJOB_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "[npc.Paizuri]"),
							UtilText.parse(getActiveSexPartner(), "As you are unable to access your penis, you cannot get [npc.name] to give you a tit-fuck!"),
							null)); 
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "[npc.Paizuri]"),
									UtilText.parse(getActiveSexPartner(),
											getActiveSexPartner().isBreastFuckablePaizuri()
												?"Get [npc.name] to kneel down and wrap her [npc.breasts+] around your cock so that you can get a tit-fuck."
												:"Get [npc.name] to kneel down and press her [npc.breasts+] against your cock so that you can grind up against them."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.BREAST);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
											return Util.newHashMapOfValues(
													new Value<>(getNyan(), Util.newArrayListOfValues(CoverableArea.NIPPLES)));
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_PAIZURI" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisBreasts.FUCKING_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Receive cunnilingus",
							UtilText.parse(getActiveSexPartner(), "As you are unable to access your vagina, you cannot get [npc.name] to perform cunnilingus on you!"),
							null)); 
				} else {
					responses.add(
							new ResponseSex("Receive cunnilingus",
									UtilText.parse(getActiveSexPartner(), "Get [npc.name] to eat you out."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotSitting.PERFORMING_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									SOLO_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_RECEIVE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
			}
			
			responses.add(
					new ResponseSex("Face-sitting",
							UtilText.parse(getActiveSexPartner(), "Get [npc.name] to sit on your face and eat her out."), 
							true, true,
							new SMNyanSex(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
									Util.newHashMapOfValues(new Value<>(getActiveSexPartner(), SexSlotLyingDown.FACE_SITTING))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return character.isPlayer();
								}
							},
							null,
							null,
							SOLO_SEX_MAIN,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_FACE_SITTING" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});

			if(Main.game.isAnalContentEnabled()) {
				if((getActiveSexPartner() instanceof Nyan) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
						responses.add(new Response("Ask about anal", "Ask Nyan if she'd be comfortable doing anal stuff with you.", SOLO_FOREPLAY_ANAL_TALK));
					
				} else if((getActiveSexPartner() instanceof NyanMum) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Ask about anal", "Ask [nyanmum.name] if she'd be comfortable doing anal stuff with you.", SOLO_FOREPLAY_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Perform anilingus",
								"Get the horny cat-girl to present you with her ass and start rimming her.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return true;
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								SOLO_SEX_MAIN,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_FOREPLAY_PERFORM_ANILINGUS"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
				}
			}
			if(index > 0 && index - 1 < responses.size()) {
				return responses.get(index - 1);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_FOREPLAY_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(getActiveSexPartner() instanceof Nyan) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_ANAL_TALK"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SOLO_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode SOLO_SEX_MAIN = new DialogueNode("Moving on", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getActiveSexPartner(), "[npc.Name] has had enough of foreplay...");
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Missionary", UtilText.parse(getActiveSexPartner(), "As you are unable to access your penis, you cannot fuck [npc.name]!"), null)); 
					
				} else {
					responses.add(
							new ResponseSex("Missionary",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"Get Nyan to lie back and present herself to you, before taking her virginity."
										:UtilText.parse(getActiveSexPartner(), "Get [npc.name] to lie back and present herself to you, before fucking her."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_MISSIONARY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Doggy-style", UtilText.parse(getActiveSexPartner(), "As you are unable to access your penis, you cannot fuck [npc.name]!"), null)); 
					
				} else {
					responses.add(
							new ResponseSex("Doggy-style",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"Get Nyan to drop down on all fours and present herself to you, before taking her virginity."
										:UtilText.parse(getActiveSexPartner(), "Get [npc.name] to drop down on all fours and present herself to you, before fucking her."),
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_DOGGY" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisVagina.PENIS_FUCKING_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Cowgirl", UtilText.parse(getActiveSexPartner(), "As you are unable to access your penis, you cannot fuck [npc.name]!"), null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Cowgirl", "Due to your lower [pc.legRace]'s body, you cannot use this position...", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Cowgirl",
									getActiveSexPartner() instanceof Nyan && getNyan().isVaginaVirgin()
										?"Take Nyan's virginity by having her ride your cock."
										:UtilText.parse(getActiveSexPartner(), "Have [npc.name] ride your cock in the cowgirl position."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.COWGIRL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_COWGIRL" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getActiveSexPartner(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Receive blowjob", "As you are unable to access your penis, you cannot have your cock sucked!", null)); 
				} else {
					responses.add(
							new ResponseSex("Receive blowjob", UtilText.parse(getActiveSexPartner(), "Get [npc.name] to suck your cock."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_BLOWJOB" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getActiveSexPartner(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Scissor ([npc.name] bottom)"),
							UtilText.parse(getActiveSexPartner(), "As you are unable to access your vagina, you cannot scissor with [npc.name]!"),
							null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Scissor ([npc.name] bottom)"), "Due to your lower [pc.legRace]'s body, you cannot use this position...", null));
					
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "Scissor ([npc.name] bottom)"),
									UtilText.parse(getActiveSexPartner(), "Get [npc.name] to lie back and scissor with her."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_SCISSOR_TOP" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), ClitClit.TRIBBING_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Scissor ([npc.name] top)"), UtilText.parse(getActiveSexPartner(), "As you are unable to access your vagina, you cannot scissor with [npc.name]!"), null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response(UtilText.parse(getActiveSexPartner(), "Scissor ([npc.name] top)"), "Due to your lower [pc.legRace]'s body, you cannot use this position...", null));
					
				} else {
					responses.add(
							new ResponseSex(UtilText.parse(getActiveSexPartner(), "Scissor ([npc.name] top)"),
									UtilText.parse(getActiveSexPartner(), "Lie back and get [npc.name] to scissor with you."),
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), SexSlotLyingDown.SCISSORING))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_SCISSOR_BOTTOM" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), ClitClit.TRIBBING_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Receive cunnilingus", "As you are unable to access your vagina, you cannot receive cunnilingus!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Receive cunnilingus",
									UtilText.parse(getActiveSexPartner(), "Get [npc.name] to eat you out."),
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getActiveSexPartner(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return true;
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_SOLO_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_RECEIVE_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				responses.add(
						new ResponseSex("Perform cunnilingus",
							"Get the horny cat-girl to present you with her pussy and eat her out.",
							true, true,
							new SMNyanSex(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
									Util.newHashMapOfValues(
											new Value<>(getActiveSexPartner(), SexSlotLyingDown.LYING_DOWN))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return true;
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.FULL;
									}
									return SexControl.ONGOING_ONLY;
								}
							},
							null,
							null,
							POST_SOLO_SEX,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_PERFORM_CUNNILINGUS" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueVagina.CUNNILINGUS_START, false, true));
						}
					});
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if((getActiveSexPartner() instanceof Nyan) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk)) {
						responses.add(new Response("Ask about anal", "Ask Nyan if she'd be comfortable doing anal stuff with you.", SOLO_SEX_ANAL_TALK));
					
				} else if((getActiveSexPartner() instanceof NyanMum) && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Ask about anal", "Ask [nyanmum.name] if she'd be comfortable doing anal stuff with you.", SOLO_SEX_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Perform anilingus",
								"Get the horny cat-girl to present you with her ass and start rimming her.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return true;
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								POST_SOLO_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_PERFORM_ANILINGUS"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), TongueAnus.ANILINGUS_START, false, true));
							}
						});
					if(Main.game.getPlayer().hasPenis()) {
						if(!penisAccess) {
							responses.add(new Response("Anal", UtilText.parse(getActiveSexPartner(), "As you are unable to access your penis, you cannot fuck [npc.name]!"), null)); 
							
						} else {
							responses.add(
									new ResponseSex("Anal",
											getActiveSexPartner() instanceof Nyan && getNyan().isAssVirgin()
												?"Get Nyan to drop down on all fours and present herself to you, before taking her anal virginity."
												:"Get [pc.name] to drop down on all fours and present herself to you, before fucking her ass.",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getActiveSexPartner(), SexSlotAllFours.ALL_FOURS))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
													} else {
														return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return true;
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_SOLO_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_SEX_MAIN_ANAL" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"))) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getActiveSexPartner(), PenisAnus.PENIS_FUCKING_START, false, true));
										}
									});
						}
					}
				}
			}
			
			if(index > 0 && index - 1 < responses.size()) {
				return responses.get(index - 1);
			}
			return null;
		}
	};

	public static final DialogueNode SOLO_SEX_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			if(getActiveSexPartner() instanceof Nyan) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			} else {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
			}
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "SOLO_ANAL_TALK"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return SOLO_SEX_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX = new DialogueNode("Finished", "", true) {
		@Override
		public String getDescription() {
			return UtilText.parse(getActiveSexPartner(), "[npc.Name] is satisfied and puts an end to the sex...");
		}
		@Override
		public void applyPreParsingEffects() {
			getActiveSexPartner().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, getNyan().hasStatusEffect(StatusEffect.PREGNANT_0));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", UtilText.parse(getActiveSexPartner(), "Let [npc.name] rest and go sleep with her in her bed."), POST_SOLO_SEX_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
				getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			}
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_SLEEP" +(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM"));
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Kitchen", "Head into the kitchen...", POST_SOLO_SEX_KITCHEN);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SOLO_SEX_KITCHEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().wearCasual();
			}
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyan(), getNyan(), null), true);
				
			} else if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyanMum(), getNyanMum(), null), true);
			}
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_KITCHEN"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM")));
//			if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied) && getNyan().isPregnant())
//					|| (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied) && getNyanMum().isPregnant())) {
//				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "PREGNANCY_ADDITION"));
//			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Goodbye",
						Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)
							?"Kiss Nyan and [nyanmum.name] goodbye and tell them that you'll see them soon."
							:"Kiss Nyan goodbye and tell her that you'll see her soon.",
						POST_SEX_MORNING_NO_CONTENT) {
					@Override
					public void effects() {
						getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_SOLO_SEX_LEAVE"+(getActiveSexPartner() instanceof Nyan?"_NYAN":"_NYANMUM")));
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_SEX_MORNING_NO_CONTENT = new DialogueNode("", "", false, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_LOUNGE);
			}
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_NYAN_APARTMENT);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return Main.game.getDefaultDialogue(false).getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_SEX_FOREPLAY = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, false);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumCreampied, false);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(index==1) {
				return new ResponseSex("Perform cunnilingus", "Get the horny cat-girls to present you with their pussies and eat them both out.",
						true, true,
						new SMNyanSex(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
								} else if(character instanceof Nyan) {
									if(targetedCharacter.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								} else {
									if(targetedCharacter.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
									} else {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
									}
								}
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
						},
						null,
						null,
						DOUBLE_SEX_MAIN,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_CUNNILINGUS")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.CUNNILINGUS_START, false, true),
								new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Fingering", "Push the horny cat-girls down on [nyanmum.namePos] bed and start fingering them.",
						true, true,
						new SMNyanSex(
								SexPosition.LYING_DOWN,
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
								Util.newHashMapOfValues(
										new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
										new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
							@Override
							public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
								if(character.isPlayer()) {
									return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA);
								} else {
									return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER);
								}
							}
							@Override
							public boolean isPartnerWantingToStopSex(GameCharacter partner) {
								return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
							}
							@Override
							public boolean isCharacterStartNaked(GameCharacter character) {
								return character.isPlayer();
							}
						},
						null,
						null,
						DOUBLE_SEX_MAIN,
						UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_FINGERING")) {
					@Override
					public List<InitialSexActionInformation> getInitialSexActions() {
						return Util.newArrayListOfValues(
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), FingerVagina.FINGERING_START, false, true),
								new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), FingerVagina.FINGERING_START, false, true));
					}
				};
			}
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Receive blowjob", "As you are unable to access your penis, you cannot have your cock sucked!", null)); 
				} else {
					responses.add(
							new ResponseSex("Receive blowjob", "Get Nyan and [nyanmum.name] to suck your cock.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.SITTING,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotSitting.SITTING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotSitting.PERFORMING_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_TWO:SexSlotSitting.PERFORMING_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
											}
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return partner instanceof NyanMum && Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									DOUBLE_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_RECEIVE_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Receive cunnilingus", "As you are unable to access your vagina, you cannot receive cunnilingus!", null)); 
				} else {
					responses.add(
							new ResponseSex("Receive cunnilingus", "Get Nyan and [nyanmum.name] to eat you out.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
											}
										}
										@Override
										public boolean isPartnerWantingToStopSex(GameCharacter partner) {
											return partner instanceof NyanMum && Main.sex.isOrgasmCountMet(Main.game.getPlayer(), 1, true);
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer();
										}
									},
									null,
									null,
									DOUBLE_SEX_MAIN,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.CUNNILINGUS_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Ask about anal", "Ask Nyan and [nyanmum.name] if they'd be comfortable doing anal stuff with you.", DOUBLE_FOREPLAY_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Perform anilingus", "Get the horny cat-girls to present you with their asses and start rimming them.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
												new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else if(character instanceof Nyan) {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										} else {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										}
									}
									@Override
									public boolean isPartnerWantingToStopSex(GameCharacter partner) {
										return partner instanceof NyanMum && Main.sex.getNumberOfOrgasms(getNyan())>=1 && Main.sex.getNumberOfOrgasms(getNyanMum())>=1;
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return character.isPlayer();
									}
								},
								null,
								null,
								DOUBLE_SEX_MAIN,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_FOREPLAY_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true),
										new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
							}
						});
				}
			}
			
			if(index > 0 && index - 3 < responses.size()) {
				return responses.get(index - 3);
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_FOREPLAY_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOUBLE_SEX_FOREPLAY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode DOUBLE_SEX_MAIN = new DialogueNode("Moving on", "Nyan and [nyanmum.name] seem to have had enough of foreplay for now...", true) {
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			List<Response> responses = new ArrayList<>();
			
			boolean penisAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true);
			boolean vaginaAccess = Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
			
			if(Main.game.getPlayer().hasPenis()) {
				if(!penisAccess) {
					responses.add(new Response("Missionary (Nyan)", "As you are unable to access your penis, you cannot fuck Nyan or [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Missionary (Nyan)",
									getNyan().isVaginaVirgin()
										?"Get Nyan and [nyanmum.name] to lie back on the bed and present themselves to you, before taking Nyan's virginity."
										:"Get Nyan and [nyanmum.name] to lie back on the bed and present themselves to you, before penetrating Nyan's pussy.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_MISSIONARY_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyanMum(), PartnerSelfFingerVagina.PARTNER_SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Missionary ([nyanmum.name])", "As you are unable to access your penis, you cannot fuck Nyan or [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Missionary ([nyanmum.name])", "Get Nyan and [nyanmum.name] to lie back on the bed and present themselves to you, before penetrating [nyanmum.namePos] [nyanmum.pussy+].",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_MISSIONARY_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyan(), PartnerSelfFingerVagina.PARTNER_SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Doggy-style (Nyan)", "As you are unable to access your penis, you cannot fuck Nyan or [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Doggy-style (Nyan)",
									getNyan().isVaginaVirgin()
										?"Get Nyan and [nyanmum.name] to get down on all fours and present themselves to you, before taking Nyan's virginity."
										:"Get Nyan and [nyanmum.name] to get down on all fours and present themselves to you, before penetrating Nyan's pussy.",
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
													new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_DOGGY_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyanMum(), PartnerSelfFingerVagina.PARTNER_SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Doggy-style ([nyanmum.name])", "As you are unable to access your penis, you cannot fuck Nyan or [nyanmum.name]!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Doggy-style ([nyanmum.name])", "Get Nyan and [nyanmum.name] to get down on all fours and present themselves to you, before penetrating [nyanmum.namePos] [nyanmum.pussy+].",
									true, true,
									new SMNyanSex(
											SexPosition.ALL_FOURS,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
													new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_DOGGY_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisVagina.PENIS_FUCKING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyan(), PartnerSelfFingerVagina.PARTNER_SELF_FINGER_VAGINA_PENETRATION, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Cowgirl (Nyan)", "As you are unable to access your penis, you cannot fuck Nyan or [nyanmum.name]!", null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Cowgirl (Nyan)", "Due to your lower [pc.legRace]'s body, you cannot use this position...", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Cowgirl (Nyan)",
									getNyan().isVaginaVirgin()
										?"Take Nyan's virginity by having her ride your cock, while [nyanmum.name] sits on your face."
										:"Get Nyan to ride your cock, while [nyanmum.name] sits on your face.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.COWGIRL),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_COWGIRL_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Cowgirl ([nyanmum.name])", "As you are unable to access your penis, you cannot fuck Nyan or [nyanmum.name]!", null)); 
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Cowgirl ([nyanmum.name])", "Due to your lower [pc.legRace]'s body, you cannot use this position...", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Cowgirl ([nyanmum.name])", "Get [nyanmum.name] to ride your cock, while Nyan sits on your face.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.COWGIRL))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_COWGIRL_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisVagina.USING_PENIS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				if(!penisAccess) {
					responses.add(new Response("Receive blowjob", "As you are unable to access your penis, you cannot have your cock sucked!", null)); 
				} else {
					responses.add(
							new ResponseSex("Receive blowjob", "Get Nyan and [nyanmum.name] to suck your cock.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_BLOWJOB")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), PenisMouth.GIVING_BLOWJOB_START_ADDITIONAL, false, true));
								}
							});
				}
			}
			if(Main.game.getPlayer().hasVagina()) {
				if(!vaginaAccess) {
					responses.add(new Response("Scissor (Nyan bottom)", "As you are unable to access your vagina, you cannot scissor with Nyan!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Scissor (Nyan bottom)", "Due to your lower [pc.legRace]'s body, you cannot use this position...", null));
					
				} else {
					responses.add(
							new ResponseSex("Scissor (Nyan bottom)", "Get Nyan to lie back and scissor with her while [nyanmum.name] sits on her face.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSOR_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Scissor ([nyanmum.name] bottom)", "As you are unable to access your vagina, you cannot scissor with [nyanmum.name]!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Scissor ([nyanmum.name] bottom)", "Due to your lower [pc.legRace]'s body, you cannot use this position...", null));
					
				} else {
					responses.add(
							new ResponseSex("Scissor ([nyanmum.name] bottom)", "Get [nyanmum.name] to lie back and scissor with her while [nyan.name] sits on her face.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.SCISSORING)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSOR_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyan(), getNyanMum(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Scissored (Nyan top)", "As you are unable to access your vagina, you cannot scissor with Nyan!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Scissored (Nyan top)", "Due to your lower [pc.legRace]'s body, you cannot use this position...", null));
					
				} else {
					responses.add(
							new ResponseSex("Scissored (Nyan top)", "Lie back and get Nyan to scissor with you while [nyanmum.name] sits on your face.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.SCISSORING),
													new Value<>(getNyanMum(), SexSlotLyingDown.FACE_SITTING_REVERSE))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSORED_NYAN")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Scissored ([nyanmum.name] top)", "As you are unable to access your vagina, you cannot scissor with [nyanmum.name]!", null));
					
				} else if(Main.game.getPlayer().isTaur()) {
					responses.add(new Response("Scissored ([nyanmum.name] top)", "Due to your lower [pc.legRace]'s body, you cannot use this position...", null));
					
				} else {
					responses.add(
							new ResponseSex("Scissored ([nyanmum.name] top)", "Lie back and get Nyan to scissor with you while [nyanmum.name] sits on your face.",
									true, true,
									new SMNyanSex(
											SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), SexSlotLyingDown.FACE_SITTING_REVERSE),
													new Value<>(getNyanMum(), SexSlotLyingDown.SCISSORING))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												if(targetedCharacter instanceof Nyan) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.CLIT);
												}
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.CLIT, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_SCISSORED_NYANMUM")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(getNyanMum(), Main.game.getPlayer(), ClitClit.TRIBBING_START, false, true),
											new InitialSexActionInformation(getNyan(), Main.game.getPlayer(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true),
											new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
								}
							});
				}
				if(!vaginaAccess) {
					responses.add(new Response("Receive cunnilingus", "As you are unable to access your vagina, you cannot receive cunnilingus!", null)); 
					
				} else {
					responses.add(
							new ResponseSex("Receive cunnilingus", "Get Nyan and [nyanmum.name] to eat you out.",
									true, true,
									new SMNyanSex(
											Main.game.getPlayer().isTaur()
												?SexPosition.STANDING
												:SexPosition.LYING_DOWN,
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), Main.game.getPlayer().isTaur()?SexSlotStanding.STANDING_DOMINANT:SexSlotLyingDown.LYING_DOWN)),
											Util.newHashMapOfValues(
													new Value<>(getNyan(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND:SexSlotLyingDown.MISSIONARY_ORAL),
													new Value<>(getNyanMum(), Main.game.getPlayer().isTaur()?SexSlotStanding.PERFORMING_ORAL_BEHIND_TWO:SexSlotLyingDown.MISSIONARY_ORAL_TWO))) {
										@Override
										public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
											if(character.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.TONGUE);
											} else if(character instanceof Nyan) {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE);
												}
											} else {
												if(targetedCharacter.isPlayer()) {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
												} else {
													return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE);
												}
											}
										}
										@Override
										public boolean isCharacterStartNaked(GameCharacter character) {
											return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
										}
										@Override
										public SexControl getSexControl(GameCharacter character) {
											if(character.isPlayer()) {
												return SexControl.FULL;
											}
											return SexControl.ONGOING_ONLY;
										}
									},
									null,
									null,
									POST_DOUBLE_SEX,
									UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_RECEIVE_CUNNILINGUS")) {
								@Override
								public List<InitialSexActionInformation> getInitialSexActions() {
									return Util.newArrayListOfValues(
											new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueVagina.RECEIVING_CUNNILINGUS_START, false, true));
								}
							});
				}
				responses.add(
						new ResponseSex("Perform cunnilingus", "Get the horny cat-girls to present you with their pussies and eat them both out.",
							true, true,
							new SMNyanSex(
									SexPosition.LYING_DOWN,
									Util.newHashMapOfValues(
											new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MISSIONARY_ORAL)),
									Util.newHashMapOfValues(
											new Value<>(getNyan(), SexSlotLyingDown.LYING_DOWN),
											new Value<>(getNyanMum(), SexSlotLyingDown.LYING_DOWN_TWO))) {
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(character.isPlayer()) {
										return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
									} else if(character instanceof Nyan) {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
										}
									} else {
										if(targetedCharacter.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE);
										} else {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
										}
									}
								}
								@Override
								public boolean isCharacterStartNaked(GameCharacter character) {
									return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
								}
								@Override
								public SexControl getSexControl(GameCharacter character) {
									if(character.isPlayer()) {
										return SexControl.FULL;
									}
									return SexControl.ONGOING_ONLY;
								}
							},
							null,
							null,
							POST_DOUBLE_SEX,
							UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_CUNNILINGUS")) {
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							return Util.newArrayListOfValues(
									new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), TongueVagina.CUNNILINGUS_START, false, true),
									new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
						}
					});
			}
			
			if(Main.game.isAnalContentEnabled()) {
				if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanAnalTalk) || !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumAnalTalk)) {
					responses.add(new Response("Ask about anal", "Ask Nyan and [nyanmum.name] if they'd be comfortable doing anal stuff with you.", DOUBLE_SEX_ANAL_TALK));
					
				} else {
					responses.add(
							new ResponseSex("Perform anilingus", "Get the horny cat-girls to present you with their asses and start rimming them.",
								true, true,
								new SMNyanSex(
										SexPosition.ALL_FOURS,
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND_ORAL)),
										Util.newHashMapOfValues(
												new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
												new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
									@Override
									public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
										if(character.isPlayer()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS);
										} else if(character instanceof Nyan) {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										} else {
											if(targetedCharacter.isPlayer()) {
												return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE);
											} else {
												return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
											}
										}
									}
									@Override
									public boolean isCharacterStartNaked(GameCharacter character) {
										return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
									}
									@Override
									public SexControl getSexControl(GameCharacter character) {
										if(character.isPlayer()) {
											return SexControl.FULL;
										}
										return SexControl.ONGOING_ONLY;
									}
								},
								null,
								null,
								POST_DOUBLE_SEX,
								UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANILINGUS")) {
							@Override
							public List<InitialSexActionInformation> getInitialSexActions() {
								return Util.newArrayListOfValues(
										new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), TongueAnus.ANILINGUS_START, false, true),
										new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true));
							}
						});
					
					if(Main.game.getPlayer().hasPenis()) {
						if(!penisAccess) {
							responses.add(new Response("Anal (Nyan)", "As you are unable to access your penis, you cannot fuck Nyan's ass!", null)); 
							
						} else {
							responses.add(
									new ResponseSex("Anal (Nyan)",
											getNyan().isAssVirgin()
												?"Get Nyan and [nyanmum.name] to get down on all fours and present themselves to you, before taking Nyan's anal virginity."
												:"Get Nyan and [nyanmum.name] to get down on all fours and present themselves to you, before penetrating Nyan's asshole.",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
															new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														if(targetedCharacter instanceof Nyan) {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														}
													} else if(character instanceof Nyan) {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													} else {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_DOUBLE_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANAL_NYAN")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getNyan(), PenisAnus.PENIS_FUCKING_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyanMum(), PartnerSelfFingerVagina.PARTNER_SELF_FINGER_VAGINA_PENETRATION, false, true));
										}
									});
						}
						if(!penisAccess) {
							responses.add(new Response("Anal ([nyanmum.name])", "As you are unable to access your penis, you cannot fuck [nyanmum.namePos] ass!", null)); 
							
						} else {
							responses.add(
									new ResponseSex("Anal ([nyanmum.name])",
											"Get Nyan and [nyanmum.name] to get down on all fours and present themselves to you, before penetrating [nyanmum.namePos] asshole.",
											true, true,
											new SMNyanSex(
													SexPosition.ALL_FOURS,
													Util.newHashMapOfValues(
															new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
													Util.newHashMapOfValues(
															new Value<>(getNyan(), SexSlotAllFours.ALL_FOURS),
															new Value<>(getNyanMum(), SexSlotAllFours.ALL_FOURS_TWO))) {
												@Override
												public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
													if(character.isPlayer()) {
														if(targetedCharacter instanceof Nyan) {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
														}
													} else if(character instanceof Nyan) {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													} else {
														if(targetedCharacter.isPlayer()) {
															return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
														} else {
															return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.MOUTH);
														}
													}
												}
												@Override
												public boolean isCharacterStartNaked(GameCharacter character) {
													return character.isPlayer() || !character.isAbleToAccessCoverableArea(CoverableArea.VAGINA, false);
												}
												@Override
												public SexControl getSexControl(GameCharacter character) {
													if(character.isPlayer()) {
														return SexControl.FULL;
													}
													return SexControl.ONGOING_ONLY;
												}
											},
											null,
											null,
											POST_DOUBLE_SEX,
											UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_SEX_MAIN_ANAL_NYANMUM")) {
										@Override
										public List<InitialSexActionInformation> getInitialSexActions() {
											return Util.newArrayListOfValues(
													new InitialSexActionInformation(Main.game.getPlayer(), getNyanMum(), PenisAnus.PENIS_FUCKING_START, false, true),
													new InitialSexActionInformation(getNyanMum(), getNyan(), TongueMouth.KISS_START, false, true),
													new InitialSexActionInformation(getNyan(), getNyan(), PartnerSelfFingerVagina.PARTNER_SELF_FINGER_VAGINA_PENETRATION, false, true));
										}
									});
						}
					}
				}
			}
			if(index==0 && responses.size()>14) {
				return responses.get(14);
			}
			if(index > 0) {
				if(index>14) {
					if(index < responses.size()) {
						return responses.get(index);
					}
				} else if(index - 1 < responses.size()) {
					return responses.get(index - 1);
				}
			}
			return null;
		}
	};

	public static final DialogueNode DOUBLE_SEX_ANAL_TALK = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanAnalTalk, true);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumAnalTalk, true);
		}
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "DOUBLE_ANAL_TALK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return DOUBLE_SEX_MAIN.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX = new DialogueNode("Finished", "Nyan and [nyanmum.name] are satisfied and put an end to the sex...", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			getNyanMum().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getPlayer().applyWash(true, true, StatusEffect.CLEANED_SHOWER, 8*60);
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanCreampied, getNyan().hasStatusEffect(StatusEffect.PREGNANT_0));
			Main.game.getDialogueFlags().setFlag(DialogueFlagValue.nyanmumCreampied, getNyanMum().hasStatusEffect(StatusEffect.PREGNANT_0));
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Sleep", "Let the cat-girls rest and go sleep with them.", POST_DOUBLE_SEX_SLEEP);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX_SLEEP = new DialogueNode("", "", true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
			getNyan().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
				getNyanMum().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
			}
			Main.game.getPlayer().applySleep(Main.game.getMinutesUntilTimeInMinutes(8*60));
		}
		@Override
		public int getSecondsPassed() {
			return Main.game.getMinutesUntilTimeInMinutes(8*60)*60;
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_SLEEP");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Kitchen", "Head into the kitchen...", POST_DOUBLE_SEX_KITCHEN);
			}
			return null;
		}
	};
	
	public static final DialogueNode POST_DOUBLE_SEX_KITCHEN = new DialogueNode("", "", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getNyan().equipClothing();
			getNyan().wearApron(true);
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumGirlfriend)) {
				getNyanMum().wearCasual();
			}
			Main.game.getPlayer().setLocation(WorldType.NYANS_APARTMENT, PlaceType.NYAN_APARTMENT_KITCHEN);
		}
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyanMum(), getNyanMum(), null), true);
			}
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied)) {
				UtilText.addSpecialParsingString(ItemEffectType.PREGNANCY_TEST.applyEffect(null, null, null, 0, getNyan(), getNyan(), null), false);
			} 
			StringBuilder sb = new StringBuilder();
			sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_KITCHEN"));
//			if((Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanCreampied) && getNyan().isPregnant())
//					|| (Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.nyanmumCreampied) && getNyanMum().isPregnant())) {
//				sb.append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "PREGNANCY_ADDITION"));
//			}
			return sb.toString();
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Goodbye", "Kiss Nyan and [nyanmum.name] goodbye and tell them that you'll see them soon.", POST_SEX_MORNING_NO_CONTENT) {
					@Override
					public void effects() {
						getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyanMum().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
						getNyan().wearApron(false);
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/nyansApartment/core", "POST_DOUBLE_SEX_LEAVE"));
					}
				};
				
			}
			return null;
		}
	};
}
