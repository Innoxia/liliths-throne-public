package com.lilithsthrone.controller;

import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.Covering;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.MiscDialogue;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.4.6.4
 * @version 0.4.6.4
 * @author Maxis010, Innoxia
 */
public class CoveringController {
	private static boolean isNoCost() {
		return !Main.game.isInNewWorld()
				|| ScarlettsShop.isSlaveCustomisationMenu()
				|| Main.game.getCurrentDialogueNode() == MiscDialogue.getMakeupDialogueForEqualityCheck()
				|| Main.game.getCurrentDialogueNode() == RoomPlayer.AUNT_HOME_PLAYERS_ROOM_MAKEUP
				|| Main.game.getCurrentDialogueNode().getDialogueNodeType() == DialogueNodeType.PHONE;
	}
	
	public static void initCoveringChangeListeners() {
		String id;
		for (AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
			id = "APPLY_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(bct);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(bct) || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (CharacterModificationUtils.getCoveringsToBeApplied().containsKey(bct)) {
									if (!isNoCost()) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
									}
									
									BodyChanging.getTarget().setSkinCovering(new Covering(CharacterModificationUtils.getCoveringsToBeApplied().get(bct)), false);
									
									if (BodyChanging.getTarget().isPlayer() && bct == BodyCoveringType.HUMAN) { // Start of game should reset skin colourings for all parts
										BodyChanging.getTarget().getBody().updateCoverings(false, false, false, true);
									}
								}
							}
						});
					}
				}, false);
			}
			
			id = "RESET_COVERING_"+BodyCoveringType.getIdFromBodyCoveringType(bct);
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getBodyCoveringTypeCost(bct) || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().remove(bct);
							}
						});
					}
				}, false);
			}
			
			id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PRIMARY_GLOW_OFF";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
							CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(false);
						}
					});
				}, false);
			}
			
			id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PRIMARY_GLOW_ON";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
							CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(true);
						}
					});
				}, false);
			}
			
			id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_SECONDARY_GLOW_OFF";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
							CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(false);
						}
					});
				}, false);
			}
			
			id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_SECONDARY_GLOW_ON";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
							CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(true);
						}
					});
				}, false);
			}
			
			for (CoveringPattern pattern : CoveringPattern.values()) {
				id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PATTERN_"+pattern;
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPattern(pattern);
							}
						});
					}, false);
				}
			}
			
			for (CoveringModifier modifier : CoveringModifier.values()) {
				id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_MODIFIER_"+modifier;
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setModifier(modifier);
							}
						});
					}, false);
				}
			}
			
			for (Colour colour : bct.getAllPrimaryColours()) {
				id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_PRIMARY_"+colour.getId();
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryColour(colour);
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing((colour != PresetColour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isPrimaryGlowing()));
							}
						});
					}, false);
				}
			}
			
			for (Colour colour : bct.getAllSecondaryColours()) {
				id = BodyCoveringType.getIdFromBodyCoveringType(bct)+"_SECONDARY_"+colour.getId();
				if (MainController.document.getElementById(id) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryColour(colour);
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(colour != PresetColour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isSecondaryGlowing());
							}
						});
					}, false);
				}
			}
		}
		
		id = "MAKEUP_LIPSTICK_HEAVY_ON";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						BodyChanging.getTarget().addHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
					}
				});
			}, false);
		}
		
		id = "MAKEUP_LIPSTICK_HEAVY_OFF";
		if (MainController.document.getElementById(id) != null) {
			((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
				Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
					@Override
					public void effects() {
						BodyChanging.getTarget().removeHeavyMakeup(BodyCoveringType.MAKEUP_LIPSTICK);
					}
				});
			}, false);
		}
	}
	
	public static void initHairLengthListeners() {
		for (HairLength hairLength : HairLength.values()) {
			String id = "HAIR_LENGTH_"+hairLength;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_LENGTH_COST || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_LENGTH_COST);
								}
								BodyChanging.getTarget().setHairLength(hairLength.getMedianValue());
							}
						});
					}
				}, false);
			}
		}
	}
	
	public static void initHairStyleListeners() {
		for (HairStyle hairStyle : HairStyle.values()) {
			String id = "HAIR_STYLE_"+hairStyle;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_HAIR_STYLE_COST || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_STYLE_COST);
								}
								BodyChanging.getTarget().setHairStyle(hairStyle);
							}
						});
					}
				}, false);
			}
		}
	}
	
	public static void initPiercingsListeners() {
		for (PiercingType piercingType : PiercingType.values()) {
			String id = piercingType+"_PIERCE_REMOVE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(piercingType) || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
								}
								switch (piercingType) {
									case EAR:
										BodyChanging.getTarget().setPiercedEar(false);
										break;
									case LIP:
										BodyChanging.getTarget().setPiercedLip(false);
										break;
									case NAVEL:
										BodyChanging.getTarget().setPiercedNavel(false);
										break;
									case NIPPLE:
										BodyChanging.getTarget().setPiercedNipples(false);
										break;
									case NOSE:
										BodyChanging.getTarget().setPiercedNose(false);
										break;
									case PENIS:
										BodyChanging.getTarget().setPiercedPenis(false);
										break;
									case TONGUE:
										BodyChanging.getTarget().setPiercedTongue(false);
										break;
									case VAGINA:
										BodyChanging.getTarget().setPiercedVagina(false);
										break;
								}
							}
						});
					}
				}, false);
			}
			
			id = piercingType+"_PIERCE";
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.getPiercingCost(piercingType) || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
								}
								switch (piercingType) {
									case EAR:
										BodyChanging.getTarget().setPiercedEar(true);
										break;
									case LIP:
										BodyChanging.getTarget().setPiercedLip(true);
										break;
									case NAVEL:
										BodyChanging.getTarget().setPiercedNavel(true);
										break;
									case NIPPLE:
										BodyChanging.getTarget().setPiercedNipples(true);
										break;
									case NOSE:
										BodyChanging.getTarget().setPiercedNose(true);
										break;
									case PENIS:
										BodyChanging.getTarget().setPiercedPenis(true);
										break;
									case TONGUE:
										BodyChanging.getTarget().setPiercedTongue(true);
										break;
									case VAGINA:
										BodyChanging.getTarget().setPiercedVagina(true);
										break;
								}
							}
						});
					}
				}, false);
			}
		}
	}
	
	public static void initBleachingListeners() {
		if (MainController.document.getElementById("BLEACHING_OFF") != null) {
			((EventTarget) MainController.document.getElementById("BLEACHING_OFF")).addEventListener("click", e->{
				if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST || isNoCost()) {
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							if (!isNoCost()) {
								Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
							}
							BodyChanging.getTarget().setAssBleached(false);
						}
					});
				}
			}, false);
		}
		
		if (MainController.document.getElementById("BLEACHING_ON") != null) {
			((EventTarget) MainController.document.getElementById("BLEACHING_ON")).addEventListener("click", e->{
				if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_ANAL_BLEACHING_COST || isNoCost()) {
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
						@Override
						public void effects() {
							if (!isNoCost()) {
								Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
							}
							BodyChanging.getTarget().setAssBleached(true);
						}
					});
				}
			}, false);
		}
	}
	
	public static void initAssHairListeners() {
		for (BodyHair bodyHair : BodyHair.values()) {
			String id = "ASS_HAIR_"+bodyHair;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
								}
								BodyChanging.getTarget().setAssHair(bodyHair);
							}
						});
					}
				}, false);
			}
		}
	}
	
	public static void initUnderarmHairListeners() {
		for (BodyHair bodyHair : BodyHair.values()) {
			String id = "UNDERARM_HAIR_"+bodyHair;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
								}
								BodyChanging.getTarget().setUnderarmHair(bodyHair);
							}
						});
					}
				}, false);
			}
		}
	}
	
	public static void initPubicHairListeners() {
		for (BodyHair bodyHair : BodyHair.values()) {
			String id = "PUBIC_HAIR_"+bodyHair;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
								}
								BodyChanging.getTarget().setPubicHair(bodyHair);
							}
						});
					}
				}, false);
			}
		}
	}
	
	public static void initFacialHairListeners() {
		for (BodyHair bodyHair : BodyHair.values()) {
			String id = "FACIAL_HAIR_"+bodyHair;
			if (MainController.document.getElementById(id) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e->{
					if (Main.game.getPlayer().getMoney()>=SuccubisSecrets.BASE_BODY_HAIR_COST || isNoCost()) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
							@Override
							public void effects() {
								if (!isNoCost()) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
								}
								BodyChanging.getTarget().setFacialHair(bodyHair);
							}
						});
					}
				}, false);
			}
		}
	}
}
