package com.lilithsthrone.modding.fetishes;

import java.util.List;

import com.lilithsthrone.game.character.fetishes.AbstractFetish;
import com.lilithsthrone.utils.Util;

public class RelatedFetishGroup extends FetishGroup {
    private AbstractFetish dominant;
    private AbstractFetish submissive;

    public RelatedFetishGroup(AbstractFetish dominant, AbstractFetish submissive) {
        this.dominant=dominant;
        this.submissive=submissive;
    }

    public AbstractFetish getDominantFetish() {
        return dominant;
    }

    public AbstractFetish getSubmissiveFetish() {
        return submissive;
    }
    
    @Override
    public List<AbstractFetish> getFetishes() {
        return Util.newArrayListOfValues(this.dominant,this.submissive);
    }
}
