package com.lilithsthrone.game.combat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.4
 * @author Innoxia
 */
public enum Spell {

	// FIRE:
	
	FIREBALL(SpellSchool.FIRE,
			SpellType.OFFENSIVE,
			DamageType.FIRE,
			false,
			"Fireball",
			"fireball",
			"Summons a ball of arcane flames that can be launched at a target.",
			15,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.FIREBALL_1),
					new ListValue<>(SpellUpgrade.FIREBALL_2),
					new ListValue<>(SpellUpgrade.FIREBALL_3)),
			null,
			null) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
				return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.LINGERING_FLAMES, 2));
			} else {
				return new HashMap<>();
			}
		}
		
		@Override
		public int getDamage(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2) && !caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_3)) {
				return 10;
			}
			return 15;
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at yourself!",
											"Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at [npc.name]!",
											"",
											"Summoning a swirling vortex of arcane fire around [npc.her] [npc.arm], [npc.she] focuses its raw power into a ball of roiling flames before launching it directly at you!",
											"Summoning a swirling vortex of arcane fire around [npc1.her] [npc1.arm], [npc1.name] focuses its raw power into a ball of roiling flames before launching it directly at [npc2.name]!")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
				descriptionSB.append(" The fireball instantly splits in two after being cast!");
			}
			descriptionSB.append("</p>");
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(caster, -damage));

				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
				
				// Second fireball:
				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
					damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
					GameCharacter secondaryTarget = Combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<p>"
								+"The second Fireball swerves around to hit "+(target.isPlayer()?"you":UtilText.parse(target,"[npc.name]"))+" for a second time!"
							+"</p>");
						
						descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
						descriptionSB.append(target.incrementHealth(caster, -damage));
						
					} else {
						descriptionSB.append("<p>"
								+"The second Fireball swerves around to hit "+(secondaryTarget.isPlayer()?"you":UtilText.parse(secondaryTarget,"[npc.name]"))+"!"
							+"</p>");
						
						descriptionSB.append(getDamageDescription(caster, secondaryTarget, damage, isHit, isCritical));
						descriptionSB.append(secondaryTarget.incrementHealth(caster, -damage));
						applyStatusEffects(caster, secondaryTarget, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, secondaryTarget, isHit, isCritical));
					}
					
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	FLASH(SpellSchool.FIRE,
			SpellType.OFFENSIVE,
			DamageType.FIRE,
			false,
			"Flash",
			"flash",
			"Crates a blinding flash of light that stuns the target.",
			0,
			DamageVariance.LOW,
			60,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.FLASH, 1)),
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.FLASH_1),
					new ListValue<>(SpellUpgrade.FLASH_2),
					new ListValue<>(SpellUpgrade.FLASH_3)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(Stuns)] target for [style.colourGood(1 turn)]"))) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_1)) {
				return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.FLASH, 2));
			} else {
				return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.FLASH, 1));
			}
		}
		
		@Override
		public int getBaseCost(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_3)) {
				return 40;
			} else {
				return 60;
			}
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With a flick of your wrist, you summon a blinding flash of light right in front of your own face!",
											"With a flick of your wrist, you summon a blinding flash of light right in front of [npc.name]'s face!",
											"",
											"With a flick of [npc.her] wrist, [npc.name] summons a blinding flash of light right in front of your face!",
											"With a flick of [npc1.her] wrist, [npc1.name] summons a blinding flash of light right in front of [npc2.name]'s face!")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
				descriptionSB.append(" A secondary flash of light arcs away from the first, seeking out another target!");
			}
			descriptionSB.append("</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				
				// Second flash:
				if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
					GameCharacter secondaryTarget = Combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<p>"
								+"The second Flash fails to find another target, and quickly fizzles out..."
							+"</p>");
						
					} else {
						descriptionSB.append("<p>"
								+"The second Flash shoots out in front of "+(secondaryTarget.isPlayer()?"your face, blinding you as well!":UtilText.parse(secondaryTarget,"[npc.name]'s face, blinding [npc.herHim] as well!"))
							+"</p>");

						descriptionSB.append(getDamageDescription(caster, secondaryTarget, 0, isHit, isCritical));
						applyStatusEffects(caster, secondaryTarget, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, secondaryTarget, isHit, isCritical));
					}
					
				}
				
			} else {
				descriptionSB.append("<p style='text-align:center;'>"
						+ "[style.italicsBad(The flash misses!)]"
						+ "</p>");
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	CLOAK_OF_FLAMES(SpellSchool.FIRE,
			SpellType.DEFENSIVE,
			DamageType.FIRE,
			true,
			"Cloak of Flames",
			"cloak_of_flames",
			"Shrouds the target in a protective cloak of arcane flames, granting them improved fire and ice resistance.",
			0,
			DamageVariance.LOW,
			40,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3)),
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.CLOAK_OF_FLAMES_1),
					new ListValue<>(SpellUpgrade.CLOAK_OF_FLAMES_2),
					new ListValue<>(SpellUpgrade.CLOAK_OF_FLAMES_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 50)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(3 turns)]"))) {

		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3)) {
				return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_3, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2)) {
				return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_2, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1)) {
				return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_1, 3));
				
			} else {
				return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3));
			}
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With a swipe of your [pc.arm], you summon a protective cloak of arcane fire around yourself!",
											"With a swipe of your [pc.arm], you summon a protective cloak of arcane fire around [npc.name]!",
											"With a swipe of [npc.her] [npc.arm], [npc.name] summons a protective cloak of arcane fire around [npc.herself]!",
											"With a swipe of [npc.her] [npc.arm], [npc.name] summons a protective cloak of arcane fire around you!",
											"With a swipe of [npc1.her] [npc1.arm], [npc1.name] summons a protective cloak of arcane fire around [npc2.name]!")
								+"</p>");
			
			target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES);
			target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_1);
			target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_2);
			target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3);
			
			// If attack hits, apply damage and effects:
			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			applyStatusEffects(caster, target, isCritical);
			descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_FIRE(SpellSchool.FIRE,
			SpellType.DEFENSIVE,
			DamageType.FIRE,
			true,
			"Elemental Fire",
			"elemental_fire",
			"Summon forth your elemental in a physical form by binding them to the school of Fire.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ELEMENTAL_FIRE_1),
					new ListValue<>(SpellUpgrade.ELEMENTAL_FIRE_2),
					new ListValue<>(SpellUpgrade.ELEMENTAL_FIRE_3A),
					new ListValue<>(SpellUpgrade.ELEMENTAL_FIRE_3B)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolFire(Fire)]"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(caster.getElemental()==null) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.getCompanions().contains(caster.getElemental());
			}
			
			caster.getElemental().setElementalSchool(SpellSchool.FIRE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a flash of light and a burst of flames, you bind your elemental, [npc2.name], to the school of Fire!"
									:"With a flash of light and a burst of flames, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Fire!"))
					+"</p>");
				
			} else {
				caster.addCompanion(caster.getElemental());
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a flash of light and a burst of flames, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Fire!"
									:"With a flash of light and a burst of flames, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Fire!"))
					+"</p>");
			}
			
			if(Main.game.isInCombat()) {
				if(caster.isPlayer() || Combat.getAllies().contains(caster)) {
					Combat.addAlly(caster.getElemental());
				} else {
					Combat.addEnemy(caster.getElemental());
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	// WATER:

	ICE_SHARD(SpellSchool.WATER,
			SpellType.OFFENSIVE,
			DamageType.ICE,
			false,
			"Ice Shard",
			"ice_shard",
			"Summons a shard of ice that can be launched at a target.",
			15,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ICE_SHARD_1),
					new ListValue<>(SpellUpgrade.ICE_SHARD_2),
					new ListValue<>(SpellUpgrade.ICE_SHARD_3)), null, null) {

		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3) && target.hasStatusEffect(StatusEffect.FREEZING_FOG) && isCritical) {
					return Util.newHashMapOfValues(
							new Value<StatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3),
							new Value<StatusEffect, Integer>(StatusEffect.FROZEN, 1));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)){
					return Util.newHashMapOfValues(
							new Value<StatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"Summoning a swirling vortex of water from the moisture in the air, you focus your energy on freezing it in place, creating a shard of ice that you then launch at yourself!",
											"Summoning a swirling vortex of water from the moisture in the air, you focus your energy on freezing it in place, creating a shard of ice that you then launch at [npc.name]!",
											"",
											"Summoning a swirling vortex of water from the moisture in the air, [npc1.name] focuses [npc1.her] energy on freezing it in place, creating a shard of ice that [npc.she] then launches at you!",
											"Summoning a swirling vortex of water from the moisture in the air, [npc1.name] focuses [npc1.her] energy on freezing it in place, creating a shard of ice that [npc.she] then launches at [npc2.name]!")
								);
			
			if(isHit && isCritical && target.hasStatusEffect(StatusEffect.FREEZING_FOG) && caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_2)) {
				descriptionSB.append(" The freezing fog detonates as the Ice Shard travels through it");
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3)) {
					descriptionSB.append(", entombing everything in the immediate vicinity in a thin layer of ice!");
				} else {
					descriptionSB.append("!");
				}
			}
			
			descriptionSB.append("</p>");
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(target.incrementHealth(caster, -damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	RAIN_CLOUD(SpellSchool.WATER,
			SpellType.OFFENSIVE,
			DamageType.ICE,
			false,
			"Rain Cloud",
			"rain_cloud",
			"Summons a small cloud of arcane-enchanted rain above the target's head, which saps their ability to cast spells.",
			0,
			DamageVariance.LOW,
			30,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.RAIN_CLOUD_1),
					new ListValue<>(SpellUpgrade.RAIN_CLOUD_2),
					new ListValue<>(SpellUpgrade.RAIN_CLOUD_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, -25)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(3 turns)]"))) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DEEP_CHILL, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.RAIN_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With an upwards thrust of your [pc.arm], you summon forth a cloud of rain above your own head!",
											"With an upwards thrust of your [pc.arm], you summon forth a cloud of rain above [npc.name]'s head!",
											"",
											"With an upwards thrust of [npc.her] [npc.arm], [npc.name] summons forth a cloud of rain above your head!",
											"With an upwards thrust of [npc1.her] [npc1.arm], [npc1.name] summons forth a cloud of rain above [npc2.name]'s head!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	SOOTHING_WATERS(SpellSchool.WATER,
			SpellType.DEFENSIVE,
			DamageType.ICE,
			true,
			"Soothing Waters",
			"soothing_waters",
			"Summons an orb of soothing arcane-infused water, which restores the energy of anyone who drinks it.",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.SOOTHING_WATERS_1),
					new ListValue<>(SpellUpgrade.SOOTHING_WATERS_2),
					new ListValue<>(SpellUpgrade.SOOTHING_WATERS_3)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.boldGood(Restores)] 20% [style.boldHealth(energy)]"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With a gentle swish of your [pc.hand], you summon forth an orb of healing water, which you quickly drink.",
											"With a gentle swish of your [pc.hand], you summon forth an orb of healing water, which you send to [npc.name] to drink.",
											"With a gentle swish of [npc.her] [npc.hand], [npc.name] summons forth an orb of healing water, which [npc.she] quickly drinks.",
											"With a gentle swish of [npc.her] [npc.hand], [npc.name] summons forth an orb of healing water, which [npc.she] sends to you to drink.",
											"With a gentle swish of [npc.her] [npc.hand], [npc.name] summons forth an orb of healing water, which [npc.she] sends to [npc2.name]to drink."));

			if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3) ) {
				descriptionSB.append(" Smaller globes of water split off from the primary orb!");
			}
			
			descriptionSB.append("</p>");
			
			
			caster.incrementMana(-cost);
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<p>"
											+ UtilText.parse(target, "One of the small orbs circles around to heal [npc.name] for a second time, restoring a total of "
																		+(int)target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
																		+(int)target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!")
											+ "</p>");
					
					descriptionSB.append(target.incrementHealth(caster, target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f);
					
					if(Main.game.isInCombat()) {
						if(Combat.getEnemies().contains(target)) {
							for(NPC combatant : Combat.getEnemies()) {
								if(!combatant.equals(target)) {
									descriptionSB.append("<p>"
											+ UtilText.parse(combatant, "Another of the orbs flies towards [npc.name], healing [npc.herHim] for a total of "
																		+(int)target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
																		+(int)target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!")
											+ "</p>");
					
									descriptionSB.append(combatant.incrementHealth(caster, combatant.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f));
									combatant.incrementMana(combatant.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f);
								}
							}
						} else {
							if(!Main.game.getPlayer().equals(target)) {
								descriptionSB.append("<p>"
											+ "Another of the orbs flies towards you, healing you for a total of "
											+(int)target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
											+(int)target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"
										+ "</p>");
								descriptionSB.append(Main.game.getPlayer().incrementHealth(caster, Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f));
								Main.game.getPlayer().incrementMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f);
							}
							for(NPC combatant : Combat.getAllies()) {
								if(!combatant.equals(target)) {
									descriptionSB.append("<p>"
											+ UtilText.parse(combatant, "Another of the orbs flies towards [npc.name], healing [npc.herHim] for a total of "
																		+(int)target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
																		+(int)target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!")
											+ "</p>");
									descriptionSB.append(combatant.incrementHealth(caster, combatant.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f));
									combatant.incrementMana(combatant.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f);
								}
							}
						}
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<p>"
								+ "The orb of water heals "+(target.isPlayer()?"you":UtilText.parse(target,"[npc.name]"))+" for a total of "
									+(int)target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
									+(int)target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"
							+ "</p>");
					descriptionSB.append(target.incrementHealth(caster, target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<p>"
								+"The orb of water heals "+(target.isPlayer()?"you":UtilText.parse(target,"[npc.name]"))+" for a total of "
									+(int)target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
									+(int)target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"
							+ "</p>");
					descriptionSB.append(target.incrementHealth(caster, target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<p>"
								+ "The orb of water heals "+(target.isPlayer()?"you":UtilText.parse(target,"[npc.name]"))+" for a total of "
									+(int)target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"!"
							+ "</p>");
					descriptionSB.append(target.incrementHealth(caster, target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_WATER(SpellSchool.WATER,
			SpellType.DEFENSIVE,
			DamageType.ICE,
			true,
			"Elemental Water",
			"elemental_water",
			"Summon forth your elemental in a physical form by binding them to the school of Water.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ELEMENTAL_WATER_1),
					new ListValue<>(SpellUpgrade.ELEMENTAL_WATER_2),
					new ListValue<>(SpellUpgrade.ELEMENTAL_WATER_3A),
					new ListValue<>(SpellUpgrade.ELEMENTAL_WATER_3B)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolWater(Water)]"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(caster.getElemental()==null) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.getCompanions().contains(caster.getElemental());
			}
			
			caster.getElemental().setElementalSchool(SpellSchool.WATER);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a huge splash, you bind your elemental, [npc2.name], to the school of Water!"
									:"With a huge splash, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Water!"))
					+"</p>");
				
			} else {
				caster.addCompanion(caster.getElemental());
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a huge splash, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Water!"
									:"With a huge splash, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Water!"))
					+"</p>");
			}
			
			if(Main.game.isInCombat()) {
				if(caster.isPlayer() || Combat.getAllies().contains(caster)) {
					Combat.addAlly(caster.getElemental());
				} else {
					Combat.addEnemy(caster.getElemental());
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	// AIR:
	
	POISON_VAPOURS(SpellSchool.AIR,
			SpellType.OFFENSIVE,
			DamageType.POISON,
			false,
			"Poison Vapours",
			"poison_vapours",
			"Summons a cloud of poisonous gas around a target.",
			0,
			DamageVariance.LOW,
			40,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.POISON_VAPOURS_1),
					new ListValue<>(SpellUpgrade.POISON_VAPOURS_2),
					new ListValue<>(SpellUpgrade.POISON_VAPOURS_3)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<b>10</b> [style.colourPoison(Poison Damage)] per turn for [style.colourGood(3 turns)]"))) {

		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.POISON_VAPOURS_CHOKING_HAZE, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.POISON_VAPOURS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With a sweeping motion of your [pc.arm], you summon forth a cloud of poison vapours around yourself!",
											"With a sweeping motion of your [pc.arm], you summon forth a cloud of poison vapours around [npc.name]!",
											"",
											"With a sweeping motion of [npc.her] [npc.arm], [npc.name] summons forth a cloud of poison vapours around you!",
											"With a sweeping motion of [npc1.her] [npc1.arm], [npc1.name] summons forth a cloud of poison vapours around [npc2.name]!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_CHOKING_HAZE);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	VACUUM(SpellSchool.AIR,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Vacuum",
			"vacuum",
			"Creates a void in the air, dealing a small amount of initial damage as it sucks in the target, before lingering around to continue to disrupt their movements.",
			5,
			DamageVariance.LOW,
			60,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.VACUUM_1),
					new ListValue<>(SpellUpgrade.VACUUM_2),
					new ListValue<>(SpellUpgrade.VACUUM_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MISS_CHANCE, 10)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(4 turns)]"))) {

		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VACUUM_TOTAL_VOID, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VACUUM_SUCTION, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VACUUM_SECONDARY_VOIDS, 4));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VACUUM, 4));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With a clench of your fist, you summon forth a vacuum right next to yourself!",
											"With a clench of your fist, you summon forth a vacuum right next to [npc.name]!",
											"",
											"With a clench of [npc.her] fist, [npc.name] summons forth a vacuum right next to you!",
											"With a clench of [npc.her] fist, [npc.name] summons forth a vacuum right next to [npc2.name]!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.VACUUM_TOTAL_VOID);
				target.removeStatusEffect(StatusEffect.VACUUM_SUCTION);
				target.removeStatusEffect(StatusEffect.VACUUM_SECONDARY_VOIDS);
				target.removeStatusEffect(StatusEffect.VACUUM);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	PROTECTIVE_GUSTS(SpellSchool.AIR,
			SpellType.DEFENSIVE,
			DamageType.PHYSICAL,
			true,
			"Protective Gusts",
			"protective_gusts",
			"Summons a benevolent wind to protect the target, as well as to help guide their attacks.",
			0,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.PROTECTIVE_GUSTS_1),
					new ListValue<>(SpellUpgrade.PROTECTIVE_GUSTS_2),
					new ListValue<>(SpellUpgrade.PROTECTIVE_GUSTS_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 25),
					new Value<Attribute, Integer>(Attribute.DODGE_CHANCE, 10)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(3 turns)]"))) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 5));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"Swishing both of your [pc.arms] up into the air, you summon forth a benevolent wind to help protect you!",
											"Swishing both of your [pc.arms] up into the air, you summon forth a benevolent wind to help protect [npc.name]!",
											"Swishing both of [npc.her] [npc.arms] up into the air, [npc.name] summons forth a benevolent wind to help protect [npc.herHim]!",
											"Swishing both of [npc.her] [npc.arms] up into the air, [npc.name] summons forth a benevolent wind to help protect you!",
											"Swishing both of [npc.her] [npc.arms] up into the air, [npc.name] summons forth a benevolent wind to help protect [npc2.name]!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_AIR(SpellSchool.AIR,
			SpellType.DEFENSIVE,
			DamageType.PHYSICAL,
			true,
			"Elemental Air",
			"elemental_air",
			"Summon forth your elemental in a physical form by binding them to the school of Air.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ELEMENTAL_AIR_1),
					new ListValue<>(SpellUpgrade.ELEMENTAL_AIR_2),
					new ListValue<>(SpellUpgrade.ELEMENTAL_AIR_3A),
					new ListValue<>(SpellUpgrade.ELEMENTAL_AIR_3B)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolAir(Air)]"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(caster.getElemental()==null) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.getCompanions().contains(caster.getElemental());
			}
			
			caster.getElemental().setElementalSchool(SpellSchool.AIR);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a tremendous gust of wind, you bind your elemental, [npc2.name], to the school of Air!"
									:"With a tremendous gust of wind, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Air!"))
					+"</p>");
				
			} else {
				caster.addCompanion(caster.getElemental());
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a tremendous gust of wind, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Air!"
									:"With a tremendous gust of wind, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Air!"))
					+"</p>");
			}
			
			if(Main.game.isInCombat()) {
				if(caster.isPlayer() || Combat.getAllies().contains(caster)) {
					Combat.addAlly(caster.getElemental());
				} else {
					Combat.addEnemy(caster.getElemental());
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	// EARTH:
	
	SLAM(SpellSchool.EARTH,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Slam",
			"slam",
			"Summons a crushing wave of force that slams down onto a target.",
			25,
			DamageVariance.LOW,
			60,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.SLAM_1),
					new ListValue<>(SpellUpgrade.SLAM_2),
					new ListValue<>(SpellUpgrade.SLAM_3)), null, null) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.SLAM_AFTER_SHOCK, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.SLAM_GROUND_SHAKE, 2));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With a downward, striking gesture, you summon forth a huge wave of pure force, which slams down on yourself!",
											"With a downward, striking gesture, you summon forth a huge wave of pure force, which slams down on [npc.name]!",
											"",
											"With a downward, striking gesture, [npc.name] summons forth a huge wave of pure force, which slams down on you!",
											"With a downward, striking gesture, [npc.name] summons forth a huge wave of pure force, which slams down on [npc2.name]!")
								);
			
			if(isHit) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					descriptionSB.append(" The force then strikes down into the ground, causing a huge earthquake!");
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					descriptionSB.append(" The force then strikes down into the ground, causing the ground to shake!");
				}
			}
			
			descriptionSB.append("</p>");
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(target.incrementHealth(caster, -damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					if(Combat.getEnemies().contains(target)) {
						for(NPC combatant : Combat.getEnemies()) {
							applyStatusEffects(caster, combatant, isCritical);
							descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
						}
					} else {
						applyStatusEffects(caster, Main.game.getPlayer(), isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, Main.game.getPlayer(), isHit, isCritical));
						for(NPC combatant : Combat.getAllies()) {
							applyStatusEffects(caster, combatant, isCritical);
							descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
						}
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	TELEKENETIC_SHOWER(SpellSchool.EARTH,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Telekenetic Shower",
			"telekenetic_shower",
			"Lifts any small objects in the surrounding area into the air, before hurling them at the target.",
			0,
			DamageVariance.LOW,
			125,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.TELEKENETIC_SHOWER_1),
					new ListValue<>(SpellUpgrade.TELEKENETIC_SHOWER_2),
					new ListValue<>(SpellUpgrade.TELEKENETIC_SHOWER_3)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<b>10</b> [style.colourPhysical(Physical Damage)] per turn for [style.colourGood(3 turns)]"))) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 6));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"Raising your [pc.arms], you lift all manner of small objects in the immediate vicinity up into the air, before hurling them at yourself!",
											"Raising your [pc.arms], you lift all manner of small objects in the immediate vicinity up into the air, before hurling them at [npc.name]!",
											"",
											"Raising [npc.her] [npc.arms], [npc.name] lifts all manner of small objects in the immediate vicinity up into the air, before hurling them at you!",
											"Raising [npc.her] [npc.arms], [npc.name] lifts all manner of small objects in the immediate vicinity up into the air, before hurling them at [npc2.name]!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},

	STONE_SHELL(SpellSchool.EARTH,
			SpellType.DEFENSIVE,
			DamageType.PHYSICAL,
			true,
			"Stone Shell",
			"stone_shell",
			"Summons a protective layer of stone around the target.",
			0,
			DamageVariance.LOW,
			25,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.STONE_SHELL_1),
					new ListValue<>(SpellUpgrade.STONE_SHELL_2),
					new ListValue<>(SpellUpgrade.STONE_SHELL_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 25)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(3 turns)]"))) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.STONE_SHELL_HARDENED_CARAPACE, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.STONE_SHELL_SHIFTING_SANDS, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.STONE_SHELL, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"Thrusting your [pc.hand] forwards, you summon forth a levitating stone shell to protect you from incoming attacks!",
											"Thrusting your [pc.hand] forwards, you summon forth a levitating stone shell to protect [npc.name] from incoming attacks!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth a levitating stone shell to protect [npc.herHim] from incoming attacks!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth a levitating stone shell to protect you from incoming attacks!",
											"Thrusting [npc1.her] [npc1.hand] forwards, [npc1.name] summons forth a levitating stone shell to protect [npc2.name] from incoming attacks!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.removeStatusEffectCombat(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH));
				target.removeStatusEffect(StatusEffect.STONE_SHELL_HARDENED_CARAPACE);
				target.removeStatusEffect(StatusEffect.STONE_SHELL_SHIFTING_SANDS);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_EARTH(SpellSchool.EARTH,
			SpellType.DEFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Elemental Earth",
			"elemental_earth",
			"Summon forth your elemental in a physical form by binding them to the school of Earth.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ELEMENTAL_EARTH_1),
					new ListValue<>(SpellUpgrade.ELEMENTAL_EARTH_2),
					new ListValue<>(SpellUpgrade.ELEMENTAL_EARTH_3A),
					new ListValue<>(SpellUpgrade.ELEMENTAL_EARTH_3B)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolEarth(Earth)]"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(caster.getElemental()==null) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.getCompanions().contains(caster.getElemental());
			}
			
			caster.getElemental().setElementalSchool(SpellSchool.EARTH);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a burst of rocks and debris, you bind your elemental, [npc2.name], to the school of Earth!"
									:"With a burst of rocks and debris, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Earth!"))
					+"</p>");
				
			} else {
				caster.addCompanion(caster.getElemental());
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a burst of rocks and debris, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Earth!"
									:"With a burst of rocks and debris, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Earth!"))
					+"</p>");
			}
			
			if(Main.game.isInCombat()) {
				if(caster.isPlayer() || Combat.getAllies().contains(caster)) {
					Combat.addAlly(caster.getElemental());
				} else {
					Combat.addEnemy(caster.getElemental());
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	// ARCANE:
	
	ARCANE_AROUSAL(SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Arcane Arousal",
			"arcane_arousal",
			"Causes the target to witness a highly arousing arcane vision.",
			15,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ARCANE_AROUSAL_1),
					new ListValue<>(SpellUpgrade.ARCANE_AROUSAL_2),
					new ListValue<>(SpellUpgrade.ARCANE_AROUSAL_3)), null, null) {

		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION, 2));
					
				}
			}
			return new HashMap<>();
		}

		@Override
		public int getDamage(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_1)) {
				return 30;
			}
			return 15;
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"You focus your arcane energy on projecting an arousing vision into your own mind",
											"You focus your arcane energy on projecting an arousing vision into [npc.name]'s mind.",
											"",
											"[npc.Name] focuses [npc.her] arcane energy on projecting an arousing vision into your mind!",
											"[npc.Name] focuses [npc.her] arcane energy on projecting an arousing vision into [npc2.name]'s mind!")
								+"</p>");
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(target.incrementLust(damage));
				}
				
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES);
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION);
					
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	TELEPATHIC_COMMUNICATION(SpellSchool.ARCANE,
			SpellType.DEFENSIVE,
			DamageType.PHYSICAL,
			true,
			"Telepathic Communication",
			"telepathic_communication",
			"The caster projects seductive voices into the mind of the target.",
			0,
			DamageVariance.LOW,
			75,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.TELEPATHIC_COMMUNICATION_1),
					new ListValue<>(SpellUpgrade.TELEPATHIC_COMMUNICATION_2),
					new ListValue<>(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 15)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(5 turns)]"))) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 10));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 5));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"You focus your arcane energy on enabling your thoughts to be projected into others' minds!",
											"You focus your arcane energy on enabling [npc.name]'s thoughts to be projected into others' minds!",
											"[npc.Name] focuses [npc.her] arcane energy on enabling [npc.her] thoughts to be projected into others' minds!",
											"[npc.Name] focuses [npc.her] arcane energy on enabling your thoughts to be projected into others' minds!",
											"[npc1.Name] focuses [npc1.her] arcane energy on enabling [npc2.name]'s thoughts to be projected into others' minds!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	ARCANE_CLOUD(SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Arcane Cloud",
			"arcane_cloud",
			"Summons an arcane-imbued stormcloud over the target's head.",
			0,
			DamageVariance.LOW,
			150,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ARCANE_CLOUD_1),
					new ListValue<>(SpellUpgrade.ARCANE_CLOUD_2),
					new ListValue<>(SpellUpgrade.ARCANE_CLOUD_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, -25)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(3 turns)]"))) {

		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ARCANE_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With an upwards thrust of your [pc.arm], you summon forth an arcane cloud above your own head!",
											"With an upwards thrust of your [pc.arm], you summon forth an arcane cloud above [npc.name]'s head!",
											"",
											"With an upwards thrust of [npc.her] [npc.arm], [npc.name] summons forth an arcane cloud above your head!",
											"With an upwards thrust of [npc1.her] [npc1.arm], [npc1.name] summons forth an arcane cloud above [npc2.name]'s head!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD);
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	CLEANSE(SpellSchool.ARCANE,
			SpellType.DEFENSIVE,
			DamageType.PHYSICAL,
			true,
			"Cleanse",
			"cleanse",
			"A cleansing wave of arcane energy bursts forth from the target, removing all of their status effects.",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.CLEANSE_1),
					new ListValue<>(SpellUpgrade.CLEANSE_2),
					new ListValue<>(SpellUpgrade.CLEANSE_3)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourGood(Removes all)] combat status effects from the target"))) {
		
		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && Main.game.isInCombat()) {
				StatusEffect effect = StatusEffect.ARCANE_DUALITY_POSITIVE;
				
				if(Combat.getEnemies().contains(caster)) {
					if(!Combat.getEnemies().contains(target)) {
						effect = StatusEffect.ARCANE_DUALITY_NEGATIVE;
					}
				} else {
					if(Combat.getEnemies().contains(target)) {
						effect = StatusEffect.ARCANE_DUALITY_NEGATIVE;
					}
				}
				
				if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(effect, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_2)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(effect, 3));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public boolean isBeneficial(GameCharacter caster, GameCharacter target) {
			if(Main.game.isInCombat()) {
				if(Combat.getEnemies().contains(caster)) {
					if(!Combat.getEnemies().contains(target)) {
						return false;
					}
				} else {
					if(Combat.getEnemies().contains(target)) {
						return false;
					}
				}
			}
			return true;
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"Thrusting your [pc.hand] forwards, you summon forth an explosion of cleansing arcane energy upon yourself!",
											"Thrusting your [pc.hand] forwards, you summon forth an explosion of cleansing arcane energy upon [npc.name]!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth an explosion of cleansing arcane energy upon [npc.herself]!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth an explosion of cleansing arcane energy upon you!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth an explosion of cleansing arcane energy upon [npc2.name]!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				List<StatusEffect> effectsToRemove = new ArrayList<>();
				boolean removeNegative = true;
				boolean removePositive = true;
				if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_1) && Main.game.isInCombat()) {
					if(Combat.getEnemies().contains(caster)) {
						if(!Combat.getEnemies().contains(target)) {
							removeNegative = false;
						} else {
							removePositive = false;
						}
						
					} else {
						if(Combat.getEnemies().contains(target)) {
							removeNegative = false;
						} else {
							removePositive = false;
						}
					}
				}
				for(StatusEffect se : target.getStatusEffects()) {
					if(se.isCombatEffect() && (se.isBeneficial()?removePositive:removeNegative)) {
						effectsToRemove.add(se);
					}
				}
				for(StatusEffect se : effectsToRemove) {
					descriptionSB.append(target.removeStatusEffectCombat(se));
				}
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	STEAL(SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Steal",
			"steal",
			"A lesser form of teleport, this spell enables the caster to steal an item from out of the target's inventory.",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.STEAL_1),
					new ListValue<>(SpellUpgrade.STEAL_2),
					new ListValue<>(SpellUpgrade.STEAL_3A),
					new ListValue<>(SpellUpgrade.STEAL_3B)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourExcellent(Steals)] a random item from the target's inventory"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"Thrusting out your [pc.hand] and clenching your fist, you channel your arcane power into stealing one of your own items...",
											"Thrusting out your [pc.hand] and clenching your fist, you channel your arcane power into stealing one of [npc.name]'s items!",
											"",
											"Thrusting out [npc.her] [npc.hand] and clenching [npc.her] fist, [npc.name] channels [npc.her] arcane power into stealing one of your items!",
											"Thrusting out [npc.her] [npc.hand] and clenching [npc.her] fist, [npc.name] channels [npc.her] arcane power into stealing one of [npc2.name]'s items!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				boolean stealItem = false;
				boolean mainWeaponSteal = false;
				boolean offhandWeaponSteal = false;
				AbstractClothing clothingToSteal = null;
				
				if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_3B)) {
					clothingToSteal = target.getClothingInSlot(InventorySlot.GROIN);
					if(clothingToSteal != null && !clothingToSteal.isSealed()) {
						target.forceUnequipClothingIntoVoid(caster, clothingToSteal);
						descriptionSB.append("<p>"
								+ getCastDescription(caster, target,
										"You stole your own "+clothingToSteal.getName()+"...",
										"[npc.Name] lets out an embarrassed cry as you steal the "+clothingToSteal.getName()+" that [npc.she]'s currently wearing, [npc.speech(Y-You pervert!)]",
										"",
										"You can't help but let out an embarrassed cry as [npc.name] steals the "+clothingToSteal.getName()+" that you're currently wearing, [pc.speech(Y-You pervert!)]",
										"[npc2.Name] lets out an embarrassed cry as [npc1.name] steals the "+clothingToSteal.getName()+" that [npc2.she]'s currently wearing, [npc2.speech(Y-You pervert!)]")
								+ "</p>"
								+ caster.addClothing(clothingToSteal, true));
					}
				}
				
				if(clothingToSteal==null) {
					if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_3A)) {
						List<AbstractClothing> nonSealedClothing = new ArrayList<>();
						for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
							if(!c.isSealed()) {
								nonSealedClothing.add(c);
							}
						}
						if(!nonSealedClothing.isEmpty()) {
							clothingToSteal = nonSealedClothing.get(Util.random.nextInt(nonSealedClothing.size()));
						}
						
					} else if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_2)) {
						mainWeaponSteal = target.getMainWeapon()!=null;
						offhandWeaponSteal = target.getOffhandWeapon()!=null;
						
					} else if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_1)) {
						List<AbstractClothing> nonSealedOuterClothing = new ArrayList<>();
						for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
							if(!c.isSealed() && target.isAbleToUnequip(c, false, target)) {
								nonSealedOuterClothing.add(c);
							}
						}
						if(!nonSealedOuterClothing.isEmpty()) {
							clothingToSteal = nonSealedOuterClothing.get(Util.random.nextInt(nonSealedOuterClothing.size()));
						}
						
					} else {
						stealItem = target.getInventorySlotsTaken()>0;
					}
				
					double rnd = Math.random();
					
					if(mainWeaponSteal && (rnd<0.2 || (!offhandWeaponSteal && !stealItem && clothingToSteal==null))) {
						AbstractWeapon weapon = target.getMainWeapon();
						target.unequipMainWeapon(true);
						descriptionSB.append("<p>"
								+ getCastDescription(caster, target,
										"You stole your own "+weapon.getName()+"...",
										"You stole [npc.name]'s "+weapon.getName()+" from out of [npc.her] [npc.hands]!",
										"",
										"[npc.Name] stole your "+weapon.getName()+" from out of your [pc.hands]!",
										"[npc1.Name] stole [npc2.name]'s "+weapon.getName()+" from out of [npc2.her] [npc2.hands]!")
								+ "</p>"
								+ caster.addWeapon(weapon, true));
						
					} else if(offhandWeaponSteal && (rnd<0.2 || (!stealItem && clothingToSteal==null))) {
						AbstractWeapon weapon = target.getOffhandWeapon();
						target.unequipOffhandWeapon(true);
						descriptionSB.append("<p>"
								+ getCastDescription(caster, target,
										"You stole your own "+weapon.getName()+"...",
										"You stole [npc.name]'s "+weapon.getName()+" from out of [npc.her] [npc.hands]!",
										"",
										"[npc.Name] stole your "+weapon.getName()+" from out of your [pc.hands]!",
										"[npc1.Name] stole [npc2.name]'s "+weapon.getName()+" from out of [npc2.her] [npc2.hands]!")
								+ "</p>"
								+ caster.addWeapon(weapon, true));
						
					} else if(stealItem && (rnd<0.5 || (clothingToSteal==null))) {
						AbstractItem item = null;
						if(!target.getAllItemsInInventory().isEmpty()) {
							item = target.getAllItemsInInventory().get(Util.random.nextInt(target.getAllItemsInInventory().size()));
						}
						AbstractWeapon weapon = null;
						if(!target.getAllWeaponsInInventory().isEmpty()) {
							weapon = target.getAllWeaponsInInventory().get(Util.random.nextInt(target.getAllWeaponsInInventory().size()));
						}
						AbstractClothing clothing = null;
						if(!target.getAllClothingInInventory().isEmpty()) {
							clothing = target.getAllClothingInInventory().get(Util.random.nextInt(target.getAllClothingInInventory().size()));
						}
						double itemStealRnd = Math.random();
						if(item!=null && (itemStealRnd<0.33 || (weapon==null && clothing==null))) {
							target.removeItem(item);
							descriptionSB.append("<p>"
									+ getCastDescription(caster, target,
											"You stole your own "+item.getName()+"...",
											"You stole [npc.name]'s "+item.getName()+" from out of [npc.her] inventory!",
											"",
											"[npc.Name] stole your "+item.getName()+" from out of your inventory!",
											"[npc1.Name] stole [npc2.name]'s "+item.getName()+" from out of [npc2.her] inventory!")
									+ "</p>"
									+ caster.addItem(item, false));
							
						} else if(weapon!=null && (itemStealRnd<0.66 || (clothing==null))) {
							target.removeWeapon(weapon);
							descriptionSB.append("<p>"
									+ getCastDescription(caster, target,
											"You stole your own "+weapon.getName()+"...",
											"You stole [npc.name]'s "+weapon.getName()+" from out of [npc.her] inventory!",
											"",
											"[npc.Name] stole your "+weapon.getName()+" from out of your inventory!",
											"[npc1.Name] stole [npc2.name]'s "+weapon.getName()+" from out of [npc2.her] inventory!")
									+ "</p>"
									+ caster.addWeapon(weapon, false));
							
						} else {
							target.removeClothing(clothing);
							descriptionSB.append("<p>"
									+ getCastDescription(caster, target,
											"You stole your own "+clothing.getName()+"...",
											"You stole [npc.name]'s "+clothing.getName()+" from out of [npc.her] inventory!",
											"",
											"[npc.Name] stole your "+clothing.getName()+" from out of your inventory!",
											"[npc1.Name] stole [npc2.name]'s "+clothing.getName()+" from out of [npc2.her] inventory!")
									+ "</p>"
									+ caster.addClothing(clothing, false));
						}
						
					} else if(clothingToSteal!=null) {
						target.forceUnequipClothingIntoVoid(caster, clothingToSteal);
						descriptionSB.append("<p>"
								+ getCastDescription(caster, target,
										"You stole your own "+clothingToSteal.getName()+"...",
										"[npc.Name] lets out an embarrassed cry as you steal the "+clothingToSteal.getName()+" that [npc.she]'s currently wearing!",
										"",
										"You can't help but let out an embarrassed cry as [npc.name] steals the "+clothingToSteal.getName()+" that you're currently wearing!",
										"[npc2.Name] lets out an embarrassed cry as [npc1.name] steals the "+clothingToSteal.getName()+" that [npc2.she]'s currently wearing!")
								+ "</p>"
								+ caster.addClothing(clothingToSteal, true));
						
					} else {
						descriptionSB.append("<p>[style.italicsDisabled(There's nothing to steal...)]</p>");
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
		public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
			return Spell.spellStealUpgradeTree;
		}
	},
	
	TELEPORT(SpellSchool.ARCANE,
			SpellType.DEFENSIVE,
			DamageType.PHYSICAL,
			true,
			"Teleport",
			"teleport",
			"The target is teleported behind their enemies, granting a huge boost to dodge chance.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.TELEPORT_1),
					new ListValue<>(SpellUpgrade.TELEPORT_2),
					new ListValue<>(SpellUpgrade.TELEPORT_3)),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DODGE_CHANCE, 100)),
			Util.newArrayListOfValues(
					new ListValue<>("Lasts for [style.colourGood(1 turn)]"),
					new ListValue<>("[style.colourExcellent(Unlocks)] map teleport"),
					new ListValue<>("Map teleport [style.colourTerrible(blocked)] by companions"))) {
		

		@Override
		public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_3)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_1)) {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 1));
					
				} else {
					return Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.TELEPORT, 1));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"With a quick, cutting motion from one of your [pc.hands], you teleport behind your enemies!",
											"With a quick, cutting motion from one of your [pc.hands], you teleport [npc.name] behind [npc.her] enemies!",
											"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports behind [npc.her] enemies!",
											"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports you behind your enemies!",
											"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports [npc2.name] behind [npc2.her] enemies!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEPORT_ARCANE_ARRIVAL);
				target.removeStatusEffect(StatusEffect.TELEPORT);
				
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_2)) {
					if(Combat.getEnemies().contains(target)) {
						for(NPC combatant : Combat.getEnemies()) {
							applyStatusEffects(caster, combatant, isCritical);
							descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
						}
					} else {
						applyStatusEffects(caster, Main.game.getPlayer(), isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, Main.game.getPlayer(), isHit, isCritical));
						for(NPC combatant : Combat.getAllies()) {
							applyStatusEffects(caster, combatant, isCritical);
							descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
						}
					}
					
				} else {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	LILITHS_COMMAND(SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Lilith's Command",
			"liliths_command",
			"Imbues the caster's words with the power of Lilith herself, forcing the target to instantly submit.",
			0,
			DamageVariance.LOW,
			400,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.LILITHS_COMMAND_1),
					new ListValue<>(SpellUpgrade.LILITHS_COMMAND_2),
					new ListValue<>(SpellUpgrade.LILITHS_COMMAND_3)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("[style.colourGood(25%)] chance for target to [style.colourExcellent(instantly submit)]"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append("<p>"
									+getCastDescription(caster, target,
											"",
											"Drawing an immense amount of power from your arcane aura, you project the words of Lilith herself into the mind of [npc.name], ordering [npc.herHim] to submit.",
											"",
											"Drawing an immense amount of power from [npc.her] arcane aura, [npc.name] projects the words of Lilith herself into your mind, ordering you to submit!",
											"Drawing an immense amount of power from [npc1.her] arcane aura, [npc1.name] projects the words of Lilith herself into [npc2.name]'s mind, ordering [npc2.herHim] to submit!")
								+"</p>");
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				boolean success = false;
				if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_3)) {
					success = true;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
					success = Math.random()<0.75f;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
					success = Math.random()<0.5f && target.getRace().isVulnerableToLilithsLustStorm();
					
				} else {
					success = Math.random()<0.25f && target.getRace().isVulnerableToLilithsLustStorm();
				}
				
				descriptionSB.append("<p>");
				if(success) {
					target.setHealthPercentage(0);
					target.setManaPercentage(0);
					target.setLust(100);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
					if(target.isPlayer()) {
						descriptionSB.append(
								"You find yourself standing face-to-face with the queen of all demons herself."
								+ " Snapping her fingers, Lilith points to the floor, and as she does so, you find yourself dropping to your knees in front of her."
								+ " Eager to please her, you look up into Lilith's eyes as you [pc.moan], [pc.speech(Please, Lilith... Do what you want with me... I'm your loyal slave...)]");
						
					} else {
						descriptionSB.append(UtilText.parse(target,
								"[npc.Name] sinks to [npc.her] knees as the effect of Lilith's Command completely overwhelms [npc.herHim]."
										+ " Letting out [npc.a_moan+], [npc.she] starts touching [npc.herself] as [npc.she] pleads, [npc.speech(Please, Lilith... Do what you want with me... I'm your loyal slave...)]"));
					}
					
				} else {
					if(target.isPlayer()) {
						descriptionSB.append(
								"You shake your head and jump back as you resist the effects of Lilith's Command!");
						
					} else if(target.getRace().isVulnerableToLilithsLustStorm() || !caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
						descriptionSB.append(UtilText.parse(target, "[npc.Name] shakes [npc.her] head and jumps back as [npc.she] resists the effects of Lilith's Command!"));
					} else {
						descriptionSB.append(UtilText.parse(target, "[npc.Name] grins as [npc.she] taunts, [npc.speech(That cheap trick isn't going to affect [npc.a_race] like me!)]"));
					}
				}
				descriptionSB.append("</p>");
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_ARCANE(SpellSchool.ARCANE,
			SpellType.DEFENSIVE,
			DamageType.LUST,
			false,
			"Elemental Arcane",
			"elemental_arcane",
			"Summon forth your elemental in a physical form by binding them to the school of Arcane.",
			0,
			DamageVariance.LOW,
			10,
			null,
			Util.newArrayListOfValues(
					new ListValue<>(SpellUpgrade.ELEMENTAL_ARCANE_1),
					new ListValue<>(SpellUpgrade.ELEMENTAL_ARCANE_2),
					new ListValue<>(SpellUpgrade.ELEMENTAL_ARCANE_3A),
					new ListValue<>(SpellUpgrade.ELEMENTAL_ARCANE_3B)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Summons [style.colourArcane(Elemental)] in form of [style.colourArcane(Arcane)]"))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(caster.getElemental()==null) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.getCompanions().contains(caster.getElemental());
			}
			
			caster.getElemental().setElementalSchool(SpellSchool.ARCANE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a flash of purple arcane lightning, you bind your elemental, [npc2.name], to the school of Arcane!"
									:"With a flash of purple arcane lightning, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Arcane!"))
					+"</p>");
				
			} else {
				caster.addCompanion(caster.getElemental());
				descriptionSB.append("<p>"
						+UtilText.parse(caster, caster.getElemental(),
								(caster.isPlayer()
									?"With a flash of purple arcane lightning, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Arcane!"
									:"With a flash of purple arcane lightning, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Arcane!"))
					+"</p>");
			}
			
			if(Main.game.isInCombat()) {
				if(caster.isPlayer() || Combat.getAllies().contains(caster)) {
					Combat.addAlly(caster.getElemental());
				} else {
					Combat.addEnemy(caster.getElemental());
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	
	// FROM WEAPONS:
	
	WITCH_SEAL(SpellSchool.AIR,
			SpellType.OFFENSIVE,
			DamageType.MISC,
			false,
			"Witch's Seal",
			"spell_witch_seal",
			"Places an arcane seal upon the target, preventing them from taking any action for two turns.",
			0,
			DamageVariance.NONE,
			25,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.WITCH_SEAL, 2)),
			null, null, null) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);

			applyStatusEffects(caster, target, isCritical);

			caster.incrementMana(-cost);
			
			return "<p>"
					+getCastDescription(caster, target,
							"",
							"Concentrating on the arcane power within your broomstick, you summon forth a powerful seal, which traps [npc.name] in place!",
							"",
							"Concentrating on the arcane power within [npc.her] broomstick, [npc.name] summons forth a powerful seal, which traps you in place!",
							"Concentrating on the arcane power within [npc1.her] broomstick, [npc1.name] summons forth a powerful seal, which traps [npc2.name] in place!")
					+"</p>";
		}
	},
	
	WITCH_CHARM(SpellSchool.ARCANE,
			SpellType.DEFENSIVE,
			DamageType.MISC,
			true,
			"Witch's Charm",
			"spell_witch_charm",
			"Places an arcane enchantment upon the target, causing them to appear irresistibly beautiful to anyone who looks upon them.",
			0,
			DamageVariance.NONE,
			20,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.WITCH_CHARM, 5)),
			null,
			null,
			null) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			applyStatusEffects(caster, caster, isCritical);

			caster.incrementMana(-getModifiedCost(caster));
			
			if (caster.isPlayer()) {
				return UtilText.parse(target,
						"<p>"
							+ "Concentrating on the arcane power within your broomstick, you cast a bewitching charm upon yourself!"
						+ "</p>");
			} else {
				return UtilText.parse(caster,
						"<p>"
							+ "Concentrating on the arcane power within [npc.her] broomstick, [npc.name] casts a bewitching charm upon [npc.herself]!"
						+ "</p>");
			}
		}
	};
	
	private static Map<SpellSchool, List<Spell>> spellsFromSchoolMap = new HashMap<>();
	
	static {
		for(SpellSchool school : SpellSchool.values()) {
			spellsFromSchoolMap.put(school, new ArrayList<>());
		}
		for(Spell s : Spell.values()) {
			spellsFromSchoolMap.get(s.getSpellSchool()).add(s);
		}
	}
	
	public static Map<SpellSchool, List<Spell>> getSpellsFromSchoolMap() {
		return spellsFromSchoolMap;
	}
	
	
	private static StringBuilder descriptionSB = new StringBuilder();
	
	protected static Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> spellStealUpgradeTree;
	static {
		spellStealUpgradeTree = new HashMap<>();

		spellStealUpgradeTree.put(0, new ArrayList<>());
		spellStealUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.ARCANE, 0, SpellUpgrade.STEAL_1));

		spellStealUpgradeTree.put(1, new ArrayList<>());
		spellStealUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.ARCANE, 1, SpellUpgrade.STEAL_2));
		spellStealUpgradeTree.get(1).get(0).addLink(spellStealUpgradeTree.get(0).get(0));

		spellStealUpgradeTree.put(2, new ArrayList<>());
		spellStealUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.ARCANE, 2, SpellUpgrade.STEAL_3A));
		spellStealUpgradeTree.get(2).get(0).addLink(spellStealUpgradeTree.get(1).get(0));

		spellStealUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.ARCANE, 2, SpellUpgrade.STEAL_3B));
		spellStealUpgradeTree.get(2).get(1).addLink(spellStealUpgradeTree.get(2).get(0));
	}
	
	private SpellSchool spellSchool;
	private SpellType type;
	protected DamageType damageType;
	private boolean beneficial;
	
	private String name;
	private String description;
	
	protected int damage;
	protected int spellCost;
	protected DamageVariance damageVariance;
	private Map<StatusEffect, Integer> statusEffects;
	
	private Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> spellUpgradeTree;
	
	private HashMap<Attribute, Integer> attributeModifiers;
	private List<String> extraEffects;
	private List<String> modifiersList;
	
	private String SVGString;

	private Spell(SpellSchool spellSchool,
			SpellType type,
			DamageType damageType,
			boolean beneficial,
			String name,
			String pathName,
			String description,
			int damage,
			DamageVariance damageVariance,
			int spellCost,
			Map<StatusEffect, Integer> statusEffects,
			List<SpellUpgrade> upgradeList,
			HashMap<Attribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		
		this.spellSchool = spellSchool;
		this.type = type;
		this.damageType = damageType;
		this.beneficial = beneficial;
		
		this.name = name;
		this.description = description;

		this.damage = damage;
		this.damageVariance = damageVariance;
		
		this.spellCost = spellCost;
		
		if(statusEffects == null) {
			this.statusEffects = new HashMap<>();
		} else {
			this.statusEffects = statusEffects;
		}
		
		spellUpgradeTree = new HashMap<>();
		initialiseBasicSpellUpgradeTree(upgradeList);

		this.attributeModifiers = attributeModifiers;
		this.extraEffects = extraEffects;
		
		modifiersList = new ArrayList<>();
		
		if (attributeModifiers != null) {
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>"
						+ " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
		}
		
		if (extraEffects != null) {
			modifiersList.addAll(extraEffects);
		}
		
		
		// SVG:
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/" + pathName + ".svg");
			SVGString = Util.inputStreamToString(is);

			SVGString = SVGString.replaceAll("#ff2a2a", damageType.getMultiplierAttribute().getColour().getShades()[0]);
			SVGString = SVGString.replaceAll("#ff5555", damageType.getMultiplierAttribute().getColour().getShades()[1]);
			SVGString = SVGString.replaceAll("#ff8080", damageType.getMultiplierAttribute().getColour().getShades()[2]);
			SVGString = SVGString.replaceAll("#ffaaaa", damageType.getMultiplierAttribute().getColour().getShades()[3]);
			SVGString = SVGString.replaceAll("#ffd5d5", damageType.getMultiplierAttribute().getColour().getShades()[4]);
			
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initialiseBasicSpellUpgradeTree(List<SpellUpgrade> upgradeList) {
		if(upgradeList!=null) {
			for(int i = 0; i<upgradeList.size(); i++) {
				spellUpgradeTree.put(i, new ArrayList<>());
				spellUpgradeTree.get(i).add(new TreeEntry<>(spellSchool, i, upgradeList.get(i)));

				if(i==upgradeList.size()-1 && upgradeList.size()==4) {
					spellUpgradeTree.get(i-1).add(new TreeEntry<>(spellSchool, i-1, upgradeList.get(i)));
					spellUpgradeTree.get(i-2).get(0).addLink(spellUpgradeTree.get(i-1).get(1));
					
				} else if(i!=0) {
					spellUpgradeTree.get(i).get(0).addLink(spellUpgradeTree.get(i-1).get(0));
				}
			}
		}
	}

	public abstract String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical);

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}
	
	public SpellSchool getSpellSchool() {
		return spellSchool;
	}

	public SpellType getType() {
		return type;
	}

	public DamageType getDamageType() {
		return damageType;
	}
	
	public boolean isBeneficial(GameCharacter caster, GameCharacter target) {
		return beneficial;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	public int getDamage(GameCharacter caster) {
		return damage;
	}

	public DamageVariance getDamageVariance() {
		return damageVariance;
	}

	/**
	 * @return The basic spell cost, not taking into account the caster's spell efficiency.
	 */
	public int getBaseCost(GameCharacter caster) {
		return spellCost;
	}
	
	/**
	 * @param caster The person casting the spell.
	 * @return The cost of casting this spell as it relates to the caster. i.e. This spell's basic spell cost, reduced by the caster's spell efficiency.
	 */
	public float getModifiedCost(GameCharacter caster) {
		float calculatedCost = getBaseCost(caster);
		
		calculatedCost *= ((100 - Util.getModifiedDropoffValue(caster.getAttributeValue(Attribute.SPELL_COST_MODIFIER), 100)) / 100f);
		
		// Round float value to nearest 1 decimal place:
		return (Math.round(calculatedCost*10))/10f;
	}
	
	public Map<StatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		return statusEffects;
	}

	public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
		return spellUpgradeTree;
	}

	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}
	
	public String getSVGString() {
		return SVGString;
	}
	
	protected void applyStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		for (Entry<StatusEffect, Integer> se : getStatusEffects(caster, target, isCritical).entrySet()) {
			target.addStatusEffect(se.getKey(), se.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1));
		}
	}

	protected String getDamageDescription(GameCharacter caster, GameCharacter target, float damage, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();
		
		boolean appliesEffects = !this.getStatusEffects(caster, target, isCritical).isEmpty();

		damageCostDescriptionSB.append("<p>");
			if (caster.isPlayer()) {
				if (isCritical) {
					if(!isHit) {
						damageCostDescriptionSB.append("[style.italicsBad(You missed!)]");
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(target,
									"You [style.boldExcellent(critically)] hit [npc.name] for " + damage + " " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
						if(appliesEffects) {
							damageCostDescriptionSB.append(" You [style.boldExcellent(critically)] cast the spell, doubling its duration!");
						}
					}
				} else {
					if(!isHit) {
						damageCostDescriptionSB.append("[style.italicsBad(You missed!)]");
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(target,
									"You hit [npc.name] for " + damage + " " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
					}
				}
				
			} else {
				if (isCritical) {
					if(!isHit) {
						damageCostDescriptionSB.append(UtilText.parse(caster, "[style.italicsBad([npc1.Name] missed!)]"));
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(caster, target,
									"[npc1.Name] [style.boldExcellent(critically)] hits " + (target.isPlayer()?"you":"[npc2.name]")+" for " + damage + " " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
						if(appliesEffects) {
							damageCostDescriptionSB.append(UtilText.parse(caster, " [npc.Name] [style.boldExcellent(critically)] casts the spell, doubling its duration!"));
						}
					}
				} else {
					if(!isHit) {
						damageCostDescriptionSB.append(UtilText.parse(caster, "[style.italicsBad([npc1.Name] missed!)]"));
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(caster, target,
									"[npc1.Name] hits " + (target.isPlayer()?"you":"[npc2.name]")+" for " + damage + " " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
					}
				}
			}
			if(damageCostDescriptionSB.toString().toString().equals("<p>")) {
				return "";
			}
		damageCostDescriptionSB.append("</p>");
		
		return damageCostDescriptionSB.toString();
	}
	
	protected String getStatusEffectApplication(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		if (this.getStatusEffects(caster, target, isCritical) != null && !this.getStatusEffects(caster, target, isCritical).isEmpty() && isHit) {
			damageCostDescriptionSB.append(
					"<p>"
						+UtilText.parse(target,
								(!target.isPlayer()
									? "[npc.Name] is now "
									: "You are now ")
								+(this.isBeneficial(caster, target)
										?"benefiting from "
										:"suffering from ")));
			
			int i = 0;
			for (Entry<StatusEffect, Integer> seEntry : this.getStatusEffects(caster, target, isCritical).entrySet()) {
				if (i != 0) {
					if (i == statusEffects.size() - 1) {
						damageCostDescriptionSB.append(" and ");
					} else {
						damageCostDescriptionSB.append(", ");
					}
				}
				damageCostDescriptionSB.append("<b>" + seEntry.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1)
						+ "</b> turns"
						+(caster.hasTrait(Perk.JOB_MUSICIAN, true)
								?" ([style.boldExcellent(doubled)] from <b style='color:"+Perk.JOB_MUSICIAN.getColour().toWebHexString()+";'>"+Perk.JOB_MUSICIAN.getName(caster)+"</b>)"
								:"")
						+ " of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
				i++;
			}
			damageCostDescriptionSB.append(".</p>");
		}
		
		return damageCostDescriptionSB.toString();
	}
	
	public static String getBasicStatusEffectApplication(GameCharacter target, boolean beneficial, Map<StatusEffect, Integer> statusEffects) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		damageCostDescriptionSB.append(
				"<p>"
					+UtilText.parse(target,
							(!target.isPlayer()
								? "[npc.She] is now "
								: "You are now ")
							+(beneficial
									?"benefiting from "
									:"suffering from ")));
		
		int i = 0;
		for (Entry<StatusEffect, Integer> seEntry : statusEffects.entrySet()) {
			if (i != 0) {
				if (i == statusEffects.size() - 1) {
					damageCostDescriptionSB.append(" and ");
				} else {
					damageCostDescriptionSB.append(", ");
				}
			}
			damageCostDescriptionSB.append("<b>" + seEntry.getValue() + "</b> turns of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
			i++;
		}
		damageCostDescriptionSB.append(".</p>");
		
		return damageCostDescriptionSB.toString();
	}

	protected String getCostDescription(GameCharacter caster, float cost) {
		return "<p>"
					+ "Casting this spell cost "+(caster.isPlayer()?"you":UtilText.parse(caster, "[npc.name]"))+" <b>" + cost + "</b> <b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>aura</b>!</b>"
				+ "</p>";
	}

	/**
	 * Utility method for returning appropriate cast description based on the identity of caster and target. Variable names should be self-explanatory.
	 */
	private static String getCastDescription(GameCharacter caster, GameCharacter target,
			String playerSelfCast,
			String playerCastOnNPC,
			String NPCSelfCast,
			String NPCCastOnPlayer,
			String NPCCastOnNPC) {
		if(caster.isPlayer()) {
			if(target.isPlayer()) {
				return playerSelfCast;
			} else {
				return UtilText.parse(target, playerCastOnNPC);
			}
		} else {
			if(target.isPlayer()) {
				return UtilText.parse(caster, NPCCastOnPlayer);
			} else {
				if(target.equals(caster)) {
					return UtilText.parse(caster, NPCSelfCast);
				} else {
					return UtilText.parse(caster, target, NPCCastOnNPC);
				}
			}
		}
	}
	
	// Rendering:
	
	private static final int ROWS = 3;
	
	private static StringBuilder treeSB = new StringBuilder();
	private static StringBuilder spellSB = new StringBuilder();
	private static StringBuilder lineSB = new StringBuilder();
	private static StringBuilder entrySB = new StringBuilder();
	
	public static String getSpellTreesDisplay(SpellSchool school, GameCharacter character) {
		treeSB.setLength(0);
		appendSpellSchool(character, school);
		return treeSB.toString();
	}
	
	private static void appendSpellSchool(GameCharacter character, SpellSchool spellSchool) {
		treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
				+ "<div class='container-full-width' style='text-align:center;'><h6 style='color:"+spellSchool.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(spellSchool.getName())+"</h6>"
						+"<b style='color:"+spellSchool.getColour().toWebHexString()+";'>"+character.getSpellUpgradePoints(spellSchool)+"</b> <b>Upgrade Points Available</b></div>");
		
		for(Spell spell : Spell.getSpellsFromSchoolMap().get(spellSchool)) {
//			System.out.println(spell.getName());
			if(!spell.getSpellUpgradeTree().isEmpty()) {
				treeSB.append("<div class='container-full-width' style='width:25%; padding:0; margin:0;'>");
					for(int row=-1; row<ROWS; row++) {
						treeSB.append(appendSpell(character, row, spell));
					}
				treeSB.append("</div>");
			}
		}
		
		treeSB.append("</div>");
	}
	
	private static String appendSpell(GameCharacter character, int row, Spell spell) {
		spellSB.setLength(0);

		spellSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
			if(row==-1) {
				boolean disabled = !character.hasSpell(spell);
				spellSB.append("<div class='square-button "+(disabled?" disabled":"")+"' style='width:50%; margin:8px 25%; cursor: default; "
										+(!disabled?"border-color:"+spell.getSpellSchool().getColour().toWebHexString()+";":"")+"' id='SPELL_TREE_"+spell+"'>"
									+ "<div class='square-button-content' style='cursor: default;'>"+spell.getSVGString()+"</div>"
									+ (disabled
										?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8;'></div>"
										:"")
								+ "</div>");
				
			} else {
				List<TreeEntry<SpellSchool, SpellUpgrade>> upgradesList = spell.getSpellUpgradeTree().get(row);
				int size = upgradesList.size();
				
				spellSB.append(getHorizontalLine(character, spell, row));
				for(TreeEntry<SpellSchool, SpellUpgrade> entry : upgradesList) {
					spellSB.append(getUpgradeEntry(character, spell, entry, size));
				}
			}
		spellSB.append("</div>");
		return spellSB.toString();
	}
	
	private static String getHorizontalLine(GameCharacter character, Spell spell, int row) {
		lineSB.setLength(0);
		
		for(TreeEntry<SpellSchool, SpellUpgrade> entry : spell.getSpellUpgradeTree().get(row)) {
			float entryX = getX(spell, entry.getRow(), entry);
			for(TreeEntry<SpellSchool, SpellUpgrade> siblingEntry : entry.getSiblingLinks()) {
				float siblingX = getX(spell, siblingEntry.getRow(), siblingEntry);
				lineSB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
						+ "<svg width='100%' height='100%'><line x1='"+siblingX+"%' y1='50%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineSiblingColour(character, spell, entry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
			}
			for(TreeEntry<SpellSchool, SpellUpgrade> parentEntry : entry.getParentLinks()) {
				float parentX = getX(spell, parentEntry.getRow(), parentEntry);
				String colour = getPerkLineParentColour(character, spell, entry).toWebHexString();
						
				if(Math.abs(entryX-parentX)>0.01f) {
					lineSB.append("<div style='width:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events: none;'>"
							+ "<svg style='padding:0; margin:0;' width='100%'><line x1='"+entryX+"%' y1='0' x2='"+parentX+"%' y2='0' stroke='"+colour+"' stroke-width='4px'/></svg></div>");
				}
			}
		}
		
		return lineSB.toString();
	}
	
	private static float getMargin(int size) {
		return (100-(size*40))/(size*2f);
	}
	
	private static float getX(Spell spell, int row, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		List<TreeEntry<SpellSchool, SpellUpgrade>> list = spell.getSpellUpgradeTree().get(row);
		int size = list.size();
		float marginSize = getMargin(size);
		int column = list.indexOf(entry);
		
		return ((marginSize*(column)*2)+(column*40)+20+marginSize);
	}
	
	private static String getUpgradeEntry(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> perkEntry, int size) {
		
		entrySB.setLength(0);
		
		boolean disabled = !character.hasSpellUpgrade(perkEntry.getEntry()) && !isSpellUpgradeAvailable(character, spell, perkEntry);
		
		// Append up/down lines:
		float entryX = getX(spell, perkEntry.getRow(), perkEntry);
		if(!perkEntry.getParentLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='0%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineParentColour(character, spell, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		if(!perkEntry.getChildLinks().isEmpty()) {
			entrySB.append("<div style='width:100%; height:100%; padding:0; margin:0; top:0; left:0; position:absolute; pointer-events:none;'>"
					+ "<svg width='100%' height='100%'><line x1='"+entryX+"%' y1='100%' x2='"+entryX+"%' y2='50%' stroke='"+getPerkLineChildColour(character, spell, perkEntry).toWebHexString()+"' stroke-width='2px'/></svg></div>");
		}
		
		entrySB.append("<div class='square-button round"+(disabled?" disabled":"")+"' style='width:40%; margin:8px "+getMargin(size)+"%; cursor:auto; "+
										(character.hasSpellUpgrade(perkEntry.getEntry())
											?"border-color:"+perkEntry.getCategory().getColour().toWebHexString()+";"
											:(!perkEntry.getEntry().isAvailable(character)
												?"border-color:"+Colour.GENERIC_BAD.toWebHexString()+";"
												:""))
										+"' id='SPELL_UPGRADE_"+perkEntry.getEntry()+"'>"
							+ "<div class='square-button-content'>"+perkEntry.getEntry().getSVGString()+"</div>"
							+ (disabled
								?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:50%; cursor:pointer;'></div>"
								:!character.hasSpellUpgrade(perkEntry.getEntry())
									?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:50%; cursor:pointer;'></div>"
									:"")
						+ "</div>");
		
		return entrySB.toString();
	}
	
	public static boolean isSpellUpgradeAvailable(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		if(character.hasSpell(spell) && entry.getEntry().isAlwaysAvailable()) {
			return true;
		}
		if(!entry.getEntry().isAvailable(character)) {
			return false;
		}
		if(!character.hasSpellUpgrade(entry.getEntry())) {
			for(TreeEntry<SpellSchool, SpellUpgrade> linkedEntry : entry.getLinks()) {
				if(character.hasSpellUpgrade(linkedEntry.getEntry())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static Colour getPerkLineParentColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean parentOwned = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> parent : entry.getParentLinks()) {
			if(character.hasSpellUpgrade(parent.getEntry())) {
				parentOwned = true;
				break;
			}
		}
		
		return character.hasSpellUpgrade(entry.getEntry()) && parentOwned
				?entry.getCategory().getColour()
				:isSpellUpgradeAvailable(character, spell, entry)
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	private static Colour getPerkLineChildColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean childOwned = false;
		boolean childAvailable = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> child : entry.getChildLinks()) {
			if(character.hasSpellUpgrade(child.getEntry())) {
				childOwned = true;
			}
			if(isSpellUpgradeAvailable(character, spell, child)) {
				childAvailable = true;
			}
		}
		
		return character.hasSpellUpgrade(entry.getEntry()) && childOwned
				?entry.getCategory().getColour()
				:childAvailable
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
	
	private static Colour getPerkLineSiblingColour(GameCharacter character, Spell spell, TreeEntry<SpellSchool, SpellUpgrade> entry) {
		boolean siblingOwned = false;
		boolean siblingAvailable = false;
		for(TreeEntry<SpellSchool, SpellUpgrade> sibling : entry.getSiblingLinks()) {
			if(character.hasSpellUpgrade(sibling.getEntry())) {
				siblingOwned = true;
			}
			if((isSpellUpgradeAvailable(character, spell, sibling) && character.hasSpellUpgrade(entry.getEntry()))
					|| (isSpellUpgradeAvailable(character, spell, entry) && character.hasSpellUpgrade(sibling.getEntry()))) {
				siblingAvailable = true;
			}
		}
		
		return isSpellUpgradeAvailable(character, spell, entry) && siblingOwned
				?entry.getCategory().getColour()
				:siblingAvailable
					?Colour.BASE_GREY
					:Colour.TEXT_GREY_DARK;
	}
}
