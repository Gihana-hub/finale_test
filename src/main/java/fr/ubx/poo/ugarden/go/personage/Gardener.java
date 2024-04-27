/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.go.personage;

import fr.ubx.poo.ugarden.game.Direction;
import fr.ubx.poo.ugarden.game.Game;
import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.Movable;
import fr.ubx.poo.ugarden.go.TakeVisitor;
import fr.ubx.poo.ugarden.go.WalkVisitor;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.Hedgehog;
import fr.ubx.poo.ugarden.engine.Timer;

public class Gardener extends GameObject implements Movable, TakeVisitor, WalkVisitor {

    private int energy;
    private Direction direction;
    private boolean moveRequested = false;
    private int insecticideCount;
    private int diseaseLevel;
    private int keys;
    private final int ENERGY_RECOVERY_AMOUNT = 1;
    private final Timer diseaseTimer = new Timer(game.configuration().diseaseDuration());
    private final Timer recoveTimer = new Timer(game.configuration().energyRecoverDuration());


    public Gardener(Game game, Position position) {

        super(game, position);
        this.direction = Direction.DOWN;
        this.energy = game.configuration().gardenerEnergy();  // Initialiser avec la configuration du jeu
        this.insecticideCount = 0;
        this.diseaseLevel = 1;
        this.keys = 0;
    }


    public int getEnergy() {
        return this.energy;
    }


    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            setModified(true);
        }
        moveRequested = true;
    }



    @Override
    public final boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        int height = game.world().getGrid().height() - 1;
        int width = game.world().getGrid().width() - 1;
        if(nextPos.x() < 0 || nextPos.x() > width || nextPos.y() < 0 || nextPos.y() > height)
            return false;
        Decor next = game.world().getGrid().get(nextPos);
        return next.walkableBy(this);
    }

    @Override
    public void doMove(Direction direction) {
        // Restart the timer
        Position nextPos = direction.nextPosition(getPosition());
        Decor next = game.world().getGrid().get(nextPos);
        setPosition(nextPos);
        if (next != null)
            next.takenBy(this);

        assert next != null;
        energy -= next.energyConsumptionWalk() * diseaseLevel;
    }

    public void update(long now) {
        recoveTimer.update(now);
        diseaseTimer.update(now);
        if (moveRequested) {
            if (canMove(direction)) {
                doMove(direction);
                recoveTimer.start();
            }
        } else {
            if(!recoveTimer.isRunning()){
                energy += ENERGY_RECOVERY_AMOUNT;
                energy = Math.min(energy, game.configuration().gardenerEnergy());
                recoveTimer.start();
            }
        }
        if(diseaseLevel > 1){
            if(!diseaseTimer.isRunning()){
                diseaseLevel--;
                diseaseTimer.start();
            }
        }
        moveRequested = false;
    }
    public void hurt(int damage) {
        energy-= damage;
    }

    public void hurt() {
        hurt(20);
    }

    public Direction getDirection() {
        return direction;
    }
    public boolean isWon(){return game.world().getGrid().get(getPosition()) instanceof Hedgehog;}

    // Add the new insecticide count methods
    public int getInsecticideCount() {
        return this.insecticideCount;
    }
    public int getDiseaseLevel() {
        return this.diseaseLevel;
    }

    public int getKeys() {
        return keys;
    }
    public void increaseEnergy(int i) {
        if(energy + i >= game.configuration().gardenerEnergy()){
            energy = game.configuration().gardenerEnergy();
        }
        else{
            energy += i;
        }
    }
    public void addKey() {
        keys++;
    }
    public void increaseInsecticide() {
        insecticideCount++;
    }
    public void decreaseInsecticide() {
        insecticideCount--;
    }
    public void increaseDiseaseLevel() {
        diseaseTimer.start();
        diseaseLevel++;
    }
    public void resetDiseaseLevel() {
        diseaseLevel = 1;
    }
    public void apple(){
        resetDiseaseLevel();
        increaseEnergy(game.configuration().energyBoost());
    }
}
