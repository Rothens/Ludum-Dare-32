package hu.afi.ld32.entities.statics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import hu.afi.ld32.entities.StaticEntity;
import hu.afi.ld32.utils.FilterUtil;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public class TreeEntity extends StaticEntity {
    public TreeEntity(World world, Vector2 location) {
        super(world, location, 2f, 2f);
        this.type = "TREE";
    }

    @Override
    protected void createBody() {
        bodyWidth = 2f;
        bodyHeight = 2f;
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(getLocation().cpy().add(bodyDiffX, bodyDiffY));
        body = getWorld().getPhys().createBody(bd);
        body.setFixedRotation(true);
        body.setBullet(true);

        Vector2[] vertices = {
                new Vector2(0.0f, 1.0f),
                new Vector2(-0.3f, 0.75f),
                new Vector2(-0.7f, -0.65f),
                new Vector2(0.0f, -1.0f),
                new Vector2(0.7f, -0.65f),
                new Vector2(0.3f, 0.75f)
        };

        PolygonShape ps = new PolygonShape();
        //ps.setAsBox(bodyWidth/2, bodyHeight/2);
        ps.set(vertices);
        Fixture f = body.createFixture(ps, 1f);
        f.setDensity(1);
        f.setRestitution(0);
        f.setFilterData(FilterUtil.ENVFILTER);

    }

    @Override
    public boolean tick(float delta) {
        return true;
    }

    public TextureRegion getTexture() {
        return TextureHandler.getInstance().getSprite("entity_tree");
    }
}
