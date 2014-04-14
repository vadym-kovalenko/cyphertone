package com.anasazi.cyphertone;

import android.app.Activity;
import android.os.Bundle;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;
import org.andengine.util.adt.io.in.IInputStreamOpener;

import java.io.IOException;
import java.io.InputStream;

public class StartActivity extends BaseGameActivity {
    private static final int WIDTH = 480;
    private static final int HEIGHT = 800;

    Camera camera;
    private Scene scene;

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, WIDTH, HEIGHT);

        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new FillResolutionPolicy(), camera);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }

    @Override
    public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        ResourceManager.getInstance().initialize(mEngine, this).loadMenuTextures();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        scene = new Scene();
        scene.setBackground(new Background(Color.PINK));
        scene.setTouchAreaBindingOnActionDownEnabled(true);
        ButtonSprite buttonSprite = new ButtonSprite(WIDTH * 0.5f, HEIGHT * 0.5f, ResourceManager.getInstance().buttonNewRegion, mEngine.getVertexBufferObjectManager()) {
          public boolean onAreaTouched(TouchEvent touchEvent, float areaX, float areaY) {
              if (touchEvent.isActionDown()) {

              }
              return super.onAreaTouched(touchEvent, areaX, areaY);
          }
        };
        scene.registerTouchArea(buttonSprite);
        scene.attachChild(buttonSprite);
        pOnCreateSceneCallback.onCreateSceneFinished(scene);
    }

    @Override
    public void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}
