package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.math.Vector2;

public class InputListener implements EventListener {
    private static final Vector2 tmpCoords = new Vector2();

    public boolean handle(Event e) {
        if (!(e instanceof InputEvent)) {
            return false;
        }
        InputEvent event = (InputEvent) e;
        switch (event.getType()) {
            case keyDown:
                return keyDown(event, event.getKeyCode());
            case keyUp:
                return keyUp(event, event.getKeyCode());
            case keyTyped:
                return keyTyped(event, event.getCharacter());
            default:
                event.toCoordinates(event.getListenerActor(), tmpCoords);
                switch (event.getType()) {
                    case touchDown:
                        return touchDown(event, tmpCoords.f102x, tmpCoords.f103y, event.getPointer(), event.getButton());
                    case touchUp:
                        touchUp(event, tmpCoords.f102x, tmpCoords.f103y, event.getPointer(), event.getButton());
                        return true;
                    case touchDragged:
                        touchDragged(event, tmpCoords.f102x, tmpCoords.f103y, event.getPointer());
                        return true;
                    case mouseMoved:
                        return mouseMoved(event, tmpCoords.f102x, tmpCoords.f103y);
                    case scrolled:
                        return scrolled(event, tmpCoords.f102x, tmpCoords.f103y, event.getScrollAmount());
                    case enter:
                        enter(event, tmpCoords.f102x, tmpCoords.f103y, event.getPointer(), event.getRelatedActor());
                        return false;
                    case exit:
                        exit(event, tmpCoords.f102x, tmpCoords.f103y, event.getPointer(), event.getRelatedActor());
                        return false;
                    default:
                        return false;
                }
        }
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return false;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
    }

    public void touchDragged(InputEvent event, float x, float y, int pointer) {
    }

    public boolean mouseMoved(InputEvent event, float x, float y) {
        return false;
    }

    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
    }

    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
    }

    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        return false;
    }

    public boolean keyDown(InputEvent event, int keycode) {
        return false;
    }

    public boolean keyUp(InputEvent event, int keycode) {
        return false;
    }

    public boolean keyTyped(InputEvent event, char character) {
        return false;
    }
}
