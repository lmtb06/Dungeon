package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.annotations.One;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dungeondevs.Enumeration.LevelState;
import com.dungeondevs.components.InputComponent;
import com.dungeondevs.components.Level.PorteComponent;
import com.dungeondevs.components.Level.SalleActuelJoueurComponent;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.Maps.MapStateComponent;

@One({PorteComponent.class, MapStateComponent.class})
public class ChangeurDeSalleSystem extends EntityProcessingSystem {
    @Override
    protected void process(Entity e) {

        if (e.getComponent(MapStateComponent.class).etat == LevelState.actuel){
            if (e.getComponent(PorteComponent.class).idMapDansLaquelleElleSeTrouve == e.getComponent(LoadMapComponent.class).idmap /** On rajoutera ici la collision **/ && Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                e.getComponent(MapStateComponent.class).etat = LevelState.actuelleAttenteAffichage;
                System.out.println("aloaaaaa");
            }
        }
    }

    /** A ecrire pour de vrai quand on aura les déplacements **/

}
