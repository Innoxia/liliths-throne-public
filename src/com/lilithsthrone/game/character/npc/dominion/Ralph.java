package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexFlags;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.3.9
 * @author Innoxia
 */
public class Ralph extends NPC {
	
	public static final String RALPH_DISCOUNT_TIMER_ID = "ralph_discount_timer";
	
	public Ralph() {
		this(false);
	}
	
	public Ralph(boolean isImported) {
		super(isImported, new NameTriplet("Ralph"), "Armstrong",
				"Ralph is the owner of the shop 'Ralph's Snacks'. There's an air of confidence in the way he holds himself, and he behaves in a professional manner at all times.",
				34, Month.MAY, 17,
				10, Gender.M_P_MALE, Subspecies.HORSE_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SHOPPING_ARCADE, PlaceType.SHOPPING_ARCADE_RALPHS_SHOP, true);
		
		if(!isImported) {
			dailyUpdate();
			
			this.setAttribute(Attribute.MAJOR_CORRUPTION, 35);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.10.5")) {
			resetBodyAfterVersion_2_10_5();
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.6")) {
			this.resetPerksMap(true);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.7.5")) {
			this.setPenisSize(30);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.9.1")) {
			this.setPenisCumStorage(250);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.1")) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		this.addSpecialPerk(Perk.SPECIAL_DIRTY_MINDED);
		
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 3),
						new Value<>(PerkCategory.LUST, 1),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.SELFISH,
					PersonalityTrait.LEWD);
			
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setHistory(Occupation.NPC_STORE_OWNER);
	
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
		}
		
		// Body:

		// Core:
		this.setHeight(195);
		this.setFemininity(5);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.THREE_LARGE.getMedianValue());
		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_EBONY), true);

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HORSE_HAIR, PresetColour.COVERING_BROWN_DARK), true);
		this.setHairLength(HairLength.TWO_SHORT);
		this.setHairStyle(HairStyle.LOOSE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.THREE_TRIMMED);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		// Leave as default:
