package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Antenna;
import com.lilithsthrone.game.character.body.Arm;
import com.lilithsthrone.game.character.body.Ass;
import com.lilithsthrone.game.character.body.BodyPartInterface;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.BreastCrotch;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.Eye;
import com.lilithsthrone.game.character.body.Face;
import com.lilithsthrone.game.character.body.Hair;
import com.lilithsthrone.game.character.body.Horn;
import com.lilithsthrone.game.character.body.Penis;
import com.lilithsthrone.game.character.body.Tail;
import com.lilithsthrone.game.character.body.Tentacle;
import com.lilithsthrone.game.character.body.Torso;
import com.lilithsthrone.game.character.body.Vagina;
import com.lilithsthrone.game.character.body.Wing;
import com.lilithsthrone.game.character.body.coverings.AbstractBodyCoveringType;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringCategory;
import com.lilithsthrone.game.character.body.coverings.BodyCoveringType;
import com.lilithsthrone.game.character.body.tags.BodyPartTag;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.npc.dominion.Kate;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CosmeticsDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.ImmobilisationType;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.managers.universal.SMSitting;
import com.lilithsthrone.game.sex.positions.slots.SexSlotSitting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.66
 * @version 0.4
 * @author Innoxia
 */
public class SuccubisSecrets {

	public static InventorySlot invSlotTattooToRemove = null;
	
	public static Map<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> coveringsNamesMap;
	
	private static StringBuilder descriptionSB;
	
	public static final int BASE_COSMETICS_COST = 200;
	public static final int BASE_PIERCINGS_COST = 25;
	public static final int BASE_HAIR_LENGTH_COST = 25;
	public static final int BASE_HAIR_STYLE_COST = 50;
	public static final int BASE_ANAL_BLEACHING_COST = 100;
	public static final int BASE_BODY_HAIR_COST = 50;
	
