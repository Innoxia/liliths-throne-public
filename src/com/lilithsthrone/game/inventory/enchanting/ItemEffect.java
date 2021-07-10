package com.lilithsthrone.game.inventory.enchanting;

import java.util.List;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.8
 * @version 0.4.1
 * @author Innoxia
 */
public class ItemEffect implements XMLSaving {

	public static final int SEALED_COST_MINOR_BOOST = 5;
	public static final int SEALED_COST_MINOR_DRAIN = 25;
	public static final int SEALED_COST_DRAIN = 100;
	public static final int SEALED_COST_MAJOR_DRAIN = 500;
	
	private AbstractItemEffectType itemEffectType;
	private TFModifier primaryModifier;
	private TFModifier secondaryModifier;
	private final TFPotency potency;
	private int limit;
	private ItemEffectTimer timer;
	
	public ItemEffect(AbstractItemEffectType itemEffectType) {
		this.itemEffectType = itemEffectType;
		this.primaryModifier = null;
		this.secondaryModifier = null;
		this.potency = null;
		this.limit = 0;
		this.timer = new ItemEffectTimer();
	}
	
	public ItemEffect(AbstractItemEffectType itemEffectType, TFModifier primaryModifier, TFModifier secondaryModifier, TFPotency potency, int limit) {
		this.itemEffectType = itemEffectType;
		this.primaryModifier = primaryModifier;
		this.secondaryModifier = secondaryModifier;
		this.potency = potency;
		this.limit = limit;
		this.timer = new ItemEffectTimer();
	}
	
	public String getId() {
		return (itemEffectType==null?"n":itemEffectType.toString())
				+ (primaryModifier==null?"n":primaryModifier.toString())
				+ (secondaryModifier==null?"n":secondaryModifier.toString())
				+ (potency==null?"n":potency.toString())
				+ limit;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ItemEffect){
			return (((ItemEffect) o).getItemEffectType() == null && itemEffectType == null
					|| ((ItemEffect) o).getItemEffectType() != null && ((ItemEffect) o).getItemEffectType().equals(itemEffectType))
					&& ((ItemEffect) o).getPrimaryModifier() == primaryModifier
					&& ((ItemEffect) o).getSecondaryModifier() == secondaryModifier
					&& ((ItemEffect) o).getPotency() == potency
					&& ((ItemEffect) o).getLimit() == limit;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if(itemEffectType!=null) {
			result = 31 * result + itemEffectType.hashCode();
		}
		if(primaryModifier!=null) {
			result = 31 * result + primaryModifier.hashCode();
		}
		if(secondaryModifier!=null) {
			result = 31 * result + secondaryModifier.hashCode();
		}
		if(potency!=null) {
			result = 31 * result + potency.hashCode();
		}
		result = 31 * result + limit;
		return result;
	}
	
	public boolean saveAsXML(Element parentElement) {
		Element effectElement = parentElement.addElement("effect");
		effectElement.addAttribute("type", ItemEffectType.getIdFromItemEffectType(itemEffectType));
		if(primaryModifier!=null) {
			effectElement.addAttribute("mod1", primaryModifier.toString());
		}
		if(secondaryModifier!=null) {
			effectElement.addAttribute("mod2", secondaryModifier.toString());
		}
		if(potency!=null) {
			effectElement.addAttribute("potency", potency.toString());
		}
		effectElement.addAttribute("limit", String.valueOf(limit));
		effectElement.addAttribute("timer", String.valueOf(timer.getSecondsPassed()));
		return true;
	}
	
	public static ItemEffect loadFromXML(Element parentElement) {
		try {
			Element effectElement = parentElement.getMandatoryFirstOf("effect");
			String itemEffectTypeId = effectElement.getAttribute("type");
			// Check itemEffectType exists before retrieving it
			if(!ItemEffectType.idToItemEffectTypeMap.containsKey(itemEffectTypeId)) {
				System.out.println("Invalid ItemEffect type");
				return null;
			}
			return new ItemEffect(ItemEffectType.getItemEffectTypeFromId(itemEffectTypeId),
					TFModifier.valueOf(effectElement.getAttribute("mod1")),
					TFModifier.valueOf(effectElement.getAttribute("mod2")),
					TFPotency.valueOf(effectElement.getAttribute("potency")),
					Integer.parseInt(effectElement.getAttribute("limit")));
		} catch (Exception ex) {
			System.out.println("Failed to load ItemEffect");
			ex.printStackTrace();
			return null;
		}
	}
	
