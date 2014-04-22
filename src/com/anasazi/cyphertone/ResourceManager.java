package com.anasazi.cyphertone;

import android.content.Context;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

public class ResourceManager {
    private static ResourceManager ourInstance = new ResourceManager();
    Engine engine;
    Camera camera;
    StartActivity activity;

    //Buttons
    public TiledTextureRegion red, green, blue, yellow;
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
        buttonAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 768, 96);
        red = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buttonAtlas, activity, "red.png", 0, 0, 2, 1);
        green = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buttonAtlas, activity, "green.png", 192, 0, 2, 1);
        yellow = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buttonAtlas, activity, "yellow.png", 384, 0, 2, 1);
        blue = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(buttonAtlas, activity, "blue.png", 576, 0, 2, 1);
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
        red = null;
        green = null;
        yellow = null;
        blue = null;
    }

    public synchronized void unloadMenuTextures() {
        menuAtlas.unload();
        buttonNewRegion = null;
    }
}
