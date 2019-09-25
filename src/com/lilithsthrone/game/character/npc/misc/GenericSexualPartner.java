package com.lilithsthrone.game.character.npc.misc;

import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.OutfitType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.2
 * @version 0.3.4
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
		this(gender, worldLocation, location, isImported, null);
	}
	
	public GenericSexualPartner(Gender gender, WorldType worldLocation, Vector2i location, boolean isImported, Predicate<Subspecies> subspeciesRemovalFilter) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
			this.setLocation(worldLocation, location, false);
			
			setLevel(Util.random.nextInt(5) + 5);
			
			// RACE & NAME:
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			List<Subspecies> availableSubspecies = new ArrayList<>();
			Collections.addAll(availableSubspecies, Subspecies.values());
			
			if(subspeciesRemovalFilter!=null) {
				availableSubspecies.removeIf(subspeciesRemovalFilter);
			}
			
			for(Subspecies s : availableSubspecies) {
				if(s==Subspecies.REINDEER_MORPH
						&& Main.game.getSeason()==Season.WINTER
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					addToSubspeciesMap(10, gender, s, availableRaces);
					
				} else if(s.getRace()!=Race.DEMON
						&& s.getRace()!=Race.ANGEL
						&& s.getRace()!=Race.ELEMENTAL
						&& s!=Subspecies.FOX_ASCENDANT
						&& s!=Subspecies.FOX_ASCENDANT_FENNEC
						&& s!=Subspecies.SLIME) {
					if(Subspecies.getMainSubspeciesOfRace(s.getRace())==s) {
						addToSubspeciesMap(10, gender, s, availableRaces);
					} else {
						addToSubspeciesMap(3, gender, s, availableRaces);
					}
				}}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who's currently only interested in having sex."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, false);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

			this.equipClothing(EquipClothingSetting.getAllClothingSettings());
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			initPerkTreeAndBackgroundPerks();

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		
		this.setName(new NameTriplet("unknown male", "unknown female", "unknown female"));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		CharacterUtils.equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
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
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	private boolean playerRequested = false;
	
	@Override
	public void generateSexChoices(boolean resetPositioningBan, GameCharacter target, List<SexType> request) {
		if(this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) && Sex.getTurn()>1) {
			playerRequested = true;
		}
		
		super.generateSexChoices(resetPositioningBan, target, request);
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.isHappyToBeInSlot(position, slot, target);
			
		} else {
			if(Sex.isInForeplay(this) || this.hasFetish(Fetish.FETISH_ORAL_GIVING) || !target.hasPenis()) {
				return slot==SexSlotUnique.GLORY_HOLE_KNEELING;
			} else {
				return slot==SexSlotUnique.GLORY_HOLE_FUCKED;
			}
		}
	}

	@Override
	public SexType getForeplayPreference(GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
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
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
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
				|| (!characterPenetrating.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)
					&& !characterPenetrating.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY))) {
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