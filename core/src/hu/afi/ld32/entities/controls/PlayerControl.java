package hu.afi.ld32.entities.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g3d.particles.emitters.Emitter;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import hu.afi.ld32.utils.TextureHandler;
import hu.afi.ld32.world.World;
import hu.afi.ld32.world.WorldRenderer;

/**
 * Created by Rothens on 2015.04.18..
 */
public class PlayerControl extends Control{

    private WorldRenderer worldRenderer;

    public PlayerControl(){
        super();
    }

    @Override
    public boolean tick() {
        if(getControlled() == null) return true;
        float angle = getAngle(getUnprojected());
        getControlled().rotation = angle -90;


        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
            getControlled().accelerate(-5,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
            getControlled().accelerate(5,0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)){
            getControlled().accelerate(0,5);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){
            getControlled().accelerate(0,-5);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            ParticleEffectPool.PooledEffect effect = TextureHandler.getInstance().domePool.obtain();
            effect.setPosition(getControlled().getLocation().x + getControlled().getWidth()/2, getControlled().getLocation().y + getControlled().getHeight()/2);
            getControlled().getWorld().effects.add(effect);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)){

            ParticleEffectPool.PooledEffect effect = TextureHandler.getInstance().breathPool.obtain();
            effect.setPosition(getControlled().getLocation().x + getControlled().getWidth()/2, getControlled().getLocation().y + getControlled().getHeight()/2);
            System.out.println(angle);
            for( ParticleEmitter e : effect.getEmitters()){

            }
            getControlled().getWorld().effects.add(effect);
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
