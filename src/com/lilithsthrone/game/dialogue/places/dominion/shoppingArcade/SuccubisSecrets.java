package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Skin;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.sex.managers.universal.SMChair;
import com.lilithsthrone.game.sex.positions.SexSlotOther;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.2.6
 * @author Innoxia
 */
public class SuccubisSecrets {
	
	private static StringBuilder descriptionSB;
	
	public static final int BASE_COSMETICS_COST = 200;
	public static final int BASE_PIERCINGS_COST = 25;
	public static final int BASE_HAIR_LENGTH_COST = 25;
	public static final int BASE_HAIR_STYLE_COST = 50;
	public static final int BASE_ANAL_BLEACHING_COST = 100;
	public static final int BASE_BODY_HAIR_COST = 50;
	
	public static final HashMap<BodyCoveringType, Integer> cosmeticCostsMap = Util.newHashMapOfValues(
			new Value<>(BodyCoveringType.MAKEUP_BLUSHER, 25),
			new Value<>(BodyCoveringType.MAKEUP_EYE_LINER, 25),
			new Value<>(BodyCoveringType.MAKEUP_EYE_SHADOW, 25),
			new Value<>(BodyCoveringType.MAKEUP_LIPSTICK, 25),
			new Value<>(BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, 25),
			new Value<>(BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, 25));

	public static final HashMap<PiercingType, Integer> piercingCostsMap = Util.newHashMapOfValues(
			new Value<>(PiercingType.EAR, 10),
			new Value<>(PiercingType.LIP, 25),
			new Value<>(PiercingType.NAVEL, 25),
			new Value<>(PiercingType.NIPPLE, 50),
			new Value<>(PiercingType.NOSE, 25),
			new Value<>(PiercingType.PENIS, 100),
			new Value<>(PiercingType.TONGUE, 50),
			new Value<>(PiercingType.VAGINA, 100));
	

	public static int getBodyCoveringTypeCost(BodyCoveringType type) {
		if(cosmeticCostsMap.containsKey(type)) {
			return cosmeticCostsMap.get(type);
		}
		
		return BASE_COSMETICS_COST;
	}

