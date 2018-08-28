package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.body.types.FootType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing logic for Combat Moves. Additionally contains all the registered combat moves in the game.
 */
public class CombatMove {
    public static List<CombatMove> allCombatMoves = new ArrayList<>();

    private String identifier;
    private String name;
    private int cooldown;
    private int APcost;
    private CombatMoveType type;
    private boolean canTargetEnemies;
    private boolean canTargetAllies;
    private boolean canTargetSelf;
    private String SVGString;

    static {
        // Initializing default actions
        // TODO
        // Strike: Main weapon deals 100% damage
        // Battle Dance: Deal 60% damage with main weapon and 30% with offhand weapon.
        // Flash kick: Deal 100% unarmed damage
        // Protect: Block 25 physical damage
        // Calm Down: Block 25 lust damage
        // Escape: Attempts to escape
        // Tease: Deals 7 lust damage


        /*=============================================
         *
         *              DEFAULT ACTIONS
         *
         =============================================*/
        /*=============================================
         *
         *
         *
         */
        CombatMove newCombatMove = new CombatMove("strike",
                "strike",
                0,
                1,
                CombatMoveType.ATTACK,
                "moves/strike",
                false,
                true,
                false){

            private int getDamage(GameCharacter source)
            {
                AbstractWeapon weapon = source.getMainWeapon();
                if (weapon == null) {
                    return source.getUnarmedDamage();
                } else {
                    return weapon.getWeaponType().getDamage();
                }
            }

            @Override
            public String isAvailableFromSpecialCase(GameCharacter source)
            {
                return "This move is a starter move, available to everyone from the beginning.";
            }

            @Override
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.PHYSICAL;
                if(source.getMainWeapon() != null)
                {
                    damageType = source.getMainWeapon().getDamageType();
                }
                return "Attack " + target.getName() + ", dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamage(source)) + " " + damageType.getName() + "</span>"
                        + " damage.";
            }

            @Override
            public String getDescription()
            {
                DamageType damageType = DamageType.PHYSICAL;
                if(Main.game.getPlayer().getMainWeapon() != null)
                {
                    damageType = Main.game.getPlayer().getMainWeapon().getDamageType();
                }
                return "Attack your enemy, dealing 100% ("
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamage(Main.game.getPlayer())) + "</span>"
                        + ") of your main weapon's damage to them.";
            }

            @Override
            public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.PHYSICAL;
                if(source.getMainWeapon() != null)
                {
                    damageType = source.getMainWeapon().getDamageType();
                }

                int dealtDamage = damageType.damageTarget(target, source, getDamage(source));

