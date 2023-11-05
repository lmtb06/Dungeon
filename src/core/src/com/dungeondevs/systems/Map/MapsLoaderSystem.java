package com.dungeondevs.systems.Map;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.All;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.dungeondevs.components.Maps.LoadMapComponent;

/**
 * Classe s'occupant de charger tous les fichiers de maps existantes dans les assets. Elle n'est pour le moment pas complète TODO
 */
@All(LoadMapComponent.class)
public class MapsLoaderSystem extends EntitySystem {

    private int idMapCourant;

    TmxMapLoader loader;

    public MapsLoaderSystem() {
         this.loader = new TmxMapLoader();
    }

    @Override
    protected void processSystem() {
        getEntities();
        idMapCourant = 0;
        for (Entity m: getEntities()
             ) {
            if (m.getComponent(LoadMapComponent.class).lienAsset == null){
                m.getComponent(LoadMapComponent.class).lienAsset = "maps/test2.tmx";
                m.getComponent(LoadMapComponent.class).idmap = idMapCourant;
                m.getComponent(LoadMapComponent.class).loaded = false;
                m.getComponent(LoadMapComponent.class).map = loader.load(m.getComponent(LoadMapComponent.class).lienAsset);
                /**if (m.getComponent(LoadMapComponent.class).idmap == 0){
                    m.getComponent(MapStateComponent.class).etat = LevelState.actuelleAttenteAffichage;
                }else {
                    m.getComponent(MapStateComponent.class).etat = LevelState.nonCommencee;
                }**/
                idMapCourant ++;
                System.out.println("salut : "+idMapCourant);
            }
        }
    }
}
