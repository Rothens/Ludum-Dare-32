package hu.afi.ld32.spell;

import java.util.Random;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public abstract class AbstractSpell {
    public enum SpellElement {FIRE, COLD, LIGHTNING}
    public enum SpellType {PROJECTILE, CONE, NOVA}

    public double failRate = 0.8;

    public boolean isFailing() {
        Random rand = new Random();
        return rand.nextDouble() < failRate;
    }

    public abstract void cast();
}
