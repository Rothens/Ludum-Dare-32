package hu.afi.ld32.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class Entity {

    protected Vector2 location;
    protected Body body;
    protected float width;
    protected float height;

    public Entity(Vector2 location){
        this.location = location;
        createBody();
    }

    protected void createBody(){

    }

    /**
     * Updates the location of the object without setting the body as well.
     * @param location
     */
    protected void updateLocation(Vector2 location){
        this.location = location;
    }

    public void setLocation(Vector2 location){
        this.location = location;
        if(body != null){
            body.setTransform(location.cpy().add(width/2, height/2), body.getAngle());
        }
    }

    public Vector2 getLocation() {
        return location;
    }

    public Body getBody() {
        return body;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public abstract boolean tick(float delta);

    public abstract TextureRegion getTexture();
}
