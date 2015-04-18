package hu.afi.ld32.entities;

import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class StaticEntity extends Entity {
    public StaticEntity(World world, Vector2 location, float width, float height) {
        super(world, location, width, height);
    }
}
