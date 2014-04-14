package com.anasazi.cyphertone;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.color.Color;


public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private MenuScene menuChildScene;
    private final int MENU_PLAY = 0;
    private final int MENU_OPTIONS = 1;

    private void createMenuChildScene()
    {
        menuChildScene = new MenuScene(camera);
        menuChildScene.setPosition(0, 0);

        final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourceManager.buttonNewRegion, vertexBufferObjectManager), 1.2f, 1);

        menuChildScene.addMenuItem(playMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 10);

        menuChildScene.setOnMenuItemClickListener(this);

        setChildScene(menuChildScene);
    }

    @Override
    public void createScene() {
        setBackground(new Background(Color.PINK));
        createMenuChildScene();
    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }

    @Override
    public SceneManager.SceneType getSceneType() {
        return SceneManager.SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene() {
        menuChildScene.detachSelf();
        menuChildScene.dispose();
        detachSelf();
        dispose();
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()) {
            case MENU_PLAY:
                SceneManager.getInstance().createGameScene();
                return true;
            default:
                return true;
        }
    }
}
