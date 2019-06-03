package com.lilithsthrone.game.dialogue.utils;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.combat.CombatMove;
import com.lilithsthrone.game.dialogue.DialogueNode;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;

/**
 * @since 0.3.4
 * @version 0.3.4
 * @author Irbynx
 */
public class CombatMovesSetup {

    private static GameCharacter target;
    private static DialogueNode dialogueReturn;
    
    public static GameCharacter getTarget() {
        if(target==null) {
            return Main.game.getPlayer();
        }
        return target;
    }

    public static void setTarget(GameCharacter target, DialogueNode dialogueReturn) {
        CombatMovesSetup.target = target;
        CombatMovesSetup.dialogueReturn = dialogueReturn;
    }

    public static final DialogueNode COMBAT_MOVES_CORE = new DialogueNode("Combat Moves", "", true) {
        @Override
        public String getHeaderContent() {
            UtilText.nodeContentSB.setLength(0);

            UtilText.nodeContentSB.append(
                    "<div class='container-full-width' style='padding:8px;'>"
                            + "Every character can have up to "+String.valueOf(GameCharacter.MAX_COMBAT_MOVES)+" combat moves available to them at a time in combat, but they may know an infinite amount themselves. <br/>"
                            + "You can click on the active moves to remove them from your active move list and click on the known ones to add them."
                            + "</div>"
                            + "<div class='container-full-width' style='padding:8px; text-align:center;'>"
                            + "<h6 style='text-align:center;'>Active Moves</h6>");

            for(int i=0;i<GameCharacter.MAX_COMBAT_MOVES;i++) {
                CombatMove mv = null;
                if(i<target.getEquippedMoves().size()) {
                    mv = target.getEquippedMoves().get(i);
                }
                if(mv!=null) {
                    UtilText.nodeContentSB.append("<div id='MOVE_" + mv.getIdentifier() + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + mv.getType().getColour().toWebHexString() + ";'>"
                            + "<div class='square-button-content'>"+mv.getSVGString()+"</div>"
                            + "</div>");

                } else {
                    UtilText.nodeContentSB.append("<div id='MOVE_" + i + "' class='square-button small' style='display:inline-block; float:none;'></div>");

                }
            }
            UtilText.nodeContentSB.append("</div>");

            UtilText.nodeContentSB.append(
                            "<div class='container-full-width' style='padding:8px; text-align:center;'>"
                            + "<h6 style='text-align:center;'>Known Moves</h6>");

            for(int i=0;i<target.getAvailableMoves().size();i++) {
                CombatMove mv = target.getAvailableMoves().get(i);
                if(!target.getEquippedMoves().contains(target.getAvailableMoves().get(i))) {
                    UtilText.nodeContentSB.append("<div id='MOVE_" + mv.getIdentifier() + "' class='square-button small' style='width:8%; display:inline-block; float:none; border:2px solid " + mv.getType().getColour().toWebHexString() + ";'>"
                            + "<div class='square-button-content'>" + mv.getSVGString() + "</div>"
                            + "</div>");
                }
            }
            UtilText.nodeContentSB.append("</div>");

            return UtilText.nodeContentSB.toString();
        }

        @Override
        public String getContent(){
            return "";
        }

        @Override
        public Response getResponse(int responseTab, int index) {
            if (index == 0) {
                return new Response("Back", "Return to the previous menu.", dialogueReturn);
            } else {
                return null;
            }
        }

        @Override
        public DialogueNodeType getDialogueNodeType() {
            return DialogueNodeType.PHONE;
        }
    };
}
