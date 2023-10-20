package com.lilithsthrone.game.character.npc.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPCGenerationFlag;
import com.lilithsthrone.game.character.npc.RandomNPC;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.outfit.OutfitType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.positions.AbstractSexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlot;
import com.lilithsthrone.game.sex.positions.slots.SexSlotUnique;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * This NPC doesn't spawn with addictive fluids so as to prevent issues with randomly getting their partners addicted to fluids.
 *
 * @since 0.2.2
 * @version 0.4.8.4
 * @author Innoxia
 */
public class GenericSexualPartner extends RandomNPC {

	public GenericSexualPartner(NPCGenerationFlag... generationFlags) {
		this(false, null, null, null, null, generationFlags);
	}
	
	public GenericSexualPartner(boolean isImported) {
		this(isImported, null, null, null, null);
	}

	public GenericSexualPartner(Gender gender, NPCGenerationFlag... generationFlags) {
		this(false, gender, null, null, null, generationFlags);
	}
	
	public GenericSexualPartner(Predicate<AbstractSubspecies> subspeciesRemovalFilter, NPCGenerationFlag... generationFlags) {
		this(false, null, null, null, subspeciesRemovalFilter, generationFlags);
	}
	
	public GenericSexualPartner(Gender gender, AbstractSubspecies subspecies, RaceStage raceStage, NPCGenerationFlag... generationFlags) {
		this(false, gender, subspecies, raceStage, null, generationFlags);
	}
	
	public GenericSexualPartner(Gender gender, Predicate<AbstractSubspecies> subspeciesRemovalFilter, NPCGenerationFlag... generationFlags) {
		this(false, gender, null, null, subspeciesRemovalFilter, generationFlags);
	}
	
