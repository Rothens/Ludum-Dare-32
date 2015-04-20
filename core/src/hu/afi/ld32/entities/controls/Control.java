package hu.afi.ld32.entities.controls;

import hu.afi.ld32.entities.DynamicEntity;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class Control {
    private DynamicEntity controlled;

    public Control(){

    }

    public DynamicEntity getControlled() {
        return controlled;
    }

    public void setControlled(DynamicEntity controlled){
        this.controlled = controlled;
    }

    public abstract boolean tick();
}
