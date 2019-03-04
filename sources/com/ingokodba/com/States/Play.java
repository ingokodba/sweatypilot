package com.ingokodba.com.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ingokodba.com.Handlers.GamestateManager;
import com.ingokodba.com.Handlers.MyContactListener;
import com.ingokodba.com.Handlers.MyInput;
import com.ingokodba.com.Objekti.Crystal;
import com.ingokodba.com.Objekti.ZubicVila;
import com.ingokodba.com.PilotGame;
import java.util.Iterator;

public class Play extends GameState {
    public static boolean crashed;
    public static boolean ended;
    Body avioncek;
    private OrthographicCamera b2dCam = new OrthographicCamera();
    private Box2DDebugRenderer b2dr;
    public Sprite backgr;
    public Sprite backgr2;
    Sound bullet;
    private MyContactListener cl = new MyContactListener(this);
    int crynum;
    public Array<Crystal> crystals;
    Sound eksplozija;
    Sprite finish;
    BitmapFont font;
    BitmapFont font2;
    public boolean kraj = false;
    Sound mortar;
    Music musika;
    public boolean nocontrol;
    Sprite num1;
    Sprite num2;
    ShapeRenderer shapeRenderer;
    SpriteBatch spriteBatch;
    CharSequence str = "Hello World!";
    Texture tex;
    private TiledMap tileMap;
    private float tileSize;
    public float timing = 0.0f;
    private OrthogonalTiledMapRenderer tmr;
    public Sprite twins;
    ZubicVila vila;
    boolean whatbckgr;
    World world;

