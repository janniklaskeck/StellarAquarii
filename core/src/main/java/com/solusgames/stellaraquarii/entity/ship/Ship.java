package com.solusgames.stellaraquarii.entity.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    private float linearSpeed = 0.0f;
    private float strafeSpeed = 0.0f;
    private float angularSpeed = 0.0f;

    BitmapFont font;

    public Ship(final FileHandle file) {
        super();
        font = new BitmapFont();
        loadShipFromFile(file);
        tex = new Texture(Gdx.files.internal("ship1/ship1.png"));
    }

    @Override
    public void update(float deltaTime) {
        if (isMoveRight()) {
            angularSpeed += -2 * deltaTime;
        } else if (isMoveLeft()) {
            angularSpeed += 2 * deltaTime;
        } else {
            angularSpeed = reduceToZero(angularSpeed, 5 * deltaTime);
        }
        final Vector2 forwardVector = new Vector2(-MathUtils.sin(body.getAngle()), MathUtils.cos(body.getAngle()));
        final Vector2 strafeRightVector = forwardVector.cpy().rotate90(-1);

        if (isMoveDown()) {
            linearSpeed += -50 * deltaTime;
        } else if (isMoveUp()) {
            linearSpeed += 50 * deltaTime;
        }

        if (isStrafeLeft()) {
            strafeSpeed += -50 * deltaTime;
        } else if (isStrafeRight()) {
            strafeSpeed += 50 * deltaTime;
        } else {
            strafeSpeed = reduceToZero(strafeSpeed, 10 * deltaTime);
        }

        final Vector2 moveVector = new Vector2(forwardVector);
        moveVector.scl(linearSpeed);
        strafeRightVector.scl(strafeSpeed);
        moveVector.add(strafeRightVector);

        body.setAngularVelocity(angularSpeed);
        body.setLinearVelocity(moveVector);

    }

    private float reduceToZero(final float value, final float f) {
        float result = value;
        if (result > 0) {
            result -= f;
        }

        if (result < 0) {
            result += f;
        }
        if (result <= 0.1f && result >= -0.1f) {
            result = 0;
        }
        return result;
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

    public void renderHUD(SpriteBatch batch) {
        font.draw(batch, "Lin: " + linearSpeed + "\nStrafe: " + strafeSpeed + "\n Ang: " + angularSpeed, 50, 50);
        font.draw(batch, "LinPX: " + body.getLinearVelocity().x + " LinPY: " + body.getLinearVelocity().y, 50, 150);
    }

    private void loadShipFromFile(final FileHandle file) {
        Gdx.app.log(TAG, "load ship from file: " + file.name());
        BodyEditorLoader loader = new BodyEditorLoader(file);

        BodyDef def = new BodyDef();
        def.position.set(300, 300);
        def.type = BodyType.DynamicBody;
        def.angularDamping = 0;
        def.linearDamping = 0;

        FixtureDef fd = new FixtureDef();
        fd.density = 10000.0f;
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

    public Body getBody() {
        return body;
    }

    public void stopVelocity() {
        linearSpeed = 0;
        angularSpeed = 0;
        strafeSpeed = 0;
    }

}
