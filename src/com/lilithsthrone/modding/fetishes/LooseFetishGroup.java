package com.lilithsthrone.modding.fetishes;

import java.util.List;

import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.utils.Util;

public class LooseFetishGroup extends FetishGroup {
    private AbstractFetish member;
    private Boolean shareWithVictims;

    public LooseFetishGroup(AbstractFetish member, boolean shareWithVictims) {
        this.member = member;
        this.shareWithVictims=shareWithVictims;
    }

    public AbstractFetish getMember() {
        return this.member;
    }

    public boolean shouldShareWithVictims() {
        return this.shareWithVictims;
    }

    @Override
    public List<AbstractFetish> getFetishes() {
        return Util.newArrayListOfValues(this.member);
    }
}
