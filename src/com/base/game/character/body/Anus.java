package com.base.game.character.body;

import java.io.Serializable;
import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.AnusType;
import com.base.game.character.body.valueEnums.BodyHair;
import com.base.game.character.body.valueEnums.OrificeModifier;
import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.OrificeType;
import com.base.game.sex.Sex;
import com.base.main.Main;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class Anus implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	// Asshole variables:
	private AnusType type;
	private OrificeAnus orificeAnus;
	private boolean bleached;
	private BodyHair assHair;

	public Anus(AnusType type, int wetness, int capacity, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		
		orificeAnus = new OrificeAnus(wetness, capacity, elasticity, plasticity, virgin, type.getDefaultRacialOrificeModifiers());
		
		bleached = false;
		assHair = BodyHair.NONE;
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
		// Randomly give a capacity, wetness or type-specific descriptor:
		
		String wetnessDescriptor = orificeAnus.getWetness(owner).getDescriptor();
		if(Main.game.isInSex()) {
			if(owner.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.ANUS_PLAYER).isEmpty()) {
				wetnessDescriptor = "wet";
			} else if(!owner.isPlayer() && !Sex.getWetOrificeTypes().get(OrificeType.ANUS_PARTNER).isEmpty()) {
				wetnessDescriptor = "wet";
			}
		}
		
		return UtilText.returnStringAtRandom(
				(orificeAnus.hasOrificeModifier(OrificeModifier.MUSCLE_CONTROL)?OrificeModifier.MUSCLE_CONTROL.getName():""),
				(orificeAnus.hasOrificeModifier(OrificeModifier.RIBBED)?OrificeModifier.RIBBED.getName():""),
				(orificeAnus.hasOrificeModifier(OrificeModifier.TENTACLED)?OrificeModifier.TENTACLED.getName():""),
				(orificeAnus.hasOrificeModifier(OrificeModifier.PUFFY)?OrificeModifier.PUFFY.getName():""),
				
				((assHair==BodyHair.BUSHY || assHair==BodyHair.TRIMMED) && Main.game.isBodyHairEnabled() ? "hairy" :""),
				
				type.getDescriptor(owner),
				
				wetnessDescriptor,
				
				orificeAnus.getCapacity().getDescriptor());
		
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
						"<p>[style.boldTfSex([npc.Name]'s asshole is now bleached!)]</p>");
			}
		} else {
			if(owner.isPlayer()) {
				return "<p>[style.boldTfSex(Your asshole is no longer bleached!)]</p>";
			} else {
				return UtilText.parse(owner,
						"<p>[style.boldTfSex([npc.Name]'s asshole is no longer bleached!)]</p>");
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
		String transformation = "";
		
		if(getAssHair() == assHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			if(owner.isPlayer()) {
				switch(assHair) {
					case NONE:
						transformation = "<p>There is no longer any trace of "+getAssHairType(owner).getFullDescription(owner, true)+" around your asshole.</p>";
						break;
					case MANICURED:
						transformation = "<p>You now have a well-manicured patch of "+getAssHairType(owner).getFullDescription(owner, true)+" around your asshole.</p>";
						break;
					case TRIMMED:
						transformation = "<p>You now have a trimmed patch of "+getAssHairType(owner).getFullDescription(owner, true)+" around your asshole.</p>";
						break;
					case BUSHY:
						transformation = "<p>You now have a thick mass of "+getAssHairType(owner).getFullDescription(owner, true)+" around your asshole.</p>";
						break;
				}
				
			} else {
				switch(assHair) {
					case NONE:
						transformation = UtilText.parse(owner, "<p>There is no longer any trace of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.name]'s asshole.</p>");
						break;
					case MANICURED:
						transformation = UtilText.parse(owner, "<p>[npc.Name] now has a well-manicured patch of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
						break;
					case TRIMMED:
						transformation = UtilText.parse(owner, "<p>[npc.Name] now has a trimmed patch of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
						break;
					case BUSHY:
						transformation = UtilText.parse(owner, "<p>[npc.Name] now has a thick mass of "+getAssHairType(owner).getFullDescription(owner, true)+" around [npc.her] asshole.</p>");
						break;
				}
			}
		}
		
		this.assHair = assHair;
		
		return transformation;
	}

}