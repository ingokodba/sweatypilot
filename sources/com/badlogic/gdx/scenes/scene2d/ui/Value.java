package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

public abstract class Value {
    public static Value maxHeight = new C00876();
    public static Value maxWidth = new C00865();
    public static Value minHeight = new C00832();
    public static Value minWidth = new C00821();
    public static Value prefHeight = new C00854();
    public static Value prefWidth = new C00843();
    public static final Fixed zero = new Fixed(0.0f);

    /* renamed from: com.badlogic.gdx.scenes.scene2d.ui.Value$1 */
    static class C00821 extends Value {
        C00821() {
        }

        public float get(Actor context) {
            if (context instanceof Layout) {
                return ((Layout) context).getMinWidth();
            }
            return context == null ? 0.0f : context.getWidth();
        }
    }

    /* renamed from: com.badlogic.gdx.scenes.scene2d.ui.Value$2 */
    static class C00832 extends Value {
        C00832() {
        }

        public float get(Actor context) {
            if (context instanceof Layout) {
                return ((Layout) context).getMinHeight();
            }
            return context == null ? 0.0f : context.getHeight();
        }
    }

    /* renamed from: com.badlogic.gdx.scenes.scene2d.ui.Value$3 */
    static class C00843 extends Value {
        C00843() {
        }

        public float get(Actor context) {
            if (context instanceof Layout) {
                return ((Layout) context).getPrefWidth();
            }
            return context == null ? 0.0f : context.getWidth();
        }
    }

    /* renamed from: com.badlogic.gdx.scenes.scene2d.ui.Value$4 */
    static class C00854 extends Value {
        C00854() {
        }

        public float get(Actor context) {
            if (context instanceof Layout) {
                return ((Layout) context).getPrefHeight();
            }
            return context == null ? 0.0f : context.getHeight();
        }
    }

    /* renamed from: com.badlogic.gdx.scenes.scene2d.ui.Value$5 */
    static class C00865 extends Value {
        C00865() {
        }

        public float get(Actor context) {
            if (context instanceof Layout) {
                return ((Layout) context).getMaxWidth();
            }
            return context == null ? 0.0f : context.getWidth();
        }
    }

    /* renamed from: com.badlogic.gdx.scenes.scene2d.ui.Value$6 */
    static class C00876 extends Value {
        C00876() {
        }

        public float get(Actor context) {
            if (context instanceof Layout) {
                return ((Layout) context).getMaxHeight();
            }
            return context == null ? 0.0f : context.getHeight();
        }
    }

    public static class Fixed extends Value {
        private final float value;

        public Fixed(float value) {
            this.value = value;
        }

        public float get(Actor context) {
            return this.value;
        }
    }

    public abstract float get(Actor actor);

    public static Value percentWidth(final float percent) {
        return new Value() {
            public float get(Actor actor) {
                return actor.getWidth() * percent;
            }
        };
    }

    public static Value percentHeight(final float percent) {
        return new Value() {
            public float get(Actor actor) {
                return actor.getHeight() * percent;
            }
        };
    }

    public static Value percentWidth(final float percent, final Actor actor) {
        if (actor != null) {
            return new Value() {
                public float get(Actor context) {
                    return actor.getWidth() * percent;
                }
            };
        }
        throw new IllegalArgumentException("actor cannot be null.");
    }

    public static Value percentHeight(final float percent, final Actor actor) {
        if (actor != null) {
            return new Value() {
                public float get(Actor context) {
                    return actor.getHeight() * percent;
                }
            };
        }
        throw new IllegalArgumentException("actor cannot be null.");
    }
}
