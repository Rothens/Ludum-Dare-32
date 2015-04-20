package hu.afi.ld32.entities.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import hu.afi.ld32.entities.Fireball;
import hu.afi.ld32.entities.statics.AdobeReader;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;
import hu.afi.ld32.world.WorldRenderer;

/**
 * Created by Rothens on 2015.04.18..
 */
public class PlayerControl extends Control{

    private WorldRenderer worldRenderer;
    private int sixPress = 0;

    public PlayerControl(){
        super();
    }

    @Override
    public boolean tick() {
        if(getControlled() == null) return true;
        float angle = getAngle(getUnprojected());
        getControlled().setRotation(angle - 90);


        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
            sixPress = 0;
            sixPress = 0;
            getControlled().accelerate(-5,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
            sixPress = 0;
            getControlled().accelerate(5,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
            sixPress = 0;
            getControlled().accelerate(0,5);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
            sixPress = 0;
            getControlled().accelerate(0,-5);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            sixPress = 0;
            ParticleEffectPool.PooledEffect effect = TextureHandler.getInstance().domePool.obtain();
            effect.setPosition(getControlled().getLocation().x + getControlled().getWidth()/2, getControlled().getLocation().y + getControlled().getHeight()/2);
            getControlled().getWorld().effects.add(effect);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){
            sixPress = 0;
            ParticleEffectPool.PooledEffect effect = TextureHandler.getInstance().breathPool.obtain();
            effect.setPosition(getControlled().getLocation().x + getControlled().getWidth()/2, getControlled().getLocation().y + getControlled().getHeight()/2);
            System.out.println(angle);
            for( ParticleEmitter e : effect.getEmitters()){

            }
            getControlled().getWorld().effects.add(effect);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)){
            sixPress = 0;
            ParticleEffectPool.PooledEffect effect = TextureHandler.getInstance().boltPool.obtain();
            effect.setPosition(getControlled().getLocation().x + getControlled().getWidth()/2, getControlled().getLocation().y + getControlled().getHeight()/2);
            //getControlled().getWorld().effects.add(effect);

            Fireball fb = new Fireball(getControlled().getWorld(), getControlled().getBody().getPosition(), 200);
            fb.setDirection(angle);
            getControlled().getWorld().handler.addEntity(fb);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)){
            sixPress++;
            if (sixPress == 3) {
                sixPress = 0;
                Vector2 pos = new Vector2(getControlled().getBody().getPosition());
                float ang = (angle/360.f*2*3.1415927f);
                pos.add((float)Math.cos(ang)*3.0f, (float)Math.sin(ang)*3.0f);
                AdobeReader ar = new AdobeReader(getControlled().getWorld(), pos, 1.0f, 1.0f);
                getControlled().getWorld().handler.addEntity(ar);
            }
        }

        if(worldRenderer != null){
            float width = (Gdx.graphics.getWidth()/2f)/ WorldRenderer.tileSize;
            float height =(Gdx.graphics.getHeight()/2f)/WorldRenderer.tileSize;
            float x = MathUtils.clamp(getControlled().getPreviousLocation().x + getControlled().getWidth(), width, worldRenderer.getWidth()-width);
            float y = MathUtils.clamp(getControlled().getPreviousLocation().y + getControlled().getHeight(), height, worldRenderer.getHeight()-height);
            worldRenderer.getCam().position.set(x,y,0);
            worldRenderer.getCam().update();
        }

        //TODO: cast spells

        return true;
    }

    public PlayerControl(WorldRenderer worldRenderer){
        super();
        this.worldRenderer = worldRenderer;
    }

    public WorldRenderer getWorldRenderer() {
        return worldRenderer;
    }

    private Vector3 getUnprojected(){
        return worldRenderer.getCam().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

    public float getAngle(Vector3 target) {
        return MathUtils.radiansToDegrees * MathUtils.atan2( target.y - (getControlled().getLocation().y + getControlled().getHeight()/2f), target.x - (getControlled().getLocation().x + getControlled().getWidth()/2f));
    }


}
