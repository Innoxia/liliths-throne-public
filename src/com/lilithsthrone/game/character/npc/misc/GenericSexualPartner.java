package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Season;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.2.6
 * @author Innoxia
 */
public class GenericSexualPartner extends NPC {

	public GenericSexualPartner() {
		this(Gender.F_V_B_FEMALE, WorldType.EMPTY, new Vector2i(0, 0), false);
	}
	
	public GenericSexualPartner(boolean isImported) {
		this(Gender.F_V_B_FEMALE, WorldType.EMPTY, new Vector2i(0, 0), isImported);
	}
	
	public GenericSexualPartner(Gender gender, WorldType worldLocation, Vector2i location, boolean isImported) {
		super(null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			this.setWorldLocation(worldLocation);
			this.setLocation(location);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No spawn chance:
					case ANGEL:
					case DEMON:
					case IMP:
					case IMP_ALPHA:
					case FOX_ASCENDANT:
					case FOX_ASCENDANT_FENNEC:
					case ELEMENTAL_AIR:
					case ELEMENTAL_ARCANE:
					case ELEMENTAL_EARTH:
					case ELEMENTAL_FIRE:
					case ELEMENTAL_WATER:
						break;
						
					// Low spawn chance:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case SLIME:
					case SLIME_ALLIGATOR:
					case SLIME_ANGEL:
					case SLIME_CAT:
					case SLIME_CAT_LEOPARD:
					case SLIME_CAT_LEOPARD_SNOW:
					case SLIME_CAT_LYNX:
					case SLIME_CAT_LION:
					case SLIME_CAT_TIGER:
					case SLIME_CAT_CARACAL:
					case SLIME_CAT_CHEETAH:
					case SLIME_COW:
					case SLIME_DEMON:
					case SLIME_DOG:
					case SLIME_DOG_DOBERMANN:
					case SLIME_DOG_BORDER_COLLIE:
					case SLIME_FOX:
					case SLIME_FOX_FENNEC:
					case SLIME_HARPY:
					case SLIME_HARPY_RAVEN:
					case SLIME_HARPY_BALD_EAGLE:
					case SLIME_HORSE:
					case SLIME_IMP:
					case SLIME_REINDEER:
					case SLIME_SQUIRREL:
					case SLIME_BAT:
					case SLIME_RAT:
					case SLIME_WOLF:
					case SLIME_RABBIT:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
					case RAT_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;

					case BAT_MORPH:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
					case HARPY:
						addToSubspeciesMap(4, gender, s, availableRaces);
						break;
					case HARPY_RAVEN:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
					case HARPY_BALD_EAGLE:
						addToSubspeciesMap(1, gender, s, availableRaces);
						break;
					case HUMAN:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
						
					// Special spawns:
					case REINDEER_MORPH:
						if(Main.game.getSeason()==Season.WINTER && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
							addToSubspeciesMap(10, gender, s, availableRaces);
						}
						break;
						
					// Regular spawns:
					case CAT_MORPH:
						addToSubspeciesMap(25, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD_SNOW:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LION:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_TIGER:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LYNX:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CHEETAH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CARACAL:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case COW_MORPH:
						addToSubspeciesMap(15, gender, s, availableRaces);
						break;
					case DOG_MORPH:
						addToSubspeciesMap(15, gender, s, availableRaces);
						break;
					case DOG_MORPH_DOBERMANN:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case DOG_MORPH_BORDER_COLLIE:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case FOX_MORPH:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case FOX_MORPH_FENNEC:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case HORSE_MORPH:
						addToSubspeciesMap(20, gender, s, availableRaces);
						break;
					case HORSE_MORPH_ZEBRA:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case SQUIRREL_MORPH:
						addToSubspeciesMap(10, gender, s, availableRaces);
						break;
					case WOLF_MORPH:
						addToSubspeciesMap(25, gender, s, availableRaces);
						break;
					case RABBIT_MORPH:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case RABBIT_MORPH_LOP:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who's currently only interested in having sex."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setName(new NameTriplet("unknown male", "unknown female", "unknown female"));
	}
	
	@Override
	public boolean isUnique() {
		return false;
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
		return null;
	}

	@Override
	public void endSex() {
	}
	
	private boolean playerRequested = false;
	
	@Override
	public void generateSexChoices(GameCharacter target, SexType request) {
		if(this.getLocationPlace().getPlaceType()==PlaceType.WATERING_HOLE_TOILETS && Sex.getTurn()>1) {
			playerRequested = true;
		}
		
		super.generateSexChoices(target, request);
	}
	
	@Override
	public Set<SexPositionSlot> getSexPositionPreferences(GameCharacter target) {
		if(this.getLocationPlace().getPlaceType()!=PlaceType.WATERING_HOLE_TOILETS || playerRequested) {
			return super.getSexPositionPreferences(target);
		}
		
		sexPositionPreferences.clear();
		
		if(Sex.isInForeplay() || this.hasFetish(Fetish.FETISH_ORAL_GIVING) || !target.hasPenis()) {
			sexPositionPreferences.add(SexPositionSlot.GLORY_HOLE_KNEELING);
		} else {
			sexPositionPreferences.add(SexPositionSlot.GLORY_HOLE_FUCKED);
		}
		
		return sexPositionPreferences;
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(this.getLocationPlace().getPlaceType()!=PlaceType.WATERING_HOLE_TOILETS || playerRequested) {
			return super.getForeplayPreference(target);
		}
		
		if(target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS);
		} else if(target.hasVagina()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA);
		} else {
			return super.getForeplayPreference(target);
		}
	}

