package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.6
 * @author Innoxia
 */
public class Ralph extends NPC {

	private static final long serialVersionUID = 1L;

	private static List<AbstractItemType> itemsForSale = Util.newArrayListOfValues(
			ItemType.FETISH_UNREFINED,
			ItemType.ADDICTION_REMOVAL,
			ItemType.MOO_MILKER_EMPTY,
			ItemType.VIXENS_VIRILITY,
			ItemType.PROMISCUITY_PILL,
			ItemType.MOTHERS_MILK,
			ItemType.PREGNANCY_TEST);
	
	static {
		for(AbstractItemType itemType : ItemType.allItems) {
			if(!itemType.getItemTags().contains(ItemTag.NOT_FOR_SALE) && (itemType.getItemTags().contains(ItemTag.ATTRIBUTE_TF_ITEM) || itemType.getItemTags().contains(ItemTag.RACIAL_TF_ITEM))) {
				itemsForSale.add(itemType);
			}
		}
	}
	
	public Ralph() {
		this(false);
	}
	
	public Ralph(boolean isImported) {
		super(new NameTriplet("Ralph"), "Ralph is the owner of the shop 'Ralph's Snacks'. There's an air of confidence in the way he holds himself, and he behaves in a professional manner at all times.",
				34, Month.MAY, 17,
				10, Gender.M_P_MALE, RacialBody.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.LOW),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.AVERAGE)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, Colour.EYE_BROWN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, Colour.COVERING_BLACK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_EBONY), true);
			this.setHairStyle(HairStyle.LOOSE);
	
			this.setPenisVirgin(false);
			
			this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
			
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.LEG_JEANS, Colour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_PINK_LIGHT, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_MENS_WATCH, Colour.CLOTHING_GOLD, false), true, this);
			
			dailyReset();
		}
		
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	/**
	 * Discount is active for three days after earning it.
	 */
	public boolean isDiscountActive(){
		if(Main.game.getDialogueFlags().ralphDiscountStartTime == -1 || Main.game.getDialogueFlags().ralphDiscount <= 0) {
			return false;
		} else {
			return (Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) < (60*24*3);
		}
	}

	@Override
	public void dailyReset() {
		clearNonEquippedInventory();
		
		for(int i=0;i<25;i++) {
			this.addItem(AbstractItemType.generateItem(ItemType.DYE_BRUSH), false);
		}
		
		for (AbstractItemType item : itemsForSale) {
			for (int i = 0; i < 6 + (Util.random.nextInt(12)); i++) {
				this.addItem(AbstractItemType.generateItem(item), false);
			}
		}
		
		Colour condomColour1 = ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().get(Util.random.nextInt(ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().size()));
		Colour condomColour2 = ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().get(Util.random.nextInt(ClothingType.PENIS_CONDOM.getAvailablePrimaryColours().size()));
		
		for (int i = 0; i < 6+(Util.random.nextInt(12)); i++) {
			this.addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, condomColour1, false), false);
		}
		for (int i = 0; i < 6+(Util.random.nextInt(12)); i++) {
			this.addClothing(AbstractClothingType.generateClothing(ClothingType.PENIS_CONDOM, condomColour2, false), false);
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
		
		if(Main.game.getDialogueFlags().ralphDiscountStartTime>0){
			
			StringBuilder descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
						+ "You look over at the counter to see Ralph smiling back at you. Sensing that you might need some assistance, he briskly walks over to where you're standing."
					+ "</p>"
					+ "<p>"
						+ "Although the memory of you submissively pleasuring Ralph's huge horse-cock is still fresh in both of your minds, he treats you with the utmost respect,"
						+ " and politely lists off the prices of the transformative consumables on display."
						+ " He reminds you that he's also interested in buying any transformative consumables that you're willing to sell."
						+ " As you turn back to look at the goods, Ralph tells you that he refreshes his stock every day."
					+ "</p>");
			
			if(isDiscountActive()){
				descriptionSB.append("<p>"
						+ "<b>Ralph is giving you a</b> <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b> <b>discount!</b>"
							+ "</p>");
			}else{
				descriptionSB.append("<p>"
						+ "Before he turns to walk back to the counter, he leans in and whispers in your ear, "
						+ UtilText.parseSpeech("If you're interested in earning a little discount again, just call me over.", Main.game.getRalph())
							+ "</p>");
			}
			
			return descriptionSB.toString();
			
		}else
			return "<p>"
						+ "You look over at the counter to see Ralph smiling back at you. Sensing that you might need some assistance, he briskly walks over to where you're standing."
					+ "</p>"
					+ "<p>"
						+ "After a brief greeting, you ask him about the transformative consumables on display. He politely informs you that they're all for sale, and lists off all the prices."
						+ " Sensing that you might not be a typical customer, he tell you that he's also interested in buying any transformative consumables that you're willing to sell."
						+ " As you turn back to look at the goods, Ralph tells you that he refreshes his stock every day."
					+ "</p>";
		
	}

	@Override
	public boolean isTrader() {
		return true;
	}

	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType().canBeSold()) {
				return true;
			}
		}
		if(item instanceof AbstractClothing) {
			return ((AbstractClothing)item).getClothingType().equals(ClothingType.PENIS_CONDOM);
		}
		
		return false;
	}

	@Override
	public void endSex() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			c.getDisplacedList().clear();
		}
	}
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Shopping", "Return to browsing the wares in Ralph's shop.", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return "<p>"
						+ "Ralph returns to running his shop, and you walk back over to the transformative consumables section, wondering if you should buy anything with your discount."
						+ " When he's sure that nobody else is watching, Ralph gazes lustfully at your body, and you're pretty sure that you could convince him to give you another \"discount\" any time you wanted it."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on browsing the wares in Ralph's shop.", RalphsSnacks.INTERIOR);
			} else {
				return null;
			}
		}
	};
	
	@Override
	public String getCondomEquipEffects(GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex() && !target.isPlayer()) {
			if(Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getRalph()).contains(SexAreaPenetration.PENIS)) {
				return "<p>"
						+ "You pull out a condom from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
							+ " He looks down at you before nodding and stepping back, sliding his huge cock free from your mouth."
							+ " You get a moment to catch your breath as Ralph tears opens the little foil package before rolling the condom down the length of his massive shaft."
							+ " Stepping forwards once more, he shoves his rubber-bound dick back down your throat, and you let out a muffled sigh of relief, happy that he did as you asked."
					+ "</p>";
			} else if(Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getRalph()).contains(SexAreaPenetration.PENIS)) {
				return "<p>"
							+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a condom."
								+ " Suppressing your moans, you turn back, holding out your hand as you ask him to put it on."
								+ " He lets out a disappointed sigh, but doesn't openly object as he pulls his cock free from your pussy, giving you a moment to breathe as he"
									+ " tears opens the little foil package before rolling the condom down the length of his massive shaft."
								+ " Once the condom is securely in place, he lines himself up and pushes forwards, burying his rubber-bound dick deep into your pussy."
						+ "</p>";
			} else if(Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getRalph()).contains(SexAreaPenetration.PENIS)) {
				return "<p>"
					+ "As Ralph carries on slamming his huge cock in and out of your ass, you fumble around in your inventory and produce a condom."
						+ " Suppressing your groans, you turn back, holding out your hand as you ask him to put it on."
						+ " He lets out a disappointed sigh, but doesn't openly object as he pulls his cock free from your rear entrance, giving you a moment to breathe as he"
							+ " tears opens the little foil package before rolling the condom down the length of his massive shaft."
						+ " Once the condom is securely in place, he lines himself up and pushes forwards, burying his rubber-bound dick deep into your waiting asshole."
					+ "</p>";
			} else {
				return "<p>"
					+ "Producing a condom from your inventory, you lean forwards, looking up at Ralph and asking him to put it on as you hold the little foil package up to him."
							+ " With a sigh, he takes the condom from you, and, tearing the package open, quickly rolls the condom down the length of his massive shaft."
							+ " You thank him for doing as you asked, and he replies that he's happy to respect your request."
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
	
	
	@Override
	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		if(Math.random()>0.3) {
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.VAGINA) {
				return UtilText.returnStringAtRandom(
						"Ralph carries on driving you into the counter-top as he fucks your "+Main.game.getPlayer().getVaginaName(true)+".",
						"You feel Ralph's strong grip on your hips as his "+Sex.getActivePartner().getPenisName(true)+" pounds away at your "+Main.game.getPlayer().getVaginaName(true)+".",
						"Ralph's "+Sex.getActivePartner().getPenisName(true)+" carries on slamming in and out of your greedy "+Main.game.getPlayer().getVaginaName(false)+".",
						"Your pussy lips spread around Ralph's "+Sex.getActivePartner().getPenisName(true)+" as he fucks you on the counter-top.");
			}
			
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.MOUTH) {
				return UtilText.returnStringAtRandom(
						"Slimy saliva drools down your chin as you carry on sucking Ralph's "+Sex.getActivePartner().getPenisName(true)+".",
						"You look down at the "+Sex.getActivePartner().getPenisName(true)+" sliding in and out of your mouth.",
						"Ralph's "+Sex.getActivePartner().getPenisName(true)+" pushes its way past your lips as you kneel beneath him.",
						"You bob your head back and forth as you carry on servicing Ralph's "+Sex.getActivePartner().getPenisName(true)+".");
			}
		}

		return super.getPenetrationDescription(initialPenetration, characterPenetrating, penetrationType, characterPenetrated, orifice);
	}
	
	// Vagina:
	
	@Override
	public String getStretchingDescription(GameCharacter partner, SexAreaPenetration penetrationType, SexAreaOrifice orifice) {
		switch(orifice) {
			case MOUTH:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(partner, penetrationType, orifice);
				} else {
					return formatStretching(UtilText.returnStringAtRandom(
							"You feel tears streaming down your face as you force the "+Sex.getActivePartner().getPenisName(true)+" down your throat.",
							"You aren't skilled enough at giving head to be able to comfortably handle Ralph's "+Sex.getActivePartner().getPenisName(true)+".",
							"You squirm and choke as you do your best to fit the "+Sex.getActivePartner().getPenisName(true)+" down your throat.",
							"You struggle to fit Ralph's "+Sex.getActivePartner().getPenisName(true)+" down your throat."));
				}
			case VAGINA:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(partner, penetrationType, orifice);
				} else {
					return formatStretching(UtilText.returnStringAtRandom(
							"You squirm about uncomfortably on the counter-top as Ralph's " +Sex.getActivePartner().getPenisName(true)+" struggles to fit in your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" "+Main.game.getPlayer().getVaginaName(false)+".",
							"Ralph's " +Sex.getActivePartner().getPenisName(true)+" painfully stretches you out as it rams its way in and out of your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" "+Main.game.getPlayer().getVaginaName(false)+".",
							"Ralph's "+Sex.getActivePartner().getPenisName(true)+" is too large for your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" "+Main.game.getPlayer().getVaginaName(false)+", and you let out an uncomfortable whine as it stretches you out.",
							"Your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" little "+Main.game.getPlayer().getVaginaName(false)+" struggles to accommodate the sheer size of Ralph's "+Sex.getActivePartner().getPenisName(true)+"."));
				}
			default:
				return super.getStretchingDescription(partner, penetrationType, orifice);
		}
	}
	
	@Override
	public String getStretchingFinishedDescription(SexAreaOrifice orifice) {
		switch(orifice) {
			case MOUTH:
				return formatStretching("The next time Ralph gently pushes forwards, you feel your throat instinctively opening up to accommodate his flared equine member,"
						+ " and with a muffled moan of delight, you realise that you're now able to comfortably suck Ralph's massive black horse-cock!");
			case VAGINA:
				return formatStretching("The next time Ralph slams forwards, you feel your pussy easily stretching out to accommodate his flared equine member, and with a squeal of delight,"
						+ " you realise that you're now able to comfortably fit Ralph's massive black horse-cock in your "+Main.game.getPlayer().getVaginaName(true)+"!");
			default:
				return super.getStretchingFinishedDescription(orifice);
		}
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
				if(item.getItemType().equals(ItemType.VIXENS_VIRILITY)) {
					Main.game.getPlayer().useItem(item, target, false);
					if(Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getRalph()).contains(SexAreaPenetration.PENIS))
						return "<p>"
								+ "You pull out a Vixen's Virility pill from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
									+ " He looks down at you, letting out a little laugh and shrugging his shoulders as he sees what you're trying to give him."
									+ " Quickly popping the pill out of its plastic container, he swallows it, and you let out a happy, although somewhat muffled, giggle, knowing that his sperm just got a lot more potent."
							+ "</p>";
					else if(Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getRalph()).contains(SexAreaPenetration.PENIS))
						return "<p>"
									+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a Vixen's Virility pill."
										+ " Suppressing your moans, you turn back, holding out your hand as you ask him to swallow it."
										+ " He lets out a little laugh as he sees what you're giving him, and, quickly popping the pill out of its plastic container and swallowing it,"
										+ " he takes a moment to say a few words before continuing to fuck you, "
										+ (Main.game.getRalph().isWearingCondom()
											?"You do realise I'm wearing a condom, right?"
											:(Main.game.getPlayer().isVisiblyPregnant()
													?"Uhh, you're already pregnant, but sure, why not?"
													:UtilText.parseSpeech("Hoping to get pregnant, huh? Well, I'm more than happy to help with that!", Main.game.getRalph())))
								+ "</p>";
					else if(Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getRalph()).contains(SexAreaPenetration.PENIS))
						return "<p>"
									+ "As Ralph carries on slamming his huge cock in and out of your ass, you fumble around in your inventory and produce a Vixen's Virility pill."
										+ " Suppressing your groans, you turn back, holding out your hand as you ask him to swallow it."
										+ " He lets out a little laugh as he sees what you're giving him, and, quickly popping the pill out of its plastic container and swallowing it,"
										+ " he takes a moment to say a few words before continuing to fuck you, "
										+ UtilText.parseSpeech("You do know you can't get pregnant from this, right?", Main.game.getRalph())
									+ "</p>";
					else
						return "<p>"
							+ "Producing a Vixen's Virility pill from your inventory, you lean forwards, looking up at Ralph and asking him to swallow it as you hold it up to him."
									+ " He looks down at you, letting out a little laugh and shrugging his shoulders as he sees what you're trying to give him."
									+ " Quickly popping the pill out of its plastic container, he swallows it, and you let out a happy giggle, knowing that his sperm just got a lot more potent."
						+ "</p>";
						
				} else {
						if(!Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getRalph()).isEmpty())
							return "<p>"
									+ "You pull out "+item.getItemType().getDeterminer()+" "+item.getName()+" from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
										+ " He looks down at you before making a dismissive grunt and stepping forwards slightly, ramming his cock that little bit further down your throat as you put the "+item.getName()+" back in your inventory."
								+ "</p>";
						else if(!Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getRalph()).isEmpty())
							return "<p>"
										+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a "+item.getItemType().getDeterminer()+" "+item.getName()+"."
											+ " Suppressing your moans, you ask him if he'd like to "+item.getItemType().getUseName()+" it, but he simply makes a dismissive grunt and carries on fucking you."
											+ " You start sighing and crying out in pleasure once more as you put the "+item.getName()+" back in your inventory."
									+ "</p>";
						else if(!Sex.getContactingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getRalph()).isEmpty())
							return "<p>"
										+ "As Ralph carries on slamming his huge cock in and out of your ass, you fumble around in your inventory and produce a "+item.getItemType().getDeterminer()+" "+item.getName()+"."
										+ " Suppressing your groans, you ask him if he'd like to "+item.getItemType().getUseName()+" it, but he simply makes a dismissive grunt and carries on fucking you."
										+ " You start sighing and crying out in pleasure once more as you put the "+item.getName()+" back in your inventory."
									+ "</p>";
						else
							return "<p>"
								+ "You ask Ralph if he'd like to use "+item.getItemType().getDeterminer()+" "+item.getName()+" that you've got, but he simply makes a dismissive grunt and tells you he's not interested."
										+ " With a sigh, you put the "+item.getName()+" back in your inventory."
							+ "</p>";
				}
			}
			
		// NPC is using an item:
		}else{
			return Sex.getActivePartner().useItem(item, target, false);
		}
	}
	
}
