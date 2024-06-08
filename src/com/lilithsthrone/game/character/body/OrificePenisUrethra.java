package com.lilithsthrone.game.character.body;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.OrificeInterface;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeDepth;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;

/**
 * @author Innoxia
 * @version 0.4.9.7
 * @since 0.1.?
 */
public class OrificePenisUrethra implements OrificeInterface {

    protected int wetness;
    protected float capacity;
    protected float stretchedCapacity;
    protected int depth;
    protected int elasticity;
    protected int plasticity;
    protected boolean virgin;
    protected Set<OrificeModifier> orificeModifiers;

    public OrificePenisUrethra(int wetness, int capacity, int depth, int elasticity, int plasticity, boolean virgin, Collection<OrificeModifier> orificeModifiers) {
        this.wetness = wetness;
        this.capacity = capacity;
        stretchedCapacity = capacity;
        this.depth = depth;
        this.elasticity = elasticity;
        this.plasticity = plasticity;
        this.virgin = virgin;

        this.orificeModifiers = new HashSet<>(orificeModifiers);
    }

    public OrificePenisUrethra(OrificePenisUrethra orificePenisUrethraToCopy) {
        this.wetness = orificePenisUrethraToCopy.wetness;
        this.capacity = orificePenisUrethraToCopy.capacity;
        this.stretchedCapacity = orificePenisUrethraToCopy.stretchedCapacity;
        this.depth = orificePenisUrethraToCopy.depth;
        this.elasticity = orificePenisUrethraToCopy.elasticity;
        this.plasticity = orificePenisUrethraToCopy.plasticity;
        this.virgin = orificePenisUrethraToCopy.virgin;

        this.orificeModifiers = new HashSet<>(orificePenisUrethraToCopy.orificeModifiers);
    }

    @Override
    /** <b>This method is not used, as a penis's wetness is determined by its cum production.</b> */
    public Wetness getWetness(GameCharacter owner) {
        return Wetness.valueOf(wetness);
    }

