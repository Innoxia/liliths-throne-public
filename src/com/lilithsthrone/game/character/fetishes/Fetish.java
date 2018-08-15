package com.lilithsthrone.game.character.fetishes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.?
 * @version 0.1.99
 * @author Innoxia
 */
public enum Fetish {
	
	// FETISHES:

	// Sex types:
	
	FETISH_ANAL_GIVING(60,
			"anal",
			"performing anal",
			"fetish_anal_giving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>anal tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>buttslut tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love giving anal sex. Using your partner's ass, claiming their asshole as your property, it all drives you crazy with lust!";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for giving anal sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "performing anal sex actions");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_ANAL_RECEIVING(60,
			"buttslut",
			"receiving anal",
			"fetish_anal_receiving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>buttslut tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>anal tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love receiving anal sex. The thought of getting your ass pounded like a good little buttslut drives you crazy with lust!";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for receiving anal sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "receiving any anal attention");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_VAGINAL_GIVING(60,
			"vaginal",
			"performing vaginal",
			"fetish_vaginal_giving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>pussy worship tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>pussy slut tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Although typically one of the most vanilla of all sexual acts, your love for performing vaginal sex has turned into a complete obsession.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has an extreme obsession with performing vaginal sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "performing vaginal sex actions");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_VAGINAL_RECEIVING(60,
			"pussy slut",
			"receiving vaginal",
			"fetish_vaginal_receiving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>pussy slut tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>pussy worship tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Although typically one of the most vanilla of all sexual acts, your love for receiving all forms of vaginal sex has turned into a complete obsession.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has an extreme obsession with receiving vaginal sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "receiving any vaginal attention");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_ORAL_RECEIVING(60,
			"oral",
			"receiving oral",
			"fetish_oral_receiving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral performer tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love receiving oral sex. Guiding your partner's head down between your legs is your favourite activity, and you're always eager for someone's lips and tongue to bring you to orgasm.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for receiving oral sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "receiving oral sex");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_ORAL_GIVING(60,
			"oral performer",
			"giving oral",
			"fetish_oral_giving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral performer tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love giving oral sex. Going down between your partner's legs is your favourite activity, and you're always ready and eager to use your mouth to bring them to orgasm!";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for giving oral sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "performing oral sex");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_BREASTS_OTHERS(60,
			"breasts lover",
			"others' breasts",
			"fetish_breasts_others",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts lover tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You have an obsession with breasts. Either big or small, if someone's got a pair (or more) of tits, you're going to be finding a way to use them.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for other's breasts.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "playing with others' breasts");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_BREASTS_SELF(60,
			"breasts",
			"self breast play",
			"fetish_breasts_self",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts lover tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You have an obsession with your breasts. You love nothing more than pleasuring your partners with them, or showing them off to your many admirers.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for using [npc.her] breasts.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having your [npc.breasts] touched and fondled");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_LACTATION_OTHERS(60,
			"milk lover",
			"being breast-fed",
			"fetish_lactation_others",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>milk-lover tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>lactation tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You have an obsession with being breast-fed. You love nothing more than to be suckling on someone's engorged, milky teats.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for being breast-fed.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being breast-fed");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_LACTATION_SELF(60,
			"lactation",
			"lactating",
			"fetish_lactation_self",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>lactation tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>milk-lover tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You have an obsession with lactating. You love nothing more than the full, satisfying feeling of having your breasts milked.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for lactating.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having your [npc.breasts] milked");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_LEG_LOVER(60,
			"leg lover",
			"partner's legs",
			"fetish_leg_lover",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>leg lover tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>strutter tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love legs. Using a partner's legs or thighs in sex is the ultimate turn-on for you.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for using other people's legs and thighs.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "others' legs");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_STRUTTER(60,
			"strutter",
			"having legs used",
			"fetish_strutter",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>strutter tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>leg lover tease</span>"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love showing off your legs. Using your legs or thighs in sex is the ultimate turn-on for you.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for using [npc.her] legs or thighs in sex.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "using your thighs and legs in sex");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	

	FETISH_FOOT_GIVING(60,
			"dominant foot",
			"using feet",
			"fetish_foot_giving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>dominant foot tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>submissive foot tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love using your feet in sex. Getting your partner to worship and perform sexual acts on your feet and toes turns you on like nothing else.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for using [npc.her] feet in sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "using your feet in sex");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_FOOT_RECEIVING(60,
			"submissive foot",
			"using partner's feet",
			"fetish_foot_receiving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>submissive foot tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>dominant foot tease</span>"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You absolutely love feet. Getting your partner to use their feet and toes in sex is the ultimate turn-on for you.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for using other people's feet.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "others' feet");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_PENIS_RECEIVING(60,
			"cock addict",
			"others cocks",
			"fetish_cock_addict",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cock addict tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cock stud tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are hopelessly addicted to cock. Large, small, fat, thin, you really don't care about the looks, just so long as it's pumping in and out of one of your holes...";
			else
				return UtilText.parse(owner, "[npc.Name] is hopelessly addicted to cock.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "others' cocks");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_PENIS_GIVING(60,
			"cock stud",
			"using their cock",
			"fetish_dick_dealer",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cock stud tease</span> (Requires penis)",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cock addict tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are obsessed with penetrative sex. Thrusting your cock into any available orifice is all you can think about...";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for using [npc.her] cock.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "using your cock");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_CUM_STUD(60,
			"cum stud",
			"cumming",
			"fetish_cum",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum stud tease</span> (Requires penis)",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum addict tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love cumming. You don't really care where it goes; all you want is to pump out load after load of your salty seed...";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for cumming.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "any form of self-focused cum play");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_CUM_ADDICT(60,
			"cum addict",
			"cum-play",
			"fetish_cum_addict",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.CLOTHING_WHITE,
			null,
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum addict tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum stud tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are hopelessly addicted to cum. You really don't care who's providing it; all you want is for your mouth to be full of delicious, salty seed..."
						+ " Letting it slide around over your tongue, savouring every moment... Mmm... Cum really is the best...";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for cum.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "others' cum");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_DEFLOWERING(60,
			"deflowering",
			"deflowering",
			"fetish_deflowering",
			Fetish.BASE_VERY_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues("Gain <span style='color:"+ Colour.GENERIC_EXPERIENCE.toWebHexString()+ ";'>xp</span> from <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>taking virginities</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love nothing more than claiming an innocent maiden's virginity. Although breaking in a soon-to-be slut's pussy is your favourite, you still enjoy being the first to fuck a person's ass, nipples, or throat.";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for deflowering. [npc.She] loves being the one to break a girl's hymen, but also enjoys being the first to fuck a person's ass, nipples, or throat.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "taking virginities");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
	},
	
	FETISH_PURE_VIRGIN(60,
			"virginity",
			"retaining virginity",
			"fetish_virginity",
			Fetish.BASE_VERY_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Gain</span> <span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>'pure virgin'</span>",
					"<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Suffer</span> <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>'broken virgin'</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner == null)
				return "";

			if (owner.isPlayer()) {
				if (owner.getVaginaType() != VaginaType.NONE)
					return "You prize your vaginal virginity above everything else in the world. If you were ever to lose it, you don't know how you'd cope...";
				else
					return "Although you currently don't have a vagina, you know that if you were ever to have one, you'd prize its virginity above everything else in the world. If you were ever to lose it, you don't know how you'd cope...";
			} else
				return UtilText.parse(owner, "[npc.Name] prizes [npc.her] virginity above all else.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "holding onto your virginity");
		}
		
		@Override
		public boolean isAvailable(GameCharacter character) {
			return character.getVaginaType() != VaginaType.NONE && character.isVaginaVirgin();
		}

		@Override
		public List<String> getPerkRequirements(GameCharacter character) {
			perkRequirementsList.clear();
			
			if(character.getVaginaType()==VaginaType.NONE) {
				perkRequirementsList.add("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Requires vagina</span>");
			} else {
				perkRequirementsList.add("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Requires vagina</span>");
			}
			
			if(!character.isVaginaVirgin()) {
				perkRequirementsList.add("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Requires virginity</span>");
			} else {
				perkRequirementsList.add("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Requires virginity</span>");
			}
			
			return perkRequirementsList;
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ZERO_PURE;
		}
	},
	
	FETISH_MASTURBATION(60,
			"masturbation",
			"masturbating",
			"fetish_masturbation",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.TEXT_GREY.toWebHexString()+ ";'>No special abilities</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "To you, masturbating is just as good as, if not better than, having sex.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves masturbating.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "masturbating");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	
	// FETISH_SPANKING("spanking", "You love the idea of spanking or being
	// spanked during sex."),

	// Body parts:
	
	// FETISH_LACTATION("lactation", "The idea of someone being milked drives
	// you crazy with lust."),
	// FETISH_FOOT("foot", "It doesn't matter if they're yours or your
	// partner's, you're going to be trying to find a way to use feet during
	// sex."),

	// Effects:
	FETISH_PREGNANCY(60,
			"pregnancy",
			"being pregnant",
			"fetish_pregnancy",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
					+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>fertility tease</span> (Requires vagina)",
					"<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Weak to</span> <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>virility tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You often find yourself fantasising about being impregnated, and the idea of being bred like an animal drives you crazy with lust.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for being impregnated.");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being pregnant");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_IMPREGNATION(60,
			"impregnation",
			"impregnating",
			"fetish_impregnation",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, 5)),
			Util.newArrayListOfValues("<span style='color:"
					+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>virility tease</span> (Requires penis)",
					"<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Weak to</span> <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>fertility tease</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You often find yourself fantasising about filling fertile wombs with your seed, and the idea of breeding your sexual partner like an animal drives you crazy with lust.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for impregnating [npc.her] partner during sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "impregnating others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_TRANSFORMATION_GIVING(60,
			"transformer",
			"transforming others",
			"fetish_transformation_giving",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Halves cost of all potion making</span>"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love the idea of transforming others. Watching their bodies change, either voluntarily or otherwise, is a massive turn-on for you.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves transforming others. Watching their bodies change, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "transforming others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
	},
	
	FETISH_TRANSFORMATION_RECEIVING(60,
			"test subject",
			"being transformed",
			"fetish_transformation_receiving",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"[style.boldGood(Increases potency)] <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>of receiving forced transformations</span>",
					"[style.boldBad(Disables)] ability to spit out TF potions"),
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love the idea of being transformed. Having your body parts changed, either voluntarily or otherwise, is a massive turn-on for you.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves being transformed. Having [npc.her] body parts changed, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "the idea of being transformed");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_KINK_GIVING(60,
			"kink advocate",
			"giving others fetishes",
//			"fetish_transformation_giving",
			"fetish_kink_giving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than triggering forced fetishes
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Enjoy making others try new things!</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "The idea of giving people new fetishes, either voluntarily or otherwise, is a massive turn-on for you.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves giving others new fetishes. Watching them enjoy perverse new things, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "giving others new fetishes");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
	},
	
