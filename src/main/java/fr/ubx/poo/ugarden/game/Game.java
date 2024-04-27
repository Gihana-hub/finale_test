package fr.ubx.poo.ugarden.game;

import fr.ubx.poo.ugarden.engine.GameEngine;
import fr.ubx.poo.ugarden.go.personage.Gardener;
import fr.ubx.poo.ugarden.go.personage.Hornet;
import fr.ubx.poo.ugarden.view.SpriteHornet;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;


public class Game {

    private final Configuration configuration;
    private final World world;
    private final Gardener gardener;
    private List<Hornet> hornets;
    private boolean switchLevelRequested = false;
    private int switchLevel;
    public Game(World world, Configuration configuration, Position gardenerPosition) {
        this.configuration = configuration;
        this.world = world;
        gardener = new Gardener(this, gardenerPosition);
        hornets = new ArrayList<>();
    }

    public Configuration configuration() {
        return configuration;
    }

    public Gardener getGardener() {
        return this.gardener;
    }

    public World world() {
        return world;
    }

    public boolean isSwitchLevelRequested() {
        return switchLevelRequested;
    }

    public int getSwitchLevel() {
        return switchLevel;
    }

    public void requestSwitchLevel(int level) {
        this.switchLevel = level;
        switchLevelRequested = true;
    }

    public void clearSwitchLevel() {
        switchLevelRequested = false;
    }

    public void addHornet(Position position){
        int index = getSizeHornets() + 1;
        Hornet hornet = new Hornet(this, position, index);
        hornets.add(hornet);
    }
    public Hornet getHornet(int i){
        return hornets.get(i);
    }
    public int getSizeHornets(){
        return hornets.size();
    }
    public void deleteHornet(int i){
        hornets.remove(i);
    }
}
