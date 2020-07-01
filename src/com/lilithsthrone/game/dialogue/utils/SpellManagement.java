package com.lilithsthrone.game.dialogue.utils;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.spells.Spell;
import com.lilithsthrone.game.combat.spells.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.3.5.1
 * @version 0.3.7.5
 * @author Innoxia
 */
public class SpellManagement {

    private static GameCharacter spellOwner;
    private static GameCharacter spellTarget;
    private static DialogueNode dialogueReturn;
    
    private static DialogueNode spellScreenAfterCasting;
    
    private static Spell spell;
    
    public static GameCharacter getSpellOwner() {
        if(spellOwner==null) {
            return Main.game.getPlayer();
        }
        return spellOwner;
    }

    public static GameCharacter getSpellTarget() {
        if(spellTarget==null) {
            return Main.game.getPlayer();
        }
        return spellTarget;
    }

    public static DialogueNode getDialogueReturn() {
        return dialogueReturn;
    }
    
    public static void setSpellOwner(GameCharacter spellOwner, DialogueNode dialogueReturn) {
        SpellManagement.spellOwner = spellOwner;
        SpellManagement.spellTarget = spellOwner;
        SpellManagement.dialogueReturn = dialogueReturn;
    }
    
    public static DialogueNode castSpell(Spell spell) {
    	SpellManagement.spellScreenAfterCasting = Main.game.getCurrentDialogueNode();
    	SpellManagement.spell = spell;
    	
    	spell.performOnSelection(0, getSpellOwner(), getSpellTarget(), null, getSpellOwner().getParty()); // Handles aura cost
    	
		Main.game.getTextStartStringBuilder().append(
				"<p style='text-align:center;'>"
					+ "<b>Casting '<span style='color:"+spell.getSpellSchool().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(spell.getName())+"</span>':</b>"
					+ "<br/>"
					+spell.applyEffect(getSpellOwner(), getSpellTarget(), true, false)
				+"</p>");
		
    	return SPELL_CAST_DIALOGUE;
    }
    
