package com.dungeondevs.files;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.dungeondevs.configs.LevelConfig;
import com.dungeondevs.configs.PlayerConfig;
import org.json.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonConfigManager implements ConfigManager {
    private String fichierJSON;
    private PlayerConfig playerConfig;
    private LevelConfig levelConfig;

    public JsonConfigManager(String fichierJSON) {

        this.fichierJSON = fichierJSON; //assign your JSON String here
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fichierJSON);
            JsonReader reader = new JsonReader();
            JsonValue value = reader.parse(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LevelConfig getLevelConfig() {
        return levelConfig;
    }

    @Override
    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public List<WeaponConfig> getWeaponConfigs () {
        return weapons;
    }

    @Override
    public void loadConfig() {

    }

    @Override
    public void saveConfig() {

    }

}
