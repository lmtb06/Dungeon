package com.dungeondevs.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dungeondevs.DungeonGame;
import com.dungeondevs.utils.Constants;

public class GameOverScreen implements Screen, DungeonGameScreen {
    private final DungeonGame game;
    private final Stage stage;
    private final Skin skin;
    public GameOverScreen(DungeonGame game) {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // Create a Table that fills the screen and set it as the stage's root.
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create a Label for the Game Over message.
        Label gameOverLabel = new Label("Game Over", skin);

        // Create buttons with listeners.
        TextButton retryButton = new TextButton("Recommencer", skin);
        retryButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.restartGame();
            }
        });

        TextButton quitButton = new TextButton("Quitter", skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        // Add the label and buttons to the table.
        table.add(gameOverLabel).padBottom(10);
        table.row(); // Move to the next row in the table.
        table.add(retryButton).padTop(10);
        table.row(); // Move to the next row in the table.
        table.add(quitButton).padTop(10);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        stage.act(Math.min(delta, 1/(float)Constants.FOREGROUND_FRAME_RATE));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void reinitialize() {

    }
}
