package com.lilithsthrone.game.character.markings;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.TFModifier;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.6
 * @version 0.3.9
 * @author Innoxia
 */
public class Tattoo extends AbstractCoreItem implements XMLSaving {
	

	private AbstractTattooType type;
	
	private Colour primaryColour;
	private Colour secondaryColour;
	private Colour tertiaryColour;

	private boolean glowing;
	
	private TattooWriting writing;
	
	private TattooCounter counter;
	
	protected List<ItemEffect> effects;
	
	protected Map<AbstractAttribute, Integer> attributeModifiers;
	
	private static Map<Colour, String> SVGGlowMap = new HashMap<>();

	public Tattoo(AbstractTattooType type,
			Colour primaryColour,
			Colour secondaryColour,
			Colour tertiaryColour,
			boolean glowing,
			TattooWriting writing,
			TattooCounter counter) {
		super(type.getName(),
				type.getName(),
				type.getPathName(),
				primaryColour,
				Rarity.COMMON,
				null);
		
		this.type = type;
		
		this.primaryColour = primaryColour;
		this.secondaryColour = secondaryColour;
		this.tertiaryColour = tertiaryColour;
		
		this.glowing = glowing;
		
		this.writing = writing;
		
		this.counter = counter;

		this.effects = new ArrayList<>();
		
		attributeModifiers = new HashMap<>();
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof Tattoo)
					&& ((Tattoo)o).getType()==type
					&& ((Tattoo)o).getPrimaryColour()==primaryColour
					&& ((Tattoo)o).getPrimaryColour()==secondaryColour
					&& ((Tattoo)o).getPrimaryColour()==tertiaryColour
					&& ((Tattoo)o).isGlowing()==glowing
					&& ((Tattoo)o).getWriting().equals(this.getWriting())
					&& ((Tattoo)o).getCounter().equals(this.getCounter())
					&& ((Tattoo)o).getEffects().equals(this.getEffects());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + this.getPrimaryColour().hashCode();
		if(this.getSecondaryColour()!=null) {
			result = 31 * result + this.getSecondaryColour().hashCode();
		}
		if(this.getTertiaryColour()!=null) {
			result = 31 * result + this.getTertiaryColour().hashCode();
		}
		result = 31 * result + (this.isGlowing() ? 1 : 0);
		result = 31 * result + this.getWriting().hashCode();
		result = 31 * result + this.getCounter().hashCode();
		result = 31 * result + this.getEffects().hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("tattoo");
		parentElement.appendChild(element);

		XMLUtil.addAttribute(doc, element, "id", TattooType.getIdFromTattooType(getType()));

		XMLUtil.addAttribute(doc, element, "name", this.getName());
		
		XMLUtil.addAttribute(doc, element, "primaryColour", this.getPrimaryColour().getId());

		if(this.getSecondaryColour()!=null) {
			XMLUtil.addAttribute(doc, element, "secondaryColour", this.getSecondaryColour().getId());
		}

		if(this.getTertiaryColour()!=null) {
			XMLUtil.addAttribute(doc, element, "tertiaryColour", this.getTertiaryColour().getId());
		}
		
		XMLUtil.addAttribute(doc, element, "glowing", String.valueOf(this.isGlowing()));
		
		if(this.getWriting()!=null) {
			this.getWriting().saveAsXML(element, doc);
		}

		if(this.getCounter()!=null) {
			this.getCounter().saveAsXML(element, doc);
		}
		
		Element innerElement = doc.createElement("effects");
		element.appendChild(innerElement);
		
		for(ItemEffect ie : this.getEffects()) {
			ie.saveAsXML(innerElement, doc);
		}
		
