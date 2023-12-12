package com.dungeondevs.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dungeondevs.components.*;

import java.util.HashMap;

@All(PlayerCharacterComponent.class)
public class HudSystem extends BaseEntitySystem {
    private Stage hudStage;
    private Label healthLabel;
    private Label weaponLabel;
    private Label powerUpLabel;
    private Image weaponImage;
    private ComponentMapper<HealthComponent> healthMapper;
    private ComponentMapper<AttackComponent> weaponMapper;
    private ComponentMapper<PowerUpUserComponent> powerUpMapper;

    private HashMap<String, String> weaponSpritesMap;
    private Cell<Image> weaponCell;
    private Table powerUpTable;

    @Override
    protected void initialize() {
        Viewport viewport = new ScreenViewport();
        hudStage = new Stage(viewport);
        weaponSpritesMap = new HashMap<>();
        weaponSpritesMap.put("epee", "./weapon_sword_icon.png");
        weaponSpritesMap.put("lance", "./weapon_spear_icon.png");
        weaponSpritesMap.put("couteau", "./weapon_dagger_icon.png");

        // Create the Label that will display the health
        Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(),new Color(1,1,1,1));

        healthLabel = new Label("Health: ", labelStyle);
        weaponLabel = new Label("epee", labelStyle);
        powerUpLabel = new Label("", labelStyle);

        weaponImage = new Image(new Texture("./weapon_sword_icon.png"));

        Table table = new Table(); // Create the layout table
        table.setFillParent(true);
        hudStage.addActor(table);
        table.add(healthLabel).padTop(10).padRight(10);
        table.top().right();

        powerUpTable = new Table();
        powerUpTable.setFillParent(true);
        hudStage.addActor(powerUpTable);
        //powerUpTable.add(powerUpLabel);
        powerUpTable.bottom().left().padBottom(5);

        float percentageWidth = 0.1f; // Ajustez ce pourcentage comme nécessaire
        float imageSize = Gdx.graphics.getWidth() * percentageWidth;

        Table weaponTable = new Table();
        weaponTable.setFillParent(true);
        hudStage.addActor(weaponTable);
        weaponImage.setSize(32f, 32f);
        weaponTable.add(weaponLabel).padRight(10).padBottom(5);
        weaponTable.row();
        weaponTable.bottom().right();
        weaponCell = weaponTable.add(weaponImage).padRight(10).padBottom(10).size(imageSize);
    }

    @Override
    protected void processSystem() {
        IntBag entities = getSubscription().getEntities();
        if (!entities.isEmpty()) {
            HealthComponent healthComponent = healthMapper.get(entities.get(0));
            AttackComponent attackComponent = weaponMapper.get(entities.get(0));
            PowerUpUserComponent powerUpUserComponent = powerUpMapper.get(entities.get(0));
            updateHealthLabel(healthComponent);
            updateWeaponLabel(attackComponent);
            updatePowerupLabel(powerUpUserComponent);
            hudStage.act(world.delta);
            hudStage.draw();
        }
    }

    private void updateHealthLabel(HealthComponent healthComponent) {
        healthLabel.setText("Points de vie: " + healthComponent.health);
    }

    private void updateWeaponLabel(AttackComponent attackComponent) {
        weaponLabel.setText(attackComponent.arme.substring(0, 1).toUpperCase() + attackComponent.arme.substring(1));
        String fileLink = weaponSpritesMap.get(attackComponent.arme);

        Texture newTexture = new Texture(fileLink);
        TextureRegion newTextureRegion = new TextureRegion(newTexture);
        Drawable newDrawable = new TextureRegionDrawable(newTextureRegion);

        weaponImage.setDrawable(newDrawable);
    }

    private void updatePowerupLabel(PowerUpUserComponent powerUpUserComponent) {
        //boolean speedPowerUpActive = powerUpUserComponent.speedLastAppliedTime > 0;
        //float remainingSpeedTime = (float) (powerUpUserComponent.speedLastAppliedTime + powerUpUserComponent.speedDuration - TimeUtils.millis()) / 1000L;

        powerUpTable.clearChildren();
        for (PowerUpType key : powerUpUserComponent.activePowerups.keySet()) {


            float percentageWidth = 0.05f; // Ajustez ce pourcentage comme nécessaire
            float imageSize = Gdx.graphics.getWidth() * percentageWidth;

            Image powerupIcon = new Image(new Texture(getPowerUpIcon(key)));
            powerupIcon.setSize(32f, 32f);

            long endTime = powerUpUserComponent.activePowerups.get(key);
            float remainingSpeedTime = (endTime - TimeUtils.millis()) / 1000f;

            Label.LabelStyle labelStyle = new Label.LabelStyle(new BitmapFont(),new Color(1,1,1,1));
            Label powerupTime = new Label(endTime == -1 ? ("Permanent") : (String.format("%.1f", remainingSpeedTime) + " s"), labelStyle);
            powerupTime.setAlignment(Align.left);

            powerUpTable.add(powerupIcon).padLeft(10).padBottom(5).size(imageSize);
            powerUpTable.add(powerupTime).padLeft(5);
            powerUpTable.row();
        }

        //StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append(speedPowerUpActive ? "Bonus de vitesse : " + String.format("%.1f", remainingSpeedTime) : "");
        //powerUpLabel.setText(stringBuilder.toString());
    }

    @Override
    protected void dispose() {
        hudStage.dispose();
    }

    public void resize(int width, int height) {
        float percentageWidth = 0.1f; // Ajustez ce pourcentage comme nécessaire
        float imageSize = Gdx.graphics.getWidth() * percentageWidth;
        weaponCell.size(imageSize);
        hudStage.getViewport().update(width, height, true);
    }

    private String getPowerUpIcon (PowerUpType type) {
        String res = "";
        switch (type){
            case SPEED_TEMPO:
                res = "./powerup_speed_icon.png";
                break;
            case ATTACK_TEMPO:
            case ATTACK_DEFIN:
                res =  "./powerup_damages_icon.png";
                break;
            default:
                res =  "";
                break;
        }
        return res;
    }
}
