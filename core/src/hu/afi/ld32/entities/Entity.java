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



    public abstract boolean tick();
    public abstract TextureRegion getTexture();
}
