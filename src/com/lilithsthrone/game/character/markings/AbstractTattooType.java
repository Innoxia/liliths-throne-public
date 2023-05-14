package com.lilithsthrone.game.character.markings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.game.inventory.item.SvgInformation;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.ColourListPresets;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.2.6
 * @version 0.2.6
 * @author Innoxia
 */
public class AbstractTattooType extends AbstractCoreType {
	
	private static List<InventorySlot> standardInventorySlots = new ArrayList<>(InventorySlot.getClothingSlots());
	
	private boolean isMod;
	
	private int value;
	
	private List<InventorySlot> slotAvailability;

	private String name;
	private String description;
	private String bodyOverviewDescription;

	private List<Colour> availablePrimaryColours;
	private List<Colour> availableSecondaryColours;
	private List<Colour> availableTertiaryColours;

	private Colour defaultPrimaryColour;
	private Colour defaultSecondaryColour;
	private Colour defaultTertiaryColour;
	
	private List<SvgInformation> svgPathInformation;
	private Map<Colour, Map<Colour, Map<Colour, String>>> SVGStringMap;

	private String availabilityRequirements;
	private boolean unique;
	
	/**
	 * @param pathName
	 * @param name
	 * @param description
	 * @param bodyOverviewDescription To fit into the sentence at point X: "On [npc.her] "+tattooSlotName+", [npc.sheHasFull] "+X+"."
	 * @param availablePrimaryColours
	 * @param availableSecondaryColours
	 * @param availableTertiaryColours
	 * @param slotAvailability
	 */
	public AbstractTattooType(
			String pathName,
			String name,
			String description,
			String bodyOverviewDescription,
			List<Colour> availablePrimaryColours,
			List<Colour> availableSecondaryColours,
			List<Colour> availableTertiaryColours,
			List<InventorySlot> slotAvailability) {

		this.isMod = false;
		
		this.unique = false;
		
		value = 500;
		
		this.svgPathInformation = Util.newArrayListOfValues(new SvgInformation(1, pathName==null?"":pathName, 100, 0, new HashMap<>()));
		this.name = name;
		this.description = description;
		this.bodyOverviewDescription = bodyOverviewDescription;
		
		this.availablePrimaryColours = new ArrayList<>();
		if (availablePrimaryColours == null) {
			this.availablePrimaryColours.add(PresetColour.CLOTHING_BLACK);
		} else {
			this.availablePrimaryColours.addAll(availablePrimaryColours);
		}

		this.availableSecondaryColours = new ArrayList<>();
		if (availableSecondaryColours != null) {
			this.availableSecondaryColours.addAll(availableSecondaryColours);
		}

		this.availableTertiaryColours = new ArrayList<>();
		if (availableTertiaryColours != null) {
			this.availableTertiaryColours.addAll(availableTertiaryColours);
		}
		
		defaultPrimaryColour = this.availablePrimaryColours.get(0);
		defaultSecondaryColour = this.availableSecondaryColours.size()>0?this.availableSecondaryColours.get(0):defaultPrimaryColour;
		defaultTertiaryColour = this.availableTertiaryColours.size()>0?this.availableTertiaryColours.get(0):defaultPrimaryColour;
		
		SVGStringMap = new HashMap<>();
		
		if(slotAvailability==null) {
			this.slotAvailability = standardInventorySlots;
		} else {
			this.slotAvailability = slotAvailability;
		}
	}
	

