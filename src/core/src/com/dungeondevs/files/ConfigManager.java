package com.dungeondevs.files;

import com.dungeondevs.configs.LevelConfig;
import com.dungeondevs.configs.PlayerConfig;

public interface ConfigManager {

    public LevelConfig getLevelConfig();

    public PlayerConfig getPlayerConfig();

    public void loadConfig();

    public void saveConfig();
}
