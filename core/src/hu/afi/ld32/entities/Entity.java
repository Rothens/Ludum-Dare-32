package hu.afi.ld32.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import hu.afi.ld32.world.World;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class Entity {

    protected Vector2 location;
    protected Body body;
    protected float width;
    protected float height;
    protected float bodyWidth;
    protected float bodyHeight;
    protected World world;
    protected float bodyDiffX;
    protected float bodyDiffY;

    protected String type;

    public Entity(World world, Vector2 location, float width, float height){
        this.location = location;
        this.width = width;
        this.height = height;
        this.bodyDiffX = width/2;
        this.bodyDiffY = height/2;
        this.world = world;
        createBody();
        this.body.getFixtureList().get(0).setUserData(this);
    }

    public World getWorld() {
        return world;
    }

    protected void createBody(){

    }

    public String getType() {
        return type;
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
            body.setTransform(location.cpy().add(bodyDiffX, bodyDiffY), body.getAngle());
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

    /**
     * Processes the changes for the entity each render cycle
     * Returns false if the entity should be destroyed - TODO @Rothens: confirm this pls
     * @param delta
     * @return boolean
     */
    public abstract boolean tick(float delta);

    public abstract TextureRegion getTexture();

    /**
     * Renders the entity's texture by default, override if neccessary
     * @param batch
     */
    public void render(SpriteBatch batch) {
        batch.draw(getTexture(), location.x, location.y, width, height);
    }
}
