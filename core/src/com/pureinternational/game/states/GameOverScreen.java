package com.pureinternational.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pureinternational.game.KryptoSuperman;

public class GameOverScreen extends State {
    private Texture gameover;
    private Texture retry;
    private Texture bg;
    private BitmapFont font1, font2;
    private int finalScore;

    public GameOverScreen(GameStateManager gsm, int score) {
        super(gsm);
        cam.setToOrtho(false, KryptoSuperman.WIDTH / 2, KryptoSuperman.HEIGHT / 2);
        gameover = new Texture("gameover.png");
        retry = new Texture("retry.png");
        bg = new Texture("sup_bg.png");
        finalScore = score;

        font1 = new BitmapFont();
        font2 = new BitmapFont();
        font1.setColor(Color.WHITE);
        font1.getData().setScale(2, 2);
        font2.setColor(Color.WHITE);
        font2.getData().setScale(1, 1);
    }

    @Override
    protected void handleInput() {
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
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        font1.draw(sb, "Survival Score", 30, 240);
        font2.draw(sb, "" + finalScore, cam.position.x - 10, 200);
        sb.draw(gameover, 60, 270);
        sb.draw(retry, 60, 70);
        sb.end();
    }

    @Override
    public void dispose() {
        gameover.dispose();
        retry.dispose();
        bg.dispose();
        font1.dispose();
        font2.dispose();
    }
}
