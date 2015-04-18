package hu.afi.ld32;

import com.badlogic.gdx.Game;
import hu.afi.ld32.screens.MenuScreen;

public class LD32Game extends Game {
	public static LD32Game instance;
	@Override
	public void create() {

		instance = this;
		setScreen(new MenuScreen(this));
	}

	/*SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
}
