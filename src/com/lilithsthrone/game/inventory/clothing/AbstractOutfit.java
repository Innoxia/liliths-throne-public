package com.lilithsthrone.game.inventory.clothing;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.controller.xmlParsing.XMLLoadException;
import com.lilithsthrone.controller.xmlParsing.XMLMissingTagException;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.3.1
 * @version 0.3.2
 * @author Innoxia
 */
public abstract class AbstractOutfit {

	// Initially read and stored for quick checking:
	
	private String filePath;
	private String name;
	private String description;
	private List<WorldType> worldTypes;
	private Femininity femininity;
	private List<OutfitType> outfitTypes;
	private List<LegConfiguration> acceptableLegConfigurations;
	private String conditional;
	private int weight;
	
	// For use when reading:
	
	private List<String> innerConditionals;
	private List<List<Colour>> presetColourGroups;
	
	public AbstractOutfit(File outfitXMLFile) throws XMLLoadException {
		try {
			this.filePath =  outfitXMLFile.getAbsolutePath();
			
			Element outfitElement = Element.getDocumentRootElement(outfitXMLFile); // Loads the document and returns the root element - in outfit mods it's <outfit>
			Element coreAttributes = outfitElement.getMandatoryFirstOf("coreAttributes");
			
			this.name =        	coreAttributes.getMandatoryFirstOf("name").getTextContent();
			this.description = 	coreAttributes.getMandatoryFirstOf("description").getTextContent();
			this.femininity = 	Femininity.valueOf(coreAttributes.getMandatoryFirstOf("femininity").getTextContent());
			this.conditional = 	coreAttributes.getMandatoryFirstOf("conditional").getTextContent();
			this.weight = 		Integer.valueOf(coreAttributes.getMandatoryFirstOf("weight").getTextContent());
			
			if(coreAttributes.getOptionalFirstOf("worldTypes").isPresent()) {
				this.worldTypes = coreAttributes
					.getMandatoryFirstOf("worldTypes") 
					.getAllOf("world") // Get all child elements with this tag (checking only contents of parent element) and return them as List<Element>
					.stream() // Convert this list to Stream<Element>, which lets us do some nifty operations on every element at once
					.map( e -> WorldType.valueOf(e.getTextContent())) // Take every element and do something with them, return a Stream of results after this action. Here we load outfit types and get Stream<OutfitType>
					.filter(Objects::nonNull) // Ensure that we only add non-null effects
					.collect(Collectors.toList()); // Collect stream back into a list, but this time we get List<OutfitType> we need! 
			}
			
			this.outfitTypes = coreAttributes
				.getMandatoryFirstOf("outfitTypes") 
				.getAllOf("type") // Get all child elements with this tag (checking only contents of parent element) and return them as List<Element>
				.stream() // Convert this list to Stream<Element>, which lets us do some nifty operations on every element at once
				.map( e -> OutfitType.valueOf(e.getTextContent())) // Take every element and do something with them, return a Stream of results after this action. Here we load outfit types and get Stream<OutfitType>
				.filter(Objects::nonNull) // Ensure that we only add non-null effects
				.collect(Collectors.toList()); // Collect stream back into a list, but this time we get List<OutfitType> we need! 
			
			// Same as above, but now with leg configurations:
			try {
				this.acceptableLegConfigurations = coreAttributes
						.getMandatoryFirstOf("acceptableLegConfigurations") 
						.getAllOf("legConfiguration")
						.stream()
						.map( e -> LegConfiguration.valueOf(e.getTextContent()))
						.filter(Objects::nonNull)
						.collect(Collectors.toList());
			} catch(Exception ex) {
				this.acceptableLegConfigurations = null;
			}
			
		} catch(XMLMissingTagException ex){
			throw new XMLLoadException(ex, outfitXMLFile);
			
		} catch(Exception e){
			System.out.println(e);
			throw new XMLLoadException(e, outfitXMLFile);
		}
	}
	
