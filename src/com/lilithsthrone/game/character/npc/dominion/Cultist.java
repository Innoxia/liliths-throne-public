package com.lilithsthrone.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.CultistDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.88
 * @version 0.2.2
 * @author Innoxia
 */
public class Cultist extends NPC {

	private static final long serialVersionUID = 1L;

	private boolean requestedAnal = false;
	private boolean sealedSex = false;

	public Cultist() {
		this(false);
	}
	
	public Cultist(boolean isImported) {
		super(null,
				"",
				15,
				Gender.F_P_V_B_FUTANARI,
				RacialBody.DEMON,
				RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.DOMINION,
				PlaceType.DOMINION_BACK_ALLEYS,
				false);
		
		if(!isImported) {
			setAttribute(Attribute.MAJOR_CORRUPTION, 100);
	
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
			
			// BODY RANDOMISATION:
			this.addFetish(Fetish.FETISH_ORAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_ANAL_GIVING);
			this.addFetish(Fetish.FETISH_VAGINAL_GIVING);
			this.addFetish(Fetish.FETISH_IMPREGNATION);
			CharacterUtils.addFetishes(this);
			
			CharacterUtils.randomiseBody(this);
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setInternalTesticles(true);
			this.setPenisVirgin(false);
			
			setLevel(this.getLevel() - 3 + Util.random.nextInt(7));
			
			setName(Name.getRandomTriplet(Race.DEMON));
			this.setPlayerKnowsName(true);
			setDescription("As a high-ranking member of the 'Cult of Lilith', it's obvious to anyone that this demon is extremely powerful."
					+ " You aren't exactly 'anyone', however, and as you get close to her, you can almost physically feel the power of her arcane aura as it comes into contact with yours...");
			
			// Set random inventory & weapons:
			resetInventory();
			inventory.setMoney(100);
			
			// CLOTHING:
			
			List<Colour> colours = new ArrayList<>();
			colours.add(Colour.CLOTHING_ORANGE);
			colours.add(Colour.CLOTHING_BLACK);
			colours.add(Colour.CLOTHING_PURPLE);
			colours.add(Colour.CLOTHING_PURPLE_LIGHT);
			Colour underwearColour = colours.get(Util.random.nextInt(colours.size()));
	
			colours.clear();
			colours.add(Colour.CLOTHING_WHITE);
			colours.add(Colour.CLOTHING_BLACK);
			Colour witchColour = colours.get(Util.random.nextInt(colours.size()));
			
			
			List<AbstractClothingType> clothingChoices = new ArrayList<>();
			
			clothingChoices.add(ClothingType.GROIN_CROTCHLESS_PANTIES);
			clothingChoices.add(ClothingType.GROIN_CROTCHLESS_THONG);
			equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), underwearColour, false), true, this);
			
			clothingChoices.clear();
			clothingChoices.add(ClothingType.CHEST_LACY_PLUNGE_BRA);
			clothingChoices.add(ClothingType.CHEST_PLUNGE_BRA);
			equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), underwearColour, false), true, this);
			
			clothingChoices.clear();
			clothingChoices.add(ClothingType.SOCK_THIGHHIGH_SOCKS);
			equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), witchColour, false), true, this);
	
			equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WITCH_DRESS, witchColour, false), true, this);
			equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WITCH_HAT, witchColour, false), true, this);
			
			if(Math.random()>0.5f) {
				equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS, witchColour, false), true, this);
			} else {
				equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WITCH_BOOTS_THIGH_HIGH, witchColour, false), true, this);
			}
			
			this.equipMainWeaponFromNowhere(AbstractWeaponType.generateWeapon(WeaponType.MAIN_WITCH_BROOM));
			
			// Makeup:
			colours = Util.newArrayListOfValues(
					new ListValue<>(Colour.COVERING_NONE),
					new ListValue<>(Colour.COVERING_ORANGE),
					new ListValue<>(Colour.COVERING_PURPLE),
					new ListValue<>(Colour.COVERING_BLACK));
			
			Colour colourForCoordination = colours.get(Util.random.nextInt(colours.size()));
			Colour colourForNails = colours.get(Util.random.nextInt(colours.size()));
			
			setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, colourForCoordination));
			setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, Colour.COVERING_BLACK));
			setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, colourForCoordination));
			setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, colourForCoordination));
			
			setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, colourForNails));
			setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, colourForNails));
			
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}

	public boolean isSealedSex() {
		return sealedSex;
	}

	public void setSealedSex(boolean sealedSex) {
		this.sealedSex = sealedSex;
	}

	public boolean isRequestedAnal() {
		return requestedAnal;
	}

	public void setRequestedAnal(boolean requestedAnal) {
		this.requestedAnal = requestedAnal;
	}

	@Override
	public void endSex(boolean applyEffects) {
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return CultistDialogue.ENCOUNTER_START;
	}

	// Combat:
	
	@Override
	public String getCombatDescription() {
		return "[npc.Name] is furious that you're refusing to accept [npc.her] 'gift', and is now willing to fight you in order to force it upon you!";
	}
	
	@Override
	public Attack attackType() {
		if (Math.random() < 0.3f && this.getManaPercentage() > 0.4f
				&& (!Main.game.getPlayer().getStatusEffects().contains(StatusEffect.WITCH_SEAL) || !this.getStatusEffects().contains(StatusEffect.WITCH_CHARM))) {
			return Attack.SPELL;
		}
		
		if(Math.random() < 0.3f) {
			return Attack.MAIN;
		}
		
		return Attack.SEDUCTION;
	}
	
	@Override
	public String getLostVirginityDescriptor() {
		return "in her chapel";
	}

	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()) {
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			} else {
				if(item.getItemType().equals(ItemType.PROMISCUITY_PILL)) {
					if(Sex.isDom(Main.game.getPlayer())) {
						Main.game.getPlayer().useItem(item, target, false);
						return "<p>"
								+ "Holding out a 'Promiscuity pill' to [npc.name], you tell [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
								+ " [npc.She] lets out an angry huff, but as [npc.she]'s in no position to refuse, [npc.she] reluctantly does as you ask,"
								+ " [npc.speech(This is an insult to Lilith herself...)]"
								+ "</p>";
					} else {
						Main.game.getPlayer().useItem(item, target, false);
						return "<p>"
								+ "Holding out a 'Promiscuity pill' to [npc.name], you ask [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
								+ " With an angry huff, [npc.she] slaps the pill out of your hand,"
								+ " [npc.speech(How dare you! Lilith demands that her followers' seed remain strong!)]"
								+ "</p>";
					}
						
				} else if(item.getItemType().equals(ItemType.VIXENS_VIRILITY)) {
					Main.game.getPlayer().useItem(item, target, false);
					if(Sex.isDom(Main.game.getPlayer())) {
						return "<p>"
									+ "Holding out a 'Vixen's Virility' to [npc.name], you tell [npc.her] to swallow it."
									+ " [npc.She] lets out a delighted cry, and eagerly swallows the little pink pill,"
									+ " [npc.speech(Thank you! Being as fertile as possible is one of the best ways in which to worship Lilith!)]"
								+ "</p>";
					} else {
						return "<p>"
									+ "Holding out a 'Vixen's Virility' to [npc.name], you ask [npc.her] to swallow it."
									+ " [npc.She] lets out a delighted cry, and eagerly swallows the little pink pill,"
									+ " [npc.speech(Good toy! Being as fertile as possible is one of the best ways in which to worship Lilith!)]"
								+ "</p>";
					}
						
				} else if(item.getItemType().equals(ItemType.POTION) || item.getItemType().equals(ItemType.ELIXIR)) {
					
					if(Sex.isDom(Main.game.getPlayer())) {
						Main.game.getPlayer().removeItem(item);
						return "<p>"
									+ "Taking your "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
									+ " Seeing what you're offering [npc.herHim], [npc.she] lets out a little laugh, "
									+ " [npc.speech(Hah! Don't you know demons can't be transfo~Mrph!~)]"
								+ "</p>"
									+ "Not liking the start of [npc.her] response, you quickly remove the bottle's stopper, and rather unceremoniously shove the neck down [npc.her] throat."
									+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.her] go."
									+ " [npc.She] coughs and splutters for a moment, before letting out an annoyed cry as [npc.she] wipes the liquid from [npc.her] mouth,"
									+ " [npc.speech(W-what did I just say? Demons can't be transformed like that! But the taste is kinda nice I suppose...)]"
								+ "</p>";
					} else {
						return "<p>"
									+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
									+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
									+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
								+ "</p>";
					}
					
				} else if(item.getItemType().equals(ItemType.FETISH_UNREFINED) || item.getItemType().equals(ItemType.FETISH_REFINED)) {
					
					if(Sex.isDom(Main.game.getPlayer())) {
						Main.game.getPlayer().removeItem(item);
						return "<p>"
									+ "Taking your "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
									+ " Seeing what you're offering [npc.herHim], [npc.she] lets out a little laugh, "
									+ " [npc.speech(Hah! Don't you know demons can't be transfo~Mrph!~)]"
								+ "</p>"
									+ "Not liking the start of [npc.her] response, you quickly remove the bottle's stopper, and rather unceremoniously shove the neck down [npc.her] throat."
									+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to down all of the liquid before finally letting [npc.her] go."
									+ " [npc.She] coughs and splutters for a moment, before letting out a lewd little cry as [npc.she] wipes the liquid from [npc.her] mouth,"
									+ " [npc.speech(W-Wait! That was a fetish transformative?! I feel... hot...)]"
								+ "</p>"
								+ Main.game.getPlayer().useItem(item, target, false);
					} else {
						return "<p>"
									+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
									+ " [npc.speech(Hah! Nice try, but do you really expect me to drink some random potion?!)]</br>"
									+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
								+ "</p>";
					}
					
				} else if(item.getItemType().equals(ItemType.EGGPLANT)) {
					if(Sex.isDom(Main.game.getPlayer())) {
						return "<p>"
									+ "Taking the eggplant from your inventory, you hold it out to [npc.name]."
									+ " Seeing what you're offering [npc.herHim], [npc.she] shifts about uncomfortably, "
									+ " [npc.speech(W-What are you going to do with th-~Mrph!~)]"
								+ "</p>"
								+ "<p>"
									+ "Not liking the start of [npc.her] response, you quickly shove the eggplant into [npc.her] mouth, grinning as you force [npc.herHim] to eat the purple fruit..."
								+ "</p>"
								+Main.game.getPlayer().useItem(item, target, false, true);
					} else {
						return "<p>"
									+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and laughs,"
									+ " [npc.speech(Hah! Did you really think I was going to eat that?!)]</br>"
									+ "You reluctantly put the "+item.getName()+" back in your inventory, disappointed that [npc.she]'s not interested."
								+ "</p>";
					}
						
				} else {
					return "<p>"
								+ "You try to give [npc.name] "+item.getItemType().getDeterminer()+" "+item.getName()+", but [npc.she] refuses to take it. You put the "+item.getName()+" back in your inventory."
							+ "</p>";
				}
			}
			
		// NPC is using an item:
		}else{
			return Sex.getActivePartner().useItem(item, target, false);
		}
	}
	
	public String getSpellDescription() {
		return "<p>"
				+UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] swirls her broomstick around in a mesmerising pattern, before thrusting it at you and casting a spell!",
							"[npc.Name] places her broomstick between her legs, and, thrusting her hips forwards, she lets out an incredibly lewd moan as she casts a spell!",
							"[npc.Name] thrusts her broomstick out into mid-air five times, before letting out a desperate moan and casting a spell!"))
			+"</p>";
	}
	
	public String getSeductionDescription(Attack attackType, boolean isHit) {
		return "<p>"
				+UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] puts on a smouldering look, and as her eyes meet yours, you hear an extremely lewd moan echoing around in your head, [npc.thought(~Aaah!~ You're making me so wet!)]",
							"[npc.Name] locks her big, innocent-looking eyes with yours, and as she pouts, you hear an echoing moan deep within your mind, [npc.thought(~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!)]",
							"[npc.Name] pouts innocently at you, before blowing you a wet kiss. As she straightens back up, you feel a ghostly pair of wet lips press against your cheek."))
				+"</p>";
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", CultistDialogue.ENCOUNTER_CHAPEL_COMBAT_VICTORY);
		} else {
			return new Response ("", "", CultistDialogue.ENCOUNTER_CHAPEL_COMBAT_LOSS);
		}
	}
	
	
	// ****************** Sex & Dirty talk: ***************************
	
	@Override
	public boolean getSexBehaviourDeniesRequests() {
		return true;
	}
	
	public Set<SexPositionSlot> getSexPositionPreferences() {
		sexPositionPreferences.clear();
		
		if(Sex.isInForeplay()) {
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS);
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS);
		} else {
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS);
			sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS);
		}
		
		return sexPositionPreferences;
	}
	
	public SexType getForeplayPreference() {
		if(Sex.getSexPositionSlot(this)==SexPositionSlot.MISSIONARY_ALTAR_KNEELING_BETWEEN_LEGS || Sex.getSexPositionSlot(this)==SexPositionSlot.MISSIONARY_ALTAR_SEALED_KNEELING_BETWEEN_LEGS) {
			if(requestedAnal) {
				return new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.ANUS);
			} else {
				return new SexType(SexParticipantType.PITCHER, PenetrationType.TONGUE, OrificeType.VAGINA);
			}
		} else {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH);
		}
	}
	
	public SexType getMainSexPreference() {
		if(Sex.getSexPositionSlot(this)==SexPositionSlot.MISSIONARY_ALTAR_STANDING_BETWEEN_LEGS || Sex.getSexPositionSlot(this)==SexPositionSlot.MISSIONARY_ALTAR_SEALED_STANDING_BETWEEN_LEGS) {
			if(requestedAnal) {
				return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS);
			} else {
				return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA);
			}
		} else {
			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.MOUTH);
		}
	}
	
	@Override
	public String getCondomEquipEffects(GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex()) {
			if((Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl()) && !target.isPlayer()) {
				return "<p>"
							+ "Holding out a condom to [npc.name], you force [npc.herHim] to take it and put it on."
							+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] whines at you,"
							+ " [npc.speech(This is an insult to Lilith...)]"
						+ "</p>";
			} else if (!target.isPlayer()){
				target.unequipClothingIntoVoid(target.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, equipper);
				return "<p>"
							+ "You pull out a condom and try to give it to [npc.name], but she simply laughs in your face before grabbing the little foil packet and tearing it in two,"
							+ " [npc.speech(I don't think so! You're going to take my seed, and you're going to love it!)]"
						+ "</p>";
			}
		}
		
		return AbstractClothingType.getEquipDescriptions(target, equipper, rough,
				"You tear open the packet and roll the condom down the length of your [pc.penis].",
				"You tear open the packet and roll the condom down the length of [npc.name]'s [npc.penis].",
				"You tear open the packet and forcefully roll the condom down the length [npc.name]'s [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of [npc.her] [npc.penis].",
				"[npc.Name] tears open the packet and rolls the condom down the length of your [pc.penis].",
				"[npc.Name] tears open the packet and forcefully rolls the condom down the length of your [pc.penis].", null, null);
	}
	
