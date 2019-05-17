package com.lilithsthrone.game.character.body.types;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3
 * @version 0.3.1
 * @author Innoxia
 */
public abstract class AbstractBreastType implements BodyPartTypeInterface {

	private BodyCoveringType skinType;
	private Race race;
	private NippleType nippleType;
	private FluidType fluidType;
	
	private List<String> namesFlat;
	private List<String> namesFlatPlural;
	private List<String> descriptorsFlat;

	private List<String> namesBreasts;
	private List<String> namesBreastsPlural;
	private List<String> descriptorsBreasts;
	
	private String breastsTransformationDescription;
	private String breastsBodyDescription;
	private String breastsCrotchTransformationDescription;
	private String breastsCrotchBodyDescription;
	
	/**
	 * @param skinType What covers this breasts type (i.e skin/fur/feather type). This is never used, as skin type covering breasts is determined by torso covering.
	 * @param race What race has this breasts type.
	 * @param nippleType The type of nipples that these breasts have.
	 * @param fluidType The type of milk that these breasts produce.
	 * @param namesFlat A list of singular names for this breasts type for when the character has a flat chest. Pass in null to use generic names.
	 * @param namesFlatPlural A list of plural names for this breasts type for when the character has a flat chest. Pass in null to use generic names.
	 * @param descriptorsFlat The descriptors that can be used to describe this breasts type for when the character has a flat chest.
	 * @param namesBreasts A list of singular names for this breasts type for when the character has breasts, not a flat chest. Pass in null to use generic names.
	 * @param namesBreastsPlural A list of plural names for this breasts type for when the character has breasts, not a flat chest. Pass in null to use generic names.
	 * @param descriptorsBreasts The descriptors that can be used to this breasts type for when the character has breasts, not a flat chest.
	 * @param breastsTransformationDescription A paragraph describing a character's breasts transforming into this breasts type. Parsing assumes that the character already has this breasts type and associated skin covering.
	 * @param breastsBodyDescription A sentence or two to describe this breasts type, as seen in the character view screen. It should follow the same format as all of the other entries in the BreastType class.
	 * @param breastsCrotchTransformationDescription A paragraph describing a character's crotch-boobs transforming into this breasts type. Parsing assumes that the character already has this breasts type and associated skin covering.
	 * @param breastsCrotchBodyDescription A sentence or two to describe this crotch-boob type, as seen in the character view screen. It should follow the same format as all of the other entries in the BreastType class.
	 */
	public AbstractBreastType(BodyCoveringType skinType,
			Race race,
			NippleType nippleType,
			FluidType fluidType, //Should probably be linked to nipples, but as nipples are always the same as breast type, it doesn't make a difference.
			List<String> namesFlat,
			List<String> namesFlatPlural,
			List<String> descriptorsFlat,
			List<String> namesBreasts,
			List<String> namesBreastsPlural,
			List<String> descriptorsBreasts,
			String breastsTransformationDescription,
			String breastsBodyDescription,
			String breastsCrotchTransformationDescription,
			String breastsCrotchBodyDescription) {
		
		this.skinType = skinType;
		this.race = race;
		this.nippleType = nippleType;
		this.fluidType = fluidType;
		
		this.namesFlat = namesFlat;
		this.namesFlatPlural = namesFlatPlural;
		this.descriptorsFlat = descriptorsFlat;

		this.namesBreasts = namesBreasts;
		this.namesBreastsPlural = namesBreastsPlural;
		this.descriptorsBreasts = descriptorsBreasts;
		
		this.breastsTransformationDescription = breastsTransformationDescription;
		this.breastsBodyDescription = breastsBodyDescription;
		
		this.breastsCrotchTransformationDescription = breastsCrotchTransformationDescription;
		this.breastsCrotchBodyDescription = breastsCrotchBodyDescription;
	}
	
	public AbstractBreastType(BodyCoveringType skinType,
			Race race,
			NippleType nippleType,
			FluidType fluidType,
			String breastsTransformationDescription,
			String breastsBodyDescription,
			String breastsCrotchTransformationDescription,
			String breastsCrotchBodyDescription) {
		this(skinType,
				race,
				nippleType,
				fluidType,
				null, null, null, null, null, null,
				breastsTransformationDescription,
				breastsBodyDescription,
				breastsCrotchTransformationDescription,
				breastsCrotchBodyDescription);
	}
	
	public NippleType getNippleType() {
		return nippleType;
	}

	public FluidType getFluidType() {
		return fluidType;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getBreastCrotchShape()==BreastShape.UDDERS) {
			return "a set of";
		}
		if(gc.getBreastRows()==1) {
			return "a pair of";
		} else {
			return Util.intToString(gc.getBreastRows())+" pairs of";
		}
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if (gc.hasBreasts()) {
			if(namesBreasts==null) {
				return UtilText.returnStringAtRandom("breast", "boob", "tit");
			}
			return Util.randomItemFrom(namesBreasts);
			
		} else {
			if(namesFlat==null) {
				return UtilText.returnStringAtRandom("pec");
			}
			return Util.randomItemFrom(namesFlat);
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if (gc.hasBreasts()) {
			if(namesBreastsPlural==null) {
				return UtilText.returnStringAtRandom("breasts", "boobs", "mammaries", "tits");
			}
			return Util.randomItemFrom(namesBreastsPlural);
			
		} else {
			if(namesFlatPlural==null) {
				return UtilText.returnStringAtRandom("pecs");
			}
			return Util.randomItemFrom(namesFlatPlural);
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		if (gc.hasBreasts()) {
			if(descriptorsBreasts==null) {
				return "";
			}
			return Util.randomItemFrom(descriptorsBreasts);
		} else {
			if(descriptorsFlat==null) {
				return "";
			}
			return Util.randomItemFrom(descriptorsFlat);
		}
	}

	public String getCrotchNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("crotch-breast", "crotch-boob", "crotch-boob", "crotch-boob", "crotch-tit");
	}
	
	public String getCrotchNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("crotch-breasts", "crotch-boobs", "crotch-boobs", "crotch-boobs", "crotch-tits");
	}

	@Override
	/**
	 * <b>This should never be used - the covering of breasts is determined by the torso's covering!</b>
	 */
	public BodyCoveringType getBodyCoveringType(Body body) {
		if(body!=null) {
			return body.getSkin().getBodyCoveringType(body);
		}
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

//	@Override
	public String getBodyDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsBodyDescription);
	}
	
//	@Override
	public String getTransformationDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsTransformationDescription);
	}

	public String getTransformationCrotchDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsCrotchTransformationDescription);
	}
	
	public String getBodyCrotchDescription(GameCharacter owner) {
		return UtilText.parse(owner, breastsCrotchBodyDescription);
	}
	
}
