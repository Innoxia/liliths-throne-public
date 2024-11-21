package com.lilithsthrone.game.combat.moves;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.3.7.8
 * @version 0.3.7.8
 * @author Innoxia
 */
public class CMWeaponSpecials {
	
	public static AbstractCombatMove MKAR_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "mag dump",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 21_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_mkar")), "Available to characters who have an equipped MKAR.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Empty an entire 30-round magazine in full-auto, dealing "+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+" damage for each bullet that hits."
            				+ "<br/>[style.italicsGood(Targets every enemy!)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can switch to full-auto and empty an entire 30-round magazine at [npc.her] enemies, dealing "+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+" damage for each bullet that hits."
            				+ "<br/>[style.italicsGood(Targets every enemy!)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 30 - 5 - Util.random.nextInt(6); // Hit with 20-25 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();
            
            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "rifle";
    		}
            
        	attackDesc.append(UtilText.parse(source,
        			"Pushing the fire selector on [npc.her] "+weaponName+" up into fully automatic, [npc.name] [npc.verb(take)] aim and [npc.verb(pull)] the trigger, unleashing a deafening, deadly hail of bullets at "
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her] enemies")+"!"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull] hit by [style.boldTerrible("+finalBullets+")] bullet"+(finalBullets==1?"":"s")+"!"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name] took "+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+" damage!"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };
    

	public static AbstractCombatMove BR14_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "mag dump",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 26_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_br14")), "Available to characters who have an equipped BR14.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Empty an entire 20-round magazine in full-auto, dealing "+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+" damage for each bullet that hits."
            				+ "<br/>[style.italicsGood(Targets every enemy!)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can switch to full-auto and empty an entire 20-round magazine at [npc.her] enemies, dealing "+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+" damage for each bullet that hits."
            				+ "<br/>[style.italicsGood(Targets every enemy!)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 20 - 5 - Util.random.nextInt(11); // Hit with 5-15 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();

            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "rifle";
    		}
    		
        	attackDesc.append(UtilText.parse(source,
        			"Flicking the fire selector on [npc.her] "+weaponName+" into fully automatic, [npc.name] [npc.verb(take)] aim and [npc.verb(pull)] the trigger, unleashing a deafening, deadly hail of bullets at "
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her] enemies")+"!"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull] hit by [style.boldTerrible("+finalBullets+")] bullet"+(finalBullets==1?"":"s")+"!"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name] took "+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+" damage!"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };
    
    public static AbstractCombatMove FAUXMAS_MAG_DUMP = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "mag dump",
            2,
            2,
            CombatMoveType.ATTACK,
            DamageType.PHYSICAL,
            "moves/mag_dump",
            Util.newArrayListOfValues(PresetColour.BASE_ORANGE),
            false,
            true,
            false,
			null) {
		private int getBulletDamage() {
			return 18_000;
		}
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_gun_famase")), "Available to characters who have an equipped FAUXMAS.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, target,
            		"Empty an entire 25-round magazine in full-auto, dealing "+getFormattedDamage(damageType, getBulletDamage(), target, false, isTargetAtMaximumLust(target))+" damage for each bullet that hits."
            				+ "<br/>[style.italicsGood(Targets every enemy!)]");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            DamageType damageType = getDamageType(turnIndex, source);
            return UtilText.parse(source, 
            		"[npc.Name] can switch to full-auto and empty an entire 25-round magazine at [npc.her] enemies, dealing "+getFormattedDamage(damageType, getBulletDamage(), null, false, false)+" damage for each bullet that hits."
            				+ "<br/>[style.italicsGood(Targets every enemy!)]");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            DamageType damageType = getDamageType(turnIndex, source);
            
            StringBuilder attackDesc = new StringBuilder();
            StringBuilder attackEffects = new StringBuilder();
            
            int bulletsHit = 25 - 5 - Util.random.nextInt(6); // Hit with 15-20 bullets.
            int bulletsPerEnemy = bulletsHit/enemies.size();
            
            String weaponName = "";
    		for(AbstractWeapon weapon : source.getMainWeaponArray()) {
    			if(weapon.getCombatMoves().contains(this)) {
    				weaponName = weapon.getName();
    				break;
    			}
    		}
    		if(weaponName.isEmpty()) {
	    		for(AbstractWeapon weapon : source.getOffhandWeaponArray()) {
	    			if(weapon.getCombatMoves().contains(this)) {
	    				weaponName = weapon.getName();
	    				break;
	    			}
	    		}
    		}
    		if(weaponName.isEmpty()) {
    			weaponName = "rifle";
    		}
            
        	attackDesc.append(UtilText.parse(source,
        			"Pushing the fire selector on [npc.her] "+weaponName+" into fully automatic, [npc.name] [npc.verb(take)] aim and [npc.verb(pull)] the trigger, unleashing a deafening, deadly hail of bullets at "
        				+(enemies.size()==1?UtilText.parse(target, "[npc.name]"):"[npc.her] enemies")+"!"));
            for(int i=0; i<enemies.size(); i++) {
            	GameCharacter enemy = enemies.get(i);
            	int finalBullets = Math.max(1, bulletsPerEnemy-Util.random.nextInt(3));
            	boolean maxLust = isTargetAtMaximumLust(target);
                Value<String, Integer> damageValue = damageType.damageTarget(source, enemy, getBulletDamage()*finalBullets);
            	attackDesc.append(UtilText.parse(enemy,
            			"<br/>[npc.NameIsFull] hit by [style.boldTerrible("+finalBullets+")] bullet"+(finalBullets==1?"":"s")+"!"+damageValue.getKey()));
            	if(i>0) {
            		attackEffects.append("<br/>");
            	}
            	attackEffects.append(UtilText.parse(enemy, "[npc.Name] took "+getFormattedDamage(damageType, damageValue.getValue(), enemy, true, maxLust)+" damage!"));
            }
            
            return formatAttackOutcome(source, target,
            		attackDesc.toString(),
            		attackEffects.toString(),
            		null,
                	null);
        }
    };
}