		return element;
	}
	
	public static Tattoo loadFromXML(Element parentElement, Document doc) {
		try {
			TattooWriting writing = null;
			if(parentElement.getElementsByTagName("tattooWriting").item(0)!=null) {
				writing = TattooWriting.loadFromXML((Element) parentElement.getElementsByTagName("tattooWriting").item(0), doc);
			}

			TattooCounter counter = null;
			if(parentElement.getElementsByTagName("tattooCounter").item(0)!=null) {
				counter = TattooCounter.loadFromXML((Element) parentElement.getElementsByTagName("tattooCounter").item(0), doc);
			}
			Tattoo tat = new Tattoo(
					TattooType.getTattooTypeFromId(parentElement.getAttribute("id")),
					PresetColour.getColourFromId(parentElement.getAttribute("primaryColour")),
					parentElement.getAttribute("secondaryColour").isEmpty()?null:PresetColour.getColourFromId(parentElement.getAttribute("secondaryColour")),
					parentElement.getAttribute("tertiaryColour").isEmpty()?null:PresetColour.getColourFromId(parentElement.getAttribute("tertiaryColour")),
					Boolean.valueOf(parentElement.getAttribute("glowing")),
					writing,
					counter);
			
			try {
				tat.setName(parentElement.getAttribute("name"));
			} catch(Exception ex) {
			}
			
			Element element = (Element)parentElement.getElementsByTagName("effects").item(0);
			if(element!=null) {
				NodeList nodeList = element.getElementsByTagName("effect");
				for(int i = 0; i < nodeList.getLength(); i++){
					Element e = ((Element)nodeList.item(i));
					ItemEffect ie = ItemEffect.loadFromXML(e, doc);
					if(ie!=null) {
						tat.addEffect(ie);
					}
				}
			}
			
			return tat;
			
		} catch(Exception ex) {
			System.err.println("Warning: An instance of Tattoo was unable to be imported!");
			return null;
		}
	}
	
	@Override
	public String getSVGString() {
		return getSVGImage(Main.game.getPlayer());
	}
	
	public String getSVGImage(GameCharacter character) {
		if(this.isGlowing()) {
			return getSVGGlow() + "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;padding:0;margin:0'>"+type.getSVGImage(character, primaryColour, secondaryColour, tertiaryColour)+"</div>";
			
		} else {
			return type.getSVGImage(character, primaryColour, secondaryColour, tertiaryColour);
		}
	}
	
	private String getSVGGlow() {
		String stringFromMap = SVGGlowMap.get(this.getPrimaryColour());
		if (stringFromMap!=null) {
			return stringFromMap;
			
		} else {
			try {
				InputStream is;
				String s;
				
				is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/tattoos/glow.svg");
				s = Util.inputStreamToString(is);
				is.close();
				
				s = SvgUtil.colourReplacement("tattooGlow"+this.getPrimaryColour().getId(), this.getPrimaryColour(), this.getPrimaryColour(), this.getPrimaryColour(), s);
				
				SVGGlowMap.put(this.getPrimaryColour(), s);
				
				return s;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return "";
	}

	public Rarity getRarity() {
		if(effects.isEmpty()) {
			return Rarity.COMMON;
			
		} else if(effects.size()<=1) {
			return Rarity.UNCOMMON;
			
		} else if(effects.size()<=3) {
			return Rarity.RARE;
			
		} else if(effects.size()<=5) {
			return Rarity.EPIC;
			
		} else {
			return Rarity.LEGENDARY;
		}
	}

	public String getDisplayName(boolean withRarityColour) {

		if(!this.getName().replaceAll("\u00A0"," ").equalsIgnoreCase(this.getType().getName().replaceAll("\u00A0"," "))) { // If this tattoo has a custom name, just display that:
			return (withRarityColour
						? " <span style='color: " + this.getRarity().getColour().toWebHexString() + ";'>" + getName() + "</span>"
						: getName());
//					+" tattoo";
		}
		
		return Util.capitaliseSentence(this.getPrimaryColour().getName()) + " "
				+ (withRarityColour?" <span style='color: " + this.getRarity().getColour().toWebHexString() + ";'>":"")
					+ (this.getType()==TattooType.NONE
						?"tattoo"
						:this.getName() + " tattoo")
				+ (withRarityColour?"</span>":"")
				+(!this.getEffects().isEmpty()
						? " "+getEnchantmentPostfix(withRarityColour, "b")
						: "");
	}
	
	public String getEnchantmentPostfix(boolean coloured, String tag) {
		if(!this.getEffects().isEmpty()) {
			for(ItemEffect ie : this.getEffects()) {
				if(ie.getSecondaryModifier() == TFModifier.CLOTHING_ENSLAVEMENT) {
					return "of "+(coloured?"<"+tag+" style='color:"+TFModifier.CLOTHING_ENSLAVEMENT.getColour().toWebHexString()+";'>enslavement</"+tag+">":"enslavement");
					
				} else if(ie.getSecondaryModifier() == TFModifier.CLOTHING_SERVITUDE) {
					return "of "+(coloured?"<"+tag+" style='color:"+TFModifier.CLOTHING_SERVITUDE.getColour().toWebHexString()+";'>servitude</"+tag+">":"servitude");
					
				} else if(ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BEHAVIOUR || ie.getPrimaryModifier() == TFModifier.TF_MOD_FETISH_BODY_PART) {
					return "of "+(coloured?"<"+tag+" style='color:"+PresetColour.FETISH.toWebHexString()+";'>"+ie.getSecondaryModifier().getDescriptor()+"</"+tag+">":ie.getSecondaryModifier().getDescriptor());
					
				} else if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE) {
					String name = (this.isBadEnchantment()?this.getCoreEnchantment().getNegativeEnchantment():this.getCoreEnchantment().getPositiveEnchantment());
					return "of "+(coloured?"<"+tag+" style='color:"+this.getCoreEnchantment().getColour().toWebHexString()+";'>"+name+"</"+tag+">":name);
					
				} else if(ie.getSecondaryModifier() == TFModifier.CLOTHING_SEALING) {
					return "of "+(coloured?"<"+tag+" style='color:"+PresetColour.SEALED.toWebHexString()+";'>sealing</"+tag+">":"sealing");
					
				} else {
					return "of "+(coloured?"<"+tag+" style='color:"+PresetColour.TRANSFORMATION_GENERIC.toWebHexString()+";'>transformation</"+tag+">":"transformation");
				}
			}
		}
		return "";
	}

	public AbstractAttribute getCoreEnchantment() {
		AbstractAttribute att = Attribute.MAJOR_PHYSIQUE;
		int max = 0;
		for(Entry<AbstractAttribute, Integer> entry : getAttributeModifiers().entrySet()) {
			if(entry.getValue() > max) {
				att = entry.getKey();
				max = entry.getValue();
			}
		}
		
		return att;
	}
	
	public boolean isBadEnchantment() {
//		return this.getEffects().stream().mapToInt(e -> (e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || e.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE)?e.getPotency().getClothingBonusValue():0).sum()<0;
		return this.getEffects().stream().mapToInt(e -> (
				((e.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || e.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE))
					?e.getPotency().getClothingBonusValue()*(e.getSecondaryModifier()==TFModifier.CORRUPTION?-1:1)
					:0)
				+ (e.getSecondaryModifier()==TFModifier.CLOTHING_SEALING?-10:0)
				+ (e.getSecondaryModifier()==TFModifier.CLOTHING_SERVITUDE?-10:0)
			).sum()<0;
	}
	
	public boolean isSelfTransformationInhibiting() {
		return this.getEffects().stream().anyMatch(e -> e.getSecondaryModifier() == TFModifier.CLOTHING_SERVITUDE);
	}

	public boolean isJinxRemovalInhibiting() {
		return this.getEffects().stream().anyMatch(e -> e.getSecondaryModifier() == TFModifier.CLOTHING_SERVITUDE);
	}
	
	public Map<AbstractAttribute, Integer> getAttributeModifiers() {
		attributeModifiers.clear();
		
		for(ItemEffect ie : getEffects()) {
			if(ie.getPrimaryModifier() == TFModifier.CLOTHING_ATTRIBUTE || ie.getPrimaryModifier() == TFModifier.CLOTHING_MAJOR_ATTRIBUTE) {
				if(attributeModifiers.containsKey(ie.getSecondaryModifier().getAssociatedAttribute())) {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), attributeModifiers.get(ie.getSecondaryModifier().getAssociatedAttribute()) + ie.getPotency().getClothingBonusValue());
				} else {
					attributeModifiers.put(ie.getSecondaryModifier().getAssociatedAttribute(), ie.getPotency().getClothingBonusValue());
				}
			}
		}
		
		return attributeModifiers;
	}

	/**
	 * @return An integer value of the 'enchantment capacity cost' for this particular tattoo. Does not count negative attribute values, nor values of Corruption.
	 */
	public int getEnchantmentCapacityCost() {
		Map<AbstractAttribute, Integer> noCorruption = new HashMap<>();
		attributeModifiers.entrySet().stream().filter(ent -> ent.getKey()!=Attribute.MAJOR_CORRUPTION && ent.getKey()!=Attribute.FERTILITY && ent.getKey()!=Attribute.VIRILITY).forEach(ent -> noCorruption.put(ent.getKey(), ent.getValue()));
		return noCorruption.values().stream().reduce(0, (a, b) -> a + Math.max(0, b));
	}
	
	public AbstractTattooType getType() {
		return type;
	}

	public List<ItemEffect> getEffects() {
		return effects;
	}
	
	public void setEffects(List<ItemEffect> effects) {
		this.effects = new ArrayList<>(effects);
	}

	public void addEffect(ItemEffect effect) {
		effects.add(effect);
	}

	public Colour getPrimaryColour() {
		return primaryColour;
	}

	public Colour getSecondaryColour() {
		return secondaryColour;
	}

	public Colour getTertiaryColour() {
		return tertiaryColour;
	}

	public boolean isGlowing() {
		return glowing;
	}

	public TattooWriting getWriting() {
		return writing;
	}

	public void setWriting(TattooWriting writing) {
		this.writing = writing;
	}

	public void setWriting(String text, Colour colour, boolean glow, TattooWritingStyle... styles) {
		this.writing = new TattooWriting(text, colour, glow, styles);
	}
	
	/**
	 * For examples.
	 */
	public String getFormattedWritingOutput(String input) {
		return (this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"<b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICISED)?"<i>":"")
				+"<span style='color:"+getWriting().getColour().toWebHexString()+";'>"+(getWriting().isGlow()?UtilText.applyGlow(input, getWriting().getColour()):input)+"</span>"
				+(this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"</b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICISED)?"</i>":"");
	}
	
	public String getFormattedWritingOutput() {
		return (this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"<b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICISED)?"<i>":"")
				+"<span style='color:"+getWriting().getColour().toWebHexString()+";'>"+(getWriting().isGlow()?UtilText.applyGlow(getWriting().getText(), getWriting().getColour()):getWriting().getText())+"</span>"
				+(this.getWriting().getStyles().contains(TattooWritingStyle.BOLD)?"</b>":"")
				+ (this.getWriting().getStyles().contains(TattooWritingStyle.ITALICISED)?"</i>":"");
	}

	public TattooCounter getCounter() {
		return counter;
	}

	public void setCounter(TattooCounter counter) {
		this.counter = counter;
	}

	public void setCounter(TattooCounterType type, TattooCountType countType, Colour colour, boolean glow) {
		this.counter = new TattooCounter(type, countType, colour, glow);
	}
	
	/**
	 * For examples.
	 */
	public String getFormattedCounterOutput(int input) {
		return "<span style='color:"+getCounter().getColour().toWebHexString()+";'>"+(getCounter().isGlow()?UtilText.applyGlow(getCounter().getCountType().convertInt(input), getCounter().getColour()):getCounter().getCountType().convertInt(input))+"</span>";
	}
	
	public String getFormattedCounterOutput(GameCharacter equippedToCharacter) {
		String convertedInt = getCounter().getCountType().convertInt(getCounter().getType().getCount(equippedToCharacter));
		return "<span style='color:"+getCounter().getColour().toWebHexString()+";'>"+(getCounter().isGlow()?UtilText.applyGlow(convertedInt, getCounter().getColour()):convertedInt)+"</span>";
	}

	public void setType(AbstractTattooType type) {
		this.type = type;
	}

	public void setPrimaryColour(Colour primaryColour) {
		this.primaryColour = primaryColour;
	}

	public void setSecondaryColour(Colour secondaryColour) {
		this.secondaryColour = secondaryColour;
	}

	public void setTertiaryColour(Colour tertiaryColour) {
		this.tertiaryColour = tertiaryColour;
	}

	public void setGlowing(boolean glowing) {
		this.glowing = glowing;
	}

	@Override
	public String getDescription() {
		return this.getType().getDescription();
	}

	public String getBodyOverviewDescription() {
		return this.getType().getBodyOverviewDescription();
	}
	
	@Override
	public int getValue() {
		return this.getType().getValue();
	}
	
	@Override
	public int getEnchantmentLimit() {
		return this.getType().getEnchantmentLimit();
	}
	
	@Override
	public AbstractItemEffectType getEnchantmentEffect() {
		return ItemEffectType.TATTOO;
	}
	
	@Override
	public AbstractTattooType getEnchantmentItemType(List<ItemEffect> effects) {
		return this.getType();
	}
}
