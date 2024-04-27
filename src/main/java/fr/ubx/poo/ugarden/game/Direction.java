/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ugarden.game;

import java.util.Random;

public enum Direction {
    UP {
        @Override
        public Position nextPosition(Position pos, int delta) {
            return new Position(pos.level(), pos.x(), pos.y() - delta);
        }
    },
    RIGHT {
        @Override
        public Position nextPosition(Position pos, int delta) {
            return new Position(pos.level(), pos.x() + delta, pos.y());
        }
    },
    DOWN {
        @Override
        public Position nextPosition(Position pos, int delta) {
            return new Position(pos.level(), pos.x(), pos.y() + delta);
        }
    },
    LEFT {
        @Override
        public Position nextPosition(Position pos, int delta) {
            return new Position(pos.level(), pos.x() - delta, pos.y());
        }
    },
    ;

    private static final Random randomGenerator = new Random();

    public static Direction random() {
        int i = randomGenerator.nextInt(values().length);
        return values()[i];
    }

    public abstract Position nextPosition(Position pos, int delta);

    public Position nextPosition(Position pos) {
        return nextPosition(pos, 1);
    }

    public static Position randomPos(int level, Game game) {
        int x = randomGenerator.nextInt(game.world().getGrid().height());
        int y = randomGenerator.nextInt(game.world().getGrid().width());
        return new Position(level , x, y);
    }
}