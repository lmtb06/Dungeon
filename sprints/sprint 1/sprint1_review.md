# Joueur
- Déplacements :
    - **Implémenté** : Déplacement vertical ou horizontal sur appui de l’une des quatre touches associées.
    - **Implémenté** : Déplacement diagonal en combinant une touche de déplacement vertical et horizontal.
- Propriété :
    - **En développement** : Points de vie : Lorsqu’ils sont amenés à 0, la partie est perdue.
    - **En développement** : Arme active : Définit la hitbox d’attaque du joueur et les dégâts qu’il inflige.
    -  **Implémenté** : Vitesse : Définit sa vitesse de déplacement.
    -  **En développement** : Power-ups : Ensemble des power-ups actifs.
# Niveaux
- Cases :
    - Les cases sont des unités d’espace pour chaque salle
    - Il en existe plusieurs types :
        - **Implémenté** : Murs : les murs sont des cases solides à travers lesquelles le joueur et la plupart des monstres ne peuvent pas passer.
        - **En développement** : Power-ups : ces cases vont générer un power-up aléatoire (ou non) que le joueur peut récupérer
            - Point de vie supplémentaire (Permanent)
            - Vitesse supplémentaire (Permanent et temporaire)
# Salles
- **Implémenté** : Contiennent plusieurs types de cases
# Monstres
- Déplacements :
    - **Implémenté** : Ennemi immobile