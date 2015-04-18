package hu.afi.ld32.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class StaticEntity extends Entity {
    public StaticEntity(Vector2 location, float width, float height) {
        super(location, width, height);
    }
}