	public GenericSexualPartner(boolean isImported, Gender gender, AbstractSubspecies subspecies, RaceStage raceStage, Predicate<AbstractSubspecies> subspeciesRemovalFilter, NPCGenerationFlag... generationFlags) {
		super(isImported, false, generationFlags);
		
		if (isImported) {
			return;
		}
		
		// Pre-setup
		this.setLevel(Util.random.nextInt(5) + 5);
		
		Map<AbstractSubspecies, Integer> subspeciesMap = new HashMap<>();
		List<AbstractSubspecies> availableSubspecies = new ArrayList<>(Subspecies.getAllSubspecies());
		
		if(subspeciesRemovalFilter!=null) {
			availableSubspecies.removeIf(subspeciesRemovalFilter);
		}
		
		for(AbstractSubspecies s : availableSubspecies) {
			if(s.getSubspeciesOverridePriority()>0) { // Do not spawn demonic races, elementals, or youko
				continue;
			}
			if(s==Subspecies.REINDEER_MORPH
					&& Main.game.getSeason()==Season.WINTER
					&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
				AbstractSubspecies.addToSubspeciesMap(50, gender, s, subspeciesMap);
			}
			
			if(Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.DOMINION, null, false).get(s).getChanceMultiplier()), gender, s, subspeciesMap);
			} else if(Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).containsKey(s)) {
				AbstractSubspecies.addToSubspeciesMap((int) (1000*Subspecies.getWorldSpecies(WorldType.SUBMISSION, null, false).get(s).getChanceMultiplier()), gender, s, subspeciesMap);
			}
		}
		
		// Setup
		this.setupNPC(subspeciesMap,
				null,
				null,
				subspeciesRemovalFilter==null,
				false,
				false,
				false,
				false,
				false,
				false,
				generationFlags);
		
		// Post-setup
		this.setDescription("[npc.NameIsFull] [npc.a_race].");
		this.removeMilkCrotchModifier(FluidModifier.ADDICTIVE);
		this.removeMilkModifier(FluidModifier.ADDICTIVE);
		this.removeCumModifier(FluidModifier.ADDICTIVE);
		this.removeGirlcumModifier(FluidModifier.ADDICTIVE);
	}
	
	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		Main.game.getCharacterUtils().equipClothingFromOutfitType(this, OutfitType.CASUAL, settings);
	}
	
	private boolean playerRequested = false;
	
	@Override
	public void generateSexChoices(boolean resetPositioningBan, GameCharacter target, List<SexType> request) {
		if(this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) && Main.sex.getTurn()>1) {
			playerRequested = true;
		}
		
		super.generateSexChoices(resetPositioningBan, target, request);
	}
	
	@Override
	public boolean isHappyToBeInSlot(AbstractSexPosition position, SexSlot slot, GameCharacter target) {
		if(!this.getLocationPlace().getPlaceType().equals(PlaceType.WATERING_HOLE_TOILETS) || playerRequested) {
			return super.isHappyToBeInSlot(position, slot, target);
			
		} else {
			if(Main.sex.isInForeplay(this) || this.hasFetish(Fetish.FETISH_ORAL_GIVING) || !target.hasPenis()) {
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
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer()
				|| penetrating != SexAreaPenetration.PENIS
				|| penetrated != SexAreaOrifice.VAGINA
				|| (!penetratingCharacter.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY) && !penetratingCharacter.getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_PREGNANCY))) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(Main.game.getPlayer().hasHymen()) {
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("As [npc.namePos] [npc.cock+] thrusts into your [pc.pussy+], you can't help but let out a desperate, shuddering wail."
								+ " Being so enamoured with the idea of being a pure virgin, you don't know what on Earth possessed you to sign up for pregnancy roulette,"
									+ " but as [npc.namePos] [npc.cock+] claims your precious virginity, you don't have any time to reflect on your poor choice."
								+ " The only thing that's on your mind is the agonising pain of having your hymen torn by a person you've never even seen or spoken to before.");
				} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
					sb.append("As [npc.namePos] [npc.cock+] thrusts into your [pc.pussy+] to tear your hymen and claim your virginity, you can't help but let out a lewd, masochistic scream."
							+ " The agonising pain of having your hymen torn by a person you've never even seen or spoken to before completely overwhelms you, and you can't help but squeal and moan in a delightful haze of overwhelming ecstasy.");
				} else {
					sb.append("As [npc.namePos] [npc.cock+] thrusts into your [pc.pussy+] to claim your virginity, you can't help but let out a desperate, shuddering wail."
							+ " The agonising pain of having your hymen torn by a person you've never even seen or spoken to before completely overwhelms you, and you squirm about on the table as you try to endure this terrible experience.");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "As your wail turns into a shuddering cry, you hear the [npc.race] on the other side of the wall let out a surprised shout,"
						+ " [npc.speechNoExtraEffects(Holy shit! This slut was a virgin!)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("The room beyond the wall is suddenly filled with derisive laughs and lewd remarks, and you can't help but shed a tear as you realise that you've lost your precious,"
								+ " pure virginity to some stranger who signed up to try and get you pregnant.");
				} else {
					sb.append("The room beyond the wall is suddenly filled with derisive laughs and lewd remarks, and you can't help but wince as you realise that you've lost your virginity to some stranger who signed up to try and get you pregnant.");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[pc.thought(Pregnant... From my first time... I'm sure that's not possible...)] you think to yourself, trying to suppress your whimpers as [npc.name] pulls back, before thrusting into you once more."
					+ "</p>"
					+ "<p>"
						+ "As [npc.she] fills your freshly popped cherry with [npc.her] [npc.cock+], you hear [npc.herHim] taunting you from the other side of the wall."
						+ " [npc.speechNoExtraEffects(What a fucking slut! Choosing to lose your virginity to a game of pregnancy roulette! Hah! Glad I'll never be the one who has to tell our kids how they were conceived!)]"
					+ "</p>");
			
		} else {
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("As [npc.namePos] [npc.cock+] thrusts into your [pc.pussy+], you can't help but let out a distressed wail."
								+ " Being so enamoured with the idea of being a pure virgin, you don't know what on Earth possessed you to sign up for pregnancy roulette,"
									+ " but as [npc.namePos] [npc.cock+] claims your precious virginity, you don't have any time to reflect on your poor choice."
								+ " The only thing that's on your mind is the fact that you're being broken in by a person you've never even seen or spoken to before, and you can't help but continue to desperately cry out as [npc.she] penetrates you.");
				} else {
					sb.append("As [npc.namePos] [npc.cock+] thrusts into your [pc.pussy+] to claim your virginity, you can't help but let out a desperately lewd [pc.moan]."
							+ " This person who you've never even seen or spoken to before is giving you a feeling unlike any you've felt before, and you can't help but continue to scream and [pc.moan] in a delightful haze of overwhelming ecstasy.");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "Thanks to the fact that you'd previously lost your hymen, the only indication that you were a virgin is your intense reaction to being fucked,"
							+ " which is apparently enough for the [npc.race] on the other side of the wall, as [npc.she] lets out a surprised shout,"
						+ " [npc.speechNoExtraEffects(Holy shit! I think this slut was a virgin!)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("The room beyond the wall is suddenly filled with derisive laughs and lewd remarks, and you can't help but shed a tear as you realise that you've lost your precious,"
								+ " pure virginity to some stranger who signed up to try and get you pregnant.");
				} else {
					sb.append("The room beyond the wall is suddenly filled with derisive laughs and lewd remarks, and you can't help but wince a little as you realise that you've lost your virginity to some stranger who signed up to try and get you pregnant.");
				}
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[pc.thought(Pregnant... From my first time... I'm sure that's not possible...)] you think to yourself, letting out an involuntary [pc.moan] as [npc.name] pulls back and thrusts into you once more."
					+ "</p>"
					+ "<p>"
						+ "As [npc.she] fills your freshly popped cherry with [npc.her] [npc.cock+], you hear [npc.herHim] taunting you from the other side of the wall."
						+ " [npc.speechNoExtraEffects(What a fucking slut! Choosing to lose your virginity to a game of pregnancy roulette! Hah! Glad I'll never be the one who has to tell our kids how they were conceived!)]"
					+ "</p>");
		}
		
		return sb.toString();
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return UtilText.parse(penetratingCharacter,
				"<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
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
					+ " As your [pc.labia+] spread lewdly around the "
					+ (penetrating==SexAreaPenetration.PENIS
						?"hot, thick [npc.cock]"
						:penetrating.getName(penetratingCharacter))
					+ " that's pumping in and out of you, you start convincing yourself that this is all you're good for."
				+ "</p>"
				+ "<p style='text-align:center;'>"
				+ UtilText.parsePlayerThought("If I'm not a virgin, that makes me a slut...<br/>"
						+ "Just a slut to be fucked by whoever wants to use me...<br/>"
						+ "After this [npc.race] is done with me, I bet it won't be long before I'm fucked by someone else...")
				+ "</p>"
				+ "<p>"
					+ "You're vaguely aware of [npc.name] [npc.moaning] in triumphant pleasure as [npc.she] realises that [npc.sheHas] just taken your virginity."
					+ " With a desperate [pc.moan],"
					+ (Main.game.getPlayer().hasLegs()
						?" you spread your legs and"
						:" you")
					+ " resign yourself to the fact that you're now nothing more than a"
					+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
				+ "</p>");
	}
	
}