//		this.setLipSize(LipSize.ONE_AVERAGE);
//		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
//		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		// Leave as default:
//		this.setNippleVirgin(true);
//		this.setBreastSize(CupSize.FLAT.getMeasurement());
//		this.setBreastShape(BreastShape.ROUND);
//		this.setNippleSize(NippleSize.ZERO_TINY);
//		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		// Leave as default:
//		this.setAssSize(AssSize.TWO_SMALL);
//		this.setHipSize(HipSize.TWO_NARROW);
//		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
//		this.setAssWetness(Wetness.ZERO_DRY);
//		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
//		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FIVE_THICK);
		this.setPenisSize(30);
		this.setTesticleSize(TesticleSize.FOUR_HUGE);
		this.setPenisCumStorage(250);
		this.fillCumToMaxStorage();
		// Leave cum as normal value
		
		// Vagina:
		// No vagina
		
		// Feet:
		// Foot shape
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.unequipAllClothingIntoVoid(true, true);

		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.GROIN_BOXERS, PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_jeans", PresetColour.CLOTHING_BLACK, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_short_sleeved_shirt", PresetColour.CLOTHING_PINK_LIGHT, false), true, this);
		this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_GOLD, false), true, this);

	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	/**
	 * Discount is active for three days after earning it.
	 */
	public boolean isDiscountActive(){
		if(Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID) == -1 || Main.game.getDialogueFlags().ralphDiscount <= 0) {
			return false;
		} else {
			return (Main.game.getMinutesPassed()-Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID)) < (60*24*3);
		}
	}

	@Override
	public void dailyUpdate() {
		clearNonEquippedInventory(false);
		
		this.addItem(Main.game.getItemGen().generateItem(ItemType.DYE_BRUSH), 25, false, false);
		this.addItem(Main.game.getItemGen().generateItem(ItemType.REFORGE_HAMMER), 10, false, false);
		
		for(AbstractItemType item : ItemType.getAllItems()) {
			if(item.getItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!item.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addItem(Main.game.getItemGen().generateItem(item), !item.isConsumedOnUse()?1:(6+Util.random.nextInt(12)), false, false);
			}
		}

		for(AbstractWeaponType weapon : WeaponType.getAllWeapons()) {
			if(weapon.getItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!weapon.getItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				this.addWeapon(Main.game.getItemGen().generateWeapon(weapon), 1+Util.random.nextInt(5), false, false);
			}
		}
		
		for(AbstractClothingType clothing : ClothingType.getAllClothing()) {
			if(clothing.getDefaultItemTags().contains(ItemTag.SOLD_BY_RALPH)
					&& (!clothing.getDefaultItemTags().contains(ItemTag.SILLY_MODE) || Main.game.isSillyMode())) {
				if(clothing.isDefaultSlotCondom()) {
					Colour condomColour = clothing.getColourReplacement(0).getRandomOfDefaultColours();
					Colour condomColourSec = PresetColour.CLOTHING_BLACK;
					Colour condomColourTer = PresetColour.CLOTHING_BLACK;
					
					if(clothing.getColourReplacement(1)!=null) {
						condomColourSec = clothing.getColourReplacement(1).getRandomOfDefaultColours();
					}
					if(clothing.getColourReplacement(2)!=null) {
						condomColourTer = clothing.getColourReplacement(2).getRandomOfDefaultColours();
					}
					for (int i = 0; i < (3+(Util.random.nextInt(4)))*(clothing.getRarity()==Rarity.COMMON?3:(clothing.getRarity()==Rarity.UNCOMMON?2:1)); i++) {
						this.addClothing(Main.game.getItemGen().generateClothing(clothing, condomColour, condomColourSec, condomColourTer, false), false);
					}
					
				} else {
					this.addClothing(Main.game.getItemGen().generateClothing(clothing), false);
				}
			}
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
		if(Main.game.getDialogueFlags().getSavedLong(RALPH_DISCOUNT_TIMER_ID)>0){
			StringBuilder descriptionSB = new StringBuilder();
			
			descriptionSB.append("<p>"
						+ "You look over at the counter to see Ralph smiling back at you. Sensing that you might need some assistance, he briskly walks over to where you're standing."
					+ "</p>"
					+ "<p>"
						+ "Although the memory of you submissively pleasuring Ralph's huge horse-cock is still fresh in both of your minds, he treats you with the utmost respect and politely lists off the prices of the transformative consumables on display."
						+ (this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
								?" While most of them seem to have a reasonable markup, the small blue '"+ItemType.getItemTypeFromId("innoxia_pills_sterility").getNamePlural(false)+"' appear to be unreasonably pricy,"
								+ " and Ralph once again notes that there's a huge tax levied on their sale."
							+ "</p>"
							+ "<p>"
						:"")
						+ " He reminds you that he's also interested in buying any transformative consumables that you're willing to sell."
						+ " As you turn back to look at the goods, Ralph tells you that he refreshes his stock every day."
					+ "</p>");
			
			if(isDiscountActive()){
				descriptionSB.append("<p>"
						+ "<b>Ralph is giving you a</b> <b style='color:" + PresetColour.GENERIC_GOOD.toWebHexString() + ";'>"+Main.game.getDialogueFlags().ralphDiscount+"%</b> <b>discount!</b>"
							+ "</p>");
			}else{
				descriptionSB.append("<p>"
						+ "Before he turns to walk back to the counter, he leans in and whispers in your ear, "
						+ UtilText.parseSpeech("If you're interested in earning a little discount again, just call me over.", Main.game.getNpc(Ralph.class))
							+ "</p>");
			}
			
			return descriptionSB.toString();
			
		} else {
			return "<p>"
						+ "You look over at the counter to see Ralph smiling back at you. Sensing that you might need some assistance, he briskly walks over to where you're standing."
					+ "</p>"
					+ "<p>"
						+ "After a brief greeting, you ask him about the transformative consumables on display. He politely informs you that they're all for sale and quickly lists off their prices."
						+ (this.hasItemType(ItemType.getItemTypeFromId("innoxia_pills_sterility"))
							?" While most of them seem to have a reasonable markup, the small blue '"+ItemType.getItemTypeFromId("innoxia_pills_sterility").getNamePlural(false)+"' appear to be unreasonably pricy,"
									+ " and after asking Ralph about their high cost, he explains that there's a huge tax levied on their sale."
								+ "</p>"
								+ "<p>"
							:"")
						+ " Sensing that you might not be a typical customer, he tells you that he's also interested in buying any transformative consumables that you're willing to sell."
						+ " As you turn back to look at the goods, Ralph tells you that he refreshes his stock every day."
					+ "</p>";
		}
		
	}

	@Override
	public boolean isTrader() {
		return true;
	}
	
	@Override
	public float getSellModifier(AbstractCoreItem item) {
		float base = 1.5f;
		if(item instanceof AbstractItem) {
			if(((AbstractItem)item).getItemType()==ItemType.getItemTypeFromId("innoxia_pills_sterility")) {
				base*=10;
			}
		}
		return Math.max(getBuyModifier(), (base * ((100-SexFlags.ralphDiscount)/100f)) * (Main.game.getPlayer().hasTrait(Perk.JOB_STUDENT, true)?0.75f:1));
	}
	
	@Override
	public boolean willBuy(AbstractCoreItem item) {
		if(item.getItemTags().contains(ItemTag.CONTRABAND_LIGHT)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_MEDIUM)
				|| item.getItemTags().contains(ItemTag.CONTRABAND_HEAVY)) {
			return false;
		}
		if(item instanceof AbstractItem) {
			return true;
		}
		if(item instanceof AbstractClothing) {
			AbstractClothingType type = ((AbstractClothing)item).getClothingType();
			return type.isDefaultSlotCondom();
		}
		
		return false;
	}

	@Override
	public void endSex() {
		for(AbstractClothing c : this.getClothingCurrentlyEquipped()) {
			c.getDisplacedList().clear();
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		return super.getForeplayPreference(target);
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
		}
		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex()) {
			if(Main.sex.getSexManager().getPosition() == SexPosition.OVER_DESK && target.equals(this)) {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name] [npc.verb(direct)] [npc.her] spinneret at [npc2.namePos] [npc2.cock], but, sensing what [npc.sheIs] about to do, he slaps it away and grunts,"
							+ " [npc2.speech(I don't think so! You agreed to let me breed you, and that's exactly what I'm going to do!)]");
				}
				return UtilText.parse(target,
						"<p>"
							+ "You pull out a condom and hold it out to Ralph, but as he sees what it is you've got, he grabs it and tears it in two, before dismissively grunting,"
							+ " [npc.speech(I don't think so! You agreed to let me breed you, and that's exactly what I'm going to do!)]"
						+ "</p>");
			}
			
			if(!target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>"
							+ "You pull out a condom from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
								+ " He looks down at you before nodding and stepping back, sliding his huge cock free from your mouth."
								+ " You get a moment to catch your breath as Ralph tears opens the little foil package before rolling the condom down the length of his massive shaft."
								+ " Stepping forwards once more, he shoves his rubber-bound dick back down your throat, and you let out a muffled sigh of relief, happy that he did as you asked."
						+ "</p>";
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return "<p>"
								+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a condom."
									+ " Suppressing your moans, you turn back, holding out your hand as you ask him to put it on."
									+ " He lets out a disappointed sigh, but doesn't openly object as he pulls his cock free from your pussy, giving you a moment to breathe as he"
										+ " tears opens the little foil package before rolling the condom down the length of his massive shaft."
									+ " Once the condom is securely in place, he lines himself up and pushes forwards, burying his rubber-bound dick deep into your pussy."
							+ "</p>";
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
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
		}
		return null;
	}
	
	
	@Override
	public String getPenetrationDescription(boolean initialPenetration, GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaInterface orifice) {
		if(Math.random()>0.3) {
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.VAGINA) {
				return UtilText.returnStringAtRandom(
						"Ralph carries on driving you into the counter-top as he fucks your [pc.pussy+].",
						"You feel Ralph's strong grip on your hips as his [ralph.cock+] pounds away at your [pc.pussy+].",
						"Ralph's [ralph.cock+] carries on slamming in and out of your greedy [pc.pussy].",
						"Your pussy lips spread around Ralph's [ralph.cock+] as he fucks you on the counter-top.");
			}
			
			if(penetrationType == SexAreaPenetration.PENIS && orifice == SexAreaOrifice.MOUTH) {
				return UtilText.returnStringAtRandom(
						"Slimy saliva drools down your chin as you carry on sucking Ralph's [ralph.cock+].",
						"You look down at the [ralph.cock+] sliding in and out of your mouth.",
						"Ralph's [ralph.cock+] pushes its way past your lips as you kneel beneath him.",
						"You bob your head back and forth as you carry on servicing Ralph's [ralph.cock+].");
			}
		}

		return super.getPenetrationDescription(initialPenetration, characterPenetrating, penetrationType, characterPenetrated, orifice);
	}
	
	// Vagina:
	
	@Override
	public String getStretchingDescription(boolean initialPenetration, GameCharacter partner, SexAreaPenetration penetrationType, SexAreaOrifice orifice, boolean pastTense) {
		switch(orifice) {
			case MOUTH:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
				} else {
					return UtilText.returnStringAtRandom(
							"You feel tears streaming down your face as you force the [ralph.cock+] down your throat.",
							"You aren't skilled enough at giving head to be able to comfortably handle Ralph's [ralph.cock+].",
							"You squirm and choke as you do your best to fit the [ralph.cock+] down your throat.",
							"You struggle to fit Ralph's [ralph.cock+] down your throat.");
				}
			case VAGINA:
				if(Math.random()<0.3) {
					return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
				} else {
					return UtilText.returnStringAtRandom(
							"You squirm about uncomfortably on the counter-top as Ralph's [ralph.cock+] struggles to fit in your [pc.pussyCapacity] [pc.pussy].",
							"Ralph's [ralph.cock+] painfully stretches you out as it rams its way in and out of your [pc.pussyCapacity] [pc.pussy].",
							"Ralph's [ralph.cock+] is too large for your [pc.pussyCapacity] [pc.pussy], and you let out an uncomfortable whine as it stretches you out.",
							"Your [pc.pussyCapacity] [pc.pussy] struggles to accommodate the sheer size of Ralph's [ralph.cock+].");
				}
			default:
				return super.getStretchingDescription(initialPenetration, partner, penetrationType, orifice, pastTense);
		}
	}
	
	@Override
	public String getStretchingFinishedDescription(SexAreaOrifice orifice) {
		switch(orifice) {
			case MOUTH:
				return "The next time Ralph gently pushes forwards, you feel your throat instinctively opening up to accommodate his flared equine member,"
						+ " and with a muffled moan of delight, you realise that you're now able to comfortably suck Ralph's massive black horse-cock!";
			case VAGINA:
				return "The next time Ralph slams forwards, you feel your pussy easily stretching out to accommodate his flared equine member, and with a squeal of delight,"
						+ " you realise that you're now able to comfortably fit Ralph's massive black horse-cock in your [pc.pussy+]!";
			default:
				return super.getStretchingFinishedDescription(orifice);
		}
	}
	
	@Override
	public Value<Boolean, String> getItemUseEffects(AbstractItem item, GameCharacter itemOwner, GameCharacter user, GameCharacter target) {
		if(user.isPlayer() && !target.isPlayer()) {
			if(item.isTypeOneOf("innoxia_pills_fertility", "innoxia_pills_broodmother")) {
				itemOwner.useItem(item, target, false);
				
				if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
							"<p>"
								+ "You pull out a "+item.getName(false, false)+" from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
								+ " He looks down at you, letting out a little laugh and shrugging his shoulders as he sees what you're trying to give him."
								+ " Quickly popping the pill out of its plastic container, he swallows it, and you let out a happy, although somewhat muffled, giggle, knowing that his sperm just got a lot more potent."
							+ "</p>");
				
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
							"<p>"
								+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a "+item.getName(false, false)+"."
								+ " Suppressing your moans, you turn back, holding out your hand as you ask him to swallow it."
								+ " He lets out a little laugh as he sees what you're giving him, and, quickly popping the pill out of its plastic container and swallowing it,"
								+ " he takes a moment to say a few words before continuing to fuck you, "
								+ (Main.game.getNpc(Ralph.class).isWearingCondom()
									?" [ralph.speech(You do realise I'm wearing a condom, right?)]"
									:(Main.game.getPlayer().isVisiblyPregnant()
											?" [ralph.speech(Uhh, you're already pregnant, but sure, why not?)]"
											:" [ralph.speech(Hoping to get pregnant, huh? Well, I'm more than happy to help with that!)]"))
							+ "</p>");
					
				} else if(Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).contains(SexAreaPenetration.PENIS)) {
					return new Value<>(true,
								"<p>"
									+ "As Ralph carries on slamming his huge cock in and out of your ass, you fumble around in your inventory and produce a "+item.getName(false, false)+"."
									+ " Suppressing your groans, you turn back, holding out your hand as you ask him to swallow it."
									+ " He lets out a little laugh as he sees what you're giving him, and, quickly popping the pill out of its plastic container and swallowing it,"
									+ " he takes a moment to say a few words before continuing to fuck you, "
									+ "[ralph.speech(You do know you can't get pregnant from this, right?)]"
								+ "</p>");
					
				} else {
					return new Value<>(true,
							"<p>"
								+ "Producing a "+item.getName(false, false)+" from your inventory, you lean forwards, looking up at Ralph and asking him to swallow it as you hold it up to him."
									+ " He looks down at you, letting out a little laugh and shrugging his shoulders as he sees what you're trying to give him."
									+ " Quickly popping the pill out of its plastic container, he swallows it, and you let out a happy giggle, knowing that his sperm just got a lot more potent."
							+ "</p>");
				}
				
			} else {
				if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.MOUTH, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>"
								+ "You pull out "+item.getItemType().getDeterminer()+" "+item.getName()+" from your inventory, and, making a muffled questioning sound, hold it up to Ralph."
								+ " He looks down at you before making a dismissive grunt and stepping forwards slightly, ramming his cock that little bit further down your throat as you put the "+item.getName()+" back in your inventory."
							+ "</p>");
					
				} else if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.VAGINA, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>"
								+ "As Ralph carries on slamming his huge cock in and out of your pussy, you fumble around in your inventory and produce a "+item.getItemType().getDeterminer()+" "+item.getName()+"."
								+ " Suppressing your moans, you ask him if he'd like to "+item.getItemType().getUseName()+" it, but he simply makes a dismissive grunt and carries on fucking you."
								+ " You start sighing and crying out in pleasure once more as you put the "+item.getName()+" back in your inventory."
							+ "</p>");
					
				} else if(!Main.sex.getOngoingSexAreas(Main.game.getPlayer(), SexAreaOrifice.ANUS, Main.game.getNpc(Ralph.class)).isEmpty()) {
					return new Value<>(false,
							"<p>"
								+ "As Ralph carries on slamming his huge cock in and out of your ass, you fumble around in your inventory and produce a "+item.getItemType().getDeterminer()+" "+item.getName()+"."
								+ " Suppressing your groans, you ask him if he'd like to "+item.getItemType().getUseName()+" it, but he simply makes a dismissive grunt and carries on fucking you."
								+ " You start sighing and crying out in pleasure once more as you put the "+item.getName()+" back in your inventory."
							+ "</p>");
					
				} else {
					return new Value<>(false,
							"<p>"
								+ "You ask Ralph if he'd like to use "+item.getItemType().getDeterminer()+" "+item.getName()+" that you've got, but he simply makes a dismissive grunt and tells you he's not interested."
								+ " With a sigh, you put the "+item.getName()+" back in your inventory."
							+ "</p>");
				}
			}
		}
		return super.getItemUseEffects(item, itemOwner, user, target);
	}
	
}
