package com.lilithsthrone.game.character.npc.dominion;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.DominionSuccubusDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.game.sex.PenetrationType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.2.2
 * @author Innoxia
 */
public class DominionSuccubusAttacker extends NPC {

	private static final long serialVersionUID = 1L;
	
	public DominionSuccubusAttacker() {
		this(false);
	}
	
	public DominionSuccubusAttacker(boolean isImported) {
		super(null, "", 5, Gender.F_V_B_FEMALE, RacialBody.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
			
			// BODY RANDOMISATION:
			addFetish(Fetish.FETISH_DEFLOWERING);
			addFetish(Fetish.FETISH_DOMINANT);
			CharacterUtils.addFetishes(this);
			
			if(!GenderPreference.getGenderFromUserPreferences().isFeminine()) {
				this.setBody(Gender.M_P_MALE, RacialBody.DEMON, RaceStage.GREATER);
				this.setGenderIdentity(Gender.M_P_MALE);
			}
			
			CharacterUtils.randomiseBody(this);
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			setLevel(Util.random.nextInt(3) + 4);
			
			setName(Name.getRandomTriplet(Race.DEMON));
			this.setPlayerKnowsName(false);
			
			// Set random inventory & weapons:
			resetInventory();
			inventory.setMoney(50);
			CharacterUtils.generateItemsInInventory(this);
			
			// CLOTHING:
			
			this.equipClothing(true, false);
			
			CharacterUtils.applyMakeup(this, true);
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		this.setEnslavementDialogue(DominionSuccubusDialogue.ENSLAVEMENT_DIALOGUE);
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

	@Override
	public String getDescription() {
		return UtilText.parse(this,
				"Although all demons have an extremely powerful libido, some suffer from it far more than others."
				+ " While most are able to control their sexual desires, others, such as this [npc.race], struggle to think of anything but how to secure their next sexual conquest.");
	}
	
	@Override
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		CharacterUtils.equipClothing(this, replaceUnsuitableClothing, onlyAddCoreClothing);
	}
	
	@Override
	public void endSex(boolean applyEffects) {
		if (applyEffects) {
			if(!isSlave()) {
				if(this.getGender()!=this.getGenderIdentity() && !this.isPregnant()) {
					this.setPendingTransformationToGenderIdentity(true);
				}
//				setVaginaType(VaginaType.DEMON_COMMON);
//				setPenisType(PenisType.NONE);
				setPendingClothingDressing(true);
			}
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
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		return DominionSuccubusDialogue.ALLEY_DEMON_ATTACK;
	}

	// Combat:

	@Override
	public String getCombatDescription() {
		return UtilText.parse(this,
				"Although strong enough to easily overpower most solitary travellers, this horny [npc.race] is finding it difficult to focus on harnessing [npc.her] arcane aura, resulting in [npc.herHim] being far weaker than a normal demon.");
	}

	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()){
			// Player uses item on themselves:
			if(target.isPlayer()){
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			}else{
				if(item.getItemType().equals(ItemType.PROMISCUITY_PILL)) {
					
						Main.game.getPlayer().useItem(item, target, false);
						if(Sex.isDom(Main.game.getPlayer())) {
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
						if(Sex.isDom(Main.game.getPlayer())) {
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

	@Override
	public String getMainAttackDescription(boolean isHit) {
		if(this.isFeminine()) {
			return UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] looks annoyed that you're trying to put up a fight, and leaps forwards to deliver a stinging slap across your face.",
							"With an angry little click of her tongue, [npc.Name] slaps you across the face.",
							"With a frustrated whine, [npc.Name] kicks out at your shins."));
		} else {
			return UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] looks annoyed that you're trying to put up a fight, and leaps forwards to deliver a solid punch to your [pc.arm].",
							"With an angry shout, [npc.Name] darts forwards and punches you right in the chest!",
							"With a frustrated cry, [npc.Name] kicks out at your shins."));
		}
	}
	