//	// Losing virginity: TODO
//	private static StringBuilder StringBuilderSB;
//	public String getPlayerVaginaVirginityLossDescription(boolean isPlayerDom){
//		if(isPlayerDom || Sex.getPenetrationTypeInOrifice(Main.game.getPlayer(), OrificeType.VAGINA)==PenetrationType.TAIL) {
//			return super.getPlayerVaginaVirginityLossDescription(isPlayerDom);
//		}
//		
//		StringBuilderSB = new StringBuilder();
//		
//		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
//			StringBuilderSB.append(
//							"<p>"
//								+ "As the dominant succubus drives her "+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getVaginaName(true)
//									+", your vision explodes in stars, and a painful, high-pitched shriek escapes from between your lips."
//								+ " Due to the fact that you're still a virgin, the sudden, violent penetration is like nothing you've ever felt before,"
//									+ " and your shriek turns into a shuddering cry as you collapse back against the wall in pure agony."
//							+ "</p>"
//							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)?"<p>"
//									+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
//									+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
//									+ "</p>":"")
//							+"<p>"
//								+ "Glancing down, you see a small trickle of blood flowing out of your now-broken-in pussy, and you realise that your hymen has been completely torn."
//								+ " As you let out a desperate sigh, you hear the succubus giggling at your reaction."
//							+ "</p>"
//							+"<p>"
//								+ "Looking up at her, you see a wild, crazed look in her eyes."
//								+ " Another jolt of pain shoots up between your legs as she drives her bumpy, demonic cock even deeper into your "+Main.game.getPlayer().getVaginaName(true)+"."
//								+ " Leaning into you, she pulls you into a sloppy kiss, and with a final, violent thrust into your groin, you feel the base of her cock grind up against your outer labia."
//							+ "</p>"
//							+ "<p>"
//								+ "Still keeping her demonic cock fully hilted in your "+Main.game.getPlayer().getVaginaName(true)+", she breaks off the kiss, raising her hand to cover your mouth as she muffles your painful cries."
//								+ " Playfully biting her lip, she starts taunting you, clearly getting incredibly excited about being the one to take your virginity."
//							+ "</p>"
//							+ "<p>"
//								+ UtilText.parseSpeechNoEffects("~Mmm!~ Doesn't that feel good?! Aww, I wonder if you were saving yourself for someone,", Sex.getPartner())
//								+" she pauses for a moment to let out a mocking laugh, before continuing, "
//								+ UtilText.parseSpeechNoEffects("Well, I guess you were saving yourself for me in the end! Which is a shame, really, because you're just another easy fuck to me.", Sex.getPartner())
//							+ "</p>"
//							+ "<p>"
//								+ "You let out a protesting moan, but the succubus just presses her hand down even tighter over your mouth as she continues, "
//								+ UtilText.parseSpeechNoEffects("What's that? You want me to do it again?!", Sex.getPartner())
//							+ "</p>"
//							+ "<p>"
//								+ "Letting out a manic laugh, the succubus suddenly drops her hips, allowing her "+Sex.getPartner().getPenisName(true)+" to slide fully out of your "+Main.game.getPlayer().getVaginaName(true)+"."
//								+ " Lining herself up once more, she wastes no time before violently thrusting up into you for a second time."
//								+ " Although still uncomfortable enough to cause you to cry out into the palm of her hand, it's nothing like the agony you experienced on the first thrust,"
//									+ " and as you feel her throbbing length filling you up once more, you find yourself letting out a desperate little moan."
//							+ "</p>"
//							+ "<p>"
//								+ "The succubus looks delighted at your reaction, and leans in as she continues teasing you, "
//								+ UtilText.parseSpeechNoEffects("~Ooh!~ So, you're a slut for demon dick? Can you feel it, pushing <i>deep</i>, claiming your precious little virginity?!", Sex.getPartner())
//							+ "</p>"
//							+ "<p>"
//								+ "Dropping her hips a little, she starts thrusting in and out of your "+Main.game.getPlayer().getVaginaName(true)
//									+", taking her hand away from your mouth as you find yourself letting out a series of lewd moans."
//								+ " Moving her hands down to take hold of your waist, she carries on taunting you,"
//									+ " repeating that you'll always remember this moment as the time you discovered that you're just a slut for big, thick demon cock."
//								+ " As the pain between your legs fades away into a dull ache, you find yourself letting out moan after desperate moan, and you start to worry that the succubus might be right..."
//							+ "</p>");
//		} else {
//			StringBuilderSB.append(
//					"<p>"
//						+ "As the dominant succubus drives her "+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getVaginaName(true)
//							+", your vision explodes in stars, and a desperate, high-pitched wail escapes from between your lips."
//						+ " Due to the fact that you're still a virgin, the sudden, violent penetration is like nothing you've ever felt before,"
//							+ " and your wail turns into a shuddering cry as you collapse back against the wall."
//					+ "</p>"
//					+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)?"<p>"
//							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
//							+ " The discomfort between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
//							+ "</p>":"")
//					+"<p>"
//						+ "Letting out a sigh as you realise that your hymen is now completely torn, you look up at the succubus, and see a wild, crazed look in her eyes."
//						+ " Another little painful jolt shoots up between your legs as she drives her bumpy, demonic cock even deeper into your "+Main.game.getPlayer().getVaginaName(true)+"."
//						+ " Leaning into you, she pulls you into a sloppy kiss, and with a final, violent thrust into your groin, you feel the base of her cock grind up against your outer labia."
//					+ "</p>"
//					+ "<p>"
//						+ "Still keeping her demonic cock fully hilted in your "+Main.game.getPlayer().getVaginaName(true)+", she breaks off the kiss, raising her hand to cover your mouth as she muffles your desperate cries."
//						+ " Playfully biting her lip, she starts taunting you, clearly getting incredibly excited about being the one to take your virginity."
//					+ "</p>"
//					+ "<p>"
//						+ UtilText.parseSpeechNoEffects("~Mmm!~ Doesn't that feel good?! Aww, I wonder if you were saving yourself for someone,", Sex.getPartner())
//						+" she pauses for a moment to let out a mocking laugh, before continuing, "
//						+ UtilText.parseSpeechNoEffects("Well, I guess you were saving yourself for me in the end! Which is a shame, really, because you're just another easy fuck to me.", Sex.getPartner())
//					+ "</p>"
//					+ "<p>"
//						+ "You let out a protesting moan, but the succubus just presses her hand down even tighter over your mouth as she continues, "
//						+ UtilText.parseSpeechNoEffects("What's that? You want me to do it again?!", Sex.getPartner())
//					+ "</p>"
//					+ "<p>"
//						+ "Letting out a manic laugh, the succubus suddenly drops her hips, allowing her "+Sex.getPartner().getPenisName(true)+" to slide fully out of your "+Main.game.getPlayer().getVaginaName(true)+"."
//						+ " Lining herself up once more, she wastes no time before violently thrusting up into you for a second time."
//						+ " Although still uncomfortable enough to cause you to cry out into the palm of her hand, it's nothing like the pain you experienced on the first thrust,"
//							+ " and as you feel her throbbing length filling you up once more, you find yourself letting out a desperate little moan."
//					+ "</p>"
//					+ "<p>"
//						+ "The succubus looks delighted at your reaction, and leans in as she continues teasing you, "
//						+ UtilText.parseSpeechNoEffects("~Ooh!~ So, you're a slut for demon dick? Can you feel it, pushing <i>deep</i>, claiming your precious little virginity?!", Sex.getPartner())
//					+ "</p>"
//					+ "<p>"
//						+ "Dropping her hips a little, she starts thrusting in and out of your "+Main.game.getPlayer().getVaginaName(true)
//							+", taking her hand away from your mouth as you find yourself letting out a series of lewd moans."
//						+ " Moving her hands down to take hold of your waist, she carries on taunting you,"
//							+ " repeating that you'll always remember this moment as the time you discovered that you're just a slut for big, thick demon cock."
//						+ " As the pain between your legs fades away into a dull ache, you find yourself letting out moan after desperate moan, and you start to worry that the succubus might be right..."
//					+ "</p>");
//			
//		}
//		
//		StringBuilderSB.append(formatVirginityLoss("Your hymen has been torn; you have lost your virginity!"));
//		
//		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN))
//			StringBuilderSB.append("<p style='text-align:center;'>"
//					+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
//				+ "</p>"
//				+ "<p>"
//					+ "As the succubus carries on pounding away between your legs, the sudden realisation of what's just happened hits you like a sledgehammer."
//				+ "</p>"
//				+ "<p style='text-align:center;'>"
//					+ UtilText.parsePlayerThought("I-I've lost my virginity?!</br>"
//							+ "To... <b>her</b>?!")
//				+ "</p>"
//				+ "<p>"
//					+ "You don't know what's worse, losing the virginity that you prized so highly, or the fact that you're actually enjoying it."
//					+ " As your labia spread lewdly around the hot, thick demon-dick, you find yourself starting to agree with what the succubus is telling you."
//				+ "</p>"
//				+ "<p style='text-align:center;'>"
//				+ UtilText.parsePlayerThought("If I'm not a virgin, that makes me a slut...</br>"
//						+ "Just a slut for demon cock...</br>"
//						+ "She's right, I'm just another easy fuck for someone like her...")
//				+ "</p>"
//				+ "<p>"
//				+ "You're vaguely aware of the succubus's taunts fading away as she starts to focus her concentration on fucking you."
//				+ " With a desperate moan, you start bucking your hips back against her, resigning yourself to the fact that now you're nothing more than a"
//				+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
//				+ "</p>");
//		
//		return UtilText.parse(Sex.getPartner(),
//				StringBuilderSB.toString());
//	}
//	
//	// Dirty talk:
//	
//	@Override
//	public String getDirtyTalkNoPenetration(boolean isPlayerDom){
//		List<String> speech = new ArrayList<>();
//		
//		if(isPlayerDom){
//			speech.add("Come on, fuck me already!");
//			speech.add("Come on! What's taking so long?!");
//			speech.add("Fuck me already!");
//			speech.add("Let's get started! Come on!");
//		} else {
//			speech.add("I'm gonna turn you into a slut for demon cock!");
//			speech.add("You ever been fucked by a demon?");
//			speech.add("You're going to be begging for my cum before the end!");
//			speech.add("You're going to be a good little bitch!");
//		}
//		
//		return speech.get(Util.random.nextInt(speech.size()));
//	}
//	
	
}
