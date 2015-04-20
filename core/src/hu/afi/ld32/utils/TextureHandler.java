package hu.afi.ld32.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Rothens on 2015.04.18..
 */
public final class TextureHandler {
    private static TextureHandler instance;
    private TextureAtlas tileAtlas;
    private TextureAtlas charAtlas;
    private HashMap<String, TextureRegion> sprites;
    private Array<TextureAtlas.AtlasRegion> main;
    private Array<TextureAtlas.AtlasRegion> ee;
    private Array<TextureAtlas.AtlasRegion> em;
    private Array<TextureAtlas.AtlasRegion> eh;



    public ParticleEffectPool domePool;
    public ParticleEffectPool breathPool;
    public ParticleEffectPool boltPool;

    public static TextureHandler getInstance(){
        if(instance == null) instance = new TextureHandler();
        return instance;
    }

    private TextureHandler(){
        init();
    }

    private void init(){
        tileAtlas = new TextureAtlas(Gdx.files.internal("textures/tiles.atlas"));
        charAtlas = new TextureAtlas(Gdx.files.internal("textures/chars.atlas"));
        sprites = new HashMap<String, TextureRegion>();

        TextureRegion tr = new TextureRegion(new Texture(Gdx.files.internal("player.png")));
        sprites.put("player", tr);
        sprites.put("bushes", tileAtlas.findRegion("bushes"));
        sprites.put("grass", tileAtlas.findRegion("grass"));
        sprites.put("road", tileAtlas.findRegion("road"));
        sprites.put("rock", tileAtlas.findRegion("rock"));
        sprites.put("sand", tileAtlas.findRegion("sand"));
        sprites.put("wood", tileAtlas.findRegion("wood"));

        main = charAtlas.findRegions("main");
        ee = charAtlas.findRegions("ee");
        em = charAtlas.findRegions("em");
        eh = charAtlas.findRegions("eh");
        ParticleEffect dome = new ParticleEffect();
        ParticleEffect breath = new ParticleEffect();
        ParticleEffect bolt = new ParticleEffect();
        tr = new TextureRegion(new Texture(Gdx.files.internal("textures/wall.png")));
        sprites.put("entity_wall", tr);
        tr = new TextureRegion(new Texture(Gdx.files.internal("textures/tree.png")));
        sprites.put("entity_tree", tr);
        tr = new TextureRegion(new Texture(Gdx.files.internal("textures/stone.png")));
        sprites.put("entity_stone", tr);
        tr = new TextureRegion(new Texture(Gdx.files.internal("textures/fireball.png")));
        sprites.put("entity_fireball", tr);

        dome.load(Gdx.files.internal("particlez/lightningdome.p"), Gdx.files.internal(""));
        dome.scaleEffect(0.03f);
        domePool = new ParticleEffectPool(dome, 1, 10);

        breath.load(Gdx.files.internal("particlez/icebreath.p"), Gdx.files.internal(""));
        breath.scaleEffect(0.03f);
        breathPool = new ParticleEffectPool(breath, 1, 10);

        bolt.load(Gdx.files.internal("particlez/fireball.p"), Gdx.files.internal(""));
        bolt.scaleEffect(0.03f);
        boltPool = new ParticleEffectPool(bolt, 1, 10);
    }

    public TextureAtlas.AtlasRegion getMain(int i){
        return main.get(i);
    }

    public TextureAtlas.AtlasRegion getEe(int i){
        return ee.get(i);
    }

    public TextureAtlas.AtlasRegion getEm(int i){
        return em.get(i);
    }
    public TextureAtlas.AtlasRegion getEh(int i){
        return eh.get(i);
    }


    public TextureRegion getSprite(String name){
        return sprites.get(name.toLowerCase());
    }
}