	@Override
	public String getSeductionDescription() {
		if(this.isFeminine()) {
			return UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] puts on a smouldering look, and as her eyes meet yours, you hear an extremely lewd moan echoing around in your head, [npc.thought(~Aaah!~ "
									+(this.hasVagina()
											?"You're making me so wet!"
											:this.hasPenis()
												?"You're getting me so hard!"
												:"You're turning me on so much!")+")]",
							"[npc.Name] locks her big, innocent-looking eyes with yours, and as she pouts, you hear an echoing moan deep within your mind, [npc.thought("+
									(this.hasVagina()
											?"~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!"
											:this.hasPenis()
												?"~Mmm!~ I can't wait to fuck you! ~Aaa!~ You're going to love my cock!"
												:"~Mmm!~ Fuck me! ~Aaa!~ I need you so badly!")+")]",
							"[npc.Name] pouts innocently at you, before blowing you a wet kiss. As she straightens back up, you feel a ghostly pair of wet lips press against your cheek."));
		} else {
			return UtilText.parse(this,
					UtilText.returnStringAtRandom(
							"[npc.Name] puts on a confident look, and as his eyes meet yours, you hear an extremely lewd groan echoing around in your head, [npc.thought(~Mmm!~ "
									+(this.hasVagina()
											?"You're making me so wet!"
											:this.hasPenis()
												?"You're getting me so hard!"
												:"You're turning me on so much!")+")]",
							"[npc.Name] locks his eyes with yours, and as he throws you a charming smile, you hear an echoing groan deep within your mind, [npc.thought("+
									(this.hasVagina()
											?"~Mmm!~ Fuck me! ~Aaa!~ My pussy's wet and ready for you!"
											:this.hasPenis()
												?"~Mmm!~ I can't wait to fuck you! You're going to love my cock!"
												:"~Mmm!~ I can't wait to have some fun with you!")+")]",
							"[npc.Name] throws you a charming smile, before winking at you and striking a heroic pose. As he straightens back up, you feel a ghostly pair of arms pulling you into a strong, confident embrace."));
		}
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", DominionSuccubusDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", DominionSuccubusDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	
	// ****************** Sex & Dirty talk: ***************************
	
//	@Override
//	public SexType getForeplayPreference() {
//		if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
//			return new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.VAGINA);
//			
//		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//			return new SexType(SexParticipantType.PITCHER, PenetrationType.FINGER, OrificeType.ANUS);
//		}
//		
//		return foreplayPreference;
//	}
//	
//	@Override
//	public SexType getMainSexPreference() {
//		if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
//			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.VAGINA);
//			
//		} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//			return new SexType(SexParticipantType.PITCHER, PenetrationType.PENIS, OrificeType.ANUS);
//		}
//		
//		return mainSexPreference;
//	}
//	
//	public Set<SexPositionSlot> getSexPositionPreferences() {
//		sexPositionPreferences.clear();
//		
//		if(Sex.isInForeplay()) {
//			if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
//				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
//				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
//				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
//				
//			} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
//				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
//				
//			} else {
//				return super.getSexPositionPreferences();
//			}
//			
//		} else {
//			if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
//				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
//				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
//				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
//				
//			} else if(Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)) {
//				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
//				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
//				
//			} else {
//				return super.getSexPositionPreferences();
//			}
//		}
//		
//		return sexPositionPreferences;
//	}
	
