package hu.afi.ld32.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import hu.afi.ld32.entities.Player;
import hu.afi.ld32.entities.controls.PlayerControl;
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
    private static GameScreen instance;

    public GameScreen(){
        Gdx.input.setInputProcessor(null);
        world = new World();
        renderer = new WorldRenderer(world);

        MapLoader loader = new MapLoader();
        loader.loadMap("testmap.png", world);
        Player p = new Player(world,new Vector2(world.width/2f, world.height/2f), 100);
        p.setControl(new PlayerControl(renderer));
        world.handler.setPlayer(p);
        world.handler.addEntity(p);
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
        Gdx.gl.glClearColor(135f / 255f, 206f / 255f, 250f / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.update(delta);
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
