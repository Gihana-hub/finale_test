/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go.decor;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.Walkable;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.ground.Ground;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public class Hedgehog extends Decor implements Walkable {

    public Hedgehog(Position position) {
        super(position);
    }
}
