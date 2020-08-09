package com.lilithsthrone.game.dialogue.places.submission.gamblingDen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.npc.submission.Epona;
import com.lilithsthrone.game.character.npc.submission.Murk;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.Dice;
import com.lilithsthrone.game.dialogue.places.submission.dicePoker.DiceFace;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.InitialSexActionInformation;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.managers.submission.SMBreedingStall;
import com.lilithsthrone.game.sex.managers.universal.SMAllFours;
import com.lilithsthrone.game.sex.managers.universal.SMGeneric;
import com.lilithsthrone.game.sex.positions.slots.SexSlotAllFours;
import com.lilithsthrone.game.sex.positions.slots.SexSlotBreedingStall;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisAnus;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisMouth;
import com.lilithsthrone.game.sex.sexActions.baseActions.PenisVagina;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.6
 * @version 0.3.5.5
 * @author Innoxia
 */
public class PregnancyRoulette {

	private static List<GenericSexualPartner> breeders = new ArrayList<>();
	private static int breederIndex = 0;
	private static int roll = 1;
	private static GenericSexualPartner mother;
	private static GameCharacter selectedBreeder;
	
	private static void initBreeder(NPC partner) {
		partner.deleteAllEquippedClothing(true);
		partner.clearFetishes();
		partner.clearFetishDesires();
		partner.addFetish(Fetish.FETISH_IMPREGNATION);
		partner.addFetish(Fetish.FETISH_VAGINAL_GIVING);
		partner.setPlayerKnowsName(true);
		partner.setPenisVirgin(false);
		partner.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		partner.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		partner.setAttribute(Attribute.VIRILITY, (partner.getPenisRawSizeValue()*2)+(partner.getTesticleSize().getValue() * 5)+partner.getPenisRawCumStorageValue());
		
		try {
			Main.game.addNPC(partner, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initMother() {
		mother = new GenericSexualPartner(Gender.F_V_B_FEMALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
		mother.clearFetishes();
		mother.clearFetishDesires();
		mother.addFetish(Fetish.FETISH_PREGNANCY);
		mother.addFetish(Fetish.FETISH_VAGINAL_RECEIVING);
		mother.removeStatusEffect(StatusEffect.PROMISCUITY_PILL);
		mother.deleteAllEquippedClothing(true);
		mother.setSexualOrientation(SexualOrientation.AMBIPHILIC);
		mother.setPlayerKnowsName(true);
		mother.useItem(Main.game.getItemGen().generateItem(ItemType.VIXENS_VIRILITY), mother, false);
		try {
			Main.game.addNPC(mother, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final DialogueNode PREGNANCY_ROULETTE = new DialogueNode("Pregnancy Roulette Counter", "", false) {
		
		@Override
		public boolean isTravelDisabled() {
			return !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)
					|| (Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer()));
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE"));
				
				boolean preg = Main.game.getPlayer().hasFetish(Fetish.FETISH_PREGNANCY);
				boolean impreg = Main.game.getPlayer().hasFetish(Fetish.FETISH_IMPREGNATION);
				if(preg) {
					if(impreg) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BOTH_FETISH"));
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_PREGNANCY_FETISH"));
					}
					
				} else if(impreg) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_IMPREGNATION_FETISH"));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_NO_FETISH"));
				}
				
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_END"));
				
			} else if(Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_EPONA_IMPREGNATED"));
				
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_REPEAT"));
				if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsBreeder) || Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.playedPregnancyRouletteAsMother)) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Returning her greeting, you walk up to the counter, where the happy horse-girl asks, [epona.speech(You interested in having another go at pregnancy roulette?");
					
					if(Main.game.getDialogueFlags().eponaStamps>=6) {
						UtilText.nodeContentSB.append(" Or perhaps you want to cash in your stamps and take me for a ride!)]"
								+ "</p>");
					} else {
						UtilText.nodeContentSB.append(" You've got "+Util.intToString(6-Main.game.getDialogueFlags().eponaStamps)+" stamps to go until you get to take me for a ride!)]"
								+ "</p>");
					}
				}  else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Returning her greeting, you walk up to the counter, where the happy horse-girl asks, [epona.speech(You interested in having a go at pregnancy roulette? Gotta start collecting those stamps!)]"
							+ "</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(!Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaIntroduced)) {
				if(index==1) {
					return new Response("Continue", "Now that Epona has told you about the game of pregnancy roulette, you wonder what you should do next...", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaIntroduced, true);
							Main.game.getTextStartStringBuilder().append(UtilText.nodeContentSB.toString());
						}
					};
				} else {
					return null;
				}
				
			} else if(Main.game.getNpc(Epona.class).isVisiblyPregnant() && !Main.game.getNpc(Epona.class).isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				if(index==1) {
					return new Response("Continue", "You're happy to see how delighted Epona is to be the mother of your children.", PREGNANCY_ROULETTE_GREETING_UTIL) {
						@Override
						public void effects() {
							Main.game.getNpc(Epona.class).setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							Main.game.getTextStartStringBuilder().append(UtilText.nodeContentSB.toString());
						}
					};
				} else {
					return null;
				}
				
			} else {
				if(index == 1) {
					return new Response("Rules", "Ask Epona about the rules for pregnancy roulette.", PREGNANCY_ROULETTE_RULES);
					
				} else if(index==2) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You are already pregnant, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You can't sign up for pregnancy roulette if your pussy already is full of cum!", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have a vagina, so you can't sign up to be the mother!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You aren't able to gain access to your vagina, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Male Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have enough money, so you can't sign up to be the mother!", null);
						
					} else {
						return new Response("Male Bred ("+UtilText.formatAsMoney(10000, "span")+")", "Sign up as the mother for pregnancy roulette, asking to bred by the males.", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false, ((s) -> s.getRace()==Race.HARPY));
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==3) {
					if(Main.game.getPlayer().isPregnant()) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You are already pregnant, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getTotalFluidInArea(SexAreaOrifice.VAGINA)>0) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You can't sign up for pregnancy roulette if your pussy already is full of cum!", null);
						
					} else if(!Main.game.getPlayer().hasVagina()) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have a vagina, so you can't sign up to be the mother!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You aren't able to gain access to your vagina, so you can't sign up to be the mother!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Futa Bred ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have enough money, so you can't sign up to be the mother!", null);
						
					} else {
						return new Response("Futa Bred ("+UtilText.formatAsMoney(10000, "span")+")", "Sign up as the mother for pregnancy roulette, asking to bred by the futas.", PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								for(int i=0; i<6; i++) {
									GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
									initBreeder(partner);
									breeders.add(partner);
								}
							}
						};
					}
					
				} else if(index==4) {
					if(!Main.game.getPlayer().hasPenisIgnoreDildo()) {
						return new Response("Breeder ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have a penis, so you can't sign up to be one of the breeders!", null);
						
					} else if(!Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.PENIS, true)) {
						return new Response("Breeder ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You aren't able to gain access to your penis, so you can't sign up to be one of the breeders!", null);
						
					} else if(Main.game.getPlayer().getMoney()<10000) {
						return new Response("Breeder ("+UtilText.formatAsMoneyUncoloured(10000, "span")+")", "You don't have enough money, so you can't sign up to be one of the breeders!", null);
						
					} else {
						return new Response("Breeder ("+UtilText.formatAsMoney(10000, "span")+")", "Sign up as one of the breeders for pregnancy roulette.", PREGNANCY_ROULETTE_BREEDER) {
							@Override
							public Colour getHighlightColour() {
								return PresetColour.GENERIC_SEX;
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-10000));
								Main.game.getDialogueFlags().eponaStamps+=1;
								
								// Skew the dice roll in the player's favour (lowest number goes first):
								Dice d = new Dice(Util.newHashMapOfValues(
										new Value<>(DiceFace.ONE, 4f),
										new Value<>(DiceFace.TWO, 3f),
										new Value<>(DiceFace.THREE, 2f),
										new Value<>(DiceFace.FOUR, 1f),
										new Value<>(DiceFace.FIVE, 0.5f),
										new Value<>(DiceFace.SIX, 0.25f)));
								d.roll();
								roll = d.getFace().getValue();
								
								breeders.clear();
								breederIndex=0;
								selectedBreeder=null;
								
								if(Main.game.getPlayer().isFeminine()) {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_FUTA_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
									
								} else {
									Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY);
									for(int i=0; i<5; i++) {
										GenericSexualPartner partner = new GenericSexualPartner(Gender.M_P_MALE, Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation(), false);
										initBreeder(partner);
										breeders.add(partner);
									}
								}
								
								initMother();
							}
						};
					}
					
				} else if(index==6) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("Ride Epona", "You haven't collected enough stamps to take Epona for a ride! You have "+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+", and you need at least six.", null);
						
					} else {
						return new ResponseSex("Ride Epona", "Tell Epona that you want to cash in your stamps and take her for a ride. (Have sex as the dominant partner.)",
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getPlayer()),
										Util.newArrayListOfValues(Main.game.getNpc(Epona.class)),
								null,
								null), EPONA_POST_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_START_SEX_AS_SUB")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else if(index==7) {
					if(Main.game.getDialogueFlags().eponaStamps<6) {
						return new Response("Epona Rides", "You haven't collected enough stamps to get Epona to take you for a ride! You have "+Util.intToString(Main.game.getDialogueFlags().eponaStamps)+", and you need at least six.", null);
						
					} else {
						return new ResponseSex("Epona Rides", "Tell Epona that you want to cash in your stamps and have her take you for a ride. (Have sex as the submissive partner.)",
								null, null, null, null, null, null,
								true, true,
								new SMGeneric(
										Util.newArrayListOfValues(Main.game.getNpc(Epona.class)),
										Util.newArrayListOfValues(Main.game.getPlayer()),
								null,
								null), EPONA_POST_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_START_SEX_AS_DOM")) {
							@Override
							public void effects() {
								Main.game.getDialogueFlags().eponaStamps-=6;
							}
						};
					}
					
				} else if(index==11 && Main.game.getNpc(Murk.class).isSlave()) {
					return new Response("[murk.Name]", "Ask Epona about [murk.name].", MURK);
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode EPONA_POST_SEX = new DialogueNode("Finished", "Epona takes a moment to catch her breath, before getting ready to return to work.", false) {
		
		@Override
		public String getContent() {
			if(Main.sex.getNumberOfOrgasms(Main.game.getNpc(Epona.class))>=Main.game.getNpc(Epona.class).getOrgasmsBeforeSatisfied()) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_POST_SEX");
			} else {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "EPONA_POST_SEX_NO_ORGASM");
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_GREETING_UTIL = new DialogueNode("Pregnancy Roulette Counter", "", false) {
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
		
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_RULES = new DialogueNode("Pregnancy Roulette Counter", "", false) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_RULES");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
	};
	
	private static String getBreederPanel(NPC breeder) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-half-width'>"
				+ "[npc.Name] - [npc.FullRace(true)]<br/>"
				+ "[npc.CockGirth], [npc.penisValue], [npc.cockColour(true)] [npc.cockRace] [npc.cock], with [npc.ballSize] balls."
				+ "</div>");
		
		return UtilText.parse(breeder, sb.toString());
	}
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_PRE_SELECTION"));
			}
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index==1) {
				return new Response("Wait", "Wait for Epona to lead the breeders into the room.", PREGNANCY_ROULETTE_MOTHER_SELECTION) {
					@Override
					public void effects() {
						Main.game.getNpc(Epona.class).useItem(Main.game.getItemGen().generateItem(ItemType.VIXENS_VIRILITY), Main.game.getPlayer(), false);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_SELECTION = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().getLocationPlace().getPlaceType().equals(PlaceType.GAMBLING_DEN_FUTA_PREGNANCY)) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_SELECTION_FUTA"));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_SELECTION"));
			}

			for(int i=0;i< breeders.size()/2;i++) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='width:100%; margin:0'>");
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2)));
					UtilText.nodeContentSB.append(getBreederPanel(breeders.get(i*2 +1)));
				UtilText.nodeContentSB.append("</div>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==0) {
				return null;
				
			} else if(index<=breeders.size()) {
				NPC breeder = breeders.get(index-1);
				return new Response(breeder.getName(true), "Tell Epona that you think "+breeder.getName(true)+" will get you pregnant.", PREGNANCY_ROULETTE_MOTHER_START) {
					@Override
					public void effects() {
						selectedBreeder=breeder;
						Collections.shuffle(breeders);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_START = new DialogueNode("", "", true, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_START", Util.newArrayListOfValues(selectedBreeder));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				NPC breeder = breeders.get(breederIndex);
				
				return new ResponseSex("Front "+breeder.getName(true), "Lie on your front, where "+breeder.getName(true)+" will be the first to move up to fuck you.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLustNoText(80);
								}
							}
						},
						null,
						null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else if(index==2) {
				NPC breeder = breeders.get(breederIndex);

				if(Main.game.getPlayer().isTaur()) {
					return new Response("Back "+breeder.getName(true),
							"Due to your lower body being that of a feral [pc.legRace], you're unable to fit into the breeding stall by lying on your back. You'll have to lie down on your front instead...",
							null);
				}
				
				return new ResponseSex("Back "+breeder.getName(true), "Lie on your back, where "+breeder.getName(true)+" will be the first to move up to fuck you.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_BACK))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								if(!character.isPlayer()) {
									character.setArousal(75);
									character.setLustNoText(80);
								}
							}
						},
						null,
						null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
					@Override
					public void effects() {
						breederIndex++;
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode AFTER_ROULETTE_SEX = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return breeders.get(breederIndex-1).getName(true)+" is done";
		}
		
		@Override
		public String getContent() {
			NPC breeder = breeders.get(breederIndex-1);
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_ROULETTE_SEX", Util.newArrayListOfValues(breeder));
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(breederIndex<breeders.size()) {
				if(index==1) {
					NPC breeder = breeders.get(breederIndex);
					
					return new ResponseSex("Front "+breeder.getName(true), "Position yourself so that you're lying on your front, where "+breeder.getName(true)+" will be the next breeder to move up to fuck you.",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStall(
									Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FRONT))) {
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLustNoText(80);
									}
								}
							},
							null,
							null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else if(index==2) {
					NPC breeder = breeders.get(breederIndex);

					if(Main.game.getPlayer().isTaur()) {
						return new Response("Back "+breeder.getName(true),
								"Due to your lower body being that of a feral [pc.legRace], you're unable to fit into the breeding stall by lying on your back. You'll have to lie down on your front instead...",
								null);
					}
					
					return new ResponseSex("Back "+breeder.getName(true), "Position yourself so that you're lying on your back, where "+breeder.getName(true)+" will be the next breeder to move up to fuck you.",
							null, null, null, null, null, null,
							true, false,
							new SMBreedingStall(
									Util.newHashMapOfValues(new Value<>(breeder, SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_BACK))) {
								@Override
								public void initStartingLustAndArousal(GameCharacter character) {
									if(!character.isPlayer()) {
										character.setArousal(75);
										character.setLustNoText(80);
									}
								}
							},
							null,
							null, AFTER_ROULETTE_SEX, UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "ROULETTE_STARTING", Util.newArrayListOfValues(breeder))){
						@Override
						public void effects() {
							breederIndex++;
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if(index==1) {
					return new Response("Finished", "All six of the breeders have deposited their cum in your [pc.pussy+].", PREGNANCY_ROULETTE_MOTHER_FINISHED) {
						@Override
						public void effects() {
							Main.game.getTextEndStringBuilder().append(Main.game.getNpc(Epona.class).useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), Main.game.getPlayer(), false));
						}
					};
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_FINISHED = new DialogueNode("", "", true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_FINISHED");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isPregnant()) {
					if(Objects.equals(Main.game.getPlayer().getPregnantLitter().getFather(), selectedBreeder)) {
						return new Response("Winner!", "You correctly guessed that "+selectedBreeder.getName(true)+" would be the father!", PREGNANCY_ROULETTE_MOTHER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(50000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
						
					} else {
						return new Response("Loser", "You incorrectly guessed that "+selectedBreeder.getName(true)+" would be the father, so lost your bet...", PREGNANCY_ROULETTE_MOTHER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
							}
						};
					}
						
				} else {
					return new Response("Loser", "You didn't get pregnant, so everyone loses their bets...", PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_WINNER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_LOSER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_MOTHER_LOSER_NO_PREGNANCY");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};

	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER = new DialogueNode("", "", true) {
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getPlayer().isFeminine()) {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FUTA", Util.newArrayListOfValues(mother)));
			} else {
				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER", Util.newArrayListOfValues(mother)));
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Epona rolls the dice, and you watch as it clatters to a halt on the number "+Util.intToString(roll)+"."
						+ " [epona.speech(Alright, [pc.name], that means you're going "+Util.intToPosition(roll)+"! Now I'll keep rolling for the rest of you!)]"
					+ "</p>"
					+ "<p>"
						+ "You all then have to wait while Epona rolls for each of the other breeders."
						+ " Every time she rolls an already-rolled number, she has to re-roll the dice, which results in you all having to wait for far longer than if she'd just used a more efficient method of deciding who goes first."
					+ "</p>"
					+ "<p>"
						+ "Eventually, however, Epona has finished, and you move forwards to take your place in the line..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<i>You are going <b>"+Util.intToPosition(roll)+"</b>!</i>"
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1){
				return new ResponseSex(roll==1?"First":"Your turn",
						roll==1
							?"As you rolled a one, you're the first to have a go at fucking the volunteer."
							:"As the breeder steps away from the volunteer, you step forwards to take your turn at fucking her creampied-pussy.",
						null, null, null, null, null, null,
						true, false,
						new SMBreedingStall(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotBreedingStall.BREEDING_STALL_FUCKING)),
								Util.newHashMapOfValues(new Value<>(mother, SexSlotBreedingStall.BREEDING_STALL_BACK))) {
							@Override
							public void initStartingLustAndArousal(GameCharacter character) {
								character.setArousal(50);
								character.setLustNoText(80);
							}
						},
						null,
						null,
						PREGNANCY_ROULETTE_BREEDER_POST_SEX,
						roll==1
							?UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FIRST", Util.newArrayListOfValues(mother))
							:UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_MIDDLE", Util.newArrayListOfValues(mother))){
					@Override
					public void effects() {
						for(int i=0; i<roll-1; i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCum(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity());
						}
					}
				};
				
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_POST_SEX = new DialogueNode("Finished", "", true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_POST_SEX");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Wait", "Wait for Epona to return.", PREGNANCY_ROULETTE_BREEDER_FINISHED) {
					@Override
					public void effects() {
						for(int i=roll-1; i<breeders.size(); i++) {
							mother.setVaginaVirgin(false);
							mother.ingestFluid(breeders.get(i), breeders.get(i).getCum(), SexAreaOrifice.VAGINA, breeders.get(i).getPenisRawOrgasmCumQuantity());
						}
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_FINISHED = new DialogueNode("", "", true, true) {

		@Override
		public int getSecondsPassed() {
			return 30*60;
		}
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_FINISHED")
					+mother.useItem(Main.game.getItemGen().generateItem(ItemType.PREGNANCY_TEST), mother, false);
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(mother.isPregnant()) {
					if(mother.getPregnantLitter().getFather()!=null && mother.getPregnantLitter().getFather().isPlayer()) {
						return new Response("Winner!", "You got "+mother.getName(true)+" pregnant!", PREGNANCY_ROULETTE_BREEDER_WINNER) {
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(20000));
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
							}
						};
						
					} else {
						return new Response("Loser", "You didn't get "+mother.getName(true)+" pregnant, so lost your bet...", PREGNANCY_ROULETTE_BREEDER_LOSER){
							@Override
							public void effects() {
								Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
								for(NPC npc : breeders) {
									Main.game.banishNPC(npc);
								}
								Main.game.banishNPC(mother);
							}
						};
					}
						
				} else {
					return new Response("Loser", "Nobody got "+mother.getName(true)+" pregnant, so everyone loses their bets...", PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY){
						@Override
						public void effects() {
							Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							Main.game.getNpc(Epona.class).setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_PREGNANCY_ROULETTE);
							for(NPC npc : breeders) {
								Main.game.banishNPC(npc);
							}
							Main.game.banishNPC(mother);
						}
					};
				}
			}
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_WINNER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_WINNER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_LOSER = new DialogueNode("", "", false, true) {
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_LOSER");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNode PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY = new DialogueNode("", "", false, true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "PREGNANCY_ROULETTE_BREEDER_LOSER_NO_PREGNANCY");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	/* Murk:
	 * 	Ask about him. Epons reveals she is disgusted by what he's done. He's tasked with cleaning up the stalls in pregnancy roulette. Currently chained up in the back, however.
	 *  Can convince to let you punish him.
	 * 	When alone, can submit instead.
	 *  Or fuck him
	 *  Or permanently transform him into rat-girl
	 */
	
	public static final DialogueNode MURK = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaMurkOwnerIntroduced)) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_REPEAT");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Punish", "Tell Epona that you could spend some time punishing [murk.name] for [murk.her] past crimes.", MURK_ALONE) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkOwnerIntroduced, true);
					}
				};
				
			} else if(index==0) {
				return new Response("Back", "Tell Epona that you've had enough of discussing [murk.name] for now.", MURK_BACK) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkOwnerIntroduced, true);
					}
				};
			}
			return null;
		}
	};

	public static final DialogueNode MURK_BACK = new DialogueNode("", "", false) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_BACK");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return PREGNANCY_ROULETTE.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNode MURK_ALONE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_ALONE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.eponaMurkSubmitted)) {
				if(index==1) {
					return new Response("Obey", "Do as [murk.name] commands and get down on your knees to worship his cock.", MURK_SUBMIT_ACCEPT) {
						@Override
						public boolean isSexHighlight() {
							return true;
						}
						@Override
						public void effects() {
							if(isMurkNoAccess()) {
								int increment = (int) (Main.game.getNpc(Murk.class).getPenisRawStoredCumValue()/2);
								Main.game.getNpc(Murk.class).incrementPenisStoredCum(increment);
								List<CoverableArea> cummedOnAreas = Util.newArrayListOfValues(CoverableArea.ANUS, CoverableArea.ASS, CoverableArea.BACK, CoverableArea.LEGS, CoverableArea.VAGINA);
								for(CoverableArea area : cummedOnAreas) {
									for(InventorySlot slot : area.getAssociatedInventorySlots(Main.game.getPlayer())) {
										List<AbstractClothing> dirtyClothing = new ArrayList<>(Main.game.getPlayer().getVisibleClothingConcealingSlot(slot));
										if(!dirtyClothing.isEmpty()) {
											for(AbstractClothing c : dirtyClothing) {
												c.setDirty(Main.game.getPlayer(), true);
											}
										} else if(slot!=InventorySlot.TORSO_OVER) { // Do not dirty over-torso slot, as it doesn't really make much sense...
											Main.game.getPlayer().addDirtySlot(slot);
										}
									}
								}
							}
						}
					};
					
				} else if(index==2) {
					return new Response("Refuse", "Tell [murk.name] not to get cocky, just because you let him dominate you the last time you were here.", MURK_SUBMIT_REFUSE) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, false);
						}
					};
				}
				
			} else {
				if(index==1) {
					return new Response("Lecture", "Spend some time lecturing [murk.name] on why what [murk.she] did was wrong.", MURK_LECTURING) {
						@Override
						public void effects() {
							if(Main.game.getNpc(Murk.class).isFeminine() && Main.game.getNpc(Murk.class).getAffection(Main.game.getPlayer())<25) {
								Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), 5));
							} else {
								Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), -5));
							}
						}
					};
					
				} else if(index==2) {
					return new Response("Spank", "Spend some time spanking [murk.name].", MURK_SPANKING) {
						@Override
						public void effects() {
							if(Main.game.getNpc(Murk.class).isFeminine()) {
								Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), 10));
							} else {
								Main.game.getTextStartStringBuilder().append(Main.game.getNpc(Murk.class).incrementAffection(Main.game.getPlayer(), -10));
							}
						}
					};
					
				} else if(index==3) {
					return new ResponseSex(
							"Fuck",
							"Dominantly fuck [murk.name].",
							true,
							false,
							new SMGeneric(
									Util.newArrayListOfValues(
											Main.game.getPlayer()),
									Util.newArrayListOfValues(
											Main.game.getNpc(Murk.class)),
									null,
									null) {
							},
							AFTER_MURK_SEX,
							UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MURK_SEX_DOM"));
					
				} else if(index==4 && !Main.game.getNpc(Murk.class).isFeminine()) {
					if((!Main.game.getPlayer().hasVagina() || Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
							&& (!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
							&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)) {
						return new Response("Submit", "Murk is unable to access your mouth, ass, or pussy, so has no interest in trying to fuck you.", null);
					}
					return new ResponseSex(
							"Submit",
							"Tell [murk.name] that you want [murk.herHim] to dominate you...",
							true,
							false,
							new SMAllFours(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Murk.class),
											(!Main.game.getPlayer().hasVagina() || Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
											&& (!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
												?SexSlotAllFours.IN_FRONT
												:SexSlotAllFours.HUMPING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return getMainSexPreference(character, targetedCharacter);
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										if(targetedCharacter.hasVagina()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											
										} else if(Main.game.isAnalContentEnabled()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
										}
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
										
									} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)){
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
										
									} else {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
									}
									
									if(map.isEmpty()) {
										return super.exposeAtStartOfSexMap();
									}
									map.put(Main.game.getNpc(Murk.class), Util.newArrayListOfValues(CoverableArea.PENIS));
									
									return map;
								}
							},
							null,
							null,
							AFTER_MURK_SEX,
							(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
								?UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MURK_SEX_SUB_VAGINA")
								:((Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
									?UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MURK_SEX_SUB_ANUS")
									:UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MURK_SEX_SUB"))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, true);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)){
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
								
							} else {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						}
					};
					
				} else if(index==5 && !Main.game.getNpc(Murk.class).isFeminine()) {
					return new Response("Feminise",
							"Use some of the potions in the storage room to feminise [murk.name] into a submissive rat-girl."
									+ "<br/>[style.italicsGenericTf(This is a permanent transformation, after which you will no longer be able to choose the 'Submit' action with her.)]",
							MURK_FEMINISE) {
						@Override
						public void effects() {
							((Murk)Main.game.getNpc(Murk.class)).applyFeminisation();
						}
					};
					
				} else if(index==0) {
					return new Response("Back", "Head back out of the storage room and let Epona know that you've finished with [murk.name].", MURK_LEAVE) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_ALONE_LEAVE"));
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode MURK_LECTURING = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_LECTURING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MURK_SPANKING = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SPANKING");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode AFTER_MURK_SEX = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.sex.isDom(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_AS_DOM");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_AS_SUB");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.sex.isDom(Main.game.getPlayer())) {
					return new Response("Leave", "Leave [murk.name] panting on the floor and leave", MURK_LEAVE){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_DOM_LEAVE"));
						}
					};
				} else {
						return new Response("Leave", "Do as Murk says and leave", MURK_LEAVE){
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "AFTER_MURK_SEX_SUB_LEAVE"));
							}
						};
				}
			}
			return null;
		}
	};

	public static final DialogueNode MURK_FEMINISE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_FEMINISE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MURK_LEAVE = new DialogueNode("", "", false) {
		@Override
		public void applyPreParsingEffects() {
			Main.game.getPlayer().setLocation(WorldType.GAMBLING_DEN, PlaceType.GAMBLING_DEN_CORRIDOR);
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return GamblingDenDialogue.CORRIDOR.getResponse(responseTab, index);
		}
	};

	private static boolean isMurkNoAccess() {
		return (!Main.game.getPlayer().hasVagina() || Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
				&& (!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
				&& !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true);
	}
	
	public static final DialogueNode MURK_SUBMIT_ACCEPT = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			if(Main.game.isGapeContentEnabled()
					&& Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
					&& ((Main.game.getPlayer().hasVagina() && Main.game.getPlayer().getVaginaPlasticity().getValue()<OrificePlasticity.SEVEN_MOULDABLE.getValue())
							|| (Main.game.isAnalContentEnabled() && Main.game.getPlayer().getAssPlasticity().getValue()<OrificePlasticity.SEVEN_MOULDABLE.getValue()))) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_ACCEPT_POTION");
			}
			if(isMurkNoAccess()) {
				return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_ACCEPT_NO_ACCESS");
			}
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_ACCEPT");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.isGapeContentEnabled()
					&& ((Main.game.getPlayer().hasVagina() && Main.game.getPlayer().getVaginaPlasticity().getValue()<OrificePlasticity.SEVEN_MOULDABLE.getValue())
							|| (Main.game.isAnalContentEnabled() && Main.game.getPlayer().getAssPlasticity().getValue()<OrificePlasticity.SEVEN_MOULDABLE.getValue()))) {
				if(index==1) {
					return new Response("Drink",
							"Drink the potion Murk has found for you.",
									MURK_SUBMIT_ACCEPT_POTION) {
						@Override
						public void effects() {
							if(Main.game.getPlayer().hasVagina()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setVaginaPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue()));
							}
							if(Main.game.isAnalContentEnabled()) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setAssPlasticity(OrificePlasticity.SEVEN_MOULDABLE.getValue()));
							}
						}
					};
				}
				
			} else {
				if(index==1) {
					if(isMurkNoAccess()) {
						return new Response("Kicked out", "Murk is unable to access your mouth, ass, or pussy, so has no interest in trying to fuck you.", MURK_LEAVE) {
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_ACCEPT_KICKED_OUT"));
							}
						};
					}
					return new ResponseSex(
							"Submit",
							"You submit to [murk.name] and let him dominate you...",
							true,
							false,
							new SMAllFours(
									Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Murk.class),
											(!Main.game.getPlayer().hasVagina() || Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
											&& (!Main.game.isAnalContentEnabled() || !Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
												?SexSlotAllFours.IN_FRONT
												:SexSlotAllFours.HUMPING)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotAllFours.ALL_FOURS))) {
								@Override
								public boolean isSwapPositionAllowed(GameCharacter character, GameCharacter target) {
									return false;
								}
								@Override
								public boolean isPositionChangingAllowed(GameCharacter character) {
									return false;
								}
								@Override
								public SexType getForeplayPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										return getMainSexPreference(character, targetedCharacter);
									}
									return super.getForeplayPreference(character, targetedCharacter);
								}
								@Override
								public SexType getMainSexPreference(GameCharacter character, GameCharacter targetedCharacter) {
									if(!character.isPlayer()) {
										if(targetedCharacter.hasVagina()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA);
											
										} else if(Main.game.isAnalContentEnabled()) {
											return new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS);
										}
									}
									return super.getMainSexPreference(character, targetedCharacter);
								}
								@Override
								public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
									Map<GameCharacter, List<CoverableArea>> map = new HashMap<>();
									
									if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.VAGINA));
										
									} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)){
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.ANUS));
										
									} else {
										map.put(Main.game.getPlayer(), Util.newArrayListOfValues(CoverableArea.MOUTH));
									}
									
									if(map.isEmpty()) {
										return super.exposeAtStartOfSexMap();
									}
									map.put(Main.game.getNpc(Murk.class), Util.newArrayListOfValues(CoverableArea.PENIS));
									
									return map;
								}
							},
							null,
							null,
							AFTER_MURK_SEX,
							(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true))
								?UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MURK_SUBMIT_SEX_SUB_VAGINA")
								:((Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true))
									?UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MURK_SUBMIT_SEX_SUB_ANUS")
									:UtilText.parseFromXMLFile("places/submission/ratWarrens/core", "MURK_SUBMIT_SEX_SUB"))) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().setFlag(DialogueFlagValue.eponaMurkSubmitted, true);
						}
						@Override
						public List<InitialSexActionInformation> getInitialSexActions() {
							if(Main.game.getPlayer().hasVagina() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisVagina.PENIS_FUCKING_START, false, true));
								
							} else if(Main.game.isAnalContentEnabled() && Main.game.getPlayer().isAbleToAccessCoverableArea(CoverableArea.ANUS, true)){
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisAnus.PENIS_FUCKING_START, false, true));
								
							} else {
								return Util.newArrayListOfValues(new InitialSexActionInformation(Main.game.getNpc(Murk.class), Main.game.getPlayer(), PenisMouth.BLOWJOB_START, false, true));
							}
						}
					};
				}
			}
			return null;
		}
	};

	public static final DialogueNode MURK_SUBMIT_ACCEPT_POTION = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_ACCEPT_POTION");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_SUBMIT_ACCEPT.getResponse(responseTab, index);
		}
	};

	public static final DialogueNode MURK_SUBMIT_REFUSE = new DialogueNode("", "", true) {
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/submission/gamblingDen/pregnancyRoulette", "MURK_SUBMIT_REFUSE");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return MURK_ALONE.getResponse(responseTab, index);
		}
	};
	
	
}
