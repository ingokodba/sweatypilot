package com.ingokodba.com.Handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ingokodba.com.PilotGame;
import com.ingokodba.com.States.GameState;
import com.ingokodba.com.States.Menu;
import com.ingokodba.com.States.Play;
import java.util.Stack;

public class GamestateManager {
    public static boolean playmusic = true;
    public static boolean playsound = true;
    private short MENU = (short) 1151;
    private short PLAY = (short) 1818;
    private SpriteBatch batch;
    private OrthographicCamera cam;
    private PilotGame game;
    private Stack<GameState> gameStates;
    private OrthographicCamera hudCam;

    public GamestateManager(PilotGame game) {
        this.game = game;
        this.gameStates = new Stack();
        pushState(this.MENU);
    }

    public PilotGame game() {
        return this.game;
    }

    public void update(float dt) {
        ((GameState) this.gameStates.peek()).update(dt);
    }

    public void render() {
        ((GameState) this.gameStates.peek()).render();
    }

    private GameState getState(int state) {
        if (state == this.PLAY) {
            return new Play(this);
        }
        if (state == this.MENU) {
            return new Menu(this);
        }
        return null;
    }

    public void setState(int state) {
        popState();
        pushState(state);
    }

    public void pushState(int state) {
        this.gameStates.push(getState(state));
    }

    public void restartGame() {
        popState();
        pushState(this.PLAY);
    }

    public void popState() {
        ((GameState) this.gameStates.pop()).dispose();
    }
}
