# UGarden 2024

légende:

    * -> terminé
    / -> commencé 

Premiers pas

Dans cette première version, les frelons sont immobiles. Nous utiliserons pour le moment la carte par défaut.

    *Modifiez le code pour afficher tous les éléments ainsi que les frelons et le hérisson. Vous pouvez maintenant utiliser la carte MapLevelDefault à la place de MapLevelDefaultStart.
    *Modifiez le code pour que les mouvements du jardinier soient limités par le cadre de la carte et les éléments de décor.
    /Faire en sorte que le jardinier perde de l’énergie lorsqu’il touche un frelon.
    *Faire en sorte que la partie se termine par une défaite si le jardinier meurt.
    *Faire en sorte que la partie se termine par une victoire si le jardinier retrouve le hérisson.
    *Mettre à jour le panneau d’affichage avec : niveau de fatigue, nombre de clés et niveau d’énergie
    *Faire en sorte que le jardinier dépense de l’énergie quand il se déplace
    *Permettre au jardinier de récupérer de l’énergie quand il reste immobile assez longtemps
    Faire en sorte que le jardinier puisse ramasser les bonus

Donnons vie aux frelons

    *La ruche produit un nouveau frelon toutes les 10 secondes.
    /Faire en sorte que chaque frelon se déplace tout seul de manière aléatoire
    *Un frelon doit mourir quand il pique le jardinier
    *La vitesse de déplacement d’un frelon est définie par la variable de configuration hornetMoveFrequency qui indique le nombre de fois par seconde qu’un frelon peut bouger.

Gestion des mondes

Dans la version de base, le jeu ne dispose que d’un seul niveau codé en dur dans le code. Nous allons maintenant charger une configuration complète de jeu depuis un fichier. Vous trouverez un répertoire world à la racine du projet avec deux fichiers d’extension .properties représentant un monde avec 2 niveaux (avec ou sans compression des niveaux). Nous utilisons le codage LRE vu en TD avec ou sans compression en fonction du paramètre compression dans le fichier de configuration.

    Permettre le chargement d’une carte depuis un fichier
    Gérez les portes ouvertes, le ramassage des clés et l’ouverture des portes fermées.

Bombes insecticides

    *À chaque fois qu’un frelon sort de la ruche, une bombe insecticide apparait sur une case aléatoire (herbe) qui ne contenait pas déjà un bonus.
    Le jardinier peut ramasser des bombes insecticides (le compteur est mis à jour)
    Si le jardinier dispose d’une bombe insecticide quand il rencontre un frelon, il utilise sa bombe pour tuer le frelon et ne se fait pas piquer, mais il perd sa bombe.
