package com.dungeondevs.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.components.PlayerCharacterComponent;

@All(PlayerCharacterComponent.class)
public class HudSystem extends BaseEntitySystem {
    private Stage hudStage;
    private Label healthLabel;
    private ComponentMapper<HealthComponent> healthMapper;

    @Override
    protected void initialize() {
        Viewport viewport = new ScreenViewport();
        hudStage = new Stage(viewport);

        // Create the Label that will display the health
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(),new Color(1,1,1,1));
        healthLabel = new Label("Health: ", labelStyle);
        Table table = new Table(); // Create the layout table
        table.setFillParent(true);
        hudStage.addActor(table);
        table.add(healthLabel).padTop(10).padRight(10);
        table.top().right();
    }

    @Override
    protected void processSystem() {
        IntBag entities = getSubscription().getEntities();
        if (!entities.isEmpty()) {
            HealthComponent healthComponent = healthMapper.get(entities.get(0));
            updateHealthLabel(healthComponent);
            hudStage.act(world.delta);
            hudStage.draw();
        }
    }

    private void updateHealthLabel(HealthComponent healthComponent) {
        healthLabel.setText("Points de vie: " + healthComponent.health);
    }

    @Override
    protected void dispose() {
        hudStage.dispose();
    }

    public void resize(int width, int height) {
        hudStage.getViewport().update(width, height, true);
    }
}
