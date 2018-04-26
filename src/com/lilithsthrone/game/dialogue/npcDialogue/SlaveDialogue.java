package com.lilithsthrone.game.dialogue.npcDialogue;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevelBasic;
import com.lilithsthrone.game.character.attributes.ObedienceLevelBasic;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SMMilkingStall;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.game.slavery.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceUpgrade;

/**
 * @since 0.1.85
 * @version 0.2.1
 * @author Innoxia
 */
public class SlaveDialogue {
	
	private static NPC slave() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNodeOld DEFAULT_ENSLAVEMENT_DIALOGUE = new DialogueNodeOld("New Slave", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return ".";
		}

		@Override
		public String getContent() {
			AbstractClothing enslavementClothing = Main.game.getActiveNPC().getEnslavementClothing();
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "Holding the "+enslavementClothing.getName()+" in one [pc.hand], you take a step towards [npc.name]."
						+ " [npc.She] lets out a distressed cry as [npc.she] sees what you're about to do, but [npc.she]'s so exhausted that [npc.she] can't manage to put up any significant amount of resistance."
					+ "</p>"
					+ "<p>"
						+ "Forcing the item of clothing onto [npc.herHim], you step back, looking down at a face filled with fear."
						+ " The "+enslavementClothing.getName()+"'s arcane enchantment recognises [npc.name] as being a criminal, and, with a purple flash,"
							+ " <b>[npc.she]'s teleported to the 'Slave Administration' building in Slaver Alley, where [npc.she]'ll be waiting for you to pick them up</b>."
					+ "</p>"
					+ "<p>"
						+ "Just before they disappear, glowing purple lettering is projected into the air, which reads:</br>"
						+ "<i>Slave identification: [style.boldArcane("+Main.game.getActiveNPC().getNameIgnoresPlayerKnowledge()+")]</i>"
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", DEFAULT_ENSLAVEMENT_DIALOGUE){
					@Override
					public void effects() {
						Main.game.getActiveNPC().applyEnslavementEffects(Main.game.getPlayer());
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_START = new DialogueNodeOld("", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getActiveNPC().isVisiblyPregnant()){
				// Pregnant encounters:
				if(!Main.game.getActiveNPC().isReactedToPregnancy()) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "As you approach [npc.name], it's impossible not to notice the fact that [npc.she]'s sporting a round belly."
								+ " [npc.She] absent-mindedly strokes [npc.her] swollen bump as [npc.she] looks up at you,");
					
					GameCharacter father = Main.game.getActiveNPC().getPregnantLitter().getFather();
					
					if(father!=null && father.isPlayer()) {
						switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
							case DISLIKE:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
									case DISOBEDIENT:
										UtilText.nodeContentSB.append(" not bothering to even to try and conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] spits,"
												+ " [npc.speech(Eugh, it's <i>you</i>. You went and got me pregnant, so I expect some time off work. Fucking asshole...)]");
										break;
									case NEUTRAL:
										UtilText.nodeContentSB.append(" trying to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] remarks,"
												+ " [npc.speech(Oh, hello [npc.pcName]. You got me pregnant, so I'll need some time off work.)]");
										break;
									case OBEDIENT:
										UtilText.nodeContentSB.append(" obediently doing [npc.her] very best to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] calls out,"
												+ " [npc.speech(Hello [npc.pcName]. As I'm sure you can see, you've got me pregnant...)]");
										break;
								}
								break;
							case NEUTRAL:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
									case DISOBEDIENT:
										UtilText.nodeContentSB.append(" sighing,"
												+ " [npc.speech(Hi [npc.pcName]. You got me pregnant, so I'm going to need to take it easy for a while, ok?)]");
										break;
									case NEUTRAL:
										UtilText.nodeContentSB.append(" sighing,"
												+ " [npc.speech(Hello [npc.pcName]. You got me pregnant...)]");
										break;
									case OBEDIENT:
										UtilText.nodeContentSB.append(" sighing,"
												+ " [npc.speech(Hello [npc.pcName]. You got me pregnant... I'll make sure to take good care of our children!)]");
										break;
								}
								break;
							case LIKE:
								switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
									case DISOBEDIENT:
										UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
												+ " [npc.speech([npc.PcName]! Look! You got me pregnant, isn't it wonderful?! I'm going to need to take it easy for a while, so that I can take good care of myself, ok?)]");
										break;
									case NEUTRAL:
										UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
												+ " [npc.speech([npc.PcName]! You got me pregnant, isn't it wonderful?! I'll make sure to take good care of our children!)]");
										break;
									case OBEDIENT:
										UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
												+ " [npc.speech(Hello [npc.pcName]! You got me pregnant! I'll make sure to take good care of our children!)]");
										break;
								}
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "You walk over to your slave, and, running your [pc.hands] over [npc.her] pregnant belly, you smile reassuringly at the mother of your children,"
									+ " [pc.speech(When the time's right, Lilaya will be able to help you give birth, ok?)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.Name] responds in the affirmative, and after stroking and caressing [npc.her] belly for a little while, you wonder what to do next..."
								+ "</p>");
						
					} else {
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(" sighing,"
										+ " [npc.speech(Hi [npc.pcName]. "+(father==null?"I ended up getting pregnant":father.getName("A")+" got me pregnant")+", so I'm going to take it easy for a while. Get one of the other slaves to cover for me, ok?)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(" sighing,"
										+ " [npc.speech(Hi [npc.pcName]. "+(father==null?"I ended up getting pregnant":father.getName("A")+" got me pregnant")+", so I'm going to need to take it easy for a while, ok?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(" obediently informing you of what happened,"
										+ " [npc.speech(Hello [npc.pcName]. "+(father==null?"I ended up getting pregnant":father.getName("A")+" got me pregnant")+", but I won't let it get in the way of my duties!)]");
								break;
						}
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "You walk over to your slave, and, running your [pc.hands] over [npc.her] pregnant belly, you smile reassuringly at [npc.herHim],"
									+ " [pc.speech(When the time's right, Lilaya will be able to help you give birth, ok?)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.Name] responds in the affirmative, and after stroking and caressing [npc.her] belly for a little while, you wonder what to do next..."
								+ "</p>");
					}
				
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "As you approach [npc.name], you see that [npc.she]'s still sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] pregnant bump as [npc.she] looks up at you,");
					switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
						case DISLIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(" not bothering to even to try and conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] spits,"
											+ "[npc.speech(Eugh, it's <i>you</i>. What the hell do you want now?!)]");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(" trying to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] remarks,"
											+ "[npc.speech(Oh, hello [npc.pcName]. What is it that you want?)]");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(" obediently doing [npc.her] very best to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] calls out,"
											+ " [npc.speech(Hello [npc.pcName]. What can I do for you?)]");
									break;
							}
							break;
						case NEUTRAL:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(" sighing,"
											+ " [npc.speech(Hi [npc.pcName]. I'm taking it easy, what with the pregnancy and all, ok?)]");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(" sighing,"
											+ " [npc.speech(Hello [npc.pcName]. What can I do for you?)]");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(" sighing,"
											+ " [npc.speech(Hello [npc.pcName]. Is there anything that I can do for you?)]");
									break;
							}
							break;
						case LIKE:
							switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
								case DISOBEDIENT:
									UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
											+ " [npc.speech(Hi [npc.pcName]! How are you doing?! I'm taking it easy at the moment, so that I can take good care of myself, ok?)]");
									break;
								case NEUTRAL:
									UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
											+ " [npc.speech(Hello [npc.pcName]! I'm taking good care of myself! How are you?)]");
									break;
								case OBEDIENT:
									UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
											+ " [npc.speech(Hello [npc.pcName]! Is there anything I can do for you?)]");
									break;
							}
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "You walk over to your slave, and, running your [pc.hands] over [npc.her] pregnant belly, you smile reassuringly at [npc.herHim],"
								+ " [pc.speech(Remember that Lilaya will be able to help you to give birth. Make sure you visit her when the time's right, ok?)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] responds in the affirmative, and after stroking and caressing [npc.her] belly for a little while, you wonder what to do next..."
							+ "</p>");
				}
				
			} else {
				// Standard repeat encounter:
				UtilText.nodeContentSB.append(
						"<p>"
							+ "As you approach [npc.name], [npc.she] looks up at you,");
				switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
					case DISLIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(" not bothering to even to try to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] spits,"
										+ "[npc.speech(Eugh, it's <i>you</i>. What the hell do you want now?!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(" trying to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] remarks,"
										+ "[npc.speech(Oh, hello [npc.pcName]. What is it that you want?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(" obediently doing [npc.her] very best to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] calls out,"
										+ " [npc.speech(Hello [npc.pcName]. What can I do for you?)]");
								break;
						}
						break;
					case NEUTRAL:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(" sighing,"
										+ " [npc.speech(Hi [npc.pcName]. What do you want?)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(" sighing,"
										+ " [npc.speech(Hello [npc.pcName]. What can I do for you?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(" sighing,"
										+ " [npc.speech(Hello [npc.pcName]. Is there anything that I can do for you?)]");
								break;
						}
						break;
					case LIKE:
						switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
							case DISOBEDIENT:
								UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
										+ " [npc.speech(Hi [npc.pcName]! Oh, I'm so happy to see you again! I've been on my best behaviour!)]");
								break;
							case NEUTRAL:
								UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
										+ " [npc.speech(Hello [npc.pcName]! How are you? Is there anything I can do for you?)]");
								break;
							case OBEDIENT:
								UtilText.nodeContentSB.append(" a huge smile breaking out across [npc.her] face as [npc.she] joyously calls out,"
										+ " [npc.speech(Hello [npc.pcName]! Is there anything I can do for you?)]");
								break;
						}
						break;
				}
				UtilText.nodeContentSB.append("</p>"
						+ "<p>"
							+ "You walk over to your slave, wondering what to do next..."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(getFooterText());

			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Dialogue";
			} else if(index == 1) {
				return "Sex";
			} else if(index == 2) {
				return "Management";
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if (index == 1) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveBackground)) {
						return new Response("Background", "Ask [npc.name] about [npc.her] past life.", SLAVE_PROGRESSION) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveBackground);
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 3));
							}
						};
					} else {
						return new Response("Background", "You've already talked with [npc.name] about [npc.her] past life today.", null);
					}
					
				} else if (index == 2) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveSmallTalk)) {
						return new Response("Small talk", "Chat about this and that with [npc.name].", SLAVE_MINOR) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveSmallTalk);
								switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
									case DISLIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -1f));
										break;
									case NEUTRAL:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 2f));
										break;
									case LIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 4f));
										break;
								}
							}
						};
					} else {
						return new Response("Small talk", "You've already spent time talking with [npc.name] today.", null);
					}
					
				} else if (index == 3) {
					if(!slave().isAbleToSelfTransform()) {
						return new Response("Transformations", "Only demons and slimes can transform themselves on command...", null);
						
					} else {
						return new Response("Transformations",
								"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
								BodyChanging.BODY_CHANGING_CORE){
							@Override
							public void effects() {
								Main.game.saveDialogueNode();
								BodyChanging.setTarget(slave());
							}
						};
					}
					
				} else if (index == 5) {
					if(!Main.game.getPlayer().hasCompanion(slave())) {
						if(Main.game.getPlayer().canHaveMoreCompanions()) {
							return new Response("Add to party",
									UtilText.parse(slave(), "Command [npc.name] to start following you around."),
									SLAVE_START){
								@Override
								public void effects() {
									Main.game.getPlayer().addCompanion(slave());
								}
							};
						} else {
							return new Response("Add to party",
									"You are already at your party limit!",
									null);
						}
					} else {
						return new Response("Remove from party",
								"Command [npc.name] to leave your party.",
								SLAVE_START){
							@Override
							public void effects() {
								Main.game.getPlayer().removeCompanion(slave());
							}
						};
					}
					
				} else if (index == 6) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveEncourage)) {
						return new Response("Work", "Ask [npc.name] about how [npc.her] work's going.", SLAVE_ENCOURAGE) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveEncourage);
								switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
									case DISLIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 0.5f));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(0.1f));
										break;
									case NEUTRAL:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 2f));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(1f));
										break;
									case LIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 4f));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(2f));
										break;
								}
							}
						};
					} else {
						return new Response("Work", "You've talked to [npc.name] about [npc.her] work today.", null);
					}
					
				} else if (index == 7) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveHug)) {
						return new Response("Hug", "Hug [npc.name].", SLAVE_HUG) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveHug);
								
								switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
									case DISLIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -2));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-1f));
										break;
									case NEUTRAL:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 2));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-1));
										break;
									case LIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-2));
										break;
								}
								
							}
						};
					} else {
						return new Response("Hug", "You've already spent time hugging [npc.name] today.", null);
					}
					
				} else if (index == 8) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlavePettings)) {
						return new Response("Pettings", "Give [npc.name] some loving pettings.", SLAVE_PETTINGS) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlavePettings);
	
								switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
									case DISLIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -2));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-1f));
										break;
									case NEUTRAL:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 2));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-1));
										break;
									case LIKE:
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-2));
										break;
								}
							}
						};
					} else {
						return new Response("Pettings", "You've already spent time petting [npc.name] today.", null);
					}
					
				} else if (index == 9) {
					if(Main.game.getPlayer().hasItemType(ItemType.PRESENT)) {
						return new Response("Give Present", "Give [npc.name] the present that you're carrying.", SLAVE_PRESENT) {
							@Override
							public void effects() {
								Main.game.getPlayer().removeItem(AbstractItemType.generateItem(ItemType.PRESENT));
								
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(-2));
							}
						};
					} else {
						return null;
					}
					
				} else if (index == 11) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveInspect)) {
						return new Response("Inspect", "Make [npc.name] strip and parade around [npc.her] room for your inspection.", SLAVE_INSPECT) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveInspect);
	
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.ANUS);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.ASS);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.BREASTS);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.MOUND);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.MOUTH);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.NIPPLES);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.PENIS);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.TESTICLES);
								Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.VAGINA);
								
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -5));
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(5));
							}
						};
					} else {
						return new Response("Inspect", "You've already inspected [npc.name] today.", null);
					}
					
				} else if (index == 12) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveSpanking)) {
						return new Response("Spanking", "Bend [npc.name] over your knee and give [npc.herHim] a rough spanking.", SLAVE_SPANKING) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveSpanking);
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -5));
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(10));
							}
						};
					} else {
						return new Response("Spanking", "You've already spanked [npc.name] today.", null);
					}
					
				} else if (index == 13) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveMolest)) {
						return new Response("Molest", "Make [npc.name] sit still as you grope and molest [npc.her] body.", SLAVE_MOLEST) {
							@Override
							public void effects() {
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveMolest);
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementObedience(10));
								
								if(slave().isAttractedTo(Main.game.getPlayer())) {
									if(slave().hasFetish(Fetish.FETISH_SUBMISSIVE) || slave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
									} else {
									}
								} else {
									if(slave().hasFetish(Fetish.FETISH_SUBMISSIVE) || slave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
									} else {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -10));
									}
								}
							}
						};
					} else {
						return new Response("Molest", "You've already molested [npc.name] today.", null);
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", SLAVE_START) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return DebugDialogue.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				List<NPC> charactersPresent = Main.game.getCharactersPresent();
				
				if(Main.game.getPlayer().getLocationPlace().getPlaceUpgrades().contains(PlaceUpgrade.LILAYA_MILKING_ROOM)) {
					
					if(index == 1) {
						if(Main.game.isNonConEnabled() && !Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
							return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
									false, false,
									new SMMilkingStall(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MILKING_STALL_FUCKING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL))),
									AFTER_SEX,
									"<p>"
										+ "As [npc.name] is locked into the milking machine, [npc.she]'s left completely powerless as you step around behind [npc.herHim] and reach down to grab [npc.her] [npc.ass+]."
										+ " Letting out [npc.a_sob+], [npc.she] pleads,"
										+ " [npc.speech(No! Please! Just leave me alone!)]"
									+ "</p>") {
								@Override
								public void effects() {
									if(Main.game.getActiveNPC().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
									} else {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -50));
									}
								}
							};
							
						} else {
							return new ResponseSex("Sex", "Have sex with [npc.name].", 
									true, false,
									new SMMilkingStall(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MILKING_STALL_FUCKING)),
											Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.MILKING_STALL_LOCKED_IN_MILKING_STALL))),
									AFTER_SEX,
									"<p>"
										+ "As [npc.name] is locked into the milking machine, [npc.she]'s left completely powerless as you step around behind [npc.herHim] and reach down to grab [npc.her] [npc.ass+]."
										+ " Letting out [npc.a_moan+], [npc.she] pleads,"
										+ " [npc.speech(~Mmm!~ Yes! Fuck me!)]"
									+ "</p>") {
								@Override
								public void effects() {
									Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
								}
							};
						}
					} else {
						return null;
					}
					
				} else {
					if (index == 1) { //TODO improve descriptions and affection hit from rape
						if(Main.game.isNonConEnabled() && !Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
							if(Main.game.getActiveNPC().hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
								return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
										true, false,
										new SMDoggy(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
												Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
										AFTER_SEX,
										"<p>"
											+ "As you've instructed [npc.name] to crawl everywhere [npc.she] goes, there's nothing stopping you from simply stepping around behind [npc.herHim] and dropping to your knees,"
												+ " ready to fuck [npc.herHim] in the doggy-style position."
											+ " Reaching down to grab [npc.her] [npc.ass+], you [npc.moanVerb],"
											+ " [pc.speech(~Mmm!~ This is going to be good!)]"
										+ "</p>"
										+ "<p>"
											+ "[npc.Name] tries to crawl away, but you keep a firm grip on [npc.her] [npc.hips+], preventing [npc.herHim] from escaping."
											+ " Turning back to try and push you away, [npc.she] [npc.sobs+],"
											+ " [npc.speech(No! Stop! Leave me alone!)]"
										+ "</p>") {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
									}
								};
								
							} else {
								return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
										false, false,
										new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
										AFTER_SEX,
										"<p>"
											+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
											+ " [npc.She] desperately tries to push you away, [npc.moaning],"
											+ " [npc.speech(No! Stop!)]"
										+ "</p>") {
									@Override
									public void effects() {
										if(Main.game.getActiveNPC().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
											Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 10));
										} else {
											Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -50));
										}
									}
								};
							}
							
						} else {
							if(Main.game.getActiveNPC().hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
								return new ResponseSex("Sex", "Have sex with [npc.name].", 
										true, false,
										new SMDoggy(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
												Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
										AFTER_SEX,
										"<p>"
											+ "As you've instructed [npc.name] to crawl everywhere [npc.she] goes, there's nothing stopping you from simply stepping around behind [npc.herHim] and dropping to your knees,"
												+ " ready to fuck [npc.herHim] in the doggy-style position."
											+ " Reaching down to grab [npc.her] [npc.ass+], you [npc.moanVerb],"
											+ " [pc.speech(~Mmm!~ This is going to be good!)]"
										+ "</p>") {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
									}
								};
								
							} else {
								return new ResponseSex("Sex", "Have sex with [npc.name].", 
										true, false,
										new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
										AFTER_SEX,
										"<p>"
											+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
											+ " [npc.She] desperately leans into you, [npc.moaning],"
											+ " [npc.speech(~Mmm!~ Yes!)]"
										+ "</p>") {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
									}
								};
							}
						}
						
					} else if (index == 2) {
						if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
	
							if(Main.game.getActiveNPC().hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING)) {
								return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
										true, false,
										new SMDoggy(
												Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.DOGGY_BEHIND)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
										AFTER_SEX,
										"<p>"
											+ "As you've instructed [npc.name] to crawl everywhere [npc.she] goes, there's nothing stopping you from simply dropping down onto all fours in front of [npc.herHim], presenting your [pc.ass+] as you [pc.moanVerb],"
											+ " [pc.speech(~Mmm!~ Take me!)]"
										+ "</p>"
										+"<p>"
											+ "Pushing [npc.herself] up onto [npc.her] knees, [npc.name] reaches forwards and grabs your [pc.hips+]."
											+ " [npc.speech(Looking for some fun, hmm?)]"
									+ "</p>") {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
									}
								};
								
							} else {
								return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
										Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(), null, null, null,
										true, true,
										new SMStanding(
												Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
												Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
										AFTER_SEX,
										"<p>"
											+ "Taking hold of [npc.name]'s [npc.arms], you take a step forwards, guiding [npc.her] [npc.hands] around your body as you press forwards into a passionate kiss."
											+ " [npc.She] eagerly pulls you into [npc.herHim], [npc.moaning],"
											+ " [npc.speech(Looking for some fun, hmm?)]"
										+ "</p>") {
									@Override
									public void effects() {
										Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
									}
								};
							}
							
						} else {
							return new Response("Submissive sex", "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom...", null);
						}
						
					} else if (index == 3) {
						if(charactersPresent.size()==2) {
							return new ResponseSex("Get Spitroasted",
									UtilText.parse(charactersPresent.get(0), charactersPresent.get(1), "Let [npc1.name] and [npc2.name] spitroast you."),
									null, null, null, null, null, null,
									true, true,
									new SMDoggy(
											Util.newHashMapOfValues(
													new Value<>(charactersPresent.get(1), SexPositionSlot.DOGGY_INFRONT),
													new Value<>(charactersPresent.get(0), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
									AFTER_SEX,
									"<p>"
										+ ""//TODO
									+ "</p>");
						} else {
							return new Response("Spitroast", "Another slave needs to be present for this...",null);
						}
					
					} else if (index == 4) {
						if(charactersPresent.size()==2) {
							return new ResponseSex("Side-by-side",
									UtilText.parse(charactersPresent.get(0), charactersPresent.get(1), "Push [npc1.name] and [npc2.name] down onto all fours, side-by-side, and get ready to fuck them."),
									null, null, null, null, null, null,
									true, false,
									new SMDoggy(
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(charactersPresent.get(0), SexPositionSlot.DOGGY_ON_ALL_FOURS),
													new Value<>(charactersPresent.get(1), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
									AFTER_SEX,
									"<p>"
										+ ""//TODO
									+ "</p>");
						} else {
							return new Response("Side-by-side", "Another slave needs to be present for this...",null);
						}
					
					} else if (index == 0) {
						return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", SLAVE_START) {
							@Override
							public DialogueNodeOld getNextDialogue() {
								return DebugDialogue.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
							}
						};
						
					} else  {
						return null;
					}
				}
				
			} else if(responseTab == 2) {
				switch(index) {
					case 1:
						return new Response("Inspect",
								"Inspect [npc.name].",
								SlaveryManagementDialogue.getSlaveryManagementInspectSlaveDialogue(Main.game.getActiveNPC()));
					case 2:
						return new Response("Job",
								"Set [npc.name]'s job and work hours.",
								SlaveryManagementDialogue.getSlaveryManagementSlaveJobsDialogue(Main.game.getActiveNPC()));
					case 3:
						return new Response("Permissions",
								"Manage [npc.name]'s permissions.",
								SlaveryManagementDialogue.getSlaveryManagementSlavePermissionsDialogue(Main.game.getActiveNPC()));
					case 4:
						return new ResponseEffectsOnly("Inventory",
								"Manage [npc.name]'s inventory.")
								{
									@Override
									public void effects()
									{
										Main.mainController.openInventory(Main.game.getActiveNPC(),
												InventoryInteraction.FULL_MANAGEMENT);
									}
								};
					case 5:
						return new Response("Send to Kate",
								"Send [npc.name] to Kate's beauty salon, 'Succubi's secrets', to get [npc.her] appearance changed.",
								SlaveryManagementDialogue.SLAVE_MANAGEMENT_COSMETICS_HAIR) {
									@Override
									public void effects() {
										Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(Main.game.getActiveNPC());
										BodyChanging.setTarget(Main.game.getActiveNPC());
									}
								};
					case 0:
						return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", SLAVE_START) {
							@Override
							public DialogueNodeOld getNextDialogue() {
								return DebugDialogue.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
							}
						};
								
					default:
						return null;
				}
				
			} else {
				return null;
			}
		}
	};
	
	private static String getFooterText() {
		return "<p><i>"
				+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
						?"From the way [npc.she] keeps on glancing hungrily at your body, you can tell that [npc.she]'s attracted to you..."
						:"[npc.She] doesn't show any interest in being attracted to you...")
					+ "</i></p>";
	}
	
	public static final DialogueNodeOld SLAVE_PROGRESSION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "<i>This isn't fully implemented yet!</i>"
					+ "</p>"
					+ "<p>"
						+ "Deciding that you'd like to talk to [npc.name] about something a little more serious, you ask [npc.herHim] about [npc.her] life before becoming your slave,"
						+ " [pc.speech(I'd like to know a little more about your past, [npc.name]. What was your life like before coming here?)]"
					+ "</p>"
					+ "<p>");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append("With a look of intense hatred in [npc.her] [npc.eyes], [npc.she] quickly spits out an insolent response,"
									+ " [npc.speech(Fuck off! Like I'm going to talk about that stuff with you! Asshole!)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append("Although [npc.she] tries to conceal it, you see the distinct look of hatred in [npc.her] [npc.eyes] as [npc.she] remarks,"
									+ " [npc.speech(I wasn't doing much. There's really nothing more to say, [npc.pcName].)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append("[npc.She] obediently does [npc.her] very best to conceal the look of hatred in [npc.her] [npc.eyes] as [npc.she] responds,"
									+ " [npc.speech(There's not really much to say about all that, [npc.pcName]. I lived an uneventful life up until becoming your property. Is there anything else you need?)]");
							break;
					}
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append("Although [npc.she] doesn't seem to hate you, [npc.name] obviously doesn't feel too comfortable talking about [npc.her] past with you, and sighs,"
									+ " [npc.speech(I don't know, [npc.pcName], it's not like there's anything to tell, really. Let's just talk about something else, ok?)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append("Although [npc.she] doesn't seem to hate you, [npc.name] obviously doesn't feel too comfortable talking about [npc.her] past with you, and sighs,"
									+ " [npc.speech(I'm sorry [npc.pcName], there's not really anything to say about my past. Perhaps I can do something else for you?)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append("Although [npc.she] doesn't seem to hate you, [npc.name] obviously doesn't feel too comfortable talking about [npc.her] past with you, and simply responds,"
									+ " [npc.speech(There isn't anything to say about that, [npc.pcName]. My life was entirely uneventful before becoming your slave. Can I do anything else for you?)]");
							break;
					}
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) { //TODO
						case DISOBEDIENT:
							UtilText.nodeContentSB.append("Barely able to contain [npc.her] excitement at being asked about [npc.her] past life, [npc.name] quickly responds,"
									+ " [npc.speech(Thanks for asking, [npc.pcName]! Oh, but maybe we should talk about this some other time...)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append("[npc.Name] smiles as you ask [npc.herHim] about [npc.her] past life, and responds,"
									+ " [npc.speech(Ah, [npc.pcName], maybe we should talk about this some other time...)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append("Clearly happy at being asked about [npc.her] past life, [npc.name] quickly responds,"
									+ " [npc.speech(I'm sorry, [npc.pcName], but we'll have to talk about this some other time...)]");
							break;
					}
					break;
			}
			UtilText.nodeContentSB.append("</p>"
					+getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_MINOR = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "You decide to try and make some small talk with [npc.name], and ask [npc.her] a series of questions ranging from how [npc.she]'s finding life as your slave, to what [npc.she] thinks of the peculiar arcane weather here in Dominion.");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(" No matter how hard you try, however, your attempts at conversation are met with incredibly rude remarks."
									+ "</p>"
									+ "<p>"
									+ "Realising that you're not going to get anywhere like this, you give up on trying to talk to [npc.name]."
									+ " As you turn away, [npc.she] scowls,"
									+ " [npc.speech(Can you please just fuck off now?!)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(" No matter how hard you try, however, your attempts at conversation are met with short, dismissive remarks."
									+ "</p>"
									+ "<p>"
									+ "Realising that you're not going to get anywhere like this, you give up on trying to talk to [npc.name]."
									+ " As you turn away, [npc.she] scowls,"
									+ " [npc.speech(Are you finished with me, [npc.pcName]?)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(" No matter how hard you try, however, your attempts at conversation are met with short, dismissive remarks."
									+ "</p>"
									+ "<p>"
									+ "Realising that you're not going to get anywhere like this, you give up on trying to talk to [npc.name]."
									+ " As you turn away, [npc.she] asks,"
									+ " [npc.speech(Is there anything else I can do for you, [npc.pcName]?)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, your attempt at making small talk is doing more harm than good!</i>"
							+ "</p>");
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										" Although [npc.she] seems uninterested in talking to you at first, [npc.name] nevertheless responds to each of your questions in an amicable manner."
										+ " From the smile that slowly forms on [npc.her] face, you can tell that [npc.she] appreciates the fact that you're attempting to put [npc.herHim] at ease."
									+ "</p>"
									+ "<p>"
										+ "After spending some time talking with [npc.name] like this, you decide to bring your conversation to an end, and as you do, your slave mutters,"
										+ " [npc.speech(Thanks for talking to me, [npc.pcName]...)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									" Although [npc.she] seems a little hesitant to talk to you, [npc.name] nevertheless responds to each of your questions in an amicable manner."
									+ " From the smile that slowly forms on [npc.her] face, you can tell that [npc.she] appreciates the fact that you're attempting to put [npc.herHim] at ease."
								+ "</p>"
								+ "<p>"
									+ "After spending some time talking with [npc.name] like this, you decide to bring your conversation to an end, and as you do, your slave mutters,"
									+ " [npc.speech(Thank you, [npc.pcName]. I enjoyed talking with you...)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									" [npc.She] obediently responds to each one of your questions in an amicable manner."
									+ " From the smile that slowly forms on [npc.her] face, you can tell that [npc.she] appreciates the fact that you're attempting to put [npc.herHim] at ease."
								+ "</p>"
								+ "<p>"
									+ "After spending some time talking with [npc.name] like this, you decide to bring your conversation to an end, and as you do, your slave smiles,"
									+ " [npc.speech(I hope my answers were to your satisfaction, [npc.pcName].)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, your attempt at making small talk is helping to get [npc.herHim] to like you more!</i>"
							+ "</p>");
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										" Beaming from ear to ear, [npc.name] enthusiastically responds to each of your questions, [npc.her] attitude more like that of a close friend than of your slave."
										+ " From [npc.her] smile and the way [npc.she] looks longingly up into your [pc.eyes], you can tell that [npc.name] really appreciates the fact that you're taking some time to talk with [npc.herHim]."
									+ "</p>"
									+ "<p>"
										+ "After a little while, you decide to bring your conversation to an end, and as you do, your slave grins at you,"
										+ " [npc.speech(Thanks, [npc.pcName]! It's really great getting to talk with you now and again!)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									" Beaming from ear to ear, [npc.name] enthusiastically responds to each of your questions, but [npc.she] makes sure not to overdo it, remaining a little distant as [npc.she] tries to act like a good slave."
									+ " From [npc.her] smile and the way [npc.she] looks longingly up into your [pc.eyes], you can tell that [npc.name] really appreciates the fact that you're taking some time to talk with [npc.herHim]."
								+ "</p>"
								+ "<p>"
									+ "After a little while, you decide to bring your conversation to an end, and as you do, your slave smiles,"
									+ " [npc.speech(Thank you, [npc.pcName]. I appreciate you taking your time to talk with me.)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									" Trying to control the excitement in [npc.her] voice, [npc.name] enthusiastically responds to each of your questions,"
											+ " although [npc.she]'s careful to remain [npc.her] composure, doing [npc.her] very best to act like an obedient slave."
									+ " [npc.She] can't manage to totally suppress [npc.her] smile and the way [npc.she] looks longingly up into your [pc.eyes], however,"
										+ " letting you know that [npc.she] really appreciates the fact that you're taking some time to talk with [npc.herHim]."
								+ "</p>"
								+ "<p>"
									+ "After a little while, you decide to bring your conversation to an end, and as you do, your slave smiles,"
									+ " [npc.speech(I hope my answers were to your satisfaction, [npc.pcName].)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, your attempt at making small talk is greatly helping to get [npc.herHim] to like you more!</i>"
							+ "</p>");
					break;
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_ENCOURAGE = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Encouraging [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append("<p>");
			
			switch(Main.game.getActiveNPC().getSlaveJob()) {
				case CLEANING:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while working as your "+Util.capitaliseSentence(SlaveJob.CLEANING.getName(Main.game.getActiveNPC()))+", you ask [npc.her] how [npc.she]'s finding it.");
					break;
				case IDLE:
					UtilText.nodeContentSB.append("Although [npc.name] hasn't been assigned to a job, you ask [npc.her] how [npc.she]'s finding life as your slave.");
					break;
				case KITCHEN:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while working in the kitchen as your cook, you ask [npc.her] how [npc.she]'s finding it.");
					break;
				case LAB_ASSISTANT:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while working in Lilaya's lab as your aunt's assistant, you ask [npc.her] how [npc.she]'s finding it.");
					break;
				case LIBRARY:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while working as a librarian in Lilaya's extensive library, you ask [npc.her] how [npc.she]'s finding it.");
					break;
				case TEST_SUBJECT:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while being used as a test subject for Lilaya's work on transformations, you ask [npc.her] how [npc.she]'s finding it.");
					break;
				case PUBLIC_STOCKS:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while being publicly used in the stocks in Slaver Alley, you ask [npc.her] how [npc.she]'s finding it.");
					break;
				case PROSTITUTE:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while working as a prostitute in Angel's Kiss, you ask [npc.her] how [npc.she]'s finding it.");
					break;
				case MILKING:
					UtilText.nodeContentSB.append("Wanting to encourage [npc.name] to do [npc.her] best while working in the milking stalls, you ask [npc.her] how [npc.she]'s finding it.");
					break;
			}
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(" The moment that you finish speaking, your disobedient slave spits out,"
									+ " [npc.speech(Fuck off! I hate it, and I'm not even doing any work while I'm on duty, so fuck you!)]"
									+ "</p>"
									+ "<p>"
									+ "From [npc.her] rude reaction, it's quite clear that [npc.name] not only hates you, but also isn't too keen on living life as your slave."
									+ " Before you can try asking [npc.her] to give you a proper answer, [npc.she] turns [npc.her] back on you and snarls,"
									+ " [npc.speech(Why don't you fuck off and go suck Lilaya's cock!)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(" The moment that you finish speaking, your slave sharply responds,"
									+ " [npc.speech(I do what I must. I don't enjoy it, or being your slave for that matter, but I'll do what I have to.)]"
									+ "</p>"
									+ "<p>"
									+ "From [npc.her] curt reaction, it's quite clear that [npc.name] isn't too well adjusted to [npc.her] life as a slave."
									+ " Before you can say anything else, [npc.she] impatiently asks,"
									+ " [npc.speech(Is there anything else, [npc.pcName]? Or are you finished with me for now?)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(" The moment that you finish speaking, your obedient slave quickly responds,"
									+ " [npc.speech(I do whatever I'm told to, [npc.pcName]. I'll carry out my duties to the best of my ability, as that's what's expected of me.)]"
									+ "</p>"
									+ "<p>"
									+ "From [npc.her] curt reaction, it's quite clear that while [npc.name] will obediently carry out [npc.her] duties as a slave, [npc.she] doens't like you."
									+ " Before you can say anything else, [npc.she] asks,"
									+ " [npc.speech(What more do you need of me, [npc.pcName]?)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, showing your interest in [npc.her] work has only had a minor effect!</i>"
							+ "</p>");
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(" The moment that you finish speaking, your disobedient slave whines,"
									+ " [npc.speech(Well, it's not like I have a choice in the matter. I mean, thanks for asking, but I'm a slave, so I've kind of got to do whatever you order me to, whether I like it or not...)]"
									+ "</p>"
									+ "<p>"
									+ "From [npc.her] brutally honest reaction, it's quite clear that [npc.name] isn't fully adjusted to living life as your slave just yet."
									+ " As you wonder how to respond, [npc.she] asks,"
									+ " [npc.speech(So is there anything you want, [npc.pcName]?)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(" The moment that you finish speaking, your slave replies,"
									+ " [npc.speech(Well, it's not too bad, [npc.pcName]. Thanks for asking, I'll do whatever you order me to...)]"
									+ "</p>"
									+ "<p>"
									+ "From [npc.her] honest reaction, it's quite clear that [npc.name] still has a few reservations about being your slave."
									+ " As you wonder how to respond, [npc.she] asks,"
									+ " [npc.speech(Is there anything else I can do for you, [npc.pcName]?)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
										" The moment that you finish speaking, your slave replies,"
										+ " [npc.speech(I always do my best, [npc.pcName]. Thank you for asking, but please be assured that I'm working to the best of my abilities.)]"
									+ "</p>"
									+ "<p>"
										+ "From [npc.her] reaction, it's quite clear that [npc.name] has fully accepted [npc.her] place as your slave."
										+ " [npc.Her] answer seemed a little cold, however, and you realise that while [npc.she] doesn't hate you, [npc.she] doesn't exactly love you either."
										+ " Before you can make a comment, [npc.she] continues,"
										+ " [npc.speech(What more can I do for you, [npc.pcName]?)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, showing your interest in [npc.her] work has had a noticeable effect!</i>"
							+ "</p>");
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										" The moment that you finish speaking, your disobedient slave laughs,"
										+ " [npc.speech(Well, it's not like I really have a choice in the matter, do I? Haha! Don't worry though, I love you! I-I mean! I love <i>working</i> for you!)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.Her] cheeks flush bright red as [npc.she] tries to hastily cover up [npc.her] slip of the tongue, but even without [npc.her] accidental confession, you already know that [npc.name] cares for you deeply."
										+ " From the way [npc.she] looks at you, to the smile that breaks out on [npc.her] face each time you show [npc.herHim] any attention, it's clear to everyone how infatuated with you [npc.she] is."
										+ " Before you can say anything on the matter, your slave quickly tries to shift the topic to something less embarrassing for [npc.herHim],"
										+ " [npc.speech(Can I do anything else for you, [npc.pcName]? Anything at all, just ask!)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									" The moment that you finish speaking, your slave smiles and responds,"
									+ " [npc.speech(Everything's going fine, thank you for asking, [npc.pcName]. I love working for you...)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.Her] cheeks flush bright red as [npc.she] admits to [npc.her] enjoyment at being your slave, but even without [npc.her] shy display, you already know that [npc.name] cares for you deeply."
									+ " From the way [npc.she] looks at you, to the smile that breaks out on [npc.her] face each time you show [npc.herHim] any attention, it's clear to everyone how infatuated with you [npc.she] is."
									+ " Before you can say anything on the matter, your slave quickly tries to shift the topic to something less embarrassing for [npc.herHim],"
									+ " [npc.speech(Can I do anything else for you, [npc.pcName]?)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									" The moment that you finish speaking, your slave smiles and responds,"
									+ " [npc.speech(Everything's going very well, [npc.pcName]. I love working for you...)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.Her] cheeks flush bright red as [npc.she] admits to [npc.her] enjoyment at being your slave, but even without [npc.her] shy display, you already know that [npc.name] cares for you deeply."
									+ " From the way [npc.she] looks at you, to the smile that breaks out on [npc.her] face each time you show [npc.herHim] any attention, it's clear to everyone how infatuated with you [npc.she] is."
									+ " Before you can say anything on the matter, your slave quickly tries to shift the topic to something less embarrassing for [npc.herHim],"
									+ " [npc.speech(Can I do anything else for you, [npc.pcName]?)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, showing your interest in [npc.her] work has had a significant effect!</i>"
							+ "</p>");
					break;
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_HUG = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Hugging [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "You decide that [npc.name]'s in need of some physical comfort, and, stepping forwards, you reach and take hold of [npc.herHim], before pulling [npc.herHim] into a tight hug.");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										" [npc.She] instantly tries to wriggle free from your grasp, shouting,"
										+ " [npc.speech(Let me go! Fuck off already!)]"
									+ "</p>"
									+ "<p>"
										+ "You ignore your disobedient slave's protests, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
										+ " [npc.She] carries on struggling against you, not at all impressed by your method of reassuring [npc.herHim]."
										+ " After a short while, you finally release [npc.name], and [npc.she] staggers back, shouting,"
										+ " [npc.speech(I don't need your sympathy, you "+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"!)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									" [npc.She] instinctively tries to pull free from your grasp, whining,"
									+ " [npc.speech([npc.PcName], please! I don't want this!)]"
								+ "</p>"
								+ "<p>"
									+ "You ignore your slave's protests, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
									+ " [npc.She] tries to slip free from your grip, clearly not impressed by your method of reassuring [npc.herHim], but you make sure to hold on tightly, preventing [npc.her] escape."
									+ " After a short while, you finally release [npc.name], and [npc.she] staggers back, muttering,"
									+ " [npc.speech(I don't even like hugs...)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									" [npc.She] freezes up as you take hold of [npc.herHim], and mutters, without a trace of sincerity in [npc.her] voice,"
									+ " [npc.speech(Thank you, [npc.pcName]...)]"
								+ "</p>"
								+ "<p>"
									+ "You ignore your slave's cold reaction, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
									+ " [npc.She] remains completely still, clearly not impressed by your method of reassuring [npc.herHim], but you ignore [npc.her] refusal to react, and continue pressing yourself against [npc.herHim]."
									+ " After a short while, you finally release [npc.name], and [npc.she] steps back, looking down at the floor,"
									+ " [npc.speech(Thank you, [npc.pcName]. What else do you require?)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, your attempt at forcing physical contact is doing more harm than good!</i>"
							+ "</p>");
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										" [npc.She] half-heartedly returns your embrace, sighing,"
										+ " [npc.speech(Thanks, [npc.pcName], I guess a hug now and then isn't too bad...)]"
									+ "</p>"
									+ "<p>"
										+ "You pull [npc.name] in a little more, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
										+ " [npc.She] pats your back a little, and you get the distinct impression that [npc.she]'s only reciprocating your gesture because that's what's expected of [npc.herHim]."
										+ " After a short while, you finally release [npc.name], and [npc.she] steps back, smiling,"
										+ " [npc.speech(That was nice, I guess. Anything else you want?)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									" [npc.She] half-heartedly returns your embrace, sighing,"
									+ " [npc.speech(Thank you, [npc.pcName]...)]"
								+ "</p>"
								+ "<p>"
									+ "You pull [npc.name] in a little more, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
									+ " [npc.She] pats your back a little, and you get the distinct impression that [npc.she]'s only reciprocating your gesture because that's what's expected of [npc.herHim]."
									+ " After a short while, you finally release [npc.name], and [npc.she] steps back, smiling,"
									+ " [npc.speech(Is there anything else you need, [npc.pcName]?)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									" [npc.She] half-heartedly returns your embrace, sighing,"
									+ " [npc.speech(Thank you, [npc.pcName].)]"
								+ "</p>"
								+ "<p>"
									+ "You pull [npc.name] in a little more, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
									+ " [npc.She] pats your back a little, and you get the distinct impression that [npc.she]'s only reciprocating your gesture because it's what's expected of [npc.herHim]."
									+ " After a short while, you finally release [npc.name], and [npc.she] steps back, smiling,"
									+ " [npc.speech(Is there anything else you need, [npc.pcName]?)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, giving [npc.herHim] a reassuring hug has had a positive affect on how [npc.she] sees you! However, being treated in such a familiar manner has had a slightly negative impact on [npc.her] obedience...</i>"
							+ "</p>");
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										" [npc.She] eagerly returns your embrace, letting out a deep sigh,"
										+ " [npc.speech(Thank you, [npc.pcName]!)]"
									+ "</p>"
									+ "<p>"
										+ "You pull [npc.name] in a little more, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
										+ " [npc.She] snuggles into you, wrapping [npc.her] [npc.arms] around your back and letting out a contented sigh."
										+ " From [npc.her] reaction, it's quite clear that [npc.she] really appreciates your physical gesture, and, encouraged by [npc.her] enthusiasm, you spend quite some time hugging your slave."
									+ "</p>"
									+ "<p>"
										+ "After a while, you finally release [npc.name], and [npc.she] steps back, smiling,"
										+ " [npc.speech(Thank you, [npc.pcName]! I really needed that...)]");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									" [npc.She] eagerly returns your embrace, letting out a deep sigh,"
									+ " [npc.speech(Thank you, [npc.pcName]...)]"
								+ "</p>"
								+ "<p>"
									+ "You pull [npc.name] in a little more, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
									+ " [npc.She] snuggles into you, wrapping [npc.her] [npc.arms] around your back and letting out a contented sigh."
									+ " From [npc.her] reaction, it's quite clear that [npc.she] really appreciates your physical gesture, and, encouraged by [npc.her] enthusiasm, you spend quite some time hugging your slave."
								+ "</p>"
								+ "<p>"
									+ "After a while, you finally release [npc.name], and [npc.she] steps back, smiling,"
									+ " [npc.speech(Thank you, [npc.pcName]. I really needed that... Is there anything I can do for you?)]");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									" [npc.She] eagerly returns your embrace, letting out a deep sigh,"
									+ " [npc.speech(Thank you, [npc.pcName]...)]"
								+ "</p>"
								+ "<p>"
									+ "You pull [npc.name] in a little more, holding [npc.herHim] close to your body and breathing in [npc.her] [npc.scent]."
									+ " [npc.She] snuggles into you, wrapping [npc.her] [npc.arms] around your back and letting out a contented sigh."
									+ " From [npc.her] reaction, it's quite clear that [npc.she] really appreciates your physical gesture, and, encouraged by [npc.her] enthusiasm, you spend quite some time hugging your slave."
								+ "</p>"
								+ "<p>"
									+ "After a while, you finally release [npc.name], and [npc.she] steps back, smiling,"
									+ " [npc.speech(Thank you, [npc.pcName]. Is there anything I can do for you?)]");
							break;
					}
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, giving [npc.herHim] a reassuring hug has had a hugely positive affect on how [npc.she] sees you! However, being treated in such a familiar manner has had a slightly negative impact on [npc.her] obedience...</i>"
							+ "</p>");
					break;
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_PETTINGS = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Petting [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
							+ "Wanting to give [npc.name] some reassuring pettings, you reach up and place a [pc.hand] on [npc.her] head."
							+ " Before [npc.she] can react, you start stroking [npc.her] [npc.hair+], before moving down to gently scratch behind one of [npc.her] [npc.ears]."
						+ "</p>"
						+ "<p>");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Shocked by your actions, it takes [npc.name] a moment to respond, and with an angry cry, [npc.she] slaps your [pc.hand] away and steps back, shouting,"
										+ " [npc.speech(What the fuck?! Get off me! Leave me alone you fuck!)]"
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Shocked by your actions, it takes [npc.name] a moment to respond, and with an uncomfortable whine, [npc.she] steps back, apologising,"
									+ " [npc.speech(Sorry, [npc.pcName], but, could you not do that?)]"
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Shocked by your actions, it takes [npc.name] a moment to respond, and with an uncomfortable whine,"
										+ " [npc.she] remains still, scrunching up [npc.her] [npc.eyes] as [npc.she] forces [npc.herself] to endure your unwanted attention."
									+ " After a moment, you stop what you're doing and take your [pc.hand] away, causing [npc.name] to let out a relieved sigh, before asking,"
									+ " [npc.speech(Is there anything else I can do for you, [npc.pcName]?)]"
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, your attempt at forcing physical contact is doing more harm than good!</i>"
							+ "</p>");
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Taken by surprise at your action, it takes [npc.name] a moment to respond, and with little sigh, [npc.she] tilts [npc.her] head to one side,"
										+ " [npc.speech(That feels kind of good... Keep going!)]"
									+ "</p>"
									+ "<p>"
										+ "You do as [npc.she] asks, and continue stroking and patting [npc.her] head for quite some time."
										+ " Eventually, however, you feel as though [npc.name]'s had enough for now, and take your [pc.hand] away, smiling as your slave lets out a very contented sigh."
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Taken by surprise at your action, it takes [npc.name] a moment to respond, and with little sigh, [npc.she] tilts [npc.her] head to one side,"
									+ " [npc.speech(That feels kind of good... Thank you, [npc.pcName]...)]"
								+ "</p>"
								+ "<p>"
									+ "Encouraged by [npc.her] reaction, you continue stroking and patting [npc.her] head for quite some time."
									+ " Eventually, however, you feel as though [npc.name]'s had enough for now, and take your [pc.hand] away, smiling as your slave lets out a very contented sigh."
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Taken by surprise at your action, it takes [npc.name] a moment to respond, and with little sigh, [npc.she] tilts [npc.her] head to one side,"
									+ " [npc.speech(Thank you, [npc.pcName]...)]"
								+ "</p>"
								+ "<p>"
									+ "Encouraged by [npc.her] reaction, you continue stroking and patting [npc.her] head for quite some time."
									+ " Eventually, however, you feel as though [npc.name]'s had enough for now, and take your [pc.hand] away, smiling as your slave lets out a very contented sigh."
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, giving [npc.herHim] such intimate physical attention has made [npc.her] like you a lot more! However, being treated in such a familiar manner has had a slightly negative impact on [npc.her] obedience...</i>"
							+ "</p>");
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"[npc.Name] lets out a loving sigh, and steps towards you as [npc.she] tilts [npc.her] head to one side,"
										+ " [npc.speech(That feels so good... Keep on going!)]"
									+ "</p>"
									+ "<p>"
										+ "You do as [npc.she] asks, and continue stroking and patting [npc.her] head for quite some time."
										+ " Eventually, however, you feel as though [npc.name]'s had enough for now, and take your [pc.hand] away, smiling as your slave lets out a very contented sigh."
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
										"[npc.Name] lets out a loving sigh, and steps towards you as [npc.she] tilts [npc.her] head to one side,"
										+ " [npc.speech(Thank you, [npc.pcName]! That feels so good! Please don't stop!)]"
									+ "</p>"
									+ "<p>"
										+ "You do as [npc.she] asks, and continue stroking and patting [npc.her] head for quite some time."
										+ " Eventually, however, you feel as though [npc.name]'s had enough for now, and take your [pc.hand] away, smiling as your slave lets out a very contented sigh."
									+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
										"[npc.Name] lets out a loving sigh, and steps towards you as [npc.she] tilts [npc.her] head to one side,"
										+ " [npc.speech(Thank you, [npc.pcName]!)]"
									+ "</p>"
									+ "<p>"
										+ "Encouraged by [npc.her] reaction, you continue stroking and patting [npc.her] head for quite some time."
										+ " Eventually, however, you feel as though [npc.name]'s had enough for now, and take your [pc.hand] away, smiling as your slave lets out a very contented sigh."
									+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>Due to the fact that [npc.name]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, giving [npc.herHim] such intimate physical attention has made [npc.her] like you a lot more! However, being treated in such a familiar manner has had a slightly negative impact on [npc.her] obedience...</i>"
							+ "</p>");
					break;
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_PRESENT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Giving [npc.Name] a present";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
							+ "Deciding that [npc.name] deserves a gift this Yuletide, you hold out the present towards [npc.herHim],"
							+ " [pc.speech(This is for you, [npc.name]. Happy Yuletide!)]"
						+ "</p>"
						+ "<p>");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Taken completely off-guard, [npc.name] doesn't seem to know how to respond, and as you press the gift into [npc.her] [npc.hands], [npc.she] mutters,"
										+ " [npc.speech(F-For me? I still hate you, you know...)]"
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Taken completely off-guard, [npc.name] doesn't seem to know how to respond, and as you press the gift into [npc.her] [npc.hands], [npc.she] mutters,"
									+ " [npc.speech(F-For me? I guess I'll take it then...)]"
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Taken completely off-guard, [npc.name] doesn't seem to know how to respond, but as you press the gift into [npc.her] [npc.hands], [npc.she] regains [npc.her] composure and coldly states,"
									+ " [npc.speech(I will accept this if it your wish, [npc.pcName].)]"
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>Despite the fact that [npc.she]"
									+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
									+ " you, [npc.name] is happy to have been given a gift!</i>"
							+ "</p>");
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Taken completely off-guard, [npc.name] lets out a happy little cry, and as you press the gift into [npc.her] [npc.hands], [npc.she] smiles at you,"
										+ " [npc.speech(F-For me? Thank you, [npc.pcName]!)]"
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Taken completely off-guard, [npc.name] lets out a happy little cry, and as you press the gift into [npc.her] [npc.hands], [npc.she] smiles at you,"
									+ " [npc.speech(F-For me? Thank you, [npc.pcName]!)]"
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Taken completely off-guard, [npc.name] lets out a happy little cry, and as you press the gift into [npc.her] [npc.hands], [npc.she] smiles at you,"
									+ " [npc.speech(Thank you, [npc.pcName], I wish you a happy Yuletide as well!)]"
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>While [npc.name] is happy to have been given a gift, being treated in such a friendly manner has had a slightly negative impact on [npc.her] obedience...</i>"
							+ "</p>");
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"Taken completely off-guard, [npc.name] lets out an ecstatic cry, and as you press the gift into [npc.her] [npc.hands], [npc.she] bursts out,"
										+ " [npc.speech([npc.PcName]! Thank you so much! Happy Yuletide to you too!)]"
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"Taken completely off-guard, [npc.name] lets out an ecstatic cry, and as you press the gift into [npc.her] [npc.hands], [npc.she] bursts out,"
									+ " [npc.speech([npc.PcName]! Thank you so much! Happy Yuletide to you too!)]"
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"Taken completely off-guard, [npc.name] lets out an ecstatic cry, and as you press the gift into [npc.her] [npc.hands], [npc.she] beams at you,"
									+ " [npc.speech(Thank you, [npc.pcName]! Happy Yuletide to you too!)]"
								+ "</p>");
							break;
					}
					UtilText.nodeContentSB.append(
							"<p>"
								+ "<i>While [npc.name] is happy to have been given a gift, being treated in such a friendly manner has had a slightly negative impact on [npc.her] obedience...</i>"
							+ "</p>");
					break;
			}
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_INSPECT = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Petting [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
							+ "Deciding that you'd like to inspect [npc.name]'s body, you order [npc.herHim] to strip naked, before taking a step back in order to get a better view of [npc.herHim]."
						+ "</p>"
						+ "<p>");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"[npc.She] lets out an angry scowl as [npc.she] hears your order, but, realising that [npc.she] really doesn't have any choice in the matter, begrudgingly moves to obey."
										+ " As [npc.she] takes [npc.her] clothes off, [npc.she] snarls,"
										+ " [npc.speech(Fucking "+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"! Go on! Take a good look at your <i>property</i>, you sick fuck!)]"
									+ "</p>"
									+ "<p>"
										+ "Ignoring [npc.her] rebellious words, you command [npc.name] to parade around in front of you; an order which [npc.she] again reluctantly carries out."
										+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
										+" Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, drawing yet another angry remark from between your disobedient slave's [npc.lips]."
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a frustrated sigh as [npc.she] hears your order, but, trying to keep [npc.her] emotions under control, begrudgingly moves to obey."
									+ " As [npc.she] takes [npc.her] clothes off, [npc.she] snaps,"
									+ " [npc.speech(This will just take a moment, <i>[npc.pcName]</i>.)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring [npc.her] slightly-rebellious tone, you command [npc.name] to parade around in front of you; an order which [npc.she] again reluctantly carries out."
									+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
									+" Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, and, with a relieved sigh, [npc.she] does as you command."
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] immediately moves to obey your order, but you see the distinct look of hatred in [npc.her] [npc.eyes] as [npc.she] obediently drops [npc.her] clothing to the floor."
									+ " As [npc.she] takes the last of [npc.her] clothes off, [npc.she] asks,"
									+ " [npc.speech(Is this to your pleasure, [npc.pcName]?)]"
								+ "</p>"
								+ "<p>"
									+ "You answer in the affirmative, before commanding [npc.name] to parade around in front of you; an order which [npc.she] again dutifully carries out."
									+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
									+ " Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, and, without a word of complaint, [npc.she] once more does exactly as you say."
								+ "</p>");
							break;
					}
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"[npc.She] lets out a flustered cry as [npc.she] hears your order, but, realising that [npc.she] really doesn't have any choice in the matter, begrudgingly moves to obey."
										+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
										+ " [npc.speech(Do I really have to? It's kind of degrading being forced to do this...)]"
									+ "</p>"
									+ "<p>"
										+ "Ignoring [npc.her] words, you command [npc.name] to parade around in front of you; an order which [npc.she] again reluctantly carries out."
										+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
										+" Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, which draws a relieved sigh from between your disobedient slave's [npc.lips]."
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a flustered cry as [npc.she] hears your order, but, trying to keep [npc.her] emotions under control, begrudgingly moves to obey."
									+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
									+ " [npc.speech(Just give me a moment, [npc.pcName].)]"
								+ "</p>"
								+ "<p>"
									+ "After [npc.she]'s stripped naked, you command [npc.name] to parade around in front of you; an order which [npc.she] again reluctantly carries out."
									+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
									+" Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, and, with a relieved sigh, [npc.she] does as you command."
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] immediately moves to obey your order, but you see the distinct look of distress in [npc.her] [npc.eyes] as [npc.she] obediently drops [npc.her] clothing to the floor."
									+ " As [npc.she] takes the last of [npc.her] clothes off, [npc.she] asks,"
									+ " [npc.speech(Is this to your pleasure, [npc.pcName]?)]"
								+ "</p>"
								+ "<p>"
									+ "You answer in the affirmative, before commanding [npc.name] to parade around in front of you; an order which [npc.she] again dutifully carries out."
									+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
									+ " Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, and, without a word of complaint, [npc.she] once more does exactly as you say."
								+ "</p>");
							break;
					}
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"[npc.She] lets out a sad little cry as [npc.she] hears your order, but, realising that [npc.she] really doesn't have any choice in the matter, begrudgingly moves to obey."
										+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
										+ " [npc.speech(You know, it's kind of degrading being forced to do this... I thought you liked me...)]"
									+ "</p>"
									+ "<p>"
										+ "Ignoring [npc.her] words, you command [npc.name] to parade around in front of you; an order which [npc.she] again reluctantly carries out."
										+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
										+" Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, which draws a relieved sigh from between your disobedient slave's [npc.lips]."
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a sad little cry as [npc.she] hears your order, but, trying to keep [npc.her] emotions under control, slowly moves to obey."
									+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
									+ " [npc.speech(I thought you liked me, [npc.pcName]... But if this is what you want...)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring [npc.her] slightly-rebellious protest, you command [npc.name] to parade around in front of you; an order which [npc.she] again reluctantly carries out."
									+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
									+" Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, and, with a relieved sigh, [npc.she] does as you command."
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] immediately moves to obey your order, but you see the distinct look of sadness in [npc.her] [npc.eyes] as [npc.she] obediently drops [npc.her] clothing to the floor."
									+ " As [npc.she] takes the last of [npc.her] clothes off, [npc.she] asks,"
									+ " [npc.speech(Is this to your pleasure, [npc.pcName]?)]"
								+ "</p>"
								+ "<p>"
									+ "You answer in the affirmative, before commanding [npc.name] to parade around in front of you; an order which [npc.she] again dutifully carries out."
									+ " After that, you get [npc.herHim] to spread [npc.her] [npc.legs] and present [npc.her] "+partInspection()
									+ " Satisfied with [npc.her] appearance, you tell [npc.name] to get dressed again, and, without a word of complaint, [npc.she] once more does exactly as you say."
								+ "</p>");
							break;
					}
					break;
			}
			UtilText.nodeContentSB.append(
					"<p>"
						+ "<i>It makes no difference that [npc.name]"
							+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
							+ " you, as being forced to strip and present [npc.herself] to you like a piece of meat has a hugely negative impact on [npc.her] affection towards you, while simultaneously increasing [npc.her] obedience!</i>"
					+ "</p>");
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	private static String partInspection() {
		if(Main.game.getActiveNPC().hasPenis()) {
			if(Main.game.getActiveNPC().hasVagina()) {
				return ("[npc.penis+] and [npc.vagina+] for your inspection.");
			} else {
				return ("[npc.penis+] for your inspection.");
			}
			
		} else if(Main.game.getActiveNPC().hasVagina()) {
			return ("[npc.vagina+] for your inspection.");
			
		} else {
			return ("genderless mound for your inspection.");
		}
	}
	
	
	public static final DialogueNodeOld SLAVE_SPANKING = new DialogueNodeOld("", "", true, true) { //TODO
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Spanking [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
							+ "You feel like [npc.name] could do with a reminder of who's in charge, so, taking a seat, you order [npc.herHim] to strip and bend over in your lap."
						+ "</p>"
						+ "<p>");
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
										"An angry growl escapes from [npc.her] mouth as [npc.she] hears what you're ordering [npc.herHim] to do, but,"
												+ " understanding that [npc.she] doesn't have any choice in the matter, reluctantly moves to obey."
										+ " As [npc.she] slowly takes [npc.her] clothes off, [npc.she] snaps,"
										+ " [npc.speech(You fucking "+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"! I hate you so fucking much!)]"
									+ "</p>"
									+ "<p>"
										+ "Ignoring [npc.her] insolent remarks, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can."
										+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
										+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
									+ "</p>"
									+ "<p>"
										+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
										+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
									+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a flustered cry as [npc.she] hears what you're ordering [npc.herHim] to do, but,"
											+ " trying to keep [npc.her] emotions under control, reluctantly moves to obey."
									+ " As [npc.she] slowly takes [npc.her] clothes off, [npc.she] sighs,"
									+ " [npc.speech(This will just take a moment, <i>[npc.pcName]</i>.)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring [npc.her] slightly-rebellious tone, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] immediately moves to obey your order, but you see the distinct look of hatred in [npc.her] [npc.eyes] as [npc.she] obediently drops [npc.her] clothing to the floor."
									+ " As [npc.she] takes the last of [npc.her] clothes off, [npc.she] asks,"
									+ " [npc.speech(Is this to your pleasure, [npc.pcName]?)]"
								+ "</p>"
								+ "<p>"
									+ "You answer in the affirmative, before once again commanding [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
					}
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a flustered cry as [npc.she] hears your order, but, realising that [npc.she] really doesn't have any choice in the matter, begrudgingly moves to obey."
									+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
									+ " [npc.speech(Do I really have to? It's kind of degrading being forced to do this...)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring [npc.her] words, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a flustered cry as [npc.she] hears your order, but, trying to keep [npc.her] emotions under control, begrudgingly moves to obey."
									+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
									+ " [npc.speech(Just give me a moment, [npc.pcName].)]"
								+ "</p>"
								+ "<p>"
									+ "After [npc.she]'s stripped naked, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] immediately moves to obey your order, but you see the distinct look of distress in [npc.her] [npc.eyes] as [npc.she] obediently drops [npc.her] clothing to the floor."
									+ " As [npc.she] takes the last of [npc.her] clothes off, [npc.she] asks,"
									+ " [npc.speech(Is this to your pleasure, [npc.pcName]?)]"
								+ "</p>"
								+ "<p>"
									+ "You answer in the affirmative, before once again commanding [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
					}
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a sad little cry as [npc.she] hears your order, but, realising that [npc.she] really doesn't have any choice in the matter, begrudgingly moves to obey."
									+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
									+ " [npc.speech(You know, it's kind of degrading being forced to do this... I thought you liked me...)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring [npc.her] words, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
						case NEUTRAL:
							UtilText.nodeContentSB.append(
									"[npc.She] lets out a sad little cry as [npc.she] hears your order, but, trying to keep [npc.her] emotions under control, slowly moves to obey."
									+ " As [npc.she] takes [npc.her] clothes off, [npc.she] whines,"
									+ " [npc.speech(I thought you liked me, [npc.pcName]... But if this is what you want...)]"
								+ "</p>"
								+ "<p>"
									+ "Ignoring [npc.her] words, you once again command [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
						case OBEDIENT:
							UtilText.nodeContentSB.append(
									"[npc.She] immediately moves to obey your order, but you see the distinct look of sadness in [npc.her] [npc.eyes] as [npc.she] obediently drops [npc.her] clothing to the floor."
									+ " As [npc.she] takes the last of [npc.her] clothes off, [npc.she] asks,"
									+ " [npc.speech(Is this to your pleasure, [npc.pcName]?)]"
								+ "</p>"
								+ "<p>"
									+ "You answer in the affirmative, before once again commanding [npc.name] to bend [npc.herself] over your knee; an order which [npc.she] again carries out as slowly as [npc.she] possibly can."
									+ " As [npc.she] lies down in your lap, presenting [npc.her] [npc.ass+], you let out a victorious laugh, before swiftly bringing your [pc.hand] down to smack [npc.her] exposed cheeks."
									+ " A squeal accompanies the sharp sound of flesh being whacked, which drives you to deliver yet another blow to [npc.her] [npc.ass]."
								+ "</p>"
								+ "<p>"
									+ "You continue this for a little while; your slave squirming and squealing in your lap as you rain down blow after blow upon [npc.her] vulnerable backside."
									+ " Eventually, however, you feel as though [npc.she]'s been taught [npc.her] lesson, and you order [npc.her] to stand up and put [npc.her] clothes on once more."
								+ "</p>");
							break;
					}
					break;
			}
			UtilText.nodeContentSB.append(
					"<p>"
						+ "<i>It makes no difference that [npc.name]"
							+ " <span style='"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getColour().toWebHexString()+"'>"+Main.game.getActiveNPC().getAffectionLevel(Main.game.getPlayer()).getDescriptor()+"</span>"
							+ " you, as being forced to present [npc.herself] for a disciplinary spanking has a hugely negative impact on [npc.her] affection towards you, while simultaneously increasing [npc.her] obedience!</i>"
					+ "</p>");
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SLAVE_MOLEST = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Molesting [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "Not being able to resist the allure of [npc.name]'s [npc.feminine] body, you walk around behind [npc.her] back, before stepping forwards and wrapping your [pc.arms] around [npc.herHim].");

			
			String firstCry = "a horrified cry";
			String firstReaction = "instantly starts squirming and trying to pull away from your touch";
			String firstSpeech = "Fuck off! Leave me alone!";
			String firstPCReaction = "[npc.Her] words do nothing to deter your advance upon [npc.her] unwilling body";
			
			String secondReaction = "[npc.She] continues to shout and struggle";
			String thirdReaction = ", despite [npc.her] protests,";
			String secondSpeech = "[npc.speech(Stop it! F-Fuck off already!)]"
						+ " [npc.she] shouts, [npc.her] words falling on deaf ears as you continue having your fun.";
			
			String finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to dash across to the other side of the room, you look [npc.herHim] up and down, grinning."
						+ " [npc.She] continues to spit curses and tell you to leave [npc.herHim] alone, and you wonder if you should do as your slave asks, or do something else with [npc.herHim]...";
			
			switch(AffectionLevelBasic.getAffectionLevelFromValue(Main.game.getActiveNPC().getAffection(Main.game.getPlayer()))) {
				case DISLIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							break;
						case NEUTRAL:
							firstCry = "an uncomfortable cry";
							firstReaction = "starts squirming and trying to shift [npc.her] body away from your touch";
							firstSpeech = "C-Can you stop this now?!";
							firstPCReaction = "[npc.Her] words do nothing to deter your advance upon [npc.her] unwilling body";
							
							secondReaction = "[npc.She] continues to struggle in discomfort";
							thirdReaction = ", despite [npc.her] reluctance,";
							secondSpeech = "[npc.speech(P-Please! S-Stop it now!)]"
										+ " [npc.she] cries, [npc.her] words falling on deaf ears as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to dash across to the other side of the room, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] avoids your gaze as [npc.she] asks you to leave [npc.herHim] alone, and you wonder if you should do as your slave requests, or do something else with [npc.herHim]...";
							break;
						case OBEDIENT:
							firstCry = "a shocked cry";
							firstReaction = "obediently holds [npc.her] body still for you";
							firstSpeech = "I am yours to use, [npc.pcName].";
							firstPCReaction = "[npc.Her] cold words do nothing to deter your advance upon [npc.her] body";
							
							secondReaction = "[npc.She] continues to obediently allow [npc.herself] to be used";
							thirdReaction = ", despite [npc.her] cold attitude,";
							secondSpeech = "[npc.speech(Is this to your satisfaction, [npc.pcName]?)]"
										+ " [npc.she] dutifully asks, showing no sign of emotion as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to take a step back, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] obediently asks you if there's anything else that's required of [npc.herHim], which makes you wonder if you should do something else with [npc.herHim]...";
							break;
					}
					break;
				case NEUTRAL:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							firstCry = "a startled cry";
							firstReaction = "starts squirming and trying to shift [npc.her] body away from your touch";
							firstSpeech = "D-Do you have to do this?";
							firstPCReaction = "[npc.Her] worried tone does nothing to deter your advance upon [npc.her] body";
							
							secondReaction = "[npc.She] continues to struggle a little";
							thirdReaction = ", despite [npc.her] less-than-enthusiastic attitude,";
							secondSpeech = "[npc.speech(How much longer is this going to do on for, [npc.pcName]?)]"
										+ " [npc.she] asks, [npc.her] disobedient questioning receiving no answer as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to take a few steps back, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] lets out a sigh of relief as [npc.she] asks you to leave [npc.herHim] alone now, and you wonder if you should do as your slave requests, or do something else with [npc.herHim]...";
							break;
						case NEUTRAL:
							firstCry = "a half-suppressed cry";
							firstReaction = "starts squirming and trying to shift [npc.her] body away from your touch";
							firstSpeech = "[npc.pcName], do you really have to do this?";
							firstPCReaction = "[npc.Her] worried tone does nothing to deter your advance upon [npc.her] body";
							
							secondReaction = "[npc.She] continues to struggle a little";
							thirdReaction = ", despite [npc.her] less-than-enthusiastic attitude,";
							secondSpeech = "[npc.speech(Are you almost done, [npc.pcName]?)]"
										+ " [npc.she] asks, [npc.her] disobedient questioning receiving no answer as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to take a few steps back, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] lets out a little sigh of relief as [npc.she] avoids your gaze, and you wonder if you should do something else with [npc.herHim]...";
							break;
						case OBEDIENT:
							firstCry = "a shocked cry";
							firstReaction = "obediently holds [npc.her] body still for you";
							firstSpeech = "I am yours to use, [npc.pcName].";
							firstPCReaction = "[npc.Her] cold words do nothing to deter your advance upon [npc.her] body";
							
							secondReaction = "[npc.She] continues to obediently allow [npc.herself] to be used";
							thirdReaction = ", despite [npc.her] cold attitude,";
							secondSpeech = "[npc.speech(Is this to your satisfaction, [npc.pcName]?)]"
										+ " [npc.she] dutifully asks, showing no sign of emotion as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to take a step back, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] obediently asks you if there's anything else that's required of [npc.herHim], which makes you wonder if you should do something else with [npc.herHim]...";
							break;
					}
					break;
				case LIKE:
					switch(ObedienceLevelBasic.getObedienceLevelFromValue(Main.game.getActiveNPC().getObedienceValue())) {
						case DISOBEDIENT:
							firstCry = "a startled cry";
							firstReaction = "starts squirming and trying to shift [npc.her] body away from your touch";
							firstSpeech = "[npc.PcName]! I thought you liked me?! W-Why are you doing this?!";
							firstPCReaction = "[npc.Her] distressed tone does nothing to deter your advance upon [npc.her] body";
							
							secondReaction = "[npc.She] continues to struggle a little";
							thirdReaction = ", despite [npc.her] less-than-enthusiastic attitude,";
							secondSpeech = "[npc.speech([npc.PcName], please! I-I just wanted a hug...)]"
										+ " [npc.she] sighs, [npc.her] disobedient remark receiving no answer as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to take a few steps back, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] lets out a sigh of relief as [npc.she] smiles up at you, and you wonder if you should do something else with [npc.herHim] now...";
							break;
						case NEUTRAL:
							firstCry = "a little cry";
							firstReaction = "holds [npc.her] body still for you";
							firstSpeech = "[npc.PcName], do you really have to do this?";
							firstPCReaction = "[npc.Her] worried tone does nothing to deter your advance upon [npc.her] body";
							
							secondReaction = "[npc.She] continues to hold still";
							thirdReaction = ", despite [npc.her] slightly-disobedient attitude,";
							secondSpeech = "[npc.speech(Is this to your liking, [npc.pcName]?)]"
										+ " [npc.she] asks, [npc.her] questioning receiving no answer as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to take a few steps back, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] lets out a little sigh of relief as [npc.she] avoids your gaze, and you wonder if you should do something else with [npc.herHim]...";
							break;
						case OBEDIENT:
							firstCry = "a little gasp";
							firstReaction = "obediently holds [npc.her] body still for you";
							firstSpeech = "I am yours to use, [npc.pcName].";
							firstPCReaction = "[npc.Her] cold words do nothing to deter your advance upon [npc.her] body";
							
							secondReaction = "[npc.She] continues to obediently allow [npc.herself] to be used";
							thirdReaction = ", despite [npc.her] cold attitude,";
							secondSpeech = "[npc.speech(Is this to your satisfaction, [npc.pcName]?)]"
										+ " [npc.she] dutifully asks, showing no sign of emotion as you continue having your fun.";
							
							finalDescription = "Eventually, you feel as though you've had enough, and, releasing [npc.name] to allow [npc.her] to take a step back, you look [npc.herHim] up and down, grinning."
										+ " [npc.She] obediently asks you if there's anything else that's required of [npc.herHim], which makes you wonder if you should do something else with [npc.herHim]...";
							break;
					}
					break;
			}
			
			
			
			if(slave().hasBreasts()) {
				UtilText.nodeContentSB.append(
							" Your [pc.hands] reach up to cup and squeeze [npc.her] [npc.breasts+], drawing "+firstCry+" from [npc.name]'s mouth as [npc.she] "+firstReaction+","
							+ " [npc.speech("+firstSpeech+")]"
						+ "</p>"
						+ "<p>"
							+ firstPCReaction+", and, with one [pc.hand] still squeezing [npc.her] [npc.breasts+], you slip the other down between [npc.her] [npc.legs].");
			} else {
				UtilText.nodeContentSB.append(
						" Your [pc.hands] reach up to run over [npc.her] chest, drawing "+firstCry+" from [npc.name]'s mouth as [npc.she] "+firstReaction+","
						+ " [npc.speech("+firstSpeech+")]"
					+ "</p>"
					+ "<p>"
						+ firstPCReaction+", and, with one [pc.hand] still pressed against [npc.her] chest, you slip the other down between [npc.her] [npc.legs].");
			}

			if(slave().hasVagina() && slave().hasPenis()) {
				UtilText.nodeContentSB.append(
							" "+secondReaction+" as you grope, stroke, and fondle [npc.her] [npc.penis+], and you can't help but [pc.moan] into your slave's [npc.ear] as"+thirdReaction+" you feel [npc.her] [npc.cock+] growing hard under your touch."
							+ " Dropping your [pc.hand] down yet further, you shift your attention to [npc.her] [npc.pussy+],"
								+ " grinning once again as you feel that your stimulation of [npc.her] [npc.clit+] and [npc.labia+] have already gotten [npc.her] wet."
						+ "</p>");
				
			} else if(slave().hasVagina()) {
				UtilText.nodeContentSB.append(
						" "+secondReaction+" as you grope, stroke, and probe at [npc.her] [npc.clit+] and [npc.labia+], and you can't help but [pc.moan] into your slave's [npc.ear] as"+thirdReaction+" you feel [npc.her] [npc.pussy+]"
								+ " is already wet from your touch."
					+ "</p>");
				
			} else if(slave().hasPenis()) {
				UtilText.nodeContentSB.append(
						" "+secondReaction+" as you grope, stroke, and fondle [npc.her] [npc.penis+], and you can't help but [pc.moan] into your slave's [npc.ear] as"+thirdReaction+" you feel [npc.her] [npc.cock+] growing hard under your touch."
					+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						" "+secondReaction+" as you grope, stroke, and probe at [npc.her] genderless mound, and you can't help but [pc.moan] into your slave's [npc.ear] as"+thirdReaction+" you feel [npc.herHim] thrust [npc.her] [npc.hips] out against your touch."
					+ "</p>");
				
			}
			UtilText.nodeContentSB.append(
					"<p>"
						+ secondSpeech
					+ "</p>"
					+ "<p>"
						+ finalDescription
					+ "</p>");
			
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "<i>Molesting [npc.name] has helped reinforce in [npc.her] mind that [npc.she] belongs to you, increasing [npc.her] obedience!");
			if(slave().isAttractedTo(Main.game.getPlayer())) {
				if(slave().hasFetish(Fetish.FETISH_SUBMISSIVE) || slave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
					UtilText.nodeContentSB.append(
								" As [npc.she]'s both attracted to you, as well as"
								+ (slave().hasFetish(Fetish.FETISH_SUBMISSIVE)
										?(slave().hasFetish(Fetish.FETISH_NON_CON_SUB)
												?" being both a submissive and turned on by non-consensual encounters,"
												:" being a submissive,")
										:" being turned on by non-consensual encounters,")
								+ " your dominant, non-consensual use of [npc.name]'s body has resulted in [npc.herHim] liking you more!</i>"
							+ "</p>");
				} else {
					UtilText.nodeContentSB.append(
							" As [npc.she]'s attracted to you, your dominant, non-consensual use of [npc.name]'s body has resulted in [npc.herHim] neither losing nor gaining affection towards you!</i>"
						+ "</p>");
				}
			} else {
				if(slave().hasFetish(Fetish.FETISH_SUBMISSIVE) || slave().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
					UtilText.nodeContentSB.append(
							" Despite the fact that [npc.she]'s not attracted to you,"
							+ (slave().hasFetish(Fetish.FETISH_SUBMISSIVE)
									?(slave().hasFetish(Fetish.FETISH_NON_CON_SUB)
											?" by being both a submissive and turned on by non-consensual encounters,"
											:" by being a submissive,")
									:" by being turned on by non-consensual encounters,")
							+ " your dominant, non-consensual use of [npc.name]'s body has resulted in [npc.herHim] neither losing nor gaining affection towards you!</i>"
						+ "</p>");
				} else {
					UtilText.nodeContentSB.append(
							" As [npc.she]'s not attracted to you, your dominant, non-consensual use of [npc.name]'s body has resulted in [npc.herHim] losing a lot of affection towards you!</i>"
						+ "</p>");
				}
			}
			
			UtilText.nodeContentSB.append(getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
						+ "</p>");
				
			} else {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
								+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
							+ "</p>");
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
								+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! I'm still horny!)]"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Decide what to do next.", SLAVE_START);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_USES_YOU = new DialogueNodeOld("Ambushed", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "[npc.Name] ambushes you in the corridor!";
		}

		@Override
		public String getContent() {
				return"<div class='container-full-width' style='text-align:center;'>"
							+ "<i>This scene is a very rough placeholder! I'll get a proper version added soon!</i>"
						+ "</div>"
						+"<p>"
							+ "As you walk down one of the empty corridors, a figure suddenly jumps out at you!"
							+ " Taking you completely by surprise, you're unable to react, and find yourself being pinned up against a nearby wall as your assailant growls into your ear,"
							+ " [npc.speech(Who's the bitch now?!)]"
						+ "</p>"
						+ "<p>"
							+ "Looking up into [npc.name]'s grinning face as [npc.she] pulls you forward into [npc.her] [npc.arms], you let out a surprised little cry,"
							+ " [pc.speech(I-I'm your [pc.master]! You can't d-)]"
						+ "</p>"
						+ "<p>"
							+ "Your words are cut off as [npc.name] clasps a [npc.hand] over your mouth,"
							+ " [npc.speech(No. Right now, you're no better than a slave yourself!)]"
						+ "</p>"
						+ "<p>"
							+ "Being caught totally unawares, you're in no position to fight back, and are at the mercy of your supposed 'slave'..."
						+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						SLAVE_USES_YOU_POST_SEX,
						"<p>"
							+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally pulling away."
							+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
						+ "</p>");
				
			} else if (index == 2) {
				return new ResponseSex("Eager Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_EAGER;
								}
								return null;
							}
						},
						SLAVE_USES_YOU_POST_SEX,
						"<p>"
							+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
							+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
						+ "</p>");
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Resist Sex",
						"[npc.Name] forces [npc.herself] on you...",
						false, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
							@Override
							public SexPace getStartingSexPaceModifier(GameCharacter character) {
								if(character.isPlayer()) {
									return SexPace.SUB_RESISTING;
								}
								return null;
							}
						},
						SLAVE_USES_YOU_POST_SEX,
						"<p>"
							+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
							+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
							+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLAVE_USES_YOU_POST_SEX = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that [npc.she]'s had [npc.her] fun, [npc.name] starts to step back...";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smirk cross [npc.her] face,"
						+ " [npc.speech(That was fun, <i>[npc.pcName]</i>!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Continue on your way.", SLAVE_USES_YOU_POST_SEX) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return DebugDialogue.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getActiveNPC().setLocation(Main.game.getActiveNPC().getHomeWorldLocation(), Main.game.getActiveNPC().getHomeLocation(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	

}
