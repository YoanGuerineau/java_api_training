package fr.lernejo.navy_battle.game.boats;

import fr.lernejo.navy_battle.game.board.Cell;
import fr.lernejo.navy_battle.game.board.Position;

import java.util.HashSet;
import java.util.Set;

public abstract class Boat {

    private final HashSet<Cell> position = new HashSet<>();

    public boolean isAlive() {
        boolean isAlive = false;
        for ( Cell cell: this.position ) {
            if( cell.getCurrentState().equals(Cell.states.OCCUPIED.toString())) {
                isAlive = true;
                break;
            }
        }
        return isAlive;
    }

    public HashSet<Cell> getPosition() {
        return this.position;
    }

}
