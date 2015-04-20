package hu.afi.ld32.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Rothens on 2015.04.20..
 */
public class AnimUtil {
    public static final int[] animFrames = {2,0,1,0,2,3,4,3 };
    public static final float frameLength = .25f;

    public static int getFrameId(float delta){
        int fr = (int)( delta / frameLength);
        fr %= animFrames.length;
        return animFrames[fr];
    }
}
