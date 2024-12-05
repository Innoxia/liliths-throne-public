package com.lilithsthrone.game.character.body;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.abstractTypes.AbstractAssType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.4.9.7
 * @author Innoxia
 */
public class Ass implements BodyPartInterface {

	protected AbstractAssType type;
	protected int assSize;
	protected int hipSize;
	
	protected Anus anus;

	public Ass(AbstractAssType type, int assSize, int hipSize, int wetness, float capacity, int depth, int elasticity, int plasticity, boolean virgin) {
		this.type = type;
		this.assSize = assSize;
		this.hipSize = hipSize;
		
		anus = new Anus(type.getAnusType(), wetness, capacity, depth, elasticity, plasticity, virgin);
	}

	public Ass(Ass assToCopy) {
		this.type = assToCopy.type;
		this.assSize = assToCopy.assSize;
		this.hipSize = assToCopy.hipSize;
		
		this.anus = new Anus(assToCopy.anus);
	}
	
	public Anus getAnus() {
		return anus;
	}
	
	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
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
	public String getDescriptor(GameCharacter gc) {
		List<String> list = new ArrayList<>();
		
		list.add(type.getDescriptor(gc));
		list.add(this.getAssSize().getDescriptor());

		if(gc.getBodyMaterial().getPartDescriptors()!=null && !gc.getBodyMaterial().getPartDescriptors().isEmpty()) {
			list.add(Util.randomItemFrom(gc.getBodyMaterial().getPartDescriptors()));
		}
		
		return Util.randomItemFrom(list);
	}

	@Override
	public AbstractAssType getType() {
		return type;
	}

	public String setType(GameCharacter owner, AbstractAssType type) {
		if(!Main.game.isStarted() || owner==null) {
			this.type = type;
			anus.setType(type.getAnusType());
			if(owner!=null) {
				owner.resetAreaKnownByCharacters(CoverableArea.ANUS);
				owner.postTransformationCalculation();
			}
			return "";
		}
		
		if (type == getType()) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already [npc.has] the ass of [npc.a_assRace], so nothing happens...)]</p>");
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(
				"<p>"
				+ "[npc.NamePos] [npc.verb(feel)] [npc.her] [npc.ass] suddenly softening and becoming very sensitive, and [npc.she] [npc.verb(let)] out [npc.a_moan+] as the intense feeling moves down into [npc.her] [npc.asshole]."
				+ " Panting and sighing, [npc.she] [npc.verb(continue)] letting out the occasional involuntary [npc.moan] as [npc.she] [npc.verb(feel)] [npc.her] entire rear-end transform.<br/>");
		
		// Parse existing content before transformation:
		String s = UtilText.parse(owner, sb.toString());
		sb.setLength(0);
		sb.append(s);
		this.type = type;
		anus.setType(type.getAnusType());
		owner.resetAreaKnownByCharacters(CoverableArea.ANUS);

		sb.append(type.getTransformationDescription(owner)+"</p>");
		
		return UtilText.parse(owner, sb.toString())
				+ "<p>"
				+ owner.postTransformationCalculation(false)
				+ "</p>";
	}
	
	public AssSize getAssSize() {
		return AssSize.getAssSizeFromInt(assSize);
	}

	/**
	 * Sets assSize attribute.<br/>
	 * Value is bound to >=0 && <=AssSize.SEVEN_GIGANTIC.getValue()
	 * 
	 * @param owner The character to change.
	 * @param assSize Value to set assSize to.
	 * @return Description of the change.
	 */
	public String setAssSize(GameCharacter owner, int assSize) {
		int oldSize = this.assSize;
		this.assSize = Math.max(0, Math.min(assSize, AssSize.SEVEN_GIGANTIC.getValue()));
		
		if(owner==null) {
			return "";
		}
		
		int sizeChange = this.assSize - oldSize;
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] ass doesn't change...)]</p>");
		}
		
		String sizeDescriptor = getAssSize().getDescriptor();
		if (sizeChange > 0) {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You feel a little off-balance as your [pc.ass] suddenly seems to get heavier, and as you give it an experimental shake, you find that it's definitely [style.boldGrow(grown larger)].<br/>"
							+ "You now have [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] looks to be a little off-balance as [npc.her] [npc.ass] suddenly seems to get bigger, and as [npc.she] gives it an experimental shake, [npc.she] discovers that it's definitely [style.boldGrow(grown larger)].<br/>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>");
			}
		} else {
			if (owner.isPlayer()) {
				return "<p>"
							+ "You suddenly feel like a weight has been lifted from your rear end, and, giving your [pc.ass] an experimental shake, you discover that it's [style.boldShrink(shrunk)].<br/>"
							+ "You now have [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>";
			} else {
				return UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] looks to be a little off-balance as [npc.her] [npc.ass] suddenly seems to get smaller, and as [npc.she] gives it an experimental shake, [npc.she] discovers that it's definitely [style.boldShrink(shrunk)].<br/>"
							+ "[npc.She] now has [style.boldSex(" + UtilText.generateSingularDeterminer(sizeDescriptor) + " " + sizeDescriptor + " ass)]!"
						+ "</p>");
			}
		}
	}

	public HipSize getHipSize() {
		return HipSize.getHipSizeFromInt(hipSize);
	}
	
	/**
	 * Sets hipSize attribute.<br/>
	 * Value is bound to >=0 && <=HipSize.SEVEN_ABSURDLY_WIDE.getValue()
	 * 
	 * @param owner The character to change.
	 * @param hipSize Value to set hipSize to.
	 * @return Description of the change.
	 */
	public String setHipSize(GameCharacter owner, int hipSize) {
		int oldSize = this.hipSize;
		this.hipSize = Math.max(0, Math.min(hipSize, HipSize.SEVEN_ABSURDLY_WIDE.getValue()));
		int sizeChange = this.hipSize - oldSize;
		
		if(owner==null) {
			return "";
		}
		
		if (sizeChange == 0) {
			return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The size of [npc.namePos] hips doesn't change...)]</p>");
		}
		
		String styledSizeDescriptor = "[style.boldSex("+ getHipSize().getDescriptor() + " hips)]";
		if (sizeChange > 0) {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(inhale)] sharply in surprise as [npc.she] [npc.verb(feel)] [npc.her] hips reshape themselves, pushing out and [style.boldGrow(growing wider)].<br/>"
						+ "[npc.She] now [npc.has] " + styledSizeDescriptor + "!"
					+ "</p>");
				
		} else {
			return UtilText.parse(owner,
					"<p>"
						+ "[npc.Name] [npc.verb(inhale)] sharply in surprise as [npc.she] [npc.verb(feel)] [npc.her] hips collapse inwards and reshape themselves as they get [style.boldShrink(narrower)].<br/>"
						+ "[npc.She] now [npc.has] " + styledSizeDescriptor + "!"
					+ "</p>");
		}
	}

	@Override
	public boolean isFeral(GameCharacter owner) {
		if(owner==null) {
			return false;
		}
		return owner.isFeral() || (owner.getLegConfiguration().getFeralParts().contains(Ass.class) && getType().getRace().isFeralPartsAvailable());
	}
	
}
