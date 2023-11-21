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
import com.dungeondevs.components.rendering.AnimationListComponent;
import com.dungeondevs.components.rendering.SpriteComponent;
import com.dungeondevs.utils.AnimationData;
import com.dungeondevs.utils.FixtureUserData;
import com.dungeondevs.utils.GameArchetypes;

import java.util.ArrayList;

/**
 * System d'initialiseur de salle. Le systeme va lire la tiledMap dans laquelle se trouve le joueur et afficher les différentes entités de la salle
 */
@All({LoadMapComponent.class})
public class RoomIntializerSystem extends EntityProcessingSystem {

    /**
     * attribut désignant le world dans lequel iront les bodys des différentes nouvelles entités
     */
    private World box2dworld;

    /**
     * attribut ayant pour rôle de désigner la salle actuelle, si cette dernière change, les entités seront supprimés et remplacées par celles de la nouvelle salle
     */
    public int salleActuelle = -1;

    private LoadMapComponent mapChargeeActuelle;

    /**
     * Le joueur, on en a besoin pour récupérer la salle dans laquelle il se trouve
     */
    public Entity joueur;

    /**
     * liste ayant pour but de stocker les différents bodys à supprimer du world au moment du changement de salle.
     */
    public Array<Entity> listeEntiteADesactiver;


    public RoomIntializerSystem(World world) {
        listeEntiteADesactiver = new Array<>();
        this.box2dworld = world;
    }

    @Override
    protected void process(Entity e) {

        /**
         * On vérifie si le joueur a traversé une porte, si c'est le cas, son idmap a changé et sera différent de la salle actuelle
         */
        if (joueur!=null && joueur.getComponent(SalleAssocieeComponent.class).idMap != salleActuelle ){

            if(mapChargeeActuelle != null)
                mapChargeeActuelle.loaded = false;

            /**
             * Comme notre systeme boucle sur toutes les maps
             * On va ici s'intéresser uniquement à la map dans laquelle se trouve le joueur en la désignant grace à un if
             */
            if (joueur.getComponent(SalleAssocieeComponent.class).idMap == e.getComponent(LoadMapComponent.class).idmap){

                LoadMapComponent lmc = e.getComponent(LoadMapComponent.class);

                /**
                 * Liste qui va stocker les différents téléporteurs pour pouvoir les liés après leurs créations
                 */
            ArrayList<Entity> listeTpSalle = new ArrayList();

                //this.box2dworld.getBodies(listeEntiteADesactiver);

                for (Entity entitsBody: listeEntiteADesactiver) {
                    PhysicsComponent physics = entitsBody.getComponent(PhysicsComponent.class);
                    if (physics != null && joueur.getComponent(PhysicsComponent.class).body != physics.body){
                        this.box2dworld.destroyBody(physics.body);
                        getWorld().deleteEntity(entitsBody);
                    }
                }
                listeEntiteADesactiver.clear();



                float facteurCase = 0.5f;

                float decalageX = facteurCase*-0.5f;
                float decalageY = facteurCase*-0.5f;
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
                            listeEntiteADesactiver.add(mur);
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

                Archetype armeArchetype = GameArchetypes.ARME_ARCHETYPE
                        .build(getWorld());


                for (int i = 0; i < so.getCount(); i++) {
                    System.out.println("siu:" +Float.parseFloat(so.get(i).getProperties().get("x spawn").toString()));

                    switch (so.get(i).getProperties().get("categorie").toString()){
                        case "monstre":
                            Entity monstre = getWorld().createEntity(monsterArchetype);
                            listeEntiteADesactiver.add(monstre);

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
                            monstre.getComponent(AnimationListComponent.class).addAnimationData(new AnimationData(1, 4, "./skeleton_idle.png", 0.5f));
                            monstre.getComponent(AnimationListComponent.class).setCurrentAnimation(0);

                            break;
                        case "powerUps":
                            Entity powerUp = getWorld().createEntity(powerUpArchetype);
                            listeEntiteADesactiver.add(powerUp);

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
                            powerUp.getComponent(SpriteComponent.class).setSprite("./powerup_speed.png");
                            break;

                        case "porte":
                            Entity porte = getWorld().createEntity(porteArchetype);
                            listeEntiteADesactiver.add(porte);

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
                            listeEntiteADesactiver.add(trap);

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

                        trap.getComponent(ActifSalleActuelleComponent.class).action = true;

                            trap.getComponent(PhysicsComponent.class).body = trapBody;
                            trap.getComponent(SpriteComponent.class).setSprite("./trap_pikes.png");
                            break;

                        case "teleporteur":
                            Entity teleporteur = getWorld().createEntity(teleporteurArchetype);
                            listeEntiteADesactiver.add(teleporteur);

                            //Body Teleporteur
                            BodyDef teleporteurBodyDef = new BodyDef();
                            teleporteurBodyDef.type = BodyDef.BodyType.StaticBody;
                            float coordonneeX = Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurX - decalageX;
                            float coordonneeY = Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurY - decalageY;
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
                            teleporteur.getComponent(SpriteComponent.class).setSprite("./test.png");


                            teleporteur.getComponent(PhysicsComponent.class).body = teleporteurBody;

                            listeTpSalle.add(teleporteur);

                        break;
                    case "arme":
                        Entity arme = getWorld().createEntity(armeArchetype);
                        listeEntiteADesactiver.add(arme);

                        //Body trap
                        BodyDef armeBodyDef = new BodyDef();
                        armeBodyDef.type = BodyDef.BodyType.StaticBody;
                        armeBodyDef.position.set(Float.parseFloat(so.get(i).getProperties().get("x spawn").toString())*facteurX - decalageX, Float.parseFloat(so.get(i).getProperties().get("y spawn").toString())*facteurY - decalageY);
                        Body armeBody = box2dworld.createBody(armeBodyDef);

                        PolygonShape armeboxShape = new PolygonShape();
                        armeboxShape.setAsBox(0.2f, 0.2f);

                        FixtureDef armeboxFixtureDef = new FixtureDef();
                        armeboxFixtureDef.shape = armeboxShape;
                        armeboxFixtureDef.density = 1;

                        Fixture armefixture = armeBody.createFixture(armeboxFixtureDef);
                        armefixture.setUserData(new FixtureUserData(FixtureUserData.EntityTypes.Arme, arme));
                        armeboxShape.dispose();

                        arme.getComponent(ArmeComponent.class).nomArme = so.get(i).getProperties().get("type").toString();

                        arme.getComponent(SalleAssocieeComponent.class).idMap = lmc.idmap;
                        arme.getComponent(ActifSalleActuelleComponent.class).action = true;

                        arme.getComponent(PhysicsComponent.class).body = armeBody;
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
                mapChargeeActuelle = lmc;
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

    public int getSalleActuelle() {
        return salleActuelle;
    }
}
