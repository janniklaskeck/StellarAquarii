package com.solusgames.stellaraquarii.world;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.solusgames.stellaraquarii.StellarAquariiMain;

public class StarSystem {

    private int width;
    private int height;
    private String systemName;
    private Body sunBody;

    public StarSystem(final String name, final int width, final int height) {
        BodyDef sunDef = new BodyDef();
        sunDef.position.set(width / 2, height / 2);
        sunDef.type = BodyType.KinematicBody;
        sunBody = StellarAquariiMain.world.createBody(sunDef);
        CircleShape circle = new CircleShape();
        circle.setRadius(500.0f);

        sunBody.createFixture(circle, 0.0f);
        circle.dispose();
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the systemName
     */
    public String getSystemName() {
        return systemName;
    }

}
