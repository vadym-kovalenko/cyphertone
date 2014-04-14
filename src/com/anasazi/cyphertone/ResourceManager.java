package com.anasazi.cyphertone;

import android.content.Context;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

public class ResourceManager {
    private static ResourceManager ourInstance = new ResourceManager();
    Engine engine;
    Camera camera;
    StartActivity activity;

    //Buttons
    public ITextureRegion ra, ri, ga, gi, ya, yi, ba, bi;
    private BitmapTextureAtlas buttonAtlas;

    //Main menu
    public ITextureRegion buttonNewRegion;
    private BitmapTextureAtlas menuAtlas;

    //Backgrounds
    public ITextureRegion backgroundRegion;

    public static ResourceManager getInstance() {
        return ourInstance;
    }

    public ResourceManager initialize(Engine engine, StartActivity activity, Camera camera) {
        this.engine = engine;
        this.activity = activity;
        this.camera = camera;
        return this;
    }

    private ResourceManager() {
    }

    public synchronized void loadGameTextures() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        buttonAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 404, 48);
        ra = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "red_active.png", 0, 0);
        ri = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "red_inactive.png", 50, 0);
        ga = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "green_active.png", 100, 0);
        gi = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "green_inactive.png", 150, 0);
        ya = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "yellow_active.png", 200, 0);
        yi = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "yellow_inactive.png", 250, 0);
        ba = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "blue_active.png", 300, 0);
        bi = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, activity, "blue_inactive.png", 350, 0);
        buttonAtlas.load();
    }

    public synchronized void loadMenuTextures() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        menuAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 310, 200);
        buttonNewRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuAtlas, activity, "menu_new_game.png", 0, 0);
        menuAtlas.load();
    }

    public synchronized void unloadGameTextures() {
        buttonAtlas.unload();
        ra = null;
        ri = null;
        ga = null;
        gi = null;
        ya = null;
        yi = null;
        ba = null;
        bi = null;
    }

    public synchronized void unloadMenuTextures() {
        menuAtlas.unload();
        buttonNewRegion = null;
    }
}