    public Play(GamestateManager gsm) {
        super(gsm);
        this.b2dCam.setToOrtho(false, 4.0f, 3.08f);
        this.b2dr = new Box2DDebugRenderer();
        this.world = new World(new Vector2(0.0f, 0.0f), true);
        this.world.setContactListener(this.cl);
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        bdef.position.set(-1.0f, 1.3199999f);
        bdef.type = BodyType.DynamicBody;
        bdef.linearVelocity.set(2.0f, 0.0f);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.25f, 0.15f);
        this.avioncek = this.world.createBody(bdef);
        this.avioncek.setUserData("avion");
        fdef.shape = shape;
        fdef.isSensor = true;
        fdef.filter.categoryBits = (short) 2;
        fdef.filter.maskBits = (short) 48;
        this.avioncek.createFixture(fdef);
        this.vila = new ZubicVila(this.avioncek);
        createTiles();
        createCrystals();
        this.tex = PilotGame.res.getTexture("background");
        this.backgr = new Sprite(this.tex);
        this.backgr2 = new Sprite(this.tex);
        this.tex = PilotGame.res.getTexture("twins");
        this.twins = new Sprite(this.tex);
        this.tex = PilotGame.res.getTexture("finish");
        this.finish = new Sprite(this.tex);
        this.finish.setOrigin(0.0f, 0.0f);
        this.finish.setPosition(60.0f, 60.0f);
        this.finish.setScale(0.8f, 1.0f);
        this.backgr.setOrigin(0.0f, 0.0f);
        this.backgr.setScale(1.2f, 1.2f);
        this.backgr2.setOrigin(0.0f, 0.0f);
        this.backgr2.setScale(1.2f, 1.2f);
        this.twins.setOrigin(0.0f, 0.0f);
        this.twins.setScale(0.6f, 0.6f);
        this.whatbckgr = false;
        this.crynum = 0;
        this.shapeRenderer = new ShapeRenderer();
        if (GamestateManager.playmusic) {
            this.musika = Gdx.audio.newMusic(Gdx.files.internal("musik/hahahahhahah.mp3"));
        }
        if (GamestateManager.playsound) {
            this.bullet = Gdx.audio.newSound(Gdx.files.internal("musik/bullet.mp3"));
        }
        if (GamestateManager.playsound) {
            this.eksplozija = Gdx.audio.newSound(Gdx.files.internal("musik/eksplozija.mp3"));
        }
        if (GamestateManager.playsound) {
            this.mortar = Gdx.audio.newSound(Gdx.files.internal("musik/mortar.mp3"));
        }
        if (GamestateManager.playmusic) {
            this.musika.play();
            this.musika.setVolume(0.5f);
        }
        this.font = new BitmapFont(Gdx.files.internal("fonts/Nash.fnt"), Gdx.files.internal("fonts/Nash.png"), false);
        this.font2 = new BitmapFont(Gdx.files.internal("fonts/Nasho.fnt"), Gdx.files.internal("fonts/Nasho.png"), false);
    }

    public void createTiles() {
        this.tileMap = new TmxMapLoader().load("maps/mapa.tmx");
        this.tmr = new OrthogonalTiledMapRenderer(this.tileMap, 0.6f);
        this.tileSize = (float) ((Integer) this.tileMap.getProperties().get("tilewidth")).intValue();
        createLayer((TiledMapTileLayer) this.tileMap.getLayers().get("oblaki"), (short) 16);
    }

    private void createCrystals() {
        this.crystals = new Array();
        MapLayer layer = this.tileMap.getLayers().get("bodovi");
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        Iterator var5 = layer.getObjects().iterator();
        while (var5.hasNext()) {
            MapObject mo = (MapObject) var5.next();
            bdef.type = BodyType.StaticBody;
            bdef.position.set(((Float) mo.getProperties().get("x")).floatValue() / 166.6f, ((Float) mo.getProperties().get("y")).floatValue() / 166.6f);
            CircleShape cshape = new CircleShape();
            cshape.setRadius(0.08f);
            fdef.shape = cshape;
            fdef.isSensor = true;
            fdef.filter.categoryBits = (short) 32;
            fdef.filter.maskBits = (short) 2;
            Body body = this.world.createBody(bdef);
            body.createFixture(fdef).setUserData("crystal");
            Crystal c = new Crystal(body);
            this.crystals.add(c);
            body.setUserData(c);
        }
    }

    private void createLayer(TiledMapTileLayer layer, short bits) {
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        for (int row = 0; row < layer.getHeight(); row++) {
            for (int col = 0; col < layer.getWidth(); col++) {
                Cell cell = layer.getCell(col, row);
                if (!(cell == null || cell.getTile() == null)) {
                    bdef.type = BodyType.StaticBody;
                    bdef.position.set((((((float) col) + 0.5f) * this.tileSize) / 1.666f) / 100.0f, (((((float) row) + 0.5f) * this.tileSize) / 1.666f) / 100.0f);
                    ChainShape cs = new ChainShape();
                    cs.createChain(new Vector2[]{new Vector2(((-this.tileSize) / 10.0f) / 100.0f, ((-this.tileSize) / 10.0f) / 100.0f), new Vector2(((-this.tileSize) / 10.0f) / 100.0f, (this.tileSize / 10.0f) / 100.0f), new Vector2((this.tileSize / 10.0f) / 100.0f, (this.tileSize / 10.0f) / 100.0f)});
                    fdef.friction = 0.0f;
                    fdef.shape = cs;
                    fdef.filter.categoryBits = (short) 16;
                    fdef.filter.maskBits = (short) 2;
                    fdef.isSensor = false;
                    this.world.createBody(bdef).createFixture(fdef).setUserData("oblak");
                }
            }
        }
    }

    public void whatwhat() {
        if (!this.kraj) {
            this.vila.doCrash();
        }
        if (this.kraj) {
            this.vila.goCenter();
        }
        GamestateManager gamestateManager = this.gsm;
        if (GamestateManager.playmusic && !this.kraj) {
            this.musika.stop();
        }
        gamestateManager = this.gsm;
        if (GamestateManager.playsound && !this.kraj) {
            this.mortar.play();
        }
        this.nocontrol = true;
        if (!this.kraj) {
            crashed = true;
        }
    }

    public void handleInput() {
        if (!this.nocontrol) {
            if (MyInput.isPressed(0) && !MyInput.isPressed(1)) {
                this.vila.goUp();
            }
            if (MyInput.isPressed(1) && !MyInput.isPressed(0)) {
                this.vila.goDown();
            }
            if (!(MyInput.isDown(1) || MyInput.isDown(0))) {
                this.vila.goBack();
            }
        }
        if (crashed && MyInput.isPressed(4)) {
            GamestateManager gamestateManager = this.gsm;
            if (GamestateManager.playmusic) {
                this.musika.dispose();
            }
            crashed = false;
            ended = false;
            this.timing = 0.0f;
            this.gsm.restartGame();
        }
    }

    public void update(float dt) {
        int i;
        this.world.step(dt, 6, 2);
        handleInput();
        Array bodies = this.cl.getBodiesToRemove();
        if (!this.nocontrol) {
            for (i = 0; i < bodies.size; i++) {
                Body b = (Body) bodies.get(i);
                this.crystals.removeValue((Crystal) b.getUserData(), true);
                this.world.destroyBody(b);
                this.vila.collectCrystals();
                GamestateManager gamestateManager = this.gsm;
                if (GamestateManager.playsound) {
                    this.bullet.play();
                }
                this.crynum++;
            }
        }
        bodies.clear();
        this.vila.update(dt);
        for (i = 0; i < this.crystals.size; i++) {
            ((Crystal) this.crystals.get(i)).update(dt);
        }
        this.timing = (float) (((double) this.timing) + 0.1d);
    }

    public void render() {
        Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float hex = this.avioncek.getPosition().f102x;
        if (!(this.kraj && ended) && ((this.kraj || !crashed) && hex > 1.0f)) {
            this.cam.position.set((100.0f * hex) + 100.0f, this.cam.position.f108y, 0.0f);
        }
        if (!ended) {
            if (!(hex < 50.0f || this.nocontrol || this.kraj)) {
                this.kraj = true;
                whatwhat();
            }
            if (((double) hex) >= 52.3d && this.kraj && !crashed) {
                crashed = true;
                GamestateManager gamestateManager = this.gsm;
                if (GamestateManager.playsound) {
                    this.eksplozija.play();
                }
            }
            if (hex >= 53.0f && crashed) {
                ended = true;
            }
        }
        if (hex < 1.0f) {
            this.cam.position.set(200.0f, this.cam.position.f108y, 0.0f);
        }
        this.sb.setProjectionMatrix(this.cam.combined);
        if (hex >= -5.0f && ((double) hex) < 20.0d) {
            this.backgr.setPosition(-this.timing, 0.0f);
            this.sb.begin();
            this.backgr.draw(this.sb);
            this.sb.end();
        } else if (((double) hex) < 20.0d || ((double) hex) >= 40.0d) {
            this.backgr.setPosition(4790.0f - this.timing, 0.0f);
            this.backgr2.setPosition(2398.0f - this.timing, 0.0f);
            this.twins.setPosition(5100.0f - this.timing, 0.0f);
            this.sb.begin();
            this.backgr.draw(this.sb);
            this.backgr2.draw(this.sb);
            this.twins.draw(this.sb);
            this.sb.end();
        } else {
            this.backgr.setPosition(-this.timing, 0.0f);
            this.backgr2.setPosition(2398.0f - this.timing, 0.0f);
            this.sb.begin();
            this.backgr.draw(this.sb);
            this.backgr2.draw(this.sb);
            this.sb.end();
        }
        this.cam.update();
        this.tmr.setView(this.cam);
        this.tmr.render();
        this.vila.render(this.sb);
        for (int i = 0; i < this.crystals.size; i++) {
            ((Crystal) this.crystals.get(i)).render(this.sb);
        }
        if ((crashed && !this.kraj) || ended) {
            this.sb.setProjectionMatrix(this.hudCam.combined);
            this.sb.begin();
            this.finish.draw(this.sb);
            this.font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
            this.font.draw(this.sb, Integer.toString(this.crynum), 195.0f, 125.0f);
            CharSequence haha = getFinishText();
            this.font2.setColor(0.2f, 0.2f, 0.2f, 1.0f);
            this.font2.draw(this.sb, haha, (float) (230 - ((haha.length() / 2) * 15)), 210.0f);
            this.tex = PilotGame.res.getTexture("play_again");
            Sprite ad = new Sprite(this.tex);
            ad.setOrigin(0.0f, 0.0f);
            ad.setPosition(165.0f, 135.0f);
            ad.setScale(0.5f, 0.5f);
            ad.draw(this.sb);
            this.sb.end();
        }
    }

    String getFinishText() {
        String we = new String();
        switch (this.crynum) {
            case 0:
            case 1:
                we = "Try Harder!";
                break;
            case 2:
            case 3:
            case 4:
                we = "Not So Bad!";
                break;
            case 5:
            case 6:
            case 7:
                we = "Better!";
                break;
            case 8:
            case 9:
            case 10:
                we = "Improving!";
                break;
            case 11:
            case 12:
            case 13:
                we = "Very Good!";
                break;
            case 14:
            case 15:
            case 16:
                we = "Pretty Impressive!";
                break;
            case 17:
            case 18:
            case 19:
                we = "Master!";
                break;
            case 20:
            case 21:
            case 22:
                we = "Master Of Masters!";
                break;
            default:
                we = "Bravo!";
                break;
        }
        if (this.kraj) {
            return "Ma super si!";
        }
        return we;
    }

    public void dispose() {
    }
}
