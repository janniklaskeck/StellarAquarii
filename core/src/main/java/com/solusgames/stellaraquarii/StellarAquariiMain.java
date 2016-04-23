package com.solusgames.stellaraquarii;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class StellarAquariiMain extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    World world = new World(new Vector2(0, -10), true);
    Box2DDebugRenderer dRenderer;
    OrthographicCamera oCam;
    float x = 100.0f;
    float y = 300.0f;
    Body body;

    @Override
    public void create() {

        batch = new SpriteBatch();
        FileHandle handle = Gdx.files.internal("badlogic.jpg");
        img = new Texture(handle);
        oCam = new OrthographicCamera(400, 300);
        oCam.position.set(oCam.viewportWidth / 2f, oCam.viewportHeight / 2, 0);
        oCam.update();
        createPhysics();
        createGround();

    }

    private void createGround() {
        BodyDef groundDef = new BodyDef();
        groundDef.position.set(0, 10);
        world.createBody(groundDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(oCam.viewportWidth, 10.0f);
        body.createFixture(box, 0.0f);
        box.dispose();

    }

    @Override
    public void render() {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        oCam.update();
        dRenderer.render(world, oCam.combined);

        world.step(1 / 60f, 6, 2);
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.applyLinearImpulse(new Vector2(100, 0), body.getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.applyLinearImpulse(new Vector2(-100, 0), body.getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && !body.isAwake()) {
            body.applyLinearImpulse(new Vector2(0, 100), body.getWorldCenter(), true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

    }

    private void createPhysics() {
        Box2D.init();
        dRenderer = new Box2DDebugRenderer();
        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;
        def.position.set(x, y);
        body = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(10f);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = circle;
        fDef.density = 1.0f;
        fDef.friction = 0.7f;
        fDef.restitution = 0.2f;
        body.createFixture(fDef);
        circle.dispose();
    }
}
