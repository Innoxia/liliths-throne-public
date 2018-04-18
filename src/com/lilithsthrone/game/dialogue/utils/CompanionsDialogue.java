package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;


public class CompanionsDialogue {
	
	private static GameCharacter target;
	
	private static List<GameCharacter> possibleSexTargets;
	
	public static GameCharacter getTarget() {
		if(target==null) {
			return Main.game.getPlayer();
		}
		return target;
	}

	public static void setTarget(GameCharacter target) {
		CompanionsDialogue.target = target;
	}
	
	public static List<GameCharacter> getPossibleSexTargets() {
		return possibleSexTargets;
	}

	public static void genPossibleSexTargets() {
		possibleSexTargets = new ArrayList<>();
		if(target != null)
		{
			// TODO : Include slaves in this one.
			for(GameCharacter partner : Main.game.getPlayer().getCompanions())
			{
				if(!partner.isPlayer() && partner != target)
				{
					possibleSexTargets.add(partner);
				}
			}
		}
	}

	public static final DialogueNodeOld CHARACTER_SELECT = new DialogueNodeOld("Character select", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			return "Companions";
		}		
		
		@Override
		public String getContent() {
			return "<p>You look over your companions.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {		
			if (index == 0){
				return new ResponseEffectsOnly("Back", "Return to previous screen."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent();
					}
				};
			}
			else if (index <= Main.game.getPlayer().getCompanions().size()) {
				return new Response(Util.capitaliseSentence(
						Main.game.getPlayer().getCompanions().get(index - 1).getName()),
						"Talk to " + Main.game.getPlayer().getCompanions().get(index - 1).getName("the") + ".",
						CHARACTER_MANAGE){
					@Override
					public void effects() {
						genPossibleSexTargets();
						setTarget(Main.game.getPlayer().getCompanions().get(index - 1));
					}
				};
				
			} else {
				return null;
			}
		}
	};	

	public static final DialogueNodeOld CHARACTER_MANAGE = new DialogueNodeOld("Character manager", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			// TODO: BETTER TEXT
			return "Talking to "+getTarget().getName();
		}		
		
		@Override
		public String getContent() {
			// TODO: BETTER TEXT
			return "<p>You are talking to your companion.</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {		
			if (index == 0){
				return new ResponseEffectsOnly("Back", "Return to previous screen."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent();
					}
				};
			} else if (index == 1) {
				return new ResponseEffectsOnly("Go Home",
						"Tell " + getTarget().getName("the") + " to go home."){
					@Override
					public void effects() {
						Main.game.getPlayer().removeCompanion(getTarget());
						getTarget().returnToHome();
						setTarget(null);
						Main.game.restoreSavedContent();
					}
				};
			}  else if (index == 2) {
				return new Response("Sex",
						"Opens the sex interactions menu.",
						CHARACTER_SEX){
					@Override
					public void effects() {
					}
				};
			} else {
				return null;
			}
		}
	};		

	public static final DialogueNodeOld CHARACTER_SEX = new DialogueNodeOld("Sex pose selection", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getLabel() {
			// TODO: BETTER TEXT
			return "Sex options with "+getTarget().getName();
		}		
		
		@Override
		public String getContent() {
			// TODO: BETTER TEXT
			return "<p>You are considering your options with your companion.</p>";
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return getTarget().getName();
			} else if(index <= getPossibleSexTargets().size()) {
				return getTarget().getName()+" and "+getPossibleSexTargets().get(index-1);
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {	
			boolean companionWantsSex = getTarget().isCompanionAvailableForSex();
			
			if(responseTab == 0)
			{
				if (index == 0){
					return new ResponseEffectsOnly("Back", "Return to previous screen."){
						@Override
						public void effects() {
							Main.game.restoreSavedContent();
						}
					};
				} else if (index == 1) {
					if(companionWantsSex && 
							getTarget().getFetishDesire(Fetish.FETISH_SUBMISSIVE) != FetishDesire.ZERO_HATE && 
							getTarget().getFetishDesire(Fetish.FETISH_SUBMISSIVE) != FetishDesire.ONE_DISLIKE)
					{
						return new ResponseSex("Sex (Dominant)",
								"Start dominant sex with your companion.",
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getTarget(), SexPositionSlot.STANDING_SUBMISSIVE))),
								AFTER_SEX,
								"<p>"
									+ "With a lusty smirk and a few suggestive words, you've managed get your companion in the mood to get some action."
								+ "</p>");
					}
					else
					{
						String rejectionReason;
						if(companionWantsSex)
						{
							rejectionReason = getTarget().getName("the")+" doesn't like being a bottom.";
						}
						else
						{
							rejectionReason = getTarget().getCompanionSexRejectionReason();
						}
						return new Response("Sex (Dominant)", rejectionReason, null){};
						
					}
				} else if (index == 2) {
					if(companionWantsSex && 
							getTarget().getFetishDesire(Fetish.FETISH_DOMINANT) != FetishDesire.ZERO_HATE && 
							getTarget().getFetishDesire(Fetish.FETISH_DOMINANT) != FetishDesire.ONE_DISLIKE)
					{
						return new ResponseSex("Sex (Submissive)",
								"Start submissive sex with your companion.",
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getTarget(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								AFTER_SEX,
								"<p>"
									+ "With a lusty smirk and a few suggestive words, you've managed get your companion in the mood to get some action."
								+ "</p>");
					}
					else
					{
						String rejectionReason;
						if(companionWantsSex)
						{
							rejectionReason = getTarget().getName("the")+" doesn't like being on top.";
						}
						else
						{
							rejectionReason = getTarget().getCompanionSexRejectionReason();
						}
						return new Response("Sex (Dominant)", rejectionReason, null){};
						
					}
				} else {
					return null;
				}
			}
			else if (responseTab >= getPossibleSexTargets().size())
			{
				boolean secondCompanionWantsSex = getPossibleSexTargets().get(responseTab-1).isCompanionAvailableForSex();
				boolean companionWantsDominantSex =
						(getTarget().getFetishDesire(Fetish.FETISH_SUBMISSIVE) != FetishDesire.ZERO_HATE && 
						getTarget().getFetishDesire(Fetish.FETISH_SUBMISSIVE) != FetishDesire.ONE_DISLIKE);
				boolean companionWantsSubmissiveSex =
						(getTarget().getFetishDesire(Fetish.FETISH_DOMINANT) != FetishDesire.ZERO_HATE && 
						getTarget().getFetishDesire(Fetish.FETISH_DOMINANT) != FetishDesire.ONE_DISLIKE);
				boolean secondCompanionWantsDominantSex =
						(getPossibleSexTargets().get(responseTab-1).getFetishDesire(Fetish.FETISH_SUBMISSIVE) != FetishDesire.ZERO_HATE && 
						getPossibleSexTargets().get(responseTab-1).getFetishDesire(Fetish.FETISH_SUBMISSIVE) != FetishDesire.ONE_DISLIKE);
				boolean secondCompanionWantsSubmissiveSex =
						(getPossibleSexTargets().get(responseTab-1).getFetishDesire(Fetish.FETISH_DOMINANT) != FetishDesire.ZERO_HATE && 
						getPossibleSexTargets().get(responseTab-1).getFetishDesire(Fetish.FETISH_DOMINANT) != FetishDesire.ONE_DISLIKE);
				if (index == 0){
					return new ResponseEffectsOnly("Back", "Return to previous screen."){
						@Override
						public void effects() {
							Main.game.restoreSavedContent();
						}
					};
				} else if (index == 1) {
					if(companionWantsSex && secondCompanionWantsSex && companionWantsDominantSex && secondCompanionWantsDominantSex)
					{
						return new ResponseSex("Get Spitroasted",
								UtilText.parse(getTarget(), getPossibleSexTargets().get(responseTab-1), "Let [npc1.name] and [npc2.name] spitroast you."),
								null, null, null, null, null, null,
								true, true,
								new SMDoggy(
										Util.newHashMapOfValues(
												new Value<>(getTarget(), SexPositionSlot.DOGGY_INFRONT),
												new Value<>(getPossibleSexTargets().get(responseTab-1), SexPositionSlot.DOGGY_BEHIND)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
								AFTER_SEX,
								"<p>"
									+ ""//TODO
								+ "</p>");
					}
					else
					{
						String rejectionReason = "";
						if(companionWantsSex)
						{
							rejectionReason = getTarget().getName("the")+" doesn't like being a top. ";
						}
						else if(!companionWantsDominantSex)
						{
							rejectionReason = getTarget().getCompanionSexRejectionReason()+" ";
						}
						if(secondCompanionWantsSex)
						{
							rejectionReason = rejectionReason + getPossibleSexTargets().get(responseTab-1).getName("the")+" doesn't like being a top.";
						}
						else if(!secondCompanionWantsDominantSex)
						{
							rejectionReason = rejectionReason + getPossibleSexTargets().get(responseTab-1).getCompanionSexRejectionReason();
						}
						return new Response("Get Spitroasted", rejectionReason, null){};
						
					}
				} else if (index == 2) {
					if(companionWantsSex && secondCompanionWantsSex && companionWantsSubmissiveSex && secondCompanionWantsSubmissiveSex)
					{
						return new ResponseSex("Side-by-side",
								UtilText.parse(getTarget(), getPossibleSexTargets().get(responseTab-1), "Push [npc1.name] and [npc2.name] down onto all fours, side-by-side, and get ready to fuck them."),
								null, null, null, null, null, null,
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
										Util.newHashMapOfValues(
												new Value<>(getTarget(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
												new Value<>(getPossibleSexTargets().get(responseTab-1), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
								AFTER_SEX,
								"<p>"
									+ ""//TODO
								+ "</p>");
					}
					else
					{
						String rejectionReason = "";
						if(companionWantsSex)
						{
							rejectionReason = getTarget().getName("the")+" doesn't like being a bottom. ";
						}
						else if(!companionWantsSubmissiveSex)
						{
							rejectionReason = getTarget().getCompanionSexRejectionReason()+" ";
						}
						if(secondCompanionWantsSex)
						{
							rejectionReason = rejectionReason + getPossibleSexTargets().get(responseTab-1).getName("the")+" doesn't like being a bottom.";
						}
						else if(!secondCompanionWantsSubmissiveSex)
						{
							rejectionReason = rejectionReason + getPossibleSexTargets().get(responseTab-1).getCompanionSexRejectionReason();
						}
						return new Response("Side-by-side", rejectionReason, null){};
						
					}
				} else {
					return null;
				}
			}
			else
			{
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave your companions to recover."; // TODO
		}

		@Override
		public String getContent() {
			return "Now that you've had your fun, you can step back and leave your companions to recover."; // TODO
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Decide what to do next.", CHARACTER_MANAGE);
				
			} else {
				return null;
			}
		}
	};
	
}
