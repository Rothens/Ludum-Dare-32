package hu.afi.ld32.entities.statics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import hu.afi.ld32.entities.StaticEntity;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public class AdobeReader extends StaticEntity {

    public AdobeReader(World world, Vector2 location, float width, float height) {
        super(world, location, width, height);
    }

    @Override
    public boolean tick(float delta) {
        return true;
    }

    @Override
    protected void createBody() {
        bodyWidth = 1f;
        bodyHeight = 1f;
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(getLocation().cpy().add(bodyDiffX, bodyDiffY));
        body = getWorld().getPhys().createBody(bd);
        body.setFixedRotation(true);
        body.setBullet(true);

        PolygonShape ps = new PolygonShape();
        ps.setAsBox(bodyWidth/2, bodyHeight/2);
        Fixture f = body.createFixture(ps, 1f);
        f.setDensity(1);
        f.setRestitution(0);
    }

    public TextureRegion getTexture() {
        return TextureHandler.getInstance().getSprite("entity_ar");
    }
}
