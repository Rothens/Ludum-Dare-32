package hu.afi.ld32.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.controls.Control;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class LivingEntity extends DynamicEntity {
    public int health;
    public float fireVulnerability = 1.0f;
    public float frostVulnerability = 1.0f;
    public float lightningVulnerability = 1.0f;

    public LivingEntity(World world, Vector2 location, float width, float height, int health) {
        super(world, location, width, height);
        this.health = health;
    }
}