    @Override
    /** <b>This method is not used, as a penis's wetness is determined by its cum production.</b> */
    public String setWetness(GameCharacter owner, int wetness) {
        if (owner != null && !owner.hasPenis()) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a penis, so nothing happens...)]</p>");
        }

        int oldWetness = this.wetness;
        this.wetness = Math.max(0, Math.min(wetness, Wetness.SEVEN_DROOLING.getValue()));
        if (owner == null) {
            return "";
        }

        int wetnessChange = this.wetness - oldWetness;

        if (wetnessChange == 0) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.NamePos] precum production doesn't change...)]</p>");
        }

        String wetnessDescriptor = getWetness(owner).getDescriptor();
        if (wetnessChange > 0) {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.NamePos] [npc.eyes] widen as [npc.she] [npc.verb(feel)] [npc.her] [npc.cock+] suddenly grow hard,"
                            + " and [npc.she] [npc.verb(let)] out [npc.a_moan+] as a slick stream of precum oozes out of the tip as its production [style.boldGrow(increases)].<br/>"
                            + "[npc.She] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
                            + "</p>");

        } else {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] [npc.verb(shift)] about uncomfortably and [npc.verb(let)] out a frustrated groan as [npc.she] [npc.verb(feel)] [npc.her] precum production [style.boldShrink(decrease)].<br/>"
                            + "[npc.She] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(wetnessDescriptor) + " " + wetnessDescriptor + " urethra)]!"
                            + "</p>");
        }
    }

    @Override
    public Capacity getCapacity() {
        return Capacity.getCapacityFromValue((int) capacity);
    }

    @Override
    public float getRawCapacityValue() {
        return capacity;
    }

    @Override
    public String setCapacity(GameCharacter owner, float capacity, boolean setStretchedValueToNewValue) {
        if (owner != null && !owner.hasPenis()) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a penis, so nothing happens...)]</p>");
        }

        float oldCapacity = this.capacity;
        this.capacity = Math.max(0, Math.min(capacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
        if (setStretchedValueToNewValue) {
            this.stretchedCapacity = this.capacity;
        }
        if (owner == null) {
            return "";
        }

        float capacityChange = this.capacity - oldCapacity;

        if (capacityChange == 0) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The capacity of [npc.namePos] urethra doesn't change...)]</p>");
        }

        String capacityDescriptor = getCapacity().getDescriptor();
        if (capacityChange > 0) {
            return UtilText.parse(owner,
                    "<p>"
                            + "An involuntary, shocked gasp escapes from [npc.namePos] mouth as [npc.she] [npc.verb(feel)] [npc.her] penis's urethra relaxing and stretching out."
                            + " Within moments, the alarming feeling has passed, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] urethra's internal [style.boldGrow(capacity has increased)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
                            + "</p>");

        } else {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] [npc.verb(let)] out a cry as [npc.she] [npc.verb(feel)] [npc.her] penis's urethra uncontrollably tightening and clenching."
                            + " Within moments, the alarming feeling has passed, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] urethra's internal [style.boldShrink(capacity has decreased)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(capacityDescriptor) + " " + capacityDescriptor + " urethra)]!"
                            + "</p>");
        }
    }

    @Override
    public float getStretchedCapacity() {
        return stretchedCapacity;
    }

    @Override
    public boolean setStretchedCapacity(float stretchedCapacity) {
        float oldStretchedCapacity = this.stretchedCapacity;
        this.stretchedCapacity = Math.max(0, Math.min(stretchedCapacity, Capacity.SEVEN_GAPING.getMaximumValue(false)));
        return oldStretchedCapacity != this.stretchedCapacity;
    }

    @Override
    public int getMaximumPenetrationDepthComfortable(GameCharacter owner, OrificeDepth depth) {
        return (int) (owner.getPenisRawSizeValue() * 1.5f * depth.getDepthPercentage());
    }

    @Override
    public int getMaximumPenetrationDepthUncomfortable(GameCharacter owner, OrificeDepth depth) {
        if (Main.game.isElasticityAffectDepthEnabled() && OrificeElasticity.getElasticityFromInt(elasticity).isExtendingUncomfortableDepth()) {
            return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * (float) elasticity / 1.8f);
        } else {
            return (int) (getMaximumPenetrationDepthComfortable(owner, depth) * 1.5f);
        }
    }

    @Override
    public OrificeDepth getDepth(GameCharacter owner) {
        if (owner != null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
            return OrificeDepth.SEVEN_FATHOMLESS;
        }
        return OrificeDepth.getDepthFromInt(depth);
    }

    @Override
    public String setDepth(GameCharacter owner, int depth) {
        if (owner != null && !owner.hasPenis()) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a penis, so nothing happens...)]</p>");
        }
        if (owner != null && !owner.getBodyMaterial().isOrificesLimitedDepth()) {
            return UtilText.parse(owner,
                    "<p style='text-align:center;'>[style.colourSex(Due to being made out of " + owner.getBodyMaterial().getName() + ", the depth of [npc.namePos] " + OrificeDepth.SEVEN_FATHOMLESS.getDescriptor() + " urethra can't be changed...)]</p>");
        }

        int oldDepth = this.depth;
        this.depth = Math.max(0, Math.min(depth, OrificeDepth.SEVEN_FATHOMLESS.getValue()));
        if (owner == null) {
            return "";
        }

        int depthChange = this.depth - oldDepth;

        if (depthChange == 0) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The depth of [npc.namePos] urethra doesn't change...)]</p>");
        }

        String depthDescriptor = getDepth(owner).getDescriptor();
        if (depthChange > 0) {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming pressure pulsating from the base of [npc.her] cock up into [npc.her] groin."
                            + " Before [npc.her] gasp can turn into a distressed cry, the pressure suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] penis's urethra [style.boldGrow(has deepened)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " urethra)]!"
                            + "</p>");

        } else {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] an alarming tightening sensation moving its way down from [npc.her] lower abdomen into the base of [npc.her] cock."
                            + " Before [npc.her] gasp can turn into a distressed cry, the feeling suddenly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] penis's urethra [style.boldShrink(has become shallower)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(depthDescriptor) + " " + depthDescriptor + " urethra)]!"
                            + "</p>");
        }
    }

    @Override
    public OrificeElasticity getElasticity() {
        return OrificeElasticity.getElasticityFromInt(elasticity);
    }

    @Override
    public String setElasticity(GameCharacter owner, int elasticity) {
        if (owner != null && !owner.hasPenis()) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a penis, so nothing happens...)]</p>");
        }

        int oldElasticity = this.elasticity;
        if (owner.getBodyMaterial().isOrificesAlwaysMaximumElasticity()) {
            this.elasticity = OrificeElasticity.SEVEN_ELASTIC.getValue();
            if (oldElasticity != this.elasticity) {
                return UtilText.parse(owner,
                        "<p style='text-align:center;'>[style.colourSex(Due to being made out of " + owner.getBodyMaterial().getName() + ", the [npc.namePos] " + OrificeElasticity.SEVEN_ELASTIC.getDescriptor() + " urethra can't be changed...)]</p>");
            } else {
                return "";
            }
        }
        this.elasticity = Math.max(0, Math.min(elasticity, OrificeElasticity.SEVEN_ELASTIC.getValue()));
        int elasticityChange = this.elasticity - oldElasticity;
        if (owner == null) {
            return "";
        }

        if (elasticityChange == 0) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The elasticity of [npc.namePos] urethra doesn't change...)]</p>");
        }

        String elasticityDescriptor = getElasticity().getDescriptor();
        if (elasticityChange > 0) {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange slackening sensation pulsating deep within [npc.her] cock."
                            + " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] urethra's [style.boldGrow(elasticity has increased)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
                            + "</p>");

        } else {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] can't help but let out a surprised gasp as [npc.she] [npc.verb(feel)] a strange clenching sensation pulsating deep within [npc.her] cock."
                            + " Just as quickly as it started, the feeling passes, and [npc.she] very quickly [npc.verb(realise)] that [npc.her] urethra's [style.boldShrink(elasticity has decreased)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(elasticityDescriptor) + " " + elasticityDescriptor + " urethra)]!"
                            + "</p>");
        }
    }

    @Override
    public OrificePlasticity getPlasticity() {
        return OrificePlasticity.getElasticityFromInt(plasticity);
    }

    @Override
    public String setPlasticity(GameCharacter owner, int plasticity) {
        if (owner != null && !owner.hasPenis()) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a penis, so nothing happens...)]</p>");
        }

        int oldPlasticity = this.plasticity;
        this.plasticity = Math.max(0, Math.min(plasticity, OrificePlasticity.SEVEN_MOULDABLE.getValue()));
        int plasticityChange = this.plasticity - oldPlasticity;
        if (owner == null) {
            return "";
        }

        if (plasticityChange == 0) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled(The plasticity of [npc.namePos] urethra doesn't change...)]</p>");
        }

        String plasticityDescriptor = getPlasticity().getDescriptor();
        if (plasticityChange > 0) {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange hardening sensation pulsating deep within [npc.her] cock."
                            + " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] urethra's [style.boldGrow(plasticity has increased)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
                            + "</p>");

        } else {
            return UtilText.parse(owner,
                    "<p>"
                            + "[npc.Name] [npc.verb(let)] out a shocked gasp as [npc.she] suddenly [npc.verb(feel)] a strange softening sensation pulsating deep within [npc.her] cock."
                            + " Before [npc.she] [npc.has] any time to panic, the feeling quickly fades away, leaving [npc.herHim] instinctively knowing that [npc.her] urethra's [style.boldShrink(plasticity has decreased)].<br/>"
                            + "[npc.Name] now [npc.has] [style.boldSex(" + UtilText.generateSingularDeterminer(plasticityDescriptor) + " " + plasticityDescriptor + " urethra)]!"
                            + "</p>");
        }
    }

    @Override
    public boolean isVirgin() {
        return virgin;
    }

    @Override
    public void setVirgin(boolean virgin) {
        this.virgin = virgin;
    }

    @Override
    public boolean hasOrificeModifier(OrificeModifier modifier) {
        return orificeModifiers.contains(modifier);
    }

    @Override
    public String addOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
        if (hasOrificeModifier(modifier)) {
            return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
        }
        if (owner != null && !owner.hasPenis()) {
            return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] [npc.verb(lack)] a penis, so nothing happens...)]</p>");
        }

        orificeModifiers.add(modifier);

        if (owner == null) {
            return "";
        }

        switch (modifier) {
            case MUSCLE_CONTROL:
                if (owner.isUrethraFuckable()) {
                    return UtilText.parse(owner,
                            "<p>"
                                    + "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up at the base of [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                                    + " With an experimental clench, [npc.she] [npc.verb(discover)] that the interior of [npc.her] urethra is now lined with [style.boldGrow(muscles)],"
                                    + " which [npc.she] can use to expertly grip and squeeze down on any penetrating object.<br/>"
                                    + "[style.boldSex([npc.NamePos] penile urethra is now lined with an intricate series of muscles!)]"
                                    + "</p>");
                }
                break;
            case RIBBED:
                if (owner.isUrethraFuckable()) {
                    return UtilText.parse(owner,
                            "<p>"
                                    + "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up at the base of [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                                    + " Shifting around a little, [npc.she] [npc.verb(discover)] that the inside of [npc.her] urethra is now lined with [style.boldGrow(fleshy, highly-sensitive ribs)],"
                                    + " which provide extreme pleasure when stimulated.<br/>"
                                    + "[style.boldSex([npc.NamePos] penile urethra is now lined with fleshy, pleasure-inducing ribs!)]"
                                    + "</p>");
                }
                break;
            case TENTACLED:
                if (owner.isUrethraFuckable()) {
                    return UtilText.parse(owner,
                            "<p>"
                                    + "[npc.Name] can't help but let out [npc.a_moan+] as an intense pressure swells up at the base of [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                                    + " With an experimental clench, [npc.she] [npc.verb(discover)] that the inside of [npc.her] urethra is now lined with [style.boldGrow(little wriggling tentacles)], over which [npc.sheHasFull] limited control.<br/>"
                                    + "[style.boldSex(The inside of [npc.namePos] penile urethra is now filled with small tentacles, which wriggle and caress any intruding object with a mind of their own!)]"
                                    + "</p>");
                }
                break;
            case PUFFY:
                return UtilText.parse(owner,
                        "<p>"
                                + "[npc.Name] can't help but let out [npc.a_moan+] as [npc.she] [npc.verb(feel)] a tingling sensation running up the length of [npc.her] [npc.cock],"
                                + " before the rim of [npc.her] urethra suddenly [style.boldGrow(puffs up)] into a doughnut-like ring.<br/>"
                                + "[style.boldSex(The rim of [npc.namePos] penile urethra is now swollen and puffy!)]"
                                + "</p>");
        }

        // Catch:
        return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
    }

    @Override
    public String removeOrificeModifier(GameCharacter owner, OrificeModifier modifier) {
        if (!hasOrificeModifier(modifier)) {
            return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
        }

        orificeModifiers.remove(modifier);

        if (owner == null) {
            return "";
        }

        switch (modifier) {
            case MUSCLE_CONTROL:
                if (owner.isUrethraFuckable()) {
                    return UtilText.parse(owner,
                            "<p>"
                                    + "[npc.Name] can't help but let out a startled cry as an intense pressure swells up within [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                                    + " With an experimental clench, [npc.she] [npc.verb(discover)] that the interior of [npc.her] urethra has [style.boldShrink(lost its extra muscles)].<br/>"
                                    + "[style.boldSex([npc.NamePos] penile urethra is no longer lined with an intricate series of muscles!)]"
                                    + "</p>");
                }
                break;
            case RIBBED:
                if (owner.isUrethraFuckable()) {
                    return UtilText.parse(owner,
                            "<p>"
                                    + "[npc.Name] can't help but let out a startled cry as an intense pressure swells up within [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                                    + " Shifting [npc.her] [npc.pussy] around a little, [npc.she] [npc.verb(discover)] that the [style.boldShrink(fleshy, highly-sensitive ribs)] that once lined [npc.her] urethra [style.boldShrink(have vanished)].<br/>"
                                    + "[style.boldSex([npc.NamePos] penile urethra is no longer lined with fleshy, pleasure-inducing ribs!)]"
                                    + "</p>");
                }
                break;
            case TENTACLED:
                if (owner.isUrethraFuckable()) {
                    return UtilText.parse(owner,
                            "<p>"
                                    + "[npc.Name] can't help but let out a startled cry as an intense pressure swells up within [npc.her] [npc.cock], but before [npc.sheHasFull] any chance to react, the feeling quickly dissipates."
                                    + " With an experimental clench, [npc.she] [npc.verb(discover)] that the [style.boldShrink(wriggling tentacles)] within [npc.her] urethra [style.boldShrink(have all disappeared)].<br/>"
                                    + "[style.boldSex(The inside of [npc.namePos] penile urethra is no longer filled with tentacles!)]"
                                    + "</p>");
                }
                break;
            case PUFFY:
                return UtilText.parse(owner,
                        "<p>"
                                + "[npc.Name] can't help but let out a startled cry as [npc.she] feels a tingling sensation running up the length of [npc.her] [npc.cock],"
                                + " before the [style.boldShrink(puffy rim)] of [npc.her] urethra [style.boldShrink(deflates)] into a more normal-looking shape.<br/>"
                                + "[style.boldSex(The rim of [npc.namePos] penile urethra is no longer swollen and puffy!)]"
                                + "</p>");
        }

        // Catch:
        return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
    }

    public Set<OrificeModifier> getOrificeModifiers() {
        return orificeModifiers;
    }

}
