package com.dungeondevs.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.Enumeration.LevelState;
import com.dungeondevs.components.InputComponent;
import com.dungeondevs.components.LoadMapComponent;
import com.dungeondevs.components.MapStateComponent;

/**
 * Classe s'occupant de charger tous les fichiers de maps existantes dans les assets. Elle n'est pour le moment pas complète TODO
 */
@All(LoadMapComponent.class)
public class MapsLoaderSystem extends EntitySystem {

    private int idMapCourant;

    @Override
    protected void processSystem() {
        getEntities();
        idMapCourant = 0;
        for (Entity m: getEntities()
             ) {
            if (m.getComponent(LoadMapComponent.class).lienAsset == null){
                m.getComponent(LoadMapComponent.class).lienAsset = "maps/test2.tmx";
                m.getComponent(LoadMapComponent.class).idmap = idMapCourant;
                if (m.getComponent(LoadMapComponent.class).idmap == 0){
                    m.getComponent(MapStateComponent.class).etat = LevelState.actuelleAttenteAffichage;
                }else {
                    m.getComponent(MapStateComponent.class).etat = LevelState.nonCommencee;
                }
                idMapCourant ++;
                System.out.println("salut : "+idMapCourant);
            }
        }
    }
}
