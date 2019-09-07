package com.lilithsthrone.game.combat;

import java.util.List;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.persona.Relationship;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class CMFetishAttack {
	
	public static CombatMove TEASE_ANAL_RECEIVING = new CombatMove("buttslut-tease",
            "buttslut tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_anal_receiving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_ANAL_RECEIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_ANAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		"Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by offering [npc2.herHim] the use of [npc.her] ass, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
    				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
					(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(turn)] around, presenting [npc.her] [npc.ass+] to [npc2.name] before giving it a slap and [npc.moaning], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You want to come have a squeeze?)]"
									:"[npc.speech(I feel so empty! My asshole's crying out for your cock!)]"),
						"[npc.Name] [npc.verb(turn)] around, squeezing and groping [npc.her] [npc.ass+] as [npc.she] [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Why don't you come and have a go?)]"
									:"[npc.speech(My asshole needs cock!)]"),
						"[npc.Name] [npc.verb(turn)] around, using both hands to grope and spread apart [npc.her] ass cheeks as [npc.she] [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Come and play!)]"
									:"[npc.speech(Please, I want your cock in my ass!)]"),
						"[npc.Name] [npc.verb(turn)] around, spreading apart [npc.her] ass cheeks with both hands as [npc.she] [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You know you want a go!)]"
									:"[npc.speech(My slutty asshole <i>needs</i> your cock!)]"))),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
                	"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_ANAL_GIVING = new CombatMove("anal-tease",
            "anal tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_anal_giving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_ANAL_GIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_ANAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you're going to use [npc2.her] ass, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(grin)] at [npc2.name], before moving [npc.her] gaze down to [npc2.her] [npc2.ass+] and [npc.moaning],"
    							+" [npc.speech(Your ass looks like it needs a good fuck!)]",
    						"[npc.Name] hungrily [npc.verb(stare)] at [npc2.namePos] [npc2.ass+], [npc.moaning],"
    							+" [npc.speech(I'm going to fuck that ass so hard!)]",
    						"Gazing lustfully at [npc2.namePos] [npc2.ass+], [npc.name] [npc.verb(let)] out [npc.a_moan+],"
    							+" [npc.speech(I'm going to pound that sweet ass into the ground!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
                	"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static CombatMove TEASE_VAGINAL_RECEIVING = new CombatMove("pussy-slut-tease",
            "pussy slut tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_vaginal_receiving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_VAGINAL_RECEIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_VAGINAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasVagina(), "Available to characters who both have a vagina and have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by offering [npc2.herHim] the use of [npc.her] pussy, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            			+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }
        
        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base "
            				+ getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slide)] [npc.her] [npc.hands] down between [npc.her] [npc.legs] and [npc.moansVerb], "
								+ "[npc.speech(My pussy's aching for your touch!)]",
						"[npc.Name] [npc.verb(slip)] one [npc.hand] down between [npc.her] [npc.legs] and [npc.moansVerb], "
								+ "[npc.speech(Come fuck my pussy!)]",
						"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.hips+] out a little and [npc.moansVerb], "
								+ "[npc.speech(I'm getting wet already! Come fuck my little pussy!)]",
						"[npc.Name] [npc.verb(wink)] at [npc2.name] and [npc.moansVerb], "
								+ "[npc.speech(My slutty pussy <i>needs</i> some attention!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
                	"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static CombatMove TEASE_VAGINAL_GIVING = new CombatMove("vaginal-tease",
            "vaginal tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_vaginal_giving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_VAGINAL_GIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_VAGINAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasVagina()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasVagina() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasVagina(), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		"Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you're going to use [npc2.her] pussy, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            			+ (!target.hasVagina()
            					?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] does not have a vagina!)]"
            					:(target.getFetishDesire(oppositeFetish).isNegative()
                    					?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]"
                            			:"")));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target doesn't have a vagina or they dislike the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(!target.hasVagina() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(grin)] at [npc2.name], before moving [npc.her] gaze down to between [npc2.her] [npc2.legs] and [npc.moaning],"
							+" [npc.speech(Your pussy looks like it needs a good fuck!)]",
						"[npc.Name] hungrily [npc.verb(stare)] between [npc2.namePos] [npc2.legs], [npc.moaning],"
							+" [npc.speech(I'm going to fuck that pussy so hard!)]",
						"Gazing lustfully between [npc2.namePos] [npc2.legs], [npc.name] [npc.verb(let)] out [npc.a_moan+],"
							+" [npc.speech(I'm going to pound that sweet pussy into the ground!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };

    public static CombatMove TEASE_INCEST = new CombatMove("incest-tease",
            "incest tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_incest",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_INCEST;
    	private Fetish oppositeFetish = Fetish.FETISH_INCEST;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).isRelatedTo(source)) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.isRelatedTo(source) || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasVagina(), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            if(target.isRelatedTo(source)) {
	            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
	            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you're going to have incestuous sex with [npc2.herHim], dealing "
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
	            			+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
            } else {
	            return UtilText.parse(source, target,
	            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
	            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you love fucking your relatives, dealing "
	            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
	            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] is not related to you!)]":""));
            }
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target is not related to you or they dislike the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(!target.isRelatedTo(source) || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            if(target.isRelatedTo(source)) {
    			String dialogue = "";
    			Set<Relationship> rel = source.getRelationshipsTo(target);
    			if(rel.contains(Relationship.Child)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"Let me take care of you, [npc.mommy]!",
    						"Come on, [npc2.mommy]! I just want to take <i>real</i> good care of you!",
    						"[npc2.Mommy]! I just want to show you how much I love you!");
    			} else if(rel.contains(Relationship.Parent)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"Let [npc.mommy] take care of you!",
    						"Don't worry sweetie, [npc.mommy]'s going to take good care of you!",
    						"[npc.Mommy] just wants to show you how much [npc.she] loves you!");
    			} else if(rel.contains(Relationship.Sibling)) {
    				dialogue = UtilText.returnStringAtRandom(
    						"Let your [npc.sis] take care of you!",
    						"Don't worry [npc2.sis], I'm going to take good care of you!",
    						"Come on, [npc2.sis]! I just want to show you how much I love you!");
    			} else {
    				dialogue = UtilText.returnStringAtRandom(
    						"Let [npc.mommy] take care of you!",
    						"Don't worry sweetie, [npc.mommy]'s going to take good care of you!",
    						"[npc.Mommy] just wants to show you how much [npc.she] loves you!");
    			}
            	
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc1.Name] [npc.verb(grin)] at [npc2.name], before thrusting [npc1.her] [npc1.hips] forwards and calling out, "
        								+ "[npc1.speech("+dialogue+")]",
        						"Running [npc1.her] [npc1.hands] down over [npc1.her] groin, [npc1.name] [npc.verb(call)] out to [npc2.name], "
        								+ "[npc1.speech("+dialogue+")]",
        						"[npc1.Name] [npc.verb(let)] out an eager groan as [npc1.she] [npc.verb(thrust)] [npc1.her] [npc1.hips] at [npc2.name], "
        								+ "[npc1.speech("+dialogue+")]")),
                		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
                		(isCrit
                			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
                			:null),
                		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
            	
            	
            } else {
                return formatAttackOutcome(source, target,
                		(UtilText.returnStringAtRandom(
        						"[npc1.Name] [npc.verb(grin)] at [npc2.name], before thrusting [npc1.her] [npc1.hips] forwards and calling out, "
        								+ "[npc1.speech(I'm so dirty that I love nothing more than fucking my relatives! Imagine what I'll do with you!)]",
        						"Running [npc1.her] [npc1.hands] down over [npc1.her] groin, [npc1.name] [npc.verb(call)] out to [npc2.name], "
        								+ "[npc1.speech(It's a shame you aren't my [npc2.sister] or something; there's nothing hotter than siblings fucking!)]",
        						"[npc1.Name] [npc.verb(let)] out an eager groan as [npc1.she] [npc.verb(thrust)] [npc1.her] [npc1.hips] at [npc2.name], "
        								+ "[npc1.speech(I wish you were my relative; I love incestuous sex!)]")),
                		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
                		(isCrit
                			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
                			:null),
                		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
            }
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish and is related to you.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish) && target.isRelatedTo(source);
        }
    };
    
    public static CombatMove TEASE_CUM_STUD = new CombatMove("cum-stud-tease",
            "cum stud tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cum",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_CUM_STUD;
    	private Fetish oppositeFetish = Fetish.FETISH_CUM_ADDICT;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you're going to give [npc2.herHim] your cum, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(grin)] at [npc2.name], before thrusting [npc.her] [npc.hips] and calling out, "
    								+ "[npc.speech(I can't wait to fill you with my [npc.cum]!)]",
    						"Running [npc.her] [npc.hands] down over [npc.her] groin, [npc.name] [npc.verb(call)] out to [npc2.name], "
    								+ "[npc.speech(I bet you can't wait to taste my [npc.cum]!)]",
    						"[npc.Name] [npc.verb(let)] out an eager groan as [npc.she] [npc.verb(thrust)] [npc.her] [npc.hips] at [npc2.name], "
    								+ "[npc.speech(I can't wait to fill you with my [npc.cum]!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_CUM_ADDICT = new CombatMove("cum-addict-tease",
            "cum addict tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cum_addict",
            Util.newArrayListOfValues(Colour.CLOTHING_WHITE),
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_CUM_ADDICT;
    	private Fetish oppositeFetish = Fetish.FETISH_CUM_STUD;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasPenis()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you want [npc2.her] cum, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (!target.hasPenisIgnoreDildo()
            						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] does not have a penis!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]"
                            				:"")));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target has no penis or if they dislike the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(let)] out [npc.a_moan+] as [npc.she] [npc.verb(reveal)] [npc.her] cum-lust, "
    								+ "[npc.speech(Aah! I need cum! I haven't had a meal in ages!)]",
    						"First letting out a hungry whine, [npc.name] [npc.verb(plead)], "
    								+ "[npc.speech(Mmm! I need cum!)]",
    						"[npc.Name] [npc.verb(let)] out a pathetic whine as [npc.she] [npc.verb(beg)], "
    								+ "[npc.speech(I'm so hungry! I need your cum!)]",
    						"[npc.Name] [npc.verb(let)] out a pathetic whine as [npc.she] [npc.verb(beg)] for a meal, "
    								+ "[npc.speech(Please! I need some cum!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_PENIS_RECEIVING = new CombatMove("cock-addict-tease",
            "cock addict tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_cock_addict",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_PENIS_RECEIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_PENIS_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasPenis()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by begging for [npc2.her] cock, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (!target.hasPenisIgnoreDildo()
            						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] does not have a penis!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]"
                            				:"")));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target has no penis or if they dislike the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(!target.hasPenisIgnoreDildo() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(stare)] down between [npc2.namePos] [npc2.legs] as [npc.she] [npc.moanVerb], [npc.speech(I'll be getting a taste of your [npc2.cock] soon enough!)]",
						"[npc.Name] hungrily [npc.verb(gaze)] down between [npc2.namePos] [npc2.legs] and [npc.moanVerb], [npc.speech(I want to feel your cock throbbing inside of me!)]",
						"[npc.Name] [npc.verb(grin)] at [npc2.name], licking [npc.her] [npc.lips+] and flicking [npc.her] gaze down to rest on [npc2.namePos] crotch as [npc.she] [npc.moanVerb], [npc.speech(I'll take good care of your cock!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_PENIS_GIVING = new CombatMove("cock-stud-tease",
            "cock stud tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_dick_dealer",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_PENIS_GIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_PENIS_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasPenisIgnoreDildo(), "Available to characters who both have a penis and have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you're going to give [npc2.herHim] a taste of your cock, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(slide)] [npc.a_hand] down between [npc.her] [npc.legs], before grabbing [npc.her] crotch and [npc.moaning] at [npc2.name], [npc.speech(I can't wait to stuff my cock inside you!)]",
						"[npc.Name] [npc.verb(thrust)] [npc.her] [npc.hips+] forward and [npc.moanVerb] at [npc2.name], [npc.speech(Come get a taste of my cock!)]",
						"[npc.Name] [npc.verb(grin)] at [npc2.name] as [npc.she] [npc.moanVerb], [npc.speech(You're going to love the feeling of my cock!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
	public static CombatMove TEASE_FOOT_RECEIVING = new CombatMove("submissive-foot-tease",
            "submissive foot tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_foot_receiving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_FOOT_RECEIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_FOOT_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by begging for [npc2.herHim] to use [npc2.her] feet, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(gaze)] hungrily down at [npc2.namePos] [npc2.feet] as [npc.she] [npc.moanVerb], [npc.speech(I can't wait to feel your [npc2.feet] all over me!)]",
    						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] as [npc.she] [npc.verb(gaze)] down at [npc2.namePos] [npc2.feet]. [npc.speech(Let me worship your [npc2.feet]!)]",
    						"[npc.Name] [npc.verb(grin)] at [npc2.name], licking [npc.her] [npc.lips+] and flicking [npc.her] gaze down to rest on [npc2.namePos] [npc2.feet] as [npc.she] [npc.moanVerb],"
    								+ " [npc.speech(I just want to worship your [npc2.feet]!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_FOOT_GIVING = new CombatMove("dominant-foot-tease",
            "dominant foot tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_foot_giving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_FOOT_GIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_FOOT_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you're going to use your [npc.feet] on [npc2.herHim], dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(lift)] one of [npc.her] [npc.legs], before pointing [npc.her] [npc.foot] at [npc2.name]. [npc.speech(Get down on your knees and kiss my [npc.feet]!)]",
    						"[npc.Name] [npc.verb(thrust)] one of [npc.her] [npc.feet+] forwards and [npc.moanVerb] at [npc2.name], [npc.speech(You'll be licking my toes soon enough!)]",
    						"[npc.Name] [npc.verb(grin)] at [npc2.name] as [npc.she] [npc.moanVerb], [npc.speech(Crawl over here and kiss my feet!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_ORAL_RECEIVING = new CombatMove("oral-tease",
            "oral tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_oral_receiving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_ORAL_RECEIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_ORAL_GIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] that you want to use [npc2.her] mouth, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(grin)] at [npc2.name], gazing at [npc2.her] [npc2.lips+] as [npc.she] [npc.moansVerb],"
    							+" [npc.speech(I can't wait to put your [npc2.lips] to use!)]",
    						"[npc.Name] hungrily [npc.verb(stare)] at [npc2.namePos] [npc2.lips+], [npc.moaning],"
    							+" [npc.speech(Your tongue belongs between my [npc.legs]!)]",
    						"Gazing lustfully at [npc2.namePos] [npc2.lips+], [npc.name] [npc.verb(let)] out [npc.a_moan+],"
    							+" [npc.speech(I'm going to put your [npc2.lips] to good use!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_ORAL_GIVING = new CombatMove("oral-performer-tease",
            "oral performer tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_oral_giving",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_ORAL_GIVING;
    	private Fetish oppositeFetish = Fetish.FETISH_ORAL_RECEIVING;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by offering [npc2.herHim] the use of [npc.her] mouth, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(open)] [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(You know, I'm pretty skilled with my tongue!)]"
    									:"[npc.speech(Want to find out how deep I can take it?)]"),
    						"[npc.Name] [npc.verb(open)] [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(You'd love the feeling of my tongue!)]"
    									:"[npc.speech(Want to stick your cock down my throat?)]"),
    						"[npc.Name] [npc.verb(open)] [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(I'm the best at eating girls out! Want to see?)]"
    									:"[npc.speech(I'm the best at blowjobs! Want to see?)]"),
    						"[npc.Name] [npc.verb(open)] [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(You know you want to feel my tongue!)]"
    									:"[npc.speech(You know you want me to suck your cock!)]"))),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_BREASTS_OTHERS = new CombatMove("breasts-lover-tease",
            "breasts lover tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_breasts_others",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_BREASTS_OTHERS;
    	private Fetish oppositeFetish = Fetish.FETISH_BREASTS_SELF;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || !this.getPreferredTarget(source, enemies, allies).hasBreasts()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(!target.hasBreasts() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] that you want to use [npc2.her] [npc2.breasts], dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (!target.hasBreasts()
            						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] does not have breasts!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]"
                            				:"")));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target has no breasts or if they dislike the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(!target.hasBreasts() || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            String attackText = "";
            if(target.hasBreasts()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"[npc.Name] [npc.verb(grin)] at [npc2.name], gazing at [npc2.her] [npc2.breasts+] as [npc.she] [npc.moansVerb],"
							+" [npc.speech(I can't wait to get my [npc.hands] on your [npc2.breasts]!)]",
						"[npc.Name] hungrily [npc.verb(stare)] at [npc2.namePos] [npc2.breasts+], [npc.moaning],"
							+" [npc.speech(I'm going to have fun playing with those!)]",
						"Gazing lustfully at [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out [npc.a_moan+],"
								+" [npc.speech(I'm going to have fun with those [npc2.breasts+] of yours!)]")));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"Gazing at [npc2.namePos] chest, [npc.name] [npc.verb(let)] out an annoyed huff,"
									+ " [npc.speech(I wish you had a nice pair of tits that I could use!)]",
							"[npc.Name] [npc.verb(pout)] at [npc2.namePos] [npc2.breasts+], before whining,"
									+ " [npc.speech(Aww... I wish you had some nice tits for me to play with!)]")));
			}
            
            return formatAttackOutcome(source, target,
            		attackText,
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_BREASTS = new CombatMove("breasts-tease",
            "breasts tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_breasts_self",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_BREASTS_SELF;
    	private Fetish oppositeFetish = Fetish.FETISH_BREASTS_OTHERS;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasBreasts(), "Available to characters who both have breasts and who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by offering [npc2.herHim] the use of [npc.her] tits, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"Pushing [npc.her] [npc.breasts+] together, [npc.name] [npc.verb(lean)] forwards and [npc.verb(wink)] at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Let's play together!)]"
    									:"[npc.speech(Come on, I'll let you have a squeeze!)]"),
    						"Pushing [npc.her] [npc.breasts+] together, [npc.name] [npc.verb(lean)] forwards and [npc.verb(wink)] at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Want to have some fun?)]"
    									:"[npc.speech(Come on, you know you want a feel!)]"),
    						"Running [npc.her] [npc.hands] suggestively over [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] lip before pouting at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Don't you want to come and play?)]"
    									:"[npc.speech(I bet you're dying for a touch!)]"),
    						"Running [npc.her] [npc.hands] suggestively over [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] lip before pouting at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Come on! Let's have some fun!)]"
    									:"[npc.speech(~Aah!~ My nipples are so hard!)]"))),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_LACTATION_OTHERS = new CombatMove("milk-lover-tease",
            "milk lover tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_lactation_others",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_LACTATION_OTHERS;
    	private Fetish oppositeFetish = Fetish.FETISH_LACTATION_SELF;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative() || this.getPreferredTarget(source, enemies, allies).getBreastRawMilkStorageValue()==0) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] that you want to milk [npc2.her] [npc2.breasts], dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getBreastRawMilkStorageValue()==0
            						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] is not lactating!)]"
            						:(target.getFetishDesire(oppositeFetish).isNegative()
                    						?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]"
                            				:"")));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target is not lactating or if they dislike the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            String attackText = "";
            if(target.hasBreasts()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"[npc.Name] [npc.verb(grin)] at [npc2.name], gazing at [npc2.her] [npc2.breasts+] as [npc.she] [npc.moansVerb],"
								+" [npc.speech(I'm going to have fun milking your udders!)]",
							"[npc.Name] hungrily [npc.verb(stare)] at [npc2.namePos] [npc2.breasts+], [npc.moaning],"
								+" [npc.speech(I can't wait to give you a good milking!)]",
							"Gazing lustfully at [npc2.namePos] [npc2.breasts+], [npc.name] [npc.verb(let)] out [npc.a_moan+],"
									+" [npc.speech(I'm going to have fun milking those [npc2.breasts+] of yours!)]")));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
							"Gazing at [npc2.namePos] chest, [npc.name] [npc.verb(let)] out an annoyed huff,"
									+ " [npc.speech(I wish you had a nice pair of tits that I could milk!)]",
							"[npc.Name] [npc.verb(pout)] at [npc2.namePos] [npc2.breasts+], before whining,"
									+ " [npc.speech(Aww... I wish you had some nice tits for me to milk!)]")));
			}
            
            return formatAttackOutcome(source, target,
            		attackText,
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_LACTATION = new CombatMove("lactation-tease",
            "lactation tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_lactation_self",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_LACTATION_SELF;
    	private Fetish oppositeFetish = Fetish.FETISH_LACTATION_OTHERS;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasBreasts(), "Available to characters who both have breasts and who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by offering to let [npc2.herHim] milk [npc.her] tits, dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"Pushing [npc.her] [npc.breasts+] together, [npc.name] [npc.verb(pout)] at [npc2.name], "
    								+ "[npc.speech(My breasts are so sore! Please, I need milking!)]",
    						"Pushing [npc.her] [npc.breasts+] together, [npc.name] [npc.verb(let)] out a whine as [npc.she] [npc.verb(bat)] [npc.her] eyelids at [npc2.name], "
    								+ "[npc.speech(I'm so full of milk! I need help!)]",
    						"Running [npc.her] [npc.hands] suggestively over [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] lip before pouting at [npc2.name], "
    								+ "[npc.speech(Please, I need milking!)]",
    						"Running [npc.her] [npc.hands] suggestively over [npc.her] [npc.breasts+], [npc.name] [npc.verb(bite)] [npc.her] lip before pouting at [npc2.name], "
    								+ "[npc.speech(~Aah!~ I'm so full of milk! Please, I need your help!)]")),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_FERTILITY = new CombatMove("fertility-tease",
            "fertility tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_pregnancy",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_PREGNANCY;
    	private Fetish oppositeFetish = Fetish.FETISH_IMPREGNATION;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who both have a vagina and who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] how fertile [npc.sheIsFull], dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            String attackText = "";
            if(source.isVisiblyPregnant()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"Running [npc.her] [npc.hands] over [npc.her] pregnant belly, [npc.name] [npc.verb(wink)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Let me tell you the best ways to get knocked up!)]"
									:"[npc.speech(Ever wanted to fuck a pregnant [npc.girl]?)]"),
						"[npc.Name] pushes out [npc.her] pregnant belly and [npc.verb(giggle)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(I'll tell you all about how I got knocked up!)]"
									:"[npc.speech(Fucking pregnant [npc.girls] is the best thing ever! Come on, I'll show you!)]"),
						"Sliding [npc.her] [npc.hands] over [npc.her] pregnant bump, [npc.name] [npc.verb(pout)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Come have a feel!)]"
									:"[npc.speech(Pregnant [npc.girls] are the the best fucks around!)]"),
						"Posturing so as to draw attention to [npc.her] pregnant bump, [npc.name] [npc.verb(bite)] [npc.her] lip at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Come on, why don't we have some fun?!)]"
									:"[npc.speech(Want to find out how good it feels to fuck a pregnant [npc.girl]?)]"))));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"Rubbing [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] [npc.verb(bite)] [npc.her] [npc.lip] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Aww... I wish I were pregnant so bad!)]"
									:"[npc.speech(I need to get pregnant so bad! Come fill me your cum already!)]"),
						"Sliding [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] [npc.verb(pout)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Aww... I wish I were pregnant...)]"
									:"[npc.speech(Come put some kids in my belly already!)]"),
						"Sliding [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] [npc.verb(pout)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(I wish I were pregnant...)]"
									:"[npc.speech(Come fill my womb your yummy cum! I want to get pregnant already!)]"),
						"Rubbing [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] [npc.verb(bite)] [npc.her] [npc.lip] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(I wish I were pregnant...)]"
									:"[npc.speech(Come breed me already! My womb's waiting for your seed!)]"))));
			}
            
            return formatAttackOutcome(source, target,
            		attackText,
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_VIRILITY = new CombatMove("virility-tease",
            "virility tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_impregnation",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_IMPREGNATION;
    	private Fetish oppositeFetish = Fetish.FETISH_PREGNANCY;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish) && source.hasPenisIgnoreDildo(), "Available to characters who both have a penis and who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] you're going to breed [npc2.herHim], dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            String attackText = "";
			if(target.isVisiblyPregnant()) {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"Reaching down to grab [npc.her] crotch, [npc.name] [npc.verb(wink)] at [npc2.name], "
								+ "[npc.speech(Shame you're already pregnant... But that won't stop me from filling your pussy with my [npc.cum+]!)]",
						"Running [npc.her] [npc.hands] down over [npc.her] crotch, [npc.name] [npc.verb(wink)] at [npc2.name], "
								+ "[npc.speech(You may already be pregnant, but that won't stop me from giving you a nice creampie!)]",
						"Sliding [npc.her] [npc.hands] down to draw attention to [npc.her] crotch, [npc.name] [npc.verb(grin)] at [npc2.name], "
								+ "[npc.speech(Don't think that being pregnant will stop me from filling your cunt with my [npc.cum+]!)]",
						"Reaching down to grab [npc.her] crotch, [npc.name] [npc.verb(grin)] at [npc2.name], "
								+ "[npc.speech(It's a shame you're already pregnant... But I'm still going to fill you up with my [npc.cum+]!)]")));
				
			} else {
				attackText = UtilText.parse(source, target,
						(UtilText.returnStringAtRandom(
						"Reaching down to grab [npc.her] crotch, [npc.name] [npc.verb(wink)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(Want to get knocked up? My cum's crying out to fill your womb!)]"
									:"[npc.speech(Maybe I should give you a pussy, then knock you up!)]"),
						"Running [npc.her] [npc.hands] down over [npc.her] crotch, [npc.name] [npc.verb(wink)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(My seed's so potent, I'm going to knock you up on the first fuck!)]"
									:"[npc.speech(If only you had a pussy, I'd breed you real good!)]"),
						"Sliding [npc.her] [npc.hands] down to draw attention to [npc.her] crotch, [npc.name] [npc.verb(grin)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(My seed's so potent, you're going to get knocked up from our first fuck!)]"
									:"[npc.speech(Perhaps I'll give you a nice tight cunt, then knock you up on your first fuck!)]"),
						"Reaching down to grab [npc.her] crotch, [npc.name] [npc.verb(grin)] at [npc2.name], "
								+ (target.getAppearsAsGender().isFeminine() || (target.isAreaKnownByCharacter(CoverableArea.VAGINA, source) && target.hasVagina())
									?"[npc.speech(I'm going to fuck you pregnant!)]"
									:"[npc.speech(You need a nice tight pussy, so I can fuck you pregnant!)]"))));
			}
            
            return formatAttackOutcome(source, target,
            		attackText,
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_DOMINANT = new CombatMove("dominant-tease",
            "dominant tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_dominant",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_DOMINANT;
    	private Fetish oppositeFetish = Fetish.FETISH_SUBMISSIVE;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by telling [npc2.herHim] that you're going to dominate [npc2.herHim], dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"Grinning lustfully at [npc2.name], [npc.name] [npc.verb(growl)], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(I'm going to fuck you into next week, bitch!)]"
    									:"[npc.speech(I'm going to make you my bitch!)]"),
    						"With an evil grin, [npc.name] [npc.verb(growl)] at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(You're going to get the fucking of a lifetime!)]"
    									:"[npc.speech(You're going to be my bitch soon enough!)]"),
    						"With a powerful stare, [npc.name] [npc.verb(growl)] at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(I'm going to make you my bitch!)]"
    									:"[npc.speech(You're going to be a mewling little bitch by the time I'm done with you!)]"),
    						"[npc.Name] lets out a menacing growl as [npc.she] [npc.verb(stare)] lustfully at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(You're going to be a good little submissive slut for me!)]"
    									:"[npc.speech(I'm going to fuck you so hard, you'll be squealing like a little bitch!)]"))),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
    
    public static CombatMove TEASE_SUBMISSIVE = new CombatMove("submissive-tease",
            "submissive tease",
            0,
            1,
            CombatMoveType.TEASE,
            DamageType.LUST,
            "fetishes/fetish_submissive",
            false,
            true,
            false,
			null) {

    	private Fetish associatedFetish = Fetish.FETISH_SUBMISSIVE;
    	private Fetish oppositeFetish = Fetish.FETISH_DOMINANT;

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(this.getPreferredTarget(source, enemies, allies).getFetishDesire(oppositeFetish).isNegative()) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}

        private int getBaseDamage(GameCharacter source, boolean isCrit) {
            return 5 * (isCrit?3:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            if(target.getBreastRawMilkStorageValue()==0 || target.getFetishDesire(oppositeFetish).isNegative()) {
            	return 1;
            }
            return (int) Attack.calculateSeductionDamage(source, target, getBaseDamage(source, isCrit), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasFetish(associatedFetish), "Available to characters who have the "+associatedFetish.getName(source)+" fetish.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            
            return UtilText.parse(source, target,
            		(isCrit?"[style.colourExcellent(Critical)]: ":"")
            		+ "Tease " + (target==null?"[npc.her] target":"[npc2.name]") + " by asking [npc2.herHim] to dominate [npc.herHim], dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, isCrit), target, false) + " damage."
            				+ (target.getFetishDesire(oppositeFetish).isNegative()?" [style.italicsMinorBad(Damage is reduced to 1 as [npc2.name] [npc2.verb(dislike)] the "+oppositeFetish.getName(source)+" fetish!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] "+associatedFetish.getName(Main.game.getPlayer())+" fetish to tease [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer(), false), null, false) + " damage."
            				+ " [style.italicsMinorBad(Damage is reduced to 1 if the target dislikes the "+oppositeFetish.getName(null)+" fetish.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = 0;
            if(target.getFetishDesire(oppositeFetish).isNegative()) {
            	dealtDamage = 1;
            	isCrit = false;
            } else {
            	dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, isCrit)));
            }
            
            return formatAttackOutcome(source, target,
            		(UtilText.returnStringAtRandom(
    						"[npc.Name] [npc.verb(tilt)] [npc.her] head down in a sign of submission, before looking up with big, innocent eyes, "
    								+ "[npc.speech(I'll be a good [npc.girl]! I promise!)]",
    						"[npc.Name] [npc.verb(bite)] [npc.her] [npc.lip] and [npc.verb(shuffle)] [npc.her] [npc.feet] as [npc.she] [npc.do] [npc.her] best to look as weak as possible, "
    								+ "[npc.speech(I'll do anything you want!)]",
    						"[npc.Name] [npc.verb(shuffle)] [npc.her] [npc.feet] and [npc.verb(make)] [npc.herself] as small as possible, before lustfully gazing up at [npc2.name], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(Please! Treat me like your little bitch!)]"
    									:"[npc.speech(Please! Make me your little fuck-toy!)]"),
    						"[npc.Name] [npc.verb(put)] on [npc.her] most innocent look as [npc.she] [npc.verb(gaze)] lustfully into [npc2.namePos] [npc2.eyes], "
    								+ (target.getAppearsAsGender().isFeminine()
    									?"[npc.speech(I'll be your little slave!)]"
    									:"[npc.speech(I'll be a good little cock-sleeve! I promise)]"))),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc2.NameIsFull] incredibly turned on, and takes triple damage!"
            			:null),
            		"[npc2.NameIsFull] incredibly turned on, and takes triple damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 (target!=null?UtilText.parse(target,"[npc.NamehasFull]"):"The target has")+" the "+oppositeFetish.getName(target)+" fetish.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return target.hasFetish(oppositeFetish);
        }
    };
}
