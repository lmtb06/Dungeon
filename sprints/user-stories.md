# Interface graphique
- En tant que joueur, je veux voir mes points de vie restants, afin de jauger le niveau de risque que je peux prendre sans mourir.
- En tant que joueur, je veux avoir une minimap qui met en évidence la salle où je suis, afin de pouvoir me situer dans l’étage.
- En tant que joueur, je veux savoir le numéro de l’étage dans lequel je suis, afin de savoir combien d'étages il me reste pour arriver au dernier niveau.
- En tant que joueur, je veux voir les power-ups actifs avec des icônes dédiées, afin de jauger rapidement de leur effets.
- En tant que joueur, je veux avoir un retour visuel rapide du temps qu’il me reste pour les différents power-up.
- En tant que joueur, je veux voir une icône dédiée pour l’arme que j’équipe, afin de savoir quel genre d’attaques je peux effectuer.
# Personnage
## Actions
- En tant que joueur, je veux que mon personnage se déplace horizontalement, verticalement et en diagonal en fonction des touches (flèche gauche/droite, flèche haut/bas, combinaison composante horizontale et verticale)
- En tant que joueur, je veux attaquer les ennemis qui se trouvent dans une certaine hitbox en appuyant sur une touche spécifique et en fonction de la direction que je vais appuyer (par défaut la hitbox sera vers la direction courante du joueur), afin de pouvoir combattre.
- En tant que joueur, je veux tirer des projectiles si mon arme courante le permet, afin de diversifier mes options de combat.
## Propriétés
- En tant que joueur, je veux que le jeu se termine si les points de vie de mon personnage arrivent à zéro, afin d’avoir un challenge.
- En tant que joueur, je veux que mon arme courante, définisse la hitbox de mes attaques et les dégâts infligés, afin de pouvoir choisir ma stratégie de combat.
- En tant que joueur, je veux que ma vitesse affecte mon déplacement, afin que les power-up puissent me permettre de naviguer plus rapidement dans le niveau.
- En tant que joueur, je veux pouvoir améliorer les statistiques de mon personnage de façon temporaire et/ou permanente grâce à des power-ups, afin d’améliorer mon expérience de jeu et d’avoir une sensation de progression.
- En tant que joueur, je veux que mon personnage soit invincible pendant un court instant, afin de ne pas me retrouver coincé entre plusieurs monstres et mourir sans pouvoir rien faire.


# Niveaux
## Actions
- En tant que level designer, je souhaite pouvoir charger une carte via à un fichier (monstres, cases et power ups), afin de pouvoir créer des niveaux
- En tant que level designer, je veux que certaines cases ne soient pas traversables
- En tant que level designer, je veux pouvoir mettre différents power ups dans les cartes pour améliorer le personnage durant un laps de temps
- En tant que level designer, je veux que ma salle puisse être connectée à d’autres salles via des portes placées sur les murs
- En tant que level designer, je veux pouvoir placer des escalier permettant d’accéder au prochain étage et de fixer un objectif au joueur au sein de l’étage
- En tant que level designer, je veux pouvoir placer des pièges et des téléporteurs pour amener de la difficulté et faire varier la manière dont le joueur doit appréhender chaque salle.
- En tant que level designer, je veux pouvoir choisir la position initiale de différents monstres et du joueur au sein de chacune des salles
- En tant que level designer, je veux que le jeu génère automatiquement un assemblage de salles pour créer un étage ayant un escalier à la fin et un nombre défini de couloirs subsidiaires.
- En tant que level designer, Je veux pouvoir mettre à disposition dans les niveaux des armes qui permettent au joueur de changer la hitbox de ses attaques

# Monstres
## Déplacements
En tant que level designer, je veux pouvoir placer différents monstres qui peuvent avoir un comportement parmi :
- Je veux me déplacer en direction du joueur, tout en contournant les murs grâce à un pathfinding
- Je veux me déplacer en direction du joueur, tout en ignorant les murs et en les traversant
- Je veux pouvoir patrouiller entre deux positions définies au préalable dans une salle
- Je veux pouvoir être immobile
- Je veux charger le joueur lorsqu’il entre dans ma zone de détection qui est définie pour chaque monstre
## Attaques
En tant que level designer, je veux que les monstres que je place dans le niveau puissent posséder une façon d’attaquer le joueur, parmi :
- Infliger des dégâts lorsqu’il entre en contact avec le joueur
- Infliger des dégâts lorsque le joueur entre en contact avec des projectiles qui ont été créés par un monstre
