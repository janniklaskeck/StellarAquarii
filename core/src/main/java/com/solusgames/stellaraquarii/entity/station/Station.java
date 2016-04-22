package com.solusgames.stellaraquarii.entity.station;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.solusgames.stellaraquarii.entity.Entity;

public class Station extends Entity {

    private String stationName;

    public Station(final FileHandle file) {
        super();
    }

    @Override
    public void update(final float deltaTime) { // NOSONAR

    }

    @Override
    public void render(final SpriteBatch spriteBatch) { // NOSONAR

    }

    public String getStationName() {
        return stationName;
    }
}
