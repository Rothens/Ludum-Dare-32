package hu.afi.ld32.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Rothens on 2015.04.18..
 */
public class World {

    public Array<ParticleEffectPool.PooledEffect> effects;
    public Tile[][] map;
    public int width;
    public int height;
    public EntityHandler handler;
    private com.badlogic.gdx.physics.box2d.World phys;


    public World(){
        width = 0;
        height = 0;
        effects = new Array<ParticleEffectPool.PooledEffect>();
    }

    public com.badlogic.gdx.physics.box2d.World getPhys() {
        return phys;
    }

    public void update(float delta){
        handler.tick(delta);
        if(phys != null)
            phys.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    public void regenPhys(){
        if(phys != null)
            phys.dispose();
        phys = new com.badlogic.gdx.physics.box2d.World(new Vector2(0,0), true);
        addEdgeShape(0,0,width,0);
        addEdgeShape(0,0,0,height);
        addEdgeShape(width,0,width, height);
        addEdgeShape(0,height,width,height);

    }

    void addEdgeShape(float x1, float y1, float x2, float y2){
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        float bx = (x1+x2)/2f;
        float by = (y1+y2)/2f;
        float lx = (x1-x2)/2f;
        float ly = (y1-y2)/2f;
        bd.position.set(bx , by);
        bd.angle = 0f;
        Body body = phys.createBody(bd);
        EdgeShape es = new EdgeShape();
        es.set(-lx, -ly, lx, ly);
        body.createFixture(es, 1f);
        es.dispose();

    }
}
