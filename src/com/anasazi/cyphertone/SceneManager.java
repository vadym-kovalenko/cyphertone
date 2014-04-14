package com.anasazi.cyphertone;

import org.andengine.engine.Engine;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface;

public class SceneManager {
    private static SceneManager ourInstance = new SceneManager();

    public static SceneManager getInstance() {
        return ourInstance;
    }

    private Engine engine = ResourceManager.getInstance().engine;
    private SceneType currentSceneType = SceneType.SCENE_MENU;
    private BaseScene currentScene;
    private BaseScene gameScene;
    private BaseScene menuScene;

    public enum SceneType
    {
        SCENE_MENU,
        SCENE_GAME,
    }

    private SceneManager() {
        engine = ResourceManager.getInstance().engine;
    }

    public void setScene(BaseScene scene)
    {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }

    public void setScene(SceneType sceneType)
    {
        switch (sceneType)
        {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            default:
                break;
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public SceneType getCurrentSceneType()
    {
        return currentSceneType;
    }

    public void createMenuScene(IGameInterface.OnCreateSceneCallback callback) {
        ResourceManager.getInstance().loadMenuTextures();
        menuScene = new MainMenuScene();
        setScene(menuScene);
        if (gameScene != null) {
            disposeGameScene();
        }
        if (callback != null) {
            callback.onCreateSceneFinished(menuScene);
        }
    }

    public void disposeMenuScene() {
        ResourceManager.getInstance().unloadMenuTextures();
        menuScene.disposeScene();
        menuScene = null;
    }

    public void createGameScene() {
        ResourceManager.getInstance().loadGameTextures();
        gameScene = new GameScene();
        setScene(gameScene);
        disposeMenuScene();
    }

    public void disposeGameScene() {
        ResourceManager.getInstance().unloadGameTextures();
        gameScene.disposeScene();
        gameScene = null;
    }
}
