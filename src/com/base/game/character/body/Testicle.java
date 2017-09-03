package com.base.game.character.body;

import java.io.Serializable;
import com.base.game.character.GameCharacter;
import com.base.game.character.body.types.TesticleType;
import com.base.game.character.body.valueEnums.CumProduction;
import com.base.game.character.body.valueEnums.TesticleSize;
import com.base.game.dialogue.utils.UtilText;
import com.base.utils.Util;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class Testicle implements BodyPartInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int MIN_TESTICLE_COUNT = 2;
	public static final int MAX_TESTICLE_COUNT = 8;
	
	private TesticleType type;
	private int testicleSize;
	private int cumProduction;
	private int testicleCount;
	private boolean internal;
	
	private FluidCum cum;

	public Testicle(TesticleType type, int testicleSize, int cumProduction, int testicleCount) {
		this.type = type;
		this.testicleSize = testicleSize;
		this.cumProduction = cumProduction;
		
		this.testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		internal = type.isInternal();
		
		cum = new FluidCum(type.getFluidType());
	}

	public FluidCum getCum() {
		return cum;
	}

	@Override
	public TesticleType getType() {
		return type;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getTesticleCount()==2) {
			return "a pair of";
		} else if(gc.getTesticleCount()==3) {
			return "a trio of";
		} else {
			return Util.intToString(gc.getTesticleCount());
		}
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}

	@Override
	public String getDescriptor(GameCharacter owner) {
		return type.getDescriptor(owner);
	}
	
	public void setType(GameCharacter owner, TesticleType type) {
		this.type = type;
		cum.setType(type.getFluidType());
	}
	
	public TesticleSize getTesticleSize() {
		return TesticleSize.getTesticleSizeFromInt(testicleSize);
	}

	public String setTesticleSize(GameCharacter owner, int testicleSize) {
		if(!owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int oldSize = this.testicleSize;
		this.testicleSize = Math.max(0, Math.min(testicleSize, TesticleSize.SEVEN_ABSURD.getValue()));
		int sizeChange = this.testicleSize - oldSize;
		
		if (sizeChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The size of your [pc.balls] doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.name]'s [npc.balls] doesn't change...)]</p>");
			}
		} else if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You let out a lewd moan as you feel your [pc.balls] suddenly swell and [style.boldGrow(grow larger)].</br>"
							+ "You now have [style.boldSex(" +owner.getTesticleSize().getDescriptor()+ " [pc.balls])]!"
						+ "</p>";
			} else {
				return UtilText.genderParsing(owner,
						"</p>"
							+ "[npc.Name] lets out a lewd moan as [npc.she] feels [npc.her] [npc.balls] suddenly swell and [style.boldGrow(grow larger)].</br>"
							+ "[npc.She] now has [style.boldSex(" +owner.getTesticleSize().getDescriptor()+ " [npc.balls])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You let out a surprised gasp as you feel your [pc.balls] suddenly [style.boldShrink(shrink)].</br>"
							+ "You now have [style.boldSex(" +owner.getTesticleSize().getDescriptor()+ " [pc.balls])]!"
						+ "</p>";
			} else {
				return UtilText.genderParsing(owner,
						"</p>"
							+ "[npc.Name] lets out a surprised gasp as [npc.she] feels [npc.her] [npc.balls] suddenly [style.boldShrink(shrink)].</br>"
							+ "[npc.She] now has [style.boldSex(" +owner.getTesticleSize().getDescriptor()+ " [npc.balls])]!"
						+ "</p>");
			}
		}
	}

	// Cum production:

	public CumProduction getCumProduction() {
		return CumProduction.getCumProductionFromInt(cumProduction);
	}

	public int getRawCumProductionValue() {
		return cumProduction;
	}

	public String setCumProduction(GameCharacter owner, int cumProduction) {
		
		if(!owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		int cumProductionChange = 0;
		
		if (cumProduction <= 0) {
			if (this.cumProduction != 0) {
				cumProductionChange = 0 - this.cumProduction;
				this.cumProduction = 0;
			}
		} else if (cumProduction >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
			if (this.cumProduction != CumProduction.SEVEN_MONSTROUS.getMaximumValue()) {
				cumProductionChange = CumProduction.SEVEN_MONSTROUS.getMaximumValue() - this.cumProduction;
				this.cumProduction = CumProduction.SEVEN_MONSTROUS.getMaximumValue();
			}
		} else {
			if (this.cumProduction != cumProduction) {
				cumProductionChange = cumProduction - this.cumProduction;
				this.cumProduction = cumProduction;
			}
		}
		
		if(cumProductionChange == 0) {
			if(owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(The amount of [pc.cum] that you're producing doesn't change...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The amount of [npc.cum] that [npc.name] is producing doesn't change...)]</p>");
			}
		}
		
		String cumProductionDescriptor = getCumProduction().getDescriptor();
		if (cumProductionChange > 0) {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You feel your [pc.balls] grow heavier and fill up as your cum production [style.boldGrow(increases)].</br>"
							+ "You are now producing [style.boldSex(" + cumProductionDescriptor + " [pc.cum])]!"
						+ "</p>";
			} else {
				return UtilText.genderParsing(owner,
						"</p>"
							+ "[npc.Name] feels [npc.her] [npc.balls] grow heavier and fill up as [npc.her] cum production [style.boldGrow(increases)].</br>"
							+ "[npc.Name] is now producing [style.boldSex(" + cumProductionDescriptor + " [npc.cum])]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "</p>"
							+ "You feel your [pc.balls] get lighter as your cum production [style.boldShrink(decreases)].</br>"
							+ "You are now producing [style.boldSex(" + cumProductionDescriptor + " [pc.cum])]!"
						+ "</p>";
			} else {
				return UtilText.genderParsing(owner,
						"</p>"
							+ "[npc.Name] feels [npc.her] [npc.balls] get lighter as [npc.her] cum production [style.boldShrink(decreases)].</br>"
							+ "[npc.Name] is now producing [style.boldSex(" + cumProductionDescriptor + " [npc.cum])]!"
						+ "</p>");
			}
		}
	}

	public int getTesticleCount() {
		return testicleCount;
	}

	public String setTesticleCount(GameCharacter owner, int testicleCount) {
		testicleCount = Math.max(MIN_TESTICLE_COUNT, Math.min(testicleCount, MAX_TESTICLE_COUNT));
		
		if(owner.getTesticleCount() == testicleCount || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		boolean removingTesticles = this.testicleCount < testicleCount;
		this.testicleCount = testicleCount;
		
		if(removingTesticles) {
			if(owner.isPlayer()) {
				return "<p>"
							+ "A tingling feeling spreads down into your [pc.balls], and you let out a little cry as you feel some of them shrinking away and [style.boldShrink(disappearing)].</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_balls])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads down into [npc.name]'s [npc.balls], and [npc.she] lets out a little cry as [npc.she] feels some of them shrinking away and [style.boldShrink(disappearing)].</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric([npc.a_balls])]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>"
							+ "A tingling feeling spreads down into your [pc.balls], and you let out a little cry as you feel them [style.boldGrow(multiplying)].</br>"
							+ "After a few moments, you're left with [style.boldTfGeneric([pc.a_balls])]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "A tingling feeling spreads down into [npc.name]'s [npc.balls], and [npc.she] lets out a little cry as [npc.she] feels them [style.boldGrow(multiplying)].</br>"
							+ "After a few moments, [npc.she]'s left with [style.boldTfGeneric([npc.a_balls])]."
						+ "</p>");
			}
		}
	}

	public boolean isInternal() {
		return internal;
	}

	public String setInternal(GameCharacter owner, boolean internal) {
		
		if(owner.isInternalTesticles() == internal || !owner.hasPenis()) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		this.internal = internal;
		
		if(internal) {
			if(owner.isPlayer()) {
				return "<p>"
							+ "You feel your [pc.balls] tightening and [style.boldShrink(withdrawing)] up into your groin, and you let out a little cry as you feel them shift up inside your body.</br>"
							+ "Your [pc.balls+] [style.boldTfGeneric(are now internal)]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels [npc.her] [npc.balls] tightening and [style.boldShrink(withdrawing)] up into [npc.her] groin, and [npc.she] lets out a little cry as [npc.she] feels them shift up inside [npc.her] body.</br>"
							+ "[npc.Her] [npc.balls+] [style.boldTfGeneric(are now internal)]."
						+ "</p>");
			}
			
		} else {
			if(owner.isPlayer()) {
				return "<p>"
							+ "You feel your internal [pc.balls] slackening and [style.boldGrow(dropping down)], and you let out a little cry as they settle down into an external sack.</br>"
							+ "Your [pc.balls+] [style.boldTfGeneric(are now external)]."
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] feels [npc.her] internal [npc.balls] slackening and [style.boldGrow(dropping down)], and [npc.she] lets out a little cry as they settle down into an external sack.</br>"
							+ "[npc.Her] [npc.balls+] [style.boldTfGeneric(are now external)]."
						+ "</p>");
			}
		}
	}
}