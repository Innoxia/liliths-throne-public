package com.base.game.character.npc.dominion;

import com.base.game.character.GameCharacter;
import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.effects.Fetish;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.places.dominion.shoppingArcade.RalphsSnacks;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemType;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.WorldType;
import com.base.world.places.ShoppingArcade;

/**
 * @since 0.1.0
 * @version 0.1.82
 * @author Innoxia
 */
public class Ralph extends NPC {

	private static final long serialVersionUID = 1L;

	private static StringBuilder descriptionSB = new StringBuilder();

	private ItemType[] itemsForSale = new ItemType[] {
			ItemType.RACE_INGREDIENT_CAT_MORPH,
			ItemType.RACE_INGREDIENT_DOG_MORPH,
			ItemType.RACE_INGREDIENT_HARPY,
			ItemType.RACE_INGREDIENT_HORSE_MORPH,
			ItemType.RACE_INGREDIENT_WOLF_MORPH,
			ItemType.RACE_INGREDIENT_HUMAN,
			ItemType.RACE_INGREDIENT_DEMON,

			ItemType.STR_INGREDIENT_EQUINE_CIDER,
			ItemType.STR_INGREDIENT_WOLF_WHISKEY,
			ItemType.INT_INGREDIENT_FELINE_FANCY,
			ItemType.FIT_INGREDIENT_CANINE_CRUSH,
			ItemType.SEX_INGREDIENT_HARPY_PERFUME,
			ItemType.COR_INGREDIENT_LILITHS_GIFT,

			ItemType.DYE_BRUSH,
			ItemType.CONDOM,
			ItemType.VIXENS_VIRILITY,
			ItemType.PROMISCUITY_PILL,
			ItemType.MOTHERS_MILK };

	private AbstractClothing underwear = ClothingType.generateClothing(ClothingType.GROIN_BOXERS, Colour.CLOTHING_BLACK, false),
			legs = ClothingType.generateClothing(ClothingType.LEG_TROUSERS, Colour.CLOTHING_BLACK, false),
			torso = ClothingType.generateClothing(ClothingType.TORSO_SHORT_SLEEVE_SHIRT, Colour.CLOTHING_WHITE, false),
			socks = ClothingType.generateClothing(ClothingType.SOCK_SOCKS, Colour.CLOTHING_WHITE, false),
			shoes = ClothingType.generateClothing(ClothingType.FOOT_MENS_SMART_SHOES, Colour.CLOTHING_BLACK, false);

