/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go.bonus;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.Takeable;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public class Key extends Bonus implements Takeable {
    public Key(Position position, Decor decor) {
        super(position, decor);
    }

    @Override
    public void takenBy(Gardener gardener) {
        gardener.take(this);
        gardener.addKey();
        System.out.println("Key taken! You now have " + gardener.getKeys()+ " keys.");
    }
}
