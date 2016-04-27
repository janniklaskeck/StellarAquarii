package com.solusgames.stellaraquarii;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.solusgames.stellaraquarii.entity.ship.Ship;
import com.solusgames.stellaraquarii.input.SAInputAdapter;
import com.solusgames.stellaraquarii.world.StarSystem;

public class StellarAquariiMain extends ApplicationAdapter {

    private SpriteBatch spriteBatch;
    private SpriteBatch hudBatch;
    public static final World world = new World(new Vector2(0, 0), true);
    Box2DDebugRenderer dRenderer;
    OrthographicCamera oCam;

    Ship test;

    @Override
    public void create() {
        test = new Ship(Gdx.files.internal("ship1/body.json"));
        spriteBatch = new SpriteBatch();
        hudBatch = new SpriteBatch();
        oCam = new OrthographicCamera(960, 540);
        oCam.position.set(oCam.viewportWidth / 2f, oCam.viewportHeight / 2, 0);
        
        oCam.update();
        initBox2D();
        new StarSystem("Aquarii 1", 2560, 1440);
        Gdx.input.setInputProcessor(new SAInputAdapter(test, oCam));
    }

    @Override
    public void render() {
        update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        oCam.position.set(test.getBody().getPosition(), 0);
        oCam.update();
        spriteBatch.setProjectionMatrix(oCam.combined);
        spriteBatch.begin();
        test.render(spriteBatch);
        spriteBatch.end();

        hudBatch.begin();
        test.renderHUD(hudBatch);
        hudBatch.end();
        dRenderer.render(world, oCam.combined);

        world.step(1 / 60f, 6, 2);
    }

    private void update() {
        test.update(Gdx.graphics.getDeltaTime());
    }

    private void initBox2D() {
        Box2D.init();
        dRenderer = new Box2DDebugRenderer();

    }
}
