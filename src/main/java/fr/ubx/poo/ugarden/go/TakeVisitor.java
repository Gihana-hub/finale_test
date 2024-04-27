package fr.ubx.poo.ugarden.go;

import fr.ubx.poo.ugarden.go.bonus.Apple;
import fr.ubx.poo.ugarden.go.bonus.Insecticide;
import fr.ubx.poo.ugarden.go.bonus.Key;
import fr.ubx.poo.ugarden.go.bonus.PoisonedApple;

public interface TakeVisitor {

    // Key
    default void take(Key key) {
        key.remove();
    }
    default void take(Apple apple) {
        apple.remove();
    }
    default void take(PoisonedApple poisonedApple) {
        poisonedApple.remove();
    }
    default void take(Insecticide insecticide) {
        insecticide.remove();
    }
}
