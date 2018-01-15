package com.lilithsthrone.game.character.effects;

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
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;
import com.lilithsthrone.utils.Util.Value;
/**
 * @since 0.1.?
 * @version 0.1.82
 * @author Innoxia
 */
public enum Fetish {
	
	// FETISHES:

	// Sex types:
	FETISH_ANAL_GIVING(60,
			"anal",
			"fetish_anal_giving",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>anal tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>buttslut tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_ANAL_RECEIVING(60,
			"buttslut",
			"fetish_anal_receiving",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>buttslut tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>anal tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_ORAL_RECEIVING(60,
			"oral",
			"fetish_oral_receiving",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral performer tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_ORAL_GIVING(60,
			"oral performer",
			"fetish_oral_giving",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral performer tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>oral tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_BREASTS_OTHERS(60,
			"breasts lover",
			"fetish_breasts_others",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts lover tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_BREASTS_SELF(60,
			"breasts",
			"fetish_breasts_self",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>breasts lover tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_CUM_STUD(60,
			"cum stud",
			"fetish_cum",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum stud tease</span> (Requires penis)"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum addict tease</span>")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love cumming. You don't really care where it goes; all you want is to pump out load after load of your salty seed...";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for cumming.");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_CUM_ADDICT(60,
			"cum addict",
			"fetish_cum_addict",
			Colour.CLOTHING_WHITE,
			null,
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum addict tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>cum stud tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_DEFLOWERING(60,
			"deflowering",
			"fetish_deflowering",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(new ListValue<>("Gain <span style='color:"+ Colour.GENERIC_EXPERIENCE.toWebHexString()+ ";'>xp</span> from <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>taking virginities</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
	},
	
	FETISH_PURE_VIRGIN(60,
			"virginity",
			"fetish_virginity",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Gain</span> <span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>'pure virgin'</span>"),
					new ListValue<>("<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Suffer</span> <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>'broken virgin'</span>")),
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
			"fetish_masturbation",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.TEXT_GREY.toWebHexString()+ ";'>No special abilities</span>")),
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
			"fetish_pregnancy",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, 5)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"
					+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>fertility tease</span> (Requires vagina)"),
					new ListValue<>("<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Weak to</span> <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>virility tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_IMPREGNATION(60,
			"impregnation",
			"fetish_impregnation",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, 5)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"
					+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>virility tease</span> (Requires penis)"),
					new ListValue<>("<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Weak to</span> <span style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>fertility tease</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_BROODMOTHER(60,
			"broodmother",
			"fetish_broodmother",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, 10)),
			Util.newArrayListOfValues(new ListValue<>("2x <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Maximum offspring in mothered litters</span>")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your body is built for one thing; pumping out as many children as possible."
							+ " Whether due to an effect of your arcane aura, or perhaps just because of your body's natural fertility, you seem to always give birth to huge numbers of children at once.";
			} else {
				return UtilText.parse(owner, "[npc.Name]'s body is built for one thing; pumping out as many children as possible."
						+ " [npc.She] seems to always give birth to huge numbers of children at once.");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_SEEDER(60,
			"seeder",
			"fetish_seeder",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, 10)),
			Util.newArrayListOfValues(new ListValue<>("2x <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Maximum offspring in fathered litters</span>")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your seed has the potent effect of causing anyone impregnated by it to give birth to huge numbers of children.";
			} else {
				return UtilText.parse(owner, "[npc.Name]'s seed has the potent effect of causing anyone impregnated by it to give birth to huge numbers of children.");
			}
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	FETISH_TRANSFORMATION_GIVING(60,
			"transformer",
			"fetish_transformation_giving",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Halves cost of all potion making</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
	},
	
	FETISH_TRANSFORMATION_RECEIVING(60,
			"transformation test subject",
			"fetish_transformation_receiving",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Increases potency</span> <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>of receiving forced transformations</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	},
	
	// Behaviour (organised roughly in active/passive pairs):
	FETISH_BIMBO(60,
			"bimbo",
			"fetish_bimbo",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 10)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"
					+ Colour.GENERIC_SEX.toWebHexString()
					+ ";'>Talk like a bimbo</span>")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're obsessed with the idea of acting like a complete bimbo. It's gotten to the point where no matter how intelligent you might actually be, you can't imagine yourself as anything other than a braindead slut.";
			else
				return UtilText.parse(owner, "[npc.Name] loves acting like a complete bimbo.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},
	
	FETISH_CROSS_DRESSER(60,
			"cross dressing",
			"fetish_cross_dresser",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 10)),
			Util.newArrayListOfValues(new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Immune to clothing femininity status effects</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.ONE_VANILLA;
		}
	},

	FETISH_DENIAL(60,
			"denial",
			"fetish_denial",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Unlocks <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Deny</span> in non-submissive sex")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Either by teasing them with your body, or preventing them from orgasming, you love denying your partners during sex.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for orgasm denial.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	FETISH_DOMINANT(60,
			"dominant",
			"fetish_dominant",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 5)),
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>dominant tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>submissive tease</span>")),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love being the dominant partner during sex, and you know just how to show your partners who's in charge.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the dominant partner in sex.");
		}
		
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	FETISH_SUBMISSIVE(60,
			"submissive",
			"fetish_submissive",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STAMINA_MAXIMUM, 5)),
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Unlocks</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>submissive tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Weak to</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>dominant tease</span>"),
					new ListValue<>("Unlocks <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>submissive sex</span>")),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love being the submissive partner during sex. You'll do anything to show your submission, and will happily let your partner do whatever they want with you.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for being the submissive partner in sex.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	FETISH_INCEST(60,
			"incest",
			"fetish_incest",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("Unlocks <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>incest tease</span>"),
					new ListValue<>("<span style='color:"+ Colour.GENERIC_BAD.toWebHexString()+ ";'>Not yet implemented!</span>")),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You always did have the hots for your aunt Lily...";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for incestuous sex.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
	},
	FETISH_EXHIBITIONIST(60,
			"exhibitionist",
			"fetish_exhibitionist",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Replaces</span> <span style='color:"+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>exposed status effects</span>"
										+" <span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>with beneficial versions</span>")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love showing off your body, and the act of parading your naked form in public places turns you on like nothing else.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for exhibiting [npc.her] body.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	FETISH_MASOCHIST(60,
			"masochist",
			"fetish_masochist",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("40% of incoming <span style='color:"+ Colour.ATTRIBUTE_HEALTH.toWebHexString()+ ";'>health damage</span> is converted to <span style='color:"+ Colour.DAMAGE_TYPE_MANA.toWebHexString()+ ";'>willpower damage</span>")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love pain, and being treated like dirt sends you absolutely wild with lust.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for being treated like a worthless slut.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	FETISH_SADIST(60,
			"sadist",
			"fetish_sadist",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PURE, 5)),
			Util.newArrayListOfValues(
					new ListValue<>("You take 10% of dealt <span style='color:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>health damage</span> as "
										+ "<span style='color:"+ Colour.DAMAGE_TYPE_MANA.toWebHexString()+ ";'>willpower damage</span>")),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love dishing out pain and humiliation, and causing others to suffer sends you absolutely wild with lust.";
			else
				return UtilText.parse(owner, "[npc.Name] has a fetish for dealing out pain and humiliation.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_NON_CON_DOM(60,
			"non-consent",
			"fetish_noncon_dom",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Increases</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>arousal gain when partner is resisting sex</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FIVE_CORRUPT;
		}
	},
	
	FETISH_NON_CON_SUB(60,
			"unwilling fuck-toy",
			"fetish_noncon_sub",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Increases</span> <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>arousal gain when you are resisting sex</span>")),
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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.FOUR_LUSTFUL;
		}
	},
	
	// Derived fetishes:
	
	FETISH_SWITCH(60,
			"switch",
			"fetish_switch",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 10),
					new Value<Attribute, Integer>(Attribute.STAMINA_MAXIMUM, 10)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>(Fetish.FETISH_DOMINANT),
					new ListValue<>(Fetish.FETISH_SUBMISSIVE))) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're perfectly happy with switching between dom and sub as the situation calls for it.";
			else
				return UtilText.parse(owner, "[npc.Name] is happy to play as either the dom or sub during sex.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_BREEDER(60,
			"breeder",
			"fetish_breeder",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.FERTILITY, 25),
					new Value<Attribute, Integer>(Attribute.VIRILITY, 25)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>(Fetish.FETISH_PREGNANCY),
					new ListValue<>(Fetish.FETISH_IMPREGNATION),
					new ListValue<>(Fetish.FETISH_BROODMOTHER),
					new ListValue<>(Fetish.FETISH_SEEDER))) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a dream. A dream of a world in which everyone is pregnant, including yourself!";
			else
				return UtilText.parse(owner, "[npc.Name] wants nothing more than to share [npc.her] love of pregnancies with everyone [npc.she] meets.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
	FETISH_SADOMASOCHIST(60,
			"sadomasochist",
			"fetish_sadomasochist",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PURE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_PURE, 5)),
			null,
			Util.newArrayListOfValues(
					new ListValue<>(Fetish.FETISH_SADIST),
					new ListValue<>(Fetish.FETISH_MASOCHIST))) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You don't care whether you're on the giving or receiving end; if there's pain and humiliation involved, you're up for anything.";
			else
				return UtilText.parse(owner, "[npc.Name] loves pain and humiliation in all of its forms.");
		}
		@Override
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.THREE_DIRTY;
		}
	},
	
