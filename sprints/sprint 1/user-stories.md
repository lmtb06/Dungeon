# Interface graphique
- En tant que joueur, je veux voir un écran de fin lorsque je quitte le dernier étage.
- En tant que joueur, je veux voir un écran de mort lorsque je tombe à 0 points de vie.

# Personnage
## Actions
- En tant que joueur, je veux que mon personnage se déplace horizontalement, verticalement et en diagonal en fonction des touches (flèche gauche/droite, flèche haut/bas, combinaison composante horizontale et verticale)
- En tant que joueur, je veux attaquer les ennemis qui se trouvent dans une certaine hitbox en appuyant sur une touche spécifique et en fonction de la direction que je vais appuyer (par défaut la hitbox sera vers la direction courante du joueur), afin de pouvoir combattre.
## Propriétés
- En tant que joueur, je veux que le jeu se termine si les points de vie de mon personnage arrivent à zéro, afin d’avoir un challenge.


# Niveaux
## Actions
- En tant que level designer, je veux que certaines cases ne soient pas traversables
- En tant que level designer, je veux que ma salle puisse être connectée à d’autres salles via des portes placées sur les murs
- En tant que level designer, je veux pouvoir placer des pièges et des téléporteurs pour amener de la difficulté et faire varier la manière dont le joueur doit appréhender chaque salle.

# Monstres
## Déplacements
En tant que level designer, je veux pouvoir placer différents monstres qui peuvent avoir un comportement parmi :
- Je veux pouvoir être immobile
## Attaques
En tant que level designer, je veux que les monstres que je place dans le niveau puissent posséder une façon d’attaquer le joueur, parmi :
- Infliger des dégâts lorsqu’il entre en contact avec le joueur
