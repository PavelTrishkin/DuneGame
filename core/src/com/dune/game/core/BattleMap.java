package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BattleMap {
    private TextureRegion grassTexture;
    private TextureRegion resourceTexture;
    private Vector2 resourcePosition;

    public BattleMap() {
        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");
        this.resourceTexture = Assets.getInstance().getAtlas().findRegion("veton");
        this.resourcePosition = new Vector2(200, 500);
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                batch.draw(grassTexture, i * 80, j * 80);
            }

        }
        batch.draw(resourceTexture, resourcePosition.x - 17, resourcePosition.y - 16, 17,16,35,33,1,1,0 );
    }

    public Vector2 respawnResourcePosition(){
        return resourcePosition.set(MathUtils.random(50,1100),MathUtils.random(50,600) );
    }

    public Vector2 getResourcePosition() {
        return resourcePosition;
    }
}