	public static final HashMap<AbstractBodyCoveringType, Integer> cosmeticCostsMap = Util.newHashMapOfValues(
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
	
	public static void initCoveringsMap(GameCharacter target) {
		coveringsNamesMap = new LinkedHashMap<>();
		
		for(BodyPartInterface bp : target.getAllBodyParts()){
			if(bp.getBodyCoveringType(target)!=null
					&& !(bp instanceof Hair)
					&& !(bp instanceof Eye)) {
				
				String name = bp.getName(target);
				if(bp instanceof Torso) {
					name = "torso";
				} else if(bp instanceof Vagina) {
					name = "vagina";
				}
				
				boolean addBpi = true;
				// Check for parts not owned:
				if((bp instanceof Antenna && !target.hasAntennae())
						|| (bp instanceof Arm && !target.hasArms())
						|| (bp instanceof Breast && !target.hasNipples())
						|| (bp instanceof BreastCrotch && !target.hasBreastsCrotch())
						|| (bp instanceof Hair && !target.hasHair())
						|| (bp instanceof Horn && !target.hasHorns())
						|| (bp instanceof Penis && !target.hasPenisIgnoreDildo())
						|| (bp instanceof Tail && !target.hasTail())
						|| (bp instanceof Tentacle && !target.hasTentacle())
						|| (bp instanceof Vagina && !target.hasVagina())
						|| (bp instanceof Wing && !target.hasWings())) {
					addBpi = false;
				}
				AbstractRace race = bp.getType().getRace();
				if(addBpi) {
					AbstractBodyCoveringType coveringType = bp.getBodyCoveringType(target);
					if(bp instanceof Ass) {
						coveringType = BodyCoveringType.ANUS;
					} else if(bp instanceof Breast) {
						coveringType = BodyCoveringType.NIPPLES;
					} else if(bp instanceof BreastCrotch) {
						coveringType = BodyCoveringType.NIPPLES_CROTCH;
					}
					if(coveringsNamesMap.containsKey(coveringType)) {
						coveringsNamesMap.get(coveringType).getValue().add(name);
					} else {
						coveringsNamesMap.put(coveringType, new Value<>(race, Util.newArrayListOfValues(name)));
					}
					
					if(bp instanceof Face) {
						coveringType = BodyCoveringType.MOUTH;
						if(coveringsNamesMap.containsKey(coveringType)) {
							coveringsNamesMap.get(coveringType).getValue().add(name);
						} else {
							coveringsNamesMap.put(coveringType, new Value<>(race, Util.newArrayListOfValues(name)));
						}
						coveringType = BodyCoveringType.TONGUE;
						if(coveringsNamesMap.containsKey(coveringType)) {
							coveringsNamesMap.get(coveringType).getValue().add(name);
						} else {
							coveringsNamesMap.put(coveringType, new Value<>(race, Util.newArrayListOfValues(name)));
						}
					}
				}
			}
		}
		
		if(target.getTailType()==TailType.DEMON_HAIR_TIP && !coveringsNamesMap.containsKey(BodyCoveringType.HAIR_DEMON)) {
			coveringsNamesMap.put(BodyCoveringType.HAIR_DEMON, new Value<>(Race.DEMON, Util.newArrayListOfValues(BodyCoveringType.HAIR_DEMON.getName(target))));
		}
		
		if(target.hasNipples()) {
			coveringsNamesMap.putIfAbsent(BodyCoveringType.MILK, new Value<>(Race.NONE, Util.newArrayListOfValues("milk")));
		}
		if(target.hasPenisIgnoreDildo()) {
			coveringsNamesMap.putIfAbsent(BodyCoveringType.CUM, new Value<>(Race.NONE, Util.newArrayListOfValues("cum")));
		}
		if(target.hasVagina()) {
			coveringsNamesMap.putIfAbsent(BodyCoveringType.GIRL_CUM, new Value<>(Race.NONE, Util.newArrayListOfValues("girlcum")));
		}
		
		
		if(Main.getProperties().hasValue(PropertyValue.pubicHairContent) && target.getPubicHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getPubicHairType().getType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getPubicHairType().getType()).getValue().add(UtilText.parse(target, "growing around [npc.namePos] pubic region"));
		}
		if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && target.getFacialHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getFacialHairType().getType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getFacialHairType().getType()).getValue().add(UtilText.parse(target, "covering [npc.namePos] face"));
		}
		if(Main.getProperties().hasValue(PropertyValue.bodyHairContent) && target.getUnderarmHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getBodyHairCoveringType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getBodyHairCoveringType()).getValue().add(UtilText.parse(target, "growing in [npc.namePos] underarms"));
		}
		if(Main.getProperties().hasValue(PropertyValue.assHairContent) && target.getAssHair()!=BodyHair.ZERO_NONE) {
			coveringsNamesMap.putIfAbsent(target.getAssHairType().getType(), new Value<>(Race.NONE, new ArrayList<>()));
			coveringsNamesMap.get(target.getAssHairType().getType()).getValue().add(UtilText.parse(target, "growing around [npc.namePos] anus"));
		}
		
		// Alter the map for if the target's body is not made of flesh:
		if(BodyChanging.getTarget().getBodyMaterial()!=BodyMaterial.FLESH) {
			Map<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> altMaterialCoveringsNamesMap = new LinkedHashMap<>();
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : coveringsNamesMap.entrySet()) {
				if(entry.getKey().getCategory().isInfluencedByMaterialType()) {
					altMaterialCoveringsNamesMap.put(BodyCoveringType.getMaterialBodyCoveringType(BodyChanging.getTarget().getBodyMaterial(), entry.getKey().getCategory()), entry.getValue());
				} else {
					altMaterialCoveringsNamesMap.put(entry.getKey(), entry.getValue());
				}
			}
			coveringsNamesMap = altMaterialCoveringsNamesMap;
		}

		for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : coveringsNamesMap.entrySet()) {
			if(entry.getKey().getCategory()==BodyCoveringCategory.ANUS) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("anus");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.MOUTH) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("mouth");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.NIPPLE) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("nipples");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.NIPPLE_CROTCH) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("crotch nipples");
			} else if(entry.getKey().getCategory()==BodyCoveringCategory.TONGUE) {
				entry.getValue().getValue().clear();
				entry.getValue().getValue().add("tongue");
			}
		}
	}
	
	public static Value<String, String> getCoveringTitleDescription(GameCharacter target, AbstractBodyCoveringType coveringType, List<String> areasList) {
		String title = Util.capitaliseSentence(coveringType.getNameTransformation(target));
		
		String description = "This is the "+coveringType.getName(target)+" that's currently covering [npc.namePos] "+Util.stringsToStringList(areasList, false)+".";
		
		if(coveringType.getCategory()==BodyCoveringCategory.FLUID) {
			description = "As its name would suggest, this is simply [npc.namePos] "+Util.stringsToStringList(areasList, false)+".";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.ANUS) {
			title = "Anus";
			description = "This is the skin that's currently covering [npc.namePos] anal rim. The secondary colour determines what [npc.her] anus's inner-walls look like.";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.VAGINA) {
			title = "Vagina";
			description = "This is the skin that's currently covering [npc.namePos] labia. The secondary colour determines what [npc.her] vagina's inner-walls look like.";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.PENIS) {
			title = "Penis";
			description = "This is the skin that's currently covering [npc.namePos] penis. The secondary colour determines what the inside of [npc.her] urethra looks like (if it's fuckable).";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.NIPPLE) {
			title = "Nipples";
			description = "This is the skin that's currently covering [npc.namePos] nipples and areolae. The secondary colour determines what [npc.her] nipples' inner-walls look like (if they are fuckable).";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.NIPPLE_CROTCH) {
			title = "Crotch Nipples";
			description = "This is the skin that's currently covering the nipples and areolae on [npc.namePos] [npc.crotchBoobs]. The secondary colour determines what [npc.her] nipples' inner-walls look like (if they are fuckable).";
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.MOUTH) {
			title = "Lips & Throat";
			if(target.getFaceType().getTags().contains(BodyPartTag.FACE_BEAK)) {
				description = "This is the colour of [npc.namePos] beak. The secondary colour determines what the insides of [npc.her] mouth and throat look like.";
			} else {
				description = "This is the skin that's currently covering [npc.namePos] lips. The secondary colour determines what the insides of [npc.her] mouth and throat look like.";
			}
			
		} else if(coveringType.getCategory()==BodyCoveringCategory.TONGUE) {
			title = "Tongue";
			description = "This is the skin that's currently covering [npc.namePos] tongue.";
		
		} else if(Main.getProperties().hasValue(PropertyValue.pubicHairContent) && coveringType == target.getPubicHairType().getType()) {
			title = "Pubic "+coveringType.getName(target);
			description = "This is the "+coveringType.getName(target)+" that's currently "+Util.stringsToStringList(areasList, false)+".";
			
		} else if(Main.getProperties().hasValue(PropertyValue.facialHairContent) && coveringType == target.getFacialHairType().getType()) {
			title = "Facial "+coveringType.getName(target);
			description = "This is the "+coveringType.getName(target)+" that's currently "+Util.stringsToStringList(areasList, false)+".";
			
		} else if(Main.getProperties().hasValue(PropertyValue.bodyHairContent) && coveringType == target.getBodyHairCoveringType()) {
			title = "Body "+coveringType.getName(target);
			description = "This is the "+coveringType.getName(target)+" that's currently "+Util.stringsToStringList(areasList, false)+".";
		}
		
		return new Value<>(title, description);
	}
	
	public static int getBodyCoveringTypeCost(AbstractBodyCoveringType type) {
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
	
	private static Kate getKate() {
		return (Kate) Main.game.getNpc(Kate.class);
	}
	
	public static final DialogueNode EXTERIOR = new DialogueNode("Succubi's Secrets (Exterior)", "-", false) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "EXTERIOR");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return ShoppingArcadeDialogue.getCoreResponseTab(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==0) {
				if (index == 1) {
					if(!Main.game.isExtendedWorkTime()) {
						return new Response("Enter", "'Succubi's Secrets' is currently closed, so you'll have to come back during opening hours if you wanted to take a look inside.", null);
						
					} else if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.kateIntroduced)) {
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
				}
			}
			return ShoppingArcadeDialogue.getFastTravelResponses(responseTab, index);
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
				return new Response("Leave", "Head back out to the Shopping Arcade.", EXTERIOR) {
					@Override
					public void effects() {
						Main.game.setResponseTab(0);
					}
				};
			}
			return null;
		}
	};
	public static final DialogueNode SHOP_BEAUTY_SALON_WAKE = new DialogueNode("Succubi's Secrets", "-", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getKate().wakeUp();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WAKE");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("No thanks", "Tell her that you're not the sort of person who just has sex with random shopkeepers.", SHOP_BEAUTY_SALON_NO_THANKS);
				
			} else if (index == 2) {
				return new ResponseSex("Sex", "You can't resist the horny succubus's request...",
						true, true,
						new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WAKE_START_SEX"));
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_WATCH = new DialogueNode("Succubi's Secrets", "-", true, true) {
		@Override
		public void applyPreParsingEffects() {
			getKate().wakeUp();
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WATCH");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("No thanks", "Tell her that you're not the sort of person who just has sex with random shopkeepers.", SHOP_BEAUTY_SALON_NO_THANKS);
				
			} else if (index == 2) {
				return new ResponseSex("Fuck her", "Do as she says and start having sex with her.",
						true, true,
						new SMSitting(
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
								Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
							@Override
							public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
								return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
							}
						},
						null,
						null,
						AFTER_SEX,
						UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_WATCH_START_SEX"));
			}
			return null;
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
			}
			return null;
		}
	};
	
	public static final DialogueNode SHOP_BEAUTY_SALON_ENTER = new DialogueNode("Succubi's Secrets", "-", true) {
		@Override
		public void applyPreParsingEffects() {
			getKate().wakeUp();
			Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_ENTER"));
			if(Main.game.getPlayer().isVisiblyPregnant()) {
				Main.game.getPlayer().setCharacterReactedToPregnancy(getKate(), true);
			}
			if(getKate().isVisiblyPregnant()) {
				getKate().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
			}
		}
		@Override
		public String getContent() {
			return "";//UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_ENTER");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
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
			return new ResponseTrade("Trade with Kate", "There's a separate leaflet tucked into the back of the brochure. It informs you that Kate is a registered distributor for a large jewellery firm.", getKate());
			
		} else if (index == 2) {
			if(!Main.game.getPlayer().isAbleToWearMakeup()) {
				return new Response("Makeup", "As your body is made of "+Main.game.getPlayer().getBodyMaterial().getName()+", Kate is unable to apply any makeup!", null);
				
			} else {
				return new Response("Makeup",
						"Kate offers a wide range of different cosmetic services, and several pages of the brochure are devoted to images displaying different styles and colours of lipstick, nail polish, and other forms of makeup.",
						SHOP_BEAUTY_SALON_COSMETICS);
			}

		} else if (index == 3) {
			return new Response("Hair",
					"There's a double-page spread of all the different dyes, styles, and lengths of hair that Kate's able to work with.",
					SHOP_BEAUTY_SALON_HAIR);

		} else if (index == 4) {
				return new Response("Piercings",
						"Kate offers a wide range of different piercings.",
						SHOP_BEAUTY_SALON_PIERCINGS);

		}  else if (index == 5) {
				return new Response("Eyes",
						"There's a special page near the front of the brochure, advertising Kate's ability to recolour a person's eyes."
						+ " Just like skin recolourings, this is quite demanding on her aura, and is therefore very expensive.", SHOP_BEAUTY_SALON_EYES);

		} else if (index == 6) {
			return new Response("Coverings",
					"There's a special page in the middle of the brochure, advertising Kate's special ability to harness the arcane in order to recolour a person's skin or fur."
					+ " Apparently, this is quite demanding on her aura, and is therefore very expensive.",
					SHOP_BEAUTY_SALON_SKIN_COLOUR){
				@Override
				public void effects() {
					initCoveringsMap(Main.game.getPlayer());
				}
			};

		} else if (index == 7) {
			return new Response("Other", "Kate can offer other miscellaneous services, such as anal bleaching.", SHOP_BEAUTY_SALON_OTHER);

		} else if (index == 8) {
			return new Response("Tattoos", "Most of the brochure is taken up with drawings and photographs displaying Kate's considerable artistic talents."
					+ " She's even able to apply arcane-enchanted tattoos, but they look to be very expensive...", SHOP_BEAUTY_SALON_TATTOOS);

		} else if (index == 9) {
			return new ResponseSex("Sex",
					"At the end of the brochure, there's an extremely lewd collection of pictures of Kate inserting her tail into her various orifices, with the suggestive caption 'Don't make me do it myself...'",
					true, true,
					new SMSitting(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
						}
					},
					null,
					null,
					AFTER_SEX_REPEATED,
					UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MAIN_SEX"));
			
		} else if(index==10) {
			return new ResponseSex("Sleep sex",
					"As she's such a deep sleeper, you're confident that you'd be able to fuck Kate without waking her up."
							+ " All you'd need to do is wait for her to fall asleep and be gentle with her...",
					true, false,
					new SMSitting(
							Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexSlotSitting.SITTING_BETWEEN_LEGS)),
							Util.newHashMapOfValues(new Value<>(getKate(), SexSlotSitting.SITTING))) {
						@Override
						public Map<GameCharacter, List<CoverableArea>> exposeAtStartOfSexMap() {
							return Util.newHashMapOfValues(new Value<>(getKate(), Util.newArrayListOfValues(CoverableArea.VAGINA)));
						}
						@Override
						public SexPace getStartingSexPaceModifier(GameCharacter character) {
							if(character.isPlayer()) {
								return SexPace.DOM_GENTLE;
							}
							return super.getStartingSexPaceModifier(character);
						}
						@Override
						public Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> getStartingCharactersImmobilised() {
							Map<ImmobilisationType, Map<GameCharacter, Set<GameCharacter>>> map = new HashMap<>();
							map.put(ImmobilisationType.SLEEP, new HashMap<>());
							map.get(ImmobilisationType.SLEEP).put(Main.game.getPlayer(), Util.newHashSetOfValues(getKate()));
							return map;
						}
					},
					null,
					null,
					AFTER_SEX_REPEATED,
					UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MAIN_SEX_SLEEP")){
				@Override
				public void effects() {
					getKate().addStatusEffect(StatusEffect.SLEEPING_HEAVY, -1);
				}
			};
			
		} else if (index == 11
				&& Main.game.getPlayer().hasQuest(QuestLine.SIDE_BUYING_BRAX)
				&& Main.game.getPlayer().getQuest(QuestLine.SIDE_BUYING_BRAX)==Quest.BUYING_BRAX_START
				&& !Main.game.getPlayer().hasItemType(ItemType.CANDI_PERFUMES)) {
			if(Main.game.getPlayer().getMoney()<500) {
				return new Response("Candi's perfume", "You need at least 500 flames in order to pay for Candi's perfume!", null);
			}
			return new Response("Candi's perfume", "Tell Kate that you're here to collect Candi's order of perfume.", SHOP_BEAUTY_SALON_CANDI_PERFUME) {
				@Override
				public void effects() {
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-500));
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().addItem(Main.game.getItemGen().generateItem(ItemType.CANDI_PERFUMES), false));
					Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_BUYING_BRAX, Quest.BUYING_BRAX_DELIVER_PERFUME));
				}
			};
			
		} else if (index == 0) {
			return new Response("Leave", "Leave Kate's shop, heading back out into the Shopping Arcade.", EXTERIOR){
				@Override
				public void effects() {
					Main.game.setResponseTab(0);
				}
			};
		}
		
		return null;
	}
	
	public static final DialogueNode SHOP_BEAUTY_SALON_CANDI_PERFUME = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_CANDI_PERFUME");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return getMainResponse(index);
		}
	};
	
	private static String getMoneyRemainingString() {
		return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_MONEY_REMAINING");
	}
	
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
							true, Race.NONE, BodyCoveringType.MAKEUP_BLUSHER, "Blusher", "Blusher (also called rouge) is used to colour the cheeks so as to provide a more youthful appearance, and to emphasise the cheekbones.", true, true)
							
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_LIPSTICK, "Lipstick", "Lipstick is used to provide colour, texture, and protection to the wearer's lips.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_LINER, "Eyeliner", "Eyeliner is applied around the contours of the eyes to help to define shape or highlight different features.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_EYE_SHADOW, "Eye shadow", "Eye shadow is used to make the wearer's eyes stand out or look more attractive.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_HANDS, "Nail polish", "Nail polish is used to colour and protect the nails on your [pc.hands].", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, BodyCoveringType.MAKEUP_NAIL_POLISH_FEET, "Toenail polish", "Toenail polish is used to colour and protect the nails on your [pc.feet].", true, true));
			
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
	
	public static final DialogueNode SHOP_BEAUTY_SALON_HAIR = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "SHOP_BEAUTY_SALON_HAIR"));
			
			UtilText.nodeContentSB.append(getMoneyRemainingString());
			
			UtilText.nodeContentSB.append(
				CharacterModificationUtils.getKatesDivHairLengths(true, "Hair Length", "Hair length determines what hair styles you're able to have. The longer the hair, the more styles are available.")

				+CharacterModificationUtils.getKatesDivHairStyles(true, "Hair Style", "Hair style availability is determined by your hair length.")
				
				+CharacterModificationUtils.getKatesDivCoveringsNew(true, Main.game.getPlayer().getHairType().getRace(), Main.game.getPlayer().getCovering(Main.game.getPlayer().getHairCovering()).getType(),
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
			
			for(Entry<AbstractBodyCoveringType, Value<AbstractRace, List<String>>> entry : coveringsNamesMap.entrySet()){
				AbstractBodyCoveringType bct = entry.getKey();
				AbstractRace race = entry.getValue().getKey();
				GameCharacter target = Main.game.getPlayer();
				
				Value<String, String> titleDescription = getCoveringTitleDescription(target, bct, entry.getValue().getValue());
				
				UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
						true,
						race,
						bct,
						titleDescription.getKey(),
						UtilText.parse(target, titleDescription.getValue()),
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
							true, Main.game.getPlayer().getEyeType().getRace(), Main.game.getPlayer().getEyeCovering(),
							"Irises", "The iris is the coloured part of the eye that's responsible for controlling the diameter and size of the pupil.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_PUPIL)
								:BodyCoveringType.EYE_PUPILS,
							"Pupils", "The pupil is a hole located in the centre of the iris that allows light to strike the retina.", true, true)
		
					+CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE,
							Main.game.getPlayer().getBodyMaterial()!=BodyMaterial.FLESH
								?BodyCoveringType.getMaterialBodyCoveringType(Main.game.getPlayer().getBodyMaterial(), BodyCoveringCategory.EYE_SCLERA)
								:BodyCoveringType.EYE_SCLERA,
							"Sclerae", "The sclera is the (typically white) part of the eye that surrounds the iris.", true, true));
			
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
			
			UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivPiercings(false));
			
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
					CharacterModificationUtils.getKatesDivAnalBleaching()

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
			
			for(AbstractBodyCoveringType bct : BodyCoveringType.getAllBodyCoveringTypes()) {
				if((Main.game.isFacialHairEnabled() && Main.game.getPlayer().getFacialHairType().getType()==bct)
						|| (Main.game.isBodyHairEnabled() && Main.game.getPlayer().getUnderarmHairType().getType()==bct)
						|| (Main.game.isAssHairEnabled() &&  Main.game.getPlayer().getAssHairType().getType()==bct)
						|| (Main.game.isPubicHairEnabled() && Main.game.getPlayer().getPubicHairType().getType()==bct)) {
					UtilText.nodeContentSB.append(CharacterModificationUtils.getKatesDivCoveringsNew(
							true, Race.NONE, bct, "Body hair", "Your body hair.", true, true));
					
				}
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
									?"<span style='color:"+PresetColour.GENERIC_GOOD.toWebHexString()+";'>ON</span>"
									:"<span style='color:"+PresetColour.GENERIC_BAD.toWebHexString()+";'>OFF</span>");
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
	
	public static final DialogueNode SHOP_BEAUTY_SALON_TATTOOS_ADD = new DialogueNode("Succubi's Secrets", "-", true) {

		@Override
		public String getLabel() {
			return "Succubi's Secrets - "+Util.capitaliseSentence(CharacterModificationUtils.tattooInventorySlot.getTattooSlotName()) +" Tattoo";
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
					
				} else if(CharacterModificationUtils.tattoo.getType().equals(TattooType.getTattooTypeFromId("innoxia_misc_none"))
						&& CharacterModificationUtils.tattoo.getWriting().getText().isEmpty()
						&& CharacterModificationUtils.tattoo.getCounter().getType()==TattooCounterType.NONE) {
					return new Response("Apply ("+UtilText.formatAsMoneyUncoloured(value, "span")+")", "You need to select a tattoo type, add some writing, or add a counter in order to make a tattoo!", null);
					
				} else {
					return new Response("Apply ("+UtilText.formatAsMoney(value, "span")+")", "Tell Kate that you'd like her to give you this tattoo.", SHOP_BEAUTY_SALON_TATTOOS) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-value));

							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
							CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
							CharacterModificationUtils.tattoo.setName(CharacterModificationUtils.tattoo.getType().getName());
							Main.game.getPlayer().addTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo);
						}
					};
				}
			
			} else if(index==2) {
				return new Response("Save/Load", "Save/Load tattoo presets.", CosmeticsDialogue.TATTOO_SAVE_LOAD) {
					@Override
					public void effects() {
						CosmeticsDialogue.initTattooSaveLoadDialogue(SHOP_BEAUTY_SALON_TATTOOS_ADD);
					}
				};
			
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
	
	// Sex:
	
	public static final DialogueNode AFTER_SEX = new DialogueNode("Finished", "", true, false) {
		@Override
		public String getDescription() {
			return "[pc.Step] back and allow Kate to recover.";
		}
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "AFTER_SEX");
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Services", "Read the brochure that Kate just handed to you.", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN){
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.kateIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNode AFTER_SEX_REPEATED = new DialogueNode("Finished", "", true, false) {
		@Override
		public String getDescription() {
			if(getKate().isAsleep()) {
				return "[pc.Step] back and leave Kate to continue sleeping.";
			}
			return "[pc.Step] back and allow Kate to recover.";
		}
		public void applyPreParsingEffects() {
			Main.game.appendToTextStartStringBuilder(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/succubisSecrets", "AFTER_SEX_REPEATED"));
			getKate().wakeUp();
		}
		@Override
		public String getContent() {
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Services", "Turn your attention back to the brochure.", SuccubisSecrets.SHOP_BEAUTY_SALON_MAIN);
			}
			return null;
		}
	};
	
}
