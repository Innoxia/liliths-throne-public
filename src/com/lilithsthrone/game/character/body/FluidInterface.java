package com.lilithsthrone.game.character.body;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractFluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;

import java.util.List;

/**
 * @since 0.2.6
 * @version 0.3.8.2
 * @author Innoxia
 */
public interface FluidInterface extends BodyPartInterface {

	@Override
	AbstractFluidType getType();
	
	FluidFlavour getFlavour();
	String setFlavour(GameCharacter owner, FluidFlavour flavour);
	
	
	List<FluidModifier> getFluidModifiers();
	
	boolean hasFluidModifier(FluidModifier fluidModifier);
	
	String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier);
	String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier);
	
	
	List<ItemEffect> getTransformativeEffects();
	void addTransformativeEffect(ItemEffect ie);
	
	
	float getValuePerMl();
	
}
