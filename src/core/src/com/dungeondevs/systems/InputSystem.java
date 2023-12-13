package com.dungeondevs.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dungeondevs.components.InputComponent;
import com.dungeondevs.components.PlayerCharacterComponent;

/**
 * Système qui gère les entrées clavier du joueur
 */
@All({InputComponent.class, PlayerCharacterComponent.class})
public class InputSystem extends EntityProcessingSystem {
    private ComponentMapper<InputComponent> inputComponentMapper;

    /**
     * Process les entités
     * Les entrées clavier sont:
     * - Flèche gauche: gauche
     * - Flèche droite: droite
     * - Flèche haut: haut
     * - Flèche bas: bas
     * - Espace: attaque
     * - E: attaque à distance
     * @param e l'entité à traiter
     */
    @Override
    protected void process(Entity e) {
        InputComponent inputComponent = inputComponentMapper.get(e);

        inputComponent.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        inputComponent.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        inputComponent.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));
        inputComponent.setDown(Gdx.input.isKeyPressed(Input.Keys.DOWN));
        inputComponent.setAttack(Gdx.input.isKeyPressed(Input.Keys.SPACE));
        inputComponent.setRangedAttack(Gdx.input.isKeyPressed(Input.Keys.E));
    }
}
