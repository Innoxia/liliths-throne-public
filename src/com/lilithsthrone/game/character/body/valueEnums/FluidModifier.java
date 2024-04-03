package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.83
 * @version 0.4.4
 * @author Innoxia
 */
public enum FluidModifier {
	
	VISCOUS(PresetColour.BASE_PURPLE_DARK,
			false,
			"viscous",
			"It's quite viscous, and slowly drips in large globules, much like thick treacle.",
			"Gives this fluid a thick consistency, somewhere between liquid and solid."),
	
	STICKY(PresetColour.BASE_YELLOW_LIGHT,
			false,
			"sticky",
			"It's quite sticky, and is difficult to fully wash off without soap.",
			"Makes this fluid stick to anything it touches."),
	
	SLIMY(PresetColour.BASE_BLUE_LIGHT,
			false,
			"slimy",
			"It has a slimy, oily texture.",
			"Gives this fluid a slimy feel to it."),
	
	BUBBLING(PresetColour.BASE_LILAC_LIGHT,
			false,
			"bubbling",
			"It fizzes and bubbles like a carbonated drink.",
			"Makes this fluid bubble like a carbonated drink."),
	
	// SPECIAL EFFECTS:

	MUSKY(PresetColour.BASE_TAN,
			true,
			"musky",
			"It has a strong, musky smell.",
			"Musky cum and girlcum will apply 'marked by musk' to anyone who is covered in it during sex."),
	
	
	MINERAL_OIL(PresetColour.BASE_BLACK,
			true,
			"mineral oil",
			"It is rich in minerals good for your skin but not for latex.",
			"Fluids imbued with mineral oil will rapidly degrade condoms, causing them to break at the moment of orgasm."),
	
	ALCOHOLIC(PresetColour.BASE_ORANGE,
			true,
			"strongly alcoholic",
			"It has a high alcohol content, and will get those who consume it very drunk.",
			"Strongly alcoholic fluids will greatly increase the intoxication level of anyone who consumes them.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.001f); //TODO factor in body size
		}
	},

	ALCOHOLIC_WEAK(PresetColour.BASE_ORANGE_LIGHT,
			true,
			"alcoholic",
			"It has a low alcohol content, and will get those who consume it drunk.",
			"Alcoholic fluids will increase the intoxication level of anyone who consumes them.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.0001f); //TODO factor in body size
		}
	},
	
	ADDICTIVE(PresetColour.BASE_PINK,
			true,
			"addictive",
			"It is highly addictive, and anyone who drinks too much will quickly become dependent on it.",
			"Addictive fluids will make anyone who consumes them become addicted to that particular type of fluid.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target==null || fluidProvider==null) {
				return ""; // catch for if one of the characters is null, which was the case in GameCharacter.calculateGenericSexEffects
			}
			if(target.isDoll()) {
				return "";
			}
			boolean curedWithdrawal = target.getAddiction(fluid.getType())!=null && Main.game.getMinutesPassed()-target.getAddiction(fluid.getType()).getLastTimeSatisfied()>=24*60;
			boolean appendAddiction = !Main.game.isInSex() || curedWithdrawal;
			if(target.addAddiction(new Addiction(fluid.getType(), Main.game.getMinutesPassed(), fluidProvider.getId()))) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "Due to the addictive properties of "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(find)] [npc.herself] [style.colourArcane(craving)]"
								+ " <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))+"</span> "+fluid.getName(fluidProvider)+"!"
						+ "</p>");
				
				
			} else {
				target.setLastTimeSatisfiedAddiction(fluid.getType(), Main.game.getMinutesPassed());
				if(appendAddiction) {
					return UtilText.parse(target, fluidProvider,
							"<p style='padding:0; margin:0; text-align:center;'>"
								+ "[npc.NamePos] [style.colourArcane(craving)] for <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"
									+fluid.getType().getRace().getName(fluidProvider.getBody(), fluid.isFeral(fluidProvider))
								+"</span> "+fluid.getName(fluidProvider)
									+" has been satisfied!"
								+ (curedWithdrawal
									?" [npc.She] [npc.verb(feel)] deeply grateful to "+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+" for providing [npc.herHim] with what [npc.she] needed most..."
											+ (target.isSlave()?target.incrementObedience(5):"")
									:" [npc.She] [npc.was]n't suffering from withdrawal, but [npc.she] still [npc.verb(feel)] thankful to "
											+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+" for feeding [npc.her] addiction...")
							+ "</p>");
				}
				return "";
			}
		}
	},
	
	HALLUCINOGENIC(PresetColour.BASE_PINK_DEEP,
			true,
			"psychoactive",
			"Anyone who ingests it suffers psychoactive effects, which can manifest in lactation-related hallucinations or sensitivity to hypnotic suggestion.",
			"Psychoactive fluids will cause anyone who ingests them to experience a hallucinogenic trip, causing their view of sexual organs to be distorted as well as opening them up to the possibility of being hypnotically manipulated.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target.isDoll()) {
				return "";
			}
			target.addPsychoactiveFluidIngested(fluid.getType());
			boolean appendPsychoactive = !target.hasStatusEffect(StatusEffect.PSYCHOACTIVE);
			target.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60*60);
			if(appendPsychoactive) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "Due to the psychoactive properties of "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(start)] <span style='color:"+PresetColour.PSYCHOACTIVE.toWebHexString()+";'>tripping out</span>!"
						+ "</p>");
			}
			return "";
		}
	};
	
	private Colour colour;
	private boolean specialEffects;
	private String name;
	private String description;
	private String briefDescription;
	
	private FluidModifier(Colour colour, boolean specialEffects, String name, String briefDescription, String description) {
		this.colour = colour;
		this.specialEffects = specialEffects;
		this.name = name;
		this.briefDescription = briefDescription;
		this.description = description;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isSpecialEffects() {
		return specialEffects;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBriefDescription() {
		return briefDescription;
	}
	
	public String getDescription() {
		return description;
	}

	public boolean isAppliesSpecialEffects() {
		return description!=null;
	}
	
	public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
		return "";
	}
}
