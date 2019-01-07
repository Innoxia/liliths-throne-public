package com.lilithsthrone.game.character.npc.util;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.*;
import com.lilithsthrone.game.character.body.types.*;
import com.lilithsthrone.game.character.body.valueEnums.*;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.FurryPreference;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.inventory.enchanting.EnchantingUtils;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.game.inventory.enchanting.TFPotency;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class TFPotionGenerator {
    private enum GenitalAvailChange {
        REMOVE,
        ADD,
        NONE
    }

    private static List<Capacity> MIN_VAGINA_CAPACITY = Util.newArrayListOfValues(
            Capacity.ZERO_IMPENETRABLE,
            Capacity.ONE_EXTREMELY_TIGHT
    );
    private static List<PenisSize> MIN_PENIS_SIZE = Util.newArrayListOfValues(
            PenisSize.ZERO_MICROSCOPIC,
            PenisSize.ONE_TINY
    );
    private static Map<BreastShape, TFModifier> BREAST_SHAPE_MOD_MAP = Util.newHashMapOfValues(
            new Util.Value<>(BreastShape.ROUND, TFModifier.TF_MOD_BREAST_SHAPE_ROUND),
            new Util.Value<>(BreastShape.POINTY, TFModifier.TF_MOD_BREAST_SHAPE_POINTY),
            new Util.Value<>(BreastShape.PERKY, TFModifier.TF_MOD_BREAST_SHAPE_PERKY),
            new Util.Value<>(BreastShape.SIDE_SET, TFModifier.TF_MOD_BREAST_SHAPE_SIDESET),
            new Util.Value<>(BreastShape.WIDE, TFModifier.TF_MOD_BREAST_SHAPE_WIDE),
            new Util.Value<>(BreastShape.NARROW, TFModifier.TF_MOD_BREAST_SHAPE_NARROW)
    );
    private static Map<NippleShape, TFModifier> NIPPLE_SHAPE_MOD_MAP = Util.newHashMapOfValues(
            new Util.Value<>(NippleShape.NORMAL, TFModifier.TF_MOD_NIPPLE_NORMAL),
            new Util.Value<>(NippleShape.LIPS, TFModifier.TF_MOD_NIPPLE_LIPS),
            new Util.Value<>(NippleShape.VAGINA, TFModifier.TF_MOD_NIPPLE_VAGINA)
    );
    private static Map<AreolaeShape, TFModifier> AREOLAE_SHAPE_MOD_MAP = Util.newHashMapOfValues(
            new Util.Value<>(AreolaeShape.NORMAL, TFModifier.TF_MOD_AREOLAE_CIRCLE),
            new Util.Value<>(AreolaeShape.HEART, TFModifier.TF_MOD_AREOLAE_HEART),
            new Util.Value<>(AreolaeShape.STAR, TFModifier.TF_MOD_AREOLAE_STAR)
    );
    private static Map<EyeShape, TFModifier> IRIS_SHAPE_MOD_MAP = Util.newHashMapOfValues(
            new Util.Value<>(EyeShape.ROUND, TFModifier.TF_MOD_EYE_IRIS_CIRCLE),
            new Util.Value<>(EyeShape.VERTICAL, TFModifier.TF_MOD_EYE_IRIS_VERTICAL),
            new Util.Value<>(EyeShape.HORIZONTAL, TFModifier.TF_MOD_EYE_IRIS_HORIZONTAL),
            new Util.Value<>(EyeShape.HEART, TFModifier.TF_MOD_EYE_IRIS_HEART),
            new Util.Value<>(EyeShape.STAR, TFModifier.TF_MOD_EYE_IRIS_STAR)
    );
    private static Map<EyeShape, TFModifier> PUPIL_SHAPE_MOD_MAP = Util.newHashMapOfValues(
            new Util.Value<>(EyeShape.ROUND, TFModifier.TF_MOD_EYE_PUPIL_CIRCLE),
            new Util.Value<>(EyeShape.VERTICAL, TFModifier.TF_MOD_EYE_PUPIL_VERTICAL),
            new Util.Value<>(EyeShape.HORIZONTAL, TFModifier.TF_MOD_EYE_PUPIL_HORIZONTAL),
            new Util.Value<>(EyeShape.HEART, TFModifier.TF_MOD_EYE_PUPIL_HEART),
            new Util.Value<>(EyeShape.STAR, TFModifier.TF_MOD_EYE_PUPIL_STAR)
    );
    private static List<Fetish> FETISH_FILTER = Util.newArrayListOfValues(
            Fetish.FETISH_VAGINAL_GIVING,
            Fetish.FETISH_PENIS_RECEIVING,
            Fetish.FETISH_CUM_ADDICT,
            Fetish.FETISH_BREASTS_OTHERS,
            Fetish.FETISH_LACTATION_OTHERS,
            Fetish.FETISH_ORAL_RECEIVING,
            Fetish.FETISH_ANAL_GIVING
    );

    private NPC applier;
    private GameCharacter subject;
    private FurryPreference furryPreference = Main.getProperties().getForcedTFPreference();
    private List<ItemEffect> effects = new ArrayList<>();
    private List<PossibleEffect> possibleEffects = new ArrayList<>();
    private AbstractItemType itemType;
    private String reaction;
    private GenitalAvailChange penisAvail = GenitalAvailChange.NONE;
    private GenitalAvailChange vaginaAvail = GenitalAvailChange.NONE;


    public TFPotionGenerator(NPC applier, GameCharacter subject) {
        this.applier = applier;
        this.subject = subject;

        updateBySpeciesAndGender();
        addRequiredGenitalEffects();
        addRequiredFemininityEffects();
        addPossibleTypeTFEffects();
        addPossibleSecondaryEffects();
    }

    private int getPotionEffectCount() {
        // five are granted
        int count = 4;
        // subject likes transforming
        if(subject.hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) count += 5;
        // applier is nasty
        count += applier.getFetishes().size();
        // applier is really nasty
        if(applier.hasFetish(Fetish.FETISH_TRANSFORMATION_GIVING)) count += 4;
        if(applier.hasFetish(Fetish.FETISH_KINK_GIVING)) count += 4;
        // nasty applier may also be lucky
        double baseChance = 0.1d * applier.getFetishes().size();
        while(Util.random.nextDouble() < baseChance) {
            count++;
            if(baseChance < 1) baseChance = Math.pow(baseChance, 2);
            else baseChance /= 2;
        }
        return count;
    }


    public AbstractItemType getItemType() {
        return itemType;
    }

    public List<ItemEffect> getPotionEffects() {
        // granted effects
        List<ItemEffect> potionEffects = new ArrayList<>(effects);
        // split effects preferred by fetishes and remaining
        Set<Fetish> appliedFetishes = FETISH_FILTER.stream()
                .filter(f -> applier.getFetishDesire(f) != FetishDesire.TWO_NEUTRAL)
                .collect(Collectors.toSet());
        List<PossibleEffect> fetishEffects = possibleEffects.stream()
                .filter(e -> e.getAssociatedFetishes() != null && !Collections.disjoint(appliedFetishes, e.getAssociatedFetishes()))
                .collect(Collectors.toList());
        List<PossibleEffect> remainingEffects = new ArrayList<>(possibleEffects);
        remainingEffects.removeAll(fetishEffects);

        int additionalEffectCount = getPotionEffectCount() - effects.size();
        while(additionalEffectCount > 0 && (remainingEffects.size() > 0 || fetishEffects.size() > 0)) {
            List<PossibleEffect> collection = remainingEffects.size() > 0 ? remainingEffects : fetishEffects;
            boolean amplify = false;
            // 75% chance for a fetish preferred effect
            if(fetishEffects.size() > 0 && Util.random.nextFloat() > 0.75f) {
                amplify = true;
                collection = fetishEffects;
            }
            // draw random effect from collection
            int pos = Util.random.nextInt(collection.size());
            potionEffects.add(collection.get(pos).getEffect(amplify));
            collection.remove(pos);
            additionalEffectCount--;
        }
        return potionEffects;
    }

    public AbstractItem getPotion() {
        List<ItemEffect> potionEffects = getPotionEffects();
        if(potionEffects.size() == 0) return null;
        return EnchantingUtils.craftItem(AbstractItemType.generateItem(itemType), potionEffects);
    }

    public String getReaction() {
        return reaction;
    }

    private Body getPreferredBody() {
        return applier.getPreferredBody();
    }

    private Body getCurrentBody() {
        return subject.getBody();
    }

    private Gender getPreferredGender() {
        return getPreferredBody().getGender();
    }

    private void updateBySpeciesAndGender() {
        String raceName;
        if(furryPreference != FurryPreference.HUMAN) {
            Subspecies subSpecies = getPreferredBody().getSubspecies();
            raceName = getPreferredGender().getName() + " ";
            raceName += getPreferredGender().isFeminine()
                    ? subSpecies.getSingularFemaleName(null)
                    : subSpecies.getSingularMaleName(null);

            switch(subSpecies) {
                case CAT_MORPH:
                case CAT_MORPH_CARACAL:
                case CAT_MORPH_CHEETAH:
                case CAT_MORPH_LEOPARD:
                case CAT_MORPH_LEOPARD_SNOW:
                case CAT_MORPH_LION:
                case CAT_MORPH_LYNX:
                case CAT_MORPH_TIGER:
                    itemType = ItemType.RACE_INGREDIENT_CAT_MORPH;
                    reaction = "Time to turn you into a cute little "+raceName+"!";
                    break;
                case DOG_MORPH:
                case DOG_MORPH_BORDER_COLLIE:
                case DOG_MORPH_DOBERMANN:
                    itemType = ItemType.RACE_INGREDIENT_DOG_MORPH;
                    reaction = "Time to turn you into an excitable little "+raceName+"!";
                    break;
                case FOX_MORPH:
                case FOX_ASCENDANT:
                case FOX_ASCENDANT_FENNEC:
                case FOX_MORPH_FENNEC:
                    itemType = ItemType.RACE_INGREDIENT_FOX_MORPH;
                    reaction = "Time to turn you into a cute little "+raceName+"!";
                    break;
                case HARPY:
                case HARPY_BALD_EAGLE:
                case HARPY_RAVEN:
                    itemType = ItemType.RACE_INGREDIENT_HARPY;
                    reaction = "Time to turn you into a hot little "+raceName+"!";
                    break;
                case HORSE_MORPH:
                case HORSE_MORPH_ZEBRA:
                    itemType = ItemType.RACE_INGREDIENT_HORSE_MORPH;
                    reaction = getPreferredGender().isFeminine()
                            ? "Time to turn you into my little mare!"
                            : "Time to turn you into my very own stallion!";
                    break;
                case REINDEER_MORPH:
                    itemType = ItemType.RACE_INGREDIENT_REINDEER_MORPH;
                    reaction = getPreferredGender().isFeminine()
                            ? "Time to turn you into my little doe!"
                            : "Time to turn you into my very own buck!";
                    break;
                case SQUIRREL_MORPH:
                    itemType = ItemType.RACE_INGREDIENT_SQUIRREL_MORPH;
                    reaction = "Time to turn you into a cute little "+raceName+"!";
                    break;
                case WOLF_MORPH:
                    itemType = ItemType.RACE_INGREDIENT_WOLF_MORPH;
                    reaction = "Time to turn you into a "+raceName+"!";
                    break;
                case ALLIGATOR_MORPH:
                    itemType = ItemType.RACE_INGREDIENT_ALLIGATOR_MORPH;
                    reaction = "Time to turn you into a "+raceName+"!";
                    break;
                case COW_MORPH:
                    itemType = ItemType.RACE_INGREDIENT_COW_MORPH;
                    break;
                case RAT_MORPH:
                    itemType = ItemType.RACE_INGREDIENT_RAT_MORPH;
                    break;
                case BAT_MORPH:
                    itemType = ItemType.RACE_INGREDIENT_BAT_MORPH;
                    break;
                case RABBIT_MORPH:
                case RABBIT_MORPH_LOP:
                    itemType = ItemType.RACE_INGREDIENT_RABBIT_MORPH;
                    break;
                case ANGEL:
                case HALF_DEMON:
                case DEMON:
                case LILIN:
                case ELDER_LILIN:
                case IMP:
                case IMP_ALPHA:
                case HUMAN:
                case SLIME:
                case ELEMENTAL_AIR:
                case ELEMENTAL_ARCANE:
                case ELEMENTAL_EARTH:
                case ELEMENTAL_FIRE:
                case ELEMENTAL_WATER:
                    itemType = ItemType.RACE_INGREDIENT_HUMAN;
                    break;
            }
        } else {
            reaction = "Time to transform you!";
            itemType = ItemType.RACE_INGREDIENT_HUMAN;
        }
    }

    private AbstractItemType getGenitalItemType() {
        return furryPreference == FurryPreference.HUMAN  || furryPreference == FurryPreference.MINIMUM
                ? ItemType.RACE_INGREDIENT_HUMAN
                : itemType;
    }

    private void addRequiredGenitalEffects() {
        // Vagina
        if(!subject.isHasAnyPregnancyEffects()) { // vagina cannot be transformed if pregnant, so skip this
            Vagina preferredVagina = getPreferredBody().getVagina();
            Vagina currentVagina = getCurrentBody().getVagina();
            if(preferredVagina.getType() != currentVagina.getType()) {
                if(preferredVagina.getType() == VaginaType.NONE) {
                    // desires no vagina
                    if(MIN_VAGINA_CAPACITY.contains(currentVagina.getOrificeVagina().getCapacity())) {
                        // remove vagina
                        effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1));
                        reaction += " Let's get rid of that tight little cunt of yours!";
                        vaginaAvail = GenitalAvailChange.REMOVE;
                    } else {
                        // reduce vagina capacity
                        effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_CAPACITY, TFPotency.DRAIN, 1));
                        reaction += " Let's get to work on getting rid of that little cunt of yours!";
                    }
                } else {
                    // desires vagina
                    if(currentVagina.getType() == VaginaType.NONE) {
                        // add vagina
                        TFPotency initialSizeBoost = preferredVagina.getOrificeVagina().getCapacity().getMedianValue() > 5
                                ? TFPotency.BOOST
                                : TFPotency.MINOR_BOOST;
                        effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1));
                        effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.TF_MOD_CAPACITY, initialSizeBoost, 1));
                        reaction += " Let's give you a nice " + preferredVagina.getName(subject, false) + "!";
                    } else {
                        // add vagina type-TF
                        possibleEffects.add(new PossibleEffect(
                                new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_VAGINA, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
                                " Let's give you a nice " + preferredVagina.getName(subject, false) + "!"
                        ));
                        vaginaAvail = GenitalAvailChange.ADD;
                    }
                }
            }
        }
        // Penis
        Penis preferredPenis = getPreferredBody().getPenis();
        Penis currentPenis = getCurrentBody().getPenis();
        if(preferredPenis.getType() != currentPenis.getType()) {
            if(preferredPenis.getType() == PenisType.NONE) {
                // desires no penis
                if(MIN_PENIS_SIZE.contains(currentPenis.getSize())) {
                    // remove penis
                    effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.REMOVAL, TFPotency.MINOR_BOOST, 1));
                    reaction += " Let's get rid of that pathetic little cock of yours!";
                    penisAvail = GenitalAvailChange.REMOVE;
                } else {
                    // reduce penis size
                    effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, TFPotency.DRAIN, 1));
                    reaction += " Let's get to work on getting rid of that cock of yours!";
                }
            } else {
                // desires penis
                if(currentPenis.getType() == PenisType.NONE) {
                    TFPotency initialSizeBoost = preferredPenis.getSize() == PenisSize.ONE_TINY
                            ? TFPotency.MINOR_BOOST
                            : preferredPenis.getSize() == PenisSize.TWO_AVERAGE
                                ? TFPotency.BOOST
                                : TFPotency.MAJOR_BOOST;
                    effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1));
                    effects.add(new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, initialSizeBoost, 1));
                    reaction += " Let's give you a nice " + preferredPenis.getName(subject, false) + "!";
                    penisAvail = GenitalAvailChange.ADD;
                } else {
                    // add penis type-TF
                    possibleEffects.add(new PossibleEffect(
                            new ItemEffect(getGenitalItemType().getEnchantmentEffect(), TFModifier.TF_PENIS, TFModifier.NONE, TFPotency.MINOR_BOOST, 1),
                            " Let's give you a nice " + preferredPenis.getName(subject, false) + "!"
                    ));
                }
            }
        }
    }

    private void addRequiredFemininityEffects() {
        if(applier.getSexualOrientation() != SexualOrientation.AMBIPHILIC) {
            boolean wantsFeminineBody = applier.getSexualOrientation() == SexualOrientation.GYNEPHILIC;
            if (!subject.isFeminine() && wantsFeminineBody) {
                // definitely raise femininity greatly
                effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_BOOST, 1));
                if(getPreferredBody().getFemininity() > Femininity.FEMININE.getMaximumFemininity())
                    effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.BOOST, 1));
            } else if(subject.isFeminine() && !wantsFeminineBody) {
                // definitely lower femininity greatly
                effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.MAJOR_DRAIN, 1));
                if(getPreferredBody().getFemininity() < Femininity.MASCULINE.getMinimumFemininity())
                    effects.add(new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, TFPotency.DRAIN, 1));
            } else {
                // possibly alter femininity moderately
                addPossibleFemininityEffects();
            }
        } else {
            // applier does not care so much, possibly alter femininity
            addPossibleFemininityEffects();
        }
    }

    private void addPossibleFemininityEffects() {
        int diff = getPreferredBody().getFemininity() - getCurrentBody().getFemininity();
        if(diff != 0) {
            ItemEffect effect;
            if (diff > 0) {
                // possible raise
                TFPotency potency = diff > 15 ? TFPotency.MAJOR_BOOST : diff > 10 ? TFPotency.BOOST : TFPotency.MINOR_BOOST;
                effect = new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, potency, 1);
            } else {
                // possible lowering
                int absDiff = Math.abs(diff);
                TFPotency potency = absDiff > 15 ? TFPotency.MAJOR_DRAIN : absDiff > 10 ? TFPotency.DRAIN : TFPotency.MINOR_DRAIN;
                effect = new ItemEffect(itemType.getEnchantmentEffect(), TFModifier.TF_CORE, TFModifier.TF_MOD_FEMININITY, potency, 1);
            }
            possibleEffects.add(new PossibleEffect(effect));
        }
    }

    private boolean isBodyPartTypeMismatch(Type klass) {
        for(BodyPartInterface prefPart : getPreferredBody().getAllBodyParts()) {
            if(prefPart.getClass() != klass) continue;
            for(BodyPartInterface curPart : getCurrentBody().getAllBodyParts()) {
                if(curPart.getClass() != klass) continue;
                return curPart.getType() != prefPart.getType();
            }
        }
        // this should not happen
        return true;
    }

    private void addPossibleTypeTF(TFModifier mod) {
        addPossibleTypeTF(mod, null);
    }

    private void addPossibleTypeTF(TFModifier mod, List<Fetish> associatedFetishes) {
        ItemEffect effect = new ItemEffect(
                itemType.getEnchantmentEffect(),
                mod,
                TFModifier.NONE,
                TFPotency.MINOR_BOOST,
                1
        );
        // maximum furry preference demands immediate type transformation
        if(furryPreference == FurryPreference.MAXIMUM) effects.add(effect);
        else possibleEffects.add(new PossibleEffect(effect, null, associatedFetishes));
    }

    private void addPossibleTF(TFModifier main, TFModifier secondary, TFPotency potency) {
        addPossibleTF(main, secondary, potency, null);
    }

    private void addPossibleTF(TFModifier main, TFModifier secondary, TFPotency potency, List<Fetish> associatedFetishes) {
        possibleEffects.add(possibleTF(main, secondary, potency, associatedFetishes));
    }

    private PossibleEffect possibleTF(TFModifier main, TFModifier secondary, TFPotency potency, List<Fetish> associatedFetishes) {
        ItemEffect effect = new ItemEffect(
                itemType.getEnchantmentEffect(),
                main,
                secondary,
                potency,
                1
        );
        return new PossibleEffect(effect, null, associatedFetishes);
    }

    private void addPossibleTypeTFEffects() {
        switch(furryPreference) {
            case MAXIMUM:
            case NORMAL:
                if(isBodyPartTypeMismatch(Skin.class)) addPossibleTypeTF(TFModifier.TF_SKIN);
                if(isBodyPartTypeMismatch(Face.class)) addPossibleTypeTF(TFModifier.TF_FACE, Util.newArrayListOfValues(
                        Fetish.FETISH_ORAL_RECEIVING
                ));
            case REDUCED:
                if(isBodyPartTypeMismatch(Ass.class)) addPossibleTypeTF(TFModifier.TF_ASS, Util.newArrayListOfValues(
                        Fetish.FETISH_ANAL_GIVING
                ));
                if(isBodyPartTypeMismatch(Breast.class)) addPossibleTypeTF(TFModifier.TF_BREASTS, Util.newArrayListOfValues(
                        Fetish.FETISH_BREASTS_OTHERS,
                        Fetish.FETISH_LACTATION_OTHERS
                ));
                if(isBodyPartTypeMismatch(Arm.class)) addPossibleTypeTF(TFModifier.TF_ARMS, Util.newArrayListOfValues(
                        Fetish.FETISH_MASTURBATION
                ));
                if(isBodyPartTypeMismatch(Leg.class)) addPossibleTypeTF(TFModifier.TF_LEGS, Util.newArrayListOfValues(
                        Fetish.FETISH_LEG_LOVER,
                        Fetish.FETISH_FOOT_RECEIVING
                ));
            case MINIMUM:
                if(isBodyPartTypeMismatch(Antenna.class)) addPossibleTypeTF(TFModifier.TF_ANTENNA);
                if(isBodyPartTypeMismatch(Ear.class)) addPossibleTypeTF(TFModifier.TF_EARS);
                if(isBodyPartTypeMismatch(Eye.class)) addPossibleTypeTF(TFModifier.TF_EYES);
                if(isBodyPartTypeMismatch(Hair.class)) addPossibleTypeTF(TFModifier.TF_HAIR);
                if(isBodyPartTypeMismatch(Horn.class)) addPossibleTypeTF(TFModifier.TF_HORNS);
                if(isBodyPartTypeMismatch(Tail.class)) addPossibleTypeTF(TFModifier.TF_TAIL);
                if(isBodyPartTypeMismatch(Wing.class)) addPossibleTypeTF(TFModifier.TF_WINGS);
        }
    }

    private int orificeWetnessCompare(OrificeInterface preferred, OrificeInterface current) {
        return Integer.compare(preferred.getWetness(subject).getValue(), current.getWetness(subject).getValue());
    }

    private static TFPotency minorBoostOrDrain(int direction) {
        return direction < 0 ? TFPotency.MINOR_DRAIN : TFPotency.MINOR_BOOST;
    }

    private static TFPotency boostOrDrain(int direction) {
        return direction < 0 ? TFPotency.DRAIN : TFPotency.BOOST;
    }

    private void addMajorAmplifiable(PossibleEffect effect) {
        switch(effect.getEffect().getPotency()) {
            case MINOR_BOOST:
            case BOOST:
            case MAJOR_BOOST:
                effect.setAmplifiableTo(TFPotency.MAJOR_BOOST);
                break;
            case MINOR_DRAIN:
            case DRAIN:
            case MAJOR_DRAIN:
                effect.setAmplifiableTo(TFPotency.MAJOR_DRAIN);
        }
        possibleEffects.add(effect);
    }

    private void addAmplifiable(PossibleEffect effect) {
        switch(effect.getEffect().getPotency()) {
            case MINOR_BOOST:
            case BOOST:
                effect.setAmplifiableTo(TFPotency.BOOST);
                break;
            case MINOR_DRAIN:
            case DRAIN:
                effect.setAmplifiableTo(TFPotency.DRAIN);
        }
        possibleEffects.add(effect);
    }

    private void addPossibleSecondaryEffects() {
        boolean urethralEnabled = Main.getProperties().hasValue(PropertyValue.urethralContent);
        boolean cumEnabled = Main.getProperties().hasValue(PropertyValue.cumRegenerationContent);
        boolean lactationEnabled = Main.getProperties().hasValue(PropertyValue.lactationContent);
        boolean nipplePenEnabled = Main.getProperties().hasValue(PropertyValue.nipplePenContent);
        boolean assHairEnabled = Main.getProperties().hasValue(PropertyValue.assHairContent);
        boolean analEnabled = Main.getProperties().hasValue(PropertyValue.analContent);

        // procedural shared
        OrificeInterface preferredOrifice, currentOrifice;
        List<Fetish> associatedFetishes;
        int m;

        // vagina effects
        // TODO: orifice modifiers, girlcum fluid modifiers
        Vagina
                preferredVagina = getPreferredBody().getVagina(),
                currentVagina = getCurrentBody().getVagina();
        if(preferredVagina.getType() != VaginaType.NONE) {
            associatedFetishes = Util.newArrayListOfValues(Fetish.FETISH_VAGINAL_GIVING);
            preferredOrifice = preferredVagina.getOrificeVagina();
            currentOrifice = currentVagina.getOrificeVagina();
            // vagina capacity
            if(vaginaAvail != GenitalAvailChange.ADD) { // vagina capacity will be adjusted after adding already
                m = Float.compare(preferredOrifice.getRawCapacityValue(), currentOrifice.getRawCapacityValue());
                if(m != 0)
                    addAmplifiable(possibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_CAPACITY, boostOrDrain(m), associatedFetishes));
            }
            // vagina wetness
            m = orificeWetnessCompare(preferredOrifice, currentOrifice);
            if(m != 0)
                addMajorAmplifiable(possibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_WETNESS, minorBoostOrDrain(m), associatedFetishes));
            // vagina elasticity
            m = Integer.compare(preferredOrifice.getElasticity().getValue(), currentOrifice.getElasticity().getValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_ELASTICITY, minorBoostOrDrain(m), associatedFetishes));
            // vagina plasticity
            m = Integer.compare(preferredOrifice.getPlasticity().getValue(), currentOrifice.getPlasticity().getValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_PLASTICITY, minorBoostOrDrain(m), associatedFetishes));
            // labia size
            m = Integer.compare(preferredVagina.getRawLabiaSizeValue(), currentVagina.getRawLabiaSizeValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_SIZE_TERTIARY, minorBoostOrDrain(m), associatedFetishes));
            Clitoris
                    preferredClitoris = preferredVagina.getClitoris(),
                    currentClitoris = currentVagina.getClitoris();
            // clitoris size
            m = Integer.compare(preferredClitoris.getRawClitorisSizeValue(), currentClitoris.getRawClitorisSizeValue());
            if(m != 0)
                addPossibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m));
            // clitoris girth
            m = Integer.compare(preferredClitoris.getRawGirthValue(), currentClitoris.getRawGirthValue());
            if(m != 0)
                addPossibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_SIZE_SECONDARY, minorBoostOrDrain(m));
            // vagina urethra capacity
            if(urethralEnabled) {
                preferredOrifice = preferredVagina.getOrificeUrethra();
                currentOrifice = currentVagina.getOrificeUrethra();
                m = Float.compare(preferredOrifice.getRawCapacityValue(), currentOrifice.getRawCapacityValue());
                if(m != 0)
                    addPossibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_CAPACITY_2, minorBoostOrDrain(m));
                // vagina urethra elasticity
                m = Integer.compare(preferredOrifice.getElasticity().getValue(), currentOrifice.getElasticity().getValue());
                if(m != 0)
                    addAmplifiable(possibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_ELASTICITY_2, minorBoostOrDrain(m), associatedFetishes));
                // vagina urethra plasticity
                m = Integer.compare(preferredOrifice.getPlasticity().getValue(), currentOrifice.getPlasticity().getValue());
                if(m != 0)
                    addAmplifiable(possibleTF(TFModifier.TF_VAGINA, TFModifier.TF_MOD_PLASTICITY_2, minorBoostOrDrain(m), associatedFetishes));
            }
        }
        
        // penis effects
        // TODO: penis/testicle/orifice modifiers
        Penis 
                preferredPenis = getPreferredBody().getPenis(), 
                currentPenis = getCurrentBody().getPenis();
        if(preferredPenis.getType() != PenisType.NONE) {
            associatedFetishes = Util.newArrayListOfValues(Fetish.FETISH_PENIS_RECEIVING);
            if(penisAvail != GenitalAvailChange.ADD) { // penis size will be adjusted after adding already
                // penis size
                m = Integer.compare(preferredPenis.getRawSizeValue(), currentPenis.getRawSizeValue());
                if(m != 0)
                    addAmplifiable(possibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m), associatedFetishes));
            }
            // penis girth
            m = Integer.compare(preferredPenis.getRawGirthValue(), preferredPenis.getRawGirthValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_SECONDARY, minorBoostOrDrain(m), associatedFetishes));
            // testicle size
            m = Integer.compare(preferredPenis.getTesticle().getTesticleSize().getValue(), currentPenis.getTesticle().getTesticleSize().getValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_SIZE_TERTIARY, minorBoostOrDrain(m), associatedFetishes));
            if(urethralEnabled) {
                preferredOrifice = preferredPenis.getOrificeUrethra();
                currentOrifice = currentPenis.getOrificeUrethra();
                // penis urethra capacity
                m = Float.compare(preferredOrifice.getRawCapacityValue(), currentOrifice.getRawCapacityValue());
                if(m != 0)
                    addPossibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_CAPACITY_2, minorBoostOrDrain(m));
                // penis urethra elasticity
                m = Integer.compare(preferredOrifice.getElasticity().getValue(), currentOrifice.getElasticity().getValue());
                if(m != 0)
                    addPossibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_ELASTICITY, minorBoostOrDrain(m), associatedFetishes);
                // penis urethra plasticity
                m = Integer.compare(preferredOrifice.getPlasticity().getValue(), currentOrifice.getPlasticity().getValue());
                if(m != 0)
                    addPossibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_PLASTICITY, minorBoostOrDrain(m), associatedFetishes);
            }

            // male cum effects
            // TODO: cum fluid modifiers
            if(cumEnabled) {
                Testicle
                        preferredTestis = getPreferredBody().getPenis().getTesticle(),
                        currentTestis = getCurrentBody().getPenis().getTesticle();
                associatedFetishes = Util.newArrayListOfValues(Fetish.FETISH_CUM_ADDICT);
                // cum storage
                m = Integer.compare(preferredTestis.getRawCumStorageValue(), currentTestis.getRawCumStorageValue());
                if(m != 0)
                    addMajorAmplifiable(possibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_WETNESS, minorBoostOrDrain(m), associatedFetishes));
                // cum expulsion
                m = Integer.compare(preferredTestis.getRawCumExpulsionValue(), currentTestis.getRawCumExpulsionValue());
                if(m > 0)
                    addMajorAmplifiable(possibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_CUM_EXPULSION, minorBoostOrDrain(m), associatedFetishes));
                // cum regeneration
                m = Integer.compare(preferredTestis.getRawCumProductionRegenerationValue(), currentTestis.getRawCumProductionRegenerationValue());
                if(m != 0)
                    addMajorAmplifiable(possibleTF(TFModifier.TF_PENIS, TFModifier.TF_MOD_REGENERATION, minorBoostOrDrain(m), associatedFetishes));
            }
        }

        // breast effects
        // TODO: breast fluid modifiers, orifice modifiers
        Breast
                preferredBreast = getPreferredBody().getBreast(),
                currentBreast = getCurrentBody().getBreast();
        associatedFetishes = Util.newArrayListOfValues(Fetish.FETISH_BREASTS_OTHERS);
        // cup size
        m = Integer.compare(preferredBreast.getRawSizeValue(), currentBreast.getRawSizeValue());
        if(m != 0) {
            if(!preferredBreast.getSize().isTrainingBraSize()
                    && (currentBreast.getSize() == CupSize.FLAT || currentBreast.getSize().isTrainingBraSize())) {
                addAmplifiable(possibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_BOOST, associatedFetishes));
            } else if(preferredBreast.getSize() == CupSize.FLAT) {
                addAmplifiable(possibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, TFPotency.MAJOR_DRAIN, associatedFetishes));
            } else {
                addAmplifiable(possibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m), associatedFetishes));
            }
        }
        // nipple size
        m = Integer.compare(preferredBreast.getNipples().getNippleSize().getValue(), currentBreast.getNipples().getNippleSize().getValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_SECONDARY, minorBoostOrDrain(m), associatedFetishes));
        // areolae size
        m = Integer.compare(preferredBreast.getNipples().getAreolaeSizeValue(), currentBreast.getNipples().getAreolaeSizeValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_SIZE_TERTIARY, minorBoostOrDrain(m), associatedFetishes));
        // areolae shape
        if(preferredBreast.getNipples().getAreolaeShape() != currentBreast.getNipples().getAreolaeShape())
            addPossibleTF(TFModifier.TF_BREASTS, AREOLAE_SHAPE_MOD_MAP.get(preferredBreast.getNipples().getAreolaeShape()), TFPotency.MINOR_BOOST, associatedFetishes);
        // breast row count
        if(Main.getProperties().multiBreasts != 0) {
            m = Integer.compare(preferredBreast.getRows(), currentBreast.getRows());
            if(m != 0)
                addPossibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT, minorBoostOrDrain(m), associatedFetishes);
            // nipple count
            m = Integer.compare(preferredBreast.getNippleCountPerBreast(), currentBreast.getNippleCountPerBreast());
            if(m != 0)
                addPossibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_COUNT_SECONDARY, minorBoostOrDrain(m), associatedFetishes);
        }
        // nipple shape
        if(preferredBreast.getNipples().getNippleShape() != currentBreast.getNipples().getNippleShape())
            addPossibleTF(TFModifier.TF_BREASTS, NIPPLE_SHAPE_MOD_MAP.get(preferredBreast.getNipples().getNippleShape()), TFPotency.MINOR_BOOST, associatedFetishes);
        // breast shape
        if(preferredBreast.getShape() != currentBreast.getShape())
            addPossibleTF(TFModifier.TF_BREASTS, BREAST_SHAPE_MOD_MAP.get(preferredBreast.getShape()), TFPotency.MINOR_BOOST, associatedFetishes);

        if(lactationEnabled) {
            associatedFetishes = Util.newArrayListOfValues(Fetish.FETISH_LACTATION_OTHERS);
            // lactation storage
            m = Integer.compare(preferredBreast.getRawMilkStorageValue(), currentBreast.getRawMilkStorageValue());
            if(m != 0)
                addMajorAmplifiable(possibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_WETNESS, minorBoostOrDrain(m), associatedFetishes));
            // lactation regeneration
            m = Integer.compare(preferredBreast.getRawLactationRegenerationValue(), currentBreast.getRawLactationRegenerationValue());
            if(m != 0)
                addMajorAmplifiable(possibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_REGENERATION, minorBoostOrDrain(m), associatedFetishes));
        }

        if(nipplePenEnabled) {
            preferredOrifice = preferredBreast.getNipples().getOrificeNipples();
            currentOrifice = currentBreast.getNipples().getOrificeNipples();
            // nipple capacity
            m = Float.compare(preferredOrifice.getRawCapacityValue(), currentOrifice.getRawCapacityValue());
            if(m != 0)
                addPossibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_CAPACITY, minorBoostOrDrain(m));
            // nipple elasticity
            m = Integer.compare(preferredOrifice.getElasticity().getValue(), currentOrifice.getElasticity().getValue());
            if(m != 0)
                addPossibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_ELASTICITY, minorBoostOrDrain(m));
            // nipple plasticity
            m = Integer.compare(preferredOrifice.getPlasticity().getValue(), currentOrifice.getPlasticity().getValue());
            if(m != 0)
                addPossibleTF(TFModifier.TF_BREASTS, TFModifier.TF_MOD_PLASTICITY, minorBoostOrDrain(m));
        }
        
        // mouth/tongue/throat effects
        // TODO: orifice modifiers, tongue modifiers
        Mouth 
                preferredMouth = getPreferredBody().getFace().getMouth(),
                currentMouth = getCurrentBody().getFace().getMouth();
        associatedFetishes = Util.newArrayListOfValues(Fetish.FETISH_ORAL_RECEIVING);
        preferredOrifice = preferredMouth.getOrificeMouth();
        currentOrifice = currentMouth.getOrificeMouth();
        // lip size
        m = Integer.compare(preferredMouth.getLipSizeValue(), currentMouth.getLipSizeValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m), associatedFetishes));
        // throat capacity
        m = Float.compare(preferredOrifice.getRawCapacityValue(), currentOrifice.getRawCapacityValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_FACE, TFModifier.TF_MOD_CAPACITY, minorBoostOrDrain(m), associatedFetishes));
        // throat wetness
        m = orificeWetnessCompare(preferredOrifice, currentOrifice);
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_FACE, TFModifier.TF_MOD_WETNESS, minorBoostOrDrain(m), associatedFetishes));
        // throat elasticity
        m = Integer.compare(preferredOrifice.getElasticity().getValue(), currentOrifice.getElasticity().getValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_FACE, TFModifier.TF_MOD_ELASTICITY, minorBoostOrDrain(m), associatedFetishes));
        // throat plasticity
        m = Integer.compare(preferredOrifice.getPlasticity().getValue(), currentOrifice.getPlasticity().getValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_FACE, TFModifier.TF_MOD_PLASTICITY, minorBoostOrDrain(m), associatedFetishes));
        // tongue length
        Tongue
                preferredTongue = getPreferredBody().getFace().getTongue(),
                currentTongue = getCurrentBody().getFace().getTongue();
        m = Integer.compare(preferredTongue.getTongueLengthValue(), currentTongue.getTongueLengthValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_FACE, TFModifier.TF_MOD_SIZE_SECONDARY, minorBoostOrDrain(m), associatedFetishes));

        // ass/anus
        // TODO: orifice modifiers
        Ass
                preferredAss = getPreferredBody().getAss(),
                currentAss = getCurrentBody().getAss();
        associatedFetishes = Util.newArrayListOfValues(Fetish.FETISH_ANAL_GIVING);
        // ass size
        m = Integer.compare(preferredAss.getAssSize().getValue(), currentAss.getAssSize().getValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m), associatedFetishes));
        // hip size
        m = Integer.compare(preferredAss.getHipSize().getValue(), currentAss.getHipSize().getValue());
        if(m != 0)
            addAmplifiable(possibleTF(TFModifier.TF_ASS, TFModifier.TF_MOD_SIZE_SECONDARY, minorBoostOrDrain(m), associatedFetishes));
        if(assHairEnabled) {
            // ass hairiness
            m = Integer.compare(preferredAss.getAnus().getAssHair().getValue(), currentAss.getAnus().getAssHair().getValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_ASS, TFModifier.TF_MOD_BODY_HAIR, minorBoostOrDrain(m), associatedFetishes));
        }
        if(analEnabled) {
            preferredOrifice = preferredAss.getAnus().getOrificeAnus();
            currentOrifice = currentAss.getAnus().getOrificeAnus();
            // anus capacity
            m = Float.compare(preferredOrifice.getRawCapacityValue(), currentOrifice.getRawCapacityValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_ASS, TFModifier.TF_MOD_CAPACITY, minorBoostOrDrain(m), associatedFetishes));
            // anus wetness
            m = orificeWetnessCompare(preferredOrifice, currentOrifice);
            if(m != 0)
                addMajorAmplifiable(possibleTF(TFModifier.TF_ASS, TFModifier.TF_MOD_WETNESS, minorBoostOrDrain(m), associatedFetishes));
            // anus elasticity
            m = Integer.compare(preferredOrifice.getElasticity().getValue(), currentOrifice.getElasticity().getValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_ASS, TFModifier.TF_MOD_ELASTICITY, minorBoostOrDrain(m), associatedFetishes));
            // anus plasticity
            m = Integer.compare(preferredOrifice.getPlasticity().getValue(), currentOrifice.getPlasticity().getValue());
            if(m != 0)
                addAmplifiable(possibleTF(TFModifier.TF_ASS, TFModifier.TF_MOD_PLASTICITY, minorBoostOrDrain(m), associatedFetishes));
        }


        // core attributes
        // height
        m = Integer.compare(getPreferredBody().getHeightValue(), getCurrentBody().getHeightValue());
        if(m != 0)
            addPossibleTF(TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE, boostOrDrain(m));
        // muscle
        m = Integer.compare(getPreferredBody().getMuscle(), getCurrentBody().getMuscle());
        if(m != 0)
            addPossibleTF(TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_SECONDARY, boostOrDrain(m));
        // size
        m = Integer.compare(getPreferredBody().getBodySize(), getCurrentBody().getBodySize());
        if(m != 0)
            addPossibleTF(TFModifier.TF_CORE, TFModifier.TF_MOD_SIZE_TERTIARY, boostOrDrain(m));
        // core hairiness
        m = Integer.compare(getPreferredBody().getPubicHair().getValue(), getCurrentBody().getPubicHair().getValue());
        if(m != 0)
            addPossibleTF(TFModifier.TF_CORE, TFModifier.TF_MOD_BODY_HAIR, minorBoostOrDrain(m));

        // arm rows
        m = Integer.compare(getPreferredBody().getArm().getArmRows(), getCurrentBody().getArm().getArmRows());
        if(m != 0)
            addPossibleTF(TFModifier.TF_ARMS, TFModifier.TF_MOD_COUNT, minorBoostOrDrain(m));

        // eyes
        Eye
                preferredEye = getPreferredBody().getEye(),
                currentEye = getCurrentBody().getEye();
        // eye rows
        m = Integer.compare(preferredEye.getEyePairs(), currentEye.getEyePairs());
        if(m != 0)
            addPossibleTF(TFModifier.TF_EYES, TFModifier.TF_MOD_COUNT, minorBoostOrDrain(m));
        // iris shape
        if(preferredEye.getIrisShape() != currentEye.getIrisShape())
            addPossibleTF(TFModifier.TF_EYES, IRIS_SHAPE_MOD_MAP.get(preferredEye.getIrisShape()), TFPotency.MINOR_BOOST);
        // pupil shape
        if(preferredEye.getPupilShape() != currentEye.getPupilShape())
            addPossibleTF(TFModifier.TF_EYES, PUPIL_SHAPE_MOD_MAP.get(preferredEye.getPupilShape()), TFPotency.MINOR_BOOST);

        // hair length
        m = Integer.compare(getPreferredBody().getHair().getRawLengthValue(), getCurrentBody().getHair().getRawLengthValue());
        if(m != 0)
            addPossibleTF(TFModifier.TF_HAIR, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m));

        // horns
        Horn
                preferredHorn = getPreferredBody().getHorn(),
                currentHorn = getCurrentBody().getHorn();
        // horn length
        m = Integer.compare(preferredHorn.getHornLengthValue(), currentHorn.getHornLengthValue());
        if(m != 0)
            addPossibleTF(TFModifier.TF_HORNS, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m));
        // horn rows
        m = Integer.compare(preferredHorn.getHornRows(), currentHorn.getHornRows());
        if(m != 0)
            addPossibleTF(TFModifier.TF_HORNS, TFModifier.TF_MOD_COUNT, minorBoostOrDrain(m));
        // horn count per row
        m = Integer.compare(preferredHorn.getHornsPerRow(), currentHorn.getHornsPerRow());
        if(m != 0)
            addPossibleTF(TFModifier.TF_HORNS, TFModifier.TF_MOD_COUNT_SECONDARY, minorBoostOrDrain(m));

        // tail count
        m = Integer.compare(getPreferredBody().getTail().getTailCount(), getCurrentBody().getTail().getTailCount());
        if(m != 0)
            addPossibleTF(TFModifier.TF_TAIL, TFModifier.TF_MOD_COUNT, minorBoostOrDrain(m));

        // wing size
        m = Integer.compare(getPreferredBody().getWing().getSizeValue(), getCurrentBody().getWing().getSizeValue());
        if(m != 0)
            addPossibleTF(TFModifier.TF_WINGS, TFModifier.TF_MOD_SIZE, minorBoostOrDrain(m));
    }

    private class PossibleEffect {
        private ItemEffect effect;
        private String reaction;
        private List<Fetish> associatedFetishes;
        private TFPotency amplifiableTo;

        PossibleEffect(ItemEffect effect) {
            this(effect, null, null);
        }

        PossibleEffect(ItemEffect effect, String reaction) {
            this(effect, reaction, null);
        }

        PossibleEffect(ItemEffect effect, List<Fetish> associatedFetishes) {
            this(effect, null, associatedFetishes);
        }

        PossibleEffect(ItemEffect effect, String reaction, List<Fetish> associatedFetishes) {
            this.effect = effect;
            this.reaction = reaction;
            this.associatedFetishes = associatedFetishes;
        }

        public ItemEffect getEffect() {
            return effect;
        }

        public ItemEffect getEffect(boolean amplify) {
            if(amplify && amplifiableTo != null) {
                return new ItemEffect(effect.getItemEffectType(), effect.getPrimaryModifier(), effect.getSecondaryModifier(), amplifiableTo, effect.getLimit());
            }
            return effect;
        }

        public void setEffect(ItemEffect effect) {
            this.effect = effect;
        }

        public String getReaction() {
            return reaction;
        }

        public void setReaction(String reaction) {
            this.reaction = reaction;
        }

        public List<Fetish> getAssociatedFetishes() {
            return associatedFetishes;
        }

        public void setAssociatedFetishes(List<Fetish> associatedFetishes) {
            this.associatedFetishes = associatedFetishes;
        }

        public TFPotency getAmplifiableTo() {
            return amplifiableTo;
        }

        public void setAmplifiableTo(TFPotency amplifiableTo) {
            this.amplifiableTo = amplifiableTo;
        }
    }
}
