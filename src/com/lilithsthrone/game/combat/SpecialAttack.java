package com.lilithsthrone.game.combat;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.effects.Fetish;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.89
 * @author Innoxia
 */
public enum SpecialAttack {

	// Special tease attacks from fetishes:
	TEASE_ANAL_RECEIVING(50,
			"buttslut tease",
			"fetish_generic",
			Colour.GENERIC_ARCANE,
			DamageType.MANA,
			DamageLevel.NORMAL,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"You turn around, presenting your [pc.ass+] to [npc.name] before giving it a slap and [pc.moaning], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(You want to come have a squeeze?)]"
									:"[pc.speech(I feel so empty! My asshole's crying out for your cock!)]"),
								
						"You turn around, squeezing and groping your [pc.ass+] as you [pc.moan], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Why don't you come and have a go?)]"
									:"[pc.speech(My asshole needs cock!)]"),
								
						"You turn around, using both hands to grope and spread apart your ass cheeks as you [pc.moan], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Come and play!)]"
									:"[pc.speech(Please, I want your cock in my ass!)]"),
										
						"You turn around, spreading apart your ass cheeks with both hands as you [pc.moan], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(You know you want a go!)]"
									:"[pc.speech(My slutty asshole <i>needs</i> your cock!)]")));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"[npc.Name] turns around, presenting [npc.her] [npc.ass+] to you before giving it a slap and [npc.moaning], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You want to come have a squeeze?)]"
									:"[npc.speech(I feel so empty! My asshole's crying out for your cock!)]"),
								
						"[npc.Name] turns around, squeezing and groping [npc.her] [npc.ass+] as [npc.she] [npc.moansVerb], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Why don't you come and have a go?)]"
									:"[npc.speech(My asshole needs cock!)]"),
								
						"[npc.Name] turns around, using both hands to grope and spread apart [npc.her] ass cheeks as [npc.she] [pc.moans], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Come and play!)]"
									:"[npc.speech(Please, I want your cock in my ass!)]"),
								
						"[npc.Name] turns around, spreading apart [npc.her] ass cheeks with both hands as [npc.she] [npc.moans], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You know you want a go!)]"
									:"[npc.speech(My slutty asshole <i>needs</i> your cock!)]")));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_ANAL_GIVING, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your "+Fetish.FETISH_ANAL_RECEIVING.getName(owner)+" fetish, you're able to use your ass as a tool to seduce your opponents.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use [npc.her] ass in an attempt to seduce you!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_ANAL_RECEIVING);
		}
	},
	
	TEASE_ANAL_GIVING(50,
			"anal tease",
			"fetish_generic",
			Colour.GENERIC_ARCANE,
			DamageType.MANA,
			DamageLevel.NORMAL,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"You grin at [npc.name], before moving your gaze down to [npc.her] [npc.ass+] and [pc.moaning],"
							+" [pc.speech(Your ass looks like it needs a good fuck!)]",

						"You hungrily stare at [npc.name]'s [npc.ass+], [pc.moaning],"
							+" [pc.speech(I'm going to fuck that ass so hard!)]",

						"Gazing lustfully at [npc.name]'s [npc.ass+], you let out [pc.a_moan+],"
							+" [pc.speech(I'm going to pound that sweet ass into the ground!)]"));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"[npc.Name] grins at you, before moving [npc.her] gaze down to your [pc.ass+] and [npc.moaning],"
							+" [npc.speech(Your ass looks like it needs a good fuck!)]",

						"[npc.Name] hungrily stares at your [pc.ass+], [npc.moaning],"
							+" [npc.speech(I'm going to fuck that ass so hard!)]",

						"Gazing lustfully at your [pc.ass+], [npc.name] lets out [npc.a_moan+],"
							+" [npc.speech(I'm going to pound that sweet ass into the ground!)]"));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_ANAL_RECEIVING, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your "+Fetish.FETISH_ANAL_GIVING.getName(owner)+" fetish, you're able to seduce your opponents by telling them how you're going to use their ass.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to tell you how [npc.she]'s going to use your ass in an attempt to seduce you!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_ANAL_GIVING);
		}
	},
	
	TEASE_CUM_STUD(50,
			"cum stud tease",
			"fetish_generic",
			Colour.GENERIC_ARCANE,
			DamageType.MANA,
			DamageLevel.NORMAL,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"You grin at [npc.name], before thrusting your [pc.hips] out at [npc.herHim], "
								+ "[pc.speech(I can't wait to fill you with my [pc.cum]!)]",

						"Running your [pc.hands] down over your groin, you call out to [npc.name], "
								+ "[pc.speech(I bet you can't wait to taste my [pc.cum]!)]",

						"You let out an eager groan as you thrust your [pc.hips] at [npc.name], "
								+ "[pc.speech(I can't wait to fill you with my [pc.cum]!)]"));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"[npc.Name] grins at you, before thrusting [npc.her] [npc.hips] and calling out, "
								+ "[npc.speech(I can't wait to fill you with my [npc.cum]!)]",

						"Running [npc.her] [npc.hands] down over [npc.her] groin, [npc.name] calls out to you, "
								+ "[npc.speech(I bet you can't wait to taste my [npc.cum]!)]",

						"[npc.Name] lets out an eager groan as [npc.she] thrusts [npc.her] [npc.hips] at you, "
								+ "[npc.speech(I can't wait to fill you with my [npc.cum]!)]"));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_CUM_ADDICT, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your cum stud fetish, you're able to offer your cum as a way to seduce your opponents.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to offer [npc.her] cum in an attempt to seduce you!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_CUM_STUD) && owner.hasPenis();
		}
	},
	
	TEASE_CUM_ADDICT(50,
			"cum tease",
			"fetish_generic",
			Colour.GENERIC_ARCANE,
			DamageType.MANA,
			DamageLevel.NORMAL,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"You let out a desperate moan as you suddenly realise that your mouth isn't full of cum, "
								+ "[pc.speech(Aah! I need cum! I haven't had a meal in ages!)]",

						"Opening wide to show [npc.name] how empty your mouth is, you cry out, "
								+ "[pc.speech(Mmm! I need cum!)]",

						"You let out a pathetic whine as you realise that you haven't had any cum in a while, "
								+ "[pc.speech(I'm so hungry! I need cum!)]",

						"You let out a pathetic whine as you beg for a meal, "
								+ "[pc.speech(Please! I need some cum!)]"));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.a_moan+] as [npc.she] suddenly realises that [npc.her] mouth isn't full of cum, "
								+ "[npc.speech(Aah! I need cum! I haven't had a meal in ages!)]",

						"Opening wide to show you how empty [npc.her] mouth is, [npc.name] cries out, "
								+ "[npc.speech(Mmm! I need cum!)]",

						"[npc.Name] lets out a pathetic whine as [npc.she] realises that [npc.she] hasn't had any cum in a while, "
								+ "[npc.speech(I'm so hungry! I need cum!)]",

						"[npc.Name] lets out a pathetic whine as [npc.she] begs for a meal, "
								+ "[npc.speech(Please! I need some cum!)]"));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_CUM_STUD, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your cum addict fetish, you're able to beg for cum as a way to seduce your opponents.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to beg for cum in an attempt to seduce you!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_CUM_ADDICT);
		}
	},
	
	TEASE_ORAL_RECEIVING(50,
			"oral tease",
			"fetish_generic",
			Colour.GENERIC_ARCANE,
			DamageType.MANA,
			DamageLevel.NORMAL,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"You grin at [npc.name], gazing at [npc.her] [npc.lips+] as you [pc.moanVerb],"
							+" [pc.speech(I can't wait to put your [npc.lips] to use!)]",

						"You hungrily stare at [npc.name]'s [npc.lips+], [pc.moaning],"
							+" [pc.speech(Your tongue belongs between my [pc.legs]!)]",

						"Gazing lustfully at [npc.name]'s [npc.lips+], you let out [pc.a_moan+],"
							+" [pc.speech(I'm going to put your [npc.lips] to good use!)]"));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"[npc.Name] grins at you, gazing at your [pc.lips+] as [npc.she] [npc.moanVerb],"
							+" [npc.speech(I can't wait to put your [pc.lips] to use!)]",

						"[npc.Name] hungrily stares at your [pc.lips+], [npc.moaning],"
							+" [npc.speech(Your tongue belongs between my [npc.legs]!)]",

						"Gazing lustfully at your [pc.lips+], [npc.name] lets out [npc.a_moan+],"
							+" [npc.speech(I'm going to put your [pc.lips] to good use!)]"));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_ORAL_GIVING, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your "+Fetish.FETISH_ORAL_RECEIVING.getName(owner)+" fetish, you're able to use a special tease attack!";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use a special "+Fetish.FETISH_ORAL_RECEIVING.getName(owner)+" tease attack!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_ORAL_RECEIVING);
		}
	},
	
	TEASE_ORAL_GIVING(50, "oral performer tease", "fetish_generic", Colour.GENERIC_ARCANE,
			DamageType.MANA, DamageLevel.NORMAL, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"You open your mouth, sticking out your [pc.tongue] and making a suggestive gesture with one of your [pc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(You know, I'm pretty skilled with my tongue!)]"
									:"[pc.speech(Want to find out how deep I can take it?)]"),

						"You open your mouth, sticking out your [pc.tongue] and making a suggestive gesture with one of your [pc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(You'd love the feeling of my tongue!)]"
									:"[pc.speech(Want to stick your cock down my throat?)]"),

						"You open your mouth, sticking out your [pc.tongue] and making a suggestive gesture with one of your [pc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'm the best at eating girls out! Want to see?)]"
									:"[pc.speech(I'm the best at blowjobs! Want to see?)]"),

						"You open your mouth, sticking out your [pc.tongue] and making a suggestive gesture with one of your [pc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(You know you want to feel my tongue!)]"
									:"[pc.speech(You know you want me to suck your cock!)]")));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"[npc.Name] opens [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You know, I'm pretty skilled with my tongue!)]"
									:"[npc.speech(Want to find out how deep I can take it?)]"),

						"[npc.Name] opens [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You'd love the feeling of my tongue!)]"
									:"[npc.speech(Want to stick your cock down my throat?)]"),

						"[npc.Name] opens [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(I'm the best at eating girls out! Want to see?)]"
									:"[npc.speech(I'm the best at blowjobs! Want to see?)]"),

						"[npc.Name] opens [npc.her] mouth, sticking out [npc.her] [npc.tongue] and making a suggestive gesture with one of [npc.her] [npc.hands], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You know you want to feel my tongue!)]"
									:"[npc.speech(You know you want me to suck your cock!)]")));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_ORAL_RECEIVING, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your "+Fetish.FETISH_ORAL_GIVING.getName(owner)+" fetish, you're able to use a special tease attack!";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use a special "+Fetish.FETISH_ORAL_GIVING.getName(owner)+" tease attack!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_ORAL_GIVING);
		}
	},
	
	TEASE_BREASTS_OTHERS(50,
			"breasts lover tease",
			"fetish_generic",
			Colour.GENERIC_ARCANE,
			DamageType.MANA,
			DamageLevel.NORMAL,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				if(target.hasBreasts()) {
					attackText = (UtilText.returnStringAtRandom(
							"You grin at [npc.name], gazing at [npc.her] [npc.breasts+] as you [pc.moanVerb],"
								+" [pc.speech(I can't wait to get my [pc.hands] on your [npc.breasts]!)]",
	
							"You hungrily stare at [npc.name]'s [npc.breasts+], [pc.moaning],"
								+" [pc.speech(I'm going to have fun playing with those!)]",
	
							"Gazing lustfully at [npc.name]'s [npc.breasts+], you let out [pc.a_moan+],"
								+" [pc.speech(I'm going to have fun those [npc.breasts+] of yours!)]"));
					
				} else { //TODO
					attackText = "Gazing at [npc.name]'s chest, you let out an annoyed huff,"
									+ " [pc.speech(I wish you had a nice pair of tits that I could use!)]";
				}
				
			} else {
				if(target.hasBreasts()) {
					attackText = (UtilText.returnStringAtRandom(
							"[npc.Name] grins at you, gazing at your [pc.breasts+] as [npc.she] [npc.moanVerb],"
								+" [npc.speech(I can't wait to put your [pc.lips] to use!)]",
	
							"[npc.Name] hungrily stares at your [pc.breasts+], [npc.moaning],"
								+" [npc.speech(Your going to have fun playing with those!)]",
	
							"Gazing lustfully at your [pc.breasts+], [npc.name] lets out [npc.a_moan+],"
									+" [npc.speech(I'm going to have fun those [pc.breasts+] of yours!)]"));
					
				} else { //TODO
					attackText = "Gazing at your chest, [npc.name] lets out an annoyed huff,"
									+ " [npc.speech(I wish you had a nice pair of tits that I could use!)]";
				}
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_BREASTS_SELF, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your "+Fetish.FETISH_BREASTS_OTHERS.getName(owner)+" fetish, you're able to use a special tease attack!";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use a special "+Fetish.FETISH_BREASTS_OTHERS.getName(owner)+" tease attack!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_BREASTS_OTHERS)
					&& (!Main.game.isInCombat()
						|| (owner.isPlayer()
							? Combat.getOpponent().hasBreasts()
							: Main.game.getPlayer().hasBreasts()));
		}
	},
	
	TEASE_BREASTS(50, "breasts tease", "fetish_generic", Colour.GENERIC_ARCANE,
			DamageType.MANA, DamageLevel.NORMAL, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"Pushing your [pc.breasts+] together, you lean forwards and wink at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Let's play together!)]"
									:"[pc.speech(Come on, I'll let you have a squeeze!)]"),

						"Pushing your [pc.breasts+] breasts together, you lean forwards and wink at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Want to have some fun?)]"
									:"[pc.speech(Come on, you know you want a feel!)]"),

						"Running your hands suggestively over your [pc.breasts+], you bite your lip before pouting at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Don't you want to come and play?)]"
									:"[pc.speech(I bet you're dying for a touch!)]"),

						"Running your hands suggestively over your [pc.breasts+], you bite your lip before pouting at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Come on! Let's have some fun!)]"
									:"[pc.speech(~Aah!~ My nipples are so hard!)]")));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"Pushing [npc.her] [npc.breasts+] together, [npc.name] leans forwards and winks at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Let's play together!)]"
									:"[npc.speech(Come on, I'll let you have a squeeze!)]"),

						"Pushing [npc.her] [npc.breasts+] together, [npc.name] leans forwards and winks at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Want to have some fun?)]"
									:"[npc.speech(Come on, you know you want a feel!)]"),

						"Running [npc.her] [npc.hands] suggestively over [npc.her] [npc.breasts+], [npc.name] bites [npc.her] lip before pouting at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Don't you want to come and play?)]"
									:"[npc.speech(I bet you're dying for a touch!)]"),

						"Running [npc.her] [npc.hands] suggestively over [npc.her] [npc.breasts+], [npc.name] bites [npc.her] lip before pouting at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(Come on! Let's have some fun!)]"
									:"[npc.speech(~Aah!~ My nipples are so hard!)]")));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_BREASTS_OTHERS, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your breast fetish, you're able to use a special tease attack!";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use a special tease attack by using [npc.her] breasts!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_BREASTS_SELF) && owner.hasBreasts();
		}
	},
	
	TEASE_FERTILITY(50, "fertility tease", "fetish_generic", Colour.GENERIC_ARCANE,
			DamageType.MANA, DamageLevel.NORMAL, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				if(caster.isVisiblyPregnant()) {
					attackText = (UtilText.returnStringAtRandom(
							"Running your [pc.hands] over your pregnant belly, you wink at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Let me tell you the best ways to get knocked up!)]"
										:"[pc.speech(Ever wanted to fuck a pregnant [pc.girl]?)]"),

							"You push out your pregnant belly, giggling at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(I'll tell you all about how I got knocked up!)]"
										:"[pc.speech(Fucking pregnant [pc.girl]s is the best thing ever! Come on, I'll show you!)]"),

							"Sliding your [pc.hands] over your pregnant bump, you pout at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Come have a feel!)]"
										:"[pc.speech(Pregnant [pc.girl]s are the the best fucks around!)]"),

							"Posturing so as to draw attention to your pregnant bump, you bite your lip at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Come on, why don't we have some fun?!)]"
										:"[pc.speech(Want to find out how good it feels to fuck a pregnant [pc.girl]?)]")));
					
				} else {
					attackText = (UtilText.returnStringAtRandom(
							"Rubbing your [pc.hands] over your flat stomach, you bite your lip at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Aww... I wish I were pregnant so bad!)]"
										:"[pc.speech(I need to get pregnant so bad! Come fill me your cum already!)]"),

							"Sliding your [pc.hands] over your flat stomach, you pout at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Aww... I wish I were pregnant...)]"
										:"[pc.speech(Come put some kids in my belly already!)]"),

							"Sliding your [pc.hands] over your flat abdomen, you pout at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(I wish I were pregnant...)]"
										:"[pc.speech(Come fill my womb your yummy cum! I want to get pregnant already!)]"),

							"Sliding your [pc.hands] over your flat abdomen, you bite your lip at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(I wish I were pregnant...)]"
										:"[pc.speech(Come breed me already! My womb's waiting for your seed!)]")));
				}
				
			} else {
				if(caster.isVisiblyPregnant()) {
					attackText = (UtilText.returnStringAtRandom(
							"Running [npc.her] [npc.hands] over [npc.her] pregnant belly, [npc.name] winks at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Let me tell you the best ways to get knocked up!)]"
										:"[npc.speech(Ever wanted to fuck a pregnant [npc.girl]?)]"),

							"[npc.Name] pushes out [npc.her] pregnant belly and giggles at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(I'll tell you all about how I got knocked up!)]"
										:"[npc.speech(Fucking pregnant [npc.girl]s is the best thing ever! Come on, I'll show you!)]"),

							"Sliding [npc.her] [npc.hands] over [npc.her] pregnant bump, [npc.name] pouts at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Come have a feel!)]"
										:"[npc.speech(Pregnant [npc.girl]s are the the best fucks around!)]"),

							"Posturing so as to draw attention to [npc.her] pregnant bump, [npc.name] bites [npc.her] lip at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Come on, why don't we have some fun?!)]"
										:"[npc.speech(Want to find out how good it feels to fuck a pregnant [npc.girl]?)]")));
					
				} else {
					attackText = (UtilText.returnStringAtRandom(
							"Rubbing [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] bites [npc.her] [npc.lip] at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Aww... I wish I were pregnant so bad!)]"
										:"[npc.speech(I need to get pregnant so bad! Come fill me your cum already!)]"),

							"Sliding [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] pouts at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Aww... I wish I were pregnant...)]"
										:"[npc.speech(Come put some kids in my belly already!)]"),

							"Sliding [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] pouts at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(I wish I were pregnant...)]"
										:"[npc.speech(Come fill my womb your yummy cum! I want to get pregnant already!)]"),

							"Rubbing [npc.her] [npc.hands] over [npc.her] flat stomach, [npc.name] bites [npc.her] [npc.lip] at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(I wish I were pregnant...)]"
										:"[npc.speech(Come breed me already! My womb's waiting for your seed!)]")));
				}
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_IMPREGNATION, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your pregnancy fetish, you're able to use a special tease attack!";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use a special tease attack due to [npc.her] pregnancy fetish!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_PREGNANCY) && owner.hasVagina();
		}
	},
	
	TEASE_VIRILITY(50, "virility tease", "fetish_generic", Colour.GENERIC_ARCANE,
			DamageType.MANA, DamageLevel.NORMAL, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				if(target.isVisiblyPregnant()) {
					attackText = (UtilText.returnStringAtRandom(
							"Reaching down to grab your crotch, you wink at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Shame you're already pregnant... But that won't stop me from filling your pussy with my [pc.cum+]!)]"
										:"[pc.speech(How many girls have you knocked up recently?)]"),

							"Running your hands down over your crotch, you wink at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(You may already be pregnant, but that won't stop me from giving you a nice creampie!)]"
										:"[pc.speech(How many girls have you got pregnant recently?)]"),

							"Sliding your hands down to draw attention to your crotch, you grin at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Don't think that being pregnant will stop me from filling your cunt with my [pc.cum+]!)]"
										:"[pc.speech(Got many girls pregnant recently?)]"),

							"Reaching down to grab your crotch, you grin at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(It's a shame you're already pregnant... But I'm still going to fill you up with my [pc.cum+]!)]"
										:"[pc.speech(Got many girls knocked up recently?)]")));
					
				} else {
					attackText = (UtilText.returnStringAtRandom(
							"Reaching down to grab your crotch, you wink at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(Want to get knocked up? My cum's crying out to fill your womb!)]"
										:"[pc.speech(How many girls have you knocked up recently?)]"),

							"Running your hands down over your crotch, you wink at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(My seed's so potent, I'm going to knock you up on the first fuck!)]"
										:"[pc.speech(How many girls have you got pregnant recently?)]"),

							"Sliding your hands down to draw attention to your crotch, you grin at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(My seed's so potent, you're going to get knocked up from our first fuck!)]"
										:"[pc.speech(Got many girls pregnant recently?)]"),

							"Reaching down to grab your crotch, you grin at [npc.name], "
									+ (target.getAppearsAsGender().isFeminine()
										?"[pc.speech(I'm going to fuck you pregnant!)]"
										:"[pc.speech(Got many girls knocked up recently?)]")));
				}
				
			} else {
				if(target.isVisiblyPregnant()) {
					attackText = (UtilText.returnStringAtRandom(
							"Reaching down to grab [npc.her] crotch, [npc.name] winks at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Shame you're already pregnant... But that won't stop me from filling your pussy with my [npc.cum+]!)]"
										:"[npc.speech(How many girls have you knocked up recently?)]"),

							"Running [npc.her] [npc.hands] down over [npc.her] crotch, [npc.name] winks at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(You may already be pregnant, but that won't stop me from giving you a nice creampie!)]"
										:"[npc.speech(How many girls have you got pregnant recently?)]"),

							"Sliding [npc.her] [npc.hands] down to draw attention to [npc.her] crotch, [npc.name] grins at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Don't think that being pregnant will stop me from filling your cunt with my [npc.cum+]!)]"
										:"[npc.speech(Got many girls pregnant recently?)]"),

							"Reaching down to grab [npc.her] crotch, [npc.name] grins at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(It's a shame you're already pregnant... But I'm still going to fill you up with my [npc.cum+]!)]"
										:"[npc.speech(Got many girls knocked up recently?)]")));
					
				} else {
					attackText = (UtilText.returnStringAtRandom(
							"Reaching down to grab [npc.her] crotch, [npc.name] winks at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(Want to get knocked up? My cum's crying out to fill your womb!)]"
										:"[npc.speech(How many girls have you knocked up recently?)]"),

							"Running [npc.her] [npc.hands] down over [npc.her] crotch, [npc.name] winks at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(My seed's so potent, I'm going to knock you up on the first fuck!)]"
										:"[npc.speech(How many girls have you got pregnant recently?)]"),

							"Sliding [npc.her] [npc.hands] down to draw attention to [npc.her] crotch, [npc.name] grins at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(My seed's so potent, you're going to get knocked up from our first fuck!)]"
										:"[npc.speech(Got many girls pregnant recently?)]"),

							"Reaching down to grab [npc.her] crotch, [npc.name] grins at you, "
									+ (target.getAppearsAsGender().isFeminine()
										?"[npc.speech(I'm going to fuck you pregnant!)]"
										:"[npc.speech(Got many girls knocked up recently?)]")));
				}
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_PREGNANCY, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your impregnation fetish, you're able to use a special tease attack!";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use a special tease attack due to [npc.her] impregnation fetish!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_IMPREGNATION) && owner.hasPenis();
		}
	},
	
	
	TEASE_DOMINANT(50, "dominant tease", "fetish_generic", Colour.GENERIC_ARCANE,
			DamageType.MANA, DamageLevel.NORMAL, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"Grinning lustfully at [npc.name], you growl out, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'm going to fuck you into next week, bitch!)]"
									:"[pc.speech(I'm going to make you my bitch!)]"),

						"With an evil grin, you growl at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(You're going to get the fucking of a lifetime!)]"
									:"[pc.speech(You're going to be my bitch soon enough!)]"),

						"With a powerful stare, you growl at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'm going to make you my bitch!)]"
									:"[pc.speech(You're going to be a mewling little bitch by the time I'm done with you!)]"),

						"You let out a menacing growl as you stare lustfully at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(You're going to be a good little submissive slut for me!)]"
									:"[pc.speech(I'm going to fuck you so hard, you'll be squealing like a little bitch!)]")));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"Grinning lustfully at you, [npc.name] growls out, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(I'm going to fuck you into next week, bitch!)]"
									:"[npc.speech(I'm going to make you my bitch!)]"),

						"With an evil grin, [npc.name] growls at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You're going to get the fucking of a lifetime!)]"
									:"[npc.speech(You're going to be my bitch soon enough!)]"),

						"With a powerful stare, [npc.name] growls at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(I'm going to make you my bitch!)]"
									:"[npc.speech(You're going to be a mewling little bitch by the time I'm done with you!)]"),

						"[npc.Name] lets out a menacing growl as [npc.she] stares lustfully at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[npc.speech(You're going to be a good little submissive slut for me!)]"
									:"[npc.speech(I'm going to fuck you so hard, you'll be squealing like a little bitch!)]")));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_SUBMISSIVE, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Due to your dominant fetish, you're able to use a special tease attack!";
			} else {
				return UtilText.parse(owner, "[npc.Name] is able to use a special tease attack due to [npc.her] dominant fetish!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_DOMINANT);
		}
	},
	TEASE_SUBMISSIVE(50, "submissive tease", "fetish_generic", Colour.GENERIC_ARCANE,
			DamageType.MANA, DamageLevel.NORMAL, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM,
			null) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			
			String attackText = "";
			
			if(caster.isPlayer()) {
				attackText = (UtilText.returnStringAtRandom(
						"You tilt your head down in a sign of submission, before looking up with big, innocent eyes, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'll be a good [pc.girl]! I promise!)]"
									:"[pc.speech(I'll be a good [pc.girl]! I promise!)]"),

						"You bite your lip and shuffle your feet as you do your best to look as weak as possible, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'll do anything you want!)]"
									:"[pc.speech(I'll do anything you want!)]"),

						"You shuffle your feet and make yourself as small as possible, before lustfully gazing up at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Please! Treat me like your little bitch!)]"
									:"[pc.speech(Please! Make me your little fuck-toy!)]"),

						"You put on your most innocent look as you gaze up lustfully at [npc.name], "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'll be your little slave!)]"
									:"[pc.speech(I'll be a good little cock-sleeve! I promise)]")));
				
			} else {
				attackText = (UtilText.returnStringAtRandom(
						"[npc.Name] tilts [npc.her] head down in a sign of submission, before looking up with big, innocent eyes, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'll be a good [pc.girl]! I promise!)]"
									:"[pc.speech(I'll be a good [pc.girl]! I promise!)]"),

						"[npc.Name] bites [npc.her] [npc.lip] and shuffles [npc.her] [npc.feet] as [npc.she] does [npc.her] best to look as weak as possible, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'll do anything you want!)]"
									:"[pc.speech(I'll do anything you want!)]"),

						"[npc.Name] shuffles [npc.her] [npc.feet] and makes [npc.herself] as small as possible, before lustfully gazing up at you, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(Please! Treat me like your little bitch!)]"
									:"[pc.speech(Please! Make me your little fuck-toy!)]"),

						"[npc.Name] puts on [npc.her] most innocent look as [npc.she] gazes up lustfully into your eyes, "
								+ (target.getAppearsAsGender().isFeminine()
									?"[pc.speech(I'll be your little slave!)]"
									:"[pc.speech(I'll be a good little cock-sleeve! I promise)]")));
			}
			
			return applySpecialSeduction(caster, target, Fetish.FETISH_DOMINANT, attackText);

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Due to your submissive fetish, you're able to use a special tease attack!";
			else
				return UtilText.parse(owner, "[npc.Name] is able to use a special tease attack due to [npc.her] submissive fetish!");
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.hasFetish(Fetish.FETISH_SUBMISSIVE);
		}
	},
	
	
	
	
	/*
	 * Special attack's main feature is that they should apply long-duration
	 * status effects. Damage is secondary. They CAN miss.
	 */
	DOG_BITE(50,
			"bite",
			"biteIcon",
			Colour.DAMAGE_TYPE_PHYSICAL,
			DamageType.PHYSICAL,
			DamageLevel.HIGH,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CRIPPLE, 4))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);

			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>" + "With a burst of energy, you leap forwards, trying to bite at " + target.getName("the") + "."
								+ (isHit ? " Your dog-like muzzle clamps down on " + target.getName("the") + "'s " + target.getArmNameSingular() + ","
										+ " and you manage to cause some serious damage with your sharp canines before [npc.she] manages to throw you off of [npc.herHim]."
										: target.getName("The") + " manages to jump to one side, and there's an audible snap as your teeth clamp down on thin air.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>" + "With a sudden burst of energy, " + caster.getName("the") + " leaps forwards as [npc.she] tries to bite you."
								+ (isHit ? " [npc.Her] dog-like muzzle clamps down on your " + target.getArmNameSingular() + ","
										+ " and [npc.she] shakes [npc.her] head from side-to-side, managing to cause some serious damage with [npc.her] sharp canines before you manage to throw [npc.herHim] off of you."
										: "You jump to one side as you see the attack coming, and there's an audible snap as [npc.her] teeth thankfully clamp down on nothing but thin air.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your anthropomorphic dog-like muzzle can be used to deliver a powerful bite.";
			else
				return UtilText.parse(owner, owner.getName("The") + "'s anthropomorphic dog-like muzzle can be used to deliver a powerful bite.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getFaceType() == FaceType.DOG_MORPH;
		}
	},

	COW_HEADBUTT(50,
			"Headbutt",
			"hornsIcon",
			Colour.DAMAGE_TYPE_PHYSICAL,
			DamageType.PHYSICAL,
			DamageLevel.HIGH,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);

			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>"
							+ "With a burst of energy, you leap forwards, trying to butt your head into [npc.name]."
							+ (isHit
									? " You manage to make contact; ramming your forehead into [npc.her] body and whacking [npc.herHim] with the sides of your horns,"
											+ " you knock the wind out of [npc.herHim] and cause [npc.herHim] to stagger backwards in a daze."
									: " [npc.She] manages to jump to one side, and there's an audible whoosh as you thrust your horns through the air.")
						+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>"
							+ "With a burst of energy, [npc.name] leaps forwards, trying to butt [npc.her] head into you."
							+ (isHit
									? " [npc.She] manages to make contact; ramming [npc.her] forehead into your body and whacking you with the sides of [npc.her] horns,"
											+ " [npc.she] knocks the wind out of you and causes you to stagger backwards in a daze."
									: " You manage to jump to one side, and there's an audible whoosh as [npc.she] thrusts [npc.her] horns through the air.")
						+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your anthropomorphic cow-like head and horns can be used to deliver a powerful attack.";
			} else {
				return UtilText.parse(owner, "[npc.Name]'s anthropomorphic cow-like head and horns can be used to deliver a powerful attack.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getFaceType() == FaceType.COW_MORPH && owner.hasHorns();
		}
	},

	ANTLER_HEADBUTT(50,
			"Headbutt",
			"hornsIcon",
			Colour.DAMAGE_TYPE_PHYSICAL,
			DamageType.PHYSICAL,
			DamageLevel.HIGH,
			DamageVariance.LOW,
			SpecialAttackSpellCosts.MEDIUM,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);

			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>"
							+ "With a burst of energy, you leap forwards, trying to butt your head into [npc.name]."
							+ (isHit
									? " You manage to make contact; ramming your forehead into [npc.her] body and whacking [npc.herHim] with the sides of your antlers,"
											+ " you knock the wind out of [npc.herHim] and cause [npc.herHim] to stagger backwards in a daze."
									: " [npc.She] manages to jump to one side, and there's an audible whoosh as you thrust your antlers through the air.")
						+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>"
							+ "With a burst of energy, [npc.name] leaps forwards, trying to butt [npc.her] head into you."
							+ (isHit
									? " [npc.She] manages to make contact; ramming [npc.her] forehead into your body and whacking you with the sides of [npc.her] antlers,"
											+ " [npc.she] knocks the wind out of you and causes you to stagger backwards in a daze."
									: " You manage to jump to one side, and there's an audible whoosh as [npc.she] thrusts [npc.her] antlers through the air.")
						+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your anthropomorphic reindeer-like head and antlers can be used to deliver a powerful attack.";
			} else {
				return UtilText.parse(owner, "[npc.Name]'s anthropomorphic reindeer-like head and antlers can be used to deliver a powerful attack.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getFaceType() == FaceType.REINDEER_MORPH && owner.hasHorns();
		}
	},

	WOLF_SAVAGE(50, "savage attack", "savageIcon", Colour.DAMAGE_TYPE_PHYSICAL, DamageType.PHYSICAL, DamageLevel.EXTREME, DamageVariance.HIGH, SpecialAttackSpellCosts.EXTREME,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.CRIPPLE, 5))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);
			
			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>"
							+ "With a savage howl, you launch yourself at [npc.name]."
								+ (isHit
										? " Your wolf-like muzzle clamps down on one of [npc.her] [npc.arms], and you rake at [npc.her] body with your sharp claws,"
												+ " doing a considerable amount of damage before [npc.she] manages to kick you off of [npc.herHim]."
										: " [npc.She] manages to jump out of the way, and you end up tumbling to the ground as you're caught off-guard by your target's sudden evasive move.")
							+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>" + "With a savage howl, [npc.name] launches [npc.herself] at you."
								+ (isHit
										? " [npc.Her] wolf-like muzzle clamps down on one of your [pc.arms], and [npc.she] rakes at your body with [npc.her] sharp claws,"
												+ " doing a considerable amount of damage before you manage to kick [npc.herHim] off of you."
										: "You manage to jump out of the way, and [npc.she] ends up tumbling to the ground as [npc.she]'s caught off-guard by your sudden evasive move.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "A powerful, primal energy bubbles just beneath the surface of your wolf-like body, and although you're able to keep it under control, you could always tap into it to deliver a savage attack.";
			else
				return UtilText.parse(owner,
						"A powerful, primal energy bubbles just beneath the surface of [npc.name]'s wolf-like body, and [npc.she]'s able to use it to deliver a savage attack.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getArmType() == ArmType.LYCAN && owner.getFaceType() == FaceType.LYCAN;
		}
	},

	SQUIRREL_SCRATCH(50, "scratch", "scratchIcon", Colour.DAMAGE_TYPE_PHYSICAL, DamageType.PHYSICAL, DamageLevel.NORMAL, DamageVariance.HIGH, SpecialAttackSpellCosts.HIGH,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VULNERABLE, 4))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);

			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>" + "You flex the claws on your anthropomorphic squirrel-like hands, and with a quick dash forwards, you attempt to strike at " + target.getName("the") + "."
								+ (isHit ? " Your sharp claws rake over " + target.getName("the") + "'s body, and [npc.she] lets out a surprised cry as you jump back."
										: target.getName("The") + " manages to dodge your attack, and you end up swiping at nothing more than thin air.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>" + caster.getName("The") + " flexes the claws on [npc.her] anthropomorphic squirrel-like hands, and with a quick dash forwards, attempts to strike at you."
								+ (isHit ? " [npc.Her] sharp claws rake over your body, and you let out a surprised cry as [npc.she] quickly jumps back, and smirking at you."
										: " You see [npc.her] attack coming, and you jump out of the way just in time, leaving [npc.herHim] to swipe at nothing more than thin air.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();
			
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your anthropomorphic squirrel-like hands have a series of sharp claws. You can use these claws to deliver a scratching attack.";
			else
				return UtilText.parse(owner,
						owner.getName("The") + "'s anthropomorphic squirrel-like hands have a series of sharp claws. [npc.She] can use these claws to deliver a scratching attack.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getArmType() == ArmType.SQUIRREL_MORPH;
		}
	},
	
	ALLIGATOR_TAIL_SWIPE(50, "tail swipe", "tailSwipeIcon", Colour.RACE_ALLIGATOR_MORPH, DamageType.PHYSICAL, DamageLevel.HIGH, DamageVariance.HIGH, SpecialAttackSpellCosts.HIGH,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);

			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>"
							+ "You turn to one side, swinging your huge, alligator-like tail straight at [npc.name]."
								+ (isHit
										? " Your appendage connects fully with [npc.her] body, causing considerable damage and dazing [npc.herHim] from the powerful blow!"
										: " [npc.Name] manages to dodge your attack, and you end up swiping at nothing more than thin air!")
						+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>"
							+ "[npc.Name] turns to one side, swinging [npc.her] huge, alligator-like tail straight at you."
								+ (isHit
										? " [npc.Her] appendage connects fully with your body, causing considerable damage and dazing you from the powerful blow!"
										: " You manage to dodge [npc.her] attack, and [npc.she] ends up swiping at nothing more than thin air!")
						+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();
			
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your powerful alligator-like tail can be swung at someone to cause huge damage!";
			else
				return UtilText.parse(owner,
						"[npc.Name]'s powerful alligator-like tail can be swung at someone to cause huge damage!");
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getTailType() == TailType.ALLIGATOR_MORPH;
		}
	},

	CAT_SCRATCH(50, "scratch", "scratchIcon", Colour.DAMAGE_TYPE_PHYSICAL, DamageType.PHYSICAL, DamageLevel.NORMAL, DamageVariance.HIGH, SpecialAttackSpellCosts.HIGH,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.VULNERABLE, 4))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);

			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>" + "You extend the claws on your anthropomorphic cat-like hands, and with a quick dash forwards, you attempt to strike at " + target.getName("the") + "."
								+ (isHit ? " Your sharp claws rake over " + target.getName("the") + "'s body, and [npc.she] lets out a surprised cry as you jump back, retracting your claws."
										: target.getName("The") + " manages to dodge your attack, and you end up swiping at nothing more than thin air.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>" + caster.getName("The") + " extends the claws on [npc.her] anthropomorphic cat-like hands, and with a quick dash forwards, attempts to strike at you."
								+ (isHit ? " [npc.Her] sharp claws rake over your body, and you let out a surprised cry as [npc.she] quickly jumps back, retracting [npc.her] claws and smirking at you."
										: " You see [npc.her] attack coming, and you jump out of the way just in time, leaving [npc.herHim] to swipe at nothing more than thin air.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();
			
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your anthropomorphic cat-like hands have a series of sharp claws that can be retracted and extended at will. You can use these claws to deliver a scratching attack.";
			else
				return UtilText.parse(owner,
						owner.getName("The") + "'s anthropomorphic cat-like hands have a series of sharp claws that can be retracted and extended at will. [npc.She] can use these claws to deliver a scratching attack.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getArmType() == ArmType.CAT_MORPH;
		}
	},

	HORSE_KICK(50, "kick", "kickIcon", Colour.CLOTHING_RED, DamageType.PHYSICAL, DamageLevel.HIGH, DamageVariance.LOW, SpecialAttackSpellCosts.MEDIUM, Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 4))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = calculateDamage(caster, target, isCritical), cost = calculateCost(caster);

			descriptionSB = new StringBuilder();
			
			if (caster == Main.game.getPlayer()) {
				descriptionSB.append(UtilText.parse(target,
						"<p>" + "You turn to one side and kick out with one of your powerful horse-like legs."
								+ (isHit ? " Your hooved foot slams into " + target.getName("the") + ", and [npc.she] staggers back in a daze."
										: target.getName("The") + " sees your attack coming, and [npc.she] manages to dodge to one side at the last second.")
								+ "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			} else {
				descriptionSB.append(UtilText.parse(caster,
						"<p>" + caster.getName("The") + " turns to one side and kicks out with one of [npc.her] powerful horse-like legs."
								+ (isHit ? " [npc.Her] hooved foot slams into you, causing you to stagger back in a daze." : " You see [npc.her] attack coming, and manage to dodge to one side at the last second.") + "</p>")
						+ getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			}
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(-damage));
				for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet())
					target.addStatusEffect(se.getKey(), se.getValue());
			}

			caster.incrementStamina(-cost);
			
			return descriptionSB.toString();

		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your anthropomorphic horse-like legs are very strong, and you're able to use them to deliver a powerful kick.";
			else
				return UtilText.parse(owner, owner.getName("The") + "'s anthropomorphic horse-like legs are very strong, and [npc.she]'s able to use them to deliver a powerful kick.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter owner) {
			return owner.getLegType() == LegType.HORSE_MORPH;
		}
	};

	private static StringBuilder descriptionSB;

	protected String name;
	protected DamageType damageType;
	protected DamageLevel damageLevel;
	protected DamageVariance damageVariance;
	protected SpecialAttackSpellCosts specialAttackCost;
	protected int renderingPriority;
	private Map<StatusEffect, Integer> statusEffects;
	private String SVGString;

	private SpecialAttack(int renderingPriority, String name, String pathName, Colour colourShade, DamageType damage, DamageLevel damageLevel, DamageVariance damageVariance, SpecialAttackSpellCosts specialAttackCost,
			Map<StatusEffect, Integer> statusEffects) {
		this.renderingPriority = renderingPriority;
		this.name = name;
		this.damageType = damage;
		this.damageLevel = damageLevel;
		this.damageVariance = damageVariance;
		this.specialAttackCost = specialAttackCost;
		
		if(statusEffects==null)
			this.statusEffects = new EnumMap<>(StatusEffect.class);
		else
			this.statusEffects = statusEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/" + pathName + ".svg");
			SVGString = Util.inputStreamToString(is);

			SVGString = SVGString.replaceAll("#ff2a2a", colourShade.getShades()[0]);
			SVGString = SVGString.replaceAll("#ff5555", colourShade.getShades()[1]);
			SVGString = SVGString.replaceAll("#ff8080", colourShade.getShades()[2]);
			SVGString = SVGString.replaceAll("#ffaaaa", colourShade.getShades()[3]);
			SVGString = SVGString.replaceAll("#ffd5d5", colourShade.getShades()[4]);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public float calculateDamage(GameCharacter caster, GameCharacter target, boolean critical) {

		float damage = getMinimumDamage(caster, target);

		// Add variation:
		if (getMaximumDamage(caster, target) - getMinimumDamage(caster, target) > 0) {
			float difference = getMaximumDamage(caster, target) - getMinimumDamage(caster, target);
			
			damage += Math.random()*difference;
		}

		// Is critical:
		if (critical)
			damage *= (caster.getAttributeValue(Attribute.CRITICAL_DAMAGE) / 100f);

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;

		return damage;
	}

	public float getMaximumDamage(GameCharacter caster, GameCharacter target) {
		float damage = getModifiedDamage(caster, target, getDamage(caster) * (1 + damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}

	public float getMinimumDamage(GameCharacter caster, GameCharacter target) {
		float damage = getModifiedDamage(caster, target, getDamage(caster) * (1 - damageVariance.getPercentage()));

		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}

	private float getModifiedDamage(GameCharacter caster, GameCharacter target, float attackersDamage) {
		float damage = attackersDamage;
		if (damage < 1)
			damage = 1;

		// Attacker modifiers:
		// Damage Type modifier:
		damage *= (caster.getAttributeValue(damageType.getMultiplierAttribute()) / 100f);
		// Pure damage modifier:
		damage *= ((100 + caster.getAttributeValue(Attribute.DAMAGE_PURE)) / 100f);

		if (damage < 1)
			damage = 1;

		if (target != null) {
			// Defender modifiers:
			// Damage Type modifier:
			damage *= ((100 - target.getAttributeValue(damageType.getResistAttribute())) / 100f);
			// Pure damage modifier:
			damage *= ((100 - target.getAttributeValue(Attribute.RESISTANCE_PURE)) / 100f);

			if (damage < 1)
				damage = 1;

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

	
	public float calculateCost(GameCharacter caster) {

		float cost = getMinimumCost(caster);

		// Add variation:
		if (getMaximumCost(caster) - getMinimumCost(caster) > 0) {
			float difference = getMaximumCost(caster) - getMinimumCost(caster);
			
			cost += Math.random()*difference;
		}

		// Round float value to nearest 1 decimal place:
		cost = (Math.round(cost*10))/10f;

		return cost;
	}

	public float getMaximumCost(GameCharacter caster) {
		float damage = getModifiedCost(caster);
		
		damage*=1.1f;
		
		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}

	public float getMinimumCost(GameCharacter caster) {
		float damage = getModifiedCost(caster);
		
		damage*=0.9f;
		
		// Round float value to nearest 1 decimal place:
		damage = (Math.round(damage*10))/10f;
		
		return damage;
	}
	
	private float getModifiedCost(GameCharacter caster) {
		float calculatedCost = caster.getLevel() + (caster.getAttributeValue(Attribute.STAMINA_MAXIMUM) * (specialAttackCost.getPercentage())/100f);
		
		// Round float value to nearest 1 decimal place:
		calculatedCost = (Math.round(calculatedCost*10))/10f;
		
		return calculatedCost;
	}

	protected String getDamageAndCostDescription(GameCharacter caster, GameCharacter target, float cost, float damage, boolean isHit, boolean isCritical) {
		StringBuilder descriptionSB = new StringBuilder();
		
		if (caster == Main.game.getPlayer()) {
			if (isCritical)
				descriptionSB.append("<p>" + (isHit ? "<b>You <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
						+ damageType.getName() + "</b>" + " damage!</b>" : "<b>You missed!</b>") + "</p>");
			else
				descriptionSB.append(
						"<p>" + (isHit ? "<b>You did " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + damageType.getName() + "</b>" + " damage!</b>" : "<b>You missed!</b>") + "</p>");

			if (statusEffects != null && isHit) {
				descriptionSB.append(UtilText.parse(target, "<p>[npc.She] is now suffering "));
				int i = 0;
				for (Entry<StatusEffect, Integer> seEntry : statusEffects.entrySet()) {
					if (i != 0) {
						if (i == statusEffects.size() - 1)
							descriptionSB.append(" and ");
						else
							descriptionSB.append(", ");
					}
					descriptionSB.append("<b>" + seEntry.getValue() + "</b> turns of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
				}
				descriptionSB.append(".</p>");
			}

			descriptionSB.append("<p>" + "Using your non-human body parts in such a manner is quite tiring, and you lose <b>" + cost + "</b> <b style='color:" + Attribute.DAMAGE_STAMINA.getColour().toWebHexString() + ";'>stamina</b>!</b>" + "</p>");

		} else {
			if (isCritical)
				descriptionSB.append("<p>" + (isHit ? "<b>You were <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString()
						+ ";'>" + damageType.getName() + "</b>" + " damage!</b>" : "<b>" + caster.getName("The") + " missed!</b>") + "</p>");
			else
				descriptionSB.append("<p>" + (isHit ? "<b>You took " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + damageType.getName() + "</b>" + " damage!</b>"
						: "<b>" + caster.getName("The") + " missed!</b>") + "</p>");

			if (statusEffects != null && isHit) {
				descriptionSB.append("<p>You are now suffering ");
				int i = 0;
				for (Entry<StatusEffect, Integer> seEntry : statusEffects.entrySet()) {
					if (i != 0) {
						if (i == statusEffects.size() - 1)
							descriptionSB.append(" and ");
						else
							descriptionSB.append(", ");
					}
					descriptionSB.append("<b>" + seEntry.getValue() + "</b> turns of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
				}
				descriptionSB.append("!</p>");
			}

			descriptionSB.append(UtilText.parse(caster,
					"<p>" + "Using [npc.her] non-human body parts in such a manner is quite tiring, and [npc.she] loses <b>" + cost + "</b> <b style='color:" + Attribute.DAMAGE_STAMINA.getColour().toWebHexString() + ";'>stamina</b>!</b>" + "</p>"));
		}

		return descriptionSB.toString();
	}
	
	protected String applySpecialSeduction(GameCharacter caster, GameCharacter target, Fetish fetishWeakness, String attackText) {
		descriptionSB = new StringBuilder();
		
		descriptionSB.append("<p>"
				+ attackText
				+ "</p>");

		boolean critical = target.hasFetish(fetishWeakness);

		float damage = Attack.calculateDamage(caster, target, Attack.SEDUCTION, critical);
		
		target.incrementMana(-damage);
		
		if(caster.isPlayer()) {
			if(critical) {
				descriptionSB.append(
						"<p>"
							+ "[npc.Name] can't bring [npc.herself] to look away, and as [npc.she] lets out a desperate whine, you realise that [npc.she] has "
							+ UtilText.generateSingularDeterminer(fetishWeakness.getName(target))+" <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>"+fetishWeakness.getName(target)+" fetish</b>, and your display is"
							+ " <b style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>massively turning [npc.herHim] on</b>!</br></br>"
							+ "<b>[npc.She] loses " + damage + " <b style='color:" + DamageType.MANA.getMultiplierAttribute().getColour().toWebHexString() + ";'>willpower</b>!"
						+ "</p>");
			} else {
				descriptionSB.append(
						"<p>"
							+ "[npc.Name] seems to be enjoying the show you're putting on, but it doesn't seem to be any more effective than a normal tease attack...</br></br>"
							+ "<b>[npc.She] loses " + damage + " <b style='color:" + DamageType.MANA.getMultiplierAttribute().getColour().toWebHexString() + ";'>willpower</b>."
						+ "</p>");
			}
		} else {
			if(critical) {
				descriptionSB.append(
						"<p>"
							+ "Because you have "
							+UtilText.generateSingularDeterminer(fetishWeakness.getName(target))+" <b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>"+fetishWeakness.getName(target)+" fetish</b>,"
							+ " you find yourself unable to look away from [npc.name]'s enticing display, which is <b style='color:" + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>massively turning you on</b>!</br></br>"
							+ "<b>You lose " + damage + " <b style='color:" + DamageType.MANA.getMultiplierAttribute().getColour().toWebHexString() + ";'>willpower</b>!"
						+ "</p>");
			} else {
				descriptionSB.append(
						"<p>"
							+ "[npc.Name]'s display is quite arousing...</br></br>"
							+ "<b>You lose " + damage + " <b style='color:" + DamageType.MANA.getMultiplierAttribute().getColour().toWebHexString() + ";'>willpower</b>."
						+ "</p>");
			}	
		}
		

		float cost = calculateCost(caster);
		caster.incrementStamina(-cost);
		if(caster.isPlayer()) {
			descriptionSB.append("<p>"
						+ "Putting on such a display is quite tiring, and you lose <b>" + cost + "</b>  <b style='color:" + Attribute.DAMAGE_STAMINA.getColour().toWebHexString() + ";'>stamina</b>.</b>"
					+ "</p>");
		} else {
			descriptionSB.append("<p>"
						+ "Putting on such a display is quite tiring, and as a result [npc.name] loses <b>" + cost + "</b>  <b style='color:" + Attribute.DAMAGE_STAMINA.getColour().toWebHexString() + ";'>stamina</b>.</b>"
					+ "</p>");
		}
		
		return descriptionSB.toString();
	}

	public abstract boolean isConditionsMet(GameCharacter owner);

	public abstract String getDescription(GameCharacter owner);

	public abstract String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical);

	public String getName() {
		return name;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	/**
	 * Special attack damage is 3 + (caster level) * (1 + damageModifier):
	 * 
	 * @param attacker
	 * @return
	 */
	public int getDamage(GameCharacter caster) {
		int damage = (int) Math.ceil(3 + caster.getLevel() * (1 + damageLevel.getDamageModifier()));

		if (damage < 1)
			damage = 1;

		return damage;
	}

	public DamageVariance getDamageVariance(GameCharacter caster) {
		return damageVariance;
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public Map<StatusEffect, Integer> getStatusEffects() {
		return statusEffects;
	}

	public String getSVGString() {
		return SVGString;
	}
}