	FETISH_KINK_RECEIVING(60,
			"kink curious",
			"being given new fetishes",
//			"fetish_transformation_receiving",
			"fetish_kink_receiving",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					// Unclear what extra effects this fetish should provide, other than not taking corruption from recieving forced fetishes
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Removes corruption gain when a fetish is forced on you.</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love the idea of developing new fetishes. Gaining perverse joy from new things, either voluntarily or otherwise, is a massive turn-on for you.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves developing new fetishes. Gaining perverse joy from new things, either voluntarily or otherwise, is a massive turn-on for [npc.herHim].");
			}
		}
		
		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "the idea of being given new fetishes");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	// Behaviour (organised roughly in active/passive pairs):
	

	FETISH_DENIAL(60,
			"orgasm denier",
			"denying orgasms",
			"fetish_denial",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"Unlocks <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Deny</span> in non-submissive sex"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Either by teasing them with your body, or preventing them from orgasming, you love denying your partners during sex.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for orgasm denial.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "denying orgasms");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_DENIAL_SELF(60,
			"self-denial",
			"being denied",
			"fetish_denial_self",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 2)),
			null,
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love edging and having your orgasms denied.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for having [npc.her] orgasms denied.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having your orgasms denied");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_DOMINANT(60,
			"dominant",
			"acting dominantly",
			"fetish_dominant",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>dominant tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>submissive tease</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love being the dominant partner during sex, and you know just how to show your partners who's in charge.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the dominant partner in sex.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being the dominant partner");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_SUBMISSIVE(60,
			"submissive",
			"acting submissively",
			"fetish_submissive",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 2)),
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>submissive tease</span>",
					"<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>dominant tease</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love being the submissive partner during sex. You'll do anything to show your submission, and will happily let your partner do whatever they want with you.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the submissive partner in sex.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being the submissive partner");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_INCEST(60,
			"incest",
			"incestuous sex",
			"fetish_incest",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"Unlocks <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>incest tease</span>"),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if(owner.getSexualOrientation()==SexualOrientation.ANDROPHILIC) {
					return "You always did have the hots for your male cousin...";
				} else {
					return "You always did have the hots for your aunt Lily, as well as your female cousin...";
				}
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for incestuous sex.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having sex with your relations");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
	},
	
	FETISH_MASOCHIST(60,
			"masochist",
			"pain and humiliation",
			"fetish_masochist",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"All incoming <span style='color:"+ Colour.ATTRIBUTE_HEALTH.toWebHexString()+ ";'>energy damage</span> is reduced by 25%",
					"Reduced damage is converted to <span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>lust damage</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love pain, and being treated like dirt sends you absolutely wild with lust.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for being treated like a worthless slut.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "pain and humiliation");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_SADIST(60,
			"sadist",
			"inflicting pain",
			"fetish_sadist",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5)),
			Util.newArrayListOfValues(
					"You take 10% of dealt <span style='color:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>energy damage</span> as "
										+ "<span style='color:"+ Attribute.DAMAGE_LUST.getColour().toWebHexString()+ ";'>lust damage</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love dishing out pain and humiliation, and causing others to suffer sends you absolutely wild with lust.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for dealing out pain and humiliation.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "inflicting pain and humiliation on others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_NON_CON_DOM(60,
			"non-consent",
			"raping",
			"fetish_noncon_dom",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Increases</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>arousal gain when partner is resisting sex</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love nothing more than when someone's being forced, against their will, to have sex. The more they're struggling, the more you get turned on...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for non-consensual encounters. The more [npc.her] victim struggles, the more [npc.she] gets turned on...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "having sex with an unwilling partner");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
	},
	
	FETISH_NON_CON_SUB(60,
			"unwilling fuck-toy",
			"being raped",
			"fetish_noncon_sub",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Increases</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>arousal gain when you are resisting sex</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love nothing more than when you're forced, against your will, to have sex with someone. Struggling and pleading to be released turns you on like nothing else...";
				
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the victim in non-consensual encounters. Struggling and pleading to be released turns [npc.herHim] on like nothing else...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "being forced to have sex against your will");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
	},

	FETISH_EXHIBITIONIST(60,
			"exhibitionist",
			"exposing themself",
			"fetish_exhibitionist",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Replaces</span> <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>exposed status effects</span>"
										+" <span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>with beneficial versions</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love showing off your body, and the act of parading your naked form in public places turns you on like nothing else.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for exhibiting [npc.her] body.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "exposing yourself to others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_VOYEURIST(60,
			"voyeurist",
			"watching others",
			"fetish_voyeurist",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Arousal boost</span> while watching sex scenes"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love watching other people... Especially while they're doing something naughty...";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a fetish for watching other people...");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "watching others");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_BIMBO(60,
			"bimbo",
			"being a bimbo",
			"fetish_bimbo",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 10)),
			Util.newArrayListOfValues("<span style='color:"
					+ Colour.GENERIC_SEX.toWebHexString()
					+ ";'>Talk like a bimbo</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're obsessed with the idea of acting like a complete bimbo. It's gotten to the point where no matter how intelligent you might actually be, you can't imagine yourself as anything other than a braindead slut.";
			else
				return UtilText.parse(owner, "[npc.Name] loves acting like a complete bimbo.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "acting like a bimbo");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_CROSS_DRESSER(60,
			"cross dressing",
			"cross dressing",
			"fetish_cross_dresser",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 10)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to clothing femininity status effects</span>"),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You love wearing all manner of different clothing, and you don't really care if it's too masculine or feminine for your body.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves wearing all manner of different clothing, and [npc.she] doesn't really care if it's too masculine or feminine for [npc.her] body.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "wearing clothing more suited to the opposite gender");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	// Derived fetishes:
	
	FETISH_SWITCH(60,
			"switch",
			"being a switch",
			"fetish_switch",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_DOMINANT,
					Fetish.FETISH_SUBMISSIVE)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're perfectly happy with switching between dom and sub as the situation calls for it.";
			else
				return UtilText.parse(owner, "[npc.Name] is happy to play as either the dom or sub during sex.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "switching between dom and sub");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_BREEDER(60,
			"breeder",
			"breeding",
			"fetish_breeder",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.FERTILITY, 25),
					new Value<Attribute, Integer>(Attribute.VIRILITY, 25)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_PREGNANCY,
					Fetish.FETISH_IMPREGNATION)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a dream. A dream of a world in which everyone is pregnant, including yourself!";
			else
				return UtilText.parse(owner, "[npc.Name] wants nothing more than to share [npc.her] love of pregnancies with everyone [npc.she] meets.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "anything to do with reproduction");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_SADOMASOCHIST(60,
			"sadomasochist",
			"sadomasochism",
			"fetish_sadomasochist",
			Fetish.BASE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 10),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 10)),
			null,
			Util.newArrayListOfValues(
					Fetish.FETISH_SADIST,
					Fetish.FETISH_MASOCHIST)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You don't care whether you're on the giving or receiving end; if there's pain and humiliation involved, you're up for anything.";
			else
				return UtilText.parse(owner, "[npc.Name] loves pain and humiliation in all of its forms.");
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "all forms of pain and humiliation");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_LUSTY_MAIDEN(60,
			"lusty maiden",
			"lusty maiden",
			"fetish_lusty_maiden",
			Fetish.BASE_RARE_EXPERIENCE_GAIN,
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					"<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Empowers</span> <span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>'pure virgin'</span>",
					"<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Amplifies</span> <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>'broken virgin'</span>"),
			Util.newArrayListOfValues(
					Fetish.FETISH_PURE_VIRGIN,
					Fetish.FETISH_ANAL_RECEIVING,
					Fetish.FETISH_ORAL_GIVING,
					Fetish.FETISH_BREASTS_SELF)) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are the ultimate tease, seducing and pleasuring others with your ass, mouth, breasts, and even the promise of your pussy,"
							+ " but you'll never actually allow anyone to penetrate your feminine sex and take your precious virginity.";
			} else {
				return UtilText.parse(owner, "[npc.Name] loves to pleasure others with [npc.her] ass, mouth, breasts, and even the promise of [npc.her] pussy,"
							+ " but [npc.she]'ll never actually allow anyone to penetrate [npc.her] feminine sex and take [npc.her] precious virginity.");
			}
		}

		@Override
		public String getFetishDesireDescription(GameCharacter target, FetishDesire desire) {
			return getGenericFetishDesireDescription(target, desire, "avoiding vaginal sex");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	private int renderingPriority;
	protected String name;
	protected String shortDescriptor;
	private int experienceGainFromSexAction;
	private HashMap<Attribute, Integer> attributeModifiers;

	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;
	
	private List<Fetish> fetishesForAutomaticUnlock;
	
	private static final int BASE_EXPERIENCE_GAIN = 2;
	private static final int BASE_RARE_EXPERIENCE_GAIN = 10;
	private static final int BASE_VERY_RARE_EXPERIENCE_GAIN = 25;

	private Fetish(
			int renderingPriority,
			String name,
			String shortDescriptor,
			String pathName,
			int experienceGainFromSexAction,
			Colour colourShade,
			HashMap<Attribute, Integer> attributeModifiers,
			List<String> extraEffects,
			List<Fetish> fetishesForAutomaticUnlock) {

		this.renderingPriority = renderingPriority;
		this.name = name;
		this.shortDescriptor = shortDescriptor;
		this.experienceGainFromSexAction = experienceGainFromSexAction;
		
		this.attributeModifiers = attributeModifiers;

		this.extraEffects = extraEffects;
		
		if(fetishesForAutomaticUnlock==null) {
			this.fetishesForAutomaticUnlock = new ArrayList<>();
		} else {
			this.fetishesForAutomaticUnlock = fetishesForAutomaticUnlock;
		}
		
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Fetish icon file does not exist (Trying to read from '"+pathName+"')!");
			}
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

		modifiersList = new ArrayList<>();

		if (attributeModifiers != null) {
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet()) {
				modifiersList.add("<b>"+(e.getValue() > 0 ? "+" : "") + e.getValue() + "</b> <b style='color: "+ e.getKey().getColour().toWebHexString()+ ";'>"+ Util.capitaliseSentence(e.getKey().getAbbreviatedName())+ "</b>");
			}
		}
		
		if (extraEffects != null) {
			modifiersList.addAll(extraEffects);
		}
