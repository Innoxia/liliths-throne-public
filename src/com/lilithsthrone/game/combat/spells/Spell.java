package com.lilithsthrone.game.combat.spells;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AbstractAttribute;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.AbstractStatusEffect;
import com.lilithsthrone.game.character.effects.EffectBenefit;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.CombatBehaviour;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.combat.DamageVariance;
import com.lilithsthrone.game.combat.moves.AbstractCombatMove;
import com.lilithsthrone.game.combat.moves.CombatMoveType;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.utils.SpellManagement;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.SvgUtil;
import com.lilithsthrone.utils.Units;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public enum Spell {

	// FIRE:
	
	FIREBALL(false,
			SpellSchool.FIRE,
			SpellType.OFFENSIVE,
			DamageType.FIRE,
			false,
			"Fireball",
			"fireball",
			"Summons a ball of arcane flames that can be launched at a target.",
			30,
			DamageVariance.LOW,
			75,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.FIREBALL_1,
					SpellUpgrade.FIREBALL_2,
					SpellUpgrade.FIREBALL_3),
			null, null) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.LINGERING_FLAMES, 2));
			} else {
				return new HashMap<>();
			}
		}
		
		@Override
		public int getDamage(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2) && !caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_3)) {
				return 15;
			}
			return 30;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals "+AbstractCombatMove.getFormattedDamage(getDamageType(), Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, false), target, false, isTargetAtMaximumLust(target))+" damage.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			
			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"By the fires of eternity, I release this seal upon the maelstrom within! Come forth, flames of destruction!",
													"From beyond the veil of flames, I hear the arcane's call! Through me, now is hell unleashed!",
													"May the dark void shatter the ancient seals which keep the fires of hell itself at bay! Go forth, flaming fury!"),
											"Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at yourself!",
											"Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at [npc.name]!",
											"",
											"Summoning a swirling vortex of arcane fire around [npc.her] [npc.arm], [npc.she] focuses its raw power into a ball of roiling flames before launching it directly at you!",
											"Summoning a swirling vortex of arcane fire around [npc1.her] [npc1.arm], [npc1.name] focuses its raw power into a ball of roiling flames before launching it directly at [npc2.name]!")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
				descriptionSB.append(" The fireball instantly splits in two after being cast!");
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(applyDamage(caster, target, damage));

				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
				
				// Second fireball:
				if(caster.hasSpellUpgrade(SpellUpgrade.FIREBALL_2)) {
					damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
					GameCharacter secondaryTarget = Main.combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<br/>"
								+"The second Fireball swerves around to hit "+UtilText.parse(target,"[npc.name]")+" for a second time!");
						
						descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
						descriptionSB.append(applyDamage(caster, target, damage));
						
					} else {
						descriptionSB.append("<br/>"
								+"The second Fireball swerves around to hit "+(secondaryTarget.isPlayer()?"you":UtilText.parse(secondaryTarget,"[npc.name]"))+"!");
						
						descriptionSB.append(getDamageDescription(caster, secondaryTarget, damage, isHit, isCritical));
						descriptionSB.append(applyDamage(caster, secondaryTarget, damage));
						applyStatusEffects(caster, secondaryTarget, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, secondaryTarget, isHit, isCritical));
					}
					
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	FLASH(false,
			SpellSchool.FIRE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.FIRE,
			false,
			"Flash",
			"flash",
			"Creates a blinding flash of light which dazzles the target.",
			0,
			DamageVariance.LOW,
			50,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH, 1)),
			Util.newArrayListOfValues(
					SpellUpgrade.FLASH_1,
					SpellUpgrade.FLASH_2,
					SpellUpgrade.FLASH_3),
			null,
			Util.newArrayListOfValues("[style.colourExcellent(Dazzles)] the target for [style.colourTerrible(-1)] [style.colourActionPoints(action points)]!")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH_1, 1));
			} else {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.FLASH, 1));
			}
		}
		
		@Override
		public int getBaseCost(GameCharacter caster) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_3)) {
				return 25;
			} else {
				return 50;
			}
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.FLASH_1)) {
				return "Dazzles for [style.colourTerrible(-2)] action points!";
			}
			return "Dazzles for [style.colourTerrible(-1)] action points!";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			
			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Locked away for a thousand years, the powers lying dormant within me have awoken! Now, bear witness to the blinding fury of a universe being born!",
													"The light of a million stars are as nothing to the power that I now unleash! May my arcane seals be broken, and blind all who dare stand before me!",
													"By fury of sun, and gaze of moon, may the heavens themselves bear witness to the power that I now unleash! Gaze upon the end of worlds, and despair!"),
											"With a flick of your wrist, you summon a blinding flash of light right in front of your own face!",
											"With a flick of your wrist, you summon a blinding flash of light right in front of [npc.namePos] face!",
											"",
											"With a flick of [npc.her] wrist, [npc.name] summons a blinding flash of light right in front of your face!",
											"With a flick of [npc1.her] wrist, [npc1.name] summons a blinding flash of light right in front of [npc2.namePos] face!")
								);
			if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
				descriptionSB.append(" A secondary flash of light arcs away from the first, seeking out another target!");
			}

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				
				// Second flash:
				if(caster.hasSpellUpgrade(SpellUpgrade.FLASH_2)) {
					GameCharacter secondaryTarget = Main.combat.getRandomAlliedPartyMember(target);
					
					if(secondaryTarget.equals(target)) {
						descriptionSB.append("<br/>"
								+"The second Flash fails to find another target, and quickly fizzles out...");
						
					} else {
						descriptionSB.append("<br/>"
								+"The second Flash shoots out in front of "+UtilText.parse(secondaryTarget,"[npc.namePos] face, dazzling [npc.herHim] as well!"));

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
			
			return descriptionSB.toString();
		}
	},
	
	CLOAK_OF_FLAMES(false,
			SpellSchool.FIRE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.FIRE,
			true,
			"Cloak of Flames",
			"cloak_of_flames",
			"Shrouds the target in a protective cloak of arcane flames, granting them improved fire and ice resistance.",
			0,
			DamageVariance.LOW,
			50,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3)),
			Util.newArrayListOfValues(
					SpellUpgrade.CLOAK_OF_FLAMES_1,
					SpellUpgrade.CLOAK_OF_FLAMES_2,
					SpellUpgrade.CLOAK_OF_FLAMES_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_FIRE, 5),
					new Value<>(Attribute.RESISTANCE_ICE, 10)),
			Util.newArrayListOfValues("Lasts for [style.colourGood(3 turns)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_3)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_3, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_2)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_2, 3));
				
			} else if(caster!=null && caster.hasSpellUpgrade(SpellUpgrade.CLOAK_OF_FLAMES_1)) {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES_1, 3));
				
			} else {
				return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.CLOAK_OF_FLAMES, 3));
			}
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Grants the target a protective cloak of flames.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"By my power, shall glaciers melt, and a thousand suns be extinguished! Oh, fury of the stars, I call upon thee to shield your [npc.master]!",
													"Know now that the flames of hell itself obey my every command, and from the fiery chasm of the dimension of flame, I summon forth the power to shield me from all harm!",
													"By fire and fury, I unleash the infernal flames of the sun itself! Come now, arcane inferno, shield your [npc.master] from all that dare harm [npc.herHim]!"),
											"With a swipe of your [pc.arm], you summon a protective cloak of arcane fire around yourself!",
											"With a swipe of your [pc.arm], you summon a protective cloak of arcane fire around [npc.name]!",
											"With a swipe of [npc.her] [npc.arm], [npc.name] summons a protective cloak of arcane fire around [npc.herself]!",
											"With a swipe of [npc.her] [npc.arm], [npc.name] summons a protective cloak of arcane fire around you!",
											"With a swipe of [npc1.her] [npc1.arm], [npc1.name] summons a protective cloak of arcane fire around [npc2.name]!"));
			
			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_1);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_2);
				target.removeStatusEffect(StatusEffect.CLOAK_OF_FLAMES_3);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_FIRE(false,
			SpellSchool.FIRE,
			SpellType.SUMMON,
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
					SpellUpgrade.ELEMENTAL_FIRE_1,
					SpellUpgrade.ELEMENTAL_FIRE_2,
					SpellUpgrade.ELEMENTAL_FIRE_3A,
					SpellUpgrade.ELEMENTAL_FIRE_3B),
			null, Util.newArrayListOfValues("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolFire(Fire)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do]n't know this spell, so cannot cast it!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Spells cannot be cast while in captivity!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "While in combat, spells can only be cast as a combat move!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_FIRE?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Spells can only be cast in a neutral scene!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner) && !owner.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] at least <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] in order to cast this spell!"));
				
			} else {
				String description = owner.isPlayer()?"Summon your elemental by binding it to the school of Fire!":"Get [npc.name] to summon [npc.her] elemental by binding it to the school of Fire!";
				String cost = " This will cost <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)]!";
				if(owner.getMana()<this.getModifiedCost(owner)) {
					cost = " This will cost <b>"+Math.round((owner.getMana()-this.getModifiedCost(owner))*-0.25f)+"</b> [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]!";
				}
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons your elemental in the form of fire.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			if(caster.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
	    		cost = Main.combat.getManaBurnStack().get(caster).remove(0);
			}
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}
			
			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.FIRE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
									?Util.randomItemFrom(
									Util.newArrayListOfValues(
										"[npc.speech(By the ancient rites of flame, I summon forth hell and fury incarnate! Answer your [npc.master]'s call, [npc2.name], and, by the incineration of a million dimensions, be bound to my will!)] ",
										"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! I call upon the dimension of flame itself, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
										"[npc.speech(Let fire consume, and the inferno within me be unleashed! Flame and fury, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
									:"")
								+(caster.isPlayer()
									?"With a flash of light and a burst of flames, you bind your elemental, [npc2.name], to the school of Fire!"
									:"With a flash of light and a burst of flames, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Fire!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rites of flame, I summon forth hell and fury incarnate! Answer your [npc.master]'s call, [npc2.name], and, by the incineration of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! I call upon the dimension of flame itself, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let fire consume, and the inferno within me be unleashed! Flame and fury, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+(caster.isPlayer()
									?"With a flash of light and a burst of flames, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Fire!"
									:"With a flash of light and a burst of flames, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Fire!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	// WATER:

	ICE_SHARD(false,
			SpellSchool.WATER,
			SpellType.OFFENSIVE,
			DamageType.ICE,
			false,
			"Ice Shard",
			"ice_shard",
			"Summons a shard of ice that can be launched at a target.",
			25,
			DamageVariance.LOW,
			35,
			null, Util.newArrayListOfValues(
							SpellUpgrade.ICE_SHARD_1,
							SpellUpgrade.ICE_SHARD_2,
							SpellUpgrade.ICE_SHARD_3), null, null) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3) && isCritical) {
					return Util.newHashMapOfValues(
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3),
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FROZEN, 1));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)){
					return Util.newHashMapOfValues(
							new Value<AbstractStatusEffect, Integer>(StatusEffect.FREEZING_FOG, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals "+AbstractCombatMove.getFormattedDamage(getDamageType(), Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, false), target, false, isTargetAtMaximumLust(target))+" damage.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Witness now, the terror of the starless void! By hail of ice, and fury of the blizzard, let my arcane power be unleashed!",
													"The seals within me have been broken, and by my power, now will the universe itself freeze! Hail, sleet, and ice, hear my call, and come forth!",
													"From beyond the frozen void, now is my power unleashed! Crossing the boundary from the frozen realm of chaos, let my power be manifest!"),
											"Summoning a swirling vortex of water from the moisture in the air, you focus your energy on freezing it in place, creating a shard of ice that you then launch at yourself!",
											"Summoning a swirling vortex of water from the moisture in the air, you focus your energy on freezing it in place, creating a shard of ice that you then launch at [npc.name]!",
											"",
											"Summoning a swirling vortex of water from the moisture in the air, [npc1.name] focuses [npc1.her] energy on freezing it in place, creating a shard of ice that [npc.she] then launches at you!",
											"Summoning a swirling vortex of water from the moisture in the air, [npc1.name] focuses [npc1.her] energy on freezing it in place, creating a shard of ice that [npc.she] then launches at [npc2.name]!")
								);
			
			if(isHit && isCritical && caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_2)) {
				descriptionSB.append(" The freezing fog detonates as the Ice Shard travels through it");
				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_3)) {
					descriptionSB.append(", entombing everything in the immediate vicinity in a thin layer of ice!");
				} else {
					descriptionSB.append("!");
				}
			}
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.ICE_SHARD_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
		
		public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Target has the 'freezing fog' status effect.");
	    }
		
		//Differs from normal version; spells have special crit requirements.
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return target.hasStatusEffect(StatusEffect.FREEZING_FOG) || Main.combat.getStatusEffectsToApply().get(target).containsKey(StatusEffect.FREEZING_FOG);
		}
	},

	RAIN_CLOUD(false,
			SpellSchool.WATER,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.ICE,
			false,
			"Rain Cloud",
			"rain_cloud",
			"Summons a small cloud of arcane-enchanted rain above the target's head, which saps their ability to cast spells.",
			0,
			DamageVariance.LOW,
			33,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.RAIN_CLOUD_1,
					SpellUpgrade.RAIN_CLOUD_2,
					SpellUpgrade.RAIN_CLOUD_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.SPELL_COST_MODIFIER, -25)), Util.newArrayListOfValues("Lasts for [style.colourGood(3 turns)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DOWNPOUR, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.RAIN_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD_DEEP_CHILL, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.RAIN_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons a raincloud above the target.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(//TODO chuuni three from here
													"May the heavens burst, and floods sweep the Earth! By the powers manifest within me, I tear open the skies, and deliver unto you your watery grave!"),
											"With an upwards thrust of your [pc.arm], you summon forth a cloud of rain above your own head!",
											"With an upwards thrust of your [pc.arm], you summon forth a cloud of rain above [npc.namePos] head!",
											"",
											"With an upwards thrust of [npc.her] [npc.arm], [npc.name] summons forth a cloud of rain above your head!",
											"With an upwards thrust of [npc1.her] [npc1.arm], [npc1.name] summons forth a cloud of rain above [npc2.namePos] head!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR_FOR_CLOUDBURST);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DOWNPOUR);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD_DEEP_CHILL);
				target.removeStatusEffect(StatusEffect.RAIN_CLOUD);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	SOOTHING_WATERS(false,
			SpellSchool.WATER,
			SpellType.DEFENSIVE_HEAL,
			DamageType.ICE,
			true,
			"Soothing Waters",
			"soothing_waters",
			"Summons an orb of soothing arcane-infused water, which restores the "+Attribute.HEALTH_MAXIMUM.getName()+" of anyone who drinks it.",
			0,
			DamageVariance.LOW,
			100,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.SOOTHING_WATERS_1_CLEAN,
					SpellUpgrade.SOOTHING_WATERS_2_CLEAN,
					SpellUpgrade.SOOTHING_WATERS_1,
					SpellUpgrade.SOOTHING_WATERS_2,
					SpellUpgrade.SOOTHING_WATERS_3),
			null, Util.newArrayListOfValues("[style.boldGood(Restores)] 20% [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]")) {
		@Override
		public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
			return Spell.soothingWatersUpgradeTree;
		}
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do]n't know this spell, so cannot cast it!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Spells cannot be cast while in captivity!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "While in combat, spells can only be cast as a combat move!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_WATER?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Spells can only be cast in a neutral scene!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] at least <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] in order to cast this spell!"));
				
			} else {
				String description = owner.isPlayer()?"Cast Soothing Waters":"Get [npc.name] to cast Soothing Waters";
				if(owner==target) {
					description+=" on [npc.herself]!";
				} else {
					description+=" on [npc2.name]!";
				}
				String cost = " This will cost <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)]!";
				return new Value<>(true, UtilText.parse(owner, target, description+"<br/>"+cost));
			}
		}
		@Override
		public int getAPCost() {
			return 3;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Heals the target.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"By stream and brook, river and sea, I call upon the eternal currents that know no end! I cast my power unto thee, and bring forth the fountain of eternal life!"),
											"With a gentle swish of your [pc.hand], you summon forth an orb of healing water, which you quickly drink.",
											"With a gentle swish of your [pc.hand], you summon forth an orb of healing water, which you send to [npc.name] to drink.",
											"With a gentle swish of [npc.her] [npc.hand], [npc.name] summons forth an orb of healing water, which [npc.she] quickly drinks.",
											"With a gentle swish of [npc.her] [npc.hand], [npc.name] summons forth an orb of healing water, which [npc.she] sends to you to drink.",
											"With a gentle swish of [npc.her] [npc.hand], [npc.name] summons forth an orb of healing water, which [npc.she] sends to [npc2.name]to drink."));

			if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3) ) {
				descriptionSB.append(" Smaller globes of water split off from the primary orb!");
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_3)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));

					descriptionSB.append("<br/>"
								+ "The orb of water heals "+UtilText.parse(target,"[npc.name]")+" for a total of "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
//					descriptionSB.append("<br/>"
//											+ UtilText.parse(target, "One of the small orbs circles around to heal [npc.name] for a second time, restoring a total of "
//																		+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
//																		+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"));
//					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.5f));
//					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.3f);
					
					if(Main.game.isInCombat()) {
						List<GameCharacter> alliesPlusCaster = new ArrayList<>(Main.combat.getAllies(caster));
						alliesPlusCaster.add(caster);
						for(GameCharacter combatant : alliesPlusCaster) {
							descriptionSB.append("<br/>"
									+ UtilText.parse(combatant, "One of the small orbs flies towards [npc.name], healing [npc.herHim] for a total of "
																+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
																+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!"));
							descriptionSB.append(applyDamage(caster, combatant, -combatant.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.1f));
							combatant.incrementMana(combatant.getAttributeValue(Attribute.MANA_MAXIMUM)*0.1f);
							if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1_CLEAN)) {
								descriptionSB.append(UtilText.parse(combatant, "<br/>[npc.NamePos] body and all of [npc.her] worn clothing is [style.colourAqua(cleaned)] by the spell!"));
								combatant.cleanAllClothing(false, false);
								combatant.cleanAllDirtySlots(true);
							}
							if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2_CLEAN)) {
								descriptionSB.append(UtilText.parse(combatant, "<br/>[npc.NamePos] body is [style.colourAqua(thoroughly washed)] by the spell!"));
								combatant.drainTotalFluidsStored(SexAreaOrifice.ANUS, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.VAGINA, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, 250);
								combatant.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, 250);
							}
						}
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+ "The orb of water heals "+UtilText.parse(target,"[npc.name]")+" for a total of "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.4f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1)) {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+"The orb of water heals "+UtilText.parse(target,"[npc.name]")+" for a total of "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+" and "
									+(int)(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f)+" "+Attribute.MANA_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
					target.incrementMana(target.getAttributeValue(Attribute.MANA_MAXIMUM)*0.2f);
					
				} else {
					descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
					descriptionSB.append("<br/>"
								+ "The orb of water heals "+UtilText.parse(target,"[npc.name]")+" for a total of "
									+(int)(target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f)+" "+Attribute.HEALTH_MAXIMUM.getColouredName("b")+"!");
					descriptionSB.append(applyDamage(caster, target, -target.getAttributeValue(Attribute.HEALTH_MAXIMUM)*0.2f));
				}
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_1_CLEAN)) {
					descriptionSB.append(UtilText.parse(target, "<br/>[npc.NamePos] body and all of [npc.her] worn clothing is [style.colourAqua(cleaned)] by the spell!"));
					target.cleanAllClothing(false, false);
					target.cleanAllDirtySlots(true);
				}
				if(caster.hasSpellUpgrade(SpellUpgrade.SOOTHING_WATERS_2_CLEAN)) {
					descriptionSB.append(UtilText.parse(target, "<br/>[npc.NamePos] body is [style.colourAqua(thoroughly washed)] by the spell!"));
					target.drainTotalFluidsStored(SexAreaOrifice.ANUS, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE_CROTCH, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, 250);
					target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, 250);
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			
			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_WATER(false,
			SpellSchool.WATER,
			SpellType.SUMMON,
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
					SpellUpgrade.ELEMENTAL_WATER_1,
					SpellUpgrade.ELEMENTAL_WATER_2,
					SpellUpgrade.ELEMENTAL_WATER_3A,
					SpellUpgrade.ELEMENTAL_WATER_3B),
			null, Util.newArrayListOfValues("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolWater(Water)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do]n't know this spell, so cannot cast it!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Spells cannot be cast while in captivity!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "While in combat, spells can only be cast as a combat move!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_WATER?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Spells can only be cast in a neutral scene!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] at least <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] in order to cast this spell!"));
				
			} else {
				String description = owner.isPlayer()?"Summon your elemental by binding it to the school of Water!":"Get [npc.name] to summon [npc.her] elemental by binding it to the school of Water!";
				String cost = " This will cost <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons your elemental in the form of water.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.WATER);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of sea and sky, I summon forth the eternal torrent! Answer your [npc.master]'s call, [npc2.name], and, by the drowning of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May all waters be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the seas rise, and the tsunami within me be unleashed! Flood and deluge, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a huge splash, you bind your elemental, [npc2.name], to the school of Water!"
									:"With a huge splash, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Water!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of sea and sky, I summon forth the eternal torrent! Answer your [npc.master]'s call, [npc2.name], and, by the drowning of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May all waters be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the seas rise, and the tsunami within me be unleashed! Flood and deluge, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a huge splash, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Water!"
									:"With a huge splash, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Water!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	// AIR:
	
	POISON_VAPOURS(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.POISON,
			false,
			"Poison Vapours",
			"poison_vapours",
			"Summons a cloud of poisonous gas around a target.",
			0,
			DamageVariance.LOW,
			50,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.POISON_VAPOURS_1,
					SpellUpgrade.POISON_VAPOURS_2,
					SpellUpgrade.POISON_VAPOURS_3),
			null,
			Util.newArrayListOfValues("<b>25</b> [style.colourPoison(Poison Damage)] per turn for [style.colourGood(3 turns)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.POISON_VAPOURS_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS_CHOKING_HAZE, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.POISON_VAPOURS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons a poison cloud around the target.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Let seal be broken, and by true power be unleashed! Now, by haze and smog, the air itself obeys my command!"),
											"With a sweeping motion of your [pc.arm], you summon forth a cloud of poison vapours around yourself!",
											"With a sweeping motion of your [pc.arm], you summon forth a cloud of poison vapours around [npc.name]!",
											"",
											"With a sweeping motion of [npc.her] [npc.arm], [npc.name] summons forth a cloud of poison vapours around you!",
											"With a sweeping motion of [npc1.her] [npc1.arm], [npc1.name] summons forth a cloud of poison vapours around [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_WEAKENING_CLOUD);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_ARCANE_SICKNESS);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS_CHOKING_HAZE);
				target.removeStatusEffect(StatusEffect.POISON_VAPOURS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	VACUUM(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT_MINOR_DAMAGE,
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
					SpellUpgrade.VACUUM_1,
					SpellUpgrade.VACUUM_2,
					SpellUpgrade.VACUUM_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.ENERGY_SHIELDING, -5)), Util.newArrayListOfValues("Lasts for [style.colourGood(4 turns)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_TOTAL_VOID, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_SUCTION, 4));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.VACUUM_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM_SECONDARY_VOIDS, 4));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.VACUUM, 4));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons a disruptive vacuum next to the target.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Darkness blacker than black! I call upon the eternal void, and by rift through time and space, is my power made manifest!"),
											"With a clench of your fist, you summon forth a vacuum right next to yourself!",
											"With a clench of your fist, you summon forth a vacuum right next to [npc.name]!",
											"",
											"With a clench of [npc.her] fist, [npc.name] summons forth a vacuum right next to you!",
											"With a clench of [npc.her] fist, [npc.name] summons forth a vacuum right next to [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(applyDamage(caster, target, damage));

				target.removeStatusEffect(StatusEffect.VACUUM_TOTAL_VOID);
				target.removeStatusEffect(StatusEffect.VACUUM_SUCTION);
				target.removeStatusEffect(StatusEffect.VACUUM_SECONDARY_VOIDS);
				target.removeStatusEffect(StatusEffect.VACUUM);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	PROTECTIVE_GUSTS(false,
			SpellSchool.AIR,
			SpellType.DEFENSIVE_STATUS_EFFECT,
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
					SpellUpgrade.PROTECTIVE_GUSTS_1,
					SpellUpgrade.PROTECTIVE_GUSTS_2,
					SpellUpgrade.PROTECTIVE_GUSTS_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_POISON, 5),
					new Value<>(Attribute.ENERGY_SHIELDING, 1)),
			Util.newArrayListOfValues("Lasts for [style.colourGood(3 turns)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 5));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.PROTECTIVE_GUSTS_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.PROTECTIVE_GUSTS, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons a benevolent wind to protect the target.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"The very air itself obeys my command! Now, may my true power be unleashed, and bind the wind itself to my will!"),
											"Swishing both of your [pc.arms] up into the air, you summon forth a benevolent wind to help protect you!",
											"Swishing both of your [pc.arms] up into the air, you summon forth a benevolent wind to help protect [npc.name]!",
											"Swishing both of [npc.her] [npc.arms] up into the air, [npc.name] summons forth a benevolent wind to help protect [npc.herHim]!",
											"Swishing both of [npc.her] [npc.arms] up into the air, [npc.name] summons forth a benevolent wind to help protect you!",
											"Swishing both of [npc.her] [npc.arms] up into the air, [npc.name] summons forth a benevolent wind to help protect [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_FOCUSED_BLAST);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS_GUIDING_WIND);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_AIR(false,
			SpellSchool.AIR,
			SpellType.SUMMON,
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
					SpellUpgrade.ELEMENTAL_AIR_1,
					SpellUpgrade.ELEMENTAL_AIR_2,
					SpellUpgrade.ELEMENTAL_AIR_3A,
					SpellUpgrade.ELEMENTAL_AIR_3B),
			null, Util.newArrayListOfValues("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolAir(Air)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do]n't know this spell, so cannot cast it!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Spells cannot be cast while in captivity!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "While in combat, spells can only be cast as a combat move!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_AIR?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Spells can only be cast in a neutral scene!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] at least <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] in order to cast this spell!"));
				
			} else {
				String description = owner.isPlayer()?"Summon your elemental by binding it to the school of Air!":"Get [npc.name] to summon [npc.her] elemental by binding it to the school of Air!";
				String cost = " This will cost <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons your elemental in the form of air.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.AIR);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of wind and gale, I summon forth the hurricane itself! Answer your [npc.master]'s call, [npc2.name], and, by the offering of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May the air itself be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the winds rise, and the hurricane within me be unleashed! Storm and chaos, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a tremendous gust of wind, you bind your elemental, [npc2.name], to the school of Air!"
									:"With a tremendous gust of wind, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Air!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of wind and gale, I summon forth the hurricane itself! Answer your [npc.master]'s call, [npc2.name], and, by the offering of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May the air itself be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the winds rise, and the hurricane within me be unleashed! Storm and chaos, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a tremendous gust of wind, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Air!"
									:"With a tremendous gust of wind, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Air!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	// EARTH:
	
	SLAM(false,
			SpellSchool.EARTH,
			SpellType.OFFENSIVE,
			DamageType.PHYSICAL,
			false,
			"Slam",
			"slam",
			"Summons a crushing wave of force that slams down onto a target.",
			40,
			DamageVariance.LOW,
			60,
			null, Util.newArrayListOfValues(
							SpellUpgrade.SLAM_1,
							SpellUpgrade.SLAM_2,
							SpellUpgrade.SLAM_3), null, null) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.SLAM_AFTER_SHOCK, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.SLAM_GROUND_SHAKE, 2));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals "+AbstractCombatMove.getFormattedDamage(getDamageType(), Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, false), target, false, isTargetAtMaximumLust(target))+" damage.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"May the mountains shake, and the earth be split asunder! My power now shall be unleashed, and the cosmic forces at my command deliver unto you your demise!"),
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
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}

				if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_3)) {
					for(GameCharacter combatant : Main.combat.getEnemies(caster)) {
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.SLAM_1)) {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	TELEKENETIC_SHOWER(false,
			SpellSchool.EARTH,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.PHYSICAL,
			false,
			"Telekinetic Shower",
			"telekinetic_shower",
			"Lifts any small objects in the surrounding area into the air, before hurling them at the target.",
			0,
			DamageVariance.LOW,
			125,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.TELEKENETIC_SHOWER_1,
					SpellUpgrade.TELEKENETIC_SHOWER_2,
					SpellUpgrade.TELEKENETIC_SHOWER_3),
			null, Util.newArrayListOfValues("<b>25</b> [style.colourPhysical(Physical Damage)] per turn for [style.colourGood(3 turns)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEKENETIC_SHOWER_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 6));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEKENETIC_SHOWER, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Launch small objects at your target to damage and disrupt them.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"The cosmic forces of the universe obey my every command! With this dimensional vortex, shall I warp the boundaries of space and time!"),
											"Raising your [pc.arms], you lift all manner of small objects in the immediate vicinity up into the air, before hurling them at yourself!",
											"Raising your [pc.arms], you lift all manner of small objects in the immediate vicinity up into the air, before hurling them at [npc.name]!",
											"",
											"Raising [npc.her] [npc.arms], [npc.name] lifts all manner of small objects in the immediate vicinity up into the air, before hurling them at you!",
											"Raising [npc.her] [npc.arms], [npc.name] lifts all manner of small objects in the immediate vicinity up into the air, before hurling them at [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_PRECISION_STRIKES);
				target.removeStatusEffect(StatusEffect.TELEKENETIC_SHOWER_UNSEEN_FORCE);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},

	STONE_SHELL(false,
			SpellSchool.EARTH,
			SpellType.DEFENSIVE_STATUS_EFFECT,
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
					SpellUpgrade.STONE_SHELL_1,
					SpellUpgrade.STONE_SHELL_2,
					SpellUpgrade.STONE_SHELL_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_PHYSICAL, 5)), Util.newArrayListOfValues("Lasts for [style.colourGood(3 turns)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_HARDENED_CARAPACE, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.STONE_SHELL_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL_SHIFTING_SANDS, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.STONE_SHELL, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summon a shield to protect your target.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"The confinement of my astral powers is at an end! By my power unleashed, the very Earth itself serves its [npc.master]'s call!"),
											"Thrusting your [pc.hand] forwards, you summon forth a levitating stone shell to protect you from incoming attacks!",
											"Thrusting your [pc.hand] forwards, you summon forth a levitating stone shell to protect [npc.name] from incoming attacks!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth a levitating stone shell to protect [npc.herHim] from incoming attacks!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth a levitating stone shell to protect you from incoming attacks!",
											"Thrusting [npc1.her] [npc1.hand] forwards, [npc1.name] summons forth a levitating stone shell to protect [npc2.name] from incoming attacks!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.removeStatusEffectCombat(StatusEffect.STONE_SHELL_EXPLOSIVE_FINISH));
				target.removeStatusEffect(StatusEffect.STONE_SHELL_HARDENED_CARAPACE);
				target.removeStatusEffect(StatusEffect.STONE_SHELL_SHIFTING_SANDS);
				target.removeStatusEffect(StatusEffect.PROTECTIVE_GUSTS);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_EARTH(false,
			SpellSchool.EARTH,
			SpellType.SUMMON,
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
					SpellUpgrade.ELEMENTAL_EARTH_1,
					SpellUpgrade.ELEMENTAL_EARTH_2,
					SpellUpgrade.ELEMENTAL_EARTH_3A,
					SpellUpgrade.ELEMENTAL_EARTH_3B),
			null, Util.newArrayListOfValues("Summons [style.colourArcane(Elemental)] in form of [style.colourSchoolEarth(Earth)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do]n't know this spell, so cannot cast it!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Spells cannot be cast while in captivity!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "While in combat, spells can only be cast as a combat move!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_EARTH?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Spells can only be cast in a neutral scene!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] at least <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] in order to cast this spell!"));
				
			} else {
				String description = owner.isPlayer()?"Summon your elemental by binding it to the school of Earth!":"Get [npc.name] to summon [npc.her] elemental by binding it to the school of Earth!";
				String cost = " This will cost <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons your elemental in the form of earth.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
//				System.out.println(caster.getName());
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.EARTH);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of sand and stone, I summon forth the earthquake itself! Answer your [npc.master]'s call, [npc2.name], and, by the crushing of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May the earth itself be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the earth shake, and the power within me be unleashed! Boulder and mountain, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a burst of rocks and debris, you bind your elemental, [npc2.name], to the school of Earth!"
									:"With a burst of rocks and debris, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Earth!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of sand and stone, I summon forth the earthquake itself! Answer your [npc.master]'s call, [npc2.name], and, by the crushing of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May the earth itself be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the earth shake, and the power within me be unleashed! Boulder and mountain, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a burst of rocks and debris, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Earth!"
									:"With a burst of rocks and debris, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Earth!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	// ARCANE:
	
	ARCANE_AROUSAL(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Arcane Arousal",
			"arcane_arousal",
			"Causes the target to witness a highly arousing arcane vision.",
			15,
			DamageVariance.LOW,
			50,
			null, Util.newArrayListOfValues(
							SpellUpgrade.ARCANE_AROUSAL_1,
							SpellUpgrade.ARCANE_AROUSAL_2,
							SpellUpgrade.ARCANE_AROUSAL_3), null, null) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION, 2));
					
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
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals "+AbstractCombatMove.getFormattedDamage(getDamageType(), Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, false), target, false, isTargetAtMaximumLust(target))+" damage.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"By the true nature of the arcane, do I deliver unto you the prophecy of Lilith herself! Behold, the fate that awaits all mortals who dare stand against me!"),
											"You focus your arcane energy on projecting an arousing vision into your own mind",
											"You focus your arcane energy on projecting an arousing vision into [npc.namePos] mind.",
											"",
											"[npc.Name] focuses [npc.her] arcane energy on projecting an arousing vision into your mind!",
											"[npc.Name] focuses [npc.her] arcane energy on projecting an arousing vision into [npc2.namePos] mind!"));
			
			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(target.incrementLust(damage, true));
				}
				
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_AROUSAL_2)) {
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_DIRTY_PROMISES);
					target.removeStatusEffect(StatusEffect.ARCANE_AROUSAL_LUSTFUL_DISTRACTION);
					
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	TELEPATHIC_COMMUNICATION(false,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
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
					SpellUpgrade.TELEPATHIC_COMMUNICATION_1,
					SpellUpgrade.TELEPATHIC_COMMUNICATION_2,
					SpellUpgrade.TELEPATHIC_COMMUNICATION_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 15)), Util.newArrayListOfValues("Lasts for [style.colourGood(5 turns)]")) {
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH, 10));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPATHIC_COMMUNICATION_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 10));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPATHIC_COMMUNICATION, 5));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Speak directly into the target's mind.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"By my mastery of all dimensions, I now shatter the boundaries of space and time! The heavens themselves shall hear my voice, and despair!"),
											"You focus your arcane energy on enabling your thoughts to be projected into others' minds!",
											"You focus your arcane energy on enabling [npc.namePos] thoughts to be projected into others' minds!",
											"[npc.Name] focuses [npc.her] arcane energy on enabling [npc.her] thoughts to be projected into others' minds!",
											"[npc.Name] focuses [npc.her] arcane energy on enabling your thoughts to be projected into others' minds!",
											"[npc1.Name] focuses [npc1.her] arcane energy on enabling [npc2.namePos] thoughts to be projected into others' minds!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_PROJECTED_TOUCH);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION);
				target.removeStatusEffect(StatusEffect.TELEPATHIC_COMMUNICATION);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ARCANE_CLOUD(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
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
					SpellUpgrade.ARCANE_CLOUD_1,
					SpellUpgrade.ARCANE_CLOUD_2,
					SpellUpgrade.ARCANE_CLOUD_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.RESISTANCE_LUST, -25)), Util.newArrayListOfValues("Lasts for [style.colourGood(3 turns)]")) {

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER, 3));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.ARCANE_CLOUD_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING, 3));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.ARCANE_CLOUD, 3));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summon an arcane cloud above the target.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Oh, dark seals containing my ultimate power, now be rend to pieces! Let arcane manifestations be summoned forth, to deliver unto my enemies their final judgement!"),
											"With an upwards thrust of your [pc.arm], you summon forth an arcane cloud above your own head!",
											"With an upwards thrust of your [pc.arm], you summon forth an arcane cloud above [npc.namePos] head!",
											"",
											"With an upwards thrust of [npc.her] [npc.arm], [npc.name] summons forth an arcane cloud above your head!",
											"With an upwards thrust of [npc1.her] [npc1.arm], [npc1.name] summons forth an arcane cloud above [npc2.namePos] head!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_LOCALISED_STORM);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_THUNDER);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD_ARCANE_LIGHTNING);
				target.removeStatusEffect(StatusEffect.ARCANE_CLOUD);
				
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	CLEANSE(true,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT_CLEAR,
			DamageType.PHYSICAL,
			true,
			"Cleanse",
			"cleanse",
			"A cleansing wave of arcane energy bursts forth from the target, removing all of their status effects.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.CLEANSE_1,
					SpellUpgrade.CLEANSE_2,
					SpellUpgrade.CLEANSE_3),
			null, Util.newArrayListOfValues("[style.colourGood(Removes all)] combat status effects from both the targeted ally and enemy")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Removes combat status effects from the targeted ally and enemy.";
		}
		
		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null && Main.game.isInCombat()) {
				AbstractStatusEffect effect = StatusEffect.ARCANE_DUALITY_POSITIVE;
				
				if(Main.combat.getEnemies(caster).contains(target)) {
					effect = StatusEffect.ARCANE_DUALITY_NEGATIVE;
				}
				
				
				if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(effect, 6));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_2)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(effect, 3));
					
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"By Lilith's power, I do shatter the world's illusions! Let time and space be torn asunder, and by my arcane dominance, shall reality itself be warped to my will!"),
											"Thrusting your [pc.hand] forwards, you summon forth an explosion of cleansing arcane energy upon yourself!",
											"Thrusting your [pc.hand] forwards, you summon forth an explosion of cleansing arcane energy upon [npc.name]!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth an explosion of cleansing arcane energy upon [npc.herself]!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth an explosion of cleansing arcane energy upon you!",
											"Thrusting [npc.her] [npc.hand] forwards, [npc.name] summons forth an explosion of cleansing arcane energy upon [npc2.name]!")
								);

			descriptionSB.append(UtilText.parse(this.getPreferredTarget(caster, enemies, allies),
					" The energy then shoots off and explodes around [npc.name]!"));
			
			
			// If attack hits, apply damage and effects: TODO
			if (isHit) {
				List<AbstractStatusEffect> effectsToRemove = new ArrayList<>();
				// Remove status effects from ally:
				for(AbstractStatusEffect se : target.getStatusEffects()) {
					if(se.isCombatEffect() && ((se.getBeneficialStatus()==EffectBenefit.BENEFICIAL && !caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_1)) || se.getBeneficialStatus()!=EffectBenefit.BENEFICIAL)) {
						effectsToRemove.add(se);
					}
				}
				for(AbstractStatusEffect se : effectsToRemove) {
					descriptionSB.append(target.removeStatusEffectCombat(se));
				}
				// Remove status effects from enemy:
				effectsToRemove.clear();
				for(AbstractStatusEffect se : this.getPreferredTarget(caster, enemies, allies).getStatusEffects()) {
					if(se.isCombatEffect() && (se.getBeneficialStatus()==EffectBenefit.BENEFICIAL || (se.getBeneficialStatus()!=EffectBenefit.BENEFICIAL && !caster.hasSpellUpgrade(SpellUpgrade.CLEANSE_1)))) {
						effectsToRemove.add(se);
					}
				}
				for(AbstractStatusEffect se : effectsToRemove) {
					descriptionSB.append(this.getPreferredTarget(caster, enemies, allies).removeStatusEffectCombat(se));
				}
				
				descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	STEAL(true,
			SpellSchool.ARCANE,
			SpellType.MISC,
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
					SpellUpgrade.STEAL_1,
					SpellUpgrade.STEAL_2,
					SpellUpgrade.STEAL_3A,
					SpellUpgrade.STEAL_3B),
			null, Util.newArrayListOfValues("[style.colourExcellent(Steals)] a random item from the target's inventory")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Steals the target's items.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"The fabric of space and time is mine to command! Cosmic dimensions, obey my command, and deliver unto me the assets of my hated foes!"),
											"Thrusting out your [pc.hand] and clenching your fist, you channel your arcane power into stealing one of your own items...", // ...
											"Thrusting out your [pc.hand] and clenching your fist, you channel your arcane power into stealing one of [npc.namePos] items!",
											"",
											"Thrusting out [npc.her] [npc.hand] and clenching [npc.her] fist, [npc.name] channels [npc.her] arcane power into stealing one of your items!",
											"Thrusting out [npc.her] [npc.hand] and clenching [npc.her] fist, [npc.name] channels [npc.her] arcane power into stealing one of [npc2.namePos] items!"));
			
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
						descriptionSB.append("<br/>"
								+getCastDescription(caster, target,
										null,
										"You stole your own "+clothingToSteal.getName()+"...",
										"[npc.Name] lets out an embarrassed cry as you steal the "+clothingToSteal.getName()+" that [npc.sheIs] currently wearing, [npc.speech(Y-You pervert!)]",
										"",
										"You can't help but let out an embarrassed cry as [npc.name] steals the "+clothingToSteal.getName()+" that you're currently wearing, [pc.speech(Y-You pervert!)]",
										"[npc2.Name] lets out an embarrassed cry as [npc1.name] steals the "+clothingToSteal.getName()+" that [npc2.sheIs] currently wearing, [npc2.speech(Y-You pervert!)]")
								+ "<br/>"
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
						
					}
					
					int mainWeaponIndex = 0;
					AbstractWeapon mainWeapon = null;
					int offhandWeaponIndex = 0;
					AbstractWeapon offhandWeapon = null;
					List<Integer> weaponIndexes = new ArrayList<>();
					for(int i=0;i<target.getMainWeaponArray().length; i++) {
						if(target.getMainWeapon(i)!=null) {
							weaponIndexes.add(i);
						}
					}
					if(!weaponIndexes.isEmpty()) {
						mainWeaponIndex = Util.randomItemFrom(weaponIndexes);
						mainWeapon = target.getMainWeapon(mainWeaponIndex);
					}
					weaponIndexes = new ArrayList<>();
					for(int i=0;i<target.getOffhandWeaponArray().length; i++) {
						if(target.getOffhandWeapon(i)!=null) {
							weaponIndexes.add(i);
						}
					}
					if(!weaponIndexes.isEmpty()) {
						offhandWeaponIndex = Util.randomItemFrom(weaponIndexes);
						offhandWeapon = target.getOffhandWeapon(offhandWeaponIndex);
					}
						
					if(caster.hasSpellUpgrade(SpellUpgrade.STEAL_2)) {
						mainWeaponSteal = mainWeapon!=null;
						offhandWeaponSteal = offhandWeapon!=null;
					}
					
					stealItem = target.getInventorySlotsTaken()>0;
					
				
					double rnd = Math.random();
					
					if(mainWeaponSteal && (rnd<0.2 || (!offhandWeaponSteal && !stealItem && clothingToSteal==null))) {
						target.unequipMainWeapon(mainWeaponIndex, true, target.isPlayer());
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"You stole your own "+mainWeapon.getName()+"...",
										"You stole [npc.namePos] "+mainWeapon.getName()+" from out of [npc.her] [npc.hands]!",
										"",
										"[npc.Name] stole your "+mainWeapon.getName()+" from out of your [pc.hands]!",
										"[npc1.Name] stole [npc2.namePos] "+mainWeapon.getName()+" from out of [npc2.her] [npc2.hands]!")
								+ "<br/>"
								+ caster.addWeapon(mainWeapon, true));
						
					} else if(offhandWeaponSteal && (rnd<0.2 || (!stealItem && clothingToSteal==null))) {
						target.unequipOffhandWeapon(offhandWeaponIndex, true, target.isPlayer());
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"You stole your own "+offhandWeapon.getName()+"...",
										"You stole [npc.namePos] "+offhandWeapon.getName()+" from out of [npc.her] [npc.hands]!",
										"",
										"[npc.Name] stole your "+offhandWeapon.getName()+" from out of your [pc.hands]!",
										"[npc1.Name] stole [npc2.namePos] "+offhandWeapon.getName()+" from out of [npc2.her] [npc2.hands]!")
								+ "<br/>"
								+ caster.addWeapon(offhandWeapon, true));
						
					} else if(stealItem && (rnd<0.5 || (clothingToSteal==null))) {
						AbstractItem item = null;
						if(!target.getAllItemsInInventory().isEmpty()) {
							item = Util.randomItemFrom(target.getAllItemsInInventory().keySet());
						}
						AbstractWeapon weapon = null;
						if(!target.getAllWeaponsInInventory().isEmpty()) {
							weapon = Util.randomItemFrom(target.getAllWeaponsInInventory().keySet());
						}
						AbstractClothing clothing = null;
						if(!target.getAllClothingInInventory().isEmpty()) {
							clothing = Util.randomItemFrom(target.getAllClothingInInventory().keySet());
						}
						double itemStealRnd = Math.random();
						if(item!=null && (itemStealRnd<0.33 || (weapon==null && clothing==null))) {
							target.removeItem(item);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"You stole your own "+item.getName()+"...",
											"You stole [npc.namePos] "+item.getName()+" from out of [npc.her] inventory!",
											"",
											"[npc.Name] stole your "+item.getName()+" from out of your inventory!",
											"[npc1.Name] stole [npc2.namePos] "+item.getName()+" from out of [npc2.her] inventory!")
									+ "<br/>"
									+ caster.addItem(item, false));
							
						} else if(weapon!=null && (itemStealRnd<0.66 || (clothing==null))) {
							target.removeWeapon(weapon);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"You stole your own "+weapon.getName()+"...",
											"You stole [npc.namePos] "+weapon.getName()+" from out of [npc.her] inventory!",
											"",
											"[npc.Name] stole your "+weapon.getName()+" from out of your inventory!",
											"[npc1.Name] stole [npc2.namePos] "+weapon.getName()+" from out of [npc2.her] inventory!")
									+ "<br/>"
									+ caster.addWeapon(weapon, false));
							
						} else {
							target.removeClothing(clothing);
							descriptionSB.append("<br/>"
									+ getCastDescription(caster, target,
											null,
											"You stole your own "+clothing.getName()+"...",
											"You stole [npc.namePos] "+clothing.getName()+" from out of [npc.her] inventory!",
											"",
											"[npc.Name] stole your "+clothing.getName()+" from out of your inventory!",
											"[npc1.Name] stole [npc2.namePos] "+clothing.getName()+" from out of [npc2.her] inventory!")
									+ "<br/>"
									+ caster.addClothing(clothing, false));
						}
						
					} else if(clothingToSteal!=null) {
						target.forceUnequipClothingIntoVoid(caster, clothingToSteal);
						descriptionSB.append("<br/>"
								+ getCastDescription(caster, target,
										null,
										"You stole your own "+clothingToSteal.getName()+"...",
										"[npc.Name] lets out an embarrassed cry as you steal the "+clothingToSteal.getName()+" that [npc.sheIs] currently wearing!",
										"",
										"You can't help but let out an embarrassed cry as [npc.name] steals the "+clothingToSteal.getName()+" that you're currently wearing!",
										"[npc2.Name] lets out an embarrassed cry as [npc1.name] steals the "+clothingToSteal.getName()+" that [npc2.sheIs] currently wearing!")
								+ "<br/>"
								+ caster.addClothing(clothingToSteal, true));
						
					} else {
						descriptionSB.append("<br/>[style.italicsDisabled(There's nothing to steal...)]");
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
		
		@Override
		public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
			return Spell.spellStealUpgradeTree;
		}
	},
	
	TELEPORT(true,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
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
					SpellUpgrade.TELEPORT_1,
					SpellUpgrade.TELEPORT_2,
					SpellUpgrade.TELEPORT_3),
			Util.newHashMapOfValues(
					new Value<>(Attribute.ENERGY_SHIELDING, 100)), Util.newArrayListOfValues(
					"Lasts for [style.colourGood(1 turn)]",
					"[style.colourExcellent(Unlocks)] map teleport",
					"Map teleport [style.colourTerrible(blocked)] by companions")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Teleport behind your target.";
		}

		@Override
		public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
			if(caster!=null) {
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_3)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 2));
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_1)) {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT_ARCANE_ARRIVAL, 1));
					
				} else {
					return Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.TELEPORT, 1));
				}
			}
			return new HashMap<>();
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);

			if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_2) && !allies.isEmpty()) {
				descriptionSB.append(getCastDescription(caster, target,
						Util.newArrayListOfValues(
								"Through a thousand dimensions, and across a million worlds, have I wandered! Distance and time are nothing more than the insignificant trappings of the ignorant masses!"),
						"With a quick, cutting motion from one of your [pc.hands], you teleport both yourself and your allies behind your enemies!",
						"With a quick, cutting motion from one of your [pc.hands], you teleport both yourself and your allies behind your enemies!",
						"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports both [npc.herself] and [npc.her] allies behind [npc.her] enemies!",
						"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports both [npc.herself] and [npc.her] allies behind [npc.her] enemies!",
						"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports both [npc.herself] and [npc.her] allies behind [npc.her] enemies!"));
				
			} else {
				descriptionSB.append(getCastDescription(caster, target,
						Util.newArrayListOfValues(
								"Through a thousand dimensions, and across a million worlds, have I wandered! Distance and time are nothing more than the insignificant trappings of the ignorant masses!"),
						"With a quick, cutting motion from one of your [pc.hands], you teleport behind your enemies!",
						"With a quick, cutting motion from one of your [pc.hands], you teleport [npc.name] behind [npc.her] enemies!",
						"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports behind [npc.her] enemies!",
						"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports you behind your enemies!",
						"With a quick, cutting motion from one of [npc.her] [npc.hands], [npc.name] teleports [npc2.name] behind [npc2.her] enemies!"));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {

				target.removeStatusEffect(StatusEffect.TELEPORT_ARCANE_ARRIVAL);
				target.removeStatusEffect(StatusEffect.TELEPORT);
				
				if(caster.hasSpellUpgrade(SpellUpgrade.TELEPORT_2)) {
					applyStatusEffects(caster, caster, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, caster, isHit, isCritical));
					
					for(GameCharacter combatant : Main.combat.getAllies(caster)) {
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
					
				} else {
					applyStatusEffects(caster, target, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	LILITHS_COMMAND(true,
			SpellSchool.ARCANE,
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
					SpellUpgrade.LILITHS_COMMAND_1,
					SpellUpgrade.LILITHS_COMMAND_2,
					SpellUpgrade.LILITHS_COMMAND_3),
			null,
			Util.newArrayListOfValues("[style.colourGood(25%)] chance for target to [style.colourExcellent(instantly submit)]")) {
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Force your target to submit.";
		}
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			float cost = getModifiedCost(caster);
			
			descriptionSB.setLength(0);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Through me, is Lilith made manifest! Across the gulf of time and space, her command is infinite, and does order you to obey!"),
											"",
											"Drawing an immense amount of power from your arcane aura, you project the words of Lilith herself into the mind of [npc.name], ordering [npc.herHim] to submit.",
											"",
											"Drawing an immense amount of power from [npc.her] arcane aura, [npc.name] projects the words of Lilith herself into your mind, ordering you to submit!",
											"Drawing an immense amount of power from [npc1.her] arcane aura, [npc1.name] projects the words of Lilith herself into [npc2.namePos] mind, ordering [npc2.herHim] to submit!"));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				boolean success = false;
				if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_3)) {
					success = true;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
					success = Math.random()<0.75f;
					
				} else if(caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_1)) {
					success = Math.random()<0.5f && target.isVulnerableToArcaneStorm();
					
				} else {
					success = Math.random()<0.25f && target.isVulnerableToArcaneStorm();
				}
				
				if(success) {
					target.setHealthPercentage(0);
					target.setManaPercentage(0);
					target.setLustNoText(100);
					descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
					if(target.isPlayer()) {
						descriptionSB.append(
								"<br/>"
								+ "You find yourself standing face-to-face with the queen of all demons herself."
								+ " Snapping her fingers, Lilith points to the floor, and as she does so, you find yourself dropping to your knees in front of her."
								+ " Eager to please her, you look up into Lilith's eyes as you [pc.moan], [pc.speech(Please, Lilith... Do what you want with me... I'm your loyal slave...)]");
						
					} else {
						descriptionSB.append(UtilText.parse(target,
								"<br/>"
								+ "[npc.Name] sinks to [npc.her] knees as the effect of Lilith's Command completely overwhelms [npc.herHim]."
										+ " Letting out [npc.a_moan+], [npc.she] starts touching [npc.herself] as [npc.she] pleads, [npc.speech(Please, Lilith... Do what you want with me... I'm your loyal slave...)]"));
					}
					
				} else {
					if(target.isPlayer()) {
						descriptionSB.append(
								"<br/>You shake your head and jump back as you resist the effects of Lilith's Command!");
						
					} else if(target.isVulnerableToArcaneStorm() || !caster.hasSpellUpgrade(SpellUpgrade.LILITHS_COMMAND_2)) {
						descriptionSB.append(UtilText.parse(target, "<br/>[npc.Name] shakes [npc.her] head and jumps back as [npc.she] resists the effects of Lilith's Command!"));
					} else {
						descriptionSB.append(UtilText.parse(target, "<br/>[npc.Name] grins as [npc.she] taunts, [npc.speech(That cheap trick isn't going to affect [npc.a_race] like me!)]"));
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	ELEMENTAL_ARCANE(false,
			SpellSchool.ARCANE,
			SpellType.SUMMON,
			DamageType.LUST,
			false,
			"Elemental Arcane",
			"elemental_arcane",
			"Summon forth your elemental in a physical form by binding them to the school of Arcane.",
			0,
			DamageVariance.LOW,
			200,
			null,
			Util.newArrayListOfValues(
					SpellUpgrade.ELEMENTAL_ARCANE_1,
					SpellUpgrade.ELEMENTAL_ARCANE_2,
					SpellUpgrade.ELEMENTAL_ARCANE_3A,
					SpellUpgrade.ELEMENTAL_ARCANE_3B),
			null, Util.newArrayListOfValues("Summons [style.colourArcane(Elemental)] in form of [style.colourArcane(Arcane)]")) {
		@Override
		public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
			if(!owner.hasSpell(this)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do]n't know this spell, so cannot cast it!"));
				
			} else if(owner.isCaptive()) {
				return new Value<>(false, UtilText.parse(owner, "Spells cannot be cast while in captivity!"));
				
			} else if(Main.game.isInCombat()) {
				return new Value<>(false, UtilText.parse(owner, "While in combat, spells can only be cast as a combat move!"));
				
			} else if(!Main.game.isSavedDialogueNeutral()
					&& (Main.game.getCurrentDialogueNode()!=SpellManagement.CHARACTER_SPELLS_ARCANE?false:SpellManagement.getDialogueReturn().getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT)) {
				return new Value<>(false, "Spells can only be cast in a neutral scene!");
				
			} else if(owner.getMana()<this.getModifiedCost(owner)) {
				return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.verb(need)] at least <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)] in order to cast this spell!"));
				
			} else {
				String description = owner.isPlayer()?"Summon your elemental by binding it to the school of Arcane!":"Get [npc.name] to summon [npc.her] elemental by binding it to the school of Arcane!";
				String cost = " This will cost <b>"+this.getModifiedCost(owner)+"</b> [style.boldMana(aura)]!";
				return new Value<>(true, UtilText.parse(owner, description+"<br/>"+cost));
			}
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Summons your elemental in the form of arcane energy.";
		}
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);
			descriptionSB.setLength(0);
			
			boolean elementalAlreadySummoned = false;
			if(!caster.hasDiscoveredElemental()) {
				caster.createElemental();
			} else {
				elementalAlreadySummoned = caster.isElementalSummoned();
			}

			caster.setElementalSummoned(true);
			caster.getElemental().setElementalSchool(SpellSchool.ARCANE);
			
			if(elementalAlreadySummoned) {
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of devil and demon, I summon forth the arcane itself! Answer your [npc.master]'s call, [npc2.name], and, by the conquest of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May the arcane itself be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the arcane be mine, and the power within me be unleashed! The spirit of arcane itself, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a flash of purple arcane lightning, you bind your elemental, [npc2.name], to the school of Arcane!"
									:"With a flash of purple arcane lightning, [npc1.name] binds [npc1.her] elemental, [npc2.name], to the school of Arcane!")));
				
			} else {
				//caster.addCompanion(caster.getElemental());
				descriptionSB.append(UtilText.parse(caster, caster.getElemental(),
								(caster.hasTraitActivated(Perk.CHUUNI)
										?Util.randomItemFrom(
										Util.newArrayListOfValues(
											"[npc.speech(By the ancient rite of devil and demon, I summon forth the arcane itself! Answer your [npc.master]'s call, [npc2.name], and, by the conquest of a million dimensions, be bound to my will!)] ",
											"[npc.speech(May the powers sealed away within me for a thousand years now be unleashed! May the arcane itself be at my command, and, through our eternal contract, I summon you forth, [npc2.name]!)] ",
											"[npc.speech(Let the arcane be mine, and the power within me be unleashed! The spirit of arcane itself, your [npc.master] calls! Obey, and be summoned forth, [npc2.name]!)] "))
										:"")
								+ (caster.isPlayer()
									?"With a flash of purple arcane lightning, you summon forth your elemental, [npc2.name], by binding [npc2.herHim] to the school of Arcane!"
									:"With a flash of purple arcane lightning, [npc1.name] summons forth [npc1.her] elemental, [npc2.name], by binding [npc2.herHim] to the school of Arcane!")));
				
				if(Main.game.isInCombat()) {
					caster.getElemental().setLocation(caster, false);
					if(caster.isPlayer() || Main.combat.getAllies(Main.game.getPlayer()).contains(caster)) {
						Main.combat.addAlly(caster.getElemental());
					} else {
						Main.combat.addEnemy(caster.getElemental());
					}
				}
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	// FROM WEAPONS:
	
	WITCH_SEAL(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE_STATUS_EFFECT,
			DamageType.MISC,
			false,
			"Witch's Seal",
			"spell_witch_seal",
			"Places an arcane seal upon the target, completely preventing them from moving.",
			0,
			DamageVariance.NONE,
			80,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.WITCH_SEAL, 1)),
			null,
			null,
			Util.newArrayListOfValues(
					"[style.colourExcellent(Seals)] the target for [style.colourTerrible(-3)] [style.colourActionPoints(action points)]!")) {
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Seals for [style.colourTerrible(-3)] action points!";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);
			
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Let the seal which once contained my arcane power now obey my command! Render unto my foe the debilitating darkness of absolute submission, and bind them to their fate!"),
										"",
										"Concentrating on the arcane power within your broomstick, you summon forth a powerful seal, which traps [npc.name] in place!",
										"",
										"Concentrating on the arcane power within [npc.her] broomstick, [npc.name] summons forth a powerful seal, which traps you in place!",
										"Concentrating on the arcane power within [npc1.her] broomstick, [npc1.name] [npc.verb(summon)] forth a powerful seal, which traps [npc2.name] in place!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	
	WITCH_CHARM(false,
			SpellSchool.ARCANE,
			SpellType.DEFENSIVE_STATUS_EFFECT,
			DamageType.MISC,
			true,
			"Witch's Charm",
			"spell_witch_charm",
			"Places an arcane enchantment upon the target, causing them to appear irresistibly beautiful to anyone who looks upon them.",
			0,
			DamageVariance.NONE,
			40,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.WITCH_CHARM, 5)),
			null,
			Util.newHashMapOfValues(
					new Value<>(Attribute.DAMAGE_LUST, 25)), Util.newArrayListOfValues("Lasts for [style.colourGood(5 turns)]")) {
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Increases seduction damage.";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);
			
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Let reality itself be warped by my ultimate power! All who gaze upon my visage be bewitched, and see their heart's true desire!"),
										"Concentrating on the arcane power within your broomstick, you cast a bewitching charm upon yourself!",
										"Concentrating on the arcane power within your broomstick, you cast a bewitching charm upon [npc.name]!",
										"Concentrating on the arcane power within [npc.her] broomstick, [npc.name] casts a bewitching charm upon [npc.herself]!",
										"Concentrating on the arcane power within [npc.her] broomstick, [npc.name] casts a bewitching charm upon you!",
										"Concentrating on the arcane power within [npc1.her] broomstick, [npc.name] casts a bewitching charm upon [npc2.name]!"));

			descriptionSB.append(getDamageDescription(caster, target, 0, isHit, isCritical));
			
			if(isHit) {
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));

			return descriptionSB.toString();
		}
	},
	

	
	DARK_SIREN_SIRENS_CALL(false,
			SpellSchool.AIR,
			SpellType.OFFENSIVE_STATUS_EFFECT_MINOR_DAMAGE,
			DamageType.PHYSICAL,
			false,
			"Siren's Call",
			"dark_siren_sirens_call",
			"Unleashes a reverberating scream, the power of which causes the ground to split open. From this fissure, poisonous vapours rise to choke and stifle all nearby enemies.",
			10,
			DamageVariance.NONE,
			200,
			Util.newHashMapOfValues(new Value<AbstractStatusEffect, Integer>(StatusEffect.BANEFUL_FISSURE, 10)),
			null,
			null,
			Util.newArrayListOfValues(
					"<b>25</b> [style.colourPoison(Poison Damage)] per turn for [style.colourGood(10 turns)]",
					"Affects [style.colourExcellent(all enemies)]")) {

		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals "+AbstractCombatMove.getFormattedDamage(getDamageType(), Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, false), target, false, isTargetAtMaximumLust(target))+" damage.";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {

			descriptionSB.setLength(0);

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Powers beneath the earth, obey your [npc.master]'s command! Rend unto the end of time a chasm of darkness, from which the suffocating miasma of toxic dimensions may pour forth!"),
										"",
										"Concentrating on the immense arcane power within your scythe, you smite down into the ground beneath [npc.namePos] [npc.feet], splitting the earth and summoning forth poison fumes!",
										"",
										"Concentrating on the immense arcane power within [npc.her] scythe, [npc.name] smites down into the ground beneath your [pc.feet], splitting the earth and summoning forth poison fumes!",
										"Concentrating on the immense arcane power within [npc.her] scythe, [npc.name] smites down into the ground beneath [npc2.namePos] [npc2.feet], splitting the earth and summoning forth poison fumes!"));

			descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}
			}
			
			for(GameCharacter combatant : Main.combat.getEnemies(caster)) {
				applyStatusEffects(caster, combatant, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
//			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
	},
	

	LIGHTNING_SPHERE_DISCHARGE(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Lightning Discharge",
			"arcane_lightning_sphere_discharge",
			"By drawing a small amount of aura from its wielder, the arcane lightning globe can discharge a burst of arousing arcane lightning, hitting everyone, [style.colourBad(including the caster)], in its immediate vicinity.",
			10,
			DamageVariance.MEDIUM,
			50,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"Affects [style.colourExcellent(all enemies)]",
					"Affects [style.colourTerrible(the caster)]",
					"Affects [style.colourTerrible(all allies)]")) {

		@Override
		public int getAPCost() {
			return 1;
		}

		@Override
		public int getCooldown() {
			return 2;
		}
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals [style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+ " " +damageType.getName()
					+ ")]"
					+ " damage to [style.colourExcellent(all enemies)] <i>and</i> [style.colourTerrible(all allies, including the caster)].";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Sealed away for countless millennia, this infinite cosmic power shall now be unleashed! Witness the arcane maelstrom I call forth, then submit to your fate as my lust-crazed puppet!"),
										"Channelling some of your aura into the arcane lightning globe, you force it to unleash a portion of its power in the form of a lightning discharge!",
										"Channelling some of your aura into the arcane lightning globe, you force it to unleash a portion of its power in the form of a lightning discharge!",
										"Channelling some of [npc.her] aura into the arcane lightning globe, [npc.name] [npc.verb(force)] it to unleash a portion of its power in the form of a lightning discharge!",
										"Channelling some of [npc.her] aura into the arcane lightning globe, [npc.name] [npc.verb(force)] it to unleash a portion of its power in the form of a lightning discharge!",
										"Channelling some of [npc.her] aura into the arcane lightning globe, [npc.name] [npc.verb(force)] it to unleash a portion of its power in the form of a lightning discharge!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
					descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
					if(damage>0) {
						descriptionSB.append(applyDamage(caster, combatant, damage));
					}
					applyStatusEffects(caster, combatant, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Cannot crit.");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	LIGHTNING_SPHERE_OVERCHARGE(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Lightning Overcharge",
			"arcane_lightning_sphere_overcharge",
			"By drawing a considerable amount of aura from its wielder, the arcane lightning globe can discharge a huge burst of arousing arcane lightning, hitting everyone, [style.colourBad(including the caster)], in its immediate vicinity.",
			30,
			DamageVariance.HIGH,
			250,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"Affects [style.colourExcellent(all enemies)]",
					"Affects [style.colourTerrible(the caster)]",
					"Affects [style.colourTerrible(all allies)]")) {

		@Override
		public int getAPCost() {
			return 3;
		}

		@Override
		public int getCooldown() {
			return 10;
		}
		
		@Override
		public boolean isSpellBook() {
			return false;
		}
		
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals [style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+ " " +damageType.getName()
					+ ")]"
					+ " damage to [style.colourExcellent(all enemies)] <i>and</i> [style.colourTerrible(all allies, including the caster)].";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Sealed away for countless millennia, this infinite cosmic power shall now be unleashed! Witness the arcane maelstrom I call forth, then submit to your fate as my lust-crazed puppet!"),
										"Channelling your aura into the arcane lightning globe, you force it to unleash its power in the form of an almighty lightning discharge!",
										"Channelling your aura into the arcane lightning globe, you force it to unleash its power in the form of an almighty lightning discharge!",
										"Channelling [npc.her] aura into the arcane lightning globe, [npc.name] [npc.verb(force)] it to unleash its power in the form of an almighty lightning discharge!",
										"Channelling [npc.her] aura into the arcane lightning globe, [npc.name] [npc.verb(force)] it to unleash its power in the form of an almighty lightning discharge!",
										"Channelling [npc.her] aura into the arcane lightning globe, [npc.name] [npc.verb(force)] it to unleash its power in the form of an almighty lightning discharge!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {

				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
					descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
					if(damage>0) {
						descriptionSB.append(applyDamage(caster, combatant, damage));
					}
					applyStatusEffects(caster, combatant, isCritical);
					descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Cannot crit.");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	ARCANE_CHAIN_LIGHTNING(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Chain Lightning",
			"arcane_lightning_chain",
			"The caster is able to summon forth a crackling manifestation of arcane lightning, which leaps from target to target, causing each person struck to become uncontrollably aroused.",
			15,
			DamageVariance.MEDIUM,
			40,
			null,
			null,
			null,
			Util.newArrayListOfValues(
					"Affects [style.colourExcellent(all enemies)]")) {
		@Override
		public int getAPCost() {
			return 1;
		}
		@Override
		public int getCooldown() {
			return 2;
		}
		@Override
		public boolean isSpellBook() {
			return false;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals [style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+ " " +damageType.getName()
					+ ")]"
					+ " damage to [style.colourExcellent(all enemies)].";
		}
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Sealed away for countless millennia, this infinite cosmic power shall now be unleashed! Witness the arcane maelstrom I call forth, then submit to your fate as my lust-crazed puppet!"),
													"After taking a moment to focus your aura, you summon forth a crackling manifestation of arcane lightning!",
													"After taking a moment to focus your aura, you summon forth a crackling manifestation of arcane lightning!",
													"After taking a moment to focus [npc.her] aura, [npc.name] [npc.verb(summon)] forth a crackling manifestation of arcane lightning!",
													"After taking a moment to focus [npc.her] aura, [npc.name] [npc.verb(summon)] forth a crackling manifestation of arcane lightning!",
													"After taking a moment to focus [npc.her] aura, [npc.name] [npc.verb(summon)] forth a crackling manifestation of arcane lightning!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				for(GameCharacter combatant : Main.combat.getAllCombatants(true)) {
					if(Main.combat.isOpponent(caster, combatant)) {
						float damage = Attack.calculateSpellDamage(caster, combatant, damageType, this.getDamage(caster), damageVariance, isCritical);
						descriptionSB.append(getDamageDescription(caster, combatant, damage, isHit, isCritical));
						if(damage>0) {
							descriptionSB.append(applyDamage(caster, combatant, damage));
						}
						applyStatusEffects(caster, combatant, isCritical);
						descriptionSB.append(getStatusEffectApplication(caster, combatant, isHit, isCritical));
					}
				}
				
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Cannot crit.");
	    }
		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
		}
	},
	
	ARCANE_LIGHTNING_SUPERBOLT(false,
			SpellSchool.ARCANE,
			SpellType.OFFENSIVE,
			DamageType.LUST,
			false,
			"Lightning Superbolt",
			"arcane_lightning_superbolt",
			"The caster summons forth an almighty arcane lightning superbolt and fires it at their target, causing the person who's struck to experience an incredible surge in arousal.",
			50,
			DamageVariance.HIGH,
			200,
			null,
			null,
			null,
			Util.newArrayListOfValues()) {
		@Override
		public int getAPCost() {
			return 3;
		}
		@Override
		public int getCooldown() {
			return 10;
		}
		@Override
		public boolean isSpellBook() {
			return false;
		}
		@Override
		public String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return "Deals [style.colourDmgLust("
					+Attack.getMinimumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+"-"
					+Attack.getMaximumSpellDamage(caster, target, getDamageType(), this.getDamage(caster), this.getDamageVariance())
					+ " " +damageType.getName()
					+ ")]"
					+ " damage.";
		}
		
		public String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical) {
			descriptionSB.setLength(0);

			float cost = getModifiedCost(caster);
			
			descriptionSB.append(getCastDescription(caster, target,
											Util.newArrayListOfValues(
													"Sealed away for countless millennia, this infinite cosmic power shall now be unleashed! Witness the arcane apocalypse I call forth, then submit to your fate as my lust-crazed puppet!"),
													"After taking a moment to focus your aura, you summon forth an almighty arcane lightning superbolt, before launching it directly at yourself!",
													"After taking a moment to focus your aura, you summon forth an almighty arcane lightning superbolt, before launching it directly at [npc.name]!",
													"After taking a moment to focus [npc.her] aura, [npc.name] [npc.verb(summon)] forth an almighty arcane lightning superbolt, before launching it directly at [npc.herself]!",
													"After taking a moment to focus [npc.her] aura, [npc.name] [npc.verb(summon)] forth an almighty arcane lightning superbolt, before launching it directly at you!",
													"After taking a moment to focus [npc.her] aura, [npc.name] [npc.verb(summon)] forth an almighty arcane lightning superbolt, before launching it directly at [npc2.name]!"));
			
			// If attack hits, apply damage. Status effect always applies.:
			if (isHit) {
				float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(caster), damageVariance, isCritical);
				descriptionSB.append(getDamageDescription(caster, target, damage, isHit, isCritical));
				if(damage>0) {
					descriptionSB.append(applyDamage(caster, target, damage));
				}
				applyStatusEffects(caster, target, isCritical);
				descriptionSB.append(getStatusEffectApplication(caster, target, isHit, isCritical));
			}
			
			descriptionSB.append(getCostDescription(caster, cost));
			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}
		
		@Override
	    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
	    	return Util.newArrayListOfValues("Cannot crit.");
	    }

		@Override
		public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
			return false;
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
	protected static Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> soothingWatersUpgradeTree;
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
		
		soothingWatersUpgradeTree = new HashMap<>();

		soothingWatersUpgradeTree.put(0, new ArrayList<>());
		soothingWatersUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.WATER, 0, SpellUpgrade.SOOTHING_WATERS_1));
		soothingWatersUpgradeTree.get(0).add(new TreeEntry<>(SpellSchool.WATER, 0, SpellUpgrade.SOOTHING_WATERS_1_CLEAN));

		soothingWatersUpgradeTree.put(1, new ArrayList<>());
		soothingWatersUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.WATER, 1, SpellUpgrade.SOOTHING_WATERS_2));
		soothingWatersUpgradeTree.get(1).get(0).addLink(soothingWatersUpgradeTree.get(0).get(0));
		soothingWatersUpgradeTree.get(1).add(new TreeEntry<>(SpellSchool.WATER, 1, SpellUpgrade.SOOTHING_WATERS_2_CLEAN));

		soothingWatersUpgradeTree.put(2, new ArrayList<>());
		soothingWatersUpgradeTree.get(2).add(new TreeEntry<>(SpellSchool.WATER, 2, SpellUpgrade.SOOTHING_WATERS_3));
		soothingWatersUpgradeTree.get(2).get(0).addLink(soothingWatersUpgradeTree.get(1).get(0));
	}
	
	
	private boolean forbiddenSpell;
	private SpellSchool spellSchool;
	private SpellType type;
	protected DamageType damageType;
	private boolean beneficial;
	
	private String name;
	private String description;
	
	protected int damage;
	protected int spellCost;
	protected DamageVariance damageVariance;
	private Map<AbstractStatusEffect, Integer> statusEffects;
	
	private List<SpellUpgrade> upgradeList;
	private Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> spellUpgradeTree;
	
	private HashMap<AbstractAttribute, Integer> attributeModifiers;
	private List<String> extraEffects;
	private List<String> modifiersList;

	private String pathName;
	private String SVGString;

	private Spell(boolean forbiddenSpell,
			SpellSchool spellSchool,
			SpellType type,
			DamageType damageType,
			boolean beneficial,
			String name,
			String pathName,
			String description,
			int damage,
			DamageVariance damageVariance,
			int spellCost,
			Map<AbstractStatusEffect, Integer> statusEffects,
			List<SpellUpgrade> upgradeList,
			HashMap<AbstractAttribute, Integer> attributeModifiers,
			List<String> extraEffects) {
		
		this.forbiddenSpell = forbiddenSpell;
		
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
		this.upgradeList = upgradeList;
		initialiseBasicSpellUpgradeTree(upgradeList);

		this.attributeModifiers = attributeModifiers;
		this.extraEffects = extraEffects;
		
		modifiersList = new ArrayList<>();
		
		if (attributeModifiers != null) {
			for (Entry<AbstractAttribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>"
						+ " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
		}
		
		if (extraEffects != null) {
			modifiersList.addAll(extraEffects);
		}
		
		
		// SVG:
		this.pathName = pathName;
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/spell/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Spell icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGString = Util.inputStreamToString(is);
			
			SVGString = SvgUtil.colourReplacement(this.toString(), damageType.getMultiplierAttribute().getColour(), SVGString);
			
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    protected boolean isTargetAtMaximumLust(GameCharacter target) {
    	return target!=null && target.hasStatusEffect(StatusEffect.DESPERATE_FOR_SEX);
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

	public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
		return applyEffect(caster, target, null, null, isHit, isCritical);
	}

	public abstract String applyEffect(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies, boolean isHit, boolean isCritical);

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}
	
	public boolean isSpellBook() {
		return true;
	}
	
	public boolean isForbiddenSpell() {
		return forbiddenSpell;
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
	
	public boolean isBeneficial() {
		return beneficial;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription(GameCharacter source) {
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
		
		calculatedCost *= ((100 - caster.getAttributeValue(Attribute.SPELL_COST_MODIFIER)) / 100f);
		
		// Round float value to nearest 1 decimal place:
		return (Math.round(calculatedCost*10))/10f;
	}
	
	public Map<AbstractStatusEffect, Integer> getStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		return statusEffects;
	}

	/**
	 * @return A list of all available SpellUpgrades for this Spell. <b>You should most likely be checking getSpellUpgradeTree() instead!</b>
	 */
	public List<SpellUpgrade> getUpgradeList() {
		return upgradeList;
	}
	
	public Map<Integer, List<TreeEntry<SpellSchool, SpellUpgrade>>> getSpellUpgradeTree() {
		return spellUpgradeTree;
	}

	public HashMap<AbstractAttribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}
	
	public String getSVGString() {
		return SVGString;
	}

	public String getPathName() {
		return pathName;
	}
	
	protected void applyStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		for (Entry<AbstractStatusEffect, Integer> se : getStatusEffects(caster, target, isCritical).entrySet()) {
			Main.combat.addStatusEffectToApply(target, se.getKey(), se.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1));
		}
	}

	protected String getDamageDescription(GameCharacter caster, GameCharacter target, float damage, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();
		
		boolean appliesEffects = !this.getStatusEffects(caster, target, isCritical).isEmpty();

		damageCostDescriptionSB.append("<br/>");
			if (caster.isPlayer()) {
				if (isCritical) {
					if(!isHit) {
						damageCostDescriptionSB.append("[style.italicsBad(You missed!)]");
					} else {
						if(damage>0) {
							damageCostDescriptionSB.append(UtilText.parse(target,
									"You [style.boldExcellent(critically)] hit [npc.name] for <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
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
									"You hit [npc.name] for <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
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
									"[npc1.Name] [style.boldExcellent(critically)] hits " + (target.isPlayer()?"you":"[npc2.name]")+" for <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
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
									"[npc1.Name] hits " + (target.isPlayer()?"you":"[npc2.name]")+" for <b>" + damage + "</b> " + damageType.getMultiplierAttribute().getColouredName("b") + "!"));
						}
					}
				}
			}
			if(damageCostDescriptionSB.toString().toString().equals("<br/>")) {
				return "";
			}
		
		return damageCostDescriptionSB.toString();
	}
	
	protected String applyDamage(GameCharacter caster, GameCharacter target, float damage) {
		return this.getDamageType().damageTarget(caster, target, (int)damage).getKey();
//		return target.incrementHealth(caster, -damage);
	}
	
	protected String getStatusEffectApplication(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		if (this.getStatusEffects(caster, target, isCritical) != null && !this.getStatusEffects(caster, target, isCritical).isEmpty() && isHit) {
			damageCostDescriptionSB.append(
					"<br/>"+UtilText.parse(target,
								(!target.isPlayer()
									? "[npc.Name] is now "
									: "You are now ")
								+(this.isBeneficial()
										?"benefiting from "
										:"suffering from ")));
			
			int i = 0;
			for (Entry<AbstractStatusEffect, Integer> seEntry : this.getStatusEffects(caster, target, isCritical).entrySet()) {
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
			damageCostDescriptionSB.append(".");
		}
		
		return damageCostDescriptionSB.toString();
	}
	
	public static String getBasicStatusEffectApplication(GameCharacter target, boolean beneficial, Map<AbstractStatusEffect, Integer> statusEffects) {
		StringBuilder damageCostDescriptionSB = new StringBuilder();

		damageCostDescriptionSB.append(
				"<br/>"+UtilText.parse(target,
							(!target.isPlayer()
								? "[npc.She] is now "
								: "You are now ")
							+(beneficial
									?"benefiting from "
									:"suffering from ")));
		
		int i = 0;
		for (Entry<AbstractStatusEffect, Integer> seEntry : statusEffects.entrySet()) {
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
		damageCostDescriptionSB.append(".");
		
		return damageCostDescriptionSB.toString();
	}

	protected String getCostDescription(GameCharacter caster, float cost) {
		if(cost<0) {
			return "<br/>Casting this spell cost "+(caster.isPlayer()?"you":UtilText.parse(caster, "[npc.name]"))+" <b>"
					+ -cost + "</b> <b style='color:" + Attribute.HEALTH_MAXIMUM.getColour().toWebHexString() + ";'>"+Attribute.HEALTH_MAXIMUM.getName()+"</b>!</b>";
		} else {
			return "<br/>Casting this spell cost "+(caster.isPlayer()?"you":UtilText.parse(caster, "[npc.name]"))+" <b>"
					+ cost + "</b> <b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>aura</b>!</b>";
		}
	}

	/**
	 * Utility method for returning appropriate cast description based on the identity of caster and target. Variable names should be self-explanatory.
	 */
	private static String getCastDescription(GameCharacter caster, GameCharacter target,
			List<String> chuuniDialogue,
			String playerSelfCast,
			String playerCastOnNPC,
			String NPCSelfCast,
			String NPCCastOnPlayer,
			String NPCCastOnNPC) {
		StringBuilder sb = new StringBuilder();
		
		if(caster.hasTraitActivated(Perk.CHUUNI) && chuuniDialogue!=null) {
			sb.append(UtilText.parse(caster, target, "[npc.speech("+Util.randomItemFrom(chuuniDialogue)+")]</br>"));
		}
		if(caster.isPlayer()) {
			if(target.isPlayer()) {
				sb.append(playerSelfCast);
			} else {
				sb.append(UtilText.parse(target, playerCastOnNPC));
			}
		} else {
			if(target.isPlayer()) {
				sb.append(UtilText.parse(caster, NPCCastOnPlayer));
			} else {
				if(target.equals(caster)) {
					sb.append(UtilText.parse(caster, NPCSelfCast));
				} else {
					sb.append(UtilText.parse(caster, target, NPCCastOnNPC));
				}
			}
		}
		return sb.toString();
	}
	
	// Rendering:
	
	private static final int ROWS = 3;
	
	private static StringBuilder treeSB = new StringBuilder();
	private static StringBuilder spellSB = new StringBuilder();
	private static StringBuilder lineSB = new StringBuilder();
	private static StringBuilder entrySB = new StringBuilder();
	

	public static String getSpellMiscTreeDisplay(GameCharacter character, GameCharacter target) {
		treeSB.setLength(0);

		treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
				+ "<div class='container-full-width' style='text-align:center;'><h6 style='color:"+PresetColour.DAMAGE_TYPE_SPELL.toWebHexString()+";'>Miscellaneous</h6></div>");
		
		for(Spell spell : Spell.values()) {
			if(!spell.isSpellBook()) { // Only append special spells obtained from weapons & other sources
				treeSB.append("<div class='container-full-width' style='border:1px solid "+(character.hasSpell(spell, true)?PresetColour.DAMAGE_TYPE_SPELL:PresetColour.BASE_GREY_DARK).toWebHexString()+"; width:25%; padding:0; margin:0;'>");
					treeSB.append(appendSpell(character, target, -1, spell, true));
				treeSB.append("</div>");
			}
		}
		
		treeSB.append("</div>");
		
		return treeSB.toString();
	}
	
	public static String getSpellTreesDisplay(SpellSchool school, GameCharacter character, GameCharacter target) {
		treeSB.setLength(0);
		appendSpellSchool(school, character, target);
		return treeSB.toString();
	}
	
	private static void appendSpellSchool(SpellSchool spellSchool, GameCharacter character, GameCharacter target) {
		treeSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
				+ "<div class='container-full-width' style='text-align:center;'><h6 style='color:"+spellSchool.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(spellSchool.getName())+"</h6>"
						+"<b style='color:"+spellSchool.getColour().toWebHexString()+";'>"+character.getSpellUpgradePoints(spellSchool)+"</b> <b>Upgrade Points Available</b></div>");
		
		for(Spell spell : Spell.getSpellsFromSchoolMap().get(spellSchool)) {
			if(spell.isSpellBook()) { // Do not append spells obtained from weapons & other sources
				boolean fullyUpgraded = character.isSpellFullyUpgraded(spell);
				
				if(!spell.getSpellUpgradeTree().isEmpty()) {
					treeSB.append("<div class='container-full-width' style='border:1px solid "+(fullyUpgraded?spell.getSpellSchool().getColour():PresetColour.BASE_GREY_DARK).toWebHexString()+"; width:25%; padding:0; margin:0;'>");
						for(int row=-1; row<ROWS; row++) {
							treeSB.append(appendSpell(character, target, row, spell, false));
						}
					treeSB.append("</div>");
				}
			}
		}
		
		treeSB.append("</div>");
	}
	
	private static String appendSpell(GameCharacter character, GameCharacter target, int row, Spell spell, boolean miscSpell) {
		spellSB.setLength(0);

		spellSB.append("<div class='container-full-width' style='width:100%; padding:0; margin:0;'>");
			if(row==-1) {
				boolean hasSpell = character.hasSpell(spell, miscSpell);
				boolean forbidden = spell.isForbiddenSpell();
				
				spellSB.append("<div class='square-button "+(!hasSpell?" disabled":"")+"' style='width:50%; margin:8px 25% 4px 25%; cursor: default; "
										+(hasSpell
												?"border-color:"+spell.getSpellSchool().getColour().toWebHexString()+";"
												:"")+"' id='SPELL_TREE_"+spell+"'>"
									+ "<div class='square-button-content' style='cursor: default;'>"+spell.getSVGString()+"</div>"
									+ (!hasSpell
										?(forbidden
											?"<div class='overlay disabled-dark' style='cursor:default;'></div>"
											:"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8;'></div>")
										:"")
								+ "</div>");
				
				Value<Boolean, String> useDesc = spell.getSpellCastOutOfCombatDescription(character, target);
				spellSB.append("<div class='normal-button "+(useDesc.getKey()?"":"disabled")+"' id='SPELL_TREE_CAST_"+spell+"' style='width:50%; margin:8px 25% "+(miscSpell?"8px":"0")+" 25%; text-align:center;'>");
				spellSB.append("Cast");
				spellSB.append("</div>");
				
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

		boolean forbidden = spell.isForbiddenSpell();
		boolean hasUpgrade = character.hasSpellUpgrade(perkEntry.getEntry());
		boolean isUpgradeAvailable = isSpellUpgradeAvailable(character, spell, perkEntry);
		
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
		
		entrySB.append("<div class='square-button round"+(!hasUpgrade && !isUpgradeAvailable?" disabled":"")+"' style='width:40%; margin:8px "+getMargin(size)+"%; "
										+ (character.hasSpellUpgrade(perkEntry.getEntry())
											?"cursor: default; border-color:"+perkEntry.getCategory().getColour().toWebHexString()+";"
											:(!perkEntry.getEntry().isAvailable(character) //|| character.getSpellUpgradePoints(perkEntry.getCategory()) < perkEntry.getEntry().getPointCost()
												?"cursor: default; border-color:"+PresetColour.GENERIC_BAD.toWebHexString()+";"
												:""))
										+"' id='SPELL_UPGRADE_"+perkEntry.getEntry()+"'>"
							+ "<div class='square-button-content'>"+perkEntry.getEntry().getSVGString()+"</div>"
							+ (!hasUpgrade && !isUpgradeAvailable
								?(forbidden
										?"<div class='overlay disabled-dark' style='border-radius:50%;'></div>"
										:"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.8; border-radius:50%; cursor: default;'></div>")
								:(!hasUpgrade
									?"<div style='position:absolute; left:0; top:0; margin:0; padding:0; width:100%; height:100%; background-color:#000; opacity:0.6; border-radius:50%; cursor:pointer;'></div>"
									:""))
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
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
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
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
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
					?PresetColour.BASE_GREY
					:PresetColour.TEXT_GREY_DARK;
	}

	// Combat maneuver compatibility
	// These functions are almost identical to the ones in CombatMove class,  with modifications to fit spells as necessary. Refer to CombatMove class for information.

	public int getAPCost() {
		return 1; // Normally just 1 AP.
	}

	public int getCooldown() {
		return 0; // Normally no CD.
	}

	public boolean isCanTargetEnemies() {
		return !isBeneficial();
	}

	public boolean isCanTargetAllies() {
		return isBeneficial();
	}

	public boolean isCanTargetSelf() {
		return isBeneficial();
	}

	public Value<Boolean, String> getSpellCastOutOfCombatDescription(GameCharacter owner, GameCharacter target) {
		if(!owner.hasSpell(this)) {
			return new Value<>(false, UtilText.parse(owner, "[npc.Name] [npc.do]n't know this spell, so cannot cast it!"));
			
		} else if(Main.game.isInCombat()) {
			return new Value<>(false, UtilText.parse(owner, "While in combat, spells can only be cast as a combat move!"));
		}
		
		return new Value<>(false, "This spell can only be cast during combat!");
	}

	public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(isCanTargetAllies() && allies.isEmpty()) {
			return 0.0f;
		}
		
		if(this.getType().isStatusEffectFocus()) { // If this spell is just for the application of a status effect, do not use it if all targets already have that status effect:
			boolean noEffect = true;
			if(isCanTargetEnemies()) { // Enemy status effect application:
				Set<GameCharacter> survivingEnemies = new HashSet<>(enemies);
				survivingEnemies.removeIf(enemy -> Main.combat.isCombatantDefeated(enemy));
//				System.out.println(survivingEnemies.size());
				enemyLoop:
				for(GameCharacter enemy : survivingEnemies) {
					List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, enemy, false).keySet());
					if(!statusEffects.isEmpty()) {
						for(AbstractStatusEffect se : statusEffects) {
							if(!enemy.hasStatusEffect(se)) {
								boolean alreadyTargetedWithThisSpell = false;
								for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
									if(move.getKey()==enemy && move.getValue().getAssociatedSpell()==this) {
										alreadyTargetedWithThisSpell = true;
										break;
									}
								}
								if(!alreadyTargetedWithThisSpell) {
									noEffect = false;
//									System.out.println(source.getName()+" | "+this.getName());
									break enemyLoop;
								}
							}
						}
					}
				}
				
			} else {
				Set<GameCharacter> survivingAllies = new HashSet<>(allies);
				survivingAllies.add(source);
				survivingAllies.removeIf(ally -> Main.combat.isCombatantDefeated(ally));
				allyLoop:
				for(GameCharacter ally : survivingAllies) {
					List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, ally, false).keySet());
					if(!statusEffects.isEmpty()) {
						for(AbstractStatusEffect se : statusEffects) {
							if(!ally.hasStatusEffect(se)) {
								boolean alreadyTargetedWithThisSpell = false;
								for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
									if(move.getKey()==ally && move.getValue().getAssociatedSpell()==this) {
										alreadyTargetedWithThisSpell = true;
										break;
									}
								}
								if(!alreadyTargetedWithThisSpell) {
									noEffect = false;
									break allyLoop;
								}
							}
						}
					}
				}
			}
			if(noEffect) {
				return 0;
			}
		}
		
		int behaviourMultiplier = 1;
		if(source.getCombatBehaviour()==CombatBehaviour.ATTACK && !this.isBeneficial()) {
			behaviourMultiplier = 2;
		}
		if(source.getCombatBehaviour()==CombatBehaviour.SUPPORT && this.isBeneficial()) {
			behaviourMultiplier = 10;
		}
		if(source.getCombatBehaviour()==CombatBehaviour.SPELLS) {
			behaviourMultiplier = 10;
		}
		return (0.2f*behaviourMultiplier) - 0.2f * source.getSelectedMovesByType(CombatMoveType.SPELL);
	}

	public GameCharacter getPreferredTarget(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(Main.game.isInCombat() && source.isPlayer()) {
			return Main.combat.getTargetedCombatant();
		}
		if(isCanTargetEnemies()) {
			if(AbstractCombatMove.shouldBlunder()) {
				return enemies.get(Util.random.nextInt(enemies.size()));
				
			} else {
				float lowestHP = -1;
				GameCharacter potentialCharacter = null;
				
				if(this.getType().isStatusEffectFocus()) { // If this spell is primarily concerned with applying a status effect, only use it on targets who do not already have that status effect:
					Set<GameCharacter> survivingEnemies = new HashSet<>(enemies);
					survivingEnemies.removeIf(enemy -> Main.combat.isCombatantDefeated(enemy));
					enemyLoop:
					for(GameCharacter enemy : survivingEnemies) {
						List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, enemy, false).keySet());
						if(!statusEffects.isEmpty()) {
							for(AbstractStatusEffect se : statusEffects) {
								if(!enemy.hasStatusEffect(se)) {
									boolean alreadyTargetedWithThisSpell = false;
									for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
										if(move.getKey()==enemy && move.getValue().getAssociatedSpell()==this) {
											alreadyTargetedWithThisSpell = true;
											break;
										}
									}
									if(!alreadyTargetedWithThisSpell) {
										potentialCharacter = enemy;
										break enemyLoop;
									}
								}
							}
						}
					}
				}
				
				if(potentialCharacter==null) {
					for(GameCharacter character : enemies) {
						if(lowestHP == -1 || character.getHealth() < lowestHP) {
							potentialCharacter = character;
							lowestHP = character.getHealth();
						}
					}
				}
				return potentialCharacter;
			}
		}
		if(isCanTargetAllies() && !allies.isEmpty()) {
			if(AbstractCombatMove.shouldBlunder()) {
				return allies.get(Util.random.nextInt(allies.size()));
				
			} else {
				float lowestHP = -1;
				GameCharacter potentialCharacter = null;
				
				if(this.getType().isStatusEffectFocus()) { // If this spell is primarily concerned with applying a status effect, only use it on targets who do not already have that status effect:
					Set<GameCharacter> survivingAllies = new HashSet<>(allies);
					survivingAllies.add(source);
					survivingAllies.removeIf(ally -> Main.combat.isCombatantDefeated(ally));
					allyLoop:
					for(GameCharacter ally : survivingAllies) {
						List<AbstractStatusEffect> statusEffects = new ArrayList<>(this.getStatusEffects(source, ally, false).keySet());
						if(!statusEffects.isEmpty()) {
							for(AbstractStatusEffect se : statusEffects) {
								if(!ally.hasStatusEffect(se)) {
									boolean alreadyTargetedWithThisSpell = false;
									for(Value<GameCharacter, AbstractCombatMove> move : source.getSelectedMoves()) {
										if(move.getKey()==ally && move.getValue().getAssociatedSpell()==this) {
											alreadyTargetedWithThisSpell = true;
											break;
										}
									}
									if(!alreadyTargetedWithThisSpell) {
										potentialCharacter = ally;
										break allyLoop;
									}
								}
							}
						}
					}
				}
				
				if(potentialCharacter==null) {
					for(GameCharacter character : allies) {
						if(lowestHP == -1 || character.getHealth() < lowestHP) {
							potentialCharacter = character;
							lowestHP = character.getHealth();
						}
					}
				}
				return potentialCharacter;
			}
		}
		return source;
	}
	
	public abstract String getBasicEffectsString(GameCharacter caster, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies);
	
	public String getPrediction(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
        boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
        StringBuilder predictionSB = new StringBuilder();
        
        predictionSB.append(
				(isCrit?"[style.colourExcellent(Critical)]: ":"")
				+ "<span style='color:" + getSpellSchool().getColour().toWebHexString() + ";'>Cast spell '"+ getName() + "'</span>"
				+ " on [npc2.name].");

    	if(getSpellSchool()==SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN) && Main.combat.getManaBurnStack().get(source).size()>0 && Main.combat.getManaBurnStack().get(source).peek()<0) {
    		predictionSB.append("<br/>This will cost <b style='color:"+PresetColour.ATTRIBUTE_HEALTH.toWebHexString()+";'>"+Units.round((-Main.combat.getManaBurnStack().get(source).peek()), 1)+" "+Attribute.HEALTH_MAXIMUM.getName()+"</b>"
    				+ " ([style.colourFire("+StatusEffect.FIRE_MANA_BURN.getName(source)+")]).");
    	} else {
    		predictionSB.append("<br/>This will cost <b style='color:"+PresetColour.ATTRIBUTE_MANA.toWebHexString()+";'>"+this.getModifiedCost(source)+" aura</b>.");
    	}
    	
        predictionSB.append("<br/><i>"+getBasicEffectsString(source, target, enemies, allies)+"</i>");
        
        return UtilText.parse(source, target, predictionSB.toString());
	}

	public String perform(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		boolean isCrit = canCrit(turnIndex, source, target, enemies, allies);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.applyEffect(source, target, enemies, allies, true, isCrit));
		
		if(isCrit && !this.isBeneficial() && source.hasPerkAnywhereInTree(Perk.ARCANE_CRITICALS)) {
			sb.append(UtilText.parse(source, "<br/>[npc.NamePos] [style.boldExcellent(critical)] spell applies [style.boldArcane(arcane weakness)] to "+(target.isPlayer()?"you":UtilText.parse(target, "[npc.name]"))+"!"));
			target.addStatusEffect(StatusEffect.ARCANE_WEAKNESS, 2);
			sb.append(
					UtilText.parse(target,
							"<br/>[npc.NameIsFull] now affected by <b style='color:"+StatusEffect.ARCANE_WEAKNESS.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(StatusEffect.ARCANE_WEAKNESS.getName(target))+"</b>"
									+ " for <b>two turns</b>!"));
		}
		
		return sb.toString();
	}

	// Applies mana cost effects here. If overridden, don't forget to super call it unless it's a free spell.
	public void performOnSelection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(getSpellSchool() == SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
			if(!Main.game.isInCombat()) {
				Main.combat.setupManaBurnStackForOutOfCombat(source);
			}
			Main.combat.getManaBurnStack().get(source).push(source.burnMana(getModifiedCost(source)));
			
		} else {
			source.incrementMana(-getModifiedCost(source));
		}
	}
	
    public void performOnDeselection(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	if(getSpellSchool() == SpellSchool.FIRE && source.hasStatusEffect(StatusEffect.FIRE_MANA_BURN)) {
    		float amount = Main.combat.getManaBurnStack().get(source).pop();
    		if(amount<0) {
        		source.incrementHealth(-amount);
    		} else {
    			source.incrementMana(amount);
    		}
		} else {
			source.incrementMana(getModifiedCost(source));
		}
    }

	public void applyDisruption(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		// Override. Note that disrupted spells don't disrupt their mana.
	}

	//TODO combine these two methods:
	
    public List<String> getCritRequirements(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
    	List<String> critReqs = new ArrayList<>();

    	if(this.getSpellSchool() == SpellSchool.FIRE) {
    		critReqs.add(UtilText.parse(source, "[npc.NamePos] "+Attribute.HEALTH_MAXIMUM.getName()+" is below 25%."));
    	} else {
        	return Util.newArrayListOfValues("It's the only action being used.");
    	}
    	
    	return critReqs;
    }
	
	//Differs from normal version; spells have special crit requirements.
	public boolean canCrit(int turnIndex, GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies) {
		if(this.getSpellSchool() == SpellSchool.FIRE) {
			return source.getHealthPercentage()<=0.25f; // Fire school spells crit when below 25% health.
		} else {
			return source.getSelectedMoves().size()<=1;
		}
	}
}
