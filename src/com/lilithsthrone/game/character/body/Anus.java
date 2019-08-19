package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.AnusType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.3.1
 * @author Innoxia
 */
public class Anus implements BodyPartInterface {

	
	// Asshole variables:
	protected AnusType type;
	protected OrificeAnus orificeAnus;
	protected boolean bleached;
	protected BodyHair assHair;

	public Anus(AnusType type, int wetness, float capacity, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		
		orificeAnus = new OrificeAnus(wetness, capacity, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
		
		bleached = false;
		assHair = BodyHair.ZERO_NONE;
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
		return type.getNamePlural(gc);
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		List<String> descriptorList = new ArrayList<String>();
		
		for(OrificeModifier om : orificeAnus.getOrificeModifiers()) {
			descriptorList.add(om.getName());
		}
		
		String wetnessDescriptor = orificeAnus.getWetness(owner).getDescriptor();
		if(Main.game.isInSex() && Sex.getAllParticipants().contains(owner)) {
			if(Sex.hasLubricationTypeFromAnyone(owner, SexAreaOrifice.ANUS)) {
				wetnessDescriptor = "wet";
			}
		}
		descriptorList.add(wetnessDescriptor);
		if(owner.getPubicHair().getValue()>=BodyHair.FOUR_NATURAL.getValue() && Main.game.isAssHairEnabled()) {
			descriptorList.add("hairy");
		}
		
		if(owner.isAnusBestial()) {
			descriptorList.add(Util.randomItemFrom(Util.newArrayListOfValues(
					"feral",
					owner.getAssRace().getName(true)+"-",
					"bestial",
					"animalistic")));
		} else {
			descriptorList.add(type.getDescriptor(owner));
		}

		descriptorList.add(Capacity.getCapacityFromValue(orificeAnus.getStretchedCapacity()).getDescriptor());
		
		return UtilText.returnStringAtRandom(descriptorList.toArray(new String[]{}));
	}

	@Override
	public AnusType getType() {
		return type;
	}
	
	public void setType(AnusType type) {
		this.type = type;
	}
	
	public OrificeAnus getOrificeAnus() {
		return orificeAnus;
	}


	public boolean isBleached() {
		return bleached;
	}
	
	public String setAssBleached(GameCharacter owner, boolean bleached) {
		if(this.bleached == bleached) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.bleached = bleached;
		
		if(bleached) {
			if(owner.isPlayer()) {
				return "<p>[style.boldTfSex(Your asshole is now bleached!)]</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[style.boldTfSex([npc.NamePos] asshole is now bleached!)]</p>");
			}
		} else {
			if(owner.isPlayer()) {
				return "<p>[style.boldTfSex(Your asshole is no longer bleached!)]</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[style.boldTfSex([npc.NamePos] asshole is no longer bleached!)]</p>");
			}
		}
	}

	public BodyHair getAssHair() {
		return assHair;
	}
	
	public Covering getAssHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getAssType().getRace()));
	}
	
	public String setAssHair(GameCharacter owner, BodyHair assHair) {
		if(owner==null) {
			this.assHair=assHair;
			return "";
		}
		String transformation = "";
		
		if(getAssHair() == assHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			switch(assHair) {
				case ZERO_NONE:
					transformation = UtilText.parse(owner, "<p>There is no longer any trace of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.namePos] asshole.</p>");
					break;
				case ONE_STUBBLE:
					transformation = UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a stubbly patch of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
					break;
				case TWO_MANICURED:
					transformation = UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a well-manicured patch of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
					break;
				case THREE_TRIMMED:
					transformation = UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a trimmed patch of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
					break;
				case FOUR_NATURAL:
					transformation = UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a natural amount of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
					break;
				case FIVE_UNKEMPT:
					transformation = UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a unkempt bush of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
					break;
				case SIX_BUSHY:
					transformation = UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a thick, bushy mass of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
					break;
				case SEVEN_WILD:
					transformation = UtilText.parse(owner, "<p>[npc.Name] now [npc.has] a wild, bushy mass of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
					break;
			}
		}
		
		this.assHair = assHair;
		
		return transformation;
	}

	@Override
	public boolean isBestial(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.getLegConfiguration().getBestialParts().contains(Anus.class) && getType().getRace().isBestialPartsAvailable();
	}

}