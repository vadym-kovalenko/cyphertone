package com.anasazi.cyphertone;

import android.app.Activity;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class BaseScene extends Scene {

    static final int WIDTH = 480;
    static final int HEIGHT = 800;

    ResourceManager resourceManager;
    Engine engine;
    Activity activity;
    VertexBufferObjectManager vertexBufferObjectManager;
    Camera camera;

    public BaseScene(){
        resourceManager = ResourceManager.getInstance();
        engine = resourceManager.engine;
        vertexBufferObjectManager = engine.getVertexBufferObjectManager();
        camera = resourceManager.camera;
        activity = resourceManager.activity;
        createScene();
    }

    public abstract void createScene();

    public abstract void onBackKeyPressed();

    public abstract SceneManager.SceneType getSceneType();

    public abstract void disposeScene();

}
