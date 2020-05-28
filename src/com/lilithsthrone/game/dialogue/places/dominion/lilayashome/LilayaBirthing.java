package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.dominion.Lilaya;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
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
 * @version 0.3.5.5
 * @author Innoxia
 */
public class LilayaBirthing {

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
			if(player.hasSexCountWith(lilaya)) {
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
				if(Main.game.getPlayer().getVaginaType().isEggLayer()) {
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
					&& Main.getProperties().hasValue(PropertyValue.lactationContent)) {
				UtilText.nodeContentSB.append("You feel a desperate suckling at your nipples, and you're vaguely aware of something greedily drinking down mouthfuls of your [pc.milk]...");
			} else {
				UtilText.nodeContentSB.append("You feel a weight on your chest, and you're vaguely aware of something greedily drinking a bottle of milk as you cradle it in your arms...");
			}
			
			String offspringId = Util.randomItemFrom(Main.game.getPlayer().getLastLitterBirthed().getOffspring());
			try {
				GameCharacter offspring = Main.game.getNPCById(offspringId);
				if(offspring.isFeminine()) {
					UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
								+offspring.getSubspecies().getSingularFemaleName(offspring)
								+" bending down over you, who gives you a loving hug and a kiss on your cheek before departing...");
				} else {
					UtilText.nodeContentSB.append("<br/><br/>Some time later, you imagine seeing a strangely familiar "
								+offspring.getSubspecies().getSingularMaleName(offspring)
								+" bending down over you, who plants a kiss on your cheek and mutters something in your ear before walking out the door...");
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
				return new Response("Protect the eggs!", "Why is Lilaya sitting so close behind you?! Maybe she wants to take your eggs for herself!", LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS) {
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
				GameCharacter offspring = Main.game.getNPCById(offspringId);
				if(offspring.isFeminine()) {
					UtilText.addSpecialParsingString(offspring.getSubspecies().getSingularFemaleName(offspring), true);
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS_FEMININE_HATCHING"));
					
				} else {
					UtilText.addSpecialParsingString(offspring.getSubspecies().getSingularMaleName(offspring), true);
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/lilayasHome/lilayaBirthing", "LILAYA_ASSISTS_EGG_LAYING_PROTECT_THE_EGGS_MASCULINE_HATCHING"));
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
	
	
	private static String getOffspringDescriptor(GameCharacter offspring) {
		List<String> descriptors = new ArrayList<>();
		descriptors.add(offspring.getBodyShape().getName(false));
		descriptors.add(offspring.getHeight().getDescriptor());
		descriptors.add(offspring.getFemininity().getName(false));
		return Util.randomItemFrom(descriptors);
	}
	
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
					GameCharacter offspring = Main.game.getNPCById(id);
					String descriptor = getOffspringDescriptor(offspring);
					UtilText.nodeContentSB.append("<br/>"
							+ Util.capitaliseSentence(UtilText.generateSingularDeterminer(descriptor))+" "+descriptor
							+ " <i style='color:"+offspring.getGender().getColour().toWebHexString()+";'>"+offspring.getGender().getName()+"</i>"
							+ " <i style='color:"+offspring.getSubspecies().getColour(offspring).toWebHexString()+";'>"+UtilText.parse(offspring,"[npc.race]")+"</i>");
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
	
}
