package hu.afi.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Created by Rothens on 2015.04.18..
 */
public class World {

    public Tile[][] map;
    public int width;
    public int height;

    public World(String filename){
        FileHandle mapFile = Gdx.files.internal(filename);
        try {
            BufferedImage read = ImageIO.read(mapFile.file());
            width = read.getWidth();
            height = read.getHeight();
            map = new Tile[width][height];

            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    int r = read.getRGB(x,y) & 0xFF;
                    if(r < 85){
                        map[x][y] = Tile.SAND;
                    } else if (r < 170){
                        map[x][y] = Tile.DIRT;
                    } else {
                        map[x][y] = Tile.GRASS;
                    }
                }
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