	public AbstractTattooType(File tattooXMLFile) {

		if (tattooXMLFile.exists()) {
			try {
				Element tattooElement = Element.getDocumentRootElement(tattooXMLFile); // Loads the document and returns the root element - in item mods it's <tattoo>
				Element coreAttributes = null;
				try {
					coreAttributes = tattooElement.getMandatoryFirstOf("coreAtributes");
				} catch (XMLMissingTagException ex) {
					coreAttributes = tattooElement.getMandatoryFirstOf("coreAttributes");
				}

				List<InventorySlot> slotAvailability = new ArrayList<>();
				if(coreAttributes.getOptionalFirstOf("slotAvailability").isPresent()) {
					for(Element slotElement : coreAttributes.getMandatoryFirstOf("slotAvailability").getAllOf("slot")) {
						slotAvailability.add(InventorySlot.valueOf(slotElement.getTextContent()));
					}
				}
				if(slotAvailability.isEmpty()) {
					this.slotAvailability = new ArrayList<>(standardInventorySlots);
				} else {
					this.slotAvailability = slotAvailability;
				}
				
				this.isMod = true;
				
				this.svgPathInformation = new ArrayList<>();

				for(Element imagePathElement : coreAttributes.getAllOf("imageName")) {
					String rawPath = imagePathElement.getTextContent();
					String pathName;
					if(rawPath.contains("res/")) {
						pathName = rawPath;
					} else {
						pathName = tattooXMLFile.getParentFile().getAbsolutePath() + "/"+ rawPath;
					}
					int zLayer = 1;
					int imageSize = 100;
					int imageRotation = 0;
					Map<String, String> replacements = new HashMap<>();

					if(!imagePathElement.getAttribute("zLayer").isEmpty()) {
						try {
							zLayer = Integer.valueOf(imagePathElement.getAttribute("zLayer"));
						} catch(Exception ex) {
						}
					}
					if(!imagePathElement.getAttribute("imageSize").isEmpty()) {
						try {
							imageSize = Math.min(100, Math.max(1, Integer.valueOf(imagePathElement.getAttribute("imageSize"))));
						} catch(Exception ex) {
						}
					}
					if(!imagePathElement.getAttribute("imageRotation").isEmpty()) {
						try {
							imageRotation = Math.min(360, Math.max(-360, Integer.valueOf(imagePathElement.getAttribute("imageRotation"))));
						} catch(Exception ex) {
						}
					}
					int i=1;
					while(!imagePathElement.getAttribute("target"+i).isEmpty()) {
						replacements.put(imagePathElement.getAttribute("target"+i), imagePathElement.getAttribute("replacement"+i));
						i++;
					}
					
					svgPathInformation.add(new SvgInformation(zLayer, pathName, imageSize, imageRotation, replacements));
				}

				this.value = Integer.valueOf(coreAttributes.getMandatoryFirstOf("value").getTextContent());
				this.name = coreAttributes.getMandatoryFirstOf("name").getTextContent();
				this.description = coreAttributes.getMandatoryFirstOf("description").getTextContent();
				
				this.bodyOverviewDescription = "";
				if(coreAttributes.getOptionalFirstOf("bodyOverviewDescription").isPresent()) {
					this.bodyOverviewDescription = coreAttributes.getMandatoryFirstOf("bodyOverviewDescription").getTextContent();
				}
				
				this.unique = false;
				this.availabilityRequirements = "";
				if(coreAttributes.getOptionalFirstOf("availabilityRequirements").isPresent()) {
					this.unique = Boolean.valueOf(coreAttributes.getMandatoryFirstOf("availabilityRequirements").getAttribute("unique"));
					this.availabilityRequirements = coreAttributes.getMandatoryFirstOf("availabilityRequirements").getTextContent();
				}
				
				
				List<Colour> importedPrimaryColours = new ArrayList<>();
				try {
					importedPrimaryColours = readColoursFromElement(coreAttributes, "primaryColours");
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'primaryColours' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}

				List<Colour> importedSecondaryColours = new ArrayList<>();
				try {
					importedSecondaryColours = readColoursFromElement(coreAttributes, "secondaryColours");
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'secondaryColours' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}

				List<Colour> importedTertiaryColours = new ArrayList<>();
				try {
					importedTertiaryColours = readColoursFromElement(coreAttributes, "tertiaryColours");
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'tertiaryColours' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}
				
				this.availablePrimaryColours = new ArrayList<>(importedPrimaryColours);
				this.availableSecondaryColours = new ArrayList<>(importedSecondaryColours);
				this.availableTertiaryColours = new ArrayList<>(importedTertiaryColours);
				
				defaultPrimaryColour = this.availablePrimaryColours.get(0);
				String defaultColour = coreAttributes.getMandatoryFirstOf("primaryColours").getAttribute("defaultColour");
				if(!defaultColour.isEmpty()) {
					defaultPrimaryColour = PresetColour.getColourFromId(defaultColour);
				}
				defaultSecondaryColour = this.availableSecondaryColours.size()>0?this.availableSecondaryColours.get(0):defaultPrimaryColour;
				defaultColour = coreAttributes.getMandatoryFirstOf("secondaryColours").getAttribute("defaultColour");
				if(!defaultColour.isEmpty()) {
					defaultSecondaryColour = PresetColour.getColourFromId(defaultColour);
				}
				defaultTertiaryColour = this.availableTertiaryColours.size()>0?this.availableTertiaryColours.get(0):defaultPrimaryColour;
				defaultColour = coreAttributes.getMandatoryFirstOf("tertiaryColours").getAttribute("defaultColour");
				if(!defaultColour.isEmpty()) {
					defaultTertiaryColour = PresetColour.getColourFromId(defaultColour);
				}
				
				SVGStringMap = new HashMap<>();

			} catch(Exception ex) {
				System.err.println("TattooType was unable to be loaded from file! (" + tattooXMLFile.getName() + ")\n" + ex);
			}
		}
	}


