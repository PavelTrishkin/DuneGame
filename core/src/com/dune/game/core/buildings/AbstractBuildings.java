package com.dune.game.core.buildings;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dune.game.core.Assets;
import com.dune.game.core.GameController;
import com.dune.game.core.GameObject;
import com.dune.game.core.Poolable;
import com.dune.game.core.units.Owner;

public abstract class AbstractBuildings extends GameObject implements Poolable {

    protected TextureRegion texture;
    protected int hp;
    protected int hpMax;
    protected Owner ownerType;
    protected TextureRegion progressbarTexture;

    @Override
    public boolean isActive() {
        return hp > 0;
    }

    public Owner getOwnerType() {
        return ownerType;
    }

    public AbstractBuildings(GameController gc) {
        super(gc);
        this.progressbarTexture = Assets.getInstance().getAtlas().findRegion("progressbar");
    }

    public abstract void setup(Owner ownerType, float x, float y);



    public void render(SpriteBatch batch){
        batch.draw(texture, position.x - 60, position.y - 60, 75, 68, 150, 136, 1, 1, -30);
        renderBuildingGui(batch);
    }

    public void renderBuildingGui(SpriteBatch batch) {
        if (hp < hpMax) {
            batch.setColor(0.2f, 0.2f, 0.0f, 1.0f);
            batch.draw(progressbarTexture, position.x - 32, position.y + 30, 64, 12);
            batch.setColor(0.0f, 1.0f, 0.0f, 1.0f);
            float percentage = (float) hp / hpMax;
            batch.draw(progressbarTexture, position.x - 30, position.y + 32, 60 * percentage, 8);
            batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
