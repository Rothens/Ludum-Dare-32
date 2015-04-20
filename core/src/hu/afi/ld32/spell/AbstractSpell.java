package hu.afi.ld32.spell;

import java.util.Random;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public abstract class AbstractSpell {
    public enum SpellElement {FIRE, COLD, LIGHTNING}
    public enum SpellType {PROJECTILE, CONE, NOVA, SPECIAL}

    protected String displayedName;

    public double[] failRate = new double[10];

    public String getDisplayedName() {
        return displayedName;
    }

    public int getDamage() {
        return damage[level];
    }

    public int getCost() {
        return cost[level];
    }

    public double getRange() {
        return range[level];
    }

    public double getStunDuration() {
        return stunDuration[level];
    }

    public int getSlowRate() {
        return slowRate[level];
    }

    public double getSlowDuration() {
        return slowDuration[level];
    }

    public double getBurnRate() {
        return burnRate[level];
    }

    protected int[] damage = new int[10];
    protected int[] cost = new int[10];
    // TODO: range és irányultság is a particle-höz tartozik, viszont a fail-tõl függ, valahogy össze kell egyeztetni.
    protected double[] range = new double[10];
    protected int[] slowRate = new int[10];
    protected double[] slowDuration = new double[10];
    protected double[] burnRate = new double[10];
    protected double[] stunDuration = new double[10];

    protected SpellElement element;
    protected SpellType type;
    protected int level = 1;
    protected boolean canLevel = true;
    protected double swayDirection = 0;

    public AbstractSpell(String displayedName, double[] failRate, int[] damage, int[] cost, double[] range,
                         int[] slowRate, double[] slowDuration, double[] burnRate, double[] stunDuration,
                         SpellElement element, SpellType type, boolean canLevel) {
        this.displayedName = displayedName;
        this.failRate = failRate;
        this.damage = damage;
        this.cost = cost;
        this.range = range;
        this.slowRate = slowRate;
        this.slowDuration = slowDuration;
        this.burnRate = burnRate;
        this.stunDuration = stunDuration;
        this.element = element;
        this.type = type;
        this.canLevel = canLevel;
    }

    public double getSwayDirection() {
        return swayDirection;
    }

    protected void fail() {
        Random rand = new Random();
        double failExtent = rand.nextDouble()/2 + 0.25f;
        burnRate[level] = 0;
        if (type == SpellType.PROJECTILE) {
            swayDirection = rand.nextDouble()/10*4 - 20f;
        }
        if (type == SpellType.CONE){
            range[level] = range[level]*failExtent;
        }
        slowDuration[level] = slowDuration[level]*failExtent;
        if (element == SpellElement.LIGHTNING) {
            damage[level] = (int)(damage[level]*failExtent);
            stunDuration[level] = stunDuration[level]*failExtent;
        }
    }

    protected boolean isFailing() {
        Random rand = new Random();
        return rand.nextDouble() < failRate[level];
    }

    public AbstractSpell cast() {
        AbstractSpell spell = this;
        if (isFailing()) {
            spell.fail();
        }
        return spell;
    }
    public void levelUpSpell(){
        if (level < 10 && canLevel) {
            level++;
        } else {
            // TODO: hibaüzenet.
        }

    }
}
