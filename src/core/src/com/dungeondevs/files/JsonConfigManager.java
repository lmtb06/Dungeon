package com.dungeondevs.files;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.dungeondevs.configs.Level.LevelConfig;
import com.dungeondevs.configs.Level.PlayerConfig;
import com.dungeondevs.configs.spawnable.Items.PowerUpConfig;
import com.dungeondevs.configs.spawnable.Items.TrapConfig;
import com.dungeondevs.configs.spawnable.MonsterConfig;
import com.dungeondevs.configs.spawnable.WeaponConfig;
import com.dungeondevs.factories.spawnable.TrapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class JsonConfigManager implements ConfigManager {
    private String fichierJSON;
    private PlayerConfig playerConfig;
    private LevelConfig levelConfig;

    protected List<WeaponConfig> weapons;
    protected List<PowerUpConfig> PowerUps;
    protected List<MonsterConfig> monstres;
    protected List<TrapConfig> traps;
    //protected List<MonsterConfig> teleporteurs;

    public JsonConfigManager(String fichierJSON) {

        this.fichierJSON = fichierJSON; //assign your JSON String here
        this.weapons = new ArrayList<>();

    }

    public void LoadJsonSpawnable(){

        try {

            FileInputStream fileInputStream = new FileInputStream("JSON/weapon.json");
            JsonReader reader = new JsonReader();
            JsonValue value = reader.parse(fileInputStream);

            System.out.println(value);
            for (JsonValue arme: value) {
                System.out.println(arme.get("name"));

                BodyDef weaponBodyDef = new BodyDef();
                weaponBodyDef.type = BodyDef.BodyType.StaticBody;
                weaponBodyDef.position.set(arme.get("bodyDef").getInt("x"), arme.get("bodyDef").getInt("y"));
                weaponBodyDef.fixedRotation = true;

                System.out.println(arme.get("textureRegion").getString("textureFichier"));

                WeaponConfig wc = new WeaponConfig(
                        arme.getString("name"),
                        arme.getInt("damage"),
                        arme.getFloat("cooldown"),
                        new TextureRegion(new Texture(
                                arme.get("textureRegion").getString("textureFichier")),
                                arme.get("textureRegion").getFloat("x"),
                                arme.get("textureRegion").getFloat("y"),
                                arme.get("textureRegion").getFloat("width"),
                                arme.get("textureRegion").getFloat("height")),
                        weaponBodyDef
                );
                this.weapons.add(wc);
            }
/**
             fileInputStream = new FileInputStream("JSON/PowerUps/");
             reader = new JsonReader();
             value = reader.parse(fileInputStream);

             fileInputStream = new FileInputStream("JSON/Traps/");
             reader = new JsonReader();
             value = reader.parse(fileInputStream);

             fileInputStream = new FileInputStream("JSON/PowerUps/");
             reader = new JsonReader();
             value = reader.parse(fileInputStream);
**/
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
