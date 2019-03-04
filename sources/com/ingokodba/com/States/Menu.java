package com.ingokodba.com.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.ingokodba.com.Handlers.GamestateManager;
import com.ingokodba.com.Handlers.MyInput;
import com.ingokodba.com.Objekti.ZubicVila;
import com.ingokodba.com.PilotGame;

public class Menu extends GameState {
    private OrthographicCamera b2dCam;
    private Box2DDebugRenderer b2dr;
    GamestateManager gamest;
    Texture menutex;
    Sprite musik;
    Sprite sound;
    Texture tex;
    private float timing;
    ZubicVila vila;
    World world;

    public Menu(GamestateManager gsm) {
        super(gsm);
        this.gamest = gsm;
        if (GamestateManager.playsound) {
            this.tex = PilotGame.res.getTexture("sound_on");
        }
        if (!GamestateManager.playsound) {
            this.tex = PilotGame.res.getTexture("sound_off");
        }
        this.sound = new Sprite(this.tex);
        this.sound.setOrigin(0.0f, 0.0f);
        this.sound.setPosition(30.0f, 50.0f);
        this.sound.setScale(1.0f, 1.0f);
        if (GamestateManager.playmusic) {
            this.tex = PilotGame.res.getTexture("music_on");
        }
        if (!GamestateManager.playmusic) {
            this.tex = PilotGame.res.getTexture("music_off");
        }
        this.musik = new Sprite(this.tex);
        this.musik.setOrigin(0.0f, 0.0f);
        this.musik.setPosition(210.0f, 50.0f);
        this.musik.setScale(1.0f, 1.0f);
        this.timing = 0.0f;
        this.b2dCam = new OrthographicCamera();
        this.b2dCam.setToOrtho(false, 4.0f, 3.08f);
        this.b2dr = new Box2DDebugRenderer();
        this.menutex = PilotGame.res.getTexture("menu");
    }

    public void handleInput() {
        boolean z = true;
        if (MyInput.isPressed(3)) {
            this.gamest.setState(1818);
        }
        if (MyInput.isPressed(5)) {
            GamestateManager.playsound = !GamestateManager.playsound;
            if (GamestateManager.playsound) {
                this.tex = PilotGame.res.getTexture("sound_on");
            }
            if (!GamestateManager.playsound) {
                this.tex = PilotGame.res.getTexture("sound_off");
            }
            this.sound.setTexture(this.tex);
        }
        if (MyInput.isPressed(6)) {
            if (GamestateManager.playmusic) {
                z = false;
            }
            GamestateManager.playmusic = z;
            if (GamestateManager.playmusic) {
                this.tex = PilotGame.res.getTexture("music_on");
            }
            if (!GamestateManager.playmusic) {
                this.tex = PilotGame.res.getTexture("music_off");
            }
            this.musik.setTexture(this.tex);
        }
    }

    public void update(float dt) {
        handleInput();
    }

    public void render() {
        Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.sb.setProjectionMatrix(this.cam.combined);
        this.sb.begin();
        this.sb.draw(this.menutex, 0.0f, 0.0f, 400.0f, 308.0f);
        this.musik.draw(this.sb);
        this.sound.draw(this.sb);
        this.sb.end();
    }

    public void dispose() {
    }
}
