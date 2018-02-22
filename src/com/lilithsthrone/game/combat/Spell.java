package com.lilithsthrone.game.combat;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.88
 * @author Innoxia
 */
public enum Spell {

	// OFFENSIVE SPELLS:

	SLAM_1("slam", "spellSlam", DamageType.PHYSICAL, DamageLevel.HIGH, DamageVariance.MEDIUM, SpecialAttackSpellCosts.MEDIUM, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, spellLevel, isCritical);
			float cost = calculateCost(caster, spellLevel);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
						"<p>"
							+ "Summoning a swirling vortex of arcane energy around your [pc.arm], you focus its raw power into a wall of pure force before launching it at [npc.name]."
						+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
						"<p>"
							+ "Summoning a swirling vortex of arcane energy around [npc.her] [npc.arm], [npc.she] focuses its raw power into a wall of pure force before launching it directly at you!"
						+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a wave of crushing force that slams into the unlucky target.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}
	},
	FIREBALL_1("fireball", "spellFireball", DamageType.FIRE, DamageLevel.HIGH, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.BURN_WEAK, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, spellLevel, isCritical);
			float cost = calculateCost(caster, spellLevel);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around [npc.her] [npc.arm], [npc.she] focuses its raw power into a ball of roiling flames before launching it directly at you!"
							+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a ball of flames that can be launched at a target.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}
	},
	
	FIRE_INFERNO("inferno",
			"spellFireball",
			DamageType.FIRE,
			DamageLevel.EXTREME,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.HIGH,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.BURN_WEAK, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, spellLevel, isCritical);
			float cost = calculateCost(caster, spellLevel);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around [npc.her] [npc.arm], [npc.she] focuses its raw power into a ball of roiling flames before launching it directly at you!"
							+ "</p>"));
			}

			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a ball of flames that can be launched at a target.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}
	},
	
	ICESHARD_1("ice shard", "spellIceShard", DamageType.ICE, DamageLevel.POOR, DamageVariance.LOW, SpecialAttackSpellCosts.LOW, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CHILL, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, spellLevel, isCritical);
			float cost = calculateCost(caster, spellLevel);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane ice around your [pc.arm], you focus its raw power into a shard of freezing ice before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane ice around [npc.her] [npc.arm], [npc.she] focuses its raw power into a shard of freezing ice before launching it directly at you!"
							+ "</p>"));
			}

			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shard of ice that can be launched at a target.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}
	},
	POISON_NOVA_1("poison nova", "spellPoisonNova", DamageType.POISON, DamageLevel.POOR, DamageVariance.MEDIUM, SpecialAttackSpellCosts.LOW, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.POISON_WEAK, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, spellLevel, isCritical);
			float cost = calculateCost(caster, spellLevel);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane poison around your [pc.arm], you focus its raw power into a cloud of toxic miasma before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane poison around [npc.her] [npc.arm], [npc.she] focuses its raw power into a cloud of toxic miasma before launching it directly at you!"
							+ "</p>"));
			}

			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a cloud of toxic miasma that can be launched at a target.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}
	},

	// DEFENSIVE SPELLS:
	ARCANE_SHIELD("arcane shield", "specialAttackIcon", DamageType.PHYSICAL, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.LOW, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ARCANE_SHIELD, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float cost = calculateCost(caster, spellLevel);

			for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
				caster.addStatusEffect(se.getKey(), se.getValue() * (isCritical ? 2 : 1));

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of pure energy around your [pc.arm], you focus its raw power into an"
							+ " <b style='color: "+ Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>arcane shield</b> that quickly surrounds you, providing improved protection from physical attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
								"<p>"
									+ "Summoning a swirling vortex of pure energy around [npc.her] [npc.arm], [npc.she] focuses its raw power into an"
										+ " <b style='color: " + Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>" + "arcane shield</b> that quickly surrounds [npc.herHim], providing improved protection from physical attacks."
								+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of pure energy that protects the caster from physical attacks.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return true;
		}
	},
	FIRE_SHIELD("fire shield", "specialAttackIcon", DamageType.FIRE, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.LOW, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.FIRE_SHIELD, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float cost = calculateCost(caster, spellLevel);

			for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
				caster.addStatusEffect(se.getKey(), se.getValue() * (isCritical ? 2 : 1));

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of arcane flames around your [pc.arm], you focus its raw power into a"
								+ " <b style='color: "+ Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>fire shield</b> that quickly surrounds you, providing improved protection from fire attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
								"<p>"
									+ "Summoning a swirling vortex of arcane flames around [npc.her] [npc.arm], [npc.she] focuses its raw power into a"
										+ " <b style='color: " + Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>" + "fire shield</b> that quickly surrounds [npc.herHim], providing improved protection from fire attacks."
								+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of arcane fire that protects the caster from fire attacks.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return true;
		}
	},
	ICE_SHIELD("ice shield", "specialAttackIcon", DamageType.ICE, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.LOW, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.ICE_SHIELD, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float cost = calculateCost(caster, spellLevel);

			for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
				caster.addStatusEffect(se.getKey(), se.getValue() * (isCritical ? 2 : 1));

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of arcane ice around your [pc.arm], you focus its raw power into an" + " <b style='color: "
								+ Attribute.DAMAGE_ICE.getColour().toWebHexString() + ";'>" + "ice shield</b> that quickly surrounds you, providing improved protection from cold attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
								"<p>"
									+ "Summoning a swirling vortex of arcane ice around [npc.her] [npc.arm], [npc.she] focuses its raw power into an" + " <b style='color: "
										+ Attribute.DAMAGE_ICE.getColour().toWebHexString() + ";'>" + "ice shield</b> that quickly surrounds [npc.herHim], providing improved protection from cold attacks."
								+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of arcane ice that protects the caster from cold attacks.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return true;
		}
	},
	
	POISON_SHIELD("poison shield", "specialAttackIcon", DamageType.POISON, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.LOW, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.POISON_SHIELD, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float cost = calculateCost(caster, spellLevel);

			for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
				caster.addStatusEffect(se.getKey(), se.getValue() * (isCritical ? 2 : 1));

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of toxic miasma around your [pc.arm], you focus its raw power into a"
								+ " <b style='color: "+ Attribute.DAMAGE_POISON.getColour().toWebHexString() + ";'>" + "poison shield</b> that quickly surrounds you, providing improved protection from poison attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of toxic miasma around [npc.her] [npc.arm], [npc.she] focuses its raw power into a"
									+ " <b style='color: " + Attribute.DAMAGE_POISON.getColour().toWebHexString() + ";'>" + "poison shield</b> that quickly surrounds [npc.herHim], providing improved protection from poison attacks."
							+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of toxic miasma that protects the caster from poison attacks.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return true;
		}
	},

	CLEANSE("cleanse", "specialAttackIcon", DamageType.MISC, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.MEDIUM, null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {
			return "";
		}

		@Override
		public boolean isSelfCastSpell() {
			return true;
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	BLIND("blind", "specialAttackIcon", DamageType.MISC, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.MEDIUM, null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {
			return "";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	SILENCE("silence", "specialAttackIcon", DamageType.MISC, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.MEDIUM, null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {
			return "";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	HEAL("heal", "specialAttackIcon", DamageType.MISC, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.MEDIUM, null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {
			return "";
		}

		@Override
		public boolean isSelfCastSpell() {
			return true;
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	// MISC SPELLS:
	CHARM("charm", "specialAttackIcon", DamageType.MISC, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.MEDIUM, null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {
			return "";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			// TODO Auto-generated method stub
			return null;
		}
	},

	STUN("stun", "specialAttackIcon", DamageType.MISC, DamageLevel.NONE, DamageVariance.NONE, SpecialAttackSpellCosts.MEDIUM, null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {
			return "";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	
	WITCH_SEAL("Witch's Seal",
			"spell_witch_seal",
			DamageType.MISC,
			DamageLevel.NONE,
			DamageVariance.NONE,
			SpecialAttackSpellCosts.EXTREME,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.WITCH_SEAL, 2))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {

			float cost = calculateCost(caster, spellLevel);

			for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet()) {
				target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementMana(-cost);
			
			if (caster.isPlayer()) {
				return UtilText.parse(target,
						"<p>"
							+ "Concentrating on the arcane power within your broomstick, you summon forth a powerful seal, which traps [npc.name] in place!"
						+ "</p>");
			} else {
				return UtilText.parse(caster,
						"<p>"
							+ "Concentrating on the arcane power within [npc.her] broomstick, [npc.name] summons forth a powerful seal, which traps you in place!"
						+ "</p>");
			}
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Places an arcane seal upon the target, preventing them from taking any action for two turns.";
		}

		@Override
		public boolean isSelfCastSpell() {
			return false;
		}
	},
	
	WITCH_CHARM("Witch's Charm",
			"spell_witch_charm",
			DamageType.MISC,
			DamageLevel.NONE,
			DamageVariance.NONE,
			SpecialAttackSpellCosts.HIGH,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.WITCH_CHARM, 5))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical) {
			float cost = calculateCost(caster, spellLevel);

			for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet()) {
				caster.addStatusEffect(se.getKey(), se.getValue() * (isCritical ? 2 : 1));
			}

			caster.incrementMana(-cost);
			
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

		@Override
		public boolean isSelfCastSpell() {
			return true;
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Places an arcane enchantment upon the target, causing them to appear irresistibly beautiful to anyone who looks upon them.";
		}
	},
	
	;

	private static StringBuilder descriptionSB;

	private String name;
	protected DamageType damageType;
	protected DamageLevel damageLevel;
	protected DamageVariance damageVariance;
	protected SpecialAttackSpellCosts spellCost;
	private Map<StatusEffect, Integer> statusEffects;

	private String SVGString;

	private Spell(String name, String pathName, DamageType damage, DamageLevel damageLevel, DamageVariance damageVariance, SpecialAttackSpellCosts spellCost, Map<StatusEffect, Integer> statusEffects) {
		this.name = name;
		this.damageType = damage;

		this.damageLevel = damageLevel;
		this.damageVariance = damageVariance;

		this.spellCost = spellCost;

		this.statusEffects = statusEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/" + pathName + ".svg");
			SVGString = Util.inputStreamToString(is);

			SVGString = SVGString.replaceAll("#ff2a2a", damage.getMultiplierAttribute().getColour().getShades()[0]);
			SVGString = SVGString.replaceAll("#ff5555", damage.getMultiplierAttribute().getColour().getShades()[1]);
			SVGString = SVGString.replaceAll("#ff8080", damage.getMultiplierAttribute().getColour().getShades()[2]);
			SVGString = SVGString.replaceAll("#ffaaaa", damage.getMultiplierAttribute().getColour().getShades()[3]);
			SVGString = SVGString.replaceAll("#ffd5d5", damage.getMultiplierAttribute().getColour().getShades()[4]);
			
			SVGString += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"
							+ SVGImages.SVG_IMAGE_PROVIDER.getSpellOverlay()
						+ "</div>";
			
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract String applyEffect(GameCharacter caster, GameCharacter target, int spellLevel, boolean isHit, boolean isCritical);

	public abstract boolean isSelfCastSpell();

	public float calculateDamage(GameCharacter caster, GameCharacter target, int spellLevel, boolean critical) {

		float damage = getMinimumDamage(caster, target, spellLevel);

		// Add variation:
		if (getMaximumDamage(caster, target, spellLevel) - getMinimumDamage(caster, target, spellLevel) > 0) {
			float difference = getMaximumDamage(caster, target, spellLevel) - getMinimumDamage(caster, target, spellLevel);
			
			damage += Math.random()*difference;
		}

		// Is critical:
		if (critical)
			damage *= (caster.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;

		return damage;
	}

	public float getMaximumDamage(GameCharacter caster, GameCharacter target, int spellLevel) {
		float damage = getModifiedDamage(caster, target, getDamage(caster, spellLevel) * (1 + damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}

	public float getMinimumDamage(GameCharacter caster, GameCharacter target, int spellLevel) {
		float damage = getModifiedDamage(caster, target, getDamage(caster, spellLevel) * (1 - damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}

	private float getModifiedDamage(GameCharacter caster, GameCharacter target, float attackersDamage) {
		float damage = attackersDamage;
		if (damage < 0)
			damage = 0;

		// Attacker modifiers:
		// Spell modifier:
		damage *= (caster.getAttributeValue(Attribute.DAMAGE_SPELLS) / 100f);
		// Damage Type modifier:
		damage *= (caster.getAttributeValue(damageType.getMultiplierAttribute()) / 100f);

		if (damage < 0)
			damage = 0;

		if (target != null) {
			// Defender modifiers:
			// Spell modifier:
			damage *= ((100 - target.getAttributeValue(Attribute.RESISTANCE_SPELLS)) / 100f);
			// Damage Type modifier:
			damage *= ((100 - target.getAttributeValue(damageType.getResistAttribute())) / 100f);

			if (damage < 0)
				damage = 0;

			// Modifiers based on level:
			if (target.getLevel() - caster.getLevel() >= 3) // High defender
															// level
				return damage * 0.5f;
			else if (target.getLevel() - caster.getLevel() <= -3) // Low
																	// defender
																	// level
				return damage * 1.5f;
			else
				return damage;

		} else
			return damage;

	}

	public float calculateCost(GameCharacter caster, int level) {

		float cost = getMinimumCost(caster, level);

		// Add variation:
		if (getMaximumCost(caster, level) - getMinimumCost(caster, level) > 0) {
			float difference = getMaximumCost(caster, level) - getMinimumCost(caster, level);
			
			cost += Math.random()*difference;
		}

		// Round float value to nearest 1 decimal place:
		cost = (Math.round(cost*10))/10f;

		return cost;
	}

	public float getMaximumCost(GameCharacter caster, int level) {
		float damage = getModifiedCost(caster, level);
		
		damage*=1.1f;
		
		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}

	public float getMinimumCost(GameCharacter caster, int level) {
		float damage = getModifiedCost(caster, level);
		
		damage*=0.9f;
		
		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}
	
	private float getModifiedCost(GameCharacter caster, int level) {
		float calculatedCost = level + (caster.getAttributeValue(Attribute.MANA_MAXIMUM) * (spellCost.getPercentage())/100f);
		
		calculatedCost *= ((100 - caster.getAttributeValue(Attribute.SPELL_COST_MODIFIER)) / 100f);
		
		// Round float value to nearest 1 decimal place:
		calculatedCost = (Math.round(calculatedCost*10))/10f;
		
		return calculatedCost;
	}

	public abstract String getDescription(GameCharacter caster, int level);

	private StringBuilder damageCostDescriptionSB;

	protected String getDamageAndCostDescription(GameCharacter caster, GameCharacter target, float cost, float damage, boolean isHit, boolean isCritical) {
		damageCostDescriptionSB = new StringBuilder();

		if (caster == Main.game.getPlayer()) {
			if (isCritical)
				damageCostDescriptionSB.append(!isSelfCastSpell()
						? "<p>" + (isHit ? "<b>You <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
								+ damageType.getName() + "</b>" + " damage!</b>" : "<b>You missed!</b>") + "</p>"
						: "<p>" + (isHit ? "<b>You <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> cast the spell, doubling its duration!</b>" : "<b>You missed!</b>") + "</p>");
			else
				damageCostDescriptionSB.append(!isSelfCastSpell()
						? "<p>" + (isHit ? "<b>You did " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + damageType.getName() + "</b>" + " damage!</b>" : "<b>You missed!</b>") + "</p>"
						: "");

			if (statusEffects != null && isHit) {
				damageCostDescriptionSB.append(UtilText.parse(target, (!isSelfCastSpell() ? "<p>[npc.She] is now suffering " : "<p>You are now benefiting from ")));
				int i = 0;
				for (Entry<StatusEffect, Integer> seEntry : statusEffects.entrySet()) {
					if (i != 0) {
						if (i == statusEffects.size() - 1)
							damageCostDescriptionSB.append(" and ");
						else
							damageCostDescriptionSB.append(", ");
					}
					damageCostDescriptionSB.append("<b>" + seEntry.getValue() + "</b> turns of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
				}
				damageCostDescriptionSB.append(".</p>");
			}

			damageCostDescriptionSB
					.append("<p>" + "Harnessing the arcane to cast spells is incredibly draining, and you lose <b>" + cost + "</b> <b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>aura</b>!</b>" + "</p>");
		} else {

			if (isCritical)
				damageCostDescriptionSB.append(!isSelfCastSpell()
						? "<p>" + (isHit ? "<b>You were <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
								+ damageType.getName() + "</b>" + " damage!</b>" : "<b>" + caster.getName("The") + " missed!</b>") + "</p>"
						: "<p>" + (isHit ? "<b>" + caster.getName("The") + " <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> cast the spell, doubling its duration!</b>"
								: "<b>" + caster.getName("The") + " missed!</b>") + "</p>");
			else
				damageCostDescriptionSB
						.append(!isSelfCastSpell() ? "<p>" + (isHit ? "<b>You took " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + damageType.getName() + "</b>" + " damage!</b>"
								: "<b>" + caster.getName("The") + " missed!</b>") + "</p>" : "");

			if (statusEffects != null && isHit) {
				damageCostDescriptionSB.append(UtilText.parse(caster, (!isSelfCastSpell() ? "<p>You are now suffering " : "<p>[npc.She] is now benefiting from ")));
				int i = 0;
				for (Entry<StatusEffect, Integer> seEntry : statusEffects.entrySet()) {
					if (i != 0) {
						if (i == statusEffects.size() - 1)
							damageCostDescriptionSB.append(" and ");
						else
							damageCostDescriptionSB.append(", ");
					}
					damageCostDescriptionSB.append("<b>" + seEntry.getValue() + "</b> turns of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
				}
				damageCostDescriptionSB.append("!</p>");
			}

			damageCostDescriptionSB.append(UtilText.parse(caster,
					"<p>" + "Harnessing the arcane to cast spells is incredibly draining, and [npc.she] loses <b>" + cost + "</b> <b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>aura</b>!</b>" + "</p>"));
		}

		return damageCostDescriptionSB.toString();
	}

	public String getName() {
		return name;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	/**
	 * Spell damage is 3 + (caster level) * (1 + (spell level *
	 * damageModifier)):
	 * 
	 * @param attacker
	 * @return
	 */
	public float getDamage(GameCharacter caster, int level) {
		float damage = (4 + caster.getLevel()) * damageLevel.getDamageModifier();

		if (damage < 0)
			damage = 0;

		return damage;
	}

	public DamageVariance getDamageVariance(int level) {
		return damageVariance;
	}

	public Map<StatusEffect, Integer> getStatusEffects() {
		return statusEffects;
	}

	public String getSVGString() {
		return SVGString;
	}
}
