package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.dune.game.core.buildings.AbstractBuildings;
import com.dune.game.core.units.AbstractUnit;
import com.dune.game.core.units.BattleTank;
import com.dune.game.core.units.Harvester;
import com.dune.game.core.units.Owner;

public class HarvestersController extends ObjectPool<Harvester> {
    private GameController gc;

    @Override
    protected Harvester newObject() {
        return new Harvester(gc);
    }

    public HarvestersController(GameController gc) {
        this.gc = gc;
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }

    public void setup(float x, float y, Owner ownerType) {
        Harvester t = activateObject();
        t.setup(ownerType, x, y);
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
            uploadingResources(activeList.get(i));
        }
        checkPool();
    }

    public void uploadingResources(Harvester harvester){
        if(harvester.position.epsilonEquals(gc.getStorage().getPosition(), 75)) {
            if (harvester.getOwnerType() == gc.getStorage().getOwnerType()) {
                gc.getStorage().setResourcesAmount(gc.getStorage().getResourcesAmount() + harvester.getContainer());
                harvester.setContainer(0);
            }
        }
    }
}