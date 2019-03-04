package com.ingokodba.com.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ingokodba.com.Handlers.GamestateManager;
import com.ingokodba.com.PilotGame;

public abstract class GameState {
    protected OrthographicCamera cam = this.game.getCamera();
    PilotGame game;
    GamestateManager gsm;
    protected OrthographicCamera hudCam = this.game.getHUDCamera();
    protected SpriteBatch sb = this.game.getSpriteBatch();

    public abstract void dispose();

    public abstract void render();

    public abstract void update(float f);

    public GameState(GamestateManager gsm) {
        this.gsm = gsm;
        this.game = gsm.game();
    }
}
