package com.lilithsthrone.game.character.attributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;

/**
 * @since 0.4
 * @version 0.4
 * @author Innoxia
 */
public abstract class AbstractAttribute {

	private boolean percentage;
	private int baseValue;
	private	int lowerLimit;
	private	int upperLimit;
	private String name;
	private String nameAbbreviation;
	private String positiveEnchantment;
	private String negativeEnchantment;
	private Colour colour;
	private List<String> extraEffects;
	private String SVGString;
	
	public AbstractAttribute(boolean percentage,
			int baseValue,
			int lowerLimit,
			int upperLimit,
			String name,
			String nameAbbreviation,
			String pathName,
			Colour colour,
			String positiveEnchantment,
			String negativeEnchantment,
			List<String> extraEffects) {
		this.percentage = percentage;
		this.baseValue = baseValue;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.name = name;
		this.nameAbbreviation = nameAbbreviation;
		this.colour = colour;
		this.positiveEnchantment = positiveEnchantment;
		this.negativeEnchantment = negativeEnchantment;
		this.extraEffects = extraEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Attribute icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGString = Util.inputStreamToString(is);

			SVGString = SvgUtil.colourReplacement("att_"+name.replaceAll("\\s", ""), colour, SVGString);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		System.err.println("Warning: AbstractAttribute's toString() method is being called!");
//		throw new IllegalAccessError();
		return Attribute.getIdFromAttribute(this);
	}
	
	public boolean hasStatusEffect() {
		return false;
	}
	
	public boolean isPercentage() {
		return percentage;
	}
	
	public int getBaseValue() {
		return baseValue;
	}

	public int getLowerLimit() {
		return lowerLimit;
	}
	
	public int getUpperLimit() {
		return upperLimit;
	}
	
	/**
	 * @return true if this Attribute should be treates as being 'infinite' when the upperLimit is reached. (Only used for shielding.)
	 */
	public boolean isInfiniteAtUpperLimit() {
		return false;
	}
	
	public String getInfiniteDescription() {
		return "";
	}
	
	public String getName() {
		return name;
	}
	
	public String getColouredName(String tag) {
		return "<"+tag+" style='color:"+this.getColour().toWebHexString()+";'>"+name+"</"+tag+">";
	}

	public String getFormattedValue(float value) {
		return getFormattedValue(value, null);
	}
	
	public String getFormattedValue(float value, String htmlTag) {
		String valueForDisplay;
		if(((int)value)==value) {
			valueForDisplay = String.valueOf(((int)value));
		} else {
			valueForDisplay = String.valueOf(value);
		}
		String returnValue = "";
		if(this.isInfiniteAtUpperLimit() && value>=this.getUpperLimit()) {
			if(!this.getInfiniteDescription().isEmpty()) {
				returnValue = this.getInfiniteDescription();
			} else {
				returnValue = "[style.colourExcellent(Infinite)] <span style='color: "+ this.getColour().toWebHexString()+ ";'>"+ Util.capitaliseSentence(this.getAbbreviatedName())+ "</span>";
			}
			
		} else {
			String minorColour = "";
			if(this.isPercentage()){
				minorColour = "Minor";
				valueForDisplay = valueForDisplay+"%";
			}
			returnValue = (value>0?"[style.colour"+minorColour+"Good(+":"[style.colour"+minorColour+"Bad(")+valueForDisplay+")]"
					+ " <span style='color:"+this.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(this.getAbbreviatedName())+"</span>";
		}
		
		if(htmlTag!=null) {
			return "<"+htmlTag+">"+returnValue+"</"+htmlTag+">";
		} else {
			return returnValue;
		}
	}

	public String getAbbreviatedName() {
		return nameAbbreviation;
	}

	public abstract String getDescription(GameCharacter owner);

	public Colour getColour() {
		return colour;
	}

	public String getEffectsAsStringList() {
		StringBuilder descriptionSB = new StringBuilder();

		if (extraEffects != null)
			for (String s : extraEffects)
				descriptionSB.append("<br/>" + s);

		return descriptionSB.toString();
	}

	public String getPositiveEnchantment() {
		return positiveEnchantment;
	}

	public String getNegativeEnchantment() {
		return negativeEnchantment;
	}

	public String getSVGString() {
		return SVGString;
	}
}
