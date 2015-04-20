package hu.afi.ld32.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.statics.StoneEntity;
import hu.afi.ld32.entities.statics.TreeEntity;
import hu.afi.ld32.entities.statics.WallEntity;
import hu.afi.ld32.world.EntityHandler;
import hu.afi.ld32.world.Tile;
import hu.afi.ld32.world.World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Created by zsomkovacs on 2015.04.18..
 */
public class MapLoader {
    public void loadMap(String fileName, World world) {
        FileHandle mapFile = Gdx.files.internal(fileName);

        try {
            BufferedImage read = ImageIO.read(mapFile.file());
            world.handler = new EntityHandler(world);
            world.width = read.getWidth();
            world.height = read.getHeight();
            world.map = new Tile[world.width][world.height];

            for(int y = 0; y < world.height; y++){
                for(int x = 0; x < world.width; x++){
                    int r = read.getRGB(x,y) & 0xFF;
                    if(r < 85){
                        world.map[x][y] = Tile.SAND;
                    } else if (r < 170){
                        world.map[x][y] = Tile.ROCK;
                    } else {
                        world.map[x][y] = Tile.GRASS;
                    }
                }
            }
            world.regenPhys();

            for (int x=0; x<11; x++) {
                WallEntity wallEntity = new WallEntity(world, new Vector2(world.width/2f - x + 5, world.height/2f + 4), 1f, 1f);
                world.handler.addEntity(wallEntity);
                if (x%2 == 0) {
                    TreeEntity treeEntity = new TreeEntity(world, new Vector2(world.width/2f - x + 5, world.height/2f + 5));
                    world.handler.addEntity(treeEntity);
                } else {
                    StoneEntity stoneEntity = new StoneEntity(world, new Vector2(world.width/2f - x + 5, world.height/2f + 5), 1f, 1f);
                    world.handler.addEntity(stoneEntity);
                }
            }

        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
