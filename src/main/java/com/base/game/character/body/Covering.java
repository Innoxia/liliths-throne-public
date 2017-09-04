package com.base.game.character.body;

import java.io.Serializable;

import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.BodyCoveringType;
import com.base.game.character.body.valueEnums.CoveringPattern;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Colour;
import com.base.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class Covering implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private BodyCoveringType type;
	private CoveringPattern pattern;
	
	private Colour primaryColour, secondaryColour;
	private boolean primaryGlowing, secondaryGlowing;

	/**
	 * Constructor.</br>
	 * Initialises CoveringPattern pattern to a random value, and boolean glowing to false.
	 * @param type The BodyCoveringType to set this skin to.
	 */
	public Covering(BodyCoveringType type) {
		this(type, type.getNaturalPatterns().get(Util.random.nextInt(type.getNaturalPatterns().size())),
				type.getNaturalColoursPrimary().get(Util.random.nextInt(type.getNaturalColoursPrimary().size())), false,
				(type.getNaturalColoursSecondary().isEmpty()
						?type.getNaturalColoursPrimary().get(Util.random.nextInt(type.getNaturalColoursPrimary().size()))
						:type.getNaturalColoursSecondary().get(Util.random.nextInt(type.getNaturalColoursSecondary().size()))), false);
	}
	
	/**
	 * Constructor.</br>
	 * Initialises CoveringPattern pattern to the first value, boolean glowing to false, and secondaryColour to same as primaryColour (where available).
	 * @param type
	 * @param primaryColour
	 */
	public Covering(BodyCoveringType type, Colour primaryColour) {
		this(type, type.getNaturalPatterns().get(0),
				primaryColour, false,
				(type.getNaturalColoursSecondary().contains(primaryColour) || type.getNaturalColoursSecondary().isEmpty()
						?primaryColour
						:type.getNaturalColoursSecondary().get(Util.random.nextInt(type.getNaturalColoursSecondary().size()))), false);
	}
	
	/**
	 * Constructor.
	 * @param type The BodyCoveringType to set this skin to.
	 * @param pattern The CoveringPattern to set this skin to.
	 * @param glowing Whether this skin is glowing or not.
	 */
	public Covering(BodyCoveringType type, CoveringPattern pattern, Colour primaryColour, boolean primaryGlowing, Colour secondaryColour, boolean secondaryGlowing) {
		this.type = type;
		this.pattern = pattern;
		this.primaryColour = primaryColour;
		this.primaryGlowing = primaryGlowing;
		this.secondaryColour = secondaryColour;
		this.secondaryGlowing = secondaryGlowing;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		return type.getDescriptor(gc);
	}
	
	public String getColourDescriptor(boolean coloured) {
		if(coloured) {
			switch(pattern) {
				case HAIR_HIGHLIGHTS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-highlighted</span>";
				case MOTTLED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-mottled</span>";
				case SPOTTED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-spotted</span>";
				case STRIPED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-striped</span>";
				case ORIFICE_ANUS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"-rimmed</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-interiored</span>";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-interiored</span>";
				case ORIFICE_VAGINA:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"-lipped</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-interiored</span>";
				case ORIFICE_MOUTH:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"-lipped</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"-interiored</span>";
				case NONE: case FLUID:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>";
				case EYE_IRISES:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"</span>";
				case EYE_PUPILS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"</span>";
			}
			return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>";
		
		} else {
			switch(pattern) {
				case HAIR_HIGHLIGHTS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+"-highlighted";
				case MOTTLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+"-mottled";
				case SPOTTED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+"-spotted";
				case STRIPED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+"-striped";
				case ORIFICE_ANUS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+"-rimmed";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
				case ORIFICE_VAGINA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+"-lipped";
				case ORIFICE_MOUTH:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" lips";
				case NONE: case FLUID:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
				case EYE_IRISES:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName();
				case EYE_PUPILS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName();
			}
			return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
		}
	}
	
	public String getPrimaryColourDescriptor(boolean coloured) {
		if(coloured) {
			return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>";
		
		} else {
			return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
		}
	}
	
	public String getSecondaryColourDescriptor(boolean coloured) {
		if(coloured) {
			return (secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"</span>";
		
		} else {
			return (secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName();
		}
	}
	
	/**
	 * @return A full description of this covering. e.g. "glowing black, wolf-like fur, with luminescent purple stripes" or "black, shaggy fur"
	 */
	public String getFullDescription(GameCharacter gc, boolean coloured) {
		//text-shadow: 0px 0px 4px #FF0000;
		if(coloured) {
			switch(pattern) {
				case HAIR_HIGHLIGHTS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "+type.getName(true, gc)+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" highlights</span>";
				case MOTTLED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "+type.getName(true, gc)+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" mottling</span>";
				case SPOTTED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "+type.getName(true, gc)+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" spots</span>";
				case STRIPED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>, "+type.getName(true, gc)+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" stripes</span>";
				case ORIFICE_ANUS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"-rimmed anus</span>, with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" internal walls</span>";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+" nipples</span>, with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" internal walls</span>";
				case ORIFICE_VAGINA:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"-lipped pussy</span>, with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" internal walls</span>";
				case ORIFICE_MOUTH:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+" lips</span>, with a "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" throat</span>";
				case NONE: case FLUID:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> "+type.getName(true, gc);
				case EYE_IRISES:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> irises";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"</span> irises";
				case EYE_PUPILS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> pupils";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"</span> pupils";
			
			}
			
		} else {
			switch(pattern) {
				case HAIR_HIGHLIGHTS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+type.getName(true, gc)+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" highlights";
				case MOTTLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+type.getName(true, gc)+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" mottling";
				case SPOTTED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+type.getName(true, gc)+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" spots";
				case STRIPED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+", "+type.getName(true, gc)+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" stripes";
				case ORIFICE_ANUS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+"-rimmed anus, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" internal walls";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" nipples, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" internal walls";
				case ORIFICE_VAGINA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+"-lipped pussy, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" internal walls";
				case ORIFICE_MOUTH:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" lips, with a "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" throat";
				case NONE: case FLUID:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" "+type.getName(true, gc);
				case EYE_IRISES:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" irises";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" irises";
				case EYE_PUPILS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" pupils";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" pupils";
			}
		}
		return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
	}
	
	private String spanStartGlowing(Colour colour) {
		return "<span style='color:"+colour.toWebHexString()+"; text-shadow: 0px 0px 4px "+colour.getShades()[4]+";'>";
	}
	
	private String getGlowingDescriptor() {
		return UtilText.returnStringAtRandom("glowing", "luminescent", "luminous", "fluorescent");
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)){
			if(o instanceof Covering){
				if(((Covering)o).getType() == type
						&& ((Covering)o).getPattern() == pattern
						&& ((Covering)o).getPrimaryColour() == primaryColour
						&& ((Covering)o).isPrimaryGlowing() == primaryGlowing
						&& ((Covering)o).getSecondaryColour() == secondaryColour
						&& ((Covering)o).isSecondaryGlowing() == secondaryGlowing){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + pattern.hashCode();
		result = 31 * result + primaryColour.hashCode();
		result = 31 * result + (primaryGlowing ? 1 : 0);
		result = 31 * result + secondaryColour.hashCode();
		result = 31 * result + (secondaryGlowing ? 1 : 0);
		return result;
	}

	@Override
	public BodyCoveringType getType() {
		return type;
	}

	public void setType(BodyCoveringType type) {
		this.type = type;
	}

	public CoveringPattern getPattern() {
		return pattern;
	}

	public void setPattern(CoveringPattern pattern) {
		this.pattern = pattern;
	}

	public Colour getPrimaryColour() {
		return primaryColour;
	}

	public void setPrimaryColour(Colour primaryColour) {
		this.primaryColour = primaryColour;
	}

	public Colour getSecondaryColour() {
		return secondaryColour;
	}

	public void setSecondaryColour(Colour secondaryColour) {
		this.secondaryColour = secondaryColour;
	}

	public boolean isPrimaryGlowing() {
		return primaryGlowing;
	}

	public void setPrimaryGlowing(boolean primaryGlowing) {
		this.primaryGlowing = primaryGlowing;
	}

	public boolean isSecondaryGlowing() {
		return secondaryGlowing;
	}

	public void setSecondaryGlowing(boolean secondaryGlowing) {
		this.secondaryGlowing = secondaryGlowing;
	}
}
