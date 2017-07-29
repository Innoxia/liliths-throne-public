package com.base.game.sex.managers.dominion;

import com.base.game.dialogue.utils.UtilText;
import com.base.game.sex.SexPace;
import com.base.game.sex.SexPosition;
import com.base.game.sex.managers.SexManagerDefault;
import com.base.game.sex.sexActions.dominion.ralph.SARalphOrgasms;
import com.base.game.sex.sexActions.dominion.ralph.SexActionRalphDiscount;
import com.base.main.Main;
import com.base.utils.Colour;

/**
 * @since 0.1.6?
 * @version 0.1.79
 * @author Innoxia
 */
public class SexManagerRalphDiscount extends SexManagerDefault {
	
	public SexManagerRalphDiscount() {
		super(SexActionRalphDiscount.class, SARalphOrgasms.class);
	}
	
	@Override
	public SexPosition getPosition() {
		return SexPosition.RALPH_UNDER_DESK;
	}
	
	@Override
	public String getStartSexDescription() {
		return "<p>"
					+ UtilText.parsePlayerSpeech("Ok, I'll do it,") + " you say, looking up at Ralph to see his smile grow even wider." + "</p>" + "<p>"
					+ "He leans in, and you half-expect him to try and kiss you, but instead, he simply grabs your " + Main.game.getPlayer().getArmNameSingular() + " and starts to drag you back to his desk."
					+ " As he walks, he starts intructing you on what's about to happen."
				+ "</p>"
				+ "<p>"
					+ UtilText.parseSpeech("You're going to kneel under my desk over here, and I don't expect to have to do any of the work, understood?", Main.game.getRalph())
					+ " he asks, and as you answer in the affirmative, he continues, "
					+ UtilText.parseSpeech("This is a respectable shop, so if anyone comes in, you're to keep quiet! For each customer that hears you, I'm going to knock five percent off our deal.", Main.game.getRalph())
				+ "</p>"
				+ "<p>"
					+ "By this time, Ralph's led you behind the shop's front desk, and you see that there's a hollow space beneath the counter-top, large enough for you to kneel inside quite comfortably."
					+ " The desk's solid front conceals you from the rest of the shop, and you realise that if you keep quiet, any customers will be completely oblivious as to what's going on."
					+ " Ralph places his hands on your shoulders, and, feeling that it's too late to back out now, you allow him to push you to your knees."
					+ " Shuffling back, you occupy the space under his desk, and Ralph steps forwards, bringing the massive bulge in his trousers right up to your face."
				+ "</p>"
				+ "<p>"
					+ UtilText.parseSpeech("Make sure you give my balls some attention as well,", Main.game.getRalph()) + " you hear him command."
				+ "</p>"
				+ "<p>"
					+ "Just as you're about to answer him, you hear the little bell over the shop's front door ring out, announcing the arrival of a customer."
					+ " You hear Ralph calling out his friendly greeting, but as he does so, he pushes his hips forwards, making it quite clear that he wants you to get started."
					+ " There isn't much room for you to move around, and you realise that you're going to be totally restricted to using just your mouth in order to earn your discount."
				+ "</p>"
				+ "<p>"
					+ "As the customer walks off to another part of the shop, Ralph reaches down and unbuttons his trousers."
					+ " With a quick tug, he pulls them, along with his boxers, down to pool around his ankles."
					+ " You feel your eyes go wide as you see the gigantic length of Ralph's rapidly-hardening horse-cock rise up to bump against your chin."
					+ " His huge pair of black-skinned balls droop down loosely at the base of his bestial shaft, and you gulp at the thought of what's about to happen..."
				+ "</p>" 
				+ "<p>" 
					+ "<b>There are</b> <b style='color:" + Colour.GENERIC_GOOD() + ";'>no customers</b> <b>near the counter.</b>"
					+ " <b>You will earn a</b> <b style='color:" + Colour.GENERIC_GOOD() + ";'>25%</b> <b>discount.</b>"
				+ "</p>";
	}
	
	@Override
	public boolean isConsensualSex(){
		return true;
	}
	
	@Override
	public boolean isPlayerAbleToStopSex() {
		return false;
	}
	
	@Override
	public boolean isPlayerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPlayerCanRemovePartnersClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemoveOwnClothes(){
		return true;
	}
	
	@Override
	public boolean isPartnerCanRemovePlayersClothes(){
		return true;
	}

	@Override
	public SexPace getStartingSexPacePlayer() {
		return SexPace.SUB_NORMAL;
	}

	@Override
	public SexPace getStartingSexPacePartner() {
		return SexPace.DOM_NORMAL;
	}
}
