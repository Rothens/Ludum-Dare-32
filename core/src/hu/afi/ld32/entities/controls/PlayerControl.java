package hu.afi.ld32.entities.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
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

        if(worldRenderer != null){
            float width = (Gdx.graphics.getWidth()/2f)/ WorldRenderer.tileSize;
            float height =(Gdx.graphics.getHeight()/2f)/WorldRenderer.tileSize;
            float x = MathUtils.clamp(getControlled().getLocation().x, width, worldRenderer.getWidth()-width);
            float y = MathUtils.clamp(getControlled().getLocation().y, height, worldRenderer.getHeight()-height);
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


}
