package com.ingokodba.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ingokodba.com.Handlers.Content;
import com.ingokodba.com.Handlers.GamestateManager;
import com.ingokodba.com.Handlers.MyInput;
import com.ingokodba.com.Handlers.MyInputProcessor;

public class PilotGame extends ApplicationAdapter {
    private static final float STEP = 0.016666668f;
    public static Content res;
    SpriteBatch batch;
    private OrthographicCamera cam;
    GamestateManager gsm;
    private OrthographicCamera hudCam;
    float timePassed;

    public void create() {
        res = new Content();
        res.loadTexture("images/bigairp.png", "airplane");
        res.loadTexture("images/crystal.png", "crystal");
        res.loadTexture("images/background2.png", "background");
        res.loadTexture("images/menu.png", "menu");
        res.loadTexture("images/finish.png", "finish");
        res.loadTexture("images/play_again.png", "play_again");
        res.loadTexture("images/sound_on.png", "sound_on");
        res.loadTexture("images/sound_off.png", "sound_off");
        res.loadTexture("images/music_on.png", "music_on");
        res.loadTexture("images/music_off.png", "music_off");
        res.loadTexture("images/twins.png", "twins");
        this.batch = new SpriteBatch();
        this.cam = new OrthographicCamera();
        this.cam.setToOrtho(false, 400.0f, 308.0f);
        this.hudCam = new OrthographicCamera();
        this.hudCam.setToOrtho(false, 400.0f, 308.0f);
        this.gsm = new GamestateManager(this);
        this.timePassed = 0.0f;
        Gdx.input.setInputProcessor(new MyInputProcessor());
    }

    public void render() {
        this.timePassed += Gdx.graphics.getDeltaTime();
        if (this.timePassed > STEP) {
            this.timePassed -= STEP;
            Gdx.gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
            this.gsm.update(STEP);
            this.gsm.render();
            MyInput.update();
        }
    }

    public SpriteBatch getSpriteBatch() {
        return this.batch;
    }

    public OrthographicCamera getCamera() {
        return this.cam;
    }

    public OrthographicCamera getHUDCamera() {
        return this.hudCam;
    }
}
