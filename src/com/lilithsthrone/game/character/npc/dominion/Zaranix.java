package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.QuestLine;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPosition;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.dominion.zaranix.SMZaranixCockSucking;
import com.lilithsthrone.game.sex.managers.universal.SMDomStanding;
import com.lilithsthrone.game.sex.managers.universal.SMSubStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.90
 * @author Innoxia
 */
public class Zaranix extends NPC {

	private static final long serialVersionUID = 1L;

	public Zaranix() {
		this(false);
	}
	
	private Zaranix(boolean isImported) {
		super(new NameTriplet("Zaranix", "Zaranix", "Zoelix"),
				"Zaranix is one of the few demons that feels more comfortable in his incubus, rather than succubus, form."
						+ " Muscular, tall, and handsome, Zaranix uses both his cunning mind and good looks to get what he wants.",
				15, Gender.M_P_MALE, RacialBody.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_OFFICE, true);

		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_ORANGE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PURPLE), true);
			
			this.setHeight(Height.THREE_TALL.getMedianValue());
			
			this.setHornType(HornType.STRAIGHT);
			
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
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
	public Zaranix loadFromXML(Element parentElement, Document doc) {
		Zaranix npc = new Zaranix(true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);

		if(npc.getMainWeapon()==null) {
			npc.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.PHYSICAL));
		}
		npc.addFetish(Fetish.FETISH_ORAL_RECEIVING);
		
		return npc;
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#42C6FF";
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
	public String getAttackDescription(Attack attackType, boolean isHit) {
		if (attackType == Attack.MAIN) {
			return "<p>"
						+ UtilText.returnStringAtRandom(
								"With a booming shout, Zaranix delivers a solid kick to your torso!",
								"With an angry roar, Zaranix punches you square in the chest!",
								"Zaranix lets out a furious shout as he punches you in the [pc.arm]!") 
					+ "</p>";
		} else {
			return "<p>"
					+ UtilText.returnStringAtRandom(
							"Letting out a booming roar, Zaranix thrusts his arm into the air and casts a spell!",
							"Zaranix steps back, and with an angry shout, casts a spell!") 
				+ "</p>";
		}
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY);
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO - Zaranix collapses."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Release Arthur", "Demand that Zaranix releases Arthur.", AFTER_COMBAT_VICTORY_RELEASED_ARTHUR) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
						Main.game.getArthur().setLocation(WorldType.ZARANIX_HOUSE_GROUND_FLOOR, PlaceType.ZARANIX_GF_LOUNGE, false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_RELEASED_ARTHUR = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "TODO - Zaranix releases Arthur. Arthur says that he'll see you back at Lilaya's house."
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
						true, false, Main.game.getZaranix(), new SMDomStanding(), AFTER_SEX_VICTORY,
						"<p>"
							+ ""
						+ "</p>");
				
			} else if(index==3) {
				return new ResponseSex("Submit",
						"Allow Zaranix to fuck you.",
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null, null,
						true, true, Main.game.getZaranix(), new SMSubStanding(), AFTER_SEX_VICTORY,
						"<p>"
							+ ""
						+ "</p>");
				
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
			if(Sex.getNumberOfPartnerOrgasms() >= 1) {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "TODO - Zaranix is satisfied."
						+ "</p>");
				
			} else {
				UtilText.nodeContentSB.append(
						"<p>"
							+ "TODO - Zaranix is not satisfied."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You decide to take your leave."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
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
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeated", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>" //TODO drink potion or not
						+ "TODO - Zaranix tries to use the potion Arthur just made on you."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_DEFEAT_SPIT);
			
			} else if(index==2) {
				return new Response("Swallow", "Swallow the potion.", AFTER_COMBAT_DEFEAT_SWALLOW) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append("<p>"+Main.game.getPlayer().setFemininity(100)+"</p>");
						Main.game.getTextEndStringBuilder().append("<p>"+Main.game.getPlayer().incrementLipSize(7)+"</p>");
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
						+ "TODO - You spit out the potion."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Zaranix forces you to orally service him.",
						false, false, Main.game.getZaranix(), new SMZaranixCockSucking(), AFTER_SEX_DEFEAT,
						"<p>"
							+ ""
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
						+ "TODO - You swallow the potion. Your entire body shifts to become extremely feminine, before your lips swell up into perfect cock-sucking lips."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Zaranix forces you to orally service him.",
						false, false, Main.game.getZaranix(), new SMZaranixCockSucking(), AFTER_SEX_DEFEAT,
						"<p>"
							+ ""
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
						+ ""
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Zaranix throws you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
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
	
	@Override
	public Attack attackType() {
		if (Math.random() < 0.6
				|| this.getManaPercentage() <= 0.4f
				|| (Main.game.getPlayer().getStatusEffects().contains(StatusEffect.DAZED)
						&& this.getStatusEffects().contains(StatusEffect.ARCANE_SHIELD))) {
			return Attack.MAIN;
		} else {
			return Attack.SPELL;
		}
	}
	
	@Override
	public Spell getSpell() {
		if (!Main.game.getPlayer().getStatusEffects().contains(StatusEffect.DAZED)) {
			return Spell.SLAM_1;
		} else {
			return Spell.ARCANE_SHIELD;
		}
	}
	
	// Sex:
	
	public SexType getForeplayPreference() {
		if(Sex.getSexManager().getPosition() == SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
			return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER);
		}
		
		return super.getForeplayPreference();
	}
	
	public SexType getMainSexPreference() {
		if(Sex.getSexManager().getPosition() == SexPosition.KNEELING_PLAYER_PERFORMING_ORAL) {
			return new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER);
		}

		return super.getMainSexPreference();
	}

}