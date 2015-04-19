package hu.afi.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import hu.afi.ld32.entities.Entity;

/**
 * Created by Rothens on 2015.04.18..
 */
public class WorldRenderer {
    private World world;
    private OrthographicCamera cam;
    public static float tileSize = 64f;
    private ShapeRenderer debugRenderer = new ShapeRenderer();
    private SpriteBatch batch;
    private Box2DDebugRenderer physRender = new Box2DDebugRenderer();

    public WorldRenderer(World world){
        this.world = world;
        cam = new OrthographicCamera(Gdx.graphics.getWidth()/tileSize, Gdx.graphics.getHeight()/tileSize);
        cam.position.set((world.width) / 2f, (world.height) / 2f, 0);
        cam.update();
        batch = new SpriteBatch();

    }

    public void resize(int w, int h){
        Vector3 old = new Vector3(cam.position);
        cam = new OrthographicCamera(w/tileSize, h/tileSize);
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

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        for(int i = world.effects.size -1; i >= 0; i--){
            ParticleEffectPool.PooledEffect effect = world.effects.get(i);
            effect.draw(batch, delta);
            if(effect.isComplete()) {
                effect.free();
                world.effects.removeIndex(i);
            }
        }

        for(Entity e : world.handler.getEntities()) {
            e.render(batch);
        }



        batch.end();
        //physRender.render(world.getPhys(), cam.combined); //uncomment for phys debug rendering.

    }


    public OrthographicCamera getCam() {
        return cam;
    }

    public int getWidth(){
        if(world == null) return 0;
        return world.width;
    }

    public int getHeight(){
        if(world == null) return 0;
        return world.height;
    }

    public void setWorld(World world){
        this.world = world;
    }
}
