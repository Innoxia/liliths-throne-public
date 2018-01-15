package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.alleyway.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * Test class that I'm using to try out some methods and stuff. It might end up as a bit of a mess, but don't remove it.
 * 
 * @since 0.1.83
 * @version 0.1.89
 * @author Innoxia
 */
public class TestNPC extends NPC {

	private static final long serialVersionUID = 1L;

	public TestNPC() {
		this(false);
	}
	
	private TestNPC(boolean isImported) {
		super(new NameTriplet("TestNPC"),
				"A mysterious [test.race] that you found in the back of one of the Shopping Arcade's many shops.",
				1, Gender.F_V_B_FEMALE, RacialBody.CAT_MORPH, RaceStage.PARTIAL_FULL,
				new CharacterInventory(10), WorldType.JUNGLE, PlaceType.JUNGLE_CLUB, true); //TODO need to test moving into a 'null' world
		
		if(!isImported) {
			String s = "";
			int[] nameArray = new int[] {78, 111, 120, 105, 110, 105, 97};
			for(int c : nameArray) {
				s+=(char)c;
			}
			this.setName(new NameTriplet(s));
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_FELINE, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_FELINE_FUR, Colour.COVERING_BROWN), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FELINE_FUR, Colour.COVERING_BROWN), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_LIGHT), true);
	
			this.setAttribute(Attribute.STRENGTH, 5);
			this.setAttribute(Attribute.INTELLIGENCE, 25);
			this.setAttribute(Attribute.FITNESS, 20);
			this.setAttribute(Attribute.CORRUPTION, 0);
			
			this.setFemininity(75);
			
			this.setHeight(170);
			
			this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			
			this.setAssSize(AssSize.THREE_NORMAL.getValue());
			
			this.setBreastSize(CupSize.B.getMeasurement());
			
			this.setHipSize(HipSize.FOUR_WOMANLY.getValue());
			
			this.setPiercedEar(true);
			
			this.setAssVirgin(true);
			this.setNippleVirgin(true);
			this.setVaginaVirgin(true);
			this.setFaceVirgin(true);
			
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_BROODMOTHER);
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_INCEST);
			this.addFetish(Fetish.FETISH_MASOCHIST);
			this.addFetish(Fetish.FETISH_MASTURBATION);
			this.addFetish(Fetish.FETISH_NON_CON_SUB);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_PREGNANCY);
			this.addFetish(Fetish.FETISH_PURE_VIRGIN);
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_HOTPANTS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_THIGHHIGH_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK_STEEL, false), true, this);
		}
		
		this.setEnslavementDialogue(AlleywayAttackerDialogue.ENSLAVEMENT_DIALOGUE);
	}
	
	@Override
	public TestNPC loadFromXML(Element parentElement, Document doc) {
		TestNPC npc = new  TestNPC(true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			if(!isSlave()) {
				setPendingClothingDressing(true);
			}
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		this.resetInventory();
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_PANTIES, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_FULLCUP_BRA, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_HOODIE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_HOTPANTS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_THIGHHIGH_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HEAD_HEADBAND, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.EYES_GLASSES, Colour.CLOTHING_BLACK, false), true, this);
		
	}
	
	@Override
	public boolean isAttractedTo(GameCharacter character) {
		return getSexPaceSubPreference(Main.game.getPlayer())!=SexPace.SUB_RESISTING;
	}

	public static boolean resisting = true;
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		if(resisting && Main.game.isNonConEnabled()) {
			return SexPace.SUB_RESISTING;
		} else {
			return SexPace.SUB_EAGER;
		}
	}
	
	public static final DialogueNodeOld TEST_DIALOGUE = new DialogueNodeOld("Shopping arcade", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Reaching the back of the shop, you finish your exploration of this store's wares, finding that there's nothing in here that's worth buying."
						+ " As you turn to make your exit, you see that a door marked '[test.name]'s room: Private!' has been left slightly ajar, and you hear a frustrated little exclamation come out of it,"
						+ " [test.speech(Argh! It shouldn't be like that... I'll have to re-write it...)]"
					+ "</p>"
					+ "<p>"
						+ "You could take a closer look, or carry on your way..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Step inside", "Slip through the open door, careful not to alert the occupant of your presence.", TEST_DIALOGUE_ENTER) {
					@Override
					public void effects() {
						TestNPC.resisting = true;
					}
				};
				
			} else if(index==0) {
				return new Response("Leave", "The door is marked 'private' after all...", TEST_DIALOGUE) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld TEST_DIALOGUE_ENTER = new DialogueNodeOld("A cozy room", ".", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You step forwards, and, quietly pushing the door open, you step into a cozy little back room."
						+ " In front of you, with [test.her] back to the door, a small-framed [test.race] is sitting in front of a desk."
						+ " Scrunched up pieces of paper litter the floor around [test.her], and you notice that [test.she]'s so engrossed in [test.her] writing that [test.she] didn't hear your entry."
					+ "</p>"
					+ "<p>"
						+ "You could greet [test.herHim] and try to find out what [test.she]'s writing, or step back outside and leave [test.herHim] to [test.her] work."
						+ (Main.game.isNonConEnabled()
								?" As you realise how vulnerable [test.she] is back here, far from help, a dark thought flashes through your mind, and you realise that there's always another option..."
								:"")
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Say hello", "Say hello and ask [test.herHim] what [test.she]'s doing.", TEST_DIALOGUE_GREET) {
						@Override
						public void effects() {
							TestNPC.resisting = false;
						}
					};
				
			} else if (index == 2 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Rape [test.herHim]", "[test.Her] back is turned, and [test.she] hasn't noticed you enter the room...",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getTestNPC(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						TEST_DIALOGUE_AFTER_RAPE,
						"<p>"
							+ "You quietly close the door and lock it behind you. After all, you wouldn't want anyone interrupting your fun."
							+ " Stealthily moving forwards, you approach the oblivious [test.race], smiling to yourself as you hear [test.her] grumbling and mumbling to [test.herself]."
						+ "</p>"
						+ "<p>"
							+ "You're able to sneak right up behind [test.herHim] without [test.herHim] noticing, and, in one swift movement, you grab [test.her] shoulders and throw [test.herHim] to the floor."
						+ "</p>"
						+ "<p>"
							+ "[test.speech(Aaaah!)] [test.she] screams, falling down onto all fours."
						+ "</p>"
						+ "<p>"
							+ "You drop down behind [test.herHim], pushing [test.her] weak body into the floor as you growl into [test.her] [test.ear+],"
							+ " [pc.speech(Scream all you want, you're mine now!)]"
						+ "</p>");
				
			} else if (index == 3 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Rape [test.herHim] (gentle)", "[test.Her] back is turned, and [test.she] hasn't noticed you enter the room... (Start the sex scene in the 'gentle' pace.)",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getTestNPC(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						TEST_DIALOGUE_AFTER_RAPE,
						"<p>"
							+ "You quietly close the door and lock it behind you. After all, you wouldn't want anyone interrupting your fun."
							+ " Stealthily moving forwards, you approach the oblivious [test.race], smiling to yourself as you hear [test.her] grumbling and mumbling to [test.herself]."
						+ "</p>"
						+ "<p>"
							+ "You're able to sneak right up behind [test.herHim] without [test.herHim] noticing, and, in one swift movement, you grab [test.her] shoulders and throw [test.herHim] to the floor."
						+ "</p>"
						+ "<p>"
							+ "[test.speech(Aaaah!)] [test.she] screams, falling down onto all fours."
						+ "</p>"
						+ "<p>"
							+ "You drop down behind [test.herHim], taking hold of [test.her] weak body and preventing [test.her] from escaping as you growl into [test.her] [test.ear+],"
							+ " [pc.speech(Hush now, there's no point in struggling, you're mine now!)]"
						+ "</p>") {
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							return SexPace.DOM_GENTLE;
						}
						return null;
					}
				};
				
			} else if (index == 4 && Main.game.isNonConEnabled()) {
				return new ResponseSex("Rape [test.herHim] (rough)", "[test.Her] back is turned, and [test.she] hasn't noticed you enter the room... (Start the sex scene in the 'rough' pace.)",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_NON_CON_DOM)), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getTestNPC(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						TEST_DIALOGUE_AFTER_RAPE,
						"<p>"
							+ "You quietly close the door and lock it behind you. After all, you wouldn't want anyone interrupting your fun."
							+ " Stealthily moving forwards, you approach the oblivious [test.race], smiling to yourself as you hear [test.her] grumbling and mumbling to [test.herself]."
						+ "</p>"
						+ "<p>"
							+ "You're able to sneak right up behind [test.herHim] without [test.herHim] noticing, and, in one swift movement, you grab [test.her] shoulders and violently hurl [test.herHim] to the floor."
						+ "</p>"
						+ "<p>"
							+ "[test.speech(Aaaah!)] [test.she] screams, falling down onto all fours."
						+ "</p>"
						+ "<p>"
							+ "You drop down behind [test.herHim], looming over [test.her] weak body as you slam [test.her] down into the floor."
							+ " You grin as [test.she] struggles back up onto all fours, and, roughly grabbing [test.her] frail body, you tightly grip [test.her] waist, preventing [test.her] from escaping as you growl into [test.her] [test.ear+],"
							+ " [pc.speech(Scream all you want, bitch! You're mine now!)]"
						+ "</p>") {
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							return SexPace.DOM_ROUGH;
						}
						return null;
					}
				};
				
			} else if(index==0) {
				return new Response("Leave", "Step back without alerting the [test.race] to your presence.", TEST_DIALOGUE) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld TEST_DIALOGUE_GREET = new DialogueNodeOld("A cozy room", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Hello, I saw your door was open and I-)] you start to say, but the [test.race] interrupts you with a little shriek."
					+ "</p>"
					+ "<p>"
						+ "[test.speech(Aaaah!)]"
					+ "</p>"
					+ "<p>"
						+ "[test.She] tries to cover up [test.her] work, but in [test.her] haste, ends up knocking several sheets of paper to the floor."
						+ " You rush forwards to help [test.herHim] pick them up, and you see the title 'Lilith's Spire' at the top of one of the sheets."
						+ " Scanning down the page, your eyes open wide at the vulgar, sexually-explicit descriptions that this [test.race]'s been writing."
					+ "</p>"
					+ "<p>"
						+ "[test.speech(D-Don't look!)] [test.she] calls out, grabbing the papers from you,"
						+ " [test.speech(I-It's not like I meant for you to see them!)]"
					+ "</p>"
					+ "<p>"
						+ "Looking up at the [test.race], you see that [test.she]'s blushing profusely, and, somewhat amusingly, staring down at your groin."
						+ " All of this erotic writing must really have got her worked-up, and you grin as you hear the next words that come out of [test.her] mouth."
					+ "</p>"
					+ "<p>"
						+ "[test.speech(But... Now that you've seen it... And seeing as you're already here... We may as well fuck, right?)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex",
						"Well, [test.she] <i>is</i> asking for it!",
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getTestNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						TEST_DIALOGUE_AFTER_SEX,
						"<p>"
							+ "You step forwards, wrapping your [pc.arms] around the [test.race] and pulling [test.herHim] into you."
						+ "</p>"
						+ "<p>"
							+ "[test.speech(~Mmmm!~)] [test.she] moans, leaning into you,"
							+ " [test.speech(Fuck me!)]"
						+ "</p>");
				
			} else if (index == 2) {
				return new ResponseSex("Sex (gentle)",
						"Well, [test.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getTestNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						TEST_DIALOGUE_AFTER_SEX,
						"<p>"
							+ "You step forwards, gently wrapping your [pc.arms] around the [test.race] and lovingly pulling [test.herHim] into you."
						+ "</p>"
						+ "<p>"
							+ "[test.speech(~Mmmm!~)] [test.she] moans, leaning into you,"
							+ " [test.speech(Fuck me!)]"
						+ "</p>") {
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							return SexPace.DOM_GENTLE;
						}
						return null;
					}
				};
				
			} else if (index == 3) {
				return new ResponseSex("Sex (rough)",
						"Well, [test.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getTestNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						TEST_DIALOGUE_AFTER_SEX,
						"<p>"
								+ "You step forwards, wrapping your [pc.arms] around the [test.race] and roughly pulling [test.herHim] into you."
							+ "</p>"
							+ "<p>"
								+ "[test.speech(~Mmmm!~)] [test.she] moans, leaning into you,"
								+ " [test.speech(Yes! I like it rough!)]"
							+ "</p>") {
					@Override
					public SexPace getStartingSexPaceModifier(GameCharacter character) {
						if(character.isPlayer()) {
							return SexPace.DOM_ROUGH;
						}
						return null;
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "You're sure that [npc.name] won't mind you managing [npc.her] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if(index==0) {
				return new Response("Leave", "Leave the room.", TEST_DIALOGUE) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld TEST_DIALOGUE_AFTER_RAPE = new DialogueNodeOld("Step back", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return "<p>"
							+ "The [test.race] collapses to the floor, sobbing."
							+ " Grinning down at [test.her] despoiled body, you turn to the door and prepare to make your exit."
						+ "</p>"
						+ "<p>"
							+ "You knew [test.she] was a slut from the moment you saw [test.herHim], and the fact that [test.she] orgasmed during your fun only proves you right."
							+ " As you reach the door, you turn around and chuckle,"
							+ " [test.speech(You were a good fuck, slut. Maybe I'll pay you another visit some time!)]"
						+ "</p>";
			} else {
				return "<p>"
							+ "The [test.race] collapses to the floor, sobbing."
							+ " Grinning down at [test.her] despoiled body, you turn to the door and prepare to make your exit."
							+ " As you reach for the handle, you turn around and chuckle,"
							+ " [test.speech(You were a good fuck, slut. Maybe I'll pay you another visit some time!)]"
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "Exit the room.", TEST_DIALOGUE) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld TEST_DIALOGUE_AFTER_SEX = new DialogueNodeOld("Step back", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				return "<p>"
							+ "The [test.race] collapses to the floor, totally worn out and satisfied from the orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+" you gave to [test.herHim]."
							+ " Grinning down at [test.her] delicate body, you turn to the door and prepare to make your exit."
							+ " As you reach for the handle, you turn around and chuckle,"
							+ " [test.speech(That was fun! Maybe I'll pay you another visit some time!)]"
						+ "</p>";
			} else {
				return "<p>"
							+ "The [test.race] collapses to the floor, totally worn out."
							+ " Grinning down at [test.her] delicate body, you turn to the door and prepare to make your exit."
							+ " As you reach for the handle, you turn around and chuckle,"
							+ " [test.speech(That was fun! Maybe I'll pay you another visit some time!)]"
						+ "</p>";
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return new Response("Leave", "Exit the room.", TEST_DIALOGUE) {
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getPlace().getDialogue(false);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	// Combat:
	@Override
	public String getCombatDescription() {
		return null;
	}
	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		return null;
	}
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		return null;
	}
	@Override
	public Attack attackType() {
		return null;
	}
	@Override
	public int getExperienceFromVictory() {
		return 0;
	}
	
	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()){
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			}else{
				if(item.getItemType().equals(ItemType.PROMISCUITY_PILL)) {
					Main.game.getPlayer().useItem(item, target, false);
					if(Sex.isDom(Main.game.getPlayer())) {
						return "<p>"
								+ "Holding out a 'Promiscuity pill' to [npc.name], you tell [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
								+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
								+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
								+ "</p>";
					} else {
						return "<p>"
								+ "Holding out a 'Promiscuity pill' to [npc.name], you ask [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
								+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
								+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
								+ "</p>";
					}

				} else if(item.getItemType().equals(ItemType.VIXENS_VIRILITY)) {
					
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isDom(Main.game.getPlayer())) {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you tell [npc.her] to swallow it."
									+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
									+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
									+ "</p>";
						} else {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you ask [npc.her] to swallow it."
									+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
									+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.POTION) || item.getItemType().equals(ItemType.ELIXIR) || item.getItemType().equals(ItemType.FETISH_UNREFINED) || item.getItemType().equals(ItemType.FETISH_REFINED)) {
					
						if(Sex.isDom(Main.game.getPlayer())) {
							return "<p>"
										+ "Taking your "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink some rando~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you quickly remove the bottle's stopper, and, rather unceremoniously, shove the neck down [npc.her] throat."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.MOTHERS_MILK)) {
					
						if(Sex.isDom(Main.game.getPlayer())) {
							return "<p>"
										+ "Taking the bottle of "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you unceremoniously shove the bottle's teat into [npc.her] mouth."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.RACE_INGREDIENT_CAT_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_DOG_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HARPY)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_COW_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HORSE_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_SQUIRREL_MORPH)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_WOLF_MORPH)) {
					
						if(Sex.isDom(Main.game.getPlayer())) {
							return "<p>"
										+ "Taking the "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to eat tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you unceremoniously shove the "+item.getName()+" into [npc.her] mouth."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to gulp down the entire meal before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the food's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to eat that?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.SEX_INGREDIENT_HARPY_PERFUME)) {
					
						if(Sex.isDom(Main.game.getPlayer())) {
							return "<p>"
										+ "Taking the "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to use tha~Hey!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you squirt the "+item.getName()+" onto [npc.her] neck."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the perfume's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to use that?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.COR_INGREDIENT_LILITHS_GIFT)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_HUMAN)
						|| item.getItemType().equals(ItemType.RACE_INGREDIENT_DEMON)
						|| item.getItemType().equals(ItemType.FIT_INGREDIENT_CANINE_CRUSH)
						|| item.getItemType().equals(ItemType.FIT_INGREDIENT_SQUIRREL_JAVA)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_BUBBLE_MILK)
						|| item.getItemType().equals(ItemType.INT_INGREDIENT_FELINE_FANCY)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_EQUINE_CIDER)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_WOLF_WHISKEY)
						|| item.getItemType().equals(ItemType.STR_INGREDIENT_SWAMP_WATER)) {
					
						if(Sex.isDom(Main.game.getPlayer())) {
							return "<p>"
										+ "Taking the bottle of "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
										+ " [npc.speech(Do you really expect me to drink tha~Mrph!~)]"
									+ "</p>"
									+ "<p>"
										+ "Not liking the start of [npc.her] response, you quickly remove the bottle's cap, and, rather unceremoniously, shove the neck down [npc.her] throat."
										+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.herHim] go."
										+ " [npc.She] coughs and splutters for a moment, before letting out a surprised cry as [npc.she] starts to feel the liquid's effects taking root deep in [npc.her] body..."
									+ "</p>"
									+Main.game.getPlayer().useItem(item, target, false, true);
						} else {
							return "<p>"
										+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
										+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
										+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
									+ "</p>";
						}
	
				} else if(item.getItemType().equals(ItemType.EGGPLANT)) {
					
					if(Sex.isDom(Main.game.getPlayer())) {
						return "<p>"
									+ "Taking the eggplant from your inventory, you hold it out to [npc.name]."
									+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
									+ " [npc.speech(W-What are you going to do with th-~Mrph!~)]"
								+ "</p>"
								+ "<p>"
									+ "Not liking the start of [npc.her] response, you quickly shove the eggplant into [npc.her] mouth, grinning as you force [npc.herHim] to eat the purple fruit..."
								+ "</p>"
								+Main.game.getPlayer().useItem(item, target, false, true);
					} else {
						return "<p>"
									+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
									+ " [npc.speech(Hah! Did you really think I was going to eat that?!)]</br>"
									+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
								+ "</p>";
					}
					
				} else {
					return "<p>"
								+ "You try to give [npc.name] "+item.getItemType().getDeterminer()+" "+item.getName()+", but [npc.she] refuses to take it. You put the "+item.getName()+" back in your inventory."
							+ "</p>";
				}
				
			}
			
		// NPC is using an item:
		}else{
			return Sex.getActivePartner().useItem(item, target, false);
		}
	}
}
