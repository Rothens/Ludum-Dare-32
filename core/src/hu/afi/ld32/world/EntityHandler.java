package hu.afi.ld32.world;

import com.badlogic.gdx.physics.box2d.Body;
import hu.afi.ld32.entities.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rothens on 2015.04.18..
 */
public class EntityHandler {
    private List<Entity> entities;
    private List<Entity> toAdd;
    private World world;
    private Entity player;
    private final Object lock = new Object();

    public EntityHandler(World world){
        this.world = world;
        entities = Collections.synchronizedList(new ArrayList<Entity>());
        toAdd = new ArrayList<Entity>();

    }

    public Entity addEntity(Entity e){
        synchronized (lock) {
            toAdd.add(e);
        }
        return e;
    }

    public void tick(float delta){
        Iterator<Entity> i = getEntitiesIterator();
        while(i.hasNext()){
            Entity e = i.next();
            if(!e.tick(delta)){
                removeEntityBody(e);
                i.remove();
            }
        }
        synchronized (lock){
            for(Entity e : toAdd){
                entities.add(e);
            }
            toAdd.clear();
        }

    }

    private void removeEntityBody(Entity e){
        Body b = e.getBody();
        if(b != null){
            world.getPhys().destroyBody(b);
        }
    }

    public Iterator<Entity> getEntitiesIterator(){
        return entities.iterator();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public Entity getPlayer() {
        return player;
    }

    public void setPlayer(Entity player) {
        this.player = player;
    }
}