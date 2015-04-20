package hu.afi.ld32.spell;

import java.util.Random;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public class Spell {
    public enum SpellElement {FIRE, COLD, LIGHTNING}
    public enum SpellType {PROJECTILE, CONE, NOVA, SPECIAL}

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

    public double getSlowRate() {
        return slowRate[level];
    }

    public double getSlowDuration() {
        return slowDuration[level];
    }

    public double getBurnRate() {
        return burnRate;
    }

    public double getSwayDirection() {
        return swayDirection;
    }


    protected String displayedName;
    protected double[] failRate = new double[10];

    protected int[] damage = new int[10];
    protected int[] cost = new int[10];
    // TODO: range és irányultság is a particle-höz tartozik, viszont a fail-tõl függ, valahogy össze kell egyeztetni.
    protected double[] range = new double[10]; // méterben. célszerû valahogy átkonvertálni még...
    protected double[] slowRate = new double[10];
    protected double[] slowDuration = new double[10]; // másodpercben.
    protected double burnRate = 0; // sebzésének mekkora részét rakja még rá burn-ben
    protected double[] stunDuration = new double[10]; // másodpercben.

    protected SpellElement element;
    protected SpellType type;
    protected int level = 1; // level of the spell.
    protected boolean canLevel = true; // is it a 10-leveled spell, or not.
    protected double swayDirection = 0; // projetile-hoz; szögben megadva az eltérés mértéke.

    public Spell(String displayedName, double[] failRate, int[] damage, int[] cost, double[] range,
                 double[] slowRate, double[] slowDuration, double burnRate, double[] stunDuration,
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


    protected void fail() {
        Random rand = new Random();
        double failExtent = rand.nextDouble()/2 + 0.25f;
        burnRate = 0;
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

    public Spell cast() {
        Spell spell = this;
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
