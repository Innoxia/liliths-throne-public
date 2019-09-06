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
                    "<div class='container-full-width' style='padding:8px; text-align:center;'>"
                            + "While there is no limit on the number of moves which a character can know and use in combat,"
                            	+ " it's only possible for "+String.valueOf(GameCharacter.MAX_COMBAT_MOVES)+" [style.colourMinorGood(core combat moves)] to be selected at a time."
                            + " Any [style.colourMinorBad(non-core moves)] cost [style.colourBad(+1 action point)] to use in combat, and will suffer a [style.colourBad(+1 turn cooldown)].<br/>"
                            + UtilText.parse(getTarget(), "<i>(You can click on the icons below to add and remove them from [npc.namePos] core combat moves.)</i>")
                            +(getTarget().isPlayer()?"":"<i>([npc.Name] will only choose to use [npc.her] core moves when in combat, unless [npc.she] has none available, in which case [npc.she] will choose from [npc.her] non-core moves.)</i>")
                            + "</div>"
                            + "<div class='container-full-width' style='padding:8px; text-align:center;'>"
                            + "<h6 style='text-align:center;'>[style.colourMinorGood(Core Combat Moves)]</h6>");

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
                            + "<h6 style='text-align:center;'>[style.colourMinorBad(Non-Core Combat Moves)]</h6>");

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
