package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class ScoreboardHealthCriteria extends ScoreboardBaseCriteria {

    public ScoreboardHealthCriteria(String s) {
        super(s);
    }

    public int getScoreModifier(List<EntityHuman> list) {
        float f = 0.0F;

        EntityHuman entityhuman;

        for (Iterator iterator = list.iterator(); iterator.hasNext(); f += entityhuman.getHealth() + entityhuman.getAbsorptionHearts()) {
            entityhuman = (EntityHuman) iterator.next();
        }

        if (list.size() > 0) {
            f /= (float) list.size();
        }

        return MathHelper.f(f);
    }

    public boolean isReadOnly() {
        return true;
    }

    public IScoreboardCriteria.EnumScoreboardHealthDisplay c() {
        return IScoreboardCriteria.EnumScoreboardHealthDisplay.HEARTS;
    }
}
