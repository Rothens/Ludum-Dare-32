package hu.afi.ld32.entities.controls;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;
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
        if(getControlled() == null) return false;

        if(Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT)){
            //getControlled().moveLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT)){
           //getControlled().moveRight();
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
