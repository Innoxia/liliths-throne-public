package com.base.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;

import com.base.game.character.GameCharacter;
import com.base.game.character.NameTriplet;
import com.base.game.character.SexualOrientation;
import com.base.game.character.body.Covering;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.valueEnums.CupSize;
import com.base.game.character.effects.Fetish;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.AbstractCoreItem;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothing;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.world.WorldType;
import com.base.world.places.ShoppingArcade;

/**
 * @since 0.1.66
 * @version 0.1.78
 * @author Innoxia
 */
public class Kate extends NPC {

	private static final long serialVersionUID = 1L;

	private AbstractClothing
			skirt = ClothingType.generateClothing(ClothingType.LEG_MINI_SKIRT, Colour.CLOTHING_PINK, false),
			torso = ClothingType.generateClothing(ClothingType.TORSO_CAMITOP_STRAPS, Colour.CLOTHING_PINK, false),
			socks = ClothingType.generateClothing(ClothingType.SOCK_KNEEHIGH_SOCKS, Colour.CLOTHING_WHITE, false),
			shoes = ClothingType.generateClothing(ClothingType.FOOT_HEELS, Colour.CLOTHING_PINK, false);

	public Kate() {
		super(new NameTriplet("Kate"), "Kate is a demon who owns the beauty salon 'Succubi's Secrets'."
				+ " Despite being incredibly good at what she does, she's exceedingly lazy, and prefers to keep the exterior of her shop looking run-down so as to scare off potential customers.",
				10, Gender.FEMALE, RacialBody.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, ShoppingArcade.KATES_SHOP_BEAUTY, true);

		this.setSexualOrientation(SexualOrientation.AMBIPHILIC);

		this.setEyeCovering(new Covering(BodyCoveringType.EYE_DEMON_COMMON, Colour.EYE_GREEN));
		this.setHairCovering(new Covering(BodyCoveringType.HAIR_DEMON, Colour.COVERING_BLACK));
		this.setCovering(new Covering(BodyCoveringType.DEMON_COMMON, Colour.SKIN_PINK));

		this.setBreastSize(CupSize.F.getMeasurement());
		
		this.setAssVirgin(false);
		this.setNippleVirgin(false);
		this.setVaginaVirgin(false);
		this.setFaceVirgin(false);
		
		this.addFetish(Fetish.FETISH_SUBMISSIVE);
		this.addFetish(Fetish.FETISH_PREGNANCY);
		
		applyReset();
	}

	@Override
	public void applyReset() {
		resetInventory();
		
		this.setMoney(10);
		
		this.equipClothingFromNowhere(skirt, true, this);
		this.equipClothingFromNowhere(torso, true, this);
		this.equipClothingFromNowhere(socks, true, this);
		this.equipClothingFromNowhere(shoes, true, this);
		
		
		int iterations = 3 + Util.random.nextInt(3);
		for (int i = 0; i < iterations; i++)
			this.addClothing(ClothingType.generateClothingWithEnchantment(ClothingType.getCommonJewellery().get(Util.random.nextInt(ClothingType.getCommonJewellery().size()))), false);
		
		for(int i=0; i<getClothingCount(); i++)
			getClothing(i).setEnchantmentKnown(true);
		
		for (ClothingType ct : ClothingType.getCommonJewellery()) {
			iterations = 1 + Util.random.nextInt(2);
			for(int i=0; i<iterations; i++)
				this.addClothing(ClothingType.generateClothing(ct, false), false);
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
		if(item instanceof AbstractClothing)
			return true;
		
		return false;
	}

	@Override
	public void endSex(boolean applyEffects) {
		if (applyEffects) {
			setPenisType(PenisType.NONE);
		}
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Step back", "Step back and allow Kate to recover.", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}

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
					+ UtilText.parseSpeech("Well, yeah I'm aware! You know, the owners of this whole promenade keep threatening me with legal action, saying I have a 'responsibility' to keep the area looking nice."
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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().kateIntroduced = true;
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
		public int getMinutesPassed(){
			return 15;
		}

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
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN);
			} else {
				return null;
			}
		}
	};

	// Combat (you never fight Kate):
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
		return "as you fucked her on a chair in her beauty salon";
	}
	
	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()){
				switch(item.getItemType()){
					case CONDOM:
							Main.game.getPlayer().removeItem(item);
							return "<p>"
								+ "As you pull out a condom, a worried frown flashes across Kate's face, "
								+ UtilText.parseSpeech("Oh! Erm, let me put that on for you!", Main.game.getKate())
								+"</br>"
								+ "Before you can react, Kate snatches the condom out of your hands, and with a devious smile, uses her sharp little canines to bite a big hole straight through the centre."
								+ " She laughs at your shocked reaction, "
								+ UtilText.parseSpeech("It's no fun if I don't get any cum!", Main.game.getKate())
							+ "</p>";
					default:
						return Main.game.getPlayer().useItem(item, target, false);
				}
				
			// Player uses item on NPC:
			}else{
				switch(item.getItemType()){
					case VIXENS_VIRILITY:
						Main.game.getPlayer().useItem(item, target, false);
							return "<p>"
								+ "Producing a Vixen's Virility pill from your inventory, you pop it out of its plastic wrapper before pushing it into Kate's mouth."
								+ " She giggles as she happily swallows the little pink pill, knowing that it's going to make her womb far more fertile."
							+ "</p>";
					default:
							return "<p>"
								+ "You start to pull "+item.getItemType().getDeterminer()+" "+item.getName()+" out from your inventory, but Kate quickly kicks your hand away and frowns at you."
							+ "</p>";
				}
			}
			
		// NPC is using an item:
		}else{
			return Sex.getPartner().useItem(item, target, false);
		}
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
