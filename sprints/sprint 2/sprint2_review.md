

# Joueur
- Déplacements :
    - **Implémenté** : Déplacement vertical ou horizontal sur appui de l’une des quatre touches associées.
    - **Implémenté** : Déplacement diagonal en combinant une touche de déplacement vertical et horizontal.
- Propriété :
    - **Implémenté** : Points de vie : Lorsqu’ils sont amenés à 0, la partie est perdue.
    - **Implémenté** : En tant que joueur, je veux attaquer les ennemis qui se trouvent dans une certaine hitbox en appuyant sur une touche spécifique et en fonction de la direction que je vais appuyer (par défaut la hitbox sera vers la direction courante du joueur), afin de pouvoir combattre.
    - **Implémenté** : En tant que joueur, je veux que ma vitesse affecte mon déplacement, afin que les power-up puissent me permettre de naviguer plus rapidement dans le niveau.
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