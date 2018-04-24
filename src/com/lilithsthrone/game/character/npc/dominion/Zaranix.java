package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.?
 * @version 0.2.4
 * @author Innoxia
 */
public class Zaranix extends NPC {

	private static final long serialVersionUID = 1L;

	public Zaranix() {
		this(false);
	}
	
	public Zaranix(boolean isImported) {
		super(new NameTriplet("Zaranix", "Zaranix", "Zoelix"),
				"Zaranix is one of the few demons that feels more comfortable in his incubus, rather than succubus, form."
						+ " Muscular, tall, and handsome, Zaranix uses both his cunning mind and good looks to get what he wants.",
				15, Gender.M_P_MALE, RacialBody.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_ORANGE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PURPLE), true);
			
			this.setHeight(Height.THREE_TALL.getMedianValue());
			
			this.setHornType(HornType.STRAIGHT);
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

			this.setWingSize(WingSize.THREE_LARGE.getValue());
			
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			
			this.setAssVirgin(true);
			this.setFaceVirgin(true);
			this.setNippleVirgin(true);
			this.setPenisVirgin(false);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BRIEFS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OXFORD_SHIRT, Colour.CLOTHING_GREY, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_MENS_SMART_SHOES, Colour.CLOTHING_BLACK, false), true, this);

			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.PHYSICAL));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(this.getMainWeapon()==null) {
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.PHYSICAL));
		}
		
		this.setWingSize(WingSize.THREE_LARGE.getValue());
		
		this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
	}
	
	public void resetBody() {
		this.setBody(Gender.M_P_MALE, RacialBody.DEMON, RaceStage.GREATER);
		
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_ORANGE));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PURPLE), true);
		
		this.setHeight(Height.THREE_TALL.getMedianValue());
		
		this.setHornType(HornType.STRAIGHT);
		
		this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		this.setWingSize(WingSize.THREE_LARGE.getValue());
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(this.isFeminine()) {
			return "#EB82ED";
		} else {
			return "#42C6FF";
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}
	
	@Override
	public void endSex(boolean applyEffects) {
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	

	// Combat:
	
	@Override
	public String getCombatDescription() {
		return "Zaranix is furious at your intrusion into his laboratory, and who knows what he'll do to you if you fall here?";
	}

	@Override
	public String getMainAttackDescription(boolean isHit) {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"With a booming shout, Zaranix delivers a solid kick to your torso!",
						"With an angry roar, Zaranix punches you square in the chest!",
						"Zaranix lets out a furious shout as he punches you in the [pc.arm]!") 
			+ "</p>";
	}

	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"Letting out a booming roar, Zaranix thrusts his arm into the air and casts a spell!",
						"Zaranix steps back, and with an angry shout, casts a spell!") 
			+ "</p>";
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("Victory", "You have defeated Zaranix!", AFTER_COMBAT_VICTORY) {
				@Override
				public void effects() {
					Main.game.getArthur().setLocation(WorldType.LILAYAS_HOUSE_GROUND_FLOOR, PlaceType.LILAYA_HOME_LAB, false);
					
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_H_THE_GREAT_ESCAPE) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_I_ARTHURS_TALE));
					}
				}
			};
		} else {
			return new Response("Defeat", "You have been defeated...", AFTER_COMBAT_DEFEAT);
		}
	}
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {// need to mention Lilaya
			return "<p>"
						+ "Zaranix staggers back, reaching out to grab hold of the edge of a nearby table as he pants,"
						+ " [zaranix.speech(Please! No more!)]"
					+ "</p>"
					+ "<p>"
						+ "It's quite clear that he's completely unable to continue fighting any longer, so, stepping forwards, you make your demands known,"
						+ " [pc.speech(You're going to hand over Arthur now, understood?)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(F-Fine...)]"
						+ " he sighs, understanding that he has little choice in the matter,"
						+ " [zaranix.speech(You heard the [pc.race], Arthur, you're free to go...)]"
					+ "</p>"
					+ "<p>"
						+ "Arthur, up until now having been hiding behind one of the laboratory's cabinets, slowly stands up,"
						+ " [arthur.speech(Bloody hell! Well, you're certainly letting me go easily! Not that I should be complaining...)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(If it were anyone else, I'd have refused to let you go, but I just realised who this [pc.race] is,)]"
						+ " Zaranix says, stepping back and collapsing down into a nearby chair."
						+ " [zaranix.speech(<i>[pc.She]'s</i> the [pc.race] who's moved in with Lilaya... Yes, I know who you are. I may not get out much, but I still keep my ear close to the grapevine.)]"
					+ "</p>"
					+ "<p>"
						+ "[arthur.speech(Bloody hell! So it's Lilaya who's behind this rescue?)]"
						+ " Arthur exclaims, half-way across the room towards the door,"
						+ " [arthur.speech(The last I heard, oh, what was it now... Oh yes! She was going to 'blast me into a million pieces, or banish me into an alternate dimension' the next time she saw me."
							+ " I mean, considering that nobody's managed to travel between dimensions before, I couldn't quite take her threat seriously, but she <i>did</i> seem to be pretty angry."
							+ " Maybe I'd be better off staying he-)]"
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(Be quiet Arthur!)]"
						+ " Zaranix shouts,"
						+ " [zaranix.speech(I may have wealth and a modicum of power in this city, but having never been recognised by my Lilin mother, I'd be a fool to stand against the wishes of a demon who has."
							+ " And it's not just any demon! Hah! Lyssieth's precious little half-demon herself!"
							+ " So go on! Get out of here Arthur!)]"
					+ "</p>"
					+"<p>"
						+ "[arthur.speech(Well, bloody hell! Maybe I'll see you around Zaranix. It wasn't too bad living here, all things considered!)]"
						+ " Arthur says, before turning to you,"
						+ " [arthur.speech(I'll go on ahead, so I suppose I'll see you over at Lilaya's place!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, he steps past you and makes his exit, leaving you alone in the lab with the defeated incubus."
						+ " You wonder if you should take advantage of his weakened state, or simply turn around and leave..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Leave", "Leave Zaranix's house.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else if(index==2) {
				return new ResponseSex("Use Zaranix", "Have some fun with this incubus.",
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getZaranix(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "[pc.speech(You made me go through a lot of trouble to find Arthur,)]"
							+ " you say, stepping towards the exhausted [zaranix.race],"
							+ " [pc.speech(I think you owe me an apology...)]"
						+ "</p>"
						+ "<p>"
							+ "Despite the fact that [zaranix.she]'s a powerful demon, [zaranix.name] lets out a lewd little sigh as [zaranix.she] looks up into your [pc.eyes]."
							+ " Your strong arcane aura is quite clearly having a profound effect on [zaranix.herHim], and as you approach, [zaranix.she] pleads,"
							+ " [zaranix.speech(Please! Let me make it up to you!)]"
						+ "</p>"
						+ "<p>"
							+ "Grinning down at [zaranix.name], you prepare to have some fun..."
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"Allow Zaranix to fuck you.",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getZaranix(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						AFTER_SEX_VICTORY,
						"<p>"
							+ "You weren't really expecting it to be so easy to convince [zaranix.name] to part with Arthur, and, feeling a little guilty for fighting [zaranix.herHim] in [zaranix.her] own house,"
								+ " you decide to try and make it up to [zaranix.herHim]."
							+ " Looking down at [zaranix.her] crotch and biting your [pc.lip], you let out a pleading little whine,"
							+ " [pc.speech(Sorry [zaranix.name] for all this trouble... Perhaps you'd like me to do something for you...)]"
						+ "</p>"
						+ "<p>"
							+ "[zaranix.Name] looks up at you, a big grin spreading across [zaranix.her] face as [zaranix.she] realises what it is you want."
							+ " Standing up, [zaranix.she] steps over towards you, before running [zaranix.her] hands over your [pc.hips] and around to give your [pc.ass+] a firm squeeze."
							+ " You can tell by the hungry look in [zaranix.her] eyes that your arcane aura is filling [zaranix.her] mind with lust, and, to confirm your suspicions, the [zaranix.race] leans forwards, before [zaranix.moaning],"
							+ " [zaranix.speech(There is one thing you can do... I want to hear you call out my name as I fuck you!)]"
						+ "</p>");
				
			} else if (index == 4) {
				return new Response("Transformations",
						"Get Zaranix to use [zaranix.her] demonic powers to transform [zaranix.herself]...",
						BodyChanging.BODY_CHANGING_CORE){
					@Override
					public void effects() {
						Main.game.saveDialogueNode();
						BodyChanging.setTarget(Main.game.getZaranix());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Continue", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name] steps back and sinks down into a nearby chair, a happy smile on [npc.her] face as [npc.she] gazes up at you,"
							+ " [npc.speech(That was fun... I guess losing Arthur for that wasn't too bad a deal...)]"
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "[npc.Name] steps back and sinks down to the floor, letting out [npc.a_moan+] as [npc.her] hands drop down between [npc.her] legs,"
							+ " [npc.speech(Ah! I didn't even get to orgasm...)]"
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Smirking down at the [npc.race] one last time, you turn around and take your leave."
						+ " Retracing your steps through [npc.her] house, you soon find yourself back out in the streets of Demon Home."
						+ " Now that Arthur's been rescued, you realise that you should probably head over to Lilaya's house to find out what your next step will be."
					+ "</p>");
			
			return UtilText.parse(Main.game.getZaranix(), UtilText.nodeContentSB.toString());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your journey.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeated", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You can't continue fighting any longer, and, letting out a defeated groan, you stagger backwards and fall down against one wall of Zaranix's lab."
						+ " Glancing up at your conqueror, you see the imposing form of the huge incubus towering over you with a maniacal grin on his face."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(So, you thought you'd barge into my lab and get away with it?! Hah!)]"
						+ " He shouts, turning around and grabbing a small glass bottle, before facing you once more,"
						+ " [zaranix.speech(Well, Arthur and I were in need of a test subject, and you'll do nicely!)]"
					+ "</p>"
					+ "<p>"
						+ "Before you can say anything, the huge incubus pulls the stopper out of the top of the bottle, and, stepping towards you, kneels down and forces the glass against your lips,"
						+ " [zaranix.speech(Come on! Drink up! Arthur assures me that this particular concoction will transform you the perfect little cock-sucker, and I can't wait to be judge of that myself!)]"
					+ "</p>"
					+ "<p>"
						+ "You still have a little energy left, and as you feel the first few drops of the potion seeping into your mouth, you realise that you could spit it out and try to throw the bottle to the floor."
						+ " Then again, maybe it's best to just do as the demon says..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
					return new Response("Spit",
							"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
								+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
							null);
				} else {
					return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_DEFEAT_SPIT);
				}
				
			} else if(index==2) {
				return new Response("Swallow", "Swallow the potion.", AFTER_COMBAT_DEFEAT_SWALLOW) {
					@Override
					public void effects() {
//						Main.game.getTextEndStringBuilder().append("<p>"+Main.game.getPlayer().setFemininity(100)+"</p>");
						Main.game.getTextEndStringBuilder().append("<p>"+Main.game.getPlayer().incrementLipSize(7)+"</p>");
						Main.game.getPlayer().addFetish(Fetish.FETISH_ORAL_GIVING);
						Main.game.getTextEndStringBuilder().append(
								"<p>"
									+ "A desperate, overwhelming need to have Zaranix's huge demonic cock thrusting deep down your throat causes you to let out an incredibly lewd moan."
									+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the "+Fetish.FETISH_ORAL_GIVING.getName(Main.game.getPlayer())+" fetish!</b>"
								+ "</p>");
						Main.game.getPlayer().addFetish(Fetish.FETISH_CUM_ADDICT);
						Main.game.getTextEndStringBuilder().append(
								"<p>"
									+ "You can't help but lick your huge new cock-sucking lips as you imagine the incubus giving you your delicious reward; a huge load of salty, demonic cum...."
									+ "</br><b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>You have gained the "+Fetish.FETISH_CUM_ADDICT.getName(Main.game.getPlayer())+" fetish!</b>"
								+ "</p>");
					}
				};
			
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT_SPIT = new DialogueNodeOld("Defeated", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "There's no way you want to be transformed into Zaranix's 'perfect little cock-sucker'."
						+ " Summoning the last of your energy, you twist your head to one side, spitting the few drops of the potion that entered your mouth to the floor, before grabbing the little glass bottle and hurling it to the other side of the room."
					+ "</p>"
					+ "<p>"
						+ "The sound of smashing glass is accompanies by a deep, booming roar, as the now-furious incubus shouts,"
						+ " [zaranix.speech(You insolent "+(Main.game.getPlayer().isFeminine()?"whore":"bastard")+"! You'll suck my cock, with or without that potion!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Zaranix forces you to orally service him.",
						false, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getZaranix(), SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL_ZARANIX))),
						AFTER_SEX_DEFEAT,
						"<p>"
							+ "Grabbing you by the [pc.arm], Zaranix roughly drags you across the lab."
							+ " Collapsing down in a nearby chair, he pulls you forwards on your knees as he spreads his legs, bringing your face right up against his crotch and booming,"
							+ " [zaranix.speech(You're going to love my cock, slut!)]"
						+ "</p>"
						+ "<p>"
							+ "You're far too exhausted from your fight to put up much of a resistance, and as the horny incubus forces your mouth ever closer to the huge bulge in his groin,"
								+ " you realise that he's not going to let you go until he's satisfied..."
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT_SWALLOW = new DialogueNodeOld("Defeated", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You decide that there's no point in resisting the dominant incubus, and, opening wide to accept the neck of the potion bottle past your lips, you obediently take the thick, pink liquid into your mouth."
						+ " The taste strongly reminds you of strawberries, and, eagerly gulping down the sweet mixture, it only takes you a few moments to completely drain the bottle of its contents."
					+ "</p>"
					+ "<p>"
						+ "As Zaranix takes the flask away from your mouth, a fuzzy, warm feeling starts spreading all throughout your body."
						+ " You suddenly find yourself thinking how utterly wonderful it would be if Zaranix gave you even more of that sickly-sweet liquid to drink."
						+ " Even better, you think, if there was an endless supply..."
					+ "</p>"
					+ "<p>"
						+ "Perhaps you could convince Zaranix to enchant his cock so that he cums that delicious liquid..."
					+ "</p>"
					+ "<p>"
						+ "You'd happily wrap your fat, juicy, cock-sucking lips -<i>were they always this big?</i>- around his huge, thick cock if it meant you got to taste that again..."
					+ "</p>"
					+ "<p>"
						+ "Now that you think about it, just getting to suck his cock would be reward enough..."
					+ "</p>"
					+ "<p>"
						+ "You need to suck his cock... You <i>need</i> to drink his cum... You <b><i>need</i></b> his cock down your throat... Right... Now!"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Need... Cock...", "Zaranix's potion has had quite a strong effect... You really need to suck his cock, then maybe you'll be able to think clearly again?",
						true, false,
						new SMZaranixCockSucking(
								Util.newHashMapOfValues(new Value<>(Main.game.getZaranix(), SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_PERFORMING_ORAL_ZARANIX))),
						AFTER_SEX_DEFEAT,
						"<p>"
							+ "Zaranix steps back and sits down in one of the lab's many chairs."
							+ " Spreading his legs, he grins down at you,"
							+ " [zaranix.speech(I bet you want to put those new lips of yours to use, don't you, my little cock-sucker?)]"
						+ "</p>"
						+ "<p>"
							+ "You let out an intense, desperate whine as you gaze hungrily at the bulge in Zaranix's groin."
							+ " Before you know what you're doing, you're shuffling over towards him on your knees, running your hands up and over his groin as you plead,"
							+ " [zaranix.speech(~Mmm!~ Please! Need... cock!)]"
						+ "</p>"
						+ "<p>"
							+ "[zaranix.speech(Hah!)]"
							+ " The incubus booms,"
							+ " [zaranix.speech(Then get to it, cock-sucker!)]"
						+ "</p>");
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Used", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Having had enough of using your mouth, Zaranix rather unceremoniously pushes you away and stands up,"
						+ " [zaranix.speech(That was pretty good. Hah!)]"
					+ "</p>"
					+ "<p>"
						+ "Before you can reply, the incubus reaches down and grabs your [pc.arm]."
						+ " The grip of his big, strong hand is too much for you, and, left with little choice but to shuffle and crawl alongside Zaranix, you're quickly dragged back through the house and to the front door."
					+ "</p>"
					+ "<p>"
						+ "[zaranix.speech(I wouldn't bother coming back, unless you want another taste of my cock, slut!)]"
						+ " He laughs, throwing you out into the street and slamming eh door behind you..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Zaranix has thrown you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
					@Override
					public void effects() {
						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_DEMON_HOME, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	// Sex:

	public SexType getForeplayPreference() {
		if(Sex.getSexPositionSlot(Main.game.getZaranix())==SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX && this.hasPenis()) {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH);
		}
		
		return super.getForeplayPreference();
	}
	
	public SexType getMainSexPreference() {
		if(Sex.getSexPositionSlot(Main.game.getZaranix())==SexPositionSlot.KNEELING_RECEIVING_ORAL_ZARANIX && this.hasPenis()) {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH);
		}

		return super.getMainSexPreference();
	}

}