package com.base.game.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.base.game.character.attributes.Attribute;
import com.base.game.character.body.valueEnums.Capacity;
import com.base.game.character.gender.Gender;
import com.base.game.sex.OrificeType;
import com.base.game.sex.PenetrationType;
import com.base.game.sex.SexType;
import com.base.utils.Colour;
import com.base.utils.Util;
import com.base.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.1.78
 * @author Innoxia
 */
public enum History {

	// Neutral:
	NEUTRAL("Average", "You're average in every way.", Util.newHashMapOfValues()),

	// Good:
	STRONG("Strong", "You work out at the gym almost every day. You're stronger than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STRENGTH, 5))),

	STUDIOUS("Intelligent", "You spend a lot of time reading and studying. You're more intelligent than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.INTELLIGENCE, 5))),

	HEALTHY("Healthy", "You make sure to stick to a very healthy diet and go out running every day. As a result, you're fitter than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FITNESS, 5))),

	INNOCENT("Innocent", "People don't <i>really</i> have sex before marriage, right?!"
			+ " But I suppose if they only do it once, it's ok, as you can't get pregnant from the first time!", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CORRUPTION, -5))),
	
	// Bad:
	WEAK("Weak", "You've got a small frame and puny muscles. You're weaker than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.STRENGTH, -5))),

	BIRD_BRAIN("Bird brain", "You sometimes forget what you were doing halfway throu- Ooh a penny! You're less intelligent than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.INTELLIGENCE, -5))),

	UNFIT("Unfit", "You've never done any excercise in your life, leaving you quite out of shape. You are less fit than an average person.", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FITNESS, -5))),

	// Other:
	TOWN_BIKE("Slut", "You've lost count of the amount of guys (and sometimes girls) that you've slept with. You have a lot of experience with flirting and seducing people." + " <span style='color:" + Colour.GENERIC_SEX.()
			+ ";'>You start the game having already lost your virginity.</span>", Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_MANA, 5))) {
		@Override
		public void applyExtraEffects(GameCharacter character) {
			character.setVaginaVirgin(false);
			character.setAssVirgin(false);
			character.setFaceVirgin(false);

			if (character.isPlayer()) {
				character.getStats().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), "your first boyfriend in the park");
				character.getStats().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 130 + Util.random.nextInt(50));
				character.getStats().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 60 + Util.random.nextInt(40));


				character.getStats().setVirginityLoss(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.VAGINA_PARTNER), "your first girlfriend as she lay back on her bed");
				character.getStats().setSexCount(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.VAGINA_PARTNER), 5 + Util.random.nextInt(20));
				

				character.getStats().setVirginityLoss(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.VAGINA_PLAYER), "your first girlfriend after you did the same for her");
				character.getStats().setSexCount(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.VAGINA_PLAYER), 5 + Util.random.nextInt(15));
				

				character.getStats().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), "some guy in a club's toilet cubicle");
				character.getStats().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 60 + Util.random.nextInt(30));
				character.getStats().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 20 + Util.random.nextInt(20));
				

				character.getStats().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), "some guy in your first threesome");
				character.getStats().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), 5 + Util.random.nextInt(10));
				character.getStats().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), 2 + Util.random.nextInt(5));
			}

			character.setAssCapacity(Capacity.THREE_SLIGHTLY_LOOSE.getMedianValue());
			character.setVaginaCapacity(Capacity.FOUR_LOOSE.getMedianValue());
		}

		@Override
		public void revertExtraEffects(GameCharacter character) {
			character.setVaginaVirgin(true);
			character.setAssVirgin(true);
			character.setFaceVirgin(true);

			if (character.isPlayer()) {
				character.getStats().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), "");
				character.getStats().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 0);
				character.getStats().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.MOUTH_PLAYER), 0);


				character.getStats().setVirginityLoss(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.VAGINA_PARTNER), "");
				character.getStats().setSexCount(new SexType(PenetrationType.TONGUE_PLAYER, OrificeType.VAGINA_PARTNER), 0);
				

				character.getStats().setVirginityLoss(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.VAGINA_PLAYER), "");
				character.getStats().setSexCount(new SexType(PenetrationType.TONGUE_PARTNER, OrificeType.VAGINA_PLAYER), 0);
				

				character.getStats().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), "");
				character.getStats().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 0);
				character.getStats().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.VAGINA_PLAYER), 0);
				

				character.getStats().setVirginityLoss(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), "");
				character.getStats().setSexCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), 0);
				character.getStats().setCumCount(new SexType(PenetrationType.PENIS_PARTNER, OrificeType.ANUS_PLAYER), 0);
			}

			character.setAssCapacity(Capacity.ZERO_IMPENETRABLE.getMedianValue());
			character.setVaginaCapacity(Capacity.ONE_EXTREMELY_TIGHT.getMedianValue());

		}

		@Override
		public boolean isAvailable(GameCharacter player) {
			if (player.getGender() == Gender.FEMALE)
				return true;
			else
				return false;
		}
	};

	private String name, descriptionPlayer;
	// Attributes modified by this Trait:
	private HashMap<Attribute, Integer> attributeModifiers;

	private History(String name, String descriptionPlayer, HashMap<Attribute, Integer> attributeModifiers) {
		this.name = name;
		this.descriptionPlayer = descriptionPlayer;
		this.attributeModifiers = attributeModifiers;
	}

	private static List<History> historiesList;
	
	public static List<History> getAvailableHistories(GameCharacter character) {
		historiesList = new ArrayList<>();

		for(History history : History.values())
			if(history.isAvailable(character))
				historiesList.add(history);

		return historiesList;
	}

	public boolean isAvailable(GameCharacter character) {
		return true;
	}

	public void applyExtraEffects(GameCharacter character) {
	}

	public void revertExtraEffects(GameCharacter character) {
	}

	public String getName() {
		return name;
	}

	public String getDescriptionPlayer() {
		return descriptionPlayer;
	}

	private StringBuilder descriptionSB;

	public String getModifiersAsStringList() {
		descriptionSB = new StringBuilder();
		int i = 0;
		if (attributeModifiers != null)
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet()) {
				if (i != 0)
					descriptionSB.append("</br>");
				descriptionSB.append("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b> <b style='color:" + Colour.GENERIC_EXCELLENT.() + ";'>core</b> " + "<b style='color: " + e.getKey().getColour().() + ";'>"
						+ Util.capitaliseSentence(e.getKey().getName()) + "</b>");
				i++;
			}
		return descriptionSB.toString();
	}

	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}
}
