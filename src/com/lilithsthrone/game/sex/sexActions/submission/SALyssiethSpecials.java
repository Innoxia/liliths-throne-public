package com.lilithsthrone.game.sex.sexActions.submission;

import com.lilithsthrone.game.character.PlayerCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.FluidExpulsion;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.npc.submission.Lyssieth;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.ParserTag;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMLyssiethDemonTF;
import com.lilithsthrone.game.sex.positions.SexPosition;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotDesk;
import com.lilithsthrone.game.sex.positions.slots.SexSlotLyingDown;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionCategory;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.3
 * @version 0.3
 * @author Innoxia
 */
public class SALyssiethSpecials {

	public static final void playerGrowDemonicVagina() {
		Main.game.getPlayer().setVaginaType(VaginaType.DEMON_COMMON, true);
		Main.game.getPlayer().setVaginaVirgin(true);
		Main.game.getPlayer().setVaginaLabiaSize(LabiaSize.ZERO_TINY);
		Main.game.getPlayer().setVaginaWetness(Wetness.SEVEN_DROOLING);
		Main.game.getPlayer().setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMinimumValue(), true);
		Main.game.getPlayer().setVaginaElasticity(OrificeElasticity.FOUR_LIMBER.getValue());
		Main.game.getPlayer().setVaginaPlasticity(OrificePlasticity.THREE_RESILIENT.getValue());

		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.VAGINA, Colour.SKIN_RED_DARK), false);
	}

	public static final void playerGrowDemonicPenis() {
		Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
		Main.game.getPlayer().setPenisSize(20);
		Main.game.getPlayer().setPenisGirth(PenisGirth.THREE_THICK.getValue());
		Main.game.getPlayer().setTesticleSize(TesticleSize.THREE_LARGE);
		Main.game.getPlayer().setPenisCumStorage(750);
		Main.game.getPlayer().setPenisCumExpulsion(FluidExpulsion.FOUR_HUGE.getMedianValue());
		Main.game.getPlayer().fillCumToMaxStorage();
		
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED), false);
	}

	public static final void playerGrowDemonicBreasts(CupSize breastCup, CupSize crotchBreastCup) {
		PlayerCharacter player = Main.game.getPlayer();
		if (player.getBreastCrotchType() != BreastType.NONE) {
			player.setBreastCrotchType(BreastType.DEMON_COMMON);
			if (crotchBreastCup != null) {
				player.setMinimumBreastCrotchSize(crotchBreastCup);
			}
		}
		player.setBreastType(BreastType.DEMON_COMMON);
		if (breastCup != null) {
			player.setMinimumBreastSize(breastCup);
		}
		
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES, Colour.SKIN_RED_DARK), false);
		Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.NIPPLES_CROTCH, Colour.SKIN_RED_DARK), false);
	}

	public static final void playerGrowDemonicBreasts(CupSize breastCup) {
		playerGrowDemonicBreasts(breastCup, null);
	}

	public static final void playerGrowDemonicBreasts() {
		playerGrowDemonicBreasts(null, null);
	}

	public static final SexAction DEMON_TF_STAGE_1_CHOICE_GET_FUCKED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1
					&& Sex.getPosition()==SexPosition.STANDING
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Get fucked";
		}

		@Override
		public String getActionDescription() {
			return "Allow Lyssieth to push you down onto your back over her desk, and let her fuck you.<br/>"
								+ "[style.italicsFeminine(Lyssieth will feminise you and give you a demonic vagina!)]<br/>"
								+ (Main.game.getPlayer().hasPenis()
										?"[style.italicsTfSexual(Lyssieth will also temporarily remove your cock!)]"
										:"");
		}

		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_1_CHOICE_GET_FUCKED");
		}

		@Override
		public void applyEffects() {
			specialActionTriggered = false;
			Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
			
			if(Main.game.getPlayer().getFemininityValue()<75) {
				Main.game.getPlayer().setFemininity(75);
			} else {
				Main.game.getPlayer().incrementFemininity(15);
			}
			Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			Main.game.getPlayer().setHipSize(HipSize.FIVE_VERY_WIDE);
			Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE);
			Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			Main.game.getPlayer().setHeight(168);
			Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_RED_DARK), false);
			playerGrowDemonicBreasts(CupSize.DD);
			Main.game.getPlayer().setPenisType(PenisType.NONE);
			playerGrowDemonicVagina();
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.OVER_DESK,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotDesk.BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_BACK))));
			
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};

	public static final SexAction DEMON_TF_STAGE_1_CHOICE_GET_FUCKED_ANALLY = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1
					&& Sex.getPosition()==SexPosition.STANDING
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Ask for anal";
		}

		@Override
		public String getActionDescription() {
			return "Allow Lyssieth to push you down onto your back over her desk, and request that she use your ass.<br/>"
								+ "[style.italicsFeminine(Lyssieth will feminise you!)]<br/>"
								+ (Main.game.getPlayer().hasPenis()
										?"[style.italicsFeminine(Lyssieth will sissify your cock!)]"
										:"");
		}

		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_1_CHOICE_GET_FUCKED_ANALLY");
		}

		@Override
		public void applyEffects() {
			specialActionTriggered = false;
			Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
			
			if(Main.game.getPlayer().getFemininityValue()<=Femininity.MASCULINE.getMaximumFemininity()) {
				Main.game.getPlayer().setFemininity(50);
				Main.game.getPlayer().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
				Main.game.getPlayer().setHipSize(HipSize.THREE_GIRLY);
				Main.game.getPlayer().setAssSize(AssSize.THREE_NORMAL);
				Main.game.getPlayer().setMuscle(Muscle.TWO_TONED.getMedianValue());
				Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
				Main.game.getPlayer().setHeight(168);
				Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
				Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_RED_DARK), false);
				playerGrowDemonicBreasts(CupSize.AA);
			} else {
				Main.game.getPlayer().incrementFemininity(15);
				Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
				Main.game.getPlayer().setHipSize(HipSize.FIVE_VERY_WIDE);
				Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE);
				Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
				Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
				Main.game.getPlayer().setHeight(168);
				Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
				Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_RED_DARK), false);
				playerGrowDemonicBreasts(CupSize.DD);
			}
			
			if(Main.game.getPlayer().hasPenis()) {
				Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
				Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED), false);
				Main.game.getPlayer().setPenisSize(8);
				Main.game.getPlayer().setTesticleSize(TesticleSize.ONE_TINY);
				Main.game.getPlayer().fillCumToMaxStorage();
			}
			if(Main.game.getPlayer().hasVagina()) {
				playerGrowDemonicVagina();
			}
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.OVER_DESK,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotDesk.BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_BACK))));
			
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction DEMON_TF_STAGE_1_CHOICE_FUCK_LYSSIETH = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==1
					&& Sex.getPosition()==SexPosition.STANDING
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Fuck her";
		}

		@Override
		public String getActionDescription() {
			return "You never agreed to let Lyssieth fuck you. Push her down onto her desk and fuck her instead.<br/>"
								+ (!Main.game.getPlayer().isFeminine()
										?"[style.italicsMasculine(Lyssieth will make you more masculine!)]"
										:"[style.italicsDisabled(This will have no extra feminising effect.)]")
								+ (!Main.game.getPlayer().hasPenis()
										?"[style.italicsTfSexual(Lyssieth will give you a cock with which to fuck her!)]"
										:"");
		}

		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_1_CHOICE_FUCK_LYSSIETH");
		}

		@Override
		public void applyEffects() {
			specialActionTriggered = false;
			Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
			
			playerGrowDemonicPenis();
			Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_RED_DARK), false);
			playerGrowDemonicBreasts();
			
			if(!Main.game.getPlayer().isFeminine()) {
				Main.game.getPlayer().setHeight(208);
				if(Main.game.getPlayer().getFemininityValue()>25) {
					Main.game.getPlayer().setFemininity(25);
				} else {
					Main.game.getPlayer().incrementFemininity(-15);
				}
				Main.game.getPlayer().setHairLength(HairLength.TWO_SHORT.getMedianValue());
				if(Main.game.getPlayer().getMuscleValue()<Muscle.TWO_TONED.getMedianValue()) {
					Main.game.getPlayer().setMuscle(Muscle.TWO_TONED.getMedianValue());
				} else {
					Main.game.getPlayer().incrementMuscle(25);
				}
				if(Main.game.getPlayer().getBodySizeValue()<BodySize.TWO_AVERAGE.getMedianValue()) {
					Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
				} else {
					Main.game.getPlayer().incrementBodySize(10);
				}
				Main.game.getPlayer().setVaginaType(VaginaType.NONE);
			} else {
				if(Main.game.getPlayer().hasVagina()) {
					playerGrowDemonicVagina();
				}
			}
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.OVER_DESK,
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotDesk.OVER_DESK_ON_BACK))));

			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};
	
	
	// STAGE 2
	public static boolean specialActionTriggered = false; // OK to use this and not reset it, as the game can't be saved mid-sex, and this sex scene is only ever used once.
	
	public static final SexAction DEMON_TF_STAGE_2_CHOICE_FUCK_LYSSIETH = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2
					&& !specialActionTriggered
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))==0;
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Fuck her";
		}

		@Override
		public String getActionDescription() {
			return "Allow Lyssieth to get down on her hands and knees, and kneel down behind her to fuck her in the doggy-style position.<br/>"
								+ "[style.italicsDisabled(This will have no extra feminising effect.)]<br/>"
								+ (!Main.game.getPlayer().hasPenis() || Main.game.getPlayer().getPenisType().getRace()!=Race.DEMON
										?"[style.italicsTfSexual(Lyssieth will give you a demonic cock!)]"
										:"");
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().getPenisType().getRace()==Race.DEMON && Main.game.getPlayer().getPenisRawSizeValue()<=4) {
				return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_CHOICE_FUCK_LYSSIETH_AS_SISSY");
			} else {
				return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_CHOICE_FUCK_LYSSIETH");
			}
		}

		@Override
		public void applyEffects() {
			Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
			specialActionTriggered = true;
			
			if(!Main.game.getPlayer().hasPenis() || Main.game.getPlayer().getPenisType().getRace()!=Race.DEMON) {
				playerGrowDemonicPenis();
			}

			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.ALL_FOURS,
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.BEHIND)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotAllFours.ALL_FOURS))));

			Main.game.getPlayer().fillCumToMaxStorage();
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction DEMON_TF_STAGE_2_CHOICE_LYSSIETH_ORAL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2
					&& !specialActionTriggered
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))==0;
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Ask for oral";
		}

		@Override
		public String getActionDescription() {
			return "Ask Lyssieth to service you with her mouth instead.<br/>"
							+ "[style.italicsDisabled(This will have no extra feminising effect.)]";
		}

		@Override
		public String getDescription() {
			if(Main.game.getPlayer().hasPenis() && Main.game.getPlayer().getPenisType().getRace()==Race.DEMON && Main.game.getPlayer().getPenisRawSizeValue()<=4) {
				return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_CHOICE_LYSSIETH_ORAL_AS_SISSY");
			} else {
				return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_CHOICE_LYSSIETH_ORAL");
			}
		}

		@Override
		public void applyEffects() {
			if(Main.game.getPlayer().hasPenis()) {
				Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
			} else {
				Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
			}
			specialActionTriggered = true;
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.SITTING,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotSitting.PERFORMING_ORAL)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING))));
			
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};

	public static final SexAction DEMON_TF_STAGE_2_ASK_FOR_MORE_GETTING_FUCKED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2
					&& !specialActionTriggered
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))==0;
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			if(Main.game.getNpc(Lyssieth.class).getForeplayPreference(Main.game.getPlayer()).getTargetedSexArea()==SexAreaOrifice.ANUS) {
				return "More anal";
			}
			return "Ask for more";
		}

		@Override
		public String getActionDescription() {
			if(Main.game.getNpc(Lyssieth.class).getForeplayPreference(Main.game.getPlayer()).getTargetedSexArea()==SexAreaOrifice.ANUS) {
				return "Ask Lyssieth to carry on fucking your ass instead.<br/>"
							+ "[style.italicsFeminine(Lyssieth will fully feminise you!)]"//TODO
							+ (Main.game.getPlayer().hasPenis()
									?"<br/>[style.italicsTfSexual(Lyssieth will also fully sissify your cock!)]"
									:"");
			}
			return "Ask Lyssieth to carry on fucking your pussy instead.<br/>"
						+ "[style.italicsFeminine(Lyssieth will fully feminise you!)]";
		}

		@Override
		public String getDescription() {
			if(Main.game.getNpc(Lyssieth.class).getForeplayPreference(Main.game.getPlayer()).getTargetedSexArea()==SexAreaOrifice.ANUS) {
				return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_ASK_FOR_MORE_GETTING_FUCKED_ANAL");
			}
			return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_ASK_FOR_MORE_GETTING_FUCKED");
		}

		@Override
		public void applyEffects() {
			specialActionTriggered = true;
			
			Main.game.getPlayer().setFemininity(100);
			Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMaximumValue());
			Main.game.getPlayer().setHipSize(HipSize.FIVE_VERY_WIDE);
			Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE);
			Main.game.getPlayer().setLipSize(LipSize.THREE_PLUMP);
			Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			Main.game.getPlayer().setHeight(168);
			Main.game.getPlayer().setBreastSize(CupSize.F);
			
			if(Main.game.getNpc(Lyssieth.class).getForeplayPreference(Main.game.getPlayer()).getTargetedSexArea()==SexAreaOrifice.ANUS) {
				if(Main.game.getPlayer().hasPenis()) {
					Main.game.getPlayer().setPenisType(PenisType.DEMON_COMMON);
					Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED), false);
					Main.game.getPlayer().setPenisSize(3);
					Main.game.getPlayer().setTesticleSize(TesticleSize.ZERO_VESTIGIAL);
					Main.game.getPlayer().fillCumToMaxStorage();
				}
				if(Main.game.getPlayer().hasVagina()) {
					// Add anal-only vagina sticker:
					AbstractClothing pastie = AbstractClothingType.generateClothing("innoxia_vagina_sticker_anal_only", Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_PINK_HOT, Colour.CLOTHING_PURPLE_VERY_DARK, false);
					pastie.getEffects().clear();
					pastie.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.DRAIN, 0));
					pastie.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_ANAL_RECEIVING, TFPotency.MAJOR_BOOST, 0));
					pastie.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_VAGINAL_RECEIVING, TFPotency.MAJOR_DRAIN, 0));
					pastie.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_SUBMISSIVE, TFPotency.BOOST, 0));
					pastie.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_DOMINANT, TFPotency.DRAIN, 0));
					pastie.setName("Lyssieth's buttslut pastie");
					Main.game.getPlayer().equipClothingFromNowhere(pastie, true, Main.game.getNpc(Lyssieth.class));
				}
				if(Main.game.getPlayer().hasPenis()) {
					// Add chastity cage:
					AbstractClothing cage = AbstractClothingType.generateClothing("innoxia_bdsm_ornate_chastity_cage", Colour.CLOTHING_PINK_LIGHT, Colour.CLOTHING_PINK_HOT, Colour.CLOTHING_GOLD, false);
					cage.getEffects().clear();
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.CLOTHING_SEALING, TFModifier.ARCANE_BOOST, TFPotency.DRAIN, 0));
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_PENIS_RECEIVING, TFPotency.MAJOR_BOOST, 0));
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BODY_PART, TFModifier.TF_MOD_FETISH_PENIS_GIVING, TFPotency.MAJOR_DRAIN, 0));
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_CUM_ADDICT, TFPotency.BOOST, 0));
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_CUM_STUD, TFPotency.DRAIN, 0));
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, 1));
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, TFPotency.MAJOR_DRAIN, 0));
					cage.addEffect(new ItemEffect(ItemEffectType.CLOTHING, TFModifier.TF_MOD_FETISH_BEHAVIOUR, TFModifier.TF_MOD_FETISH_DENIAL_SELF, TFPotency.MAJOR_BOOST, 0));
					
					cage.setName("Lyssieth's sissy cage");
					Main.game.getPlayer().equipClothingFromNowhere(cage, true, Main.game.getNpc(Lyssieth.class));
				}
			}
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.ALL_FOURS,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotAllFours.BEHIND)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))));
			
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};
	
	// These are for if the player has already fucked Lyssieth:
	
	public static final SexAction DEMON_TF_STAGE_2_CHOICE_GET_FUCKED = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2
					&& !specialActionTriggered
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Get fucked";
		}

		@Override
		public String getActionDescription() {
			return "Allow Lyssieth to push you down onto your back over her desk, and let her give you a demonic pussy for her to fuck.<br/>"
								+ "[style.italicsFeminine(Lyssieth will feminise you into a futa succubus!)]<br/>"
								+ (!Main.game.getPlayer().hasVagina()
										?"[style.italicsTfSexual(Lyssieth will give you a demonic vagina!)]"
										:"");
		}

		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_CHOICE_GET_FUCKED");
		}

		@Override
		public void applyEffects() {
			specialActionTriggered = true;
			
			Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
			
			Main.game.getPlayer().setFemininity(75);
			Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
			Main.game.getPlayer().setHipSize(HipSize.FIVE_VERY_WIDE);
			Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE);
			Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
			Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			Main.game.getPlayer().setHeight(168);
			Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
			Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_RED_DARK), false);
			playerGrowDemonicBreasts(CupSize.F);
			playerGrowDemonicVagina();
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.OVER_DESK,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotDesk.BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_BACK))));
			
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};

	public static final SexAction DEMON_TF_STAGE_2_CHOICE_GET_FUCKED_ANALLY = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2
					&& !specialActionTriggered
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Ask for anal";
		}

		@Override
		public String getActionDescription() {
			return "Allow Lyssieth to push you down onto your back over her desk, and request that she use your ass.<br/>"
						+ "[style.italicsFeminine(Lyssieth will feminise you!)]";
		}

		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_CHOICE_GET_FUCKED_ANALLY");
		}

		@Override
		public void applyEffects() {
			specialActionTriggered = true;
			
			Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
			
			if(Main.game.getPlayer().isFeminine()) {
				Main.game.getPlayer().incrementFemininity(25);
				Main.game.getPlayer().setHairLength(HairLength.FOUR_MID_BACK.getMedianValue());
				Main.game.getPlayer().setHipSize(HipSize.FIVE_VERY_WIDE);
				Main.game.getPlayer().setAssSize(AssSize.FOUR_LARGE);
				Main.game.getPlayer().setMuscle(Muscle.THREE_MUSCULAR.getMedianValue());
				Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
				Main.game.getPlayer().setHeight(168);
				Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
				Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_RED_DARK), false);
				playerGrowDemonicBreasts(CupSize.F);
				
			} else {
				Main.game.getPlayer().setFemininity(50);
				Main.game.getPlayer().setHairLength(HairLength.THREE_SHOULDER_LENGTH.getMedianValue());
				Main.game.getPlayer().setHipSize(HipSize.THREE_GIRLY);
				Main.game.getPlayer().setAssSize(AssSize.THREE_NORMAL);
				Main.game.getPlayer().setMuscle(Muscle.TWO_TONED.getMedianValue());
				Main.game.getPlayer().setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
				Main.game.getPlayer().setHeight(168);
				Main.game.getPlayer().setAssType(AssType.DEMON_COMMON);
				Main.game.getPlayer().setSkinCovering(new Covering(BodyCoveringType.ANUS, Colour.SKIN_RED_DARK), false);
				playerGrowDemonicBreasts(CupSize.AA);
			}
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.OVER_DESK,
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotDesk.BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotDesk.OVER_DESK_ON_BACK))));
			
			Main.game.getNpc(Lyssieth.class).fillCumToMaxStorage();
		}
	};
	
	public static final SexAction DEMON_TF_STAGE_2_MATING_PRESS = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.FOUR_HIGH,
			ArousalIncrease.FOUR_HIGH,
			CorruptionLevel.ONE_VANILLA,
			null,
			SexParticipantType.NORMAL) {

		@Override
		public SexActionCategory getCategory() {
			return SexActionCategory.MISCELLANEOUS;
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager() instanceof SMLyssiethDemonTF
					&& Sex.getNumberOfOrgasms(Main.game.getNpc(Lyssieth.class))==2
					&& !specialActionTriggered
					&& Sex.getCharacterPerformingAction().isPlayer()
					&& Sex.getSexTypeCount(Sex.getCharacterPerformingAction(), Main.game.getNpc(Lyssieth.class), new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))>0;
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.UNIQUE_MAX;
		}
		
		@Override
		public String getActionTitle() {
			return "Breed her";
		}

		@Override
		public String getActionDescription() {
			return "There's no way you're going to let Lyssieth fuck you. Wrestle her into the 'mating press' position and give Lilaya a new sister or two.<br/>"
								+ (!Main.game.getPlayer().isFeminine()
										?"[style.italicsMasculine(Lyssieth will turn you into her ideal incubus!)]"
										:"[style.italicsDisabled(This will have no extra feminising effect.)]");
		}

		@Override
		public String getDescription() {
			return UtilText.parseFromXMLFile(Util.newArrayListOfValues(ParserTag.SEX_ALLOW_MUFFLED_SPEECH), "characters/submission/lyssieth", "DEMON_TF_STAGE_2_MATING_PRESS");
		}

		@Override
		public void applyEffects() {
			specialActionTriggered = true;
			Main.game.getNpc(Lyssieth.class).setForeplayPreference(Main.game.getPlayer(), new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
			
			if(!Main.game.getPlayer().isFeminine()) {
				Main.game.getPlayer().setHairLength(HairLength.ONE_VERY_SHORT.getMedianValue());
				Main.game.getPlayer().setVaginaType(VaginaType.NONE);
				Main.game.getPlayer().setFemininity(0);
				Main.game.getPlayer().setMuscle(100);
				Main.game.getPlayer().setBodySize(BodySize.THREE_LARGE.getMedianValue());
				Main.game.getPlayer().setPenisSize(35);
				Main.game.getPlayer().setTesticleSize(TesticleSize.FOUR_HUGE);
				Main.game.getPlayer().setPenisCumStorage(2000);
			}

			Main.game.getNpc(Lyssieth.class).setPenisType(PenisType.NONE);
			Main.game.getPlayer().fillCumToMaxStorage();
			
			Sex.setSexManager(
					new SMLyssiethDemonTF(
							SexPosition.LYING_DOWN,
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotLyingDown.MATING_PRESS)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Lyssieth.class), SexSlotLyingDown.LYING_DOWN))));
			
			Sex.applyOngoingAction(Main.game.getPlayer(), SexAreaPenetration.PENIS, Main.game.getNpc(Lyssieth.class), SexAreaOrifice.VAGINA, true);
		}
	};
}
