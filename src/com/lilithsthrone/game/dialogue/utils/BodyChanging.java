package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.90
 * @version 0.3.9.1
 * @author Innoxia
 */
public class BodyChanging {
	
	private static GameCharacter target;
	private static DialogueNode coreNode;
	private static int defaultResponseTab;
	private static boolean debugMenu;
	
	public static boolean isDebugMenu() {
		return debugMenu;
	}

	public static GameCharacter getTarget() {
		if(target==null) {
			return Main.game.getPlayer();
		}
		return target;
	}

	private static String getPowers() {
		return (isDemonTFMenu()?"demonic":"innate")+" transformative powers on changing";
	}

	public static void setTarget(GameCharacter target) {
		BodyChanging.target = target;
		BodyChanging.coreNode = null;
		BodyChanging.debugMenu = false;
	}

	public static void setTarget(GameCharacter target, DialogueNode coreNode, int defaultResponseTab) {
		BodyChanging.target = target;
		BodyChanging.coreNode = coreNode;
		BodyChanging.defaultResponseTab = defaultResponseTab;
		BodyChanging.debugMenu = false;
	}
	
	/**
	 * @param target
	 * @param debugMenu If this menu is accessed from the debug menu.
	 */
	public static void setTarget(GameCharacter target, boolean debugMenu) {
		BodyChanging.target = target;
		BodyChanging.coreNode = null;
		BodyChanging.debugMenu = debugMenu;
	}
	
