package com.lilithsthrone.game.character.fetishes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

public abstract class AbstractFetish {
	
	private int renderingPriority;
	protected String id;
	protected String name;
	protected String shortDescriptor;
	private int experienceGainFromSexAction;
	private HashMap<AbstractAttribute, Integer> attributeModifiers;

	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;
	
	private List<AbstractFetish> fetishesForAutomaticUnlock;
    
    public AbstractFetish(
			int renderingPriority,
			String name,
			String shortDescriptor,
			String pathName,
			FetishExperience experienceGainFromSexAction,
			Colour colourShade,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects,
			List<AbstractFetish> fetishesForAutomaticUnlock) {
		this(renderingPriority,
				name,
				shortDescriptor,
				pathName,
				experienceGainFromSexAction,
				Util.newArrayListOfValues(colourShade),
				attributeModifiers,
				extraEffects,
				fetishesForAutomaticUnlock);
	}

    public AbstractFetish(
			int renderingPriority,
			String name,
			String shortDescriptor,
			String pathName,
			FetishExperience experienceGainFromSexAction,
			List<Colour> colourShade,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects,
			List<AbstractFetish> fetishesForAutomaticUnlock) {

		this.renderingPriority = renderingPriority;
		this.name = name;
		this.shortDescriptor = shortDescriptor;
		this.experienceGainFromSexAction = experienceGainFromSexAction.getExperience();

		this.attributeModifiers = attributeModifiers;

		this.extraEffects = extraEffects;

		if (fetishesForAutomaticUnlock == null) {
			this.fetishesForAutomaticUnlock = new ArrayList<>();
		} else {
			this.fetishesForAutomaticUnlock = fetishesForAutomaticUnlock;
		}

		if (pathName != null) {
			try {
				InputStream is = this.getClass()
						.getResourceAsStream("/com/lilithsthrone/res/fetishes/" + pathName + ".svg");
				if (is == null) {
					System.err.println(
							"Error! Fetish icon file does not exist (Trying to read from '" + pathName + "')!");
				}
				SVGString = Util.inputStreamToString(is);
				SVGString = SvgUtil.colourReplacement(this.toString(), colourShade.get(0),
						colourShade.size() >= 2 ? colourShade.get(1) : null,
						colourShade.size() >= 3 ? colourShade.get(2) : null, SVGString);
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			SVGString = "";
		}

		modifiersList = new ArrayList<>();

		if (attributeModifiers != null) {
			for (Entry<AbstractAttribute, Integer> e : attributeModifiers.entrySet()) {
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b> <b style='color: "
						+ e.getKey().getColour().toWebHexString() + ";'>"
						+ Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
			}
		}
	}

    public List<AbstractFetish> getFetishesForAutomaticUnlock() {
        return fetishesForAutomaticUnlock;
    }

    public boolean isAvailable(GameCharacter character) {
        return true;
    }

    protected static List<String> perkRequirementsList = new ArrayList<>();

    public List<String> getPerkRequirements(GameCharacter character) {
        perkRequirementsList.clear();

        return perkRequirementsList;
    }

    public String getName(GameCharacter owner) {
        return name;
    }

    public String getShortDescriptor(GameCharacter target) {
        return shortDescriptor;
    }

    public abstract String getDescription(GameCharacter target);

    public abstract String getFetishDesireDescription(GameCharacter target, FetishDesire desire);

    public static String getGenericFetishDesireDescription(GameCharacter target, FetishDesire desire,
            String descriptor) {
        switch (desire) {
            case ZERO_HATE:
                return UtilText.parse(target, "You absolutely hate " + descriptor + ".");
            case ONE_DISLIKE:
                return UtilText.parse(target, "You don't like " + descriptor + ".");
            case TWO_NEUTRAL:
                return UtilText.parse(target, "You are indifferent to " + descriptor + ".");
            case THREE_LIKE:
                return UtilText.parse(target, "You like " + descriptor + ".");
            case FOUR_LOVE:
                return UtilText.parse(target, "You love " + descriptor + ".");
        }
        return "";
    }

    public int getExperienceGainFromSexAction() {
        return experienceGainFromSexAction;
    }

    public int getCost() {
        return 5;
    }

    public List<String> getModifiersAsStringList(GameCharacter owner) {
        List<String> modList = new ArrayList<>(modifiersList);
        if (getExtraEffects(owner) != null) {
            modList.addAll(getExtraEffects(owner));
        }
        return modList;
    }

    public HashMap<AbstractAttribute, Integer> getAttributeModifiers() {
        return attributeModifiers;
    }

    public String applyPerkGained(GameCharacter character) {
        return "";
    }

    public String applyPerkLost(GameCharacter character) {
        return "";
    }

    public Fetish getPreviousLevelPerk() {
        return null;
    }

    public Perk getNextLevelPerk() {
        return null;
    }

    public CorruptionLevel getAssociatedCorruptionLevel() {
        return CorruptionLevel.ZERO_PURE;
    }

    public int getRenderingPriority() {
        return renderingPriority;
    }

    public List<String> getExtraEffects(GameCharacter owner) {
        return extraEffects;
    }

    public String getSVGString(GameCharacter owner) {
        return SVGString;
    }

    public static int getExperienceGainFromTakingVaginalVirginity(GameCharacter owner) {
        return owner.getLevel() * 2;
    }

    public static int getExperienceGainFromTakingOtherVirginity(GameCharacter owner) {
        return owner.getLevel();
    }

    public FetishPreference getFetishPreferenceDefault() {
        return FetishPreference.THREE_NEUTRAL;
    }
    
    /**
     * For fetishes that have content toggles.
     * @return
     */
    public boolean isDisabled() {
    	return false;
    }
    
    /**
     * A collection of AbstractFetishes that oppose this fetish.
     * @return
     */
    public Collection<? extends AbstractFetish> getOppositeFetishes() {
		return Util.newArrayListOfValues();
    }

	public boolean isAllowed(GameCharacter character) {
		return getFetishesForAutomaticUnlock().isEmpty();
	}

	/**
	 * Used in CharacterUtils.generateDesires(), before desireMaps are built.
	 * @param character
	 * @param availableFetishes
	 */
	public void onBeforeGeneratingDesires(GameCharacter character, List<AbstractFetish> availableFetishes) {}
	/**
	 * Used in CharacterUtils.generateDesires(), after desireMaps are built, in the likedFetishes loop.
	 * @param character
	 * @param availableFetishes
	 */
	public void onGeneratingDesiresForLikedFetishes(GameCharacter character, List<AbstractFetish> availableFetishes) {}
	
	public void registered(String id) {
		this.id=id;
	}
}
