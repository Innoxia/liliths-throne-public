package com.lilithsthrone.game.character.effects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.combat.SpellUpgrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.3.4
 * @author Innoxia
 */
public class Perk {
	
	// NPC Histories:
	
	public static AbstractPerk JOB_MISC = new AbstractPerk(20,
			true,
			"Misc",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return ".";//TODO
		}
	};

	public static AbstractPerk JOB_NPC_HARPY_MATRIARCH = new AbstractPerk(20,
			true,
			"queen of the skies",
			PerkCategory.JOB,
			"perks/jobs/npc_harpy_matriarch",
			Colour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 25),
					new Value<Attribute, Integer>(Attribute.MAJOR_CORRUPTION, 25),
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] risen to the position of matriarch of a harpy flock through a combination of [npc.her] natural beauty and willingness to do anything it takes to rule.");
		}
	};

	public static AbstractPerk JOB_NPC_HARPY_FLOCK_MEMBER = new AbstractPerk(20,
			true,
			"pecking order",
			PerkCategory.JOB,
			"perks/jobs/npc_harpy_flock_member",
			Colour.RACE_HARPY,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 10),
					new Value<Attribute, Integer>(Attribute.MAJOR_CORRUPTION, 25),
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] the member of a harpy flock, and as such has considerable experience in both flattering and backstabbing others.");
		}
	};

	
	// Enforcers:
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_INSPECTOR = new AbstractPerk(20,
			true,
			"Enforcer: Patrol Inspector",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Inspector' in the Enforcer's Frontline Patrol division, and has received a moderate amount of combat training.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_SERGEANT = new AbstractPerk(20,
			true,
			"Enforcer: Patrol Sergeant",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Sergeant' in the Enforcer's Frontline Patrol division, and has received a limited amount of combat training.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_PATROL_CONSTABLE = new AbstractPerk(20,
			true,
			"Enforcer: Patrol Constable",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 1),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Constable' in the Enforcer's Frontline Patrol division, and has received a very limited amount of combat training.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_INSPECTOR = new AbstractPerk(20,
			true,
			"Enforcer: SWORD Inspector",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Inspector' in the Enforcer's 'Special Weapons and Operations Response Department', and has received an extensive amount of combat training.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_SERGEANT = new AbstractPerk(20,
			true,
			"Enforcer: SWORD Sergeant",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 20)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Sergeant' in the Enforcer's 'Special Weapons and Operations Response Department', and has received a significant amount of combat training.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_SWORD_CONSTABLE = new AbstractPerk(20,
			true,
			"Enforcer: SWORD Constable",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 15)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Constable' in the Enforcer's 'Special Weapons and Operations Response Department', and has received a good amount of combat training.");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_INSPECTOR = new AbstractPerk(20,
			true,
			"Enforcer: ORICL Inspector",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_inspector",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 15),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 10)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Inspector' in the Enforcer's 'Office of Realm Intelligence, Counter-Espionage, and Logistics', and knows many ways in which to get someone to talk...");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_SERGEANT = new AbstractPerk(20,
			true,
			"Enforcer: ORICL Sergeant",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_sergeant",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Sergeant' in the Enforcer's 'Office of Realm Intelligence, Counter-Espionage, and Logistics', and knows many ways in which to get someone to talk...");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_ENFORCER_ORICL_CONSTABLE = new AbstractPerk(20,
			true,
			"Enforcer: ORICL Constable",
			PerkCategory.JOB,
			"perks/jobs/npc_enforcer_constable",
			Colour.CLOTHING_BLUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] holds the rank of 'Constable' in the Enforcer's 'Office of Realm Intelligence, Counter-Espionage, and Logistics', and knows many ways in which to get someone to talk...");
		}
		@Override
		public String getSVGString(GameCharacter owner) {
			if(owner.isFeminine()) {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_PINK));
			} else {
				generateSVGImage(this.pathName, Util.newArrayListOfValues(Colour.CLOTHING_BLUE));
			}
			return super.getSVGString(owner);
		}
	};
	
	public static AbstractPerk JOB_NPC_CULTIST = new AbstractPerk(20,
			true,
			"Worshipper of Lilith",
			PerkCategory.JOB,
			"perks/jobs/npc_cultist",
			Colour.CLOTHING_BLACK,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] a full-time member of the 'Cult of Lilith', and in order to properly worship [npc.her] goddess, [npc.she] has spent an inordinate amount of time training both [npc.her] spell-casting and seductive powers.");
		}
	};

	public static AbstractPerk JOB_NPC_SLAVER_ADMIN = new AbstractPerk(20,
			true,
			"shady overseer",
			PerkCategory.JOB,
			"perks/jobs/npc_slave_admin",
			Colour.CLOTHING_BLACK_STEEL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_CORRUPTION, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] the manager of Slaver Alley's 'Slave Administration' building, and while [npc.she] acts friendly enough, it's sure to have taken a lot more than simply being nice to get to this position...");
		}
	};

	public static AbstractPerk JOB_NPC_BOUNCER = new AbstractPerk(20,
			true,
			"bouncer",
			PerkCategory.JOB,
			"perks/jobs/npc_bouncer",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 15),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] a bouncer, and [npc.has] accumulated a significant amount of fighting experience while working to keep the riff-raff out of nightclubs and bars.");
		}
	};

	public static AbstractPerk JOB_NPC_BARMAID = new AbstractPerk(20,
			true,
			"barmaid",
			PerkCategory.JOB,
			"perks/jobs/npc_barmaid",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 15),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner==null) {
				return "bartender";
			}
			if(owner.isFeminine()) {
				return super.getName(owner);
			}
			return "barman";
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"Thanks to [npc.her] experience working as a "+(owner.isFeminine()?"barmaid":"barman")+", [npc.name] knows all the tricks to flirting, as well as how to resist the advances of lustful drunks.");
		}
	};

	public static AbstractPerk JOB_NPC_BEAUTICIAN = new AbstractPerk(20,
			true,
			"beauty ideal",
			PerkCategory.JOB,
			"perks/jobs/npc_beautician",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 15),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.Name] knows all there is to how to make people look their best.");
		}
	};
	
	public static AbstractPerk JOB_NPC_NIGHTCLUB_OWNER = new AbstractPerk(20,
			true,
			"the boss",
			PerkCategory.JOB,
			"perks/jobs/npc_nightclub_owner",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] the very wealthy owner of one of Dominion's most popular nightclubs, and has no trouble in getting company for each evening.");
		}
	};

	public static AbstractPerk JOB_NPC_ARCANE_RESEARCHER = new AbstractPerk(20,
			true,
			"secrets of the arcane",
			PerkCategory.JOB,
			"perks/jobs/npc_arcane_researcher",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 50),
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] dedicated most of [npc.her] life to studying the arcane and unlocking its secrets.");
		}
	};

	public static AbstractPerk JOB_NPC_SHOP_MANAGER = new AbstractPerk(20,
			true,
			"manager",
			PerkCategory.JOB,
			"perks/jobs/npc_shop_manager",
			Colour.CURRENCY_GOLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.ENERGY_SHIELDING, 1)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The pressure of having to make sure [npc.her] business is successful has caused [npc.name] to have to do a lot of work, building up [npc.her] resistances in the process.");
		}
	};

	public static AbstractPerk JOB_NPC_REINDEER_OVERSEER = new AbstractPerk(20,
			true,
			"winter-proof",
			PerkCategory.JOB,
			"perks/jobs/npc_reindeer_overseer",
			Colour.RACE_REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 5)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] the overseer of a reindeer workgang, and has built up not only a very strong body, but also an impressive resistance to the cold.");
		}
	};

	public static AbstractPerk JOB_ELDER_LILIN = new AbstractPerk(20,
			true,
			"untouchable",
			PerkCategory.JOB,
			"perks/jobs/elder_lilin",
			Colour.RACE_LILIN,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 100),
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 100),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 100),
					new Value<Attribute, Integer>(Attribute.ENERGY_SHIELDING, 100),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 100),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 100),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 100),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 100),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 100)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] one of the seven elder lilin, and holds powers that mere mortals can only dream of.");
		}
	};

	public static AbstractPerk JOB_NPC_SLIME_QUEEN = new AbstractPerk(20,
			true,
			"royal jelly",
			PerkCategory.JOB,
			"perks/jobs/npc_slime_queen",
			Colour.SLIME_PINK,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] the queen of all slimes, and is able to use [npc.her] shape-shifting abilities to make [npc.herself] look truly beautiful.");
		}
	};

	public static AbstractPerk JOB_NPC_SLIME_QUEEN_GUARD = new AbstractPerk(20,
			true,
			"slime servant",
			PerkCategory.JOB,
			"perks/jobs/npc_slime_queen_guard",
			Colour.RACE_REINDEER_MORPH,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 25),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 25)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] one of the slime queen's personal guardians, and is well trained in how to handle a sword.");
		}
	};

	public static AbstractPerk JOB_EPONA = new AbstractPerk(20,
			true,
			"fertility queen",
			PerkCategory.JOB,
			"perks/jobs/npc_epona",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.FERTILITY, 100),
					new Value<Attribute, Integer>(Attribute.VIRILITY, 100)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameHasFull] dedicated [npc.her] entire life to helping others become mothers and fathers, and is currently achieving this noble goal by running the game 'pregnancy roulette' in Submission's Gambling Den.");
		}
	};

	public static AbstractPerk JOB_LYSSIETH_GUARD = new AbstractPerk(20,
			true,
			"dutiful daughter",
			PerkCategory.JOB,
			"perks/jobs/npc_lyssieth_guard",
			Util.newArrayListOfValues(
					Colour.DAMAGE_TYPE_PHYSICAL,
					Colour.CLOTHING_STEEL,
					Colour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 25),
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 25),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"[npc.NameIsFull] an unrecognised daughter of the elder lilin, Lyssieth, and has been given the task of defending her mother's palace from unwelcome visitors.");
		}
	};
	
	
	
	
	public static AbstractPerk JOB_SLAVE = new AbstractPerk(20,
			true,
			"A life of servitude",
			PerkCategory.JOB,
			"perks/jobs/slave",
			Colour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 2),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 2)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] steeled [npc.her] body and mind to help [npc.herHim] deal with the fact that [npc.sheIs] just someone else's property.");
		}
	};
	
	public static AbstractPerk JOB_PROSTITUTE = new AbstractPerk(20,
			true,
			"The oldest profession",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_CORRUPTION, 50),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 25),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Doubles)] all slave and self-prostitution income")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] experienced at selling [npc.her] body to strangers in order to make a living. After having sex so many times, it takes a lot to get [npc.herHim] really turned on.");
		}
	};
	
	public static AbstractPerk JOB_MUGGER = new AbstractPerk(20,
			true,
			"Outlaw",
			PerkCategory.JOB,
			"perks/jobs/mugger",
			Colour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(Triples)] all mugging income")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(live)] a life of crime, stealing from the rich and poor alike.");
		}
	};
	
	public static AbstractPerk JOB_CONSTRUCTION_WORKER = new AbstractPerk(20,
			true,
			"Builder",
			PerkCategory.JOB,
			"perks/jobs/npc_construction",
			Util.newArrayListOfValues(Colour.CLOTHING_YELLOW, Colour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 15)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] [npc.verb(spent)] a while working in the construction industry, helping to build and repair both infrastructure and property.");
		}
	};
	
	public static AbstractPerk JOB_CONSTRUCTION_WORKER_ARCANE = new AbstractPerk(20,
			true,
			"Matter manipulation",
			PerkCategory.JOB,
			"perks/jobs/npc_construction",
			Util.newArrayListOfValues(Colour.CLOTHING_PURPLE, Colour.CLOTHING_BLACK),
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 10),
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 25)),
			null,
			null, null, null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Being able to harness the arcane, and therefore able to telekinetically manipulate manner, [npc.nameIsFull] a very valuable asset for construction firms to have in their employ.");
		}
	};
	
	
	
	
	// Player Histories:
	
	public static AbstractPerk JOB_UNEMPLOYED = new AbstractPerk(20,
			true,
			"NEET",
			PerkCategory.JOB,
			"perks/jobs/unemployed",
			Colour.BASE_RED,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 2),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues("[style.boldExcellent(Boosts)] 'Well Rested' bonus")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "With so much free time on [npc.herPos] hands, [npc.nameHas] managed to improve [npc.herself] in several different ways."
					+ " [npc.Name] also [npc.verb(benefit)] from knowing exactly how best to relax, boosting the bonus [npc.she] [npc.verb(get)] from sleeping.");
		}
	};
	
	public static AbstractPerk JOB_OFFICE_WORKER = new AbstractPerk(20,
			true,
			"The Salaryman",
			PerkCategory.JOB,
			"perks/jobs/officeWorker",
			Colour.BASE_BROWN,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.CRITICAL_DAMAGE, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(+25%)] all slave income")) {
		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "The Career Woman";
			} else {
				return "The Salaryman";
			}
		}
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "From [npc.herPos] considerable office experience, [npc.name] [npc.verb(know)] exactly how to motivate those working beneath [npc.herPro]."
					+ " The stressful work environment has caused [npc.herPro] to bottle up a lot of frustration, which manifests in increased critical power.");
		}
	};
	
	public static AbstractPerk JOB_STUDENT = new AbstractPerk(20,
			true,
			"Student Discount",
			PerkCategory.JOB,
			"perks/jobs/student",
			Colour.BASE_YELLOW,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 10)),
			Util.newArrayListOfValues("[style.boldExcellent(25%)] discount in all stores")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] student discount has never failed [npc.herPro] before!"
					+ " Along with a guaranteed 25% discount in all stores, [npc.she] can be confident in [npc.herPos] ability to quickly learn new things.");
		}
	};
	
	public static AbstractPerk JOB_MUSICIAN = new AbstractPerk(20,
			true,
			"Arcane Composition",
			PerkCategory.JOB,
			"perks/jobs/musician",
			Colour.BASE_GREY,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(Double)] length of all spell effects")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(find)] that [npc.herPos] abilities as a musician translate quite well into the art of seduction."
					+ " [npc.She] [npc.verb(feel)] the same sort of rhythm in casting spells as [npc.she] [npc.do] with music, resulting in all of [npc.herPos] spell effects lasting twice as long as usual.");
		}
	};
	
	public static AbstractPerk JOB_TEACHER = new AbstractPerk(20,
			true,
			"In Control",
			PerkCategory.JOB,
			"perks/jobs/teacher",
			Colour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 10)),
			Util.newArrayListOfValues("[style.boldExcellent(Triple)] all slave obedience gains")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(know)] exactly how to deal with unruly students."
					+ " [npc.HerPos] ability to clearly understand and explain difficult subjects is reflected in a reduced cost of casting spells.");
		}
	};
	
	public static AbstractPerk JOB_WRITER = new AbstractPerk(20,
			true,
			"Meditations",
			PerkCategory.JOB,
			"perks/jobs/writer",
			Colour.BASE_PURPLE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(+25%)] to all experience gains")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(keep)] a diary of [npc.herPos] personal thoughts and encounters, allowing [npc.herPro] to reflect upon and learn from [npc.herPos] experiences."
					+ " [npc.HerPos] keen interest in books also allows [npc.herPro] to quickly read up on the most effective application of spells.");
		}
	};

	public static AbstractPerk JOB_CHEF = new AbstractPerk(20,
			true,
			"Fine Taste",
			PerkCategory.JOB,
			"perks/jobs/chef",
			Colour.BASE_ORANGE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(Double)] all potions effects' strength and length")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Thanks to spending a considerable amount of time tasting food, [npc.name] [npc.has] both a significant resistance to poison, as well as the ability to make culinary marvels out of basic ingredients.");
		}
	};

	public static AbstractPerk JOB_SOLDIER = new AbstractPerk(20,
			true,
			"Controlled Aggression",
			PerkCategory.JOB,
			"perks/jobs/soldier",
			Colour.BASE_GREEN,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 25),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 10)),
			Util.newArrayListOfValues("Your first strike in combat deals [style.boldExcellent(double)] damage")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHas] spent a considerable amount of time training to fight, and as a result, [npc.she] [npc.is] far stronger and healthier than a normal person."
					+ " Thanks to this training, [npc.she] [npc.is] also able to channel [npc.herPos] aggression into [npc.herPos] attacks.");
		}
	};

	public static AbstractPerk JOB_ATHLETE = new AbstractPerk(20,
			true,
			"Ten-Second Barrier",
			PerkCategory.JOB,
			"perks/jobs/athlete",
			Colour.BASE_TEAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 10)),
			Util.newArrayListOfValues("All non-zero escape chances in combat are boosted to [style.boldExcellent(100%)]")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] a world-class sprinter, and [npc.has] a guaranteed 100% success of escaping any combat situation where running away is an option.");
		}
	};

	public static AbstractPerk JOB_MAID = new AbstractPerk(20,
			true,
			"Housekeeper",
			PerkCategory.JOB,
			"perks/jobs/maid",
			Colour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5),
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Boosted)] Maid's set bonuses",
					"[style.boldExcellent(Double)] slave income from maids and butlers")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.is] the perfect example of a hard-working maid, and while wearing a complete set of maid's clothes, the bonus that [npc.she] [npc.verb(receive)] is considerably boosted."
					+ " [npc.She] also [npc.verb(know)] how to train butlers and other maids to be exceptional at their jobs.");
		}
	};

	public static AbstractPerk JOB_BUTLER = new AbstractPerk(20,
			true,
			"Legacy of Jeeves",
			PerkCategory.JOB,
			"perks/jobs/butler",
			Colour.BASE_BLUE_STEEL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 25)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Boosted)] Butler's set bonuses",
					"[style.boldExcellent(Double)] slave income from maids and butlers")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.name] [npc.is] the perfect example of a hard-working butler, and while wearing a complete set of butler's clothes, the bonus that [npc.she] [npc.verb(receive)] is considerably boosted."
					+ " [npc.She] also [npc.verb(know)] how to train maids and other butlers to be exceptional at their jobs.");
		}
	};
	
	// Physical:
	
	public static AbstractPerk PHYSICAL_BASE = new AbstractPerk(20,
			false,
			"natural fitness",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You have a natural amount of physical fitness.";
		}
	};

	public static AbstractPerk CRITICAL_BOOST = new AbstractPerk(20,
			false,
			"critical power",
			PerkCategory.PHYSICAL,
			"perks/critical_power",
			Colour.BASE_ORANGE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_DAMAGE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] able to really get the most out of any critical moves [npc.she] [npc.verb(make)].");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_ALT = new AbstractPerk(20,
			false,
			"critical power",
			PerkCategory.PHYSICAL,
			"perks/critical_power",
			Colour.BASE_ORANGE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_DAMAGE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] able to really get the most out of any critical moves [npc.she] [npc.verb(make)].");
		}
	};

	public static AbstractPerk CRITICAL_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"critical power",
			PerkCategory.PHYSICAL,
			"perks/critical_power",
			Colour.BASE_ORANGE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_DAMAGE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] able to really get the most out of any critical moves [npc.she] [npc.verb(make)].");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST = new AbstractPerk(20,
			false,
			"physically fit",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent a lot of time working towards improving [npc.her] physical fitness.");
		}
	};
	
	public static AbstractPerk PHYSIQUE_BOOST_ALT = new AbstractPerk(20,
			false,
			"physically fit",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent a lot of time working towards improving [npc.her] physical fitness.");
		}
	};

	public static AbstractPerk PHYSIQUE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"physically fit",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Through hard work and perseverance, [npc.nameHasFull] managed to massively improve [npc.her] level of physical fitness.");
		}
	};
	
	public static AbstractPerk PHYSICAL_DAMAGE = new AbstractPerk(20,
			false,
			"striker",
			PerkCategory.PHYSICAL,
			"perks/strike",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By training without the aid of the arcane, [npc.nameHasFull] managed to improve the damage of [npc.her] physical attacks.");
		}
	};

	public static AbstractPerk UNARMED_DAMAGE = new AbstractPerk(20,
			false,
			"hand-to-hand",
			PerkCategory.PHYSICAL,
			"perks/unarmed_damage",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent many hours in training for hand-to-hand combat, making [npc.herHim] a dangerous foe even when unarmed.");
		}
	};

	public static AbstractPerk MELEE_DAMAGE = new AbstractPerk(20,
			false,
			"melee weapons expert",
			PerkCategory.PHYSICAL,
			"perks/melee_damage",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Having spent considerable time training with all sorts of different melee weapons, [npc.nameIsFull] now a very dangerous foe to face in close combat.");
		}
	};

	public static AbstractPerk RANGED_DAMAGE = new AbstractPerk(20,
			false,
			"sharp-shooter",
			PerkCategory.PHYSICAL,
			"perks/ranged_damage",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Proficient with all sorts of ranged weapons, [npc.nameIsFull] a truly terrifying foe to face in long-distance engagements.");
		}
	};

	public static AbstractPerk BESERK = new AbstractPerk(20,
			false,
			"beserk",
			PerkCategory.PHYSICAL,
			"perks/beserk",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 20),
					new Value<Attribute, Integer>(Attribute.CRITICAL_DAMAGE, 20),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, -2),
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, -15),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, -15)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Having little time for strategies which don't involve an all-out show of force, [npc.name] [npc.verb(throw)] [npc.herself] into battle with a complete disregard for [npc.her] own safety.");
		}
	};
	
	public static AbstractPerk PHYSICAL_DEFENCE = new AbstractPerk(20,
			false,
			"defender",
			PerkCategory.PHYSICAL,
			"perks/shield",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent a good deal of time working on improving [npc.her] ability to counter physical attacks.");
		}
	};
	
	public static AbstractPerk SPELL_DAMAGE = new AbstractPerk(20,
			false,
			"spell power",
			PerkCategory.ARCANE,
			"perks/arcane_power_1",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By spending time practicing the casting of spells, [npc.nameHasFull] discovered new ways in which to increase their effectiveness.");
		}
	};
	
	public static AbstractPerk SPELL_DAMAGE_MAJOR = new AbstractPerk(20,
			false,
			"spell mastery",
			PerkCategory.ARCANE,
			"perks/arcane_power_3",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By spending a great deal of time focusing on improving the power of [npc.her] spells, [npc.nameIsFull] now able to cast them much more effectively than before.");
		}
	};
	
	public static AbstractPerk SPELL_EFFICIENCY = new AbstractPerk(20,
			false,
			"spell efficiency",
			PerkCategory.ARCANE,
			"perks/spell_efficiency",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] managed to improve the efficiency of [npc.her] spell casting!");
		}
	};
	
	public static AbstractPerk AURA_BOOST = new AbstractPerk(20,
			false,
			"aura reserves",
			PerkCategory.ARCANE,
			"perks/resource_boost",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "After spending quite some time focusing inwards, [npc.nameHasFull] managed to increase [npc.her] aura reserves.");
		}
	};
	
	public static AbstractPerk ENERGY_BOOST = new AbstractPerk(20,
			false,
			"energy reserves",
			PerkCategory.PHYSICAL,
			"perks/resource_boost",
			Colour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] increased [npc.her] energy reserves, allowing [npc.herHim] to withstand attacks that would once have taken [npc.herHim] out of the fight.");
		}
	};

	public static AbstractPerk ENERGY_BOOST_DRAIN_DAMAGE = new AbstractPerk(20,
			false,
			"aura shielding",
			PerkCategory.PHYSICAL,
			"perks/resource_boost_drain_aura",
			Colour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 20),
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, -25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By focusing [npc.her] arcane aura into a protective barrier, [npc.nameIsFull] able to endure taking more damage than [npc.she] otherwise would be able to.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST = new AbstractPerk(20,
			false,
			"elemental striker",
			PerkCategory.LUST,
			"perks/elemental_damage",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] dedicated [npc.herself] to learning how to harness all of the arcane elements more effectively.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST_ALT = new AbstractPerk(20,
			false,
			"elemental striker",
			PerkCategory.LUST,
			"perks/elemental_damage",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] dedicated [npc.herself] to learning how to harness all of the arcane elements more effectively.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"elemental striker",
			PerkCategory.LUST,
			"perks/elemental_damage",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] dedicated [npc.herself] to learning how to harness all of the arcane elements more effectively.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_DEFENCE_BOOST = new AbstractPerk(20,
			false,
			"elemental defender",
			PerkCategory.LUST,
			"perks/elemental_defence",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 1),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 1),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent time learning how best to defend [npc.herself] from the arcane elements.");
		}
	};
	
	
	public static AbstractPerk ARCANE_BASE = new AbstractPerk(20,
			false,
			"natural arcane power",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 1)),
			null) {
		
		@Override
		public HashMap<Attribute, Integer> getAttributeModifiers(GameCharacter character) {
			if(character!=null && character.isPlayer()) {
				return Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 20));
			} else {
				return super.getAttributeModifiers(character);
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if(owner.isPlayer()) {
				return "You have a surprisingly large amount of natural arcane power; far more than a regular person aught to have.";
			} else {
				return "Everybody in this reality has an arcane aura, no matter how weak, and so at the very least has a tiny hint of arcane power available to them.";
			}
		}
	};

	public static AbstractPerk ARCANE_BOOST = new AbstractPerk(20,
			false,
			"arcane training",
			PerkCategory.ARCANE,
			"perks/attIntelligence1",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] focused on improving [npc.her] ability to harness the arcane.");
		}
	};

	public static AbstractPerk ARCANE_BOOST_ALT = new AbstractPerk(20,
			false,
			"arcane training",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] focused on improving [npc.her] ability to harness the arcane.");
		}
	};

	public static AbstractPerk ARCANE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"arcane affinity",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] unlocked secrets of the arcane which few ever get to know.");
		}
	};
	
	
	public static AbstractPerk LEWD_KNOWLEDGE = new AbstractPerk(20,
			false,
			"lewd knowledge",
			PerkCategory.LUST,
			"perks/lewd_knowledge",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 1),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] [npc.verb(know)] exactly what sort of lewd things the residents of Dominion get up to, and so can use this knowledge to [npc.her] advantage...");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST = new AbstractPerk(20,
			false,
			"seductive",
			PerkCategory.LUST,
			"perks/attSeduction1",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent a good deal of time working on [npc.her] seduction techniques, allowing [npc.herHim] to deal extra damage when teasing others.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT = new AbstractPerk(20,
			false,
			"seductive",
			PerkCategory.LUST,
			"perks/attSeduction1",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent a good deal of time working on [npc.her] seduction techniques, allowing [npc.herHim] to deal extra damage when teasing others.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_ALT_2 = new AbstractPerk(20,
			false,
			"seductive",
			PerkCategory.LUST,
			"perks/attSeduction1",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent a good deal of time working on [npc.her] seduction techniques, allowing [npc.herHim] to deal extra damage when teasing others.");
		}
	};
	
	public static AbstractPerk SEDUCTION_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"seductive",
			PerkCategory.LUST,
			"perks/attSeduction5",
			Colour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] a walking sex bomb. [npc.Her] every movement drips with suggestive body language, and people can't help but feel extremely aroused just by looking at [npc.herHim].");
		}
	};
	
	public static AbstractPerk SEDUCTION_DEFENCE_BOOST = new AbstractPerk(20,
			false,
			"resistance",
			PerkCategory.LUST,
			"perks/shield",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] steeled [npc.herself] to resist any temptation, making it much more likely that [npc.she]'ll be able to avoid getting turned on when teased.");
		}
	};
	
	public static AbstractPerk VIRILITY_BOOST = new AbstractPerk(20,
			false,
			"virile",
			PerkCategory.LUST,
			"perks/virile",
			Colour.BASE_BLUE_LIGHT,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] body is able to produce very potent sperm, increasing the chance that any sexual partner of [npc.hers] gets pregnant.");
		}
	};
	
	public static AbstractPerk VIRILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"virile",
			PerkCategory.LUST,
			"perks/virile",
			Colour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] body is able to produce incredibly potent sperm, greatly increasing the chance that any sexual partner of [npc.hers] gets pregnant.");
		}
	};

	public static AbstractPerk FERTILITY_BOOST = new AbstractPerk(20,
			false,
			"fertile",
			PerkCategory.LUST,
			"perks/fertile",
			Colour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] body is very fertile, increasing the chance that any sexual partner of [npc.hers] manages to get [npc.her] pregnant.");
		}
	};

	public static AbstractPerk FERTILITY_MAJOR_BOOST = new AbstractPerk(20,
			false,
			"fertile",
			PerkCategory.LUST,
			"perks/fertile",
			Colour.GENERIC_EXCELLENT,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, 25)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] body is incredibly fertile, increasing the chance that any sexual partner of [npc.hers] manages to get [npc.her] pregnant.");
		}
	};
	
	
	public static AbstractPerk ARCANE_COMBATANT = new AbstractPerk(20,
			true,
			"arcane combatant",
			PerkCategory.ARCANE,
			"perks/physical_brawler",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 10),
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] an expert harnessing the arcane for combat applications. [npc.She] [npc.verb(gain)] a bonus to [npc.her] spell damage and efficiency.");
		}
	};
	
	public static AbstractPerk SACRIFICIAL_SHIELDING = new AbstractPerk(20,
			true,
			"sacrificial shielding",
			PerkCategory.ARCANE,
			"perks/sacrificial_shielding",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, -10),
					new Value<Attribute, Integer>(Attribute.ENERGY_SHIELDING, 2)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By sacrificing a portion of [npc.her] physical strength, [npc.nameIsFull] able to summon forth an all-protective shield.");
		}
	};

	public static AbstractPerk ARCANE_VAMPYRISM = new AbstractPerk(20,
			true,
			"arcane vampyrism",
			PerkCategory.ARCANE,
			"perks/arcane_vampyrism",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, -25)),
			Util.newArrayListOfValues(
					"[style.colourExcellent(Absorb 50%)] of any combatant's remaining [style.colourMana("+Attribute.MANA_MAXIMUM.getName()+")] when they are defeated")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] trained [npc.her] arcane aura to be vampyric in nature, relying less on its own reserves, and more upon the ability to drain the aura from anyone (friend or foe) who is defeated nearby.");
		}
	};
	
	
	public static AbstractPerk FEROCIOUS_WARRIOR = new AbstractPerk(20,
			true,
			"ferocious warrior",
			PerkCategory.PHYSICAL,
			"perks/physical_brawler",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 2)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] a lot of fighting experience, making [npc.herHim] a dangerous opponent for anyone foolish enough to challenge [npc.herHim].");
		}
	};
	
	
	public static AbstractPerk OBSERVANT = new AbstractPerk(60,
			true,
			"observant",
			PerkCategory.PHYSICAL,
			"perks/misc_observant",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Gender detection</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner!=null && owner.isPlayer()) {
				return "You are very perceptive, and are capable of noticing the slightest of changes in your surroundings."
						+ " You are always able to determine a person's gender, even if you have no knowledge of what their groin looks like.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is very perceptive, and [npc.she] continuously scans [npc.her] surroundings for signs of danger.");
			}
		}
	};

	// Arcane:
	
	public static AbstractPerk ARCANE_CRITICALS = new AbstractPerk(60,
			true,
			"arcane precision",
			PerkCategory.ARCANE,
			"perks/physical_accurate",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 5)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_COMBAT.toWebHexString()+ ";'>Critical</span> spells apply <b style='color:"+Colour.ATTRIBUTE_LUST.toWebHexString()+";'>Arcane weakness</b>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spells are particularly effective when striking a target's weak spots."
					+ " Any critical hits from [npc.her] spells apply 'Arcane weakness' for one turn (-10 to all shielding).");
		}
	};
	