                return source.getName("The")+" attacked " + target.getName() + ", dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(dealtDamage) + "</span> "
                        + damageType.getName() + " damage to them.";
            }
        };
        allCombatMoves.add(newCombatMove);

        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("twin-strike",
                "twin strike",
                0,
                1,
                CombatMoveType.ATTACK,
                "moves/twin-strike",
                false,
                true,
                false){

            private int getDamageMain(GameCharacter source)
            {
                AbstractWeapon weapon = source.getMainWeapon();
                int totalDamage;
                if (weapon == null) {
                    totalDamage = source.getUnarmedDamage();
                } else {
                    totalDamage = weapon.getWeaponType().getDamage();
                }
                return (int)(totalDamage * 0.6);
            }

            private int getDamageOffhand(GameCharacter source)
            {
                AbstractWeapon weapon = source.getOffhandWeapon();
                int totalDamage;
                if (weapon == null) {
                    totalDamage = source.getUnarmedDamage();
                } else {
                    totalDamage = weapon.getWeaponType().getDamage();
                }
                return (int)(totalDamage * 0.3);
            }

            @Override
            public String isAvailableFromSpecialCase(GameCharacter source)
            {
                return "This move is a starter move, available to everyone from the beginning.";
            }

            @Override
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageTypeMain = DamageType.PHYSICAL;
                if(source.getMainWeapon() != null)
                {
                    damageTypeMain = source.getMainWeapon().getDamageType();
                }
                DamageType damageTypeOffhand = DamageType.PHYSICAL;
                if(source.getOffhandWeapon() != null)
                {
                    damageTypeOffhand = source.getOffhandWeapon().getDamageType();
                }
                return "Attack " + target.getName() + ", dealing "
                        + "<span style='color:" + damageTypeMain.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamageMain(source)) + " " + damageTypeMain.getName() + "</span>"
                        + " and "
                        + "<span style='color:" + damageTypeOffhand.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamageOffhand(source)) + " " + damageTypeOffhand.getName() + "</span>"
                        + " damage.";
            }

            @Override
            public String getDescription()
            {
                DamageType damageTypeMain = DamageType.PHYSICAL;
                if(Main.game.getPlayer().getMainWeapon() != null)
                {
                    damageTypeMain = Main.game.getPlayer().getMainWeapon().getDamageType();
                }
                DamageType damageTypeOffhand = DamageType.PHYSICAL;
                if(Main.game.getPlayer().getOffhandWeapon() != null)
                {
                    damageTypeOffhand = Main.game.getPlayer().getOffhandWeapon().getDamageType();
                }
                return "Attack your enemy, dealing 60% ("
                        + "<span style='color:" + damageTypeMain.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamageMain(Main.game.getPlayer())) + "</span>"
                        + ") of your main weapon's damage and 30% ("
                        + "<span style='color:" + damageTypeOffhand.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamageOffhand(Main.game.getPlayer())) + "</span>"
                        + ") of your offhand weapon's damage to them.";
            }

            @Override
            public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageTypeMain = DamageType.PHYSICAL;
                if(source.getMainWeapon() != null)
                {
                    damageTypeMain = source.getMainWeapon().getDamageType();
                }
                DamageType damageTypeOffhand = DamageType.PHYSICAL;
                if(source.getOffhandWeapon() != null)
                {
                    damageTypeOffhand = source.getOffhandWeapon().getDamageType();
                }

                int dealtDamageMain = damageTypeMain.damageTarget(target, source, getDamageMain(source));
                int dealtDamageOffhand = damageTypeOffhand.damageTarget(target, source, getDamageOffhand(source));

                return source.getName("The")+" attacked " + target.getName() + ", dealing "
                        + "<span style='color:" + damageTypeMain.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(dealtDamageMain) + " </span> "
                        + damageTypeMain.getName()
                        + "and"
                        + "<span style='color:" + damageTypeOffhand.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(dealtDamageOffhand) + " </span> "
                        + damageTypeOffhand.getName()
                        + " damage to them.";
            }
        };
        allCombatMoves.add(newCombatMove);

        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("block",
                "block",
                0,
                1,
                CombatMoveType.DEFEND,
                "moves/block",
                false,
                false,
                true){
            private int getBlock() {
                return 7;
            }

            @Override
            public String isAvailableFromSpecialCase(GameCharacter source)
            {
                return "This move is a starter move, available to everyone from the beginning.";
            }

            @Override
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.ENERGY;
                return "Defend for "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock())+ " " + damageType.getName() + " </span>"
                        + " damage for the next turn.";
            }

            @Override
            public String getDescription()
            {
                DamageType damageType = DamageType.ENERGY;
                return "Defend, blocking for "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock()) + "</span>"
                        + " of the incoming " + damageType.getName() + " damage for this turn.";
            }

            @Override
            public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.ENERGY;
                return source.getName("The")+" defended this turn, gaining protection from "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock()) + "</span>"
                        + " of the incoming " + damageType.getName() + " damage for this turn.";
            }

            @Override
            public void performOnSelection(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.ENERGY;
                target.setShields(damageType, target.getShields(damageType) + getBlock());
            }
        };
        allCombatMoves.add(newCombatMove);


        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("tease",
                "tease",
                0,
                1,
                CombatMoveType.TEASE,
                "moves/tease",
                false,
                true,
                false){

            private int getDamage(GameCharacter source)
            {
                return 7;
            }

            @Override
            public String isAvailableFromSpecialCase(GameCharacter source)
            {
                return "This move is a starter move, available to everyone from the beginning.";
            }

            @Override
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.LUST;
                return "Tease " + target.getName() + ", dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamage(source)) + " " + damageType.getName() + "</span>"
                        + " damage.";
            }

            @Override
            public String getDescription()
            {
                DamageType damageType = DamageType.LUST;
                return "Tease your enemy, dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamage(Main.game.getPlayer())) + "</span>"
                        + " lust damage to them.";
            }

            @Override
            public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.LUST;

                int dealtDamage = damageType.damageTarget(target, source, getDamage(source));

                return source.getSeductionDescription(target) + "<br/>"
                        + "As a result, " + source.getName("the")+" teased " + target.getName() + ", dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(dealtDamage) + "</span> "
                        + damageType.getName() + " damage to them.";
            }
        };
        allCombatMoves.add(newCombatMove);


        /*=============================================
         *
         *
         *
         */
        newCombatMove = new CombatMove("avert",
                "avert",
                0,
                1,
                CombatMoveType.DEFEND,
                "moves/avert",
                false,
                false,
                true){
            private int getBlock() {
                return 7;
            }

            @Override
            public String isAvailableFromSpecialCase(GameCharacter source)
            {
                return "This move is a starter move, available to everyone from the beginning.";
            }

            @Override
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.LUST;
                return "Defend for "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock())+ " " + damageType.getName() + " </span>"
                        + " damage for the next turn.";
            }

            @Override
            public String getDescription()
            {
                DamageType damageType = DamageType.LUST;
                return "Avert your gase, blocking for "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock()) + "</span>"
                        + " of the incoming " + damageType.getName() + " damage for this turn.";
            }

            @Override
            public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.LUST;
                target.setShields(damageType, target.getShields(damageType) + getBlock());
                return source.getName("The")+" averted their gase this turn, gaining protection from "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock()) + "</span>"
                        + " of the incoming " + damageType.getName() + " damage for this turn.";
            }

            @Override
            public void performOnSelection(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.LUST;
                target.setShields(damageType, target.getShields(damageType) + getBlock());
            }

            @Override
            public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                if(shouldBlunder())
                {
                    return (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
                }
                if(source.getLustLevel() == LustLevel.FOUR_IMPASSIONED || source.getLustLevel() == LustLevel.FIVE_BURNING)
                {
                    return 1.0f + 0.2f * (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
                }
                return 0.8f + 0.2f * (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(this.getType());
            }
        };
        allCombatMoves.add(newCombatMove);




        /*=============================================
         *
         *                   RACIAL
         *
         =============================================*/
        /*=============================================
         *
         *
         *
         */newCombatMove = new CombatMove("hoof-kick",
                "hoof kick",
                0,
                2,
                CombatMoveType.ATTACK,
                "moves/hoof-kick",
                false,
                true,
                false){

            private int getDamage(GameCharacter source)
            {
                return source.getUnarmedDamage()*2;
            }

            @Override
            public String isAvailableFromSpecialCase(GameCharacter source)
            {
                if(source.getLegType().getFootType() == FootType.HOOFS)
                {
                    return "This move is a move available to anyone with hooves.";
                }
                else
                {
                    return null;
                }
            }

            @Override
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.UNARMED.getSuperDamage(target, source);
                return "Attack " + target.getName() + ", dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamage(source)) + " " + damageType.getName() + "</span>"
                        + " damage. "
                        + "<span style='color:" + Colour.GENERIC_GOOD + ";'>"
                        + "Crit if this move breaks block; if crits, do a second kick." + "</span>";
            }

            @Override
            public String getDescription()
            {
                DamageType damageType = DamageType.UNARMED.getSuperDamage(null, Main.game.getPlayer());
                return "Attack your enemy, dealing 100% ("
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamage(Main.game.getPlayer())) + "</span>"
                        + ") unarmed damage to them. "
                        + "<span style='color:" + Colour.GENERIC_GOOD + ";'>"
                        + "Will crit if this move breaks block; if it crits, will do a second kick." + "</span>";
            }

            @Override
            public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.UNARMED.getSuperDamage(target, source);

                int dealtDamage = damageType.damageTarget(target, source, getDamage(source));

                String report = source.getName("The")+" kicked " + target.getName() + ", dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(dealtDamage) + "</span> "
                        + damageType.getName() + " damage to them.<br/>";
                if(canCrit(source, target, enemies, allies))
                {
                    dealtDamage = damageType.damageTarget(target, source, getDamage(source)); // Second kick damage from the crit.
                    report += "The attack broke through the block, staggering the target, letting " + source.getName("the")
                        + "<span style='color:" + Colour.GENERIC_GOOD + ";'>"
                        + "to critically hit" + "</span>" + target.getName("the") + ", throwing in a second kick for "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(dealtDamage) + "</span> "
                        + damageType.getName() + " additional damage!";
                }
                return report;
            }

            @Override
            public boolean canCrit(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                int potentialDamage = DamageType.UNARMED.shieldCheckNoDamage(source, target, getDamage(source));
                if(potentialDamage > 0 && potentialDamage != getDamage(source))
                {
                    return true;
                }
                return false;
            }
        };
        allCombatMoves.add(newCombatMove);




        /*=============================================
         *
         *                   SPELLS
         *
         =============================================*/
        // Automatically generates respective moves based on the Spell class.
        for(Spell spell : Spell.values())
        {
            newCombatMove = new CombatMove(
                    spell.name(),
                    spell.getName(),
                    spell.getCooldown(),
                    spell.getAPCost(),
                    CombatMoveType.SPELL,
                    spell.getSVGString(),
                    spell.isCanTargetAllies(),
                    spell.isCanTargetEnemies(),
                    spell.isCanTargetSelf()) {

                private Spell associatedSpell = spell;

                @Override
                public String isAvailableFromSpecialCase(GameCharacter source)
                {
                    if(source.hasSpell(this.associatedSpell))
                    {
                        return "This is a spell and it is available to casters that learned it.";
                    }
                    else
                    {
                        return null;
                    }
                }

                @Override
                public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies)
                {
                    return associatedSpell.getWeight(source, enemies, allies);
                }

                @Override
                public GameCharacter getPreferredTarget(GameCharacter  source, List<GameCharacter> enemies, List<GameCharacter> allies)
                {
                    return associatedSpell.getPreferredTarget(source, enemies, allies);
                }

                @Override
                public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
                {
                    return associatedSpell.getPrediction(source, target, enemies, allies);
                }

                @Override
                public void applyDisruption(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
                {
                    associatedSpell.applyDisruption(source, target, enemies, allies);
                }

                @Override
                public String getDescription()
                {
                    return associatedSpell.getDescription();
                }

                @Override
                public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
                {
                    return associatedSpell.perform(source, target, enemies, allies);
                }

                @Override
                public void performOnSelection(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
                {
                    associatedSpell.performOnSelection(source, target, enemies, allies);
                }

                @Override
                public boolean canCrit(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
                {
                    return associatedSpell.canCrit(source, target, enemies, allies);
                }
            };
        }
    }

    /**
     * Default constructor
     * @param identifier = Internal name that the game will refer to when looking up the item. Must be unique for proper searching.
     * @param name = Human friendly name of the action
     * @param cooldown = Amount of turns this action takes to become available. 0 means it's available always, 1 means it is available only once per turn.
     * @param APcost = Cost in action points to perform this action.
     * @param type = Combat action type. Used for various interactions.
     */
    public CombatMove(String identifier, String name, int cooldown, int APcost, CombatMoveType type, String pathName, boolean canTargetAllies, boolean canTargetEnemies, boolean canTargetSelf)
    {
        this.identifier = identifier;
        this.name = name;
        this.cooldown = cooldown;
        this.APcost = APcost;
        this.type = type;
        this.canTargetEnemies = canTargetEnemies;
        this.canTargetAllies = canTargetAllies;
        this.canTargetSelf = canTargetSelf;

        try {
            InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + pathName + ".svg");
            if(is==null) {
                System.err.println("Error! Perk icon file does not exist (Trying to read from '"+pathName+"')!");
            }
            SVGString = Util.inputStreamToString(is);

            SVGString = SVGString.replaceAll("#ff2a2a", this.type.getColour().getShades()[0]);
            SVGString = SVGString.replaceAll("#ff5555", this.type.getColour().getShades()[1]);
            SVGString = SVGString.replaceAll("#ff8080", this.type.getColour().getShades()[2]);
            SVGString = SVGString.replaceAll("#ffaaaa", this.type.getColour().getShades()[3]);
            SVGString = SVGString.replaceAll("#ffd5d5", this.type.getColour().getShades()[4]);

            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns weight of the action. Used in calculations for AI to pick certain actions. For every unspent AP, AI will try to select an action out of the available ones. The AI will then pick the action with highest weight. Randomness of action selection should be in the weight values itself!
     * @param source Character that uses the weight function.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return Weight of the action. 1.0 is the expected normal weight; weigh the actions accordingly.
     */
    public float getWeight(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        if(canTargetAllies && allies.isEmpty())
        {
            return 0.0f;
        }
        if(shouldBlunder())
        {
            return (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
        }
        // Trying to figure out best use cases
        switch(type)
        {
            default:
                return (float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type); // Other types are too nuanced in themselves to have a broad weight generation apply to them
            case ATTACK:
                for(GameCharacter character : enemies)
                {
                    if(character.getHealthPercentage() < 0.2)
                    {
                        return 1.1f + 0.2f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type); // If the enemy is low on health, chances to attack increase
                    }
                }
                return 0.8f + 0.2f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type); // Attacks aren't sophisticated
            case DEFEND:
                if(source.getHealthPercentage() < 0.2)
                {
                    return 1.0f + 0.5f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
                }
                return 0.25f + 0.75f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
            case TEASE:
                float weight = 0.8f + 0.2f*(float)(Math.random()) - 0.2f * source.getSelectedMovesByType(type);
                for(GameCharacter character : enemies)
                {
                    if(character.getLustLevel() == LustLevel.FOUR_IMPASSIONED || character.getLustLevel() == LustLevel.FIVE_BURNING)
                    {
                        weight += 0.2f;
                        break;
                    }
                }
                if(source.getCorruptionLevel() == CorruptionLevel.FOUR_LUSTFUL || source.getCorruptionLevel() == CorruptionLevel.FIVE_CORRUPT)
                {
                    weight += 0.4f;
                }
                return weight; // Attacks aren't sophisticated
        }
    }

    /**
     * Returns the preferred target for the action. Prefers to aim at targets with lowest HP values if not forced to select at random. Override for custom behavior
     * @param source Character that uses the target function.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return Character to target with this action.
     */
    public GameCharacter getPreferredTarget(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        if(canTargetEnemies)
        {
            if(shouldBlunder())
            {
                return enemies.get(Util.random.nextInt(enemies.size()));
            }
            else
            {
                float lowestHP = -1;
                GameCharacter potentialCharacter = null;
                for(GameCharacter character : enemies)
                {
                    if(lowestHP == -1 || character.getHealth() < lowestHP)
                    {
                        potentialCharacter = character;
                        lowestHP = character.getHealth();
                    }
                }
                return potentialCharacter;
            }
        }
        if(canTargetAllies && !allies.isEmpty())
        {
            if(shouldBlunder())
            {
                return allies.get(Util.random.nextInt(allies.size()));
            }
            else
            {
                float lowestHP = -1;
                GameCharacter potentialCharacter = null;
                for(GameCharacter character : allies)
                {
                    if(lowestHP == -1 || character.getHealth() < lowestHP)
                    {
                        potentialCharacter = character;
                        lowestHP = character.getHealth();
                    }
                }
                return potentialCharacter;
            }
        }
        return source;
    }

    /**
     * @param identifier Identifier of the move to find.
     * @return Returns the move if it can find one or null if it can't
     */
    public static CombatMove getMove(String identifier)
    {
        for (CombatMove move: allCombatMoves) {
            if(move.getIdentifier().equals(identifier)) {
                return move;
            }
        }
        return null;
    }

    /**
     * Gets a prediction that specifies what kind of action will be performed for the player (i.e "The catgirl will attack you for 10 damage" or "The horseboy is planning to buff his ally")
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the intent of the NPC that uses this action.
     */
    public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        return "There's a plan for you. More pain.";
    }

    /**
     * Performs the action itself. Override to get actual abilities there.
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the action that has been performed.
     */
    public String perform(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        return "The action has been performed.";
    }

    /**
     * Performs the action itself. Override to get actual abilities there. This is performed during selection of the action and not during the turn. Use for blocks, AP damage/gains or disrupts.
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the action that has been performed.
     */
    public void performOnSelection(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        // Nothing. Override it.
    }


    /**
     * Cancel out the action's effects if it's disrupted or cancelled via AP loss. Is called for every action in the queue for every disruption caused; non disrupted actions get reapplied.
     * @param source Character that uses the action.
     * @param source Target for the action.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return The string that describes the action that has been performed.
     */
    public void applyDisruption(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        source.setRemainingAP(source.getRemainingAP() + this.getAPcost() * -1, enemies, allies); // Normally this is the only thing that gets adjusted on selection.
    }

    /**
     * Checks the source character to see if they will have to use the action already with a disruption.
     * @param source
     */
    public boolean isAlreadyDisrupted(GameCharacter source)
    {
        return source.disruptionByTypeCheck(this.getType());
    }

    /**
     * Returns a string if the character has the move available to select even if they don't "own" it; for example, purity based moves are available to Pure Virgin fetishists without even unlocking them.
     *
     * String contains the reason for why the move is available to them. Otherwise returns null.
     */
    public String isAvailableFromSpecialCase(GameCharacter source)
    {
        return null;
    }

    /**
     * Returns a string if action can't be used either due to special constraints or because of AP/cooldowns on a specified target; string specifies rejection reason. Returns null if action can be used without an issue.
     * @param source Character that uses the action.
     * @param source Target for the action. Can be null.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     */
    public String isUseable(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        if(target != null)
        {
            // TODO: Figure out if target is an enemy
            if(!canTargetSelf && source == target)
            {
                return "This action can't be used on yourself!";
            }

            if(!canTargetAllies && allies.contains(target) && source != target)
            {
                return "This action can't be used on your allies!";
            }

            if(!canTargetEnemies && enemies.contains(target))
            {
                return "This action can't be used on your enemies!";
            }
        }

        if(source.getMoveCooldown(this.getIdentifier()) > 0)
        {
            return "This action can't be used since it is still on cooldown! "+String.valueOf(source.getMoveCooldown(this.getIdentifier()))+" turns remaining.";
        }

        if(source.getRemainingAP() < this.getAPcost())
        {
            return "This action can't be used since you don't have enough AP!";
        }

        return null;
    }

    /**
     * Returns true based on user settings on how often should the AI make "mistakes" and select actions irrationally.
     * @return
     */
    static boolean shouldBlunder()
    {
        return Math.random() <= Main.getProperties().AIblunderRate;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getCooldown() {
        return cooldown;
    }

    public CombatMoveType getType() {
        return type;
    }

    public boolean isCanTargetEnemies() {
        return canTargetEnemies;
    }

    public boolean isCanTargetAllies() {
        return canTargetAllies;
    }

    public boolean isCanTargetSelf() {
        return canTargetSelf;
    }

    public int getAPcost() {
        return APcost;
    }

    public String getDescription() {
        return "This action does nothing.";
    }

    public String getName() {
        return name;
    }

    public String getSVGString() {
        return SVGString;
    }

    public boolean canCrit(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        // Normally moves crit on three hits in a row.
        int thisMoveSelected = 0;
        for(CombatMove move : source.getSelectedMoves())
        {
            if(move.getIdentifier() == this.getIdentifier())
            {
                thisMoveSelected++;
            }
        }
        if(thisMoveSelected >= 3)
        {
            return true;
        }
        return false;
    }

}
