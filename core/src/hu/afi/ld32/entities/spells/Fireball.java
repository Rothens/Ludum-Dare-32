package hu.afi.ld32.entities.spells;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import hu.afi.ld32.entities.SpellEntity;
import hu.afi.ld32.spell.SpellFactory;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.20..
 */
public class Fireball extends SpellEntity {
    private ParticleEffectPool.PooledEffect effect;

    public Fireball(World world, Vector2 location) {
        super(world, location, .5f, .5f);
        effect = TextureHandler.getInstance().boltPool.obtain();
        effect.setPosition(getLocation().x + getWidth() / 2, getLocation().y + getHeight() / 2);
        getWorld().effects.add(effect);
        this.type = "FIREBALL";
        SpellFactory sf = new SpellFactory();
        this.spell = sf.createSpell(SpellFactory.SpellName.FIREBOLT);
    }

    public void setDirection(float dir){
        Vector2 accel = new Vector2(10f, 0f);
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
        ps.setRadius(bodyWidth / 2f);
        Fixture f = body.createFixture(ps, 1f);
    }

    @Override
    public void accelerate(float x, float y) {

    }

    @Override
    public void accelerateForward(float acc) {

    }

    @Override
    public void accelerateRight(float acc) {

    }

    @Override
    public boolean tick(float delta) {
        super.tick(delta);
        effect.setPosition(getLocation().x, getLocation().y);
        return true;
    }

    @Override
    public TextureRegion getTexture() {
        return TextureHandler.getInstance().getSprite("entity_fireball");
    }
}
