package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.2.1
 * @author Innoxia
 */
public class Covering  {
	
	protected BodyCoveringType type;
	protected CoveringPattern pattern;
	protected CoveringModifier modifier;
	
	protected Colour primaryColour, secondaryColour;
	protected boolean primaryGlowing, secondaryGlowing;

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
		this(type, pattern, type.getNaturalModifiers().get(0), primaryColour, primaryGlowing, secondaryColour, secondaryGlowing);
	}
	
	public Covering(BodyCoveringType type, CoveringPattern pattern, CoveringModifier modifier, Colour primaryColour, boolean primaryGlowing, Colour secondaryColour, boolean secondaryGlowing) {
		this.type = type;
		this.pattern = pattern;
		this.modifier = modifier;
		this.primaryColour = primaryColour;
		this.primaryGlowing = primaryGlowing;
		this.secondaryColour = secondaryColour;
		this.secondaryGlowing = secondaryGlowing;
	}
	
	public Covering(Covering coveringToClone) {
		this.type = coveringToClone.getType();
		this.pattern = coveringToClone.getPattern();
		this.modifier = coveringToClone.getModifier();
		this.primaryColour = coveringToClone.getPrimaryColour();
		this.primaryGlowing = coveringToClone.isPrimaryGlowing();
		this.secondaryColour = coveringToClone.getSecondaryColour();
		this.secondaryGlowing = coveringToClone.isSecondaryGlowing();
	}
	
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
//	public String getName(GameCharacter gc, boolean withDescriptor) {
//		return (getDescriptor(gc).length() > 0 ? getDescriptor(gc) + " " : "") + getName(gc);
//	}

	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	public String getNamePlural(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	public String getDescriptor(GameCharacter gc) {
		return modifier.getName();
	}
	
	public String getColourDescriptor(GameCharacter gc, boolean coloured, boolean capitalised) {
		String primaryColourName = capitalised?Util.capitaliseSentence(primaryColour.getName()):primaryColour.getName();
		String secondaryColourName = capitalised?Util.capitaliseSentence(secondaryColour.getName()):secondaryColour.getName();
//		if(gc.getRace()==Race.SLIME) {
//			if(this.getType()!=BodyCoveringType.SLIME) {
//				return gc.getCovering(BodyCoveringType.SLIME).getColourDescriptor(gc, coloured, capitalised);
//			}
//		}
		if(coloured) {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-highlighted</span>";
				case OMBRE:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"fading into <span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"</span>";
				case MOTTLED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-mottled</span>";
				case FRECKLED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-freckled</span>";
				case SPOTTED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-spotted</span>";
				case MARKED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-marked</span>";
				case STRIPED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-striped</span>";
				case ORIFICE_ANUS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"-rimmed</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-interiored</span>";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-interiored</span>";
				case ORIFICE_VAGINA:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"-lipped</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-interiored</span>";
				case ORIFICE_MOUTH:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"-lipped</span>, "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"-interiored</span>";
				case NONE: case FLUID:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>";
				case EYE_IRISES:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"</span>";
				case EYE_PUPILS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"</span>";
				case EYE_SCLERA:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>";
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColourName+"</span>";
			}
			return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColourName+"</span>";
		
		} else {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName+"-highlighted";
				case OMBRE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+", fading into "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName+"";
				case MOTTLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName+"-mottled";
				case FRECKLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName+"-freckled";
				case SPOTTED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName+"-spotted";
				case MARKED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName+"-marked";
				case STRIPED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+", "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName+"-striped";
				case ORIFICE_ANUS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+"-rimmed";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName;
				case ORIFICE_VAGINA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+"-lipped";
				case ORIFICE_MOUTH:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+" lips";
				case NONE: case FLUID:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName;
				case EYE_IRISES:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName;
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName;
				case EYE_PUPILS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName;
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName;
				case EYE_SCLERA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName;
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColourName;
			}
			return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColourName;
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
		String descriptor = modifier.getName();
		String name = type.getName(gc);
//		if(gc.getRace()==Race.SLIME) {
//			if(this.getType()!=BodyCoveringType.SLIME) {
//				return gc.getCovering(BodyCoveringType.SLIME).getFullDescription(gc, coloured);
//			}
//		}
		if(coloured) {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" highlights</span>";
				case OMBRE:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", which gradually fades into "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"</span>";
				case MOTTLED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" mottling</span>";
				case FRECKLED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" freckles</span>";
				case SPOTTED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" spots</span>";
				case MARKED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
							+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+" markings</span>";
				case STRIPED:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "
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
					if(primaryColour==Colour.COVERING_NONE) {
						return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>";
					}
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span>"
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name;
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
				case EYE_SCLERA:
					return (primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> sclerae";
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?spanStartGlowing(primaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+primaryColour.toWebHexString()+";'>")+primaryColour.getName()+"</span> and "
						+(secondaryGlowing?spanStartGlowing(secondaryColour)+getGlowingDescriptor()+" ":"<span style='color:"+secondaryColour.toWebHexString()+";'>")+secondaryColour.getName()+"</span> sclerae";
			
			}
			
		} else {
			switch(pattern) {
				case HIGHLIGHTS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" highlights";
				case OMBRE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", which gradually fades into "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName();
				case MOTTLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" mottling";
				case FRECKLED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" freckles";
				case SPOTTED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" spots";
				case MARKED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" markings";
				case STRIPED:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()
							+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name+", with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" stripes";
				case ORIFICE_ANUS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+"-rimmed anus, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" internal walls";
				case ORIFICE_NIPPLE:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" nipples, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" internal walls";
				case ORIFICE_VAGINA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+"-lipped pussy, with "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" internal walls";
				case ORIFICE_MOUTH:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" lips, with a "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" throat";
				case NONE: case FLUID:
					if(primaryColour==Colour.COVERING_NONE) {
						return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName();
					}
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+(descriptor!=null && !descriptor.isEmpty()?", "+descriptor:"")+" "+name;
				case EYE_IRISES:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" irises";
				case EYE_IRISES_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" irises";
				case EYE_PUPILS:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" pupils";
				case EYE_PUPILS_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" sclerae";
				case EYE_SCLERA:
					return (primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" pupils";
				case EYE_SCLERA_HETEROCHROMATIC:
					return "heterochromatic "+(primaryGlowing?getGlowingDescriptor()+" ":"")+primaryColour.getName()+" and "+(secondaryGlowing?getGlowingDescriptor()+" ":"")+secondaryColour.getName()+" sclerae";
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
//		if(super.equals(o)){
			if(o instanceof Covering){
				if(((Covering)o).getType() == type
						&& ((Covering)o).getPattern() == pattern
						&& ((Covering)o).getModifier() == modifier
						&& ((Covering)o).getPrimaryColour() == primaryColour
						&& ((Covering)o).isPrimaryGlowing() == primaryGlowing
						&& ((Covering)o).getSecondaryColour() == secondaryColour
						&& ((Covering)o).isSecondaryGlowing() == secondaryGlowing){
					return true;
				}
			}
//		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + pattern.hashCode();
		result = 31 * result + modifier.hashCode();
		result = 31 * result + primaryColour.hashCode();
		result = 31 * result + (primaryGlowing ? 1 : 0);
		result = 31 * result + secondaryColour.hashCode();
		result = 31 * result + (secondaryGlowing ? 1 : 0);
		return result;
	}

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

	public CoveringModifier getModifier() {
		return modifier;
	}

	public void setModifier(CoveringModifier modifier) {
		this.modifier = modifier;
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
