package fr.ubx.poo.ugarden.go.personage;

import fr.ubx.poo.ugarden.engine.Timer;
import fr.ubx.poo.ugarden.game.Direction;
import fr.ubx.poo.ugarden.game.Game;
import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.Movable;
import fr.ubx.poo.ugarden.go.WalkVisitor;
import fr.ubx.poo.ugarden.go.Walkable;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.ground.Flowers;

public class Hornet extends GameObject implements Movable, WalkVisitor {
    private Direction direction;
    private final Timer timer = new Timer(game.configuration().hornetMoveFrequency());
    private final int index;
    private boolean spriteUp = false;

    public Hornet(Game game, Position position, int index) {
        super(game, position);
        this.direction = Direction.DOWN;
        this.index = index;
        timer.start();
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
        Gardener gardener = game.getGardener();
        Position nextPos = direction.nextPosition(getPosition());
        Decor next = game.world().getGrid().get(nextPos);
        setPosition(nextPos);
    }

    public void update(long now) {
        timer.update(now);
        if(!timer.isRunning()){
            Direction direction = Direction.random();
            while (!this.canMove(direction)){
                direction = Direction.random();
            }
            doMove(direction);
            timer.start();
        }

    }
    public void sting(Gardener gardener){
        if(gardener.getInsecticideCount() >= 1) {   //insecticide condition
            gardener.decreaseInsecticide();
        }
        else {
            gardener.hurt();
        }
        game.deleteHornet(index);
        remove();
        System.out.println("sting");
    }
    public Direction getDirection() {
        return direction;
    }
    public boolean canWalkOn(Flowers flowers) {
        return true;
    }

    public boolean isSpriteUp() {
        return spriteUp;
    }
    public void setSpriteUp(){
        spriteUp = true;
    }
}
