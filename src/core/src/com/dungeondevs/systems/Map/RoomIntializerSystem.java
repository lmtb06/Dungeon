package com.dungeondevs.systems.Map;

import com.artemis.Archetype;
import com.artemis.Entity;
import com.artemis.annotations.All;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.dungeondevs.components.*;
import com.dungeondevs.components.Level.PorteComponent;
import com.dungeondevs.components.Level.SalleAssocieeComponent;
import com.dungeondevs.components.Maps.ActiveEntity;
import com.dungeondevs.components.Maps.LoadMapComponent;
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

            ArrayList<Entity> listeTpSalle = new ArrayList();

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

            Archetype trapArchetype = GameArchetypes.TRAP_ENTITY_ARCHETYPE
                    .build(getWorld());

            Archetype teleporteurArchetype = GameArchetypes.TELEPORTEUR_ENTITY_ARCHETYPE
                    .build(getWorld());

                Archetype powerUpArchetype = GameArchetypes.POWER_UP_ARCHETYPE
                        .build(getWorld());


            for (int i = 0; i < so.getCount(); i++) {
                System.out.println("siu:" +Float.parseFloat(so.get(i).getProperties().get("x spawn").toString()));

                switch (so.get(i).getProperties().get("categorie").toString()){
                    case "monstre":
                        Entity monstre = getWorld().createEntity(monsterArchetype);

                        //Body Monstre
                        BodyDef playerBodyDef = new BodyDef();
                        playerBodyDef.type = BodyDef.BodyType.KinematicBody;
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
                        Entity powerUp = getWorld().createEntity(powerUpArchetype);

                        BodyDef powerUpBodyDef = new BodyDef();
                        powerUpBodyDef.type = BodyDef.BodyType.DynamicBody;
                        powerUpBodyDef.position.set(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurX - decalageX, Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurY - decalageY);
                        Body powerUpBody = box2dworld.createBody(powerUpBodyDef);

                        PolygonShape boxShapePowerUp = new PolygonShape();
                        boxShapePowerUp.setAsBox(0.2f, 0.2f);

                        FixtureDef boxFixtureDefPowerUp = new FixtureDef();
                        boxFixtureDefPowerUp.shape = boxShapePowerUp;
                        boxFixtureDefPowerUp.density = 1;
                        boxFixtureDefPowerUp.isSensor = true;

                        Fixture fixturePowerUp = powerUpBody.createFixture(boxFixtureDefPowerUp);
                        fixturePowerUp.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.PowerUp, powerUp));
                        boxShapePowerUp.dispose();

                        //Composant relatif à la salle dans laquelle il se trouve
                        powerUp.getComponent(SalleAssocieeComponent.class).idMap = lmc.idmap;
                        powerUp.getComponent(ActiveEntity.class).active = false;
                        powerUp.getComponent(PhysicsComponent.class).body = powerUpBody;
                        powerUp.getComponent(PowerUpTypeComponent.class).powerUpType= PowerUpType.SPEED_TEMPO;
                        powerUp.getComponent(PowerUpTypeComponent.class).duration=3000;
                        powerUp.getComponent(PowerUpTypeComponent.class).value=3f;
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
                        break;

                    case "piege":
                        Entity trap = getWorld().createEntity(trapArchetype);

                        //Body trap
                        BodyDef trapBodyDef = new BodyDef();
                        trapBodyDef.type = BodyDef.BodyType.StaticBody;
                        trapBodyDef.position.set(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurX - decalageX, Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurY - decalageY);
                        Body trapBody = box2dworld.createBody(trapBodyDef);

                        PolygonShape trapboxShape = new PolygonShape();
                        trapboxShape.setAsBox(0.2f, 0.2f);

                        FixtureDef trapboxFixtureDef = new FixtureDef();
                        trapboxFixtureDef.shape = trapboxShape;
                        trapboxFixtureDef.density = 1;

                        Fixture trapfixture = trapBody.createFixture(trapboxFixtureDef);
                        trapfixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Trap, trap));
                        trapboxShape.dispose();

                        trap.getComponent(ContactDamageComponent.class).setDamages(Integer.parseInt(so.get(i).getProperties().get("damages").toString()));

                        trap.getComponent(SalleAssocieeComponent.class).idMap = lmc.idmap;

                        trap.getComponent(PiegeActifComponent.class).action = true;

                        trap.getComponent(PhysicsComponent.class).body = trapBody;
                        break;

                    case "teleporteur":
                        Entity teleporteur = getWorld().createEntity(teleporteurArchetype);

                        //Body Teleporteur
                        BodyDef teleporteurBodyDef = new BodyDef();
                        teleporteurBodyDef.type = BodyDef.BodyType.StaticBody;
                        Float coordonneeX = Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurX - decalageX;
                        Float coordonneeY = Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurY - decalageY;
                        teleporteurBodyDef.position.set(coordonneeX, coordonneeY);
                        Body teleporteurBody = box2dworld.createBody(teleporteurBodyDef);

                        PolygonShape teleporteurboxShape = new PolygonShape();
                        teleporteurboxShape.setAsBox(0.2f, 0.2f);

                        FixtureDef teleporteurboxFixtureDef = new FixtureDef();
                        teleporteurboxFixtureDef.shape = teleporteurboxShape;
                        teleporteurboxFixtureDef.density = 1;

                        Fixture teleporteurfixture = teleporteurBody.createFixture(teleporteurboxFixtureDef);
                        teleporteurfixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Teleporteur, teleporteur));
                        teleporteurboxShape.dispose();

                        //idmap du teleporteur
                        teleporteur.getComponent(SalleAssocieeComponent.class).idMap = lmc.idmap;

                        //information tp component
                        teleporteur.getComponent(InformationTPComponent.class).idTeleporteurAssocie = Integer.parseInt(so.get(i).getProperties().get("TPRelie").toString());
                        teleporteur.getComponent(InformationTPComponent.class).idTeleporteur = Integer.parseInt(so.get(i).getProperties().get("IDTP").toString());
                        teleporteur.getComponent(InformationTPComponent.class).coordonneeX = coordonneeX;
                        teleporteur.getComponent(InformationTPComponent.class).coordonneeY = coordonneeY;
                        teleporteur.getComponent(InformationTPComponent.class).directionDeSortie = so.get(i).getProperties().get("directionSortie").toString();


                        teleporteur.getComponent(PhysicsComponent.class).body = teleporteurBody;

                        listeTpSalle.add(teleporteur);

                        break;
                }



            }

            //lier les teleporteurs entre eux
                for (Entity tps: listeTpSalle) {
                    //System.out.println(tps.getComponent(InformationTPComponent.class).);
                    for (Entity tpsTmp: listeTpSalle) {
                        if (tps.getComponent(InformationTPComponent.class).idTeleporteurAssocie == tpsTmp.getComponent(InformationTPComponent.class).idTeleporteur){
                            Float decalageTpX = 0.0f;
                            Float decalageTpY = 0.0f;
                            switch (tps.getComponent(InformationTPComponent.class).directionDeSortie){
                                case "droite" :
                                    decalageTpX = facteurCase;
                                    break;
                                case "gauche" :
                                    decalageTpX = -facteurCase;
                                    break;
                                case "haut" :
                                    decalageTpY = facteurCase;
                                    break;
                                case "bas" :
                                    decalageTpY = -facteurCase;
                                    break;
                            }
                            System.out.println();

                            tps.getComponent(InformationTPComponent.class).TPVersLaPositionX = tpsTmp.getComponent(InformationTPComponent.class).coordonneeX + decalageTpX;
                            tps.getComponent(InformationTPComponent.class).TPVersLaPositionY = tpsTmp.getComponent(InformationTPComponent.class).coordonneeY + decalageTpY;
                        }
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
