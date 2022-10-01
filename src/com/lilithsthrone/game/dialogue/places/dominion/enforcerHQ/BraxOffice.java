package com.lilithsthrone.game.dialogue.places.dominion.enforcerHQ;

import java.util.stream.Collectors;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Brax;
import com.lilithsthrone.game.character.npc.dominion.CandiReceptionist;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.TransformativePotion;
import com.lilithsthrone.game.sex.managers.dominion.SMBraxDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.5
 * @author Innoxia
 */
public class BraxOffice {

	public static void setBraxsPostQuestStatus(boolean applyPlayerLocationChange) {
		Main.game.getNpc(Brax.class).setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK, true);
		Main.game.getNpc(Brax.class).setPendingClothingDressing(true);
		Main.game.getNpc(Brax.class).setAffection(Main.game.getPlayer(), -50);
		
		Main.game.getNpc(CandiReceptionist.class).addSlave(Main.game.getNpc(Brax.class));
		Main.game.getWorlds().get(WorldType.ENFORCER_HQ).getCell(PlaceType.ENFORCER_HQ_BRAXS_OFFICE).getInventory().clearNonEquippedInventory(true);
		
		if(applyPlayerLocationChange) {
			Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
		}
	}
	
	public static void givePlayerEnforcerUniform(StringBuilder sb) {
		if(sb==null) {
			sb = new StringBuilder();
		}
		if(Main.game.getPlayer().isFeminine()) {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfskirt", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_flsldshirt", PresetColour.CLOTHING_PINK, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_PINK, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfpumps", PresetColour.CLOTHING_BLACK, false), false));

			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_bwhat", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
			
		} else {
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdslacks", PresetColour.CLOTHING_BLACK, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_lsldshirt", PresetColour.CLOTHING_BLUE, false), false));
			
			AbstractClothing jacket = Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdjacket", PresetColour.CLOTHING_BLACK, PresetColour.CLOTHING_BLUE, PresetColour.CLOTHING_GOLD, false);
			jacket.setSticker("collar", "tab_pc");
			jacket.setSticker("name", "name_pc");
			sb.append(Main.game.getPlayer().addClothing(jacket, false));
			
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_servequipset_enfdbelt", PresetColour.CLOTHING_DESATURATED_BROWN, false), false));
			sb.append(Main.game.getPlayer().addClothing(Main.game.getItemGen().generateClothing("dsg_eep_tacequipset_cboots", PresetColour.CLOTHING_BLACK, false), false));
			
			AbstractClothing hat = Main.game.getItemGen().generateClothing("dsg_eep_ptrlequipset_pcap", PresetColour.CLOTHING_BLACK, false);
			hat.setSticker("badge", "badge_dominion");
			sb.append(Main.game.getPlayer().addClothing(hat, false));
		}

		TransformativePotion tfPotion = Main.game.getNpc(Brax.class).generateTransformativePotion(Main.game.getPlayer());
		AbstractItem potion = EnchantingUtils.craftItem(
			Main.game.getItemGen().generateItem(tfPotion.getItemType()),
			tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
		potion.setName("Brax's Surprise");
		sb.append(Main.game.getPlayer().addItem(potion, false));
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
						null, null, null, Femininity.FEMININE, Util.newArrayListOfValues(Subspecies.WOLF_MORPH)){
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
		public boolean isTravelDisabled() {
			return !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN);
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_REPEAT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1 && !Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
				return new ResponseCombat("Fight", "[brax.name] looks like he's ready to give you another beating!", Main.game.getNpc(Brax.class));
			}
			return null;
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
			return UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "INTERIOR_BRAX_LIE_BLUFFING_SUCCESS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Exit", "Leave the Enforcer HQ.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						setBraxsPostQuestStatus(true);
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
				return new Response("Exit", "Leave the Enforcer HQ.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						setBraxsPostQuestStatus(true);
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
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_DOMINATE"));
				
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
						UtilText.parseFromXMLFile("places/dominion/enforcerHQ/brax", "AFTER_COMBAT_VICTORY_SUBMIT"));
				
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
				return new Response("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						setBraxsPostQuestStatus(true);
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
							"Due to your <b style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
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
						
						TransformativePotion tfPotion = Main.game.getNpc(Brax.class).generateTransformativePotion(Main.game.getPlayer());
						AbstractItem potion = EnchantingUtils.craftItem(
							Main.game.getItemGen().generateItem(tfPotion.getItemType()),
							tfPotion.getEffects().stream().map(x -> x.getEffect()).collect(Collectors.toList()));
						potion.setName("Brax's Surprise");
						
						Main.game.getNpc(Brax.class).useItem(potion, Main.game.getPlayer(), false);
						
						Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, PresetColour.EYE_YELLOW));
						Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, PresetColour.COVERING_BLACK), true);
						Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, PresetColour.COVERING_WHITE), true);
						
						if(Main.getProperties().forcedFetishPercentage!=0 && !Main.game.getPlayer().hasFetish(Fetish.FETISH_SUBMISSIVE)) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE));
						}
						if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)<CorruptionLevel.TWO_HORNY.getMinimumValue()) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, CorruptionLevel.TWO_HORNY.getMinimumValue()));
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
				return new Response("Carry on", "Get up and carry on your way.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						if(Main.game.getPlayer().isQuestProgressGreaterThan(QuestLine.MAIN, Quest.MAIN_1_C_WOLFS_DEN)) {
							setBraxsPostQuestStatus(true);
							
						} else {
							Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ, false);
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
				return new Response("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.", PlaceType.DOMINION_ENFORCER_HQ.getDialogue(false)) {
					@Override
					public void effects() {
						setBraxsPostQuestStatus(true);
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
