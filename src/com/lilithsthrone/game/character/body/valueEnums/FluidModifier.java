package com.lilithsthrone.game.character.body.valueEnums;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.FluidInterface;
import com.lilithsthrone.game.character.effects.Addiction;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.1.83
 * @version 0.3.6.5
 * @author Innoxia
 */
public enum FluidModifier {
	
	MUSKY(Colour.BASE_TAN,
			false,
			"musky",
			"Makes this fluid give off a heady, musky scent."),
	
	VISCOUS(Colour.BASE_PURPLE_DARK,
			false,
			"viscous",
			"Gives this fluid a thick consistency, somewhere between liquid and solid."),
	
	STICKY(Colour.BASE_YELLOW_LIGHT,
			false,
			"sticky",
			"Makes this fluid stick to anything it touches."),
	
	SLIMY(Colour.BASE_BLUE_LIGHT,
			false,
			"slimy",
			"Gives this fluid a slimy feel to it."),
	
	BUBBLING(Colour.BASE_LILAC_LIGHT,
			false,
			"bubbling", "Makes this fluid bubble like a carbonated drink."),
	
	// SPECIAL EFFECTS:
	
	MINERAL_OIL(Colour.BASE_BLACK,
			true,
			"mineral oil",
			"Fluids imbued with mineral oil will rapidly degrade condoms, causing them to break at the moment of orgasm."),
	
	ALCOHOLIC(Colour.BASE_ORANGE,
			true,
			"alcoholic",
			"Alcoholic fluids will increase the intoxication level of anyone who consumes them.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			return target.incrementAlcoholLevel(millilitres * 0.001f); //TODO factor in body size
		}
	},
	
	ADDICTIVE(Colour.BASE_PINK,
			true,
			"addictive",
			"Addictive fluids will make anyone who consumes them become addicted to that particular type of fluid.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			if(target.getAddiction(fluid.getType()) == null) {
				target.addAddiction(new Addiction(fluid.getType(), Main.game.getMinutesPassed(), fluidProvider.getId()));
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "Due to the addictive properties of "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(find)] [npc.herself] [style.colourArcane(craving)]"
								+ " <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"+fluid.getType().getRace().getName(fluidProvider, fluid.isBestial(fluidProvider))+"</span> "+fluid.getName(fluidProvider)+"!"
						+ "</p>");
				
				
			} else {
				boolean curedWithdrawal = Main.game.getMinutesPassed()-target.getAddiction(fluid.getType()).getLastTimeSatisfied()>=24*60;
				boolean appendAddiction = !Main.game.isInSex() || curedWithdrawal;
				target.setLastTimeSatisfiedAddiction(fluid.getType(), Main.game.getMinutesPassed());
				if(appendAddiction) {
					return UtilText.parse(target, fluidProvider,
							"<p style='padding:0; margin:0; text-align:center;'>"
								+ "[npc.NamePos] [style.colourArcane(craving)] for <span style='color:"+fluid.getType().getRace().getColour().toWebHexString()+";'>"
									+fluid.getType().getRace().getName(fluidProvider, fluid.isBestial(fluidProvider))
								+"</span> "+fluid.getName(fluidProvider)
									+" has been satisfied!"
								+ (curedWithdrawal
									?" [npc.She] [npc.verb(feel)] deeply grateful to "+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.namePos]"))+" for providing [npc.herHim] with what [npc.she] needed most..."
											+ (target.isSlave()?target.incrementObedience(5):"")
									:" [npc.She] [npc.was]n't suffering from withdrawal, but [npc.she] still [npc.verb(feel)] thankful to "
											+(fluidProvider==null?"":UtilText.parse(fluidProvider, "[npc.name]"))+" for feeding [npc.her] addiction...")
							+ "</p>");
				}
				return "";
			}
		}
	},
	
	HALLUCINOGENIC(Colour.BASE_PINK_DEEP,
			true,
			"psychoactive",
			"Psychoactive fluids will cause anyone who ingests them to experience a hallucinogenic trip, causing their view of sexual organs to be distorted as well as opening them up to the possibility of being hypnotically manipulated.") {
		@Override
		public String applyEffects(GameCharacter target, GameCharacter fluidProvider, float millilitres, FluidInterface fluid) {
			target.addPsychoactiveFluidIngested(fluid.getType());
			boolean appendPsychoactive = !target.hasStatusEffect(StatusEffect.PSYCHOACTIVE);
			target.addStatusEffect(StatusEffect.PSYCHOACTIVE, 6*60*60);
			if(appendPsychoactive) {
				return UtilText.parse(target,
						"<p style='padding:0; margin:0; text-align:center;'>"
							+ "Due to the psychoactive properties of "+(fluidProvider==null?"":(fluidProvider.equals(target)?"[npc.her]":UtilText.parse(fluidProvider, "[npc.namePos]")))+" "+fluid.getName(fluidProvider)
								+", [npc.name] [npc.verb(start)] <span style='color:"+Colour.PSYCHOACTIVE.toWebHexString()+";'>tripping out</span>!"
						+ "</p>");
			}
			return "";
		}
	};
	
	private Colour colour;
	private boolean specialEffects;
	private String name;
	private String description;
	
	private FluidModifier(Colour colour, boolean specialEffects, String name, String description) {
		this.colour = colour;
		this.specialEffects = specialEffects;
		this.name = name;
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
