package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.markings.Tattoo;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
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
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.2.7
 * @author Innoxia
 */
public class Kate extends NPC {

	private static final long serialVersionUID = 1L;

	public Kate() {
		this(false);
	}
	
	public Kate(boolean isImported) {
		super(new NameTriplet("Kate"), "Kate is a demon who owns the beauty salon 'Succubi's Secrets'."
				+ " Despite being incredibly good at what she does, she's exceedingly lazy, and prefers to keep the exterior of her shop looking run-down so as to scare off potential customers.",
				37, Month.SEPTEMBER, 9,
				10, Gender.F_V_B_FEMALE, RacialBody.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_KATES_SHOP, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			try {
				Tattoo tat = new Tattoo(
						TattooType.getTattooTypeFromId("innoxia_heartWomb_heart_womb"),
						Colour.CLOTHING_PINK,
						Colour.CLOTHING_PINK_LIGHT,
						Colour.CLOTHING_PURPLE,
						true,
						new TattooWriting(
								"Breed me!",
								Colour.CLOTHING_PINK_LIGHT,
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
							Colour.CLOTHING_PURPLE,
							Colour.CLOTHING_PINK,
							Colour.CLOTHING_PINK_LIGHT,
							false,
							null,
							null));
				
				this.addTattoo(InventorySlot.TORSO_UNDER,
						new Tattoo(
							TattooType.TRIBAL,
							Colour.CLOTHING_BLACK,
							null,
							null,
							false,
							new TattooWriting(
									"Don't pull out!",
									Colour.CLOTHING_BLACK,
									false),
							null));
				
			} catch(Exception ex) {
			}
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_RED), true);
			this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
			this.setHairStyle(HairStyle.SIDECUT);
			this.setSkinCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PINK), true);
			
			this.setHornType(HornType.CURLED);
			this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_DARK_GREY), true);

			this.setLegType(LegType.DEMON_COMMON);
			
			this.setBreastSize(CupSize.F.getMeasurement());
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			
			this.setAssVirgin(false);
			this.setNippleVirgin(false);
			this.setVaginaVirgin(false);
			this.setFaceVirgin(false);
			this.setPenisVirgin(false);
			
			this.setPiercedEar(true);
			
			this.addFetish(Fetish.FETISH_SUBMISSIVE);
			this.addFetish(Fetish.FETISH_PREGNANCY);
	
			this.setMoney(10);

			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MICRO_SKIRT_BELTED, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_WOMENS_LEATHER_JACKET, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_FISHNET_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
			
			dailyReset();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setWingSize(WingSize.ONE_SMALL.getValue());
		
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_RED), true);
		this.setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
		this.setHairStyle(HairStyle.SIDECUT);

		this.setHornType(HornType.CURLED);
		this.setSkinCovering(new Covering(BodyCoveringType.HORN, Colour.HORN_DARK_GREY), true);

		this.setLegType(LegType.DEMON_COMMON);
		
		this.deleteAllEquippedClothing();

		this.setPiercedEar(true);
		
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_VSTRING, Colour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_MICRO_SKIRT_BELTED, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_PINK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_OVER_WOMENS_LEATHER_JACKET, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.SOCK_FISHNET_STOCKINGS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_GOLD, false), true, this);
		
		if(this.getTattooInSlot(InventorySlot.GROIN)==null) {
			try {
				Tattoo tat = new Tattoo(
						TattooType.getTattooTypeFromId("innoxia_heartWomb_heart_womb"),
						Colour.CLOTHING_PINK,
						Colour.CLOTHING_PINK_LIGHT,
						Colour.CLOTHING_PURPLE,
						true,
						new TattooWriting(
								"Breed me!",
								Colour.CLOTHING_PINK_LIGHT,
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
							Colour.CLOTHING_PURPLE,
							Colour.CLOTHING_PINK,
							Colour.CLOTHING_PINK_LIGHT,
							false,
							null,
							null));
				
				this.addTattoo(InventorySlot.TORSO_UNDER,
						new Tattoo(
							TattooType.TRIBAL,
							Colour.CLOTHING_BLACK,
							null,
							null,
							false,
							new TattooWriting(
									"Don't pull out!",
									Colour.CLOTHING_BLACK,
									false),
							null));
				
			} catch(Exception ex) {
			}
		}

		dailyReset();
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public int getAppearsAsAge() {
		return 28;
	}

	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		
		List<AbstractClothing> clothingToSell = new ArrayList<>();
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getItemTags().contains(ItemTag.SOLD_BY_KATE)) {
				clothingToSell.add(AbstractClothingType.generateClothing(clothing, false));
			}
		}
		
		addEnchantedClothing(clothingToSell);
		
		for(AbstractClothing c : clothingToSell) {
			this.addClothing(c, false);
		}
	}
	
	/**
	 * Adds four uncommon clothing items to the list, and two rare items.
	 */
	private static void addEnchantedClothing(List<AbstractClothing> clothingList) {
		List<AbstractClothingType> typesToAdd = new ArrayList<>();
		for(int i=0;i<6;i++) {
			typesToAdd.add(Util.randomItemFrom(clothingList).getClothingType());
		}
		
		for(int i=0; i<typesToAdd.size(); i++) {
			if(i>=typesToAdd.size()-2) {
				clothingList.add(AbstractClothingType.generateRareClothing(typesToAdd.get(i)));
			} else {
				clothingList.add(AbstractClothingType.generateClothingWithEnchantment(typesToAdd.get(i)));
			}
		}

		for(AbstractClothing c : clothingList) {
			c.setEnchantmentKnown(true);
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
	public String getTraderDescription() {
		return "";
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		return item instanceof AbstractClothing;
	}

	@Override
	public void endSex() {
		setPenisType(PenisType.NONE);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Step back", "Step back and allow Kate to recover.", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
					+ "Quickly sorting your own clothes back into position, you watch as Kate does the same."
					+ " Standing up, she wipes herself clean with a tissue she's produced from somewhere, before flattening down her mini skirt and turning to smile at you."
					+ " She seems to have got her lust fully under control by now, and as she speaks, she sounds almost embarrassed of what just happened, "
					+ UtilText.parseSpeech("Ehhh, thanks for helping me out there... You know, it's pretty hard for us demons sometimes... Anyway! What are you even doing in here?"
							+ " Weren't you deterred by the boarded-up windows and stuff?",
						Main.game.getKate())
					+ "</p>"
					+ "<p>"
					+ UtilText.parsePlayerSpeech("So you're aware of how it appears to customers?")
					+ " you ask as you finally get your clothing back in order."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Well, yeah I'm aware! You know, the owners of this whole Arcade keep threatening me with legal action, saying I have a 'responsibility' to keep the area looking nice."
							+ " As if! As long as I display an 'open for business' sign, I'm following all the terms of my contract! You know what happened when I opened this place?! Thirty. Six. Customers. All in one day. Eugh!"
							+ " As the last one of those demanding know-it-alls left, I followed them outside, boarded up the windows, and threw paint stripper all over the sign. One day's hard work is enough for anyone...",
							Main.game.getKate())
					+ "</p>"
					+ "<p>"
					+ "As she's been speaking, she's started gathering items from the shelves on the other side of the room, stacking them up on a little metal trolley that's been sitting nearby."
					+ " Looking back at you, she makes a satisfied little humming noise before making her way back over to the leather chair you just fucked her on, pulling the trolley behind her."
					+ "</p>"
					+ "<p>"
					+ UtilText.parseSpeech("Well, I suppose I don't mind one customer every now and then. I could use the cash after all,",
						Main.game.getKate())
					+" she says, motioning for you to come and sit down, "
					+ UtilText.parseSpeech("This is the most comfortable seat, by the way, and hey, I've even warmed it up for you!",
						Main.game.getKate())
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
	public static final DialogueNodeOld AFTER_SEX_REPEATED = new DialogueNodeOld("Step back", "Step back and allow Kate to recover.", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return  "<p>"
					+ "Quickly sorting your own clothes back into position, you watch as Kate does the same."
					+ " Standing up, she wipes herself clean with a tissue she's produced from somewhere, before flattening down her mini skirt and turning to smile at you."
					+ " She seems to have got her lust fully under control by now, and as she speaks, she sounds almost embarrassed of what just happened, "
					+ UtilText.parseSpeech("Ehhh, thanks for helping me out there... You know, it's pretty hard for us demons sometimes... Anyway! You need any more of my services?",
						Main.game.getKate())
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
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()){
				return Main.game.getPlayer().useItem(item, target, false);
						
			// Player uses item on NPC:
			}else{
				if(item.getItemType().equals(ItemType.VIXENS_VIRILITY)) {
					Main.game.getPlayer().useItem(item, target, false);
						return "<p>"
							+ "Producing a Vixen's Virility pill from your inventory, you pop it out of its plastic wrapper before pushing it into Kate's mouth."
							+ " She giggles as she happily swallows the little pink pill, knowing that it's going to make her womb far more fertile."
						+ "</p>";
				} else {
					return "<p>"
						+ "You start to pull "+item.getItemType().getDeterminer()+" "+item.getName()+" out from your inventory, but Kate quickly kicks your hand away and frowns at you."
					+ "</p>";
				}
			}
			
		// NPC is using an item:
		} else {
			return Sex.getActivePartner().useItem(item, target, false);
		}
	}
	
	
	
	
	
	@Override
	public String getCondomEquipEffects(GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex()) {
			if(!target.isPlayer()) {
				return "<p>"
							+ "Holding out a condom to [npc.name], you force [npc.herHim] to take it and put it on."
							+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] whines at you,"
							+ " [npc.speech(Do I really have to? It feels so much better without one...)]"
						+ "</p>";
			} else {
				Main.game.getPlayer().unequipClothingIntoVoid(Main.game.getPlayer().getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, equipper);
				return "<p>"
							+ "As you pull out a condom, a worried frown flashes across Kate's face, "
							+ UtilText.parseSpeech("Oh! Erm, let me put that on for you!", Main.game.getKate())
							+"<br/>"
							+ "Before you can react, Kate snatches the condom out of your hands, and with a devious smile, uses her sharp little canines to bite a big hole straight through the centre."
							+ " She laughs at your shocked reaction, "
							+ UtilText.parseSpeech("It's no fun if I don't get any cum!", Main.game.getKate())
						+ "</p>";
			}
		}
		return AbstractClothingType.getEquipDescriptions(target, equipper, rough,
				"You tear open the packet and roll the condom down the length of your [pc.penis].",
				"You tear open the packet and roll the condom down the length of [npc.namePos] [npc.penis].",
				"You tear open the packet and forcefully roll the condom down the length [npc.namePos] [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of [npc.her] [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of your [pc.penis].",
				"[npc.Name] tears open the packet and forcefully rolls the condom down the length of your [pc.penis].", null, null);
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