	public static int getPiercingCost(PiercingType type) {
		if(piercingCostsMap.containsKey(type)) {
			return piercingCostsMap.get(type);
		}
		
		return BASE_PIERCINGS_COST;
	}
	
	
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Succubi's Secrets (Exterior)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.kateIntroduced)) {
					return new Response("Enter", "Step inside Succubi's Secrets.", SHOP_BEAUTY_SALON_ENTER) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer());
						}
					};
					
				} else {
					return new Response("Enter", "Step inside Succubi's Secrets.", SHOP_BEAUTY_SALON) {
						@Override
						public void effects() {
							BodyChanging.setTarget(Main.game.getPlayer());
						}
					};
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Wake her", "Wake the sleeping demon.", SHOP_BEAUTY_SALON_WAKE);
				
			} else if (index == 2) {
				return new Response("Watch", "Wait for the sleeping demon to wake up.", SHOP_BEAUTY_SALON_WATCH);

			} else if (index == 0) {
				return new Response("Back", "Head back out to the Shopping Arcade.", EXTERIOR);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNode SHOP_BEAUTY_SALON_WAKE = new DialogueNode("Succubi's Secrets", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WAKE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Sex", "You can't resist the horny succubus's request...",
						true, true,
						new SMChair(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotOther.SITTING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kate.class), SexSlotOther.SITTING))),
						null,
						null,
						Kate.AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WAKE_START_SEX"));
				
			} else if (index == 2) {
				return new Response("No thanks", "Tell her that you're not the sort of person who just has sex with random shopkeepers.", SHOP_BEAUTY_SALON_NO_THANKS);

			} else {
				return null;
			}
		}
	};
	public static final DialogueNode SHOP_BEAUTY_SALON_WATCH = new DialogueNode("Succubi's Secrets", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WATCH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseSex("Fuck her", "Do as she says and start having sex with her.",
						true, true,
						new SMChair(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotOther.SITTING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kate.class), SexSlotOther.SITTING))),
						null,
						null,
						Kate.AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WATCH_START_SEX"));
				
			} else if (index == 2) {
				return new Response("No thanks", "Tell her that you're not the sort of person who just has sex with random shopkeepers.", SHOP_BEAUTY_SALON_NO_THANKS);

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_NO_THANKS = new DialogueNode("Succubi's Secrets", "-", true, true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_NO_THANKS");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.kateIntroduced);
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_ENTER = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_ENTER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	private static Map<BodyCoveringType, List<String>> CoveringsNamesMap;
	
	public static final DialogueNode SHOP_BEAUTY_SALON_MAIN = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MAIN");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	private static Response getMainResponse(int index) {
		if(index == 1){
			return new ResponseTrade("Trade with Kate", "There's a separate leaflet tucked into the back of the brochure. It informs you that Kate is a registered distributor for a large jewellery firm.", Main.game.getNpc(Kate.class)){
				@Override
				public void effects() {
					if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy))
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
				}
			};
			
		} else if (index == 2) {
			if(!Main.game.getPlayer().getBodyMaterial().isAbleToWearMakeup()) {
				return new Response("Makeup", "As your body is made of "+Main.game.getPlayer().getBodyMaterial().getName()+", Kate is unable to apply any makeup!", null);
				
			} else {
				return new Response("Makeup",
						"Kate offers a wide range of different cosmetic services, and several pages of the brochure are devoted to images displaying different styles and colours of lipstick, nail polish, and other forms of makeup.",
						SHOP_BEAUTY_SALON_COSMETICS){
					@Override
					public void effects() {
						if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
						}
					}
				};
			}

		} else if (index == 3) {
			return new Response("Hair",
					"There's a double-page spread of all the different dyes, styles, and lengths of hair that Kate's able to work with.",
					SHOP_BEAUTY_SALON_HAIR){
				@Override
				public void effects() {
					if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};

		} else if (index == 4) {
				return new Response("Piercings",
						"Kate offers a wide range of different piercings.",
						SHOP_BEAUTY_SALON_PIERCINGS){
					@Override
					public void effects() {
						if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
						}
					}
				};

		}  else if (index == 5) {
				return new Response("Eyes",
						"There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
						+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_EYES){
					@Override
					public void effects() {
						if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
						}
					}
				};

		} else if (index == 6) {
			return new Response("Coverings",
					"There's a special page in the middle of the brochure, advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
					+ " Apparently, this is quite demanding on her aura, and is therefore very expensive.",
					SHOP_BEAUTY_SALON_SKIN_COLOUR){
				@Override
				public void effects() {
					
					CoveringsNamesMap = new LinkedHashMap<>();
					
					if(Main.game.getPlayer().getBodyMaterial()==BodyMaterial.SLIME) {
						CoveringsNamesMap.put(BodyCoveringType.SLIME, Util.newArrayListOfValues("SLIME"));
						
					} else {
						for(BodyPartInterface bp : Main.game.getPlayer().getAllBodyParts()){
							if(bp.getBodyCoveringType(Main.game.getPlayer())!=null
									&& !(bp instanceof Hair)
									&& !(bp instanceof Eye)) {
								
								String name = bp.getName(Main.game.getPlayer());
								if(bp instanceof Skin) {
									name = "torso";
								} else if(bp instanceof Vagina) {
									name = "vagina";
								}
								
								if(CoveringsNamesMap.containsKey(bp.getBodyCoveringType(Main.game.getPlayer()))) {
									CoveringsNamesMap.get(bp.getBodyCoveringType(Main.game.getPlayer())).add(name);
								} else {
									CoveringsNamesMap.put(bp.getBodyCoveringType(Main.game.getPlayer()), Util.newArrayListOfValues(name));
								}
							}
						}
						if(Main.game.getPlayer().getTailType()==TailType.DEMON_HAIR_TIP && !CoveringsNamesMap.containsKey(BodyCoveringType.HAIR_DEMON)) {
							CoveringsNamesMap.put(BodyCoveringType.HAIR_DEMON, Util.newArrayListOfValues(BodyCoveringType.HAIR_DEMON.getName(Main.game.getPlayer())));
						}
						
						if(Main.getProperties().hasValue(PropertyValue.pubicHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getPubicHairType().getType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getPubicHairType().getType()).add("growing around your pubic region");
						}
						if(Main.getProperties().hasValue(PropertyValue.facialHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getFacialHairType().getType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getFacialHairType().getType()).add("covering your face");
						}
						if(Main.getProperties().hasValue(PropertyValue.bodyHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getBodyHairCoveringType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getBodyHairCoveringType()).add("growing in your underarms");
						}
						if(Main.getProperties().hasValue(PropertyValue.assHairContent)) {
							CoveringsNamesMap.putIfAbsent(Main.game.getPlayer().getAssHairType().getType(), new ArrayList<>());
							CoveringsNamesMap.get(Main.game.getPlayer().getAssHairType().getType()).add("growing around your anus");
						}
						
						CoveringsNamesMap.put(BodyCoveringType.ANUS, Util.newArrayListOfValues("anus"));
						CoveringsNamesMap.put(BodyCoveringType.MOUTH, Util.newArrayListOfValues("mouth"));
						CoveringsNamesMap.put(BodyCoveringType.NIPPLES, Util.newArrayListOfValues("nipples"));
						CoveringsNamesMap.put(BodyCoveringType.TONGUE, Util.newArrayListOfValues("tongue"));
						if(Main.game.getPlayer().hasBreastsCrotch()) {
							CoveringsNamesMap.put(BodyCoveringType.NIPPLES_CROTCH, Util.newArrayListOfValues("crotch nipples"));
						}
					}

					if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};

		} else if (index == 7) {
			return new Response("Other", "Kate can offer other miscellaneous services, such as anal bleaching.", SHOP_BEAUTY_SALON_OTHER);

		} else if (index == 8) {
			return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
					+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...", SHOP_BEAUTY_SALON_TATTOOS);

		} else if (index == 9) {
			return new ResponseSex("Sex",
					"You roll your eyes as you reach the end of the brochure."
							+ " On a double-page spread, there's an extremely lewd collection of pictures of Kate inserting her tail into her various orifices, with the suggestive caption 'Don't make me do it myself...'",
					true, true,
					new SMChair(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotOther.SITTING_BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(Main.game.getNpc(Kate.class), SexSlotOther.SITTING))),
					null,
					null,
					Kate.AFTER_SEX_REPEATED,
					UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MAIN_SEX")){
				@Override
				public void effects() {
					if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};
			
		} else if (index == 0) {
			return new Response("Leave", "Leave Kate's shop, heading back out into the Shopping Arcade.", EXTERIOR){
				@Override
				public void effects() {
					if(Main.game.getNpc(Kate.class).isVisiblyPregnant() && !Main.game.getDialogueFlags().values.contains(DialogueFlagValue.reactedToKatePregnancy)) {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.reactedToKatePregnancy);
					}
				}
			};
			
		} else {
			return null;
		}
	}
	
	private static String getMoneyRemainingString() {
		return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MONEY_REMAINING");
	}
	
	public static final DialogueNode SHOP_BEAUTY_SALON_HAIR = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_HAIR"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
				CharacterModificationUtils.getKatesDivHairLengths(true, "Hair Length", "Hair length determines what hair styles you're able to have. The longer the hair, the more styles are available.")

				+CharacterModificationUtils.getKatesDivHairStyles(true, "Hair Style", "Hair style availability is determined by your hair length.")
				
				+CharacterModificationUtils.getKatesDivCoveringsNew(true, Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering()).getType(),
						"[pc.Hair] Colour", "All hair recolourings are permanent, so if you want to change your colour again at a later time, you'll have to visit Kate again.", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	public static final DialogueNode SHOP_BEAUTY_SALON_SKIN_COLOUR = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_SKIN_COLOUR"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			for(Entry<BodyCoveringType, List<String>> entry : CoveringsNamesMap.entrySet()){
				BodyCoveringType bct = entry.getKey();
				
				String title = Util.capitaliseSentence(bct.getName(Main.game.getPlayer()));
				String description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently covering your "+Util.stringsToStringList(entry.getValue(), false)+".";
				
				if(bct == BodyCoveringType.SLIME) {
					title = "Slime";
					description = "Your entire body is made of slime!";
					
				} else if(bct == BodyCoveringType.ANUS) {
					title = "Anus";
					description = "This is the skin that's currently covering your anal rim. The secondary colour determines what your anus's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.VAGINA) {
					title = "Vagina";
					description = "This is the skin that's currently covering your labia. The secondary colour determines what your vagina's inner-walls look like.";
					
				} else if(bct == BodyCoveringType.PENIS) {
					title = "Penis";
					description = "This is the skin that's currently covering your penis. The secondary colour determines what the inside of your urethra looks like (if it's fuckable).";
					
				} else if(bct == BodyCoveringType.NIPPLES) {
					title = "Nipples";
					description = "This is the skin that's currently covering your nipples and areolae. The secondary colour determines what your nipples' inner-walls look like (if they are fuckable).";
					
				} else if(bct == BodyCoveringType.NIPPLES_CROTCH) {
					title = "Crotch Nipples";
					description = "This is the skin that's currently covering the nipples and areolae on your [pc.crotchBoobs]. The secondary colour determines what your nipples' inner-walls look like (if they are fuckable).";
					
				} else if(bct == BodyCoveringType.MOUTH) {
					title = "Lips & Throat";
					if(Main.game.getPlayer().getFaceType() == FaceType.HARPY) {
						description = "This is the colour of your beak. The secondary colour determines what the insides of your mouth and throat look like.";
					} else {
						description = "This is the skin that's currently covering your lips. The secondary colour determines what the insides of your mouth and throat look like.";
					}
					
				} else if(bct == BodyCoveringType.TONGUE) {
					title = "Tongue";
					description = "This is the skin that's currently covering your tongue.";
				
				} else if(Main.getProperties().hasValue(PropertyValue.pubicHairContent) && bct == Main.game.getPlayer().getPubicHairType().getType()) {
					title = "Pubic "+bct.getName(Main.game.getPlayer());
					description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently "+Util.stringsToStringList(entry.getValue(), false)+".";
					
				} else if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && bct == Main.game.getPlayer().getFacialHairType().getType()) {
					title = "Facial "+bct.getName(Main.game.getPlayer());
					description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently "+Util.stringsToStringList(entry.getValue(), false)+".";
					
				} else if(Main.getProperties().hasValue(PropertyValue.bodyHairContent) && bct == Main.game.getPlayer().getBodyHairCoveringType()) {
					title = "Body "+bct.getName(Main.game.getPlayer());
					description = "This is the "+bct.getName(Main.game.getPlayer())+" that's currently "+Util.stringsToStringList(entry.getValue(), false)+".";
				}
					
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true, 
						bct,
						title,
						description,
						true,
						true));
			}
			
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_EYES = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_EYES"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Main.game.getPlayer().getEyeCovering(), "Irises", "The iris is the coloured part of the eye that's responsible for controlling the diameter and size of the pupil.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_PUPILS, "Pupils", "The pupil is a hole located in the centre of the iris that allows light to strike the retina.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.EYE_SCLERA, "Sclerae", "The sclera is the (typically white) part of the eye that surrounds the iris.", true, true));
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
		
		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_PIERCINGS = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_PIERCINGS"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivPiercings(PiercingType.EAR, "Ear Piercing", "Ears are the most common area of the body that are pierced, and enable the equipping of earrings and other ear-related jewellery.")

					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NOSE, "Nose Piercing", "Having a nose piercing allows you to equip jewellery such as nose rings or studs.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.LIP, "Lip Piercing", "Lip piercings allow you to wear lip rings.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NAVEL, "Navel Piercing", "Getting your navel (belly button) pierced allows you to equip navel-related jewellery.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.TONGUE, "Tongue Piercing", "Getting a tongue piercing will allow you to equip tongue bars.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.NIPPLE, "Nipple Piercing", "Nipple piercings will allow you to equip nipple bars.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.PENIS, "Penis Piercing", "Having a penis piercing will allow you to equip penis-related jewellery.")
					
					+CharacterModificationUtils.getKatesDivPiercings(PiercingType.VAGINA, "Vagina Piercing", "Having a vagina piercing will allow you to equip vagina-related jewellery."));
			
			return UtilText.nodeContentSB.toString();
		
		}

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_OTHER = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "Cosmetics";
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_OTHER"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivAnalBleaching("Anal bleaching", "Anal bleaching is the process of lightening the colour of the skin around the anus, to make it more uniform with the surrounding area.")

