package com.dungeondevs.systems;

import com.artemis.Archetype;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
import com.dungeondevs.components.Maps.ActiveEntity;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.utils.FixtureUserData;
import com.dungeondevs.utils.GameArchetypes;

@All(LoadMapComponent.class)
public class RoomIntializerSystem extends EntityProcessingSystem {

    private World world;

    public RoomIntializerSystem(World world) {
        this.world = world;
    }

    @Override
    protected void process(Entity e) {
        LoadMapComponent lmc = e.getComponent(LoadMapComponent.class);
        if (!lmc.loaded){

            float facteur = 0.5f;
            //collision
            TiledMapTileLayer layerCollision = (TiledMapTileLayer) lmc.map.getLayers().get(1);

            Archetype murArchetype = GameArchetypes.MUR_ARCHETYPE
                    .build(getWorld());

            for (int i = 0; i < layerCollision.getWidth(); i++) {
                for (int j = 0; j < layerCollision.getHeight(); j++) {
                    if (layerCollision.getCell(i,j) != null){
                        Entity mur = getWorld().createEntity(murArchetype);
                        mur.getComponent(PhysicsComponent.class).body = createBoundary(i*facteur + 0.59f, j*facteur + 0.59f, facteur,facteur);
                        mur.getComponent(SalleAssocieeComponent.class).idMap = lmc.idmap;
                        mur.getComponent(ActiveEntity.class).active = false;
                    }
                }
            }




            // objet entities
            MapObjects so = lmc.map.getLayers().get(2).getObjects();

            float facteurEntity = 0.6f;

            Archetype monsterArchetype = GameArchetypes.MONSTRE_ARCHETYPE
                    .build(getWorld());

            for (int i = 0; i < so.getCount(); i++) {
                System.out.println("siu:" +Float.parseFloat(so.get(i).getProperties().get("x spawn").toString()));

                switch (so.get(i).getProperties().get("categorie").toString()){
                    case "monstre":
                        Entity monstre = getWorld().createEntity(monsterArchetype);

                        //Body Monstre
                        BodyDef playerBodyDef = new BodyDef();
                        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
                        playerBodyDef.position.set(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurEntity, Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurEntity);
                        Body playerBody = world.createBody(playerBodyDef);

                        PolygonShape boxShape = new PolygonShape();
                        boxShape.setAsBox(0.2f, 0.2f);

                        FixtureDef boxFixtureDef = new FixtureDef();
                        boxFixtureDef.shape = boxShape;
                        boxFixtureDef.density = 1;

                        Fixture fixture = playerBody.createFixture(boxFixtureDef);
                        fixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Monster, monstre));
                        boxShape.dispose();

                        //Composant relatif à la salle dans laquelle il se trouve
                        monstre.getComponent(SalleAssocieeComponent.class).idMap = lmc.idmap;
                        monstre.getComponent(ActiveEntity.class).active = false;
                        /**Monster m = new Monster(Integer.parseInt(so.get(i).getProperties().get("health").toString()),
                                Float.parseFloat(so.get(i).getProperties().get("speed").toString()),
                                Integer.parseInt(so.get(i).getProperties().get("damages").toString()),
                                new Vector2(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteur,
                                        Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteur
                                ),
                                new Vector2(0.6f,0.6f),
                                this.world

                        );**/
                        break;
                    case "powerUps":
                        /**PowerUp pu = new PowerUp(
                                new Vector2(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteur,
                                        Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteur
                                ),
                                new Vector2(0.6f,0.6f),
                                this.world,
                                this
                        );**/
                        break;
                }



            }



            System.out.printf("count: "+ layerCollision.getHeight() +" "+ so.getCount());

            e.getComponent(LoadMapComponent.class).loaded = true;
        }
    }

    private Body createBoundary(float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, 0);

        shape.dispose();
        return body;
    }

}