	//TODO
//	public static List<ItemEffect> groupEffects(List<ItemEffect> effects) {
//		List<ItemEffect> groupedEffects = new ArrayList<>();
//		
//		for(ItemEffect ie : effects) {
//			
//		}
//		
//		return groupedEffects;
//	}
	
	public String applyEffect(GameCharacter user, GameCharacter target, int secondsPassed) {
		this.timer.incrementSecondsPassed(secondsPassed);
		if(target!=null
				&& getItemEffectType()!=ItemEffectType.CLOTHING
				&& getItemEffectType()!=ItemEffectType.TATTOO
				&& getItemEffectType().getAssociatedRace()!=Race.DEMON) { // For debug demon TF options
			if((target.getSubspeciesOverrideRace()==Race.DEMON || (!target.isAbleToHaveRaceTransformed() && target.getRace()!=Race.SLIME))
					&& (getSecondaryModifier()==TFModifier.TF_TYPE_1
							|| getSecondaryModifier()==TFModifier.TF_TYPE_2
							|| getSecondaryModifier()==TFModifier.TF_TYPE_3
							|| getSecondaryModifier()==TFModifier.TF_TYPE_4
							|| getSecondaryModifier()==TFModifier.TF_TYPE_5
							|| getSecondaryModifier()==TFModifier.TF_TYPE_6
							|| getSecondaryModifier()==TFModifier.TF_TYPE_7
							|| getSecondaryModifier()==TFModifier.TF_TYPE_8
							|| getSecondaryModifier()==TFModifier.TF_TYPE_9
							|| getSecondaryModifier()==TFModifier.TF_TYPE_10
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_ARACHNID
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_AVIAN
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAIL
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG
							|| getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAUR
							|| getSecondaryModifier()==TFModifier.REMOVAL)) {
				
				if(getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_ARACHNID && (target.getLegType().isLegConfigurationAvailable(LegConfiguration.ARACHNID) || target.getLegType().getRace()==Race.DEMON)) {
					return AbstractItemEffectType.getRacialEffect(target.getLegType().getRace(), getPrimaryModifier(), getSecondaryModifier(), getPotency(), user, target).applyEffect();
				}
				if(getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_BIPEDAL && (target.getLegType().isLegConfigurationAvailable(LegConfiguration.BIPEDAL) || target.getLegType().getRace()==Race.DEMON)) {
					return AbstractItemEffectType.getRacialEffect(target.getLegType().getRace(), getPrimaryModifier(), getSecondaryModifier(), getPotency(), user, target).applyEffect();
				}
				if(getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_CEPHALOPOD && (target.getLegType().isLegConfigurationAvailable(LegConfiguration.CEPHALOPOD) || target.getLegType().getRace()==Race.DEMON)) {
					return AbstractItemEffectType.getRacialEffect(target.getLegType().getRace(), getPrimaryModifier(), getSecondaryModifier(), getPotency(), user, target).applyEffect();
				}
				if(getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_AVIAN && (target.getLegType().isLegConfigurationAvailable(LegConfiguration.AVIAN) || target.getLegType().getRace()==Race.DEMON)) {
					return AbstractItemEffectType.getRacialEffect(target.getLegType().getRace(), getPrimaryModifier(), getSecondaryModifier(), getPotency(), user, target).applyEffect();
				}
				if(getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAIL && (target.getLegType().isLegConfigurationAvailable(LegConfiguration.TAIL) || target.getLegType().getRace()==Race.DEMON)) {
					return AbstractItemEffectType.getRacialEffect(target.getLegType().getRace(), getPrimaryModifier(), getSecondaryModifier(), getPotency(), user, target).applyEffect();
				}
				if(getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAIL_LONG && (target.getLegType().isLegConfigurationAvailable(LegConfiguration.TAIL_LONG) || target.getLegType().getRace()==Race.DEMON)) {
					return AbstractItemEffectType.getRacialEffect(target.getLegType().getRace(), getPrimaryModifier(), getSecondaryModifier(), getPotency(), user, target).applyEffect();
				}
				if(getSecondaryModifier()==TFModifier.TF_MOD_LEG_CONFIG_TAUR && (target.getLegType().isLegConfigurationAvailable(LegConfiguration.QUADRUPEDAL) || target.getLegType().getRace()==Race.DEMON)) {
					return AbstractItemEffectType.getRacialEffect(target.getLegType().getRace(), getPrimaryModifier(), getSecondaryModifier(), getPotency(), user, target).applyEffect();
				}
				TFModifier secondaryMod = getSecondaryModifier();
				if(secondaryMod==TFModifier.TF_TYPE_2
						|| secondaryMod==TFModifier.TF_TYPE_3
						|| secondaryMod==TFModifier.TF_TYPE_4
						|| secondaryMod==TFModifier.TF_TYPE_5
						|| secondaryMod==TFModifier.TF_TYPE_6
						|| secondaryMod==TFModifier.TF_TYPE_7
						|| secondaryMod==TFModifier.TF_TYPE_8
						|| secondaryMod==TFModifier.TF_TYPE_9
						|| secondaryMod==TFModifier.TF_TYPE_10) {
					secondaryMod = TFModifier.TF_TYPE_1;
				}
				if(target.getSubspeciesOverride()==Subspecies.HALF_DEMON) {
					AbstractSubspecies halfSubspecies = target.getHalfDemonSubspecies();
					switch(getPrimaryModifier()) {
						case TF_EARS:
						case TF_HAIR:
							if(halfSubspecies==Subspecies.HUMAN) {
				 				return AbstractItemEffectType.getRacialEffect(Race.DEMON, getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
							} else {
				 				return AbstractItemEffectType.getRacialEffect(halfSubspecies.getRace(), getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
							}
						case TF_ANTENNA:
						case TF_ARMS:
						case TF_CORE:
						case TF_FACE:
						case TF_LEGS:
						case TF_SKIN:
			 				return AbstractItemEffectType.getRacialEffect(halfSubspecies.getRace(), getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
						case TF_TAIL:
							List<AbstractTailType> tailTypes = RacialBody.valueOfRace(halfSubspecies.getRace()).getTailType();
							if(tailTypes.size()==1 && tailTypes.get(0)==TailType.NONE) {
				 				return AbstractItemEffectType.getRacialEffect(Race.DEMON, getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
							} else {
				 				return AbstractItemEffectType.getRacialEffect(halfSubspecies.getRace(), getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
							}
						case TF_ASS:
						case TF_BREASTS:
						case TF_BREASTS_CROTCH:
						case TF_EYES:
						case TF_HORNS:
						case TF_PENIS:
						case TF_VAGINA:
						case TF_WINGS:
						case TF_TENTACLE:
			 				return AbstractItemEffectType.getRacialEffect(Race.DEMON, getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
						default:
							break;
					}
				}
 				return AbstractItemEffectType.getRacialEffect(target.getRace(), getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
			}
		}
		return getItemEffectType().applyEffect(getPrimaryModifier(), getSecondaryModifier(), getPotency(), getLimit(), user, target, this.timer);
	}
	
	public List<String> getEffectsDescription(GameCharacter user, GameCharacter target) {
		return getItemEffectType().getEffectsDescription(getPrimaryModifier(), getSecondaryModifier(), getPotency(), getLimit(), user, target);
	}
	
	public int getCost() {
		int cost = 1;
		if(getPrimaryModifier()!=null) {
			if(getPrimaryModifier()!=TFModifier.NONE) {
				cost+=getPrimaryModifier().getValue();
			}
		}
		if(getSecondaryModifier()!=null) {
			if(getSecondaryModifier()!=TFModifier.NONE) {
				cost+=getSecondaryModifier().getValue();
			}
		}
		if(potency!=null) {
			cost += potency.getValue();
		}
		if(getLimit() != -1) {
			cost+=1;
		}
		
		return cost;
	}
	
	public AbstractItemEffectType getItemEffectType() {
		return itemEffectType;
	}

	public void setItemEffectType(AbstractItemEffectType itemEffectType) {
		this.itemEffectType = itemEffectType;
	}

	public TFModifier getPrimaryModifier() {
		return primaryModifier;
	}

	public void setPrimaryModifier(TFModifier primaryModifier) {
		this.primaryModifier = primaryModifier;
	}

	public TFModifier getSecondaryModifier() {
		return secondaryModifier;
	}

	public void setSecondaryModifier(TFModifier secondaryModifier) {
		this.secondaryModifier = secondaryModifier;
	}

	public TFPotency getPotency() {
		return potency;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public ItemEffectTimer getTimer() {
		return timer;
	}

	public void setTimer(ItemEffectTimer timer) {
		this.timer = timer;
	}
	
}