//					+(Main.game.isFacialHairEnabled() || Main.game.isBodyHairEnabled() || Main.game.isPubicHairEnabled()
//							?CharacterModificationUtils.getKatesDivCoveringsNew(
//									true, Main.game.getPlayer().getBodyHairCoveringType(), "Body hair", "This is the hair that covers all areas other than the head.", true, true)
//							:"")
					
					+(Main.game.isFacialHairEnabled()
							? CharacterModificationUtils.getKatesDivFacialHair(true, "Facial hair", "The body hair found on your face." 
									+ (Main.game.isFemaleFacialHairEnabled() ? "" : " Feminine characters cannot grow facial hair."))
							:"")
					
					+(Main.game.isPubicHairEnabled()
							?CharacterModificationUtils.getKatesDivPubicHair(true, "Pubic hair", "The body hair found in the genital area; located on and around your sex organs and crotch.")
							:"")
					
					+(Main.game.isBodyHairEnabled()
							?CharacterModificationUtils.getKatesDivUnderarmHair(true, "Underarm hair", "The body hair found in your armpits.")
							:"")
					
					+(Main.game.isAssHairEnabled()
							?CharacterModificationUtils.getKatesDivAssHair(true, "Ass hair", "The body hair found around your asshole.")
							:"")
					);
			
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				if((Main.game.isFacialHairEnabled() && Main.game.getPlayer().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && Main.game.getPlayer().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() &&  Main.game.getPlayer().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && Main.game.getPlayer().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, bct, "Body hair", "Your body hair.", true, true));
					
				}
			}
			
			return UtilText.nodeContentSB.toString();
			
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_COSMETICS = new DialogueNode("", "", true) {
		
		@Override
		public String getLabel() {
			return "Cosmetics";
		}

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_COSMETICS"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
					CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true));
			
			return UtilText.nodeContentSB.toString();
			
		}

		@Override
		public String getContent() {
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_TATTOOS = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_TATTOOS"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivTattoos());
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 8) {
				return new Response("Tattoos", "You are already looking at the tattoos available...", null);
			} else if(index==11) {
				return new Response("Confirmations: ",
						"Toggle tattoo removal confirmations."
							+ " When turned on, it will take two clicks to remove tattoos."
							+ " When turned off, it will only take one click.",
							SHOP_BEAUTY_SALON_TATTOOS) {
					@Override
					public String getTitle() {
						return "Confirmations: "+(Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)
									?"<span style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
									:"<span style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
					}
					
					@Override
					public void effects() {
						Main.getProperties().setValue(PropertyValue.tattooRemovalConfirmations, !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations));
						Main.getProperties().savePropertiesAsXML();
					}
				};
			}
			
			return getMainResponse(index);
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
	
	public static InventorySlot invSlotTattooToRemove = null;
	
	public static final DialogueNode SHOP_BEAUTY_SALON_TATTOOS_ADD = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getLabel() {
			return "Succubi's Secrets - "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getName()) +" Tattoo";
		}
		
		@Override
		public String getContent() {
			descriptionSB = new StringBuilder();
			
			descriptionSB.append(CharacterModificationUtils.getKatesDivTattoosAdd());
			
			return descriptionSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			int value = CharacterModificationUtils.tattoo.getValue();
			if (index == 1) {
				if(Main.game.getPlayer().getMoney()<value) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "You don't have enough money to get a tattoo!", null);
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.NONE)
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "You need to select a tattoo type, add some writing, or add a counter in order to make a tattoo!", null);
					
				} else {
					return new Response("Apply ("+UtilText.formatAsMoney(value, "span")+")", "Tell Kate that you'd like her to give you this tattoo.", SHOP_BEAUTY_SALON_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value)); //TODO Kate description

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							Main.game.getPlayer().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==0) {
				return new Response("Back", "Decide not to get this tattoo and return to the main selection screen.", SHOP_BEAUTY_SALON_TATTOOS);
			}
			
			return null;
		}

		@Override
		public boolean reloadOnRestore() {
			return true;
		}
	};
}
