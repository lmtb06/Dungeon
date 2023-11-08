package com.dungeondevs.systems;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dungeondevs.components.InputComponent;

@All(InputComponent.class)
public class InputSystem extends EntityProcessingSystem {
    private ComponentMapper<InputComponent> inputComponentMapper;
    @Override
    protected void process(Entity e) {
        InputComponent inputComponent = inputComponentMapper.get(e);

        inputComponent.left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        inputComponent.right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        inputComponent.up = Gdx.input.isKeyPressed(Input.Keys.UP);
        inputComponent.down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        inputComponent.space = Gdx.input.isKeyPressed(Input.Keys.SPACE);
    }
}
