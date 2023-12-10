package com.dungeondevs.components.rendering;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 *  Composant en charge de contenir les données d'un sprite, utilisé pour son affichage en jeu.
 */
public class SpriteComponent extends Component {

    /**
     *  Booléen déterminant si le sprite doit s'afficher ou non.
     */
    private boolean show;
    /**
     *  TextureRegion permettant de savoir quelle section du sprite afficher.
     */
    private TextureRegion textureRegion;
    /**
     *  Position à laquelle le sprite doit s'afficher.
     */
    private Vector3 position;
    /**
     *  Rotation à laquelle le sprite doit s'afficher, en degrés.
     */
    private float rotation;


    // region CONSTRUCTEURS

    /**
     *  Constructeur basique du SpriteComponent, qui crée un Sprite avec une TextureRegion donnée à une position donnée.
     *  @param textureRegion la TextureRegion du sprite.
     *  @param position la position à laquelle doit s'afficher le sprite.
     */
    /*public SpriteComponent (TextureRegion textureRegion, Vector3 position) {
        this(textureRegion, position, 0, true);
    }*/

    /**
     *  Constructeur du SpriteComponent utilisant une TextureRegion à une position et une rotation données.
     *  @param textureRegion la TextureRegion du sprite.
     *  @param position la position à laquelle doit s'afficher le sprite.
     *  @param rotation la rotation du sprite.
     */
    /*public SpriteComponent (TextureRegion textureRegion, Vector3 position, float rotation) {
        this(textureRegion, position, rotation, true);
    }*/

    /**
     *  Constructeur du SpriteComponent utilisant une TextureRegion à une position et une rotation données, et qui s'affiche ou non en fonction de la valeur show fournie.
     *  @param textureRegion la TextureRegion du sprite.
     *  @param position la position à laquelle doit s'afficher le sprite.
     *  @param rotation la rotation du sprite.
     *  @param show booléen indiquant si le sprite doit être affiché ou non.
     */
    /*public SpriteComponent (TextureRegion textureRegion, Vector3 position, float rotation, boolean show) {
        this.textureRegion = textureRegion;
        this.position = position;
        this.rotation = rotation;
        this.show = show;
    }*/

    // endregion

    // region SETTERS

    /**
     *  Modifie la TextureRegion du sprite.
     *  @param textureRegion la nouvelle TextureRegion du sprite.
     */
    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    /**
     *  Modifie la position du sprite.
     *  @param position la nouvelle position du sprite.
     */
    public void setPosition (Vector3 position) {
        this.position = position;
    }

    /**
     *  Modifie la rotation du sprite.
     *  @param rotation le nouvel angle du sprite, en degré.
     */
    public void setRotation (float rotation) {
        this.rotation = rotation;
    }

    /**
     *  Modifie la visibilité du sprite.
     *  @param show booléen indiquant si le sprite doit être affiché ou non.
     */
    public void setVisible (boolean show) {
        this.show = show;
    }

    // endregion

    // region GETTERS

    /**
     *  Retourne la TextureRegion du sprite, utilisée pour son affichage.
     *  @return la TextureRegion du sprite.
     */
    public TextureRegion getTextureRegion () {
        return textureRegion;
    }

    /**
     *  Retourne la position actuelle du sprite.
     *  @return la position du sprite.
     */
    public Vector3 getPosition () {
        return position;
    }

    /**
     *  Retourne la rotation actuelle du sprite.
     *  @return la rotation du sprite.
     */
    public float getRotation () {
        return rotation;
    }

    /**
     *  Retourne un booléen indiquant si le sprite est affiché ou non.
     *  @return true si le sprite est visible, false sinon.
     */
    public boolean isVisible () {
        return show;
    }

    // endregion
}
