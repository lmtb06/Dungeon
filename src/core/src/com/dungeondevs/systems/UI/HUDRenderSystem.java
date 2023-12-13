package com.dungeondevs.systems.UI;

import com.artemis.BaseEntitySystem;
import com.artemis.BaseSystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.AttackComponent;
import com.dungeondevs.components.HealthComponent;
import com.dungeondevs.components.PowerUpUserComponent;

import java.util.List;

public class HUDRenderSystem extends BaseEntitySystem {

    private Stage hudStage;
    private Label healthLabel;
    private Label weaponLabel;
    private Label powerupLabel;

    public HUDRenderSystem (Stage hudStage, Label healthLabel, Label weaponLabel, Label powerupLabel) {
        this.hudStage = hudStage;
        this.healthLabel = healthLabel;
        this.weaponLabel = weaponLabel;
        this.powerupLabel = powerupLabel;

        Viewport viewport = new ScreenViewport();
        hudStage = new Stage(viewport);

        // Create the Label that will display the health
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(),new Color(1,1,1,1));

        healthLabel = new Label("Health: ", labelStyle);
        weaponLabel = new Label("Weapon : ", labelStyle);
        powerupLabel = new Label("", labelStyle);

        Table table = new Table(); // Create the layout table
        table.setFillParent(true);
        hudStage.addActor(table);
        table.add(healthLabel).padTop(10).padRight(10);
        table.add(weaponLabel).padTop(10).padRight(10);
        table.top().right();

        Table powerUpTable = new Table();
        powerUpTable.setFillParent(true);
        hudStage.addActor(powerUpTable);
        powerUpTable.add(powerupLabel);
        powerUpTable.top().left();
    }

    private void updateHealthLabel (HealthComponent healthComponent) {
        healthLabel.setText("Points de vie: " + healthComponent.health);
    }

    private void updateWeaponLabel (AttackComponent attackComponent) {
        weaponLabel.setText("Arme actuelle: " + attackComponent.arme);
    }

    private void updatePowerupLabel (List<String> powerups) {
        //boolean speedPowerUpActive = powerUpUserComponent.speedLastAppliedTime > 0;
        //float remainingSpeedTime = (float) (powerUpUserComponent.speedLastAppliedTime + powerUpUserComponent.speedDuration - TimeUtils.millis()) / 1000L;

        StringBuilder stringBuilder = new StringBuilder();
        for (String powerup: powerups) {
            stringBuilder.append(powerup).append("\n");
        }
        //stringBuilder.append(speedPowerUpActive ? "Bonus de vitesse : " + String.format("%.1f", remainingSpeedTime) : "");
        powerupLabel.setText(stringBuilder.toString());
    }

    @Override
    protected void processSystem() {
        IntBag entities = getSubscription().getEntities();
        if (!entities.isEmpty()) {
            /*HealthComponent healthComponent = healthMapper.get(entities.get(0));
            AttackComponent attackComponent = weaponMapper.get(entities.get(0));
            PowerUpUserComponent powerUpUserComponent = powerUpMapper.get(entities.get(0));
            updateHealthLabel(healthComponent);
            updateWeaponLabel(attackComponent);
            updatePowerupLabel(powerUpUserComponent);*/
            hudStage.act(world.delta);
            hudStage.draw();
        }
    }

    @Override
    protected void dispose() {
        hudStage.dispose();
    }

    public void resize(int width, int height) {
        hudStage.getViewport().update(width, height, true);
    }
}
