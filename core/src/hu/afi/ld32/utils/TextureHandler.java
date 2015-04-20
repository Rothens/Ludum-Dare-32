package hu.afi.ld32.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

/**
 * Created by Rothens on 2015.04.18..
 */
public final class TextureHandler {
    private static TextureHandler instance;
    private TextureAtlas atlas;
    private HashMap<String, TextureRegion> sprites;

    public ParticleEffectPool domePool;
    public ParticleEffectPool breathPool;

    public static TextureHandler getInstance(){
        if(instance == null) instance = new TextureHandler();
        return instance;
    }

    private TextureHandler(){
        init();
    }

    private void init(){
        sprites = new HashMap<String, TextureRegion>();

        TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("player.png")));
        sprites.put("player", tr);
        ParticleEffect dome = new ParticleEffect();
        ParticleEffect breath = new ParticleEffect();
        tr = new TextureRegion(new Texture(Gdx.files.internal("textures/wall.png")));
        sprites.put("entity_wall", tr);
        tr = new TextureRegion(new Texture(Gdx.files.internal("textures/tree.png")));
        sprites.put("entity_tree", tr);
        tr = new TextureRegion(new Texture(Gdx.files.internal("textures/stone.png")));
        sprites.put("entity_stone", tr);

        dome.load(Gdx.files.internal("particlez/lightningdome.p"), Gdx.files.internal(""));
        dome.scaleEffect(0.03f);
        domePool = new ParticleEffectPool(dome, 1, 10);

        breath.load(Gdx.files.internal("particlez/icebreath.p"), Gdx.files.internal(""));
        breath.scaleEffect(0.03f);
        breathPool = new ParticleEffectPool(breath, 1, 10);
    }

    public TextureRegion getSprite(String name){
        return sprites.get(name.toLowerCase());
    }
}
