/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go.decor.ground;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public abstract class Ground extends Decor {

    public Ground(Position position) {
        super(position);
    }
    public boolean walkableBy(Gardener gardener){ return gardener.getEnergy() - (energyConsumptionWalk() * gardener.getDiseaseLevel())>=0;}
}