	public Ralph() {
		super(new NameTriplet("Ralph"), "Ralph is the owner of the shop 'Ralph's Snacks'. There's an air of confidence in the way he holds himself, and he behaves in a professional manner at all times.",
				10, Gender.MALE, RacialBody.HORSE_MORPH, RaceStage.LESSER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, ShoppingArcade.RALPHS_SHOP_ITEMS, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		
		this.setEyeColour(Colour.EYE_BROWN);
		this.setHairColour(Colour.COVERING_BLACK);
		this.setSkinColour(BodyCoveringType.HUMAN, Colour.HUMAN_SKIN_EBONY);
		this.setSkinColour(BodyCoveringType.HORSE_HAIR, Colour.COVERING_BLACK);

		this.equipClothingFromNowhere(underwear, true, this);
		this.equipClothingFromNowhere(legs, true, this);
		this.equipClothingFromNowhere(torso, true, this);
		this.equipClothingFromNowhere(socks, true, this);
		this.equipClothingFromNowhere(shoes, true, this);
		
		this.addFetish(Fetish.FETISH_IMPREGNATION);

		// Items:
		for(int i=0;i<5;i++)
			this.addItem(ItemType.generateItem(ItemType.DYE_BRUSH), false);
		for (ItemType item : itemsForSale) {
			for (int i = 0; i < (Util.random.nextInt(5) + 1); i++) {
				this.addItem(ItemType.generateItem(item), false);
			}
		}
	}
	
	/**
	 * Discount is active for three days after earning it.
	 */
	public boolean isDiscountActive(){
		if(Main.game.getDialogueFlags().ralphDiscountStartTime==-1 || Main.game.getDialogueFlags().ralphDiscount<=0) {
			return false;
		} else {
			return (Main.game.getMinutesPassed()-Main.game.getDialogueFlags().ralphDiscountStartTime) < (60*24*3);
		}
	}

	@Override
	public void applyReset() {
		resetInventory();
		
		this.setMoney(10);

		this.equipClothingFromNowhere(underwear, true, this);
		this.equipClothingFromNowhere(legs, true, this);
		this.equipClothingFromNowhere(torso, true, this);
		this.equipClothingFromNowhere(socks, true, this);
		this.equipClothingFromNowhere(shoes, true, this);

		for(int i=0;i<5;i++)
			this.addItem(ItemType.generateItem(ItemType.DYE_BRUSH), false);
		for (ItemType item : itemsForSale) {
			for (int i = 0; i < (Util.random.nextInt(5) + 1); i++)
				this.addItem(ItemType.generateItem(item), false);
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
			
			descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
						+ "You look over at the counter to see Ralph smiling back at you. Sensing that you might need some assistance, he brisky walks over to where you're standing."
					+ "</p>"
					+ "<p>"
						+ "Although the memory of you submissively pleasuring Ralph's huge horse-cock is still fresh in both of your minds, he treates you with the utmost respect,"
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
						+ "You look over at the counter to see Ralph smiling back at you. Sensing that you might need some assistance, he brisky walks over to where you're standing."
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
			if(((AbstractItem)item).getItemType().canBeSold())
				return true;
		}
		
		return false;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Shopping", "Return to browsing the wares in Ralph's shop.", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Ralph returns to running his shop, and you walk back over to the transformative consumables section, wondering if you should buy anything with your discount."
						+ " When he's sure that nobody else is watching, Ralph gazes lustfully at your body, and you're pretty sure that you could convince him to give you another \"discount\" any time you wanted it."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on browsing the wares in Ralph's shop.", RalphsSnacks.INTERIOR);
			} else {
				return null;
			}
		}
	};

	// Combat (you never fight Ralph):
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
	public String getLostVirginityDescriptor() {
		return "as you earned a discount";
	}
	
	
	
	@Override
	public String getPenetrationDescription(boolean initialPenetration, PenetrationType penetrationType, OrificeType orifice) {
		if(Math.random()>0.3) {
			if(penetrationType == PenetrationType.PENIS_PARTNER && orifice == OrificeType.VAGINA_PLAYER) {
				return UtilText.returnStringAtRandom(
						"Ralph carries on driving you into the counter-top as he fucks your "+Main.game.getPlayer().getVaginaName(true)+".",
						"You feel Ralph's strong grip on your hips as his "+Sex.getPartner().getPenisName(true)+" pounds away at your "+Main.game.getPlayer().getVaginaName(true)+".",
						"Ralph's "+Sex.getPartner().getPenisName(true)+" carries on slamming in and out of your greedy "+Main.game.getPlayer().getVaginaName(false)+".",
						"Your pussy lips spread around Ralph's "+Sex.getPartner().getPenisName(true)+" as he fucks you on the counter-top.");
			}
			
			if(penetrationType == PenetrationType.PENIS_PARTNER && orifice == OrificeType.MOUTH_PLAYER) {
				return UtilText.returnStringAtRandom(
						"Slimy saliva drools down your chin as you carry on sucking Ralph's "+Sex.getPartner().getPenisName(true)+".",
						"You look down at the "+Sex.getPartner().getPenisName(true)+" sliding in and out of your mouth.",
						"Ralph's "+Sex.getPartner().getPenisName(true)+" pushes its way past your lips as you kneel beneath him.",
						"You bob your head back and forth as you carry on servicing Ralph's "+Sex.getPartner().getPenisName(true)+".");
			}
		}

		return super.getPenetrationDescription(initialPenetration, penetrationType, orifice);
	}
	
	// Vagina:
	
	@Override
	public String getPlayerVaginaVirginityLossDescription(boolean isPlayerDom){
		return formatVirginityLoss("Ralph's massive black horse-cock tears through your hymen as he takes your virginity!")
				+ 
				(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)
						?losingPureVirginity()
						:"");
	}
	
	@Override
	public String getPlayerVaginaStretchingDescription(PenetrationType penetrationType) {
		if(Math.random()<0.3) {
			return super.getPlayerVaginaStretchingDescription(penetrationType);
		} else {
			return formatStretching(UtilText.returnStringAtRandom(
					"You squirm about uncomfortably on the counter-top as Ralph's " +Sex.getPartner().getPenisName(true)+" struggles to fit in your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" "+Main.game.getPlayer().getVaginaName(false)+".",
					"Ralph's " +Sex.getPartner().getPenisName(true)+" painfully stretches you out as it rams its way in and out of your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" "+Main.game.getPlayer().getVaginaName(false)+".",
					"Ralph's "+Sex.getPartner().getPenisName(true)+" is too large for your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" "+Main.game.getPlayer().getVaginaName(false)+", and you let out an uncomfortable whine as it stretches you out.",
					"Your "+Main.game.getPlayer().getVaginaCapacity().getDescriptor()+" little "+Main.game.getPlayer().getVaginaName(false)+" struggles to accommodate the sheer size of Ralph's "+Sex.getPartner().getPenisName(true)+"."));
		}
	}
	
	@Override
	public String getPlayerVaginaStretchingFinishedDescription() {
		return formatStretching("The next time Ralph slams forwards, you feel your pussy easily stretching out to accommodate his flared equine member, and with a squeal of delight,"
				+ " you realise that you're now able to comfortably fit Ralph's massive black horse-cock in your "+Main.game.getPlayer().getVaginaName(true)+"!");
	}
	
	// Mouth:

	@Override
	public String getPlayerMouthVirginityLossDescription(){
		return formatVirginityLoss("You'd never have thought that your first time sucking cock would be like this!");
	}
	
	@Override
	public String getPlayerMouthStretchingDescription(PenetrationType penetrationType) {
		if(Math.random()<0.3) {
			return super.getPlayerMouthStretchingDescription(penetrationType);
		} else {
			return formatStretching(UtilText.returnStringAtRandom(
					"You feel tears streaming down your face as you force the "+Sex.getPartner().getPenisName(true)+" down your throat.",
					"You aren't skilled enough at giving head to be able to comfortably handle Ralph's "+Sex.getPartner().getPenisName(true)+".",
					"You squirm and choke as you do your best to fit the "+Sex.getPartner().getPenisName(true)+" down your throat.",
					"You struggle to fit Ralph's "+Sex.getPartner().getPenisName(true)+" down your throat."));
		}
	}
	
	@Override
	public String getPlayerMouthStretchingFinishedDescription() {
		return formatStretching("The next time Ralph gently pushes forwards, you feel your throat instinctively opening up to accommodate his flared equine member,"
				+ " and with a muffled moan of delight, you realise that you're now able to comfortably suck Ralph's massive black horse-cock!");
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
				switch(item.getItemType()){
					case CONDOM:
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)==(PenetrationType.PENIS_PARTNER))
							return "<p>"
									+ "You pull out a condom from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
										+ " He looks down at you before nodding and stepping back, sliding his huge cock free from your mouth."
										+ " You get a moment to catch your breath as Ralph tears opens the little foil package before rolling the condom down the length of his massive shaft."
										+ " Stepping forwards once more, he shoves his rubber-bound dick back down your throat, and you let out a muffled sigh of relief, happy that he did as you asked."
								+ "</p>";
						else if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)==(PenetrationType.PENIS_PARTNER))
							return "<p>"
										+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a condom."
											+ " Suppressing your moans, you turn back, holding out your hand as you ask him to put it on."
											+ " He lets out a disappointed sigh, but doesn't openly object as he pulls his cock free from your pussy, giving you a moment to breathe as he"
												+ " tears opens the little foil package before rolling the condom down the length of his massive shaft."
											+ " Once the condom is securely in place, he lines himself up and pushes forwards, burying his rubber-bound dick deep into your pussy."
									+ "</p>";
						else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)==(PenetrationType.PENIS_PARTNER))
							return "<p>"
								+ "As Ralph carries on slamming his huge cock in and out of your ass, you fumble around in your inventory and produce a condom."
									+ " Suppressing your groans, you turn back, holding out your hand as you ask him to put it on."
									+ " He lets out a disappointed sigh, but doesn't openly object as he pulls his cock free from your rear entrance, giving you a moment to breathe as he"
										+ " tears opens the little foil package before rolling the condom down the length of his massive shaft."
									+ " Once the condom is securely in place, he lines himself up and pushes forwards, burying his rubber-bound dick deep into your waiting asshole."
								+ "</p>";
						else
							return "<p>"
								+ "Producing a condom from your inventory, you lean forwards, looking up at Ralph and asking him to put it on as you hold the little foil package up to him."
										+ " With a sigh, he takes the condom from you, and, tearing the package open, quickly rolls the condom down the length of his massive shaft."
										+ " You thank him for doing as you asked, and he replies that he's happy to respect your request."
							+ "</p>";
					case VIXENS_VIRILITY:
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)==(PenetrationType.PENIS_PARTNER))
							return "<p>"
									+ "You pull out a Vixen's Virility pill from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
										+ " He looks down at you, letting out a little laugh and shrugging his shoulders as he sees what you're trying to give him."
										+ " Quickly popping the pill out of its plastic container, he swallows it, and you let out a happy, although somewhat muffled, giggle, knowing that his sperm just got a lot more potent."
								+ "</p>";
						else if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)==(PenetrationType.PENIS_PARTNER))
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
						else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)==(PenetrationType.PENIS_PARTNER))
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
					default:
						if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)!=null)
							return "<p>"
									+ "You pull out "+item.getItemType().getDeterminer()+" "+item.getName()+" from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
										+ " He looks down at you before making a dismissive grunt and stepping forwards slightly, ramming his cock that little bit further down your throat as you put the "+item.getName()+" back in your inventory."
								+ "</p>";
						else if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)!=null)
							return "<p>"
										+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a "+item.getItemType().getDeterminer()+" "+item.getName()+"."
											+ " Suppressing your moans, you ask him if he'd like to "+item.getItemType().getUseName()+" it, but he simply makes a dismissive grunt and carries on fucking you."
											+ " You start sighing and crying out in pleasure once more as you put the "+item.getName()+" back in your inventory."
									+ "</p>";
						else if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)!=null)
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
			return Sex.getPartner().useItem(item, target, false);
		}
	}

}