	@Override
	public String getCondomEquipEffects(GameCharacter equipper, GameCharacter target, boolean rough) {
		if(Main.game.isInSex()) {
			if((Sex.isDom(Main.game.getPlayer()) || Sex.isSubHasEqualControl()) && !target.isPlayer()) {
				return "<p>"
							+ "Holding out a condom to [npc.name], you force [npc.herHim] to take it and put it on."
							+ " Quickly ripping it out of its little foil wrapper, [npc.she] rolls it down the length of [npc.her] [npc.cock+] as [npc.she] whines at you,"
							+ " [npc.speech(Do I really have to? It feels so much better without one...)]"
						+ "</p>";
			} else if (!target.isPlayer()){
				target.unequipClothingIntoVoid(target.getClothingInSlot(ClothingType.PENIS_CONDOM.getSlot()), true, equipper);
				return "<p>"
							+ "You pull out a condom and try to give it to the horny [npc.race], but [npc.she] simply laughs in your face before grabbing the little foil packet and tearing it in two."
							+ " Mocking your attempt at trying to get her to wear a rubber, [npc.she] sneers,"
							+ " [npc.speech(Hah! I don't think so!)]"
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
	
	// Losing virginity:
	private static StringBuilder StringBuilderSB;
	@Override
	public String getVirginityLossOrificeDescription(GameCharacter characterPenetrating, PenetrationType penetrationType, GameCharacter characterPenetrated, OrificeType orifice){
		if(!characterPenetrated.isPlayer() || penetrationType!=PenetrationType.PENIS || orifice!=OrificeType.VAGINA || !characterPenetrating.equals(this)) {
			return super.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
		}
		
		StringBuilderSB = new StringBuilder();
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			StringBuilderSB.append(
							"<p>"
								+ "As the dominant [npc.race] drives [npc.her] [npc.cock+] deep into your [pc.pussy+], your vision explodes in stars, and a painful, high-pitched shriek escapes from between your lips."
								+ " Due to the fact that you're still a virgin, the sudden, violent penetration is like nothing you've ever felt before,"
									+ " and your shriek turns into a shuddering cry as you find yourself overwhelmed by the agony of your first penetration."
							+ "</p>"
							+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)
									?"<p>"
										+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
										+ " The agony between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
									+ "</p>"
									:"")
							+"<p>"
								+ "Glancing down, you see a small trickle of blood flowing out of your now-broken-in pussy, and you realise that your hymen has been completely torn."
								+ " As you let out a desperate sigh, you hear the [npc.race] giggling at your reaction."
							+ "</p>"
							+"<p>"
								+ "Looking up at [npc.herHim], you see a wild, crazed look in [npc.her] eyes."
								+ " Another jolt of pain shoots up between your legs as [npc.she] drives [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."
								+ " Leaning into you, [npc.she] pulls you into a sloppy kiss, and with a final, violent thrust into your groin, you feel the base of [npc.her] cock grind up against your [pc.labia+]."
							+ "</p>"
							+ "<p>"
								+ "Still keeping [npc.her] [npc.cock] fully hilted in your [pc.pussy], [npc.she] breaks off the kiss, raising [npc.her] hand to cover your mouth as [npc.she] muffles your painful cries."
								+ " Playfully biting [npc.her] lip, [npc.she] starts taunting you, clearly getting incredibly excited about being the one to take your virginity."
							+ "</p>"
							+ "<p>"
								+ "[npc.speechNoEffects(~Mmm!~ Doesn't that feel good?! Aww, I wonder if you were saving yourself for someone?)]"
								+ " [npc.She] pauses for a moment to let out a mocking laugh, before continuing,"
								+ " [npc.speechNoEffects(Well, I guess you were saving yourself for me in the end! Which is a shame, really, because you're just another easy fuck to me.)]"
							+ "</p>"
							+ "<p>"
								+ "You let out a protesting moan, but the [npc.race] just presses [npc.her] hand down even tighter over your mouth as [npc.she] continues, "
								+ "[npc.speechNoEffects(What's that? You want me to do it again?!)]"
							+ "</p>"
							+ "<p>"
								+ "Letting out a manic laugh, the [npc.race] suddenly drops [npc.her] hips, allowing [npc.her] [npc.cock+] to slide fully out of your [pc.pussy+]."
								+ " Lining [npc.herself] up once more, [npc.she] wastes no time before violently thrusting up into you for a second time."
								+ " Although still uncomfortable enough to cause you to cry out into the palm of [npc.her] hand, it's nothing like the agony you experienced on the first thrust,"
									+ " and as you feel [npc.her] throbbing length filling you up once more, you find yourself letting out a desperate little moan."
							+ "</p>"
							+ "<p>"
								+ "The [npc.race] looks delighted at your reaction, and leans in as [npc.she] continues teasing you,"
								+ " [npc.speechNoEffects(~Ooh!~ So, you're a slut for demon dick? Can you feel it, pushing <i>deep</i>, claiming your precious little virginity?!)]"
							+ "</p>"
							+ "<p>"
								+ "Dropping [npc.her] hips a little, [npc.she] starts thrusting in and out of your [pc.pussy], taking [npc.her] hand away from your mouth as you find yourself letting out a series of lewd moans."
								+ " Moving [npc.her] hands down to take hold of your waist, [npc.she] carries on taunting you, repeating that you'll always remember this moment as the time you discovered that you're just a slut for big, thick demon cock."
								+ " As the pain between your legs fades away into a dull ache, you find yourself letting out moan after desperate moan, and you start to worry that the [npc.race] might be right..."
							+ "</p>");
		} else {
			StringBuilderSB.append(
					"<p>"
						+ "As the dominant [npc.race] drives [npc.her] [npc.cock+] deep into your [pc.pussy+], your vision explodes in stars, and a desperate, high-pitched wail escapes from between your lips."
						+ " Due to the fact that you're still a virgin, the sudden, violent penetration is like nothing you've ever felt before, and your wail turns into a shuddering cry as you collapse back against the wall."
					+ "</p>"
					+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)
							?"<p>"
								+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
								+ " The discomfort between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
							+ "</p>"
							:"")
					+"<p>"
						+ "Letting out a sigh as you realise that your hymen is now completely torn, you look up at the [npc.race], and see a wild, crazed look in [npc.her] eyes."
						+ " Another little painful jolt shoots up between your legs as [npc.she] drives [npc.her] [npc.cock+] even deeper into your [pc.pussy+]."
						+ " Leaning into you, [npc.she] pulls you into a sloppy kiss, and with a final, violent thrust into your groin, you feel the base of [npc.her] cock grind up against your [pc.labia+]."
					+ "</p>"
					+ "<p>"
						+ "Still keeping [npc.her] demonic cock fully hilted in your [pc.pussy+], [npc.she] breaks off the kiss, raising [npc.her] hand to cover your mouth as [npc.she] muffles your desperate cries."
						+ " Playfully biting [npc.her] lip, [npc.she] starts taunting you, clearly getting incredibly excited about being the one to take your virginity."
					+ "</p>"
					+ "<p>"
						+ "[npc.speechNoEffects(~Mmm!~ Doesn't that feel good?! Aww, I wonder if you were saving yourself for someone?)]"
						+ " [npc.She] pauses for a moment to let out a mocking laugh, before continuing,"
						+ " [npc.speechNoEffects(Well, I guess you were saving yourself for me in the end! Which is a shame, really, because you're just another easy fuck to me.)]"
					+ "</p>"
					+ "<p>"
						+ "You let out a protesting moan, but the [npc.race] just presses [npc.her] hand down even tighter over your mouth as [npc.she] continues, "
						+ "[npc.speechNoEffects(What's that? You want me to do it again?!)]"
					+ "</p>"
					+ "<p>"
						+ "Letting out a manic laugh, the [npc.race] suddenly drops [npc.her] hips, allowing [npc.her] [npc.cock+] to slide fully out of your [pc.pussy+]."
						+ " Lining [npc.herself] up once more, [npc.she] wastes no time before violently thrusting up into you for a second time."
						+ " Although still uncomfortable enough to cause you to cry out into the palm of [npc.her] hand, it's nothing like the agony you experienced on the first thrust,"
							+ " and as you feel [npc.her] throbbing length filling you up once more, you find yourself letting out a desperate little moan."
					+ "</p>"
					+ "<p>"
						+ "The [npc.race] looks delighted at your reaction, and leans in as [npc.she] continues teasing you,"
						+ " [npc.speechNoEffects(~Ooh!~ So, you're a slut for demon dick? Can you feel it, pushing <i>deep</i>, claiming your precious little virginity?!)]"
					+ "</p>"
					+ "<p>"
						+ "Dropping [npc.her] hips a little, [npc.she] starts thrusting in and out of your [pc.pussy], taking [npc.her] hand away from your mouth as you find yourself letting out a series of lewd moans."
						+ " Moving [npc.her] hands down to take hold of your waist, [npc.she] carries on taunting you, repeating that you'll always remember this moment as the time you discovered that you're just a slut for big, thick demon cock."
						+ " As the pain between your legs fades away into a dull ache, you find yourself letting out moan after desperate moan, and you start to worry that the [npc.race] might be right..."
					+ "</p>");
			
		}
		
		StringBuilderSB.append(formatVirginityLoss("Your hymen has been torn; you have lost your virginity!"));
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN))
			StringBuilderSB.append("<p style='text-align:center;'>"
					+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
				+ "</p>"
				+ "<p>"
					+ "As the [npc.race] carries on pounding away between your legs, the sudden realisation of what's just happened hits you like a sledgehammer."
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("I-I've lost my virginity?!</br>"
							+ "To... <b>her</b>?!")
				+ "</p>"
				+ "<p>"
					+ "You don't know what's worse, losing the virginity that you prized so highly, or the fact that you're actually enjoying it."
					+ " As your labia spread lewdly around the hot, thick demon-dick, you find yourself starting to agree with what the [npc.race] is telling you."
				+ "</p>"
				+ "<p style='text-align:center;'>"
				+ UtilText.parsePlayerThought("If I'm not a virgin, that makes me a slut...</br>"
						+ "Just a slut for demon cock...</br>"
						+ "She's right, I'm just another easy fuck for someone like her...")
				+ "</p>"
				+ "<p>"
					+ "You're vaguely aware of the [npc.race]'s taunts fading away as [npc.she] starts to focus [npc.her] concentration on fucking you."
					+ " With a desperate moan, you start bucking your hips back against [npc.herHim], resigning yourself to the fact that now you're nothing more than a"
					+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
				+ "</p>");
		
		return UtilText.parse(Sex.getActivePartner(),
				StringBuilderSB.toString());
	}
	
	// Dirty talk:
	
	@Override
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		List<String> speech = new ArrayList<>();
		
		if(isPlayerDom && Sex.getSexPace(this)!=SexPace.SUB_RESISTING){
			speech.add("Come on, fuck me already!");
			speech.add("Come on! What's taking so long?!");
			speech.add("Fuck me already!");
			speech.add("Let's get started! Come on!");
		} else if(!isPlayerDom) {
			speech.add("I'm gonna turn you into a slut for demon cock!");
			speech.add("You ever been fucked by a demon?");
			speech.add("You're going to be begging for my cum before the end!");
			speech.add("You're going to be a good little bitch!");
		} else {
			return super.getDirtyTalkNoPenetration(target, isPlayerDom);
		}
		
		return speech.get(Util.random.nextInt(speech.size()));
	}
}
