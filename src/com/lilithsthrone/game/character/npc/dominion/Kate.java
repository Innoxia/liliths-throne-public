package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.3.1
 * @author Innoxia
 */
public class Kate extends NPC {

	public Kate() {
		this(false);
	}
	
	public Kate(boolean isImported) {
		super(isImported, new NameTriplet("Kate"), "Lasiellemartu",
				"Kate is a demon who owns the beauty salon 'Succubi's Secrets'."
						+ " Despite being incredibly good at what she does, she's exceedingly lazy, and prefers to keep the exterior of her shop looking run-down so as to scare off potential customers.",
				37, Month.SEPTEMBER, 9,
				10, Gender.F_V_B_FEMALE, Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_KATES_SHOP, true);
		
		if(!isImported) {
			dailyUpdate();
		}
	}
	
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);

		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceDifferenceToAppearAsAge(28);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
			this.setTailGirth(PenetrationGirth.TWO_NARROW);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_ARCANE_TATTOOIST);
		this.addSpecialPerk(Perk.SPECIAL_MEGA_SLUT);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 0),
						new Value<>(PerkCategory.LUST, 5),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		
		// Persona:

		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_BEAUTICIAN);
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_PREGNANCY);
		}
		
		// Body:
		this.setLegType(LegType.DEMON_COMMON);
		this.setHornType(HornType.CURLED);
		this.setWingType(WingType.DEMON_COMMON);
		this.setWingSize(WingSize.ONE_SMALL.getValue());
		this.setTailType(TailType.DEMON_COMMON);
		this.setTailGirth(PenetrationGirth.TWO_NARROW);

		if(this.getTattooInSlot(InventorySlot.GROIN)==null) {
			try {
				Tattoo tat = new Tattoo(
						TattooType.getTattooTypeFromId("innoxia_heartWomb_heart_womb"),
						PresetColour.CLOTHING_PINK,
						PresetColour.CLOTHING_PINK_LIGHT,
						PresetColour.CLOTHING_PURPLE,
						true,
						new TattooWriting(
								"Breed me!",
								PresetColour.CLOTHING_PINK_LIGHT,
								true,
								TattooWritingStyle.ITALICISED),
						null);
				
				for(int i=0; i<10; i++) {
					tat.addEffect(new ItemEffect(ItemEffectType.TATTOO, TFModifier.CLOTHING_ATTRIBUTE, TFModifier.FERTILITY, TFPotency.MAJOR_BOOST, 0));
				}
				
				this.addTattoo(InventorySlot.GROIN, tat);
				
				this.addTattoo(InventorySlot.TORSO_OVER,
						new Tattoo(
							TattooType.BUTTERFLIES,
							PresetColour.CLOTHING_PURPLE,
							PresetColour.CLOTHING_PINK,
							PresetColour.CLOTHING_PINK_LIGHT,
							false,
							null,
							null));
				
				this.addTattoo(InventorySlot.TORSO_UNDER,
						new Tattoo(
							TattooType.TRIBAL,
							PresetColour.CLOTHING_BLACK,
							null,
							null,
							false,
							new TattooWriting(
									"Don't pull out!",
									PresetColour.CLOTHING_BLACK,
									false),
							null));
				
			} catch(Exception ex) {
			}
		}

		// Core:
		this.setAgeAppearanceDifferenceToAppearAsAge(28);
		this.setHeight(180);
		this.setFemininity(85);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, PresetColour.EYE_GREEN));
		this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, PresetColour.SKIN_PINK), true);
		
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, PresetColour.COVERING_DARK_GREY), false);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, PresetColour.COVERING_RED), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.SIDECUT);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_DEMON, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.ZERO_NONE);
		this.setAssHair(BodyHair.ZERO_NONE);
		this.setPubicHair(BodyHair.ZERO_NONE);
		this.setFacialHair(BodyHair.ZERO_NONE);

		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_PINK));
		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_PINK));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_RED));
		
		// Face:
		this.setFaceVirgin(false);
		this.setLipSize(LipSize.TWO_FULL);
		this.setFaceCapacity(Capacity.FIVE_ROOMY, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(false);
		this.setBreastSize(CupSize.F.getMeasurement());
		this.setBreastShape(BreastShape.ROUND);
		this.setNippleSize(NippleSize.TWO_BIG);
		this.setAreolaeSize(AreolaeSize.TWO_BIG);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(false);
		this.setAssBleached(true);
		this.setAssSize(AssSize.FOUR_LARGE);
		this.setHipSize(HipSize.THREE_GIRLY);
		this.setAssCapacity(Capacity.TWO_TIGHT, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setAssPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		// Anus modifiers
		
		// Penis:
		// (For when she grows one)
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(15);
//		this.setInternalTesticles(true); Use player preferences
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(150);
		this.fillCumToMaxStorage();
		
		// Vagina:
		this.setVaginaVirgin(false);
		this.setVaginaClitorisSize(ClitorisSize.ZERO_AVERAGE);
		this.setVaginaLabiaSize(LabiaSize.TWO_AVERAGE);
		this.setVaginaSquirter(true);
		this.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT, true);
		this.setVaginaWetness(Wetness.SIX_SOPPING_WET);
		this.setVaginaElasticity(OrificeElasticity.SEVEN_ELASTIC.getValue());
		this.setVaginaPlasticity(OrificePlasticity.ONE_SPRINGY.getValue());
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {

		this.unequipAllClothingIntoVoid(true, true);

		this.setMoney(10);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_vstring", PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_micro_skirt_belted", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, PresetColour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torsoOver_womens_leather_jacket", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_sock_fishnets", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_foot_heels", PresetColour.CLOTHING_BLACK, false), true, this);

		this.setPiercedEar(true);
		this.setPiercedNavel(true);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_ear_ring", PresetColour.CLOTHING_GOLD, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_piercing_gemstone_barbell", PresetColour.CLOTHING_GOLD, false), InventorySlot.PIERCING_STOMACH, true, this);

	}
	
	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);

		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_KATE)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addItem(Main.game.getItemGen().generateItem(item), !item.isConsumedOnUse()?1:(6+Util.random.nextInt(12)), false, false);
			}
		}
		
		List<AbstractClothing> clothingToSell = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_KATE)
					&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				clothingToSell.add(Main.game.getItemGen().generateClothing(clothing, false));
			}
		}

		for(AbstractClothing c : clothingToSell) {
			this.addClothing(c, 2+Util.random.nextInt(5), false, false);
		}
		
		for(AbstractClothing c : Main.game.getCharacterUtils().generateEnchantedClothingForTrader(this, clothingToSell, 6, 2)) {
			this.addClothing(c, false);
		}
	}
	
	@Override
	public void turnUpdate() {
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isExtendedWorkTime()) {
				this.returnToHome();
			} else {
				this.setLocation(WorldType.EMPTY, PlaceType.GENERIC_HOLDING_CELL, false);
			}
		}
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}

	@Override
	public String getTraderDescription() {
		return "";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return (item instanceof AbstractClothing)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				&& !item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY);
	}

	@Override
	public void endSex() {
		setPenisType(PenisType.NONE);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Step back", "Step back and allow Kate to recover.", true, true) {
		
		@Override
		public String getContent() {
			return "<p>"
					+ "Quickly sorting your own clothes back into position, you watch as Kate does the same."
					+ " Standing up, she wipes herself clean with a tissue she's produced from somewhere, before flattening down her mini skirt and turning to smile at you."
					+ " She seems to have got her lust fully under control by now, and as she speaks, she sounds almost embarrassed of what just happened, "
					+ UtilText.parseSpeech("Mmm, thanks for helping me out there... You know, it's pretty hard for us demons sometimes... Anyway! What are you even doing in here?"
							+ " Weren't you deterred by the boarded-up windows and stuff?",
						Main.game.getNpc(Kate.class))
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("So you're aware of how it appears to customers?")
					+ " you ask as you finally get your clothing back in order."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Well, yeah I'm aware! You know, the owners of this whole Arcade keep threatening me with legal action, saying I have a 'responsibility' to keep the area looking nice."
							+ " As if! As long as I display an 'open for business' sign, I'm following all the terms of my contract! You know what happened when I opened this place?! Thirty. Six. Customers. All in one day. Eugh!"
							+ " As the last one of those demanding know-it-alls left, I followed them outside, boarded up the windows, and threw paint stripper all over the sign. One day's hard work is enough for anyone...",
							Main.game.getNpc(Kate.class))
					+ "</p>"
					+ "<p>"
					+ "As she's been speaking, she's started gathering items from the shelves on the other side of the room, stacking them up on a little metal trolley that's been sitting nearby."
					+ " Looking back at you, she makes a satisfied little humming noise before making her way back over to the leather chair you just fucked her on, pulling the trolley behind her."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Well, I suppose I don't mind one customer every now and then. I could use the cash after all,",
						Main.game.getNpc(Kate.class))
					+" she says, motioning for you to come and sit down, "
					+ UtilText.parseSpeech("This is the most comfortable seat, by the way, and hey, I've even warmed it up for you!",
						Main.game.getNpc(Kate.class))
					+ "</p>"
					+ "<p>"
					+ "Kate wipes down the seat, and, seeing that it looks clean enough, you do as she instructs and sink down into the chair."
					+ " You feel some of Kate's residual warmth still clinging to the seat's padded leather backing, and you smile up at her as she hands you a little brochure."
					+ " Flicking through, you see that it contains all the services she's capable of, and as you read, Kate sinks down onto one of the chairs next to you."
					+ " Within a few seconds, her snores start to fill the empty shop once more..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.kateIntroduced);
					}
				};
			} else {
				return null;
			}
		}
	};
	public static final DialogueNode AFTER_SEX_REPEATED = new DialogueNode("Step back", "Step back and allow Kate to recover.", true, true) {
		
		@Override
		public String getContent() {
			return  "<p>"
					+ "Quickly sorting your own clothes back into position, you watch as Kate does the same."
					+ " Standing up, she wipes herself clean with a tissue she's produced from somewhere, before flattening down her mini skirt and turning to smile at you."
					+ " She seems to have got her lust fully under control by now, and as she speaks, she sounds almost embarrassed of what just happened, "
					+ UtilText.parseSpeech("Mmm, thanks for helping me out there... You know, it's pretty hard for us demons sometimes... Anyway! You need any more of my services?",
						Main.game.getNpc(Kate.class))
					+ "</p>"
					+ "<p>"
					+ "Kate wipes down the seat she's just vacated, and, seeing that it looks clean enough, you take her offer of a seat and sink down into the chair."
					+ " You feel some of Kate's residual warmth still clinging to the padded leather backing, and you smile up at her as she hands you a little brochure."
					+ " Flicking through, you see that it contains all the services she's capable of, and as you read, Kate sinks down onto one of the chairs next to you."
					+ " Within a few seconds, her snores start to fill the empty shop once more..."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN);
			} else {
				return null;
			}
		}
	};
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item,  GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer()) {
			if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				String useDesc = itemOwner.useItem(item, target, false, true);
				return new Value<>(true,
						"<p>"
							+ "Producing a "+item.getName(false, false)+" from your inventory, you pop it out of its plastic wrapper before pushing it into Kate's mouth."
							+ " She giggles as she happily swallows the little "+item.getColour(0).getName()+" pill, knowing that it's going to make her womb far more fertile."
						+ "</p>"
						+ useDesc);
				
			} else if(item.getItemType()==ItemType.PREGNANCY_TEST) {
				String useDesc = itemOwner.useItem(item, target, false, true);
				if(this.isPregnant()) {
					this.setCharacterReactedToPregnancy(user, true);
					String litterCount = Util.intToString(this.getPregnantLitter().getTotalLitterCount());
					return new Value<>(true,
							"<p>"
								+ "Producing "+item.getName(true, false)+" from your inventory, you pass it over Kate's tummy and take a look at the results..."
							+ "</p>"
							+ useDesc
							+"<p>"
								+ (this.isVisiblyPregnant()
									?"[kate.speechNoEffects(What were you expecting?)] Kate laughs, before rubbing her pregnant belly and winking at you. [kate.speech(You can already see I'm knocked up, can't you?)]"
									:"[kate.speechNoEffects(~Ooh!~ I'm pregnant!)]"
											+ (this.getPregnantLitter().getTotalLitterCount()>2
													?" Kate exclaims, before rubbing her belly and biting her lip. [kate.speechNoEffects(I've got "+litterCount+" kids in here? ~Mmm!~ My tummy's gonna be so big...)]"
													:" Kate moans, before rubbing her belly and winking at you. [kate.speech(I'm gonna have a big, round tummy soon!)]"))
							+ "</p>");
					
				} else {
					return new Value<>(true,
							"<p>"
									+ "Producing "+item.getName(true, false)+" from your inventory, you pass it over Kate's tummy and take a look at the results..."
							+ "</p>"
							+ useDesc
							+"<p>"
								+ (this.hasStatusEffect(StatusEffect.PREGNANT_0)
									?"[kate.speechNoEffects(~Aww!~ I'm not pregnant!)] Kate whines, before rubbing her belly and pouting at you. [kate.speech(Come on, [pc.name], there's plenty of time for you to change that!)]"
									:"[kate.speechNoEffects(What were you expecting? Of course I'm not going to be pregnant!)] Kate laughs, before rubbing her belly and biting her lip. [kate.speech(Although there's plenty of time for you to change that...)]")
							+ "</p>");
				}
				
			} else {
				return new Value<>(false,
						"<p>"
							+ "You start to pull "+item.getItemType().getDeterminer()+" "+item.getName()+" out from your inventory, but Kate quickly kicks your hand away and frowns at you."
						+ "</p>");
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex()) {
			if(!target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				return "<p>"
							+ "Holding out a condom to [kate.name], you force [kate.herHim] to take it and put it on."
							+ " Quickly ripping it out of its little foil wrapper, [kate.she] rolls it down the length of [kate.her] [kate.cock+] as [kate.she] whines at you,"
							+ " [kate.speech(Do I really have to? It feels so much better without one...)]"
						+ "</p>";
			} else {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name] [npc.verb(direct)] [npc.her] spinneret at [npc2.namePos] [npc2.cock], but, sensing what [npc.sheIs] about to do, Kate slaps it away and laughs,"
							+ " [kate.speech(No way! It's no fun if I don't get any cum!)]");
				}
				return "<p>"
							+ "As you pull out a condom, a worried frown flashes across Kate's face, "
							+ "[kate.speech(Oh! Erm, let me put that on for you!)]"
							+"<br/>"
							+ "Before you can react, Kate snatches the condom out of your hands, and with a devious smile, uses her sharp little canines to bite a big hole straight through the centre."
							+ " She laughs at your shocked reaction, "
							+ "[kate.speech(It's no fun if I don't get any cum!)]"
						+ "</p>";
			}
		}
		return null;
	}
	
	
	// Dirty talk:

	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	public String getDirtyTalkNoPenetration(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();

		speech.add("Come on! Fuck me already!");
		speech.add("Please, let's get started!");
		speech.add("My little pussy needs you so bad!");
		
		return speech.get(Util.random.nextInt(speech.size()));
	}
	
	/**
	 * @return A <b>non-formatted</b> String of this NPCs speech related to no ongoing penetration.
	 */
	public String getPlayerDirtyTalkNoPenetration(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		speech.add("You're so hot!");
		speech.add("What a cute little demon you are!");
		
		return speech.get(Util.random.nextInt(speech.size()));
	}
}
