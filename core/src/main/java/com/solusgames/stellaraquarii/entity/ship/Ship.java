package com.solusgames.stellaraquarii.entity.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.solusgames.stellaraquarii.entity.Entity;

public class Ship extends Entity {

    public static final int MAXSHIPWIDTH = 100;
    public static final int MAXSHIPHEIGHT = 100;

    private String shipName;
    private int[][] shipStructure;

    public Ship(final FileHandle file) {
        super();
        loadShipFromFile(file);
        createPhysicsBody();
    }

    @Override
    public void update(float deltaTime) { // NOSONAR

    }

    @Override
    public void render(SpriteBatch batch) { // NOSONAR

    }

    private void loadShipFromFile(final FileHandle file) {
        Gdx.app.log("Ship", "load ship from file: " + file.name());
    }

    private void createPhysicsBody() {
        Gdx.app.log("Ship", "create Physics Body for Ship: " + getShipName());
    }

    public String getShipName() {
        return shipName;
    }

    public int[][] getShipStructure() {
        return shipStructure;
    }

}