	private static Response getBodyChangingResponse(int responseTab, int index) {
		if(index==1) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_CORE) {
				return new Response("Core", "You are already in this screen!", null);
			}
			return new Response("Core",
					UtilText.parse(getTarget(), "Change core aspects of [npc.namePos] body."),
					BODY_CHANGING_CORE);
			
		} else if(index==2) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_EYES) {
				return new Response("Eyes", "You are already in this screen!", null);
			}
			return new Response("Eyes", 
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] eyes."),
					BODY_CHANGING_EYES);
			
		} else if(index==3) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_HAIR) {
				return new Response("Hair", "You are already in this screen!", null);
			}
			return new Response("Hair", 
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] hair."),
					BODY_CHANGING_HAIR);
			
		} else if(index==4) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_HEAD) {
				return new Response("Head", "You are already in this screen!", null);
			}
			return new Response("Head", 
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] face and head."),
					BODY_CHANGING_HEAD);
			
		} else if(index==5) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_ASS) {
				return new Response("Ass", "You are already in this screen!", null);
			}
			return new Response("Ass",
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] ass."),
					BODY_CHANGING_ASS);
			
		} else if(index==6) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_BREASTS) {
				return new Response("Breasts", "You are already in this screen!", null);
			}
			if(!BodyChanging.getTarget().hasNipples()) {
				return new Response("Breasts",
						UtilText.parse(getTarget(), "[npc.Name] [npc.do] not have any breasts!"),
						null);
			}
			return new Response("Breasts",
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] breasts."),
					BODY_CHANGING_BREASTS);
			
		} else if(index==7) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_VAGINA) {
				return new Response("Vagina", "You are already in this screen!", null);
			}
			return new Response("Vagina", 
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] vagina."),
					BODY_CHANGING_VAGINA);
			
		} else if(index==8) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_PENIS) {
				return new Response("Penis", "You are already in this screen!", null);
			}
			return new Response("Penis", 
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] penis."),
					BODY_CHANGING_PENIS);
			
		} else if(index==9) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_SPINNERET) {
				return new Response("Spinneret", "You are already in this screen!", null);
			}
			if(!BodyChanging.getTarget().hasSpinneret()) {
				return new Response("Spinneret",
						UtilText.parse(getTarget(), "[npc.Name] [npc.do] not have a spinneret!<br/><i>Spinnerets are gained via certain tail or leg types.</i>"),
						null);
			}
			return new Response("Spinneret", 
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] spinneret."),
					BODY_CHANGING_SPINNERET);
			
		} else if(index==10 && (BodyChanging.getTarget().isFeral() || Main.getProperties().getUddersLevel()!=0 || debugMenu)) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_BREASTS_CROTCH) {
				return new Response(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs", "You are already in this screen!", null);
			}
			if(BodyChanging.getTarget().isFeral() || debugMenu) {
				if(!BodyChanging.getTarget().isFeral() && Main.getProperties().getUddersLevel()==0) {
					return new Response(
							BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs",
							UtilText.parse(getTarget(), "Change aspects of [npc.namePos] [npc.crotchBoobs]."
									+ "<br/>[style.italicsBad(Crotch-boobs are disabled in the content settings! You cannot access these options outside of this debug menu!)]"),
							BODY_CHANGING_BREASTS_CROTCH) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_BAD;
						}
					};
				}
				if(!BodyChanging.getTarget().isFeral() && Main.getProperties().getUddersLevel()==1 && BodyChanging.getTarget().getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
					return new Response(
							BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs",
							UtilText.parse(getTarget(), "Change aspects of [npc.namePos] [npc.crotchBoobs]."
									+ "<br/>[style.italicsMinorBad(Crotch-boobs are disabled for non-taur characters in the content settings! As a result of this, you cannot access these options outside of this debug menu!)]"),
							BODY_CHANGING_BREASTS_CROTCH) {
						@Override
						public Colour getHighlightColour() {
							return PresetColour.GENERIC_MINOR_BAD;
						}
					};
				}
			}
			
			if(!BodyChanging.getTarget().isFeral() && Main.getProperties().getUddersLevel()==1 && BodyChanging.getTarget().getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				return new Response("Crotch-boobs", "As you have crotch-boobs disabled for non-taur characters, you cannot access this menu!", null);
			}
			
			return new Response(
					BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs",
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] [npc.crotchBoobs]."),
					BODY_CHANGING_BREASTS_CROTCH);
			
		} else if(index==11 && debugMenu) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_MAKEUP) {
				return new Response("Makeup", "You are already in this screen!", null);
			}
			return new Response("Makeup", 
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] makeup."),
					BODY_CHANGING_MAKEUP);
			
		}  else if(index==0) {
			if(debugMenu) {
				return new Response("Back", "Return to the previous screen.", DebugDialogue.DEBUG_MENU);
				
			} else if(coreNode!=null) {
				return new Response("Back", "Return to the previous screen.", coreNode) {
					@Override
					public void effects() {
						Main.game.setResponseTab(defaultResponseTab);
					}
				};
				
			} else {
				return new ResponseEffectsOnly("Back", "Return to the previous screen."){
					@Override
					public void effects() {
						Main.game.restoreSavedContent(false);
					}
				};
			}
			
		} else {
			return null;
		}
	}

	private static final List<AbstractRace> allRaces = new ArrayList<>(Race.getAllRaces());

	private static List<AbstractRace> getFaceSkinDemonRaces() {
		List<AbstractRace> faceSkinOptions = Util.newArrayListOfValues();
		GameCharacter target = BodyChanging.getTarget();
		
		if(target.isElemental()) {
			return allRaces;

		} else if(isHalfDemon()) {
			faceSkinOptions.add(target.getHalfDemonSubspecies().getRace());
			faceSkinOptions.add(Race.HUMAN);

		} else if(isSelfTFMenu()) {
			faceSkinOptions.add(target.getRace());
			if (target.isYouko()){
				faceSkinOptions.add(Race.FOX_MORPH);
				faceSkinOptions.add(Race.HUMAN);
			}

		} else {
			faceSkinOptions.add(Race.DEMON);
			if(target.isPlayer()) {
				if(target.hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4)) {
					faceSkinOptions.add(Race.HUMAN);
				}
			}
		}
		return faceSkinOptions;
	}
	
	private static List<AbstractRace> getArmLegDemonRaces() {
		List<AbstractRace> armLegOptions = Util.newArrayListOfValues();
		GameCharacter target = BodyChanging.getTarget();
		
		if(target.isElemental()) {
			return allRaces;
			
		} else if(isHalfDemon()) {
			armLegOptions.add(target.getHalfDemonSubspecies().getRace());

		} else if(isSelfTFMenu()) {
			armLegOptions.add(target.getRace());
			if (target.isYouko()) {
				armLegOptions.add(Race.FOX_MORPH);
				armLegOptions.add(Race.HUMAN);
			}
		} else {
			armLegOptions.add(Race.DEMON);
			if(target.isPlayer()) {
				if(target.hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4)) {
					armLegOptions.add(Race.HUMAN);
				}
			}
		}
		return armLegOptions;
	}
	
	/**
	 * @param isHalfSpeciesReplacement True if this is a part that should always be of the core race type (if not human). This is so that things like hellhounds will still have dog tails and ears.
	 * @return List of races available to the target.
	 */
	private static List<AbstractRace> getMinorPartsDemonRaces(boolean isHalfSpeciesReplacement) {
		List<AbstractRace> minorPartsOptions = Util.newArrayListOfValues();
		GameCharacter target = BodyChanging.getTarget();
		
		if(target.isElemental()) {
			return allRaces;
			
		} else if(isHalfDemon()) {
			if(isHalfSpeciesReplacement && target.getHalfDemonSubspecies().getRace()!=Race.HUMAN) {
				minorPartsOptions.add(target.getHalfDemonSubspecies().getRace());
			} else {
				minorPartsOptions.add(Race.DEMON);
			}

		} else if(isSelfTFMenu()) {
			minorPartsOptions.add(target.getRace());
			if (target.isYouko()) {
				minorPartsOptions.add(Race.FOX_MORPH);
				minorPartsOptions.add(Race.HUMAN);
			}
		} else {
			minorPartsOptions.add(Race.DEMON);
			if(target.isPlayer()) {
				if(target.hasPerkAnywhereInTree(Perk.POWER_OF_LYSSIETH_4)) {
					minorPartsOptions.add(Race.HUMAN);
				}
			}
		}
		
		return minorPartsOptions;
	}
	
	private static boolean removeNoneFromTailChoices() {
		if(isHalfDemon() && !(BodyChanging.getTarget().isElemental())) {
			return !RacialBody.valueOfRace(target.getHalfDemonSubspecies().getRace()).getTailType().contains(TailType.NONE);
		}
		return false;
	}
	
	private static boolean removeNoneFromWingChoices() {
		if(isHalfDemon() && !(BodyChanging.getTarget().isElemental())) {
			return !RacialBody.valueOfRace(target.getHalfDemonSubspecies().getRace()).getWingTypes().contains(WingType.NONE);
		}
		return false;
	}
	
	private static boolean isDemonTFMenu() {
		return !debugMenu
				&& BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.SLIME
				&& (BodyChanging.getTarget().getRace()==Race.DEMON
					|| BodyChanging.getTarget().getSubspeciesOverride()==Subspecies.DEMON
					|| BodyChanging.getTarget().getSubspeciesOverride()==Subspecies.LILIN
					|| BodyChanging.getTarget().getSubspeciesOverride()==Subspecies.ELDER_LILIN
					|| BodyChanging.getTarget().isElemental());
	}

	private static boolean isSelfTFMenu() {
		return !debugMenu
				&& !isDemonTFMenu()
				&& BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.SLIME
				&& BodyChanging.getTarget().getTrueSubspecies().isAbleToSelfTransform();
	}

	private static boolean isHalfDemon() {
		return BodyChanging.getTarget().getSubspeciesOverride()==Subspecies.HALF_DEMON;
	}
	