	public boolean isAvailableForCharacter(OutfitType type, GameCharacter character) {
		switch(this.getFemininity()) {
			case FEMININE:
			case FEMININE_STRONG:
				if(character.isFeminine()
						?character.hasFetish(Fetish.FETISH_CROSS_DRESSER)
						:!character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
					return false;
				}
				break;
			case ANDROGYNOUS:
				break;
			case MASCULINE:
			case MASCULINE_STRONG:
				if(character.isFeminine()
						?!character.hasFetish(Fetish.FETISH_CROSS_DRESSER)
						:character.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
					return false;
				}
				break;
		}
		
		if(this.getWorldTypes()!=null && !this.getWorldTypes().contains(character.getWorldLocation())) {
			return false;
		}
		
		if(!this.getOutfitTypes().contains(type)) {
			return false;
		}
		
		if(this.getAcceptableLegConfigurations()!=null
				&& !this.getAcceptableLegConfigurations().isEmpty()
				&& !this.getAcceptableLegConfigurations().contains(character.getLegConfiguration())) {
			return false;
		}
		
		return conditional.isEmpty() || evalConditional(character, conditional);
	}
	
	/**
	 * @param character The character to which this outfit should be applied.
	 * @param stripCharacterBeforehand Pass in true if you want the character to be stripped. Should probably usually be true.
	 * @param addWeapons true if you want weapons to be added from this outfit.
	 * @param addScarsAndTattoos true if you want scars and tattoos to be added from this outfit. TODO
	 * @param addAccessories true if you want non-core clothing to be added from this outfit.
	 * @return A description of all the items added.
	 * @throws XMLLoadException
	 */
	@SuppressWarnings("deprecation")
	public String applyOutfit(GameCharacter character, List<EquipClothingSetting> settings) throws XMLLoadException {
		StringBuilder sb = new StringBuilder();
		
		boolean debug = false;
		
		File outfitXMLFile = new File(filePath);
		
		if(outfitXMLFile.exists()) {
			try {
				if(settings.contains(EquipClothingSetting.REPLACE_CLOTHING)) {
					character.unequipAllClothingIntoVoid(settings.contains(EquipClothingSetting.REMOVE_SEALS), false);
				}
				
				Element outfitElement = Element.getDocumentRootElement(outfitXMLFile); // Loads the document and returns the root element - in outfit mods it's <outfit>
				Element generationAttributes = outfitElement.getMandatoryFirstOf("generationAttributes");
				
				innerConditionals = new ArrayList<>();
				try {
					for(int i=1; i<20; i++) {
						Element innerConditional = generationAttributes.getMandatoryFirstOf("clothingConditional"+i);
						if(Boolean.valueOf(innerConditional.getAttribute("constant"))) {
							innerConditionals.add(String.valueOf(evalConditional(character, innerConditional.getTextContent())));
						} else {
							innerConditionals.add(innerConditional.getTextContent());
						}
					}
				} catch(Exception ex) {
					// Just catch and continue when there are no more clothingConditional elements.
				}
				
				if(debug) {
					System.out.println("1");
				}
				
				presetColourGroups = new ArrayList<>();
				try {
					for(int i=1; i<20; i++) {
						Element presetColourGroup = generationAttributes.getMandatoryFirstOf("presetColourGroup"+i);
						List<Colour> randomColours = new ArrayList<>();

						if(!presetColourGroup.getAttribute("values").isEmpty()) {
							randomColours.addAll(ColourListPresets.getColourListFromId(presetColourGroup.getAttribute("values")));
							
						} else {
							for(Element e : presetColourGroup.getAllOf("randomColour")) {
								try {
									String text = e.getTextContent();
									if(text.startsWith("presetColourGroup")) {
										randomColours.addAll(presetColourGroups.get(Integer.valueOf(text.substring(text.length()-1))-1));
									} else {
										randomColours.add(Colour.valueOf(text));
									}
								} catch(Exception ex) {
									// Just catch and continue when there are no more clothingConditional elements.
								}
							}
						}
						if(Boolean.valueOf(presetColourGroup.getAttribute("singleColour"))) {
							Collections.shuffle(randomColours);
							randomColours.subList(1, randomColours.size()).clear();
						}
						presetColourGroups.add(randomColours);
					}
				} catch(Exception ex) {
					// Just catch and continue when there are no more clothingConditional elements.
				}

				if(debug) {
					System.out.println("2a");
				}
				
				
				// Main weapon:
				if(settings.contains(EquipClothingSetting.ADD_WEAPONS)) {
					try {
						List<AbstractWeapon> weapons = new ArrayList<>();
						for(Element e : generationAttributes.getMandatoryFirstOf("mainWeapons").getAllOf("weapon")) {
							try {
								String weaponConditional = e.getMandatoryFirstOf("conditional").getTextContent();
								if(!weaponConditional.isEmpty() && !evalConditional(character, weaponConditional)) {
									continue;
								}
							} catch(Exception ex) {
							}
							weapons.add(getWeapon(e));
						}
						if(!weapons.isEmpty()) {
							character.equipMainWeaponFromNowhere(Util.randomItemFrom(weapons));
						}
					} catch(Exception e){
						e.printStackTrace();
					}
					
					// Offhand weapon:
					try {
						List<AbstractWeapon> weapons = new ArrayList<>();
						for(Element e : generationAttributes.getMandatoryFirstOf("offhandWeapons").getAllOf("weapon")) {
							try {
								String weaponConditional = e.getMandatoryFirstOf("conditional").getTextContent();
								if(!weaponConditional.isEmpty() && !evalConditional(character, weaponConditional)) {
									continue;
								}
							} catch(Exception ex) {
							}
							weapons.add(getWeapon(e));
						}
						if(!weapons.isEmpty()) {
							character.equipOffhandWeaponFromNowhere(Util.randomItemFrom(weapons));
						}
					} catch(Exception e){
						e.printStackTrace();
					}
				}
				
				if(debug) {
					System.out.println("2b");
				}
				
				if(generationAttributes.getOptionalFirstOf("guaranteedClothingEquips").isPresent()) {
					List<AbstractClothing> guaranteedClothingEquips = new ArrayList<>();
					
					guaranteedClothingEquips = generationAttributes
							.getMandatoryFirstOf("guaranteedClothingEquips")
							.getAllOf("uniqueClothing")
							.stream()
							.map( e -> {
								try {
									AbstractClothing ac = AbstractClothing.loadFromXML(e.getMandatoryFirstOf("clothing").getInnerElement(), e.getDocument());
									
									try {
										UtilText.setClothingTypeForParsing(ac.getClothingType());
										String conditional = e.getMandatoryFirstOf("conditional").getTextContent();
										if(!evalConditional(character, conditional)) {
											return null;
										}
									} catch (XMLMissingTagException e1) {
									}
									
									String colourText = e.getAttribute("colour");
									if(colourText.startsWith("presetColourGroup")) {
										int index = Integer.valueOf(colourText.substring(colourText.length()-1))-1;
										List<Colour> colours = presetColourGroups.get(index);
										ac.setColour(Util.randomItemFrom(colours));
									}
	
									colourText = e.getAttribute("colourSecondary");
									if(colourText.startsWith("presetColourGroup")) {
										int index = Integer.valueOf(colourText.substring(colourText.length()-1))-1;
										List<Colour> colours = presetColourGroups.get(index);
										ac.setSecondaryColour(Util.randomItemFrom(colours));
									}
	
									colourText = e.getAttribute("colourTertiary");
									if(colourText.startsWith("presetColourGroup")) {
										int index = Integer.valueOf(colourText.substring(colourText.length()-1))-1;
										List<Colour> colours = presetColourGroups.get(index);
										ac.setTertiaryColour(Util.randomItemFrom(colours));
									}
									
									return ac;
								} catch (XMLMissingTagException e1) {
									e1.printStackTrace();
									System.err.println("Error in guaranteedClothingEquips()");
									return null;
								}
							})
							.filter(Objects::nonNull)
							.collect(Collectors.toList());
					
					if(debug) {
						System.out.println("3");
					}
					
					Collections.shuffle(guaranteedClothingEquips);
					for(AbstractClothing c : guaranteedClothingEquips) {
						if(c.getClothingType().getEquipSlots().get(0).isCoreClothing() || settings.contains(EquipClothingSetting.ADD_ACCESSORIES)) {
							c.setName(UtilText.parse(character, c.getName()));
							character.equipClothingOverride(c, c.getClothingType().getEquipSlots().get(0), false, false);
						}
					}
				}
				
				if(debug) {
					System.out.println("4");
				}
				
				List<OutfitPotential> outfitPotentials = new ArrayList<>();
				
				for(Element genericClothingType : generationAttributes.getAllOf("genericClothingType")) {
					List<AbstractClothingType> ctList = new ArrayList<>();
					
					boolean anyConditionalsFound = false;
					
					for(AbstractClothingType ct : ClothingType.getAllClothing()) {
						// Check for required tags:
						try {
							if(genericClothingType.getOptionalFirstOf("itemTags").isPresent()) {
								anyConditionalsFound = true;
								List<ItemTag> tags = genericClothingType.getMandatoryFirstOf("itemTags")
										.getAllOf("tag") 
										.stream()
										.map( e -> ItemTag.valueOf(e.getTextContent()))
										.filter(Objects::nonNull)
										.collect(Collectors.toList());
								if(!ct.getItemTags().containsAll(tags)) {
									continue;
								}
							}
						}catch(Exception ex) {
							System.err.println("genericClothingType error: itemTags");
						}

						// Check femininity:
						try {
							if(genericClothingType.getOptionalFirstOf("acceptableFemininities").isPresent()
									&& genericClothingType.getMandatoryFirstOf("acceptableFemininities").getOptionalFirstOf("femininity").isPresent()) {
								anyConditionalsFound = true;

								List<Femininity> femininities = genericClothingType.getMandatoryFirstOf("acceptableFemininities")
										.getAllOf("femininity") 
										.stream()
										.map( e -> {
											return Femininity.valueOf(e.getTextContent());
										})
										.filter(Objects::nonNull)
										.collect(Collectors.toList());
								
								if(ct.getFemininityRestriction()!=null) {
									switch(ct.getFemininityRestriction()) {
										case FEMININE:
										case FEMININE_STRONG:
											if(!femininities.contains(Femininity.FEMININE)
													&& !femininities.contains(Femininity.FEMININE_STRONG)) {
												continue;
											}
											break;
										case ANDROGYNOUS:
											if(!femininities.contains(Femininity.ANDROGYNOUS)) {
												continue;
											}
											break;
										case MASCULINE:
										case MASCULINE_STRONG:
											if(!femininities.contains(Femininity.MASCULINE)
													&& !femininities.contains(Femininity.MASCULINE_STRONG)) {
												continue;
											}
											break;
									}
								} else {
									if(!femininities.contains(Femininity.ANDROGYNOUS)) {
										continue;
									}
								}
							}
						} catch(Exception ex) {
							ex.printStackTrace();
							System.err.println("genericClothingType error: femininity");
						}

						// Check slot:
						try {
							if(genericClothingType.getOptionalFirstOf("slot").isPresent()
									&& !genericClothingType.getMandatoryFirstOf("slot").getTextContent().isEmpty()) {
								anyConditionalsFound = true;
								
								InventorySlot slot = InventorySlot.valueOf(genericClothingType.getMandatoryFirstOf("slot").getTextContent());
								if(ct.getEquipSlots().get(0)!=slot) {
									continue;
								}
							}
						} catch(Exception ex) {
							System.err.println("genericClothingType error: slot");
						}

						// Check rarity:
						try {
							if(genericClothingType.getOptionalFirstOf("rarity").isPresent()) {
								anyConditionalsFound = true;
								
								Rarity rarity = Rarity.valueOf(genericClothingType.getMandatoryFirstOf("rarity").getTextContent());
								if(ct.getRarity()!=rarity) {
									continue;
								}
							}
						} catch(Exception ex) {
							System.err.println("genericClothingType error: rarity");
						}
						
						// Check conditional:
						try {
							UtilText.setClothingTypeForParsing(ct);
							if(genericClothingType.getOptionalFirstOf("conditional").isPresent()) {
								anyConditionalsFound = true;
								
								String genericClothingConditional = genericClothingType.getMandatoryFirstOf("conditional").getTextContent();
	
								if(!genericClothingConditional.isEmpty() && !evalConditional(character, genericClothingConditional)) {
									continue;
								}
							}
						} catch(Exception ex) {
							System.err.println("genericClothingType error: conditional");
						}
						
						if(!anyConditionalsFound) {
							break;
						}
						if(ct.isCanBeEquipped(character, ct.getEquipSlots().get(0))) {
							ctList.add(ct);
						}
					}
					
					outfitPotentials.add(getOutfitPotential(ctList, genericClothingType));
				}

				if(debug) {
					System.out.println("5");
				}
				
				
				// Add clothing types:

				for(Element clothingTypeElement : generationAttributes.getAllOf("clothingType")) {
					List<AbstractClothingType> clothingTypeList = clothingTypeElement
							.getMandatoryFirstOf("types")
							.getAllOf("type")
							.stream()
							.map( e -> {
								AbstractClothingType ct = ClothingType.getClothingTypeFromId(e.getTextContent());
								if(!ct.isCanBeEquipped(character, ct.getEquipSlots().get(0))) {
									return null;
								}
								return ct;
							})
							.filter(Objects::nonNull)
							.collect(Collectors.toList());
					
					// Check conditional:
					for(AbstractClothingType ct : new ArrayList<>(clothingTypeList)) {
						try {
							UtilText.setClothingTypeForParsing(ct);
							String clothingConditional = clothingTypeElement.getMandatoryFirstOf("conditional").getTextContent();
							
							if(!clothingConditional.isEmpty() && !evalConditional(character, clothingConditional)) {
								clothingTypeList.remove(ct);
							}
						} catch(Exception ex) {
							ex.printStackTrace();
						}
					}
					if(!clothingTypeList.isEmpty()) {
						outfitPotentials.add(getOutfitPotential(clothingTypeList, clothingTypeElement));
					}
				}
				
				if(debug) {
					System.out.println("6");
				}
				
				// Add clothing from all potential outfit entries:
				Collections.shuffle(outfitPotentials);
				for(OutfitPotential ot : outfitPotentials) {
					Collections.shuffle(ot.getTypes());
					for(AbstractClothingType ct : ot.getTypes()) {
						if(character.getClothingInSlot(ct.getEquipSlots().get(0))==null
								&& (ct.getEquipSlots().get(0).isCoreClothing() || settings.contains(EquipClothingSetting.ADD_ACCESSORIES))) {
							AbstractClothing clothing = AbstractClothingType.generateClothing(
									ct,
									ot.getPrimaryColours().isEmpty()?null:Util.randomItemFrom(ot.getPrimaryColours()),
									ot.getSecondaryColours().isEmpty()?null:Util.randomItemFrom(ot.getSecondaryColours()),
									ot.getTertiaryColours().isEmpty()?null:Util.randomItemFrom(ot.getTertiaryColours()), false);
							
							character.equipClothingOverride(
									clothing,
									ct.getEquipSlots().get(0),
									false,
									false);
							
							// Patterns are set when the clothing is created, so this was only used for testing. I've commented it out instead of deleting it as I may need it fo further testing use.
//							if(clothing.getClothingType().isPatternAvailable()) {
//								clothing.setPattern(Util.randomItemFrom(new ArrayList<>(Pattern.getAllDefaultPatterns().values())).getName());
//								clothing.setPatternColour(clothing.getColour());
//								clothing.setPatternSecondaryColour(clothing.getSecondaryColour());
//								clothing.setPatternTertiaryColour(clothing.getPatternTertiaryColour());
//							}
						}
					}
				}
				
				
			} catch(Exception e){
				System.out.println(e);
				throw new XMLLoadException(e, outfitXMLFile);
			}
		}
		
		return sb.toString();
	}
	