//	FETISH_HUMILIATION(60,
//			"humiliation",
//			false,
//			"fetish_humiliation",
//			Colour.GENERIC_ARCANE,
//			Util.newHashMapOfValues(
//					new Value<Attribute, Integer>(Attribute.RESISTANCE_PURE, 10)),
//			null,
//			Util.newArrayListOfValues(
//					new ListValue<>(Fetish.FETISH_EXHIBITIONIST),
//					new ListValue<>(Fetish.FETISH_MASOCHIST))) {
//
//		@Override
//		public String getDescription(GameCharacter owner) {
//			if (owner.isPlayer()) {
//				return "You love being humiliated and degraded, and if there's a crowd to watch, then that's all the better!";
//			} else {
//				return UtilText.parse(owner, "[npc.Name] loves to be publicly humiliated.");
//			}
//		}
//		@Override
//		public CorruptionLevel getAssociatedCorruptionLevel() {
//			return CorruptionLevel.THREE_DIRTY;
//		}
//	},
	
	FETISH_LUSTY_MAIDEN(60,
			"lusty maiden",
			"fetish_lusty_maiden",
			Colour.GENERIC_ARCANE,
			null,
			Util.newArrayListOfValues(
					new ListValue<>("<span style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>Empowers</span> <span style='color:" + Colour.GENERIC_EXCELLENT.toWebHexString() + ";'>'pure virgin'</span>"),
					new ListValue<>("<span style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>Amplifies</span> <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>'broken virgin'</span>")),
			Util.newArrayListOfValues(
					new ListValue<>(Fetish.FETISH_PURE_VIRGIN),
					new ListValue<>(Fetish.FETISH_ANAL_RECEIVING),
					new ListValue<>(Fetish.FETISH_ORAL_GIVING),
					new ListValue<>(Fetish.FETISH_BREASTS_SELF))) {

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
		public CorruptionLevel getAssociatedCorruptionLevel() {
			return CorruptionLevel.TWO_HORNY;
		}
	};
	
	private int renderingPriority;
	protected String name;
	
	private HashMap<Attribute, Integer> attributeModifiers;

	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;
	
	private List<Fetish> fetishesForAutomaticUnlock;

	private Fetish(
			int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			HashMap<Attribute, Integer> attributeModifiers,
			List<String> extraEffects,
			List<Fetish> fetishesForAutomaticUnlock) {

		this.renderingPriority = renderingPriority;
		this.name = name;
		
		this.attributeModifiers = attributeModifiers;

		this.extraEffects = extraEffects;
		
		if(fetishesForAutomaticUnlock==null) {
			this.fetishesForAutomaticUnlock = new ArrayList<>();
		} else {
			this.fetishesForAutomaticUnlock = fetishesForAutomaticUnlock;
		}
		
		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/fetishes/"
					+ pathName
					+ ".svg");
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

		if (attributeModifiers != null)
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>"
						+ (e.getValue() > 0 ? "+" : "")
						+ e.getValue()
						+ "</b>"
						+ " <b style='color: "
						+ e.getKey().getColour().toWebHexString()
						+ ";'>"
						+ Util.capitaliseSentence(e.getKey().getAbbreviatedName())
						+ "</b>");

		if (extraEffects != null)
			modifiersList.addAll(extraEffects);
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

	public abstract String getDescription(GameCharacter target);

	public int getCost() {
		return 5;
	}

	public List<String> getModifiersAsStringList() {
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
