package com.lilithsthrone.game.combat;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.Race;
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
public class CMSpecialAttack {
	
	public static CombatMove HORSE_KICK = new CombatMove("hoof-kick",
            "hoof kick",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/hoof_kick",
            Util.newArrayListOfValues(Colour.RACE_HORSE_MORPH),
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 1))) {

        private int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, (source.getUnarmedDamage() * 2 * (source.isLegMovementHindered()?0.1f:1)));
        }

        private int getDamage(GameCharacter source, GameCharacter target) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getLegType().getFootType().equals(FootType.HOOFS), "Available to characters with hoofs.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a powerful hoof-kick to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target), target, false) + " damage."
            				+ (source.isLegMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 10% as [npc.her] clothing is hindering leg movement!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] strong legs to deliver a powerful kick to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 10% if [npc.her] clothing hinders leg movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            int dealtDamage = damageType.damageTarget(source, target, getDamage(source, target));
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtCritDamage = 0;
            if(isCrit) {
            	dealtCritDamage = damageType.damageTarget(source, target, getDamage(source, target))/2; // Second kick damage from the crit.
            }
            
            return formatAttackOutcome(source, target,
            		(source.isLegMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] leg movement, [npc.name] [npc.verb(struggle)] to put any power behind [npc.her] kick, dealing minimal damage to [npc2.name]..."
            				:"[npc.Name] [npc.verb(turn)] to one side, before kicking out and powerfully striking [npc2.name] with [npc.her] "+(source.getLegConfiguration()==LegConfiguration.TAUR?"hoofs":"hoof")+"!"),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc.Name] immediately strikes again in an attempt to break through [npc2.namePos] block!"
            			:null),
                	"[npc2.Name] took an additional " + getFormattedDamage(damageType, dealtCritDamage, target, true) + " damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 "Hoof-kick fails to break "+(target!=null?UtilText.parse(target,"[npc.namePos]"):"the target's")+" shielding.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int damage = getDamage(source, target);
            int potentialDamage = getDamageType(source).shieldCheckNoDamage(source, target, damage);
            if(potentialDamage != damage) {
                return true;
            }
            return false;
        }
    };

	public static CombatMove CAT_SCRATCH = new CombatMove("cat-scratch",
            "scratch",
            0,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VULNERABLE, 2))) {

        private int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, ((source.getUnarmedDamage()*1.5f) * (source.isArmMovementHindered()?0.5f:1)));
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.CAT_MORPH), "Available to characters with feline claws.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a vicious slash to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, false), target, false) + " damage."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 50% as [npc.her] clothing is hindering arm movement!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] feline claws to deliver a vicious slash to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 50% if [npc.her] clothing hinders arm movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, false));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] arm movement, [npc.name] [npc.verb(struggle)] to land [npc.her] slashing attack, dealing only half damage to [npc2.name]..."
            				:"Extending the claws on [npc.her] anthropomorphic cat-like hands, [npc.name] quickly [npc.verb(dash)] forwards, attempting to slash at [npc2.name]!"),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc.NamePos] slash is particularly effective!"
            			:null),
                	"The duration of '"+StatusEffect.VULNERABLE.getName(target)+"' is doubled!");
        }
        
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
    };

	public static CombatMove ALLIGATOR_TAIL_SWIPE = new CombatMove("tail-swipe",
            "tail swipe",
            2,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/tail_swipe",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 3))) {

        private int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*3;
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getTailType().equals(TailType.ALLIGATOR_MORPH), "Available to characters who have an alligator tail.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a thunderous tail-smack to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, false), target, false) + " damage.");
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] alligator tail to deliver a thunderous smack to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage.");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, false)));
            
            return formatAttackOutcome(source, target,
            		"[npc.Name] [npc.verb(turn)] to one side, using the momentum to smack [npc.her] huge, alligator-like tail straight into [npc2.name]!",
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
        				?"[npc.NamePos] tail swipe is particularly effective!"
            			:null),
                    "The duration of '"+StatusEffect.DAZED.getName(target)+"' is doubled!");
        }
        
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
    };

	public static CombatMove SQUIRREL_SCRATCH = new CombatMove("squirrel-scratch",
            "squirrel scratch",
            0,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch_double",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VULNERABLE, 1))) {

        private int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * (source.isArmMovementHindered()?0.5f:1));
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.SQUIRREL_MORPH), "Available to characters who have squirrel claws.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a vicious scratch to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, false), target, false) + " damage."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 50% as [npc.her] clothing is hindering arm movement!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] sharp claws to deliver a vicious slash to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 50% if [npc.her] clothing hinders arm movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, false));
            int dealtCritDamage2 = 0;
            int dealtCritDamage3 = 0;
            if(isCrit) {
            	dealtCritDamage2 = damageType.damageTarget(source, target, getDamage(source, target, false))/2;
            	dealtCritDamage3 = damageType.damageTarget(source, target, getDamage(source, target, false))/2;
            }
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] arm movement, [npc.name] [npc.verb(struggle)] to land [npc.her] scratch attack, dealing only half damage to [npc2.name]..."
            				:"Extending the claws on [npc.her] anthropomorphic squirrel-like hands, [npc.name] quickly [npc.verb(dash)] forwards, attempting to scratch at [npc2.name]!"),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc.Name] rapidly [npc.verb(scratch)] [npc2.name] two more times!"
            			:null),
                	"[npc2.Name] took an additional "+getFormattedDamage(damageType, dealtCritDamage2, target, true)+", and then another "+getFormattedDamage(damageType, dealtCritDamage3, target, true)+" damage!");
        }
    };

	public static CombatMove WOLF_SAVAGE = new CombatMove("savage-attack",
            "savage attack",
            6,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/savage_attack",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CRIPPLE, 3))) {

        private int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * 4 * (source.isArmMovementHindered()?0.5f:1));
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().getRace().equals(Race.WOLF_MORPH), "Available to characters who have a wolf-morph's arms.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a savage series of slashes to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, false), target, false) + " damage."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 50% as [npc.her] clothing is hindering arm movement!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] sharp claws to deliver a savage series of slashes to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 50% if [npc.her] clothing hinders arm movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = damageType.damageTarget(source, target, getDamage(source, target, false));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] arm movement, [npc.name] [npc.verb(struggle)] to land [npc.her] savage slashes, dealing only half damage to [npc2.name]..."
            				:"With a savage howl, [npc.name] [npc.verb(launch)] [npc.herself] at [npc2.name], managing to do considerable damage to [npc2.herHim] by raking at [npc2.her] body with [npc.her] sharp claws."),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"The ferocity of [npc.namePos] attack catches [npc2.name] off guard!"
            			:null),
                    "The duration of '"+StatusEffect.CRIPPLE.getName(target)+"' is doubled!");
        }
        
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 "Used in the first turn of combat.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Combat.getTurn()==0;
        }
    };

	public static CombatMove ANTLER_HEADBUTT = new CombatMove("antler-headbutt",
            "antler headbutt",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/antlers",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 3))) {

        private int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getHornType().equals(HornType.REINDEER_RACK), "Available to characters who have antlers.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a powerful headbutt to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, false), target, false) + " damage.");
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] antlers to deliver a powerful headbutt to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage.");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, false)));
            
            return formatAttackOutcome(source, target,
            		"With a burst of energy, [npc.name] [npc.verb(leap)] forwards, ramming [npc.her] forehead into [npc2.namePos] body and whacking [npc2.herHim] with the sides of [npc.her] antlers.",
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			? "[npc.NamePos] headbutt is particularly effective!"
            			:null),
                    "The duration of '"+StatusEffect.DAZED.getName(target)+"' is doubled!");
        }
        
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
    };

	public static CombatMove COW_HEADBUTT = new CombatMove("horn-headbutt",
            "horn headbutt",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/horns",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 3))) {

        private int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasHorns() && !source.getHornType().equals(HornType.REINDEER_RACK), "Available to characters who have horns.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a powerful headbutt to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, false), target, false) + " damage.");
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] horns to deliver a powerful headbutt to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage.");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, false)));
            
            return formatAttackOutcome(source, target,
            		"With a burst of energy, [npc.name] [npc.verb(leap)] forwards, ramming [npc.her] forehead into [npc2.namePos] body and whacking [npc2.herHim] with the sides of [npc.her] horns.",
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc.NamePos] headbutt is particularly effective!"
            			:null),
                    "The duration of '"+StatusEffect.DAZED.getName(target)+"' is doubled!");
        }
        
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
    };
    
    public static CombatMove BITE = new CombatMove("bite",
            "feral bite",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/bite",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CRIPPLE, 2))) {

    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(!source.isCoverableAreaExposed(CoverableArea.MOUTH)) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}
    	
        private int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage() * 2 * (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?0:1);
        }

        private int getDamage(GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, damageType, getBaseDamage(source), DamageVariance.NONE, isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(
            		source.getFaceRace()!=Race.HUMAN
						&& source.getFaceRace()!=Race.ANGEL
						&& source.getFaceRace()!=Race.DEMON
						&& source.getFaceRace()!=Race.ELEMENTAL,
					"Available to characters with an anthropomorphic face.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            return UtilText.parse(source, target,
            		"Deliver a feral bite to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(source, target, false), target, false) + " damage."
            				+ (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?" [style.italicsBad(Damage is reduced to 0% as [npc.her] clothing is blocking [npc.her] mouth!)]":""));
        }

        @Override
        public String getDescription() {
            DamageType damageType = getDamageType(Main.game.getPlayer());
            return UtilText.parse(Main.game.getPlayer(), 
            		"[npc.Name] can use [npc.her] anthropomorphic face to deliver a feral bite to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(Main.game.getPlayer()), null, false) + " damage."
            				+ " [style.italicsBad(Damage is reduced to 0% if [npc.her] clothing is blocking [npc.her] mouth.)]");
        }
        
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            int dealtDamage = (int) (damageType.damageTarget(source, target, getDamage(source, target, false)));
            
            return formatAttackOutcome(source, target,
            		(!source.isCoverableAreaExposed(CoverableArea.MOUTH)
            				?"As [npc.her] clothing is covering [npc.her] mouth, [npc.nameIsFull] unable to do any damage with [npc.her] feral bite..."
            				:"With a burst of energy, [npc.name] [npc.verb(leap)] forwards, trying to bite [npc2.name]!"
            					+ " [npc.Her] [npc.mouth] clamps down on [npc2.her] [npc2.arm],"
										+ " and [npc.she] [npc.verb(manage)] to cause some serious damage with [npc.her] "+(source.getFaceType()==FaceType.HARPY?"sharp beak":"animalistic teeth")+" before [npc2.she] [npc2.verb(pull)] free."),
            		"[npc2.Name] took " + getFormattedDamage(damageType, dealtDamage, target, true) + " damage!",
            		(isCrit
            			?"[npc.NamePos] feral bite is particularly effective!"
            			:null),
                    "The duration of '"+StatusEffect.CRIPPLE.getName(target)+"' is doubled!");
        }
        
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
    };
}
