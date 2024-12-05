package com.lilithsthrone.game.inventory.item;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.FluidStored;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.FluidCum;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.valueEnums.FluidModifier;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.race.AbstractRacialBody;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.86
 * @version 0.4.9.7
 * @author Innoxia
 */
public class AbstractFilledCondom extends AbstractItem implements XMLSaving {
	
	private FluidStored cum;
	
	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, GameCharacter cumProvider, FluidCum cum, int millilitresStored) {
//		super(itemType);
//		
//		this.cumProvider = cumProvider.getId();
//		this.cum = new FluidStored(cumProvider, new FluidCum(cum), millilitresStored);
//		this.millilitresStored = millilitresStored;
//		
//		this.setColour(0, colour);
//		SVGString = getSVGString(itemType.getPathNameInformation().get(0).getPathName(), colour);
		
		this(itemType, colour, cumProvider, new FluidStored(cumProvider, new FluidCum(cum), millilitresStored), millilitresStored);
	}

	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, GameCharacter cumProvider, FluidStored cum, int millilitresStored) {
//		super(itemType);
//		
//		this.cumProvider = cumProvider.getId();
//		this.cum = cum;
//		this.millilitresStored = millilitresStored;
//		
//		this.setColour(0, colour);
//		SVGString = getSVGString(itemType.getPathNameInformation().get(0).getPathName(), colour);

		this(itemType, colour, cum);
	}

	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, FluidStored cum) {
		super(itemType);
		
		this.cum = cum;
		
		this.setColour(0, colour);
		SVGString = getSVGString(itemType.getPathNameInformation().get(0).getPathName(), colour);
	}
	
	public AbstractFilledCondom(AbstractItemType itemType, Colour colour, String cumProviderId, Body cumProviderBody, FluidCum cum, int millilitresStored) {
		super(itemType);
		
		this.cum = new FluidStored(cumProviderId, cumProviderBody, new FluidCum(cum), millilitresStored);
		
		this.setColour(0, colour);
		SVGString = getSVGString(itemType.getPathNameInformation().get(0).getPathName(), colour);
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

		this.getCum().saveAsXML(element, doc);
		
		return element;
	}

	public static AbstractFilledCondom loadFromXML(Element parentElement, Document doc) {
		String provider = parentElement.getAttribute("cumProvider");
		if(provider.isEmpty()) {
			provider = parentElement.getAttribute("cumProvidor"); // Support for old versions in which I could not spell
		}

		float ml = parentElement.getAttribute("millilitresStored").isEmpty()
				?25
				:Float.valueOf(parentElement.getAttribute("millilitresStored"));
		
		FluidStored fs = null;;
		if(parentElement.getElementsByTagName("fluidStored").item(0)!=null) {
			fs = FluidStored.loadFromXML(null, (Element) parentElement.getElementsByTagName("fluidStored").item(0), doc);
			
		} else { // Old version support to generate Body based on cum type:
			FluidCum fluidCum = ((Element) parentElement.getElementsByTagName("cum").item(0)==null
					?new FluidCum(FluidType.CUM_HUMAN)
					:FluidCum.loadFromXML("cum", (Element) parentElement.getElementsByTagName("cum").item(0), doc));
			
			AbstractRacialBody racialBody = fluidCum.getType().getRace().getRacialBody();
			
			fs = new FluidStored(provider, Main.game.getCharacterUtils().generateBody(null, Gender.M_P_MALE, racialBody, RaceStage.GREATER), fluidCum, ml);
		}
		
		
		return new AbstractFilledCondom(
				ItemType.getIdToItemMap().get(parentElement.getAttribute("id")),
				PresetColour.getColourFromId(parentElement.getAttribute("colour")),
				fs);
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
		Main.game.setContent(new Response(
				"",
				"",
				MiscDialogue.getUsedCondomSelectionDialogue(
						Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.INVENTORY?InventoryDialogue.getOwner():user,
								user,
								target,
								this,
								this.getItemType().getUseDescription(user, target))));
		return "";
//		if(target.hasFetish(Fetish.FETISH_CUM_ADDICT)) {
//			return UtilText.parse(target, user,
//					"<p>"
//						+ "[npc.Name] can't help but let out a delighted [npc.moan] as [npc.she] greedily [npc.verb(gulp)] down the slimy fluid."
//						+ " Darting [npc.her] [npc.tongue] out, [npc.she] desperately [npc.verb(lick)] up every last drop of cum; only discarding the condom once [npc.sheIs] sure that it's completely empty."
//					+ "</p>"
//					+ (cum==null
//						?""
//						:target.ingestFluid(cum, SexAreaOrifice.MOUTH, getMillilitresStored())));
//		} else {
//			return UtilText.parse(target, user,
//					"<p>"
//						+ "[npc.Name] [npc.verb(scrunch)] [npc.her] [npc.eyes] shut as [npc.she] [npc.verb(gulp)] down the slimy fluid,"
//						+ " trying [npc.her] best not to think about what [npc.sheHas] just done as "+(user.equals(target)?"[npc.she] [npc.verb(throw)]":"[npc2.name] [npc2.verb(throw)]")+" the now-empty condom to the floor..."
//					+ "</p>"
//					+ (cum==null
//						?""
//						:target.ingestFluid(cum, SexAreaOrifice.MOUTH, getMillilitresStored())));
//		}
		
	}

	@Override
	public boolean isAppendItemEffectLinesToTooltip() {
		return getCumProvider()==null;
	}
	
	@Override
	public List<String> getEffectTooltipLines() {
		List<String> descriptionsList = new ArrayList<>();
		
		if(getCumProvider()!=null) {
			descriptionsList.add(UtilText.parse(getCumProvider(),
					"Contains [units.fluid("+getMillilitresStored()+")] of <span style='color:"+getCumProvider().getFemininity().getColour().toWebHexString()+";'>[npc.namePos]</span> [style.colourCum("+cum.getFluid().getName(getCumProvider())+")]"));
		} else {
			descriptionsList.add("Contains [units.fluid("+getMillilitresStored()+")] of [style.colourCum(cum)]");
		}
		
		descriptionsList.add("It tastes of <span style='color:"+cum.getFluid().getFlavour().getColour().toWebHexString()+";'>"+cum.getFluid().getFlavour().getName()+"</span>");
		if(!cum.getFluid().getFluidModifiers().isEmpty()) {
			StringBuilder modifiersSB = new StringBuilder();
			modifiersSB.append("It is ");
			List<String> modList = new ArrayList<>();
			for(FluidModifier mod : cum.getFluid().getFluidModifiers()) {
				modList.add("<span style='color:"+mod.getColour().toWebHexString()+";'>"+mod.getName()+"</span>");
			}
			modifiersSB.append(Util.stringsToStringList(modList, false));
			descriptionsList.add(modifiersSB.toString());
		}
		
		return descriptionsList;
	}
	
	public String getCumProviderId() {
		return getCum().getCharactersFluidID();
	}
	
	public GameCharacter getCumProvider() {
		try {
			return getCum().getFluidCharacter();
		} catch (Exception e) {
			Util.logGetNpcByIdError("AbstractFilledCondom.getCumProvider()", getCum().getCharactersFluidID());
			return null;
		}
	}

	public FluidStored getCum() {
		return cum;
	}

	public float getMillilitresStored() {
		return getCum().getMillilitres();
	}
	
	@Override
	public boolean isAbleToBeUsed(GameCharacter user, GameCharacter target) {
		if(!target.isPlayer() && target.isUnique() && !target.isSlave()) {
			return false; // Do not allow unique characters to be impregnated by condoms!
		}
		return user.hasFetish(Fetish.FETISH_CUM_ADDICT) || user.getAttributeValue(Attribute.MAJOR_CORRUPTION)>=CorruptionLevel.THREE_DIRTY.getMinimumValue();
	}
	@Override
	public String getUnableToBeUsedDescription(GameCharacter user, GameCharacter target) {
		if(!target.isPlayer() && target.isUnique() && !target.isSlave()) {
			return UtilText.parse(target, "As [npc.nameIsFull] a non-slave unique character, you cannot use used condoms on [npc.herHim]...");
		}
		return "You can't think of a use for this. Maybe it's best to throw it away...<br/>"
				+ "(You require either the '[style.colourFetish("+Fetish.FETISH_CUM_ADDICT.getName(user)+")]' fetish"
						+ " or to have at least a <b style='color:"+CorruptionLevel.THREE_DIRTY.getColour().toWebHexString()+";'>"+CorruptionLevel.THREE_DIRTY.getName()+"</b> level of corruption in order to use this!)";
	}

}
