package com.lilithsthrone.game.character.markings;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.main.Main;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreType;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.enchanting.AbstractItemEffectType;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
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
	
	private String pathName;
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
		
		this.pathName = pathName;
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
				Document doc = Main.getDocBuilder().parse(tattooXMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element clothingElement = (Element) doc.getElementsByTagName("tattoo").item(0);
				
				Element coreAttributes;
				if(clothingElement.getElementsByTagName("coreAtributes").getLength()>0) {
					coreAttributes = (Element) clothingElement.getElementsByTagName("coreAtributes").item(0); // Support for old versions
				} else {
					coreAttributes = (Element) clothingElement.getElementsByTagName("coreAttributes").item(0); // Fix typo
				}
				
				List<InventorySlot> slotAvailability = new ArrayList<>();
				NodeList slotAvailabilityNodeList = ((Element)coreAttributes.getElementsByTagName("slotAvailability").item(0)).getElementsByTagName("slot");
				try {
					for(int i = 0; i < slotAvailabilityNodeList.getLength(); i++){
						Element e = ((Element)slotAvailabilityNodeList.item(i));
						slotAvailability.add(InventorySlot.valueOf(e.getTextContent()));
					}
					if (slotAvailability.isEmpty()) {
						slotAvailability = standardInventorySlots;
					}
				} catch(Exception ex) {
					System.err.println("AbstractTattooType loading failed. Cause: 'slotAvailability' element unable to be parsed. (" + tattooXMLFile.getName() + ")\n" + ex);
				}
				this.slotAvailability = slotAvailability;
				
				this.isMod = true;
				
				this.value = Integer.valueOf(coreAttributes.getElementsByTagName("value").item(0).getTextContent());
				this.pathName = tattooXMLFile.getParentFile().getAbsolutePath() + "/" + coreAttributes.getElementsByTagName("imageName").item(0).getTextContent();
				this.name = coreAttributes.getElementsByTagName("name").item(0).getTextContent();
				this.description = coreAttributes.getElementsByTagName("description").item(0).getTextContent();
				
				this.bodyOverviewDescription = "";
				if(coreAttributes.getElementsByTagName("bodyOverviewDescription").item(0)!=null) {
					this.bodyOverviewDescription = coreAttributes.getElementsByTagName("bodyOverviewDescription").item(0).getTextContent();
				}
				
				this.unique = false;
				try {
					this.unique = Boolean.valueOf(coreAttributes.getElementsByTagName("availabilityRequirements").item(0).getAttributes().getNamedItem("unique").getTextContent());
//					System.out.println(this.name+" | "+unique);
				} catch(Exception ex) {
				}
				try {
					this.availabilityRequirements = coreAttributes.getElementsByTagName("availabilityRequirements").item(0).getTextContent();
				} catch(Exception ex) {
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
				
				SVGStringMap = new HashMap<>();

			} catch(Exception ex) {
				System.err.println("TattooType was unable to be loaded from file! (" + tattooXMLFile.getName() + ")\n" + ex);
			}
		}
	}


	private List<Colour> readColoursFromElement(Element coreAttributes, String elementTagName) {
		Element coloursElement = ((Element)coreAttributes.getElementsByTagName(elementTagName).item(0));
		if(coloursElement.getAttribute("values").isEmpty()) {
			NodeList coloursNodeList = coloursElement.getElementsByTagName("colour");
			List<Colour> result = new ArrayList<>(coloursNodeList.getLength());
			for(int i = 0; i < coloursNodeList.getLength(); i++){
				result.add(PresetColour.getColourFromId(((Element)coloursNodeList.item(i)).getTextContent()));
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
	
	public String getSVGImage(GameCharacter character, Colour colour, Colour colourSecondary, Colour colourTertiary) {
		if (!availablePrimaryColours.contains(colour) || pathName==null || pathName.isEmpty()) {
			return "";
		}
		
		String stringFromMap = getSVGStringFromMap(colour, colourSecondary, colourTertiary);
		if (stringFromMap!=null) {
			return stringFromMap;
			
		} else {
			if (availablePrimaryColours.contains(colour)) {
				try {
					InputStream is;
					String s;
					if(isMod) {
						List<String> lines = Files.readAllLines(Paths.get(pathName));
						StringBuilder sb = new StringBuilder();
						for(String line : lines) {
							sb.append(line);
						}
						s = sb.toString();
						
					} else {
						is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/tattoos/" + pathName + ".svg");
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
		}
	
		return "";
	}

	public String getPathName() {
		return pathName;
	}
}
