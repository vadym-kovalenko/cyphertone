package com.anasazi.cyphertone;

import android.content.Context;
import org.andengine.engine.Engine;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;

public class ResourceManager {
    private static ResourceManager ourInstance = new ResourceManager();

    //Buttons
    public ITextureRegion ra, ri, ga, gi, ya, yi, ba, bi;
    private BitmapTextureAtlas buttonAtlas;

    //Backgrounds
    public ITextureRegion backgroundRegion;

    public static ResourceManager getInstance() {
        return ourInstance;
    }

    private ResourceManager() {
    }

    public synchronized void loadTextures(Engine engine, Context context) {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        buttonAtlas = new BitmapTextureAtlas(engine.getTextureManager(), 404, 48);
        ra = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "red_active.png", 0, 0);
        ri = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "red_inactive.png", 0, 50);
        ga = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "green_active.png", 0, 100);
        gi = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "green_inactive.png", 0, 150);
        ya = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "yellow_active.png", 0, 200);
        yi = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "yellow_inactive.png", 0, 250);
        ba = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "blue_active.png", 0, 300);
        bi = BitmapTextureAtlasTextureRegionFactory.createFromAsset(buttonAtlas, context, "blue_inactive.png", 0, 350);
        buttonAtlas.load();
    }

    public synchronized void unloadTextures() {
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
}
