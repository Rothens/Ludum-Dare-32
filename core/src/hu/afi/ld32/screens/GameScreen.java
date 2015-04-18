package hu.afi.ld32.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import hu.afi.ld32.utils.MapLoader;
import hu.afi.ld32.world.EntityHandler;
import hu.afi.ld32.world.World;
import hu.afi.ld32.world.WorldRenderer;

import java.util.Map;

/**
 * Created by Rothens on 2015.04.18..
 */
public class GameScreen implements Screen {

    private World world;
    private WorldRenderer renderer;
    private EntityHandler entityHandler;
    private static GameScreen instance;

    public GameScreen(){
        world = new World();
        renderer = new WorldRenderer(world);
        entityHandler = new EntityHandler(world);

        MapLoader loader = new MapLoader();
        loader.loadMap("testmap.png", world, entityHandler);
        instance = this;

    }

    public static GameScreen getInstance(){
        return instance;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(135f/255f,206f/255f,250f/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        entityHandler.tick(delta);
        renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
