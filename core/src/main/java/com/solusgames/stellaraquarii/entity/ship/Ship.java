package com.solusgames.stellaraquarii.entity.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.solusgames.stellaraquarii.StellarAquariiMain;
import com.solusgames.stellaraquarii.entity.Entity;

import aurelienribon.bodyeditor.BodyEditorLoader;

public class Ship extends Entity {

    private static final String TAG = "SHIP";

    public static final int MAXSHIPWIDTH = 100;
    public static final int MAXSHIPHEIGHT = 100;

    private String shipName;
    private int[][] shipStructure;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean moveUp;
    private boolean moveDown;
    private boolean strafeLeft;
    private boolean strafeRight;

    private Body body;
    private Texture tex;

    public Ship(final FileHandle file) {
        super();
        loadShipFromFile(file);
        tex = new Texture(Gdx.files.internal("ship1/ship1.png"));
    }

    @Override
    public void update(float deltaTime) {
        if (isMoveRight()) {
            body.applyTorque(-1000000, true);
        } else if (isMoveLeft()) {
            body.applyTorque(1000000, true);
        }

        if (isMoveDown()) {
            float y1 = (float) -Math.cos(body.getAngle()) * 5000;
            float x1 = (float) Math.sin(body.getAngle()) * 5000;
            body.applyLinearImpulse(new Vector2(x1, y1), body.getWorldCenter(), true);
        } else if (isMoveUp()) {
            float y1 = (float) Math.cos(body.getAngle()) * 5000;
            float x1 = (float) -Math.sin(body.getAngle()) * 5000;
            body.applyLinearImpulse(new Vector2(x1, y1), body.getWorldCenter(), true);
        }

        if (isStrafeLeft()) {
            float y1 = (float) -Math.cos(body.getAngle()) * 5000;
            float x1 = (float) Math.sin(body.getAngle()) * 5000;
            body.applyLinearImpulse(new Vector2(x1, y1).rotate90(-1), body.getWorldCenter(), true);
        } else if (isStrafeRight()) {
            float y1 = (float) -Math.cos(body.getAngle()) * 5000;
            float x1 = (float) Math.sin(body.getAngle()) * 5000;
            body.applyLinearImpulse(new Vector2(x1, y1).rotate90(1), body.getWorldCenter(), true);
        }
    }

    @Override
    public void render(SpriteBatch batch) { // NOSONAR
        float w = tex.getWidth() / 5.0f;
        float h = tex.getHeight() / 5.0f;
        float xpos = body.getPosition().x - w / 2.0f;
        float ypos = body.getPosition().y - h / 2.0f;
        float rot = body.getAngle() * MathUtils.radiansToDegrees;
        batch.draw(tex, xpos, ypos, w / 2.0f, h / 2.0f, w, h, 1.0f, 1.0f, rot, 0, 0, tex.getWidth(), tex.getHeight(),
                false, false);

    }

    private void loadShipFromFile(final FileHandle file) {
        Gdx.app.log(TAG, "load ship from file: " + file.name());
        BodyEditorLoader loader = new BodyEditorLoader(file);

        BodyDef def = new BodyDef();
        def.position.set(300, 300);
        def.type = BodyType.DynamicBody;

        FixtureDef fd = new FixtureDef();
        fd.density = 1.0f;
        fd.friction = 0.5f;
        fd.restitution = 0.1f;

        body = StellarAquariiMain.world.createBody(def);

        loader.attachFixture(body, "Ship1-Main", fd, 11.0f, 11.0f, 11.0f);
    }

    public void moveLeft(final boolean doMove) {
        moveLeft = doMove;
    }

    public void moveRight(final boolean doMove) {
        moveRight = doMove;
    }

    public void moveUp(final boolean doMove) {
        moveUp = doMove;
    }

    public void moveDown(final boolean doMove) {
        moveDown = doMove;
    }

    public String getShipName() {
        return shipName;
    }

    public int[][] getShipStructure() {
        return shipStructure;
    }

    /**
     * @return the maxshipwidth
     */
    public static int getMaxshipwidth() {
        return MAXSHIPWIDTH;
    }

    /**
     * @return the maxshipheight
     */
    public static int getMaxshipheight() {
        return MAXSHIPHEIGHT;
    }

    /**
     * @return the moveLeft
     */
    public boolean isMoveLeft() {
        return moveLeft;
    }

    /**
     * @return the moveRight
     */
    public boolean isMoveRight() {
        return moveRight;
    }

    /**
     * @return the moveUp
     */
    public boolean isMoveUp() {
        return moveUp;
    }

    /**
     * @return the moveDown
     */
    public boolean isMoveDown() {
        return moveDown;
    }

    public boolean isStrafeLeft() {
        return strafeLeft;
    }

    public boolean isStrafeRight() {
        return strafeRight;
    }

    public void strafeLeft(boolean isStrafeLeft) {
        strafeLeft = isStrafeLeft;
    }

    public void strafeRight(boolean isStrafeRight) {
        strafeRight = isStrafeRight;
    }

}
