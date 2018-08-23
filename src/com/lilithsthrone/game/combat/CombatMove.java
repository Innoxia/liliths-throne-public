package com.lilithsthrone.game.combat;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
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
                    return 1 + (int)(source.getAttributeValue(Attribute.MAJOR_PHYSIQUE)/10);
                } else {
                    return weapon.getWeaponType().getDamage();
                }
            }

            @Override
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.PHYSICAL;
                if(source.getMainWeapon() != null)
                {
                    damageType = source.getMainWeapon().getDamageType();
                }
                return "Attack " + target.getName() + ", dealing 100% ("
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getDamage(source)) + "</span>"
                        + ") of their weapon's damage to them.";
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

                int dealtDamage = damageType.damageTarget(target, getDamage(source));

                return source.getName("The")+" attacked " + target.getName() + ", dealing "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(dealtDamage) + "</span> "
                        + damageType.getName() + " damage to them.";
            }
        };
        allCombatMoves.add(newCombatMove);

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
            public String getPrediction(GameCharacter source, GameCharacter target, List<GameCharacter> enemies, List<GameCharacter> allies)
            {
                DamageType damageType = DamageType.ENERGY;
                return "Defend for "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock()) + "</span>"
                        + " of the incoming " + damageType.getName() + " damage for the next turn.";
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
                target.setShields(damageType, target.getShields(damageType) + getBlock());
                return source.getName("The")+" defended this turn, gaining protection from "
                        + "<span style='color:" + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
                        + String.valueOf(getBlock()) + "</span>"
                        + " of the incoming " + damageType.getName() + " damage for this turn.";
            }
        };
        allCombatMoves.add(newCombatMove);
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
        return (float)(Math.random());
    }

    /**
     * Returns the preferred target for the action. By default prefers a random enemy or ally if it can't target enemies but can target allies and has one, otherwise targets self.
     * @param source Character that uses the target function.
     * @param enemies Enemies of the character
     * @param allies Allies of the character
     * @return Character to target with this action.
     */
    public GameCharacter getPreferredTarget(GameCharacter source, List<GameCharacter> enemies, List<GameCharacter> allies)
    {
        if(canTargetEnemies)
        {
            return enemies.get(Util.random.nextInt(enemies.size()));
        }
        if(canTargetAllies && !allies.isEmpty())
        {
            return allies.get(Util.random.nextInt(allies.size()));
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
     * Returns true if the character has the move available to select even if they don't "own" it; for example, purity based moves are available to Pure Virgin fetishists without even unlocking them.
     */
    public boolean isAvailableFromSpecialCase(GameCharacter source)
    {
        return false;
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

}
