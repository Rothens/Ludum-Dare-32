package hu.afi.ld32.spell;

/**
 * Created by Robin on 2015.04.20..
 */


public class SpellFactory {
    public enum SpellName {FIREBOLT, CONEOFCOLD, ELECTRICITYFIELD}
    String fireboltName = "Firebolt";
    String coneOfColdName = "Cone of Cold";
    String electricityFieldName = "Electricity Field";

    double[] nullArray = {0,0,0,0,0,0,0,0,0,0};


    double[] basicFailRate = {0.5,0.45,0.4,0.35,0.3,0.25,0.2,0.15,0.1,0.05};

    int[] fireBoltDamage = {120,140,160,180,200,220,240,260,280,300};
    int[] coneOfColdDamage = {60,70,80,90,100,110,120,130,140,150};
    int[] electricityFieldDamage = {30,35,40,45,50,55,60,65,70,80};

    int[] basicCost = {5,6,7,8,9,10,11,12,13,14};

    double[] fireBoltRange = nullArray;
    double[] coneOfColdRange = {4,4.5,5,5.5,6,6.5,7,7.5,8,9};
    double[] electricityFieldRange = {1,1.2,1.4,1.6,1.8,2,2.2,2.4,2.6,3};

    double[] coneOfColdSlowRate = {0.2,0.2,0.25,0.25,0.3,0.3,0.35,0.35,0.4,0.4};

    double[] coneOfColdSlowDuration = {3,3,3,3,3,3,3,3,3,3};

    double fireBoltBurnRate = 0.1;

    double[] electricityFieldStunDuration = {1,1.2,1.4,1.8,2,2.2,2.4,2.6,2,8,3};

    public Spell createSpell(SpellName spellName) {
        Spell spell = null;
        if (spellName == SpellName.FIREBOLT) {
            spell = new Spell(fireboltName, basicFailRate, fireBoltDamage, basicCost, fireBoltRange,
                    nullArray, nullArray, fireBoltBurnRate, nullArray,
                    Spell.SpellElement.FIRE, Spell.SpellType.PROJECTILE, true);
        }
        else if (spellName == SpellName.CONEOFCOLD) {
            spell = new Spell(coneOfColdName, basicFailRate, coneOfColdDamage, basicCost, coneOfColdRange,
                    coneOfColdSlowRate, coneOfColdSlowDuration, 0, nullArray,
                    Spell.SpellElement.COLD, Spell.SpellType.CONE, true);
        }

        if (spellName == SpellName.ELECTRICITYFIELD) {
            spell = new Spell(electricityFieldName, basicFailRate, electricityFieldDamage, basicCost, electricityFieldRange,
                    nullArray, nullArray, 0, electricityFieldStunDuration,
                    Spell.SpellElement.LIGHTNING, Spell.SpellType.NOVA, true);
        }

        return spell;
    }
}
