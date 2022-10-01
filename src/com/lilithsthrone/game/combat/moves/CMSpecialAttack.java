package com.lilithsthrone.game.combat.moves;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.valueEnums.LegConfiguration;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationGirth;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.4
 * @author Innoxia
 */
public class CMSpecialAttack {
	
	public static AbstractCombatMove HORSE_KICK = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "hoof kick",
            1,
            2,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            DamageVariance.NONE,
            "moves/hoof_kick",
            Util.newArrayListOfValues(PresetColour.RACE_HORSE_MORPH),
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 1)),
            Util.newHashMapOfValues()) {

        protected int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, (source.getUnarmedDamage() * 2 * (source.isLegMovementHindered()?0.1f:1)));
        }

        //TODO shouldn't this be override with turnIndex???
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), false);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getLegType().getFootType().equals(FootType.HOOFS), "Available to characters with hoofs.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a powerful hoof-kick to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target), target, false, isTargetAtMaximumLust(target)) + " damage."
            				+ (source.isLegMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 10% as [npc.her] clothing is hindering leg movement!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] strong legs to deliver a powerful kick to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 10% if [npc.her] clothing hinders leg movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target));
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            Value<String, Integer> critDamageValue = new Value<>("", 0);
            if(isCrit) {
            	critDamageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target)/2); // Second kick damage from the crit.
            }
            
            return formatAttackOutcome(source, target,
            		(source.isLegMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] leg movement, [npc.name] [npc.verb(struggle)] to put any power behind [npc.her] kick, dealing minimal damage to [npc2.name]..."
            				:"[npc.Name] [npc.verb(turn)] to one side, before kicking out and powerfully striking [npc2.name] with [npc.her] "+(source.getLegConfiguration()==LegConfiguration.QUADRUPEDAL?"hoofs":"hoof")+"!")
            			+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
            		(isCrit
            			?"[npc.Name] immediately strikes again in an attempt to break through [npc2.namePos] block!"+critDamageValue.getKey()
            			:null),
                	"[npc2.Name] took an additional " + getFormattedDamage(damageType, critDamageValue.getValue(), target, true, maxLust) + " damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	return Util.newArrayListOfValues(
        			 "Hoof-kick fails to break "+(target!=null?UtilText.parse(target,"[npc.namePos]"):"the target's")+" shielding.");
        }
        
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        	int damage = getDamage(turnIndex, source, target);
            int potentialDamage = getDamageType(turnIndex, source).shieldCheckNoDamage(source, target, damage);
            if(potentialDamage<=0) {// != damage) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove CAT_SCRATCH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "scratch",
            1,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 2))) {

        protected int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, ((source.getUnarmedDamage()*1.5f) * (source.isArmMovementHindered()?0.5f:1)));
        }

        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.CAT_MORPH), "Available to characters with feline claws.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a vicious slash to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 50% as [npc.her] clothing is hindering arm movement!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] feline claws to deliver a vicious slash to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 50% if [npc.her] clothing hinders arm movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] arm movement, [npc.name] [npc.verb(struggle)] to land [npc.her] slashing attack, dealing only half damage to [npc2.name]..."
            				:"Extending the claws on [npc.her] anthropomorphic cat-like hands, [npc.name] quickly [npc.verb(dash)] forwards, attempting to slash at [npc2.name]!")+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
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

	public static AbstractCombatMove TAIL_SWIPE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "tail swipe",
            2,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/tail_swipe",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        protected int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*3;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(
            		(source.getTailType().isSuitableForAttack() && source.getTailLength(false)>=100)
            			|| source.getLegConfiguration()==LegConfiguration.TAIL_LONG,
            		"Available to characters who have a suitable tail which is at least [units.sizeShort(100)] long, or a '"+LegConfiguration.TAIL_LONG.getName()+"' lower body.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a thunderous tail-smack to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage.");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] [npc.tailRace]-tail to deliver a thunderous smack to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage.");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"[npc.Name] [npc.verb(turn)] to one side, using the momentum to smack [npc.her] huge [npc.tailRace]-tail straight into [npc2.name]!"+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
            		(isCrit
        				?"[npc.NamePos] tail swipe is particularly effective!"
            			:null),
                    "The duration of '"+StatusEffect.DAZED.getName(target)+"' is doubled!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
            		(source!=null?UtilText.parse(source,"[npc.NamePos]"):"Attacker's")+" tail is at least '<span style='color:"+PenetrationGirth.FIVE_THICK.getColour().toWebHexString()+";'>"+PenetrationGirth.FIVE_THICK.getName()+"</span>'.");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getTailGirth().getValue()>=PenetrationGirth.FIVE_THICK.getValue()) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove SQUIRREL_SCRATCH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "squirrel scratch",
            1,
            1,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/scratch_double",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 1))) {

        protected int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * (source.isArmMovementHindered()?0.5f:1));
        }
        
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().equals(ArmType.SQUIRREL_MORPH), "Available to characters who have squirrel claws.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a vicious scratch to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, false), target, false, isTargetAtMaximumLust(target)) + " damage."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 50% as [npc.her] clothing is hindering arm movement!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] sharp claws to deliver a vicious slash to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 50% if [npc.her] clothing hinders arm movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            int dealtCritDamage2 = 0;
            int dealtCritDamage3 = 0;
            if(isCrit) {
            	dealtCritDamage2 = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, false)).getValue()/2;
            	dealtCritDamage3 = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, false)).getValue()/2;
            }
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] arm movement, [npc.name] [npc.verb(struggle)] to land [npc.her] scratch attack, dealing only half damage to [npc2.name]..."
            				:"Extending the claws on [npc.her] anthropomorphic squirrel-like hands, [npc.name] quickly [npc.verb(dash)] forwards, attempting to scratch at [npc2.name]!")+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
            		(isCrit
            			?"[npc.Name] rapidly [npc.verb(scratch)] [npc2.name] two more times!"
            			:null),
                	"[npc2.Name] took an additional "+getFormattedDamage(damageType, dealtCritDamage2, target, true, maxLust)+", and then another "+getFormattedDamage(damageType, dealtCritDamage3, target, true, maxLust)+" damage!");
        }
    };

	public static AbstractCombatMove WOLF_SAVAGE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "savage attack",
            6,
            3,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/savage_attack",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 3))) {

        protected int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, source.getUnarmedDamage() * 4 * (source.isArmMovementHindered()?0.5f:1));
        }

        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getArmType().getRace().equals(Race.WOLF_MORPH), "Available to characters who have a wolf-morph's arms.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a savage series of slashes to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage."
            				+ (source.isArmMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 50% as [npc.her] clothing is hindering arm movement!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] sharp claws to deliver a savage series of slashes to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage."
            				+ "[style.italicsMinorBad(Damage is reduced to 50% if [npc.her] clothing hinders arm movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(source.isArmMovementHindered()
            				?"As [npc.her] clothing is restricting [npc.her] arm movement, [npc.name] [npc.verb(struggle)] to land [npc.her] savage slashes, dealing only half damage to [npc2.name]..."
            				:"With a savage howl, [npc.name] [npc.verb(launch)] [npc.herself] at [npc2.name], managing to do considerable damage to [npc2.herHim] by raking at [npc2.her] body with [npc.her] sharp claws.")+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
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
        	return Main.combat.getTurn()==0;
        }
    };

	public static AbstractCombatMove ANTLER_HEADBUTT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "antler headbutt",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/antlers",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {

        protected int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }

        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getHornType().equals(HornType.REINDEER_RACK), "Available to characters who have antlers.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a powerful headbutt to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage.");
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] antlers to deliver a powerful headbutt to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage.");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"With a burst of energy, [npc.name] [npc.verb(leap)] forwards, ramming [npc.her] forehead into [npc2.namePos] body and whacking [npc2.herHim] with the sides of [npc.her] antlers."+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
            		(isCrit
            			? "[npc.NamePos] headbutt is particularly effective!"
            			:null),
                    "The duration of '"+StatusEffect.DAZED.getName(target)+"' is doubled!");
        }
        
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((source!=null?UtilText.parse(source,"[npc.NameIsFull]"):"Attacker is")+" at least 50% taller than "+(target!=null?UtilText.parse(target,"[npc.name]"):"the target")+".");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getHeightValue()>target.getHeightValue()*1.5f) {
                return true;
            }
            return false;
        }
    };

	public static AbstractCombatMove COW_HEADBUTT = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "horn headbutt",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/horns",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.DAZED, 2))) {
        protected int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage()*2;
        }
        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasHorns() && !source.getHornType().equals(HornType.REINDEER_RACK), "Available to characters who have horns.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a powerful headbutt to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage.");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] horns to deliver a powerful headbutt to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage.");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		"With a burst of energy, [npc.name] [npc.verb(leap)] forwards, ramming [npc.her] forehead into [npc2.namePos] body and whacking [npc2.herHim] with the sides of [npc.her] horns."+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
            		(isCrit
            			?"[npc.NamePos] headbutt is particularly effective!"
            			:null),
                    "The duration of '"+StatusEffect.DAZED.getName(target)+"' is doubled!");
        }
        @Override
        public float getCritStatusEffectDurationMultiplier() {
        	return 2;
        }
        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues((source!=null?UtilText.parse(source,"[npc.NameIsFull]"):"Attacker is")+" at least 50% taller than "+(target!=null?UtilText.parse(target,"[npc.name]"):"the target")+".");
        }
        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            if(source.getHeightValue()>target.getHeightValue()*1.5f) {
                return true;
            }
            return false;
        }
    };
    
    public static AbstractCombatMove BITE = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "feral bite",
            1,
            2,
            CombatMoveType.ATTACK,
            DamageType.UNARMED,
            "moves/bite",
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 2))) {
    	
    	@Override
        public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
    		Map<AbstractStatusEffect, Integer> effects = Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CRIPPLE, 2));

            if(caster.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)) {
            	effects.put(StatusEffect.POISONED, 6);
            }
            if(caster.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)) {
            	effects.put(StatusEffect.POISONED_LUST, 6);
            }
            
        	if(isCritical) {
        		return effects;
        	}
    		return effects;
    	}
    	
    	@Override
    	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
    		if(!source.isCoverableAreaExposed(CoverableArea.MOUTH)) {
    			return 0;
    		}
    		return super.getWeight(source, enemies, allies);
    	}
    	
        protected int getBaseDamage(GameCharacter source) {
            return source.getUnarmedDamage() * 2 * (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?0:1);
        }

        @Override
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target, boolean isCrit) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), isCrit);
        }
        
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(
				!Collections.disjoint(source.getFaceTypeTags(), Util.newArrayListOfValues(
						BodyPartTag.FACE_MUZZLE,
						BodyPartTag.FACE_FANGS,
//						BodyPartTag.FACE_SHARK_TEETH,
						BodyPartTag.FACE_BEAK
				)),
				"Available to characters with an anthropomorphic face.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Deliver a feral bite to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
            				+ getFormattedDamage(damageType, getDamage(turnIndex, source, target, isCrit), target, false, isTargetAtMaximumLust(target)) + " damage"
            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
        						?" and applying 'poisoned' for 6 turns."
        						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
                					?" and applying 'lust-poisoned' for 6 turns."
                					:"."))
            				+ (!source.isCoverableAreaExposed(CoverableArea.MOUTH)?" [style.italicsBad(Damage is reduced to 0% as [npc.her] clothing is blocking [npc.her] mouth!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can use [npc.her] anthropomorphic face to deliver a feral bite to [npc.her] target, dealing base " + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage"
            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
        						?" and applying 'poisoned' for 6 turns."
        						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
                					?" and applying 'lust-poisoned' for 6 turns."
                					:"."))
            				+ " [style.italicsBad(Damage is reduced to 0% if [npc.her] clothing is blocking [npc.her] mouth.)]");
        }
        
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target, isCrit));
            
            return formatAttackOutcome(source, target,
            		(!source.isCoverableAreaExposed(CoverableArea.MOUTH)
            				?"As [npc.her] clothing is covering [npc.her] mouth, [npc.nameIsFull] unable to do any damage with [npc.her] feral bite..."
            				:"With a burst of energy, [npc.name] [npc.verb(leap)] forwards, trying to bite [npc2.name]!"
            					+ " [npc.Her] [npc.mouth] clamps down on [npc2.her] [npc2.arm],"
										+ " and [npc.she] [npc.verb(manage)] to cause some serious damage with [npc.her] "+(source.getFaceType()==FaceType.HARPY?"sharp beak":"animalistic teeth")+" before [npc2.she] [npc2.verb(pull)] free."
	            				+ (source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH)
	            						?" In the process of being bitten by [npc.namePos] venomous fangs, [npc2.namehasFull] been injected with poison!"
	            						:(source.getFaceType().getTags().contains(BodyPartTag.FACE_VENOMOUS_TEETH_LUST)
	                    					?" In the process of being bitten by [npc.namePos] venomous fangs, [npc2.namehasFull] been injected with lust-poison!"
	                    					:"")))
            			+damageValue.getKey(),
            		"[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
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

    public static AbstractCombatMove TALON_SLASH = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "talon slash",
            1,
            1,
            10,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            DamageVariance.HIGH,
            "moves/talon_slash",
            Util.newArrayListOfValues(PresetColour.RACE_HARPY),
            false,
            true,
            false,
            Util.newHashMapOfValues(),
            Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VULNERABLE, 2))) {

        protected int getBaseDamage(GameCharacter source) {
            return (int) Math.max(1, 20 * (source.isLegMovementHindered()?0.1f:1)); // kerambit damage
        }

        //TODO override as HORSE_KICK should be?
        protected int getDamage(int turnIndex, GameCharacter source, GameCharacter target) {
            DamageType damageType = getDamageType(turnIndex, source);
            return (int) Attack.calculateSpecialAttackDamage(source, target, getType(), damageType, getBaseDamage(source), getDamageVariance(), false);
        }

        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.getLegType().getFootType().equals(FootType.TALONS), "Available to characters with talons.");
        }

        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
                    "Deliver a powerful slash attack with [npc.her] talons to " + (target==null?"[npc.her] target":"[npc2.name]") + ", dealing "
                            + getFormattedDamage(damageType, getDamage(turnIndex, source, target), target, false, isTargetAtMaximumLust(target)) + " damage."
                            + (source.isLegMovementHindered()?" [style.italicsMinorBad(Damage is reduced to 10% as [npc.her] clothing is hindering leg movement!)]":""));
        }

        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source,
                    "[npc.Name] can use [npc.her] sharp talons to deliver a powerful slash attack to [npc.her] target, dealing base "
                            + getFormattedDamage(damageType, getBaseDamage(source), null, false, false) + " damage."
                            + " [style.italicsMinorBad(Damage is reduced to 10% if [npc.her] clothing hinders leg movement.)]");
        }

        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            boolean maxLust = isTargetAtMaximumLust(target);
            Value<String, Integer> damageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target));
            boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
            Value<String, Integer> critDamageValue = new Value<>("", 0);
            if(isCrit) {
                critDamageValue = damageType.damageTarget(source, target, getDamage(turnIndex, source, target)/2); // Second slash damage from the crit.
            }

            return formatAttackOutcome(source, target,
                    (source.isLegMovementHindered()
                            ?"As [npc.her] clothing is restricting [npc.her] leg movement, [npc.name] [npc.verb(struggle)] to make effective use of [npc.her] talons, dealing minimal damage to [npc2.name]..."
                            :"[npc.Name] [npc.verb(vault)] in the air, before landing with outstretched legs and powerfully slashing [npc2.name] with [npc.her] talons!")
                            +damageValue.getKey(),
                    "[npc2.Name] took " + getFormattedDamage(damageType, damageValue.getValue(), target, true, maxLust) + " damage!",
                    (isCrit
                            ?"[npc.Name] immediately [npc.verb(slash)] again after breaking through [npc2.namePos] block!"+critDamageValue.getKey()
                            :null),
                    "[npc2.Name] took an additional " + getFormattedDamage(damageType, critDamageValue.getValue(), target, true, maxLust) + " damage!");
        }

        @Override
        public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return Util.newArrayListOfValues(
                    "Slashing with the talons breaks "+(target!=null?UtilText.parse(target,"[npc.namePos]"):"the target's")+" shielding.");
        }

        @Override
        public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            int damage = getDamage(turnIndex, source, target);
            int potentialDamage = getDamageType(turnIndex, source).shieldCheckNoDamage(source, target, damage);
            if(potentialDamage>0) {
                return true;
            }
            return false;
        }
    };
}
