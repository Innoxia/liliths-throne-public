package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.2.5
 * @version 0.2.5
 * @author Innoxia
 */
public class RentalMommyDialogue {
	
	public static final DialogueNodeOld ENCOUNTER = new DialogueNodeOld("Rental Mommy", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.mommyFound)) {
				return UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "ENCOUNTER_REPEAT");
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "ENCOUNTER");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<50) {
					return new Response("Hire "+UtilText.formatAsMoneyUncoloured(50, "span"), "You don't have enough money for this!", null);
				}
				return new Response("Hire "+UtilText.formatAsMoney(50, "span"), "Hire the rental Mommy.", MOMMYS_EXTRAS) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-50);
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.mommyFound, true);
					}
				};
				
			} else if(index==2) {
				return new Response("Decline", "", ENCOUNTER) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "DECLINE"));
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.mommyFound, true);
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld MOMMYS_EXTRAS = new DialogueNodeOld("Rental Mommy", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS"));
			
			if(Main.getProperties().hasValue(PropertyValue.lactationContent)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_OFFER_BREASTFEEDING"));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().getMoney()<1000) {
					return new Response("Submissive Sex "+UtilText.formatAsMoneyUncoloured(1000, "span"), "You don't have enough money for this!", null);
				}
				return new ResponseSex("Submissive Sex "+UtilText.formatAsMoney(1000, "span"), "Follow Mommy into her house, before letting her take the dominant role in having sex with you.",
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						AFTER_SEX_MOMMY_AS_DOM, UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_SEX_SUB")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_SEX_SUB")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-1000);
					}
				};
				
			} else if(index==2) {
				if(Main.game.getPlayer().getMoney()<2000) {
					return new Response("Dominant Sex "+UtilText.formatAsMoneyUncoloured(1000, "span"), "You don't have enough money for this!", null);
				}
				return new ResponseSex("Dominant Sex "+UtilText.formatAsMoney(2000, "span"), "Follow Mommy into her house, before taking the dominant role and having sex with her.",
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
						null,
						AFTER_SEX_MOMMY_AS_SUB, UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_SEX_DOM")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY")
							+ UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_SEX_DOM")) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-2000);
					}
				};
				
			} else if(index==3) {
				if(Main.game.getPlayer().getMoney()<500) {
					return new Response("Breastfeeding "+UtilText.formatAsMoneyUncoloured(500, "span"), "You don't have enough money for this!", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Breastfeeding "+UtilText.formatAsMoneyUncoloured(500, "span"), "You can't access your mouth, so you can't be breastfed by Mommy!", null);
				}
				return new Response("Breastfeeding "+UtilText.formatAsMoney(500, "span"), "Follow Mommy into her house and be breastfed by her.", MOMMYS_EXTRAS_BREASTFEEDING) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-500);
						Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.BREASTS);
						Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.NIPPLES);
						Main.game.getPlayer().ingestFluid(Main.game.getActiveNPC(), Main.game.getActiveNPC().getMilkType(), SexAreaOrifice.MOUTH, 500, Main.game.getActiveNPC().getMilk().getFluidModifiers());
					}
				};
				
			} else if(index==4) {
				if(Main.game.getPlayer().getMoney()<1000) {
					return new Response("Public Breastfeeding "+UtilText.formatAsMoneyUncoloured(1000, "span"), "You don't have enough money for this!", null);
				}
				if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
					return new Response("Public Breastfeeding "+UtilText.formatAsMoneyUncoloured(1000, "span"), "You can't access your mouth, so you can't be breastfed by Mommy!", null);
				}
				return new Response("Public Breastfeeding "+UtilText.formatAsMoney(1000, "span"), "Remain out here on the bench, and, in full sight of the public, be breastfed by Mommy.", MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC) {
					@Override
					public void effects() {
						Main.game.getPlayer().incrementMoney(-1000);
						Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.BREASTS);
						Main.game.getActiveNPC().getPlayerKnowsAreas().add(CoverableArea.NIPPLES);
						Main.game.getPlayer().incrementFetishExperience(Fetish.FETISH_LACTATION_OTHERS, 25);
						Main.game.getPlayer().incrementFetishExperience(Fetish.FETISH_EXHIBITIONIST, 10);
						Main.game.getPlayer().ingestFluid(Main.game.getActiveNPC(), Main.game.getActiveNPC().getMilkType(), SexAreaOrifice.MOUTH, 500, Main.game.getActiveNPC().getMilk().getFluidModifiers());
					}
				};
				
			} else if(index==5) {
				return new Response("Decline", "Decline Mommy's extra offers, and take your leave.", ENCOUNTER) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_DECLINED"));
					}
				};
			}
			
			return null;
		}
	};
	
	public static final DialogueNodeOld MOMMYS_EXTRAS_BREASTFEEDING = new DialogueNodeOld("Rental Mommy", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY_BREASTFEEDING"));
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_HOUSE_ENTRY"));
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_BREASTFEEDING"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Leave", "Thank Mommy and take your leave.", MOMMYS_EXTRAS_BREASTFEEDING) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC = new DialogueNodeOld("Rental Mommy", "", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Leave", "Thank Mommy and take your leave.", MOMMYS_EXTRAS_BREASTFEEDING_PUBLIC) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_MOMMY_AS_DOM = new DialogueNodeOld("Rental Mommy", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_ORGASMED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_NO_ORGASM"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "AFTER_SEX_MOMMY_AS_DOM_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Leave", "Thank Mommy and take your leave.", AFTER_SEX_MOMMY_AS_DOM) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_MOMMY_AS_SUB = new DialogueNodeOld("Rental Mommy", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_ORGASMED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "MOMMY_NO_ORGASM"));
			}

			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/rentalMommy", "AFTER_SEX_MOMMY_AS_SUB_END"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			 if(index==1) {
				return new Response("Leave", "Thank Mommy and take your leave.", AFTER_SEX_MOMMY_AS_SUB) {
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
			} else {
				return null;
			}
		}
	};
}
