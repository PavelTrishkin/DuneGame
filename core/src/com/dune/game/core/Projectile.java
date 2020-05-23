package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion texture;

    public boolean shooting = true;

    public Projectile(TextureAtlas atlas, Vector2 position) {
        this.position = position;
        this.texture = atlas.findRegion("bullet");
        this.velocity = new Vector2(0, 0);
    }

    public void setup(Vector2 startPosition, float angle) {
        velocity.set(200.0f * MathUtils.cosDeg(angle), 200.0f * MathUtils.sinDeg(angle));
        shooting = false;
    }

    public void update(float dt) {
        if (checkBounds()){
            position.mulAdd(velocity, dt);
        }

        deActivateShoot();
    }

    public void deActivateShoot() { //дезактивация снаряда
        if (position.x > 1300 || position.x < -30) {
            shooting = true;
        }
        if (position.y > 740 || position.y < -30) {
            shooting = true;
        }
    }

    private boolean checkBounds(){//проверка нахождения снаряда в пределах поля
        if (position.x > 1300 || position.x < -30) {
            return false;
        }
        if (position.y > 740 || position.y < -30) {
            return false;
        }
        return true;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x -10, position.y - 10,10, 10, 20, 20, 1, 1, 0);
    }
}
