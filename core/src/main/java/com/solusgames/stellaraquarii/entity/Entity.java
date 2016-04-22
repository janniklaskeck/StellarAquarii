package com.solusgames.stellaraquarii.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {

    private float x = 0.0f;
    private float y = 0.0f;

    public Entity() {
        setX(0.0f);
        setY(0.0f);
    }

    public void update(float deltaTime) {
    }

    public void render(SpriteBatch batch) {
    }

    public float getY() {
        return y;
    }

    public void setY(float yPos) {
        this.y = yPos;
    }

    public float getX() {
        return x;
    }

    public void setX(float xPos) {
        this.x = xPos;
    }
}
