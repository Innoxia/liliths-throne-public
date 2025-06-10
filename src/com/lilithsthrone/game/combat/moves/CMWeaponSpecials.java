package com.lilithsthrone.game.combat.moves;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.StatusEffect;
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

    public static AbstractCombatMove INKY_SUMMON = new AbstractCombatMove(CombatMoveCategory.SPECIAL,
            "summon Inky",
            100,
            1,
            CombatMoveType.ATTACK,
            DamageType.LUST,
            "statusEffects/inky_summon",
            Util.newArrayListOfValues(PresetColour.BASE_PURPLE),
            false,
            true,
            false,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.INKY_ATTACK, 6))) {
        @Override
        public Value<Boolean, String> isAvailableFromSpecialCase(GameCharacter source) {
            return new Value<>(source.hasWeaponEquipped(WeaponType.getWeaponTypeFromId("innoxia_pen_inky")), "Available to characters who have Inky's pen.");
        }
        @Override
        public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            return UtilText.parse(source, target,
            		"Unleash Inky on [npc2.name]. The little arcane entity will happily inflict "+getFormattedDamage(DamageType.LUST, 15, null, false, false)+" damage every turn and reduce [npc2.her] actions points by 1!");
        }
        @Override
        public String getDescription(int turnIndex, GameCharacter source) {
            return UtilText.parse(source,
            		"Unleash Inky on your target. The little arcane entity will happily inflict "+getFormattedDamage(DamageType.LUST, 15, null, false, false)+" damage every turn and reduce their actions points by 1!");
        }
        @Override
        public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
            
            return formatAttackOutcome(source, target,
            		"Twisting the cap of [npc.her] pen three times, [npc.name] [npc.verb(pull)] it off to reveal something very special."
            				+ " Dripping off the exposed nib, a blob of purple liquid rapidly swells and forms into a small, octopus-like creature."
            				+ " This arcane being, known only as Inky, levitates up into the air, and with hearts in is eyes, it looks at [npc2.name] and makes a happy little moaning noise."
            				+ "<br/><br/>"
            				+ "Flying directly towards [npc2.name], Inky wraps its tentacles around [npc2.herHim], and with more joyous little squishing noises, it starts lewdly humping and groping [npc2.herHim]."
            				+ " It very quickly becomes apparent that any attempt of [npc2.namePos] to pull this arcane creature off of [npc2.herHim] are doomed to failure."
            				+ " Each time [npc2.she] [npc2.verb(try)] to grab the small octopus, [npc2.her] [npc.hands] simply slip through its liquid body, which elicits cute, gurgling laughter from the horny creature."
            				+ "<br/><br/>"
            				+ "Although Inky's tentacles are both extremely distracting and arousing, there's nothing [npc2.name] can do to stop it,"
            					+ " and so [npc2.she] [npc2.verb(try)] to ignore it as [npc2.she] [npc.verb(wait)] for its arcane power to run out...",
            		"Inky is now giving [npc2.name] its intimate attention!",
            		null,
                	null);
        }
    };
}
