package hu.afi.ld32.utils;

import com.badlogic.gdx.physics.box2d.Filter;

/**
 * Created by Rothens on 2015.04.21..
 */
public class FilterUtil {
    public static final short PLAYER = 0x0001;
    public static final short ENEMY = 0x0002;
    public static final short ENV = 0x0004;

    public static final Filter PLAYERFILTER = new Filter();
    public static final Filter ENEMYFILTER = new Filter();
    public static final Filter ENVFILTER = new Filter();

    static {
        PLAYERFILTER.categoryBits = PLAYER;
        PLAYERFILTER.maskBits = ENEMY | ENV;

        ENEMYFILTER.categoryBits = ENEMY;
        ENEMYFILTER.maskBits = PLAYER | ENV;

        ENVFILTER.categoryBits = ENV;
        ENVFILTER.maskBits = PLAYER | ENEMY;
    }


}
