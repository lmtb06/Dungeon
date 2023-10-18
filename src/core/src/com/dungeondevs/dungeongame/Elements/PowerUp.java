package com.dungeondevs.dungeongame.Elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.dungeondevs.dungeongame.tiled_elements.Level;

public class PowerUp extends GameObject {

    public Level niveauPresent;

    public PowerUp(Vector2 position, Vector2 size, World world, Level lvl) {
        super(position, size, world);
        this.niveauPresent = lvl;
    }

}
