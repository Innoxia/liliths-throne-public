package com.lilithsthrone.game.character.npc.dominion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.AntennaType;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
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
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMCowgirl;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMKneeling;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.5
 * @version 0.2.4
 * @author Innoxia
 */
public class Brax extends NPC {

	private static final long serialVersionUID = 1L;

	private static List<Artwork> braxArtwork = new ArrayList<>();
	private static List<Artwork> breeArtwork = new ArrayList<>();
	private static List<Artwork> brandiArtwork = new ArrayList<>();
	
	static {
		String artworkFolderName = "Brax";
				
		if(artworkFolderName!=null && !artworkFolderName.isEmpty()) {
			for(Artist artist : Artwork.allArtists) {
				File f = new File("res/images/characters/"+artworkFolderName+"/"+artist.getFolderName());
				if(f.exists()) {
					braxArtwork.add(new Artwork(artworkFolderName, artist));
				}
			}
		}
		
		artworkFolderName = "Bree";
		
		if(artworkFolderName!=null && !artworkFolderName.isEmpty()) {
			for(Artist artist : Artwork.allArtists) {
				File f = new File("res/images/characters/"+artworkFolderName+"/"+artist.getFolderName());
				if(f.exists()) {
					breeArtwork.add(new Artwork(artworkFolderName, artist));
				}
			}
		}
		
		artworkFolderName = "Brandi";
		
		if(artworkFolderName!=null && !artworkFolderName.isEmpty()) {
			for(Artist artist : Artwork.allArtists) {
				File f = new File("res/images/characters/"+artworkFolderName+"/"+artist.getFolderName());
				if(f.exists()) {
					brandiArtwork.add(new Artwork(artworkFolderName, artist));
				}
			}
		}
	}
	
	public Brax() {
		this(false);
	}
	
	public Brax(boolean isImported) {
		super(new NameTriplet("Brax", "Bree", "Brandi"),
				"The 'Chief of Dominion Operations', Brax is a high-ranking enforcer. Muscular, handsome, and with an incredibly dominant personality, he's the focus of every female enforcer's attention.", 3, Gender.M_P_MALE,
				RacialBody.WOLF_MORPH, RaceStage.GREATER, new CharacterInventory(10), WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_BRAXS_OFFICE, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_YELLOW));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
	
			this.setPenisSize(PenisSize.FOUR_HUGE.getMedianValue());
			this.setPenisVirgin(false);
	
