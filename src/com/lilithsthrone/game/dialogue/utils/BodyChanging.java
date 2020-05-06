package com.lilithsthrone.game.dialogue.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Mouth;
import com.lilithsthrone.game.character.body.Nipples;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.ScarlettsShop;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.90
 * @version 0.3.5.1
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
					getTarget().isPlayer()
						?"Change core aspects of your body."
						:UtilText.parse(getTarget(), "Change core aspects of [npc.namePos] body."),
					BODY_CHANGING_CORE);
			
		} else if(index==2) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_FACE) {
				return new Response("Head", "You are already in this screen!", null);
			}
			return new Response("Head", 
					getTarget().isPlayer()
						?"Change aspects of your face and hair."
						:UtilText.parse(getTarget(), "Change aspects of [npc.namePos] face and hair."),
					BODY_CHANGING_FACE);
			
		} else if(index==3) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_ASS) {
				return new Response("Ass", "You are already in this screen!", null);
			}
			return new Response("Ass",
					getTarget().isPlayer()
						?"Change aspects of your ass."
						:UtilText.parse(getTarget(), "Change aspects of [npc.namePos] ass."),
					BODY_CHANGING_ASS);
			
		} else if(index==4) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_BREASTS) {
				return new Response("Breasts", "You are already in this screen!", null);
			}
			return new Response("Breasts",
					getTarget().isPlayer()
						?"Change aspects of your breasts."
						:UtilText.parse(getTarget(), "Change aspects of [npc.namePos] breasts."),
					BODY_CHANGING_BREASTS);
			
		} else if(index==5) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_VAGINA) {
				return new Response("Vagina", "You are already in this screen!", null);
			}
			return new Response("Vagina", 
					getTarget().isPlayer()
						?"Change aspects of your vagina."
						:UtilText.parse(getTarget(), "Change aspects of [npc.namePos] vagina."),
						BODY_CHANGING_VAGINA);
			
		} else if(index==6) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_PENIS) {
				return new Response("Penis", "You are already in this screen!", null);
			}
			return new Response("Penis", 
					getTarget().isPlayer()
						?"Change aspects of your penis."
						:UtilText.parse(getTarget(), "Change aspects of [npc.namePos] penis."),
						BODY_CHANGING_PENIS);
			
		} else if(index==7 && (Main.getProperties().udders!=0 || debugMenu)) {
			if(Main.game.getCurrentDialogueNode()==BODY_CHANGING_BREASTS_CROTCH) {
				return new Response(BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs", "You are already in this screen!", null);
			}
			if(debugMenu) {
				if(Main.getProperties().udders==0) {
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
				if(Main.getProperties().udders==1 && BodyChanging.getTarget().getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
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
			
			if(Main.getProperties().udders==1 && BodyChanging.getTarget().getLegConfiguration().isBipedalPositionedCrotchBoobs()) {
				return new Response("Crotch-boobs", "As you have crotch-boobs disabled for non-taur characters, you cannot access this menu!", null);
			}
			
			return new Response(
					BodyChanging.getTarget().getBreastCrotchShape()==BreastShape.UDDERS?"Udders":"Crotch-boobs",
					UtilText.parse(getTarget(), "Change aspects of [npc.namePos] [npc.crotchBoobs]."),
					BODY_CHANGING_BREASTS_CROTCH);
			
		} else if(index==8 && debugMenu) {
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

	private static List<Race> allRaces = new ArrayList<>();
	static {
		for(Race r : Race.values()) {
			allRaces.add(r);
		}
	}
	
	private static List<Race> getFaceSkinDemonRaces() {
		List<Race> faceSkinOptions = Util.newArrayListOfValues();
		GameCharacter target = BodyChanging.getTarget();
		
		if(BodyChanging.getTarget().isElemental()) {
			faceSkinOptions = Util.newArrayListOfValues(Race.values());
			
		} else if(isHalfDemon()) {
			faceSkinOptions.add(target.getHalfDemonSubspecies().getRace());
			faceSkinOptions.add(Race.HUMAN);
			
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
	
	private static List<Race> getArmLegDemonRaces() {
		List<Race> armLegOptions = Util.newArrayListOfValues();
		GameCharacter target = BodyChanging.getTarget();
		
		if(BodyChanging.getTarget().isElemental()) {
			armLegOptions = Util.newArrayListOfValues(Race.values());
			
		} else if(isHalfDemon()) {
			armLegOptions.add(target.getHalfDemonSubspecies().getRace());
			
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
	private static List<Race> getMinorPartsDemonRaces(boolean isHalfSpeciesReplacement) {
		List<Race> minorPartsOptions = Util.newArrayListOfValues();
		GameCharacter target = BodyChanging.getTarget();
		
		if(BodyChanging.getTarget().isElemental()) {
			minorPartsOptions = Util.newArrayListOfValues(Race.values());
			
		} else if(isHalfDemon()) {
			if(isHalfSpeciesReplacement && target.getHalfDemonSubspecies().getRace()!=Race.HUMAN) {
				minorPartsOptions.add(target.getHalfDemonSubspecies().getRace());
			} else {
				minorPartsOptions.add(Race.DEMON);
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
				&& (BodyChanging.getTarget().getRace()==Race.DEMON
					|| BodyChanging.getTarget().getSubspecies()==Subspecies.DEMON
					|| BodyChanging.getTarget().isElemental());
	}
	
	private static boolean isHalfDemon() {
		return BodyChanging.getTarget().getSubspecies()==Subspecies.HALF_DEMON;
	}
	
	private static Map<BodyCoveringType, List<String>> getMainCoveringsMap() {
		Map<BodyCoveringType, List<String>> coveringsNamesMap = new LinkedHashMap<>();
		
		if(getTarget().isElemental()) {
			switch(getTarget().getBodyMaterial()) {
				case AIR:
					coveringsNamesMap.put(BodyCoveringType.AIR, Util.newArrayListOfValues("AIR"));
					break;
				case ARCANE:
					coveringsNamesMap.put(BodyCoveringType.ARCANE, Util.newArrayListOfValues("ARCANE"));
					break;
				case FIRE:
					coveringsNamesMap.put(BodyCoveringType.FIRE, Util.newArrayListOfValues("FIRE"));
					break;
				case FLESH:
					break;
				case ICE:
					coveringsNamesMap.put(BodyCoveringType.ICE, Util.newArrayListOfValues("ICE"));
					break;
				case RUBBER:
					coveringsNamesMap.put(BodyCoveringType.RUBBER, Util.newArrayListOfValues("RUBBER"));
					break;
				case SLIME:
					break;
				case STONE:
					coveringsNamesMap.put(BodyCoveringType.STONE, Util.newArrayListOfValues("STONE"));
					break;
				case WATER:
					coveringsNamesMap.put(BodyCoveringType.WATER, Util.newArrayListOfValues("WATER"));
					break;
			}
			
		} else if(getTarget().getBodyMaterial()==BodyMaterial.SLIME) {
			coveringsNamesMap.put(BodyCoveringType.SLIME, Util.newArrayListOfValues("SLIME"));
			
		} else {
			for(BodyPartInterface bp : getTarget().getAllBodyParts()){
				if(bp.getBodyCoveringType(getTarget())!=null
						&& !(bp instanceof Hair)
						&& !(bp instanceof Eye)
						&& !(bp instanceof Mouth)
						&& !(bp instanceof Vagina)
						&& !(bp instanceof Ass)
						&& !(bp instanceof Nipples)
						&& !(bp instanceof Breast)
						&& !(bp instanceof Penis)
						&& !(bp instanceof Antenna)
						&& !(bp instanceof Horn)) {
					String name = bp.getName(getTarget());
					if(bp instanceof Skin) {
						name = "torso";
					}
					
					if(coveringsNamesMap.containsKey(bp.getBodyCoveringType(getTarget()))) {
						coveringsNamesMap.get(bp.getBodyCoveringType(getTarget())).add(name);
					} else {
						coveringsNamesMap.put(bp.getBodyCoveringType(getTarget()), Util.newArrayListOfValues(name));
					}
				}
			}
			if(getTarget().getTailType()==TailType.DEMON_HAIR_TIP && !coveringsNamesMap.containsKey(BodyCoveringType.HAIR_DEMON)) {
				coveringsNamesMap.put(BodyCoveringType.HAIR_DEMON, Util.newArrayListOfValues(BodyCoveringType.HAIR_DEMON.getName(getTarget())));
			}
		}
		
		return coveringsNamesMap;
	}
	
	private static List<Race> getSlaveCustomisationRaceOptions() {
		List<Race> list = new ArrayList<>();
		
		for(Race race : Race.values()) {
			if(race != Race.ANGEL
					&& race != Race.DEMON
					&& race != Race.ELEMENTAL
					&& race != Race.NONE
					&& race != Race.SLIME) {
				list.add(race);
			}
		}
		
		return list;
	}
	
	public static final DialogueNode BODY_CHANGING_CORE = new DialogueNode("Core", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(ScarlettsShop.isSlaveCustomisationMenu()) {
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
							+ CharacterModificationUtils.getSelfTransformLegChoiceDiv(getSlaveCustomisationRaceOptions())
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
						+"</div>"
							
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTailCountDiv()
							+ CharacterModificationUtils.getSelfTransformTailGirthDiv()
						+"</div>"
						
							
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformWingChoiceDiv(getSlaveCustomisationRaceOptions(), false)
							+ CharacterModificationUtils.getSelfTransformWingSizeDiv()
						+"</div>");
				
			} else if(isDemonTFMenu()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
							+ (BodyChanging.getTarget().isPlayer()
									?"<i>Focus your demonic transformative powers on changing core aspects of your body.</i>"
											:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing core aspects of [npc.her] body.</i>"))
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
							+ CharacterModificationUtils.getSelfTransformFaceChoiceDiv(getFaceSkinDemonRaces())
							+ CharacterModificationUtils.getSelfTransformBodyChoiceDiv(getFaceSkinDemonRaces())
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformArmChoiceDiv(getArmLegDemonRaces())
							+ CharacterModificationUtils.getSelfTransformLegChoiceDiv(getArmLegDemonRaces())
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
							+ CharacterModificationUtils.getSelfTransformTailChoiceDiv(
									(getTarget().isElemental())
										?allRaces
										:(!removeNoneFromTailChoices()
											?Util.newArrayListOfValues(Race.DEMON)
											:getMinorPartsDemonRaces(true)),
									removeNoneFromTailChoices())
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTailCountDiv()
							+ CharacterModificationUtils.getSelfTransformTailGirthDiv()
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
						+"</div>");
				
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
						+ CharacterModificationUtils.getSelfTransformLegChoiceDiv(allRaces)
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
					+"</div>"
						
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformTailCountDiv()
						+ CharacterModificationUtils.getSelfTransformTailGirthDiv()
					+"</div>"
					
						
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformWingChoiceDiv(allRaces, false)
						+ CharacterModificationUtils.getSelfTransformWingSizeDiv()
					+"</div>");
			}
			
			for(Entry<BodyCoveringType, List<String>> entry : getMainCoveringsMap().entrySet()){
				BodyCoveringType bct = entry.getKey();
				
				String title = Util.capitaliseSentence(bct.getName(getTarget()));
				String description = UtilText.parse(getTarget(), "This is the "+bct.getName(getTarget())+" that's currently covering [npc.namePos] "+Util.stringsToStringList(entry.getValue(), false)+".");
				
				if(bct == BodyCoveringType.SLIME) {
					title = "Slime";
					description = UtilText.parse(getTarget(), "[npc.NamePos] entire body is made of slime!");
					
				} else if(Main.getProperties().hasValue(PropertyValue.bodyHairContent) && bct == getTarget().getBodyHairCoveringType()) {
					title = "Body "+bct.getName(getTarget());
					description = "This is the "+bct.getName(getTarget())+" that's currently "+Util.stringsToStringList(entry.getValue(), false)+".";
				}
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						false, 
						bct,
						title,
						description,
						true,
						true));
			}

			if(Main.getProperties().hasValue(PropertyValue.bodyHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivUnderarmHair(false, "Underarm hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair in [npc.namePos] underarms."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getUnderarmHairType().getType(), "Underarm hair colour",
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
	
	public static final DialogueNode BODY_CHANGING_FACE = new DialogueNode("Head", "", true) {

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
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getEyeCovering()).getType(), "Iris colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your irises."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(), "Pupil colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your pupils."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(), "Sclerae colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your sclerae."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae.")),
								true, true)
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformEarChoiceDiv(getSlaveCustomisationRaceOptions())
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(getSlaveCustomisationRaceOptions())
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(getSlaveCustomisationRaceOptions())
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
						+"</div>"

						+ (BodyChanging.getTarget().getHornType()!=HornType.NONE
								?CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHornCovering()).getType(), "Horn Colour",
									(BodyChanging.getTarget().isPlayer()
										?"The colour of your horns."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] horns.")),
									true, true)
								:"")
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformLipSizeDiv()
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
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
							
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.MOUTH).getType(), "Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(),
										"The natural colour of [npc.namePos] "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."
										+ "Lipstick can be used to conceal [npc.her] natural lip colour."),
								true, true)

						+ CharacterModificationUtils.getSelfDivHairStyles("Hair Style", UtilText.parse(BodyChanging.getTarget(), "Change [npc.namePos] hair style."))
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.TONGUE).getType(), "Tongue colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour of your tongue."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] tongue.")),
								true, true)
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(), "Hair colour",
								(BodyChanging.getTarget().isPlayer()
										?"Change the colour of your hair."
										:UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.her] hair.")), true, true));
				
			} else if(debugMenu) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your face and hair.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] face and hair.</i>"))
						+ "</div>"
	
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformEyeChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformEyeCountDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getEyeCovering()).getType(), "Iris colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your irises."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(), "Pupil colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your pupils."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(), "Sclerae colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your sclerae."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae.")),
								true, true)
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformEarChoiceDiv(allRaces)
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(allRaces)
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
						+"</div>"

						+ (BodyChanging.getTarget().getHornType()!=HornType.NONE
								?CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHornCovering()).getType(), "Horn Colour",
									(BodyChanging.getTarget().isPlayer()
										?"The colour of your horns."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] horns.")),
									true, true)
								:"")
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformLipSizeDiv()
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
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
							
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.MOUTH).getType(), "Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(),
										"The natural colour of [npc.namePos] "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."
										+ "Lipstick can be used to conceal [npc.her] natural lip colour."),
								true, true)

						+ CharacterModificationUtils.getSelfDivHairStyles("Hair Style", "You can change the style of your hair here.")
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.TONGUE).getType(), "Tongue colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour of your tongue."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] tongue.")),
								true, true)
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(), "Hair colour",
								(BodyChanging.getTarget().isPlayer()
										?"Change the colour of your hair."
										:UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.her] hair.")), true, true));

			} else if(isDemonTFMenu()) {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your demonic transformative powers on changing aspects of your face and hair.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing aspects of [npc.her] face and hair.</i>"))
						+ "</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformEyeChoiceDiv(
									(getTarget().isElemental())
										?allRaces
										:getMinorPartsDemonRaces(false))
							+ CharacterModificationUtils.getSelfTransformEyeCountDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
						+"</div>"
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getEyeCovering()).getType(), "Iris colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your irises."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_PUPILS).getType(), "Pupil colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your pupils."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.EYE_SCLERA).getType(), "Sclerae colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your sclerae."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae.")),
								true, true)
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(
									(getTarget().isElemental())
										?allRaces
										:getMinorPartsDemonRaces(true))
							+ CharacterModificationUtils.getSelfTransformEarChoiceDiv(
									(getTarget().isElemental())
										?allRaces
										:getMinorPartsDemonRaces(true))
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(
									(getTarget().isElemental())
										?allRaces
										:getMinorPartsDemonRaces(true))
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(
									(getTarget().isElemental())
										?allRaces
										:Util.mergeLists(getMinorPartsDemonRaces(true), Util.newArrayListOfValues(Race.DEMON)))
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
						+"</div>"

						+ (BodyChanging.getTarget().getHornType()!=HornType.NONE
								?CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHornCovering()).getType(), "Horn Colour",
									(BodyChanging.getTarget().isPlayer()
										?"The colour of your horns."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] horns.")),
									true, true)
								:"")
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformLipSizeDiv()
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
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
							
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.MOUTH).getType(), "Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(), "The natural colour of [npc.namePos] slimy "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."),
								true, true)
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyCoveringType.TONGUE).getType(), "Tongue colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour of your tongue."
										:UtilText.parse(BodyChanging.getTarget(), "The colour of [npc.namePos] tongue.")),
								true, true)
	
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyChanging.getTarget().getCovering(BodyChanging.getTarget().getHairCovering()).getType(), "Hair colour",
								(BodyChanging.getTarget().isPlayer()
										?"You can harness the power of your demonic form to change the colour of your hair."
										:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the colour of [npc.her] hair.")), true, true));
				
			// Slime:
			} else {
				UtilText.nodeContentSB.append("<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your efforts on changing aspects of your slimy face and hair.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] efforts on changing aspects of [npc.her] slimy face and hair.</i>"))
						+ "</div>"
	
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformEyeChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformEyeCountDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformIrisChoiceDiv()
							+ CharacterModificationUtils.getSelfTransformPupilChoiceDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformEarChoiceDiv(allRaces)
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHairLengthDiv()
							+ CharacterModificationUtils.getSelfTransformAntennaChoiceDiv(allRaces)
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornChoiceDiv(allRaces)
							+ CharacterModificationUtils.getSelfTransformHornSizeDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformHornCountDiv()
							+ CharacterModificationUtils.getSelfTransformHornsPerRowCountDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformLipSizeDiv()
							+ CharacterModificationUtils.getSelfTransformThroatModifiersDiv()
						+"</div>"
						
						+"<div style='clear:left;'>"
							+ CharacterModificationUtils.getSelfTransformTongueSizeDiv()
							+ CharacterModificationUtils.getSelfTransformTongueModifiersDiv()
						+"</div>"
						
							
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyCoveringType.SLIME_MOUTH, "Lip & Throat colour",
								UtilText.parse(BodyChanging.getTarget(), "The natural colour of [npc.namePos] slimy "+(getTarget().getFaceType() == FaceType.HARPY?"beak":"lips")+" (top options) and [npc.her] throat (bottom options)."),
								true, true)
						
						+ CharacterModificationUtils.getSelfDivHairStyles("Hair Style", "You can change the style of your slimy hair at will!")
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyCoveringType.SLIME_HAIR, "Hair colour",
								(BodyChanging.getTarget().isPlayer()
										?"You can freely change the colour of your slimy hair."
										:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can freely change the colour of [npc.her] slimy hair.")), true, true)
						
						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyCoveringType.SLIME_EYE, "Iris colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your irises."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] irises.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyCoveringType.SLIME_PUPILS, "Pupil colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your pupils."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] pupils.")),
								true, true)

						+ CharacterModificationUtils.getKatesDivCoveringsNew(false, BodyCoveringType.SLIME_SCLERA, "Sclerae colour",
								(BodyChanging.getTarget().isPlayer()
										?"The colour and pattern of your sclerae."
										:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.namePos] sclerae.")),
								true, true));
						
	//					+ CharacterModificationUtils.getSelfTransformTongueLengthChoiceDiv() TODO
	//					+ CharacterModificationUtils.getSelfTransformTongueModifiersChoiceDiv()
						
	//					+ CharacterModificationUtils.getSelfTransformMouthModifiersChoiceDiv()
			}
			
			if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && (!getTarget().isFeminine() || Main.getProperties().hasValue(PropertyValue.feminineBeardsContent))) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivFacialHair(false, "Beard length",
						UtilText.parse(BodyChanging.getTarget(), "Change the length of [npc.namePos] beard."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getFacialHairType().getType(), "Beard colour",
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
	
	private static List<Race> getRacesForMinorPartSelfTransform() {
		if(ScarlettsShop.isSlaveCustomisationMenu()) {
			return getSlaveCustomisationRaceOptions();
		}
		if(isDemonTFMenu()) {
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
				
			} else if(debugMenu) {
				sb.append(UtilText.parse(BodyChanging.getTarget(), "<i>[npc.Name] can harness the power of the debugging tool to self-transform aspects of [npc.her] "+area+".</i>"));
				
			} else {
				sb.append(UtilText.parse(BodyChanging.getTarget(), "<i>[npc.Name] can take advantage of [npc.her] morphable, slimy body to self-transform aspects of [npc.her] "+area+".</i>"));
			}
		sb.append("</div>");
		
		return sb.toString();
	}
	
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
							BodyChanging.getTarget().getCovering(BodyCoveringType.ANUS).getType(),
							"Anus Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] asshole."),
							true, true));
				
			if(Main.getProperties().hasValue(PropertyValue.assHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivAssHair(false, "Ass hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair around [npc.namePos] anus."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getAssHairType().getType(), "Ass hair colour",
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
			return getSelfTransformDescription("breasts")
					
					+ CharacterModificationUtils.getSelfTransformBreastChoiceDiv(getRacesForMinorPartSelfTransform())
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastSizeDiv()
						+ CharacterModificationUtils.getSelfTransformBreastShapeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastRowsDiv()
						+ CharacterModificationUtils.getSelfTransformNippleModifiersDiv()
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

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformNippleDepthDiv()
					+"</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleElasticityDiv()
						+ CharacterModificationUtils.getSelfTransformNipplePlasticityDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().getCovering(BodyCoveringType.NIPPLES).getType(),
							"Nipple Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] nipples."),
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
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformGirlcumFlavourDiv()
						+ CharacterModificationUtils.getSelfTransformGirlcumModifiersDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaModifiersDiv()
						+ CharacterModificationUtils.getSelfTransformLabiaSizeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformVaginaSquirterDiv()
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
							BodyChanging.getTarget().getCovering(BodyCoveringType.VAGINA).getType(),
							"Vagina Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] vagina."),
							true, true));
			

			if(Main.getProperties().hasValue(PropertyValue.pubicHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Pubic hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair around [npc.namePos] genitals."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getPubicHairType().getType(), "Pubic hair colour",
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
							BodyChanging.getTarget().getCovering(BodyCoveringType.PENIS).getType(),
							"Penis Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] penis."),
							true, true));
			

			if(Main.getProperties().hasValue(PropertyValue.pubicHairContent)) {
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPubicHair(false, "Pubic hair",
						UtilText.parse(BodyChanging.getTarget(), "Change the amount of hair around [npc.namePos] genitals."))
				
				+ CharacterModificationUtils.getKatesDivCoveringsNew(false, getTarget().getPubicHairType().getType(), "Pubic hair colour",
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
			return getSelfTransformDescription("[npc.crotchBoobs]")
					
					+ CharacterModificationUtils.getSelfTransformBreastCrotchChoiceDiv(getRacesForMinorPartSelfTransform())

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastCrotchSizeDiv()
						+ CharacterModificationUtils.getSelfTransformBreastCrotchShapeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformBreastCrotchRowsDiv()
						+ CharacterModificationUtils.getSelfTransformNippleCrotchModifiersDiv()
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

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleCrotchCapacityDiv()
						+ CharacterModificationUtils.getSelfTransformNippleCrotchDepthDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getSelfTransformNippleCrotchElasticityDiv()
						+ CharacterModificationUtils.getSelfTransformNippleCrotchPlasticityDiv()
					+"</div>"
					
					
					+ CharacterModificationUtils.getKatesDivCoveringsNew(false,
							BodyChanging.getTarget().getCovering(BodyCoveringType.NIPPLES_CROTCH).getType(),
							"Nipple Colour", 
							UtilText.parse(BodyChanging.getTarget(), "Change the colour of [npc.namePos] [npc.crotchNipples]."),
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
	
	public static final DialogueNode BODY_CHANGING_MAKEUP = new DialogueNode("Makeup", "", true) {
		
		@Override
		public String getHeaderContent() {
			return getSelfTransformDescription("makeup")
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
					
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)

					+CharacterModificationUtils.getKatesDivCoveringsNew(
							false, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true);
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
