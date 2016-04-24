package com.solusgames.stellaraquarii.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.solusgames.stellaraquarii.entity.ship.Ship;

public class SAInputAdapter extends InputAdapter {

    private static final String TAG = "SAInputAdapter";
    private Ship player;

    public SAInputAdapter(final Ship player) {
        this.player = player;
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
        case Keys.ESCAPE:
            Gdx.app.exit();
            break;
        case Keys.Q:
            break;
        case Keys.E:
            break;
        default:

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
        case Keys.ESCAPE:
            Gdx.app.exit();
            break;
        case Keys.Q:
            break;
        case Keys.E:
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