//		modifiersList.add("[style.boldBad(Locks)] desire to <b style='color:"+FetishDesire.FOUR_LOVE.getColour().toWebHexString()+";'>"+FetishDesire.FOUR_LOVE.getName()+"</b>");
	}
	
	public List<Fetish> getFetishesForAutomaticUnlock() {
		return fetishesForAutomaticUnlock;
	}
	
	public boolean isAvailable(GameCharacter character) {
		return true;
	}
	
	private static List<String> perkRequirementsList = new ArrayList<>();
	public List<String> getPerkRequirements(GameCharacter character) {
		perkRequirementsList.clear();

		return perkRequirementsList;
	}

	public String getName(GameCharacter owner) {
		return name;
	}
	
	public String getShortDescriptor() {
		return shortDescriptor;
	}

	public abstract String getDescription(GameCharacter target);
	
	public abstract String getFetishDesireDescription(GameCharacter target, FetishDesire desire);
	
	private static String getGenericFetishDesireDescription(GameCharacter target, FetishDesire desire, String descriptor) {
		switch(desire) {
			case ZERO_HATE:
				return UtilText.parse(target, "You absolutely hate "+descriptor+".");
			case ONE_DISLIKE:
				return UtilText.parse(target, "You don't like "+descriptor+".");
			case TWO_NEUTRAL:
				return UtilText.parse(target, "You are indifferent to "+descriptor+".");
			case THREE_LIKE:
				return UtilText.parse(target, "You like "+descriptor+".");
			case FOUR_LOVE:
				return UtilText.parse(target, "You love "+descriptor+".");
		}
		return "";
	}

	public int getExperienceGainFromSexAction() {
		return experienceGainFromSexAction;
	}
	
	public int getCost() {
		return 5;
	}

	public List<String> getModifiersAsStringList(GameCharacter owner) {
//		List<String> modListWithArousal = new ArrayList<>(modifiersList);
//		float arousalIncreaseOwner = owner.getFetishLevel(this).getBonusArousalIncrease();
//		float arousalIncreasePartner = owner.getFetishLevel(this).getBonusArousalIncreasePartner();
//		
//		modListWithArousal.add("<b>"+(arousalIncreaseOwner >= 0 ? "+" : "") + arousalIncreaseOwner + "</b> <b style='color: "+ Attribute.AROUSAL.getColour().toWebHexString()+ ";'> Self-"+ Attribute.AROUSAL.getAbbreviatedName()+ "</b>");
//		modListWithArousal.add("<b>"+(arousalIncreasePartner >= 0 ? "+" : "") + arousalIncreasePartner + "</b> <b style='color: "+ Attribute.AROUSAL.getColour().toWebHexString()+ ";'> Partner "+ Attribute.AROUSAL.getAbbreviatedName()+ "</b>");
		
		return modifiersList;
	}

	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public String applyPerkGained(GameCharacter character) {
		return "";
	}

	public String applyPerkLost(GameCharacter character){
		return "";
	}

	public Fetish getPreviousLevelPerk() {
		return null;
	}

	public Perk getNextLevelPerk() {
		return null;
	}
	
	public CorruptionLevel getAssociatedCorruptionLevel() {
		return CorruptionLevel.ZERO_PURE;
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString() {
		return SVGString;
	}
	
	public static int getExperienceGainFromTakingVaginalVirginity(GameCharacter owner) {
		return owner.getLevel()*2;
	}
	
	public static int getExperienceGainFromTakingOtherVirginity(GameCharacter owner) {
		return owner.getLevel();
	}
}
