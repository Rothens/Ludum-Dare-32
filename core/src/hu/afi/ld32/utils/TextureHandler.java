package hu.afi.ld32.utils;

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

    public static TextureHandler getInstance(){
        if(instance == null) instance = new TextureHandler();
        return instance;
    }

    private TextureHandler(){
        init();
    }

    private void init(){

    }

    public TextureRegion getSprite(String name){
        return sprites.get(name.toLowerCase());
    }
}
