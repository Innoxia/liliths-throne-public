package com.lilithsthrone.game.combat.moves;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * A class containing logic for Combat Moves.
 * 
 * @since 0.3.4
 * @version 0.4
 * @author Irbynx, Innoxia
 */
public class CombatMove {

    public static final AbstractCombatMove ITEM_USAGE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "use item",
            0,
            1,
            CombatMoveType.DEFEND,
            DamageType.HEALTH,
            "moves/block",
            Util.newArrayListOfValues(PresetColour.GENERIC_MINOR_GOOD),
            false,
            false,
            true,
            null) {
    	
    	private Value<GameCharacter, AbstractItem> getItem(int turnIndex, GameCharacter source) {
            int index=0;
            int turnCount=0;
            for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
            	if(turnCount==turnIndex) {
            		break;
            	}
            	if(move.getValue().getIdentifier().equals(ITEM_USAGE.getIdentifier())) {
            		index++;
            	}
            	turnCount++;
            }
            return Main.combat.getItemsToBeUsed(source).get(index);
    	}	
    	
    	@Override
    	public String getName(int turnIndex, GameCharacter source) {
        	Value<GameCharacter, AbstractItem> itemValue = getItem(turnIndex, source);
        	
    		return Util.capitaliseSentence(itemValue.getValue().getItemType().getUseName()+" the "+itemValue.getValue().getName());
    	}
    	
    	@Override
    	public int getAPcost(GameCharacter source) {
    		return 1;
    	}

        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(true, "Available to everyone as a basic move.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	Value<GameCharacter, AbstractItem> itemValue = getItem(turnIndex, source);
            
            return "[style.colourMinorGood("+Util.capitaliseSentence(UtilText.parse(itemValue.getValue().getItemType().getUseName()))+")] the "+itemValue.getValue().getName()+".";
        }

        @Override
        public String getDescription(GameCharacter source) {
            return "Use an item from your inventory.";
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	Value<GameCharacter, AbstractItem> itemValue = getItem(turnIndex, source);
            
            return itemValue.getValue().applyEffect(source, itemValue.getKey());
        }

        @Override
        public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	Value<GameCharacter, AbstractItem> itemValue = getItem(turnIndex, source);
        	if(itemValue.getValue().isConsumedOnUse()) {
        		source.removeItem(itemValue.getValue());
        	}
        }
        
        @Override
        public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	Value<GameCharacter, AbstractItem> itemValue = getItem(turnIndex, source);
        	if(itemValue.getValue().isConsumedOnUse()) {
        		source.addItem(itemValue.getValue(), false);
        	}
        }
        
        @Override
    	public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues("This move can never crit.");
        }

        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return false;
        }
    };
    
    public static List<AbstractCombatMove> allCombatMoves = new ArrayList<>();
    public static Map<CombatMoveCategory, List<AbstractCombatMove>> combatMoveCategoryMap = new HashMap<>();
    
	public static Map<AbstractCombatMove, String> combatMoveToIdMap = new HashMap<>();
	public static Map<String, AbstractCombatMove> idToCombatMoveMap = new HashMap<>();
	
    static {
        /*=============================================
         *             BASIC MOVES
         =============================================*/
	    Field[] fields = CMBasicAttack.class.getFields();
		for(Field f : fields) {
			if(AbstractCombatMove.class.isAssignableFrom(f.getType())) {
				AbstractCombatMove combatMove;
				try {
					combatMove = ((AbstractCombatMove) f.get(null));
					
					CombatMove.idToCombatMoveMap.put(f.getName(), combatMove);
					CombatMove.combatMoveToIdMap.put(combatMove, f.getName());
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
    	
        /*=============================================
         *             RACIAL & FETISH
         =============================================*/
	    fields = CMSpecialAttack.class.getFields();
		for(Field f : fields) {
			if(AbstractCombatMove.class.isAssignableFrom(f.getType())) {
				AbstractCombatMove combatMove;
				try {
					combatMove = ((AbstractCombatMove) f.get(null));
					
					CombatMove.idToCombatMoveMap.put(f.getName(), combatMove);
					CombatMove.combatMoveToIdMap.put(combatMove, f.getName());
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	
		fields = CMFetishAttack.class.getFields();
		for (Field f : fields) {
			if(AbstractCombatMove.class.isAssignableFrom(f.getType())) {
				AbstractCombatMove combatMove;
				try {
					combatMove = ((AbstractCombatMove) f.get(null));
					
					CombatMove.idToCombatMoveMap.put(f.getName(), combatMove);
					CombatMove.combatMoveToIdMap.put(combatMove, f.getName());
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	
		fields = CMWeaponSpecials.class.getFields();
		for (Field f : fields) {
			if(AbstractCombatMove.class.isAssignableFrom(f.getType())) {
				AbstractCombatMove combatMove;
				try {
					combatMove = ((AbstractCombatMove) f.get(null));
					
					CombatMove.idToCombatMoveMap.put(f.getName(), combatMove);
					CombatMove.combatMoveToIdMap.put(combatMove, f.getName());
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
	    /*=============================================
	     *                   SPELLS
	     =============================================*/
	    // Automatically generates respective moves based on the Spell class.
	    for(Spell spell : Spell.values()) {
	    	AbstractCombatMove newCombatMove = new AbstractCombatMove(CombatMoveCategory.SPELL,
	                spell.getName(),
	                spell.getCooldown(),
	                spell.getAPCost(),
	                CombatMoveType.SPELL,
	                spell.getDamageType(),
	                "combat/spell/"+spell.getPathName(),
	                spell.isCanTargetAllies(),
	                spell.isCanTargetEnemies(),
	                spell.isCanTargetSelf(),
	                spell.getStatusEffects(null, null, false)) {
	        	
	        	@Override
	        	public Spell getAssociatedSpell() {
	                return spell;
	            }
	        	
	            @Override
	            public float getCritStatusEffectDurationMultiplier() {
	            	return 2;
	            }
	
	            @Override
	            public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
	            	return getAssociatedSpell().getStatusEffects(caster, target, isCritical);
	            }
	            
	            @Override
	            public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
	                if(source.hasSpell(this.getAssociatedSpell())) {
	                    return new Value<>(true, "This is a spell available to those who have learned it.");
	                    
	                } else if(source.getExtraSpells().contains(this.getAssociatedSpell())) {
	                    return new Value<>(true, "This spell is available because of your equipped weapon.");
	                    
	                } else {
	                    return null;
	                }
	            }
	
	            @Override
	            public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                return getAssociatedSpell().getWeight(source, enemies, allies);
	            }
	
	            @Override
	            public GameCharacter getPreferredTarget(GameCharacter  source, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                return getAssociatedSpell().getPreferredTarget(source, enemies, allies);
	            }
	
	            @Override
	            public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                return getAssociatedSpell().getPrediction(turnIndex, source, target, enemies, allies);
	            }
	
	            @Override
	            public void applyDisruption(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                getAssociatedSpell().applyDisruption(source, target, enemies, allies);
	            }
	
	            @Override
	            public String getDescription(GameCharacter source) {
	                return getAssociatedSpell().getDescription(source);
	            }
	
	            @Override
	            public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                return getAssociatedSpell().perform(turnIndex, source, target, enemies, allies);
	            }
	
	            @Override
	            public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                getAssociatedSpell().performOnSelection(turnIndex, source, target, enemies, allies);
	            }
	            
	            @Override
	            public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                getAssociatedSpell().performOnDeselection(turnIndex, source, target, enemies, allies);
	            }
	
	            @Override
	            public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	            	return getAssociatedSpell().getCritRequirements(source, target, enemies, allies);
	            }
	            
	            @Override
	            public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                return getAssociatedSpell().canCrit(turnIndex, source, target, enemies, allies);
	            }
	
	            @Override
	            public String isUsable(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	                String reason = super.isUsable(source, target, enemies, allies);
	                if(reason == null) {
	                    if((getAssociatedSpell().getSpellSchool()!=SpellSchool.FIRE || !source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) && source.getMana()<getAssociatedSpell().getModifiedCost(source)) {
	                        reason = "Not enough aura to cast this spell!";
	                    }
	                }
	                return reason;
	            }
	        };
	        newCombatMove.setAssociatedSpell(spell);
			CombatMove.idToCombatMoveMap.put("SPELL_"+spell.toString(), newCombatMove);
			CombatMove.combatMoveToIdMap.put(newCombatMove, "SPELL_"+spell.toString());
	    }

	    /*=============================================
	     *              EXTERNAL FILES
	     =============================================*/
		// Modded Moves:
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/combatMove");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractCombatMove combatMove = new AbstractCombatMove(innerEntry.getValue(), entry.getKey(), true) {};
					CombatMove.idToCombatMoveMap.put(innerEntry.getKey(), combatMove);
					CombatMove.combatMoveToIdMap.put(combatMove, innerEntry.getKey());
					
				} catch(Exception ex) {
					System.err.println("Loading modded combat move failed at 'CombatMove'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res Moves:
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res//combatMove");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					AbstractCombatMove combatMove = new AbstractCombatMove(innerEntry.getValue(), entry.getKey(), false) {};
					CombatMove.idToCombatMoveMap.put(innerEntry.getKey(), combatMove);
					CombatMove.combatMoveToIdMap.put(combatMove, innerEntry.getKey());
					
				} catch(Exception ex) {
					System.err.println("Loading combat move failed at 'CombatMove'. File path: "+innerEntry.getValue().getAbsolutePath());
					System.err.println("Actual exception: ");
					ex.printStackTrace(System.err);
				}
			}
		}
	    
	    
	    for(AbstractCombatMove move : combatMoveToIdMap.keySet()) {
	    	allCombatMoves.add(move);
	    	combatMoveCategoryMap.putIfAbsent(move.getCategory(), new ArrayList<>());
	    	combatMoveCategoryMap.get(move.getCategory()).add(move);
	    }
    }
    
	/**
	 * @param id Will be in the format of: 'innoxia_quadruped_kick'.
	 */
	public static AbstractCombatMove getCombatMoveFromId(String id) {
		for(Spell spell : Spell.values()) {
			if(id.equals(spell.name())) {
				id = "SPELL_"+spell.toString();
				id = Util.getClosestStringMatch(id, idToCombatMoveMap.keySet());
				return idToCombatMoveMap.get(id);
			}
		}
		
		if(id.equals("item-usage")) {
			return ITEM_USAGE;
		}
		
		if(id.equals("hoof-kick")) {
			return CMSpecialAttack.HORSE_KICK;
		} else if(id.equals("cat-scratch")) {
			return CMSpecialAttack.CAT_SCRATCH;
		} else if(id.equals("tail-swipe")) {
			return CMSpecialAttack.ALLIGATOR_TAIL_SWIPE;
		} else if(id.equals("squirrel-scratch")) {
			return CMSpecialAttack.SQUIRREL_SCRATCH;
		} else if(id.equals("savage-attack")) {
			return CMSpecialAttack.WOLF_SAVAGE;
		} else if(id.equals("antler-headbutt")) {
			return CMSpecialAttack.ANTLER_HEADBUTT;
		} else if(id.equals("horn-headbutt")) {
			return CMSpecialAttack.COW_HEADBUTT;
		} else if(id.equals("bite")) {
			return CMSpecialAttack.BITE;
		}
		
		if(id.equals("strike")) {
			return CMBasicAttack.BASIC_STRIKE;
		} else if(id.equals("offhand-strike")) {
			return CMBasicAttack.BASIC_OFFHAND_STRIKE;
		} else if(id.equals("twin-strike")) {
			return CMBasicAttack.BASIC_TWIN_STRIKE;
		} else if(id.equals("block")) {
			return CMBasicAttack.BASIC_BLOCK;
		} else if(id.equals("tease")) {
			return CMBasicAttack.BASIC_TEASE;
		} else if(id.equals("avert")) {
			return CMBasicAttack.BASIC_TEASE_BLOCK;
		} else if(id.equals("arcane-strike")) {
			return CMBasicAttack.BASIC_ARCANE_STRIKE;
		}
		
		if(id.equals("buttslut-tease")) {
			return CMFetishAttack.TEASE_ANAL_RECEIVING;
		} else if(id.equals("anal-tease")) {
			return CMFetishAttack.TEASE_ANAL_GIVING;
		} else if(id.equals("pussy-slut-tease")) {
			return CMFetishAttack.TEASE_VAGINAL_RECEIVING;
		} else if(id.equals("vaginal-tease")) {
			return CMFetishAttack.TEASE_VAGINAL_GIVING;
		} else if(id.equals("incest-tease")) {
			return CMFetishAttack.TEASE_INCEST;
		} else if(id.equals("cum-stud-tease")) {
			return CMFetishAttack.TEASE_CUM_STUD;
		} else if(id.equals("cum-addict-tease")) {
			return CMFetishAttack.TEASE_CUM_ADDICT;
		} else if(id.equals("cock-addict-tease")) {
			return CMFetishAttack.TEASE_PENIS_RECEIVING;
		} else if(id.equals("cock-stud-tease")) {
			return CMFetishAttack.TEASE_PENIS_GIVING;
		} else if(id.equals("submissive-foot-tease")) {
			return CMFetishAttack.TEASE_FOOT_RECEIVING;
		} else if(id.equals("dominant-foot-tease")) {
			return CMFetishAttack.TEASE_FOOT_GIVING;
		} else if(id.equals("oral-tease")) {
			return CMFetishAttack.TEASE_ORAL_RECEIVING;
		} else if(id.equals("oral-performer-tease")) {
			return CMFetishAttack.TEASE_ORAL_GIVING;
		} else if(id.equals("breasts-lover-tease")) {
			return CMFetishAttack.TEASE_BREASTS_OTHERS;
		} else if(id.equals("breasts-tease")) {
			return CMFetishAttack.TEASE_BREASTS;
		} else if(id.equals("milk-lover-tease")) {
			return CMFetishAttack.TEASE_LACTATION_OTHERS;
		} else if(id.equals("lactation-tease")) {
			return CMFetishAttack.TEASE_LACTATION;
		} else if(id.equals("fertility-tease")) {
			return CMFetishAttack.TEASE_FERTILITY;
		} else if(id.equals("virility-tease")) {
			return CMFetishAttack.TEASE_VIRILITY;
		} else if(id.equals("dominant-tease")) {
			return CMFetishAttack.TEASE_DOMINANT;
		} else if(id.equals("submissive-tease")) {
			return CMFetishAttack.TEASE_SUBMISSIVE;
		}
		
		id = Util.getClosestStringMatch(id, idToCombatMoveMap.keySet());
		
		return idToCombatMoveMap.get(id);
	}
	
	public static String getIdFromCombatMove(AbstractCombatMove move) {
		return combatMoveToIdMap.get(move);
	}

	public static List<AbstractCombatMove> getAllCombatMoves() {
		return allCombatMoves;
	}

	public static List<AbstractCombatMove> getAllCombatMovesInCategory(CombatMoveCategory category) {
		return combatMoveCategoryMap.get(category);
	}
}
