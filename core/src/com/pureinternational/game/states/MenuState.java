package com.pureinternational.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pureinternational.game.KryptoSuperman;

public class MenuState extends State {
    private Texture background;
    private Texture header;
    private Texture gameStart;
    private Texture supIntro;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, KryptoSuperman.WIDTH / 2, KryptoSuperman.HEIGHT / 2);
        header = new Texture("header.png");
        background = new Texture("sup_bg.png");
        gameStart = new Texture("gamestart.png");
        supIntro = new Texture("superman_intro.png");


    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(header, cam.position.x - header.getWidth() / 2, cam.position.y);
        sb.draw(supIntro, 60, 140);
        sb.draw(gameStart, 60, 50);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        header.dispose();
        gameStart.dispose();
        supIntro.dispose();
    }

}
