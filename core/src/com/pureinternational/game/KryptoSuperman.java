package com.pureinternational.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pureinternational.game.states.GameStateManager;
import com.pureinternational.game.states.MenuState;
import com.pureinternational.game.states.SplashState;

public class KryptoSuperman extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "KRYPTO SUPERMAN";
    private GameStateManager gsm;
    public SpriteBatch batch;
    public Music music;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameStateManager();
        music = Gdx.audio.newMusic(Gdx.files.internal("music3.mp3"));
        music.setLooping(true);
        music.setVolume(0.2f);
        music.play();
       // FileHandle handle = Gdx.files.internal("res/layout/activity_splash_screen.xml");
       // handle.parent();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        gsm.push(new SplashState(gsm));
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
    }


}
