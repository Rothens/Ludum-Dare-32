package hu.afi.ld32.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import hu.afi.ld32.utils.AnimUtil;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.18..
 */
public class Player extends LivingEntity{
    float maxSpeed = 5f;
    float lastMove = 0f;
    float moveTime = 0f;
    public Player(World world, Vector2 location, int health) {
        super(world, location, 2f, 2f, health);

    }

    @Override
    protected void createBody() {
        bodyDiffY = 0.3f;
        bodyWidth = 1f;
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(getLocation().cpy().add(bodyDiffX, bodyDiffY));
        body = getWorld().getPhys().createBody(bd);
        body.setFixedRotation(true);
        CircleShape ps = new CircleShape();
        //ps.setAsBox(getWidth()/2f, getHeight()/2f);
        ps.setRadius(bodyWidth/2f);
        Fixture f = body.createFixture(ps, 1f);
        Filter filt = new Filter();
        filt.categoryBits = (short)1;
        filt.maskBits = (short)~1;
        f.setFilterData(filt);
        body.setLinearDamping(2f);
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
        moveTime+= delta;
        if(lastMove > 0.8f){
            moveTime = 0f;
            body.setLinearVelocity(0,0);
        }
        return super.tick(delta);
    }

    @Override
    public TextureRegion getTexture() {
        return TextureHandler.getInstance().getMain(AnimUtil.getFrameId(moveTime));
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), location.x, location.y, 1f, 0.5f, width, height, 1f, 1f, rotation);
    }
}