//	private static Map<AbstractBodyCoveringType, List<String>> getMainCoveringsMap() {
//		Map<AbstractBodyCoveringType, List<String>> coveringsNamesMap = new LinkedHashMap<>();
//
////		if(getTarget().isElemental()) {
////			switch(getTarget().getBodyMaterial()) {
////				case AIR:
////					coveringsNamesMap.put(BodyCoveringType.AIR, Util.newArrayListOfValues("AIR"));
////					break;
////				case ARCANE:
////					coveringsNamesMap.put(BodyCoveringType.ARCANE, Util.newArrayListOfValues("ARCANE"));
////					break;
////				case FIRE:
////					coveringsNamesMap.put(BodyCoveringType.FIRE, Util.newArrayListOfValues("FIRE"));
////					break;
////				case FLESH:
////					break;
////				case ICE:
////					coveringsNamesMap.put(BodyCoveringType.ICE, Util.newArrayListOfValues("ICE"));
////					break;
////				case RUBBER:
////					coveringsNamesMap.put(BodyCoveringType.RUBBER, Util.newArrayListOfValues("RUBBER"));
////					break;
////				case SLIME:
////					break;
////				case STONE:
////					coveringsNamesMap.put(BodyCoveringType.STONE, Util.newArrayListOfValues("STONE"));
////					break;
////				case WATER:
////					coveringsNamesMap.put(BodyCoveringType.WATER, Util.newArrayListOfValues("WATER"));
////					break;
////			}
////
////		} else if(getTarget().getBodyMaterial()==BodyMaterial.SLIME) {
////			coveringsNamesMap.put(BodyCoveringType.SLIME, Util.newArrayListOfValues("SLIME"));
////
////		} else {
//			for(BodyPartInterface bp : getTarget().getAllBodyParts()){
//				if(bp.getBodyCoveringType(getTarget())!=null
//						&& !(bp instanceof Hair)
//						&& !(bp instanceof Eye)
//						&& !(bp instanceof Mouth)
//						&& !(bp instanceof Vagina)
//						&& !(bp instanceof Ass)
//						&& !(bp instanceof Nipples)
//						&& !(bp instanceof Breast)
//						&& !(bp instanceof Penis)
//						&& !(bp instanceof Antenna)
//						&& !(bp instanceof Horn)) {
//					String name = bp.getName(getTarget());
//					if(bp instanceof Torso) {
//						name = "torso";
//					}
//
//					if(coveringsNamesMap.containsKey(bp.getBodyCoveringType(getTarget()))) {
//						coveringsNamesMap.get(bp.getBodyCoveringType(getTarget())).add(name);
//					} else {
//						coveringsNamesMap.put(bp.getBodyCoveringType(getTarget()), Util.newArrayListOfValues(name));
//					}
//				}
//			}
//			if(getTarget().getTailType()==TailType.DEMON_HAIR_TIP && !coveringsNamesMap.containsKey(BodyCoveringType.HAIR_DEMON)) {
//				coveringsNamesMap.put(BodyCoveringType.HAIR_DEMON, Util.newArrayListOfValues(BodyCoveringType.HAIR_DEMON.getName(getTarget())));
//			}
////		}
//
//		// Return an altered map for if the target's body is not made of flesh:
//		if(getTarget().getBodyMaterial()!=BodyMaterial.FLESH) {
//			Map<AbstractBodyCoveringType, List<String>> altMaterialCoveringsNamesMap = new LinkedHashMap<>();
//			for(Entry<AbstractBodyCoveringType, List<String>> entry : coveringsNamesMap.entrySet()) {
//				if(entry.getKey().getCategory().isInfluencedByMaterialType()) {
//					altMaterialCoveringsNamesMap.put(BodyCoveringType.getMaterialBodyCoveringType(getTarget().getBodyMaterial(), entry.getKey().getCategory()), entry.getValue());
//				}
//			}
//			return altMaterialCoveringsNamesMap;
//		}
//
//
//		return coveringsNamesMap;
//	}
	
	private static List<AbstractRace> getSlaveCustomisationRaceOptions() {
		List<AbstractRace> list = new ArrayList<>();
		
		list.add(Race.NONE);

		for(AbstractRace race : Race.getAllRaces()) {
			if(!race.isAbleToSelfTransform()
					&& (Subspecies.getWorldSpecies(WorldType.DOMINION, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false).keySet().stream().anyMatch(s->s.getRace()==race)
						|| Subspecies.getWorldSpecies(WorldType.SUBMISSION, PlaceType.SLAVER_ALLEY_SCARLETTS_SHOP, false).keySet().stream().anyMatch(s->s.getRace()==race))) {
				list.add(race);
			}
		}
		
		return list;
	}
	
	private static List<AbstractRace> getRacesForMinorPartSelfTransform() {
		if(ScarlettsShop.isSlaveCustomisationMenu()) {
			return getSlaveCustomisationRaceOptions();
		}
		if(isDemonTFMenu()||isSelfTFMenu()) {
			return getTarget().isElemental()
				?allRaces
				:getMinorPartsDemonRaces(false);
		}
		return allRaces;
	}
	
	private static String getSelfTransformDescription(String area) {
		if(ScarlettsShop.isSlaveCustomisationMenu()) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div class='container-full-width' style='text-align:center;'>");
			if(isDemonTFMenu()) {
				sb.append(UtilText.parse(BodyChanging.getTarget(), "<i>[npc.Name] can harness the power of [npc.her] demonic form to self-transform aspects of [npc.her] "+area+".</i>"));

			} else if(isSelfTFMenu()) {
				sb.append(UtilText.parse(BodyChanging.getTarget(), "<i>[npc.Name] can harness [npc.her] innate powers to self-transform aspects of [npc.her] "+area+".</i>"));

			} else if(debugMenu) {
				sb.append(UtilText.parse(BodyChanging.getTarget(), "<i>[npc.Name] can harness the power of the debugging tool to self-transform aspects of [npc.her] "+area+".</i>"));
				
			} else {
				sb.append(UtilText.parse(BodyChanging.getTarget(), "<i>[npc.Name] can take advantage of [npc.her] morphable, slimy body to self-transform aspects of [npc.her] "+area+".</i>"));
			}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static final DialogueNode BODY_CHANGING_CORE = new DialogueNode("Core", "", true) {
		@Override
		public void applyPreParsingEffects() {
			SuccubisSecrets.initCoveringsMap(BodyChanging.getTarget());
		}
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(ScarlettsShop.isSlaveCustomisationMenu()) {
				SuccubisSecrets.initCoveringsMap(BodyChanging.getTarget());
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
							+ CharacterModificationUtils.getAgeAppearanceChoiceDiv()
						+"</div>"
							
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformFemininityChoiceDiv()
							+ CharacterModificationUtils.getHeightChoiceDiv()
						+"</div>"
							
						+ "<div class='cosmetics-container' style='background:transparent;'>"
							+ CharacterModificationUtils.getBodySizeChoiceDiv()
							+ CharacterModificationUtils.getMuscleChoiceDiv()
							+ "<div class='container-full-width' style='text-align:center;'>"
							+ UtilText.parse(BodyChanging.getTarget(),
									"[npc.NamePos] muscle and body size values give [npc.herHim] the body shape: "
										+ "<b style='color:"+BodyChanging.getTarget().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(BodyChanging.getTarget().getBodyShape().getName(false))+"</b>")
							+ "</div>"
						+"</div>"
							
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformFaceChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformBodyChoiceDiv(getSlaveCustomisationRaceOptions())
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformArmChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformLegChoiceDiv(getSlaveCustomisationRaceOptions(), isDebugMenu())
						+"</div>"
		
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformArmCountDiv()
							+ CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformGenitalArrangementChoiceDiv()
						+"</div>"
		
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTailChoiceDiv(getSlaveCustomisationRaceOptions(), false)
							+ CharacterModificationUtils.getSelfTransformTailLengthDiv()
						+"</div>"
							
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTailCountDiv()
							+ CharacterModificationUtils.getSelfTransformTailGirthDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTentacleLengthDiv()
							+ CharacterModificationUtils.getSelfTransformTentacleGirthDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformWingChoiceDiv(getSlaveCustomisationRaceOptions(), false)
							+ CharacterModificationUtils.getSelfTransformWingSizeDiv()
						+"</div>");

			} else if(isDemonTFMenu()||isSelfTFMenu()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
						?"<i>Focus your "+getPowers()+" core aspects of your body.</i>"
						:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] "+getPowers()+" core aspects of [npc.her] body.</i>"))
						+ "</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getAgeAppearanceChoiceDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformFemininityChoiceDiv()
							+ CharacterModificationUtils.getHeightChoiceDiv()
						+"</div>"
						
						+ (BodyChanging.getTarget().isElemental()
								?CharacterModificationUtils.getSelfTransformBodyMaterialChoiceDiv(BodyChanging.getTarget())
								:"")
						
						+ "<div class='cosmetics-container' style='background:transparent;'>"
							+ CharacterModificationUtils.getBodySizeChoiceDiv()
							+ CharacterModificationUtils.getMuscleChoiceDiv()
							+ "<div class='container-full-width' style='text-align:center;'>"
									+ UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos] muscle and body size values give [npc.herHim] the body shape: "
											+ "<b style='color:"+getTarget().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(getTarget().getBodyShape().getName(false))+"</b>")
							+ "</div>"
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformFaceChoiceDiv(getFaceSkinDemonRaces())
							+ CharacterModificationUtils.getSelfTransformBodyChoiceDiv(getFaceSkinDemonRaces())
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformArmChoiceDiv(getArmLegDemonRaces())
							+ CharacterModificationUtils.getSelfTransformLegChoiceDiv(getArmLegDemonRaces(), isDebugMenu())
						+"</div>");

						if(!BodyChanging.getTarget().isYouko()) {
							UtilText.nodeContentSB.append(
								"<div style='clear:left;'>"
								+ CharacterModificationUtils.getSelfTransformArmCountDiv()
								+ CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
								+"</div>"

								+"<div style='clear:left;'>"
								+ CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
								+ CharacterModificationUtils.getSelfTransformGenitalArrangementChoiceDiv()
								+"</div>"
							);
						}
						else {
							UtilText.nodeContentSB.append(
								"<div style='clear:left;'>"
								+ CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
								+ CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
								+"</div>"
							);
						}

						UtilText.nodeContentSB.append(
							"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTailChoiceDiv(
									(getTarget().isElemental()
											?allRaces
											:(removeNoneFromTailChoices()||isSelfTFMenu()
												?getMinorPartsDemonRaces(true)
												:Util.newArrayListOfValues(Race.DEMON))),
									removeNoneFromTailChoices())
							+ CharacterModificationUtils.getSelfTransformTailLengthDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTailCountDiv()
							+ CharacterModificationUtils.getSelfTransformTailGirthDiv()
							+"</div>"
						);

						if (!BodyChanging.getTarget().isYouko()) {
							UtilText.nodeContentSB.append(
								"<div style='clear:left;'>"
									+ CharacterModificationUtils.getSelfTransformTentacleLengthDiv()
									+ CharacterModificationUtils.getSelfTransformTentacleGirthDiv()
								+"</div>"

								+"<div style='clear:left;'>"
									+ CharacterModificationUtils.getSelfTransformWingChoiceDiv(
										(getTarget().isElemental())
											?allRaces
											:(!removeNoneFromWingChoices()
												?Util.newArrayListOfValues(Race.DEMON)
												:getMinorPartsDemonRaces(true)),
										removeNoneFromWingChoices())
									+ CharacterModificationUtils.getSelfTransformWingSizeDiv()
								+"</div>"
							);
						}
			// Slime/debug:
			} else {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing core aspects of your body.</i>"
								:UtilText.parse(BodyChanging.getTarget(),
										"<i>Get [npc.name] to focus [npc.her] efforts on changing core aspects of [npc.her] body.</i>"))
					+ "</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getAgeAppearanceChoiceDiv()
					+"</div>"
						
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformFemininityChoiceDiv()
						+ CharacterModificationUtils.getHeightChoiceDiv()
					+"</div>"
						
					+ "<div class='cosmetics-container' style='background:transparent;'>"
						+ CharacterModificationUtils.getBodySizeChoiceDiv()
						+ CharacterModificationUtils.getMuscleChoiceDiv()
						+ "<div class='container-full-width' style='text-align:center;'>"
						+ UtilText.parse(BodyChanging.getTarget(), "[npc.NamePos] muscle and body size values give [npc.herHim] the body shape: "
										+ "<b style='color:"+getTarget().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(getTarget().getBodyShape().getName(false))+"</b>")
						+ "</div>"
					+"</div>"
						
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformFaceChoiceDiv(allRaces)
						+ CharacterModificationUtils.getSelfTransformBodyChoiceDiv(allRaces)
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformArmChoiceDiv(allRaces)
						+ CharacterModificationUtils.getSelfTransformLegChoiceDiv(allRaces, isDebugMenu())
					+"</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformArmCountDiv()
						+ CharacterModificationUtils.getSelfTransformFootStructureChoiceDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformLegConfigurationChoiceDiv()
						+ CharacterModificationUtils.getSelfTransformGenitalArrangementChoiceDiv()
					+"</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformTailChoiceDiv(allRaces, false)
						+ CharacterModificationUtils.getSelfTransformTailLengthDiv()
					+"</div>"
						
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformTailCountDiv()
						+ CharacterModificationUtils.getSelfTransformTailGirthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformTentacleLengthDiv()
						+ CharacterModificationUtils.getSelfTransformTentacleGirthDiv()
					+"</div>"
						
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformWingChoiceDiv(allRaces, false)
						+ CharacterModificationUtils.getSelfTransformWingSizeDiv()
					+"</div>");
			}
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : SuccubisSecrets.coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();

				Value<String, String> titleDescription = SuccubisSecrets.getCoveringTitleDescription(target, bct, entry.getValue().getValue());

				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(target, titleDescription.getValue()),
						true,
						true));
			}

			if(Main.getProperties().hasValue(PropertyValue.bodyHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivUnderarmHair(false, "Underarm hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair in [npc.namePos] underarms."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getUnderarmHairType().getType(), "Underarm hair colour",
						UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] underarm hair."),
						true, true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_EYES = new DialogueNode("Eyes", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			if(ScarlettsShop.isSlaveCustomisationMenu()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformEyeChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformEyeCountDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getEyeType().getRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getEyeCovering()).getType(),
								"Iris colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(),
								"Pupil colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(),
								"Sclerae colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae."),
								true, true));
				
			} else if(debugMenu) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your eyes.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] eyes.</i>"))
						+ "</div>"
	
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformEyeChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformEyeCountDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getEyeType().getRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getEyeCovering()).getType(),
								"Iris colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(),
								"Pupil colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(),
								"Sclerae colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae."),
								true, true));

			} else if(isDemonTFMenu()||isSelfTFMenu()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your "+getPowers()+" aspects of your eyes.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] "+getPowers()+" aspects of [npc.her] eyes.</i>"))
						+ "</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformEyeChoiceDiv(
									(getMinorPartsDemonRaces(false)))
							+ (BodyChanging.getTarget().isYouko()?"":CharacterModificationUtils.getSelfTransformEyeCountDiv())
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getEyeType().getRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getEyeCovering()).getType(),
								"Iris colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(),
								"Pupil colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(),
								"Sclerae colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae."),
								true, true));
				
			// Slime:
			} else {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your slimy eyes.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] slimy eyes.</i>"))
						+ "</div>"
	
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformEyeChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformEyeCountDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getEyeType().getRace(), BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.EYE_IRIS),
								"Iris colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getEyeType().getRace(), BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.EYE_PUPIL),
								"Pupil colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getEyeType().getRace(), BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.EYE_SCLERA),
								"Sclerae colour",
								UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae."),
								true, true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	

	public static final DialogueNode BODY_CHANGING_HAIR = new DialogueNode("Hair", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			if(ScarlettsShop.isSlaveCustomisationMenu()) {
				UtilText.nodeContentSB.append(
						"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair style."))
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getHairRace(),
								BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(), "Hair colour",
								UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.her] hair."), true, true));
				
			} else if(debugMenu) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your hair.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] hair.</i>"))
						+ "</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair style."))
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getHairRace(),
								BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(), "Hair colour",
								(BodyChanging.getTarget().isPlayer()
										?"Change the colour of your hair."
										:UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.her] hair.")), true, true));

			} else if(isDemonTFMenu()||isSelfTFMenu()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your "+getPowers()+" aspects of your hair.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] "+getPowers()+" on changing aspects of [npc.her] hair.</i>"))
						+ "</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(
									(getMinorPartsDemonRaces(true)))
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair style."))
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getHairRace(),
								BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(), "Hair colour",
								(isDemonTFMenu()
								?UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the colour of [npc.her] hair.")
								:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness [npc.her] innate powers to change the colour of [npc.her] hair."))
						, true, true));

			// Slime:
			} else {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your slimy hair.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] slimy hair.</i>"))
						+ "</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair style."))
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getHairRace(),
								BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.HAIR), "Hair colour",
								UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can freely change the colour of [npc.her] slimy hair."), true, true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	

	public static final DialogueNode BODY_CHANGING_HEAD = new DialogueNode("Head", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			if(ScarlettsShop.isSlaveCustomisationMenu()) {
				UtilText.nodeContentSB.append(
						CharacterModificationUtils.getSelfTransformEarChoiceDiv(getSlaveCustomisationRaceOptions())

						+ "<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
						+"</div>"

						+ "<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformAntennaSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformAntennaCountDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaePerRowCountDiv()
						+"</div>"
						
						+ (BodyChanging.getTarget().getHornType()!=HornType.NONE
								?CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getHornRace(),
									BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHornCovering()).getType(), "Horn Colour",
									UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] horns."),
									true, true)
								:"")

						+ CharacterModificationUtils.getSelfTransformLipSizeDiv()
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
							+ CharacterModificationUtils.getSelfTransformThroatWetnessDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatCapacityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatDepthDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatElasticityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatPlasticityDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTongueSizeDiv()
							+ CharacterModificationUtils.getSelfTransformTongueModifiersDiv()
						+"</div>"
							
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getMouthType().getRace(), BodyChanging.getTarget().getCovering(BodyCoveringType.MOUTH).getType(),
								"Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(),
										"The natural colour of [npc.namePos] "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."
										+ "Lipstick can be used to conceal [npc.her] natural lip colour."),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getTongueType().getRace(), BodyChanging.getTarget().getCovering(BodyCoveringType.TONGUE).getType(),
								"Tongue colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour of your tongue."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] tongue.")),
								true, true));
				
			} else if(debugMenu) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your head and face.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] head and face.</i>"))
						+ "</div>"
	
						+ CharacterModificationUtils.getSelfTransformEarChoiceDiv(allRaces)
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
						+"</div>"
						
						+ "<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformAntennaSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformAntennaCountDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaePerRowCountDiv()
						+"</div>"

						+ (BodyChanging.getTarget().getHornType()!=HornType.NONE
								?CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getHornRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHornCovering()).getType(),
										"Horn Colour",
									(BodyChanging.getTarget().isPlayer()
										?"The colour of your horns."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] horns.")),
									true, true)
								:"")

						+ CharacterModificationUtils.getSelfTransformLipSizeDiv()
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
							+ CharacterModificationUtils.getSelfTransformThroatWetnessDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatCapacityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatDepthDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatElasticityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatPlasticityDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTongueSizeDiv()
							+ CharacterModificationUtils.getSelfTransformTongueModifiersDiv()
						+"</div>"
							
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getMouthType().getRace(), BodyChanging.getTarget().getCovering(BodyCoveringType.MOUTH).getType(),
								"Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(),
										"The natural colour of [npc.namePos] "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."
										+ "Lipstick can be used to conceal [npc.her] natural lip colour."),
								true, true)
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getTongueType().getRace(), BodyChanging.getTarget().getCovering(BodyCoveringType.TONGUE).getType(),
								"Tongue colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour of your tongue."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] tongue.")),
								true, true));

			} else if(isDemonTFMenu()||isSelfTFMenu()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your "+getPowers()+" aspects of your head and face.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] "+getPowers()+" aspects of [npc.her] head and face.</i>"))
						+ "</div>"
						
						+ CharacterModificationUtils.getSelfTransformEarChoiceDiv(
								(getMinorPartsDemonRaces(true)))
				);

				if(!BodyChanging.getTarget().isYouko()) {
					UtilText.nodeContentSB.append("<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(
							(getTarget().isElemental())
									?allRaces
									:Util.mergeLists(getMinorPartsDemonRaces(true), Util.newArrayListOfValues(Race.NONE, Race.DEMON)))
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
							+"</div>"

							+ "<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(
							(getMinorPartsDemonRaces(true)))
							+ CharacterModificationUtils.getSelfTransformAntennaSizeDiv()
							+"</div>"

							+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformAntennaCountDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaePerRowCountDiv()
							+"</div>"

							+ (BodyChanging.getTarget().getHornType()!=HornType.NONE
							?CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getHornRace(), BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHornCovering()).getType(),
							"Horn Colour",
							(BodyChanging.getTarget().isPlayer()
									?"The colour of your horns."
									:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] horns.")),
							true, true)
							:"")
					);
				}

				UtilText.nodeContentSB.append(

						CharacterModificationUtils.getSelfTransformLipSizeDiv()
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
							+ CharacterModificationUtils.getSelfTransformThroatWetnessDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatCapacityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatDepthDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatElasticityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatPlasticityDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTongueSizeDiv()
							+ CharacterModificationUtils.getSelfTransformTongueModifiersDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getMouthType().getRace(), BodyChanging.getTarget().getCovering(BodyCoveringType.MOUTH).getType(),
								"Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(), "The natural colour of [npc.namePos] slimy "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."),
								true, true)
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getTongueType().getRace(), BodyChanging.getTarget().getCovering(BodyCoveringType.TONGUE).getType(),
								"Tongue colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour of your tongue."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] tongue.")),
								true, true)
				);
				
			// Slime:
			} else {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your slimy head and face.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] slimy head and face.</i>"))
						+ "</div>"
						
						+ CharacterModificationUtils.getSelfTransformEarChoiceDiv(allRaces)
						
						+"<div style='clear:left;'>"
								+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
						+"</div>"
						
						+ "<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformAntennaSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformAntennaCountDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaePerRowCountDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getSelfTransformLipSizeDiv()
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
							+ CharacterModificationUtils.getSelfTransformThroatWetnessDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatCapacityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatDepthDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformThroatElasticityDiv()
							+ CharacterModificationUtils.getSelfTransformThroatPlasticityDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTongueSizeDiv()
							+ CharacterModificationUtils.getSelfTransformTongueModifiersDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.SLIME,
								BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.MOUTH),
								"Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(), "The natural colour of [npc.namePos] slimy "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."),
								true, true)
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.SLIME,
								BodyChanging.getTarget().getCovering(BodyCoveringType.getMaterialBodyCoveringType(BodyMaterial.SLIME, BodyCoveringCategory.TONGUE)).getType(),
								"Tongue colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour of your tongue."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] tongue.")),
								true, true));
			}
			
			if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && (!getTarget().isFeminine() || Main.getProperties().hasValue(PropertyValue.feminineBeardsContent))) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "Beard length",
						UtilText.parse(BodyChanging.getTarget(), "Change the length of [npc.namePos] beard."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getFacialHairType().getType(), "Beard colour",
						UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] beard."),
						true, true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_ASS = new DialogueNode("Ass", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					getSelfTransformDescription("ass and hips")
					
					+ CharacterModificationUtils.getSelfTransformAssChoiceDiv(getRacesForMinorPartSelfTransform())
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAssSizeDiv()
						+ CharacterModificationUtils.getSelfTransformHipSizeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAnusModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformAnusWetnessDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAnusCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformAnusDepthDiv()
					+"</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformAnusElasticityDiv()
						+ CharacterModificationUtils.getSelfTransformAnusPlasticityDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().getAssRace(),
							BodyChanging.getTarget().getCovering(BodyCoveringType.ANUS).getType(),
							"Anus Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] asshole."),
							true, true));
				
			if(Main.getProperties().hasValue(PropertyValue.assHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivAssHair(false, "Ass hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair around [npc.namePos] anus."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getAssHairType().getType(), "Ass hair colour",
						UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] ass hair."),
						true, true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_BREASTS = new DialogueNode("Breasts", "", true) {

		@Override
		public String getHeaderContent() {
			boolean isNipplePenOptions = isDebugMenu() || Main.game.isNipplePenEnabled();
			
			return getSelfTransformDescription("breasts")
					
					+ CharacterModificationUtils.getSelfTransformBreastChoiceDiv(getRacesForMinorPartSelfTransform())
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastSizeDiv()
						+ CharacterModificationUtils.getSelfTransformBreastShapeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastRowsDiv(isNipplePenOptions)
						+ (isNipplePenOptions
							?CharacterModificationUtils.getSelfTransformNippleModifiersDiv()
							:"")
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformLactationDiv()
						+ CharacterModificationUtils.getSelfTransformLactationRegenerationDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformLactationFlavourDiv()
						+ CharacterModificationUtils.getSelfTransformLactationModifiersDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleCountDiv()
						+ CharacterModificationUtils.getSelfTransformNippleShapeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleSizeDiv()
						+ CharacterModificationUtils.getSelfTransformAreolaeSizeDiv()
					+"</div>"

					+(isNipplePenOptions
						?"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformNippleCapacityDiv()
							+ CharacterModificationUtils.getSelfTransformNippleDepthDiv()
						+"</div>"
	
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformNippleElasticityDiv()
							+ CharacterModificationUtils.getSelfTransformNipplePlasticityDiv()
						+"</div>"
						:"")
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().getBreastRace(),
							BodyChanging.getTarget().getCovering(BodyCoveringType.NIPPLES).getType(),
							"Nipple Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] nipples."),
							true, true)

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							Race.NONE,
							BodyChanging.getTarget().getCovering(BodyCoveringType.MILK).getType(),
							"Milk Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] [npc.milk]."),
							true, true);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_VAGINA = new DialogueNode("Vagina", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					getSelfTransformDescription("vagina")
					
					+ CharacterModificationUtils.getSelfTransformVaginaChoiceDiv(getRacesForMinorPartSelfTransform())
					
					+ CharacterModificationUtils.getSelfTransformGirlcumFlavourDiv()

					+ CharacterModificationUtils.getSelfTransformGirlcumModifiersDiv()

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaHymenDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaEggLayerDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaWetnessDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaDepthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaElasticityDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaPlasticityDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformClitorisSizeDiv()
						+ CharacterModificationUtils.getSelfTransformClitorisGirthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformClitorisModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaUrethraModifiersDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaUrethraCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaUrethraDepthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaUrethraElasticityDiv()
						+ CharacterModificationUtils.getSelfTransformVaginaUrethraPlasticityDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().getVaginaRace(),
							BodyChanging.getTarget().getCovering(BodyCoveringType.VAGINA).getType(),
							"Vagina Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] vagina."),
							true, true)

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							Race.NONE,
							BodyChanging.getTarget().getCovering(BodyCoveringType.GIRL_CUM).getType(),
							"Girlcum Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] [npc.girlcum]."),
							true, true));

			if(Main.getProperties().hasValue(PropertyValue.pubicHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Pubic hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair around [npc.namePos] genitals."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getPubicHairType().getType(), "Pubic hair colour",
						UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] pubic hair."),
						true, true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_PENIS = new DialogueNode("Penis", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					getSelfTransformDescription("penis")

					+ CharacterModificationUtils.getSelfTransformPenisChoiceDiv(getRacesForMinorPartSelfTransform(), false)
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformPenisSizeDiv()
						+ CharacterModificationUtils.getSelfTransformPenisGirthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformPenisModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformCumExplusionDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformCumProductionDiv()
						+ CharacterModificationUtils.getSelfTransformCumRegenerationDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformCumFlavourDiv()
						+ CharacterModificationUtils.getSelfTransformCumModifiersDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformTesticleCountDiv()
						+ CharacterModificationUtils.getSelfTransformInternalTesticleDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformTesticleSizeDiv()
						+ CharacterModificationUtils.getSelfTransformUrethraModifiersDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformUrethraCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformUrethraDepthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformUrethraElasticityDiv()
						+ CharacterModificationUtils.getSelfTransformUrethraPlasticityDiv()
					+"</div>"

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().getPenisRace(),
							BodyChanging.getTarget().getCovering(BodyCoveringType.PENIS).getType(),
							"Penis Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] penis."),
							true, true)

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							Race.NONE,
							BodyChanging.getTarget().getCovering(BodyCoveringType.CUM).getType(),
							"Cum Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] [npc.cum]."),
							true, true));

			if(Main.getProperties().hasValue(PropertyValue.pubicHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Pubic hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair around [npc.namePos] genitals."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, Race.NONE, getTarget().getPubicHairType().getType(), "Pubic hair colour",
						UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] pubic hair."),
						true, true));
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	

	public static final DialogueNode BODY_CHANGING_BREASTS_CROTCH = new DialogueNode("Crotch-boobs", "", true) {

		@Override
		public String getHeaderContent() {
			boolean isNipplePenOptions = isDebugMenu() || Main.game.isNipplePenEnabled();
			
			return getSelfTransformDescription("[npc.crotchBoobs]")
					
					+ CharacterModificationUtils.getSelfTransformBreastCrotchChoiceDiv(getRacesForMinorPartSelfTransform())

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastCrotchSizeDiv()
						+ CharacterModificationUtils.getSelfTransformBreastCrotchShapeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastCrotchRowsDiv(isNipplePenOptions)
						+ (isNipplePenOptions
							?CharacterModificationUtils.getSelfTransformNippleCrotchModifiersDiv()
							:"")
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformLactationCrotchDiv()
						+ CharacterModificationUtils.getSelfTransformLactationCrotchRegenerationDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformLactationCrotchFlavourDiv()
						+ CharacterModificationUtils.getSelfTransformLactationCrotchModifiersDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleCrotchCountDiv()
						+ CharacterModificationUtils.getSelfTransformNippleCrotchShapeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleCrotchSizeDiv()
						+ CharacterModificationUtils.getSelfTransformAreolaeCrotchSizeDiv()
					+"</div>"

					+(isNipplePenOptions
						?"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformNippleCrotchCapacityDiv()
							+ CharacterModificationUtils.getSelfTransformNippleCrotchDepthDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformNippleCrotchElasticityDiv()
							+ CharacterModificationUtils.getSelfTransformNippleCrotchPlasticityDiv()
						+"</div>"
						:"")
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().getBreastCrotchRace(),
							BodyChanging.getTarget().getCovering(BodyCoveringType.NIPPLES_CROTCH).getType(),
							"Nipple Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] [npc.crotchNipples]."),
							true, true)

					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							Race.NONE,
							BodyChanging.getTarget().getCovering(BodyCoveringType.MILK).getType(),
							"Milk Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] [npc.milk]."),
							true, true);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};

	public static final DialogueNode BODY_CHANGING_SPINNERET = new DialogueNode("Spinneret", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					getSelfTransformDescription("spinneret")
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformSpinneretModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformSpinneretWetnessDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformSpinneretCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformSpinneretDepthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformSpinneretElasticityDiv()
						+ CharacterModificationUtils.getSelfTransformSpinneretPlasticityDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().hasLegSpinneret()
								?BodyChanging.getTarget().getLegRace()
								:BodyChanging.getTarget().getTailRace(),
							BodyChanging.getTarget().getCovering(BodyCoveringType.SPINNERET).getType(),
							"Spinneret Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] spinneret."),
							true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
	public static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("Makeup", "", true) {
		@Override
		public String getHeaderContent() {
			return getSelfTransformDescription("makeup")
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getBodyChangingResponse(responseTab, index);
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			return DialogueNodeType.PHONE;
		}
	};
	
}
