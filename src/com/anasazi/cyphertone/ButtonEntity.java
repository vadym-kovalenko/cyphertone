package com.anasazi.cyphertone;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ButtonEntity extends TiledSprite {

    GameScene gameScene;
    private boolean isPlaying;
    GameScene.ButtonType buttonType;
    TimerHandler timerHandler;

    public ButtonEntity(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, GameScene.ButtonType buttonType) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
        this.buttonType = buttonType;
        setCurrentTileIndex(1);
    }

    public boolean onAreaTouched(TouchEvent touchEvent, float touchX, float touchY) {
        if (touchEvent.isActionUp() && !gameScene.isPlaying) {
            play();
            gameScene.buttonPushed(ButtonEntity.this);
        }
        return true;
    }

    public void play() {
        setCurrentTileIndex(0);
        if (isPlaying && timerHandler != null) {
            unregisterUpdateHandler(timerHandler);
        }
        timerHandler = new TimerHandler(Constant.BUTTON_PLAY_DURATION, new ITimerCallback() {
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
                ButtonEntity.this.setCurrentTileIndex(1);
                isPlaying = false;
            }
        });
        isPlaying = true;
        registerUpdateHandler(timerHandler);
    }
}
