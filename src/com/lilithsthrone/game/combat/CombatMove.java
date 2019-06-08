package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.enchanting.TFEssence;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class containing logic for Combat Moves. Additionally contains all the registered combat moves in the game.
 * 
 * @since 0.3.4
 * @version 0.3.4
 * @author Irbynx, Innoxia
 */
public class CombatMove {
    public static List<CombatMove> allCombatMoves = new ArrayList<>();

    private String identifier;
    private String name;
    private int cooldown;
    private int APcost;
    private CombatMoveType type;
    private DamageType damageType;
    private boolean canTargetEnemies;
    private boolean canTargetAllies;
    private boolean canTargetSelf;
    private String SVGString;

	private Map<StatusEffect, Integer> statusEffects;

    private Spell associatedSpell;

    static {
        // Initializing default actions
        // TODO
        // Strike: Main weapon deals 100% damage
        // Battle Dance: Deal 60% damage with main weapon and 30% with offhand weapon.
        // Flash kick: Deal 100% unarmed damage
        // Protect: Block 25 physical damage
        // Calm Down: Block 25 lust damage
        // Escape: Attempts to escape
        // Tease: Deals 7 lust damage


        /*=============================================
         *
         *              DEFAULT ACTIONS
         *
         =============================================*/
        /*=============================================
         *
         *
         *
         */
        CombatMove newCombatMove = new CombatMove("strike",
                "strike",
                0,
                1,
                CombatMoveType.ATTACK,
                DamageType.UNARMED,
                "moves/strike",
                Util.newArrayListOfValues(Colour.BASE_CRIMSON),
                false,
                true,
                false,
                null) {
        	
        	@Override
        	public DamageType getDamageType(GameCharacter source) {
                DamageType damageType = DamageType.UNARMED.getParentDamageType(Main.game.getPlayer(), null);
                if(source.getMainWeapon() != null) {
                    damageType = source.getMainWeapon().getDamageType();
                }
                return damageType;
        	}
        	
            private int getBaseDamage(GameCharacter source) {
                AbstractWeapon weapon = source.getMainWeapon();
                if (weapon == null) {
                    return source.getUnarmedDamage();
                } else {
                    return weapon.getWeaponType().getDamage();
                }
            }

            private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
                return (int) Attack.calculateDamage(source, target, Attack.MAIN, isCrit);
            }

            @Override
            public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
                return new Value<>(true, "Available to everyone as a basic move.");
            }

