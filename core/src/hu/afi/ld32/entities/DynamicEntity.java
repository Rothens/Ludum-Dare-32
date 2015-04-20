package hu.afi.ld32.entities;

import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.controls.Control;
import hu.afi.ld32.world.World;

/**
 * Created by zsomkovacs on 2015.04.20..
 */
public abstract class DynamicEntity extends Entity {
    protected Control control;
    protected Vector2 previousLocation;
    public float rotation = 0f;

    public DynamicEntity(World world, Vector2 location, float width, float height) {
        super(world, location, width, height);
        previousLocation = location.cpy();
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
        this.control.setControlled(this);
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
        this.body.setTransform(this.body.getPosition(), rotation / 360.0f * 2.0f * 3.1415927f);
    }

    @Override
    public boolean tick(float delta) {
        previousLocation = getLocation().cpy();
        updateLocation(getBody().getPosition().cpy().add(-(bodyDiffX), -(bodyDiffY)));

        return control == null || control.tick();
    }

    public abstract void accelerate(float x, float y);
    public abstract void accelerateForward(float acc);
    public abstract void accelerateRight(float acc);

    public Vector2 getPreviousLocation() {
        return previousLocation;
    }
}
