package com.anasazi.cyphertone;

import android.widget.Toast;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class GameScene extends BaseScene implements IOnSceneTouchListener {

    ButtonEntity red, green, yellow, blue;
    public boolean isPlaying;
    private ArrayList<ButtonEntity> playingQueue;
    Random r;
    TimerHandler handler;
    private int checkedButton = -1;
    private int score;
    Text scoreText;

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionUp()) {
            if (!isPlaying) {
                createNewQueue();
                play();
            }
        }
        return true;
    }

    public enum ButtonType {
        RED, GREEN, YELLOW, BLUE
    }

    @Override
    public void createScene() {
        setBackground(new Background(Color.PINK));
        red = new ButtonEntity(240.0f, 242.0f, resourceManager.red, vertexBufferObjectManager, ButtonType.RED);
        green = new ButtonEntity(240.0f, 556.0f, resourceManager.green, vertexBufferObjectManager, ButtonType.GREEN);
        yellow = new ButtonEntity(96.0f, 400.0f, resourceManager.yellow, vertexBufferObjectManager, ButtonType.YELLOW);
        blue = new ButtonEntity(384.0f, 400.0f, resourceManager.blue, vertexBufferObjectManager, ButtonType.BLUE);
        attachChild(red);
        attachChild(green);
        attachChild(yellow);
        attachChild(blue);
        registerTouchArea(red);
        registerTouchArea(green);
        registerTouchArea(yellow);
        registerTouchArea(blue);
        red.gameScene = this;
        green.gameScene = this;
        yellow.gameScene = this;
        blue.gameScene = this;
        scoreText = new Text(20, 720, resourceManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vertexBufferObjectManager);
        scoreText.setAnchorCenter(0, 0);
        scoreText.setText("Score: 0");
        scoreText.setColor(0, 0, 0);
        attachChild(scoreText);
        setOnSceneTouchListener(this);
    }

    @Override
    public void onBackKeyPressed() {
        SceneManager.getInstance().createMenuScene();
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

    public void buttonPushed(ButtonEntity entity) {
        checkedButton++;
        if (playingQueue == null || playingQueue.size() == 0) {
            createNewQueue();
        } else {
            if (entity.buttonType != playingQueue.get(checkedButton).buttonType) {
                createNewQueue();
                play();
                return;
            }
            if (checkedButton == playingQueue.size() - 1) {
                score++;
                updateScore(score);
                checkedButton = -1;
                addToQueue();
                play();
            }
        }
    }

    private void createNewQueue() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "New game", Toast.LENGTH_SHORT).show();
            }
        });
        playingQueue = new ArrayList<ButtonEntity>();
        score = 0;
        checkedButton = -1;
        addToQueue();
        updateScore(0);
    }

    private void play() {
        isPlaying = true;
        final Iterator<ButtonEntity> i = playingQueue.iterator();
        handler = new TimerHandler(Constant.BUTTON_PLAY_DELAY, true, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                if (i.hasNext()) {
                    i.next().play();
                } else {
                    isPlaying = false;
                    engine.unregisterUpdateHandler(handler);
                }
            }
        });
        engine.registerUpdateHandler(handler);
    }

    private void addToQueue() {
        r = new Random();
        int i = r.nextInt(4);
        switch (i) {
            case 0:
                playingQueue.add(red);
                break;
            case 1:
                playingQueue.add(green);
                break;
            case 2:
                playingQueue.add(yellow);
                break;
            case 3:
                playingQueue.add(blue);
                break;
        }
    }

    private void updateScore(int score) {
        scoreText.setText("Score: " + score);
    }

}
