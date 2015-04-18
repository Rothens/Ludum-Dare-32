package hu.afi.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Rothens on 2015.04.18..
 */
public class WorldRenderer {
    private World world;
    private OrthographicCamera cam;
    private int tileSize = 64;
    private ShapeRenderer debugRenderer = new ShapeRenderer();

    public WorldRenderer(World world){
        this.world = world;
        cam = new OrthographicCamera(Gdx.graphics.getWidth()/16f, Gdx.graphics.getHeight()/16f);
        cam.position.set((world.width * tileSize)/2f, (world.height * tileSize)/2f, 0);
        cam.update();

    }

    public void resize(int w, int h){
        Vector3 old = new Vector3(cam.position);
        cam = new OrthographicCamera(w/16f, h/16f);
        cam.position.set(old);
        cam.update();
    }

    public void render(float delta){
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int y = 0; y < world.height; y++){
            for(int x = 0; x < world.width; x++){
                switch(world.map[x][y]){
                    case GRASS:
                        debugRenderer.setColor(Color.GREEN);
                        break;
                    case DIRT:
                        debugRenderer.setColor(Color.MAROON);
                        break;
                    case SAND:
                        debugRenderer.setColor(Color.YELLOW);
                        break;
                }
                debugRenderer.rect(x,y,1f,1f);

            }
        }
        debugRenderer.end();
    }

    public void setWorld(World world){
        this.world = world;
    }
}
