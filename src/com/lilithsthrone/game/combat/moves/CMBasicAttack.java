package com.lilithsthrone.game.combat.moves;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.4
 * @version 0.4
 * @author Irbynx, Innoxia
 */
public class CMBasicAttack {

    public static AbstractCombatMove BASIC_STRIKE = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "strike",
            0,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/strike",
            Util.newArrayListOfValues(PresetColour.BASE_CRIMSON),
            false,
            true,
            false,
            null) {
    	
    	private AbstractWeapon getMainWeapon(int turnIndex, GameCharacter source, int armRow) {
    		AbstractWeapon weapon = source.getMainWeaponArray()[armRow];
    		if(Main.game.isInCombat() && Main.combat.getAllCombatants(true).contains(source)) {
				Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, InventorySlot.mainWeaponSlots[armRow]);
				if(!weaponsThrown.isEmpty()) {
	    			int mainOrDualAttacksPerformed = 0;
	    			int turnCountProgress = 0;
	    			for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
	    				if(turnCountProgress < turnIndex) {
		    				if(move.getValue()==BASIC_STRIKE || move.getValue()==BASIC_TWIN_STRIKE) {
		    					mainOrDualAttacksPerformed++;
		    				}
		    				turnCountProgress++;
	    				} else {
		    				break;
		    			}
	    			}
					AbstractWeapon thrownWeaponType = weaponsThrown.keySet().iterator().next();
	    			if(mainOrDualAttacksPerformed < weaponsThrown.get(thrownWeaponType)) {
	    				weapon = weaponsThrown.keySet().iterator().next();
	    			}
				}
    		}
			return weapon;
    	}
    	
        private int getArcaneCost(int turnIndex, GameCharacter source) {
        	int essenceCost = 0;
        	for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null && weapon.getWeaponType().getArcaneCost()>0) {
    				essenceCost += weapon.getWeaponType().getArcaneCost();
    			}
    		}
        	return essenceCost;
        }
        
        private List<String> getDamageRanges(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            List<String> damages = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamageRange(source, target, weapon.getDamageType(), Attack.MAIN, weapon, isCrit));
    			} else {
    				damages.add(getFormattedDamageRange(source, target, DamageType.UNARMED.getParentDamageType(source, null), Attack.MAIN, null, isCrit));
    			}
    		}
    		return damages;
        }
        
        private List<String> getFormattedDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean damageHasBeenApplied) {
            List<String> damages = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamage(weapon.getDamageType(), weapon.getWeaponType().getDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			} else {
    				damages.add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), source.getUnarmedDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			}
    		}
    		return damages;
        }
        
    	private String getPredictionPrefix(int turnIndex, GameCharacter source, GameCharacter target) {
    		StringBuilder sb = new StringBuilder();
    		int attackCount = Math.min(source.getArmRows(), source.getMainWeaponArray().length);
    		for(int i=0; i<attackCount; i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null || attackCount>1) {
    				if(sb.length()>0) {
    					sb.append("/");
    				}
    				if(weapon!=null) {
    					sb.append(Util.capitaliseSentence(weapon.getWeaponType().getAttackDescriptionPrefix(source, target)));
    				} else {
    					sb.append("Hit");
    				}
    			}
    		}
            if(sb.length()>0) {
            	return sb.toString();
            }
    		return getName(turnIndex, source);
    	}
    	
    	@Override
    	public int getAPcost(GameCharacter source) {
    		return source.getArmRows() + (!source.getEquippedMoves().contains(this)?1:0);
    	}
        
    	@Override
    	public String getName(int turnIndex, GameCharacter source) {
    		StringBuilder sb = new StringBuilder();
    		int attackCount = Math.min(source.getArmRows(), source.getMainWeaponArray().length);
    		for(int i=0; i<attackCount; i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null || attackCount>1) {
    				if(sb.length()>0) {
    					sb.append("/");
    				}
    				if(weapon!=null) {
    					sb.append(Util.capitaliseSentence(weapon.getWeaponType().getAttackDescriptor()));
    				} else {
    					sb.append("Hit");
    				}
    			}
    		}
            if(sb.length()>0) {
            	return sb.toString();
            }
    		return super.getName(turnIndex, source);
    	}
    	
    	@Override
    	public DamageType getDamageType(int turnIndex, GameCharacter source) {
            DamageType damageType = DamageType.UNARMED.getParentDamageType(source, null);
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
                   	damageType = weapon.getDamageType();
                   	break;
    			}
    		}
            return damageType;
    	}
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(true, "Available to everyone as a basic move.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	String costText="";
        	int essenceCost = getArcaneCost(turnIndex, source);
    		if(essenceCost>0) {
    			costText = " Weapon usage costs [style.boldArcane("+essenceCost+" Arcane essence"+(essenceCost>1?"s":"")+")]!";
    		}
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);

            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "<span style='color:"+this.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(getPredictionPrefix(turnIndex, source, target))+"</span> "
        				+ (target==null?"[npc.her] target":"[npc2.name]")
        				+ " with [npc.her] "
        				+(source.getArmRows()==1
	        				?(getMainWeapon(turnIndex, source, 0)==null?"fists":getMainWeapon(turnIndex, source, 0).getName())
	        				:"main weapons")
        				+ " for "
        				+ Util.stringsToStringList(getDamageRanges(turnIndex, source, target, isCrit), false)
        				+ " damage."
        				+ costText);
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
        	String costText="";
        	int essenceCost = getArcaneCost(turnIndex, source);
        	List<String> weaponNames = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				weaponNames.add(weapon.getName());
    			} else {
    				if(!weaponNames.contains("fists")) {
    					weaponNames.add("fists");
    				}
    			}
    		}
    		if(essenceCost>0) {
    			costText = " Weapon usage costs [style.boldArcane("+essenceCost+" Arcane essence"+(essenceCost>1?"s":"")+")]!";
    		}
            return UtilText.parse(source,
            		"Strike [npc.her] target with [npc.her] "
            			+(weaponNames.isEmpty()
            					?"fists"
            					:weaponNames.size()==1
            						?weaponNames.get(0)
            						:"primary weapons")
            			+", dealing base "
        				+ Util.stringsToStringList(getFormattedDamage(turnIndex, source, null, false), false)
            			+ " damage."
            			+ costText);
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);

        	StringBuilder attackStringBuilder = new StringBuilder("");
        	StringBuilder weaponAttacksStringBuilder = new StringBuilder("");
        	Map<GameCharacter, List<String>> weaponDamages = new LinkedHashMap<>();
        	
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
    			AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			
    			if(weapon!=null) {
    				int damage = Attack.calculateDamage(source, target, Attack.MAIN, weapon, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = weapon.getDamageType().damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append((i>0?"<br/>":"")+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, target, true, maxLust));
    				
    				List<GameCharacter> aoeAvailableTargets = new ArrayList<>(enemies);
    				aoeAvailableTargets.remove(target);
    				for(Value<Integer, Integer> aoe :weapon.getWeaponType().getAoeDamage()) {
    					if(aoeAvailableTargets.isEmpty()) {
    						break;
    					}
    					GameCharacter aoeTarget = Util.randomItemFrom(aoeAvailableTargets);
    					if(Math.random()*100<=aoe.getKey()) {
            				maxLust = isTargetAtMaximumLust(target);
            				damage = Attack.calculateDamage(source, target, Attack.MAIN, weapon, aoe.getValue(), isCrit);
            				damageValue = weapon.getDamageType().damageTarget(source, aoeTarget, damage);
            				inflictedDamage = damageValue.getValue();
            				weaponDamages.putIfAbsent(aoeTarget, new ArrayList<>());
            				weaponDamages.get(aoeTarget).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, aoeTarget, true, maxLust));
        					aoeAvailableTargets.remove(aoeTarget);
    					}
    				}
            		
    			} else {
    				int damage = Attack.calculateDamage(source, target, Attack.MAIN, null, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = DamageType.UNARMED.getParentDamageType(source, null).damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append((i>0?"<br/>":"")+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), inflictedDamage, target, true, maxLust));
    			}
    		}
    		
    		if(attackStringBuilder.length()>0) {
    			attackStringBuilder.append("<br/>");
    		}
    		
    		StringBuilder damageApplied = new StringBuilder();
    		for(Entry<GameCharacter, List<String>> entry : weaponDamages.entrySet()) {
    			if(damageApplied.length()>0) {
    				damageApplied.append("<br/>");
    			}
    			damageApplied.append(UtilText.parse(entry.getKey(),
    					(entry.getKey().equals(target)
    							?""
    							:"[style.boldAqua(AoE)]: ")
    						+"[npc.Name] took "+Util.stringsToStringList(entry.getValue(), false)+" damage!"));
    		}
    		attackStringBuilder.append(formatAttackOutcome(source, target,
    				weaponAttacksStringBuilder.toString(),
    				damageApplied.toString(),
    				isCrit?"":null,
    				isCrit?"Extra damage applied!":""));
    		
    		source.incrementEssenceCount(getArcaneCost(turnIndex, source), false); // Hack to restore essences from loss in performOnSelection() method, as they are also subtracted in applyExtraAttackEffects.
    		
    		List<String> extraEffects = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
        		if(weapon != null) {
        			String s = weapon.applyExtraEffects(source, target, true, isCrit);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.MAIN, weapon, true, isCrit));
        		} else {
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.MAIN, weapon, true, isCrit));
        		}
    		}
    		
    		if(!extraEffects.isEmpty()) {
        		attackStringBuilder.append("<div class='container-full-width' style='text-align:center; padding:0; margin:0;'>");
        		for(String s : extraEffects) {
            		attackStringBuilder.append(s);
        		}
        		attackStringBuilder.append("</div>");
    		}
    		
    		return attackStringBuilder.toString();
        }
        
        @Override
        public String isUsable(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	int weaponCount = 0;
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				weaponCount++;
    			}
    		}
    		
        	if(source.getEssenceCount()<essenceCost) {
        		return "You don't have enough arcane essences to use your weapon"+(weaponCount>1?"s":"")+"! ("+Util.capitaliseSentence(Util.intToString(essenceCost))+" "+(essenceCost==1?"is":"are")+" required.)";
        	}
        	return super.isUsable(turnIndex, source, target, enemies, allies);
        }
        
        @Override
        public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	source.incrementEssenceCount(-essenceCost, false);
        	// Thrown weapons:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = source.getMainWeaponArray()[i];
    			if(weapon!=null && weapon.getWeaponType().isOneShot()) {
    				if(source.getWeaponCount(weapon)>=1) {
    					source.removeWeapon(weapon);
    				} else {
    					source.unequipWeaponIntoVoid(InventorySlot.mainWeaponSlots[i], weapon, true);
    					Main.combat.addThrownWeaponsDepleted(source, InventorySlot.mainWeaponSlots[i], weapon.getWeaponType());
    				}
    				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.mainWeaponSlots[i], weapon, 1);
    				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.mainWeaponSlots[i], weapon, 1);
    			}
    		}
        }
        
        @Override
        public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	source.incrementEssenceCount(essenceCost, false);
        	// Thrown weapons:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		InventorySlot slot = InventorySlot.mainWeaponSlots[i];
        		Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, slot);
        		if(weaponsThrown!=null) {
        			for(Entry<AbstractWeapon, Integer> entry : weaponsThrown.entrySet()) {
                		AbstractWeapon weapon = entry.getKey();
            			int thrownCount = entry.getValue();
        				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.mainWeaponSlots[i], weapon, -thrownCount);
        				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.mainWeaponSlots[i], weapon, -thrownCount);
            			if(source.getMainWeapon(i)==null) {
            				thrownCount--;
            				source.equipMainWeapon(weapon, i, false);
        					Main.combat.removeThrownWeaponsDepleted(source, slot);
        					
            			} else {
	            			for(int c=0; c<thrownCount; c++) {
	            				source.addWeapon(weapon, 1, false, false);
	            			}
            			}
        			}
        		}
        	}
        }
    };
    
    public static AbstractCombatMove BASIC_OFFHAND_STRIKE = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "offhand strike",
            0,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/strike_offhand",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
            null) {

    	private AbstractWeapon getOffhandWeapon(int turnIndex, GameCharacter source, int armRow) {
    		AbstractWeapon weapon = source.getOffhandWeaponArray()[armRow];
    		if(Main.game.isInCombat() && Main.combat.getAllCombatants(true).contains(source)) {
				Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, InventorySlot.offhandWeaponSlots[armRow]);
				if(!weaponsThrown.isEmpty()) {
	    			int offhandOrDualAttacksPerformed = 0;
	    			int turnCountProgress = 0;
	    			for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
	    				if(turnCountProgress < turnIndex) {
		    				if(move.getValue()==BASIC_OFFHAND_STRIKE || move.getValue()==BASIC_TWIN_STRIKE) {
		    					offhandOrDualAttacksPerformed++;
		    				}
		    				turnCountProgress++;
	    				} else {
		    				break;
		    			}
	    			}
					AbstractWeapon thrownWeaponType = weaponsThrown.keySet().iterator().next();
	    			if(offhandOrDualAttacksPerformed < weaponsThrown.get(thrownWeaponType)) {
	    				weapon = weaponsThrown.keySet().iterator().next();
	    			}
				}
    		}
			return weapon;
    	}
    	
        private int getArcaneCost(int turnIndex, GameCharacter source) {
        	int essenceCost = 0;
        	for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null && weapon.getWeaponType().getArcaneCost()>0) {
    				essenceCost += weapon.getWeaponType().getArcaneCost();
    			}
    		}
        	return essenceCost;
        }
        
        private List<String> getDamageRanges(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            List<String> damages = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamageRange(source, target, weapon.getDamageType(), Attack.OFFHAND, weapon, isCrit));
    			} else {
    				damages.add(getFormattedDamageRange(source, target, DamageType.UNARMED.getParentDamageType(source, null), Attack.OFFHAND, null, isCrit));
    			}
    		}
    		return damages;
        }
        
        private List<String> getFormattedDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean damageHasBeenApplied) {
            List<String> damages = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamage(weapon.getDamageType(), weapon.getWeaponType().getDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			} else {
    				damages.add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), source.getUnarmedDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			}
    		}
    		return damages;
        }
        
    	private String getPredictionPrefix(int turnIndex, GameCharacter source, GameCharacter target) {
    		StringBuilder sb = new StringBuilder();
    		int attackCount = Math.min(source.getArmRows(), source.getOffhandWeaponArray().length);
    		for(int i=0; i<attackCount; i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null || attackCount>1) {
    				if(sb.length()>0) {
    					sb.append("/");
    				}
    				if(weapon!=null) {
    					sb.append(Util.capitaliseSentence(weapon.getWeaponType().getAttackDescriptionPrefix(source, target)));
    				} else {
    					sb.append("Hit");
    				}
    			}
    		}
            if(sb.length()>0) {
            	return sb.toString();
            }
    		return getName(turnIndex, source);
    	}
    	
    	@Override
        public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	float weight = super.getWeight(source, enemies, allies);
        	if(!source.hasPerkAnywhereInTree(Perk.UNARMED_TRAINING)
        			&& source.getEquippedMoves().contains(CombatMove.getCombatMoveFromId("strike"))
        			&& source.getMainWeapon(0)!=null
        			&& source.getOffhandWeapon(0)==null) {
        		weight *= 0.1f;
        	}
        	return weight;
        }
        
    	@Override
    	public int getAPcost(GameCharacter source) {
    		return source.getArmRows() + (!source.getEquippedMoves().contains(this)?1:0);
    	}
        
    	@Override
    	public String getName(int turnIndex, GameCharacter source) {
    		StringBuilder sb = new StringBuilder();
    		int attackCount = Math.min(source.getArmRows(), source.getOffhandWeaponArray().length);
    		for(int i=0; i<attackCount; i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null || attackCount>1) {
    				if(sb.length()>0) {
    					sb.append("/");
    				}
    				if(weapon!=null) {
    					sb.append(Util.capitaliseSentence(weapon.getWeaponType().getAttackDescriptor()));
    				} else {
    					sb.append("Hit");
    				}
    			}
    		}
            if(sb.length()>0) {
            	return sb.toString();
            }
    		return super.getName(turnIndex, source);
    	}
    	
    	@Override
    	public DamageType getDamageType(int turnIndex, GameCharacter source) {
            DamageType damageType = DamageType.UNARMED.getParentDamageType(source, null);
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null) {
                   	damageType = weapon.getDamageType();
                   	break;
    			}
    		}
            return damageType;
    	}
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(true, "Available to everyone as a basic move.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	String costText="";
        	int essenceCost = getArcaneCost(turnIndex, source);
    		if(essenceCost>0) {
    			costText = " Weapon usage costs [style.boldArcane("+essenceCost+" Arcane essence"+(essenceCost>1?"s":"")+")]!";
    		}
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "<span style='color:"+this.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(getPredictionPrefix(turnIndex, source, target))+"</span> "
        				+ (target==null?"[npc.her] target":"[npc2.name]")
        				+ " with [npc.her] "
        				+ (source.getArmRows()==1
	        				?(getOffhandWeapon(turnIndex, source, 0)==null?"fists":getOffhandWeapon(turnIndex, source, 0).getName())
	        				:"offhand weapons")
        				+ " for "
        				+ Util.stringsToStringList(getDamageRanges(turnIndex, source, target, isCrit), false)
        				+ " damage."
        				+ costText);
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
        	String costText="";
        	int essenceCost = getArcaneCost(turnIndex, source);
        	List<String> weaponNames = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				weaponNames.add(weapon.getName());
    			} else {
    				if(!weaponNames.contains("fists")) {
    					weaponNames.add("fists");
    				}
    			}
    		}
    		if(essenceCost>0) {
    			costText = " Weapon usage costs [style.boldArcane("+essenceCost+" Arcane essence"+(essenceCost>1?"s":"")+")]!";
    		}
            return UtilText.parse(source,
            		"Strike [npc.her] target with [npc.her] "
            			+(weaponNames.isEmpty()
            					?"fists"
            					:weaponNames.size()==1
            						?weaponNames.get(0)
            						:"offhand weapons")
            			+", dealing base "
        				+ Util.stringsToStringList(getFormattedDamage(turnIndex, source, null, false), false)
            			+ " damage."
            			+ costText);
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);

        	StringBuilder attackStringBuilder = new StringBuilder("");
        	StringBuilder weaponAttacksStringBuilder = new StringBuilder("");
        	Map<GameCharacter, List<String>> weaponDamages = new LinkedHashMap<>();
        	
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
    			AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			
    			if(weapon!=null) {
    				int damage = Attack.calculateDamage(source, target, Attack.OFFHAND, weapon, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = weapon.getDamageType().damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append((i>0?"<br/>":"")+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, target, true, maxLust));
    				
    				List<GameCharacter> aoeAvailableTargets = new ArrayList<>(enemies);
    				aoeAvailableTargets.remove(target);
    				for(Value<Integer, Integer> aoe :weapon.getWeaponType().getAoeDamage()) {
    					if(aoeAvailableTargets.isEmpty()) {
    						break;
    					}
    					GameCharacter aoeTarget = Util.randomItemFrom(aoeAvailableTargets);
    					if(Math.random()*100<=aoe.getKey()) {
            				maxLust = isTargetAtMaximumLust(target);
            				damage = Attack.calculateDamage(source, target, Attack.OFFHAND, weapon, aoe.getValue(), isCrit);
            				damageValue = weapon.getDamageType().damageTarget(source, aoeTarget, damage);
            				inflictedDamage = damageValue.getValue();
            				weaponDamages.putIfAbsent(aoeTarget, new ArrayList<>());
            				weaponDamages.get(aoeTarget).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, aoeTarget, true, maxLust));
        					aoeAvailableTargets.remove(aoeTarget);
    					}
    				}
            		
    			} else {
    				int damage = Attack.calculateDamage(source, target, Attack.OFFHAND, null, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = DamageType.UNARMED.getParentDamageType(source, null).damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append((i>0?"<br/>":"")+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), inflictedDamage, target, true, maxLust));
    			}
    		}
    		
    		if(attackStringBuilder.length()>0) {
    			attackStringBuilder.append("<br/>");
    		}
    		
    		StringBuilder damageApplied = new StringBuilder();
    		for(Entry<GameCharacter, List<String>> entry : weaponDamages.entrySet()) {
    			if(damageApplied.length()>0) {
    				damageApplied.append("<br/>");
    			}
    			damageApplied.append(UtilText.parse(entry.getKey(),
    					(entry.getKey().equals(target)
    							?""
    							:"[style.boldAqua(AoE)]: ")
    						+"[npc.Name] took "+Util.stringsToStringList(entry.getValue(), false)+" damage!"));
    		}
    		attackStringBuilder.append(formatAttackOutcome(source, target,
    				weaponAttacksStringBuilder.toString(),
    				damageApplied.toString(),
    				isCrit?"":null,
    				isCrit?"Extra damage applied!":""));
    		
    		source.incrementEssenceCount(getArcaneCost(turnIndex, source), false); // Hack to restore essences from loss in performOnSelection() method, as they are also subtracted in applyExtraAttackEffects.
    		
    		List<String> extraEffects = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
        		if(weapon != null) {
        			String s = weapon.applyExtraEffects(source, target, true, isCrit);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.OFFHAND, weapon, true, isCrit));
        		} else {
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.OFFHAND, weapon, true, isCrit));
        		}
    		}
    		
    		if(!extraEffects.isEmpty()) {
        		attackStringBuilder.append("<div class='container-full-width' style='text-align:center; padding:0; margin:0;'>");
        		for(String s : extraEffects) {
            		attackStringBuilder.append(s);
        		}
        		attackStringBuilder.append("</div>");
    		}
    		
    		return attackStringBuilder.toString();
        }

        @Override
        public String isUsable(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	int weaponCount = 0;
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				weaponCount++;
    			}
    		}
    		
        	if(source.getEssenceCount()<essenceCost) {
        		return "You don't have enough arcane essences to use your weapon"+(weaponCount>1?"s":"")+"! ("+Util.capitaliseSentence(Util.intToString(essenceCost))+" "+(essenceCost==1?"is":"are")+" required.)";
        	}
        	return super.isUsable(turnIndex, source, target, enemies, allies);
        }
        
        @Override
        public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	source.incrementEssenceCount(-essenceCost, false);
        	// Thrown weapons:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = source.getOffhandWeaponArray()[i];
    			if(weapon!=null && weapon.getWeaponType().isOneShot()) {
    				if(source.getWeaponCount(weapon)>=1) {
    					source.removeWeapon(weapon);
    				} else {
    					source.unequipWeaponIntoVoid(InventorySlot.offhandWeaponSlots[i], weapon, true);
    					Main.combat.addThrownWeaponsDepleted(source, InventorySlot.offhandWeaponSlots[i], weapon.getWeaponType());
    				}
    				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.offhandWeaponSlots[i], weapon, 1);
    				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.offhandWeaponSlots[i], weapon, 1);
    			}
    		}
        }
        
        @Override
        public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	source.incrementEssenceCount(essenceCost, false);
        	// Thrown weapons:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		InventorySlot slot = InventorySlot.offhandWeaponSlots[i];
        		Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, slot);
        		if(weaponsThrown!=null) {
        			for(Entry<AbstractWeapon, Integer> entry : weaponsThrown.entrySet()) {
                		AbstractWeapon weapon = entry.getKey();
            			int thrownCount = entry.getValue();
        				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.offhandWeaponSlots[i], weapon, -thrownCount);
        				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.offhandWeaponSlots[i], weapon, -thrownCount);
            			if(source.getOffhandWeapon(i)==null) {
            				thrownCount--;
            				source.equipOffhandWeapon(weapon, i, false);
        					Main.combat.removeThrownWeaponsDepleted(source, slot);
        					
            			} else {
	            			for(int c=0; c<thrownCount; c++) {
	            				source.addWeapon(weapon, 1, false, false);
	            			}
            			}
        			}
        		}
        	}
        }
    };
    
    public static AbstractCombatMove BASIC_TWIN_STRIKE = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "all-out strike",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/strike_twin",
            Util.newArrayListOfValues(PresetColour.BASE_CRIMSON, PresetColour.BASE_ORANGE),
            false,
            true,
            false,
            null){
    	
    	private AbstractWeapon getMainWeapon(int turnIndex, GameCharacter source, int armRow) {
    		AbstractWeapon weapon = source.getMainWeaponArray()[armRow];
    		if(Main.game.isInCombat() && Main.combat.getAllCombatants(true).contains(source)) {
				Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, InventorySlot.mainWeaponSlots[armRow]);
				if(!weaponsThrown.isEmpty()) {
	    			int mainOrDualAttacksPerformed = 0;
	    			int turnCountProgress = 0;
	    			for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
	    				if(turnCountProgress < turnIndex) {
		    				if(move.getValue()==BASIC_STRIKE || move.getValue()==BASIC_TWIN_STRIKE) {
		    					mainOrDualAttacksPerformed++;
		    				}
		    				turnCountProgress++;
	    				} else {
		    				break;
		    			}
	    			}
					AbstractWeapon thrownWeaponType = weaponsThrown.keySet().iterator().next();
	    			if(mainOrDualAttacksPerformed < weaponsThrown.get(thrownWeaponType)) {
	    				weapon = weaponsThrown.keySet().iterator().next();
	    			}
				}
    		}
			return weapon;
    	}

    	private AbstractWeapon getOffhandWeapon(int turnIndex, GameCharacter source, int armRow) {
    		AbstractWeapon weapon = source.getOffhandWeaponArray()[armRow];
    		if(Main.game.isInCombat() && Main.combat.getAllCombatants(true).contains(source)) {
				Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, InventorySlot.offhandWeaponSlots[armRow]);
				if(!weaponsThrown.isEmpty()) {
	    			int offhandOrDualAttacksPerformed = 0;
	    			int turnCountProgress = 0;
	    			for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
	    				if(turnCountProgress < turnIndex) {
		    				if(move.getValue()==BASIC_OFFHAND_STRIKE || move.getValue()==BASIC_TWIN_STRIKE) {
		    					offhandOrDualAttacksPerformed++;
		    				}
		    				turnCountProgress++;
	    				} else {
		    				break;
		    			}
	    			}
					AbstractWeapon thrownWeaponType = weaponsThrown.keySet().iterator().next();
	    			if(offhandOrDualAttacksPerformed < weaponsThrown.get(thrownWeaponType)) {
	    				weapon = weaponsThrown.keySet().iterator().next();
	    			}
				}
    		}
			return weapon;
    	}

        private int getArcaneCost(int turnIndex, GameCharacter source) {
        	int essenceCost = 0;
        	for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null && weapon.getWeaponType().getArcaneCost()>0) {
    				essenceCost += weapon.getWeaponType().getArcaneCost();
    			}
    		}
        	for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
    			if(weapon!=null && weapon.getWeaponType().getArcaneCost()>0) {
    				essenceCost += weapon.getWeaponType().getArcaneCost();
    			}
    		}
        	return essenceCost;
        }
        
        private List<String> getDamageRanges(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) { 
            List<String> damages = new ArrayList<>();

    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamageRange(source, target, weapon.getDamageType(), Attack.MAIN, weapon, isCrit));
    			} else {
    				damages.add(getFormattedDamageRange(source, target, DamageType.UNARMED.getParentDamageType(source, null), Attack.MAIN, null, isCrit));
    			}
    		}
    		
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
				AbstractWeapon primaryWeapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamageRange(source, target, weapon.getDamageType(), Attack.OFFHAND, weapon, isCrit));
    			} else if(primaryWeapon!=null && !primaryWeapon.getWeaponType().isTwoHanded()) {
    				damages.add(getFormattedDamageRange(source, target, DamageType.UNARMED.getParentDamageType(source, null), Attack.OFFHAND, null, isCrit));
    			}
    		}
    		return damages;
        }
        
        private List<String> getFormattedDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean damageHasBeenApplied) {
            List<String> damages = new ArrayList<>();
            
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamage(weapon.getDamageType(), weapon.getWeaponType().getDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			} else {
    				damages.add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), source.getUnarmedDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			}
    		}
    		
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
				AbstractWeapon primaryWeapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				damages.add(getFormattedDamage(weapon.getDamageType(), weapon.getWeaponType().getDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			} else if(primaryWeapon!=null && !primaryWeapon.getWeaponType().isTwoHanded()) {
    				damages.add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), source.getUnarmedDamage(), target, damageHasBeenApplied, isTargetAtMaximumLust(target)));
    			}
    		}
    		
    		return damages;
        }
    	
    	@Override
    	public int getAPcost(GameCharacter source) {
    		return Math.min(3, Math.max(2, source.getArmRows() + (!source.getEquippedMoves().contains(this)?1:0)));
    	}

    	@Override
    	public DamageType getDamageType(int turnIndex, GameCharacter source) {
            DamageType damageType = DamageType.UNARMED.getParentDamageType(source, null);
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
                   	damageType = weapon.getDamageType();
                   	break;
    			}
    		}
            return damageType;
    	}

        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(true, "Available to everyone as a basic move.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	String costText="";
        	int cost = getArcaneCost(turnIndex, source);
        	if(cost>0) {
        		costText = " Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] to use this attack.";
        	}
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "<span style='color:"+this.getColour().toWebHexString()+";'>All-out strike</span> "
            		+ (target==null?"[npc.her] target":"[npc2.name]")
    				+ " with all of [npc.her] weapons for "
            		+ Util.stringsToStringList(getDamageRanges(turnIndex, source, target, isCrit), false)
            		+ " damage."
            		+ costText);
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
        	String costText="";
        	int essenceCost = getArcaneCost(turnIndex, source);
        	if(essenceCost>0) {
    			costText = " Weapon usage costs [style.boldArcane("+essenceCost+" Arcane essence"+(essenceCost>1?"s":"")+")]!";
        	}
            return UtilText.parse(source,
            		"Strike [npc.her] target with all of [npc.her] weapons, dealing "
            			+ Util.stringsToStringList(getFormattedDamage(turnIndex, source, null, false), false)
            		+ " damage."
            		+ costText);
        }
        
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
        	
        	StringBuilder attackStringBuilder = new StringBuilder("");
        	StringBuilder weaponAttacksStringBuilder = new StringBuilder("");
        	Map<GameCharacter, List<String>> weaponDamages = new LinkedHashMap<>();
        	
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
    			AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				int damage = Attack.calculateDamage(source, target, Attack.MAIN, weapon, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = weapon.getDamageType().damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append((i>0?"<br/>":"")+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, target, true, maxLust));
    				
    				List<GameCharacter> aoeAvailableTargets = new ArrayList<>(enemies);
    				aoeAvailableTargets.remove(target);
    				for(Value<Integer, Integer> aoe :weapon.getWeaponType().getAoeDamage()) {
    					if(aoeAvailableTargets.isEmpty()) {
    						break;
    					}
    					GameCharacter aoeTarget = Util.randomItemFrom(aoeAvailableTargets);
    					if(Math.random()*100<=aoe.getKey()) {
            				maxLust = isTargetAtMaximumLust(target);
            				damage = Attack.calculateDamage(source, target, Attack.MAIN, weapon, aoe.getValue(), isCrit);
            				damageValue = weapon.getDamageType().damageTarget(source, aoeTarget, damage);
            				inflictedDamage = damageValue.getValue();
            				weaponDamages.putIfAbsent(aoeTarget, new ArrayList<>());
            				weaponDamages.get(aoeTarget).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, aoeTarget, true, maxLust));
        					aoeAvailableTargets.remove(aoeTarget);
    					}
    				}
            		
    			} else {
    				int damage = Attack.calculateDamage(source, target, Attack.MAIN, null, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = DamageType.UNARMED.getParentDamageType(source, null).damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append((i>0?"<br/>":"")+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), inflictedDamage, target, true, maxLust));
    			}
    		}
    		
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
    			AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
				AbstractWeapon primaryWeapon = getMainWeapon(turnIndex, source, i);
    			if(weapon!=null) {
    				int damage = Attack.calculateDamage(source, target, Attack.OFFHAND, weapon, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = weapon.getDamageType().damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append("<br/>"+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, target, true, maxLust));
    				
    				List<GameCharacter> aoeAvailableTargets = new ArrayList<>(enemies);
    				aoeAvailableTargets.remove(target);
    				for(Value<Integer, Integer> aoe :weapon.getWeaponType().getAoeDamage()) {
    					if(aoeAvailableTargets.isEmpty()) {
    						break;
    					}
    					GameCharacter aoeTarget = Util.randomItemFrom(aoeAvailableTargets);
    					if(Math.random()*100<=aoe.getKey()) {
            				maxLust = isTargetAtMaximumLust(target);
            				damage = Attack.calculateDamage(source, target, Attack.OFFHAND, weapon, aoe.getValue(), isCrit);
            				damageValue = weapon.getDamageType().damageTarget(source, aoeTarget, damage);
            				inflictedDamage = damageValue.getValue();
            				weaponDamages.putIfAbsent(aoeTarget, new ArrayList<>());
            				weaponDamages.get(aoeTarget).add(getFormattedDamage(weapon.getDamageType(), inflictedDamage, aoeTarget, true, maxLust));
        					aoeAvailableTargets.remove(aoeTarget);
    					}
    				}
            		
    			} else if(primaryWeapon!=null && !primaryWeapon.getWeaponType().isTwoHanded()) {
    				int damage = Attack.calculateDamage(source, target, Attack.OFFHAND, null, isCrit);
    				boolean maxLust = isTargetAtMaximumLust(target);
    				Value<String, Integer> damageValue = DamageType.UNARMED.getParentDamageType(source, null).damageTarget(source, target, damage);
    				int inflictedDamage = damageValue.getValue();
    				weaponAttacksStringBuilder.append("<br/>"+source.getAttackDescription(weapon, target, true, isCrit)+damageValue.getKey());
    				weaponDamages.putIfAbsent(target, new ArrayList<>());
    				weaponDamages.get(target).add(getFormattedDamage(DamageType.UNARMED.getParentDamageType(source, null), inflictedDamage, target, true, maxLust));
    			}
    		
    		}
    		
    		if(attackStringBuilder.length()>0) {
    			attackStringBuilder.append("<br/>");
    		}
    		
    		StringBuilder damageApplied = new StringBuilder();
    		for(Entry<GameCharacter, List<String>> entry : weaponDamages.entrySet()) {
    			if(damageApplied.length()>0) {
    				damageApplied.append("<br/>");
    			}
    			damageApplied.append(UtilText.parse(entry.getKey(),
    					(entry.getKey().equals(target)
    							?""
    							:"[style.boldAqua(AoE)]: ")
    						+"[npc.Name] took "+Util.stringsToStringList(entry.getValue(), false)+" damage!"));
    		}
    		attackStringBuilder.append(formatAttackOutcome(source, target,
    				weaponAttacksStringBuilder.toString(),
    				damageApplied.toString(),
    				isCrit?"":null,
    				isCrit?"Extra damage applied!":""));

    		source.incrementEssenceCount(getArcaneCost(turnIndex, source), false); // Hack to restore essences from loss in performOnSelection() method, as they are also subtracted in applyExtraAttackEffects.
    		
    		List<String> extraEffects = new ArrayList<>();
    		for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = getMainWeapon(turnIndex, source, i);
        		if(weapon != null) {
        			String s = weapon.applyExtraEffects(source, target, true, isCrit);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.MAIN, weapon, true, isCrit));
        		} else {
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.MAIN, weapon, true, isCrit));
        		}
    		}
    		for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = getOffhandWeapon(turnIndex, source, i);
        		if(weapon != null) {
        			String s = weapon.applyExtraEffects(source, target, true, isCrit);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.OFFHAND, weapon, true, isCrit));
        		} else {
        			extraEffects.addAll(Main.combat.applyExtraAttackEffects(source, target, Attack.OFFHAND, weapon, true, isCrit));
        		}
    		}
    		
    		if(!extraEffects.isEmpty()) {
        		attackStringBuilder.append("<div class='container-full-width' style='text-align:center; padding:0; margin:0;'>");
        		for(String s : extraEffects) {
            		attackStringBuilder.append(s);
        		}
        		attackStringBuilder.append("</div>");
    		}
    		
    		return attackStringBuilder.toString();
        }
        
        @Override
        public String isUsable(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		int freeSlots = source.getArmRows();
    		
    		for (int i=0; i<source.getArmRows(); i++) {
    			AbstractWeapon mainWeapon = getMainWeapon(turnIndex, source, i);
    			AbstractWeapon offhandWeapon = getOffhandWeapon(turnIndex, source, i);
        	   	if((mainWeapon!=null && mainWeapon.getWeaponType().isTwoHanded()) || (offhandWeapon!=null && offhandWeapon.getWeaponType().isTwoHanded())) {
        	   		freeSlots--;
            	}
        	}

    		if(freeSlots==0){
    			if(source.getArmRows()>1) {
					return "You are using only two-handed weapons, so have no free hand with which to use an all-out strike!";
				} else {
					return "You are using a two-handed weapon, so have no free hand with which to use an all-out strike!";
				}
			}
    		
    		int cost = getArcaneCost(turnIndex, source);
        	if(source.getEssenceCount()<cost) {
        		return "You don't have enough arcane essences to use your weapon! ("+Util.capitaliseSentence(Util.intToString(cost))+" "+(cost==1?"is":"are")+" required.)";
        	}
        	
        	return super.isUsable(turnIndex, source, target, enemies, allies);
        }
        
        @Override
        public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	source.incrementEssenceCount(-essenceCost, false);
        	
        	// Thrown weapons:
        	// Main:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		AbstractWeapon weapon = source.getMainWeaponArray()[i];
    			if(weapon!=null && weapon.getWeaponType().isOneShot()) {
    				if(source.getWeaponCount(weapon)>=1) {
    					source.removeWeapon(weapon);
    				} else {
    					source.unequipWeaponIntoVoid(InventorySlot.mainWeaponSlots[i], weapon, true);
    					Main.combat.addThrownWeaponsDepleted(source, InventorySlot.mainWeaponSlots[i], weapon.getWeaponType());
    				}
    				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.mainWeaponSlots[i], weapon, 1);
    				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.mainWeaponSlots[i], weapon, 1);
    			}
    		}
        	// Offhand:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		AbstractWeapon weapon = source.getOffhandWeaponArray()[i];
    			if(weapon!=null && weapon.getWeaponType().isOneShot()) {
    				if(source.getWeaponCount(weapon)>=1) {
    					source.removeWeapon(weapon);
    				} else {
    					source.unequipWeaponIntoVoid(InventorySlot.offhandWeaponSlots[i], weapon, true);
    					Main.combat.addThrownWeaponsDepleted(source, InventorySlot.offhandWeaponSlots[i], weapon.getWeaponType());
    				}
    				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.offhandWeaponSlots[i], weapon, 1);
    				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.offhandWeaponSlots[i], weapon, 1);
    			}
    		}
        }
        
        @Override
        public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int essenceCost = getArcaneCost(turnIndex, source);
        	source.incrementEssenceCount(essenceCost, false);
        	
        	// Thrown weapons:
        	// Main:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getMainWeaponArray().length); i++) {
        		InventorySlot slot = InventorySlot.mainWeaponSlots[i];
        		Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, slot);
        		if(weaponsThrown!=null) {
        			for(Entry<AbstractWeapon, Integer> entry : weaponsThrown.entrySet()) {
                		AbstractWeapon weapon = entry.getKey();
            			int thrownCount = entry.getValue();
        				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.mainWeaponSlots[i], weapon, -thrownCount);
        				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.mainWeaponSlots[i], weapon, -thrownCount);
            			if(source.getMainWeapon(i)==null) {
            				thrownCount--;
            				source.equipMainWeapon(weapon, i, false);
        					Main.combat.removeThrownWeaponsDepleted(source, slot);
        					
            			} else {
	            			for(int c=0; c<thrownCount; c++) {
	            				source.addWeapon(weapon, 1, false, false);
	            			}
            			}
        			}
        		}
        	}
        	// Offhand:
        	for(int i=0; i<Math.min(source.getArmRows(), source.getOffhandWeaponArray().length); i++) {
        		InventorySlot slot = InventorySlot.offhandWeaponSlots[i];
        		Map<AbstractWeapon, Integer> weaponsThrown = Main.combat.getWeaponsThrownDuringTurn(source, slot);
        		if(weaponsThrown!=null) {
        			for(Entry<AbstractWeapon, Integer> entry : weaponsThrown.entrySet()) {
                		AbstractWeapon weapon = entry.getKey();
            			int thrownCount = entry.getValue();
        				Main.combat.incrementWeaponsThrownDuringTurn(source, InventorySlot.offhandWeaponSlots[i], weapon, -thrownCount);
        				Main.combat.incrementWeaponsThrownDuringCombat(source, InventorySlot.offhandWeaponSlots[i], weapon, -thrownCount);
            			if(source.getOffhandWeapon(i)==null) {
            				thrownCount--;
            				source.equipOffhandWeapon(weapon, i, false);
        					Main.combat.removeThrownWeaponsDepleted(source, slot);
        					
            			} else {
	            			for(int c=0; c<thrownCount; c++) {
	            				source.addWeapon(weapon, 1, false, false);
	            			}
            			}
        			}
        		}
        	}
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues("This is the only move that's being used.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		return source.getSelectedMoves().stream().anyMatch(move->move.getValue()==BASIC_TWIN_STRIKE) && source.getSelectedMoves().size()==1;
        }
    };
    
    public static AbstractCombatMove BASIC_BLOCK = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "block",
            0,
            1,
            CombatMoveType.DEFEND,
            DamageType.HEALTH,
            "moves/block",
            Util.newArrayListOfValues(PresetColour.BASE_GREY),
            false,
            false,
            true,
            null) {

        @Override
        public int getBlock(GameCharacter source, boolean isCrit) {
            return 7 * (isCrit?2:1);
        }

        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(true, "Available to everyone as a basic move.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return (isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Block</span> "
                    + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(source, isCrit))+ " " + damageType.getName() + " </span>"
                    + " damage.";
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return "Focus on defending yourself, gaining protection against "
                    + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(source, false)) + "</span>"
                    + " damage.";
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return (formatAttackOutcome(source, target,
    				"[npc.Name] focused on defending [npc.herself].",
    				"[npc.SheIs] now protected against " + getFormattedDamage(getDamageType(turnIndex, source), getBlock(source, isCrit), target, true, false) + " damage!",
    				isCrit?"":null,
    				isCrit?"[npc.Name] [npc.verb(double)] [npc.her] block!":""));
            
        }

        @Override
        public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = DamageType.HEALTH;
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            target.setShields(damageType, target.getShields(damageType) + getBlock(source, isCrit));
        }
        
        @Override
        public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = DamageType.HEALTH;
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            target.setShields(damageType, target.getShields(damageType) - getBlock(source, isCrit));
        }
    };
    
    public static AbstractCombatMove BASIC_TEASE = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "moves/tease",
            false,
            true,
            false,
            null){

        protected int getBaseDamage(GameCharacter source) {
            return 7;
        }

        protected int getDamage(GameCharacter source, GameCharacter target, boolean critical) {
            return Attack.calculateSeductionDamage(source, target, getBaseDamage(source), critical);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(true, "Available to everyone as a basic move.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            return UtilText.parse(source, target,
                    (isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Tease</span> "
            				+" [npc2.name] for " + getFormattedDamage(getDamageType(turnIndex, source), getDamage(source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage.");
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            return "Tease your enemy, dealing base "
                    + getFormattedDamage(getDamageType(turnIndex, source), getBaseDamage(source), null, false, false)
                    + " damage to them.";
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	StringBuilder sb = new StringBuilder("");

    		DamageType finalDt = getDamageType(turnIndex, source);
        	
    		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
			boolean maxLust = isTargetAtMaximumLust(target);
			Value<String, Integer> damageValue = getDamageType(turnIndex, source).damageTarget(source, target, getDamage(source, target, isCrit));
			int lustDamage = damageValue.getValue();
			
            sb.append(formatAttackOutcome(source, target,
            		source.getSeductionDescription(target)+damageValue.getKey(),
    				"[npc2.Name] took " + getFormattedDamage(finalDt, lustDamage, target, true, maxLust) + " damage!",
    				isCrit?"":null,
    				isCrit?"[npc2.Name] [npc2.verb(feel)] incredibly turned-on!":""));
            
    		if(source.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)) {
    			Main.combat.addStatusEffectToApply(target, StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 2);
    			sb.append(Spell.getBasicStatusEffectApplication(target, false, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 2))));
    		}
    		
    		return sb.toString();
        }
    };
    
    public static AbstractCombatMove BASIC_TEASE_BLOCK = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "resist",
            0,
            1,
            CombatMoveType.DEFEND,
            DamageType.LUST,
            "moves/avert",
            false,
            false,
            true,
            null) {

        @Override
        public int getBlock(GameCharacter source, boolean isCrit) {
            return 7 * (isCrit?2:1);
        }

        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(true, "Available to everyone as a basic move.");
        }
        
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source,  target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Resist</span> "
	                + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(source, isCrit))+ " " + damageType.getName() + " </span>"
	                + " damage.");
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return "Resist temptation, gaining protection against "
            		+ "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(source, false)) + "</span>"
            		+ " damage.";
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return formatAttackOutcome(source, target,
    				"[npc.Name] focused on resisting any attempts at seduction.",
    				"[npc.SheIs] now protected against " + getFormattedDamage(getDamageType(turnIndex, source), getBlock(source, isCrit), target, true, false) + " damage!",
    				isCrit?"":null,
    				isCrit?"[npc.Name] [npc.verb(double)] [npc.her] shielding!":"");
        }

        @Override
        public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            target.setShields(damageType, target.getShields(damageType) + getBlock(source, canCrit(turnIndex, source, target, enemies, allies)));
        }
        
        @Override
        public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            target.setShields(damageType, target.getShields(damageType) - getBlock(source, canCrit(turnIndex, source, target, enemies, allies)));
        }

        @Override
        public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(shouldBlunder()) {
                return (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
            }
            int behaviourMultiplier = 1;
        	if(source.getCombatBehaviour()==CombatBehaviour.DEFEND) {
        		behaviourMultiplier=2;
        	}
            if(source.getLustLevel() == LustLevel.FOUR_IMPASSIONED || source.getLustLevel() == LustLevel.FIVE_BURNING) {
                return (1.0f*behaviourMultiplier) + 0.2f * (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
            }
            return super.getWeight(source, enemies, allies);
//                return 0.8f + 0.2f * (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
        }
    };
    
    public static AbstractCombatMove BASIC_ARCANE_STRIKE = new AbstractCombatMove(CombatMoveCategory.BASIC,
            "arcane strike",
            0,
            1,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/arcane_strike",
            Util.newArrayListOfValues(PresetColour.GENERIC_ARCANE),
            false,
            true,
            false,
            null){

    	@Override
    	public DamageType getDamageType(int turnIndex, GameCharacter source) {
            return DamageType.LUST;
    	}
    	
        protected int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, getManaGain(source)*0.1f);
        }

        private int getManaGain(GameCharacter source) {
            return source.getLevel()*2;
        }
        
        protected int getDamage(GameCharacter source, GameCharacter target) {
        	return Math.max(1, (int) (Attack.getModifiedDamage(source, target, Attack.SEDUCTION, null, DamageType.LUST, getBaseDamage(source))));
        }

        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getSpells().size() > 0, "Requires knowledge of at least one spell.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Arcane strike</span> "
            				+ (target==null?"[npc.her] target":"[npc2.name]")
            				+ " to deal "
            				+ getFormattedDamage(getDamageType(turnIndex, source), getDamage(source, target), null, false, isTargetAtMaximumLust(target))
            				+ " damage"
            				+ " and gain <span style='color:" + PresetColour.ATTRIBUTE_MANA.toWebHexString() + ";'>"+(getManaGain(source)*(isCrit?2:1))+" "+Attribute.MANA_MAXIMUM.getName()+"</span>.");
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            return UtilText.parse(source,
            		"Strike [npc.her] target with a bolt of pure arcane energy, dealing base "
            				+ getFormattedDamage(getDamageType(turnIndex, source), getBaseDamage(source), null, false, false) + " damage"
            				+ " and recovering base <span style='color:" + PresetColour.ATTRIBUTE_MANA.toWebHexString() + ";'>"+getManaGain(source)+" "+Attribute.MANA_MAXIMUM.getName()+"</span>.");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);

			boolean maxLust = isTargetAtMaximumLust(target);
			Value<String, Integer> damageValue = getDamageType(turnIndex, source).damageTarget(source, target, getDamage(source, target));
			int dealtDamage = damageValue.getValue();
			
			int manaGain = getManaGain(source);
			if(isCrit) {
				manaGain *= 2;
			}
			
			source.incrementMana(manaGain);
			
        	StringBuilder attackStringBuilder = new StringBuilder("");

    		if(attackStringBuilder.length()>0) {
    			attackStringBuilder.append("<br/>");
    		}
    		
    		attackStringBuilder.append(formatAttackOutcome(source, target,
    				"Harnessing [npc.her] knowledge of the arcane, [npc.name] [npc.verb(focus)] on replenishing [npc.her] aura as [npc.she] [npc.verb(launch)] a bolt of pure arcane energy at [npc2.name]!"+damageValue.getKey(),
    				"[npc2.Name] took " + getFormattedDamage(getDamageType(turnIndex, source), dealtDamage, target, true, maxLust) + " damage, while [npc.name] recovered"
    						+ " <span style='color:" + PresetColour.ATTRIBUTE_MANA.toWebHexString() + ";'>"+manaGain+" "+Attribute.MANA_MAXIMUM.getName()+"</span>!",
    				isCrit?"":null,
    				isCrit?"Aura gain was doubled!":""));
    		
    		List<String> extraEffects = Main.combat.applyExtraAttackEffects(source, target, Attack.SEDUCTION, null, true, isCrit);
    		if(!extraEffects.isEmpty()) {
        		attackStringBuilder.append("<div class='container-full-width' style='text-align:center; padding:0; margin:0;'>");
        		for(String s : extraEffects) {
            		attackStringBuilder.append(s);
        		}
        		attackStringBuilder.append("</div>");
    		}
    		
    		return attackStringBuilder.toString();
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			UtilText.parse(source, "[npc.NameIsFull] casting no spells."));
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getSelectedMovesByType(CombatMoveType.SPELL) == 0) { // Crits if no spells are cast this turn.
                return true;
            }
            return super.canCrit(turnIndex, source, target, enemies, allies);
        }
    };
}
