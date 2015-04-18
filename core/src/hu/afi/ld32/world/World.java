package hu.afi.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Rothens on 2015.04.18..
 */
public class World {

    public Tile[][] map;
    public int width;
    public int height;
    public ArrayList<Entity> entities;
    private com.badlogic.gdx.physics.box2d.World phys;


    public World(){
        entities = new ArrayList<Entity>();
        phys = new com.badlogic.gdx.physics.box2d.World(new Vector2(0,0), true);
        width = 0;
        height = 0;
    }

    public com.badlogic.gdx.physics.box2d.World getPhys() {
        return phys;
    }

    public void update(){
        phys.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }
}