    private static Response getResponses1To6(int index) {
    	if(index==1) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_EARTH) {
    			return new Response("Earth", UtilText.parse(getSpellOwner(), "You are already viewing [npc.namePos] Earth spells!"), null);
    		}
			return new Response("Earth", UtilText.parse(getSpellOwner(), "View [npc.namePos] spells and upgrades in the school of Earth."), CHARACTER_SPELLS_EARTH);
			
		} else if(index==2) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_WATER) {
    			return new Response("Water", UtilText.parse(getSpellOwner(), "You are already viewing [npc.namePos] Water spells!"), null);
    		}
			return new Response("Water", UtilText.parse(getSpellOwner(), "View [npc.namePos] spells and upgrades in the school of Water."), CHARACTER_SPELLS_WATER);
			
		} else if(index==3) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_FIRE) {
    			return new Response("Fire", UtilText.parse(getSpellOwner(), "You are already viewing [npc.namePos] Fire spells!"), null);
    		}
			return new Response("Fire", UtilText.parse(getSpellOwner(), "View [npc.namePos] spells and upgrades in the school of Fire."), CHARACTER_SPELLS_FIRE);
			
		} else if(index==4) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_AIR) {
    			return new Response("Air", UtilText.parse(getSpellOwner(), "You are already viewing [npc.namePos] Air spells!"), null);
    		}
			return new Response("Air", UtilText.parse(getSpellOwner(), "View [npc.namePos] spells and upgrades in the school of Air."), CHARACTER_SPELLS_AIR);
			
		} else if(index==5) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_ARCANE) {
    			return new Response("Arcane", UtilText.parse(getSpellOwner(), "You are already viewing [npc.namePos] Arcane spells!"), null);
    		}
			return new Response("Arcane", UtilText.parse(getSpellOwner(), "View [npc.namePos] spells and upgrades in the school of Arcane."), CHARACTER_SPELLS_ARCANE);
			
		} else if(index==6) {
			return new ResponseEffectsOnly(
					UtilText.parse(getSpellTarget(), "Target: <b style='color:"+getSpellTarget().getFemininity().getColour().toWebHexString()+";'>[npc.Name]</b>"),
					"Cycle the targeted character for casting spells on.") {
				@Override
				public void effects() {
					List<GameCharacter> companions = Util.newArrayListOfValues(Main.game.getPlayer());
					companions.addAll(Main.game.getPlayer().getCompanions());
					if(!companions.isEmpty()) {
						for(int i=0; i<companions.size();i++) {
							if(companions.get(i).equals(getSpellTarget())) {
								if(i==companions.size()-1) {
									spellTarget = companions.get(0);
									break;
									
								} else {
									spellTarget = companions.get(i+1);
									break;
								}
							}
						}
					}
					Main.game.updateResponses();
				}
			};
		}
    	return null;
    }

    public static final DialogueNode CHARACTER_SPELLS_ARCANE = new DialogueNode("Arcane Spells", "", true) {
		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.ARCANE, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.ARCANE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldArcane(School of Arcane ability:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)
									?"[style.colourDisabled("+SpellSchool.ARCANE.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Arcane school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.ARCANE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=6) {
				return getResponses1To6(index);
				
			} else if(index==11) {
				return new Response("Reset Arcane", UtilText.parse(getSpellOwner(), "Reset [npc.namePos] Arcane upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_ARCANE) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.ARCANE);
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the previous screen.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_EARTH = new DialogueNode("Earth Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.EARTH, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.EARTH.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldEarth(School of Earth ability:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
									?"[style.colourDisabled("+SpellSchool.EARTH.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Earth school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.EARTH.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=6) {
				return getResponses1To6(index);
				
			}  else if(index==11) {
				return new Response("Reset Earth", UtilText.parse(getSpellOwner(), "Reset [npc.namePos] Earth upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.EARTH);
					}
				};
				
			} else if(index == 0) {
				return new Response("Back", "Return to the previous screen.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_WATER = new DialogueNode("Water Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.WATER, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.WATER.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldWater(School of Water ability:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
									?"[style.colourDisabled("+SpellSchool.WATER.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Water school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.WATER.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=6) {
				return getResponses1To6(index);
				
			} else if(index==11) {
				return new Response("Reset Water", UtilText.parse(getSpellOwner(), "Reset [npc.namePos] Water upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_WATER) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.WATER);
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the previous screen.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_AIR = new DialogueNode("Air Spells", "", true) {


		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.AIR, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.AIR.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldAir(School of Air ability:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.AIR)
									?"[style.colourDisabled("+SpellSchool.AIR.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Air school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.AIR.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=6) {
				return getResponses1To6(index);
				
			}  else if(index==11) {
				return new Response("Reset Air", UtilText.parse(getSpellOwner(), "Reset [npc.namePos] Air upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_AIR) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.AIR);
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the previous screen.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	
	public static final DialogueNode CHARACTER_SPELLS_FIRE = new DialogueNode("Fire Spells", "", true) {

		@Override
		public String getHeaderContent() {
			UtilText.nodeContentSB.setLength(0);

			UtilText.nodeContentSB.append(
					"<div class='container-full-width' style='width:100%; padding:0; margin:0;'>"
						+"<div class='container-full-width' style='width:50%; padding:0; margin:0;'>"
							+Spell.getSpellTreesDisplay(SpellSchool.FIRE, getSpellOwner(), getSpellTarget())
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.FIRE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldFire(School of Fire ability:)] "
								+(!getSpellOwner().isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)
									?"[style.colourDisabled("+SpellSchool.FIRE.getPassiveBuff()+")]<br/>(Requires knowing at least <b>three</b> Fire school spells to unlock.)"
									:"[style.colourGood("+SpellSchool.FIRE.getPassiveBuff()+")]")
						+ "</div>"
					+"</div>");
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public String getContent(){
			return "";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index>=1 && index<=6) {
				return getResponses1To6(index);
				
			} else if(index==11) {
				return new Response("Reset Fire", UtilText.parse(getSpellOwner(), "Reset [npc.namePos] Fire upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_FIRE) {
					@Override
					public void effects() {
						getSpellOwner().resetSpellUpgrades(SpellSchool.FIRE);
					}
				};
				
			} else if (index == 0) {
				return new Response("Back", "Return to the previous screen.", dialogueReturn);
			
			} else {
				return null;
			}
		}

		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
	

	public static final DialogueNode SPELL_CAST_DIALOGUE = new DialogueNode("", "", true) {
		@Override
		public int getSecondsPassed() {
			return 1*60;
		}
		@Override
		public String getLabel() {
			return Util.capitaliseSentence(spell.getName());
		}
		@Override
		public String getContent(){
			return "";
		}
		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Return to the spell management screen.", spellScreenAfterCasting);
			}
			return null;
		}
		@Override
		public DialogueNodeType getDialogueNodeType() {
			if(getSpellOwner().isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
}