	@Override
	public SexType getMainSexPreference(GameCharacter target) {
		if(this.getLocationPlace().getPlaceType()!=PlaceType.WATERING_HOLE_TOILETS || playerRequested) {
			return super.getMainSexPreference(target);
		}
		
		if(this.hasFetish(Fetish.FETISH_ORAL_GIVING)) {
			return getForeplayPreference(target);
		}
		
		if(this.hasVagina() && target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS);
		} else if(target.hasPenis()) {
			return new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS);
		}

		return super.getMainSexPreference(target);
	}
	
	@Override
	public String getVirginityLossOrificeDescription(GameCharacter characterPenetrating, SexAreaPenetration penetrationType, GameCharacter characterPenetrated, SexAreaOrifice orifice){
		if(!characterPenetrated.isPlayer()
				|| (characterPenetrating.getLocationPlace().getPlaceType()!=PlaceType.GAMBLING_DEN_FUTA_PREGNANCY
					&& characterPenetrating.getLocationPlace().getPlaceType()!=PlaceType.GAMBLING_DEN_PREGNANCY)) {
			return super.getVirginityLossOrificeDescription(characterPenetrating, penetrationType, characterPenetrated, orifice);
		}
		
		StringBuilder StringBuilderSB = new StringBuilder();
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
			StringBuilderSB.append(
							"<p>"
								+"As [npc.namePos] [npc.cock+] thrusts forwards into your [pc.pussy+], you can't help but let out a desperate, shuddering wail."
								+ " Being so enamoured with the idea of being a pure virgin, you don't know what on Earth possessed you to sign up for pregnancy roulette,"
									+ " but as [npc.namePos] [npc.cock+] claims your previous virginity, you don't have any time to reflect on your poor choice."
								+ " The only thing that's on your mind is the agonising pain of having your hymen torn by a person you've never even spoken to."
							+ "</p>"
							+ "<p>"
								+ "As your wail turns into a shuddering cry, you hear the [npc.race] on the other side of the wall let out a surprised shout,"
								+ " [npc.speech(Holy shit! This slut was a virgin!)]"
							+ "</p>"
							+ "<p>"
								+ "The room beyond the wall is suddenly filled with laughs and lewd remarks, and you can't help but shed a tear as you realise that you've lost your precious,"
									+ " pure virginity to some stranger who signed up to try and get you pregnant."
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(Pregnant... Me... I-I'm sure that's not possible...)] you think to yourself, trying to suppress your whimpers as [npc.name] pulls back, before thrusting into you once more."
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] fills your freshly popped cherry with [npc.her] [npc.cock+], you hear [npc.herHim] taunting you from the other side of the wall."
								+ " [npc.speech(What a fucking slut! Choosing to lose your virginity to a game of pregnancy roulette! Hah! Glad I'll never be the one who has to tell our kids how they were conceived!)]"
							+ "</p>");
		} else {
			StringBuilderSB.append(
					"<p>"
						+"As [npc.namePos] [npc.cock+] thrusts forwards into your [pc.pussy+], you can't help but let out a desperate, shuddering wail."
						+ " As [npc.namePos] [npc.cock+] claims your virginity, the only thing that's on your mind is the agonising pain of having your hymen torn by a person you've never even spoken to."
					+ "</p>"
					+ (Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)
							?"<p>"
								+ "Due to being an extreme masochist, you find your painful cries being interspersed with lewd moans of pleasure."
								+ " The discomfort between your legs is pure bliss, and you focus on the pain as you squeal and moan in a delightful haze of overwhelming ecstasy."
							+ "</p>"
							:"")
					+"<p>"
					+ "<p>"
						+ "As your wail turns into a shuddering cry, you hear the [npc.race] on the other side of the wall let out a surprised shout,"
						+ " [npc.speech(Holy shit! This slut was a virgin!)]"
					+ "</p>"
					+ "<p>"
						+ "The room beyond the wall is suddenly filled with laughs and lewd remarks,"
							+ " and you can't help but let out a defeated sigh as you realise that you've lost your virginity to some stranger who signed up to try and get you pregnant."
					+ "</p>"
					+ "<p>"
						+ "[pc.thought(Pregnant... Me... I-I'm sure that's not possible...)] you think to yourself, trying to suppress your whimpers as [npc.name] pulls back, before thrusting into you once more."
					+ "</p>"
					+ "<p>"
						+ "As [npc.she] fills your freshly popped cherry with [npc.her] [npc.cock+], you hear [npc.herHim] taunting you from the other side of the wall."
						+ " [npc.speech(What a fucking slut! Choosing to lose your virginity to a game of pregnancy roulette! Hah! Glad I'll never be the one who has to tell our kids how they were conceived!)]"
					+ "</p>");
		}
		
		StringBuilderSB.append(formatVirginityLoss("Your hymen has been torn; you have lost your virginity!"));
		
		if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN))
			StringBuilderSB.append("<p style='text-align:center;'>"
					+ "<b style='color:"+Colour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
				+ "</p>"
				+ "<p>"
					+ "As the [npc.race] carries on pounding away at your pussy, the sudden realisation of what's just happened hits you like a sledgehammer."
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ UtilText.parsePlayerThought("I-I've lost my virginity?!<br/>"
							+ "Like... Like <b>this</b>?!")
				+ "</p>"
				+ "<p>"
					+ "You don't know what's worse, losing the virginity that you prized so highly, or the fact that you're actually enjoying it."
					+ " As your [pc.labia+] spread lewdly around the hot, thick [npc.cock] pumping in and out of you, you start convincing yourself that this is all you're good for."
				+ "</p>"
				+ "<p style='text-align:center;'>"
				+ UtilText.parsePlayerThought("If I'm not a virgin, that makes me a slut...<br/>"
						+ "Just a slut to be fucked and pumped full of cum...<br/>"
						+ "If this [npc.race] doesn't knock me up, I hope one of the other breeders makes me a Mommy...")
				+ "</p>"
				+ "<p>"
					+ "You're vaguely aware of [npc.namePos] taunts fading away as [npc.she] starts to focus [npc.her] attention on fucking you."
					+ " With a desperate moan, you spread your legs and resign yourself to the fact that you're now nothing more than a"
					+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
				+ "</p>");
		
		return StringBuilderSB.toString();
	}
}