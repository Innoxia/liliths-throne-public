package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.86
 * @version 0.4.0
 * @author Innoxia
 */
public class AbstractFilledCondom extends AbstractItem implements XMLSaving {
	
	private String cumProvider;
	private FluidCum cum;
	private int millilitresStored;
	
	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, GameCharacter cumProvider, FluidCum cum, int millilitresStored) {
		super(itemType);
		
		this.cumProvider = cumProvider.getId();
		this.cum = new FluidCum(cum.getType());
		this.cum.setFlavour(cumProvider, cum.getFlavour());
		for(FluidModifier fm : cum.getFluidModifiers()) {
			this.cum.addFluidModifier(cumProvider, fm);
		}
		this.setColour(0, colour);
		SVGString = getSVGString(itemType.getPathNameInformation().get(0).getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, String cumProviderId, FluidCum cum, int millilitresStored) {
		super(itemType);
		
		this.cumProvider = cumProviderId;
		this.cum = new FluidCum(cum.getType());
		this.cum.setFlavour(null, cum.getFlavour());
		for(FluidModifier fm : cum.getFluidModifiers()) {
			this.cum.addFluidModifier(null, fm);
		}
		this.setColour(0, colour);
		SVGString = getSVGString(itemType.getPathNameInformation().get(0).getPathName(), colour);
		this.millilitresStored = millilitresStored;
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractFilledCondom)
					&& ((AbstractFilledCondom)o).getCumProviderId().equals(this.getCumProviderId())
					&& ((AbstractFilledCondom)o).getCum().equals(cum);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + cumProvider.hashCode();
		result = 31 * result + cum.hashCode();
		return result;
	}

	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("item");
		parentElement.appendChild(element);
		
		XMLUtil.addAttribute(doc, element, "id", this.getItemType().getId());
		XMLUtil.addAttribute(doc, element, "colour", this.getColour(0).getId());
		XMLUtil.addAttribute(doc, element, "cumProvider", this.getCumProviderId());
		XMLUtil.addAttribute(doc, element, "millilitresStored", String.valueOf(this.getMillilitresStored()));
		
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
		String provider = parentElement.getAttribute("cumProvider");
		if(provider.isEmpty()) {
			provider = parentElement.getAttribute("cumProvidor"); // Support for old versions in which I could not spell
		}
		
		return new AbstractFilledCondom(
				ItemType.getIdToItemMap().get(parentElement.getAttribute("id")),
				PresetColour.getColourFromId(parentElement.getAttribute("colour")),
				provider,
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
		if(cum==null) {
			System.err.println("WARNING: AbstractFilledCondom is calling applyEffect() when cum variable is null!!!");
		}
		if(target.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
			return UtilText.parse(target, user,
					"<p>"
						+ "[npc.Name] can't help but let out a delighted [npc.moan] as [npc.she] greedily [npc.verb(gulp)] down the slimy fluid."
						+ " Darting [npc.her] [npc.tongue] out, [npc.she] desperately [npc.verb(lick)] up every last drop of cum; only discarding the condom once [npc.sheIs] sure that's it's completely empty."
					+ "</p>"
					+ (cum==null
						?""
						:target.ingestFluid(getCumProvider(), cum, SexAreaOrifice.MOUTH, millilitresStored)));
		} else {
			return UtilText.parse(target, user,
					"<p>"
						+ "[npc.Name] [npc.verb(scrunch)] [npc.her] [npc.eyes] shut as [npc.she] [npc.verb(gulp)] down the slimy fluid,"
						+ " trying [npc.her] best not to think about what [npc.sheHas] just done as "+(user.equals(target)?"[npc.she] [npc.verb(throw)]":"[npc2.name] [npc2.verb(throw)]")+" the now-empty condom to the floor..."
					+ "</p>"
					+ (cum==null
						?""
						:target.ingestFluid(getCumProvider(), cum, SexAreaOrifice.MOUTH, millilitresStored)));
		}
		
	}
	
	public String getCumProviderId() {
		return cumProvider;
	}
	
	public GameCharacter getCumProvider() {
		try {
			return Main.game.getNPCById(cumProvider);
		} catch (Exception e) {
			Util.logGetNpcByIdError("getCumProvider()", cumProvider);
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