	private List<Colour> readColoursFromElement(Element coreAttributes, String elementTagName) throws XMLMissingTagException {
		Element coloursElement = coreAttributes.getMandatoryFirstOf(elementTagName);
		if(coloursElement.getAttribute("values").isEmpty()) {
			List<Colour> result = new ArrayList<>();
			for(Element colourElement : coloursElement.getAllOf("colour")) {
				result.add(PresetColour.getColourFromId(colourElement.getTextContent()));
			}
			return result;
		}
		return ColourListPresets.getColourListFromId(coloursElement.getAttribute("values"));
	}
	
	@Override
	public boolean equals(Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractTattooType)
					&& ((AbstractTattooType)o).isMod()==isMod
					&& ((AbstractTattooType)o).getSlotAvailability().equals(this.getSlotAvailability())
					&& ((AbstractTattooType)o).getName().equals(this.getName())
					&& ((AbstractTattooType)o).getDescription().equals(this.getDescription())
					&& ((AbstractTattooType)o).getAvailablePrimaryColours().equals(this.getAvailablePrimaryColours())
					&& ((AbstractTattooType)o).getAvailableSecondaryColours().equals(this.getAvailableSecondaryColours())
					&& ((AbstractTattooType)o).getAvailableTertiaryColours().equals(this.getAvailableTertiaryColours())
					&& ((AbstractTattooType)o).getPathName().equals(this.getPathName());
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (this.isMod() ? 1 : 0);
		
		result = 31 * result + getSlotAvailability().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + getDescription().hashCode();
		
		result = 31 * result + getAvailablePrimaryColours().hashCode();
		result = 31 * result + getAvailableSecondaryColours().hashCode();
		result = 31 * result + getAvailableTertiaryColours().hashCode();
		
		result = 31 * result + getPathName().hashCode();
		
