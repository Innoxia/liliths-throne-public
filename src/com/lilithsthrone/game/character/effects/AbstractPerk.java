package com.lilithsthrone.game.character.effects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public abstract class AbstractPerk {
	
	private int renderingPriority;
	protected String name;
	private Colour colour;
	private boolean equippableTrait;
	
	private Spell spell;
	private SpellUpgrade spellUpgrade;
	private SpellSchool school;

	// Attributes modified by this Virtue:
	private HashMap<Attribute, Integer> attributeModifiers;

	private PerkCategory perkCategory;

	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;
	
	public AbstractPerk(int renderingPriority,
			boolean major,
			String name,
			PerkCategory perkCategory,
			String pathName,
			Colour colour,
			HashMap<Attribute, Integer> attributeModifiers,
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
			HashMap<Attribute, Integer> attributeModifiers,
			List<String> extraEffects,
			Spell spell,
			SpellUpgrade spellUpgrade,
			SpellSchool school) {

		this.renderingPriority = renderingPriority;
		this.name = name;
		this.colour = colour;
		
		this.equippableTrait = major;
		
		this.perkCategory = perkCategory;

		this.attributeModifiers = attributeModifiers;

		if(extraEffects!=null) {
			this.extraEffects = extraEffects;
		} else {
			this.extraEffects = new ArrayList<>();
		}
		
		if(pathName!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! Perk icon file does not exist (Trying to read from '"+pathName+"')!");
				}
				SVGString = Util.inputStreamToString(is);
				
				SVGString = SvgUtil.colourReplacement(name.replaceAll(" ", ""), colour, SVGString);
	
				is.close();
	
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
	public boolean isAlwaysAvailable() {
		return false;
	}

	// Override this and return true if the perk is one that is unlock via special in-game events.
	public boolean isHiddenPerk() {
		return false;
	}
	
	public String getName(GameCharacter owner) {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isEquippableTrait() {
		return equippableTrait;
	}

	public abstract String getDescription(GameCharacter target);

	public List<String> getModifiersAsStringList(GameCharacter character) {
		modifiersList.clear();
		
		if (this.getAttributeModifiers(character) != null) {
			for (Entry<Attribute, Integer> e : this.getAttributeModifiers(character).entrySet()) {
				modifiersList.add("<b>"+ (e.getValue() > 0 ? "+" : "")+ e.getValue()+ "</b>"
						+ " <b style='color: "+ e.getKey().getColour().toWebHexString()+ ";'>"+ Util.capitaliseSentence(e.getKey().getAbbreviatedName())+ "</b>");
			}
		}
		
		return Util.mergeLists(modifiersList, getExtraEffects());
	}

	public HashMap<Attribute, Integer> getAttributeModifiers(GameCharacter character) {
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

	public String getSVGString() {
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
