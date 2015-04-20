package hu.afi.ld32.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.controls.Control;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class LivingEntity extends Entity {
    public int health;
    protected Control control;
    protected Vector2 previousLocation;
    public float rotation = 0f;

    public LivingEntity(World world, Vector2 location, float width, float height, int health) {
        super(world, location, width, height);
        this.health = health;
        previousLocation = location.cpy();
    }

    public abstract void accelerate(float x, float y);

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
        this.control.setControlled(this);
    }

    @Override
    public boolean tick(float delta) {
        previousLocation = getLocation().cpy();
        updateLocation(getBody().getPosition().cpy().add(-(bodyDiffX), -(bodyDiffY)));

        return control == null || control.tick();
    }

    public Vector2 getPreviousLocation() {
        return previousLocation;
    }


}
