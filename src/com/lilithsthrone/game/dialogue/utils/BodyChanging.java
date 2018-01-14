package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.MapDisplay;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.90
 * @version 0.1.90
 * @author Innoxia
 */
public class BodyChanging {
	
	private static GameCharacter target;
	
	public static GameCharacter getTarget() {
		if(target==null) {
			return Main.game.getPlayer();
		}
		return target;
	}

	public static void setTarget(GameCharacter target) {
		BodyChanging.target = target;
	}
	
	private static Response getBodyChangingResponse(int responseTab, int index) {
		if(index==1) {
			return new Response("Core",
					getTarget().isPlayer()
						?"Change core aspects of your body."
						:UtilText.parse(getTarget(), "Change core aspects of [npc.name]'s body."),
					BODY_CHANGING_CORE);
			
		} else if(index==2) {
			return new Response("Head", 
					getTarget().isPlayer()
						?"Change aspects of your face and hair."
						:UtilText.parse(getTarget(), "Change aspects of [npc.name]'s face and hair."),
					BODY_CHANGING_FACE);
			
		} else if(index==3) {
			return new Response("Ass",
					getTarget().isPlayer()
						?"Change aspects of your ass."
						:UtilText.parse(getTarget(), "Change aspects of [npc.name]'s ass."),
					BODY_CHANGING_ASS);
			
		} else if(index==4) {
			return new Response("Breasts",
					getTarget().isPlayer()
						?"Change aspects of your breasts."
						:UtilText.parse(getTarget(), "Change aspects of [npc.name]'s breasts."),
					BODY_CHANGING_BREASTS);
			
		} else if(index==5) {
			return new Response("Vagina", 
					getTarget().isPlayer()
						?"Change aspects of your vagina."
						:UtilText.parse(getTarget(), "Change aspects of [npc.name]'s vagina."),
						BODY_CHANGING_VAGINA);
			
		} else if(index==6) {
			return new Response("Penis", 
					getTarget().isPlayer()
						?"Change aspects of your penis."
						:UtilText.parse(getTarget(), "Change aspects of [npc.name]'s penis."),
						BODY_CHANGING_PENIS);
			
		} else if(index==0) {
			return new ResponseEffectsOnly("Back", "Return to the previous screen."){
				@Override
				public void effects() {
					Main.game.restoreSavedContent();
				}
			};
			
		} else {
			return null;
		}
	}

