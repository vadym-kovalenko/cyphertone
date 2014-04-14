package com.anasazi.cyphertone;

public class SceneManager {
    private static SceneManager ourInstance = new SceneManager();

    public static SceneManager getInstance() {
        return ourInstance;
    }

    private SceneManager() {
    }
}
