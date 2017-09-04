package com.base.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.base.game.character.CharacterUtils;
import com.base.game.character.GameCharacter;
import com.base.game.character.Name;
import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.types.PenisType;
import com.base.game.character.body.types.VaginaType;
import com.base.game.character.effects.Fetish;
import com.base.game.character.effects.StatusEffect;
import com.base.game.character.gender.Gender;
import com.base.game.character.npc.NPC;
import com.base.game.character.race.Race;
import com.base.game.character.race.RaceStage;
import com.base.game.character.race.RacialBody;
import com.base.game.combat.Attack;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.npcDialogue.DominionSuccubus;
import com.base.game.dialogue.responses.Response;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.inventory.CharacterInventory;
import com.base.game.inventory.clothing.AbstractClothingType;
import com.base.game.inventory.clothing.ClothingType;
import com.base.game.inventory.enchanting.TFEssence;
import com.base.game.inventory.item.AbstractItem;
import com.base.game.inventory.item.ItemType;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.Sex;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.Value;
import com.base.utils.Vector2i;
import com.base.world.WorldType;
import com.base.world.places.Dominion;

/**
 * @since 0.1.69
 * @version 0.1.82
 * @author Innoxia
 */
public class NPCRandomSuccubus extends NPC {

	private static final long serialVersionUID = 1L;

