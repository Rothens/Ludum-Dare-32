package hu.afi.ld32.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class LivingEntity extends Entity {
    public int health;
    public LivingEntity(Vector2 location, int health) {
        super(location);
        this.health = health;
    }
}