//	
//	TELEPATHY(60,
//			true,
//			"arcane telepathy",
//			PerkCategory.ARCANE,
//			"perks/misc_observant",
//			Colour.GENERIC_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)),
//			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Telepathic seduction</span>")) {
//		@Override
//		public String applyPerkGained(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String applyPerkLost(GameCharacter character) {
//			return UtilText.parsePlayerThought("");
//		}
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "By concentrating your arcane power into the mind of others, you're able to deliver a .";
//			} else {
//				return UtilText.parse(owner, "[npc.Name] is very perceptive, and [npc.she] continuously scans [npc.her] surroundings for signs of danger.");
//			}
//		}
//	};
	
//	SPELL_POWER_1(20,
//			true,
//			"arcane power",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_1",
//			Colour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 5)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused on improving your ability to harness the arcane and cast spells.";
//			else
//				return UtilText.parse(owner, "[npc.Name] seems reasonably competent at casting spells.");
//		}
//	};
//	SPELL_POWER_2(20,
//			true,
//			"arcane conduit",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_2",
//			Colour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 10)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer())
//				return "You have focused your ability to harness the arcane to the point where you can greatly enhance the effects of any spell.";
//			else
//				return UtilText.parse(owner, "[npc.Name] is highly competent at harnessing the arcane and improving [npc.her] spells.");
//		}
//	};
//	SPELL_POWER_3(20,
//			true,
//			"arcane mastery",
//			PerkCategory.ARCANE,
//			"perks/arcane_power_3",
//			Colour.ATTRIBUTE_ARCANE,
//			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 15)), null) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "You are a master at harnessing the arcane. Even if you didn't have an aura as strong as a demon's, you'd still be one of the greatest arcane users in Dominion.";
//			} else
//				return UtilText.parse(owner, "[npc.Name] is a master of harnessing the arcane and improving [npc.her] spells.");
//		}
//	};

	public static AbstractPerk FIRE_ENHANCEMENT = new AbstractPerk(20,
			false,
			"firebrand",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane fire. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane fire. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	};

	public static AbstractPerk FIRE_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"incendiary",
			PerkCategory.ARCANE,
			"perks/arcane_fire_1",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane fire. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane fire. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	};

	public static AbstractPerk COLD_ENHANCEMENT = new AbstractPerk(20,
			false,
			"frosty",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane ice. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane ice. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	};

	public static AbstractPerk COLD_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"ice cold",
			PerkCategory.ARCANE,
			"perks/arcane_ice_1",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane ice. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane ice. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	};

	public static AbstractPerk POISON_ENHANCEMENT = new AbstractPerk(20,
			false,
			"venomous",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane poison. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane poison. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	};

	public static AbstractPerk POISON_ENHANCEMENT_2 = new AbstractPerk(20,
			false,
			"toxic",
			PerkCategory.ARCANE,
			"perks/arcane_poison_1",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 10),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane poison. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane poison. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	};

	// Fitness:
	public static AbstractPerk RUNNER = new AbstractPerk(20,
			true,
			"runner",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 3)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Improved escape chance</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're a natural runner and possess a good level of stamina.";
			else
				return UtilText.parse(owner, "[npc.Name] is natural runner and possesses a good level of stamina.");
		}
	};

	public static AbstractPerk RUNNER_2 = new AbstractPerk(20,
			true,
			"cardio champion",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner_2",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 5)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Improved escape chance</span>")) {
		@Override
		public String getName(GameCharacter character) {
			if (character!=null && character.isFeminine()) {
				return "Cardio Queen";
			} else {
				return "Cardio King";
			}
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] the " + (owner.isFeminine() ? "queen" : "king") + " of cardio, possessing a seemingly endless reserve of energy.");
		}
	};

	public static AbstractPerk COMBAT_REGENERATION = new AbstractPerk(20,
			true,
			"combat regeneration",
			PerkCategory.PHYSICAL,
			"perks/regeneration",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, -25)),
			Util.newArrayListOfValues("[style.boldExcellent(Regenerate 5%)] total [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")] per turn in combat")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "At the expense of some of [npc.namePos] "+Attribute.MANA_MAXIMUM.getName()+", the arcane's natural healing properties will now be amplified when in the presence of [npc.her] adrenaline.");
		}
	};
	

	public static AbstractPerk UNARMED_TRAINING = new AbstractPerk(20,
			true,
			"martial artist",
			PerkCategory.PHYSICAL,
			"perks/unarmed_training",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("Base unarmed damage value is <span style='color:"+ Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>boosted to 8</span>")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] received formal training in martial arts, allowing [npc.herHim] to deal just as much damage in unarmed combat as [npc.her] strongest foe.");
		}
	};

	public static AbstractPerk FEMALE_ATTRACTION = new AbstractPerk(60,
			true,
			"ladykiller",
			PerkCategory.LUST,
			"perks/fitness_female_attraction",
			Colour.FEMININE,
			null, Util.newArrayListOfValues("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</span>"
					+ " vs <span style='color:" + Colour.FEMININE.toWebHexString()+ ";'>feminine opponents</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] very flirtatious, and although [npc.her] charms work well on both sexes, [npc.she] [npc.verb(find)] that [npc.she] [npc.verb(get)] more opportunities to seduce women than men.");
		}

	};

	public static AbstractPerk MALE_ATTRACTION = new AbstractPerk(60,
			true,
			"minx",
			PerkCategory.LUST,
			"perks/fitness_male_attraction",
			Colour.MASCULINE,
			null, Util.newArrayListOfValues("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</span>"
					+ " vs <span style='color:" + Colour.MASCULINE.toWebHexString()+ ";'>masculine opponents</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] quite a tease, and although [npc.her] charms work well on both sexes, [npc.she] [npc.verb(find)] that [npc.she] [npc.verb(get)] more opportunities to seduce men than women.");
		}
	};
	
	public static AbstractPerk CONVINCING_REQUESTS = new AbstractPerk(20,
			false,
			"irresistible appeals",
			PerkCategory.LUST,
			"perks/convincing_requests",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues("Requests during sex are no longer denied by sadists or rough dominants")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] mastered the art of sexual persuasion, and [npc.is] able to convince even the cruelest of dominant partners to do as [npc.she] [npc.verb(ask)].");
		}
	};
	
	public static AbstractPerk OBJECT_OF_DESIRE = new AbstractPerk(20,
			false,
			"object of desire",
			PerkCategory.LUST,
			"perks/object_of_desire",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1)),
			Util.newArrayListOfValues("All partners in sex require [style.colourSex(+1 orgasm)] before being satisfied")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] so astoundingly "+(owner.getFemininity()==Femininity.ANDROGYNOUS?"attractive":(owner.isFeminine()?"beautiful":"handsome"))
					+" that [npc.her] sexual partners can't help but keep on fucking well after they've had their first orgasm.");
		}
	};
	
	public static AbstractPerk NYMPHOMANIAC = new AbstractPerk(20,
			true,
			"nymphomaniac",
			PerkCategory.LUST,
			"perks/fitness_nymphomaniac",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, -2)),
			Util.newArrayListOfValues("Doubles <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString()+ ";'>arcane essence gain</span> from each orgasm")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are completely and hopelessly addicted to sex.";
			else
				return UtilText.parse(owner, "[npc.Name] is completely and hopelessly addicted to sex.");
		}
	};

	public static AbstractPerk LUSTPYRE = new AbstractPerk(20,
			true,
			"lustpyre",
			PerkCategory.LUST,
			"perks/lustful_leech",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Absorb 2%)] of target's maximum "+Attribute.MANA_MAXIMUM.getName()+" on each attack which deals [style.boldLust("+Attribute.DAMAGE_LUST.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "The arcane itself reacts to [npc.namePos] lustful advances, draining a small amount of [npc.her] enemy's "+Attribute.MANA_MAXIMUM.getName()+" each time [npc.she] teases them.");
		}
	};

	public static AbstractPerk PURE_MIND = new AbstractPerk(20,
			true,
			"pure thoughts",
			PerkCategory.LUST,
			"perks/pure_mind",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 2)),
			Util.newArrayListOfValues("[style.boldExcellent(Regain 2%)] of your maximum "+Attribute.MANA_MAXIMUM.getName()+" on each attack in which you take [style.boldLust("+Attribute.DAMAGE_LUST.getName()+")]")) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Steeling [npc.her] mind against any lustful advances, [npc.nameIsFull] able to enter a meditative state,"
					+ " reacting to any lust damage [npc.she] [npc.verb(receive)] by regenerating some of [npc.her] "+Attribute.MANA_MAXIMUM.getName()+".");
		}
	};
	
	
	public static AbstractPerk CLOTHING_ENCHANTER = new AbstractPerk(20,
			false,
			"arcane weaver",
			PerkCategory.ARCANE,
			"perks/arcaneWeaver",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Halves cost of all clothing enchantments</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] a natural affinity for weaving arcane enchantments into items of clothing, allowing [npc.herHim] to expend only half of the usual arcane essences when enchanting clothing.");
		}
	};
	
	public static AbstractPerk WEAPON_ENCHANTER = new AbstractPerk(20,
			false,
			"arcane smith",
			PerkCategory.PHYSICAL,
			"perks/arcaneSmith",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.ENCHANTMENT_LIMIT, 15)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Halves cost of all weapon enchantments</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] a natural affinity for imbuing weapons with arcane enchantments, allowing [npc.herHim] to expend only half of the usual arcane essences when enchanting weapons.");
		}
	};
	
	public static AbstractPerk ENCHANTMENT_STABILITY = new AbstractPerk(20,
			false,
			"stable enchantments",
			PerkCategory.ARCANE,
			"perks/enchantment_stability",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent time training [npc.her] body and mind in order to handle a higher "+Attribute.ENCHANTMENT_LIMIT.getName()+", allowing [npc.herHim] to equip more enchanted weapons, clothing, and tattoos.");
		}
	};

	public static AbstractPerk ENCHANTMENT_STABILITY_ALT = new AbstractPerk(20,
			false,
			"stable enchantments",
			PerkCategory.ARCANE,
			"perks/enchantment_stability",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.ENCHANTMENT_LIMIT, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent time training [npc.her] body and mind in order to handle a higher "+Attribute.ENCHANTMENT_LIMIT.getName()+", allowing [npc.herHim] to equip more enchanted weapons, clothing, and tattoos.");
		}
	};
	
	public static AbstractPerk BARREN = new AbstractPerk(20,
			true,
			"barren",
			PerkCategory.PHYSICAL,
			"perks/barren",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, -200)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are very infertile, and are highly unlikely to ever get pregnant.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is highly unlikely to get pregnant.");
			}
		}
	};
	
	public static AbstractPerk FIRING_BLANKS = new AbstractPerk(20,
			true,
			"sterile",
			PerkCategory.PHYSICAL,
			"perks/firing_blanks",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, -200)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] seed is incredibly weak, and [npc.sheIs] highly unlikely to ever get anyone pregnant.");
		}
	};
	

	
	public static AbstractPerk FETISH_BROODMOTHER = new AbstractPerk(20,
			true,
			"broodmother",
			PerkCategory.LUST,
			"fetishes/fetish_broodmother",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, 20)),
			Util.newArrayListOfValues("2x <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Maximum offspring in mothered litters</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your body is built for one thing; pumping out as many children as possible."
							+ " Whether due to an effect of your arcane aura, or perhaps just because of your body's natural fertility, you seem to always give birth to huge numbers of children at once.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] body is built for one thing; pumping out as many children as possible."
						+ " [npc.She] seems to always give birth to huge numbers of children at once.");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	public static AbstractPerk FETISH_SEEDER = new AbstractPerk(20,
			true,
			"seeder",
			PerkCategory.LUST,
			"fetishes/fetish_seeder",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, 20)),
			Util.newArrayListOfValues("2x <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Maximum offspring in fathered litters</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your seed has the potent effect of causing anyone impregnated by it to give birth to huge numbers of children.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] seed has the potent effect of causing anyone impregnated by it to give birth to huge numbers of children.");
			}
		}

		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	public static AbstractPerk CHUUNI = new AbstractPerk(20,
			true,
			"chuuni",
			PerkCategory.ARCANE,
			"perks/misc_chuuni",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 20),
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 20)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Embarrassing spell dialogue</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Roughly translated from Japanese as 'Middle School 2nd Year Syndrome', those with 'chuunibyou' believe and act as though they possess special powers."
					+ " While chuunis may once have been purely delusional, the arcane now lends some truth to their beliefs...");
		}
	};
	
	public static AbstractPerk SPECIAL_MERAXIS = new AbstractPerk(20,
			false,
			"The Dark Siren",
			PerkCategory.ARCANE,
			"perks/dark_siren",
			Colour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 25),
					new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 100),
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 100),
					new Value<Attribute, Integer>(Attribute.ENCHANTMENT_LIMIT, 100)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Meraxis is none other than the Dark Siren herself! Being the recognised daughter of the elder Lilin Lyssieth, she has considerable skill with wielding the arcane.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_ARCANE_TATTOOIST = new AbstractPerk(20,
			false,
			"Arcane Tattooist",
			PerkCategory.ARCANE,
			"perks/tattoo",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 15),
					new Value<Attribute, Integer>(Attribute.ENCHANTMENT_LIMIT, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] undergone extensive training in order to learn how to imbue tattoos with arcane enchantments.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CLOTHING_MASCULINITY_INDIFFERENCE = new AbstractPerk(20,
			false,
			"masculine clothing indifference",
			PerkCategory.PHYSICAL,
			"perks/clothingIndifferenceMasculinity",
			Util.newArrayListOfValues(Colour.MASCULINE, Colour.CLOTHING_GREY),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to 'clothing too masculine' status effect</span>"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] always [npc.verb(feel)] comfortable wearing masculine clothing, no matter how feminine [npc.her] body may be.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_CLOTHING_FEMININITY_INDIFFERENCE = new AbstractPerk(20,
			false,
			"feminine clothing indifference",
			PerkCategory.PHYSICAL,
			"perks/clothingIndifferenceFemininity",
			Util.newArrayListOfValues(Colour.FEMININE, Colour.CLOTHING_GREY),
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to 'clothing too feminine' status effect</span>"),
			null, null, null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] always [npc.verb(feel)] comfortable wearing feminine clothing, no matter how masculine [npc.her] body may be.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	// HIDDEN PERKS:

	public static AbstractPerk SPECIAL_DIRTY_MINDED = new AbstractPerk(20,
			false,
			"dirty-minded",
			PerkCategory.ARCANE,
			"statusEffects/attCorruption5",
			Colour.ATTRIBUTE_CORRUPTION,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_CORRUPTION, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] very dirty-minded, and often [npc.verb(find)] [npc.her] thoughts dwelling on sex.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_SLUT = new AbstractPerk(20,
			false,
			"slut",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			Colour.ATTRIBUTE_LUST,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 15),
					new Value<Attribute, Integer>(Attribute.MAJOR_CORRUPTION, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] had countless sexual partners, and [npc.has] performed all manner of lewd acts with them.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ARCANE_TRAINING = new AbstractPerk(20,
			false,
			"arcane training",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] undergone extensive training in order to learn how to harness the arcane.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_ARCANE_ALLERGY = new AbstractPerk(20,
			false,
			"arcane allergy",
			PerkCategory.ARCANE,
			"perks/arcane_allergy",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, -50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.Name] suffers from a rare allergic reaction to the arcane, and is therefore almost completely incapable of harnessing its power.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};

	public static AbstractPerk SPECIAL_HEALTH_FANATIC = new AbstractPerk(20,
			false,
			"health fanatic",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] obsessed with [npc.her] personal fitness, and spends hours every day working out and planning out [npc.her] diet.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk SPECIAL_MARTIAL_BACKGROUND = new AbstractPerk(20,
			false,
			"martial background",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameHasFull] spent a lot of time training [npc.her] body for combat, and as a result, [npc.sheIs] far stronger than the average person.");
		}
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
		@Override
		public boolean isBackgroundPerk() {
			return true;
		}
	};
	
	public static AbstractPerk IMP_SLAYER = new AbstractPerk(20,
			false,
			"doomguy",
			PerkCategory.ARCANE,
			"perks/imp_slayer",
			Colour.RACE_IMP,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_IMP, 100)),
			null) {

		@Override
		public String getName(GameCharacter owner) {
			if(owner!=null && owner.isFeminine()) {
				return "doomgirl";
			} else {
				return super.getName(owner);
			}
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "<i>It's the same old shit. People think they can fuck with me, and there's only one way to prove 'em wrong!</i>");
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LIRECEA_1 = new AbstractPerk(20,
			false,
			"Lirecea's Power",
			PerkCategory.ARCANE,
			"perks/lilin1",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldLightBlue(aquatic transformations)] if a [style.boldDemon(demon)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The essence of Lirecea's power has been infused into your arcane aura."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Her power has additionally enabled you to transform your demonic body into that of any aquatic species!"
							:" If you were a demon, this power would enable you to transform your body parts into those of any aquatic species!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LOVIENNE_2 = new AbstractPerk(21,
			false,
			"Lovienne's Power",
			PerkCategory.ARCANE,
			"perks/lilin2",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldHuman(human transformations)] if a [style.boldDemon(demon)]")) { //TODO

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The essence of Lyssieth's power has been infused into your arcane aura."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Her power has additionally enabled you to transform your demonic body into that of a regular human!"
							:" If you were a demon, this power would enable you to transform your body parts into those of a regular human!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	public static AbstractPerk POWER_OF_LASIELLE_3 = new AbstractPerk(22,
			false,
			"Lasielle's Power",
			PerkCategory.ARCANE,
			"perks/lilin3",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldHuman(human transformations)] if a [style.boldDemon(demon)]")) { //TODO

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The essence of Lyssieth's power has been infused into your arcane aura."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Her power has additionally enabled you to transform your demonic body into that of a regular human!"
							:" If you were a demon, this power would enable you to transform your body parts into those of a regular human!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LYSSIETH_4 = new AbstractPerk(23,
			false,
			"Lyssieth's Power",
			PerkCategory.ARCANE,
			"perks/lilin4",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues(
					"[style.boldExcellent(Unlocks)] [style.boldHuman(human transformations)] if a [style.boldDemon(demon)]",
					"[style.boldExcellent(Immunity)] to [style.boldArcane(Lilith's Command)]")) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The essence of Lyssieth's power has been infused into your arcane aura."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Her power has additionally enabled you to transform your demonic body into that of a regular human!"
							:" If you were a demon, this power would enable you to transform your body parts into those of a regular human!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LUNETTE_5 = new AbstractPerk(24,
			false,
			"Lunette's Power",
			PerkCategory.ARCANE,
			"perks/lilin5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldHuman(human transformations)] if a [style.boldDemon(demon)]")) { //TODO

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The essence of Lyssieth's power has been infused into your arcane aura."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Her power has additionally enabled you to transform your demonic body into that of a regular human!"
							:" If you were a demon, this power would enable you to transform your body parts into those of a regular human!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LYXIAS_6 = new AbstractPerk(25,
			false,
			"Lyxias's Power",
			PerkCategory.ARCANE,
			"perks/lilin6",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldHuman(human transformations)] if a [style.boldDemon(demon)]")) { //TODO

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The essence of Lyssieth's power has been infused into your arcane aura."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Her power has additionally enabled you to transform your demonic body into that of a regular human!"
							:" If you were a demon, this power would enable you to transform your body parts into those of a regular human!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};
	
	public static AbstractPerk POWER_OF_LISOPHIA_7 = new AbstractPerk(26,
			false,
			"Lisophia's Power",
			PerkCategory.ARCANE,
			"perks/lilin7",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			Util.newArrayListOfValues("[style.boldExcellent(Unlocks)] [style.boldHuman(human transformations)] if a [style.boldDemon(demon)]")) { //TODO

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner,
					"The essence of Lyssieth's power has been infused into your arcane aura."
					+ (owner.getSubspeciesOverride()==Subspecies.DEMON
							?" Her power has additionally enabled you to transform your demonic body into that of a regular human!"
							:" If you were a demon, this power would enable you to transform your body parts into those of a regular human!"));
		}
		
		@Override
		public boolean isHiddenPerk() {
			return true;
		}
	};

	

	// SPECIFIC TO ELEMENTAL PERK TREE:
	
	public static AbstractPerk ELEMENTAL_BOUND_EARTH = new AbstractPerk(20,
			true,
			"Bound to Earth",
			PerkCategory.JOB,
			"combat/spell/elemental_earth",
			Colour.SPELL_SCHOOL_EARTH,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 50),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 50),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By being bound to the school of Earth, [npc.name] has gained a strong, tough body that is extremely resilient to physical damage."
					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using physical attacks.");
		}
	};

	public static AbstractPerk ELEMENTAL_BOUND_FIRE = new AbstractPerk(20,
			true,
			"Bound to Fire",
			PerkCategory.JOB,
			"combat/spell/elemental_fire",
			Colour.SPELL_SCHOOL_FIRE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 50),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By being bound to the school of Fire, [npc.name] has gained an ethereal body that is extremely resilient to fire damage."
					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using fire-based attacks.");
		}
	};

	public static AbstractPerk ELEMENTAL_BOUND_WATER = new AbstractPerk(20,
			true,
			"Bound to Water",
			PerkCategory.JOB,
			"combat/spell/elemental_water",
			Colour.SPELL_SCHOOL_WATER,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 50),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By being bound to the school of Water, [npc.name] has gained "+(owner.getBodyMaterial()==BodyMaterial.WATER?"a liquid-like ":"an ice-like ")+"body that is extremely resilient to ice damage."
					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using ice-based attacks.");
		}
	};

	public static AbstractPerk ELEMENTAL_BOUND_AIR = new AbstractPerk(20,
			true,
			"Bound to Air",
			PerkCategory.JOB,
			"combat/spell/elemental_air",
			Colour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 50),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By being bound to the school of Air, [npc.name] has gained an ethereal body that is extremely resilient to poison damage."
					+ " As well as this, [npc.sheIs] now capable of inflicting great damage by using poison-based attacks.");
		}
	};

	public static AbstractPerk ELEMENTAL_BOUND_ARCANE = new AbstractPerk(20,
			true,
			"Bound to Arcane",
			PerkCategory.JOB,
			"combat/spell/elemental_arcane",
			Colour.SPELL_SCHOOL_AIR,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 50),
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 25),
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 25),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, -50)),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "By being bound to the school of Arcane, [npc.name] has gained an ethereal body that capable of inflicting great damage by using lust-based attacks."
					+ " [npc.She] has also become more adept at casting spells, but the arcane's arousing power has left [npc.herHim] more susceptible to lust-based attacks.");
		}
	};

	public static AbstractPerk ELEMENTAL_CORE = new AbstractPerk(20,
			false,
			"elemental",
			PerkCategory.LUST,
			"perks/elemental/core",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 50),
					new Value<Attribute, Integer>(Attribute.DAMAGE_SPELLS, 25),
					new Value<Attribute, Integer>(Attribute.SPELL_COST_MODIFIER, 100)
					), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "As beings of pure arcane energy, all elementals are very competent spell casters, and rival even the most powerful of demons in their ability to harness the arcane.");
		}
	};

	public static AbstractPerk ELEMENTAL_CORRUPTION = new AbstractPerk(20,
			false,
			"elemental",
			PerkCategory.LUST,
			"perks/elemental/coreCorruption",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_CORRUPTION, 100)
					), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Even if their summoner is completely pure and innocent, the lustful nature of the arcane causes all elementals to be incredibly perverted."
					+ " If nothing else, they can always be relied upon to be willing and ready to have sex with anyone or anything...");
		}
	};
	
	// ELEMENTAL FIRE

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] will be able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FIREBALL,
			SpellUpgrade.FIREBALL_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] will be able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.FLASH,
			SpellUpgrade.FLASH_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			null,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] will be able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_1,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_2,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_FIRE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_FIRE,
			null,
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.CLOAK_OF_FLAMES,
			SpellUpgrade.CLOAK_OF_FLAMES_3,
			SpellSchool.FIRE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_MINOR = new AbstractPerk(20,
			false,
			"ignition",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire1",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 1),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "If instructed to focus on [npc.her] ability at harnessing and taking the form of arcane fire, [npc.name] could take the first step towards unlocking the next secret of the school of arcane Fire.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST = new AbstractPerk(20,
			false,
			"ablaze",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire2",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 3),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] continuing to pursue the path of a fire elemental, and,"
					+ " while [npc.she] still has a way to go before reaching a new milestone, [npc.her] affinity with arcane fire is steadily increasing.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"conflagration",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire3",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 6),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] affinity with arcane fire has massively increased, and [npc.sheIs] on the verge of discovering something incredibly powerful!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_FIRE_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"incineration",
			PerkCategory.ARCANE_FIRE,
			"perks/elemental/fire4",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 20),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 20),
					new Value<Attribute, Integer>(Attribute.CRITICAL_DAMAGE, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Burning with the beautiful and terrifying power of the sun itself, one strike from [npc.name] is typically all it takes to incapacitate anyone unfortunate enough to incur [npc.her] wrath.");
		}
	};
	
	// ELEMENTAL EARTH

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SLAM,
			SpellUpgrade.SLAM_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEKENETIC_SHOWER,
			SpellUpgrade.TELEKENETIC_SHOWER_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			null,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_1,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_2,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_EARTH,
			null,
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.STONE_SHELL,
			SpellUpgrade.STONE_SHELL_3,
			SpellSchool.EARTH) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_EARTH_BOOST_MINOR = new AbstractPerk(20,
			false,
			"impact",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth1",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 1),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "If instructed to focus on [npc.her] ability to create powerful physical manifestations, [npc.name] could take the first step towards unlocking the next secret of the school of arcane Earth.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST = new AbstractPerk(20,
			false,
			"building pressure",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth2",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 3),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] continuing to pursue the path of an earth elemental, and,"
					+ " while [npc.she] still has a way to go before reaching a new milestone, [npc.her] affinity with arcane force is steadily increasing.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"seismic activity",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth3",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 6),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] affinity with arcane force has massively increased, and [npc.sheIs] on the verge of discovering something incredibly powerful!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_EARTH_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"epicentre",
			PerkCategory.PHYSICAL_EARTH,
			"perks/elemental/earth4",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 20),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_UNARMED, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Taking on a nigh-invincible form, [npc.name] is now able to shrug off almost any attack, which allows [npc.herHim] to get close to [npc.her] enemies and deliver a knock-out punch or kick.");
		}
	};
	
	// ELEMENTAL WATER

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ICE_SHARD,
			SpellUpgrade.ICE_SHARD_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_SPELL_2 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.RAIN_CLOUD,
			SpellUpgrade.RAIN_CLOUD_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			null,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_1,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_2,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.PHYSICAL_WATER,
			null,
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.SOOTHING_WATERS,
			SpellUpgrade.SOOTHING_WATERS_3,
			SpellSchool.WATER) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_WATER_BOOST_MINOR = new AbstractPerk(20,
			false,
			"chill",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water1",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 1),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "If instructed to focus on [npc.her] ability at harnessing and taking the form of arcane ice, [npc.name] could take the first step towards unlocking the next secret of the school of arcane Water.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST = new AbstractPerk(20,
			false,
			"frost",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water2",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 3),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] continuing to pursue the path of a water elemental, and,"
					+ " while [npc.she] still has a way to go before reaching a new milestone, [npc.her] affinity with arcane ice is steadily increasing.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"freeze",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water3",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 6),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] affinity with arcane ice has massively increased, and [npc.sheIs] on the verge of discovering something incredibly powerful!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_WATER_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"ice-age",
			PerkCategory.PHYSICAL_WATER,
			"perks/elemental/water4",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 20),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_MELEE_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Gliding from place to place with an ethereal grace, [npc.name] is able to shift and reform [npc.her] body in an instant, enabling [npc.herHim] to perform the most impossible of physical feats.");
		}
	};
	
	// ELEMENTAL AIR

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.POISON_VAPOURS,
			SpellUpgrade.POISON_VAPOURS_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.VACUUM,
			SpellUpgrade.VACUUM_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			null,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_1,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_2,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.ARCANE_AIR,
			null,
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.PROTECTIVE_GUSTS,
			SpellUpgrade.PROTECTIVE_GUSTS_3,
			SpellSchool.AIR) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_AIR_BOOST_MINOR = new AbstractPerk(20,
			false,
			"breeze",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air1",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 1),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "If instructed to focus on [npc.her] ability at harnessing the power of arcane poison, [npc.name] could take the first step towards unlocking the next secret of the school of arcane Air.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST = new AbstractPerk(20,
			false,
			"gale",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air2",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 3),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] continuing to pursue the path of an air elemental, and,"
					+ " while [npc.she] still has a way to go before reaching a new milestone, [npc.her] affinity with arcane poison is steadily increasing.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"storm",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air3",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 6),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] affinity with arcane poison has massively increased, and [npc.sheIs] on the verge of discovering something incredibly powerful!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_AIR_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"supercell",
			PerkCategory.ARCANE_AIR,
			"perks/elemental/air4",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 20),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 20),
					new Value<Attribute, Integer>(Attribute.DAMAGE_RANGED_WEAPON, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Any enemy foolish enough to draw [npc.namePos] ire soon finds themselves reaping the whirlwind,"
					+ " with each of [npc.her] devastating missiles landing perfectly on-target thanks to the guiding gusts that [npc.she] summons.");
		}
	};
	
	// ELEMENTAL ARCANE

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_1_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_AROUSAL,
			SpellUpgrade.ARCANE_AROUSAL_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_2_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.TELEPATHIC_COMMUNICATION,
			SpellUpgrade.TELEPATHIC_COMMUNICATION_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3 = new AbstractPerk(20,
			false,
			"Spell",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			null,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Spell: "+getSpell().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Gain spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " when bound to <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSchool().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "When bound to the school of "+getSchool().getName()+", [npc.name] is able to use the spell '"+getSpell().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpell().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_1 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_1,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_2 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_2,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_SPELL_3_3 = new AbstractPerk(20,
			false,
			"Upgrade",
			PerkCategory.LUST,
			null,
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(),
			Util.newArrayListOfValues(""),
			Spell.ARCANE_CLOUD,
			SpellUpgrade.ARCANE_CLOUD_3,
			SpellSchool.ARCANE) {
		
		@Override
		public String getName(GameCharacter owner) {
			return "Upgrade: "+getSpellUpgrade().getName();
		}

		@Override
		public List<String> getExtraEffects() {
			return Util.newArrayListOfValues("Upgrades spell '<span style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>"+getSpell().getName()+"</span>'"
					+ " with <span style='color:"+ getSchool().getColour().toWebHexString()+ ";'>"+getSpellUpgrade().getName()+"</span>");
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] spell, "+getSpell().getName()+", will be upgraded to have the extra power '"+getSpellUpgrade().getName()+"'.");
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return getSpellUpgrade().getSVGString();
		}
	};

	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_MINOR = new AbstractPerk(20,
			false,
			"arousal",
			PerkCategory.LUST,
			"perks/elemental/arcane1",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "If instructed to focus on [npc.her] ability at harnessing the pure power of the arcane, [npc.name] could take the first step towards unlocking the next secret of the school of the Arcane.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST = new AbstractPerk(20,
			false,
			"passion",
			PerkCategory.LUST,
			"perks/elemental/arcane2",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 3),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NameIsFull] continuing to pursue the path of an arcane elemental, and,"
					+ " while [npc.she] still has a way to go before reaching a new milestone, [npc.her] affinity with arcane lust is steadily increasing.");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_MAJOR = new AbstractPerk(20,
			false,
			"infatuation",
			PerkCategory.LUST,
			"perks/elemental/arcane3",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 6),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 6)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "[npc.NamePos] affinity with the arcane has massively increased, and [npc.sheIs] on the verge of discovering something incredibly powerful!");
		}
	};
	
	public static AbstractPerk ELEMENTAL_ARCANE_BOOST_ULTIMATE = new AbstractPerk(20,
			false,
			"nympholepsy",
			PerkCategory.LUST,
			"perks/elemental/arcane4",
			Colour.DAMAGE_TYPE_LUST,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 20),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 20),
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return UtilText.parse(owner, "Those who look upon [npc.namePos] "+(owner.isFeminine()?"gorgeous body and stunning":"chiselled body and handsome")
					+" face find themselves falling under [npc.her] spell, and within moments, are completely consumed by a wild, animalistic lust.");
		}
	};
	
	

	public static List<AbstractPerk> hiddenPerks;
	public static List<AbstractPerk> allPerks;
	
	public static Map<AbstractPerk, String> perkToIdMap = new HashMap<>();
	public static Map<String, AbstractPerk> idToPerkMap = new HashMap<>();
	
	public static AbstractPerk getPerkFromId(String id) {
//		System.out.print("ID: "+id);
		if(id.equalsIgnoreCase("MERAXIS")
				|| id.equalsIgnoreCase("ARCANE_TATTOOIST")
				|| id.equalsIgnoreCase("SLUT")
				|| id.equalsIgnoreCase("ARCANE_TRAINING")
				|| id.equalsIgnoreCase("ARCANE_ALLERGY")
				|| id.equalsIgnoreCase("HEALTH_FANATIC")
				|| id.equalsIgnoreCase("MARTIAL_BACKGROUND")) {
			id = "SPECIAL_"+id;
		}
		
		id = Util.getClosestStringMatch(id, idToPerkMap.keySet());
//		System.out.println("  set to: "+id);
		return idToPerkMap.get(id);
	}
	
	public static String getIdFromPerk(AbstractPerk perk) {
		return perkToIdMap.get(perk);
	}

	static {
		hiddenPerks = new ArrayList<>();
		allPerks = new ArrayList<>();
		
		Field[] fields = Perk.class.getFields();
		
		for(Field f : fields){
			if (AbstractPerk.class.isAssignableFrom(f.getType())) {
				
				AbstractPerk perk;
				
				try {
					perk = ((AbstractPerk) f.get(null));

					// I feel like this is stupid :thinking:
					perkToIdMap.put(perk, f.getName());
					idToPerkMap.put(f.getName(), perk);
					
					allPerks.add(perk);
					if(perk.isHiddenPerk()) {
						hiddenPerks.add(perk);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		List<Attribute> resistancesAdded = new ArrayList<>();
		for(Subspecies sub : Subspecies.values()) {
			if(!resistancesAdded.contains(sub.getDamageMultiplier())) {
				resistancesAdded.add(sub.getDamageMultiplier());
				Subspecies subToUse = sub.getDamageMultiplier()==Subspecies.getMainSubspeciesOfRace(sub.getRace()).getDamageMultiplier()?Subspecies.getMainSubspeciesOfRace(sub.getRace()):sub;
				
				AbstractPerk racePerk = new AbstractPerk(20,
						false,
						Util.capitaliseSentence(subToUse.getName(null))+" knowledge",
						PerkCategory.LUST,
						null,
						Colour.BASE_WHITE,
						Util.newHashMapOfValues(
								new Value<Attribute, Integer>(subToUse.getDamageMultiplier(), 10)),
						null) {
					@Override
					public String getDescription(GameCharacter owner) {
						return UtilText.parse(owner, "[npc.NameHasFull] advanced knowledge of "+subToUse.getNamePlural(null)+", and can therefore do increased damage when fighting them.");
					}
					@Override
					public String getSVGString(GameCharacter owner) {
						return subToUse.getSVGString(null);
					}
					@Override
					public Colour getColour() {
						return subToUse.getColour(null);
					}
					@Override
					public boolean isHiddenPerk() {
						return true;
					}
				};
//				System.out.println(subToUse.toString()+" "+racePerk.getName(null)+" "+racePerk.hashCode());
				perkToIdMap.put(racePerk, subToUse.toString());
				idToPerkMap.put(subToUse.toString(), racePerk);
				allPerks.add(racePerk);
				hiddenPerks.add(racePerk);
			}
		}
		
		hiddenPerks.sort((p1, p2) -> p1.getRenderingPriority()-p2.getRenderingPriority());
	}
	
	public static AbstractPerk getSubspeciesRelatedPerk(Subspecies subspecies) {
		Subspecies subToUse = 
				subspecies.getDamageMultiplier()==Subspecies.getMainSubspeciesOfRace(subspecies.getRace()).getDamageMultiplier()
					?Subspecies.getMainSubspeciesOfRace(subspecies.getRace())
					:subspecies;
		
		return Perk.getPerkFromId(subToUse.toString());
	}
	
	public static List<AbstractPerk> getAllPerks() {
		return allPerks;
	}
	
	public static List<AbstractPerk> getHiddenPerks() {
		return hiddenPerks;
	}
}
