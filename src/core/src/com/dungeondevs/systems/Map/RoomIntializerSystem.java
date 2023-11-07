package com.dungeondevs.systems.Map;

import com.artemis.Archetype;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.dungeondevs.components.Level.PorteComponent;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
import com.dungeondevs.components.Maps.ActiveEntity;
import com.dungeondevs.components.Maps.LoadMapComponent;
import com.dungeondevs.components.PhysicsComponent;
import com.dungeondevs.utils.FixtureUserData;
import com.dungeondevs.utils.GameArchetypes;

import java.util.ArrayList;

@All({LoadMapComponent.class})
public class RoomIntializerSystem extends EntityProcessingSystem {

    private World box2dworld;

    public int salleActuelle = -1;

    public Entity joueur;

    public Array<Body> listeEntiteADesactiver;

    public RoomIntializerSystem(World world) {
        listeEntiteADesactiver = new Array<>();
        this.box2dworld = world;
    }

    @Override
    protected void process(Entity e) {

        //System.out.println(joueur.getComponent(SalleAssocieeComponent.class).idMap != salleActuelle);
        if (joueur!=null && joueur.getComponent(SalleAssocieeComponent.class).idMap != salleActuelle ){
            if (joueur.getComponent(SalleAssocieeComponent.class).idMap == e.getComponent(LoadMapComponent.class).idmap){


            LoadMapComponent lmc = e.getComponent(LoadMapComponent.class);
            System.out.println("nbEntites : "+lmc.map.getLayers().get(2).getObjects().getCount());
            //if (lmc.idmap == joueur.getComponent(SalleAssocieeComponent.class).idMap){

            //System.out.println(this.box2dworld.getBodyCount());
                this.box2dworld.getBodies(listeEntiteADesactiver);

            for (Body entitsBody: listeEntiteADesactiver) {
                if (joueur.getComponent(PhysicsComponent.class).body != entitsBody){
                    this.box2dworld.destroyBody(entitsBody);
                }

            }

            float facteurCase = 0.5f;

            float decalageX = facteurCase*4;
                float decalageY = facteurCase*2;
            float facteur = 0.5f;

            float facteurX = 0.5f;
            float facteurY = 0.5f;
            //collision
            TiledMapTileLayer layerCollision = (TiledMapTileLayer) lmc.map.getLayers().get(1);
            //System.out.println("layer collision : " + layerCollision.);

            Archetype murArchetype = GameArchetypes.MUR_ARCHETYPE
                    .build(getWorld());

            for (int i = 0; i < layerCollision.getWidth(); i++) {
                for (int j = 0; j < layerCollision.getHeight(); j++) {
                    if (layerCollision.getCell(i,j) != null){
                        Entity mur = getWorld().createEntity(murArchetype);
                        BodyDef bodyDef = new BodyDef();
                        mur.getComponent(PhysicsComponent.class).body = createBoundary((i*facteurX) - decalageX, (j*facteurY) - decalageY, facteur,facteur, bodyDef);
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

            Archetype porteArchetype = GameArchetypes.PORTE_ARCHETYPE
                    .build(getWorld());


            for (int i = 0; i < so.getCount(); i++) {
                System.out.println("siu:" +Float.parseFloat(so.get(i).getProperties().get("x spawn").toString()));

                switch (so.get(i).getProperties().get("categorie").toString()){
                    case "monstre":
                        Entity monstre = getWorld().createEntity(monsterArchetype);

                        //Body Monstre
                        BodyDef playerBodyDef = new BodyDef();
                        playerBodyDef.type = BodyDef.BodyType.DynamicBody;
                        playerBodyDef.position.set(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurX - decalageX, Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurY - decalageY);
                        Body playerBody = box2dworld.createBody(playerBodyDef);

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
                        monstre.getComponent(PhysicsComponent.class).body = playerBody;

                        break;
                    case "powerUps":

                        break;
                    case "porte":
                        Entity porte = getWorld().createEntity(porteArchetype);

                        //Body porte
                        BodyDef porteBodyDef = new BodyDef();
                        porteBodyDef.type = BodyDef.BodyType.StaticBody;
                        porteBodyDef.position.set(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurX - decalageX, Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurY - decalageY);
                        Body porteBody = box2dworld.createBody(porteBodyDef);

                        PolygonShape porteboxShape = new PolygonShape();
                        porteboxShape.setAsBox(0.2f, 0.2f);

                        FixtureDef porteboxFixtureDef = new FixtureDef();
                        porteboxFixtureDef.shape = porteboxShape;
                        porteboxFixtureDef.density = 1;

                        Fixture portefixture = porteBody.createFixture(porteboxFixtureDef);
                        portefixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Porte, porte));
                        porteboxShape.dispose();

                        porte.getComponent(SalleAssocieeComponent.class).idMap = lmc.idmap;
                        porte.getComponent(PorteComponent.class).idMapDansLaquelleElleSeTrouve = lmc.idmap;
                        porte.getComponent(PorteComponent.class).idMapVersLaquelleElleMene = Integer.parseInt(so.get(i).getProperties().get("versSalle").toString());

                        porte.getComponent(PhysicsComponent.class).body = porteBody;

                }



            }





            e.getComponent(LoadMapComponent.class).loaded = true;
            salleActuelle = joueur.getComponent(SalleAssocieeComponent.class).idMap;

            }
            }
    }

    private Body createBoundary(float x, float y, float width, float height, BodyDef bdf) {

        bdf.type = BodyDef.BodyType.StaticBody;
        bdf.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        Body body = box2dworld.createBody(bdf);
        body.createFixture(shape, 0);

        shape.dispose();
        return body;
    }

    public void setJoueur(Entity joueur) {
        this.joueur = joueur;
    }
}
