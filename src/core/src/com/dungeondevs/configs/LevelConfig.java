package com.dungeondevs.configs;

public class LevelConfig {
    private String fichier;
    private int idSalleDepart;
    //private List<RoomConfig> salles;

    public LevelConfig (String fichier, int idSalleDepart/*, List<RoomConfig> salles*/) {
        this.fichier = fichier;
        this.idSalleDepart = idSalleDepart;
        //this.salles = salles;
    }
}
