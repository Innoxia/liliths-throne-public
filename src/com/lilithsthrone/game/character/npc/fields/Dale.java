package com.lilithsthrone.game.character.npc.fields;

import java.time.Month;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
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
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.4.2
 * @version 0.4.2
 * @author Innoxia
 */
public class Dale extends NPC {

	public Dale() {
		this(false);
	}
	
	public Dale(boolean isImported) {
		super(isImported, new NameTriplet("Dale"), "Lane",
				"Dale is the receptionist at Evelyx's Dairy.",
				28, Month.JANUARY, 2,
				15,
				Gender.M_P_MALE, Subspecies.HORSE_MORPH_DONKEY, RaceStage.GREATER,
				new CharacterInventory(10),
				WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"),
				true);
		if(!isImported) {
			this.setPlayerKnowsName(false);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1.8")) {
			this.setStartingBody(true);
		}
	}

	@Override
	public void setupPerks(boolean autoSelectPerks) {
		PerkManager.initialisePerks(this,
				Util.newArrayListOfValues(),
				Util.newHashMapOfValues(
						new Value<>(PerkCategory.PHYSICAL, 1),
						new Value<>(PerkCategory.LUST, 0),
						new Value<>(PerkCategory.ARCANE, 0)));
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Persona:
		if(setPersona) {
			this.setPersonalityTraits(
					PersonalityTrait.CONFIDENT,
					PersonalityTrait.KIND);
			
			this.setSexualOrientation(SexualOrientation.GYNEPHILIC);
			
			this.setHistory(Occupation.NPC_RECEPTIONIST);
			
			this.addFetish(Fetish.FETISH_DOMINANT);
			this.addFetish(Fetish.FETISH_DEFLOWERING);
			
			this.setFetishDesire(Fetish.FETISH_ORAL_RECEIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_VAGINAL_GIVING, FetishDesire.THREE_LIKE);
			this.setFetishDesire(Fetish.FETISH_PENIS_GIVING, FetishDesire.THREE_LIKE);
			
//			this.setFetishDesire(Fetish.FETISH_SUBMISSIVE, FetishDesire.ONE_DISLIKE);
			
			this.setFetishDesire(Fetish.FETISH_MASOCHIST, FetishDesire.ZERO_HATE);
			this.setFetishDesire(Fetish.FETISH_SADIST, FetishDesire.ZERO_HATE);
		}
		
		// Body:
		// Core:
		this.setHeight(178);
		this.setFemininity(25);
		this.setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
		this.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());

		
		// Coverings:
		this.setEyeCovering(new Covering(BodyCoveringType.EYE_HORSE_MORPH, PresetColour.EYE_BROWN));
		this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, PresetColour.SKIN_DARK), true);
		body.getCoverings().put(BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.MARKED, CoveringModifier.SHORT, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));

		this.setHairCovering(new Covering(BodyCoveringType.HAIR_HUMAN, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false), false);
		body.getCoverings().put(BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, PresetColour.COVERING_BROWN_DARK, false, PresetColour.COVERING_WHITE, false));
		this.setHairLength(10);
		this.setHairStyle(HairStyle.NONE);

		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HUMAN, PresetColour.COVERING_BLACK), false);
		this.setHairCovering(new Covering(BodyCoveringType.BODY_HAIR_HORSE_HAIR, PresetColour.COVERING_BLACK), false);
		this.setUnderarmHair(BodyHair.FOUR_NATURAL);
		this.setAssHair(BodyHair.FOUR_NATURAL);
		this.setPubicHair(BodyHair.FOUR_NATURAL);
		this.setFacialHair(BodyHair.ZERO_NONE);

