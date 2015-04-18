package hu.afi.ld32.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.18..
 */
public class Player extends LivingEntity{
    float maxSpeed = 3f;
    float lastMove = 0f;
    public Player(World world, Vector2 location, float width, float height, int health) {
        super(world, location, width, height, health);
    }

    @Override
    protected void createBody() {
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(getLocation().cpy().add(getWidth()/2, getHeight()/2));
        body = getWorld().getPhys().createBody(bd);
        body.setFixedRotation(true);
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(getWidth()/2f, getHeight()/2f);
        body.createFixture(ps, 1f);
        body.setLinearDamping(0.2f);
    }

    @Override
    public void accelerate(float x, float y) {
        lastMove = 0f;
        body.applyLinearImpulse(new Vector2(x,y), body.getPosition(), true);
        body.setLinearVelocity(body.getLinearVelocity().limit(maxSpeed));

    }

    @Override
    public boolean tick(float delta) {
        lastMove+=delta;
        if(lastMove > 1f){
            body.setLinearVelocity(0,0);
        }
        return super.tick(delta);
    }

    @Override
    public TextureRegion getTexture() {
        return null;
    }
}
