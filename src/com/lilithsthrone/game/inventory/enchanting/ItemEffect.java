package com.lilithsthrone.game.inventory.enchanting;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.controller.xmlParsing.XMLUtil;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractTailType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.8
 * @version 0.3.3.10
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
	private TFPotency potency;
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
			if((((ItemEffect)o).getItemEffectType()==null && itemEffectType==null
					||((ItemEffect)o).getItemEffectType()!=null && ((ItemEffect)o).getItemEffectType().equals(itemEffectType))
				&& ((ItemEffect)o).getPrimaryModifier() == primaryModifier
				&& ((ItemEffect)o).getSecondaryModifier() == secondaryModifier
				&& ((ItemEffect)o).getPotency() == potency
				&& ((ItemEffect)o).getLimit() == limit){
					return true;
			}
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
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element effect = doc.createElement("effect");
		parentElement.appendChild(effect);

		XMLUtil.addAttribute(doc, effect, "type", ItemEffectType.getIdFromItemEffectType(getItemEffectType()));
		XMLUtil.addAttribute(doc, effect, "mod1", (getPrimaryModifier()==null?"null":getPrimaryModifier().toString()));
		XMLUtil.addAttribute(doc, effect, "mod2", (getSecondaryModifier()==null?"null":getSecondaryModifier().toString()));
		XMLUtil.addAttribute(doc, effect, "potency", (getPotency()==null?"null":getPotency().toString()));
		XMLUtil.addAttribute(doc, effect, "limit", String.valueOf(getLimit()));
		XMLUtil.addAttribute(doc, effect, "timer", String.valueOf(getTimer().getSecondsPassed()));
		
		return effect;
	}
	
	public static ItemEffect loadFromXML(Element parentElement, Document doc) {
		String itemEffectType = parentElement.getAttribute("type");
		if(itemEffectType.isEmpty()) {
			itemEffectType = parentElement.getAttribute("itemEffectType"); // Support for effects prior to 0.3.1.5
		}

		String primaryMod = parentElement.getAttribute("mod1");
		if(primaryMod.isEmpty()) {
			primaryMod = parentElement.getAttribute("primaryModifier"); // Support for effects prior to 0.3.1.5
		}
		
		String secondaryMod = parentElement.getAttribute("mod2");
		if(secondaryMod.isEmpty()) {
			secondaryMod = parentElement.getAttribute("secondaryModifier"); // Support for effects prior to 0.3.1.5
		}
		
//		if(itemEffectType.equals("RACE_DEMON")) {
//			throw new NullPointerException();
//		}
		
		switch(itemEffectType) {
			case "ATTRIBUTE_STRENGTH":
			case "ATTRIBUTE_FITNESS":
				itemEffectType = "ATTRIBUTE_PHYSIQUE";
				break;
			case "ATTRIBUTE_INTELLIGENCE":
				itemEffectType = "ATTRIBUTE_ARCANE";
				break;
		}
		switch(primaryMod) {
			case "DAMAGE_ATTACK":
			case "RESISTANCE_ATTACK":
				return null;
			case "CLOTHING_SEALING":
				primaryMod = "CLOTHING_SPECIAL";
				secondaryMod = "CLOTHING_SEALING";
				break;
			case "CLOTHING_ENSLAVEMENT":
				primaryMod = "CLOTHING_SPECIAL";
				secondaryMod = "CLOTHING_ENSLAVEMENT";
				break;
			case "TF_PENIS":
				if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.5.1")) {
					if(secondaryMod=="TF_MOD_SIZE_SECONDARY") {
						secondaryMod = "TF_MOD_SIZE_TERTIARY";
					}
				}
				break;
		}
		switch(secondaryMod) {
			case "TF_MOD_FETISH_SEEDER":
				secondaryMod = "TF_MOD_FETISH_IMPREGNATION";
				break;
			case "TF_MOD_FETISH_BROODMOTHER":
				secondaryMod = "TF_MOD_FETISH_PREGNANCY";
				break;
			case "CRITICAL_CHANCE":
				secondaryMod = "CRITICAL_DAMAGE";
				break;
			case "CLOTHING_ANTI_SELF_TRANSFORMATION":
				secondaryMod = "CLOTHING_SERVITUDE";
				break;
			case "TF_MOD_ORIFICE_DEEP":
				secondaryMod = "TF_MOD_DEPTH";
				break;
			case "TF_MOD_ORIFICE_DEEP_2":
				secondaryMod = "TF_MOD_DEPTH_2";
				break;
		}
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.4.1") && itemEffectType=="TF_TAIL" && primaryMod=="TF_MOD_SIZE") { // Girth TF was moved from TF_MOD_SIZE to TF_MOD_SIZE_SECONDARY in v0.4
			primaryMod = "TF_MOD_SIZE_SECONDARY";
		}
		
		ItemEffect ie;
		try { // Wrap this in a try, as the TFModifier.valueOf might fail, due to removing Broodmother/Seeder fetish modifiers in 0.2.7.5, and then critical chance in 0.3.3.5.
			TFModifier primary = (primaryMod.equals("null") || primaryMod.isEmpty()?null:TFModifier.valueOf(primaryMod));
			TFModifier secondary = (secondaryMod.equals("null") || secondaryMod.isEmpty()?null:TFModifier.valueOf(secondaryMod));
			
			if(secondary!=null && TFModifier.getWeaponMajorAttributeList().contains(secondary)) {
				primary = TFModifier.CLOTHING_MAJOR_ATTRIBUTE;
			}
			
			// If there is no ItemEffectType with the id type, return null, as otherwise the game tries to load an incorrect ItemEffectType.
			if(!ItemEffectType.idToItemEffectTypeMap.containsKey(itemEffectType)) {
				return null;
			}
			
			ie = new ItemEffect(
					ItemEffectType.getItemEffectTypeFromId(itemEffectType),
					primary,
					secondary,
					(parentElement.getAttribute("potency").equals("null")?null:TFPotency.valueOf(parentElement.getAttribute("potency"))),
					Integer.valueOf(parentElement.getAttribute("limit")));
			
		} catch(Exception ex) {
			System.err.println("(Minor error, can ignore.) Unable to import ItemEffect (" + primaryMod + ", " + secondaryMod + ") from" + doc.getDocumentURI());
			System.err.println(ex);
			return null;
		}
		
		try {
			String timerString = parentElement.getAttribute("timer");
			int time = 0;
			if(!timerString.isEmpty()) {
				time = Integer.valueOf(timerString);
			}
			
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.5")) {// I think this was to align effects to all be on the hour:
				int timer = time/60;
				ie.getTimer().setSecondsPassed(((timer*60) + (int)(Main.game.getMinutesPassed()%60)));
				
			} else if(Main.isVersionOlderThan(Game.loadingVersion, "0.3.0.6")) {
				ie.getTimer().setSecondsPassed(time*60);
				
			} else {
				ie.getTimer().setSecondsPassed(time);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return ie;
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
			
			if(target.isDoll() && itemEffectType.getAssociatedRace()!=null) {
				return UtilText.parse(target, "<p style='text-align:center;'>[style.colourDisabled(As [npc.sheIs] a sex doll, normal transformatives have no effect on [npc.name]...)]</p>");
			}
			
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
						case TF_EYES:
			 				return AbstractItemEffectType.getRacialEffect(Race.DEMON, getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
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
 				return AbstractItemEffectType.getRacialEffect(target.getRace()==Race.SLIME?target.getFleshSubspecies().getRace():target.getRace(), getPrimaryModifier(), secondaryMod, getPotency(), user, target).applyEffect();
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
