package com.pureinternational.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pureinternational.game.KryptoSuperman;

public class SplashState  extends State {
    private Texture background;
    private Texture header;
    private BitmapFont font1,font2,font3,font4;
    private int timer=0;


    public SplashState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, KryptoSuperman.WIDTH / 2, KryptoSuperman.HEIGHT / 2);
        header = new Texture("header.png");
        background = new Texture("splash_bg.png");
        font1 = new BitmapFont();
        font1.setColor(Color.WHITE);
        font1.getData().setScale(2, 2);

        font2 = new BitmapFont();
        font2.setColor(Color.WHITE);
        font2.getData().setScale(2, 2);

        font3 = new BitmapFont();
        font3.setColor(Color.WHITE);
        font3.getData().setScale(2, 2);

        font4 = new BitmapFont();
        font4.setColor(Color.WHITE);
        font4.getData().setScale(2, 2);

    }

    @Override
    protected void handleInput() {

            gsm.set(new MenuState(gsm));

    }

    @Override
    public void update(float dt) {
        if (dt != dt + 1) {
            timer++;
        }
        if(timer==120)
        {
            handleInput();
        }


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(header, cam.position.x - header.getWidth() / 2, cam.position.y);

        font1.draw(sb, "Loading", 60, 140);
        if(timer>=30){
            font2.draw(sb, ".", 170, 140);
        }
        if(timer>=60){
            font3.draw(sb, ".", 180, 140);
        }
        if(timer>=90){
            font4.draw(sb, ".", 190, 140);
        }


        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        header.dispose();
        font1.dispose();
        font2.dispose();
        font3.dispose();
        font4.dispose();

    }
}
