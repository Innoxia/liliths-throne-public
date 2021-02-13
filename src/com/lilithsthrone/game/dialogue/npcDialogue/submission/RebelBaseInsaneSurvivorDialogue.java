/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lilithsthrone.game.dialogue.npcDialogue.submission;

import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 *
 * @author dustland.crow
 */
public class RebelBaseInsaneSurvivorDialogue {
    
    private static NPC getAttacker() {
		return Main.game.getActiveNPC();
    }
    
    public static final DialogueNode INSANE_SURVIVOR_ATTACK = new DialogueNode("Blocked!", "A figure blocks the way out!", true) {
        @Override
        public String getAuthor() {
                return "DSG";
        }
        @Override
        public int getSecondsPassed() {
                return 30;
        }
        @Override
        public String getContent() {
            return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "ATTACK", getAttacker());
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 1) {
                return new ResponseCombat("Fight", "[npc.Name] is stopping you from leaving this place. Force your way out.",
                        getAttacker(), 
                        Util.newHashMapOfValues(
                        new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(I'll fucking end you, demon scum!)]"))));
            }
            else if (index == 2) {
                return new Response("Talk", "You get the feeling [npc.name] is beyond reason but it might be worth a try.", INSANE_SURVIVOR_TALK_ATTEMPT);
            }
            else if (index == 3) {
                return new Response("Offer money", "[npc.Name] isn't after money it might be worth a try.", INSANE_SURVIVOR_BRIBE_ATTEMPT);
            }
            else if (index == 4) {
                return new Response("Surrender", "[npc.NamePos] kit might be ragged but it still looks serious. You might not be able to win this.", INSANE_SURVIVOR_SURRENDER_ATTEMPT);
            }
            else {
                return null;
            }
        }     
    };
    
    public static final DialogueNode INSANE_SURVIVOR_TALK_ATTEMPT = new DialogueNode("Talk Attempt", "", true) {
        @Override
        public String getAuthor() {
                return "DSG";
        }
        @Override
        public int getSecondsPassed() {
                return 10;
        }
        @Override
        public String getContent() {
            return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "TALK", getAttacker());
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 1) {
                return new ResponseCombat("Fight", "[npc.Name] can't be reasoned with. Force your way out.",
                        getAttacker(), 
                        Util.newHashMapOfValues(
                        new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(I'll fucking end you, demon scum!)]"))));
            }
            else {
                return null;
            }
        }
        
    };
    
    public static final DialogueNode INSANE_SURVIVOR_BRIBE_ATTEMPT = new DialogueNode("Bribe Attempt", "", true) {
        @Override
        public String getAuthor() {
                return "DSG";
        }
        @Override
        public int getSecondsPassed() {
                return 10;
        }
        @Override
        public String getContent() {
            return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "BRIBE", getAttacker());
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 1) {
                return new ResponseCombat("Fight", "[npc.Name] can't be bought. Force your way out.",
                        getAttacker(), 
                        Util.newHashMapOfValues(
                        new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(I'll fucking end you, demon scum!)]"))));
            }
            else {
                return null;
            }
        }
        
    };
    
    public static final DialogueNode INSANE_SURVIVOR_SURRENDER_ATTEMPT = new DialogueNode("Surrendered!", "Know when to fold 'em.", true) {
        @Override
        public String getAuthor() {
                return "DSG";
        }
        @Override
        public int getSecondsPassed() {
                return 10;
        }
        @Override
        public String getContent() {
            return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "SURRENDER", getAttacker());
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 1) {
                return new ResponseCombat("Defend yourself", "[npc.Name] doesn't think your surrender is genuine, leaving you no other option.",
                        getAttacker(), 
                        Util.newHashMapOfValues(
                        new Value<>(getAttacker(), UtilText.parse(getAttacker(), "[npc.speech(I'll fucking end you, demon scum!)]"))));
            }
            else {
                return null;
            }
        }
        
    };
    
    public static final DialogueNode INSANE_SURVIVOR_VICTORY = new DialogueNode("Victory!", "", true) {
        @Override
        public String getAuthor() {
                return "DSG";
        }
        @Override
        public int getSecondsPassed() {
                return 30;
        }
        @Override
        public String getContent() {
            return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "VICTORY", getAttacker());
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 1) {
                return new Response("Continue", "There's no sign of [npc.name] anywhere. Might as well carry on your way...", Main.game.getDefaultDialogue(false)){
                    public void effects() {
                        Main.game.banishNPC(getAttacker());
                        Main.game.getDialogueFlags().values.add(DialogueFlagValue.rebelBaseInsaneSurvivorEncountered);
                    }
                };
            }
            else {
                return null;
            }
        }       
    };
    
    public static final DialogueNode INSANE_SURVIVOR_DEFEATED = new DialogueNode("Defeated!", "", true) {
        @Override
        public String getAuthor() {
                return "DSG";
        }
        @Override
        //advance time by 15 minutes
        public int getSecondsPassed() {
                return 54000;
        }
        @Override
        public String getContent() {
            return UtilText.parseFromXMLFile("encounters/submission/rebelBase/InsaneSurvivorAttack", "DEFEATED", getAttacker());
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 1) {
                return new Response("Continue", "There's no sign of [npc.name] anywhere. Might as well carry on your way...", Main.game.getDefaultDialogue(false)) {
                    @Override
                    public void effects() {
                        Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-5000));
                        Main.game.banishNPC(getAttacker());
                        Main.game.getDialogueFlags().values.add(DialogueFlagValue.rebelBaseInsaneSurvivorEncountered);
                    }
                };
            }
            else {
                return null;
            }
        }  
    };
    
}
