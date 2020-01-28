package com.lilithsthrone.game.character.body;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidFlavour;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public interface FluidInterface extends BodyPartInterface {

	@Override
	public FluidType getType();
	
	public FluidFlavour getFlavour();
	public String setFlavour(GameCharacter owner, FluidFlavour flavour);
	
	
	public List<FluidModifier> getFluidModifiers();
	
	public boolean hasFluidModifier(FluidModifier fluidModifier);
	
	public String addFluidModifier(GameCharacter owner, FluidModifier fluidModifier);
	public String removeFluidModifier(GameCharacter owner, FluidModifier fluidModifier);
	
	
	public List<ItemEffect> getTransformativeEffects();
	public void addTransformativeEffect(ItemEffect ie);
	
	
	public float getValuePerMl();
	
}