		return result;
	}
	
	public boolean isMod() {
		return isMod;
	}
	
	public int getValue() {
		return value;
	}

	public List<SvgInformation> getSvgPathInformation() {
		return svgPathInformation;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getBodyOverviewDescription() {
		return bodyOverviewDescription;
	}

	public List<Colour> getAvailablePrimaryColours() {
		return availablePrimaryColours;
	}

	public List<Colour> getAvailableSecondaryColours() {
		return availableSecondaryColours;
	}

	public List<Colour> getAvailableTertiaryColours() {
		return availableTertiaryColours;
	}

	public Colour getDefaultPrimaryColour() {
		return defaultPrimaryColour;
	}

	public Colour getDefaultSecondaryColour() {
		return defaultSecondaryColour;
	}

	public Colour getDefaultTertiaryColour() {
		return defaultTertiaryColour;
	}

	public List<InventorySlot> getSlotAvailability() {
		return new ArrayList<>(slotAvailability);
	}
	
	public boolean isLimitedSlotAvailability() {
		return !slotAvailability.containsAll(standardInventorySlots);
	}
	
	public boolean isAvailable(GameCharacter target) {
		if(availabilityRequirements!=null && !availabilityRequirements.isEmpty()) {
			return Boolean.valueOf(UtilText.parse(target, ("[#"+availabilityRequirements+"]").replaceAll("\u200b", "")));
		}
		return true;
	}
	
	public boolean isUnique() {
		return unique;
	}
	
	public String getId() {
		return TattooType.getIdFromTattooType(this);
	}
	
	public int getEnchantmentLimit() {
		return 100;
	}
	
	public AbstractItemEffectType getEnchantmentEffect() {
		return ItemEffectType.TATTOO;
	}
	
	private void addSVGStringMapping(Colour colour, Colour colourSecondary, Colour colourTertiary, String s) {
		if(SVGStringMap.get(colour)==null) {
			SVGStringMap.put(colour, new HashMap<>());
			SVGStringMap.get(colour).put(colourSecondary, new HashMap<>());
			
		} else if(SVGStringMap.get(colour).get(colourSecondary)==null) {
			SVGStringMap.get(colour).put(colourSecondary, new HashMap<>());
		}
		
		SVGStringMap.get(colour).get(colourSecondary).put(colourTertiary, s);
	}
	
	private String getSVGStringFromMap(Colour colour, Colour colourSecondary, Colour colourTertiary) {
		if(SVGStringMap.get(colour)==null) {
			return null;
		} else {
			if(SVGStringMap.get(colour).get(colourSecondary)==null) {
				return null;
			} else {
				return SVGStringMap.get(colour).get(colourSecondary).get(colourTertiary);
			}
		}
	}

	/**
	 * @return The svg using default colours.
	 */
	public String getSVGImage(GameCharacter character) {
		return getSVGImage(character, getDefaultPrimaryColour(), getDefaultSecondaryColour(), getDefaultTertiaryColour());
	}
	
	public String getSVGImage(GameCharacter character, Colour colour, Colour colourSecondary, Colour colourTertiary) {
		if (svgPathInformation==null || svgPathInformation.isEmpty()) {
			return "";
		}
		
		String stringFromMap = getSVGStringFromMap(colour, colourSecondary, colourTertiary);
		if (stringFromMap!=null) {
			return stringFromMap;
			
		} else {
			try {
				InputStream is;
				String s;
				if(isMod) {
					Collections.sort(svgPathInformation, (i1, i2)->i1.getZLayer()-i2.getZLayer());
					StringBuilder svgBuilder = new StringBuilder();
					for(SvgInformation info : getSvgPathInformation()) {
						List<String> lines = Files.readAllLines(Paths.get(info.getPathName()));
						StringBuilder sb = new StringBuilder();
						for(String line : lines) {
							sb.append(line);
						}
						int sizeOffset = (100-info.getImageSize())/2;
						svgBuilder.append("<div style='"
								+ "width:"+info.getImageSize()+"%;"
								+ "height:"+info.getImageSize()+"%;"
								+ "transform:rotate("+info.getImageRotation()+"deg);"
								+ "position:absolute;"
								+ "left:"+sizeOffset+"%;"
								+ "bottom:"+sizeOffset+"%;"
								+ "padding:0;"
								+ "margin:0;'>");
						String finalSvg = sb.toString();
						for(Entry<String, String> entry : info.getReplacements().entrySet()) {
							finalSvg = finalSvg.replaceAll(entry.getKey(), entry.getValue());
						}
						svgBuilder.append(finalSvg);
						svgBuilder.append("</div>");
					}

					s = svgBuilder.toString();
					
				} else {
					is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/tattoos/" + getPathName() + ".svg");
					s = Util.inputStreamToString(is);
					is.close();
				}
				
				s = SvgUtil.colourReplacement(this.getId(), colour, colourSecondary, colourTertiary, s);
				
				addSVGStringMapping(colour, colourSecondary, colourTertiary, s);
				
				return s;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return "";
	}

	public String getPathName() {
		return svgPathInformation.get(0).getPathName();
	}
}
