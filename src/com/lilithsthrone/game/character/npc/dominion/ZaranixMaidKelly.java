package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.NameTriplet;
import com.lilithsthrone.game.character.SexualOrientation;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.managers.universal.SMDomStanding;
import com.lilithsthrone.game.sex.managers.universal.SMSubStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public class ZaranixMaidKelly extends NPC {

	private static final long serialVersionUID = 1L;

	public ZaranixMaidKelly() {
		this(false);
	}
	
	private ZaranixMaidKelly(boolean isImported) {
		super(new NameTriplet("Kelly"),
				"One of Zaranix's succubi maid twins, Kelly is clearly outraged by the fact that you're wandering around the house unsupervised.",
				10, Gender.F_P_V_B_FUTANARI, RacialBody.DEMON, RaceStage.GREATER, new CharacterInventory(10), WorldType.ZARANIX_HOUSE_FIRST_FLOOR, PlaceType.ZARANIX_FF_MAID, true);

		if(!isImported) {
			this.setPlayerKnowsName(false);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_BLUE));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK), true);
			this.setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			this.setHairStyle(HairStyle.STRAIGHT);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_IVORY), true);
	
			this.setHornType(HornType.CURLED);
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);

			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_THONG, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_LACY_PLUNGE_BRA, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_DRESS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEADPIECE, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_STOCKINGS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_SLEEVES, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.MAID_HEELS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
		}
	}
	
	@Override
	public ZaranixMaidKelly loadFromXML(Element parentElement, Document doc) {
		ZaranixMaidKelly npc = new ZaranixMaidKelly(true);

		loadNPCVariablesFromXML(npc, null, parentElement, doc);
		
		return npc;
	}

	@Override
	public String getName() {
		if(!playerKnowsName) {
			return "Zaranix's Maid";
			
		} else {
			return "Kelly";
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		return "#E48AFF";
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
		return "You are fighting a succubus maid!";
	}

	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {
		if (attackType == Attack.MAIN) {
			return "<p>"
						+ "The maid strikes out at you!"
					+ "</p>";
		} else {
			return "<p>"
						+ "The maid lets out an angry shout as she casts a spell!"
					+ "</p>";
		}

	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY);
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT) {
				@Override
				public void effects() {
					
				}
			};
		}
	}
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! You are victorious. You can use the maid (who, still resisting, demands you address her as Kelly), or leave her and carry on."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_MAID.getDialogue(false));
				
			} if(index==2) {
				return new ResponseSex("Use Kelly", "Kelly commands and lift your ass towards her.",
						false, false, Main.game.getKelly(), new SMDomStanding(), AFTER_SEX_VICTORY);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Continue", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "TODO! Continue through the house."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue exploring Zaranix's house.", PlaceType.ZARANIX_GF_MAID.getDialogue(false));
				
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
						+ "TODO! You are defeated. The maid introduces herself as Kelly."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new ResponseSex("Used", "Kelly uses you.",
						false, false, Main.game.getKelly(), new SMSubStanding(), AFTER_SEX_DEFEAT);
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
						+ "TODO! Kelly throws you out."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Thrown out", "Kelly throws you out into the street.", PlaceType.DOMINION_DEMON_HOME.getDialogue(false)) {
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
		if (Math.random() < 0.4) {
			return Attack.MAIN;
		} else {
			return Attack.SEDUCTION;
		}
	}
	
	@Override
	public int getEscapeChance() {
		return 0;
	}

	@Override
	public int getExperienceFromVictory() {
		return 35;
	}
	
	public int getLootMoney() {
		return 80;
	}

}