package hu.afi.ld32.entities.statics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.StaticEntity;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public class TreeEntity extends StaticEntity {
    public TreeEntity(World world, Vector2 location) {
        super(world, location, 2f,2f);
    }

    @Override
    public boolean tick(float delta) {
        return true;
    }

    public TextureRegion getTexture() {
        return TextureHandler.getInstance().getSprite("entity_tree");
    }
}
