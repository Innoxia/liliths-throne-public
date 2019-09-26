package com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5
 * @author Innoxia
 */
public class BraxOffice {

	private static void setBraxsPostQuestStatus() {
		Main.game.getNpc(Brax.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK, true);
		Main.game.getNpc(Brax.class).setPendingClothingDressing(true);
		Main.game.getNpc(Brax.class).setAffection(Main.game.getPlayer(), -50);
		
		Main.game.getNpc(CandiReceptionist.class).addSlave(Main.game.getNpc(Brax.class));
		Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory().clearNonEquippedInventory();
		
		Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
	}
	
	private static void givePlayerEnforcerUniform(StringBuilder sb) {
		if(Main.game.getPlayer().isFeminine()) {
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfskirt", Colour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_flsldshirt", Colour.CLOTHING_PINK, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdjacket_pc", Colour.CLOTHING_BLACK, Colour.CLOTHING_PINK, Colour.CLOTHING_GOLD, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdbelt", Colour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfpumps", Colour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_bwhat", Colour.CLOTHING_BLACK, false), false));
			
		} else {
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdslacks", Colour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_lsldshirt", Colour.CLOTHING_BLUE, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdjacket_pc", Colour.CLOTHING_BLACK, Colour.CLOTHING_BLUE, Colour.CLOTHING_GOLD, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_servequipset_enfdbelt", Colour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_tacequipset_cboots", Colour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing("dsg_eep_ptrlequipset_pcap", Colour.CLOTHING_BLACK, false), false));
		}
	}
	
	public static final DialogueNode INTERIOR_BRAX = new DialogueNode("[brax.namePos] Office", "-", true) {

		@Override
		public int getSecondsPassed() {
			return 2*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Truth", "Tell [brax.name] who you are, and that you're here to find out what happened to Arthur.", INTERIOR_BRAX_TRUTH) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 2) {
				return new Response("Lie", "You notice that all of the models in the posters are wolf-girls. Perhaps you could pretend that Arthur is a patron of an exclusive wolf-girl themed brothel that you so happen to own...",
						INTERIOR_BRAX_LIE,
						null, null, Util.newArrayListOfValues(Perk.OBSERVANT), null, null) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else if (index == 3) {
				return new Response("Wolf-tease", "Use your feminine wolf-like body to tease [brax.name] into giving you information about Arthur.", INTERIOR_BRAX_GETTING_TEASED,
						null, null, null, Femininity.FEMININE, Race.WOLF_MORPH){
					@Override
					public void effects(){
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.braxEncountered, true);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_REPEAT = new DialogueNode("[brax.namePos] Office", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_REPEAT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[brax.name] looks like he's ready to give you another beating!", Main.game.getNpc(Brax.class));
					
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTERIOR_BRAX_TRUTH = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_TRUTH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "If you want to find out what happened to Arthur, you're going to have to fight [brax.name]!", Main.game.getNpc(Brax.class));
					
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode INTERIOR_BRAX_LIE = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Keep on bluffing", "Imply that 'The She-wolf's Den' is a brothel you own. If he'll give you information about Arthur, you'll give him VIP status.", INTERIOR_BRAX_LIE_BLUFFING);
					
			} else if (index == 2) {
				return new Response("Drop the act", "Tell [brax.name] that he's an idiot and you're here to find out what he's done with Arthur.", INTERIOR_BRAX_LIE_IDIOT_BRAX);
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_IDIOT_BRAX = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_IDIOT_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[brax.name] looks extremely embarrassed, and you're sure that you've given yourself at least a small advantage by tricking him like this!", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(30);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Let him go", "Tell [brax.name] to have fun. From your directions, it'll take at least a couple of hours before he figures out he's been fooled.", INTERIOR_BRAX_LIE_BLUFFING_SUCCESS){
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder());
					}
				};
					
			} else if (index == 2) {
				return new Response("Stop [brax.name]", "Tell [brax.name] that he's an idiot and you're going to beat him up for being such a gullible fool.", INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX);
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_SUCCESS = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave the Enforcer HQ.") {
					@Override
					public void effects() {
						setBraxsPostQuestStatus();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING_IDIOT_BRAX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "[brax.name] looks extremely embarrassed, and you're sure that you've given yourself a big advantage by tricking him like this!", Main.game.getNpc(Brax.class)){
					@Override
					public void effects(){
						Main.game.getNpc(Brax.class).setLustNoText(50);
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Read", "Read the piece of paper [brax.name] just handed to you.", INTERIOR_BRAX_GETTING_TEASED_UH_OH) {
					@Override
					public void effects(){
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
					}
				};
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_UH_OH = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Escape", "Push [brax.name] off of you and make a quick excuse before running away.", INTERIOR_BRAX_GETTING_TEASED_ESCAPE) {
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder());
					}
				};
					
			} else if (index == 2) {
				return new ResponseSex("Get fucked", "Let [brax.name] take control and fuck you.",
						true, false, 
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH_GET_FUCKED"));
//				givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder()); //TODO
					
			} else if (index == 3) {
				return new ResponseSex("Take control", "Take control of the situation and turn [brax.name] into your little bitch.", Util.newArrayListOfValues(Fetish.FETISH_DOMINANT),
						null, null, null, null, null,
						true, false,
						new SMGeneric(
								Util.newArrayListOfValues(Main.game.getPlayer()),
								Util.newArrayListOfValues(Main.game.getNpc(Brax.class)),
								null,
								null),
						AFTER_DOMINANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_UH_OH_TAKE_CONTROL"));
//				givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder()); //TODO
					
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode INTERIOR_BRAX_GETTING_TEASED_ESCAPE = new DialogueNode("[brax.namePos] Office", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_GETTING_TEASED_ESCAPE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Exit", "Leave the Enforcer HQ.") {
					@Override
					public void effects() {
						setBraxsPostQuestStatus();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	
	//----------- BRAX COMBAT/SEX -----------
	

	public static final DialogueNode AFTER_COMBAT_VICTORY = new DialogueNode("Victory", "", true) {

		@Override
		public String getDescription() {
			return "You have defeated Brax!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Leave", "You really don't want to have sex with Brax. Leave his office and continue on your way.", AFTER_COMBAT_VICTORY_NO_SEX){
					@Override
					public void effects() {
						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder());
					}
				};
				
			} else if (index == 2) {
				return new ResponseSex("Dominate Brax",
						"Brax's broken, horny form is too much for you to resist, and you can't help but smile down deviously at the wolf-boy as you prepare to make him your bitch.",
						Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null, CorruptionLevel.TWO_HORNY, null, null, null,
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotStanding.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotStanding.PERFORMING_ORAL))),
						null,
						null,
						AFTER_DOMINANT_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_DOMINATE")) {
					@Override
					public void effects() {
//						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder()); //TODO
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("Submit to Brax",
						"Although you've defeated him, your submissive nature is causing you to consider letting Brax dominantly fuck you...",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, CorruptionLevel.TWO_HORNY, null, Femininity.FEMININE, null,
						false, true,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_SUBMIT")) {
					@Override
					public void effects() {
//						givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder()); //TODO
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_VICTORY_NO_SEX = new DialogueNode("", "", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_NO_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.") {
					@Override
					public void effects() {
						setBraxsPostQuestStatus();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_COMBAT_DEFEAT = new DialogueNode("Defeat", "", true) {
		
		@Override
		public String getDescription() {
			return "You have been defeated by Brax!";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_DEFEAT");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.isSpittingDisabled()) {
					return Response.getDisallowedSpittingResponse();
				}
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
					return new Response("Spit",
							"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
								+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
							null);
				} else {
					return new Response("Spit", "Spit out the transformative liquid.", AFTER_DEFEAT_TRANSFORMATION_REFUSED);
				}
				
			} else if (index == 2) {
				return new Response("Swallow",
						"Do as Brax says and swallow the strange liquid.",
						AFTER_DEFEAT_TRANSFORMATION,
						Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxTransformedPlayer);
						
						if(Main.getProperties().forcedFetishPercentage != 0) {
							Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE);
						}
						
						switch(Main.getProperties().getForcedTFPreference()) {
							case HUMAN:
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								if(!Main.game.getPlayer().hasVagina()) {
									Main.game.getPlayer().setVaginaType(VaginaType.HUMAN);
								}
								break;
								
							case MINIMUM: // ears, eyes, tails, horns, antenna, and wings
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								if(!Main.game.getPlayer().hasVagina()) {
									Main.game.getPlayer().setVaginaType(VaginaType.HUMAN);
								}
								Main.game.getPlayer().setEarType(EarType.LYCAN);
								Main.game.getPlayer().setEyeType(EyeType.LYCAN);
								Main.game.getPlayer().setTailType(TailType.LYCAN);
								Main.game.getPlayer().setHornType(HornType.NONE);
								Main.game.getPlayer().setAntennaType(AntennaType.NONE);
								Main.game.getPlayer().setWingType(WingType.NONE);
								Main.game.getPlayer().setHairType(HairType.LYCAN);
								break;
								
							case REDUCED:
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								Main.game.getPlayer().setVaginaType(VaginaType.WOLF_MORPH);
								
								Main.game.getPlayer().setEarType(EarType.LYCAN);
								Main.game.getPlayer().setEyeType(EyeType.LYCAN);
								Main.game.getPlayer().setTailType(TailType.LYCAN);
								Main.game.getPlayer().setHornType(HornType.NONE);
								Main.game.getPlayer().setAntennaType(AntennaType.NONE);
								Main.game.getPlayer().setWingType(WingType.NONE);
								Main.game.getPlayer().setHairType(HairType.LYCAN);
								
								Main.game.getPlayer().setBreastType(BreastType.WOLF_MORPH);
								Main.game.getPlayer().setAssType(AssType.WOLF_MORPH);
								Main.game.getPlayer().setArmType(ArmType.WOLF_MORPH);
								Main.game.getPlayer().setLegType(LegType.WOLF_MORPH);
								
								Main.game.getPlayer().setBreastRows(3);
								break;
								
							case NORMAL: case MAXIMUM:
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								Main.game.getPlayer().setVaginaType(VaginaType.WOLF_MORPH);
								
								Main.game.getPlayer().setEarType(EarType.LYCAN);
								Main.game.getPlayer().setEyeType(EyeType.LYCAN);
								Main.game.getPlayer().setTailType(TailType.LYCAN);
								Main.game.getPlayer().setHornType(HornType.NONE);
								Main.game.getPlayer().setAntennaType(AntennaType.NONE);
								Main.game.getPlayer().setWingType(WingType.NONE);
								Main.game.getPlayer().setHairType(HairType.LYCAN);
								
								Main.game.getPlayer().setBreastType(BreastType.WOLF_MORPH);
								Main.game.getPlayer().setAssType(AssType.WOLF_MORPH);
								Main.game.getPlayer().setArmType(ArmType.WOLF_MORPH);
								Main.game.getPlayer().setLegType(LegType.WOLF_MORPH);
								
								Main.game.getPlayer().setSkinType(SkinType.LYCAN);
								Main.game.getPlayer().setFaceType(FaceType.LYCAN);
								
								Main.game.getPlayer().setBreastRows(3);
								break;
						}
						
						Main.game.getPlayer().setFemininity(Femininity.FEMININE_STRONG.getMinimumFemininity());
						Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						if(Main.game.getPlayer().getVaginaWetness().getValue()<Wetness.THREE_WET.getValue()) {
							Main.game.getPlayer().setVaginaWetness(Wetness.THREE_WET.getValue());
						}
						
						Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_YELLOW));
						Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_BLACK), true);
						Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_WHITE), true);
						
						if(Main.game.getPlayer().getBreastRawSizeValue()<CupSize.E.getMeasurement()) {
							Main.game.getPlayer().setBreastSize(CupSize.E.getMeasurement());
						}
						if(Main.game.getPlayer().getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
							Main.game.getPlayer().setHipSize(HipSize.FOUR_WOMANLY.getValue());
						}
						if(Main.game.getPlayer().getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
							Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE.getValue());
						}
						Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
						Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
						
						
						if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)<CorruptionLevel.TWO_HORNY.getMinimumValue()) {
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, CorruptionLevel.TWO_HORNY.getMinimumValue());
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_TRANSFORMATION_REFUSED = new DialogueNode("Brax's Office", "In Brax's Office after being forced to drink the potion.", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_REFUSED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Dominated", "Brax is far too strong for you to resist...",
						false, false,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null, AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_REFUSED_DOMINATED"));
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_DEFEAT_TRANSFORMATION = new DialogueNode("Brax's Office", "In Brax's Office after being forced to drink the potion.", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Obey",
						"The arousing liquid you've just been forced to drink is forcing you to obey, and you eagerly fall down on all fours so that Brax can fuck you, doggy-style.",
						false, false,
						new SMBraxDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Brax.class), SexSlotAllFours.BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))),
						null,
						null,
						AFTER_SUBMISSIVE_SEX,
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DEFEAT_TRANSFORMATION_OBEY"));
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_SUBMISSIVE_SEX = new DialogueNode("Brax is done", "Brax has finished having his fun with you.", true) {
		@Override
		public void applyPreParsingEffects() {
			if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
				givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder());
			}
		}
		
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_SUBMISSIVE_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Carry on", "Get up and carry on your way.") {
					@Override
					public void effects() {
						if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
							setBraxsPostQuestStatus();
						} else {
							Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNode AFTER_DOMINANT_SEX = new DialogueNode("Brax collapses", "Brax collapses and you return to his office.", true) {
		@Override
		public void applyPreParsingEffects() {
			givePlayerEnforcerUniform(Main.game.getTextEndStringBuilder());
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_DOMINANT_SEX");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
						setBraxsPostQuestStatus();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
