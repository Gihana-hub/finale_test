package fr.ubx.poo.ugarden.go.bonus;

import fr.ubx.poo.ugarden.engine.Timer;
import fr.ubx.poo.ugarden.game.Direction;
import fr.ubx.poo.ugarden.game.Game;
import fr.ubx.poo.ugarden.game.Position;
import fr.ubx.poo.ugarden.go.GameObject;
import fr.ubx.poo.ugarden.go.decor.Decor;
import fr.ubx.poo.ugarden.go.decor.ground.Grass;

public class Nest extends Bonus {
    private final Timer timer = new Timer(10);
    private Game game;

    public Nest(Position position, Decor decor, Game game) {
        super(position, decor);
        this.game = game;
    }
    @Override
    public void update(long now) {
        timer.update(now);
        if(!timer.isRunning()){
            createHornet();
            createInsecticide();
            timer.start();
        }
    }

    private void createHornet(){
        Position pos = this.getPosition();
        game.addHornet(pos);
    }
    private  void createInsecticide(){
        Position pos = Direction.randomPos(getPosition().level(), game);
        while(!(game.world().getGrid().get(pos) instanceof Grass && game.world().getGrid().get(pos).getBonus() == null)){
            pos = Direction.randomPos(getPosition().level(), game);
        }
        Insecticide insecticide = new Insecticide(pos, game.world().getGrid().get(pos));
        game.world().getGrid().get(pos).setBonus(insecticide);
    }
}
