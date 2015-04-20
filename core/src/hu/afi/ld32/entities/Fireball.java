package hu.afi.ld32.entities;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.20..
 */
public class Fireball extends LivingEntity {
    private ParticleEffectPool.PooledEffect effect;

    public Fireball(World world, Vector2 location, int health) {
        super(world, location, .5f, .5f, health);
        this.fireVulnerability = 0.0f;
        this.frostVulnerability = 5.0f;
        this.lightningVulnerability = 0.0f;
        effect = TextureHandler.getInstance().boltPool.obtain();
        effect.setPosition(getLocation().x + getWidth()/2, getLocation().y + getHeight()/2);
        getWorld().effects.add(effect);
        this.type = "FIREBALL";
    }

    public void setDirection(float dir){
        Vector2 accel = new Vector2(10f,0f);
        accel.rotate(dir);
        if(body != null){
            body.setLinearVelocity(accel);
        }
    }

    @Override
    protected void createBody() {
        bodyWidth = 1f;
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        bd.position.set(getLocation().cpy().add(bodyDiffX, bodyDiffY));
        body = getWorld().getPhys().createBody(bd);
        body.setFixedRotation(true);
        body.setBullet(true);
        CircleShape ps = new CircleShape();
        ps.setRadius(bodyWidth/2f);
        Fixture f = body.createFixture(ps, 1f);
        Filter filt = new Filter();
        filt.categoryBits = (short)1;
        filt.maskBits = (short)~1;
        f.setFilterData(filt);
    }

    @Override
    public void accelerate(float x, float y) {

    }

    @Override
    public boolean tick(float delta) {
        super.tick(delta);
        effect.setPosition(getLocation().x, getLocation().y);
        health -= delta;
        if(health <= 0){
            effect.allowCompletion();
            if(body != null) {
                getWorld().getPhys().destroyBody(body);
            }
        }
        return health > 0;
    }

    @Override
    public TextureRegion getTexture() {
        return TextureHandler.getInstance().getSprite("entity_fireball");
    }
}
