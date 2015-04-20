package hu.afi.ld32.world;

/**
 * Created by Rothens on 2015.04.18..
 */
public enum Tile {
    GRASS,
    ROCK,
    SAND;
    private final String texture;

    private Tile(String texture){
        this.texture = texture;
    }

    private Tile(){
        this.texture = this.name().toLowerCase();
    }

    public String getTexture() {
        return texture;
    }
}
