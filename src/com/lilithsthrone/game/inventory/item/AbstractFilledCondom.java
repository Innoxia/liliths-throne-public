package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.sex.OrificeType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.86
 * @version 0.1.88
 * @author Innoxia
 */
public class AbstractFilledCondom extends AbstractItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private GameCharacter cumProvidor;
	private FluidCum cum;
	
	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, GameCharacter cumProvidor, FluidCum cum) {
		super(itemType);
		
		this.cumProvidor = cumProvidor;
		this.cum = new FluidCum(cum.getType());
		this.cum.setFlavour(cumProvidor, cum.getFlavour());
		for(FluidModifier fm : cum.getFluidModifiers()) {
			this.cum.addFluidModifier(cumProvidor, fm);
		}
		this.colourShade = colour;
		SVGString = getSVGString(itemType.getPathName(), colour);
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractFilledCondom)
					&& ((AbstractFilledCondom)o).getCumProvidor().equals(cumProvidor)
					&& ((AbstractFilledCondom)o).getCum().equals(cum);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + cumProvidor.hashCode();
		result = 31 * result + cum.hashCode();
		return result;
	}
	
	private String getSVGString(String pathName, Colour colour) {
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
			String s = Util.inputStreamToString(is);

			for (int i = 0; i <= 14; i++)
				s = s.replaceAll("linearGradient" + i, this.hashCode() + colour.toString() + "linearGradient" + i);
			s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
			s = s.replaceAll("#ff5555", colour.getShades()[1]);
			s = s.replaceAll("#ff8080", colour.getShades()[2]);
			s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
			s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
			
			is.close();
			
			return s;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	@Override
	public String applyEffect(GameCharacter user, GameCharacter target) {
		if(target.isPlayer()) {
			if(target.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
				return "<p>"
							+ "You can't help but let out a delighted [pc.moan] as you greedily gulp down the slimy fluid."
							+ " Darting your [pc.tongue] out, you desperately lick up every last drop of cum; only discarding the condom once you're sure that's it's completely empty."
						+ "</p>"
						+ target.ingestFluid(cumProvidor, cum.getType(), OrificeType.MOUTH_PLAYER, cum.hasFluidModifier(FluidModifier.ADDICTIVE));
			} else {
				return "<p>"
							+ "You scrunch your [pc.eyes] shut as you gulp down the slimy fluid, trying your best not to think about what you've just done as you throw the now-empty condom to the floor..."
						+ "</p>"
						+ target.ingestFluid(cumProvidor, cum.getType(), OrificeType.MOUTH_PLAYER, cum.hasFluidModifier(FluidModifier.ADDICTIVE));
			}
			
		} else {
			if(target.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
				return "<p>"
							+ "[npc.Name] can't help but let out a delighted [npc.moan] as [npc.she] greedily gulps down the slimy fluid."
							+ " Darting [npc.her] [npc.tongue] out, [npc.she] desperately licks up every last drop of cum; only discarding the condom once [npc.she]'s sure that's it's completely empty."
						+ "</p>"
						+ target.ingestFluid(cumProvidor, cum.getType(), OrificeType.MOUTH_PARTNER, cum.hasFluidModifier(FluidModifier.ADDICTIVE));
			} else {
				return "<p>"
							+ "[npc.Name] scrunches [npc.her] [npc.eyes] shut as [npc.she] gulps down the slimy fluid, trying [npc.her] best not to think about what [npc.she]'s just done as [npc.she] throws the now-empty condom to the floor..."
						+ "</p>"
						+ target.ingestFluid(cumProvidor, cum.getType(), OrificeType.MOUTH_PARTNER, cum.hasFluidModifier(FluidModifier.ADDICTIVE));
			}
		}
	}
	
	public GameCharacter getCumProvidor() {
		return cumProvidor;
	}

	public FluidCum getCum() {
		return cum;
	}
	
}