            @Override
            public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	String costText="";
            	if(source.getMainWeapon()!=null && source.getMainWeapon().getWeaponType().getArcaneCost()>0) {
            		int cost = source.getMainWeapon().getWeaponType().getArcaneCost();
            		costText = " Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] "+(Main.game.getPlayer().getMainWeapon().getWeaponType().isMelee()?"per attack.":"to fire.");
    			}
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                return UtilText.parse(source, target,
                		(isCrit?"[style.colourExcellent(Critical)]: ":"")
                		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Strike</span> "
            				+ (target==null?"[npc.her] target":"[npc2.name]") + " for " + getFormattedDamageRange(source, target, getDamageType(source), Attack.MAIN, isCrit) + " damage."
            				+costText);
            }

            @Override
            public String getDescription() {
            	String costText="";
            	GameCharacter source = Main.game.getPlayer();
            	if(source.getMainWeapon()!=null && source.getMainWeapon().getWeaponType().getArcaneCost()>0) {
            		int cost = source.getMainWeapon().getWeaponType().getArcaneCost();
            		costText = " Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] "+(source.getMainWeapon().getWeaponType().isMelee()?"per attack.":"to fire.");
    			}
                return UtilText.parse(source,
                		"Strike [npc.her] target with [npc.her] "+(source.getMainWeapon()==null?"fists":source.getMainWeapon().getName())
                			+", dealing base " + getFormattedDamage(getDamageType(source), getBaseDamage(source)) + " damage."
                			+costText);
            }

            @Override
            public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                int dealtDamage = getDamageType(source).damageTarget(source, target, getDamage(source, target, isCrit));
            	
            	StringBuilder attackStringBuilder = new StringBuilder("");

        		if(attackStringBuilder.length()>0) {
        			attackStringBuilder.append("<br/>");
        		}
        		
        		attackStringBuilder.append(formatAttackOutcome(source, target,
        				source.getMainAttackDescription(target, true),
        				"[npc2.Name] took " + getFormattedDamage(getDamageType(source), dealtDamage) + " damage!",
        				isCrit?"":null,
        				isCrit?"[npc2.Name] [npc2.verb(take)] extra damage!":""));
        		
        		if(source.getMainWeapon()!=null) { // Hack to restore essences from loss in performOnSelection() method, as they are also subtracted in applyExtraAttackEffects:
            		source.incrementEssenceCount(TFEssence.ARCANE, source.getMainWeapon().getWeaponType().getArcaneCost(), false);
            	}
        		
        		if(source.getMainWeapon() != null) {
        			String s = source.getMainWeapon().applyExtraEffects(source, target, true);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        		}
        		
        		List<String> extraEffects = Combat.applyExtraAttackEffects(source, target, Attack.MAIN, true, isCrit);
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
            public String isUseable(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	if(source.getMainWeapon()!=null && source.getEssenceCount(TFEssence.ARCANE)<source.getMainWeapon().getWeaponType().getArcaneCost()) {
            		int cost = source.getMainWeapon().getWeaponType().getArcaneCost();
            		return "You don't have enough arcane essences to use your weapon! ("+Util.capitaliseSentence(Util.intToString(cost))+" "+(cost==1?"is":"are")+" required.)";
            	}
            	return super.isUseable(source, target, enemies, allies);
            }
            
            @Override
            public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	if(source.getMainWeapon()!=null) {
            		source.incrementEssenceCount(TFEssence.ARCANE, -source.getMainWeapon().getWeaponType().getArcaneCost(), false);
            	}
            }
            
            @Override
            public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	if(source.getMainWeapon()!=null) {
            		source.incrementEssenceCount(TFEssence.ARCANE, source.getMainWeapon().getWeaponType().getArcaneCost(), false);
            	}
            }
        };
        allCombatMoves.add(newCombatMove);

        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("twin-strike",
                "twin strike",
                0,
                2,
                CombatMoveType.ATTACK,
                DamageType.UNARMED,
                "moves/strike_twin",
                Util.newArrayListOfValues(Colour.BASE_CRIMSON, Colour.BASE_ORANGE),
                false,
                true,
                false,
                null){

        	@Override
        	public DamageType getDamageType(GameCharacter source) {
                DamageType damageType = DamageType.UNARMED.getParentDamageType(source, null);
                if(source.getMainWeapon() != null) {
                    damageType = source.getMainWeapon().getDamageType();
                }
                return damageType;
        	}
        	
        	public DamageType getOffhandDamageType(GameCharacter source) {
                DamageType damageType = DamageType.UNARMED.getParentDamageType(source, null);
                if(source.getOffhandWeapon() != null) {
                    damageType = source.getOffhandWeapon().getDamageType();
                }
                return damageType;
        	}
        	
            private int getBaseDamageMain(GameCharacter source) {
                AbstractWeapon weapon = source.getMainWeapon();
                int totalDamage;
                if (weapon == null) {
                    totalDamage = source.getUnarmedDamage();
                } else {
                    totalDamage = weapon.getWeaponType().getDamage();
                }
                return (int)(totalDamage * 0.6);
            }

            private int getDamageMain(GameCharacter source, GameCharacter target, boolean isCrit) {
                return (int) (Attack.calculateDamage(source, target, Attack.MAIN, isCrit) * 0.6f);
            }

            private int getBaseDamageOffhand(GameCharacter source) {
                AbstractWeapon weapon = source.getOffhandWeapon();
                int totalDamage;
                if (weapon == null) {
                    totalDamage = source.getUnarmedDamage();
                } else {
                    totalDamage = weapon.getWeaponType().getDamage();
                }
                return (int)(totalDamage * 0.3);
            }

            private int getDamageOffhand(GameCharacter source, GameCharacter target, boolean isCrit) {
                return (int) (Attack.calculateDamage(source, target, Attack.OFFHAND, isCrit) * 0.3f);
            }
            
            private int getArcaneCost(GameCharacter source) {
            	int cost = 0;
            	if(source.getMainWeapon()!=null) {
            		cost += source.getMainWeapon().getWeaponType().getArcaneCost();
    			}
            	if(source.getOffhandWeapon()!=null) {
            		cost += source.getOffhandWeapon().getWeaponType().getArcaneCost();
    			}
            	return cost;
            }

            @Override
            public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
                return new Value<>(true, "Available to everyone as a basic move.");
            }

            @Override
            public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	String costText="";
            	int cost = getArcaneCost(source);
            	if(cost>0) {
            		costText = " Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] to use this attack.";
            	}
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                return UtilText.parse(source, target,
                		(isCrit?"[style.colourExcellent(Critical)]: ":"")
                		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Twin strike</span> "
                				+ (target==null?"[npc.her] target":"[npc2.name]")
//                				+(source.getMainWeapon()==null
//                					?(source.getOffhandWeapon()==null
//                    					?" twice with [npc.her] fists"
//                            			:" with [npc.her] fists and "+source.getOffhandWeapon().getName())
//                					:(source.getOffhandWeapon()==null
//                        				?" with [npc.her] "+source.getMainWeapon().getName()+" and fists"
//                                        :" with [npc.her] "+source.getMainWeapon().getName()+" and "+source.getOffhandWeapon().getName()))
                		+" for "
                			+ getFormattedDamageRange(source, target, getDamageType(source), Attack.MAIN, isCrit, 0.6f)
                		+ " and "
                			+ getFormattedDamageRange(source, target, getOffhandDamageType(source), Attack.OFFHAND, isCrit, 0.3f)
                		+" damage."
                		+costText);
            }

            @Override
            public String getDescription() {
            	GameCharacter source = Main.game.getPlayer();
            	String costText="";
            	int cost = getArcaneCost(source);
            	if(cost>0) {
            		costText = " Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] to use this attack.";
            	}
                return UtilText.parse(source,
                		"Strike [npc.her] target "
                				+(source.getMainWeapon()==null
                					?(source.getOffhandWeapon()==null
                    					?" twice with [npc.her] fists]"
                            			:" with [npc.her] fists and "+source.getOffhandWeapon().getName())
                					:(source.getOffhandWeapon()==null
                        				?" with [npc.her] "+source.getMainWeapon().getName()+" and fists"
                                        :" with [npc.her] "+source.getMainWeapon().getName()+" and "+source.getOffhandWeapon().getName()))
                		+", dealing " + getFormattedDamage(getDamageType(source), getBaseDamageMain(source)) + " and "+ getFormattedDamage(getOffhandDamageType(source), getBaseDamageOffhand(source)) +" damage."
                		+ costText);
            }

            @Override
            public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                int dealtDamage = getDamageType(source).damageTarget(source, target, getDamageMain(source, target, isCrit));
                int dealtDamageOffhand = getOffhandDamageType(source).damageTarget(source, target, getDamageOffhand(source, target, isCrit));
            	
            	StringBuilder attackStringBuilder = new StringBuilder("");

        		if(attackStringBuilder.length()>0) {
        			attackStringBuilder.append("<br/>");
        		}
        		
        		attackStringBuilder.append(formatAttackOutcome(source, target,
        				source.getMainAttackDescription(target, true)
	        				+"<br/>"
	        				+source.getOffhandAttackDescription(target, true),
        				"[npc2.Name] took "
        						+ getFormattedDamage(getDamageType(source), dealtDamage)
	        				+ " and "
	        					+ getFormattedDamage(getOffhandDamageType(source), dealtDamageOffhand)
	        				+ " damage!",
        				isCrit?"":null,
        				isCrit?"[npc2.Name] [npc2.verb(take)] extra damage!":""));


        		if(getArcaneCost(source)>0) { // Hack to restore essences from loss in performOnSelection() method, as they are also subtracted in applyExtraAttackEffects:
            		source.incrementEssenceCount(TFEssence.ARCANE, getArcaneCost(source), false);
            	}
        		
        		if(source.getMainWeapon() != null) {
        			String s = source.getMainWeapon().applyExtraEffects(source, target, true);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        		}
        		if(source.getOffhandWeapon() != null) {
        			String s = source.getOffhandWeapon().applyExtraEffects(source, target, true);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        		}
        		
        		List<String> extraEffects = Combat.applyExtraAttackEffects(source, target, Attack.MAIN, true, isCrit);
        		extraEffects.addAll(Combat.applyExtraAttackEffects(source, target, Attack.OFFHAND, true, isCrit));
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
            public String isUseable(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        		int cost = getArcaneCost(source);
            	if(source.getEssenceCount(TFEssence.ARCANE)<cost) {
            		return "You don't have enough arcane essences to use your weapon! ("+Util.capitaliseSentence(Util.intToString(cost))+" "+(cost==1?"is":"are")+" required.)";
            	}
            	return super.isUseable(source, target, enemies, allies);
            }
            
            @Override
            public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	source.incrementEssenceCount(TFEssence.ARCANE, -getArcaneCost(source), false);
            }
            
            @Override
            public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	source.incrementEssenceCount(TFEssence.ARCANE, getArcaneCost(source), false);
            }
        };
        allCombatMoves.add(newCombatMove);

        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("block",
                "block",
                0,
                1,
                CombatMoveType.DEFEND,
                DamageType.ENERGY,
                "moves/block",
                Util.newArrayListOfValues(Colour.BASE_GREY),
                false,
                false,
                true,
                null) {

            @Override
            public int getBlock(boolean isCrit) {
                return 7 * (isCrit?2:1);
            }

            @Override
            public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
                return new Value<>(true, "Available to everyone as a basic move.");
            }

            @Override
            public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                DamageType damageType = getDamageType(source);
                return (isCrit?"[style.colourExcellent(Critical)]: ":"")
                		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Block</span> "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(isCrit))+ " " + damageType.getName() + " </span>"
                        + " damage.";
            }

            @Override
            public String getDescription() {
                DamageType damageType = getDamageType(Main.game.getPlayer());
                return "Focus on defending yourself, gaining protection against "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(false)) + "</span>"
                        + " damage.";
            }

            @Override
            public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                
                return (formatAttackOutcome(source, target,
        				"[npc.Name] focused on defending [npc.herself].",
        				"[npc.SheIs] now protected against " + getFormattedDamage(getDamageType(source), getBlock(isCrit)) + " damage!",
        				isCrit?"":null,
        				isCrit?"[npc.Name] [npc.verb(double)] [npc.her] block!":""));
                
            }

            @Override
            public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                DamageType damageType = DamageType.ENERGY;
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                target.setShields(damageType, target.getShields(damageType) + getBlock(isCrit));
            }
            
            @Override
            public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                DamageType damageType = DamageType.ENERGY;
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                target.setShields(damageType, target.getShields(damageType) - getBlock(isCrit));
            }
        };
        allCombatMoves.add(newCombatMove);


        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("tease",
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

            private int getBaseDamage(GameCharacter source) {
                return 7;
            }

            private int getDamage(GameCharacter source, GameCharacter target, boolean critical) {
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
                				+" [npc2.name] for " + getFormattedDamage(getDamageType(source), getDamage(source, target, isCrit)) + " damage.");
            }

            @Override
            public String getDescription() {
                return "Tease your enemy, dealing base "
                        + getFormattedDamage(getDamageType(Main.game.getPlayer()), getBaseDamage(Main.game.getPlayer()))
                        + " damage to them.";
            }

            @Override
            public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	StringBuilder sb = new StringBuilder("");
        		
        		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
        		int lustDamage = getDamageType(source).damageTarget(source, target, getDamage(source, target, isCrit));
        		DamageType finalDt = getDamageType(source);
				if(target.getLust()>=100) {
					finalDt = DamageType.ENERGY;
				}
                sb.append(formatAttackOutcome(source, target,
                		source.getSeductionDescription(target),
        				"[npc2.Name] took " + getFormattedDamage(finalDt, lustDamage) + " damage!",
        				isCrit?"":null,
        				isCrit?"[npc2.Name] [npc2.verb(feel)] incredibly turned-on!":""));
                
        		if(source.hasStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION)) {
        			target.addStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 3);
        			sb.append(Spell.getBasicStatusEffectApplication(target, false, Util.newHashMapOfValues(new Value<>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED, 2))));
        		}
        		
        		return sb.toString();
            }
        };
        allCombatMoves.add(newCombatMove);


        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("avert",
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
            public int getBlock(boolean isCrit) {
                return 7 * (isCrit?2:1);
            }

            @Override
            public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
                return new Value<>(true, "Available to everyone as a basic move.");
            }
            
            @Override
            public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                DamageType damageType = getDamageType(source);
                return UtilText.parse(source,  target,
                		(isCrit?"[style.colourExcellent(Critical)]: ":"")
                		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Resist</span> "
		                + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(isCrit))+ " " + damageType.getName() + " </span>"
		                + " damage.");
            }

            @Override
            public String getDescription() {
                DamageType damageType = getDamageType(Main.game.getPlayer());
                return "Resist temptation, gaining protection against "
                		+ "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(getBlock(false)) + "</span>"
                		+ " damage.";
            }

            @Override
            public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                
                return (formatAttackOutcome(source, target,
        				"[npc.Name] focused on resisting [npc2.namePos] seductive moves.",
        				"[npc.SheIs] now protected against " + getFormattedDamage(getDamageType(source), getBlock(isCrit)) + " damage!",
        				isCrit?"":null,
        				isCrit?"[npc.Name] [npc.verb(double)] [npc.her] resistance!":""));
            }

            @Override
            public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                DamageType damageType = getDamageType(source);
                target.setShields(damageType, target.getShields(damageType) + getBlock(canCrit(turnIndex, source, target, enemies, allies)));
            }
            
            @Override
            public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                DamageType damageType = getDamageType(source);
                target.setShields(damageType, target.getShields(damageType) - getBlock(canCrit(turnIndex, source, target, enemies, allies)));
            }

            @Override
            public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
                if(shouldBlunder()) {
                    return (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
                }
                if(source.getLustLevel() == LustLevel.FOUR_IMPASSIONED || source.getLustLevel() == LustLevel.FIVE_BURNING) {
                    return 1.0f + 0.2f * (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
                }
                return super.getWeight(source, enemies, allies);
//                return 0.8f + 0.2f * (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
            }
        };
        allCombatMoves.add(newCombatMove);




        /*=============================================
         *
         *             RACIAL & FETISH
         *
         =============================================*/
        /*=============================================
         *
         *
         *
         */
        Field[] fields = CMSpecialAttack.class.getFields();
		
		for(Field f : fields) {
			if (CombatMove.class.isAssignableFrom(f.getType())) {
				CombatMove combatMove;
				try {
					combatMove = ((CombatMove) f.get(null));

			        allCombatMoves.add(combatMove);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		fields = CMFetishAttack.class.getFields();

		for (Field f : fields) {
			if (CombatMove.class.isAssignableFrom(f.getType())) {
				CombatMove combatMove;
				try {
					combatMove = ((CombatMove) f.get(null));

					allCombatMoves.add(combatMove);

				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}



        /*=============================================
         *
         *                   SPELLS
         *
         =============================================*/
        // Automatically generates respective moves based on the Spell class.
        for(Spell spell : Spell.values()) {
            newCombatMove = new CombatMove(
                    spell.name(),
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
                public String getDescription() {
                    return getAssociatedSpell().getDescription();
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
                public String isUseable(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
                    String reason = super.isUseable(source, target, enemies, allies);
                    if(reason == null) {
                        if(getAssociatedSpell().getSpellSchool() != SpellSchool.FIRE && source.getMana()<getAssociatedSpell().getModifiedCost(source)) {
                            reason = "Not enough aura to cast this spell!";
                        }
                    }
                    return reason;
                }
            };
            newCombatMove.setAssociatedSpell(spell);
            allCombatMoves.add(newCombatMove);
        }

        // Note: Not a spell per se, but recovers mana and is only available to spell casters.
        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("arcane-strike",
                "arcane strike",
                0,
                1,
                CombatMoveType.ATTACK,
                DamageType.PHYSICAL,
                "moves/arcane_strike",
                Util.newArrayListOfValues(Colour.GENERIC_ARCANE),
                false,
                true,
                false,
                null){

        	@Override
        	public DamageType getDamageType(GameCharacter source) {
                DamageType damageType = DamageType.UNARMED.getParentDamageType(Main.game.getPlayer(), null);
                if(source.getMainWeapon() != null) {
                    damageType = source.getMainWeapon().getDamageType();
                }
                return damageType;
        	}
        	
            private int getBaseDamage(GameCharacter source) {
                AbstractWeapon weapon = source.getMainWeapon();
                int totalDamage;
                if (weapon == null) {
                    totalDamage = source.getUnarmedDamage();
                } else {
                    totalDamage = weapon.getWeaponType().getDamage();
                }
                return (int)(totalDamage * 0.25);
            }

            private int getBaseManaGain(GameCharacter source) {
                return getBaseDamage(source)*3;
            }
            
            private int getDamage(GameCharacter source, GameCharacter target) {
            	return (int) (Attack.calculateDamage(source, target, Attack.MAIN, false) * 0.33f);
            }

            private int getManaGain(GameCharacter source, GameCharacter target) {
                return getDamage(source, target)*2;
            }

            @Override
            public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
                return new Value<>(source.getSpells().size() > 0, "Requires knowledge of at least one spell.");
            }

            @Override
            public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	String costText="";
            	if(source.getMainWeapon()!=null && source.getMainWeapon().getWeaponType().getArcaneCost()>0) {
            		int cost = source.getMainWeapon().getWeaponType().getArcaneCost();
            		costText = " Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] "+(Main.game.getPlayer().getMainWeapon().getWeaponType().isMelee()?"per attack.":"to fire.");
    			}
                boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
                return UtilText.parse(source, target,
                		(isCrit?"[style.colourExcellent(Critical)]: ":"")
                		+ "<span style='color:"+this.getColour().toWebHexString()+";'>Arcane-strike</span> "
                				+ (target==null?"[npc.her] target":"[npc2.name]")
                				+ " to deal "
                				+ getFormattedDamageRange(source, target, getDamageType(source), Attack.MAIN, false, 0.33f) + " damage"
                				+ " and gain <span style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>"+(getManaGain(source, target)*(isCrit?2:1))+" "+Attribute.MANA_MAXIMUM.getName()+"</span>."
                				+ costText);
            }

            @Override
            public String getDescription() {
            	GameCharacter source = Main.game.getPlayer();
            	String costText="";
            	if(source.getMainWeapon()!=null && source.getMainWeapon().getWeaponType().getArcaneCost()>0) {
            		int cost = source.getMainWeapon().getWeaponType().getArcaneCost();
            		costText = " Costs [style.boldArcane("+cost+" Arcane essence"+(cost>1?"s":"")+")] "+(Main.game.getPlayer().getMainWeapon().getWeaponType().isMelee()?"per attack.":"to fire.");
    			}
                return UtilText.parse(source,
                		"Strike [npc.her] target with [npc.her] primary weapon, dealing base "
                				+ getFormattedDamage(getDamageType(source), getBaseDamage(source)) + " damage"
                				+ " and recovering base <span style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>"+getBaseManaGain(source)+" "+Attribute.MANA_MAXIMUM.getName()+"</span>."
                				+ costText);
            }

            @Override
            public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
				int dealtDamage = getDamageType(source).damageTarget(source, target, getDamage(source, target));
				int manaGain = getManaGain(source, target);
				if (isCrit) {
					manaGain *= 2;
				}
				
				source.incrementMana(manaGain);
				
            	StringBuilder attackStringBuilder = new StringBuilder("");

        		if(attackStringBuilder.length()>0) {
        			attackStringBuilder.append("<br/>");
        		}
        		
        		attackStringBuilder.append(formatAttackOutcome(source, target,
        				"Harnessing [npc.her] knowledge of the arcane, [npc.name] [npc.verb(focus)] on replenishing [npc.her] aura as [npc.she] [npc.verb(strike)] out at [npc2.name]! "
        						+source.getMainAttackDescription(target, true),
        				"[npc2.Name] took " + getFormattedDamage(getDamageType(source), dealtDamage) + " damage, while [npc.name] recovered"
        						+ " <span style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>"+manaGain+" "+Attribute.MANA_MAXIMUM.getName()+"</span>!",
        				isCrit?"":null,
        				isCrit?"Aura gain was doubled!":""));
        		
        		if(source.getMainWeapon()!=null) { // Hack to restore essences from loss in performOnSelection() method, as they are also subtracted in applyExtraAttackEffects:
            		source.incrementEssenceCount(TFEssence.ARCANE, source.getMainWeapon().getWeaponType().getArcaneCost(), false);
            	}
        		
        		if(source.getMainWeapon() != null) {
        			String s = source.getMainWeapon().applyExtraEffects(source, target, true);
        			attackStringBuilder.append((s.isEmpty()?"":"<br/>")+s);
        		}
        		
        		List<String> extraEffects = Combat.applyExtraAttackEffects(source, target, Attack.MAIN, true, isCrit);
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
            
            @Override
            public String isUseable(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	if(source.getMainWeapon()!=null && source.getEssenceCount(TFEssence.ARCANE)<source.getMainWeapon().getWeaponType().getArcaneCost()) {
            		int cost = source.getMainWeapon().getWeaponType().getArcaneCost();
            		return "You don't have enough arcane essences to use your weapon! ("+Util.capitaliseSentence(Util.intToString(cost))+" "+(cost==1?"is":"are")+" required.)";
            	}
            	return super.isUseable(source, target, enemies, allies);
            }
            
            @Override
            public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	if(source.getMainWeapon()!=null) {
            		source.incrementEssenceCount(TFEssence.ARCANE, -source.getMainWeapon().getWeaponType().getArcaneCost(), false);
            	}
            }
            
            @Override
            public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            	if(source.getMainWeapon()!=null) {
            		source.incrementEssenceCount(TFEssence.ARCANE, source.getMainWeapon().getWeaponType().getArcaneCost(), false);
            	}
            }
        };
        
        allCombatMoves.add(newCombatMove);
    }

    public CombatMove(String identifier,
    		String name,
    		int cooldown,
    		int APcost,
    		CombatMoveType type,
    		DamageType damageType,
    		String pathName,
    		boolean canTargetAllies,
    		boolean canTargetEnemies,
    		boolean canTargetSelf,
    		Map<StatusEffect, Integer> statusEffects) {
    	this(identifier, name, cooldown, APcost, type, damageType, pathName, null, canTargetAllies, canTargetEnemies, canTargetSelf, statusEffects);
    }
    
    /**
     * Default constructor
     * @param identifier = Internal name that the game will refer to when looking up the item. Must be unique for proper searching.
     * @param name = Human friendly name of the action
     * @param cooldown = Amount of turns this action takes to become available. 0 means it's available always, 1 means it is available only once per turn.
     * @param APcost = Cost in action points to perform this action.
     * @param type = Combat action type. Used for various interactions.
     * @param statusEffects = Status effects that are applied when this move is used.
     */
    public CombatMove(String identifier,
    		String name,
    		int cooldown,
    		int APcost,
    		CombatMoveType type,
    		DamageType damageType,
    		String pathName,
    		List<Colour> iconColours,
    		boolean canTargetAllies,
    		boolean canTargetEnemies,
    		boolean canTargetSelf,
			Map<StatusEffect, Integer> statusEffects) {
        this.associatedSpell = null;
        
        this.identifier = identifier;
        this.name = name;
        this.cooldown = cooldown;
        this.APcost = APcost;
        this.type = type;
        this.damageType = damageType;
        this.canTargetEnemies = canTargetEnemies;
        this.canTargetAllies = canTargetAllies;
        this.canTargetSelf = canTargetSelf;
        
        this.statusEffects = statusEffects;
        
        try {
            InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + pathName + ".svg");
            if(is==null) {
                System.err.println("Error! Perk icon file does not exist (Trying to read from '"+pathName+"')!");
            }
            SVGString = Util.inputStreamToString(is);
            
            if(iconColours!=null) {
            	SVGString = SvgUtil.colourReplacement(identifier, iconColours.get(0), iconColours.size()>1?iconColours.get(1):null, iconColours.size()>2?iconColours.get(2):null, SVGString);
            } else {
            	SVGString = SvgUtil.colourReplacement(identifier, type.getColour(), SVGString);
            }

            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * @return A standard formatting of the damage.
     */
	protected static String getFormattedDamage(DamageType damageType, int damage) {
		return "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + String.valueOf(damage) + " " + damageType.getName() + "</span>";
	}

	protected static String getFormattedDamageRange(GameCharacter attacker, GameCharacter defender, DamageType damageType, Attack attackType, boolean isCritical) {
		return getFormattedDamageRange(attacker, defender, damageType, attackType, isCritical, 1);
	}
	
    /**
     * @return A standard formatting of the damage range.
     */
	protected static String getFormattedDamageRange(GameCharacter attacker, GameCharacter defender, DamageType damageType, Attack attackType, boolean isCritical, float multiplier) {
		return "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
					+ String.valueOf(Attack.applyFinalDamageModifiers(attacker, defender, Attack.getMinimumDamage(attacker, defender, attackType)*multiplier, isCritical))
					+"-"
					+ String.valueOf(Attack.applyFinalDamageModifiers(attacker, defender, Attack.getMaximumDamage(attacker, defender, attackType)*multiplier, isCritical))
					+ " "
					+ damageType.getName()
				+ "</span>";
	}
	
    /**
     * @return A standard formatting of an attack's outcome.
     */
	protected static String formatAttackOutcome(GameCharacter source, GameCharacter target, String attackText, String attackEffectText, String criticalText, String criticalEffectText) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<i>"+attackText+"</i>");
		sb.append("<br/>");
		sb.append(attackEffectText);
		
		if(criticalText!=null) {
			sb.append("<br/><span style='color:"+Colour.CRIT.toWebHexString()+";'>Critical</span>: ");
			sb.append("<i>"+criticalEffectText/*criticalText*/+"</i>");
//			if(criticalEffectText!=null && !criticalEffectText.isEmpty()) {
//				sb.append("<br/>");
//				sb.append(criticalEffectText);
//			}
		}
		
		return UtilText.parse(source, target, sb.toString());
	}
    
    /**
     * Returns weight of the action. Used in calculations for AI to pick certain actions. For every unspent AP, AI will try to select an action out of the available ones. The AI will then pick the action with highest weight. Randomness of action selection should be in the weight values itself!
     * @param source Character that uses the weight function.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return Weight of the action. 1.0 is the expected normal weight; weigh the actions accordingly.
     */
    public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
        if(canTargetAllies && allies.isEmpty()) {
            return 0.0f;
        }
        if(shouldBlunder()) {
            return (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
        }
        // Trying to figure out best use cases
        switch(type) {
            default:
                return (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type); // Other types are too nuanced in themselves to have a broad weight generation apply to them
            case ATTACK:
                for(GameCharacter character : enemies) {
                    if(character.getHealthPercentage() < 0.2)
                    {
                        return 1.1f + 0.2f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type); // If the enemy is low on health, chances to attack increase
                    }
                }
                return 0.8f + 0.2f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type); // Attacks aren't sophisticated
            case DEFEND:
                if(source.getHealthPercentage() < 0.2) {
                    return 1.0f + 0.5f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
                }
                return 0.25f + 0.75f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
            case TEASE:
                float weight = 0.8f + 0.2f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
                for(GameCharacter character : enemies) {
                    if(character.getLustLevel() == LustLevel.FOUR_IMPASSIONED || character.getLustLevel() == LustLevel.FIVE_BURNING)
                    {
                        weight += 0.2f;
                        break;
                    }
                }
                if(source.getCorruptionLevel() == CorruptionLevel.FOUR_LUSTFUL || source.getCorruptionLevel() == CorruptionLevel.FIVE_CORRUPT) {
                    weight += 0.4f;
                }
                return weight; // Attacks aren't sophisticated
        }
    }

    /**
     * Returns the preferred target for the action. Prefers to aim at targets with lowest HP values if not forced to select at random. Override for custom behavior
     * @param source Character that uses the target function.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return Character to target with this action.
     */
    public GameCharacter getPreferredTarget(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
        if(canTargetEnemies) {
            if(shouldBlunder()) {
                return enemies.get(Util.random.nextInt(enemies.size()));
            } else {
                float lowestHP = -1;
                GameCharacter potentialCharacter = null;
                for(GameCharacter character : enemies) {
                    if(lowestHP == -1 || character.getHealth() < lowestHP) {
                        potentialCharacter = character;
                        lowestHP = character.getHealth();
                    }
                }
                return potentialCharacter;
            }
        }
        if(canTargetAllies && !allies.isEmpty()) {
            if(shouldBlunder()) {
                return allies.get(Util.random.nextInt(allies.size()));
            }
            else {
                float lowestHP = -1;
                GameCharacter potentialCharacter = null;
                for(GameCharacter character : allies) {
                    if(lowestHP == -1 || character.getHealth() < lowestHP)
                    {
                        potentialCharacter = character;
                        lowestHP = character.getHealth();
                    }
                }
                return potentialCharacter;
            }
        }
        return source;
    }

    /**
     * @param identifier Identifier of the move to find.
     * @return Returns the move if it can find one or null if it can't
     */
    public static CombatMove getMove(String identifier) {
        for (CombatMove move: allCombatMoves) {
            if(move.getIdentifier().equals(identifier)) {
                return move;
            }
        }
        return null;
    }

    /**
     * Gets a prediction that specifies what kind of action will be performed for the player (i.e "The catgirl will attack you for 10 damage" or "The horseboy is planning to buff his ally")
     * @param turnIndex TODO
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the intent of the NPC that uses this action.
     */
    public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        return "There's a plan for you. More pain.";
    }

    /**
     * Performs the action itself. Override to get actual abilities there.
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the action that has been performed.
     */
    public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        return "The action has been performed.";
    }

    /**
     * Performs the action itself. Override to get actual abilities there. This is performed during selection of the action and not during the turn. Use for blocks, AP damage/gains or disrupts.
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the action that has been performed.
     */
    public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        // Nothing. Override it.
    }
    
    /**
     * Applies the reverse of the performOnSelection() method. Override whenever performOnSelection() is overridden. This is performed during deselection of the action and not during the turn.
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the action that has been performed.
     */
    public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        // Nothing. Override it.
    }

    //TODO is this needed when the performOnDeselection() method above exists?
    /**
     * Cancel out the action's effects if it's disrupted or cancelled via AP loss. Is called for every action in the queue for every disruption caused; non disrupted actions get reapplied.
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the action that has been performed.
     */
    public void applyDisruption(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        source.setRemainingAP(source.getRemainingAP() + this.getAPcost() * -1, enemies, allies); // Normally this is the only thing that gets adjusted on selection.
    }

    /**
     * Checks the source character to see if they will have to use the action already with a disruption.
     * @param source
     */
    public boolean isAlreadyDisrupted(GameCharacter source) {
        return source.disruptionByTypeCheck(this.getType());
    }

    /**
     * Returns a string if the character has the move available to select even if they don't "own" it; for example, purity based moves are available to Pure Virgin fetishists without even unlocking them.
     *
     * String contains the reason for why the move is available to them. Otherwise returns null.
     */
    public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
        return null;
    }

    /**
     * Returns a string if action can't be used either due to special constraints or because of AP/cooldowns on a specified target; string specifies rejection reason. Returns null if action can be used without an issue.
     * @param source Character that uses the action.
     * @param source Target for the action. Can be null.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     */
    public String isUseable(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        if(target != null) {
            if(!canTargetSelf && source == target) {
                return "This action can't be used on yourself!";
            }

            if(!canTargetAllies && allies.contains(target) && source != target) {
                return "This action can't be used on your allies!";
            }

            if(!canTargetEnemies && enemies.contains(target)) {
                return "This action can't be used on your enemies!";
            }
        }

        if(source.getMoveCooldown(this.getIdentifier()) > 0) {
            return "This action can't be used since it is still on cooldown! "+String.valueOf(source.getMoveCooldown(this.getIdentifier()))+" turns remaining.";
        }

        if(source.getRemainingAP() < this.getAPcost()) {
            return "This action can't be used since you don't have enough AP!";
        }

        return null;
    }

    /**
     * Returns true based on user settings on how often should the AI make "mistakes" and select actions irrationally.
     * @return
     */
    static boolean shouldBlunder() {
        return Math.random() <= Main.getProperties().AIblunderRate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getBlock(boolean isCrit) {
    	return 0;
    }
    
    public CombatMoveType getType() {
        return type;
    }

    public DamageType getDamageType(GameCharacter source) {
		return damageType;
	}

	public Spell getAssociatedSpell() {
        return associatedSpell;
    }

    public void setAssociatedSpell(Spell associatedSpell) {
        this.associatedSpell = associatedSpell;
    }

    public boolean isCanTargetEnemies() {
        return canTargetEnemies;
    }

    public boolean isCanTargetAllies() {
        return canTargetAllies;
    }

    public boolean isCanTargetSelf() {
        return canTargetSelf;
    }

    public int getAPcost() {
        return APcost;
    }

    public String getDescription() {
        return "This action does nothing.";
    }

    public String getName() {
        return name;
    }

    public String getSVGString() {
        return SVGString;
    }
    
    public Map<StatusEffect, Integer> getStatusEffects() {
		return statusEffects;
	}

	public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	return Util.newArrayListOfValues("It's the third time being used.");
    }
    
    public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	// Normally moves crit on three hits in a row.
        int thisMoveSelected = 0;
        for(Value<GameCharacter, CombatMove> move : source.getSelectedMoves()) {
            if(move.getValue().getIdentifier() == this.getIdentifier()) {
                thisMoveSelected++;
            }
        }
        if(thisMoveSelected>=3 && (turnIndex+1)%3==0) {
            return true;
        }
        return false;
    }
    
    public float getCritStatusEffectDurationIncrease() {
    	return 1;
    }
    
    public Colour getColour() {
    	if(this.getAssociatedSpell() != null) {
			return this.getAssociatedSpell().getSpellSchool().getColour();
		}
		return this.getType().getColour();
    }
    
}