	public static final DialogueNodeOld BODY_CHANGING_CORE = new DialogueNodeOld("Core", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"<i>Focus your demonic transformative powers on changing core aspects of your body.</i>"
								:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing core aspects of [npc.her] body.</i>"))
					+ "</div>"
					
					+ CharacterModificationUtils.getFullFemininityChoiceDiv()

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonTailChoiceDiv()
						+ CharacterModificationUtils.getDemonWingChoiceDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonArmChoiceDiv()
						+ CharacterModificationUtils.getDemonLegChoiceDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getHeightChoiceDiv()
					
					
					+ "<div class='cosmetics-container' style='background:transparent;'>"
					
						+ CharacterModificationUtils.getBodySizeChoiceDiv()
						+ CharacterModificationUtils.getMuscleChoiceDiv()
						
						+ "<div class='container-full-width' style='text-align:center;'>"
						+ (BodyChanging.getTarget().isPlayer()
								?"Your muscle and body size values result in your appearance being:</br>"
									+ "<b style='color:"+Main.game.getPlayer().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(Main.game.getPlayer().getBodyShape().getName(false))+"</b>"
								:UtilText.parse(BodyChanging.getTarget(), "[npc.Name]'s muscle and body size values result in [npc.her] appearance being:</br>"
										+ "<b style='color:"+getTarget().getBodyShape().toWebHexStringColour()+";'>"+Util.capitaliseSentence(getTarget().getBodyShape().getName(false))+"</b>"))
						+ "</div>"
					
					+"</div>"
					

					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.DEMON_COMMON, "Skin Colour",
							(BodyChanging.getTarget().isPlayer()
								?"The colour of the demonic skin that's covering your body."
								:UtilText.parse(BodyChanging.getTarget(), "The colour of the demonic skin that's covering [npc.name]'s body.")),
							true, true);
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Core", "You are already in this screen!", null);
				
			} else {
				return getBodyChangingResponse(responseTab, index);
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld BODY_CHANGING_FACE = new DialogueNodeOld("Head", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
					+ (BodyChanging.getTarget().isPlayer()
							?"<i>Focus your demonic transformative powers on changing aspects of your face and hair.</i>"
							:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing aspects of [npc.her] face and hair.</i>"))
					+ "</div>"

//					+ CharacterModificationUtils.getDemonEarChoiceDiv()
					
//					+ CharacterModificationUtils.getDemonEyeChoiceDiv()
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonIrisChoiceDiv()
						+ CharacterModificationUtils.getDemonPupilChoiceDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.EYE_DEMON_COMMON, "Eye colour",
							(BodyChanging.getTarget().isPlayer()
									?"The colour and pattern of your eyes."
									:UtilText.parse(BodyChanging.getTarget(), "The colour and pattern of [npc.name]'s eyes.")),
							true, true)

//					+ CharacterModificationUtils.getDemonFaceChoiceDiv()
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonHornChoiceDiv()
						+ CharacterModificationUtils.getDemonicLipSizeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonThroatModifiersDiv()
						+ CharacterModificationUtils.getDemonTongueModifiersDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.MOUTH, "Lip & Throat colour",
							(BodyChanging.getTarget().isPlayer()
									?"The natural colour of your lips (top options) and your throat (bottom options). Lipstick can be used to conceal your natural lip colour."
									:UtilText.parse(BodyChanging.getTarget(), "The natural colour of [npc.name]'s lips (top options) and [npc.her] throat (bottom options). Lipstick can be used to conceal [npc.her] natural lip colour.")),
							true, true)

					+ CharacterModificationUtils.getKatesDivHairLengths(false, "Hair Length",
							(BodyChanging.getTarget().isPlayer()
									?"You can harness the power of your demonic form to change the length of your hair."
									:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the length of [npc.her] hair.")))
					
					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyChanging.getTarget().getHairCovering().getType(), "Hair colour",
							(BodyChanging.getTarget().isPlayer()
									?"You can harness the power of your demonic form to change the colour of your hair."
									:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the colour of [npc.her] hair.")), true, true)
					
//					+ CharacterModificationUtils.getDemonTongueLengthChoiceDiv() TODO
//					+ CharacterModificationUtils.getDemonTongueModifiersChoiceDiv()
					
//					+ CharacterModificationUtils.getDemonMouthModifiersChoiceDiv()
					;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==2) {
				return new Response("Head", "You are already in this screen!", null);
				
			} else {
				return getBodyChangingResponse(responseTab, index);
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld BODY_CHANGING_ASS = new DialogueNodeOld("Ass", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
					+ (BodyChanging.getTarget().isPlayer()
							?"<i>Focus your demonic transformative powers on changing aspects of your ass and hips.</i>"
							:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing aspects of [npc.her] ass and hips.</i>"))
					+ "</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonAssSizeDiv()
						+ CharacterModificationUtils.getDemonHipSizeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonAnusCapacityDiv()
						+ CharacterModificationUtils.getDemonAnusWetnessDiv()
					+"</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonAnusElasticityDiv()
						+ CharacterModificationUtils.getDemonAnusPlasticityDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getDemonAnusModifiersDiv()
						
					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.ANUS, "Anus Colour", 
							(BodyChanging.getTarget().isPlayer()
									?"You can harness the power of your demonic form to change the colour of your asshole."
									:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the colour of [npc.her] asshole.")), true, true)
					
					;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==3) {
				return new Response("Ass", "You are already in this screen!", null);
				
			} else {
				return getBodyChangingResponse(responseTab, index);
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld BODY_CHANGING_BREASTS = new DialogueNodeOld("Breasts", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
					+ (BodyChanging.getTarget().isPlayer()
							?"<i>Focus your demonic transformative powers on changing aspects of your breasts.</i>"
							:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing aspects of [npc.her] breasts.</i>"))
					+ "</div>"
						
//					Breasts:
//						 * 	TODO milk-related changes
					
					+ CharacterModificationUtils.getDemonBreastSizeDiv()
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonBreastShapeDiv()
						+ CharacterModificationUtils.getDemonLactationDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonBreastRowsDiv()
						+ CharacterModificationUtils.getDemonNippleCountDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonNippleSizeDiv()
						+ CharacterModificationUtils.getDemonAreolaeSizeDiv()
					+"</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonNippleModifiersDiv()
						+ CharacterModificationUtils.getDemonNippleCapacityDiv()
					+"</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonNippleElasticityDiv()
						+ CharacterModificationUtils.getDemonNipplePlasticityDiv()
					+"</div>"
					
					
					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.NIPPLES, "Nipple Colour", 
							(BodyChanging.getTarget().isPlayer()
									?"You can harness the power of your demonic form to change the colour of your nipples."
									:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the colour of [npc.her] nipples.")),
							true, true)
					;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==4) {
				return new Response("Breasts", "You are already in this screen!", null);
				
			} else {
				return getBodyChangingResponse(responseTab, index);
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld BODY_CHANGING_VAGINA = new DialogueNodeOld("Vagina", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
					+ (BodyChanging.getTarget().isPlayer()
							?"<i>Focus your demonic transformative powers on changing aspects of your vagina.</i>"
							:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing aspects of [npc.her] vagina.</i>"))
					+ "</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonVaginaChoiceDiv()
						+ CharacterModificationUtils.getDemonVaginaModifiersDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonVaginaCapacityDiv()
						+ CharacterModificationUtils.getDemonVaginaWetnessDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonVaginaElasticityDiv()
						+ CharacterModificationUtils.getDemonVaginaPlasticityDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonLabiaSizeDiv()
						+ CharacterModificationUtils.getDemonClitorisSizeDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.VAGINA, "Vagina Colour", 
							(BodyChanging.getTarget().isPlayer()
									?"You can harness the power of your demonic form to change the colour of your vagina."
									:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the colour of [npc.her] vagina.")), true, true)
					
					;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==5) {
				return new Response("Vagina", "You are already in this screen!", null);
				
			} else {
				return getBodyChangingResponse(responseTab, index);
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
	public static final DialogueNodeOld BODY_CHANGING_PENIS = new DialogueNodeOld("Penis", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getHeaderContent() {
			return "<div class='container-full-width' style='text-align:center;'>"
					+ (BodyChanging.getTarget().isPlayer()
							?"<i>Focus your demonic transformative powers on changing aspects of your penis.</i>"
							:UtilText.parse(BodyChanging.getTarget(), "<i>Get [npc.name] to focus [npc.her] demonic transformative powers on changing aspects of [npc.her] penis.</i>"))
					+ "</div>"

					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonPenisChoiceDiv()
						+ CharacterModificationUtils.getDemonPenisSizeDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonTesticleSizeDiv()
						+ CharacterModificationUtils.getDemonTesticleCountDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonUrethraCapacityDiv()
						+ CharacterModificationUtils.getDemonCumProductionDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonUrethraElasticityDiv()
						+ CharacterModificationUtils.getDemonUrethraPlasticityDiv()
					+"</div>"
					
					+"<div style='clear:left;'>"
						+ CharacterModificationUtils.getDemonPenisModifiersDiv()
						+ CharacterModificationUtils.getDemonUrethraModifiersDiv()
					+"</div>"
					
					+ CharacterModificationUtils.getKatesDivCoverings(false, BodyCoveringType.PENIS, "Penis Colour", 
							(BodyChanging.getTarget().isPlayer()
									?"You can harness the power of your demonic form to change the colour of your penis."
									:UtilText.parse(BodyChanging.getTarget(), "[npc.Name] can harness the power of [npc.her] demonic form to change the colour of [npc.her] penis.")), true, true)
					
					;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==6) {
				return new Response("Penis", "You are already in this screen!", null);
				
			} else {
				return getBodyChangingResponse(responseTab, index);
			}
		}

		@Override
		public MapDisplay getMapDisplay() {
			return MapDisplay.PHONE;
		}
	};
	
}
