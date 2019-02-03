package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.86
 * @version 0.1.88
 * @author Innoxia
 */
public class AbstractFilledCondom extends AbstractItem implements XMLSaving {
	
	
	private String cumProvidor;
	private FluidCum cum;
	private int millilitresStored;
	
	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, GameCharacter cumProvidor, FluidCum cum, int millilitresStored) {
		super(itemType);
		
		this.cumProvidor = cumProvidor.getId();
		this.cum = new FluidCum(cum.getType());
		this.cum.setFlavour(cumProvidor, cum.getFlavour());
		for(FluidModifier fm : cum.getFluidModifiers()) {
			this.cum.addFluidModifier(cumProvidor, fm);
		}
		this.colourShade = colour;
		SVGString = getSVGString(itemType.getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, String cumProvidorId, FluidCum cum, int millilitresStored) {
		super(itemType);
		
		this.cumProvidor = cumProvidorId;
		this.cum = new FluidCum(cum.getType());
		this.cum.setFlavour(null, cum.getFlavour());
		for(FluidModifier fm : cum.getFluidModifiers()) {
			this.cum.addFluidModifier(null, fm);
		}
		this.colourShade = colour;
		SVGString = getSVGString(itemType.getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractFilledCondom)
					&& ((AbstractFilledCondom)o).getCumProvidorId().equals(this.getCumProvidorId())
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

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("item");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getItemType().getId());
		CharacterUtils.addAttribute(doc, element, "colour", String.valueOf(this.getColour()));
		CharacterUtils.addAttribute(doc, element, "cumProvidor", this.getCumProvidorId());
		CharacterUtils.addAttribute(doc, element, "millilitresStored", String.valueOf(this.getMillilitresStored()));
		
		Element innerElement = doc.createElement("itemEffects");
		element.appendChild(innerElement);
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}

		innerElement = doc.createElement("cum");
		element.appendChild(innerElement);
		this.getCum().saveAsXML(innerElement, doc);
		
		return element;
	}

	public static AbstractFilledCondom loadFromXML(Element parentElement, Document doc) {
		return new AbstractFilledCondom(
				ItemType.getIdToItemMap().get(parentElement.getAttribute("id")),
				Colour.valueOf(parentElement.getAttribute("colour")),
				parentElement.getAttribute("cumProvidor"),
				((Element) parentElement.getElementsByTagName("cum").item(0)==null
					?new FluidCum(FluidType.CUM_HUMAN)
					:FluidCum.loadFromXML((Element) parentElement.getElementsByTagName("cum").item(0), doc)),
				(parentElement.getAttribute("millilitresStored").isEmpty()
					?25
					:Integer.valueOf(parentElement.getAttribute("millilitresStored"))));
	}
	
	private String getSVGString(String pathName, Colour colour) {
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/items/" + pathName + ".svg");
			String s = Util.inputStreamToString(is);

			s = SvgUtil.colourReplacement(String.valueOf(this.hashCode()), colour, s);
			
			is.close();
			
			return s;
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return "";
	}
	
	@Override
	public String applyEffect(GameCharacter user, GameCharacter target) {
		if(target.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
			return UtilText.parse(user, target,
					"<p>"
						+ "[npc.Name] can't help but let out a delighted [npc.moan] as [npc.she] greedily [npc.verb(gulp)] down the slimy fluid."
						+ " Darting [npc.her] [npc.tongue] out, [npc.she] desperately [npc.verb(lick)] up every last drop of cum; only discarding the condom once [npc.sheIs] sure that's it's completely empty."
					+ "</p>"
					+ target.ingestFluid(getCumProvidor(), cum, SexAreaOrifice.MOUTH, millilitresStored));
		} else {
			return UtilText.parse(user, target,
					"<p>"
						+ "[npc.Name] scrunches [npc.her] [npc.eyes] shut as [npc.she] [npc.verb(gulp)] down the slimy fluid,"
						+ " trying [npc.her] best not to think about what [npc.sheHas] just done as "+(user.equals(target)?"[npc.she] [npc.verb(throw)]":"[npc2.name] [npc2.verb(throw)]")+" the now-empty condom to the floor..."
					+ "</p>"
					+ target.ingestFluid(getCumProvidor(), cum, SexAreaOrifice.MOUTH, millilitresStored));
		}
		
	}
	
	public String getCumProvidorId() {
		return cumProvidor;
	}
	
	public GameCharacter getCumProvidor() {
		try {
			return Main.game.getNPCById(cumProvidor);
		} catch (Exception e) {
			Util.logGetNpcByIdError("getCumProvidor()", cumProvidor);
			return null;
		}
	}

	public FluidCum getCum() {
		return cum;
	}

	public int getMillilitresStored() {
		return millilitresStored;
	}

	public void setMillilitresStored(int millilitresStored) {
		this.millilitresStored = millilitresStored;
	}
	
}