			this.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLACK, false), true, this);
	
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MELEE_CHAOS_EPIC, DamageType.FIRE));
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.OFFHAND_CHAOS_EPIC, DamageType.FIRE));
			
			this.addFetish(Fetish.FETISH_DOMINANT);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setPenisVirgin(false);

		if(hasFetish(Fetish.FETISH_BIMBO) && Main.isVersionOlderThan(Main.VERSION_NUMBER, "0.2.4")) {
			this.setPiercedEar(true);
			if(this.getClothingInSlot(InventorySlot.PIERCING_EAR)!=null) {
				this.unequipClothingIntoVoid(this.getClothingInSlot(InventorySlot.PIERCING_EAR), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_GOLD, false), true, this);
			}
		}
	}

	@Override
	public List<Artwork> getArtworkList() {
		if(this.getName().equalsIgnoreCase("Brax")) {
			return braxArtwork;
			
		} else if(this.getName().equalsIgnoreCase("Bree")) {
			return breeArtwork;
			
		} else {
			return brandiArtwork;
		}
	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	public void setBraxsPostQuestStatus() {
		Main.game.getBrax().setLocation(WorldType.ENFORCER_HQ, PlaceType.ENFORCER_HQ_RECEPTION_DESK, true);
		Main.game.getBrax().setPendingClothingDressing(true);
		Main.game.getCandi().addSlave(Main.game.getBrax());
	}
	
	@Override
	public String getDescription() {
		if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
			return "The one-time 'Chief of Dominion Operations', [brax.name] is now completely unrecognisable from [brax.her] former self."
					+ " With some help from Candi, she's been transformed into a brain-dead bimbo, who can only think about where the next cock is coming from.";
			
		} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
			return "The one-time 'Chief of Dominion Operations', [brax.name] is almost unrecognisable from [brax.her] former self."
					+ " With some help from Candi, you've transformed [brax.herHim] into a wolf-girl."
					+ " Where once [brax.she] was muscular and dominant, [brax.she]'s now feminine and submissive, and meekly agrees to do anything that's asked of [brax.herHim].";
			
		} else {
			return "The 'Chief of Dominion Operations', Brax is a high-ranking enforcer. Muscular, handsome, and with an incredibly dominant personality, he's the focus of every female enforcer's attention.";
		}
		
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if(applyEffects) {
			setPendingClothingDressing(true);
		}
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		deleteAllEquippedClothing();
		
		if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.braxBeaten)) {
			AbstractClothing collar = AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, false);
			collar.setSealed(true);
			collar.setColour(Colour.CLOTHING_SILVER);
			this.equipClothingFromNowhere(collar, true, this);
		}
		
		if(isFeminine()) {
			if(hasFetish(Fetish.FETISH_BIMBO)) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_PANTIES, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NIPPLE_TAPE_CROSSES, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_FISHNET_TOP, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MICRO_SKIRT_PLEATED, Colour.CLOTHING_PINK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_FISHNET_STOCKINGS, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_STILETTO_HEELS, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.HAND_FISHNET_GLOVES, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);

				this.setPiercedEar(true);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_HOOPS, Colour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNose(true);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NOSE_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
				this.setPiercedNavel(true);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_NAVEL_GEM, Colour.CLOTHING_GOLD, false), true, this);
				
			} else {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_THONG, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_PLUNGE_BRA, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_KNEEHIGH_SOCKS, Colour.CLOTHING_WHITE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_WOMENS_WATCH, Colour.CLOTHING_PINK_LIGHT, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING,  Colour.CLOTHING_SILVER, false), true, this);
			}
			
		} else {
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_TRAINERS, Colour.CLOTHING_BLACK, false), true, this);
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.braxBeaten)) {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLUE, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLUE, false), true, this);
			} else {
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLACK, false), true, this);
				this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLACK, false), true, this);
			}
		}
		
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}

	@Override
	public SexPace getSexPaceDomPreference(){
		return SexPace.DOM_ROUGH;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
			return SexPace.SUB_EAGER;
			
		} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
			return SexPace.SUB_NORMAL;
			
		} else {
			if(Main.game.isNonConEnabled()) {
				return SexPace.SUB_RESISTING;
				
			} else {
				return SexPace.SUB_NORMAL;
			}
		}
	}

	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
				return "#FF0AA5";
			} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
				return "#C60AFF";
			} else {
				return "#1F35FF";
			}
			
		} else {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.bimbofiedBrax)) {
				return "#E36DE1";
			} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.feminisedBrax)) {
				return "#D79EFF";
			} else {
				return "#ADB4FF";
			}
		}
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return null;
	}

	// Combat:
	@Override
	public String getCombatDescription() {
		return "Brax is crouched down in a fighting stance. His huge muscles and obvious knowledge of the arcane make him a formidable opponent."
				+ " An unsettling grin is plastered across his face, and you get the feeling that he's enjoying the chance to have a fight.";
	}

	@Override
	public String getMainAttackDescription(boolean isHit) {
		return "<p>"
					+ UtilText.returnStringAtRandom(
							"Brax lunges forwards, attempting to deliver a punch to your torso."
									+ (isHit ? " The arcane flames swirling around his forearm dart forwards as his fist makes contact, enveloping you for a brief moment in a fiery vortex."
											+ " Although the flames don't cause any real pain, the arcane's strength-sapping effect still knocks you back, reeling." : " You manage to twist away to one side, allowing Brax's fist to sail harmlessly by."),
							"With surprising swiftness, Brax darts forwards, lifting his leg as he attempts to land a kick."
									+ (isHit ? " As his wolf-like foot connects with your side, the arcane flames swirling around his forearm dart down the length of his body to strike out at the point of contact."
											+ " You're briefly enveloped in a fiery vortex, and although the flames don't cause any real pain, the arcane fire still adds considerable power to Brax's attack."
											: " You see his attack coming and step back to avoid the blow."),
							"Brax steps forwards as he attempts to punch you."
									+ (isHit ? " The arcane flames swirling around his forearm dart forwards as his fist makes contact, enveloping you for a brief moment in a fiery vortex."
											+ " Although the flames don't cause any real pain, the arcane's strength-sapping effect still knocks you back, reeling." : " You jump out of the way, managing to dodge his clearly-telegraphed attack.")) 
				+ "</p>";
	}
			
	@Override
	public String getSpellDescription() {
		return "<p>"
				+ UtilText.returnStringAtRandom(
						"With a grin, Brax focuses on the arcane fire swirling around his arm, and with a sudden thrust forwards, he casts a spell!",
						"Brax focuses on the arcane fire swirling around his arm, and with a sudden thrust forwards, he casts a spell!") 
			+ "</p>";
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AFTER_COMBAT_VICTORY){
				@Override
				public void effects() {
					if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_C_WOLFS_DEN) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_D_SLAVERY));
					}
				}
			};
		} else {
			return new Response("", "", AFTER_COMBAT_DEFEAT);
		}
	}

	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated Brax!";
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "Brax takes one last half-hearted swing at you, which you easily sidestep."
					+ " Stumbling past you, he ends up collapsing against his desk, completely and utterly defeated."
					+ "</p>"
					+ "<p>"
					+ "Walking over to his submissive form, you can't help but smirk as you hear him surrender, "
					+ "[brax.speech(P-Please... No more...)]"
					+ "</p>"
					+ "<p>"
					+ "Grinning down at the broken wolf-boy, you demand to know what's happened to Arthur."
					+ " Turning to one side, Brax pulls a piece of the paper out from a pile of freshly-signed documents, and as he hands it to you, you discover what's happened to Arthur:"
					+ "</p>"
					+ "<p>"
					+ "<h6 style='text-align:center;'>Dominion Enforcer Department</h6>"
					+ "<h5 style='text-align:center;'>RECORD OF SLAVE TRANSFER</h5>"
					+ "<p style='text-align:center;'>The person of <i>Arthur Fairbanks</i>, having being found guilty of <i>treason</i>, has, according to law, been enslaved."
					+ " Following standard procedure, the slave's ownership has been transferred from the Dominion Enforcer Department to a registered slave trader, who has been chosen by random lottery."
					+ "</br></br>"
					+ "Officer in charge of transferring slave ownership: <i>Brax</i>"
					+ "</br></br>"
					+ "Slave trader taking ownership: <i>Scarlett</i>"
					+ "</br></br>"
					+ "Contact address: <i>Scarlett's shop, Slaver Alley</i></p>"
					+ "</p>"
					+ "<p>"
					+ "As you read it over a second time, you let out an annoyed tutting sound, realising that Arthur is a hard person to track down."
					+ " It looks like you're going to have to make your way to Slaver Alley and see if you can find some way to buy Arthur's freedom from this 'Scarlett' person."
					+ "</p>"
					+ "<p>"
					+ "A little groan brings your attention back to Brax, and as you look down at him, you see a lustful glimmer in his eyes."
					+ " Surprisingly, it doesn't seem as though anyone outside the office has heard your fight, and you wonder if you should take advantage of Brax's weakened state..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Resist", "As tempting as it is, you don't really want to have sex with Brax...", AFTER_COMBAT_VICTORY_NO_SEX){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxBeaten);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_MINI_SKIRT, Colour.CLOTHING_BLUE, false), false);
					}
				};
				
			} else if (index == 2) {
				return new Response("Dominate Brax",
						"Brax's broken, horny form is too much for you to resist, and you can't help but smile down deviously at the wolf-boy as you prepare to make him your bitch.",
						AFTER_COMBAT_VICTORY_DOMINANT_SEX,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_DOMINANT)), CorruptionLevel.ONE_VANILLA, null, null, null){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxBeaten);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_MINI_SKIRT, Colour.CLOTHING_BLUE, false), false);
					}
				};
				
			} else if (index == 3) {
				return new Response("Submit to Brax",
						"Although you've defeated him, your submissive nature is causing you to consider letting Brax dominantly fuck you...",
						AFTER_COMBAT_VICTORY_SUBMISSIVE_SEX,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_SUBMISSIVE)), null, null, null, null){
					@Override
					public Femininity getFemininityRequired() {
						return Femininity.FEMININE;
					}
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxBeaten);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHIRT, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_SHORTS, Colour.CLOTHING_BLUE, false), false);
						Main.game.getPlayer().addClothing(AbstractClothingType.generateClothing(ClothingType.ENFORCER_MINI_SKIRT, Colour.CLOTHING_BLUE, false), false);
					}
				};
				
			} else {
				return null;
			}
		}

	};
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_NO_SEX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You don't really feel like having sex with the guy who just tried to beat you up."
						+ " Instead, you push Brax back into his chair, tutting in disapproval as he lets out a desperate whine."
					+ "</p>"
					+ "<p>"
						+ "Taking one last look around Brax's office, you notice that there's a neatly-folded spare uniform lying on top of a cabinet."
						+ " Deciding to punish Brax a little more for trying to beat you up, <b>you take the spare uniform, and add it to your inventory</b>."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.") {
					@Override
					public void effects() {
//						Main.game.getPlayer().setLocation(WorldType.DOMINION, PlaceType.DOMINION_ENFORCER_HQ);
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
						((Brax) Main.game.getBrax()).setBraxsPostQuestStatus();
					}
				};
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_DOMINANT_SEX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You feel a devious smile spread across your face as you walk over to Brax. Leaning into him, you growl down into his ear, "
						+ UtilText.parsePlayerSpeech("I hope you like it rough!")
					+ "</p>"
					+ "<p>"
						+ "Brax lets out a surprised cry as you reach down and firmly grab his groin with one hand."
						+ " Pressing your lips against his to prevent him from making any more noise, you thrust your tongue into his mouth, squeezing down on his satisfyingly large package as he squirms and melts under your touch."
					+ "</p>"
					+ "<p>"
						+ "Breaking off the kiss, but making sure not to let go of his crotch, you growl down to him again, "
						+ UtilText.parsePlayerSpeech("So, you ready to be my little bitch?")
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(W-Wait I-)]"
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Wrong answer!")
						+" you cry, giving Brax's throbbing cock a hard squeeze as you interrupt his response."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Aah! Yes! Yes, I like it rough!)]"
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Mmm, that's right, ")
						+" you sigh, softening your grip before running your fingers up and down Brax's shorts, biting your lip as you get a good feel of the impressive length of his throbbing cock, "
						+ UtilText.parsePlayerSpeech("and who's going to be a good little bitch for their new alpha?")
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(I-I am...)]"
						+" Brax groans, admitting defeat."
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Good little beta!")
						+" you cry, happy now that you've asserted your dominance over the handsome wolf-boy."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Get started", "Start dominating your new bitch.",
						false, false,
						new SMKneeling(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.KNEELING_RECEIVING_ORAL)),
								Util.newHashMapOfValues(new Value<>(Main.game.getBrax(), SexPositionSlot.KNEELING_PERFORMING_ORAL))),
						AFTER_DOMINANT_SEX,
						"<p>"
							+ "With a forceful push, you shove Brax down onto his knees before you."
							+ " His meek, submissive look couldn't be further from the aggressive snarl that he greeted you with when you entered his office, and you grin down at him as you prepare to make him your bitch."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY_SUBMISSIVE_SEX = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You reach down and run your fingertips over Brax's crotch, biting your lip and letting out a soft squeal as you feel that he's already hard."
						+ " Encouraged by your touch, Brax leans forwards, and you happily accept his tongue into your mouth as he pulls you down into a kiss."
					+ "</p>"
					+ "<p>"
						+ "Your submissive nature starts to take over, and after gently breaking off the kiss, you whisper down to Brax, "
						+ UtilText.parsePlayerSpeech("I bet you're pretty angry at being beaten like that, huh?")
					+ "</p>"
					+ "<p>"
						+ "As Brax grunts in agreement, you continue teasing him, "
						+ UtilText.parsePlayerSpeech("I wonder what you'd like to do to me to get your revenge... Perhaps you'd grab me with those big, strong hands...")
					+ "</p>"
					+"<p>"
						+ "You reach down, and, taking Brax's wolf-like hands in yours, guide them up to your waist."
						+ " As he starts to get the idea, you feel him roughly grab your "+Main.game.getPlayer().getHipSize().getDescriptor()+" hips, causing you to let out a lewd little cry."
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("And then, pull me in against you as you ruthlessly tongue-fuck my mou- ~hrmp!~")
					+ "</p>"
					+"<p>"
						+ "Brax does exactly what you suggest, and eagerly jerks you forwards, slamming his lips against yours as he starts passionately kissing you."
						+ " He stands up, reaching down to grope and squeeze your "+Main.game.getPlayer().getAssSize().getDescriptor()+" ass as you grind yourself against him."
					+ "</p>"
					+ "<p>"
						+ UtilText.parsePlayerSpeech("Then mayb-")
						+" you try to continue, after breaking of the kiss, but Brax reaches up and presses his hand down firmly over your mouth."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Then maybe you shut up, get down on all fours, and present yourself like the submissive little slut you clearly are!)]"
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Obey", "Do as Brax says and present yourself for him.",
						false, true,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getBrax(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						AFTER_SUBMISSIVE_SEX,
						"<p>"
							+ "You obediently do as Brax commands and drop down on all fours right there in the middle of his office."
							+ (Main.game.getPlayer().getTailType() == TailType.LYCAN
								?" You can't help but shake your ass at the dominant wolf-boy, and you playfully flick your wolf-like tail back and forth, making pitiful little whining noises as you eagerly plead for Brax's thick cock."
								:" You can't help but shake your ass at the dominant wolf-boy, and you turn your head and make a show of biting your lip, making pitiful little whining noises as you eagerly plead for Brax's thick cock.")
						+ "</p>"
						+ "<p>"
							+ "Brax stands behind you, grinning, and you know that it's only going to be a matter of seconds before he fills you with his alpha cock."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};

	
