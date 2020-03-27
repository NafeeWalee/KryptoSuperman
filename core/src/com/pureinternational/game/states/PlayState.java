package com.pureinternational.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.pureinternational.game.KryptoSuperman;
import com.pureinternational.game.sprites.Kryptonite;
import com.pureinternational.game.sprites.Player;

public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -65;
    private Texture background;
    private Player player;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private BitmapFont font;
    private int worldTimer = 0;

    private Array<Kryptonite> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        player = new Player(50, 300);
        cam.setToOrtho(false, KryptoSuperman.WIDTH / 2, KryptoSuperman.HEIGHT / 2);
        background = new Texture("sup_bg.png");
        ground = new Texture("ground_krypto.png");

        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        tubes = new Array<Kryptonite>();

        for (int i = 2; i <= TUBE_COUNT; i++) {

                tubes.add(new Kryptonite(i * (TUBE_SPACING + Kryptonite.TUBE_WIDTH)));


        }
        font = new BitmapFont();
        font.setColor(Color.CHARTREUSE);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            player.jump();

        }
    }

    @Override
    public void update(float dt) {

        if (dt != dt + 1) {
            worldTimer++;
        }
        handleInput();
        updateGround();
        player.update(dt);
        cam.position.x = player.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Kryptonite tube = tubes.get(i);
            if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Kryptonite.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(player.getBounds())) {
                gsm.set(new GameOverScreen(gsm, worldTimer));
            }
        }
        if (player.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET) {
            gsm.set(new GameOverScreen(gsm, worldTimer));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(player.getBird(), player.getPosition().x, player.getPosition().y);
        for (Kryptonite tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        font.draw(sb, "Survival Score : " + worldTimer, cam.position.x - 70, cam.position.y + 180);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        player.dispose();
        ground.dispose();
        font.dispose();
        for (Kryptonite tube : tubes) {
            tube.dispose();
        }
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