	public NPCRandomSuccubus() {
		super(null, "", 5, Gender.FEMALE, RacialBody.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, Dominion.CITY_BACK_ALLEYS, false);

		setAttribute(Attribute.STRENGTH, (int)(this.getAttributeValue(Attribute.STRENGTH) * (0.75f + (Math.random()/2))));
		setAttribute(Attribute.INTELLIGENCE, (int)(this.getAttributeValue(Attribute.INTELLIGENCE) * (0.75f + (Math.random()/2))));
		setAttribute(Attribute.FITNESS, (int)(this.getAttributeValue(Attribute.FITNESS) * (0.75f + (Math.random()/2))));
		setAttribute(Attribute.CORRUPTION, 100);

		this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
		this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
		
		// BODY RANDOMISATION:
		addFetish(Fetish.FETISH_DEFLOWERING);
		addFetish(Fetish.FETISH_DOMINANT);
		CharacterUtils.randomiseBody(this);
		
		this.setVaginaVirgin(false);
		this.setAssVirgin(false);
		this.setFaceVirgin(false);
		this.setNippleVirgin(false);
		
		setLevel(Util.random.nextInt(3) + 4);
		
		setName(Name.getRandomTriplet(Race.DEMON));
		this.setPlayerKnowsName(false);
		setDescription("Although all demons have an extremely powerful libido, some suffer from it far more than others."
				+ " While most are able to control their sexual desires, others, such as this succubus, struggle to think of anything but how to secure their next sexual conquest.");
		
		// Set random inventory & weapons:
		resetInventory();
		inventory.setMoney(50);
		
		// CLOTHING:
		
		List<AbstractClothingType> clothingChoices = new ArrayList<>();
		
		clothingChoices.add(ClothingType.GROIN_THONG);
		clothingChoices.add(ClothingType.GROIN_VSTRING);
		equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.CHEST_PLUNGE_BRA);
		equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.TORSO_BODYCONZIP_DRESS);
		clothingChoices.add(ClothingType.TORSO_SKATER_DRESS);
		clothingChoices.add(ClothingType.TORSO_PLUNGE_DRESS);
		equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.SOCK_TIGHTS);
		equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), false), true, this);
		
		clothingChoices.clear();
		clothingChoices.add(ClothingType.FOOT_HEELS);
		equipClothingFromNowhere(AbstractClothingType.generateClothing(clothingChoices.get(Util.random.nextInt(clothingChoices.size())), false), true, this);

		setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
		setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		setStamina(getAttributeValue(Attribute.STAMINA_MAXIMUM));
		
	}

	@Override
	public void endSex(boolean applyEffects) {
		if (applyEffects) {
			setVaginaType(VaginaType.DEMON_COMMON);
			setPenisType(PenisType.NONE);
			setPendingClothingDressing(true);
		}
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
	public void applyReset() {
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return DominionSuccubus.ALLEY_DEMON_ATTACK;
	}

	// Combat:

	@Override
	public String getCombatDescription() {
		return "Although strong enough to easily overpower most solitary travellers, this horny demon is finding it difficult to focus on harnessing her arcane aura, which gives you a fighting chance against her.";
	}
	
	@Override
	public Attack attackType() {
		if (Math.random() < 0.1)
			return Attack.MAIN;
		else
			return Attack.SEDUCTION;
	}
	
	@Override
	public Map<TFEssence, Integer> getLootEssenceDrops() {
		return Util.newHashMapOfValues(new Value<>(TFEssence.DEMON, Util.random.nextInt(6)+6), new Value<>(TFEssence.ARCANE, Util.random.nextInt(2)+1));
	}
	
	@Override
	public String getLostVirginityDescriptor() {
		return "in the streets of Dominion";
	}

	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()){
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			}else{
				if(item.getItemType().equals(ItemType.CONDOM)) {
						if(Sex.isPlayerDom()) {
							if(target.isWearingCondom()) {
								return "<p>"
										+ "[npc.Name] is already wearing a condom, and [npc.she] refuses to wear two at once."
										+ "</p>";
								
							} else if(target.hasPenis()) {
								Main.game.getPlayer().useItem(item, target, false);
								return "<p>"
										+ "Holding out a condom to [npc.name], you force [npc.her] to take it and put it on."
										+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] whines at you,"
										+ " [npc.speech(Do I really have to? It feels so much better without one...)]"
										+ "</p>";
							} else {
								return "<p>"
										+ "[npc.Name] doesn't have a penis, so [npc.she] can't use the condom!"
										+ "</p>";
							}
						} else {
							Main.game.getPlayer().removeItem(item);
							return "<p>"
								+ "You pull out a condom and try to give it to the horny succubus, but she simply laughs in your face before grabbing the little foil packet and tearing it in two."
								+ " Letting out a little laugh, she mocks your attempt at trying to get her to wear a rubber, "
								+ "[npc.speech(Hah! I don't think so!)]"
							+ "</p>";
						}

				} else if(item.getItemType().equals(ItemType.PROMISCUITY_PILL)) {
					
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isPlayerDom()) {
							return "<p>"
									+ "Holding out a 'Promiscuity pill' to [npc.name], you tell [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
									+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
									+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
									+ "</p>";
						} else {
							return "<p>"
									+ "Holding out a 'Promiscuity pill' to [npc.name], you ask [npc.her] to swallow it so that you don't have to worry about any unexpected pregnancies."
									+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
									+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.VIXENS_VIRILITY)) {

						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isPlayerDom()) {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you tell [npc.her] to swallow it."
									+ " Letting out a reluctant sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] whines at you,"
									+ " [npc.speech(Fine! I kinda like the taste of these things anyway...)]"
									+ "</p>";
						} else {
							return "<p>"
									+ "Holding out a 'Vixen's Virility' pill to [npc.name], you ask [npc.her] to swallow it."
									+ " Letting out an annoyed sigh, [npc.she] nevertheless takes the pill out of your hand, and, popping it out of its wrapping, [npc.she] growls at you,"
									+ " [npc.speech(Fine! I don't care either way, but I kinda like the taste of these things...)]"
									+ "</p>";
						}
						
				} else if(item.getItemType().equals(ItemType.POTION) || item.getItemType().equals(ItemType.ELIXIR)) {
					
						if(Sex.isPlayerDom()) {
							Main.game.getPlayer().removeItem(item);
							return "<p>"
										+ "Taking your "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
										+ " Seeing what you're offering [npc.herHim], [npc.she] lets out a little laugh, "
										+ " [npc.speech(Hah! Don't you know demons can't be transfo~Mrph!~)]"
									+ "</p>"
										+ "Not liking the start of [npc.her] response, you quickly remove the bottle's stopper, and, rather unceremoniously, shove the neck down [npc.her] throat."
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
						
				} else if(item.getItemType().equals(ItemType.EGGPLANT)) {
						if(Sex.isPlayerDom()) {
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
			return Sex.getPartner().useItem(item, target, false);
		}
	}
	
	@Override
	public String getAttackDescription(Attack attackType, boolean isHit) {

		if (attackType == Attack.MAIN) {
			switch (Util.random.nextInt(3)) {
				case 0:
					return UtilText.genderParsing(this,
							"<p>"
									+ getName("The")+ " looks annoyed that you're trying to put up a fight, and leaps forwards to deliver a stinging slap across your face."
							+ "</p>");
				case 1:
					return UtilText.genderParsing(this,
							"<p>"
									+ "With an angry little click of her tongue, "+ getName("the")+ " slaps you across the face."
							+ "</p>");
				default:
					return UtilText.genderParsing(this,
							"<p>"
									+ "With a frustrated whine, "+ getName("the")+ " kicks out at your shins."
							+ "</p>");
			}
		} else {
			switch (Util.random.nextInt(3)) {
				case 0:
					return "<p>"
								+ UtilText.genderParsing(this, getName("The") + " puts on a smouldering look, and as her eyes meet yours, you hear an extremely lewd moan echoing around in your head, ")
								+ UtilText.parseThought("~Aaah!~ You're making me so wet!", this)
							+ "</p>";
				case 1:
					return "<p>"
								+ UtilText.genderParsing(this, getName("The") + " locks her big, innocent-looking eyes with yours, and as she pouts, you hear an echoing moan deep within your mind, ")
								+ UtilText.parseThought("~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!", this)
							+ "</p>";
				default:
					return "<p>"
								+ UtilText.genderParsing(this, getName("The") + " pouts innocently at you, before blowing you a wet kiss."
										+ " As she straightens back up, you feel a ghostly pair of wet lips press against your cheek.")
							+ "</p>";
			}
		}
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", DominionSuccubus.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", DominionSuccubus.AFTER_COMBAT_DEFEAT);
		}
	}
	
	

	// Losing virginity:
	private static StringBuilder StringBuilderSB;
	public String getPlayerVaginaVirginityLossDescription(boolean isPlayerDom){
		if(isPlayerDom || Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)==PenetrationType.TAIL_PARTNER) {
			return super.getPlayerVaginaVirginityLossDescription(isPlayerDom);
		}
		
		StringBuilderSB = new StringBuilder();
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			StringBuilderSB.append(
							"<p>"
								+ "As the dominant succubus drives her "+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getVaginaName(true)
									+", your vision explodes in stars, and a painful, high-pitched shriek escapes from between your lips."
								+ " Due to the fact that you're still a virgin, the sudden, violent penetration is like nothing you've ever felt before,"
									+ " and your shriek turns into a shuddering cry as you collapse back against the wall in pure agony."
							+ "</p>"
							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)?"<p>"
									+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
									+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
									+ "</p>":"")
							+"<p>"
								+ "Glancing down, you see a small trickle of blood flowing out of your now-broken-in pussy, and you realise that your hymen has been completely torn."
								+ " As you let out a desperate sigh, you hear the succubus giggling at your reaction."
							+ "</p>"
							+"<p>"
								+ "Looking up at her, you see a wild, crazed look in her eyes."
								+ " Another jolt of pain shoots up between your legs as she drives her bumpy, demonic cock even deeper into your "+Main.game.getPlayer().getVaginaName(true)+"."
								+ " Leaning into you, she pulls you into a sloppy kiss, and with a final, violent thrust into your groin, you feel the base of her cock grind up against your outer labia."
							+ "</p>"
							+ "<p>"
								+ "Still keeping her demonic cock fully hilted in your "+Main.game.getPlayer().getVaginaName(true)+", she breaks off the kiss, raising her hand to cover your mouth as she muffles your painful cries."
								+ " Playfully biting her lip, she starts taunting you, clearly getting incredibly excited about being the one to take your virginity."
							+ "</p>"
							+ "<p>"
								+ UtilText.parseSpeechNoEffects("~Mmm!~ Doesn't that feel good?! Aww, I wonder if you were saving yourself for someone,", Sex.getPartner())
								+" she pauses for a moment to let out a mocking laugh, before continuing, "
								+ UtilText.parseSpeechNoEffects("Well, I guess you were saving yourself for me in the end! Which is a shame, really, because you're just another easy fuck to me.", Sex.getPartner())
							+ "</p>"
							+ "<p>"
								+ "You let out a protesting moan, but the succubus just presses her hand down even tighter over your mouth as she continues, "
								+ UtilText.parseSpeechNoEffects("What's that? You want me to do it again?!", Sex.getPartner())
							+ "</p>"
							+ "<p>"
								+ "Letting out a manic laugh, the succubus suddenly drops her hips, allowing her "+Sex.getPartner().getPenisName(true)+" to slide fully out of your "+Main.game.getPlayer().getVaginaName(true)+"."
								+ " Lining herself up once more, she wastes no time before violently thrusting up into you for a second time."
								+ " Although still uncomfortable enough to cause you to cry out into the palm of her hand, it's nothing like the agony you experienced on the first thrust,"
									+ " and as you feel her throbbing length filling you up once more, you find yourself letting out a desperate little moan."
							+ "</p>"
							+ "<p>"
								+ "The succubus looks delighted at your reaction, and leans in as she continues teasing you, "
								+ UtilText.parseSpeechNoEffects("~Ooh!~ So, you're a slut for demon dick? Can you feel it, pushing <i>deep</i>, claiming your precious little virginity?!", Sex.getPartner())
							+ "</p>"
							+ "<p>"
								+ "Dropping her hips a little, she starts thrusting in and out of your "+Main.game.getPlayer().getVaginaName(true)
									+", taking her hand away from your mouth as you find yourself letting out a series of lewd moans."
								+ " Moving her hands down to take hold of your waist, she carries on taunting you,"
									+ " repeating that you'll always remember this moment as the time you discovered that you're just a slut for big, thick demon cock."
								+ " As the pain between your legs fades away into a dull ache, you find yourself letting out moan after desperate moan, and you start to worry that the succubus might be right..."
							+ "</p>");
		} else {
			StringBuilderSB.append(
					"<p>"
						+ "As the dominant succubus drives her "+Sex.getPartner().getPenisName(true)+" deep into your "+Main.game.getPlayer().getVaginaName(true)
							+", your vision explodes in stars, and a desperate, high-pitched wail escapes from between your lips."
						+ " Due to the fact that you're still a virgin, the sudden, violent penetration is like nothing you've ever felt before,"
							+ " and your wail turns into a shuddering cry as you collapse back against the wall."
					+ "</p>"
					+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)?"<p>"
							+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
							+ " The discomfort between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
							+ "</p>":"")
					+"<p>"
						+ "Letting out a sigh as you realise that your hymen is now completely torn, you look up at the succubus, and see a wild, crazed look in her eyes."
						+ " Another little painful jolt shoots up between your legs as she drives her bumpy, demonic cock even deeper into your "+Main.game.getPlayer().getVaginaName(true)+"."
						+ " Leaning into you, she pulls you into a sloppy kiss, and with a final, violent thrust into your groin, you feel the base of her cock grind up against your outer labia."
					+ "</p>"
					+ "<p>"
						+ "Still keeping her demonic cock fully hilted in your "+Main.game.getPlayer().getVaginaName(true)+", she breaks off the kiss, raising her hand to cover your mouth as she muffles your desperate cries."
						+ " Playfully biting her lip, she starts taunting you, clearly getting incredibly excited about being the one to take your virginity."
					+ "</p>"
					+ "<p>"
						+ UtilText.parseSpeechNoEffects("~Mmm!~ Doesn't that feel good?! Aww, I wonder if you were saving yourself for someone,", Sex.getPartner())
						+" she pauses for a moment to let out a mocking laugh, before continuing, "
						+ UtilText.parseSpeechNoEffects("Well, I guess you were saving yourself for me in the end! Which is a shame, really, because you're just another easy fuck to me.", Sex.getPartner())
					+ "</p>"
					+ "<p>"
						+ "You let out a protesting moan, but the succubus just presses her hand down even tighter over your mouth as she continues, "
						+ UtilText.parseSpeechNoEffects("What's that? You want me to do it again?!", Sex.getPartner())
					+ "</p>"
					+ "<p>"
						+ "Letting out a manic laugh, the succubus suddenly drops her hips, allowing her "+Sex.getPartner().getPenisName(true)+" to slide fully out of your "+Main.game.getPlayer().getVaginaName(true)+"."
						+ " Lining herself up once more, she wastes no time before violently thrusting up into you for a second time."
						+ " Although still uncomfortable enough to cause you to cry out into the palm of her hand, it's nothing like the pain you experienced on the first thrust,"
							+ " and as you feel her throbbing length filling you up once more, you find yourself letting out a desperate little moan."
					+ "</p>"
					+ "<p>"
						+ "The succubus looks delighted at your reaction, and leans in as she continues teasing you, "
						+ UtilText.parseSpeechNoEffects("~Ooh!~ So, you're a slut for demon dick? Can you feel it, pushing <i>deep</i>, claiming your precious little virginity?!", Sex.getPartner())
					+ "</p>"
					+ "<p>"
						+ "Dropping her hips a little, she starts thrusting in and out of your "+Main.game.getPlayer().getVaginaName(true)
							+", taking her hand away from your mouth as you find yourself letting out a series of lewd moans."
						+ " Moving her hands down to take hold of your waist, she carries on taunting you,"
							+ " repeating that you'll always remember this moment as the time you discovered that you're just a slut for big, thick demon cock."
						+ " As the pain between your legs fades away into a dull ache, you find yourself letting out moan after desperate moan, and you start to worry that the succubus might be right..."
					+ "</p>");
			
		}
		
		StringBuilderSB.append(formatVirginityLoss("Your hymen has been torn; you have lost your virginity!"));
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN))
			StringBuilderSB.append("<p style='text-align:center;'>"
					+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
				+ "</p>"
				+ "<p>"
					+ "As the succubus carries on pounding away between your legs, the sudden realisation of what's just happened hits you like a sledgehammer."
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("I-I've lost my virginity?!</br>"
							+ "To... <b>her</b>?!")
				+ "</p>"
				+ "<p>"
					+ "You don't know what's worse, losing the virginity that you prized so highly, or the fact that you're actually enjoying it."
					+ " As your labia spread lewdly around the thick, ribbed demon-dick, you find yourself starting to agree with what the succubus is telling you."
				+ "</p>"
				+ "<p style='text-align:center;'>"
				+ UtilText.parsePlayerThought("If I'm not a virgin, that makes me a slut...</br>"
						+ "Just a slut for demon cock...</br>"
						+ "She's right, I'm just another easy fuck for someone like her...")
				+ "</p>"
				+ "<p>"
				+ "You're vaguely aware of the succubus's taunts fading away as she starts to focus her concentration on fucking you."
				+ " With a desperate moan, you start bucking your hips back against her, resigning yourself to the fact that now you're nothing more than a"
				+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
				+ "</p>");
		
		return UtilText.genderParsing(Sex.getPartner(),
				StringBuilderSB.toString());
	}
	
	// Dirty talk:
	
	@Override
	public String getDirtyTalkNoPenetration(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(isPlayerDom){
			speech.add(UtilText.genderParsing(Sex.getPartner(),"Come on, fuck me already!"));
			speech.add("Come on! What's taking so long?!");
			speech.add("Fuck me already!");
			speech.add("Let's get started! Come on!");
		} else {
			speech.add(UtilText.genderParsing(Main.game.getPlayer(),"I'm gonna turn you into a slut for demon cock!"));
			speech.add("You ever been fucked by a demon?");
			speech.add("You're going to be begging for my cum before the end!");
			speech.add("You're going to be a good little bitch!");
		}
		
		return speech.get(Util.random.nextInt(speech.size()));
	}
	
	@Override
	public String getDirtyTalkVaginaPenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(getVaginaType()!=VaginaType.NONE) {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER) != null) {
				switch(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PARTNER)) {
					case FINGER_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"Fuck!",
								"Yeah!",
								"Oh yeah!"));
						break;
					case FINGER_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"Push deeper!",
								"Keep going!",
								"Don't stop!"));
						break;
					case PENIS_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"Fuck!",
								"Yeah!",
								"Oh yeah!"));
						break;
					case PENIS_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"Fuck me! Yes! Harder!",
								"Oh yeah! Fuck me!",
								"Harder! Don't stop!"));
						break;
					case TAIL_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"Fuck!",
								"Yeah!",
								"Oh yeah!"));
						break;
					case TAIL_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"Fuck me! Yes! Harder!",
								"Oh yeah! Fuck me!",
								"Deeper! Don't stop!"));
						break;
					case TONGUE_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"Fuck!",
								"Yeah!",
								"Oh yeah!"));
						break;
					case TONGUE_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"Yes! Get that tongue deeper!",
								"Oh yeah! Keep going!",
								"Deeper! Don't stop!"));
						break;
					default:
						break;
				}
			}
		}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
	@Override
	public String getDirtyTalkAssPenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER) != null)
			switch(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PARTNER)) {
				case FINGER_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case FINGER_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Push deeper!",
							"Keep going!",
							"Don't stop!"));
					break;
				case PENIS_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case PENIS_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck me! Yes! Harder!",
							"Oh yeah! Fuck me!",
							"Harder! Don't stop!"));
					break;
				case TAIL_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case TAIL_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck me! Yes! Harder!",
							"Oh yeah! Fuck me!",
							"Deeper! Don't stop!"));
					break;
				case TONGUE_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case TONGUE_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Yes! Get that tongue deeper!",
							"Oh yeah! Keep going!",
							"Deeper! Don't stop!"));
					break;
				default:
					break;
			}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
	@Override
	public String getDirtyTalkMouthPenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER) != null)
			switch(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PARTNER)) {
				case FINGER_PARTNER:
					break;
				case FINGER_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"You taste good!"));
					break;
				case PENIS_PARTNER:
					break;
				case PENIS_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"I love your cock!",
							"I love sucking cocks!",
							"I'm a good cock-sucker!"));
					break;
				case TAIL_PARTNER:
					break;
				case TAIL_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Deeper! Don't stop!"));
					break;
				case TONGUE_PARTNER:
					break;
				case TONGUE_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Oh yes!",
							"I love the taste of your lips!",
							"Don't stop!"));
					break;
				default:
					break;
			}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
	@Override
	public String getDirtyTalkNipplePenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER) != null)
			switch(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PARTNER)) {
				case FINGER_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case FINGER_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Push deeper!",
							"Keep going!",
							"Don't stop!"));
					break;
				case PENIS_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case PENIS_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck me! Yes! Harder!",
							"Oh yeah! Fuck me!",
							"Harder! Don't stop!"));
					break;
				case TAIL_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case TAIL_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck me! Yes! Harder!",
							"Oh yeah! Fuck me!",
							"Deeper! Don't stop!"));
					break;
				case TONGUE_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Fuck!",
							"Yeah!",
							"Oh yeah!"));
					break;
				case TONGUE_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Yes! Get that tongue deeper!",
							"Oh yeah! Keep going!",
							"Deeper! Don't stop!"));
					break;
				default:
					break;
			}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
	// Dirty talk related to penetrating areas:
	
	@Override
	public String getDirtyTalkPlayerVaginaPenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(Main.game.getPlayer().getVaginaType()!=VaginaType.NONE) {
			if(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER) != null) {
				switch(Sex.getPenetrationTypeInOrifice(OrificeType.VAGINA_PLAYER)) {
					case FINGER_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"You like this?",
								"Your pussy's so soft!",
								"How deep can I get my fingers?"));
						break;
					case FINGER_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"That's right, keep touching yourself!",
								"You trying to get yourself off?",
								"Go on, keep using your fingers!"));
						break;
					case PENIS_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"Ah yeah! You're just a slut for demon-dick!",
								"Fuck! Yeah! Take my cock!",
								"Ah yeah! You love demon-cock, don't you?!"));
						break;
					case PENIS_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"Now that's something you don't see every day...",
								"Uhh... Are you actually fucking yourself right now?",
								"Can you get yourself pregnant from that?!"));
						break;
					case TAIL_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"That's right, take it deep!",
								"You like my tail?",
								"My tail's having a real fun time!"));
						break;
					case TAIL_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"That's right, keep fucking yourself!",
								"You like using your own tail, huh?",
								"You're fucking yourself with your own tail?"));
						break;
					case TONGUE_PARTNER:
						speech.add(UtilText.returnStringAtRandom(
								"I love your pussy!",
								"You taste so good!",
								"You like my tongue?"));
						break;
					case TONGUE_PLAYER:
						speech.add(UtilText.returnStringAtRandom(
								"Are you eating yourself out right now?!",
								"You're eating yourself out?!",
								"That's not something I see very often..."));
						break;
					default:
						break;
				}
			}
		}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
	@Override
	public String getDirtyTalkPlayerAssPenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER) != null)
			switch(Sex.getPenetrationTypeInOrifice(OrificeType.ANUS_PLAYER)) {
				case FINGER_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"You like this?",
							"I love fingering your asshole!",
							"How deep can I get my fingers?"));
					break;
				case FINGER_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"That's right, get those fingers deep!",
							"Are you fingering your ass?!",
							"Go on, keep fingering your cute little ass!"));
					break;
				case PENIS_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Ah yeah! Take my cock!",
							"Fuck! Yeah!",
							"Ah yeah! You love my cock, don't you?!"));
					break;
				case PENIS_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Now that's something you don't see every day...",
							"Uhh... Are you actually fucking yourself right now?",
							"You're fucking your own ass?!"));
					break;
				case TAIL_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"That's right, take it deep!",
							"You like my tail?",
							"My tail's having a real fun time!"));
					break;
				case TAIL_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"That's right, keep fucking yourself!",
							"You like using your own tail, huh?",
							"You're fucking yourself with your own tail?"));
					break;
				case TONGUE_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"You like this?",
							"I'll get you all nice and wet!",
							"You like my tongue?"));
					break;
				case TONGUE_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Are you licking your own asshole right now?!",
							"You're licking your own asshole?!",
							"That's not something I see very often..."));
					break;
				default:
					break;
			}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
	@Override
	public String getDirtyTalkPlayerMouthPenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER) != null)
			switch(Sex.getPenetrationTypeInOrifice(OrificeType.MOUTH_PLAYER)) {
				case FINGER_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"You like this?",
							"Keep sucking!",
							"That's right, keep sucking!"));
					break;
				case FINGER_PLAYER:
					break;
				case PENIS_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Oh yeah! Keep sucking!",
							"You're good at sucking cock!",
							"Ah yeah! You love my cock, don't you?!"));
					break;
				case PENIS_PLAYER:
					break;
				case TAIL_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"That's right, take it deep!",
							"You like my tail?",
							"Keep sucking on my tail!"));
					break;
				case TAIL_PLAYER:
					break;
				case TONGUE_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Oh yes!",
							"I love the taste of your lips!",
							"Don't stop!"));
					break;
				case TONGUE_PLAYER:
					break;
				default:
					break;
			}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
	@Override
	public String getDirtyTalkPlayerNipplePenetrated(boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER) != null)
			switch(Sex.getPenetrationTypeInOrifice(OrificeType.NIPPLE_PLAYER)) {
				case FINGER_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"You like this?",
							"I love fingering your nipples!",
							"How deep can I get my fingers?"));
					break;
				case FINGER_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"That's right, get those fingers deep!",
							"Are you fingering your nipples?!",
							"Go on, keep fingering your cute little nipples!"));
					break;
				case PENIS_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"Ah yeah! Take my cock!",
							"Fuck! Yeah!",
							"Ah yeah! You love my cock, don't you?!"));
					break;
				case PENIS_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"Now that's something you don't see every day...",
							"Uhh... Are you actually fucking your own nipples right now?",
							"You're fucking your own nipples?!"));
					break;
				case TAIL_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"That's right, take it deep!",
							"You like my tail?",
							"My tail's having a real fun time!"));
					break;
				case TAIL_PLAYER:
					speech.add(UtilText.returnStringAtRandom(
							"That's right, keep fucking yourself!",
							"You like using your own tail, huh?",
							"You're fucking your own nipples with your tail?"));
					break;
				case TONGUE_PARTNER:
					speech.add(UtilText.returnStringAtRandom(
							"You like this?",
							"Your nipples taste good!",
							"You like my tongue?"));
					break;
				case TONGUE_PLAYER:
					break;
				default:
					break;
			}
		
		if(speech.isEmpty()) {
			return null;
		} else {
			return speech.get(Util.random.nextInt(speech.size()));
		}
	}
	
//	@Override
//	public String getDirtyTalkPlayerMoundPenetrated(boolean isPlayerDom){
//		List<String> speech = new ArrayList<>();
//		
//		if(Sex.getPlayerPenetratedMap().get(CoverableArea.MOUND) != null)
//			switch(Sex.getPlayerPenetratedMap().get(CoverableArea.MOUND)) {
//				case FINGER_PARTNER:
//					speech.add(UtilText.returnStringAtRandom(
//							"You like this?",
//							"Your little mound is so soft!",
//							"You're like a little doll down here!"));
//					break;
//				case FINGER_PLAYER:
//					break;
//				case PENIS_PARTNER:
//					break;
//				case PENIS_PLAYER:
//					break;
//				case TAIL_PARTNER:
//					break;
//				case TAIL_PLAYER:
//					break;
//				case TONGUE_PARTNER:
//					speech.add(UtilText.returnStringAtRandom(
//							"You like this?",
//							"Your little mound tastes good!",
//							"You like my tongue?"));
//					break;
//				case TONGUE_PLAYER:
//					break;
//				default:
//					break;
//			}
//		
//		if(speech.isEmpty()) {
//			return null;
//		} else {
//			return speech.get(Util.random.nextInt(speech.size()));
//		}
//	}
	
}
