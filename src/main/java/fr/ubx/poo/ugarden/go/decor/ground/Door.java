package fr.ubx.poo.ugarden.go.decor.ground;

import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.personage.Gardener;

public class Door extends Ground {
    public boolean isOpen = false;
    public Door(Position position) {
        super(position);
    }

    @Override
    public boolean walkableBy(Gardener gardener) {
       if (gardener.getKeys() >= 1){
           isOpen = true;
           return true;
       }
       return false;
    }
}
