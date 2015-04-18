package hu.afi.ld32.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class LivingEntity extends Entity {
    public int health;
    protected Vector2 previousLocation;
    public LivingEntity(Vector2 location, float width, float height, int health) {
        super(location, width, height);
        this.health = health;
        previousLocation = location.cpy();
    }

    public abstract void accelerate(float x, float y);


}
