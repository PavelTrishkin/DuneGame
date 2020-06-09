package com.dune.game.core.buildings;

import com.dune.game.core.Assets;
import com.dune.game.core.GameController;
import com.dune.game.core.units.Owner;

public class Storage extends AbstractBuildings {

    private int resourcesAmount;

    public int getResourcesAmount() {
        return resourcesAmount;
    }

    public void setResourcesAmount(int resourcesAmount) {
        this.resourcesAmount = resourcesAmount;
    }

    public Storage(GameController gc) {
        super(gc);
        this.texture = Assets.getInstance().getAtlas().findRegion("storage");
        this.hpMax = 1000;
        this.resourcesAmount = 50;
    }

    @Override
    public void setup(Owner ownerType, float x, float y) {
        this.position.set(x, y);
        this.hp = this.hpMax;
        this.ownerType = ownerType;
    }
}
