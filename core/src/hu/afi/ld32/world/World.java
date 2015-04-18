package hu.afi.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rothens on 2015.04.18..
 */
public class World {

    public Tile[][] map;
    public int width;
    public int height;
    public EntityHandler handler;
    private com.badlogic.gdx.physics.box2d.World phys;


    public World(){
        phys = new com.badlogic.gdx.physics.box2d.World(new Vector2(0,0), true);
        width = 0;
        height = 0;
    }

    public com.badlogic.gdx.physics.box2d.World getPhys() {
        return phys;
    }

    public void update(float delta){
        handler.tick(delta);
        phys.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }
}
