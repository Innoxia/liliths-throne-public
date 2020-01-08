package com.lilithsthrone.game.dialogue.utils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.combat.Spell;
import com.lilithsthrone.game.combat.SpellSchool;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.5.1
 * @version 0.3.5.1
 * @author Innoxia
 */
public class SpellManagement {

    private static GameCharacter target;
    private static DialogueNode dialogueReturn;
    
    public static GameCharacter getTarget() {
        if(target==null) {
            return Main.game.getPlayer();
        }
        return target;
    }

    public static void setTarget(GameCharacter target, DialogueNode dialogueReturn) {
        SpellManagement.target = target;
        SpellManagement.dialogueReturn = dialogueReturn;
    }
    
    private static Response getResponses1To5(int index) {
    	if(index==1) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_EARTH) {
    			return new Response("Earth", UtilText.parse(getTarget(), "You are already viewing [npc.namePos] Earth spells!"), null);
    		}
			return new Response("Earth", UtilText.parse(getTarget(), "View [npc.namePos] spells and upgrades in the school of Earth."), CHARACTER_SPELLS_EARTH);
			
		} else if(index==2) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_WATER) {
    			return new Response("Water", UtilText.parse(getTarget(), "You are already viewing [npc.namePos] Water spells!"), null);
    		}
			return new Response("Water", UtilText.parse(getTarget(), "View [npc.namePos] spells and upgrades in the school of Water."), CHARACTER_SPELLS_WATER);
			
		} else if(index==3) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_FIRE) {
    			return new Response("Fire", UtilText.parse(getTarget(), "You are already viewing [npc.namePos] Fire spells!"), null);
    		}
			return new Response("Fire", UtilText.parse(getTarget(), "View [npc.namePos] spells and upgrades in the school of Fire."), CHARACTER_SPELLS_FIRE);
			
		} else if(index==4) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_AIR) {
    			return new Response("Air", UtilText.parse(getTarget(), "You are already viewing [npc.namePos] Air spells!"), null);
    		}
			return new Response("Air", UtilText.parse(getTarget(), "View [npc.namePos] spells and upgrades in the school of Air."), CHARACTER_SPELLS_AIR);
			
		} else if(index==5) {
    		if(Main.game.getCurrentDialogueNode()==CHARACTER_SPELLS_ARCANE) {
    			return new Response("Arcane", UtilText.parse(getTarget(), "You are already viewing [npc.namePos] Arcane spells!"), null);
    		}
			return new Response("Arcane", UtilText.parse(getTarget(), "View [npc.namePos] spells and upgrades in the school of Arcane."), CHARACTER_SPELLS_ARCANE);
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
							+Spell.getSpellTreesDisplay(SpellSchool.ARCANE, target)
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.ARCANE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldArcane(School of Arcane ability:)] "
								+(!target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.ARCANE)
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
			if(index>=1 && index<=5) {
				return getResponses1To5(index);
				
			} else if(index==6) {
				if(target.hasSpell(Spell.ELEMENTAL_ARCANE)) {
					if(Main.game.getPlayer().isCaptive()) {
						return new Response("Arcane Elemental", "You cannot summon elementals while a captive of someone else!", null);
						
					} else if(Main.game.isInCombat()) {
						return new Response("Arcane Elemental", "While in combat, elementals can only be summoned by casting the spell as a Combat Move!", null);
						
					} else if(dialogueReturn.getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT && !Main.game.isSavedDialogueNeutral()) {
						return new Response("Arcane Elemental", "Elementals can only be summoned in a neutral scene!", null);
					
					} else if(target.getMana()<Spell.ELEMENTAL_ARCANE.getModifiedCost(target)) {
						return new Response("Arcane Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.verb(need)] at least <b>"+Spell.ELEMENTAL_ARCANE.getModifiedCost(target)+"</b> [style.boldMana(aura)] in order to cast this spell!"), null);
						
					} else {
						return new Response("Arcane Elemental",
								(getTarget().isPlayer()
									?"Summon your elemental by binding it to the school of Arcane!"
									:UtilText.parse(getTarget(), "Get [npc.name] to summon [npc.her] elemental by binding it to the school of Arcane!"))
								+ " This will cost <b>"+Spell.ELEMENTAL_ARCANE.getModifiedCost(target)+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_ARCANE) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_ARCANE.applyEffect(target, target, true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Arcane Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.do]n't know how to bind your elemental to the school of Arcane! (Requires spell: '"+Spell.ELEMENTAL_ARCANE.getName()+"')"), null);
				}
				
			} else if(index==11) {
				return new Response("Reset Arcane", UtilText.parse(getTarget(), "Reset [npc.namePos] Arcane upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_ARCANE) {
					@Override
					public void effects() {
						target.resetSpellUpgrades(SpellSchool.ARCANE);
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
			if(target.isPlayer()) {
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
							+Spell.getSpellTreesDisplay(SpellSchool.EARTH, target)
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.EARTH.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldEarth(School of Earth ability:)] "
								+(!target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.EARTH)
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
			if(index>=1 && index<=5) {
				return getResponses1To5(index);
				
			} else if(index==6) {
				if(target.hasSpell(Spell.ELEMENTAL_EARTH)) {
					if(Main.game.getPlayer().isCaptive()) {
						return new Response("Earth Elemental", "You cannot summon elementals while a captive of someone else!", null);
						
					} else if(Main.game.isInCombat()) {
						return new Response("Earth Elemental", "While in combat, elementals can only be summoned by casting the spell as a Combat Move!", null);
						
					} else if(dialogueReturn.getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT && !Main.game.isSavedDialogueNeutral()) {
						return new Response("Earth Elemental", "Elementals can only be summoned in a neutral scene!", null);
					
					} else if(target.getMana()<Spell.ELEMENTAL_EARTH.getModifiedCost(target)) {
						return new Response("Earth Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.verb(need)] at least <b>"+Spell.ELEMENTAL_EARTH.getModifiedCost(target)+"</b> [style.boldMana(aura)] in order to cast this spell!"), null);
						
					} else {
						return new Response("Earth Elemental",
								(getTarget().isPlayer()
										?"Summon your elemental by binding it to the school of Earth!"
										:UtilText.parse(getTarget(), "Get [npc.name] to summon [npc.her] elemental by binding it to the school of Earth!"))
									+ " This will cost <b>"+Spell.ELEMENTAL_EARTH.getModifiedCost(target)+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_EARTH) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_EARTH.applyEffect(target, target, true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Earth Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.do]n't know how to bind your elemental to the school of Earth! (Requires spell: '"+Spell.ELEMENTAL_EARTH.getName()+"')"), null);
				}
				
			}  else  if(index==11) {
				return new Response("Reset Earth", UtilText.parse(getTarget(), "Reset [npc.namePos] Earth upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_EARTH) {
					@Override
					public void effects() {
						target.resetSpellUpgrades(SpellSchool.EARTH);
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
			if(target.isPlayer()) {
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
							+Spell.getSpellTreesDisplay(SpellSchool.WATER, target)
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.WATER.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldWater(School of Water ability:)] "
								+(!target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.WATER)
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
			if(index>=1 && index<=5) {
				return getResponses1To5(index);
				
			} else if(index==6) {
				if(target.hasSpell(Spell.ELEMENTAL_WATER)) {
					if(Main.game.getPlayer().isCaptive()) {
						return new Response("Water Elemental", "You cannot summon elementals while a captive of someone else!", null);
						
					} else if(Main.game.isInCombat()) {
						return new Response("Water Elemental", "While in combat, elementals can only be summoned by casting the spell as a Combat Move!", null);
						
					} else if(dialogueReturn.getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT && !Main.game.isSavedDialogueNeutral()) {
						return new Response("Water Elemental", "Elementals can only be summoned in a neutral scene!", null);
					
						
					} else if(target.getMana()<Spell.ELEMENTAL_WATER.getModifiedCost(target)) {
						return new Response("Water Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.verb(need)] at least <b>"+Spell.ELEMENTAL_WATER.getModifiedCost(target)+"</b> [style.boldMana(aura)] in order to cast this spell!"), null);
						
					} else {
						return new Response("Water Elemental",
								(getTarget().isPlayer()
										?"Summon your elemental by binding it to the school of Water!"
										:UtilText.parse(getTarget(), "Get [npc.name] to summon [npc.her] elemental by binding it to the school of Water!"))
									+ " This will cost <b>"+Spell.ELEMENTAL_WATER.getModifiedCost(target)+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_WATER) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_WATER.applyEffect(target, target, true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Water Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.do]n't know how to bind your elemental to the school of Water! (Requires spell: '"+Spell.ELEMENTAL_WATER.getName()+"')"), null);
				}
				
			} else if(index==11) {
				return new Response("Reset Water", UtilText.parse(getTarget(), "Reset [npc.namePos] Water upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_WATER) {
					@Override
					public void effects() {
						target.resetSpellUpgrades(SpellSchool.WATER);
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
			if(target.isPlayer()) {
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
							+Spell.getSpellTreesDisplay(SpellSchool.AIR, target)
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.AIR.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldAir(School of Air ability:)] "
								+(!target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.AIR)
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
			if(index>=1 && index<=5) {
				return getResponses1To5(index);
				
			} else if(index==6) {
				if(target.hasSpell(Spell.ELEMENTAL_AIR)) {
					if(Main.game.getPlayer().isCaptive()) {
						return new Response("Air Elemental", "You cannot summon elementals while a captive of someone else!", null);
						
					} else if(Main.game.isInCombat()) {
						return new Response("Air Elemental", "While in combat, elementals can only be summoned by casting the spell as a Combat Move!", null);
						
					} else if(dialogueReturn.getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT && !Main.game.isSavedDialogueNeutral()) {
						return new Response("Air Elemental", "Elementals can only be summoned in a neutral scene!", null);
					
						
					} else if(target.getMana()<Spell.ELEMENTAL_AIR.getModifiedCost(target)) {
						return new Response("Air Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.verb(need)] at least <b>"+Spell.ELEMENTAL_AIR.getModifiedCost(target)+"</b> [style.boldMana(aura)] in order to cast this spell!"), null);
						
					} else {
						return new Response("Air Elemental",
								(getTarget().isPlayer()
										?"Summon your elemental by binding it to the school of Air!"
										:UtilText.parse(getTarget(), "Get [npc.name] to summon [npc.her] elemental by binding it to the school of Air!"))
									+ " This will cost <b>"+Spell.ELEMENTAL_AIR.getModifiedCost(target)+"</b> [style.boldMana(aura)]!",
								CHARACTER_SPELLS_AIR) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_AIR.applyEffect(target, target, true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Air Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.do]n't know how to bind your elemental to the school of Air! (Requires spell: '"+Spell.ELEMENTAL_AIR.getName()+"')"), null);
				}
				
			}  else if(index==11) {
				return new Response("Reset Air", UtilText.parse(getTarget(), "Reset [npc.namePos] Air upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_AIR) {
					@Override
					public void effects() {
						target.resetSpellUpgrades(SpellSchool.AIR);
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
			if(target.isPlayer()) {
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
							+Spell.getSpellTreesDisplay(SpellSchool.FIRE, target)
						+"</div>"
						+"<div class='container-full-width' style='width:50%; padding:8px; margin:0;'>"
							+SpellSchool.FIRE.getDescription()
						+"</div>"
						+ "<div class='container-full-width inner' style='text-align:center;'>"
							+ "[style.boldFire(School of Fire ability:)] "
								+(!target.isSpellSchoolSpecialAbilityUnlocked(SpellSchool.FIRE)
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
			if(index>=1 && index<=5) {
				return getResponses1To5(index);
				
			} else if(index==6) {
				if(target.hasSpell(Spell.ELEMENTAL_FIRE)) {
					if(Main.game.getPlayer().isCaptive()) {
						return new Response("Fire Elemental", "You cannot summon elementals while a captive of someone else!", null);
						
					} else if(Main.game.isInCombat()) {
						return new Response("Fire Elemental", "While in combat, elementals can only be summoned by casting the spell as a Combat Move!", null);
						
					} else if(dialogueReturn.getDialogueNodeType()!=DialogueNodeType.OCCUPANT_MANAGEMENT && !Main.game.isSavedDialogueNeutral()) {
						return new Response("Fire Elemental", "Elementals can only be summoned in a neutral scene!", null);
						
					} else {
						String description =(getTarget().isPlayer()
								?"Summon your elemental by binding it to the school of Arcane!"
								:UtilText.parse(getTarget(), "Get [npc.name] to summon [npc.her] elemental by binding it to the school of Arcane!"));
								
						String cost = " This will cost <b>"+Spell.ELEMENTAL_ARCANE.getModifiedCost(target)+"</b> [style.boldMana(aura)]!";
						if(target.getMana()<Spell.ELEMENTAL_FIRE.getModifiedCost(target)) {
							cost = " This will cost <b>"+Math.round(Spell.ELEMENTAL_FIRE.getModifiedCost(target)*0.25f)+"</b> [style.boldHealth("+Attribute.HEALTH_MAXIMUM.getName()+")]!";
						}
						return new Response("Fire Elemental",
								description+cost,
								CHARACTER_SPELLS_FIRE) {
							@Override
							public DialogueNode getNextDialogue() {
								return Main.game.getDefaultDialogue(false);
							}
							@Override
							public void effects() {
								Main.game.getTextStartStringBuilder().append(
										"<p>"
											+Spell.ELEMENTAL_FIRE.applyEffect(target, target, true, false)
										+"</p>");
							}
						};
					}
					
				} else {
					return new Response("Fire Elemental", UtilText.parse(getTarget(), "[npc.Name] [npc.do]n't know how to bind your elemental to the school of Fire! (Requires spell: '"+Spell.ELEMENTAL_FIRE.getName()+"')"), null);
				}
				
			} else if(index==11) {
				return new Response("Reset Fire", UtilText.parse(getTarget(), "Reset [npc.namePos] Fire upgrades, refunding all points spent. [npc.Her] spells will not be reset."), CHARACTER_SPELLS_FIRE) {
					@Override
					public void effects() {
						target.resetSpellUpgrades(SpellSchool.FIRE);
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
			if(target.isPlayer()) {
				return DialogueNodeType.PHONE;
			}
			return DialogueNodeType.OCCUPANT_MANAGEMENT;
		}
	};
}
