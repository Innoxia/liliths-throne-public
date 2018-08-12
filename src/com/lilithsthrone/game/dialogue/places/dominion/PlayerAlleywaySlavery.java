package com.lilithsthrone.game.dialogue.places.dominion;

import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

public class PlayerAlleywaySlavery {

	private static String formatWashingArea(boolean isFullyCleaned, String input) {
		return "<p style='color:"+(isFullyCleaned?Colour.GENERIC_GOOD.toWebHexString():Colour.CUM.toWebHexString())+";'><i>"
					+ input
				+ "</i></p>";
	}

	public static final Response getResponseRoom(int index)
	{
		if(index==1) {
			return new Response("Sell body (Sub)", "Wait around for a client to show up...", PlayerAlleywaySlavery.PLAYER_SLAVE_SELL_SELF_SUB){
				@Override
				public void effects() {
					Main.game.getPlayer().getOwner().delayEventCheck(25);
					NPC npc = new GenericSexualPartner(GenderPreference.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					if(Math.random()<0.4f) {
						npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
					} else {
						if(Main.game.getPlayer().isFeminine()) {
							npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
						} else {
							npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
						}
					}
					npc.removeFetish(Fetish.FETISH_SUBMISSIVE);
					npc.setFetishDesire(Fetish.FETISH_DOMINANT, FetishDesire.THREE_LIKE);
					try {
						Main.game.addNPC(npc, false);
						Main.game.setActiveNPC(npc);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};


		} else if(index==2) {
			return new Response("Sell body (Dom)", "Wait around for a client to show up...", PlayerAlleywaySlavery.PLAYER_SLAVE_SELL_SELF_DOM){
				@Override
				public void effects() {
					Main.game.getPlayer().getOwner().delayEventCheck(25);
					NPC npc = new GenericSexualPartner(GenderPreference.getGenderFromUserPreferences(false, false), Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
					if(Math.random()<0.4f) {
						npc.setSexualOrientation(SexualOrientation.AMBIPHILIC);
					} else {
						if(Main.game.getPlayer().isFeminine()) {
							npc.setSexualOrientation(SexualOrientation.GYNEPHILIC);
						} else {
							npc.setSexualOrientation(SexualOrientation.ANDROPHILIC);
						}
					}
					npc.removeFetish(Fetish.FETISH_DOMINANT);
					npc.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.THREE_LIKE);
					try {
						Main.game.addNPC(npc, false);
						Main.game.setActiveNPC(npc);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
		} else if (index == 3) {
			return new Response("Rest", "Rest for four hours. As well as replenishing your energy and aura, you will also get the 'Well Rested' status effect.", ALLEYWAY_SLAVE_SLEEP){
				@Override
				public void effects() {
					Main.game.getPlayer().getOwner().delayEventCheck(240);
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLust(0);
					if(Main.game.getPlayer().hasTrait(Perk.JOB_UNEMPLOYED, true)) {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED_BOOSTED, (8 * 60) + 240);
					} else {
						Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + 240);
					}
				}
			};

		} else if (index == 4) {
			return new Response("Wash", "Use your master's shower and washing machine to clean up your clothes and yourself.", ALLEYWAY_SLAVE_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().getOwner().delayEventCheck(20);
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));

					Set<SexAreaOrifice> dirtyOrifices = new HashSet<>();
					for(SexAreaOrifice ot: SexAreaOrifice.values()) {
						if(Main.game.getPlayer().getTotalFluidInArea(ot)>0) {
							dirtyOrifices.add(ot);
						}
					}

					Main.game.getPlayer().washAllOrifices();
					Main.game.getPlayer().calculateStatusEffects(0);
					Main.game.getPlayer().cleanAllDirtySlots();
					Main.game.getPlayer().cleanAllClothing();

					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(dirtyOrifices.contains(orifice)) {
							switch(orifice) {
								case ANUS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.asshole] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.asshole]."));
									}
									break;
								case ASS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.ass] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.ass]."));
									}
									break;
								case BREAST:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.breasts] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.breasts]."));
									}
									break;
								case MOUTH:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "The shower does nothing to clean the cum out of your stomach!"));
									}
									break;
								case NIPPLE:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.nipples] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.nipples]."));
									}
									break;
								case THIGHS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.thighs] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.thighs]."));
									}
									break;
								case URETHRA_PENIS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your cock's urethra as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your cock's urethra."));
									}
									break;
								case URETHRA_VAGINA:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your vagina's urethra as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your vagina's urethra."));
									}
									break;
								case VAGINA:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.pussy] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.pussy]."));
									}
									break;
							}
						}
					}

					Main.game.getTextEndStringBuilder().append("<p>"
								+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Your clothes have been cleaned, and you feel refreshed!</b>"
							+ "</p>");
                }
			};
		} else {
			return null;
		}
	}

	public static final DialogueNodeOld PLAYER_SLAVE_SELL_SELF_SUB = new DialogueNodeOld("Back Alleys", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Nnxx";
		}

		@Override
		public int getMinutesPassed() {
			return 25;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_SUB");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int payment = 1500;

			if (index == 1) {
				return new ResponseSex("Sex ("+UtilText.formatAsMoney(1500, "span")+")",
						"Accept the price of "+1500+" flames to have sex with [npc.name].",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						AFTER_SEX_SELL_SELF_SUB,
						UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_SUB_START")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(payment);
					}
				};

			} else if(index == 2) {
				return new Response("Decline", "Tell [npc.name] that you're not interested in what [npc.she] has in mind...", PLAYER_SLAVE_SELL_SELF_DECLINE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_DECLINE")
								+UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_DECLINE_SUB"));
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld PLAYER_SLAVE_SELL_SELF_DOM = new DialogueNodeOld("Back Alleys", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Nnxx";
		}

		@Override
		public int getMinutesPassed() {
			return 25;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_DOM");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			int payment = 1500;

			if (index == 1) {
				return new ResponseSex("Sex ("+UtilText.formatAsMoney(1500, "span")+")",
						"Accept the price of "+1500+" flames to have sex with [npc.name].",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						AFTER_SEX_SELL_SELF_DOM,
						UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_DOM_START")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(payment);
					}
				};

			} else if(index == 2) {
				return new Response("Decline", "Tell [npc.name] that you're not interested in what [npc.she] has in mind...", PLAYER_SLAVE_SELL_SELF_DECLINE){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(
								UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_DECLINE")
								+UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_DECLINE_DOM"));
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld PLAYER_SLAVE_SELL_SELF_DECLINE = new DialogueNodeOld("Back Alleys", ".", false, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Nnxx";
		}

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Back to business", "Carry on your way.", Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false, false)){

				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_SEX_SELL_SELF_DOM = new DialogueNodeOld("Back Alleys", "Disentangle yourself from [npc.name]'s clutches.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Nnxx";
		}

		@Override
		public int getMinutesPassed() {
			return 25;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_DOM_AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Recover", "Recover from the exhausting sex you've just had.", Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false, false)){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_SEX_SELL_SELF_SUB = new DialogueNodeOld("Back Alleys", "Disentangle yourself from [npc.name]'s clutches.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Nnxx";
		}

		@Override
		public int getMinutesPassed() {
			return 25;
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/alleyway/playerSlavery", "SELL_SELF_SUB_AFTER_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Recover", "Recover from the exhausting sex you've just had.", Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false, false)){
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld ALLEYWAY_SLAVE_SLEEP = new DialogueNodeOld("Shady Apartment", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public int getMinutesPassed() {
			return 240;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You fall asleep like a rock on the hard matress that your master provided to you, falling asleep immediatelly."
						+ " You set your alarm for 4 hours from now."
					+ "</p>"
					+ "<p>"
						+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
						+ "Rolling over, you fumble for your phone, turning off the alarm and quickly get out of the bed before your master notices."
					+ "</p>"
					+ "<p>"
						+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>You feel completely refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Back to business", "Carry on your way.", Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false, false)){

				};

			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};

	public static final DialogueNodeOld ALLEYWAY_SLAVE_WASH = new DialogueNodeOld("Shady Appartment", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public int getMinutesPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "You step into the meager shower. Leaving your dirty clothes in the nearby washing machine, you take a long, relaxing shower."
					+ "</p>");

			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Back to business", "Carry on your way.", Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false, false)){

				};

			} else {
				return null;
			}
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};

	public static final DialogueNodeOld ALLEYWAY_SLAVE_RECOVERED = new DialogueNodeOld("Shady Appartment", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public Colour getAuthorColour(){
			return Colour.BASE_ORANGE;
		}

		@Override
		public int getMinutesPassed() {
			return 60;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "The collar's burn and a headache that came with it was too much for you to bear and after a while your head starts spinning."
						+ " It doesn't take too long for you to pass out, falling over from the exertion caused by your collar."
					+ "</p>"
					+ "<p>"
						+ "You wake up an hour later, in the familiar room of your master, the headache still going strong. And worst of all, your master is standing right here, in front of you."
					+ "<p>"
						+ "You are so getting punished."
					+ "</p>");

			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Punished!", "Brace for punishment.", Main.game.getPlayer().getOwner().getPunishmentDialogue("tried to run away from me")){

				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld BUY_FREEDOM = new DialogueNodeOld("Shady Appartment", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public int getMinutesPassed() {
			return 10;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parse(Main.game.getPlayer().getOwner(),"<p>"
						+ "Your master glances at the money you provided and looks up at you."
						+ " [npc.speech(That's actually enough to buy me a good slut... What a shame, I was actually getting used to you. Whatever."
						+ " Get lost. Don't show up in my alleyway again or I'll make you my bitch again.)]"
					+ "</p>"
					+ "<p>"
						+ "You nod as [npc.name] removes your collar and turn away, making your exit as soon as you can, enjoying your newfound freedom!"
					+ "</p>"));

			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Free!", "You are free again!", Main.game.getDefaultDialogueNoEncounter()){
					@Override
					public void effects()
					{
						AbstractClothing clothingToRemove = null;
						for(AbstractClothing potentialClothing : Main.game.getPlayer().getClothingCurrentlyEquipped())
						{
							if(potentialClothing.isContrabandEnslavementClothing())
							{
								clothingToRemove = potentialClothing;
								break;
							}
						}
						if(clothingToRemove != null)
						{
							clothingToRemove.setSealed(false);
							clothingToRemove.removeBadEnchantments();
						}
						GameCharacter owner = Main.game.getPlayer().getOwner();
						Main.game.getPlayer().unequipClothingIntoVoid(clothingToRemove, true, owner);
						owner.removeSlave(Main.game.getPlayer());
						Main.game.getPlayer().setObedience(0);
					}
				};

			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld BEGGED_FOR_PUNISHMENT = new DialogueNodeOld("Shady Appartment", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor(){
			return "Irbynx";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append(UtilText.parse(Main.game.getPlayer().getOwner(),"<p>"
						+ "[npc.Name] laughs as you beg for punishment."
						+ " [npc.speech(That's a good [pc.girl]! Eager to please, I like it.)]"
					+ "</p>"
					+ "<p>"
						+ "You nod at [npc.name] lustily looking at [pc.her], getting ready for your punishment"
					+ "</p>"));

			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Punished!", "You've <i>asked</i> for this!", Main.game.getPlayer().getOwner().getPunishmentDialogue("asked for this"));

			} else {
				return null;
			}
		}
	};


}
