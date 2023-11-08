# Joueur
- Actions
  - **Implémenté** Je veux pouvoir tuer des monstres et les faire disparaitre
  - **Implémenté** Je veux pouvoir améliorer les statistiques de mon personnage de façon temporaire et/ou permanente grâce à des power-ups, afin d’améliorer mon expérience de jeu et d’avoir une sensation de progression.
  - **Implémenté** Je veux attaquer les ennemis qui se trouvent dans une certaine hitbox en appuyant sur une touche spécifique, afin de pouvoir combattre.
- Propriété :
    - **Implémenté** : Points de vie : Lorsqu’ils sont amenés à 0, la partie est perdue.
    - **Implémenté** : En tant que joueur, je veux que ma vitesse affecte mon déplacement, afin que les power-up puissent me permettre de naviguer plus rapidement dans le niveau.
    - **Implémenté** : Power-ups : Ensemble des power-ups actifs.
    - **Implémenté** Je veux que le jeu se termine si les points de vie de mon personnage arrivent à zéro, afin d’avoir un challenge.
    - **Implémenté** Je veux que mon personnage soit invincible pendant 2 secondes après avoir reçu des dégâts, afin de ne pas me retrouver coincé entre plusieurs monstres et mourir sans pouvoir rien faire.
    - **Implémenté** Je veux que ma vitesse affecte mon déplacement, afin que les power-up puissent me permettre de naviguer plus rapidement dans le niveau.
# Niveaux
- Cases :
    - Il en existe plusieurs types :
        - **Implémenté** : Power-ups : ces cases vont générer un power-up aléatoire (ou non) que le joueur peut récupérer
            - Vitesse supplémentaire (Permanent et temporaire)
- Actions
  - **Implémenté** Je veux pouvoir placer des monstres immobile dans le niveau
  - **Implémenté** Je souhaite pouvoir charger une carte via à un fichier (monstres, cases et power ups), afin de pouvoir créer des niveaux
  - **Implémenté** Je veux pouvoir choisir la position initiale de différents monstres et du joueur au sein de chacune des salles
  - **Implémenté** Je veux pouvoir mettre différents power ups dans les cartes pour améliorer le personnage durant un laps de temps
  - **Implémenté** Je veux que ma salle puisse être connectée à d’autres salles via des portes placées sur les murs
  - **Implémenté** Je veux que certaines cases ne soient pas traversables

# Monstres
- Attaques
  - **Implémenté** Je veux que certains monstres infligent des dégâts au personnage du joueur quand il entre en contact avec
  - **Implémenté** Je veux pouvoir placer des pièges et des téléporteurs pour amener de la difficulté et faire varier la manière dont le joueur doit appréhender chaque salle.

# Interface graphique
  - **Implémenté** Je veux que le jeu affiche un écran de 'Game Over' avec une option de recommencer lorsque les points de vie de mon personnage arrivent à zéro, afin d’avoir un challenge et de comprendre la conséquence de la perte de tous mes points de vie
  - **Implémenté** Je veux voir mes points de vie restants, afin de jauger le niveau de risque que je peux prendre sans mourir.


# Fonctionnalités re-implementées

# Joueur
- Déplacements :
  - **Implémenté** : Déplacement vertical ou horizontal sur appui de l’une des quatre touches associées.
  - **Implémenté** : Déplacement diagonal en combinant une touche de déplacement vertical et horizontal.
- Propriété :
  - **Implémenté** : En tant que joueur, je veux attaquer les ennemis qui se trouvent dans une certaine hitbox en appuyant sur une touche spécifique afin de pouvoir combattre.

# Niveaux
- Cases :
  - Les cases sont des unités d’espace pour chaque salle
  - Il en existe plusieurs types :
  - **Implémenté** : Murs : les murs sont des cases solides à travers lesquelles le joueur et la plupart des monstres ne peuvent pas passer.

# Salles
- **Implémenté** : Contiennent plusieurs types de cases
# Monstres
- Déplacements :
  - **Implémenté** : Ennemi immobile










