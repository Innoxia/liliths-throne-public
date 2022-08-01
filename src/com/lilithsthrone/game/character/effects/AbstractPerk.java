package com.lilithsthrone.game.character.effects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.combat.spells.SpellUpgrade;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractPerk {
	
	private int renderingPriority;
	protected String name;
	private List<Colour> colours;
	private boolean equippableTrait;
	
	private Spell spell;
	private SpellUpgrade spellUpgrade;
	private SpellSchool school;

	// Attributes modified by this Virtue:
	private HashMap<AbstractAttribute, Integer> attributeModifiers;

	private PerkCategory perkCategory;

	protected String pathName;
	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;
	
	public AbstractPerk(int renderingPriority,
			boolean major,
			String name,
			PerkCategory perkCategory,
			String pathName,
			Colour colour,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		this(renderingPriority,
				major,
				name,
				perkCategory,
				pathName,
				colour,
				attributeModifiers,
				extraEffects,
				null,
				null,
				null);
	}
	

	public AbstractPerk(int renderingPriority,
			boolean major,
			String name,
			PerkCategory perkCategory,
			String pathName,
			Colour colour,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects,
			Spell spell,
			SpellUpgrade spellUpgrade,
			SpellSchool school) {
		this(renderingPriority,
				major,
				name,
				perkCategory,
				pathName,
				Util.newArrayListOfValues(colour),
				attributeModifiers,
				extraEffects,
				spell,
				spellUpgrade,
				school);
	}
	
	public AbstractPerk(int renderingPriority,
			boolean major,
			String name,
			PerkCategory perkCategory,
			String pathName,
			List<Colour> colours,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects,
			Spell spell,
			SpellUpgrade spellUpgrade,
			SpellSchool school) {

		this.renderingPriority = renderingPriority;
		this.name = name;
		this.colours = colours;
		
		this.equippableTrait = major;
		
		this.perkCategory = perkCategory;

		this.attributeModifiers = attributeModifiers;

		if(extraEffects!=null) {
			this.extraEffects = extraEffects;
		} else {
			this.extraEffects = new ArrayList<>();
		}
		
		this.pathName = pathName;
		if(pathName!=null) {
			generateSVGImage(pathName, colours);
		}
		
		modifiersList = new ArrayList<>();
		
		this.spell = spell;
		this.spellUpgrade = spellUpgrade;
		this.school = school;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof AbstractPerk) {
			if(((AbstractPerk)o).getName(null).equals(this.getName(null))
					&& ((AbstractPerk)o).getAttributeModifiers(null) == this.getAttributeModifiers(null)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getName(null).hashCode();
		if(this.getAttributeModifiers(null)!=null) {
			result = 31 * result + this.getAttributeModifiers(null).hashCode();
		}
		return result;
	}
	
	protected void generateSVGImage(String pathName, List<Colour> colours) {
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Perk icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGString = Util.inputStreamToString(is);
			
			SVGString = SvgUtil.colourReplacement(name.replaceAll(" ", ""), colours.get(0), colours.size()>=2?colours.get(1):null, colours.size()>=3?colours.get(2):null, SVGString);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlwaysAvailable() {
		return false;
	}

	/**
	 * Override this and return true if the perk is one that is unlock via special in-game events.
	 * @return true If this perk does not appear in the perk tree, but is otherwise obtainable through special means.
	 */
	public boolean isHiddenPerk() {
		return false;
	}
	
	/**
	 * @return true if this a perk that's granted as part of an NPC's background.
	 */
	public boolean isBackgroundPerk() {
		return false;
	}
	
	public String getName(GameCharacter owner) {
		return name;
	}

	public Colour getColour() {
		return colours.get(0);
	}

	public boolean isEquippableTrait() {
		return equippableTrait;
	}

	public abstract String getDescription(GameCharacter target);

	public List<String> getModifiersAsStringList(GameCharacter character) {
		modifiersList.clear();
		
		if (this.getAttributeModifiers(character) != null) {
			for (Entry<AbstractAttribute, Integer> e : this.getAttributeModifiers(character).entrySet()) {
				modifiersList.add(e.getKey().getFormattedValue(e.getValue()));
			}
		}
		
		return Util.mergeLists(modifiersList, getExtraEffects());
	}

	/**
	 * final because attributes are only accessed when perk is applied/removed to/from a character, so changing this while perk is applied will either not work or break attributes when the perk is removed.
	 */
	public final Map<AbstractAttribute, Integer> getAttributeModifiers(GameCharacter character) {
		return attributeModifiers;
	}
	
	public String applyPerkGained(GameCharacter character) {
		return "";
	};

	public String applyPerkLost(GameCharacter character) {
		return "";
	};

	public CorruptionLevel getAssociatedCorruptionLevel() {
		return CorruptionLevel.ZERO_PURE;
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString(GameCharacter owner) {
		return SVGString;
	}

	public PerkCategory getPerkCategory() {
		return perkCategory;
	}

	public Spell getSpell() {
		return spell;
	}

	public SpellUpgrade getSpellUpgrade() {
		return spellUpgrade;
	}

	public SpellSchool getSchool() {
		return school;
	}
}
