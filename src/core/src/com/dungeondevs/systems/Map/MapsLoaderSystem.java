package com.dungeondevs.systems.Map;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.All;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.rendering.RoomRendererComponent;

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

                if (idMapCourant == 0){
                    m.getComponent(LoadMapComponent.class).lienAsset = "maps/test2.tmx";
                    m.getComponent(LoadMapComponent.class).idmap = idMapCourant;
                    m.getComponent(LoadMapComponent.class).loaded = false;
                    m.getComponent(LoadMapComponent.class).map = loader.load(m.getComponent(LoadMapComponent.class).lienAsset);
                    m.getComponent(RoomRendererComponent.class).setFileName("maps/test2.tmx");
                    System.out.println("salut : "+m.getComponent(LoadMapComponent.class).map);
                }
                if (idMapCourant == 1){
                    m.getComponent(LoadMapComponent.class).lienAsset = "maps/test3.tmx";
                    m.getComponent(LoadMapComponent.class).idmap = idMapCourant;
                    m.getComponent(LoadMapComponent.class).loaded = false;
                    m.getComponent(LoadMapComponent.class).map = loader.load(m.getComponent(LoadMapComponent.class).lienAsset);
                    m.getComponent(RoomRendererComponent.class).setFileName("maps/test3.tmx");
                    System.out.println("salut : "+m.getComponent(LoadMapComponent.class).map);
                }
                if (idMapCourant == 2){
                    m.getComponent(LoadMapComponent.class).lienAsset = "maps/map2.tmx";
                    m.getComponent(LoadMapComponent.class).idmap = idMapCourant;
                    m.getComponent(LoadMapComponent.class).loaded = false;
                    m.getComponent(LoadMapComponent.class).map = loader.load(m.getComponent(LoadMapComponent.class).lienAsset);
                    m.getComponent(RoomRendererComponent.class).setFileName("maps/test3.tmx");
                    System.out.println("salut : "+m.getComponent(LoadMapComponent.class).map);
                }
                idMapCourant ++;

            }
        }
    }
}
