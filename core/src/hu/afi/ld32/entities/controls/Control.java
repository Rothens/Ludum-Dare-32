package hu.afi.ld32.entities.controls;

import hu.afi.ld32.entities.LivingEntity;

/**
 * Created by Rothens on 2015.04.18..
 */
public abstract class Control {
    private LivingEntity controlled;

    public Control(){

    }

    public LivingEntity getControlled() {
        return controlled;
    }

    public void setControlled(LivingEntity controlled){
        this.controlled = controlled;
    }

    public abstract boolean tick();
}
