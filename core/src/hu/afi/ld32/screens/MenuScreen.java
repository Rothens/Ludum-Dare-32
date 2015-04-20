package hu.afi.ld32.screens;

/**
 * Created by Robin on 2015.04.18..
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import hu.afi.ld32.LD32Game;
import hu.afi.ld32.screens.GameScreen;
import hu.afi.ld32.utils.TextureHandler;


public class MenuScreen implements Screen {
    Skin skin;
    Stage stage;
    SpriteBatch batch;

    Game g;
    public MenuScreen(Game g){
        create();
        this.g=g;
    }

    public MenuScreen(){
        create();
    }
    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();
        bfont.scale(1);
        skin.add("default", bfont);
        //skin.add("pentek", TextureHandler.getInstance().getSprite("bg"));

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = new TextureRegionDrawable(TextureHandler.getInstance().getSprite("button1"));
        textButtonStyle.down = new TextureRegionDrawable(TextureHandler.getInstance().getSprite("button2"));
        //textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = new TextureRegionDrawable(TextureHandler.getInstance().getSprite("button3"));

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        Image backGround = new Image(TextureHandler.getInstance().getSprite("bg"));
        final TextButton playButton=new TextButton("PLAY",textButtonStyle);
        final TextButton playButton2=new TextButton("PLAY",textButtonStyle);
        final TextButton playButton3=new TextButton("PLAY",textButtonStyle);
        final TextButton playButton4=new TextButton("PLAY",textButtonStyle);
        final TextButton settingsButton=new TextButton("SETTINGS",textButtonStyle);
        playButton.setPosition(100, 200);
        settingsButton.setPosition(400, 200);
     //   stage.addActor(backGround);

    //    stage.addActor(settingsButton);

        stage.addActor(playButton);
        playButton2.setPosition(100, 300);
        stage.addActor(playButton2);
        playButton3.setPosition(100, 400);
        stage.addActor(playButton3);
        playButton4.setPosition(100, 100);
        stage.addActor(playButton4);

        playButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                LD32Game.instance.setScreen(new GameScreen());
                //    g.setScreen( new GameScreen());

            }
        });

        playButton2.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                LD32Game.instance.setScreen(new GameScreen());
                //    g.setScreen( new GameScreen());

            }
        });

        playButton3.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                LD32Game.instance.setScreen(new GameScreen());
                //    g.setScreen( new GameScreen());

            }
        });

        playButton4.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                LD32Game.instance.setScreen(new GameScreen());
                //    g.setScreen( new GameScreen());

            }
        });



        settingsButton.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                //System.out.println("Clicked! Is checked: " + button.isChecked());
                settingsButton.setText("Entering settings");
                //    g.setScreen( new SettingsScreen());

            }
        });

    }

    public void render (float delta) {
        Gdx.gl.glClearColor(0.6f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize (int width, int height) {
        // TODO: uncomment
        //stage.setViewport(new StretchViewport(width, height));
    }

    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
}
