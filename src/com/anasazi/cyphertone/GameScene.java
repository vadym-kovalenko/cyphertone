package com.anasazi.cyphertone;

import org.andengine.entity.scene.background.Background;
import org.andengine.util.adt.color.Color;

public class GameScene extends BaseScene {

    ButtonEntity red, green, yellow, blue;

    @Override
    public void createScene() {
        setBackground(new Background(Color.PINK));
        red = new ButtonEntity(240.0f, 266.0f, resourceManager.ri, vertexBufferObjectManager);
        green = new ButtonEntity(240.0f, 532.0f, resourceManager.gi, vertexBufferObjectManager);
        yellow = new ButtonEntity(72.0f, 400.0f, resourceManager.yi, vertexBufferObjectManager);
        blue = new ButtonEntity(328.0f, 400.0f, resourceManager.bi, vertexBufferObjectManager);
        attachChild(red);
        attachChild(green);
        attachChild(yellow);
        attachChild(blue);
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().createMenuScene(null);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
        red.detachSelf();
        red.dispose();
        green.detachSelf();
        green.dispose();
        yellow.detachSelf();
        yellow.dispose();
        blue.detachSelf();
        blue.dispose();
        detachSelf();
        dispose();
    }

}
