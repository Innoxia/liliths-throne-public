package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.npc.misc.OffspringSeed;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;


/**
 * @since 0.3.5.5
 * @version 0.4
 * @author Innoxia
 */
public class LilayaBirthing {

	/**
	 * For pre-offspring seed support.
	 */
	public static String getOffspringDescriptor(GameCharacter offspring) {
		List<String> descriptors = new ArrayList<>();
		descriptors.add(offspring.getBodyShape().getName(false));
		descriptors.add(offspring.getHeight().getDescriptor());
		descriptors.add(offspring.getFemininity().getName(false));
		return Util.randomItemFrom(descriptors);
	}

	public static String getOffspringDescriptor(OffspringSeed offspring) {
		List<String> descriptors = new ArrayList<>();
		descriptors.add(offspring.getBodyShape().getName(false));
		descriptors.add(offspring.getHeight().getDescriptor());
		descriptors.add(offspring.getFemininity().getName(false));
		return Util.randomItemFrom(descriptors);
	}
	
	public static final DialogueNode LILAYA_ASSISTS_PREGNANCY = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 5*60;
		}
		
		@Override
		public String getContent() {
			PlayerCharacter player = Main.game.getPlayer();
			GameCharacter lilaya = Main.game.getNpc(Lilaya.class);
			
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_PREGNANCY_START"));
			
			// Player has had sex with Lilaya before:
			if(player.getTotalTimesHadSex(lilaya) > 0) {
				if(player.getPotentialPartnersAsMother().stream().anyMatch(x -> x.getFather() == lilaya)) {
					if(player.getPotentialPartnersAsMother().stream().anyMatch(x -> x.getFather() != lilaya)) {
						// Lilaya might be the 'father':
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_PREGNANCY_LILAYA_POSSIBLY_FATHER"));
						
					} else {
						// Lilaya is definitely the 'father':
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_PREGNANCY_LILAYA_DEFINITELY_FATHER"));
					}
					
				} else {
					// Lilaya is definitely not the 'father':
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_PREGNANCY_LILAYA_DEFINITELY_NOT_FATHER"));
				}
			}
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_PREGNANCY_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().hasStatusEffect(StatusEffect.PREGNANT_3)) {
					return new Response("Give birth", "Tell Lilaya that you're ready to give birth.", LILAYA_DETECTS_BIRTHING_TYPE){
						@Override
						public void effects() {
							if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
							}
						}
					};
				} else {
					return new Response("Give birth", "You need to wait until your belly has finished growing before you're able to give birth.", null){
						@Override
						public void effects() {
							if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
								Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
							}
						}
					};
				}

			} else if (index == 0) {
				return new Response("Back", "Tell Lilaya that you need a moment to think.", Lab.LAB_EXIT){
					@Override
					public void effects() {
						if (Main.game.getPlayer().isVisiblyPregnant() && !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.reactedToPregnancyLilaya)) {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, true);
						}
						Main.game.getTextStartStringBuilder().append(
								"<p>"
									+ "[pc.speech(Thanks for your help, Lilaya, but can I just have a moment to think?)] you ask, smiling at your demonic [lilaya.relation(pc)]."
								+ "</p>"
								+ "<p>"
									+ "[lilaya.speech(Sure, just let me know if you need anything else!)] she says, before backing off a little to give you some space."
								+ "</p>");
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_PREGNANCY_REPEAT = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_PREGNANCY_REPEAT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return LILAYA_ASSISTS_PREGNANCY.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode LILAYA_DETECTS_BIRTHING_TYPE = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getLabel() {
			return "Giving birth";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_DETECTS_BIRTHING_TYPE");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().isVaginaEggLayer()) {
					return new Response("Follow Lilaya", "Allow Lilaya to lead you up to your room.", LILAYA_ASSISTS_EGG_LAYING) {
						@Override
						public void effects() {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
						}
					};
				} else {
					return new Response("Follow Lilaya", "Allow Lilaya to lead you to the birthing room.", LILAYA_ASSISTS_BIRTHING) {
						@Override
						public void effects() {
							Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_BIRTHING_ROOM, false);
							Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_BIRTHING_ROOM, false);
						}
					};
				}

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_BIRTHING = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}

		@Override
		public String getLabel() {
			return "Birthing Room";
		}

		@Override
		public String getContent() {
			if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "ASSIST_BIRTHING_SLIME");
			}
			
			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "ASSIST_BIRTHING_FIRST_TIME");
				
			} else {
				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "ASSIST_BIRTHING");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Start", "Tell Lilaya that you're ready to give birth now.", LILAYA_ASSISTS_BIRTHING_DELIVERS){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, false);
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);
						
						if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
							Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
							Main.game.getPlayer().incrementVaginaCapacity(
									(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
									false);
						}
					}
				};

			} else if (index == 2) {
				return new Response("Knock out", "Ask Lilaya if she could give you something to knock you out. After all, she said you didn't need to be conscious for this.", LILAYA_ASSISTS_BIRTHING_KNOCK_OUT){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, false);
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);

						if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
							Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
							Main.game.getPlayer().incrementVaginaCapacity(
									(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
									false);
						}
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNode LILAYA_ASSISTS_BIRTHING_DELIVERS = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 240*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_BIRTHING_DELIVERS"));
			
			UtilText.nodeContentSB.append("<p>"
					+ "<i>"
					+ "You hear Lilaya speaking from somewhere beneath you, but you can't make out what she's saying..."
					+ "<br/><br/>");

			if(Main.game.getPlayer().getBreastRawMilkStorageValue() > 0
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
					&& Main.game.getPlayer().getNippleShape()==NippleShape.NORMAL
					&& Main.game.isLactationContentEnabled()) {
				UtilText.nodeContentSB.append("You feel a desperate suckling at your nipples, and you're vaguely aware of something greedily drinking down mouthfuls of your [pc.milk]...");
			} else {
				UtilText.nodeContentSB.append("You feel a weight on your chest, and you're vaguely aware of something greedily drinking a bottle of milk as you cradle it in your arms...");
			}
			
			String offspringId = Util.randomItemFrom(Main.game.getPlayer().getLastLitterBirthed().getOffspring());
			try {
				if(offspringId.contains("NPCOffspring")) { // If the offspring is from the pre-offspring seed PR, handle them in the old way:
					GameCharacter offspring = Main.game.getNPCById(offspringId);
					if(offspring.isFeminine()) {
						UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
									+offspring.getSubspecies().getSingularFemaleName(offspring.getBody())
									+" bending down over you, who gives you a loving hug and a kiss on your cheek before departing...");
					} else {
						UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
									+offspring.getSubspecies().getSingularMaleName(offspring.getBody())
									+" bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
					}
					
				} else {
					OffspringSeed offspring = Main.game.getOffspringSeedById(offspringId);
					if(offspring.isFeminine()) {
						UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
									+offspring.getSubspecies().getSingularFemaleName(offspring.getBody())
									+" bending down over you, who gives you a loving hug and a kiss on your cheek before departing...");
					} else {
						UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
									+offspring.getSubspecies().getSingularMaleName(offspring.getBody())
									+" bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
					}
				}
			} catch(Exception ex) {
			}
			
			UtilText.nodeContentSB.append("</i>"
					+ "</p>");
					
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pass out", "You have no energy left, and can't stay conscious any longer...", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_UTIL_COMPLETE));
						}
						
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));

						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_BIRTHING_KNOCK_OUT = new DialogueNode("Your room", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 240*60;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_BIRTHING_KNOCK_OUT");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Pass out", "The drink Lilaya gave you goes straight to your head, and you collapse back onto the bed as you lose consciousness.", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_UTIL_COMPLETE));
						}
						
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));

						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_EGG_LAYING = new DialogueNode("", "", true) {
		
		@Override
		public int getSecondsPassed() {
			return 10*60;
		}

		@Override
		public String getLabel() {
			return "Your room";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Lay eggs", "Tell Lilaya that you're ready to lay your eggs now.", LILAYA_ASSISTS_EGG_LAYING_DELIVERS){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.reactedToPregnancyLilaya, false);
						Main.game.getPlayer().endPregnancy(true);
						Main.game.getPlayer().setMana(0);

						if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
							Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
							Main.game.getPlayer().incrementVaginaCapacity(
									(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
									false);
						}
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_EGG_LAYING_DELIVERS = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 30*60;
		}

		@Override
		public String getContent() {
			UtilText.addSpecialParsingString(Util.intToString(Main.game.getPlayer().getLastLitterBirthed().getTotalLitterCount()), true);
			
			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_DELIVERS");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response(
						Main.game.getPlayer().getLastLitterBirthed().getTotalLitterCount()==1
							?"Protect the egg"
							:"Protect the eggs!",
						Main.game.getPlayer().getLastLitterBirthed().getTotalLitterCount()==1
							?"Why is Lilaya getting so close?! Maybe she wants to take your egg for herself!"
							:"Why is Lilaya getting so close?! Maybe she wants to take your eggs for herself!",
						LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS) {
					@Override
					public void effects() {
						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS = new DialogueNode("", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 24*60*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS"));
			
			String offspringId = Util.randomItemFrom(Main.game.getPlayer().getLastLitterBirthed().getOffspring());
			try {
				if(offspringId.contains("NPCOffspring")) { // If the offspring is from the pre-offspring seed PR, handle them in the old way:
					GameCharacter offspring = Main.game.getNPCById(offspringId);
					if(offspring.isFeminine()) {
						UtilText.addSpecialParsingString(offspring.getSubspecies().getSingularFemaleName(offspring.getBody()), true);
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS_FEMININE_HATCHING"));
						
					} else {
						UtilText.addSpecialParsingString(offspring.getSubspecies().getSingularMaleName(offspring.getBody()), true);
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS_MASCULINE_HATCHING"));
					}
					
				} else {
					OffspringSeed offspring = Main.game.getOffspringSeedById(offspringId);
					if(offspring.isFeminine()) {
						UtilText.addSpecialParsingString(offspring.getSubspecies().getSingularFemaleName(offspring.getBody()), true);
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS_FEMININE_HATCHING"));
						
					} else {
						UtilText.addSpecialParsingString(offspring.getSubspecies().getSingularMaleName(offspring.getBody()), true);
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS_MASCULINE_HATCHING"));
					}
				}
			} catch(Exception ex) {
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Some time later", "You eventually wake up from your exhausted slumber...", LILAYA_ASSISTS_BIRTHING_FINISHED){
					@Override
					public void effects() {
						if (Main.game.getPlayer().getQuest(QuestLine.SIDE_FIRST_TIME_PREGNANCY) == Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE) {
							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_PREGNANCY, Quest.SIDE_UTIL_COMPLETE));
						}
						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode LILAYA_ASSISTS_BIRTHING_FINISHED = new DialogueNode("Your room", "", true, true) {
		
		@Override
		public int getSecondsPassed() {
			return 2*60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_BIRTHING_FINISHED"));
			
			UtilText.nodeContentSB.append(
					"<p style='text-align:center;'>"
					+ "In the picture you see:");
			
			for(String id : Main.game.getPlayer().getLastLitterBirthed().getOffspring()) {
				try {
					if(id.contains("NPCOffspring")) { // If the offspring is from the pre-offspring seed PR, handle them in the old way:
						GameCharacter offspring = Main.game.getNPCById(id);
						String descriptor = getOffspringDescriptor(offspring);
						UtilText.nodeContentSB.append("<br/>"
								+ Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+" "+descriptor
								+ " <i style='color:"+offspring.getGender().getColour().toWebHexString()+";'>"+offspring.getGender().getName()+"</i>"
								+ (offspring.isFeral() ? " <i style='color:"+RaceStage.FERAL.getColour().toWebHexString()+";'>"+RaceStage.FERAL.getName()+"</i>" : "")
								+ " <i style='color:"+offspring.getSubspecies().getColour(offspring).toWebHexString()+";'>"+UtilText.parse(offspring,"[npc.race]")+"</i>");
						
					} else {
						OffspringSeed offspring = Main.game.getOffspringSeedById(id);
						String descriptor = getOffspringDescriptor(offspring);
						UtilText.nodeContentSB.append("<br/>"
								+ Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+" "+descriptor
								+ (offspring.isFeral() ? " <i style='color:"+RaceStage.FERAL.getColour().toWebHexString()+";'>"+RaceStage.FERAL.getName()+"</i>" : "")
								+ " <i style='color:"+offspring.getSubspecies().getColour(null).toWebHexString()+";'>"+offspring.getSubspecies().getName(offspring.getBody())+"</i>"
								+ " <i style='color:"+offspring.getGender().getColour().toWebHexString()+";'>"+offspring.getGenderName()+"</i>");
					}
				} catch(Exception ex) {
				}
			}
			
			UtilText.nodeContentSB.append("</p>"
					+ "<p>"
					+ "After taking a minute to get your emotions under control, you put the picture away for safe-keeping, and think about what to do next."
					+ "</p>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Get up", "Get out of bed, ready for a new day.", RoomPlayer.ROOM);

			} else {
				return null;
			}
		}
	};
	
	
//	// Incubation:
//	
//	public static final DialogueNode LILAYA_ASSISTS_INCUBATION = new DialogueNode("", "", true, true) {
//		@Override
//		public int getSecondsPassed() {
//			return 5*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION");
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			Set<SexAreaOrifice> incubationAreas = Main.game.getPlayer().getIncubatingLitters().keySet();
//			
//			if (index == 0) {
//				return new Response("Back", "Tell Lilaya that you need a moment to think.", Lab.LAB_EXIT){
//					@Override
//					public void effects() {
//						Main.game.getTextStartStringBuilder().append(
//								"<p>"
//									+ "[pc.speech(Thanks for your help, Lilaya, but can I just have a moment to think?)] you ask, smiling at your demonic [lilaya.relation(pc)]."
//								+ "</p>"
//								+ "<p>"
//									+ "[lilaya.speech(Sure, just let me know if you need anything else!)] she says, before backing off a little to give you some space."
//								+ "</p>");
//					}
//				};
//				
//			} else if(index==1) {
//				if(!incubationAreas.contains(SexAreaOrifice.VAGINA)) {
//					return new Response("Lay eggs (womb)", "You don't have any eggs being incubated in your womb at the moment...", null);
//				} else {
//					if(Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
//						return new Response("Lay eggs (womb)", "Tell Lilaya that you're ready to lay the eggs you've been incubating in your womb.", LILAYA_INCUBATION_EGG_LAYING_START) {
//							@Override
//							public void effects() {
//								layingEggsAreas = Util.newHashSetOfValues(SexAreaOrifice.VAGINA);
//							}
//						};
//						
//					} else {
//						return new Response("Lay eggs (womb)", "You need to wait until the eggs in your womb have finished growing before you're able to lay them.", null);
//					}
//				}
//				
//			} else if(index==2) {
//				if(!incubationAreas.contains(SexAreaOrifice.ANUS)) {
//					return new Response("Lay eggs (stomach)", "You don't have any eggs being incubated in your stomach at the moment...", null);
//				} else {
//					if(Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
//						return new Response("Lay eggs (stomach)", "Tell Lilaya that you're ready to lay the eggs you've been incubating in your stomach.", LILAYA_INCUBATION_EGG_LAYING_START) {
//							@Override
//							public void effects() {
//								layingEggsAreas = Util.newHashSetOfValues(SexAreaOrifice.ANUS);
//							}
//						};
//						
//					} else {
//						return new Response("Lay eggs (stomach)", "You need to wait until the eggs in your stomach have finished growing before you're able to lay them.", null);
//					}
//				}
//				
//			} else if(index==3) {
//				if(!incubationAreas.contains(SexAreaOrifice.NIPPLE)) {
//					return new Response("Lay eggs (breasts)", "You don't have any eggs being incubated in your breasts at the moment...", null);
//				} else {
//					if(Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
//						return new Response("Lay eggs (breasts)", "Tell Lilaya that you're ready to lay the eggs you've been incubating in your breasts.", LILAYA_INCUBATION_EGG_LAYING_START) {
//							@Override
//							public void effects() {
//								layingEggsAreas = Util.newHashSetOfValues(SexAreaOrifice.NIPPLE);
//							}
//						};
//						
//					} else {
//						return new Response("Lay eggs (breasts)", "You need to wait until the eggs in your breasts have finished growing before you're able to lay them.", null);
//					}
//				}
//				
//			} else if(index==4) {
//				String udderName = Main.game.getPlayer().getBreastCrotchShape()==BreastShape.UDDERS?"udders":"crotch-boobs";
//				if(!incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH)) {
//					return new Response("Lay eggs ("+udderName+")", "You don't have any eggs being incubated in your [pc.crotchBoobs] at the moment...", null);
//				} else {
//					if(Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
//						return new Response("Lay eggs ("+udderName+")", "Tell Lilaya that you're ready to lay the eggs you've been incubating in your [pc.crotchBoobs].", LILAYA_INCUBATION_EGG_LAYING_START) {
//							@Override
//							public void effects() {
//								layingEggsAreas = Util.newHashSetOfValues(SexAreaOrifice.NIPPLE_CROTCH);
//							}
//						};
//						
//					} else {
//						return new Response("Lay eggs ("+udderName+")", "You need to wait until the eggs in your [pc.crotchBoobs] have finished growing before you're able to lay them.", null);
//					}
//				}
//				
//			} else if(index==5) {
//				if(incubationAreas.size()<=1) {
//					return new Response("Lay eggs (all)", "Eggs are only being incubated in one of your orifices at the moment...", null);
//				} else {
//					Set<SexAreaOrifice> readyToLay = new HashSet<>();
//					if(incubationAreas.contains(SexAreaOrifice.VAGINA) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_WOMB_3)) {
//						readyToLay.add(SexAreaOrifice.VAGINA);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.ANUS) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_STOMACH_3)) {
//						readyToLay.add(SexAreaOrifice.ANUS);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.NIPPLE_CROTCH) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_NIPPLES_CROTCH_3)) {
//						readyToLay.add(SexAreaOrifice.NIPPLE_CROTCH);
//					}
//					if(incubationAreas.contains(SexAreaOrifice.SPINNERET) && Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
//						readyToLay.add(SexAreaOrifice.SPINNERET);
//					}
//					if(readyToLay.size()<=1) {
//						return new Response("Lay eggs (all)", "You need to wait until the eggs in at least two of your orifices have finished growing before you're able to lay them all at once.", null);
//					} else {
//						return new Response("Lay eggs (all)", "Tell Lilaya that you're ready to lay all of the matured eggs which you've been incubating in your orifices.", LILAYA_INCUBATION_EGG_LAYING_START) {
//							@Override
//							public void effects() {
//								layingEggsAreas = new HashSet<>(readyToLay);
//							}
//						};
//					}
//				}
//				
//			} else if(index==6 && incubationAreas.contains(SexAreaOrifice.SPINNERET)) {
//				if(Main.game.getPlayer().hasStatusEffect(StatusEffect.INCUBATING_EGGS_SPINNERET_3)) {
//					return new Response("Lay eggs (spinneret)", "Tell Lilaya that you're ready to lay the eggs you've been incubating in your spinneret.", LILAYA_INCUBATION_EGG_LAYING_START) {
//						@Override
//						public void effects() {
//							layingEggsAreas = Util.newHashSetOfValues(SexAreaOrifice.SPINNERET);
//						}
//					};
//				} else {
//					return new Response("Lay eggs (spinneret)", "You need to wait until the eggs in your spinneret have finished growing before you're able to lay them.", null);
//				}
//			}
//			
//			return null;
//		}
//	};
//	
//	public static final DialogueNode LILAYA_ASSISTS_INCUBATION_REPEAT = new DialogueNode("", "", true, true) {
//		@Override
//		public int getSecondsPassed() {
//			return 30;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION_REPEAT");
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			return LILAYA_ASSISTS_PREGNANCY.getResponse(responseTab, index);
//		}
//	};
//	
//
//	public static final DialogueNode LILAYA_INCUBATION_EGG_LAYING_START = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 2*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_INCUBATION_EGG_LAYING_START");
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Follow Lilaya", "Allow Lilaya to lead you to the birthing room.", LILAYA_ASSISTS_INCUBATION_EGG_LAYING) {
//					@Override
//					public void effects() {
//						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_BIRTHING_ROOM, false);
//						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_BIRTHING_ROOM, false);
//					}
//				};
//			}
//			return null;
//		}
//	};
//	
//	private static void applyEggLayingEffects() {
//		incubationOffspringBirthed = new HashSet<>();
//		for(SexAreaOrifice orifice : layingEggsAreas) {
//			incubationOffspringBirthed.addAll(Main.game.getPlayer().getIncubationLitter(orifice).getOffspring());
//			Main.game.getPlayer().endIncubationPregnancy(orifice, true);
//			switch(orifice) {
//				case ANUS:
//					if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
//						Main.game.getPlayer().incrementAssStretchedCapacity(15);
//						Main.game.getPlayer().incrementAssCapacity(
//								(Main.game.getPlayer().getAssStretchedCapacity()-Main.game.getPlayer().getAssRawCapacityValue())*Main.game.getPlayer().getAssPlasticity().getCapacityIncreaseModifier(),
//								false);
//					}
//					break;
//				case NIPPLE:
//					if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
//						Main.game.getPlayer().incrementNippleStretchedCapacity(15);
//						Main.game.getPlayer().incrementNippleCapacity(
//								(Main.game.getPlayer().getNippleStretchedCapacity()-Main.game.getPlayer().getNippleRawCapacityValue())*Main.game.getPlayer().getNipplePlasticity().getCapacityIncreaseModifier(),
//								false);
//					}
//					break;
//				case NIPPLE_CROTCH:
//					if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
//						Main.game.getPlayer().incrementNippleCrotchStretchedCapacity(15);
//						Main.game.getPlayer().incrementNippleCrotchCapacity(
//								(Main.game.getPlayer().getNippleCrotchStretchedCapacity()-Main.game.getPlayer().getNippleCrotchRawCapacityValue())*Main.game.getPlayer().getNippleCrotchPlasticity().getCapacityIncreaseModifier(),
//								false);
//					}
//					break;
//				case VAGINA:
//					if(Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.SLIME) {
//						Main.game.getPlayer().incrementVaginaStretchedCapacity(15);
//						Main.game.getPlayer().incrementVaginaCapacity(
//								(Main.game.getPlayer().getVaginaStretchedCapacity()-Main.game.getPlayer().getVaginaRawCapacityValue())*Main.game.getPlayer().getVaginaPlasticity().getCapacityIncreaseModifier(),
//								false);
//					}
//					break;
//				default:
//					break;
//			}
//		}
//		Main.game.getPlayer().setMana(0);
//	
//	}
//	
//	public static final DialogueNode LILAYA_ASSISTS_INCUBATION_EGG_LAYING = new DialogueNode("", "", true) {
//		@Override
//		public int getSecondsPassed() {
//			return 10*60;
//		}
//		@Override
//		public String getContent() {
//			if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
//				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION_EGG_LAYING_SLIME");
//			}
//			
//			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
//				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION_EGG_LAYING_FIRST_TIME");
//				
//			} else {
//				return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION_EGG_LAYING");
//			}
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Start", "Tell Lilaya that you're ready to lay your eggs now.", LILAYA_ASSISTS_INCUBATION_DELIVERS){
//					@Override
//					public void effects() {
//						applyEggLayingEffects();
//					}
//				};
//
//			} else if (index == 2) {
//				return new Response("Knock out", "Ask Lilaya if she could give you something to knock you out. After all, she said you didn't need to be conscious for this.", LILAYA_ASSISTS_INCUBATION_KNOCK_OUT){
//					@Override
//					public void effects() {
//						applyEggLayingEffects();
//					}
//				};
//			}
//			return null;
//		}
//	};
//
//	public static final DialogueNode LILAYA_ASSISTS_INCUBATION_DELIVERS = new DialogueNode("", "", true, true) {
//		@Override
//		public int getSecondsPassed() {
//			return 240*60;
//		}
//		@Override
//		public String getContent() {
//			UtilText.nodeContentSB.setLength(0);
//
//			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION_DELIVERS"));
//			
//			UtilText.nodeContentSB.append("<p>"
//					+ "<i>"
//					+ "You hear Lilaya speaking from somewhere beneath you, but you can't make out what she's saying..."
//					+ "<br/><br/>");
//
//			if(Main.game.getPlayer().getBreastRawMilkStorageValue() > 0
//					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
//					&& Main.game.getPlayer().getNippleShape()==NippleShape.NORMAL
//					&& Main.game.isLactationContentEnabled()) {
//				UtilText.nodeContentSB.append("You feel a desperate suckling at your nipples, and you're vaguely aware of something greedily drinking down mouthfuls of your [pc.milk]...");
//			} else {
//				UtilText.nodeContentSB.append("You feel a weight on your chest, and you're vaguely aware of something greedily drinking a bottle of milk as you cradle it in your arms...");
//			}
//			
//			String offspringId = Util.randomItemFrom(incubationOffspringBirthed);
//			try {
//				GameCharacter offspring = Main.game.getNPCById(offspringId);
//				if(offspring.isFeminine()) {
//					UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
//								+offspring.getSubspecies().getSingularFemaleName(offspring)
//								+" bending down over you, who gives you a loving hug and a kiss on your cheek before departing...");
//				} else {
//					UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
//								+offspring.getSubspecies().getSingularMaleName(offspring)
//								+" bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
//				}
//				
//			} catch(Exception ex) {
//			}
//			
//			UtilText.nodeContentSB.append("</i>"
//					+ "</p>");
//					
//			return UtilText.nodeContentSB.toString();
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Pass out", "You have no energy left, and can't stay conscious any longer...", LILAYA_ASSISTS_INCUBATION_FINISHED){
//					@Override
//					public void effects() {
//						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
//							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_INCUBATION, Quest.SIDE_UTIL_COMPLETE));
//						}
//						
//						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
//						
//						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
//						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
//					}
//				};
//			}
//			return null;
//		}
//	};
//	
//	public static final DialogueNode LILAYA_ASSISTS_INCUBATION_KNOCK_OUT = new DialogueNode("", "", true, true) {
//		@Override
//		public int getSecondsPassed() {
//			return 240*60;
//		}
//		@Override
//		public String getContent() {
//			return UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION_KNOCK_OUT");
//		}
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Pass out", "The drink Lilaya gave you goes straight to your head, and you collapse back onto the bed as you lose consciousness.", LILAYA_ASSISTS_INCUBATION_FINISHED){
//					@Override
//					public void effects() {
//						if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_INCUBATION)) {
//							Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_FIRST_TIME_INCUBATION, Quest.SIDE_UTIL_COMPLETE));
//						}
//						
//						Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
//
//						Main.game.getNpc(Lilaya.class).setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
//						Main.game.getPlayer().setLocation(WorldType.LILAYAS_HOUSE_FIRST_FLOOR, PlaceType.LILAYA_HOME_ROOM_PLAYER, false);
//					}
//				};
//			}
//			return null;
//		}
//	};
//
//	public static final DialogueNode LILAYA_ASSISTS_INCUBATION_FINISHED = new DialogueNode("Your room", "", true, true) {
//		@Override
//		public int getSecondsPassed() {
//			return 2*60;
//		}
//		@Override
//		public String getContent() {
//			UtilText.nodeContentSB.setLength(0);
//			
//			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_INCUBATION_FINISHED"));
//			
//			UtilText.nodeContentSB.append("<p style='text-align:center;'>");
//				UtilText.nodeContentSB.append("In the picture you see:");
//				
//				for(String id : incubationOffspringBirthed) {
//					try {
//						GameCharacter offspring = Main.game.getNPCById(id);
//						String descriptor = getOffspringDescriptor(offspring);
//						UtilText.nodeContentSB.append("<br/>"
//								+ Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+" "+descriptor
//								+ " <i style='color:"+offspring.getGender().getColour().toWebHexString()+";'>"+offspring.getGender().getName()+"</i>"
//								+ " <i style='color:"+offspring.getSubspecies().getColour(offspring).toWebHexString()+";'>"+UtilText.parse(offspring,"[npc.race]")+"</i>");
//					} catch(Exception ex) {
//					}
//				}
//			UtilText.nodeContentSB.append("</p>");
//			
//			UtilText.nodeContentSB.append("<p>"
//						+ "After taking a minute to get your emotions under control, you put the picture away for safe-keeping, and think about what to do next."
//					+ "</p>");
//
//			return UtilText.nodeContentSB.toString();
//		}
//
//		@Override
//		public Response getResponse(int responseTab, int index) {
//			if (index == 1) {
//				return new Response("Get up", "Get out of bed, ready for a new day.", RoomPlayer.ROOM);
//			}
//			return null;
//		}
//	};
}
