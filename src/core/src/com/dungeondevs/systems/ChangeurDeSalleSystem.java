package com.dungeondevs.systems;

import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
import com.dungeondevs.components.Maps.ActiveEntity;

@All({SalleAssocieeComponent.class, ActiveEntity.class})
public class ChangeurDeSalleSystem extends EntityProcessingSystem {

    public Entity joueur;

    @Override
    protected void process(Entity e) {

        if (this.joueur != null){
            e.getComponent(ActiveEntity.class).active = e.getComponent(SalleAssocieeComponent.class).idMap == joueur.getComponent(SalleAssocieeComponent.class).idMap;
        }

        /** A ecrire pour de vrai quand on aura les déplacements **/
    }

    public void setJoueur(Entity joueur) {
        this.joueur = joueur;
    }
}