//	private static String fetishChanges = "";
	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		/**
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have been defeated by Brax!";
		}

		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.braxTransformedPlayer)) {
				return "<p>"
						+ "You can't carry on fighting any longer, and your legs give out from beneath you as you sink down onto your knees."
						+ " As Brax lets out a deep laugh, you find yourself looking down at the floor, trying to avoid his powerful gaze."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Hah! Looks like you're still just a submissive little bitch! Didn't you learn anything from last time?!)]"
						+ " he growls, stepping forwards and grabbing you by the neck, "
						+ "[brax.speech(Now lets get you another drink!)]"
					+ "</p>"
					+ "<p>"
						+ "You cry out as Brax roughly drags you across the room to his desk."
						+ " Fishing about in one of the drawers, he quickly finds what he's looking for, and with a light clink of glass, places a very familiar-looking bottle on the desk in front of you."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(I was so pleased with the last one, I got a few more of these potions made!)]"
						+ " Brax says, pulling out the glass stopper before roughly shoving the bottle's neck down your throat,"
						+ " [brax.speech(That's right, be a good [pc.girl] and drink it all down again...)]"
					+ "</p>"
					+ "<p>"
						+ "With the bottle's transformative liquid already pouring out into your mouth, you find yourself having to make the decision of whether to spit it out, or do as the dominant wolf-boy says and swallow it all down..."
					+ "</p>";
				
			} else {
				if(Main.game.getBrax().getFoughtPlayerCount()>1) {
					return "<p>"
							+ "You can't carry on fighting any longer, and your legs give out from beneath you as you sink down onto your knees."
							+ " As Brax lets out a deep laugh, you find yourself looking down at the floor, trying to avoid his powerful gaze."
						+ "</p>"
						+ "<p>"
							+ "[brax.speech(Hah! What a submissive little bitch! I was kinda hoping that you'd put up a better fight than that, but, whatever,)]"
							+ " he growls, stepping forwards and grabbing you by the neck, "
							+ "[brax.speech(Now this time, you're gonna drink down my potion like a good little bitch, aren't you?!)]"
						+ "</p>"
						+ "<p>"
							+ "You cry out as Brax roughly drags you across the room to his desk."
							+ " Fishing about in one of the drawers, he quickly finds one of his transformative potions, and with a light clink of glass, places yet another very delicate-looking bottle on the desk in front of you."
						+ "</p>"
						+ "<p>"
							+ "[brax.speech(That ungrateful slut downstairs still doesn't want to be a wolf-girl...)]"
							+ " Brax says, pulling out the glass stopper before roughly shoving the bottle's neck down your throat, "
							+ "[brax.speech(So this one's all for you! Drink it down bitch!!)]"
						+ "</p>"
						+ "<p>"
							+ "With the bottle's transformative liquid already pouring out into your mouth, you find yourself having to make the decision of whether to spit it out, or do as the dominant wolf-boy says and swallow it all down..."
						+ "</p>";
				} else {
					return "<p>"
								+ "You can't carry on fighting any longer, and your legs give out from beneath you as you sink down onto your knees."
								+ " As Brax lets out a deep laugh, you find yourself looking down at the floor, trying to avoid his powerful gaze."
							+ "</p>"
							+ "<p>"
								+ "[brax.speech(Hah! What a submissive little bitch! I was kinda hoping that you'd put up a better fight than that, but, whatever,)]"
								+ " he growls, stepping forwards and grabbing you by the neck, "
								+ "[brax.speech(Now let's talk more about that little punishment I mentioned.)]"
							+ "</p>"
							+ "<p>"
								+ "You cry out as Brax roughly drags you across the room to his desk."
								+ " Fishing about in one of the drawers, he quickly finds what he's looking for, and, with a light clink of glass, places a very delicate-looking bottle on the desk in front of you."
							+ "</p>"
							+ "<p>"
								+ "[brax.speech(I got this made up for that useless bimbo you saw downstairs, but the ungrateful slut said she didn't want to be a wolf-girl...)]"
								+ " Brax says, pulling out the glass stopper before roughly shoving the bottle's neck down your throat, "
								+ "[brax.speech(That's right, drink it all down... Oh fuck, this is gonna be good!)]"
							+ "</p>"
							+ "<p>"
								+ "With the bottle's transformative liquid already pouring out into your mouth, you find yourself having to make the decision of whether to spit it out, or do as the dominant wolf-boy says and swallow it all down..."
							+ "</p>";
				}
			}
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
					return new Response("Spit", "Spit out the transformative liquid.", AFTER_DEFEAT_TRANSFORMATION_REFUSED);
				}
				
			} else if (index == 2) {
				return new Response("Swallow", "Do as Brax says and swallow the strange liquid.", AFTER_DEFEAT_TRANSFORMATION,
						Util.newArrayListOfValues(new ListValue<>(Fetish.FETISH_TRANSFORMATION_RECEIVING)),
						Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel(),
						null,
						null,
						null){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.braxTransformedPlayer);
						
						if(Main.getProperties().forcedFetishPercentage != 0) {
							Main.game.getPlayer().addFetish(Fetish.FETISH_SUBMISSIVE);
						}
						
						switch(Main.getProperties().forcedTFPreference) {
							case HUMAN:
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								if(!Main.game.getPlayer().hasVagina()) {
									Main.game.getPlayer().setVaginaType(VaginaType.HUMAN);
								}
								break;
								
							case MINIMUM: // ears, eyes, tails, horns, antenna, and wings
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								if(!Main.game.getPlayer().hasVagina()) {
									Main.game.getPlayer().setVaginaType(VaginaType.HUMAN);
								}
								Main.game.getPlayer().setEarType(EarType.LYCAN);
								Main.game.getPlayer().setEyeType(EyeType.LYCAN);
								Main.game.getPlayer().setTailType(TailType.LYCAN);
								Main.game.getPlayer().setHornType(HornType.NONE);
								Main.game.getPlayer().setAntennaType(AntennaType.NONE);
								Main.game.getPlayer().setWingType(WingType.NONE);
								Main.game.getPlayer().setHairType(HairType.LYCAN);
								break;
								
							case REDUCED:
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								Main.game.getPlayer().setVaginaType(VaginaType.WOLF_MORPH);
								
								Main.game.getPlayer().setEarType(EarType.LYCAN);
								Main.game.getPlayer().setEyeType(EyeType.LYCAN);
								Main.game.getPlayer().setTailType(TailType.LYCAN);
								Main.game.getPlayer().setHornType(HornType.NONE);
								Main.game.getPlayer().setAntennaType(AntennaType.NONE);
								Main.game.getPlayer().setWingType(WingType.NONE);
								Main.game.getPlayer().setHairType(HairType.LYCAN);
								
								Main.game.getPlayer().setBreastType(BreastType.WOLF_MORPH);
								Main.game.getPlayer().setAssType(AssType.WOLF_MORPH);
								Main.game.getPlayer().setArmType(ArmType.LYCAN);
								Main.game.getPlayer().setLegType(LegType.LYCAN);
								
								if(Main.getProperties().multiBreasts!=0) {
									Main.game.getPlayer().setBreastRows(3);
								}
								break;
								
							case NORMAL: case MAXIMUM:
								Main.game.getPlayer().setPenisType(PenisType.NONE);
								Main.game.getPlayer().setVaginaType(VaginaType.WOLF_MORPH);
								
								Main.game.getPlayer().setEarType(EarType.LYCAN);
								Main.game.getPlayer().setEyeType(EyeType.LYCAN);
								Main.game.getPlayer().setTailType(TailType.LYCAN);
								Main.game.getPlayer().setHornType(HornType.NONE);
								Main.game.getPlayer().setAntennaType(AntennaType.NONE);
								Main.game.getPlayer().setWingType(WingType.NONE);
								Main.game.getPlayer().setHairType(HairType.LYCAN);
								
								Main.game.getPlayer().setBreastType(BreastType.WOLF_MORPH);
								Main.game.getPlayer().setAssType(AssType.WOLF_MORPH);
								Main.game.getPlayer().setArmType(ArmType.LYCAN);
								Main.game.getPlayer().setLegType(LegType.LYCAN);
								
								Main.game.getPlayer().setSkinType(SkinType.LYCAN);
								Main.game.getPlayer().setFaceType(FaceType.LYCAN);
								
								if(Main.getProperties().multiBreasts!=0) {
									Main.game.getPlayer().setBreastRows(3);
								}
								break;
						}
						
						Main.game.getPlayer().setFemininity(Femininity.FEMININE_STRONG.getMinimumFemininity());
						Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
						if(Main.game.getPlayer().getVaginaWetness().getValue()<Wetness.THREE_WET.getValue()) {
							Main.game.getPlayer().setVaginaWetness(Wetness.THREE_WET.getValue());
						}
						
						Main.game.getPlayer().setEyeCovering(new Covering(BodyCoveringType.EYE_LYCAN, Colour.EYE_YELLOW));
						Main.game.getPlayer().setHairCovering(new Covering(BodyCoveringType.HAIR_LYCAN_FUR, Colour.COVERING_BLACK), true);
						Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.LYCAN_FUR, Colour.COVERING_WHITE), true);
						
						if(Main.game.getPlayer().getBreastRawSizeValue()<CupSize.E.getMeasurement()) {
							Main.game.getPlayer().setBreastSize(CupSize.E.getMeasurement());
						}
						if(Main.game.getPlayer().getHipSize().getValue()<HipSize.FOUR_WOMANLY.getValue()) {
							Main.game.getPlayer().setHipSize(HipSize.FOUR_WOMANLY.getValue());
						}
						if(Main.game.getPlayer().getAssSize().getValue()<AssSize.FOUR_LARGE.getValue()) {
							Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE.getValue());
						}
						Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
						Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
						
						
						if(Main.game.getPlayer().getAttributeValue(Attribute.MAJOR_CORRUPTION)<CorruptionLevel.TWO_HORNY.getMinimumValue()) {
							Main.game.getPlayer().setAttribute(Attribute.MAJOR_CORRUPTION, CorruptionLevel.TWO_HORNY.getMinimumValue());
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_DEFEAT_TRANSFORMATION_REFUSED = new DialogueNodeOld("Brax's Office", "In Brax's Office after being forced to drink the potion.", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You have no desire to be transformed into a wolf-girl, so, yanking your head away from Brax, you quickly spit out the fluid that's in your mouth."
						+ " As the pink liquid splatters onto the floor, the aggressive wolf-boy lets out a furious growl,"
						+ " [brax.speech(You stupid bitch! You'll pay for that!)]"
					+ "</p>"
					+ "<p>"
						+ "Angrily tossing the now-empty bottle to the floor, Brax grabs your [pc.arms] and pulls you into him,"
						+ " [brax.speech(You're fucked now, bitch!)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Dominated", "Brax is far too strong for you to resist...",
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getBrax(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						AFTER_SUBMISSIVE_SEX,
						"<p>"
							+ "Brax spins you around, and with a forceful shove, pushes you down to the ground."
							+ " You land on all-fours, with your ass raised up towards the dominant wolf-boy."
							+ " Hearing him let out a deep growl, you make a pitiful little whining noise in response as you realise that you're perfectly presented for Brax to take you, doggy-style."
						+ "</p>"
						+ "<p>"
							+ " Your conqueror stands behind you, grinning, and you know that it's only going to be a matter of seconds before he fills you with his alpha cock..."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_DEFEAT_TRANSFORMATION = new DialogueNodeOld("Brax's Office", "In Brax's Office after being forced to drink the potion.", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			StringBuilder descriptionSB = new StringBuilder();
			descriptionSB.append(
					"<p>"
						+ "The liquid's transformative force is enough to knock to your knees, and you collapse to the floor, panting, as you feel your body start to change."
						+ " You can't stand the powerful rush of transformations that suddenly wash through your body, and you find yourself collapsing to the floor as you pass out..."
					+ "</p>"
					+ "<p>"
						+ "..."
					+ "</p>"
					+ "<p>"
						+ "[brax.speech(Hrmph! She never said anything about them losing consciousness! Hey! Wake up!)]"
					+ "</p>"
					+ "<p>"
						+ "With Brax's worried voice sounding in your ears, you slowly start to come back to your senses."
						+ " Blinking your eyes open, you see a look of relief wash over the wolf-like face that's hovering over you."
						+ " Pushing yourself up on your elbows, you find yourself quickly recovering, and as Brax helps you to stand up on unfamiliar, shaking legs, the feeling of exhaustion is quickly replaced with an intense, burning lust."
					+ "</p>"
					+ "<p>"
						+ "Without thinking, you grab Brax's head and pull him into a manic, desperate kiss, grinding yourself against him as he tries to reel back in shock."
						+ " After a moment, you feel the wolf-boy start to return the kiss, and you let out a happy giggle as you start making out with the very guy who's just forced you to transform..."
					+ "</p>"
					+ "<p>"
						+ "As that thought crosses your mind, your eyes open wide, and you get your lust under control for a moment as you step back, looking down at your body to see what's changed."
						+ " You hear Brax let out a laugh as he notices that you're trying to find out what he's done to you, and, with a powerful grip, he grabs your shoulders and spins you around to face a mirror hanging on one wall."
					+ "</p>");

			switch(Main.getProperties().forcedTFPreference) {
				case HUMAN:
					descriptionSB.append(
							"<p>"
								+ "As you see your new reflection, you let out a little gasp."
								+ " Brax has transformed you into his perfect vision of a woman, and your [pc.eyes+] open wide in shock as you feel the weight of your huge, E-cup tits that are now sitting on your chest."
								+ " As Brax steps up behind you, you feel his shaggy fur brushing up against your vulnerable, feminine body."
							+ "</p>");
					break;
				case MINIMUM:
					descriptionSB.append(
							"<p>"
								+ "As you see your new reflection, you let out a little gasp."
								+ " Brax has transformed you into his perfect vision of a partial wolf-girl, and your yellow, predatory eyes open wide in shock as you feel the weight of your huge, E-cup tits that are now sitting on your chest."
								+ " Wolf-like fur covers your pair of fluffy ears and long, shaggy tail, and as Brax steps up behind you, you see that it's the exact same bright white colour as his is."
							+ "</p>");
					break;
				case REDUCED:
					descriptionSB.append(
							"<p>"
								+ "As you see your new reflection, you let out a little gasp."
								+ " Brax has transformed you into his perfect vision of a lesser wolf-girl, and your yellow, predatory eyes open wide in shock as you feel the weight of "
									+(Main.getProperties().multiBreasts!=0?"three pairs of":"your")+" huge, E-cup tits that are now sitting on your chest."
								+ " Fur covers your wolf-like arms and legs, and as Brax steps up behind you, you see that it's the exact same bright white colour as his is."
								+ " Your face and the skin covering your torso are the only parts of your that haven't transformed into anthropomorphic wolf-like counterparts,"
									+ " and you experimentally twitch your pair of fluffy wolf-like ears, while swishing your long, shaggy tail back and forth against Brax's leg."
							+ "</p>");
					break;
				case NORMAL: case MAXIMUM:
					descriptionSB.append(
							"<p>"
							+ "As you see your new reflection, you let out a little gasp."
							+ " Brax has transformed you into his perfect vision of a greater wolf-girl, and your yellow, predatory eyes open wide in shock as you feel the weight of "
								+(Main.getProperties().multiBreasts!=0?"three pairs of":"your")+" huge, E-cup tits that are now sitting on your chest."
							+ " Fur covers your entire body, and as Brax steps up behind you, you see that it's the exact same bright white colour as his is."
							+ " Your hands, feet and face have all transformed into anthropomorphic wolf-like counterparts, and a pair of fluffy wolf-like ears and a long, shaggy tail finish off your new look."
						+ "</p>");
					break;
			}
				
					
			descriptionSB.append("<p>"
						+ "You see Brax's hand reaching around in the mirror, and with a pathetic little yelp, you quiver as he roughly grabs your crotch, "
						+ "[brax.speech(This little cunt belongs to me, understood?)]"
					+ "</p>"
					+ "<p>"
						+ " The final effect of the transformation makes itself known, and as Brax roughly gropes your body, something unlocks in your mind, and you find yourself wanting nothing more than to be dominated by your new master."
						+ " You lean back into the masculine wolf-morph, feeling his hot breath on your neck as you let out a soft moan."
						+ " Reaching down, you eagerly guide his paw-like hands over your new breasts, letting out little squeals of joy as he sinks his greedy fingers into your pillowy mounds of flesh."
					+ "</p>"
					+ "<p>"
						+ "You see Brax's reflection smiling greedily back at you, and he issues you a command as he steps back and starts to undress, "
						+ "[brax.speech(Get down and present yourself like the bitch you are.)]"
					+ "</p>");

			return descriptionSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Obey", "The arousing liquid you've just been forced to drink is forcing you to obey, and you eagerly fall down on all fours so that Brax can fuck you, doggy-style.",
						false, false,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(Main.game.getBrax(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
						AFTER_SUBMISSIVE_SEX,
						"<p>"
							+ "You obediently do as Brax commands and drop down on all fours right there in the middle of his office."
							+ (Main.game.getPlayer().getTailType() == TailType.LYCAN
								?" You can't help but shake your ass at the dominant wolf-boy, and you playfully flick your wolf-like tail back and forth, making pitiful little whining noises as you eagerly plead for Brax's thick cock."
								:" You can't help but shake your ass at the dominant wolf-boy, and you turn your head and make a show of biting your lip, making pitiful little whining noises as you eagerly plead for Brax's thick cock.")
						+ "</p>"
						+ "<p>"
							+ "Brax stands behind you, grinning, and you know that it's only going to be a matter of seconds before he fills you with his alpha cock."
						+ "</p>");
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_SUBMISSIVE_SEX = new DialogueNodeOld("Brax is done", "Brax has finished having his fun with you.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 30;
		}

		@Override
		public String getContent() {
			return "<p>"
					+ "[brax.speech(You're a pretty good fuck, slut!)]"
					+" Brax grunts, releasing his grip on your hips as you collapse to the floor, totally worn out from his dominant treatment of you."
					+ "</p>"
					+ "<p>"
					+ "With a satisfied sigh, you feel your eyelids closing, and the last thing you hear before passing out is Brax laughing down at the mess he's made of you."
					+ "</p>"
					+ "<p>"
					+ "..."
					+ "</p>"
					+ "<p>"
					+ "With a soft moan, you slowly open your eyes, the memory of Brax's rough fucking filling your groggy mind."
					+ " After a few moments, your head starts to clear, and you see that you've been unceremoniously dumped out the back of the Enforcer HQ."
					+ "</p>"
					+(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.braxBeaten)
						?"<p>"
							+ "Although you've been thrown out without so much as a goodbye, you feel a big smile spreading across your face."
							+ " You found out where Arthur is, and what's more, you got to have a good fuck with that hunk of a wolf-boy."
							+ "</p>"
							+ "<p>"
							+ "As you walk out from behind the Enforcer HQ, you see that an open crate, full of spare uniforms, is sitting outside a side door."
							+ " The crate is too big to fit through the doorway, and the person in charge of bringing them inside isn't anywhere to be seen."
							+ " Deciding that you want a little memento of your time with Brax, <b>you take a spare uniform, and add it to your inventory as you continue on your way</b>."
							+ "</p>"
						:"<p>"
							+ "Although you've been fucked and thrown out without so much as a goodbye, you consider that things could always be worse."
							+ " Setting off, you try to look on the bright side."
							+ " After all, at least you weren't enslaved or anything crazy like that..."
							+ "</p>");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Carry on", "Get up and carry on your way.") {
					@Override
					public void effects() {
						if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.braxBeaten)) {
							((Brax) Main.game.getBrax()).setBraxsPostQuestStatus();
						}
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
					}
				};
				
			} else {
				return null;
			}
		}
	};

	public static final DialogueNodeOld AFTER_DOMINANT_SEX = new DialogueNodeOld("Brax collapses", "Brax collapses and you return to his office.", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 15;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Brax is completely exhausted, and collapses harmlessly to the floor as he passes out."
						+ " Letting out a triumphant laugh, you look down at your latest sexual conquest, smirking in satisfaction as you see the happy smile covering his unconscious face."
					+ "</p>"
					+ "<p>"
						+ "Taking one last look around Brax's office, you notice that there's a neatly-folded spare uniform lying on top of a cabinet."
						+ " Deciding that you want a little memento of your time with Brax, <b>you take the spare uniform, and add it to your inventory</b>, before heading downstairs and back outside."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Outside", "You find yourself back outside once more, but this time, with new knowledge of Arthur's location.") {
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.DOMINION), PlaceType.DOMINION_ENFORCER_HQ, true);
						((Brax) Main.game.getBrax()).setBraxsPostQuestStatus();
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	@Override
	public int getEscapeChance() {
		return 0;
	}
	
	public int getLootMoney() {
		return 2500;
	}
	
	public List<AbstractCoreItem> getLootItems() {
		return null;
	}
	
	@Override
	public Map<TFEssence, Integer> getLootEssenceDrops() {
		return Util.newHashMapOfValues(new Value<>(TFEssence.ARCANE, 8));
	}
	

	// Penetrations
	@Override
	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice) {
		if(Math.random()>0.3) {
			if(Sex.getSexManager() instanceof SMCowgirl){
				if(orifice == OrificeType.VAGINA) {
					if(penetrationType == PenetrationType.PENIS && characterPenetrated.equals(this)) {
						return UtilText.returnStringAtRandom(
								"You keep bouncing up and down, slamming [brax.name]'s [npc.penis+] in and out of your [pc.pussy+].",
								"With lewd little moans, you continue bouncing up and down on [brax.name]'s [npc.penis+].",
								"You feel [brax.name]'s [npc.penis+] lewdly spreading out your [pc.pussy+] as you ride him.",
								"You let out a gasp as you carry on spearing your [pc.pussy+] on [brax.name]'s [npc.penis+].");
					} else if(penetrationType == PenetrationType.TONGUE && characterPenetrated.isPlayer()) {
						return UtilText.returnStringAtRandom(
								"You hold the top of [brax.name]'s head, moaning softly as he carries on eating you out.",
								"With a little giggle, you grind your [pc.pussy+] down on [brax.name]'s wolf-like muzzle.",
								"You feel [brax.name]'s tongue eagerly lapping away at your [pc.pussy+].",
								"You sink down a little further onto [brax.name]'s face, letting out a delighted sigh as you feel his tongue spearing deep into your [pc.pussy+].");
					}
				}
				
				if(orifice == OrificeType.ANUS) {
					if(penetrationType == PenetrationType.PENIS && characterPenetrated.equals(this)) {
						return UtilText.returnStringAtRandom(
								"You keep bouncing up and down, slamming [brax.name]'s [npc.penis+] in and out of your [pc.asshole+].",
								"With lewd little moans, you continue bouncing up and down on [brax.name]'s [npc.penis+].",
								"You feel [brax.name]'s [npc.penis+] lewdly spreading out your [pc.asshole+] as you ride him.",
								"You let out a gasp as you carry on spearing your [pc.asshole+] on [brax.name]'s [npc.penis+].");
						
					} else if(characterPenetrated.isPlayer()) {
						return UtilText.returnStringAtRandom(
								"You hold the top of [brax.name]'s head, moaning softly as he carries on licking your [pc.asshole+].",
								"With a little giggle, you grind your [pc.asshole+] down on [brax.name]'s wolf-like muzzle.",
								"You feel [brax.name]'s tongue eagerly lapping away at your [pc.asshole+].",
								"You sink down a little further onto [brax.name]'s face, letting out a delighted sigh as you feel his tongue spearing deep into your [pc.asshole+].");
					}
				}
			}
			
			if(penetrationType == PenetrationType.PENIS && orifice == OrificeType.ANUS && characterPenetrated.equals(this)) {
				return UtilText.returnStringAtRandom(
						"You carry on slamming your [pc.penis+] into [brax.name]'s [npc.asshole+].",
						"Holding his hips, you carry on fucking [brax.name]'s [npc.asshole+].",
						"You feel [brax.name]'s [npc.asshole+] lewdly spreading out around your [pc.penis+] as you thrust into him.",
						"[brax.name] gasps and groans as you carry on spearing your [pc.penis+] into his [npc.asshole+].");
			}
		}
		
		return super.getPenetrationDescription(initialPenetration, characterPenetrating, penetrationType, characterPenetrated, orifice);
	}
	
}
