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
import com.lilithsthrone.game.character.attributes.AffectionLevel;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.companions.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayDemonDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.69
 * @version 0.3.7.4
 * @author Innoxia
 */
public class DominionSuccubusAttacker extends NPC {
	
	public DominionSuccubusAttacker() {
		this(false);
	}
	
	public DominionSuccubusAttacker(boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(50)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				5, Gender.getGenderFromUserPreferences(Femininity.FEMININE), Subspecies.DEMON, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			this.setLocation(Main.game.getPlayer(), true);
			
//			if(!Gender.getGenderFromUserPreferences(false, false).isFeminine()) {
//				this.setBody(Gender.M_P_MALE, Subspecies.DEMON, RaceStage.GREATER, true);
//				this.setGenderIdentity(Gender.M_P_MALE);
//			}
			
			Gender gender = Gender.getGenderFromUserPreferences(false, false);
			this.setBody(gender, Subspecies.DEMON, RaceStage.GREATER, true);
			this.setGenderIdentity(gender);
			
			
			Main.game.getCharacterUtils().randomiseBody(this, true);

			Main.game.getCharacterUtils().setHistoryAndPersonality(this, false);
			this.setHistory(Occupation.NPC_MUGGER); // All demon alleyway attackers are muggers
			
			addFetish(Fetish.FETISH_DEFLOWERING);
			addFetish(Fetish.FETISH_DOMINANT);
			Main.game.getCharacterUtils().addFetishes(this);

			this.removePersonalityTrait(PersonalityTrait.PRUDE);

			setSexualOrientation(SexualOrientation.AMBIPHILIC);
			
			this.setAgeAppearanceDifferenceToAppearAsAge(18+Util.random.nextInt(10));
			
			this.setVaginaVirgin(false);
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setNippleVirgin(false);
			this.setPenisVirgin(false);
			
			setLevel(Util.random.nextInt(5) + 8);
			
			setName(Name.getRandomTriplet(Subspecies.DEMON));
			this.setPlayerKnowsName(false);
			
			// Set random inventory & weapons:
			resetInventory(true);
			inventory.setMoney(50);
			Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
			
			// CLOTHING:
			
			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			Main.game.getCharacterUtils().applyMakeup(this, true);
			Main.game.getCharacterUtils().applyTattoos(this, true);

			if(hasFetish(Fetish.FETISH_CUM_ADDICT) && Math.random() < 0.1) {
				Main.game.getCharacterUtils().applyDirtiness(this);
			}
			
			this.addSpell(Spell.ARCANE_AROUSAL);
			this.addSpell(Spell.TELEPATHIC_COMMUNICATION);
			this.addSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1);

			// Set starting perks based on the character's race
			initPerkTreeAndBackgroundPerks();
			this.setStartingCombatMoves();
			loadImages();
			
