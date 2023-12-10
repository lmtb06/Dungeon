package com.dungeondevs.components.rendering;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *  Composant permettant de gérer les animations.
 */
public class AnimationComponent extends Component {

    /**
     * Animation actuelle à jouer.
     */
    private Animation<TextureRegion> animation;
    /**
     * Temps d'état de l'animation, permettant de savoir à quel frame on se trouve afin de pouvoir afficher l'animation correctement.
     */
    private float stateTime;

    /**
     *  Constructeur simple prenant une animation en paramètre, et initialisant le temps d'état de l'animation à 0.
     *  @param animation l'animation à jouer.
     */
    public AnimationComponent (Animation<TextureRegion> animation) {
        this.animation = animation;
        this.stateTime = 0f;
    }

    // region SETTERS

    /**
     * Modifie l'animation courante.
     * @param animation la nouvelle animation.
     */
    public void setAnimation (Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    /**
     *  Modifie le temps d'état actuel de l'animation.
     *  @param stateTime le nouveau temps d'état.
     */
    public void setStateTime (float stateTime) {
        this.stateTime = stateTime;
    }

    // endregion

    // region GETTERS

    /**
     *  Retourne l'animation actuelle du composant.
     *  @return l'animation du composant.
     */
    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    /**
     *  Retourn le temps d'état actuel.
     *  @return le temps d'état.
     */
    public float getStateTime () {
        return stateTime;
    }

    // endregion
}
