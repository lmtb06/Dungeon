package com.dungeondevs.files;

import com.dungeondevs.configs.Level.LevelConfig;
import com.dungeondevs.configs.Level.PlayerConfig;

public interface ConfigManager {

    public LevelConfig getLevelConfig();

    public PlayerConfig getPlayerConfig();

    public void loadConfig();

    public void saveConfig();
}