			initHealthAndManaToMax();
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE, true);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ONE_DISLIKE || this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)==FetishDesire.ZERO_HATE) {
			this.setFetishDesire(Fetish.FETISH_NON_CON_DOM, FetishDesire.TWO_NEUTRAL);
		}
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.11")) {
			this.setAgeAppearanceDifferenceToAppearAsAge(18+Util.random.nextInt(10));
		}
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.incrementMoney((int) (this.getInventory().getNonEquippedValue() * 0.5f));
		this.clearNonEquippedInventory(false);
		Main.game.getCharacterUtils().generateItemsInInventory(this, true, true, true);
		
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.MUGGER, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(isSlave()) {
			return UtilText.parse(this,
					"Having lost [npc.herself] to [npc.her] powerful libido, [npc.name] ended up stalking the alleyways of Dominion in search of innocent citizens to force [npc.herself] on."
					+ " Being unlucky enough to have been placed on the Enforcer's wanted list, and then to have encountered you, [npc.she] soon ended up being enslaved and owned by you.");
		}
		return UtilText.parse(this,
				"Although all demons have an extremely powerful libido, some suffer from it far more than others."
				+ " While most are able to control their sexual desires, others, such as this [npc.race], struggle to think of anything but how to secure their next sexual conquest.");
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
	public DialogueNode getEncounterDialogue() {
		return AlleywayDemonDialogue.DEMON_ATTACK;
	}

	@Override
	public boolean isAffectionHighEnoughToInviteHome() {
		if(this.isRelatedTo(Main.game.getPlayer())) {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_TWO_LIKE.getMinimumValue();
		} else {
			return this.getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_FOUR_LOVE.getMinimumValue();
		}
	}

	// Combat:

	@Override
	public String getMainAttackDescription(int armRow, GameCharacter target, boolean isHit, boolean critical) {
		if(!this.isSlave() && this.getMainWeapon(0)==null) {
			if(this.isFeminine()) {
				return UtilText.parse(this, target,
						UtilText.returnStringAtRandom(
								"[npc.Name] looks annoyed that [npc2.nameIs] trying to put up a fight, and leaps forwards to deliver a stinging slap across [npc2.her] face.",
								"With an angry little click of her tongue, [npc.Name] slaps [npc2.name] across the face.",
								"With a frustrated whine, [npc.Name] kicks out at [npc2.namePos] shins."));
			} else {
				return UtilText.parse(this, target,
						UtilText.returnStringAtRandom(
								"[npc.Name] looks annoyed that [npc2.nameIs] trying to put up a fight, and leaps forwards to deliver a solid punch to [npc2.her] [npc2.arm].",
								"With an angry shout, [npc.Name] darts forwards and punches [npc2.name] right in the chest!",
								"With a frustrated cry, [npc.Name] kicks out at [npc2.namePos] shins."));
			}
		}
		return super.getMainAttackDescription(armRow, target, isHit, critical);
	}

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", AlleywayDemonDialogue.AFTER_COMBAT_VICTORY);
		} else {
			return new Response ("", "", AlleywayDemonDialogue.AFTER_COMBAT_DEFEAT);
		}
	}
	
	
	// ****************** Sex & Dirty talk: ***************************
	
	@Override
	public String getCondomEquipEffects(AbstractClothingType condomClothingType, GameCharacter equipper, GameCharacter target, boolean rough) {
		if(!target.equals(equipper) && Main.game.isInSex()) {
			if((Main.sex.isDom(Main.game.getPlayer()) || Main.sex.isSubHasEqualControl()) && !target.isPlayer()) {
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return null;
				}
				return UtilText.parse(equipper, target,
						"<p>"
							+ "Holding out a condom to [npc2.name], [npc.name] [npc.verb(force)] [npc2.herHim] to take it and put it on."
							+ " Quickly ripping it out of its little foil wrapper, [npc2.she] [npc2.verb(roll)] it down the length of [npc2.her] [npc2.cock+] as [npc2.she] [npc2.verb(whine)],"
							+ " [npc2.speech(Do I really have to? It feels so much better without one...)]"
						+ "</p>");
				
			} else if (!target.isPlayer()) {
				AbstractClothing clothing = target.getClothingInSlot(InventorySlot.PENIS);
				if(clothing!=null && clothing.isCondom()) {
					target.unequipClothingIntoVoid(clothing, true, equipper);
					target.getInventory().resetEquipDescription();
				}
				if(condomClothingType.equals(ClothingType.getClothingTypeFromId("innoxia_penis_condom_webbing"))) {
					return UtilText.parse(equipper, target,
							"[npc.Name] [npc.verb(direct)] [npc.her] spinneret at [npc2.namePos] [npc2.cock], but, sensing what [npc.sheIs] about to do, [npc2.name] [npc2.verb(slap)] it away and [npc2.verb(sneer)],"
							+ " [npc2.speech(Hah! I don't think so!)]");
				}
				return UtilText.parse(equipper, target,
						"<p>"
							+ "[npc.Name] [npc.verb(pull)] out a condom and [npc.verb(try)] to give it to [npc2.name], but [npc2.she] simply laughs in [npc.her] face before grabbing the little foil packet and tearing it in two."
							+ " Mocking [npc.namePos] attempt at trying to get [npc2.herHim] to wear a rubber, [npc2.she] [npc2.verb(sneer)],"
							+ " [npc2.speech(Hah! I don't think so!)]"
						+ "</p>");
			}
		}
		return null;
	}
	
	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer() || penetrating!=SexAreaPenetration.PENIS || penetrated!=SexAreaOrifice.VAGINA || !penetratingCharacter.equals(this) || this.isSlave()) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().hasHymen()) {
			sb.append("<p>"
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
							+ "Dropping [npc.her] hips a little, [npc.she] starts rapidly thrusting in and out of your [pc.pussy], taking [npc.her] hand away from your mouth as you find yourself letting out a series of lewd moans."
							+ " Moving [npc.her] hands down to take hold of your waist, [npc.she] carries on taunting you, repeating that you'll always remember this moment as the time you discovered that you're just a slut for big, thick demon cock."
							+ " As the pain between your legs fades away into a dull ache, you find yourself letting out moan after desperate moan, and you start to worry that the [npc.race] might be right..."
						+ "</p>");
			
		} else {
			sb.append(
					"<p>"
						+ "As the dominant [npc.race] drives [npc.her] [npc.cock+] deep into your [pc.pussy+], you can't stop a desperate, high-pitched wail from bursting out of your mouth."
						+ " Due to the fact that you're still a virgin, the sudden, violent penetration is like nothing you've ever felt before, and your wail turns into a shuddering cry as you find yourself overwhelmed byt this new sensation."
					+ "</p>"
					+"<p>"
						+ "As your hymen has been previously torn, the penetration isn't painful, but from your surprised reaction, [npc.name] is still able to realise that [npc.sheHas] just taken your virginity."
						+ " With a wild, crazed look in [npc.her] eyes, [npc.name] violently thrusts into your groin, driving [npc.her] [npc.cock+] as deep as [npc.she] possibly can into your [pc.pussy+]."
						+ " Keeping [npc.her] throbbing demonic cock driven as deep as possible into your [pc.pussy+], [npc.name] playfully licks [npc.her] [npc.lips], before starting to taunt you."
					+ "</p>"
					+ "<p>"
						+ "[npc.speechNoEffects(~Mmm!~ Having a big demonic cock stuffed into your pussy feels good, doesn't it?! ~Ooh!~ I wonder if you were saving your first time for a special someone in your life?)]"
						+ " [npc.she] asks, before pausing to let out a mocking laugh. Clearly getting incredibly excited about being the one to take your virginity, [npc.she] impatiently continues,"
						+ " [npc.speechNoEffects(It looks like you were saving yourself for me in the end! Which is a shame, really, because you're just another easy fuck to me.)]"
					+ "</p>"
					+ "<p>"
						+ "You can't help but let out [pc.a_moan+] as you feel [npc.namePos] [npc.cock+] throbbing deep within your cunt, which causes [npc.herHim] to let out another mocking laugh and ask,"
						+ " [npc.speechNoEffects(What's that? You want me to do it again?!)]"
					+ "</p>"
					+ "<p>"
						+ "Not waiting to hear your response, the [npc.race] suddenly pulls [npc.her] hips back, allowing [npc.her] [npc.cock+] to slide fully out of your [pc.pussy+]."
						+ " Lining [npc.herself] up once more, [npc.she] immediately thrusts into you for a second time, and as you feel [npc.her] hot cock filling you up once more, you find yourself letting out a desperate [pc.moan]."
						+ " [npc.Name] looks delighted at your reaction, and pushes [npc.her] demonic cock in a little deeper as [npc.she] teases,"
						+ " [npc.speechNoEffects(~Ooh!~ So, you're a dirty slut for demon dick, are you? You like that feeling? ~Mmm!~ Having my cock push <i>deep</i> into your horny little pussy, claiming your precious virginity?!)]"
					+ "</p>"
					+ "<p>"
						+ "Pulling back a little, [npc.she] starts thrusting in and out of your [pc.pussy+], grinning in delight as [npc.she] hears you let out a series of lewd [pc.moans]."
						+ " As [npc.her] cock continues to pound your cunt, [npc.name] carries on taunting you, repeating that you'll always remember this moment as the time you discovered that you're just a slut for demonic cock."
						+ " As [npc.she] says this, you find yourself letting out moan after desperate moan, and you start to worry that the [npc.race] might be right..."
					+ "</p>");
		}
		
		return UtilText.parse(this, sb.toString());
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
						+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
					+ "</p>"
					+ "<p>"
						+ "As the [npc.race] carries on pounding away between your legs, the sudden realisation of what's just happened hits you like a sledgehammer."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ UtilText.parsePlayerThought("I-I've lost my virginity?!<br/>"
								+ "To... <b>her</b>?!")
					+ "</p>"
					+ "<p>"
						+ "You don't know what's worse, losing the virginity that you prized so highly, or the fact that you're actually enjoying it."
						+ " As your labia spread lewdly around the hot, thick demon-dick, you find yourself starting to agree with what the [npc.race] is telling you."
					+ "</p>"
					+ "<p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("If I'm not a virgin, that makes me a slut...<br/>"
							+ "Just a slut for demon cock...<br/>"
							+ "She's right, I'm just another easy fuck for someone like her...")
					+ "</p>"
					+ "<p>"
						+ "You're vaguely aware of the [npc.race]'s taunts fading away as [npc.she] starts to focus [npc.her] concentration on fucking you."
						+ " With a desperate moan, you start bucking your hips back against [npc.herHim], resigning yourself to the fact that now you're nothing more than a"
						+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
					+ "</p>";
	}
	
	// Dirty talk:
	
	@Override
	public String getDirtyTalkNoPenetration(GameCharacter target, boolean isPlayerDom){
		if(this.isSlave()) {
			return super.getDirtyTalkNoPenetration(target, isPlayerDom);
		}
		
		List<String> speech = new ArrayList<>();
		
		if(isPlayerDom && Main.sex.getSexPace(this)!=SexPace.SUB_RESISTING){
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
		
		String returnedLine = speech.get(Util.random.nextInt(speech.size()));
		return UtilText.parse(this, target, "[npc.speech("+returnedLine+")]");
	}
}