	private boolean evalConditional(GameCharacter character, String conditional) {
		if(innerConditionals!=null) {
			for(int i=1; i<=innerConditionals.size(); i++) {
				conditional = conditional.replaceAll("clothingConditional"+String.valueOf(i), innerConditionals.get(i-1));
			}
		}
		try {
			return Boolean.valueOf(UtilText.parse(character, ("[#"+conditional+"]").replaceAll("\u200b", "")));
		} catch(Exception ex){
			System.err.println("error in AbstractOutfit evalConditional(): cannot parse to Boolean");
			return false;
		}
	}
	
	private OutfitPotential getOutfitPotential(List<AbstractClothingType> ctList, Element baseElement) {
		List<Colour> primaryColours = new ArrayList<>();
		try {
			if(baseElement.getOptionalFirstOf("primaryColours").isPresent()) {
				if(!baseElement.getMandatoryFirstOf("primaryColours").getAttribute("values").isEmpty()) {
					primaryColours.addAll(ColourListPresets.getColourListFromId(baseElement.getMandatoryFirstOf("primaryColours").getAttribute("values")));
					
				} else {
					for(Element colour : baseElement.getMandatoryFirstOf("primaryColours").getAllOf("colour")) {
						String text = colour.getTextContent();
						if(text.startsWith("presetColourGroup")) {
							primaryColours.addAll(presetColourGroups.get(Integer.valueOf(text.substring(text.length()-1))-1));
						} else {
							primaryColours.add(Colour.valueOf(text));
						}
					}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("AbstractOutfit error: primary fail 1");
		}
		
		List<Colour> secondaryColours = new ArrayList<>();
		try {
			if(baseElement.getOptionalFirstOf("secondaryColours").isPresent()) {
				if(!baseElement.getMandatoryFirstOf("secondaryColours").getAttribute("values").isEmpty()) {
					primaryColours.addAll(ColourListPresets.getColourListFromId(baseElement.getMandatoryFirstOf("secondaryColours").getAttribute("values")));
					
				} else {
					for(Element colour : baseElement.getMandatoryFirstOf("secondaryColours").getAllOf("colour")) {
						String text = colour.getTextContent();
						if(text.startsWith("presetColourGroup")) {
							secondaryColours.addAll(presetColourGroups.get(Integer.valueOf(text.substring(text.length()-1))-1));
						} else {
							secondaryColours.add(Colour.valueOf(text));
						}
					}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("AbstractOutfit error: secondary fail 1");
		}
		
		List<Colour> tertiaryColours = new ArrayList<>();
		try {
			if(baseElement.getOptionalFirstOf("tertiaryColours").isPresent()) {
				if(!baseElement.getMandatoryFirstOf("tertiaryColours").getAttribute("values").isEmpty()) {
					primaryColours.addAll(ColourListPresets.getColourListFromId(baseElement.getMandatoryFirstOf("tertiaryColours").getAttribute("values")));
					
				} else {
					for(Element colour : baseElement.getMandatoryFirstOf("tertiaryColours").getAllOf("colour")) {
						String text = colour.getTextContent();
						if(text.startsWith("presetColourGroup")) {
							tertiaryColours.addAll(presetColourGroups.get(Integer.valueOf(text.substring(text.length()-1))-1));
						} else {
							tertiaryColours.add(Colour.valueOf(text));
						}
					}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.err.println("AbstractOutfit error: tertiary fail 1");
		}
		
		return new OutfitPotential(ctList, primaryColours, secondaryColours, tertiaryColours);
	}
	
	private AbstractWeapon getWeapon(Element e) {
		try {
			AbstractWeaponType wt;
			List<DamageType> dtList;
			DamageType dt = null;
			
			wt = WeaponType.getWeaponTypeFromId(e.getMandatoryFirstOf("type").getTextContent());
			
			dtList = e
					.getMandatoryFirstOf("damageTypes") 
					.getAllOf("damage")
					.stream()
					.map( element -> DamageType.valueOf(element.getTextContent()))
					.filter(Objects::nonNull)
					.collect(Collectors.toList());
			
			if(!dtList.isEmpty()) {
				dt = Util.randomItemFrom(dtList);
			}
			
			List<Colour> primaryColours = new ArrayList<>();
			try {
				for(Element colour : e.getMandatoryFirstOf("primaryColours").getAllOf("colour")) {
					String text = colour.getTextContent();
					if(text.startsWith("presetColourGroup")) {
						primaryColours.addAll(presetColourGroups.get(Integer.valueOf(text.substring(text.length()-1))-1));
					} else {
						primaryColours.add(Colour.valueOf(text));
					}
				}
			} catch(Exception ex) {
				System.err.println("AbstractOutfit error: main weapon primary fail 1");
			}
			
			List<Colour> secondaryColours = new ArrayList<>();
			try {
				for(Element colour : e.getMandatoryFirstOf("secondaryColours").getAllOf("colour")) {
					String text = colour.getTextContent();
					if(text.startsWith("presetColourGroup")) {
						secondaryColours.addAll(presetColourGroups.get(Integer.valueOf(text.substring(text.length()-1))-1));
					} else {
						secondaryColours.add(Colour.valueOf(text));
					}
				}
			} catch(Exception ex) {
				System.err.println("AbstractOutfit error: main weapon secondary fail 1");
			}
			
			AbstractWeapon weapon;
			if(dt!=null) {
				weapon = AbstractWeaponType.generateWeapon(wt, dt);
			} else {
				weapon = AbstractWeaponType.generateWeapon(wt);
			}
			
			if(!primaryColours.isEmpty()) {
				weapon.setPrimaryColour(Util.randomItemFrom(primaryColours));
			}
			
			if(!secondaryColours.isEmpty()) {
				weapon.setSecondaryColour(Util.randomItemFrom(secondaryColours));
			}
			
			return weapon;

		} catch (XMLMissingTagException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Femininity getFemininity() {
		return femininity;
	}

	public List<WorldType> getWorldTypes() {
		return worldTypes;
	}

	public List<OutfitType> getOutfitTypes() {
		return outfitTypes;
	}

	public List<LegConfiguration> getAcceptableLegConfigurations() {
		return acceptableLegConfigurations;
	}

	public String getConditional() {
		return conditional;
	}

	public int getWeight() {
		return weight;
	}
}
