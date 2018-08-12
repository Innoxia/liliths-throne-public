package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.dominion.Cultist;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionary;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMAltarMissionarySealed;
import com.lilithsthrone.game.sex.managers.dominion.cultist.SMCultistKneeling;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.88
 * @version 0.1.97
 * @author Innoxia
 */
public class CultistDialogue {
	
	public static final DialogueNodeOld ENCOUNTER_START = new DialogueNodeOld("A Witch Appears!", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "As you walk down yet another of Dominion's busy streets, you notice that this particular area is extravagantly decorated for Lilith's October celebrations."
						+ " Banners and streamers, of orange, black, and purple, fly from every lamp post, and numerous posters praising Lilith's rule are plastered over every wall."
						+ " There are a considerable number of succubi in this area wearing witch's costumes, and you see them approaching members of the public, before dragging them off down nearby alleyways."
					+ "</p>"
					+ "<p>"
						+ "One of these witch-themed succubi suddenly rushes over towards you, gripping a witch's broomstick tightly in one hand and smiling in delight as she blocks your path and introduces herself,"
						+ " [npc.speech(Heya! I'm [npc.name], do you have a moment to talk?!)]"
					+ "</p>"
					+ "<p>"
						+ "You open your mouth to answer, but before you're able to say anything, the excitable witch cuts you off,"
						+ " [npc.speech(Sure you do! Everyone has the time to talk about how great Lilith is, right?! I'm out here with my sisters to make sure that everyone is having fun in this special month of October!"
						+ " I'm a part of the Cult of Lilith, and I want <i>you</i> to come visit our chapel so that I can give you a special gift!)]"
					+ "</p>"
					+ "<p>"
						+ "The succubus reaches down to grab you by the [pc.arm], and starts trying to pull you off down a nearby alleyway."
						+ " As her fingers press into your [pc.armSkin], you feel an overwhelming sense of power radiating from her arcane aura."
						+ " This demon's power is sure to rival that of Lilaya's, and if she gets you alone down that alleyway, there's no telling what she might do."
					+ "</p>"
					+ "<p>"
						+ "You could pull away and make an excuse to leave, or let her lead you to her chapel, where there's a promise of a 'gift'..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Make your excuses and get away from this annoying cultist.", ENCOUNTER_START){
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parse(Main.game.getActiveNPC(),
								"<p>"
									+ "You pull your [pc.arm] away from [npc.name] and take a step back."
									+ " [pc.speech(I don't have time right now.)]"
								+ "</p>"
								+ "<p>"
									+ "You see a brief flash of anger in [npc.namePos] [npc.eyes+], but it only lasts for a moment before the smile returns to her face."
									+ " [npc.speech(Just remember to praise Lilith, or else we'll find out!)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.NamePos] words are followed by a little laugh, but despite her attempt to pass off her remark as a joke, you detect the distinct hint of malice in her tone."
									+ " Not wanting to get caught up in conversation with this overbearing cultist, you turn around and carry on your way."
								+ "</p>"));
					}
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if(index==2) {
				return new Response("Chapel", "Do as the cultist asks and follow her down a nearby back-alley. What could possibly go wrong?", ENCOUNTER_CHAPEL) {
					@Override
					public void effects() {
						// Pull up dress:
						Main.game.getActiveNPC().displaceClothingForAccess(CoverableArea.PENIS);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL = new DialogueNodeOld("The Witch's Chapel", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Sure, I've got time to come visit your chapel,)]"
						+ " you say, allowing the excitable cultist to pull you towards the foreboding alleyway."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Excellent! I knew you weren't like all the others! This is going to be fun!)]"
						+ " [npc.name] replies, practically skipping in excitement as she leads the way."
					+ "</p>"
					+ "<p>"
						+ "The hustle and bustle of the busy street is soon left behind as [npc.name] hurriedly pulls you down a couple of dark and dingy passageways."
						+ " Thankfully, her chapel doesn't seem to be too far into the maze of alleyways, and after just a minute, you find yourself stepping out into a tidy little courtyard."
						+ " A life-sized statue of a breathtakingly beautiful demon, who you assume to be Lilith, stands in the centre."
						+ " Past this statue, you see your destination taking the form of a small, one-story building, with a pillared facade and red-tiled roof."
					+ "</p>"
					+ "<p>"
						+ "It's towards this building that [npc.name] pulls you, and after quickly crossing the small courtyard, you find yourself stepping through the doorway."
						+ " Just as the diminutive exterior suggested, the interior of the chapel consists of a single, long room."
						+ " Rows of benches line either side of a central aisle, and at the far end, a dark-maroon cloth is draped over a waist-high altar."
						+ " As [npc.name] leads you towards this altar, you see that the walls are covered in detailed murals, each one portraying a demon engaged in a different sex position."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(We're here!)] [npc.name] exclaims, stopping before the altar and turning to face you."
						+ " [npc.speech(You've been such a good [pc.girl]! I usually like to show people around a little first, but I just can't wait any longer! It's time for your gift!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.Name] lets out a mischievous giggle, before reaching down and pulling up the skirt of her witch's dress."
						+ " Following the movement of her hands, you look down to see that her now-exposed underwear are completely crotchless, allowing her huge demonic cock to flop free between her legs."
						+ " Her right hand reaches down beneath her monstrous shaft to stroke her dripping-wet pussy, and her giggle turns into a sensual moan as she bites her lip and looks up at you."
						+ " [npc.speech(Be a good [pc.girl] now and get down on your knees! You can start with my pussy, and then finish with my cock! You'll be getting your gift straight down your throat soon enough!"
							+ " Oh! And don't even <i>think</i> of trying to back out now!)]"
					+ "</p>"
					+ "<p>"
						+ "You can tell from the threatening tone of her voice that she's definitely not going to take no for an answer."
						+ " It looks like you'll either have to do as she commands, or try and fight her off..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseCombat("Fight", "You're left with no choice but to fight!", Main.game.getActiveNPC(), Util.newHashMapOfValues(
						new Value<>(Main.game.getPlayer(), "You tell the succubus that you're not interested, and just as you expected, she moves to attack!"),
						new Value<>(Main.game.getActiveNPC(), "[npc.Name] readies her broomstick and shouts, [npc.speech(How <i>dare</i> you try to refuse my gift! I'll give it to you by force!)]")));
				
			} else if(index==2) {
				return new ResponseSex("Accept", "Drop to your knees and prepare to service her orally.",
						true, true,
						new SMCultistKneeling(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.KNEELING_RECEIVING_ORAL_CULTIST)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL_CULTIST))),
						null,
						ENCOUNTER_CHAPEL_POST_ORAL_SEX, "<p>"
							+ "You do as [npc.name] commands, and obediently drop to your knees in front of her."
							+ " Looking down at your submissive form, the succubus cultist coos in delight,"
							+ " [npc.speech(Good [pc.girl]! You're eager for my gift, aren't you?!)]"
						+ "</p>"
						+ (!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
								?"<p>"
									+ "You look up to see her happy smile turn into a puzzled frown as she realises that you're not able to get access to your mouth."
									+ " Reaching down to your jinxed clothing, she focuses her arcane energy into removing the jinx."
									+ " [npc.speech(There you go! Now you can suck cock to your heart's content!)]"
								+ "</p>"
								:"")
						+ "<p>"
							+ "Stepping forwards, [npc.name] strokes the top of your head, before gently pulling you towards her crotch..."
						+ "</p>") {
					@Override
					public void effects() {
						((Cultist)Main.game.getActiveNPC()).setSealedSex(false);
						
						// Remove jinxes so that player can get access to mouth:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.MOUTH, true);
							}
						}
					}
				};
				
			} else if(index == 3) {
				if(Main.game.getPlayer().hasVagina()) {
					return new ResponseSex("Offer Pussy", "Offer [npc.name] your pussy instead.", Util.newArrayListOfValues(Fetish.FETISH_PREGNANCY),
							null, Fetish.FETISH_PREGNANCY.getAssociatedCorruptionLevel(), null, null, null,
							true, false,
							new SMAltarMissionary(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR))),
							null,
							ENCOUNTER_CHAPEL_POST_VAGINAL_SEX, "<p>"
								+ "You smirk at [npc.name], before batting your eyelids and putting on the most seductive voice you can muster,"
								+ " [pc.speech(Please [npc.name], could I get your gift elsewhere?)]"
							+ "</p>"
							+ "<p>"
								+ "You slowly trace your fingers over your pussy, drawing [npc.namePos] eyes down between your [pc.legs+]."
								+ " She smiles as she realises what you're suggesting, and eagerly grabs your [pc.arm] once again, before pushing you down onto your back on top of the chapel's altar."
								+ " [npc.speech(What better way to praise Lilith than by filling an eager slut's womb with my seed?! Your belly's going to be nice and swollen with imps soon enough!)]"
							+ "</p>"
							+ (!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
									?"<p>"
										+ "You look up to see her happy smile turn into a puzzled frown as she realises that she's not able to get access to your pussy."
										+ " Reaching down to your jinxed clothing, she focuses her arcane energy into removing the jinx."
										+ " [npc.speech(There we go! Now it's time for your special gift!)]"
									+ "</p>"
									:"")) {
						@Override
						public void effects() {
							((Cultist)Main.game.getActiveNPC()).setSealedSex(false);
							((Cultist)Main.game.getActiveNPC()).setRequestedAnal(false);
							
							// Remove jinxes so that player can get access to vagina:
							if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								while (clothing != null) {
									clothing.setSealed(false);
									System.out.println(clothing.getName());
									clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
								}
							}
						}
					};
					
				} else {
					return new Response("Offer Pussy", "You'd need a vagina in order to offer it to [npc.name]...", null);
				}
				
			} else if(index==4) {
				return new ResponseSex("Offer Ass", "Offer [npc.name] your ass instead.", Util.newArrayListOfValues(Fetish.FETISH_ANAL_RECEIVING),
						null, Fetish.FETISH_ANAL_RECEIVING.getAssociatedCorruptionLevel(), null, null, null,
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR))),
						null,
						ENCOUNTER_CHAPEL_POST_ANAL_SEX, "<p>"
							+ "You smirk at [npc.name], before stepping forwards and asking,"
							+ " [pc.speech(Please [npc.name], could I get your gift elsewhere?)]"
						+ "</p>"
						+ "<p>"
							+ "You slowly turn to one side, tracing your fingers over your ass in order to draw [npc.namePos] eyes down to your rear end."
							+ " She smiles as she realises what you're suggesting, and eagerly grabs your [pc.arm] once again, before pushing you down onto your back on top of the chapel's altar."
							+ " [npc.speech(What better way to praise Lilith than by filling some slut's ass with my seed?!)]"
						+ "</p>"
						+ (!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
								?"<p>"
									+ "You look up to see her happy smile turn into a puzzled frown as she realises that she's not able to get access to your asshole."
									+ " Reaching down to your jinxed clothing, she focuses her arcane energy into removing the jinx."
									+ " [npc.speech(There we go! Now it's time for your special gift!)]"
								+ "</p>"
								:"")) {
					@Override
					public void effects() {
						((Cultist)Main.game.getActiveNPC()).setSealedSex(false);
						((Cultist)Main.game.getActiveNPC()).setRequestedAnal(true);
						
						// Remove jinxes so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_REPEAT = new DialogueNodeOld("The Witch's Chapel", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding to pay [npc.name] another visit, you set off down the dark passageway that leads to her chapel."
						+ " The route is quite easy to remember, and after just a minute you find yourself stepping out into a very familiar courtyard."
						+ " Once again you pass the life-sized statue of Lilith, and after approaching the small, one-story building, you push open the chapel's wooden door and step inside."
					+ "</p>"
					+ "<p>"
						+ "The interior is just as you remember it, with rows of benches lining either side of a central aisle, and at the far end, a dark-maroon cloth is draped over a waist-high altar."
						+ " The familiar figure of [npc.name] is standing beside the stone block, and as the door swings shut behind you, she turns to see who's entered her sanctum."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(You're back!)] [npc.name] exclaims, smiling in delight."
						+ " [npc.speech(What a good [pc.girl]! You've come back for another gift!)]"
					+ "</p>"
					+ "<p>"
						+ "[npc.Name] lets out a mischievous giggle, before reaching down and pulling up the skirt of her witch's dress."
						+ " Following the movement of her hands, you look down to see that her now-exposed underwear are completely crotchless, allowing her huge demonic cock to flop free between her legs."
						+ " Her right hand reaches down beneath her monstrous shaft to stroke her dripping-wet pussy, and her giggle turns into a sensual moan as she bites her lip and looks up at you."
						+ " [npc.speech(Now show me what a good [pc.girl] you are and get down on your knees!"
							+ " You can start with my pussy, and then finish with my cock! You'll be getting your gift straight down your throat soon enough!"
							+ " Oh! And don't even <i>think</i> of trying to back out now!)]"
					+ "</p>"
					+ "<p>"
						+ "You can tell from the threatening tone of her voice that she's definitely not going to take no for an answer."
						+ " It looks like you'll either have to do as she commands, or try and fight her off..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ENCOUNTER_CHAPEL.getResponse(0, index);
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_LEAVING = new DialogueNodeOld("The Witch's Chapel", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Leaving [npc.name] panting and exhausted on the altar, you turn to make your exit."
						+ " As you're walking towards the door, you notice that there's a spare Witch's outfit neatly folded on one of the empty benches."
						+ " There's even a spare broomstick lying beside it, and you wonder if you should help yourself to these rare items as way of compensation for [npc.namePos] behaviour..."
					+ "</p>"
					+ "<p>"
						+ "<i>Open your inventory to view the items.</i>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Leave the chapel and head back out into the streets of Dominion.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
			
			} else if(index==10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
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
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_COMBAT_VICTORY = new DialogueNodeOld("The Witch's Chapel", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[npc.Name] staggers back, gripping the edge of the altar with one hand as she pants from exertion."
						+ " [npc.speech(I-Impossible! How could I be defeated?!)]"
					+ "</p>"
					+ "<p>"
						+ "You step forwards, but before you can respond, [npc.name] lets out an incredibly lewd moan,"
						+ " [npc.speech(~Aaaaa!~ You're so strong! Come and claim your reward!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, the horny witch lies back on the altar, pulling up her dress to expose her hard demonic cock and dripping-wet pussy."
						+ " She continues to moan and squirm as she presents herself, begging for you to come and use her body as you see fit."
					+ "</p>"
					+ "<p>"
						+ "You notice that in her haste to submit to you, she's dropped her broomstick on the floor, and you wonder if you should use this opportunity to give [npc.name] a taste of her own medicine..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Colour colour = Colour.CLOTHING_BLACK;
						if(Main.game.getActiveNPC().getClothingInSlot(InventorySlot.TORSO_UNDER) != null && Main.game.getActiveNPC().getClothingInSlot(InventorySlot.TORSO_UNDER).getColour() == Colour.CLOTHING_WHITE) {
							 colour = Colour.CLOTHING_WHITE;
						}
						
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS_THIGH_HIGH, colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS, colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_DRESS, colour, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_HAT, colour, false));
						Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(WeaponType.MAIN_WITCH_BROOM));
					}
				};
			
			} else if(index==2) {
				return new ResponseSex("Sex", "Sex.",
						true, false,
						new SMAltarMissionary(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.MISSIONARY_ALTAR_LYING_ON_ALTAR))),
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEX, "<p>"
							+ "You decide against using her broomstick, and step forwards so that you're standing between [npc.namePos] legs."
							+ " She lets out an excited moan as you run your [pc.hands] up the length of her soft thighs, and props herself up on her elbows as she bites her lip at you."
							+ " [npc.speech(~Mmm!~ Yes! Use me however you want!)]"
						+ "</p>") {
					@Override
					public void effects() {
						((Cultist)Main.game.getActiveNPC()).setSealedSex(false);
					}
				};
			
			} else if(index == 3) {
				return new ResponseSex("Witch's Seal", "Use her broomstick to cast Witch's Seal on her.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))),
						null,
						ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX, "<p>"
							+ "As you pick up [npc.namePos] broomstick, you feel a powerful surge of arcane energy flowing into your body."
							+ " Without even needing to be told, you instantly know exactly how to harness the spells within this powerful weapon."
							+" As you gain knowledge of the spell 'Witch's Seal', you realise that you have the opportunity to give [npc.name] a taste of her own medicine..."
						+ "</p>"
						+ "<p>"
							+ "Stepping towards the horny witch, you thrust out with the broomstick, channelling your energy into casting the powerful immobilising spell."
							+ " A jolt of purple energy shoots out and strikes [npc.name], and as a ghostly purple pentagram flashes beneath her body, she suddenly stops moving."
						+ "</p>"
						+ "<p>"
							+ "Dropping the broomstick to the floor, you step forwards between her legs and make sure that she's ok."
							+ " Thankfully, as you take a closer look, you see that she's still breathing, and her eyes flick up to gaze into yours as you stand over her vulnerable form."
							+ " The Witch's Seal appears to have completely immobilised her, but as you reach out and lift one of her legs, you discover that the spell still allows you to move her around."
						+ "</p>"
						+ "<p>"
							+ "Grinning down at your new fuck-doll, you push her thighs apart to fully expose her groin, and prepare to have some fun..."
						+ "</p>") {
					@Override
					public void effects() {
						((Cultist)Main.game.getActiveNPC()).setSealedSex(true);
					}
				};
				
			} else if (index == 4) {
				return new Response("Full transformations",
						"Take a very detailed look at what [npc.name] can transform [npc.herself] into...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_COMBAT_LOSS = new DialogueNodeOld("The Witch's Chapel", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You stagger back against the altar, reaching one hand out to grip the hard edge as you pant from exertion."
						+ " Although you gave it your best shot, you were no match for a succubus as powerful as this one, and as you use what little energy you have left to focus on keeping yourself standing, you hear the witch let out a mocking laugh."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Hahaha! Silly little [pc.race]!)]"
						+ " she sneers, stepping forwards and roughly grabbing your chin in one hand."
						+ " [npc.speech(I think I'll have some fun with you!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, she steps back, and you look up just in time to see her thrust her broomstick out towards you."
						+ " A jolt of purple energy shoots out, striking you directly in the chest and causing you to fall back against the altar."
						+ " You try to push yourself back up, but find that your body won't listen to your commands."
						+ " You then try to let out a cry, only to find that it takes all of your energy just to utter a little whimper."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(How pathetic!)]"
						+ " [npc.name] mocks, reaching down to pull you up, before pushing you down onto the stone altar."
						+ " [npc.speech(I wonder if you'll keep on mewling like that as I fuck you senseless! Try and struggle all you want, it's no use; this Witch's Seal I've cast upon you has turned you into my very own fuck-doll!)]"
					+ "</p>"
					+ "<p>"
						+ "As she talks, the witch moves you around into position, and you find that you're totally powerless as she spreads your legs and steps forwards."
						+ " [npc.speech(Now, let's have some fun!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index == 1) {
				return new ResponseSex("Witch's Toy", "You're completely immobilised, and can do nothing as the witch prepares to use you as her toy.",
						false, false,
						new SMAltarMissionarySealed(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.MISSIONARY_ALTAR_SEALED_LYING_ON_ALTAR))),
						null,
						ENCOUNTER_CHAPEL_POST_ORAL_SEX, (!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true) || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
							?"<p>"
								+ "You look up to see [npc.namePos] grin turn into a puzzled frown as she realises that she's not able to get access to your groin."
								+ " Reaching down to your jinxed clothing, she focuses her arcane energy into removing the jinx."
								+ " [npc.speech(There we go! Now let's both find out how much you love being my fuck-toy!)]"
							+ "</p>"
							:"")) {
					@Override
					public void effects() {
						((Cultist)Main.game.getActiveNPC()).setSealedSex(true);
						((Cultist)Main.game.getActiveNPC()).setRequestedAnal(false);
						
						// Remove jinxes so that player can get access to vagina:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.VAGINA, true);
							}
						}
						// Remove jinxes so that player can get access to anus:
						if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
							AbstractClothing clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							while (clothing != null) {
								clothing.setSealed(false);
								
								clothing = Main.game.getPlayer().getClothingBlockingCoverableAreaAccess(CoverableArea.ANUS, true);
							}
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_POST_ORAL_SEX = new DialogueNodeOld("Post-sex", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[npc.Name] steps back, grinning down at you as she sorts her clothing out."
						+ " [npc.speech(I hope you enjoyed your gift just as much as I did in giving it to you! Now, I have some cult business to attend to, so run along!)]"
					+ "</p>"
					+ "<p>"
						+ "You move to do as [npc.name] says, and as soon as you've finished getting your things together, she grabs you by the arm and starts marching towards the door."
						+ " You're a little taken aback at how quickly she wants to get rid of you, but before you can object, you're being pushed out of the door."
						+ " [npc.speech(Come back some other time if you want another gift!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, the wooden door to the chapel slams shut, leaving you to walk back out to the street with the taste of demonic cum still on your tongue..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_POST_VAGINAL_SEX = new DialogueNodeOld("Post-sex", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[npc.Name] steps back, grinning down at you as she sorts her clothing out."
						+ " [npc.speech(I hope you enjoyed your gift just as much as I did in giving it to you! Now, I have some cult business to attend to, so run along!)]"
					+ "</p>"
					+ "<p>"
						+ "You move to do as [npc.name] says, and as soon as you've finished getting your things together, she grabs you by the arm and starts marching towards the door."
						+ " You're a little taken aback at how quickly she wants to get rid of you, but before you can object, you're being pushed out of the door."
						+ " [npc.speech(Come back some other time if you want another gift!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, the wooden door to the chapel slams shut, leaving you to walk back out to the street with the wet feeling of demonic cum still between your legs..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_POST_ANAL_SEX = new DialogueNodeOld("Post-sex", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[npc.Name] steps back, grinning down at you as she sorts her clothing out."
						+ " [npc.speech(I hope you enjoyed your gift just as much as I did in giving it to you! Now, I have some cult business to attend to, so run along!)]"
					+ "</p>"
					+ "<p>"
						+ "You move to do as [npc.name] says, and as soon as you've finished getting your things together, she grabs you by the arm and starts marching towards the door."
						+ " You're a little taken aback at how quickly she wants to get rid of you, but before you can object, you're being pushed out of the door."
						+ " [npc.speech(Come back some other time if you want another gift!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, the wooden door to the chapel slams shut, leaving you to walk back out to the street with the wet feeling of demonic cum still in your ass..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", ENCOUNTER_CHAPEL_POST_VAGINAL_SEX){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_POST_SUB_SEALED_SEX = new DialogueNodeOld("Post-sex", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[npc.Name] steps back, grinning down at you as she sorts her clothing out."
						+ " [npc.speech(I hope you enjoyed your gift just as much as I did in giving it to you! Now, it may take a few more minutes for that spell to wear off. If only my assistant were here...)]"
					+ "</p>"
					+ "<p>"
						+ "[femaleNPC.speech(Mistress, I'm back! Ooh! Who's this?)]"
						+ " a strange voice calls out from the doorway."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(What took you so long?! You were meant to be back here half an hour ago!)]"
						+ " [npc.name] responds."
						+ " [npc.speech(Well, nevermind! Just drag this [pc.race] out to a bench somewhere, I've finished with [pc.herHim].)]"
					+ "</p>"
					+ "<p>"
						+ "[femaleNPC.speech(Yes, Mistress!)]"
						+ " the stranger calls out, and after just a few seconds, the black-furred face of a greater cat-girl hovers into view."
						+ " [femaleNPC.speech(Aww! I missed out on all the fun!)]"
					+ "</p>"
					+ "<p>"
						+ "After being shouted at once more by [npc.name], the cat-girl grabs you under the arms and drags you outside."
						+ " She puffs and pants as she pulls you down the dark alleyways, but despite the sounds of exertion, it doesn't her take too long before she's brought you all the way back out to the street where you first met [npc.name]."
						+ " Rather unceremoniously dumping you down onto an empty bench, the cat-girl takes one last look at you before rushing back off to the chapel."
					+ "</p>"
					+ "<p>"
						+ "Thankfully, it only takes a couple of minutes before the spell finally wears off, and with the wet feeling of demonic cum still between your legs, you continue on your travels..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", ENCOUNTER_CHAPEL_POST_ORAL_SEX){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_POST_DOM_SEX = new DialogueNodeOld("Post-sex", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return "<p>"
							+ "You step back from the altar, grinning down at the witch as she lets out a deeply satisfied sigh."
							+ " You quickly get your things in order, and before you turn to leave, the succubus calls out,"
							+ " [npc.speech(Come visit me again! Next time, I'll be the one using you!)]"
						+ "</p>";
			} else {
				return "<p>"
						+ "You step back from the altar, grinning down at the witch as she lets out a deeply frustrated moan."
						+ " You quickly get your things in order, and before you turn to leave, the succubus calls out,"
						+ " [npc.speech(You didn't even let me get off! Next time, I'll be the one using you!)]"
					+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS_THIGH_HIGH, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_DRESS, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_HAT, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(WeaponType.MAIN_WITCH_BROOM));
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ENCOUNTER_CHAPEL_POST_DOM_SEALED_SEX = new DialogueNodeOld("Post-sex", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return "<p>"
							+ "You step back from the altar, grinning down at the witch as she lets out a deeply satisfied sigh."
							+ " You quickly get your things in order, and before you turn to leave, the Witch's Seal starts to wear off, allowing [npc.name] to call out,"
							+ " [npc.speech(Come visit me again! Next time, I'll be the one using you!)]"
						+ "</p>";
			} else {
				return "<p>"
						+ "You step back from the altar, grinning down at the witch as she lets out a deeply frustrated moan."
						+ " You quickly get your things in order, and before you turn to leave, the Witch's Seal starts to wear off, allowing [npc.name] to call out,"
						+ " [npc.speech(You didn't even let me get off! Next time, I'll be the one using you!)]"
					+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Turn around and head for the door.", ENCOUNTER_CHAPEL_LEAVING){
					@Override
					public void effects(){
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS_THIGH_HIGH, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_DRESS, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addClothing(AbstractClothingType.generateClothing(ClothingType.WITCH_HAT, Colour.CLOTHING_BLACK, false));
						Main.game.getPlayerCell().getInventory().addWeapon(AbstractWeaponType.generateWeapon(WeaponType.MAIN_WITCH_BROOM));
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
