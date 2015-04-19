package hu.afi.ld32.entities.statics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.StaticEntity;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public class StoneEntity extends StaticEntity {
    public StoneEntity(World world, Vector2 location, float width, float height) {
        super(world, location, width, height);
    }

    @Override
    public boolean tick(float delta) {
        return true;
    }

    public TextureRegion getTexture() {
        return TextureHandler.getInstance().getSprite("entity_stone");
    }
}