//		this.setHandNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, PresetColour.COVERING_RED));
//		this.setFootNailPolish(new Covering(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, PresetColour.COVERING_RED));
//		this.setBlusher(new Covering(BodyCoveringType.MAKEUP_BLUSHER, PresetColour.COVERING_RED));
//		this.setLipstick(new Covering(BodyCoveringType.MAKEUP_LIPSTICK, PresetColour.COVERING_RED));
//		this.setEyeLiner(new Covering(BodyCoveringType.MAKEUP_EYE_LINER, PresetColour.COVERING_BLACK));
//		this.setEyeShadow(new Covering(BodyCoveringType.MAKEUP_EYE_SHADOW, PresetColour.COVERING_PURPLE));
		
		// Face:
		this.setFaceVirgin(true);
		this.setLipSize(LipSize.ONE_AVERAGE);
		this.setFaceCapacity(Capacity.ZERO_IMPENETRABLE, true);
		// Throat settings and modifiers
		this.setTongueLength(TongueLength.ZERO_NORMAL.getMedianValue());
		// Tongue modifiers
		
		// Chest:
		this.setNippleVirgin(true);
		this.setBreastSize(CupSize.FLAT.getMeasurement());
		this.setBreastShape(BreastShape.SIDE_SET);
		this.setNippleSize(NippleSize.ZERO_TINY);
		this.setAreolaeSize(AreolaeSize.ZERO_TINY);
		// Nipple settings and modifiers
		
		// Ass:
		this.setAssVirgin(true);
		this.setAssBleached(false);
		this.setAssSize(AssSize.TWO_SMALL);
		this.setHipSize(HipSize.TWO_NARROW);
		this.setAssCapacity(Capacity.ZERO_IMPENETRABLE, true);
		this.setAssWetness(Wetness.ZERO_DRY);
		this.setAssElasticity(OrificeElasticity.ONE_RIGID.getValue());
		this.setAssPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());
		// Anus modifiers
		
		// Penis:
		this.setPenisVirgin(false);
		this.setPenisGirth(PenetrationGirth.FOUR_GIRTHY);
		this.setPenisSize(26);
		this.setTesticleSize(TesticleSize.THREE_LARGE);
		this.setPenisCumStorage(120);
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
		
		if(this.getLocationPlaceType()==PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory")) { // Sleeping
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
			
		} else {
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_groin_boxers", PresetColour.CLOTHING_DESATURATED_BROWN, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing(ClothingType.WRIST_MENS_WATCH, PresetColour.CLOTHING_IRON, PresetColour.CLOTHING_IRON, PresetColour.CLOTHING_IRON, false), true, this);
			
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_leg_trousers", PresetColour.CLOTHING_BLACK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_hips_leather_belt", PresetColour.CLOTHING_DESATURATED_BROWN_DARK, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_torso_long_sleeved_shirt", PresetColour.CLOTHING_BLUE_NAVY, false), true, this);
			this.equipClothingFromNowhere(Main.game.getItemGen().generateClothing("innoxia_neck_tie", PresetColour.CLOTHING_BLACK, false), true, this);
		}
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	private boolean needsMoving = false;
	@Override
	public void hourlyUpdate(int hour) {
		// Sleeps between 01:00-05:00
		if(!Main.game.getCharactersPresent().contains(this)) {
			if(((hour>=1 && hour<5) && this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory"))
					 || (!(hour>=1 && hour<5) && this.getLocationPlaceType()!=PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"))) {
				needsMoving = true;
			}
		}
	}
	
	@Override
	public void turnUpdate() {
		// Sleeps between 01:00-05:00
		if(needsMoving && !Main.game.getCharactersPresent().contains(this)) {
			if(Main.game.isHourBetween(1, 5)) {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_reception_sex", false);
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_dormitory"), false);
				this.equipClothing();
				Main.game.updateResponses();
				
			} else {
				Main.game.getDialogueFlags().setFlag("innoxia_evelyx_dorm_sex", false);
				this.setLocation(WorldType.getWorldTypeFromId("innoxia_fields_dairyFarm"), PlaceType.getPlaceTypeFromId("innoxia_fields_dairyFarm_reception"), true);
				this.equipClothing();
			}
		}
	}
	
	@Override
	public void endSex() {
		this.cleanAllDirtySlots(true);
		this.cleanAllClothing(true, false);
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	@Override
	public void changeFurryLevel() {
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null;
	}
	
	@Override
	public SexPace getSexPaceDomPreference() {
		return SexPace.DOM_GENTLE;
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character) {
		return SexPace.SUB_EAGER;
	}

	@Override
	public String getSpecialPlayerVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating, GameCharacter receivingCharacter, SexAreaOrifice penetrated) {
		if(!receivingCharacter.isPlayer() || penetrating != SexAreaPenetration.PENIS || (penetrated != SexAreaOrifice.VAGINA && penetrated != SexAreaOrifice.ANUS)) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(penetrated == SexAreaOrifice.VAGINA) {
			if(Main.game.getPlayer().hasHymen()) {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("As [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.pussy+], you can't help but let out a desperate, shuddering wail."
									+ " Being so enamoured with the idea of being a pure virgin, you don't know what on Earth possessed you to offer him your virginity in a place like this,"
										+ " but as [npc.namePos] [npc.cock+] claims your precious virginity, you don't have any time to reflect on your choice."
									+ " The only thing that's on your mind is the agonising pain of having your hymen torn by the [npc.cockGirth] donkey-dick that's been pushed into your now-despoiled cunt.");
					} else if(Main.game.getPlayer().hasFetish(Fetish.FETISH_MASOCHIST)) {
						sb.append("As [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.pussy+] to tear your hymen and claim your virginity, you can't help but let out a lewd, masochistic scream."
								+ " The agonising pain of having your hymen torn by [npc.namePos] [npc.cockGirth] donkey-dick completely overwhelms you, and you can't help but squeal and moan in a delightful haze of overwhelming ecstasy.");
					} else {
						sb.append("As [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.pussy+] to claim your virginity, you can't help but let out a desperate, shuddering wail."
								+ " The agonising pain of having your hymen torn by [npc.namePos] [npc.cockGirth] donkey-dick completely overwhelms you, and you squirm about in discomfort as you try to endure this painful experience.");
					}
				sb.append("</p>");
				
			} else {
				sb.append("<p>");
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
						sb.append("As [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.pussy+], you can't help but let out a desperate, shuddering wail."
								+ " Being so enamoured with the idea of being a pure virgin, you don't know what on Earth possessed you to agree to this,"
									+ " but as [npc.namePos] [npc.cock+] claims your precious virginity, you don't have any time to reflect on your choice."
								+ " The only thing that's on your mind is the fact that you've being broken in by the [npc.cockGirth] donkey-dick that's been pushed into your now-despoiled cunt.");
					} else {
						sb.append("As [npc.namePos] [npc.cock+] is slowly pushed into your [pc.pussy+] to claim your virginity, you can't help but let out a desperately lewd [pc.moan]."
								+ " [npc.NamePos] [npc.cockGirth] donkey-dick is giving you an intense pleasure unlike any you've felt before, and you can't help but continue to scream and [pc.moan] in a delightful haze of overwhelming ecstasy.");
					}
				sb.append("</p>");
			}
			
			sb.append("<p>"
						+ "Upon hearing your ear-splitting wail, [npc.name] instantly stops thrusting forwards, and in a worried tone [npc.she] asks,"
						+ " [npc.speechNoExtraEffects(Are you ok, [pc.name]? We can stop if this is too much for you...)]"
					+ "</p>");
			
			sb.append("<p>");
				if(Main.game.getPlayer().hasFetish(Fetish.FETISH_PURE_VIRGIN)) {
					sb.append("Although you can't help but shed a tear as you realise that you've lost your precious, pure virginity, you're comforted by the fact that [npc.name] seems to be genuinely concerned about you.");
				} else {
					sb.append("Although you can't help but wince as a pang of pain shoots up from your cock-filled pussy, you're comforted by the fact that [npc.name] seems to be genuinely concerned about you.");
				}
				sb.append(" After taking a moment to catch your breath, you realise that there's no going back from this, and so you pant, [pc.speechNoExtraEffects(I'm fine... You can keep going...)]");
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[npc.speechNoExtraEffects(Ok, I'll take it real slow,)] [npc.name] responds, and you try to suppress your whimpers as [npc.she] resumes where [npc.she] left off and slowly pushes [npc.her] huge donkey-cock into your cunt."
						+ " As [npc.she] fills your freshly popped cherry with [npc.her] throbbing shaft, [npc.name] reassures you with a soft grunt,"
						+ " [npc.speechNoExtraEffects(You're doing really well for your first time... You'll get into the swing of it in a moment...)]"
					+ "</p>"
					+ "<p>"
						+ "Sure enough, after [npc.nameHas] pulled [npc.her] cock back out of your pussy, the pain starts to fade away,"
							+ " and you find yourself letting out a lewd moan as you concentrate on the feeling of [npc.her] flat, flared head pushing its way into your broken-in pussy as [npc.she] gently thrusts forwards once again..."
					+ "</p>");
			
		} else if(penetrated == SexAreaOrifice.ANUS) {
			sb.append("<p>");
				sb.append("As [npc.name] slowly pushes [npc.her] [npc.cock+] into your [pc.asshole+] to claim your virginity, you can't help but let out a desperate, shuddering wail."
						+ " The uncomfortable sensation of having [npc.namePos] [npc.cockGirth] donkey-dick slowly forcing its way inside of you causes you to squirm about in discomfort.");
			sb.append("</p>");
			
			sb.append("<p>"
						+ "Upon hearing your distressed cry, [npc.name] instantly stops thrusting forwards, and in a worried tone [npc.she] asks,"
						+ " [npc.speechNoExtraEffects(Are you ok, [pc.name]? We can stop if this is too much for you...)]"
					+ "</p>");
			
			sb.append("<p>");
				sb.append("Although you can't help but wince as a pang of pain shoots up from your cock-filled ass, you're comforted by the fact that [npc.name] seems to be genuinely concerned about you.");
				sb.append(" After taking a moment to catch your breath, you realise that there's no going back from this, and so you pant, [pc.speechNoExtraEffects(I'm fine... You can keep going...)]");
			sb.append("</p>");
			
			sb.append("<p>"
						+ "[npc.speechNoExtraEffects(Ok, I'll take it real slow,)] [npc.name] responds, and you try to suppress your whimpers as [npc.she] resumes where [npc.she] left off and slowly pushes [npc.her] huge donkey-cock into your back door."
						+ " As [npc.she] fills your ass with [npc.her] throbbing shaft, [npc.name] reassures you with a soft grunt,"
						+ " [npc.speechNoExtraEffects(You're doing really well for your first time... You'll get into the swing of it in a moment...)]"
					+ "</p>"
					+ "<p>"
						+ "Sure enough, after [npc.nameHas] pulled [npc.her] cock back out of your asshole, the pain starts to fade away,"
							+ " and you find yourself letting out a lewd moan as you concentrate on the feeling of [npc.her] flat, flared head pushing its way into your broken-in butt as [npc.she] gently thrusts forwards once again..."
					+ "</p>");
		}
		
		return UtilText.parse(this,  sb.toString());
	}
	
	@Override
	public String getSpecialPlayerPureVirginityLoss(GameCharacter penetratingCharacter, SexAreaPenetration penetrating) {
		return "<p style='text-align:center;'>"
					+ "<b style='color:"+PresetColour.GENERIC_TERRIBLE.toWebHexString()+";'>Broken Virgin</b>"
				+ "</p>"
				+ "<p>"
					+ "As [npc.name] once again begins to stuff your cunt with [npc.her] hot donkey-cock, the sudden realisation of what's just happened hits you like a sledgehammer."
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+"[pc.thought(I-I've lost my virginity?!"
					+ "<br/>Like... Like <b>this</b>?!)]"
				+ "</p>"
				+ "<p>"
					+ "You don't know what's worse, losing the virginity that you prized so highly, or the fact that you're actually enjoying it."
					+ " As your [pc.labia+] spread lewdly around the hot, thick [npc.cock] slowly sliding in and out of you, you start convincing yourself that this is all you're good for."
				+ "</p>"
				+ "<p style='text-align:center;'>"
					+ "[pc.thought(If I'm not a virgin, that makes me a slut..."
					+ "<br/>Just a slut to be fucked and pumped full of cum..."
					+ "<br/>I wonder if all cocks are as good as [npc.namePos]...)]"
				+ "</p>"
				+ "<p>"
					+ "You're vaguely aware of [npc.namePos] reassuring comments fading away as [npc.she] starts to focus [npc.her] attention on fucking you."
					+ " With a desperate moan,"
					+ (Main.game.getPlayer().hasLegs()
						?" you spread your legs and"
						:" you")
					+ " resign yourself to the fact that you're now nothing more than a"
					+ " <b style='color:"+StatusEffect.FETISH_BROKEN_VIRGIN.getColour().toWebHexString()+";'>broken virgin</b>..."
				+ "</p>";
	}
}
