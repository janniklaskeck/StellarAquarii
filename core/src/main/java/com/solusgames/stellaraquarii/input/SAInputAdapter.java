package com.solusgames.stellaraquarii.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.solusgames.stellaraquarii.entity.ship.Ship;

public class SAInputAdapter extends InputAdapter {

    private static final String TAG = "SAInputAdapter";
    private Ship player;
    private OrthographicCamera oCam;

    public SAInputAdapter(final Ship player, final OrthographicCamera oCam) {
        this.player = player;
        this.oCam = oCam;
        Gdx.app.log(TAG, "Create Adapter");
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
        case Keys.W:
            player.moveUp(true);
            break;
        case Keys.D:
            player.moveRight(true);
            break;
        case Keys.S:
            player.moveDown(true);
            break;
        case Keys.A:
            player.moveLeft(true);
            break;
        case Keys.E:
            player.strafeRight(true);
            break;
        case Keys.Q:
            player.strafeLeft(true);
            break;
        case Keys.X:
            player.stopVelocity();
            break;
        case Keys.NUM_1:
            oCam.zoom += 0.1f;
            break;
        case Keys.NUM_2:
            oCam.zoom -= 0.1f;
            break;
        case Keys.ESCAPE:
            Gdx.app.exit();
            break;
        default:
            return false;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode) {
        case Keys.W:
            player.moveUp(false);
            break;
        case Keys.D:
            player.moveRight(false);
            break;
        case Keys.S:
            player.moveDown(false);
            break;
        case Keys.A:
            player.moveLeft(false);
            break;
        case Keys.E:
            player.strafeRight(false);
            break;
        case Keys.Q:
            player.strafeLeft(false);
            break;
        case Keys.ESCAPE:
            break;
        default:

        }
        return true;
    }

    @Override
    public boolean keyTyped(char charTyped) {

        return false;
    }

}
