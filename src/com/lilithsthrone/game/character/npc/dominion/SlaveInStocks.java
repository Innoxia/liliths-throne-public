package com.lilithsthrone.game.character.npc.dominion;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.EquipClothingSetting;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.misc.GenericSexualPartner;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.persona.Occupation;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.occupantManagement.SlaveJobSetting;
import com.lilithsthrone.game.occupantManagement.SlavePermission;
import com.lilithsthrone.game.occupantManagement.SlavePermissionSetting;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.Season;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.95
 * @version 0.2.6
 * @author Innoxia
 */
public class SlaveInStocks extends NPC {

	public SlaveInStocks() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public SlaveInStocks(Gender gender) {
		this(gender, false);
	}
	
	public SlaveInStocks(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public SlaveInStocks(Gender gender, boolean isImported) {
		super(isImported, null, null, "",
				Util.random.nextInt(28)+18, Util.randomItemFrom(Month.values()), 1+Util.random.nextInt(25),
				3, gender, Subspecies.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS, false);

		if(!isImported) {
			
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				if(s==Subspecies.REINDEER_MORPH
						&& Main.game.getSeason()==Season.WINTER
						&& Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.hasSnowedThisWinter)) {
					addToSubspeciesMap(10, gender, s, availableRaces);
					
				} else if(s.getRace()!=Race.DEMON
						&& s.getRace()!=Race.ANGEL
						&& s.getRace()!=Race.ELEMENTAL
						&& s!=Subspecies.FOX_ASCENDANT
						&& s!=Subspecies.FOX_ASCENDANT_FENNEC
						&& s!=Subspecies.SLIME) {
					if(Subspecies.getMainSubspeciesOfRace(s.getRace())==s) {
						addToSubspeciesMap(10, gender, s, availableRaces);
					} else {
						addToSubspeciesMap(3, gender, s, availableRaces);
					}
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(this.getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a slave, who, for one reason or another, has been locked into the stocks for public use."));
			
			// PERSONALITY & BACKGROUND:

			this.setHistory(Occupation.NPC_SLAVE);
//			if(this.isFeminine()) {
//				if(Math.random()>0.5f) {
//					this.setHistory(Occupation.NPC_PROSTITUTE);
//					setSexualOrientation(SexualOrientation.AMBIPHILIC);
//					setName(Name.getRandomProstituteTriplet());
//					useItem(AbstractItemType.generateItem(ItemType.PROMISCUITY_PILL), this, false);
//				} else {
//					this.setHistory(Occupation.NPC_MUGGER);
//				}
//				
//			} else {
//				this.setHistory(Occupation.NPC_MUGGER);
//			}
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this, true);
			
			// INVENTORY:
			
			resetInventory(true);
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);

			equipClothing(EquipClothingSetting.getAllClothingSettings());
			
			CharacterUtils.applyMakeup(this, true);

			if(Math.random()<0.8f) {
				this.addSlaveJobSettings(SlaveJobSetting.SEX_ORAL);
				this.setFaceVirgin(false);
			}
			
			if(Math.random()<0.6f) {
				this.addSlaveJobSettings(SlaveJobSetting.SEX_ANAL);
				this.setAssVirgin(false);
			}
			
			if(!this.hasVagina()) {
				this.addSlaveJobSettings(SlaveJobSetting.SEX_ANAL);
				this.setAssVirgin(false);
			} else {
				if(Math.random()<0.6f) {
					this.addSlaveJobSettings(SlaveJobSetting.SEX_VAGINAL);
					this.setVaginaVirgin(false);
				}
			}
			
			this.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_BODY);
			this.removeSlavePermissionSetting(SlavePermission.CLEANLINESS, SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES);
			
			this.setPlayerKnowsName(true);

			initHealthAndManaToMax();
		}
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}

	@Override
	public void setStartingBody(boolean setPersona) {
		// Not needed
	}

	@Override
	public void equipClothing(List<EquipClothingSetting> settings) {
		this.equipClothingFromNowhere(AbstractClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR), true, this);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.getHistory()==Occupation.NPC_PROSTITUTE) {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of whoring [npc.herself] out in the back alleys of Dominion are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a prostitute who whores [npc.herself] out in the backalleys of Dominion."));
			}
			
		} else {
			if(this.isSlave()) {
				return (UtilText.parse(this,
						"[npc.NamePos] days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
			} else {
				return (UtilText.parse(this,
						"[npc.Name] is a resident of Dominion, who prowls the back alleys in search of innocent travellers to mug and rape."));
			}
		}
	}

	@Override
	public boolean isClothingStealable() {
		return false;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public void changeFurryLevel(){
	}
	
	@Override
	public DialogueNode getEncounterDialogue() {
		return null; //TODO
	}
	
	@Override
	public void hourlyUpdate() {
		if(Main.game.isStarted() && !Main.game.getCharactersPresent().contains(this)) {
			float chanceToBeUsed = (12 - Main.game.getHourOfDay()%12)/12f;
			if(Math.random()<chanceToBeUsed) {
//				System.out.println("generated!");
				GenericSexualPartner stocksPartner;
				if(Math.random()<0.25f) {
					stocksPartner = new GenericSexualPartner(Gender.F_P_V_B_FUTANARI, this.getWorldLocation(), this.getLocation(), false);
				} else {
					stocksPartner = new GenericSexualPartner(Gender.M_P_MALE, this.getWorldLocation(), this.getLocation(), false);
				}
//				try {
//					Main.game.addNPC(stocksPartner, false);
//				} catch (Exception e1) {
//					e1.printStackTrace();
//				}
				
				if(!Main.game.getCharactersPresent().contains(this)) {
					if(this.hasSlaveJobSetting(SlaveJobSetting.SEX_ORAL)) {
						this.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
					}
					if(this.hasSlaveJobSetting(SlaveJobSetting.SEX_ANAL)) {
						this.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
					}
					if(this.hasSlaveJobSetting(SlaveJobSetting.SEX_VAGINAL)) {
						this.calculateGenericSexEffects(false, stocksPartner, new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
					}
				}
				
//				System.out.println("hmm  " + Main.game.banishNPC(stocksPartner));
				
			}
		}
	}
}
