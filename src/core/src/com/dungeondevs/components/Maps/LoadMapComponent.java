package com.dungeondevs.components.Maps;

import com.artemis.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class LoadMapComponent extends Component {
    public String lienAsset;
    public int idmap;

    public boolean loaded;

    public TiledMap map;
}
