package com.lilithsthrone.game.character.npc.dominion;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.places.dominion.harpyNests.HarpyNestNympho;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.8
 * @version 0.1.89
 * @author Innoxia
 */
public class HarpyNympho extends NPC {

	private static final long serialVersionUID = 1L;

	public HarpyNympho() {
		this(false);
	}
	
	public HarpyNympho(boolean isImported) {
		super(new NameTriplet("Lexi"),
				"One of the more notable harpy matriarchs, Lexi is the leader of a flock of harpies."
						+ " Either due to her obsession with sex, or perhaps because she's not as cruel as a typical harpy, Lexi is far more accepting of males than a typical matriarch."
						+ " As a result, her flock serves as her personal harem; it's members constantly trying to sate their matriarch's never-ending lust.",
				7, Gender.F_V_B_FEMALE, RacialBody.HARPY, RaceStage.LESSER,
				new CharacterInventory(30), WorldType.HARPY_NEST, PlaceType.HARPY_NESTS_HARPY_NEST_PINK, true);

		this.setPersonality(Util.newHashMapOfValues(
				new Value<>(PersonalityTrait.AGREEABLENESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.CONSCIENTIOUSNESS, PersonalityWeight.AVERAGE),
				new Value<>(PersonalityTrait.EXTROVERSION, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.NEUROTICISM, PersonalityWeight.HIGH),
				new Value<>(PersonalityTrait.ADVENTUROUSNESS, PersonalityWeight.HIGH)));
		
		if(!isImported) {
			this.setSexualOrientation(SexualOrientation.AMBIPHILIC);
	
			this.addFetish(Fetish.FETISH_CUM_ADDICT);
			this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ANAL_RECEIVING);
			this.addFetish(Fetish.FETISH_ORAL_GIVING);
			this.addFetish(Fetish.FETISH_BREASTS_SELF);
			this.addFetish(Fetish.FETISH_EXHIBITIONIST);
			
			this.setEyeCovering(new Covering(BodyCoveringType.EYE_HARPY, Colour.EYE_GREEN));
			this.setHairCovering(new Covering(BodyCoveringType.HAIR_HARPY, Colour.FEATHERS_PINK), true);
			this.setSkinCovering(new Covering(BodyCoveringType.FEATHERS, Colour.FEATHERS_WHITE), true);
			this.setSkinCovering(new Covering(BodyCoveringType.HUMAN, Colour.SKIN_OLIVE), true);
			
			this.setFemininity(95);
			
			this.setVaginaVirgin(false);
			this.setVaginaWetness(Wetness.FOUR_SLIMY.getValue());
			this.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue(), true);
			
			this.setAssVirgin(false);
			this.setFaceVirgin(false);
			this.setBreastSize(CupSize.C.getMeasurement());
			
			this.setHeight(164);
			
			this.setPiercedEar(true);
	
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.GROIN_CROTCHLESS_PANTIES, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.CHEST_OPEN_CUP_BRA, Colour.CLOTHING_PINK, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.ANKLE_BRACELET, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.WRIST_BANGLE, Colour.CLOTHING_GOLD, false), true, this);
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.FINGER_RING, Colour.CLOTHING_GOLD, false), true, this);
			
			this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.PIERCING_EAR_BASIC_RING, Colour.CLOTHING_SILVER, false), true, this);
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
		this.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
	}

	@Override
	public boolean isUnique() {
		return true;
	}
	
	@Override
	public String getSpeechColour() {
		if(Main.getProperties().hasValue(PropertyValue.lightTheme)) {
			return "#D60AB8";
			
		} else {
			return "#F967E3";
		}
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
	public void endSex(boolean applyEffects) {
	}
	
	@Override
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return SexPace.SUB_EAGER;
	}

	// Combat:
	
	public int getEscapeChance() {
		return 0;
	}
	
	@Override
	public String getCombatDescription() {
		return UtilText.parse(this,
				"After watching you defeat [nymphoHarpyCompanion.name], [nymphoHarpy.name] rushes forwards, determined to teach you a lesson in front of her flock.");
	}
	
	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if (victory) {
			return new Response("", "", HarpyNestNympho.HARPY_NEST_NYMPHO_FIGHT_BEAT_NYMPHO) {
				@Override
				public void effects() {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.nymphoPacified);
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(AbstractItemType.generateItem(ItemType.HARPY_MATRIARCH_NYMPHO_LOLLIPOP), false, true));
					
					if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_ONE) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_TWO));
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_TWO) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_THREE));
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_HARPY_PACIFICATION) == Quest.HARPY_PACIFICATION_THREE) {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_HARPY_PACIFICATION, Quest.HARPY_PACIFICATION_REWARD));
					}
				}
			};
		} else {
			return new Response("", "", HarpyNestNympho.HARPY_NEST_NYMPHO_FIGHT_LOSE_TO_MATRIARCH);
		}
	}


	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		if(user.isPlayer() && !target.isPlayer() && (item.getItemType().equals(ItemType.FETISH_UNREFINED) || item.getItemType().equals(ItemType.FETISH_REFINED))){
			if(Sex.isDom(Main.game.getPlayer())) {
				Main.game.getPlayer().removeItem(item);
				return "<p>"
							+ "Taking your "+item.getName()+" out from your inventory, you hold it out to [npc.name]."
							+ " Seeing what you're offering [npc.herHim], [npc.she] lets out a little laugh, "
							+ " [npc.speechNoEffects(There's no way I'm drinking tha-)]"
						+ "</p>"
							+ "Not liking the start of [npc.her] response, you quickly remove the bottle's stopper, and rather unceremoniously shove the neck down [npc.her] throat."
							+ " You pinch [npc.her] nose and hold [npc.herHim] still, forcing [npc.herHim] to gulp down all of the liquid before finally letting [npc.her] go."
							+ " [npc.She] coughs and splutters for a moment, before letting out a lewd little cry as [npc.she] wipes the liquid from [npc.her] mouth,"
							+ " [npc.speechNoEffects(~Aah!~ I feel... all hot inside...)]"
						+ "</p>"
						+ Main.game.getPlayer().useItem(item, target, false);
			} else {
				return "<p>"
							+ "You try to give [npc.name] your "+item.getName()+", but [npc.she] takes one look at it and sighs,"
							+ " [npc.speechNoEffects(Mmm, yeah, that's not happening. I'm really not going to drink some random potion of yours!)]"
						+ "</p>";
			}
		}
		
		return super.getItemUseEffects(item, user, target);
	}
}