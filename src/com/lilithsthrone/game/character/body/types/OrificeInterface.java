package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;

/**
 * @since 0.1.83
 * @version 0.3.9
 * @author Innoxia
 */
public interface OrificeInterface {
	
	public Wetness getWetness(GameCharacter owner);
	public String setWetness(GameCharacter owner, int wetness);

	public Capacity getCapacity();
	public float getRawCapacityValue();
	public String setCapacity(GameCharacter owner, float capacity, boolean setStretchedValueToNewValue);
	public float getStretchedCapacity();
	public boolean setStretchedCapacity(float stretchedCapacity);

	/** @return The minimum OrificeDepth which this orifice needs in order to comfortably accommodate the provided insertionSize. */
	public default OrificeDepth getMinimumDepthForSizeComfortable(GameCharacter owner, int insertionSize) {
		OrificeDepth depth = OrificeDepth.ONE_SHALLOW;
		while(getMaximumPenetrationDepthComfortable(owner, depth)<insertionSize) {
			if(depth == OrificeDepth.SEVEN_FATHOMLESS) {
				return depth;
			}
			depth = OrificeDepth.getDepthFromInt(depth.getValue()+1);
		}
		return depth;
	}
	
	/** @return The minimum OrificeDepth which this orifice needs in order to uncomfortably accommodate the provided insertionSize. */
	public default OrificeDepth getMinimumDepthForSizeUncomfortable(GameCharacter owner, int insertionSize) {
		OrificeDepth depth = OrificeDepth.ONE_SHALLOW;
		while(getMaximumPenetrationDepthUncomfortable(owner, depth)<insertionSize) {
			if(depth == OrificeDepth.SEVEN_FATHOMLESS) {
				return depth;
			}
			depth = OrificeDepth.getDepthFromInt(depth.getValue()+1);
		}
		return depth;
	}
	
	/** @return The maximum depth at which penetration size can be considered comfortable. (Uses the getDepth(owner) depth for calculation.)*/
	public default int getMaximumPenetrationDepthComfortable(GameCharacter owner) {
		return getMaximumPenetrationDepthComfortable(owner, getDepth(owner));
	}

	/** @return The maximum depth at which penetration size can be considered comfortable when this orifice has the provided depth.<br/>
	 * <b>You should probably be using getMaximumPenetrationDepthComfortable(GameCharacter owner)</b> */
	public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth);

	/** @return The maximum depth at which penetration size can be accommodated. (Uses the getDepth(owner) depth for calculation.)*/
	public default int getMaximumPenetrationDepthUncomfortable(GameCharacter owner) {
		return getMaximumPenetrationDepthUncomfortable(owner, getDepth(owner));
	}
	
	/** @return The maximum depth at which penetration size can be accommodated when this orifice has the provided depth.<br/>
	 * <b>You should probably be using getMaximumPenetrationDepthUncomfortable(GameCharacter owner)</b> */
	public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner, OrificeDepth depth);
	
	public OrificeDepth getDepth(GameCharacter owner);
	public String setDepth(GameCharacter owner, int depth);
	
	public OrificeElasticity getElasticity();
	public String setElasticity(GameCharacter owner, int elasticity);
	
	public OrificePlasticity getPlasticity();
	public String setPlasticity(GameCharacter owner, int plasticity);

	public boolean isVirgin();
	public void setVirgin(boolean virgin);

	public boolean hasOrificeModifier(OrificeModifier modifier);
	public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier);
	public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier);
	
}