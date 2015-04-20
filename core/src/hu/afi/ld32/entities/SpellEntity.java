package hu.afi.ld32.entities;

import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.world.World;

/**
 * Created by zsomkovacs on 2015.04.18..
 *
 * Note: for spells, extend this class, as CollisionHandler checks spell hit
 * via checking instance type with SpellEntity and LivingEntity
 */
public abstract class SpellEntity extends Entity {
    public SpellEntity(World world, Vector2 location, float width, float height) {
        super(world, location, width, height);
    }
}
