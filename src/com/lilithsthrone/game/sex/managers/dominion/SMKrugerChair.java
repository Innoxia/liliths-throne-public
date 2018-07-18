package com.lilithsthrone.game.sex.managers.dominion;

import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.SexManagerDefault;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.69.9
 * @version 0.2.8
 * @author Innoxia
 */
public class SMKrugerChair extends SexManagerDefault {

	public SMKrugerChair(Map<GameCharacter, SexPositionSlot> dominants, Map<GameCharacter, SexPositionSlot> submissives) {
		super(SexPositionType.CHAIR_SEX_ORAL,
				dominants,
				submissives);
	}
	
	@Override
	public boolean isPlayerAbleToSwapPositions() {
		return false;
	}

	@Override
	public boolean isPositionChangingAllowed(GameCharacter character) {
		return !character.isPlayer();
	}
	
	@Override
	public boolean isPublicSex() {
		return true;
	}

	@Override
	public String getPublicSexStartingDescription() {
		return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
				+ "The lionesses and zebra-girls sitting around the booth start to giggle and touch themselves as they watch you about to get fucked by Kruger..."
				+ "</p>";
	}

	@Override
	public String getRandomPublicSexDescription() {
		List<String> descriptions = Util.newArrayListOfValues(
				"One of the lionesses lifts up her skirt, and, pulling her thong to one side, starts to finger herself while watching you and Kruger.",
				"Two of the zebra-girls lean back on the sofa and start making out with one another.",
				"You hear several of the lionesses who are watching you being to share some comments on your performance.",
				"You hear one of the zebra-girls let out an erotic moan as she watches Kruger having fun with you.",
				"The girls who are watching your performance continue to giggle and make lewd comments all around you.",
				"You glance across to see several of the girls touching themselves as they watch you and Kruger go at it.",
				"One of the zebra-girls cheers you on as you and Kruger carry on having sex in front of them.",
				"One of the lionesses giggles and makes a lewd comment as you and Kruger carry on having sex in front of her.");
		
		if(Sex.getOrificesBeingPenetratedBy(Main.game.getKruger(), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.MOUTH)) {
			descriptions.add("A lioness kneels down beside you, before pushing your head down on Kruger's cock, giggling to herself as she forces you to deepthroat his [kruger.cock+].");
			descriptions.add("A pair of zebra-girls lean forwards to either side of you, and, taking hold of your head, help you to bob up and down on [kruger.cock+].");
			descriptions.add("One of the lionesses leans forwards and snarls, [genericFemale.speech(That's right, bitch, know your place.)]");
			descriptions.add("A lioness leans forwards and pushes your head down on Kruger's cock, snarling, [genericFemale.speech(Come on, slut, you can suck cock better than that.)]");
			descriptions.add("One of the zebra-girls sits back on the sofa and cheers, [genericFemale.speech(Come on Kruger, fuck that bitch's face real good!)]");
			descriptions.add("A zebra-girl leans in against Kruger, and, grinning down at you, she encourages him, [genericFemale.speech(Oh yeah, Kruger, you put that slut in [pc.her] place!)]");
		}
		
		if(Sex.getOrificesBeingPenetratedBy(Main.game.getKruger(), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.VAGINA)) {
			descriptions.add("A lioness steps up behind you, before taking hold of your shoulders and pushing down, giggling to herself as she forces Kruger's [kruger.cock+] deep into your [pc.pussy+].");
			descriptions.add("A pair of zebra-girls step forwards to either side of you, and, taking a firm grip under your [pc.arms], pull you up and down in order to help Kruger's [kruger.cock+] slide in and out of your [pc.pussy+].");
			descriptions.add("One of the lionesses leans forwards and snarls, [genericFemale.speech(You're just a slutty cock-sleeve, aren't you bitch?)]");
			descriptions.add("A lioness leans forwards, and, taking hold of your [pc.hips+], pulls you down onto Kruger's cock, snarling, [genericFemale.speech(Come on, slut, take it <i>deep</i>.)]");
			descriptions.add("One of the zebra-girls sits back on the sofa and cheers, [genericFemale.speech(Come on Kruger, fuck that bitch real good!)]");
			descriptions.add("A zebra-girl leans in against Kruger, and, grinning across at you, she encourages him, [genericFemale.speech(Oh yeah, Kruger, you put that slut in [pc.her] place!)]");
		}

		if(Sex.getOrificesBeingPenetratedBy(Main.game.getKruger(), SexAreaPenetration.PENIS, Main.game.getPlayer()).contains(SexAreaOrifice.ANUS)) {
			descriptions.add("A lioness steps up behind you, before taking hold of your shoulders and pushing down, giggling to herself as she forces Kruger's [kruger.cock+] deep into your [pc.asshole+].");
			descriptions.add("A pair of zebra-girls step forwards to either side of you, and, taking a firm grip under your [pc.arms], pull you up and down in order to help Kruger's [kruger.cock+] slide in and out of your [pc.asshole+].");
			descriptions.add("One of the lionesses leans forwards and snarls, [genericFemale.speech(You're just a filthy butt-slut, aren't you bitch?)]");
			descriptions.add("A lioness leans forwards, and, taking hold of your [pc.hips+], pulls you down onto Kruger's cock, snarling, [genericFemale.speech(Come on, you dirty butt-slut, take it <i>deep</i>.)]");
			descriptions.add("One of the zebra-girls sits back on the sofa and cheers, [genericFemale.speech(Come on Kruger, fuck that bitch's ass real good!)]");
			descriptions.add("A zebra-girl leans in against Kruger, and, grinning across at you, she encourages him, [genericFemale.speech(Oh yeah, Kruger, you put that dirty butt-slut in [pc.her] place!)]");
		}
		
		return "<p style='color:"+Colour.BASE_ORANGE.toWebHexString()+"; font-style:italic; text-align:center;'>"
					+Util.randomItemFrom(descriptions)
				+"</p>";
	}
	
	@Override
	public boolean isPartnerWantingToStopSex() {
		return Sex.getNumberOfOrgasms(Main.game.getKruger())>=2
				&& Sex.getNumberOfOrgasms(Main.game.getPlayer())>=1;
	}
}
